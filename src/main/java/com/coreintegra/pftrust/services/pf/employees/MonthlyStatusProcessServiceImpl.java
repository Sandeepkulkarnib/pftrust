package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.TotalContributionDTO;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.*;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.contribution.ContributionRepository;
import com.coreintegra.pftrust.repositories.pf.employee.*;
import com.coreintegra.pftrust.searchutil.SearchEmployeeSpecification;
import com.coreintegra.pftrust.services.pf.contribution.ContributionService;
import com.coreintegra.pftrust.services.pf.settlement.SettlementService;
import com.coreintegra.pftrust.services.pf.transferout.TransferOutService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

@Service
public class MonthlyStatusProcessServiceImpl implements MonthlyStatusProcessService {

    private final Logger logger = LoggerFactory.getLogger(MonthlyStatusProcessService.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeMonthlyContributionStatusRepository employeeMonthlyContributionStatusRepository;
    private final ContributionRepository contributionRepository;
    private final ContributionStatusRepository contributionStatusRepository;
    private final IoInterestMonthsRepository ioInterestMonthsRepository;
    private final EmployeeMonthlyStatusReportRepository employeeMonthlyStatusReportRepository;
    private final SettlementService settlementService;
    private final TransferOutService transferOutService;
    private final ContributionService contributionService;

    public MonthlyStatusProcessServiceImpl(EmployeeRepository employeeRepository,
                                           EmployeeMonthlyContributionStatusRepository employeeMonthlyContributionStatusRepository,
                                           ContributionRepository contributionRepository,
                                           ContributionStatusRepository contributionStatusRepository,
                                           IoInterestMonthsRepository ioInterestMonthsRepository,
                                           EmployeeMonthlyStatusReportRepository employeeMonthlyStatusReportRepository,
                                           SettlementService settlementService, TransferOutService transferOutService,
                                           ContributionService contributionService) {
        this.employeeRepository = employeeRepository;
        this.employeeMonthlyContributionStatusRepository = employeeMonthlyContributionStatusRepository;
        this.contributionRepository = contributionRepository;
        this.contributionStatusRepository = contributionStatusRepository;
        this.ioInterestMonthsRepository = ioInterestMonthsRepository;
        this.employeeMonthlyStatusReportRepository = employeeMonthlyStatusReportRepository;
        this.settlementService = settlementService;
        this.transferOutService = transferOutService;
        this.contributionService = contributionService;
    }


    @Override
    public void performMemberWise(String pernNumber, Integer financialYear, Integer financialMonth, Boolean isDryRun)
            throws JPAException {

        Optional<Employee> optionalEmployee = employeeRepository
                .findByPernNumberAndIsActive(pernNumber, true);

        if(optionalEmployee.isEmpty()) throw new JPAException("Employee not found.");

        Employee employee = optionalEmployee.get();

        performMemberWise(employee, financialYear, financialMonth, isDryRun, null);

    }

    @Override
    public void performUnitCodeWise(String unitCode, Integer financialYear, Integer financialMonth, Boolean isDryRun) {

        JSONArray unitCodes = new JSONArray();
        unitCodes.put(unitCode);

        SearchEmployeeSpecification employeeSpecification = new SearchEmployeeSpecification();
        Specification<Employee> unitCodeSpecification = employeeSpecification.unitCodeSpecification(unitCodes);

        Pageable pageable = PageRequest.of(0, 5000, Sort.by(Sort.Direction.ASC,
                "unitCode", "pernNumber")).first();

        boolean hasNext = true;

        while (hasNext) {

            Page<Employee> page = employeeRepository.findAll(unitCodeSpecification, pageable);

            page.get().forEach(employee -> {
                performMemberWise(employee, financialYear, financialMonth, isDryRun, null);
            });

            hasNext = page.hasNext();
        }

    }

    @ApplyTenantFilter
    @Async
    public CompletableFuture<String> performMemberWiseAsync(Employee employee, Integer financialYear,
                                                     Integer financialMonth, Boolean isDryRun, Job job) {
        performMemberWise(employee, financialYear, financialMonth, isDryRun, job);
        return CompletableFuture.completedFuture("done");
    }

    @ApplyTenantFilter
    private void performMemberWise(Employee employee, Integer financialYear, Integer financialMonth, Boolean isDryRun,
                                   Job job) {

        EmployeeMonthlyContributionStatus employeeMonthlyContributionStatus = new EmployeeMonthlyContributionStatus();

        EmployeeMonthlyStatusReport monthlyStatusReport = new EmployeeMonthlyStatusReport();

        if(employee.getContributionStatus().getSymbol().equalsIgnoreCase("s") ||
        employee.getContributionStatus().getSymbol().equalsIgnoreCase("m")){

            logger.info("employee {} is settled updating status", employee.getPernNumber());

            saveEmployeeMonthlyContributionStatus(employee, financialYear, financialMonth,
                    employee.getContributionStatus(), employeeMonthlyContributionStatus, isDryRun, job);

            saveStatusReportForSettlement(employee, employee.getContributionStatus(),
                    financialYear, financialMonth, job, monthlyStatusReport);

            return;
        }

        Optional<Contribution> optionalContribution = contributionRepository
                .findTop1ByEmployeeAndYearAndSubTypeAndIsActive(employee, financialYear, financialMonth, true);

        if(optionalContribution.isEmpty()) {

            ContributionStatus contributionStatus = contributionStatusRepository
                    .findBySymbolAndIsActive("I", true);

            logger.info("employee {} is in active updating status", employee.getPernNumber());

            saveEmployeeMonthlyContributionStatus(employee, financialYear, financialMonth,
                    contributionStatus, employeeMonthlyContributionStatus, isDryRun, job);

            saveStatusReportForInActive(employee, financialYear, financialMonth, job, monthlyStatusReport, contributionStatus);

            logger.info("checking io condition for employee {}", employee.getPernNumber());

            checkIOCondition(employee, financialYear, financialMonth, employeeMonthlyContributionStatus, isDryRun, job,
                    monthlyStatusReport);

            return;
        }

        Contribution contribution = optionalContribution.get();

        ContributionStatus contributionStatus = contributionStatusRepository
                .findBySymbolAndIsActive("A", true);

        saveEmployeeMonthlyContributionStatus(employee, financialYear, financialMonth,
                contributionStatus, employeeMonthlyContributionStatus, isDryRun, job);

        saveStatusReportForActive(employee, contributionStatus, contribution, job, financialYear, financialMonth,
                monthlyStatusReport);

    }

    private void saveStatusReportForActive(Employee employee, ContributionStatus contributionStatus, Contribution contribution,
                                           Job job, Integer financialYear, Integer financialMonth,
                                           EmployeeMonthlyStatusReport monthlyStatusReport){

        Date recoveryDate = contribution.getRecoveryDate();

        if(recoveryDate == null) {
            recoveryDate = FinancialYearAndMonth.getStandardRecoveryDate(contribution.getYear(),
                    contribution.getSubType());
        }

        TotalContributionDTO totalContribution = contributionService.getTotalContribution(employee);

        saveEmployeeMonthlyStatusReport(monthlyStatusReport, employee, contributionStatus,
                financialYear, financialMonth, contribution.getMemberContribution(), contribution.getCompanyContribution(),
                contribution.getVpfContribution(), totalContribution.total(),
                recoveryDate, null, null, job);

    }


    private void saveStatusReportForInActive(Employee employee, Integer financialYear, Integer financialMonth, Job job,
                                             EmployeeMonthlyStatusReport monthlyStatusReport,
                                             ContributionStatus contributionStatus) {

        Date recoveryDate = getRecoveryDate(employee);

        TotalContributionDTO totalContribution = contributionService.getTotalContribution(employee);

        saveEmployeeMonthlyStatusReport(monthlyStatusReport, employee, contributionStatus,
                financialYear, financialMonth, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, totalContribution.total(), recoveryDate,
                null, null, job);
    }

    private Date getRecoveryDate(Employee employee) {

        Optional<Contribution> optionalContribution = contributionRepository
                .findTop1ByEmployeeAndSubTypeGreaterThanAndIsActiveOrderByYearDescSubTypeDesc(
                        employee, 0, true);

        Date recoveryDate = null;

        if(optionalContribution.isPresent()){

            Contribution lastContribution = optionalContribution.get();

            if(lastContribution.getRecoveryDate() != null){
                recoveryDate = lastContribution.getRecoveryDate();
            }else {
                recoveryDate = FinancialYearAndMonth.getStandardRecoveryDate(lastContribution.getYear(),
                        lastContribution.getSubType());
            }

        }
        return recoveryDate;
    }

    private void saveStatusReportForIO(Employee employee, ContributionStatus contributionStatus,
                                       Integer financialYear, Integer financialMonth, Job job,
                                       EmployeeMonthlyStatusReport monthlyStatusReport, IoInterestMonths ioInterestMonths) {

        Date ioDate = ioInterestMonths.getDate();

        Date recoveryDate = getRecoveryDate(employee);

        TotalContributionDTO totalContribution = contributionService.getTotalContribution(employee);

        saveEmployeeMonthlyStatusReport(monthlyStatusReport, employee, contributionStatus, financialYear, financialMonth,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, totalContribution.total(), recoveryDate,
                null, ioDate, job);

    }

    private void saveStatusReportForSettlement(Employee employee, ContributionStatus contributionStatus,
                                               Integer financialYear, Integer financialMonth,
                                               Job job, EmployeeMonthlyStatusReport monthlyStatusReport) {
        Date settlementDate = null;

        Optional<Settlement> completedSettlement = settlementService.getCompletedSettlement(employee);

        if(completedSettlement.isPresent()){
            settlementDate = completedSettlement.get().getDateOfSettlement();
        }else {

            Optional<TransferOut> completedTransferOut = transferOutService.getCompletedTransferOut(employee);

            if(completedTransferOut.isPresent()){
                settlementDate = completedTransferOut.get().getDueDate();
            }

        }

        Date recoveryDate = getRecoveryDate(employee);

        saveEmployeeMonthlyStatusReport(monthlyStatusReport, employee, contributionStatus, financialYear,
                financialMonth, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                recoveryDate, settlementDate, null, job);
    }

    private void checkIOCondition(Employee employee, Integer financialYear, Integer month,
                                  EmployeeMonthlyContributionStatus employeeMonthlyContributionStatus,
                                  Boolean isDryRun, Job job, EmployeeMonthlyStatusReport monthlyStatusReport) {

        Integer age = employee.getEmployeeAge();

        logger.info("employee {} age {}", employee.getPernNumber(), age);

        Optional<Contribution> optionalContribution = contributionRepository
                .findTop1ByEmployeeAndSubTypeGreaterThanAndIsActiveOrderByYearDescSubTypeDesc(employee, 0,
                        true);

        logger.info("contribution -> {}", optionalContribution.isEmpty());

        if(optionalContribution.isEmpty()) return;

        Contribution contribution = optionalContribution.get();

        Date lastRecoveryDate = FinancialYearAndMonth.getStandardRecoveryDate(contribution.getYear(), contribution.getSubType());

        logger.info("year -> {}, month -> {}", contribution.getYear(), contribution.getSubType());

        logger.info("employee {}, last recovery date {}", employee.getPernNumber(), lastRecoveryDate);

        Period lastRecoveryPeriod = DateFormatterUtil.period(lastRecoveryDate);

        int lastRecoveryMonths = (lastRecoveryPeriod.getYears() * 12) + (lastRecoveryPeriod.getMonths());

        logger.info("last recovery period {}", lastRecoveryMonths);

        if(age >= 63 && lastRecoveryMonths >= 36){

            ContributionStatus contributionStatus = contributionStatusRepository
                    .findBySymbolAndIsActive("IO", true);

            logger.info("employee {} is IO updating status", employee.getPernNumber());

            saveEmployeeMonthlyContributionStatus(employee, financialYear, month, contributionStatus,
                    employeeMonthlyContributionStatus, isDryRun, job);

            logger.info("calculate number of months for interest calculation.");

            IoInterestMonths ioInterestMonths = calculateIOInterestMonths(employee, lastRecoveryDate,
                    employeeMonthlyContributionStatus, isDryRun);

            saveStatusReportForIO(employee, contributionStatus, financialYear, month, job,
                    monthlyStatusReport, ioInterestMonths);

        }

    }

    private IoInterestMonths calculateIOInterestMonths(Employee employee, Date lastRecoveryDate,
                                           EmployeeMonthlyContributionStatus employeeMonthlyContributionStatus,
                                           Boolean isDryRun) {

        Date dateOfBirth = employee.getDateOfBirth();

        Calendar dateDetailsRec = FinancialYearAndMonth.getDateDetails(lastRecoveryDate);

        dateDetailsRec.add(Calendar.MONTH, 36);
        dateDetailsRec.add(Calendar.DAY_OF_MONTH, -1);

        Date completed36MonthsBasedRecDate = dateDetailsRec.getTime();

        logger.info("last recovery date -> {}, completed36MonthsBasedRecDate -> {}", lastRecoveryDate,
                completed36MonthsBasedRecDate);

        Calendar dateDetailsDob = FinancialYearAndMonth.getDateDetails(dateOfBirth);

        dateDetailsDob.add(Calendar.YEAR, 60);

        dateDetailsDob.add(Calendar.MONTH, 36);

        dateDetailsDob.add(Calendar.DAY_OF_MONTH, -1);

        Date completed36MonthsBasedDobDate = dateDetailsDob.getTime();

        logger.info("DOB -> {}, completed36MonthsBasedDobDate -> {}", dateOfBirth.toString(),
                completed36MonthsBasedDobDate.toString());

        Integer financialMonth = 0;
        Integer financialYear = 0;
        Date date;

        if (completed36MonthsBasedDobDate.getTime() > completed36MonthsBasedRecDate.getTime()) {
            financialMonth = FinancialYearAndMonth.getFinancialMonth(completed36MonthsBasedDobDate);
            financialYear = FinancialYearAndMonth.getFinancialYear(completed36MonthsBasedDobDate);
            date = completed36MonthsBasedDobDate;
        } else {
            financialMonth = FinancialYearAndMonth.getFinancialMonth(completed36MonthsBasedRecDate);
            financialYear = FinancialYearAndMonth.getFinancialYear(completed36MonthsBasedRecDate);
            date = completed36MonthsBasedRecDate;
        }

        logger.info("financialMonth -> {}, financialYear -> {}", financialMonth, financialYear);

        IoInterestMonths ioInterestMonths = new IoInterestMonths();

        ioInterestMonths.setEmployee(employee);
        ioInterestMonths.setEmployeeMonthlyContributionStatus(employeeMonthlyContributionStatus);
        ioInterestMonths.setPublished(isDryRun);
        ioInterestMonths.setTenant(employee.getTenant());
        ioInterestMonths.setPeriod(financialMonth);
        ioInterestMonths.setMonth(financialMonth);
        ioInterestMonths.setYear(financialYear);
        ioInterestMonths.setDate(date);

        return ioInterestMonthsRepository.save(ioInterestMonths);

    }

    private void saveEmployeeMonthlyContributionStatus(Employee employee, Integer year, Integer month,
                                                       ContributionStatus contributionStatus,
                                                       EmployeeMonthlyContributionStatus status,
                                                       Boolean isDryRun, Job job){

        status.setEmployee(employee);
        status.setContributionStatus(contributionStatus);
        status.setTenant(employee.getTenant());
        status.setFinancialYear(year);
        status.setMonth(month);
        status.setPublished(!isDryRun);
        status.setJob(job);

        employeeMonthlyContributionStatusRepository.save(status);

        if(isDryRun) return;

        if(!employee.getContributionStatus().getSymbol().equalsIgnoreCase("F")){

            employee.setContributionStatus(contributionStatus);

            employeeRepository.save(employee);

        }

    }


    private EmployeeMonthlyStatusReport saveEmployeeMonthlyStatusReport(EmployeeMonthlyStatusReport reportRow, Employee employee,
                                                 ContributionStatus contributionStatus,
                                                 Integer year, Integer month, BigDecimal memberContribution,
                                                 BigDecimal companyContribution, BigDecimal vpfContribution,
                                                 BigDecimal total, Date lastRecoveryDate, Date settlementDate,
                                                                        Date ioDate, Job job){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Personnel Number", employee.getPernNumber());
        jsonObject.put("Name", employee.getName());
        jsonObject.put("PF Number", employee.getPfNumber());
        jsonObject.put("Token Number", employee.getTokenNumber());
        jsonObject.put("Unit Code", employee.getUnitCode());
        jsonObject.put("Gender", employee.getGender().getSymbol());
        jsonObject.put("Date Of Joining PF", DateFormatterUtil.format(employee.getDateOfJoiningPF(), "dd-MM-yyyy"));
        jsonObject.put("Date Of Birth", DateFormatterUtil.format(employee.getDateOfBirth(), "dd-MM-yyyy"));
        jsonObject.put("Last Recovery Date", DateFormatterUtil.format(lastRecoveryDate, "dd-MM-yyyy"));

        jsonObject.put("Member Contribution", avoidNull(memberContribution));
        jsonObject.put("Company Contribution", avoidNull(companyContribution));
        jsonObject.put("EPF Contribution", avoidNull(vpfContribution));
        jsonObject.put("Total Balance", avoidNull(total));

        jsonObject.put("Status", contributionStatus.getSymbol());
        jsonObject.put("Settlement Due Date", DateFormatterUtil.format(settlementDate, "dd-MM-yyyy"));
        jsonObject.put("Inoperative Date", DateFormatterUtil.format(ioDate, "dd-MM-yyyy"));

        reportRow.setReport_row(jsonObject.toString());
        reportRow.setPernNumber(employee.getPernNumber());
        reportRow.setUnitCode(employee.getUnitCode());
        reportRow.setYear(year);
        reportRow.setMonth(month);
        reportRow.setJob(job);

        return employeeMonthlyStatusReportRepository.save(reportRow);
    }



}
