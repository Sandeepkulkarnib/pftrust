package com.coreintegra.pftrust.services.pf.settlement;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.Nominee;
import com.coreintegra.pftrust.dtos.pdf.settlement.CalculatedAmount;
import com.coreintegra.pftrust.dtos.pdf.settlement.SettlementAnnexure;
import com.coreintegra.pftrust.dtos.pdf.settlement.SettlementDispatchLetter;
import com.coreintegra.pftrust.dtos.pdf.settlement.Worksheet;
import com.coreintegra.pftrust.entities.base.EmailAttachment;
import com.coreintegra.pftrust.entities.pf.*;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.employee.ContributionStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanWithDrawalsFinalDetails;
import com.coreintegra.pftrust.entities.pf.loan.dtos.StatusDTO;
import com.coreintegra.pftrust.entities.pf.loan.dtos.TypeDTO;
import com.coreintegra.pftrust.entities.pf.settlement.*;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementDetailsDTO;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.NotificationEmailDesignRepository;
import com.coreintegra.pftrust.repositories.pf.department.BankRepository;
import com.coreintegra.pftrust.repositories.pf.department.DocumentRepository;
import com.coreintegra.pftrust.repositories.pf.department.PaymentModeRepository;
import com.coreintegra.pftrust.repositories.pf.employee.ContributionStatusRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.settlement.*;
import com.coreintegra.pftrust.searchutil.SearchSettlementSpecification;
import com.coreintegra.pftrust.searchutil.SearchSpecificationForEmployee;
import com.coreintegra.pftrust.services.email.EmailService;
import com.coreintegra.pftrust.services.pf.contribution.ContributionService;
import com.coreintegra.pftrust.services.pf.department.FinancialYearAndMonthService;
import com.coreintegra.pftrust.services.pf.department.InterestRateService;
import com.coreintegra.pftrust.services.pf.department.UnitCodeService;
import com.coreintegra.pftrust.services.pf.interest.InterestProviderService;
import com.coreintegra.pftrust.services.pf.loans.LoanService;
import com.coreintegra.pftrust.services.pf.transferin.TransferInService;
import com.coreintegra.pftrust.util.*;
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

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

@Service
public class SettlementServiceImpl implements SettlementService {

    private final Logger logger = LoggerFactory.getLogger(SettlementService.class);

    private final EmployeeRepository employeeRepository;
    private final SettlementRepository settlementRepository;
    private final SettlementTypeRepository settlementTypeRepository;
    private final SettlementFinalDetailsRepository settlementFinalDetailsRepository;
    private final SettlementStatusRepository settlementStatusRepository;
    private final ContributionStatusRepository contributionStatusRepository;
    private final SettlementEmailStatusRepository settlementEmailStatusRepository;

    private final UnitCodeService unitCodeService;
    private final FinancialYearAndMonthService financialYearAndMonthService;
    private final EmailService emailService;
    private final ContributionService contributionService;
    private final LoanService loanService;
    private final TransferInService transferInService;
    private final InterestRateService interestRateService;
    private final InterestProviderService interestProviderService;
    private final DocumentRepository documentRepository;
    private final SettlementDocumentRepository settlementDocumentRepository;
    private final PaymentModeRepository paymentModeRepository;
    private final BankRepository bankRepository;

    private final NotificationEmailDesignRepository notificationEmailDesignRepository;

    public SettlementServiceImpl(EmployeeRepository employeeRepository,
                                 SettlementRepository settlementRepository,
                                 SettlementTypeRepository settlementTypeRepository,
                                 SettlementFinalDetailsRepository settlementFinalDetailsRepository,
                                 SettlementStatusRepository settlementStatusRepository,
                                 ContributionStatusRepository contributionStatusRepository,
                                 SettlementEmailStatusRepository settlementEmailStatusRepository,
                                 UnitCodeService unitCodeService,
                                 FinancialYearAndMonthService financialYearAndMonthService, EmailService emailService,
                                 ContributionService contributionService, LoanService loanService,
                                 TransferInService transferInService,
                                 InterestRateService interestRateService,
                                 InterestProviderService interestProviderService,
                                 DocumentRepository documentRepository,
                                 SettlementDocumentRepository settlementDocumentRepository,
                                 PaymentModeRepository paymentModeRepository, BankRepository bankRepository,
                                 NotificationEmailDesignRepository notificationEmailDesignRepository) {
        this.employeeRepository = employeeRepository;
        this.settlementRepository = settlementRepository;
        this.settlementTypeRepository = settlementTypeRepository;
        this.settlementFinalDetailsRepository = settlementFinalDetailsRepository;
        this.settlementStatusRepository = settlementStatusRepository;
        this.contributionStatusRepository = contributionStatusRepository;
        this.settlementEmailStatusRepository = settlementEmailStatusRepository;
        this.unitCodeService = unitCodeService;
        this.financialYearAndMonthService = financialYearAndMonthService;
        this.emailService = emailService;
        this.contributionService = contributionService;
        this.loanService = loanService;
        this.transferInService = transferInService;
        this.interestRateService = interestRateService;
        this.interestProviderService = interestProviderService;
        this.documentRepository = documentRepository;
        this.settlementDocumentRepository = settlementDocumentRepository;
        this.paymentModeRepository = paymentModeRepository;
        this.bankRepository = bankRepository;
        this.notificationEmailDesignRepository = notificationEmailDesignRepository;
    }

    @Transactional
    @Override
    @Async
    @ApplyTenantFilter
    public CompletableFuture<String> saveFetchedSettlement(Settlement settlement, String pernNumber,
                                                           SettlementFinalDetails settlementFinalDetails) {

        Optional<Employee> optionalEmployee = employeeRepository.findByPernNumberAndIsActive(pernNumber, true);

        if (!optionalEmployee.isPresent()) {
            throw new EntityNotFoundException("no employee found.");
        }

        Employee employee = optionalEmployee.get();

        settlement.setEmployee(employee);

        SettlementType settlementType = settlementTypeRepository
                .findByCodeAndIsActive(settlement.getSettlementType().getCode(), true);

        settlement.setSettlementType(settlementType);

        settlement.setBank(getBank(settlement.getBank().getName()));

        Settlement save = settlementRepository.save(settlement);

        settlementFinalDetails.setSettlement(save);

        settlementFinalDetailsRepository.save(settlementFinalDetails);

        ContributionStatus contributionStatus = new ContributionStatus();
        contributionStatus.setId(3L);

        employee.setContributionStatus(contributionStatus);

        employeeRepository.save(employee);

        logger.info("settlement saved successfully -> {}", save.getId());

        return CompletableFuture.completedFuture("done");

    }

    private Bank getBank(String bankName) {

        Bank bank = new Bank();

        if (bankName == null || bankName.trim().equals("KOTAK BANK")) {
            bank.setId(1L);
            return bank;
        }

        if (bankName.trim().equals("HDFC BANK")) {
            bank.setId(2L);
            return bank;
        }

        bank.setId(1L);

        return bank;

    }

    @Override
    public Page<Settlement> getSettlements(String search, Integer size, Integer page) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,
                "paymentDate"));

        Specification<Settlement> specification = new SearchSpecificationForEmployee<Settlement>()
                .getSearchSpecificationForEmployee(search);

        return settlementRepository.findAll(specification, pageable);
    }

    @Override
    public EssentialsDTO getEssentials() {

        List<SettlementType> settlementTypes = settlementTypeRepository.findAllByIsActive(true);

        List<TypeDTO> typeDTOList = settlementTypes.stream()
                .map(type -> new TypeDTO(type.getEntityId(), type.getTitle()))
                .collect(Collectors.toList());

        List<StatusDTO> statusDTOList = settlementStatusRepository.findAllByIsActive(true).stream()
                .map(status -> new StatusDTO(status.getEntityId(), status.getTitle()))
                .collect(Collectors.toList());

        List<UnitCode> unitCodes = unitCodeService.get();

        List<Integer> years = financialYearAndMonthService.getYears();

        Map<Integer, String> months = financialYearAndMonthService.getMonths();

        return new EssentialsDTO(typeDTOList, statusDTOList, unitCodes, years, months);

    }

    @Override
    public List<Settlement> getSettlements(Employee employee) {
        return settlementRepository.findAll(getSpecification(employee));
    }

    @Override
    public List<SettlementDetailsDTO> getSettlementDetails(Employee employee) {
        return getSettlements(employee).stream()
                .map(settlement -> {

                    SettlementType settlementType = settlement.getSettlementType();
                    SettlementStatus settlementStatus = settlement.getSettlementStatus();
                    SettlementFinalDetails settlementFinalDetails = settlement.getSettlementFinalDetails();

                    return new SettlementDetailsDTO(settlement.getEntityId(),
                            settlement.getPayeeName(), settlement.getDateOfLeavingService(),
                            settlement.getDateOfSettlement(), settlement.getAltEmailId(),
                            settlement.getAltContactNumber(), settlementType.getTitle(), settlementStatus.getTitle(),
                            settlement.getPaymentDate(), settlementFinalDetails.getTotalContribution(),
                            settlementFinalDetails.getNetCreditAmount(), settlement.getDocumentNumber(),
                            true);

                }).collect(Collectors.toList());
    }

    @Override
    public Settlement getSettlement(String entityId) {

        Optional<Settlement> optionalSettlement = settlementRepository
                .findByEntityIdAndIsActive(entityId, true);

        if(optionalSettlement.isEmpty()) throw new EntityNotFoundException("settlement not found");

        return optionalSettlement.get();

    }

    @Override
    public Optional<Settlement> getCompletedSettlement(Employee employee) {

        Optional<SettlementStatus> optionalSettlementStatus = settlementStatusRepository.findById(2L);

        SettlementStatus settlementStatus = optionalSettlementStatus.get();

        return settlementRepository.getSettlementByEmployeeAndSettlementStatusAndIsActive(employee, settlementStatus,
                true);

    }

    private Specification<Settlement> getSpecification(Employee employee) {
        SearchSettlementSpecification settlementSpecification = new SearchSettlementSpecification();
        return settlementSpecification.employeeSpecification(employee);
    }


    @Override
    public Settlement savePaymentDetails(Settlement settlement, Date paymentDate, String referenceNumber, Bank bank,
                                         PaymentMode paymentMode) throws JPAException {
    	
    	Employee employee1 = settlement.getEmployee();
        
        if(employee1.isSettled() || employee1.isMerged()){
            logger.info("Employee is already {}", employee1.getContributionStatus().getDescription());
            throw new JPAException("Employee -> " + employee1.getPernNumber() + " is already " + employee1.getContributionStatus().getDescription());
        }

        settlement.setPaymentDate(paymentDate);
        settlement.setChequeNo(referenceNumber);
        settlement.setBank(bank);
        settlement.setPaymentMode(paymentMode);

        SettlementStatus completed = settlementStatusRepository.findByTitleAndIsActive("Completed", true);

        settlement.setSettlementStatus(completed);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(2L);

        settlement.setSapStatus(sapStatus);

        Settlement save = settlementRepository.save(settlement);

        Employee employee = save.getEmployee();

        ContributionStatus settled = contributionStatusRepository.findBySymbolAndIsActive("S", true);

        employee.setContributionStatus(settled);

        employeeRepository.save(employee);

        return save;

    }

    @Override
    public SettlementEmailStatus createEmailStatus(Settlement settlement) {

        String emailId = settlement.getAltEmailId();

        if(emailId == null){
            emailId = settlement.getEmployee().getEmail();
        }

        SettlementEmailStatus settlementEmailStatus = new SettlementEmailStatus(emailId, settlement.getEmployee().getName(),
                settlement.getEmployee().getUnitCode(), settlement.getEmployee().getPfNumber(),
                settlement.getEmployee().getPernNumber(), settlement.getAccountNumber(),
                settlement.getBankName(), settlement.getPaymentDate(),
                settlement.getSettlementFinalDetails().getNetCreditAmount(),
                settlement, false);

        return settlementEmailStatusRepository.save(settlementEmailStatus);
    }

    @Override
    public void updateEmailStatus(SettlementEmailStatus settlementEmailStatus) {
        settlementEmailStatus.setSent(true);
        settlementEmailStatusRepository.save(settlementEmailStatus);
    }

    @Override
    public void sendConfirmationEmail(List<Pair<SettlementEmailStatus, List<EmailAttachment>>> settlementEmailStatusList) {
        for (Pair<SettlementEmailStatus, List<EmailAttachment>> emailStatusPair:settlementEmailStatusList) {
            sendConfirmationEmail(emailStatusPair.getFirst(), emailStatusPair.getSecond());
        }
    }

    @Override
    public void sendConfirmationEmail(SettlementEmailStatus settlementEmailStatus, List<EmailAttachment> emailAttachments) {
        sendConfirmationEmail(settlementEmailStatus.getEmailId(), settlementEmailStatus.getName(),
                settlementEmailStatus.getAccountNumber(), settlementEmailStatus.getEmployeeBank(),
                settlementEmailStatus.getPaymentDate(), settlementEmailStatus.getNetCredit(), emailAttachments);
    }

    @Override
    public void sendConfirmationEmail(String emailId, String employeeName, String accountNumber, String bankName,
                                      Date paymentDate, BigDecimal amount, List<EmailAttachment> emailAttachments) {

        String subject = " Subject: PF Notifications";

        Optional<NotificationEmailDesign> optional = notificationEmailDesignRepository
                .findByDocumentType(NotificationEmailDesign.SETTLEMENT_COMPLETION);

        if(optional.isEmpty()) throw new EntityNotFoundException("Email Design Not Found");

        NotificationEmailDesign notificationEmailDesign = optional.get();

        String emailBody = notificationEmailDesign.getDocument().replace("{{name}}", employeeName)
                .replace("{{accountNumber}}", accountNumber)
                .replace("{{bankName}}", bankName)
                .replace("{{amount}}", NumberFormatter.formatNumbers(amount))
                .replace("{{paymentDate}}", DateFormatterUtil.formatDateR(paymentDate));

        emailService.sendMessageWithAttachment(emailId, subject, emailBody, emailAttachments);


    }

    @Override
    public void reject(String entityId) {

        Settlement settlement = getSettlement(entityId);

        SettlementStatus transferOutStatus = settlement.getSettlementStatus();

        if(transferOutStatus.getTitle().equalsIgnoreCase("Completed") ||
                transferOutStatus.getTitle().equalsIgnoreCase("Canceled")) {
            throw new EntityExistsException("Settlement Application is already " + transferOutStatus.getTitle());
        }

        SettlementStatus canceled = settlementStatusRepository.findByTitleAndIsActive("Canceled", true);

        settlement.setSettlementStatus(canceled);

        settlementRepository.save(settlement);

    }

    @Override
    public List<SettlementEmailStatus> getEmailStatusListNotSent() {
        return settlementEmailStatusRepository.findAllByIsSentAndIsActive(false, true);
    }

    @Override
    public List<SettlementEmailStatus> getSettlementEmailStatusList(List<String> entityIds) {
        return settlementEmailStatusRepository.findAllByIsActiveAndEntityIdIn(true, new HashSet<>(entityIds));
    }

    @Override
    public List<Document> getDocumentsForSettlementType(String entityId) {

        Optional<SettlementType> optionalSettlementType = settlementTypeRepository
                .findByEntityIdAndIsActive(entityId, true);

        if(optionalSettlementType.isEmpty()) throw new EntityNotFoundException("Settlement Type Not Found.");

        SettlementType settlementType = optionalSettlementType.get();

        return settlementType.getDocuments();

    }

    @Override
    public SettlementType getSettlementType(String entityId) {

        Optional<SettlementType> optionalSettlementType = settlementTypeRepository.findByEntityIdAndIsActive(entityId, true);

        if(optionalSettlementType.isEmpty()) throw new EntityNotFoundException("Settlement Type Not Found");

        return optionalSettlementType.get();
    }

    @Override
    public Settlement create(Settlement settlement, Employee employee, Bank bank) throws JPAException {

        if(employee.isSettled() || employee.isMerged()){
            logger.info("Employee is already {}", employee.getContributionStatus().getDescription());
            throw new JPAException("Employee -> " + employee.getPernNumber() + " is already " + employee.getContributionStatus().getDescription());
        }

        int settlementYear = FinancialYearAndMonth.getFinancialYear(settlement.getDateOfSettlement());

        try {
            Contribution openingBalance = contributionService.getContribution(employee, settlementYear, 0);
        }catch (EntityNotFoundException exception){
            throw new JPAException("Please perform year end process for the employee before proceeding.");
        }

        InterestRate interestRate = interestRateService.getActive();

        List<Contribution> contributions = contributionService.getContributions(employee, settlementYear);

        List<Loan> loans = loanService.getCompletedLoans(employee, settlementYear);

        List<TransferIn> transferIns = transferInService.getCompletedTransferIns(employee, settlementYear);

        List<Contribution> interestCalculatedContributions = interestProviderService
                .performForContributions(employee, settlementYear, contributions, interestRate,
                        settlement.getDateOfSettlement());

        List<Loan> interestCalculatedLoans = interestProviderService
                .performForLoans(settlementYear, loans, interestRate, settlement.getDateOfSettlement());

        List<TransferIn> interestCalculatedTransferIns = interestProviderService
                .performForTransferIns(settlementYear, transferIns, interestRate, settlement.getDateOfSettlement());

        SettlementFinalDetails settlementFinalDetails = getSettlementFinalDetails(interestCalculatedContributions,
                interestCalculatedTransferIns,
                interestCalculatedLoans);

        BigDecimal eduCess = settlement.getEduCess();
        BigDecimal incomeTax = settlement.getIncomeTax();

        settlementFinalDetails.setEduCess(settlement.getEduCess());
        settlementFinalDetails.setIncomeTax(settlement.getIncomeTax());

        BigDecimal netAmountToCredit = settlementFinalDetails.getTotalContribution()
                .subtract(settlementFinalDetails.getTotalLoan())
                .subtract(eduCess).subtract(incomeTax);

        settlementFinalDetails.setNetCreditAmount(netAmountToCredit);

        settlement.setInterestRate(interestRate.getInterestRate().floatValue());
        settlement.setEmployee(employee);
        settlement.setBank(bank);

        SettlementStatus settlementStatus = settlementStatusRepository.findByTitleAndIsActive(SettlementStatus.IN_PROCESS, true);

        settlement.setSettlementStatus(settlementStatus);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(1L);

        settlement.setSapStatus(sapStatus);

        Settlement save = settlementRepository.save(settlement);

        settlementFinalDetails.setSettlement(save);

        settlementFinalDetailsRepository.save(settlementFinalDetails);

        for (Contribution contribution:interestCalculatedContributions) {
            contributionService.save(contribution);
        }

        for (Loan loan:interestCalculatedLoans) {
            loanService.save(loan);
        }

        for (TransferIn transferIn:interestCalculatedTransferIns) {
            transferInService.save(transferIn);
        }

        return save;
    }

    @Override
    public Settlement update(Settlement settlement, Employee employee) throws JPAException {

        if(employee.isSettled() || employee.isMerged()){
            logger.info("Employee is already {}", employee.getContributionStatus().getDescription());
            throw new JPAException("Employee -> " + employee.getPernNumber() + " is already " + employee.getContributionStatus().getDescription());
        }

        int settlementYear = FinancialYearAndMonth.getFinancialYear(settlement.getDateOfSettlement());

        try {
            Contribution openingBalance = contributionService.getContribution(employee, settlementYear, 0);
        }catch (EntityNotFoundException exception){
            throw new JPAException("Please perform year end process for the employee before proceeding.");
        }

        InterestRate interestRate = interestRateService.getActive();

        List<Contribution> contributions = contributionService.getContributions(employee, settlementYear);

        List<Loan> loans = loanService.getCompletedLoans(employee, settlementYear);

        List<TransferIn> transferIns = transferInService.getCompletedTransferIns(employee, settlementYear);

        List<Contribution> interestCalculatedContributions = interestProviderService
                .performForContributions(employee, settlementYear, contributions, interestRate,
                        settlement.getDateOfSettlement());

        List<Loan> interestCalculatedLoans = interestProviderService
                .performForLoans(settlementYear, loans, interestRate, settlement.getDateOfSettlement());

        List<TransferIn> interestCalculatedTransferIns = interestProviderService
                .performForTransferIns(settlementYear, transferIns, interestRate, settlement.getDateOfSettlement());

        SettlementFinalDetails settlementFinalDetails = getSettlementFinalDetails(
                settlement.getSettlementFinalDetails(),
                interestCalculatedContributions,
                interestCalculatedTransferIns,
                interestCalculatedLoans);

        BigDecimal eduCess = settlement.getEduCess();
        BigDecimal incomeTax = settlement.getIncomeTax();

        settlementFinalDetails.setEduCess(settlement.getEduCess());
        settlementFinalDetails.setIncomeTax(settlement.getIncomeTax());

        BigDecimal netAmountToCredit = settlementFinalDetails.getTotalContribution()
                .subtract(settlementFinalDetails.getTotalLoan())
                .subtract(eduCess).subtract(incomeTax);

        settlementFinalDetails.setNetCreditAmount(netAmountToCredit);

        settlement.setInterestRate(interestRate.getInterestRate().floatValue());
        settlement.setEmployee(employee);

        SettlementStatus settlementStatus = settlementStatusRepository.findByTitleAndIsActive(SettlementStatus.IN_PROCESS, true);

        settlement.setSettlementStatus(settlementStatus);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(1L);

        settlement.setSapStatus(sapStatus);

        Settlement save = settlementRepository.save(settlement);

        settlementFinalDetails.setSettlement(save);

        settlementFinalDetailsRepository.save(settlementFinalDetails);

        for (Contribution contribution:interestCalculatedContributions) {
            contributionService.save(contribution);
        }

        for (Loan loan:interestCalculatedLoans) {
            loanService.save(loan);
        }

        for (TransferIn transferIn:interestCalculatedTransferIns) {
            transferInService.save(transferIn);
        }

        return save;

    }

    private SettlementFinalDetails getSettlementFinalDetails(List<Contribution> interestCalculatedContributions,
                                                             List<TransferIn> interestCalculatedTransferIns,
                                                             List<Loan> interestCalculatedLoans){

        SettlementFinalDetails settlementFinalDetails = new SettlementFinalDetails();

        return getSettlementFinalDetails(settlementFinalDetails,
                interestCalculatedContributions, interestCalculatedTransferIns,
                interestCalculatedLoans);

    }


    private SettlementFinalDetails getSettlementFinalDetails(SettlementFinalDetails settlementFinalDetails,
                                                             List<Contribution> interestCalculatedContributions,
                                                             List<TransferIn> interestCalculatedTransferIns,
                                                             List<Loan> interestCalculatedLoans){

        Contribution yearOpeningContribution = interestCalculatedContributions.stream()
                .filter(contribution -> contribution.getSubType() == 0)
                .collect(Collectors.toList()).get(0);

        List<Contribution> currentYearContributions = interestCalculatedContributions.stream()
                .filter(contribution -> contribution.getSubType() != 0).collect(Collectors.toList());

        addYearOpeningContribution(yearOpeningContribution, settlementFinalDetails);

        addCurrentYearContributions(currentYearContributions, settlementFinalDetails);

        addCurrentYearTransferIn(interestCalculatedTransferIns, settlementFinalDetails);

        addCurrentYearLoans(interestCalculatedLoans, settlementFinalDetails);

        BigDecimal totalMemberContribution = settlementFinalDetails.getMemberContributionYearOpening()
                .add(settlementFinalDetails.getMemberContributionCurrentYear())
                .add(settlementFinalDetails.getMemberContributionCurrentYearTi())
                .add(settlementFinalDetails.getInterestOnMemberContributionYearOpening())
                .add(settlementFinalDetails.getInterestOnMemberContributionCurrentYear())
                .add(settlementFinalDetails.getInterestOnMemberContributionCurrentYearTi());

        BigDecimal totalCompanyContribution = settlementFinalDetails.getCompanyContributionYearOpening()
                .add(settlementFinalDetails.getCompanyContributionCurrentYear())
                .add(settlementFinalDetails.getCompanyContributionCurrentYearTi())
                .add(settlementFinalDetails.getInterestOnCompanyContributionYearOpening())
                .add(settlementFinalDetails.getInterestOnCompanyContributionCurrentYear())
                .add(settlementFinalDetails.getInterestOnCompanyContributionCurrentYearTi());

        BigDecimal totalVpfContribution = settlementFinalDetails.getVpfContributionYearOpening()
                .add(settlementFinalDetails.getVpfContributionCurrentYear())
                .add(settlementFinalDetails.getVpfContributionCurrentYearTi())
                .add(settlementFinalDetails.getInterestOnVpfContributionYearOpening())
                .add(settlementFinalDetails.getInterestOnVpfContributionCurrentYear())
                .add(settlementFinalDetails.getInterestOnVpfContributionCurrentYearTi());

        BigDecimal totalContribution = totalMemberContribution.add(totalCompanyContribution).add(totalVpfContribution);

        BigDecimal totalLoans = settlementFinalDetails.getMemberLoanWithDrawal()
                .add(settlementFinalDetails.getCompanyLoanWithDrawal())
                .add(settlementFinalDetails.getVpfLoanWithDrawal())
                .add(settlementFinalDetails.getInterestOnMemberLoanWithDrawal())
                .add(settlementFinalDetails.getInterestOnCompanyLoanWithDrawal())
                .add(settlementFinalDetails.getInterestOnVpfLoanWithDrawal());

        settlementFinalDetails.setTotalMemberContribution(totalMemberContribution);
        settlementFinalDetails.setTotalCompanyContribution(totalCompanyContribution);
        settlementFinalDetails.setTotalVpfContribution(totalVpfContribution);

        settlementFinalDetails.setTotalContribution(totalContribution);

        settlementFinalDetails.setTotalLoan(totalLoans);

        return settlementFinalDetails;

    }

    private void addYearOpeningContribution(Contribution interestCalculatedContribution, SettlementFinalDetails settlementFinalDetails){

        settlementFinalDetails.setMemberContributionYearOpening(interestCalculatedContribution.getMemberContribution());
        settlementFinalDetails.setCompanyContributionYearOpening(interestCalculatedContribution.getCompanyContribution());
        settlementFinalDetails.setVpfContributionYearOpening(interestCalculatedContribution.getVpfContribution());

        settlementFinalDetails.setInterestOnMemberContributionYearOpening(interestCalculatedContribution.getInterestMemContribution());
        settlementFinalDetails.setInterestOnCompanyContributionYearOpening(interestCalculatedContribution.getInterestCompanyContribution());
        settlementFinalDetails.setInterestOnVpfContributionYearOpening(interestCalculatedContribution.getVpfContributionInterest());

        BigDecimal totalYearOpeningContribution = BigDecimal.ZERO
                .add(avoidNull(interestCalculatedContribution.getMemberContribution()))
                .add(avoidNull(interestCalculatedContribution.getCompanyContribution()))
                .add(avoidNull(interestCalculatedContribution.getVpfContribution()));

        BigDecimal totalInterestOnYearOpeningContribution = BigDecimal.ZERO
                .add(avoidNull(interestCalculatedContribution.getInterestMemContribution()))
                .add(avoidNull(interestCalculatedContribution.getInterestCompanyContribution()))
                .add(avoidNull(interestCalculatedContribution.getVpfContributionInterest()));

        settlementFinalDetails.setTotalContributionYearOpening(totalYearOpeningContribution);
        settlementFinalDetails.setTotalInterestYearOpening(totalInterestOnYearOpeningContribution);

    }

    private void addCurrentYearContributions(List<Contribution> interestCalculatedContributions,
                                          SettlementFinalDetails settlementFinalDetails){

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal interestOnMemberContribution = BigDecimal.ZERO;
        BigDecimal interestOnCompanyContribution = BigDecimal.ZERO;
        BigDecimal interestOnVpfContribution = BigDecimal.ZERO;


        for (Contribution contribution:interestCalculatedContributions) {

            memberContribution = memberContribution.add(avoidNull(contribution.getMemberContribution()));
            companyContribution = companyContribution.add(avoidNull(contribution.getCompanyContribution()));
            vpfContribution = vpfContribution.add(avoidNull(contribution.getVpfContribution()));

            interestOnMemberContribution = interestOnMemberContribution.add(avoidNull(contribution.getInterestMemContribution()));
            interestOnCompanyContribution = interestOnCompanyContribution.add(avoidNull(contribution.getInterestCompanyContribution()));
            interestOnVpfContribution = interestOnVpfContribution.add(avoidNull(contribution.getVpfContributionInterest()));

        }

        settlementFinalDetails.setMemberContributionCurrentYear(memberContribution);
        settlementFinalDetails.setCompanyContributionCurrentYear(companyContribution);
        settlementFinalDetails.setVpfContributionCurrentYear(vpfContribution);

        settlementFinalDetails.setInterestOnMemberContributionCurrentYear(interestOnMemberContribution);
        settlementFinalDetails.setInterestOnCompanyContributionCurrentYear(interestOnCompanyContribution);
        settlementFinalDetails.setInterestOnVpfContributionCurrentYear(interestOnVpfContribution);

        BigDecimal totalCurrentYearContribution = memberContribution.add(companyContribution).add(vpfContribution);
        BigDecimal totalInterestOnCurrentYearContribution = interestOnMemberContribution
                .add(interestOnCompanyContribution).add(interestOnVpfContribution);

        settlementFinalDetails.setTotalContributionCurrentYear(totalCurrentYearContribution);
        settlementFinalDetails.setTotalInterestCurrentYear(totalInterestOnCurrentYearContribution);

    }


    private void addCurrentYearTransferIn(List<TransferIn> interestCalculatedTransferIns,
                                             SettlementFinalDetails settlementFinalDetails){

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal interestOnMemberContribution = BigDecimal.ZERO;
        BigDecimal interestOnCompanyContribution = BigDecimal.ZERO;
        BigDecimal interestOnVpfContribution = BigDecimal.ZERO;


        for (TransferIn transferIn:interestCalculatedTransferIns) {

            TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

            memberContribution = memberContribution.add(avoidNull(transferInFinalDetails.getMemberContribution()));
            companyContribution = companyContribution.add(avoidNull(transferInFinalDetails.getCompanyContribution()));
            vpfContribution = vpfContribution.add(avoidNull(transferInFinalDetails.getVpfContribution()));

            interestOnMemberContribution = interestOnMemberContribution.add(avoidNull(transferInFinalDetails.getInterestOnMemberContribution()));
            interestOnCompanyContribution = interestOnCompanyContribution.add(avoidNull(transferInFinalDetails.getInterestOnCompanyContribution()));
            interestOnVpfContribution = interestOnVpfContribution.add(avoidNull(transferInFinalDetails.getInterestOnVpfContribution()));

        }

        settlementFinalDetails.setMemberContributionCurrentYearTi(memberContribution);
        settlementFinalDetails.setCompanyContributionCurrentYearTi(companyContribution);
        settlementFinalDetails.setVpfContributionCurrentYearTi(vpfContribution);

        settlementFinalDetails.setInterestOnMemberContributionCurrentYearTi(interestOnMemberContribution);
        settlementFinalDetails.setInterestOnCompanyContributionCurrentYearTi(interestOnCompanyContribution);
        settlementFinalDetails.setInterestOnVpfContributionCurrentYearTi(interestOnVpfContribution);

        BigDecimal totalCurrentYearTransferInContribution = memberContribution.add(companyContribution).add(vpfContribution);
        BigDecimal totalInterestOnCurrentYearTransferInContribution = interestOnMemberContribution
                .add(interestOnCompanyContribution).add(interestOnVpfContribution);

        settlementFinalDetails.setTotalContributionCurrentYearTi(totalCurrentYearTransferInContribution);
        settlementFinalDetails.setTotalInterestCurrentYearTi(totalInterestOnCurrentYearTransferInContribution);

    }


    private void addCurrentYearLoans(List<Loan> interestCalculatedLoans,
                                          SettlementFinalDetails settlementFinalDetails){

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal interestOnMemberContribution = BigDecimal.ZERO;
        BigDecimal interestOnCompanyContribution = BigDecimal.ZERO;
        BigDecimal interestOnVpfContribution = BigDecimal.ZERO;


        for (Loan loan:interestCalculatedLoans) {

            LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

            memberContribution = memberContribution.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnMemContribution()));
            companyContribution = companyContribution.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnCompanyContribution()));
            vpfContribution = vpfContribution.add(avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnVPFContribution()));

            interestOnMemberContribution = interestOnMemberContribution.add(avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnMemContribution()));
            interestOnCompanyContribution = interestOnCompanyContribution.add(avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnCompanyContribution()));
            interestOnVpfContribution = interestOnVpfContribution.add(avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnVPFContribution()));

        }

        settlementFinalDetails.setMemberLoanWithDrawal(memberContribution);
        settlementFinalDetails.setCompanyLoanWithDrawal(companyContribution);
        settlementFinalDetails.setVpfLoanWithDrawal(vpfContribution);

        settlementFinalDetails.setInterestOnMemberLoanWithDrawal(interestOnMemberContribution);
        settlementFinalDetails.setInterestOnCompanyLoanWithDrawal(interestOnCompanyContribution);
        settlementFinalDetails.setInterestOnVpfLoanWithDrawal(interestOnVpfContribution);

        BigDecimal totalCurrentYearLoans = memberContribution.add(companyContribution).add(vpfContribution);
        BigDecimal totalInterestOnCurrentYearLoans = interestOnMemberContribution.add(interestOnCompanyContribution)
                .add(interestOnVpfContribution);

        settlementFinalDetails.setTotalLoanWithDrawal(totalCurrentYearLoans);
        settlementFinalDetails.setTotalInterestLoanWithDrawal(totalInterestOnCurrentYearLoans);

    }


    @Override
    public List<SettlementDocument> getSettlementDocuments(JSONArray documents) {

        List<SettlementDocument> settlementDocumentList = new ArrayList<>();

        for (int i=0; i< documents.length(); i++){

            JSONObject jsonObject = documents.getJSONObject(i);

            Optional<Document> optionalDocument = documentRepository
                    .findByEntityIdAndIsActive(jsonObject.getString("entityId"), true);

            if(optionalDocument.isEmpty()) throw new EntityNotFoundException("Document Not Found.");

            Document document = optionalDocument.get();

            SettlementDocument settlementDocument = new SettlementDocument();

            settlementDocument.setDocument(document);
            settlementDocument.setPath(jsonObject.getString("path"));

            settlementDocumentList.add(settlementDocument);

        }

        return settlementDocumentList;

    }


    @Override
    public void saveSettlementDocuments(List<SettlementDocument> settlementDocuments, Settlement settlement) {

        List<SettlementDocument> mappedDocuments = settlementDocuments.stream()
                .peek(settlementDocument -> settlementDocument.setSettlement(settlement))
                .collect(Collectors.toList());

        settlementDocumentRepository.saveAll(mappedDocuments);

    }

    @Override
    public void updateSettlementDocuments(JSONArray jsonArray) {

        for (int i=0; i< jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Optional<SettlementDocument> optionalSettlementDocument = settlementDocumentRepository
                    .findByEntityIdAndIsActive(jsonObject.getString("entityId"), true);

            if(optionalSettlementDocument.isPresent()){

                SettlementDocument settlementDocument = optionalSettlementDocument.get();

                settlementDocument.setPath(jsonObject.getString("path"));

                settlementDocumentRepository.save(settlementDocument);
            }

        }

    }

    @Override
    public List<SettlementType> getSettlementTypes() {
        return settlementTypeRepository.findAllByIsActive(true);
    }

    @Override
    public Worksheet getWorkSheet(Settlement settlement) throws ParseException {

        Worksheet worksheet = new Worksheet();

        int financialYear = FinancialYearAndMonth.getFinancialYear(settlement.getDateOfSettlement());

        String stlNo = 1 + "" + financialYear + "" + settlement.getId();

        worksheet.setSettlementNo(stlNo);
        worksheet.setDateOfCessation(settlement.getDateOfLeavingService());
        worksheet.setDocumentNo(settlement.getDocumentNumber());
        worksheet.setName(settlement.getEmployee().getName());

        worksheet.setTokenNumber(settlement.getEmployee().getTokenNumber());
        worksheet.setPfNumber(settlement.getEmployee().getPfNumber());
        worksheet.setUnitCode(settlement.getEmployee().getUnitCode());
        worksheet.setDeptCode("");

        worksheet.setStatus(settlement.getEmployee().getContributionStatus().getDescription());
        worksheet.setDateOfBirth(settlement.getEmployee().getDateOfBirth());
        worksheet.setDateOfJoining(settlement.getEmployee().getDateOfJoiningPF());

        List<PreviousCompany> previousCompanyList = settlement.getEmployee().getPreviousCompanies();

        if (previousCompanyList != null && previousCompanyList.size() > 0) {
            worksheet.setDateOfJoiningPrior(previousCompanyList.get(0).getDateOfJoining());
        }

        java.util.Date date = contributionService.getLastRecoveryDate(settlement.getEmployee());

        worksheet.setLastRecoverDate(date);

        worksheet.setYearOpeningDate(FinancialYearAndMonth.getYearOpeningFromDate(settlement.getDateOfSettlement()));

        worksheet.setInterestRate(settlement.getInterestRate());


        worksheet.setYearOpeningMemberContribution(
                settlement.getSettlementFinalDetails().getMemberContributionYearOpening());
        worksheet.setYearOpeningCompanyContribution(
                settlement.getSettlementFinalDetails().getCompanyContributionYearOpening());
        worksheet.setYearOpeningVpfContribution(settlement.getSettlementFinalDetails().getVpfContributionYearOpening());
        worksheet.setYearOpeningTotalContribution(
                settlement.getSettlementFinalDetails().getTotalContributionYearOpening());

        worksheet.setInterestOnYearOpeningMemberContribution(
                settlement.getSettlementFinalDetails().getInterestOnMemberContributionYearOpening());
        worksheet.setInterestOnYearOpeningCompanyContribution(
                settlement.getSettlementFinalDetails().getInterestOnCompanyContributionYearOpening());
        worksheet.setInterestOnYearOpeningVpfContribution(
                settlement.getSettlementFinalDetails().getInterestOnVpfContributionYearOpening());
        worksheet.setInterestOnYearOpeningTotalContribution(
                settlement.getSettlementFinalDetails().getTotalInterestYearOpening());

        worksheet.setCurrentYearMemberContribution(
                settlement.getSettlementFinalDetails().getMemberContributionCurrentYear());
        worksheet.setCurrentYearCompanyContribution(
                settlement.getSettlementFinalDetails().getCompanyContributionCurrentYear());
        worksheet.setCurrentYearVpfContribution(settlement.getSettlementFinalDetails().getVpfContributionCurrentYear());
        worksheet.setCurrentYearTotalContribution(
                settlement.getSettlementFinalDetails().getTotalContributionCurrentYear());

        worksheet.setInterestOnCurrentYearMemberContribution(
                settlement.getSettlementFinalDetails().getInterestOnMemberContributionCurrentYear());
        worksheet.setInterestOnCurrentYearCompanyContribution(
                settlement.getSettlementFinalDetails().getInterestOnCompanyContributionCurrentYear());
        worksheet.setInterestOnCurrentYearVpfContribution(
                settlement.getSettlementFinalDetails().getInterestOnVpfContributionCurrentYear());
        worksheet.setInterestOnCurrentYearTotalContribution(
                settlement.getSettlementFinalDetails().getTotalInterestCurrentYear());

        worksheet.setCurrentYearTransferInMemberContribution(
                settlement.getSettlementFinalDetails().getMemberContributionCurrentYearTi());
        worksheet.setCurrentYearTransferInCompanyContribution(
                settlement.getSettlementFinalDetails().getCompanyContributionCurrentYearTi());
        worksheet.setCurrentYearTransferInVpfContribution(
                settlement.getSettlementFinalDetails().getVpfContributionCurrentYearTi());
        worksheet.setCurrentYearTransferInTotalContribution(
                settlement.getSettlementFinalDetails().getTotalContributionCurrentYearTi());

        worksheet.setInterestOnCurrentYearTransferInMemberContribution(
                settlement.getSettlementFinalDetails().getInterestOnMemberContributionCurrentYearTi());
        worksheet.setInterestOnCurrentYearTransferInCompanyContribution(
                settlement.getSettlementFinalDetails().getInterestOnCompanyContributionCurrentYearTi());
        worksheet.setInterestOnCurrentYearTransferInVpfContribution(
                settlement.getSettlementFinalDetails().getInterestOnVpfContributionCurrentYearTi());
        worksheet.setInterestOnCurrentYearTransferInTotalContribution(
                settlement.getSettlementFinalDetails().getTotalInterestCurrentYearTi());

        worksheet.setCurrentYearLoanWithdrawalMemberContribution(
                settlement.getSettlementFinalDetails().getMemberLoanWithDrawal());
        worksheet.setCurrentYearLoanWithdrawalCompanyContribution(
                settlement.getSettlementFinalDetails().getCompanyLoanWithDrawal());
        worksheet.setCurrentYearLoanWithdrawalVpfContribution(
                settlement.getSettlementFinalDetails().getVpfLoanWithDrawal());
        worksheet.setCurrentYearLoanWithdrawalTotalContribution(
                settlement.getSettlementFinalDetails().getTotalLoanWithDrawal());

        worksheet.setInterestOnCurrentYearLoanWithdrawalMemberContribution(
                settlement.getSettlementFinalDetails().getInterestOnMemberLoanWithDrawal());
        worksheet.setInterestOnCurrentYearLoanWithdrawalCompanyContribution(
                settlement.getSettlementFinalDetails().getInterestOnCompanyLoanWithDrawal());
        worksheet.setInterestOnCurrentYearLoanWithdrawalVpfContribution(
                settlement.getSettlementFinalDetails().getInterestOnVpfLoanWithDrawal());
        worksheet.setInterestOnCurrentYearLoanWithdrawalTotalContribution(
                settlement.getSettlementFinalDetails().getTotalInterestLoanWithDrawal());

        worksheet.setTotalMemberContribution(settlement.getSettlementFinalDetails().getTotalMemberContribution());
        worksheet.setTotalCompanyContribution(settlement.getSettlementFinalDetails().getTotalCompanyContribution());
        worksheet.setTotalVpfContribution(settlement.getSettlementFinalDetails().getTotalVpfContribution());

        worksheet.setTotalContribution(settlement.getSettlementFinalDetails().getTotalContribution());

        BigDecimal totalContribution = settlement.getSettlementFinalDetails().getTotalContribution();

        BigDecimal incomeTax = settlement.getIncomeTax();
        BigDecimal eduCess = settlement.getEduCess();

        worksheet.setIncomeTax(incomeTax);
        worksheet.setEduCess(eduCess);
        worksheet.setTds(incomeTax.add(eduCess));

        worksheet.setNetContribution(totalContribution.subtract(incomeTax).subtract(eduCess));

        worksheet.setDueDate(settlement.getDateOfSettlement());

        worksheet.setProcessedOnDate(settlement.getCreatedAt());

        List<Nominee> nominees = new ArrayList<>();

        for (com.coreintegra.pftrust.entities.pf.employee.Nominee n : settlement.getEmployee().getNominees()) {

            Nominee nominee = new Nominee();

            nominee.setName(n.getName());
            nominee.setRelationship(n.getRelationship());
            nominee.setShare(n.getShare());

            nominees.add(nominee);
        }

        worksheet.setNominees(nominees);

        worksheet.setSettlementAnnexure(getSettlementAnnexure(settlement));

        return worksheet;

    }

    @Override
    public SettlementAnnexure getSettlementAnnexure(Settlement settlement) {

        Employee employee = settlement.getEmployee();

        List<Contribution> contributions = contributionService
                .getContributions(employee, FinancialYearAndMonth.getFinancialYear(settlement.getDateOfSettlement()));

        List<Contribution> contributedDuringYear = contributions.stream()
                .filter(contribution -> contribution.getSubType() > 0).collect(Collectors.toList());

        List<CalculatedAmount> monthWiseMemberContributions = contributionService.getMonthWiseMemberContributions(contributedDuringYear);

        List<CalculatedAmount> monthWiseCompanyContributions = contributionService.getMonthWiseCompanyContributions(contributedDuringYear);

        List<CalculatedAmount> monthWiseVpfContributions = contributionService.getMonthWiseVpfContributions(contributedDuringYear);

        List<CalculatedAmount> monthWiseInterestOnMemberContributions = contributionService.getMonthWiseInterestOnMemberContributions(contributedDuringYear);

        List<CalculatedAmount> monthWiseInterestOnCompanyContributions = contributionService.getMonthWiseInterestOnCompanyContributions(contributedDuringYear);

        List<CalculatedAmount> monthWiseInterestOnVpfContributions = contributionService.getMonthWiseInterestOnVpfContributions(contributedDuringYear);

        List<CalculatedAmount> monthWiseTotalContribution = contributionService.getMonthWiseTotalContribution(contributedDuringYear);

        List<CalculatedAmount> monthWiseTotalInterest = contributionService.getMonthWiseTotalInterest(contributedDuringYear);

        SettlementAnnexure settlementAnnexure = new SettlementAnnexure(employee.getName(), employee.getTokenNumber(),
                employee.getPfNumber(), employee.getUnitCode(), employee.getUnitCode(), settlement.getInterestRate(),
                monthWiseMemberContributions, monthWiseCompanyContributions, monthWiseVpfContributions,
                monthWiseInterestOnMemberContributions, monthWiseInterestOnCompanyContributions,
                monthWiseInterestOnVpfContributions, monthWiseTotalContribution, monthWiseTotalInterest);

        settlementAnnexure.setDateOfSettlement(settlement.getDateOfSettlement());

        return settlementAnnexure;

    }

    @Override
    public SettlementDispatchLetter getDispatchLetter(Settlement settlement) {
        SettlementDispatchLetter settlementDispatchLetter = new SettlementDispatchLetter();

        int financialYear = FinancialYearAndMonth.getFinancialYear(settlement.getDateOfSettlement());

        String stlNo = 1 + "" + financialYear + "" + settlement.getId();

        settlementDispatchLetter.setRefNo(stlNo);

        settlementDispatchLetter.setStlNo(stlNo);
        settlementDispatchLetter.setName(settlement.getEmployee().getName());
        settlementDispatchLetter.setAddressLine1(settlement.getAddressLine1());
        settlementDispatchLetter.setAddressLine2(settlement.getAddressLine2());
        settlementDispatchLetter.setAddressLine3(settlement.getAddressLine3());
        settlementDispatchLetter.setAddressLine4(settlement.getAddressLine4());

        settlementDispatchLetter.setAmount(settlement.getSettlementFinalDetails().getNetCreditAmount());

        settlementDispatchLetter.setBank(settlement.getBank().getName());

        settlementDispatchLetter.setChequeNumber(settlementDispatchLetter.getChequeNumber());
        settlementDispatchLetter.setCreatedBy(settlement.getSettlementCreatedBy());
        settlementDispatchLetter.setFromPfNumber(settlement.getEmployee().getPfNumber());
        settlementDispatchLetter.setMemberAccountNo(settlement.getAccountNumber());
        settlementDispatchLetter.setMemberBank(settlement.getBankName());
        settlementDispatchLetter.setPaymentDate(settlement.getPaymentDate());
        settlementDispatchLetter.setPaymentMode(settlement.getPaymentMode().getMode());

        return settlementDispatchLetter;

    }

    @Override
    public List<Settlement> getSettlements(List<String> entityIds) {
        return settlementRepository.findAllByEntityIdInAndIsActive(new HashSet<>(entityIds), true);
    }

    @Override
    public List<Settlement> getSettlementsForBankSheet(Date startDate, Date endDate, String bankId) {

        SettlementStatus completed = settlementStatusRepository.findByTitleAndIsActive("Payment Pending", true);

        Optional<PaymentMode> optionalPaymentMode = paymentModeRepository.findByModeAndIsActive("NEFT", true);

        if(optionalPaymentMode.isEmpty()) throw new EntityNotFoundException("Payment not found");

        Optional<Bank> optionalBank = bankRepository.findByEntityIdAndIsActive(bankId, true);

        if(optionalBank.isEmpty()) throw new EntityNotFoundException("Bank not found");

        return settlementRepository.findAllByDateOfSettlementBetweenAndBankAndPaymentModeAndSettlementStatusAndIsActive(
                startDate, new Date(endDate.getTime() + (1000 * 60 * 60 * 24)),
                optionalBank.get(), optionalPaymentMode.get(),
                completed, true);

    }

    @Override
    public List<Settlement> updatePaymentDate(List<Settlement> settlements, Date paymentDate) {

        settlements.forEach(settlement -> settlement.setPaymentDate(paymentDate));

        return settlementRepository.saveAll(settlements);
    }

    @Override
    public List<String> getBankSheetLines(List<Settlement> settlements, Date paymentDate) {

        DecimalFormat df = new DecimalFormat("####0.00");

        List<String> bankSheetList = new ArrayList<>();

        settlements.forEach(settlement -> {

            String modeCode = settlement.getIfscCode() == null ? "" : settlement.getIfscCode().substring(0, 4).equalsIgnoreCase("KKBK") ? "IFT" : "NEFT";

            String bankLine = "MMLSPF~SALPAY~" + modeCode + "~~" + DateFormatterUtil.formatDateWS(paymentDate) + "~" +
                    DateFormatterUtil.formatDateWS(paymentDate) + "~" + settlement.getAccountNumber() + "~" +
                    df.format(settlement.getSettlementFinalDetails().getNetCreditAmount()) + "~M~~" +
                    settlement.getPayeeName() + "~~" + settlement.getIfscCode() + "~" +
                    "~~~~~~~~~~Mahindra And Mahindra Ltd PF Trust~~~" + settlement.getEmployee().getPfNumber() +
                    "~" + settlement.getEmployee().getName() + "~~~~~~~~~~~~~~~~~~~~~\n";

            bankSheetList.add(bankLine);

        });

        return bankSheetList;
    }
    
    @Override
    public void updateSetllementStatus(Settlement settlement) {
    	settlementRepository.save(settlement);
    }

}
