package com.coreintegra.pftrust.services.pf.transferout;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.Nominee;
import com.coreintegra.pftrust.dtos.pdf.settlement.CalculatedAmount;
import com.coreintegra.pftrust.dtos.pdf.settlement.SettlementAnnexure;
import com.coreintegra.pftrust.dtos.pdf.settlement.Worksheet;
import com.coreintegra.pftrust.dtos.pdf.transferout.AnnuxureK;
import com.coreintegra.pftrust.dtos.pdf.transferout.TransferOutDispatchLetter;
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
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementDetailsDTO;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferOut.*;
import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.NotificationEmailDesignRepository;
import com.coreintegra.pftrust.repositories.pf.department.BankRepository;
import com.coreintegra.pftrust.repositories.pf.department.DocumentRepository;
import com.coreintegra.pftrust.repositories.pf.department.PaymentModeRepository;
import com.coreintegra.pftrust.repositories.pf.employee.ContributionStatusRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.transferout.*;
import com.coreintegra.pftrust.searchutil.SearchSpecificationForEmployee;
import com.coreintegra.pftrust.searchutil.SearchTransferOutSpecification;
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
public class TransferOutServiceImpl implements TransferOutService {

    private final Logger logger = LoggerFactory.getLogger(TransferOutService.class);

    private final EmployeeRepository employeeRepository;
    private final TransferOutRepository transferOutRepository;
    private final TransferOutTypeRepository transferOutTypeRepository;
    private final TransferOutFinalDetailsRepository transferOutFinalDetailsRepository;
    private final TransferOutStatusRepository transferOutStatusRepository;
    private final UnitCodeService unitCodeService;
    private final FinancialYearAndMonthService financialYearAndMonthService;
    private final ContributionStatusRepository contributionStatusRepository;
    private final TransferOutEmailStatusRepository transferOutEmailStatusRepository;
    private final EmailService emailService;
    private final DocumentRepository documentRepository;
    private final InterestProviderService interestProviderService;
    private final InterestRateService interestRateService;
    private final ContributionService contributionService;
    private final TransferInService transferInService;
    private final LoanService loanService;
    private final TransferOutDocumentRepository transferOutDocumentRepository;
    private final BankRepository bankRepository;
    private final PaymentModeRepository paymentModeRepository;
    private final NotificationEmailDesignRepository notificationEmailDesignRepository;

    public TransferOutServiceImpl(EmployeeRepository employeeRepository,
                                  TransferOutRepository transferOutRepository,
                                  TransferOutTypeRepository transferOutTypeRepository,
                                  TransferOutFinalDetailsRepository transferOutFinalDetailsRepository,
                                  TransferOutStatusRepository transferOutStatusRepository,
                                  UnitCodeService unitCodeService,
                                  FinancialYearAndMonthService financialYearAndMonthService,
                                  ContributionStatusRepository contributionStatusRepository,
                                  TransferOutEmailStatusRepository transferOutEmailStatusRepository,
                                  EmailService emailService, DocumentRepository documentRepository,
                                  InterestProviderService interestProviderService,
                                  InterestRateService interestRateService,
                                  ContributionService contributionService,
                                  TransferInService transferInService, LoanService loanService,
                                  TransferOutDocumentRepository transferOutDocumentRepository,
                                  BankRepository bankRepository,
                                  PaymentModeRepository paymentModeRepository,
                                  NotificationEmailDesignRepository notificationEmailDesignRepository) {
        this.employeeRepository = employeeRepository;
        this.transferOutRepository = transferOutRepository;
        this.transferOutTypeRepository = transferOutTypeRepository;
        this.transferOutFinalDetailsRepository = transferOutFinalDetailsRepository;
        this.transferOutStatusRepository = transferOutStatusRepository;
        this.unitCodeService = unitCodeService;
        this.financialYearAndMonthService = financialYearAndMonthService;
        this.contributionStatusRepository = contributionStatusRepository;
        this.transferOutEmailStatusRepository = transferOutEmailStatusRepository;
        this.emailService = emailService;
        this.documentRepository = documentRepository;
        this.interestProviderService = interestProviderService;
        this.interestRateService = interestRateService;
        this.contributionService = contributionService;
        this.transferInService = transferInService;
        this.loanService = loanService;
        this.transferOutDocumentRepository = transferOutDocumentRepository;
        this.bankRepository = bankRepository;
        this.paymentModeRepository = paymentModeRepository;
        this.notificationEmailDesignRepository = notificationEmailDesignRepository;
    }

    @Transactional
    @Override
    @Async
    @ApplyTenantFilter
    public CompletableFuture<String> saveFetchedTransferOut(TransferOut transferOut, String pernNumber,
                                                            TransferOutFinalDetails transferOutFinalDetails) {

        Optional<Employee> optionalEmployeeDetails = employeeRepository
                .findByPernNumberAndIsActive(pernNumber, true);

        if (!optionalEmployeeDetails.isPresent()) {
            throw new EntityNotFoundException("no employee found.");
        }

        Employee employee = optionalEmployeeDetails.get();

        transferOut.setEmployee(employee);

        transferOut.setFromPfNumber(employee.getPfNumber());

        TransferOutType transferOutType = transferOutTypeRepository
                .findByCodeAndIsActive(transferOut.getTransferOutType().getCode(), true);

        transferOut.setTransferOutType(transferOutType);

        transferOut.setBank(getBank(transferOut.getBank().getName()));

        TransferOut save = transferOutRepository.save(transferOut);

        transferOutFinalDetails.setTransferOut(save);

        transferOutFinalDetailsRepository.save(transferOutFinalDetails);

        ContributionStatus contributionStatus = new ContributionStatus();
        contributionStatus.setId(3L);

        employee.setContributionStatus(contributionStatus);

        employeeRepository.save(employee);

        logger.info("transferout saved successfully -> {}", save.getId());

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
    public Page<TransferOut> getTransferOuts(String search, Integer size, Integer page) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        Specification<TransferOut> specification = new SearchSpecificationForEmployee<TransferOut>()
                .getSearchSpecificationForEmployee(search);

        return transferOutRepository.findAll(specification, pageable);
    }

    @Override
    public EssentialsDTO getEssentials() {

        List<TransferOutType> transferOutTypes = transferOutTypeRepository.findAllByIsActive(true);

        List<TypeDTO> typeDTOList = transferOutTypes.stream()
                .map(type -> new TypeDTO(type.getEntityId(), type.getTitle()))
                .collect(Collectors.toList());

        List<StatusDTO> statusDTOList = transferOutStatusRepository.findAllByIsActive(true).stream()
                .map(status -> new StatusDTO(status.getEntityId(), status.getTitle()))
                .collect(Collectors.toList());

        List<UnitCode> unitCodes = unitCodeService.get();

        List<Integer> years = financialYearAndMonthService.getYears();

        Map<Integer, String> months = financialYearAndMonthService.getMonths();

        return new EssentialsDTO(typeDTOList, statusDTOList, unitCodes, years, months);

    }

    @Override
    public List<TransferOut> getTransferOuts(Employee employee) {
        return transferOutRepository.findAll(getSpecification(employee));
    }

    @Override
    public List<SettlementDetailsDTO> getTransferOutDetails(Employee employee) {
        return getTransferOuts(employee).stream()
                .map(transferOut -> {

                    TransferOutType transferOutType = transferOut.getTransferOutType();
                    TransferOutStatus transferOutStatus = transferOut.getTransferOutStatus();
                    TransferOutFinalDetails transferOutFinalDetails = transferOut.getTransferOutFinalDetails();

                    return new SettlementDetailsDTO(transferOut.getEntityId(),
                            transferOut.getPayeeName(), transferOut.getDateOfLeavingService(),
                            transferOut.getDueDate(), transferOut.getAlternateEmailId(),
                            transferOut.getAlternateContactNumber(), transferOutType.getTitle(),
                            transferOutStatus.getTitle(), transferOut.getPaymentDate(),
                            transferOutFinalDetails.getTotalContribution(),
                            transferOutFinalDetails.getTotalNetCreditAmount(),
                            transferOut.getDocumentNumber(), false);

                }).collect(Collectors.toList());
    }

    @Override
    public TransferOut getTransferOut(String entityId) {

        Optional<TransferOut> optionalTransferOut = transferOutRepository.findByEntityIdAndIsActive(entityId, true);

        if(optionalTransferOut.isEmpty()) throw new EntityNotFoundException("transfer out not found");

        return optionalTransferOut.get();
    }

    @Override
    public Optional<TransferOut> getCompletedTransferOut(Employee employee) {

        Optional<TransferOutStatus> optionalTransferOutStatus = transferOutStatusRepository.findById(2L);

        TransferOutStatus transferOutStatus = optionalTransferOutStatus.get();

        return transferOutRepository.getTransferOutByEmployeeAndTransferOutStatusAndIsActive(employee, transferOutStatus,
                true);

    }

    private Specification<TransferOut> getSpecification(Employee employee) {
        SearchTransferOutSpecification transferOutSpecification = new SearchTransferOutSpecification();
        return transferOutSpecification.employeeSpecification(employee);
    }


    @Override
    public TransferOut savePaymentDetails(TransferOut transferOut, Date paymentDate, String referenceNumber, Bank bank,
                                   PaymentMode paymentMode) throws JPAException {

        Employee employee1 = transferOut.getEmployee();
        
        if(employee1.isSettled() || employee1.isMerged()){
            logger.info("Employee is already {}", employee1.getContributionStatus().getDescription());
            throw new JPAException("Employee -> " + employee1.getPernNumber() + " is already " + employee1.getContributionStatus().getDescription());
        }
    	
    	transferOut.setPaymentDate(paymentDate);
        transferOut.setChequeNo(referenceNumber);
        transferOut.setBank(bank);
        transferOut.setPaymentMode(paymentMode);

        TransferOutStatus completed = transferOutStatusRepository.findByTitleAndIsActive("Completed", true);

        transferOut.setTransferOutStatus(completed);

        TransferOut save = transferOutRepository.save(transferOut);

        Employee employee = save.getEmployee();

        ContributionStatus settled = contributionStatusRepository.findBySymbolAndIsActive("S", true);

        employee.setContributionStatus(settled);

        employeeRepository.save(employee);

        return save;
    }


    @Override
    public TransferOutEmailStatus createEmailStatus(TransferOut transferOut) {

        String emailId = transferOut.getAlternateEmailId();

        if(emailId == null){
            emailId = transferOut.getEmployee().getEmail();
        }

        TransferOutEmailStatus transferOutEmailStatus = new TransferOutEmailStatus(transferOut.getFromPfNumber(),
                transferOut.getToPfNumber(), transferOut.getEmployee().getName(),
                transferOut.getEmployee().getUnitCode(), transferOut.getPaymentDate(),
                transferOut.getTransferOutFinalDetails().getTotalNetCreditAmount(),
                transferOut.getPayeeName(), transferOut.getCurrentEmployerName(),
                emailId, transferOut, false);

        return transferOutEmailStatusRepository.save(transferOutEmailStatus);

    }

    @Override
    public void updateEmailStatus(TransferOutEmailStatus transferOutEmailStatus) {
        transferOutEmailStatus.setSent(true);
        transferOutEmailStatusRepository.save(transferOutEmailStatus);
    }

    @Override
    public void sendConfirmationEmail(List<Pair<TransferOutEmailStatus, List<EmailAttachment>>> transferOutEmailStatusList) {
        for (Pair<TransferOutEmailStatus, List<EmailAttachment>>emailStatusPair:transferOutEmailStatusList) {
            sendConfirmationEmail(emailStatusPair.getFirst(), emailStatusPair.getSecond());
        }
    }

    @Override
    public void sendConfirmationEmail(TransferOutEmailStatus transferOutEmailStatus, List<EmailAttachment> emailAttachments) {
        sendConfirmationEmail(transferOutEmailStatus.getEmailId(), transferOutEmailStatus.getEmployeeName(),
                transferOutEmailStatus.getFromPfAccount(), transferOutEmailStatus.getToPfAccount(),
                transferOutEmailStatus.getTotalContribution(), emailAttachments);
    }

    @Override
    public void sendConfirmationEmail(String emailId, String employeeName, String fromPfAccount, String toPfAccount,
                                      BigDecimal netCredit, List<EmailAttachment> emailAttachments) {

        String subject = " Subject: PF Notifications";

        Optional<NotificationEmailDesign> optional = notificationEmailDesignRepository
                .findByDocumentType(NotificationEmailDesign.TRANSFER_OUT_COMPLETION);

        if(optional.isEmpty()) throw new EntityNotFoundException("Email Design Not Found");

        NotificationEmailDesign notificationEmailDesign = optional.get();

        String emailBody = notificationEmailDesign.getDocument().replace("{{name}}", employeeName)
                .replace("{{amount}}", NumberFormatter.formatNumbers(netCredit))
                .replace("{{fromPfAccount}}", fromPfAccount)
                .replace("{{toPfAccount}}", toPfAccount);

        emailService.sendMessageWithAttachment(emailId, subject, emailBody, emailAttachments);

    }

    @Override
    public void reject(String entityId) {

        TransferOut transferOut = getTransferOut(entityId);

        TransferOutStatus transferOutStatus = transferOut.getTransferOutStatus();

        if(transferOutStatus.getTitle().equalsIgnoreCase("Completed") ||
                transferOutStatus.getTitle().equalsIgnoreCase("Canceled")) {
            throw new EntityExistsException("Transfer Out Application is already " + transferOutStatus.getTitle());
        }

        TransferOutStatus canceled = transferOutStatusRepository.findByTitleAndIsActive("Canceled", true);

        transferOut.setTransferOutStatus(canceled);

        transferOutRepository.save(transferOut);

    }

    @Override
    public List<TransferOutEmailStatus> getEmailStatusListNotSent() {
        return transferOutEmailStatusRepository.findAllByIsSentAndIsActive(false, true);
    }

    @Override
    public List<TransferOutEmailStatus> getTransferOutEmailStatusList(List<String> entityIds) {
        return transferOutEmailStatusRepository.findAllByIsActiveAndEntityIdIn(true, new HashSet<>(entityIds));
    }

    @Override
    public List<Document> getDocumentsForTransferOutType(String entityId) {

        Optional<TransferOutType> optionalTransferOutType = transferOutTypeRepository.findByEntityIdAndIsActive(entityId, true);

        if(optionalTransferOutType.isEmpty()) throw new EntityNotFoundException("Transfer Out not found");

        return optionalTransferOutType.get().getDocuments();
    }

    @Override
    public List<TransferOutType> getTransferOutTypes() {
        return transferOutTypeRepository.findAllByIsActive(true);
    }

    @Override
    public TransferOutType getTransferOutType(String entityId) {
        Optional<TransferOutType> optionalTransferOutType = transferOutTypeRepository
                .findByEntityIdAndIsActive(entityId, true);

        if(optionalTransferOutType.isEmpty()) throw new EntityNotFoundException("Transfer Out not found");

        return optionalTransferOutType.get();
    }

    @Override
    public List<TransferOutDocument> getTransferOutDocuments(JSONArray documents) {
        List<TransferOutDocument> transferOutDocumentList = new ArrayList<>();

        for (int i=0; i< documents.length(); i++){

            JSONObject jsonObject = documents.getJSONObject(i);

            Optional<Document> optionalDocument = documentRepository
                    .findByEntityIdAndIsActive(jsonObject.getString("entityId"), true);

            if(optionalDocument.isEmpty()) throw new EntityNotFoundException("Document Not Found.");

            Document document = optionalDocument.get();

            TransferOutDocument transferOutDocument = new TransferOutDocument();

            transferOutDocument.setDocument(document);
            transferOutDocument.setPath(jsonObject.getString("path"));

            transferOutDocumentList.add(transferOutDocument);

        }

        return transferOutDocumentList;
    }


    @Override
    public TransferOut create(TransferOut transferOut, Employee employee, PaymentMode paymentMode) throws JPAException {

        if(employee.isSettled() || employee.isMerged()){
            logger.info("Employee is already {}", employee.getContributionStatus().getDescription());
            throw new JPAException("Employee -> " + employee.getPernNumber() + " is already " + employee.getContributionStatus().getDescription());
        }

        int transferOutYear = FinancialYearAndMonth.getFinancialYear(transferOut.getDueDate());

        try {
            Contribution openingBalance = contributionService.getContribution(employee, transferOutYear, 0);
        }catch (EntityNotFoundException exception){
            throw new JPAException("Please perform year end process for the employee before proceeding.");
        }

        InterestRate interestRate = interestRateService.getActive();

        List<Contribution> contributions = contributionService.getContributions(employee, transferOutYear);

        List<Loan> loans = loanService.getCompletedLoans(employee, transferOutYear);

        List<TransferIn> transferIns = transferInService.getCompletedTransferIns(employee, transferOutYear);

        List<Contribution> interestCalculatedContributions = interestProviderService
                .performForContributions(employee, transferOutYear, contributions, interestRate,
                        transferOut.getDueDate());

        List<Loan> interestCalculatedLoans = interestProviderService
                .performForLoans(transferOutYear, loans, interestRate, transferOut.getDueDate());

        List<TransferIn> interestCalculatedTransferIns = interestProviderService
                .performForTransferIns(transferOutYear, transferIns, interestRate, transferOut.getDueDate());

        TransferOutFinalDetails transferOutFinalDetails = getTransferOutFinalDetails(interestCalculatedContributions,
                interestCalculatedTransferIns,
                interestCalculatedLoans);

        transferOut.setInterestRate(interestRate.getInterestRate().floatValue());
        transferOut.setEmployee(employee);
        transferOut.setPaymentMode(paymentMode);

        TransferOutStatus transferOutStatus = transferOutStatusRepository.findByTitleAndIsActive(SettlementStatus.IN_PROCESS, true);

        transferOut.setTransferOutStatus(transferOutStatus);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(1L);

        transferOut.setSapStatus(sapStatus);

        TransferOut save = transferOutRepository.save(transferOut);

        transferOutFinalDetails.setTransferOut(save);

        transferOutFinalDetailsRepository.save(transferOutFinalDetails);

        return save;

    }

    @Override
    public TransferOut update(TransferOut transferOut, Employee employee, PaymentMode paymentMode) throws JPAException {

        if(employee.isSettled() || employee.isMerged()){
            logger.info("Employee is already {}", employee.getContributionStatus().getDescription());
            throw new JPAException("Employee -> " + employee.getPernNumber() + " is already " + employee.getContributionStatus().getDescription());
        }

        int transferOutYear = FinancialYearAndMonth.getFinancialYear(transferOut.getDueDate());

        try {
            Contribution openingBalance = contributionService.getContribution(employee, transferOutYear, 0);
        }catch (EntityNotFoundException exception){
            throw new JPAException("Please perform year end process for the employee before proceeding.");
        }

        InterestRate interestRate = interestRateService.getActive();

        List<Contribution> contributions = contributionService.getContributions(employee, transferOutYear);

        List<Loan> loans = loanService.getCompletedLoans(employee, transferOutYear);

        List<TransferIn> transferIns = transferInService.getCompletedTransferIns(employee, transferOutYear);

        List<Contribution> interestCalculatedContributions = interestProviderService
                .performForContributions(employee, transferOutYear, contributions, interestRate,
                        transferOut.getDueDate());

        List<Loan> interestCalculatedLoans = interestProviderService
                .performForLoans(transferOutYear, loans, interestRate, transferOut.getDueDate());

        List<TransferIn> interestCalculatedTransferIns = interestProviderService
                .performForTransferIns(transferOutYear, transferIns, interestRate, transferOut.getDueDate());

        TransferOutFinalDetails transferOutFinalDetails = getTransferOutFinalDetails(
                transferOut.getTransferOutFinalDetails(),
                interestCalculatedContributions,
                interestCalculatedTransferIns,
                interestCalculatedLoans);

        transferOut.setInterestRate(interestRate.getInterestRate().floatValue());
        transferOut.setEmployee(employee);
        transferOut.setPaymentMode(paymentMode);

        TransferOutStatus transferOutStatus = transferOutStatusRepository.findByTitleAndIsActive(SettlementStatus.IN_PROCESS, true);

        transferOut.setTransferOutStatus(transferOutStatus);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(1L);

        transferOut.setSapStatus(sapStatus);

        TransferOut save = transferOutRepository.save(transferOut);

        transferOutFinalDetails.setTransferOut(save);

        transferOutFinalDetailsRepository.save(transferOutFinalDetails);

        return save;

    }

    private TransferOutFinalDetails getTransferOutFinalDetails(List<Contribution> interestCalculatedContributions,
                                                               List<TransferIn> interestCalculatedTransferIns,
                                                               List<Loan> interestCalculatedLoans){
        TransferOutFinalDetails transferOutFinalDetails = new TransferOutFinalDetails();

        return getTransferOutFinalDetails(transferOutFinalDetails, interestCalculatedContributions, interestCalculatedTransferIns,
                interestCalculatedLoans);

    }

    private TransferOutFinalDetails getTransferOutFinalDetails(
            TransferOutFinalDetails transferOutFinalDetails,
            List<Contribution> interestCalculatedContributions,
                                                               List<TransferIn> interestCalculatedTransferIns,
                                                               List<Loan> interestCalculatedLoans){

        Contribution yearOpeningContribution = interestCalculatedContributions.stream()
                .filter(contribution -> contribution.getSubType() == 0)
                .collect(Collectors.toList()).get(0);

        List<Contribution> currentYearContributions = interestCalculatedContributions.stream()
                .filter(contribution -> contribution.getSubType() != 0).collect(Collectors.toList());

        addYearOpeningContribution(yearOpeningContribution, transferOutFinalDetails);

        addCurrentYearContributions(currentYearContributions, transferOutFinalDetails);

        addCurrentYearTransferIn(interestCalculatedTransferIns, transferOutFinalDetails);

        addCurrentYearLoans(interestCalculatedLoans, transferOutFinalDetails);

        BigDecimal totalMemberContribution = transferOutFinalDetails.getMemberContributionYearOpening()
                .add(transferOutFinalDetails.getMemberContributionCurrentYear())
                .add(transferOutFinalDetails.getMemberContributionCurrentYearTi())
                .add(transferOutFinalDetails.getInterestOnMemberContributionYearOpening())
                .add(transferOutFinalDetails.getInterestOnMemberContributionCurrentYear())
                .add(transferOutFinalDetails.getInterestOnMemberContributionCurrentYearTi());

        BigDecimal totalCompanyContribution = transferOutFinalDetails.getCompanyContributionYearOpening()
                .add(transferOutFinalDetails.getCompanyContributionCurrentYear())
                .add(transferOutFinalDetails.getCompanyContributionCurrentYearTi())
                .add(transferOutFinalDetails.getInterestOnCompanyContributionYearOpening())
                .add(transferOutFinalDetails.getInterestOnCompanyContributionCurrentYear())
                .add(transferOutFinalDetails.getInterestOnCompanyContributionCurrentYearTi());

        BigDecimal totalVpfContribution = transferOutFinalDetails.getVpfContributionYearOpening()
                .add(transferOutFinalDetails.getVpfContributionCurrentYear())
                .add(transferOutFinalDetails.getVpfContributionCurrentYearTi())
                .add(transferOutFinalDetails.getInterestOnVpfContributionYearOpening())
                .add(transferOutFinalDetails.getInterestOnVpfContributionCurrentYear())
                .add(transferOutFinalDetails.getInterestOnVpfContributionCurrentYearTi());

        BigDecimal totalLoans = transferOutFinalDetails.getMemberLoanWithDrawal()
                .add(transferOutFinalDetails.getCompanyLoanWithDrawal())
                .add(transferOutFinalDetails.getVpfLoanWithDrawal())
                .add(transferOutFinalDetails.getInterestOnMemberLoanWithDrawal())
                .add(transferOutFinalDetails.getInterestOnCompanyLoanWithDrawal())
                .add(transferOutFinalDetails.getInterestOnVpfLoanWithDrawal());

        transferOutFinalDetails.setTotalMemberContribution(totalMemberContribution);
        transferOutFinalDetails.setTotalCompanyContribution(totalCompanyContribution);
        transferOutFinalDetails.setTotalVpfContribution(totalVpfContribution);

        transferOutFinalDetails.setTotalLoan(totalLoans);

        BigDecimal totalContribution = totalMemberContribution.add(totalCompanyContribution)
                .add(totalVpfContribution);

        transferOutFinalDetails.setTotalContribution(totalContribution);

        return transferOutFinalDetails;

    }

    private void addYearOpeningContribution(Contribution interestCalculatedContribution,
                                            TransferOutFinalDetails transferOutFinalDetails){

        transferOutFinalDetails.setMemberContributionYearOpening(interestCalculatedContribution.getMemberContribution());
        transferOutFinalDetails.setCompanyContributionYearOpening(interestCalculatedContribution.getCompanyContribution());
        transferOutFinalDetails.setVpfContributionYearOpening(interestCalculatedContribution.getVpfContribution());

        transferOutFinalDetails.setInterestOnMemberContributionYearOpening(interestCalculatedContribution.getInterestMemContribution());
        transferOutFinalDetails.setInterestOnCompanyContributionYearOpening(interestCalculatedContribution.getInterestCompanyContribution());
        transferOutFinalDetails.setInterestOnVpfContributionYearOpening(interestCalculatedContribution.getVpfContributionInterest());

        BigDecimal totalYearOpeningContribution = BigDecimal.ZERO
                .add(DataUtil.avoidNull(interestCalculatedContribution.getMemberContribution()))
                .add(DataUtil.avoidNull(interestCalculatedContribution.getCompanyContribution()))
                .add(DataUtil.avoidNull(interestCalculatedContribution.getVpfContribution()));

        BigDecimal totalInterestOnYearOpeningContribution = BigDecimal.ZERO
                .add(DataUtil.avoidNull(interestCalculatedContribution.getInterestMemContribution()))
                .add(DataUtil.avoidNull(interestCalculatedContribution.getInterestCompanyContribution()))
                .add(DataUtil.avoidNull(interestCalculatedContribution.getVpfContributionInterest()));

        transferOutFinalDetails.setTotalContributionYearOpening(totalYearOpeningContribution);
        transferOutFinalDetails.setTotalInterestYearOpening(totalInterestOnYearOpeningContribution);

    }

    private void addCurrentYearContributions(List<Contribution> interestCalculatedContributions,
                                             TransferOutFinalDetails transferOutFinalDetails){

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal interestOnMemberContribution = BigDecimal.ZERO;
        BigDecimal interestOnCompanyContribution = BigDecimal.ZERO;
        BigDecimal interestOnVpfContribution = BigDecimal.ZERO;


        for (Contribution contribution:interestCalculatedContributions) {

            memberContribution = memberContribution.add(DataUtil.avoidNull(contribution.getMemberContribution()));
            companyContribution = companyContribution.add(DataUtil.avoidNull(contribution.getCompanyContribution()));
            vpfContribution = vpfContribution.add(DataUtil.avoidNull(contribution.getVpfContribution()));

            interestOnMemberContribution = interestOnMemberContribution.add(DataUtil.avoidNull(contribution.getInterestMemContribution()));
            interestOnCompanyContribution = interestOnCompanyContribution.add(DataUtil.avoidNull(contribution.getInterestCompanyContribution()));
            interestOnVpfContribution = interestOnVpfContribution.add(DataUtil.avoidNull(contribution.getVpfContributionInterest()));

        }

        transferOutFinalDetails.setMemberContributionCurrentYear(memberContribution);
        transferOutFinalDetails.setCompanyContributionCurrentYear(companyContribution);
        transferOutFinalDetails.setVpfContributionCurrentYear(vpfContribution);

        transferOutFinalDetails.setInterestOnMemberContributionCurrentYear(interestOnMemberContribution);
        transferOutFinalDetails.setInterestOnCompanyContributionCurrentYear(interestOnCompanyContribution);
        transferOutFinalDetails.setInterestOnVpfContributionCurrentYear(interestOnVpfContribution);

        BigDecimal totalCurrentYearContribution = memberContribution.add(companyContribution).add(vpfContribution);
        BigDecimal totalInterestOnCurrentYearContribution = interestOnMemberContribution
                .add(interestOnCompanyContribution).add(interestOnVpfContribution);

        transferOutFinalDetails.setTotalContributionCurrentYear(totalCurrentYearContribution);
        transferOutFinalDetails.setTotalInterestCurrentYear(totalInterestOnCurrentYearContribution);

    }


    private void addCurrentYearTransferIn(List<TransferIn> interestCalculatedTransferIns,
                                          TransferOutFinalDetails transferOutFinalDetails){

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal interestOnMemberContribution = BigDecimal.ZERO;
        BigDecimal interestOnCompanyContribution = BigDecimal.ZERO;
        BigDecimal interestOnVpfContribution = BigDecimal.ZERO;


        for (TransferIn transferIn:interestCalculatedTransferIns) {

            TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

            memberContribution = memberContribution.add(DataUtil.avoidNull(transferInFinalDetails.getMemberContribution()));
            companyContribution = companyContribution.add(DataUtil.avoidNull(transferInFinalDetails.getCompanyContribution()));
            vpfContribution = vpfContribution.add(DataUtil.avoidNull(transferInFinalDetails.getVpfContribution()));

            interestOnMemberContribution = interestOnMemberContribution.add(DataUtil.avoidNull(transferInFinalDetails.getInterestOnMemberContribution()));
            interestOnCompanyContribution = interestOnCompanyContribution.add(DataUtil.avoidNull(transferInFinalDetails.getInterestOnCompanyContribution()));
            interestOnVpfContribution = interestOnVpfContribution.add(DataUtil.avoidNull(transferInFinalDetails.getInterestOnVpfContribution()));

        }

        transferOutFinalDetails.setMemberContributionCurrentYearTi(memberContribution);
        transferOutFinalDetails.setCompanyContributionCurrentYearTi(companyContribution);
        transferOutFinalDetails.setVpfContributionCurrentYearTi(vpfContribution);

        transferOutFinalDetails.setInterestOnMemberContributionCurrentYearTi(interestOnMemberContribution);
        transferOutFinalDetails.setInterestOnCompanyContributionCurrentYearTi(interestOnCompanyContribution);
        transferOutFinalDetails.setInterestOnVpfContributionCurrentYearTi(interestOnVpfContribution);

        BigDecimal totalCurrentYearTransferInContribution = memberContribution.add(companyContribution).add(vpfContribution);
        BigDecimal totalInterestOnCurrentYearTransferInContribution = interestOnMemberContribution
                .add(interestOnCompanyContribution).add(interestOnVpfContribution);

        transferOutFinalDetails.setTotalContributionCurrentYearTi(totalCurrentYearTransferInContribution);
        transferOutFinalDetails.setTotalInterestCurrentYearTi(totalInterestOnCurrentYearTransferInContribution);

    }


    private void addCurrentYearLoans(List<Loan> interestCalculatedLoans,
                                     TransferOutFinalDetails transferOutFinalDetails){

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal interestOnMemberContribution = BigDecimal.ZERO;
        BigDecimal interestOnCompanyContribution = BigDecimal.ZERO;
        BigDecimal interestOnVpfContribution = BigDecimal.ZERO;


        for (Loan loan:interestCalculatedLoans) {

            LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

            memberContribution = memberContribution.add(DataUtil.avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnMemContribution()));
            companyContribution = companyContribution.add(DataUtil.avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnCompanyContribution()));
            vpfContribution = vpfContribution.add(DataUtil.avoidNull(loanWithDrawalsFinalDetails.getLoanAmountOnVPFContribution()));

            interestOnMemberContribution = interestOnMemberContribution.add(DataUtil.avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnMemContribution()));
            interestOnCompanyContribution = interestOnCompanyContribution.add(DataUtil.avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnCompanyContribution()));
            interestOnVpfContribution = interestOnVpfContribution.add(DataUtil.avoidNull(loanWithDrawalsFinalDetails.getInterestOnloanAmountOnVPFContribution()));

        }

        transferOutFinalDetails.setMemberLoanWithDrawal(memberContribution);
        transferOutFinalDetails.setCompanyLoanWithDrawal(companyContribution);
        transferOutFinalDetails.setVpfLoanWithDrawal(vpfContribution);

        transferOutFinalDetails.setInterestOnMemberLoanWithDrawal(interestOnMemberContribution);
        transferOutFinalDetails.setInterestOnCompanyLoanWithDrawal(interestOnCompanyContribution);
        transferOutFinalDetails.setInterestOnVpfLoanWithDrawal(interestOnVpfContribution);

        BigDecimal totalCurrentYearLoans = memberContribution.add(companyContribution).add(vpfContribution);
        BigDecimal totalInterestOnCurrentYearLoans = interestOnMemberContribution.add(interestOnCompanyContribution)
                .add(interestOnVpfContribution);

        transferOutFinalDetails.setTotalLoanWithDrawal(totalCurrentYearLoans);
        transferOutFinalDetails.setTotalInterestLoanWithDrawal(totalInterestOnCurrentYearLoans);

    }


    @Override
    public void saveTransferOutDocuments(List<TransferOutDocument> transferOutDocuments, TransferOut transferOut) {
        List<TransferOutDocument> mappedDocuments = transferOutDocuments.stream()
                .peek(settlementDocument -> settlementDocument.setTransferOut(transferOut))
                .collect(Collectors.toList());
        transferOutDocumentRepository.saveAll(mappedDocuments);
    }


    @Override
    public void updateSettlementDocuments(JSONArray jsonArray) {

        for (int i=0; i< jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Optional<TransferOutDocument> optionalTransferOutDocument = transferOutDocumentRepository
                    .findByEntityIdAndIsActive(jsonObject.getString("entityId"), true);

            if(optionalTransferOutDocument.isPresent()){

                TransferOutDocument transferOutDocument = optionalTransferOutDocument.get();

                transferOutDocument.setPath(jsonObject.getString("path"));

                transferOutDocumentRepository.save(transferOutDocument);
            }

        }

    }


    @Override
    public AnnuxureK getAnnexureK(TransferOut transferOut) throws ParseException {

        AnnuxureK annuxureK = new AnnuxureK();

        annuxureK.setUnitCode(transferOut.getEmployee().getUnitCode());
        annuxureK.setSerialNumber(transferOut.getId().toString());
        annuxureK.setDocumentNumber(transferOut.getDocumentNumber());

        String concatPfNo = "KD/MAL/496/"+transferOut.getFromPfNumber();
        annuxureK.setFromAccount(concatPfNo);
        annuxureK.setToAccount(transferOut.getToPfNumber());

        annuxureK.setTransferOutType(transferOut.getTransferOutType().getTitle());
        annuxureK.setPayeeName(transferOut.getPayeeName());
        annuxureK.setAddressLine1(transferOut.getAddressLine1());
        annuxureK.setAddressLine2(transferOut.getAddressLine2());
        annuxureK.setAddressLine3(transferOut.getAddressLine3());
        annuxureK.setAddressLine4(transferOut.getAddressLine4());
        annuxureK.setCurrentEmployerName(transferOut.getCurrentEmployerName());
        annuxureK.setCurrentAddressLine1(transferOut.getCurrentEmployerAddressLine1());
        annuxureK.setCurrentAddressLine2(transferOut.getCurrentEmployerAddressLine2());
        annuxureK.setCurrentAddressLine3(transferOut.getCurrentEmployerAddressLine3());
        annuxureK.setCurrentAddressLine4(transferOut.getCurrentEmployerAddressLine4());

        annuxureK.setNameOfSubscriber(transferOut.getEmployee().getName());

        annuxureK.setYearOpeningDate(FinancialYearAndMonth.getYearOpeningFromDate(transferOut.getDueDate()));

        annuxureK.setYearOpeningEmployeeContribution(transferOut.getTransferOutFinalDetails().getMemberContributionYearOpening());
        annuxureK.setYearOpeningEmployerContribution(transferOut.getTransferOutFinalDetails().getCompanyContributionYearOpening());
        annuxureK.setYearOpeningVoluntaryContribution(transferOut.getTransferOutFinalDetails().getVpfContributionYearOpening());

        annuxureK.setEmployeeContribution(transferOut.getTransferOutFinalDetails().getMemberContributionCurrentYear());
        annuxureK.setEmployeeContributionTransferIn(transferOut.getTransferOutFinalDetails().getMemberContributionCurrentYearTi());

        annuxureK.setInterestOnEmployeeContribution(transferOut.getTransferOutFinalDetails().getInterestOnMemberContributionTotal());

        annuxureK.setEmployerContribution(transferOut.getTransferOutFinalDetails().getCompanyContributionCurrentYear());
        annuxureK.setEmployerContributionTransferIn(transferOut.getTransferOutFinalDetails().getCompanyContributionCurrentYearTi());
        annuxureK.setInterestOnEmployerContribution(transferOut.getTransferOutFinalDetails().getInterestOnCompanyContributionTotal());

        annuxureK.setVoluntaryContribution(transferOut.getTransferOutFinalDetails().getVpfContributionCurrentYear());
        annuxureK.setInterestOnVoluntaryContribution(transferOut.getTransferOutFinalDetails().getInterestOnVpfContributionTotal());

        annuxureK.setTotalContribution(transferOut.getTransferOutFinalDetails().getTotalContributions());

        annuxureK.setLoanWithdrawals(transferOut.getTransferOutFinalDetails().getTotalLoanWithDrawal());

        annuxureK.setNetCredit(transferOut.getTransferOutFinalDetails().getTotalNetCreditAmount());

        annuxureK.setDueDate(transferOut.getDueDate());

        annuxureK.setDateOfJoiningService(transferOut.getEmployee().getDateOfJoining());
        annuxureK.setDateOfJoiningPf(transferOut.getEmployee().getDateOfJoiningPF());

        annuxureK.setEpsNumber(transferOut.getFromEpsNumber());

        annuxureK.setDateOfLeavingService(transferOut.getDateOfLeavingService());

        annuxureK.setCreatedBy(transferOut.getTransferOutCreatedBy());

        List<PreviousCompany> previousCompanyList = transferOut.getEmployee().getPreviousCompanies();

        if(previousCompanyList != null && previousCompanyList.size() > 0){
            annuxureK.setDateOfJoiningPreviousEmployer(previousCompanyList.get(0).getDateOfJoining());
        }

        return annuxureK;

    }

    @Override
    public TransferOutDispatchLetter getDispatchLetter(TransferOut transferOut) {

        TransferOutDispatchLetter transferOutDispatchLetter = new TransferOutDispatchLetter();

        transferOutDispatchLetter.setRefNo("SPF/" + transferOut.getId());
        transferOutDispatchLetter.setStlNo(transferOut.getId().toString());
        transferOutDispatchLetter.setPayeeName(transferOut.getPayeeName());

        transferOutDispatchLetter.setAddressLine1(transferOut.getAddressLine1());
        transferOutDispatchLetter.setAddressLine2(transferOut.getAddressLine2());
        transferOutDispatchLetter.setAddressLine3(transferOut.getAddressLine3());
        transferOutDispatchLetter.setAddressLine4(transferOut.getAddressLine4());

        transferOutDispatchLetter.setName(transferOut.getEmployee().getName());
        String fromPF = "KD/MAL/496/"+transferOut.getFromPfNumber();
        transferOutDispatchLetter.setFromPfNumber(fromPF);
        transferOutDispatchLetter.setToPfNumber(transferOut.getToPfNumber());

        transferOutDispatchLetter.setPaymentMode(transferOut.getPaymentMode().getMode());
        transferOutDispatchLetter.setBank(transferOut.getBank().getName());

        transferOutDispatchLetter.setPaymentDate(transferOut.getPaymentDate());

        transferOutDispatchLetter.setChequeNumber(transferOut.getChequeNo());
        transferOutDispatchLetter.setAmount(transferOut.getTransferOutFinalDetails().getTotalNetCreditAmount());

        transferOutDispatchLetter.setCreatedBy(transferOut.getTransferOutCreatedBy());

        transferOutDispatchLetter.setCurrentEmployerName(transferOut.getCurrentEmployerName());
        transferOutDispatchLetter.setCurrentEmployerAddressLine1(transferOut.getCurrentEmployerAddressLine1());
        transferOutDispatchLetter.setCurrentEmployerAddressLine2(transferOut.getCurrentEmployerAddressLine2());
        transferOutDispatchLetter.setCurrentEmployerAddressLine3(transferOut.getCurrentEmployerAddressLine3());
        transferOutDispatchLetter.setCurrentEmployerAddressLine4(transferOut.getCurrentEmployerAddressLine4());

        return transferOutDispatchLetter;
    }

    @Override
    public Worksheet getWorkSheet(TransferOut transferOut) throws ParseException {

        Worksheet worksheet = new Worksheet();

        int financialYear = FinancialYearAndMonth.getFinancialYear(transferOut.getDueDate());

        String stlNo = 1 + "" + financialYear + "" + transferOut.getId();

        worksheet.setSettlementNo(stlNo);
        worksheet.setDateOfCessation(transferOut.getDateOfLeavingService());
        worksheet.setDocumentNo(transferOut.getDocumentNumber());
        worksheet.setName(transferOut.getEmployee().getName());

        worksheet.setTokenNumber(transferOut.getEmployee().getTokenNumber());
        worksheet.setPfNumber(transferOut.getEmployee().getPfNumber());
        worksheet.setUnitCode(transferOut.getEmployee().getUnitCode());
        worksheet.setDeptCode("");

        worksheet.setStatus(transferOut.getEmployee().getContributionStatus().getDescription());
        worksheet.setDateOfBirth(transferOut.getEmployee().getDateOfBirth());
        worksheet.setDateOfJoining(transferOut.getEmployee().getDateOfJoiningPF());

        List<PreviousCompany> previousCompanyList = transferOut.getEmployee().getPreviousCompanies();

        if (previousCompanyList != null && previousCompanyList.size() > 0) {
            worksheet.setDateOfJoiningPrior(previousCompanyList.get(0).getDateOfJoining());
        }

        java.util.Date date = contributionService.getLastRecoveryDate(transferOut.getEmployee());

        worksheet.setLastRecoverDate(date);

        worksheet.setYearOpeningDate(FinancialYearAndMonth.getYearOpeningFromDate(transferOut.getDueDate()));

        worksheet.setInterestRate(transferOut.getInterestRate());

        worksheet.setYearOpeningMemberContribution(
                transferOut.getTransferOutFinalDetails().getMemberContributionYearOpening());
        worksheet.setYearOpeningCompanyContribution(
                transferOut.getTransferOutFinalDetails().getCompanyContributionYearOpening());
        worksheet.setYearOpeningVpfContribution(transferOut.getTransferOutFinalDetails().getVpfContributionYearOpening());
        worksheet.setYearOpeningTotalContribution(
                transferOut.getTransferOutFinalDetails().getTotalContributionYearOpening());

        worksheet.setInterestOnYearOpeningMemberContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnMemberContributionYearOpening());
        worksheet.setInterestOnYearOpeningCompanyContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnCompanyContributionYearOpening());
        worksheet.setInterestOnYearOpeningVpfContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnVpfContributionYearOpening());
        worksheet.setInterestOnYearOpeningTotalContribution(
                transferOut.getTransferOutFinalDetails().getTotalInterestYearOpening());

        worksheet.setCurrentYearMemberContribution(
                transferOut.getTransferOutFinalDetails().getMemberContributionCurrentYear());
        worksheet.setCurrentYearCompanyContribution(
                transferOut.getTransferOutFinalDetails().getCompanyContributionCurrentYear());
        worksheet.setCurrentYearVpfContribution(transferOut.getTransferOutFinalDetails().getVpfContributionCurrentYear());
        worksheet.setCurrentYearTotalContribution(
                transferOut.getTransferOutFinalDetails().getTotalContributionCurrentYear());

        worksheet.setInterestOnCurrentYearMemberContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnMemberContributionCurrentYear());
        worksheet.setInterestOnCurrentYearCompanyContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnCompanyContributionCurrentYear());
        worksheet.setInterestOnCurrentYearVpfContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnVpfContributionCurrentYear());
        worksheet.setInterestOnCurrentYearTotalContribution(
                transferOut.getTransferOutFinalDetails().getTotalInterestCurrentYear());

        worksheet.setCurrentYearTransferInMemberContribution(
                transferOut.getTransferOutFinalDetails().getMemberContributionCurrentYearTi());
        worksheet.setCurrentYearTransferInCompanyContribution(
                transferOut.getTransferOutFinalDetails().getCompanyContributionCurrentYearTi());
        worksheet.setCurrentYearTransferInVpfContribution(
                transferOut.getTransferOutFinalDetails().getVpfContributionCurrentYearTi());
        worksheet.setCurrentYearTransferInTotalContribution(
                transferOut.getTransferOutFinalDetails().getTotalContributionCurrentYearTi());

        worksheet.setInterestOnCurrentYearTransferInMemberContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnMemberContributionCurrentYearTi());
        worksheet.setInterestOnCurrentYearTransferInCompanyContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnCompanyContributionCurrentYearTi());
        worksheet.setInterestOnCurrentYearTransferInVpfContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnVpfContributionCurrentYearTi());
        worksheet.setInterestOnCurrentYearTransferInTotalContribution(
                transferOut.getTransferOutFinalDetails().getTotalInterestCurrentYearTi());

        worksheet.setCurrentYearLoanWithdrawalMemberContribution(
                transferOut.getTransferOutFinalDetails().getMemberLoanWithDrawal());
        worksheet.setCurrentYearLoanWithdrawalCompanyContribution(
                transferOut.getTransferOutFinalDetails().getCompanyLoanWithDrawal());
        worksheet.setCurrentYearLoanWithdrawalVpfContribution(
                transferOut.getTransferOutFinalDetails().getVpfLoanWithDrawal());
        worksheet.setCurrentYearLoanWithdrawalTotalContribution(
                transferOut.getTransferOutFinalDetails().getTotalLoanWithDrawal());

        worksheet.setInterestOnCurrentYearLoanWithdrawalMemberContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnMemberLoanWithDrawal());
        worksheet.setInterestOnCurrentYearLoanWithdrawalCompanyContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnCompanyLoanWithDrawal());
        worksheet.setInterestOnCurrentYearLoanWithdrawalVpfContribution(
                transferOut.getTransferOutFinalDetails().getInterestOnVpfLoanWithDrawal());
        worksheet.setInterestOnCurrentYearLoanWithdrawalTotalContribution(
                transferOut.getTransferOutFinalDetails().getTotalInterestLoanWithDrawal());

        worksheet.setTotalMemberContribution(transferOut.getTransferOutFinalDetails().getTotalMemberContribution());
        worksheet.setTotalCompanyContribution(transferOut.getTransferOutFinalDetails().getTotalCompanyContribution());
        worksheet.setTotalVpfContribution(transferOut.getTransferOutFinalDetails().getTotalVpfContribution());

        worksheet.setTotalContribution(transferOut.getTransferOutFinalDetails().getTotalContribution());

        BigDecimal totalContribution = transferOut.getTransferOutFinalDetails().getTotalContribution();

        BigDecimal incomeTax = transferOut.getIncomeTax();
        BigDecimal eduCess = transferOut.getEduCess();

        worksheet.setIncomeTax(incomeTax);
        worksheet.setEduCess(eduCess);
        worksheet.setTds(incomeTax.add(eduCess));

        worksheet.setNetContribution(totalContribution.subtract(incomeTax).subtract(eduCess));

        worksheet.setDueDate(transferOut.getDueDate());

        worksheet.setProcessedOnDate(transferOut.getCreatedAt());

        List<Nominee> nominees = new ArrayList<>();

        for (com.coreintegra.pftrust.entities.pf.employee.Nominee n : transferOut.getEmployee().getNominees()) {

            Nominee nominee = new Nominee();

            nominee.setName(n.getName());
            nominee.setRelationship(n.getRelationship());
            nominee.setShare(n.getShare());

            nominees.add(nominee);
        }

        worksheet.setNominees(nominees);

        worksheet.setSettlementAnnexure(getSettlementAnnexure(transferOut));

        return worksheet;

    }


    public SettlementAnnexure getSettlementAnnexure(TransferOut transferOut) {

        Employee employee = transferOut.getEmployee();

        List<Contribution> contributions = contributionService
                .getContributions(employee, FinancialYearAndMonth.getFinancialYear(transferOut.getDueDate()));

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
                employee.getPfNumber(), employee.getUnitCode(), employee.getUnitCode(), transferOut.getInterestRate(),
                monthWiseMemberContributions, monthWiseCompanyContributions, monthWiseVpfContributions,
                monthWiseInterestOnMemberContributions, monthWiseInterestOnCompanyContributions,
                monthWiseInterestOnVpfContributions, monthWiseTotalContribution, monthWiseTotalInterest);

        settlementAnnexure.setDateOfSettlement(transferOut.getDueDate());

        return settlementAnnexure;

    }

    @Override
    public List<TransferOut> getTransferOuts(List<String> entityIds) {
        return transferOutRepository.findAllByEntityIdInAndIsActive(new HashSet<>(entityIds), true);
    }

    @Override
    public List<TransferOut> getTransferOutsForBankSheet(Date startDate, Date endDate, String bankId) {

        TransferOutStatus completed = transferOutStatusRepository.findByTitleAndIsActive("Payment Pending", true);

        Optional<PaymentMode> optionalPaymentMode = paymentModeRepository.findByModeAndIsActive("NEFT", true);

        if(optionalPaymentMode.isEmpty()) throw new EntityNotFoundException("Payment not found");

        Optional<Bank> optionalBank = bankRepository.findByEntityIdAndIsActive(bankId, true);

        if(optionalBank.isEmpty()) throw new EntityNotFoundException("Bank not found");

        return transferOutRepository.findAllByDueDateBetweenAndBankAndPaymentModeAndTransferOutStatusAndIsActive(
                startDate, new Date(endDate.getTime() + (1000 * 60 * 60 * 24)),
                optionalBank.get(), optionalPaymentMode.get(), completed, true);

    }

    @Override
    public List<TransferOut> updatePaymentDate(List<TransferOut> transferOuts, Date paymentDate) {

        transferOuts.forEach(transferOut -> transferOut.setPaymentDate(paymentDate));

        return transferOutRepository.saveAll(transferOuts);

    }

    @Override
    public List<String> getBankSheetLines(List<TransferOut> transferOuts, Date paymentDate) {

        DecimalFormat df = new DecimalFormat("####0.00");

        List<String> bankSheetList = new ArrayList<>();

        transferOuts.forEach(settlement -> {

            String modeCode = settlement.getIfscCode().substring(0, 4).equalsIgnoreCase("KKBK") ? "IFT" : "NEFT";

            String bankLine = "MMLSPF~SALPAY~" + modeCode + "~~" + DateFormatterUtil.formatDateWS(paymentDate) + "~" +
                    DateFormatterUtil.formatDateWS(paymentDate) + "~" + settlement.getAccountNumber() + "~" +
                    df.format(settlement.getTransferOutFinalDetails().getTotalNetCreditAmount()) + "~M~~" +
                    settlement.getPayeeName() + "~~" + settlement.getIfscCode() + "~" +
                    "~~~~~~~~~~Mahindra And Mahindra Ltd PF Trust~~~" + settlement.getEmployee().getPfNumber() +
                    "~" + settlement.getEmployee().getName() + "~~~~~~~~~~~~~~~~~~~~~\n";

            bankSheetList.add(bankLine);

        });

        return bankSheetList;

    }
    
    @Override
    public void updateTransferOutStatus(TransferOut transferOut) {
    	transferOutRepository.save(transferOut);
    }
}
