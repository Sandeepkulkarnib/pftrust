package com.coreintegra.pftrust.services.pf.yearend;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.ContributionStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.IoInterestMonths;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanWithDrawalsFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.pf.yearend.ClosingBalance;
import com.coreintegra.pftrust.entities.pf.yearend.TdsDeduction;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndReport;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.employee.IoInterestMonthsRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.ClosingBalanceRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.TdsDeductionRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.YearEndProcessRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.YearEndReportRepository;
import com.coreintegra.pftrust.services.pf.contribution.ContributionService;
import com.coreintegra.pftrust.services.pf.department.InterestRateService;
import com.coreintegra.pftrust.services.pf.interest.InterestProviderService;
import com.coreintegra.pftrust.services.pf.loans.LoanService;
import com.coreintegra.pftrust.services.pf.transferin.TransferInService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.Pair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

@Service
public class YearEndProcessServiceImpl implements YearEndProcessService {

    private final Logger logger = LoggerFactory.getLogger(YearEndProcessService.class);

    private final ContributionService contributionService;
    private final LoanService loanService;
    private final TransferInService transferInService;
    private final InterestProviderService interestProviderService;
    private final InterestRateService interestRateService;
    private final YearEndProcessRepository yearEndProcessRepository;
    private final YearEndReportRepository yearEndReportRepository;
    private final IoInterestMonthsRepository ioInterestMonthsRepository;
    private final TdsDeductionRepository tdsDeductionRepository;
    private final ClosingBalanceRepository closingBalanceRepository;

    public YearEndProcessServiceImpl(ContributionService contributionService,
                                     LoanService loanService,
                                     TransferInService transferInService,
                                     InterestProviderService interestProviderService,
                                     InterestRateService interestRateService,
                                     YearEndProcessRepository yearEndProcessRepository,
                                     YearEndReportRepository yearEndReportRepository,
                                     IoInterestMonthsRepository ioInterestMonthsRepository,
                                     TdsDeductionRepository tdsDeductionRepository,
                                     ClosingBalanceRepository closingBalanceRepository) {
        this.contributionService = contributionService;
        this.loanService = loanService;
        this.transferInService = transferInService;
        this.interestProviderService = interestProviderService;
        this.interestRateService = interestRateService;
        this.yearEndProcessRepository = yearEndProcessRepository;
        this.yearEndReportRepository = yearEndReportRepository;
        this.ioInterestMonthsRepository = ioInterestMonthsRepository;
        this.tdsDeductionRepository = tdsDeductionRepository;
        this.closingBalanceRepository = closingBalanceRepository;
    }

    @Override
    public void performForLoan(List<Employee> employees, Integer year, Date date, Boolean isDryRun, Job job) {
        for (Employee employee:employees) {
            try {
                performForLoan(employee, year, date, isDryRun, job);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void performForLoan(Employee employee, Integer year, Date date, Boolean isDryRun, Job job) throws JPAException {

        ContributionStatus contributionStatus = employee.getContributionStatus();

        String symbol = contributionStatus.getSymbol();

        if(symbol.equalsIgnoreCase("S") || symbol.equalsIgnoreCase("M") || symbol.equalsIgnoreCase("IO")){

            logger.info("Employee -> {} is already {}", employee.getPernNumber(), contributionStatus.getDescription());

            throw new JPAException("Employee -> " + employee.getPernNumber() + " is already " + contributionStatus.getDescription());

        }

        if(!isDryRun){
            yearEndProcessRepository.unPublish(employee, year);
            yearEndProcessRepository.unPublishOpeningBalance(employee, year+1, 0);
            yearEndReportRepository.unPublish(employee.getPernNumber(), year);
        }

        Instant now = Instant.now();

        logger.info("starting the process at {}", now.toString());

        InterestRate interestRateForLoan = interestRateService.getInterestRateForLoan();

        List<Contribution> contributions = contributionService.getContributions(employee, year);

        if(contributions.size() == 0){
            return;
        }

        List<Loan> loans = loanService.getCompletedLoans(employee, year);

        loanService.distributeLoanAmountIntoTaxableAndNonTaxable(loans, employee);

        List<TransferIn> transferIns = transferInService.getCompletedTransferIns(employee, year);

        List<Contribution> interestCalculatedContributions = interestProviderService
                .performForContributions(employee, year, contributions, interestRateForLoan, date);

        List<Loan> interestCalculatedLoans = interestProviderService
                .performForLoans(year, loans, interestRateForLoan, date);

        List<TransferIn> interestCalculatedTransferIns = interestProviderService
                .performForTransferIns(year, transferIns, interestRateForLoan, date);

        saveYearEndProcessForContribution(employee, interestCalculatedContributions, year, job, isDryRun,
                YearEndProcess.PROCESS_FOR_LOAN);

        saveYearEndProcessForLoan(employee, interestCalculatedLoans, year, job, isDryRun, YearEndProcess.PROCESS_FOR_LOAN);

        saveYearEndProcessForTransferIn(employee, interestCalculatedTransferIns, year, job, isDryRun, YearEndProcess.PROCESS_FOR_LOAN);

        JSONObject yearEndReportRow = new JSONObject();

        Contribution totalContribution = calculateTotalContribution(interestCalculatedContributions, yearEndReportRow);

        addTransferInsToOpeningBalance(totalContribution, transferIns, yearEndReportRow);

        Contribution totalLoanTaken = getTotalLoanTaken(loans, yearEndReportRow);

        subtractLoans(totalContribution, totalLoanTaken);

        Optional<TdsDeduction> optionalTdsDeduction = tdsDeductionRepository.findByYearAndIsActive(year, true);

        TdsDeduction tdsDeduction = optionalTdsDeduction
                .orElse(new TdsDeduction(BigDecimal.TEN, BigDecimal.valueOf(5000), year));

        Pair<BigDecimal, BigDecimal> calculateTds = calculateTds(totalContribution, tdsDeduction);

        Contribution calculatedOpeningBalance = calculateOpeningBalance(totalContribution,
                tdsDeduction, calculateTds.getFirst(), calculateTds.getSecond());

        Integer nextFinancialYear = year + 1;

        calculatedOpeningBalance.setYear(nextFinancialYear);
        calculatedOpeningBalance.setSubType(0);
        calculatedOpeningBalance.setEmployee(employee);
        calculatedOpeningBalance.setTenant(employee.getTenant());

        YearEndProcess yearEndProcess = getYearEndProcess(
                nextFinancialYear, job, isDryRun,
                calculatedOpeningBalance.getNonTaxableMemberContribution(),
                calculatedOpeningBalance.getTaxableMemberContribution(),
                calculatedOpeningBalance.getMemberContribution(),
                calculatedOpeningBalance.getCompanyContribution(),
                calculatedOpeningBalance.getNonTaxableVpfContribution(),
                calculatedOpeningBalance.getTaxableVpfContribution(),
                calculatedOpeningBalance.getVpfContribution(),
                calculatedOpeningBalance.getInterestNonTaxableMemberContribution(),
                calculatedOpeningBalance.getInterestTaxableMemberContribution(),
                calculatedOpeningBalance.getInterestMemContribution(),
                calculatedOpeningBalance.getInterestCompanyContribution(),
                calculatedOpeningBalance.getInterestNonTaxableVpfContribution(),
                calculatedOpeningBalance.getInterestTaxableVpfContribution(),
                calculatedOpeningBalance.getVpfContributionInterest(),
                calculatedOpeningBalance.getInterestRate(),
                calculatedOpeningBalance.getMonthlyInterestRate(),
                calculatedOpeningBalance.getInterestPeriod(),
                0,
                YearEndProcess.ENTITY_CONTRIBUTION,
                YearEndProcess.PROCESS_FOR_LOAN);

        yearEndProcess.setEmployee(employee);

        BigDecimal totalTds = calculateTds.getFirst().add(calculateTds.getSecond());

        if(totalTds.intValue() > tdsDeduction.getMinimumLimit().intValue()){
            yearEndProcess.setTdsOnTaxableMemberContribution(calculateTds.getFirst());
            yearEndProcess.setTdsOnTaxableVpfContribution(calculateTds.getSecond());
        }else {
            yearEndProcess.setTdsOnTaxableMemberContribution(BigDecimal.ZERO);
            yearEndProcess.setTdsOnTaxableVpfContribution(BigDecimal.ZERO);
        }

        yearEndProcessRepository.save(yearEndProcess);

        BigDecimal grandTotal = calculatedOpeningBalance.getNonTaxableMemberContribution()
                .add(calculatedOpeningBalance.getTaxableMemberContribution())
                .add(calculatedOpeningBalance.getCompanyContribution())
                .add(calculatedOpeningBalance.getNonTaxableVpfContribution())
                .add(calculatedOpeningBalance.getTaxableVpfContribution());

        yearEndReportRow.put("GRAND TOTAL", grandTotal);

        Date lastRecoveryDate = contributionService.getLastRecoveryDate(employee);

        YearEndReport.addBasicDetails(lastRecoveryDate, employee, yearEndReportRow);

        String ioDate = "";

        if(employee.getContributionStatus().getSymbol().equalsIgnoreCase("IO")){

            Optional<IoInterestMonths> ioInterestMonthsOptional = ioInterestMonthsRepository
                    .findByEmployeeAndYearAndIsPublishedAndIsActive(employee, year, true, true);

            if(ioInterestMonthsOptional.isPresent()){

                ioDate = DateFormatterUtil.format(ioInterestMonthsOptional.get().getDate(), "dd-MM-yyyy");

            }

        }

        yearEndReportRow.put("INOPERATIVE DATE AS PER STATUS REPORT", ioDate);

        YearEndReport yearEndReport = new YearEndReport(job, yearEndReportRow.toString(), employee.getPernNumber(),
                employee.getUnitCode(), year, !isDryRun);

        yearEndReportRepository.save(yearEndReport);

        if(!isDryRun){
            Contribution save = contributionService.saveOpeningBalance(calculatedOpeningBalance);
            yearEndProcess.setContribution(save);
            yearEndProcessRepository.save(yearEndProcess);
        }

        logger.info("process completed at {} and in {} seconds", Instant.now().toString(),
                Duration.between(now, Instant.now()).getSeconds());

    }

    private Contribution calculateOpeningBalance(Contribution totalContribution,
                                                 TdsDeduction tdsDeduction,
                                                 BigDecimal tdsOnTaxableMemberContribution,
                                                 BigDecimal tdsOnTaxableVpfContribution) {

        BigDecimal totalTds = tdsOnTaxableMemberContribution.add(tdsOnTaxableVpfContribution);

        BigDecimal nonTaxableMemberOpeningBalance = totalContribution.getNonTaxableMemberContribution()
                .add(totalContribution.getInterestNonTaxableMemberContribution());

        BigDecimal taxableMemberOpeningBalance = totalContribution.getTaxableMemberContribution()
                .add(totalContribution.getInterestTaxableMemberContribution());

        if(totalTds.intValue() > tdsDeduction.getMinimumLimit().intValue()){
            taxableMemberOpeningBalance = taxableMemberOpeningBalance.subtract(tdsOnTaxableMemberContribution);
        }

        BigDecimal companyOpeningBalance = totalContribution.getCompanyContribution()
                .add(totalContribution.getInterestCompanyContribution());

        BigDecimal nonTaxableVpfOpeningBalance = totalContribution.getNonTaxableVpfContribution()
                .add(totalContribution.getInterestNonTaxableVpfContribution());

        BigDecimal taxableVpfOpeningBalance = totalContribution.getTaxableVpfContribution()
                .add(totalContribution.getInterestTaxableVpfContribution());

        if(totalTds.intValue() > tdsDeduction.getMinimumLimit().intValue()){
            taxableVpfOpeningBalance = taxableVpfOpeningBalance.subtract(tdsOnTaxableVpfContribution);
        }

        Contribution newOpeningBalance = new Contribution();

        newOpeningBalance.setNonTaxableMemberContribution(nonTaxableMemberOpeningBalance);
        newOpeningBalance.setTaxableMemberContribution(taxableMemberOpeningBalance);

        newOpeningBalance.setCompanyContribution(companyOpeningBalance);

        newOpeningBalance.setNonTaxableVpfContribution(nonTaxableVpfOpeningBalance);
        newOpeningBalance.setTaxableVpfContribution(taxableVpfOpeningBalance);

        return newOpeningBalance;

    }

    private void subtractLoans(Contribution calculatedOpeningBalance, Contribution totalLoanTaken) {

        BigDecimal nonTaxableMemberContribution = calculatedOpeningBalance.getNonTaxableMemberContribution()
                .subtract(totalLoanTaken.getNonTaxableMemberContribution());

        BigDecimal taxableMemberContribution = calculatedOpeningBalance.getTaxableMemberContribution()
                .subtract(totalLoanTaken.getTaxableMemberContribution());

        BigDecimal companyContribution = calculatedOpeningBalance.getCompanyContribution()
                .subtract(totalLoanTaken.getCompanyContribution());

        BigDecimal nonTaxableVpfContribution = calculatedOpeningBalance.getNonTaxableVpfContribution()
                .subtract(totalLoanTaken.getNonTaxableVpfContribution());

        BigDecimal taxableVpfContribution = calculatedOpeningBalance.getTaxableVpfContribution()
                .subtract(totalLoanTaken.getTaxableVpfContribution());


        // interest
        BigDecimal interestNonTaxableMemberContribution = calculatedOpeningBalance.getInterestNonTaxableMemberContribution()
                .subtract(totalLoanTaken.getInterestNonTaxableMemberContribution());

        BigDecimal interestTaxableMemberContribution = calculatedOpeningBalance.getInterestTaxableMemberContribution()
                .subtract(totalLoanTaken.getInterestTaxableMemberContribution());

        BigDecimal interestCompanyContribution = calculatedOpeningBalance.getInterestCompanyContribution()
                .subtract(totalLoanTaken.getInterestCompanyContribution());

        BigDecimal interestNonTaxableVpfContribution = calculatedOpeningBalance.getInterestNonTaxableVpfContribution()
                .subtract(totalLoanTaken.getInterestNonTaxableVpfContribution());

        BigDecimal interestTaxableVpfContribution = calculatedOpeningBalance.getInterestTaxableVpfContribution()
                .subtract(totalLoanTaken.getInterestTaxableVpfContribution());



        calculatedOpeningBalance.setNonTaxableMemberContribution(nonTaxableMemberContribution);
        calculatedOpeningBalance.setTaxableMemberContribution(taxableMemberContribution);

        calculatedOpeningBalance.setCompanyContribution(companyContribution);

        calculatedOpeningBalance.setNonTaxableVpfContribution(nonTaxableVpfContribution);
        calculatedOpeningBalance.setTaxableVpfContribution(taxableVpfContribution);

        calculatedOpeningBalance.setInterestTaxableMemberContribution(interestTaxableMemberContribution);
        calculatedOpeningBalance.setInterestNonTaxableMemberContribution(interestNonTaxableMemberContribution);

        calculatedOpeningBalance.setInterestCompanyContribution(interestCompanyContribution);

        calculatedOpeningBalance.setInterestNonTaxableVpfContribution(interestNonTaxableVpfContribution);
        calculatedOpeningBalance.setInterestTaxableVpfContribution(interestTaxableVpfContribution);

    }

    @Override
    public void performForSettlement(List<Employee> employees, Integer year, Date date, Boolean isDryRun, Job job) {
        for (Employee employee:employees) {
            try {
                performForSettlement(employee, year, date, isDryRun, job);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void performForSettlement(Employee employee, Integer year, Date date, Boolean isDryRun, Job job) throws JPAException {

        ContributionStatus contributionStatus = employee.getContributionStatus();

        String symbol = contributionStatus.getSymbol();

        if(symbol.equalsIgnoreCase("S") || symbol.equalsIgnoreCase("M")){

            logger.info("Employee is already {}", contributionStatus.getDescription());

            throw new JPAException("Employee -> " + employee.getPernNumber() + " is already " + contributionStatus.getDescription());

        }

        if(!isDryRun){
            yearEndProcessRepository.unPublish(employee, year);
            yearEndProcessRepository.unPublishOpeningBalance(employee, year+1, 0);
            yearEndReportRepository.unPublish(employee.getPernNumber(), year);
        }

        Instant now = Instant.now();

        logger.info("starting the process at {}", now.toString());

        InterestRate interestRate = interestRateService.getActive(year+1);

        List<Contribution> contributions = contributionService.getContributions(employee, year);

        if(contributions.size() == 0){
            return;
        }

        List<TransferIn> transferIns = transferInService.getCompletedTransferIns(employee, year);

        List<Contribution> interestCalculatedContributions = interestProviderService
                .performForContributions(employee, year, contributions, interestRate, date);

        List<Loan> loans = loanService.getCompletedLoans(employee, year);

        List<Loan> distributedLoans = loanService.distributeLoanAmountIntoTaxableAndNonTaxable(loans, employee);

        List<Loan> interestCalculatedLoans = interestProviderService
                .performForLoans(year, distributedLoans, interestRate, date);

        List<TransferIn> interestCalculatedTransferIns = interestProviderService
                .performForTransferIns(year, transferIns, interestRate, date);

        saveYearEndProcessForContribution(employee, interestCalculatedContributions, year, job, isDryRun,
                YearEndProcess.PROCESS_FOR_SETTLEMENT);

        saveYearEndProcessForLoan(employee, interestCalculatedLoans, year, job, isDryRun,
                YearEndProcess.PROCESS_FOR_SETTLEMENT);

        saveYearEndProcessForTransferIn(employee, interestCalculatedTransferIns, year, job, isDryRun,
                YearEndProcess.PROCESS_FOR_SETTLEMENT);

        JSONObject yearEndReportRow = new JSONObject();

        Contribution totalContribution = calculateTotalContribution(interestCalculatedContributions, yearEndReportRow);

        addTransferInsToOpeningBalance(totalContribution, transferIns, yearEndReportRow);

        Contribution totalLoanTaken = getTotalLoanTaken(loans, yearEndReportRow);

        subtractLoans(totalContribution, totalLoanTaken);

        Optional<TdsDeduction> optionalTdsDeduction = tdsDeductionRepository.findByYearAndIsActive(year+1, true);

        TdsDeduction tdsDeduction = optionalTdsDeduction
                .orElse(new TdsDeduction(BigDecimal.TEN, BigDecimal.valueOf(5000), year));

        Pair<BigDecimal, BigDecimal> calculateTds = calculateTds(totalContribution, tdsDeduction);

        Contribution calculatedOpeningBalance = calculateOpeningBalance(totalContribution,
                tdsDeduction, calculateTds.getFirst(), calculateTds.getSecond());

        Integer nextFinancialYear = year + 1;

        calculatedOpeningBalance.setYear(nextFinancialYear);
        calculatedOpeningBalance.setSubType(0);
        calculatedOpeningBalance.setEmployee(employee);
        calculatedOpeningBalance.setTenant(employee.getTenant());

        YearEndProcess yearEndProcess = getYearEndProcess(
                nextFinancialYear, job, isDryRun,
                calculatedOpeningBalance.getNonTaxableMemberContribution(),
                calculatedOpeningBalance.getTaxableMemberContribution(),
                calculatedOpeningBalance.getMemberContribution(),
                calculatedOpeningBalance.getCompanyContribution(),
                calculatedOpeningBalance.getNonTaxableVpfContribution(),
                calculatedOpeningBalance.getTaxableVpfContribution(),
                calculatedOpeningBalance.getVpfContribution(),
                calculatedOpeningBalance.getInterestNonTaxableMemberContribution(),
                calculatedOpeningBalance.getInterestTaxableMemberContribution(),
                calculatedOpeningBalance.getInterestMemContribution(),
                calculatedOpeningBalance.getInterestCompanyContribution(),
                calculatedOpeningBalance.getInterestNonTaxableVpfContribution(),
                calculatedOpeningBalance.getInterestTaxableVpfContribution(),
                calculatedOpeningBalance.getVpfContributionInterest(),
                calculatedOpeningBalance.getInterestRate(),
                calculatedOpeningBalance.getMonthlyInterestRate(),
                calculatedOpeningBalance.getInterestPeriod(),
                0,
                YearEndProcess.ENTITY_CONTRIBUTION,
                YearEndProcess.PROCESS_FOR_SETTLEMENT);

        yearEndProcess.setEmployee(employee);

        BigDecimal totalTds = calculateTds.getFirst().add(calculateTds.getSecond());

        if(totalTds.intValue() > tdsDeduction.getMinimumLimit().intValue()){
            yearEndProcess.setTdsOnTaxableMemberContribution(calculateTds.getFirst());
            yearEndProcess.setTdsOnTaxableVpfContribution(calculateTds.getSecond());
        }else {
            yearEndProcess.setTdsOnTaxableMemberContribution(BigDecimal.ZERO);
            yearEndProcess.setTdsOnTaxableVpfContribution(BigDecimal.ZERO);
        }

        YearEndProcess savedYearEndProcess = yearEndProcessRepository.save(yearEndProcess);

        BigDecimal grandTotal = calculatedOpeningBalance.getNonTaxableMemberContribution()
                .add(calculatedOpeningBalance.getTaxableMemberContribution())
                .add(calculatedOpeningBalance.getCompanyContribution())
                .add(calculatedOpeningBalance.getNonTaxableVpfContribution())
                .add(calculatedOpeningBalance.getTaxableVpfContribution());

        yearEndReportRow.put("GRAND TOTAL", grandTotal);

        Date lastRecoveryDate = contributionService.getLastRecoveryDate(employee);

        YearEndReport.addBasicDetails(lastRecoveryDate, employee, yearEndReportRow);

        String ioDate = "";

        if(employee.getContributionStatus().getSymbol().equalsIgnoreCase("IO")){

            Optional<IoInterestMonths> ioInterestMonthsOptional = ioInterestMonthsRepository
                    .findByEmployeeAndYearAndIsPublishedAndIsActive(employee, year, true, true);

            if(ioInterestMonthsOptional.isPresent()){

                ioDate = DateFormatterUtil.format(ioInterestMonthsOptional.get().getDate(), "dd-MM-yyyy");

            }

        }

        yearEndReportRow.put("INOPERATIVE DATE AS PER STATUS REPORT", ioDate);

        YearEndReport yearEndReport = new YearEndReport(job, yearEndReportRow.toString(), employee.getPernNumber(),
                employee.getPfNumber(), year, !isDryRun);

        yearEndReportRepository.save(yearEndReport);

        if(!isDryRun){
            Contribution save = contributionService.saveOpeningBalance(calculatedOpeningBalance);
            yearEndProcess.setContribution(save);
            yearEndProcessRepository.save(yearEndProcess);
        }

        logger.info("process completed at {} and in {} seconds", Instant.now().toString(),
                Duration.between(now, Instant.now()).getSeconds());

    }

    @Override
    @Async
    @ApplyTenantFilter
    public CompletableFuture<String> performForLoanAsync(Employee employee, Integer year, Date date, Boolean isDryRun,
                                                         Job job) throws JPAException {

            performForLoan(employee, year, date, isDryRun, job);

            return CompletableFuture.completedFuture("done");
    }

    @Override
    @Async
    @ApplyTenantFilter
    public CompletableFuture<String> performForSettlementAsync(Employee employee, Integer year, Date date,
                                                               Boolean isDryRun, Job job) throws JPAException {

            performForSettlement(employee, year, date, isDryRun, job);

            return CompletableFuture.completedFuture("done");
    }

    private void saveYearEndProcessForContribution(Employee employee, List<Contribution> contributions, Integer year, Job job,
                                                   Boolean isDryRun, String process){

        for (Contribution contribution: contributions) {

            YearEndProcess yearEndProcess = getYearEndProcess(year, job, isDryRun,
                    contribution.getNonTaxableMemberContribution(),
                    contribution.getTaxableMemberContribution(),
                    contribution.getMemberContribution(),

                    contribution.getCompanyContribution(),

                    contribution.getNonTaxableVpfContribution(),
                    contribution.getTaxableVpfContribution(),
                    contribution.getVpfContribution(),

                    contribution.getInterestNonTaxableMemberContribution(),
                    contribution.getInterestTaxableMemberContribution(),
                    contribution.getInterestMemContribution(),
                    contribution.getInterestCompanyContribution(),

                    contribution.getInterestNonTaxableVpfContribution(),
                    contribution.getInterestTaxableVpfContribution(),
                    contribution.getVpfContributionInterest(),

                    contribution.getInterestRate(),
                    contribution.getMonthlyInterestRate(),
                    contribution.getInterestPeriod(),
                    contribution.getSubType(),
                    YearEndProcess.ENTITY_CONTRIBUTION,
                    process);

            yearEndProcess.setContribution(contribution);

            yearEndProcess.setEmployee(employee);

            yearEndProcessRepository.save(yearEndProcess);

            if(!isDryRun){
                contributionService.save(contribution);
            }

        }

    }

    private void saveYearEndProcessForLoan(Employee employee, List<Loan> loans, Integer year, Job job,
                                                   Boolean isDryRun, String process){

        for (Loan loan: loans) {

            LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

            YearEndProcess yearEndProcess = getYearEndProcess(year, job, isDryRun,
                    loanWithDrawalsFinalDetails.getLoanAmountOnNonTaxableMemberContribution(),
                    loanWithDrawalsFinalDetails.getLoanAmountOnTaxableMemberContribution(),
                    loanWithDrawalsFinalDetails.getLoanAmountOnMemContribution(),

                    loanWithDrawalsFinalDetails.getLoanAmountOnCompanyContribution(),

                    loanWithDrawalsFinalDetails.getLoanAmountOnNonTaxableVpfContribution(),
                    loanWithDrawalsFinalDetails.getLoanAmountOnTaxableVpfContribution(),
                    loanWithDrawalsFinalDetails.getLoanAmountOnVPFContribution(),

                    loanWithDrawalsFinalDetails.getInterestOnLoanAmountOnNonTaxableMemberContribution(),
                    loanWithDrawalsFinalDetails.getInterestOnLoanAmountOnTaxableMemberContribution(),
                    loanWithDrawalsFinalDetails.getInterestOnloanAmountOnMemContribution(),

                    loanWithDrawalsFinalDetails.getInterestOnloanAmountOnCompanyContribution(),

                    loanWithDrawalsFinalDetails.getInterestOnLoanAmountOnNonTaxableVpfContribution(),
                    loanWithDrawalsFinalDetails.getInterestOnLoanAmountOnTaxableVpfContribution(),
                    loanWithDrawalsFinalDetails.getInterestOnloanAmountOnVPFContribution(),

                    loanWithDrawalsFinalDetails.getInterestRate(),
                    loanWithDrawalsFinalDetails.getMonthlyInterestRate(),
                    loanWithDrawalsFinalDetails.getInterestPeriod(),
                    loanWithDrawalsFinalDetails.getMonth(),
                    YearEndProcess.ENTITY_LOAN, process);

            yearEndProcess.setLoan(loan);

            yearEndProcess.setEmployee(employee);

            yearEndProcessRepository.save(yearEndProcess);

            if(!isDryRun){
                loanService.save(loan);
            }

        }

    }

    private void saveYearEndProcessForTransferIn(Employee employee, List<TransferIn> transferIns, Integer year, Job job,
                                                 Boolean isDryRun, String process){

        transferIns.forEach(transferIn -> {

            TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

            YearEndProcess yearEndProcess = getYearEndProcess(year, job, isDryRun,
                    transferInFinalDetails.getNonTaxableMemberContribution(),
                    transferInFinalDetails.getTaxableMemberContribution(),
                    transferInFinalDetails.getMemberContribution(),
                    transferInFinalDetails.getCompanyContribution(),
                    transferInFinalDetails.getNonTaxableVpfContribution(),
                    transferInFinalDetails.getTaxableVpfContribution(),
                    transferInFinalDetails.getVpfContribution(),
                    transferInFinalDetails.getInterestNonTaxableMemberContribution(),
                    transferInFinalDetails.getInterestTaxableMemberContribution(),
                    transferInFinalDetails.getInterestOnMemberContribution(),
                    transferInFinalDetails.getInterestOnCompanyContribution(),
                    transferInFinalDetails.getInterestNonTaxableVpfContribution(),
                    transferInFinalDetails.getInterestTaxableVpfContribution(),
                    transferInFinalDetails.getInterestOnVpfContribution(),
                    transferInFinalDetails.getInterestRate(),
                    transferInFinalDetails.getMonthlyInterestRate(),
                    transferInFinalDetails.getInterestPeriod(),
                    transferInFinalDetails.getContributedIn(),
                    YearEndProcess.ENTITY_TRANSFER_IN,
                    process);

            yearEndProcess.setTransferIn(transferIn);

            yearEndProcess.setEmployee(employee);

            yearEndProcessRepository.save(yearEndProcess);

            if(!isDryRun){
                transferInService.save(transferIn);
            }

        });

    }

    private YearEndProcess getYearEndProcess(Integer year,
                                             Job job,
                                             Boolean isDryRun,

                                             BigDecimal nonTaxableMemberContribution,
                                             BigDecimal taxableMemberContribution,
                                             BigDecimal memberContribution,

                                             BigDecimal companyContribution,


                                             BigDecimal nonTaxableVpfContribution,
                                             BigDecimal taxableVpfContribution,
                                             BigDecimal vpfContribution,

                                             BigDecimal interestOnNonTaxableMemberContribution,
                                             BigDecimal interestOnTaxableMemberContribution,
                                             BigDecimal interestOnMemberContribution,

                                             BigDecimal interestOnCompanyContribution,

                                             BigDecimal interestOnNonTaxableVpfContribution,
                                             BigDecimal interestOnTaxableVpfContribution,
                                             BigDecimal interestOnVpfContribution,

                                             BigDecimal interestRate,
                                             BigDecimal monthlyInterestRate,
                                             Integer period,
                                             Integer contributedIn,
                                             String entityType,
                                             String process) {

        YearEndProcess yearEndProcess = new YearEndProcess();

        yearEndProcess.setNonTaxableMemberContribution(nonTaxableMemberContribution);
        yearEndProcess.setTaxableMemberContribution(taxableMemberContribution);
        yearEndProcess.setMemberContribution(memberContribution);

        yearEndProcess.setCompanyContribution(companyContribution);

        yearEndProcess.setNonTaxableVpfContribution(nonTaxableVpfContribution);
        yearEndProcess.setTaxableVpfContribution(taxableVpfContribution);
        yearEndProcess.setVpfContribution(vpfContribution);

        yearEndProcess.setInterestOnNonTaxableMemberContribution(interestOnNonTaxableMemberContribution);
        yearEndProcess.setInterestOnTaxableMemberContribution(interestOnTaxableMemberContribution);
        yearEndProcess.setInterestOnMemberContribution(interestOnMemberContribution);

        yearEndProcess.setInterestOnCompanyContribution(interestOnCompanyContribution);

        yearEndProcess.setInterestOnNonTaxableVpfContribution(interestOnNonTaxableVpfContribution);
        yearEndProcess.setInterestOnTaxableVpfContribution(interestOnTaxableVpfContribution);
        yearEndProcess.setInterestOnVpfContribution(interestOnVpfContribution);

        yearEndProcess.setInterestRate(interestRate);
        yearEndProcess.setMonthlyInterestRate(monthlyInterestRate);
        yearEndProcess.setPeriod(period);

        yearEndProcess.setYear(year);
        yearEndProcess.setMonth(contributedIn);

        yearEndProcess.setEntityType(entityType);
        yearEndProcess.setProcessType(process);

        yearEndProcess.setJob(job);
        yearEndProcess.setPublished(!isDryRun);

        return yearEndProcess;
    }

    private YearEndProcess getYearEndProcess(Integer year,
                                             Job job,
                                             Boolean isDryRun,
                                             BigDecimal memberContribution,
                                             BigDecimal companyContribution,
                                             BigDecimal vpfContribution,
                                             BigDecimal interestOnMemberContribution,
                                             BigDecimal interestOnCompanyContribution,
                                             BigDecimal interestOnVpfContribution,
                                             BigDecimal interestRate,
                                             BigDecimal monthlyInterestRate,
                                             Integer period,
                                             Integer contributedIn,
                                             String entity,
                                             String process) {

        YearEndProcess yearEndProcess = new YearEndProcess();

        yearEndProcess.setMemberContribution(memberContribution);
        yearEndProcess.setCompanyContribution(companyContribution);
        yearEndProcess.setVpfContribution(vpfContribution);

        yearEndProcess.setInterestOnMemberContribution(interestOnMemberContribution);
        yearEndProcess.setInterestOnCompanyContribution(interestOnCompanyContribution);
        yearEndProcess.setInterestOnVpfContribution(interestOnVpfContribution);

        yearEndProcess.setInterestRate(interestRate);
        yearEndProcess.setMonthlyInterestRate(monthlyInterestRate);
        yearEndProcess.setPeriod(period);

        yearEndProcess.setYear(year);
        yearEndProcess.setMonth(contributedIn);

        yearEndProcess.setEntityType(entity);
        yearEndProcess.setProcessType(process);

        yearEndProcess.setJob(job);
        yearEndProcess.setPublished(!isDryRun);

        return yearEndProcess;
    }

    private Contribution calculateCurrentYearContributionTotal(List<Contribution> contributions){

        BigDecimal taxableMemberContribution = BigDecimal.ZERO;
        BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;
        BigDecimal memberContribution = BigDecimal.ZERO;

        BigDecimal companyContribution = BigDecimal.ZERO;

        BigDecimal taxableVpfContribution = BigDecimal.ZERO;
        BigDecimal nonTaxableVpfContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal taxableMemberContributionInterest = BigDecimal.ZERO;
        BigDecimal nonTaxableMemberContributionInterest = BigDecimal.ZERO;
        BigDecimal memberInterest = BigDecimal.ZERO;

        BigDecimal companyInterest = BigDecimal.ZERO;

        BigDecimal taxableVpfContributionInterest = BigDecimal.ZERO;
        BigDecimal nonTaxableVpfContributionInterest = BigDecimal.ZERO;
        BigDecimal vpfInterest = BigDecimal.ZERO;


        for (Contribution contribution:contributions) {

            if(contribution.getSubType() == 0){
                continue;
            }

            taxableMemberContribution = taxableMemberContribution.add(avoidNull(contribution.getTaxableMemberContribution()));
            nonTaxableMemberContribution = nonTaxableMemberContribution.add(avoidNull(contribution.getNonTaxableMemberContribution()));
            memberContribution = memberContribution.add(avoidNull(contribution.getMemberContribution()));

            companyContribution = companyContribution.add(avoidNull(contribution.getCompanyContribution()));

            taxableVpfContribution = taxableVpfContribution.add(avoidNull(contribution.getTaxableVpfContribution()));
            nonTaxableVpfContribution = nonTaxableVpfContribution.add(avoidNull(contribution.getNonTaxableVpfContribution()));
            vpfContribution = vpfContribution.add(contribution.getVpfContribution());

            taxableMemberContributionInterest = taxableMemberContributionInterest.add(avoidNull(contribution.getInterestTaxableMemberContribution()));

            nonTaxableMemberContributionInterest = nonTaxableMemberContributionInterest
                    .add(avoidNull(contribution.getInterestNonTaxableMemberContribution()));


            memberInterest = memberInterest.add(avoidNull(contribution.getInterestMemContribution()));

            companyInterest = companyInterest.add(avoidNull(contribution.getInterestCompanyContribution()));

            taxableVpfContributionInterest = taxableVpfContributionInterest.add(avoidNull(contribution.getInterestTaxableVpfContribution()));
            nonTaxableVpfContributionInterest = nonTaxableVpfContributionInterest.add(avoidNull(contribution.getInterestNonTaxableVpfContribution()));
            vpfInterest = vpfInterest.add(avoidNull(contribution.getVpfContributionInterest()));

        }

        Contribution currentYearTotalContributions = new Contribution();

        currentYearTotalContributions.setTaxableMemberContribution(taxableMemberContribution);
        currentYearTotalContributions.setNonTaxableMemberContribution(nonTaxableMemberContribution);
        currentYearTotalContributions.setMemberContribution(memberContribution);

        currentYearTotalContributions.setCompanyContribution(companyContribution);

        currentYearTotalContributions.setTaxableVpfContribution(taxableVpfContribution);
        currentYearTotalContributions.setNonTaxableVpfContribution(nonTaxableVpfContribution);
        currentYearTotalContributions.setVpfContribution(vpfContribution);

        currentYearTotalContributions.setInterestTaxableMemberContribution(taxableMemberContributionInterest);
        currentYearTotalContributions.setInterestNonTaxableMemberContribution(nonTaxableMemberContributionInterest);

        currentYearTotalContributions.setInterestCompanyContribution(companyInterest);

        currentYearTotalContributions.setInterestTaxableVpfContribution(taxableVpfContributionInterest);
        currentYearTotalContributions.setInterestNonTaxableVpfContribution(nonTaxableVpfContributionInterest);
        currentYearTotalContributions.setVpfContributionInterest(vpfInterest);

        return currentYearTotalContributions;

    }

    private Contribution calculateTotalContribution(List<Contribution> contributions, JSONObject yearEndReportRow){

        BigDecimal taxableMemberContribution = BigDecimal.ZERO;
        BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;
        BigDecimal memberContribution = BigDecimal.ZERO;

        BigDecimal companyContribution = BigDecimal.ZERO;

        BigDecimal taxableVpfContribution = BigDecimal.ZERO;
        BigDecimal nonTaxableVpfContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal taxableMemberContributionInterest = BigDecimal.ZERO;
        BigDecimal nonTaxableMemberContributionInterest = BigDecimal.ZERO;
        BigDecimal memberInterest = BigDecimal.ZERO;

        BigDecimal companyInterest = BigDecimal.ZERO;

        BigDecimal taxableVpfContributionInterest = BigDecimal.ZERO;
        BigDecimal nonTaxableVpfContributionInterest = BigDecimal.ZERO;
        BigDecimal vpfInterest = BigDecimal.ZERO;

        Set<Integer> foundContributions = new HashSet<>();

        for (Contribution contribution:contributions) {

            taxableMemberContribution = taxableMemberContribution.add(avoidNull(contribution.getTaxableMemberContribution()));
            nonTaxableMemberContribution = nonTaxableMemberContribution.add(avoidNull(contribution.getNonTaxableMemberContribution()));
            memberContribution = memberContribution.add(avoidNull(contribution.getMemberContribution()));

            companyContribution = companyContribution.add(avoidNull(contribution.getCompanyContribution()));

            taxableVpfContribution = taxableVpfContribution.add(avoidNull(contribution.getTaxableVpfContribution()));
            nonTaxableVpfContribution = nonTaxableVpfContribution.add(avoidNull(contribution.getNonTaxableVpfContribution()));
            vpfContribution = vpfContribution.add(contribution.getVpfContribution());

            taxableMemberContributionInterest = taxableMemberContributionInterest.add(avoidNull(contribution.getInterestTaxableMemberContribution()));

            nonTaxableMemberContributionInterest = nonTaxableMemberContributionInterest
                    .add(avoidNull(contribution.getInterestNonTaxableMemberContribution()));


            memberInterest = memberInterest.add(avoidNull(contribution.getInterestMemContribution()));

            companyInterest = companyInterest.add(avoidNull(contribution.getInterestCompanyContribution()));

            taxableVpfContributionInterest = taxableVpfContributionInterest.add(avoidNull(contribution.getInterestTaxableVpfContribution()));
            nonTaxableVpfContributionInterest = nonTaxableVpfContributionInterest.add(avoidNull(contribution.getInterestNonTaxableVpfContribution()));
            vpfInterest = vpfInterest.add(avoidNull(contribution.getVpfContributionInterest()));

            if(contribution.getSubType() == 0) {
                YearEndReport.addOpeningBalanceDetails(yearEndReportRow, contribution);
            }else {
                YearEndReport.addContribution(yearEndReportRow, contribution);
            }

            foundContributions.add(contribution.getSubType());

        }

        for(int i=1; i<13; i++){

            if(!foundContributions.contains(i)){

                Contribution contribution = new Contribution(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

                contribution.setSubType(i);

                contribution.setInterestMemContribution(BigDecimal.ZERO);
                contribution.setInterestCompanyContribution(BigDecimal.ZERO);
                contribution.setVpfContributionInterest(BigDecimal.ZERO);

                YearEndReport.addContribution(yearEndReportRow, contribution);

            }

        }

        if(!foundContributions.contains(0)){

            Contribution contribution = new Contribution(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

            contribution.setSubType(0);

            contribution.setInterestMemContribution(BigDecimal.ZERO);
            contribution.setInterestCompanyContribution(BigDecimal.ZERO);
            contribution.setVpfContributionInterest(BigDecimal.ZERO);

            YearEndReport.addOpeningBalanceDetails(yearEndReportRow, contribution);

        }

        Contribution contribution = new Contribution(memberContribution, companyContribution, vpfContribution);

        contribution.setNonTaxableMemberContribution(nonTaxableMemberContribution);
        contribution.setTaxableMemberContribution(taxableMemberContribution);

        contribution.setNonTaxableVpfContribution(nonTaxableVpfContribution);
        contribution.setTaxableVpfContribution(taxableVpfContribution);

        contribution.setInterestNonTaxableMemberContribution(nonTaxableMemberContributionInterest);
        contribution.setInterestTaxableMemberContribution(taxableMemberContributionInterest);

        contribution.setInterestCompanyContribution(companyInterest);

        contribution.setInterestNonTaxableVpfContribution(nonTaxableVpfContributionInterest);
        contribution.setInterestTaxableVpfContribution(taxableVpfContributionInterest);

        return contribution;

    }

    private void addTransferInsToOpeningBalance(Contribution contribution, List<TransferIn> transferIns,
                                                JSONObject yearEndReportRow) {


        BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;
        BigDecimal taxableMemberContribution = BigDecimal.ZERO;
        BigDecimal memberContribution = BigDecimal.ZERO;

        BigDecimal companyContribution = BigDecimal.ZERO;

        BigDecimal interestNonTaxableMemberContribution = BigDecimal.ZERO;
        BigDecimal interestTaxableMemberContribution = BigDecimal.ZERO;
        BigDecimal memberInterest = BigDecimal.ZERO;

        BigDecimal companyInterest = BigDecimal.ZERO;

        for (TransferIn transferIn:transferIns) {

            TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

            taxableMemberContribution = taxableMemberContribution.add(avoidNull(transferInFinalDetails.getTaxableMemberContribution()));
            nonTaxableMemberContribution = nonTaxableMemberContribution.add(avoidNull(transferInFinalDetails.getNonTaxableMemberContribution()));
            memberContribution = memberContribution.add(avoidNull(transferInFinalDetails.getMemberContribution()));

            companyContribution = companyContribution.add(avoidNull(transferInFinalDetails.getCompanyContribution()));

            interestNonTaxableMemberContribution = interestNonTaxableMemberContribution.add(transferInFinalDetails.getInterestNonTaxableMemberContribution());
            interestTaxableMemberContribution = interestTaxableMemberContribution.add(transferInFinalDetails.getInterestTaxableMemberContribution());
            memberInterest = memberInterest.add(avoidNull(transferInFinalDetails.getInterestOnMemberContribution()));

            companyInterest = companyInterest.add(avoidNull(transferInFinalDetails.getInterestOnCompanyContribution()));

        }

        BigDecimal newNonTaxableMemberContribution = contribution.getNonTaxableMemberContribution()
                .add(nonTaxableMemberContribution);

        BigDecimal newTaxableMemberContribution = contribution.getTaxableMemberContribution()
                .add(taxableMemberContribution);

        BigDecimal newMemberContribution = contribution.getMemberContribution()
                .add(memberContribution);

        BigDecimal newCompanyContribution = contribution.getCompanyContribution()
                .add(companyContribution);

        BigDecimal newNonTaxableMemberContributionInterest = contribution.getInterestNonTaxableMemberContribution()
                        .add(interestNonTaxableMemberContribution);

        BigDecimal newTaxableMemberContributionInterest = contribution.getInterestTaxableMemberContribution()
                        .add(interestTaxableMemberContribution);

        BigDecimal newCompanyContributionInterest = contribution.getInterestCompanyContribution()
                        .add(avoidNull(companyInterest));

        contribution.setNonTaxableMemberContribution(newNonTaxableMemberContribution);
        contribution.setTaxableMemberContribution(newTaxableMemberContribution);
        contribution.setMemberContribution(newMemberContribution);

        contribution.setCompanyContribution(newCompanyContribution);

        contribution.setInterestNonTaxableMemberContribution(newNonTaxableMemberContributionInterest);
        contribution.setInterestTaxableMemberContribution(newTaxableMemberContributionInterest);

        contribution.setInterestCompanyContribution(newCompanyContributionInterest);

        YearEndReport.addTransferInDetails(yearEndReportRow,
                nonTaxableMemberContribution,
                taxableMemberContribution,
                companyContribution,
                interestNonTaxableMemberContribution,
                interestTaxableMemberContribution,
                newCompanyContributionInterest);

    }


    private Contribution getTotalLoanTaken(List<Loan> loans, JSONObject yearEndReportRow) {

        BigDecimal nonTaxableLoanMember = BigDecimal.ZERO;
        BigDecimal taxableLoanMember = BigDecimal.ZERO;
        BigDecimal loanMember = BigDecimal.ZERO;

        BigDecimal loanCompany = BigDecimal.ZERO;

        BigDecimal nonTaxableLoanVpf = BigDecimal.ZERO;
        BigDecimal taxableLoanVpf = BigDecimal.ZERO;
        BigDecimal loanVpf = BigDecimal.ZERO;

        BigDecimal nonTaxableInterestMember = BigDecimal.ZERO;
        BigDecimal taxableInterestMember = BigDecimal.ZERO;
        BigDecimal interestMember = BigDecimal.ZERO;

        BigDecimal interestCompany = BigDecimal.ZERO;

        BigDecimal nonTaxableInterestVpf = BigDecimal.ZERO;
        BigDecimal taxableInterestVpf = BigDecimal.ZERO;
        BigDecimal interestVpf = BigDecimal.ZERO;

        for (Loan loan:loans) {

            LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

            nonTaxableLoanMember = nonTaxableLoanMember.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnNonTaxableMemberContribution()));
            taxableLoanMember = taxableLoanMember.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnTaxableMemberContribution()));
            loanMember = loanMember.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnMemContribution()));

            loanCompany = loanCompany.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnCompanyContribution()));

            nonTaxableLoanVpf = nonTaxableLoanVpf.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnNonTaxableVpfContribution()));
            taxableLoanVpf = taxableLoanVpf.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnTaxableVpfContribution()));
            loanVpf = loanVpf.add(avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnVPFContribution()));

            nonTaxableInterestMember = nonTaxableInterestMember.add(avoidNull(loanWithDrawalsFinalDetails.getInterestOnLoanAmountOnNonTaxableMemberContribution()));
            taxableInterestMember = taxableInterestMember.add(avoidNull(loanWithDrawalsFinalDetails.getInterestOnLoanAmountOnTaxableMemberContribution()));
            interestMember = interestMember.add(avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnMemContribution()));

            interestCompany = interestCompany.add(avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnCompanyContribution()));

            nonTaxableInterestVpf = nonTaxableInterestVpf.add(loanWithDrawalsFinalDetails.getInterestOnLoanAmountOnNonTaxableVpfContribution());
            taxableInterestVpf = taxableInterestVpf.add(loanWithDrawalsFinalDetails.getInterestOnLoanAmountOnTaxableVpfContribution());
            interestVpf = interestVpf.add(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnVPFContribution());

        }

        Contribution totalLoanTaken = new Contribution(loanMember, loanCompany, loanVpf);

        totalLoanTaken.setNonTaxableMemberContribution(nonTaxableLoanMember);
        totalLoanTaken.setTaxableMemberContribution(taxableLoanMember);
        totalLoanTaken.setMemberContribution(loanMember);

        totalLoanTaken.setCompanyContribution(loanCompany);

        totalLoanTaken.setNonTaxableVpfContribution(nonTaxableLoanVpf);
        totalLoanTaken.setTaxableVpfContribution(taxableLoanVpf);
        totalLoanTaken.setVpfContribution(loanVpf);

        totalLoanTaken.setInterestNonTaxableMemberContribution(nonTaxableInterestMember);
        totalLoanTaken.setInterestTaxableMemberContribution(taxableInterestMember);
        totalLoanTaken.setInterestMemContribution(interestMember);

        totalLoanTaken.setInterestCompanyContribution(interestCompany);

        totalLoanTaken.setInterestNonTaxableVpfContribution(nonTaxableInterestVpf);
        totalLoanTaken.setInterestTaxableVpfContribution(taxableInterestVpf);
        totalLoanTaken.setVpfContributionInterest(interestVpf);

        YearEndReport.addLoanDetails(yearEndReportRow,
                nonTaxableLoanMember,
                taxableLoanMember,
                loanCompany,
                nonTaxableLoanVpf,
                taxableLoanVpf,
                nonTaxableInterestMember,
                taxableInterestMember,
                interestCompany,
                nonTaxableInterestVpf,
                taxableInterestVpf);

        return totalLoanTaken;

    }

    @Override
    public Optional<YearEndProcess> getPublishedYearEndProcessForSettlement(Employee employee, Integer year) {
        return yearEndProcessRepository.getPublishedYearEndProcessForSettlement(employee, year);
    }

    private Pair<BigDecimal, BigDecimal> calculateTds(Contribution totalContribution, TdsDeduction tdsDeduction){

        BigDecimal interestTaxableMemberContribution = totalContribution.getInterestTaxableMemberContribution();

        BigDecimal interestTaxableVpfContribution = totalContribution.getInterestTaxableVpfContribution();

        BigDecimal tdsOnTaxableMemberContribution = tdsDeduction.calculate(interestTaxableMemberContribution);

        String tdsOnTaxableMemberContributionStr = String.format("%.2f", tdsOnTaxableMemberContribution.floatValue());
        Float f =  Float.valueOf(tdsOnTaxableMemberContributionStr);


        BigDecimal tdsOnTaxableVpfContribution = tdsDeduction.calculate(interestTaxableVpfContribution);

        String tdsOnTaxableVpfContributionStr = String.format("%.2f", tdsOnTaxableVpfContribution.floatValue());
        Float c =  Float.valueOf(tdsOnTaxableVpfContributionStr);

        return new Pair<>() {
            @Override
            public BigDecimal getFirst() {
                return BigDecimal.valueOf(Math.round(f));
            }

            @Override
            public BigDecimal getSecond() {
                return BigDecimal.valueOf(Math.round(c));
            }
        };
    }





}
