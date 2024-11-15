package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.dtos.pdf.ContributionDetailsForMonthlyStatement;
import com.coreintegra.pftrust.dtos.pdf.MonthlyStatement;
import com.coreintegra.pftrust.dtos.pdf.TransferInDetailsForMonthlyStatement;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.Nominee;
import com.coreintegra.pftrust.entities.pf.loan.LoanWithDrawalsFinalDetails;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutStatus;
import com.coreintegra.pftrust.projections.LoanContributionDTO;
import com.coreintegra.pftrust.repositories.pf.contribution.ContributionRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.employee.NomineeRepository;
import com.coreintegra.pftrust.repositories.pf.settlement.SettlementRepository;
import com.coreintegra.pftrust.repositories.pf.settlement.SettlementStatusRepository;
import com.coreintegra.pftrust.repositories.pf.transferin.TransferInRepository;
import com.coreintegra.pftrust.repositories.pf.transferout.TransferOutRepository;
import com.coreintegra.pftrust.repositories.pf.transferout.TransferOutStatusRepository;
import com.coreintegra.pftrust.searchutil.SearchTransferInSpecification;
import com.coreintegra.pftrust.services.pf.loans.LoanService;
import com.coreintegra.pftrust.services.pf.pdf.GenerateMonthlyStatement;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonthlyStatementServiceImpl implements MonthlyStatementService {

    private final EmployeeRepository employeeRepository;
    private final ContributionRepository contributionRepository;
    private final TransferInRepository transferInRepository;

    private final SettlementRepository settlementRepository;
    private final TransferOutRepository transferOutRepository;

    private final SettlementStatusRepository settlementStatusRepository;
    private final TransferOutStatusRepository transferOutStatusRepository;

    private final LoanService loanService;

    private final GenerateMonthlyStatement generateMonthlyStatement;

    private final NomineeRepository nomineeRepository;

    public MonthlyStatementServiceImpl(EmployeeRepository employeeRepository,
                                       ContributionRepository contributionRepository,
                                       TransferInRepository transferInRepository,
                                       SettlementRepository settlementRepository,
                                       TransferOutRepository transferOutRepository,
                                       SettlementStatusRepository settlementStatusRepository,
                                       TransferOutStatusRepository transferOutStatusRepository,
                                       LoanService loanService, GenerateMonthlyStatement generateMonthlyStatement,
                                       NomineeRepository nomineeRepository) {
        this.employeeRepository = employeeRepository;
        this.contributionRepository = contributionRepository;
        this.transferInRepository = transferInRepository;
        this.settlementRepository = settlementRepository;
        this.transferOutRepository = transferOutRepository;
        this.settlementStatusRepository = settlementStatusRepository;
        this.transferOutStatusRepository = transferOutStatusRepository;
        this.loanService = loanService;
        this.generateMonthlyStatement = generateMonthlyStatement;
        this.nomineeRepository = nomineeRepository;
    }

    @Override
    public MonthlyStatement getMonthlyStatementDetails(String entityId, Integer year) throws ParseException {

        Optional<Employee> optional = employeeRepository.findByEntityIdAndIsActive(entityId, true);

        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Employee Not Found Exception.");
        }

        Employee employee = optional.get();

        List<Contribution> contributions = contributionRepository
                .findAllByEmployeeAndYearAndIsActiveOrderBySubTypeAsc(employee, year, true);

        List<ContributionDetailsForMonthlyStatement> contributionDetailsForMS = getContributions(contributions, year);

        ContributionDetailsForMonthlyStatement totalContributionForMS = getTotalContribution(contributions);

        List<TransferIn> transferInList = getTransferIns(employee, year);

        TransferInFinalDetails totalTransferInDetails = getTotalTransferInDetailsForMs(transferInList);

        List<TransferInDetailsForMonthlyStatement> transfers = getTransferInDetailsForMS(transferInList);

        LoanWithDrawalsFinalDetails totalLoanWithdrawals = loanService.getTotalLoanWithDrawals(employee, year);

        MonthlyStatement monthlyStatement = new MonthlyStatement();

        monthlyStatement.setName(employee.getName());
        monthlyStatement.setTokenNumber(employee.getTokenNumber());
        monthlyStatement.setPfNumber(employee.getPfNumber());
        monthlyStatement.setUnitCode(employee.getUnitCode());

        Optional<Contribution> latestContributionForYear = contributionRepository.findTop1ByEmployeeAndYearAndIsActiveOrderBySubTypeDesc(employee,
                year, true);

        latestContributionForYear.ifPresent(contribution -> monthlyStatement.setLastRecoveryDate(contribution.getRecoveryDate()));

        monthlyStatement.setStatus(employee.getContributionStatus().getDescription());

        if (employee.getContributionStatus().getSymbol().equalsIgnoreCase("S")){

            Optional<SettlementStatus> optionalSettlementStatus = settlementStatusRepository.findById(2L);

            Optional<TransferOutStatus> optionalTransferOutStatus = transferOutStatusRepository.findById(2L);

            Optional<Settlement> optionalSettlement = settlementRepository
                    .getSettlementByEmployeeAndSettlementStatusAndIsActive(employee,
                            optionalSettlementStatus.get(), true);

            Optional<TransferOut> optionalTransferOut = transferOutRepository
                    .getTransferOutByEmployeeAndTransferOutStatusAndIsActive(employee,
                            optionalTransferOutStatus.get(), true);

            optionalSettlement.ifPresent(settlement -> monthlyStatement.setStatusDate(settlement.getDateOfSettlement()));

            optionalTransferOut.ifPresent(transferOut -> monthlyStatement.setStatusDate(transferOut.getDueDate()));

        }

        List<Nominee> nomineeList = nomineeRepository.findAllByEmployeeAndIsActive(employee, true);

        List<com.coreintegra.pftrust.dtos.pdf.Nominee> nomineeList1 = nomineeList.stream().map(nominee -> new com.coreintegra.pftrust.dtos.pdf.Nominee(nominee.getName(),
                nominee.getRelationship(), nominee.getShare())).collect(Collectors.toList());

        monthlyStatement.setNominees(nomineeList1);

        monthlyStatement.setYearOpeningDate(FinancialYearAndMonth.getYearOpeningFromFY(year));

        ContributionDetailsForMonthlyStatement yearOpeningContribution = contributionDetailsForMS.get(0);

        monthlyStatement.setYearOpeningMemberContribution(yearOpeningContribution.getMemberContribution());
        monthlyStatement.setYearOpeningCompanyContribution(yearOpeningContribution.getCompanyContribution());
        monthlyStatement.setYearOpeningVpfContribution(yearOpeningContribution.getVpfContribution());

        monthlyStatement.setTotalYearOpeningContribution(yearOpeningContribution.getTotalContribution());

        monthlyStatement.setFinantialYear(year);

        contributionDetailsForMS.remove(0);

        monthlyStatement.setContributionDetails(contributionDetailsForMS);

        monthlyStatement.setTotalMemberContribution(totalContributionForMS.getMemberContribution());
        monthlyStatement.setTotalCompanyContribution(totalContributionForMS.getCompanyContribution());
        monthlyStatement.setTotalVpfContribution(totalContributionForMS.getVpfContribution());

        monthlyStatement.setTotalContribution(totalContributionForMS.getTotalContribution());

        monthlyStatement.setTransferInMemberContribution(totalTransferInDetails.getMemberContribution());
        monthlyStatement.setTransferInCompanyContribution(totalTransferInDetails.getCompanyContribution());
        monthlyStatement.setTransferInVpfContribution(totalTransferInDetails.getVpfContribution());

        monthlyStatement.setTotalTransferInContribution(totalTransferInDetails.getTotal());

        monthlyStatement.setClosingDate(FinancialYearAndMonth.getClosingDate(year));

        monthlyStatement.setTransferInDetailsForMS(transfers);

        if(totalLoanWithdrawals != null) {

            monthlyStatement.setLoanOnMemberContribution(totalLoanWithdrawals.getLoanAmountOnMemContribution());
            monthlyStatement.setLoanOnCompanyContribution(totalLoanWithdrawals.getLoanAmountOnCompanyContribution());
            monthlyStatement.setLoanOnVpfContribution(totalLoanWithdrawals.getLoanAmountOnVPFContribution());
            monthlyStatement.setTotalLoanOnContribution(totalLoanWithdrawals.getTotalLoanWithDrawal());

        }

        return monthlyStatement;

    }

    @Override
    public void generateMonthlyStatement(String entityId, Integer year) throws Exception {

        MonthlyStatement monthlyStatementDetails = getMonthlyStatementDetails(entityId, year);

        ByteArrayOutputStream byteArrayOutputStream = generateMonthlyStatement.generate(monthlyStatementDetails);

        try(OutputStream outputStream = new FileOutputStream("statement.pdf")) {
            byteArrayOutputStream.writeTo(outputStream);
        }

    }

    private List<ContributionDetailsForMonthlyStatement> getContributions(List<Contribution> contributions, Integer year){

        List<ContributionDetailsForMonthlyStatement> contributionDetailsForMonthlyStatementList = contributions.stream().map(contribution ->
                        new ContributionDetailsForMonthlyStatement(contribution.getMemberContribution(),
                                contribution.getCompanyContribution(), contribution.getVpfContribution(),
                                contribution.getYear(), contribution.getSubType(), contribution.getPfBase()))
                .collect(Collectors.toList());


        return addRemainingContributionsDetails(contributionDetailsForMonthlyStatementList, year);

    }

    private List<ContributionDetailsForMonthlyStatement> addRemainingContributionsDetails(
            List<ContributionDetailsForMonthlyStatement> contributionDetailsForMSList, Integer year) {

        if (contributionDetailsForMSList != null && contributionDetailsForMSList.size() >= 13)
            return contributionDetailsForMSList;

        List<ContributionDetailsForMonthlyStatement> newContributionDTOList = new ArrayList<>();

        int month = 0;

        while (newContributionDTOList.size() < 13) {

            ContributionDetailsForMonthlyStatement contributionDTO = new ContributionDetailsForMonthlyStatement();

            contributionDTO.setMonth(month);
            contributionDTO.setYear(year);

            newContributionDTOList.add(contributionDTO);

            month++;

        }

        if (contributionDetailsForMSList == null || contributionDetailsForMSList.size() == 0)
            return newContributionDTOList;

        for (ContributionDetailsForMonthlyStatement contributionDetailsForMS : contributionDetailsForMSList) {
            newContributionDTOList.set(contributionDetailsForMS.getMonth(), contributionDetailsForMS);
        }

        return newContributionDTOList;

    }


    private ContributionDetailsForMonthlyStatement getTotalContribution(List<Contribution> contributions){

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        for (Contribution contribution:contributions) {
            if(contribution.getSubType() > 0){
                memberContribution = add(memberContribution, contribution.getMemberContribution());
                companyContribution = add(companyContribution, contribution.getCompanyContribution());
                vpfContribution = add(vpfContribution, contribution.getVpfContribution());
            }
        }

        return new ContributionDetailsForMonthlyStatement(memberContribution, companyContribution, vpfContribution);
    }


    private BigDecimal add(BigDecimal a, BigDecimal b){
        return a.add(b == null ? BigDecimal.ZERO : b);
    }


    private List<TransferIn> getTransferIns(Employee employee, Integer year) {
        return transferInRepository.findAll(getTransferInSpecification(employee, year));
    }

    private Specification<TransferIn> getTransferInSpecification(Employee employee, Integer year) {

        SearchTransferInSpecification transferInSpecification = new SearchTransferInSpecification();

        return transferInSpecification.employeeSpecification(employee)
                .and(transferInSpecification.yearSpecification(year));

    }

    private List<TransferInDetailsForMonthlyStatement> getTransferInDetailsForMS(List<TransferIn> transferInList){
        return transferInList.stream().map(transferIn ->
                new TransferInDetailsForMonthlyStatement(transferIn.getCreatedAtTimestamp(),
                        transferIn.getPostingDate(), transferIn.getTransferInFinalDetails().getMemberContribution(),
                        transferIn.getTransferInFinalDetails().getCompanyContribution(),
                        transferIn.getPreviousCompany().getName(), transferIn.getSapDocumentNumber()))
                .collect(Collectors.toList());
    }


    private TransferInFinalDetails getTotalTransferInDetailsForMs(List<TransferIn> transferInList) {

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        for (TransferIn transferIn:transferInList) {
            memberContribution = add(memberContribution, transferIn.getTransferInFinalDetails().getMemberContribution());
            companyContribution = add(companyContribution, transferIn.getTransferInFinalDetails().getCompanyContribution());
        }

        return new TransferInFinalDetails(memberContribution, companyContribution, vpfContribution);

    }


}
