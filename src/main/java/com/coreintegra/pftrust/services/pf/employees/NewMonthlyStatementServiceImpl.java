package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.dtos.pdf.*;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.LoanWithDrawalsFinalDetails;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutStatus;
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
import com.coreintegra.pftrust.services.pf.pdf.NewGenerateMonthlyStatement;
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
public class NewMonthlyStatementServiceImpl implements NewMonthlyStatementService {

    private final EmployeeRepository employeeRepository;
    private final ContributionRepository contributionRepository;
    private final TransferInRepository transferInRepository;

    private final SettlementRepository settlementRepository;
    private final TransferOutRepository transferOutRepository;

    private final SettlementStatusRepository settlementStatusRepository;
    private final TransferOutStatusRepository transferOutStatusRepository;

    private final LoanService loanService;

    private final NewGenerateMonthlyStatement generateMonthlyStatement;

    private final NomineeRepository nomineeRepository;

    public NewMonthlyStatementServiceImpl(EmployeeRepository employeeRepository,
                                          ContributionRepository contributionRepository,
                                          TransferInRepository transferInRepository,
                                          SettlementRepository settlementRepository,
                                          TransferOutRepository transferOutRepository,
                                          SettlementStatusRepository settlementStatusRepository,
                                          TransferOutStatusRepository transferOutStatusRepository,
                                          LoanService loanService,
                                          NewGenerateMonthlyStatement generateMonthlyStatement,
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
    public NewMonthlyStatement getMonthlyStatementDetails(String entityId, Integer year) throws ParseException {

        Optional<Employee> optional = employeeRepository.findByEntityIdAndIsActive(entityId, true);

        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Employee Not Found Exception.");
        }

        Employee employee = optional.get();

        List<Contribution> contributions = contributionRepository
                .findAllByEmployeeAndYearAndIsActiveOrderBySubTypeAsc(employee, year, true);

        List<NewContributionDetailsForMonthlyStatement> contributionDetailsForMS = getContributions(contributions, year);

        NewContributionDetailsForMonthlyStatement totalContributionForMS = getTotalContribution(contributions);

        List<TransferIn> transferInList = getTransferIns(employee, year);

        TransferInFinalDetails totalTransferInDetails = getTotalTransferInDetailsForMs(transferInList);

        List<TransferInDetailsForMonthlyStatement> transfers = getTransferInDetailsForMS(transferInList);

        LoanWithDrawalsFinalDetails totalLoanWithdrawals = loanService.getTotalLoanWithDrawals(employee, year);

        NewMonthlyStatement monthlyStatement = new NewMonthlyStatement();

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

        List<com.coreintegra.pftrust.entities.pf.employee.Nominee> nomineeList =
                nomineeRepository.findAllByEmployeeAndIsActive(employee, true);

        List<com.coreintegra.pftrust.dtos.pdf.Nominee> nomineeList1 = nomineeList.stream()
                .map(nominee -> new com.coreintegra.pftrust.dtos.pdf.Nominee(nominee.getName(),
                        nominee.getRelationship(), nominee.getShare()))
                .collect(Collectors.toList());

        monthlyStatement.setNominees(nomineeList1);

        monthlyStatement.setYearOpeningDate(FinancialYearAndMonth.getYearOpeningFromFY(year));

        NewContributionDetailsForMonthlyStatement yearOpeningContribution = contributionDetailsForMS.get(0);

        monthlyStatement.setYearOpeningMemberContributionTaxFree(yearOpeningContribution.getMemberContributionTaxFree());
        monthlyStatement.setYearOpeningMemberContributionTaxable(yearOpeningContribution.getMemberContributionTaxable());
        monthlyStatement.setYearOpeningMemberContribution(yearOpeningContribution.getMemberContribution());

        monthlyStatement.setYearOpeningCompanyContribution(yearOpeningContribution.getCompanyContribution());

        monthlyStatement.setYearOpeningVpfContributionTaxFree(yearOpeningContribution.getVpfContributionTaxFree());
        monthlyStatement.setYearOpeningVpfContributionTaxable(yearOpeningContribution.getVpfContributionTaxable());
        monthlyStatement.setYearOpeningVpfContribution(yearOpeningContribution.getVpfContribution());

        monthlyStatement.setTotalYearOpeningContribution(yearOpeningContribution.getTotalContribution());

        monthlyStatement.setFinantialYear(year);

        contributionDetailsForMS.remove(0);

        monthlyStatement.setContributionDetails(contributionDetailsForMS);

        monthlyStatement.setContributionDetails(contributionDetailsForMS);

        monthlyStatement.setTotalMemberContributionTaxFree(totalContributionForMS.getMemberContributionTaxFree());
        monthlyStatement.setTotalMemberContributionTaxable(totalContributionForMS.getMemberContributionTaxable());
        monthlyStatement.setTotalMemberContribution(totalContributionForMS.getMemberContribution());

        monthlyStatement.setTotalCompanyContribution(totalContributionForMS.getCompanyContribution());

        monthlyStatement.setTotalVpfContributionTaxFree(totalContributionForMS.getVpfContributionTaxFree());
        monthlyStatement.setTotalVpfContributionTaxable(totalContributionForMS.getVpfContributionTaxable());
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

        NewMonthlyStatement monthlyStatementDetails = getMonthlyStatementDetails(entityId, year);

        ByteArrayOutputStream byteArrayOutputStream = generateMonthlyStatement.generate(monthlyStatementDetails);

        try(OutputStream outputStream = new FileOutputStream("statement.pdf")) {
            byteArrayOutputStream.writeTo(outputStream);
        }

    }


    private List<NewContributionDetailsForMonthlyStatement> getContributions(List<Contribution> contributions, Integer year){

        List<NewContributionDetailsForMonthlyStatement> contributionDetailsForMonthlyStatementList = contributions.stream().map(contribution ->
                        new NewContributionDetailsForMonthlyStatement(
                                contribution.getNonTaxableMemberContribution(),
                                contribution.getTaxableMemberContribution(),
                                contribution.getMemberContribution(),
                                contribution.getCompanyContribution(),
                                contribution.getNonTaxableVpfContribution(),
                                contribution.getTaxableVpfContribution(),
                                contribution.getVpfContribution(),
                                contribution.getYear(),
                                contribution.getSubType(),
                                contribution.getPfBase()))
                .collect(Collectors.toList());


        return addRemainingContributionsDetails(contributionDetailsForMonthlyStatementList, year);

    }

    private List<NewContributionDetailsForMonthlyStatement> addRemainingContributionsDetails(
            List<NewContributionDetailsForMonthlyStatement> contributionDetailsForMSList, Integer year) {

        if (contributionDetailsForMSList != null && contributionDetailsForMSList.size() >= 13)
            return contributionDetailsForMSList;

        List<NewContributionDetailsForMonthlyStatement> newContributionDTOList = new ArrayList<>();

        int month = 0;

        while (newContributionDTOList.size() < 13) {

            NewContributionDetailsForMonthlyStatement contributionDTO = new NewContributionDetailsForMonthlyStatement();

            contributionDTO.setMonth(month);
            contributionDTO.setYear(year);

            newContributionDTOList.add(contributionDTO);

            month++;

        }

        if (contributionDetailsForMSList == null || contributionDetailsForMSList.size() == 0)
            return newContributionDTOList;

        for (NewContributionDetailsForMonthlyStatement contributionDetailsForMS : contributionDetailsForMSList) {
            newContributionDTOList.set(contributionDetailsForMS.getMonth(), contributionDetailsForMS);
        }

        return newContributionDTOList;

    }

    private NewContributionDetailsForMonthlyStatement getTotalContribution(List<Contribution> contributions){

        BigDecimal taxFreeMemberContribution = BigDecimal.ZERO;
        BigDecimal taxableMemberContribution = BigDecimal.ZERO;
        BigDecimal memberContribution = BigDecimal.ZERO;

        BigDecimal companyContribution = BigDecimal.ZERO;

        BigDecimal taxFreeVpfContribution = BigDecimal.ZERO;
        BigDecimal taxableVpfContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        for (Contribution contribution:contributions) {
            if(contribution.getSubType() > 0){

                taxFreeMemberContribution = add(taxFreeMemberContribution, contribution.getNonTaxableMemberContribution());
                taxableMemberContribution = add(taxableMemberContribution, contribution.getTaxableMemberContribution());
                memberContribution = add(memberContribution, contribution.getMemberContribution());

                companyContribution = add(companyContribution, contribution.getCompanyContribution());

                taxFreeVpfContribution = add(taxFreeVpfContribution, contribution.getNonTaxableVpfContribution());
                taxableVpfContribution = add(taxableVpfContribution, contribution.getTaxableVpfContribution());
                vpfContribution = add(vpfContribution, contribution.getVpfContribution());

            }
        }

        return new NewContributionDetailsForMonthlyStatement(taxFreeMemberContribution,
                taxableMemberContribution, memberContribution, companyContribution,
                taxFreeVpfContribution, taxableVpfContribution, vpfContribution);
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

    private List<TransferInDetailsForMonthlyStatement> getTransferInDetailsForMS(List<TransferIn> transferInList){
        return transferInList.stream().map(transferIn ->
                        new TransferInDetailsForMonthlyStatement(transferIn.getCreatedAtTimestamp(),
                                transferIn.getPostingDate(), transferIn.getTransferInFinalDetails().getMemberContribution(),
                                transferIn.getTransferInFinalDetails().getCompanyContribution(),
                                transferIn.getPreviousCompany().getName(), transferIn.getSapDocumentNumber()))
                .collect(Collectors.toList());
    }


}
