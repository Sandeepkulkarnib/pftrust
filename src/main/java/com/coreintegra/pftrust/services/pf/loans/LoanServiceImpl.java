package com.coreintegra.pftrust.services.pf.loans;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.loan.*;
import com.coreintegra.pftrust.entities.base.EmailAttachment;
import com.coreintegra.pftrust.entities.pf.*;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.TotalContributionDTO;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.loan.*;
import com.coreintegra.pftrust.entities.pf.loan.dtos.LoanDetailsDTO;
import com.coreintegra.pftrust.entities.pf.loan.dtos.StatusDTO;
import com.coreintegra.pftrust.entities.pf.loan.dtos.TypeDTO;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.TransferInFullDetailsDTO;
import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.NotificationEmailDesignRepository;
import com.coreintegra.pftrust.repositories.pf.department.BankRepository;
import com.coreintegra.pftrust.repositories.pf.department.DocumentRepository;
import com.coreintegra.pftrust.repositories.pf.department.PaymentModeRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.loan.*;
import com.coreintegra.pftrust.searchutil.SearchLoanSpecification;
import com.coreintegra.pftrust.searchutil.SearchSpecificationForEmployee;
import com.coreintegra.pftrust.services.email.EmailService;
import com.coreintegra.pftrust.services.pf.contribution.ContributionService;
import com.coreintegra.pftrust.services.pf.department.FinancialYearAndMonthService;
import com.coreintegra.pftrust.services.pf.department.UnitCodeService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final Logger logger = LoggerFactory.getLogger(LoanService.class);

    private final EmployeeRepository employeeRepository;
    private final LoanRepository loanRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final LoanWithDrawalsFinalDetailsRepository loanWithDrawalsFinalDetailsRepository;
    private final LoanStatusRepository loanStatusRepository;

    private final UnitCodeService unitCodeService;
    private final FinancialYearAndMonthService financialYearAndMonthService;
    private final EmployeeService employeeService;
    private final ContributionService contributionService;
    private final LoanEmailStatusRepository loanEmailStatusRepository;
    private final EmailService emailService;
    private final TransferInService transferInService;
    private final LoanDocumentRepository loanDocumentRepository;
    private final DocumentRepository documentRepository;

    private final SearchLoanSpecification searchLoanSpecification;
    private final NotificationEmailDesignRepository notificationEmailDesignRepository;
    private final BankRepository bankRepository;
    private final PaymentModeRepository paymentModeRepository;


    public LoanServiceImpl(EmployeeRepository employeeRepository, LoanRepository loanRepository,
                           LoanTypeRepository loanTypeRepository,
                           LoanWithDrawalsFinalDetailsRepository loanWithDrawalsFinalDetailsRepository,
                           LoanStatusRepository loanStatusRepository, UnitCodeService unitCodeService,
                           FinancialYearAndMonthService financialYearAndMonthService,
                           EmployeeService employeeService, ContributionService contributionService,
                           LoanEmailStatusRepository loanEmailStatusRepository,
                           EmailService emailService, TransferInService transferInService,
                           LoanDocumentRepository loanDocumentRepository,
                           DocumentRepository documentRepository,
                           SearchLoanSpecification searchLoanSpecification,
                           NotificationEmailDesignRepository notificationEmailDesignRepository,
                           BankRepository bankRepository, PaymentModeRepository paymentModeRepository) {
        this.employeeRepository = employeeRepository;
        this.loanRepository = loanRepository;
        this.loanTypeRepository = loanTypeRepository;
        this.loanWithDrawalsFinalDetailsRepository = loanWithDrawalsFinalDetailsRepository;
        this.loanStatusRepository = loanStatusRepository;
        this.unitCodeService = unitCodeService;
        this.financialYearAndMonthService = financialYearAndMonthService;
        this.employeeService = employeeService;
        this.contributionService = contributionService;
        this.loanEmailStatusRepository = loanEmailStatusRepository;
        this.emailService = emailService;
        this.transferInService = transferInService;
        this.loanDocumentRepository = loanDocumentRepository;
        this.documentRepository = documentRepository;
        this.searchLoanSpecification = searchLoanSpecification;
        this.notificationEmailDesignRepository = notificationEmailDesignRepository;
        this.bankRepository = bankRepository;
        this.paymentModeRepository = paymentModeRepository;
    }

    @Transactional
    @Override
    @Async
    @ApplyTenantFilter
    public CompletableFuture<String> saveFetchedLoanAsync(Loan loan, String pernNumber, LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails) {

        Optional<Employee> optionalEmployee = employeeRepository.findByPernNumberAndIsActive(pernNumber, true);

        if (optionalEmployee.isEmpty()) {
            throw new EntityNotFoundException("no employee found.");
        }

        Employee employee = optionalEmployee.get();

        Optional<LoanType> loanType = loanTypeRepository.findByCode(loan.getLoanType().getCode());

        loan.setEmployee(employee);

        loan.setLoanType(loanType.get());

        loan.setBank(getBank(loan.getBank().getName()));

        Loan save = loanRepository.save(loan);

        loanWithDrawalsFinalDetails.setLoan(save);
        loanWithDrawalsFinalDetails.setEmployee(employee);

        loanWithDrawalsFinalDetailsRepository.save(loanWithDrawalsFinalDetails);

        logger.info("loan saved successfully -> {}", save.getId());

        return CompletableFuture.completedFuture("done");
    }

    @Override
    public Loan save(Loan loan) {
        loanWithDrawalsFinalDetailsRepository.save(loan.getLoanWithDrawalsFinalDetails());
        return loanRepository.save(loan);
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
    public Page<Loan> getLoans(String search, Integer size, Integer page) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,
                "paymentDate"));

        Specification<Loan> specification = new SearchSpecificationForEmployee<Loan>()
                .getSearchSpecificationForEmployee(search);

        return loanRepository.findAll(specification, pageable);

    }

    @Override
    public EssentialsDTO getLoanEssentials() {

        List<LoanType> loanTypes = loanTypeRepository.findAllByIsActive(true);

        List<TypeDTO> typeDTOList = loanTypes.stream()
                .map(loanType -> new TypeDTO(loanType.getEntityId(), loanType.getTitle()))
                .collect(Collectors.toList());

        List<StatusDTO> statusDTOList = loanStatusRepository.findAllByIsActive(true).stream()
                .map(loanStatus -> new StatusDTO(loanStatus.getEntityId(), loanStatus.getTitle()))
                .collect(Collectors.toList());

        List<UnitCode> unitCodes = unitCodeService.get();

        List<Integer> years = financialYearAndMonthService.getYears();

        Map<Integer, String> months = financialYearAndMonthService.getMonths();

        return new EssentialsDTO(typeDTOList, statusDTOList, unitCodes, years, months);

    }

    @Override
    public List<Loan> getLoans(Employee employee) {
        return loanRepository.findAll(searchLoanSpecification.employeeSpecification(employee));
    }

    @Override
    public List<Loan> getLoans(Employee employee, Integer year) {

        Specification<Loan> loanSpecification = searchLoanSpecification.employeeSpecification(employee)
                .and(searchLoanSpecification.yearSpecification(year));

        return loanRepository.findAll(loanSpecification);
    }

    @Override
    public List<Loan> getLoans(Employee employee, Integer year, Integer month) {

        Specification<Loan> loanSpecification = searchLoanSpecification.employeeSpecification(employee)
                .and(searchLoanSpecification.yearSpecification(year))
                .and(searchLoanSpecification.monthSpecification(month));

        return loanRepository.findAll(loanSpecification);
    }




    @Override
    public List<LoanDetailsDTO> getLoanDetails(Employee employee) {
        return getLoans(employee).stream()
                .map(loan -> {

                    LoanType loanType = loan.getLoanType();
                    LoanStatus loanStatus = loan.getLoanStatus();

                    LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

                    return new LoanDetailsDTO(loan.getEntityId(), loanType.getTitle(), loan.getLoanApplicationDate(),
                            loan.getAppliedAmount(), loanStatus.getTitle(), loan.getLoanApprovalDate(),
                            loan.getEligibleAmount(), loan.getLoanDisbursalDate(), loan.getDocumentNo(),
                            loanWithDrawalsFinalDetails.getYear(), loanWithDrawalsFinalDetails.getMonth());

                }).collect(Collectors.toList());
    }

    @Override
    public LoanWithDrawalsFinalDetails getTotalLoanWithDrawals(Employee employee, Integer year) {
        List<Loan> completedLoans = getCompletedLoans(employee, year);

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        for (Loan loan:completedLoans) {
            memberContribution = memberContribution.add(DataUtil.avoidNull(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnMemContribution()));
            companyContribution = companyContribution.add(DataUtil.avoidNull(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnCompanyContribution()));
            vpfContribution = vpfContribution.add(DataUtil.avoidNull(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnVPFContribution()));
        }

        return new LoanWithDrawalsFinalDetails(memberContribution, companyContribution, vpfContribution);

    }

    @Override
    public Loan getLoan(String entityId) {
        Optional<Loan> optionalLoan = loanRepository.findByEntityIdAndIsActive(entityId, true);
        if(optionalLoan.isEmpty()) throw new EntityNotFoundException("loan not found");
        return optionalLoan.get();
    }

    @Override
    public List<Loan> getCompletedLoans(Employee employee, Integer year) {

        Specification<Loan> loanSpecification = searchLoanSpecification.employeeSpecification(employee)
                .and(searchLoanSpecification.yearSpecification(year))
                .and(searchLoanSpecification.statusSpecification("A"));

        return loanRepository.findAll(loanSpecification, Sort.by(Sort.Direction.ASC, "paymentDate"));
    }

    @Override
    public List<Loan> getCompletedLoans(Employee employee) {
        Specification<Loan> loanSpecification = searchLoanSpecification.employeeSpecification(employee)
                .and(searchLoanSpecification.statusSpecification("A"));

        return loanRepository.findAll(loanSpecification, Sort.by(Sort.Direction.ASC, "paymentDate"));
    }


    @Override
    public void savePaymentDetails(Loan loan, Bank bank, PaymentMode paymentMode, String referenceNumber,
                                   Date approvalDate, Date paymentDate) {

        LoanStatus approved = loanStatusRepository.findByTitleAndIsActive("Approved", true);

        loan.setBank(bank);
        loan.setPaymentMode(paymentMode);
        loan.setChequeNumber(referenceNumber);
        loan.setLoanApprovalDate(approvalDate);
        loan.setPaymentDate(paymentDate);

        loan.setLoanDisbursalDate(loan.getPaymentDate());
        loan.setAmountDisbursed(loan.getEligibleAmount());

        loan.setLoanStatus(approved);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(2L);

        loan.setSapStatus(sapStatus);

        loanRepository.save(loan);

    }

    @Override
    public void rejectLoan(Loan loan) {

        LoanStatus rejected = loanStatusRepository.findByTitleAndIsActive("Rejected", true);

        loan.setLoanStatus(rejected);

        loanRepository.save(loan);

    }

    @Override
    public LoanWorkSheet getWorkSheet(Loan loan) throws ParseException {

        LoanWorkSheet loanWorkSheet = new LoanWorkSheet();

        loanWorkSheet.setLoanCode(loan.getLoanType().getCode());
        loanWorkSheet.setLoanType(loan.getLoanType().getTitle());

        loanWorkSheet.setName(loan.getEmployee().getName());
        loanWorkSheet.setTokenNumber(loan.getEmployee().getTokenNumber());
        loanWorkSheet.setPfNumber(loan.getEmployee().getPfNumber());
        loanWorkSheet.setUnitCode(loan.getEmployee().getUnitCode());
        long ids = loan.getId();

        int year = FinancialYearAndMonth.getFinancialYear(new Date(loan.getCreatedAtTimestamp()));

        String applicationNo = year + "" + ids;
        loanWorkSheet.setApplicationNumber(applicationNo);

        loanWorkSheet.setDateOfBirth(loan.getEmployee().getDateOfBirth());
        loanWorkSheet.setDateOfJoiningPf(loan.getEmployee().getDateOfJoiningPF());
        loanWorkSheet.setDateOfJoining(loan.getEmployee().getDateOfJoining());

        loanWorkSheet.setApplicationDate(new Date(loan.getCreatedAtTimestamp()));

        loanWorkSheet.setLastRecoveryDate(loan.getLastRecoveryDate());

        loanWorkSheet.setPfBase(loan.getPfBase());

        loanWorkSheet.setLoanAmountApplied(loan.getAppliedAmount());
        loanWorkSheet.setCostInvolved(loan.getTotalCost());
        loanWorkSheet.setYearOpeningDate(FinancialYearAndMonth.getYearOpeningFromDate(loan.getLoanApprovalDate() != null ? loan.getLoanApprovalDate() : loan.getCreatedAt()));


        loanWorkSheet.setYearOpeningMemberContribution(loan.getLoanWithDrawalsFinalDetails().getMemberContributionYearOpening());
        loanWorkSheet.setYearOpeningCompanyContribution(loan.getLoanWithDrawalsFinalDetails().getCompanyContributionYearOpening());
        loanWorkSheet.setYearOpeningVpfContribution(loan.getLoanWithDrawalsFinalDetails().getVpfContributionYearOpening());
        loanWorkSheet.setYearOpeningTotalContribution(loan.getLoanWithDrawalsFinalDetails().getTotalContributionYearOpening());

        loanWorkSheet.setCurrentYearMemberContribution(loan.getLoanWithDrawalsFinalDetails().getMemberContributionCurrentYear());
        loanWorkSheet.setCurrentYearCompanyContribution(loan.getLoanWithDrawalsFinalDetails().getCompanyContributionCurrentYear());
        loanWorkSheet.setCurrentYearVpfContribution(loan.getLoanWithDrawalsFinalDetails().getVpfContributionCurrentYear());
        loanWorkSheet.setCurrentYearTotalContribution(loan.getLoanWithDrawalsFinalDetails().getTotalContributionCurrentYear());

        loanWorkSheet.setCurrentYearTiMemberContribution(loan.getLoanWithDrawalsFinalDetails().getMemberContributionCurrentYearTi());
        loanWorkSheet.setCurrentYearTiCompanyContribution(loan.getLoanWithDrawalsFinalDetails().getCompanyContributionCurrentYearTi());
        loanWorkSheet.setCurrentYearTiVpfContribution(loan.getLoanWithDrawalsFinalDetails().getVpfContributionCurrentYearTi());
        loanWorkSheet.setCurrentYearTiTotalContribution(loan.getLoanWithDrawalsFinalDetails().getTotalContributionCurrentYearTi());

        loanWorkSheet.setCurrentYearLwMemberContribution(loan.getLoanWithDrawalsFinalDetails().getCurrentYearMemberLoanWithDrawal());
        loanWorkSheet.setCurrentYearLwCompanyContribution(loan.getLoanWithDrawalsFinalDetails().getCurrentYearCompanyLoanWithDrawal());
        loanWorkSheet.setCurrentYearLwVpfContribution(loan.getLoanWithDrawalsFinalDetails().getCurrentYearVpfLoanWithDrawal());
        loanWorkSheet.setCurrentYearLwTotalContribution(loan.getLoanWithDrawalsFinalDetails().getCurrentYearTotalLoanWithDrawal());

        loanWorkSheet.setTotalMemberContribution(loan.getLoanWithDrawalsFinalDetails().getTotalMemberContrbution());
        loanWorkSheet.setTotalCompanyContribution(loan.getLoanWithDrawalsFinalDetails().getTotalCompanyContrbution());
        loanWorkSheet.setTotalVpfContribution(loan.getLoanWithDrawalsFinalDetails().getTotalVpfContrbution());
        loanWorkSheet.setTotalContribution(loan.getLoanWithDrawalsFinalDetails().getTotal());

        loanWorkSheet.setLoanAmountOnMemContribution(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnMemContribution());
        loanWorkSheet.setLoanAmountOnCompanyContribution(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnCompanyContribution());
        loanWorkSheet.setLoanAmountOnVPFContribution(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnVPFContribution());
        loanWorkSheet.setTotalLoanAmount(loan.getLoanWithDrawalsFinalDetails().getTotalLoanWithDrawal());

        Integer membershipYears = employeeService.getMembershipYears(loan.getEmployee());

        loanWorkSheet.setNoOfYearsOfMembership(membershipYears);

        if (loan.getLoanType().getCode().equalsIgnoreCase("99")) {
            loanWorkSheet.setMinimumRequiredYears(59);
        } else {
            loanWorkSheet.setMinimumRequiredYears(loan.getLoanType().getMinimumMembershipTenureInMonths() / 12);
        }

        loanWorkSheet.setActualNoOfYears(membershipYears);

        loanWorkSheet.setSalaryMonths(loan.getLoanType().getLoanAmount().getPfSalary());
        Integer pfBalance = loan.getLoanType().getLoanAmount().getTotalPfBalance() == null ? 0 :loan.getLoanType().getLoanAmount().getTotalPfBalance().intValue();
        loanWorkSheet.setTotalPfBalance(pfBalance);

        loanWorkSheet.setLoanAmountBasedOnSalary(loan.getLoanAmountOnPfBaseSalary());
        loanWorkSheet.setLoanAmountPF(loan.getLoanAmountOnPfBalance());
        loanWorkSheet.setEligibleAmount(loan.getEligibleAmount());

        Integer marriageLoans = countLoans(loan.getEmployee(), getMarriageLoan());
        Integer educationLoans = countLoans(loan.getEmployee(), getEducationLoan());

        loanWorkSheet.setCount(marriageLoans + educationLoans);

        loanWorkSheet.setCreatedBy(loan.getLoanCreatedBy());
        loanWorkSheet.setAprovedOnDate(loan.getUpdatedAt());

        loanWorkSheet.setDepartmentName("");

        LoanHistorySheet loanHistorySheet = getLoanHistorySheet(loan.getEmployee());

        loanWorkSheet.setLoanHistorySheet(loanHistorySheet);

        return loanWorkSheet;
    }

    @Override
    public LoanHistorySheet getLoanHistorySheet(Employee employee) {

        List<Loan> completedLoans = getCompletedLoans(employee);

        LoanHistorySheet loanHistorySheet = new LoanHistorySheet();

        loanHistorySheet.setName(employee.getName());
        loanHistorySheet.setTokenNumber(employee.getTokenNumber());
        loanHistorySheet.setPfNumber(employee.getPfNumber());
        loanHistorySheet.setUnitCode(employee.getUnitCode());
        loanHistorySheet.setDepartmentName("");
        loanHistorySheet.setApplicationNumber("");
        loanHistorySheet.setLastRecoveryDate(contributionService.getLastRecoveryDate(employee));
        loanHistorySheet.setDateOfBirth(employee.getDateOfBirth());
        loanHistorySheet.setDateOfJoiningPf(employee.getDateOfJoiningPF());

        List<PreviousCompany> previousCompanies = employee.getPreviousCompanies();

        if(!previousCompanies.isEmpty()){
            loanHistorySheet.setDateOfJoiningPrior(previousCompanies.get(0).getDateOfJoining());
        }

        loanHistorySheet.setPfBase(contributionService.getLatestPfBase(employee));

        List<LoanHistory> loanHistoryList = completedLoans.stream().map(loan -> new LoanHistory(loan.getLoanType().getCode(),
                loan.getLoanType().getTitle(),
                loan.getCreatedAtTimestamp(), loan.getAmountDisbursed())).collect(Collectors.toList());

        loanHistorySheet.setLoanHistories(loanHistoryList);

        return loanHistorySheet;
    }

    @Override
    public LoanHistorySheet getLoanHistorySheet(Loan loan) {

        Employee employee = loan.getEmployee();

        List<Loan> completedLoans = getCompletedLoans(employee);

        LoanHistorySheet loanHistorySheet = new LoanHistorySheet();
        loanHistorySheet.setDateOfBirth(employee.getDateOfBirth());
        loanHistorySheet.setDateOfJoiningPf(employee.getDateOfJoiningPF());
        loanHistorySheet.setLastRecoveryDate(loan.getLastRecoveryDate());
        loanHistorySheet.setName(employee.getName());
        loanHistorySheet.setPfBase(loan.getPfBase());
        loanHistorySheet.setPfNumber(employee.getPfNumber());
        loanHistorySheet.setTokenNumber(employee.getTokenNumber());
        loanHistorySheet.setUnitCode(employee.getUnitCode());

        loanHistorySheet.setApplicationNumber(loan.getId().toString());

        loanHistorySheet.setApplicationDate(new Date(loan.getCreatedAtTimestamp()));

        List<PreviousCompany> previousCompanies = employee.getPreviousCompanies();

        if(!previousCompanies.isEmpty()){
            loanHistorySheet.setDateOfJoiningPrior(previousCompanies.get(0).getDateOfJoining());
        }

        List<LoanHistory> loanHistoryList = completedLoans.stream().map(loan1 -> new LoanHistory(loan1.getLoanType().getCode(),
                loan1.getLoanType().getTitle(),
                loan1.getCreatedAtTimestamp(), loan1.getAmountDisbursed())).collect(Collectors.toList());

        loanHistorySheet.setLoanHistories(loanHistoryList);

        return loanHistorySheet;
    }

    private Integer countLoans(Employee employee, LoanType loanType){
        LoanStatus approved = loanStatusRepository.findByTitleAndIsActive("Approved", true);
        return loanRepository.countLoansByEmployeeAndLoanTypeAndLoanStatusAndIsActive(employee, loanType, approved, true);
    }

    private LoanType getMarriageLoan(){
        return getLoanTypeByCode("03");
    }

    private LoanType getEducationLoan(){
        return getLoanTypeByCode("08");
    }

    @Override
    public LoanType getLoanTypeByCode(String code){
        Optional<LoanType> optionalLoanType = loanTypeRepository.findByCode(code);

        if(optionalLoanType.isEmpty()) throw new EntityNotFoundException("Loan type not found for given code -> " + code);

        return optionalLoanType.get();
    }

    @Override
    public LoanEmailStatus createEmailStatus(Loan loan) {

        String emailId = loan.getAltEmail();

        if(emailId == null){
            emailId = loan.getEmployee().getEmail();
        }

        LoanEmailStatus loanEmailStatus = new LoanEmailStatus(emailId, loan.getEmployee().getName(),
                loan.getEmployee().getUnitCode(), loan.getEmployee().getPfNumber(),
                loan.getEmployee().getPernNumber(), loan.getEmployeeAccountNumber(),
                loan.getEmployeeBank(), loan.getPaymentDate(),
                loan.getAmountDisbursed(),
                loan, false);

        return loanEmailStatusRepository.save(loanEmailStatus);
    }

    @Override
    public void updateEmailStatus(LoanEmailStatus loanEmailStatus) {
        loanEmailStatus.setSent(true);
        loanEmailStatusRepository.save(loanEmailStatus);
    }

    @Override
    public void sendConfirmationEmail(List<Pair<LoanEmailStatus, List<EmailAttachment>>> loanEmailStatusList) {
        for (Pair<LoanEmailStatus, List<EmailAttachment>> emailStatusPair:loanEmailStatusList) {
            sendConfirmationEmail(emailStatusPair.getFirst(), emailStatusPair.getSecond());
        }
    }

    @Override
    public void sendConfirmationEmail(LoanEmailStatus loanEmailStatus, List<EmailAttachment> emailAttachments) {
        sendConfirmationEmail(loanEmailStatus.getEmailId(), loanEmailStatus.getName(),
                loanEmailStatus.getAccountNumber(), loanEmailStatus.getEmployeeBank(),
                loanEmailStatus.getPaymentDate(), loanEmailStatus.getNetCredit(), emailAttachments);
    }

    @Override
    public void sendConfirmationEmail(String emailId, String employeeName, String accountNumber, String bankName,
                                      Date paymentDate, BigDecimal amount, List<EmailAttachment> emailAttachments) {

        String subject = " Subject: PF Notifications";

        Optional<NotificationEmailDesign> optional = notificationEmailDesignRepository
                .findByDocumentType(NotificationEmailDesign.LOAN_COMPLETION);

        if(optional.isEmpty()) throw new EntityNotFoundException("Email Design Not Found");

        NotificationEmailDesign notificationEmailDesign = optional.get();

        String emailBody = notificationEmailDesign.getDocument().replace("{{name}}", employeeName)
                .replace("{{accountNumber}}", accountNumber)
                .replace("{{netCredit}}", EnglishNumberToWords.convert(amount.longValue()))
                .replace("{{paymentDate}}", DateFormatterUtil.formatDateR(paymentDate));

        emailService.sendMessageWithAttachment(emailId, subject, emailBody, emailAttachments);

    }


    @Override
    public LoanReceipt getLoanReceipt(Loan loan) {

        Employee employee = loan.getEmployee();

        LoanReceipt loanReceipt = new LoanReceipt();

        loanReceipt.setDate(new Date());

        long ids = loan.getId();
        Date date = new Date(loan.getCreatedAtTimestamp());
        int year = FinancialYearAndMonth.getFinancialYear(date);

        String applicationNo = year + "" + ids;
        loanReceipt.setApplicationNumber(applicationNo);

        loanReceipt.setAccountNumber(loan.getEmployeeAccountNumber());
        loanReceipt.setBankName(loan.getEmployeeBank());

        loanReceipt.setPaymentDate(loan.getPaymentDate());
        loanReceipt.setPaymentMethod(loan.getPaymentMode() == null ? "" :loan.getPaymentMode().getMode());
        loanReceipt.setPaymentBank(loan.getBank() == null ? "" :loan.getBank().getName() );
        loanReceipt.setAmount(loan.getEligibleAmount());

        loanReceipt.setName(employee.getName());
        loanReceipt.setDeptCode("");
        loanReceipt.setPfNumber(employee.getPfNumber());
        loanReceipt.setTokenNumber(employee.getTokenNumber());
        loanReceipt.setApprovalDate(loan.getLoanApprovalDate());

        return loanReceipt;
    }

    @Override
    public List<LoanType> getEligibleLoanTypes(Employee employee) throws JPAException {

        if (employee.getContributionStatus().getSymbol().equals("S")) {
            throw new JPAException("Account with Pern. Number: " + employee.getPernNumber() + " has been already settled.");
        }

        try {
            Contribution openingBalance = contributionService.getContribution(employee, FinancialYearAndMonth.getFinancialYear(new Date()), 0);
        }catch (EntityNotFoundException exception){
            throw new JPAException("Please perform year end process for the employee before proceeding.");
        }

        LoanStatus pending = loanStatusRepository.findByTitleAndIsActive("Pending", true);

        Integer pendingLoanCount = loanRepository.countLoansByEmployeeAndLoanStatusAndIsActive(employee, pending, true);

        if (pendingLoanCount > 0) {
            throw new JPAException(
                    "A pending loan application already exists for the account with Pern. Number: "
                            + employee.getPernNumber());
        }

        List<LoanType> loanTypeList = loanTypeRepository.findAll();

        List<LoanType> eligibleLoanTypes = new ArrayList<>();

        for (LoanType loanType : loanTypeList) {

            if (checkEligibilityForMembershipTenure(employee, loanType) && checkEligibilityByRetirementDate(employee, loanType)
            && checkEligibilityByMaxNoOfWithdrawals(employee, loanType) && checkEligibilityByNextEligibility(employee,loanType)
            && checkEligibilityByCombinedEligibility(employee,loanType)) {
                System.out.println("Added");
                eligibleLoanTypes.add(loanType);
            }
        }

        return eligibleLoanTypes;
    }


    @Override
    public List<LoanType> getEligibleLoanTypes(Loan loan) throws JPAException {

        List<LoanType> loanTypeList = loanTypeRepository.findAll();

        List<LoanType> eligibleLoanTypes = new ArrayList<>();

        for (LoanType loanType : loanTypeList) {
            if (checkEligibilityForMembershipTenure(loan.getEmployee(), loanType)) {
                eligibleLoanTypes.add(loanType);
            }
        }

        return checkForSpecialConditions(loan.getEmployee(), eligibleLoanTypes);
    }


    // check eligibility conditions in terms of membership
    private Boolean checkEligibilityForMembershipTenure(Employee employee, LoanType loanType) {

        Date date = employeeService.getDateOfJoiningPrior(employee);

        if (date == null)
            date = employee.getDateOfJoiningPF();

        long months = (FinancialYearAndMonth.getDateDiff(date) / 365) * 12;

        return months >= loanType.getMinimumMembershipTenureInMonths();

    }

    //Eligibility check for maximum withdrawal
    public Boolean checkEligibilityByMaxNoOfWithdrawals(Employee employee, LoanType loanType) {

        LoanStatus approved = loanStatusRepository.findByTitleAndIsActive("Approved", true);

        //Appropved loanStatus  added
        List<Loan> loans = loanRepository
                .findAllByEmployeeAndLoanTypeAndLoanStatusAndIsActive(employee,loanType,approved,true);

        int withdrawalCount = loans.size();

        int maxWithdrawalCount = loanType.getMaximumNumberOfWithdrawals();

        Boolean status = maxWithdrawalCount > withdrawalCount;

        System.out.println("############### output : " + status);

        return status;

    }

    // Eligibility check by Next Eligibility
     public Boolean checkEligibilityByNextEligibility(Employee employee, LoanType loanType) {

         LoanStatus approved = loanStatusRepository.findByTitleAndIsActive("Approved", true);

         List<Loan> loan = loanRepository
                .findAllByEmployeeAndLoanTypeAndLoanStatusAndIsActiveOrderById
                        (employee.getId().intValue(),loanType.getId().intValue(),approved.getId().intValue());

         if(loan.size() < 1){
             return true;
         }

         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
         LocalDateTime now = LocalDateTime.now();
         String date1 = dtf.format(now).toString();

         Date date = loan.get(0).getLoanDisbursalDate();

         Calendar dateDetails = FinancialYearAndMonth.getDateDetails(date);

         int year = dateDetails.get(Calendar.YEAR);


         System.out.println("Date : "+date);

         if ( date != null){

             date1 = date1.substring(0, 4);
             int previousYear = year;
             int currentYear = Integer.parseInt(date1);

            if((currentYear-previousYear) >= loanType.getNextEligibility()){
                return true;
            }
         }

        return false;
     }

     // Eligibility Retirement Date
     public Boolean checkEligibilityByRetirementDate(Employee employee, LoanType loanType){

        LoanType loanType1 = loanTypeRepository.findByCodeAndIsActive(loanType.getCode(),true);

        int age = employee.getEmployeeAge();

        int difference = 60 - age;

        Boolean status = false ;

        if(loanType1.getFromRetirementDate() == 0) {
            status = true;
        }else{
            if (difference <= loanType1.getFromRetirementDate()) {
                status = true;
            }
        }

        return status;
    }

     // Combined Eligibility
     public Boolean checkEligibilityByCombinedEligibility(Employee employee, LoanType loanType){
    	 
    	LoanType loanType1 = loanTypeRepository.findByCodeAndIsActive(loanType.getCode(),true);
    	
    	if(loanType1.getCombinedEligibility() == null || loanType1.getCombinedEligibility().equals("")) {
    		return true;
    	}
    	
    	String[] loanTypeId = loanType1.getCombinedEligibility().split(",");
    	
    	LoanStatus approved = loanStatusRepository.findByTitleAndIsActive("Approved", true);
    	
    	Integer maxCount = 0;
    	
    	for(int i=0;i<loanTypeId.length;i++) {
    		
    		LoanType loanType2 = loanTypeRepository.findByCodeAndIsActive(loanTypeId[i], true);
    		
    		List<Loan> loans = loanRepository
                    .findAllByEmployeeAndLoanTypeAndLoanStatusAndIsActive(employee,loanType2,approved,true);
    		
    		maxCount += loans.size();
    	}

        int maxWithdrawalCount = loanType.getMaximumNumberOfWithdrawals();

        Boolean status = maxWithdrawalCount > maxCount;

        return status;
    }

    private List<LoanType> checkForSpecialConditions(Employee employee, List<LoanType> eligibleLoanTypes) {

        LoanStatus approved = loanStatusRepository.findByTitleAndIsActive("Approved", true);

        List<LoanType> finalEligibleLoanTypes = new ArrayList<>();

        for (LoanType loanType : eligibleLoanTypes) {

            if (loanType.getCode().equals("04")) {
                finalEligibleLoanTypes.add(loanType);
                continue;
            }

            if (loanType.getCode().equals("02") || (loanType.getCode().equals("14"))) {
                if (checkEligibilityFor02A(employee, loanType, approved)) {
                    finalEligibleLoanTypes.add(loanType);
                }
                continue;
            }

            if (loanType.getCode().equals("99")) {
                if (checkEligibilityFor99E(employee, loanType, approved)) {
                    finalEligibleLoanTypes.add(loanType);
                }
                continue;
            }

            if (loanType.getCode().equals("03") || loanType.getCode().equals("08")) {
                if (checkEligibilityFor03B(employee, approved)) {
                    finalEligibleLoanTypes.add(loanType);
                }
                continue;
            }

            if (checkEligibilityInMaxWithdrawals(employee, loanType, approved)) {
                finalEligibleLoanTypes.add(loanType);
            }

        }

        return finalEligibleLoanTypes;

    }

    private Boolean checkEligibilityFor02A(Employee employee, LoanType loanType, LoanStatus loanStatus) {

        List<Loan> loans = loanRepository.findAllByEmployeeAndLoanTypeAndLoanStatusAndIsActive(employee, loanType,
                loanStatus,true);

        if (loans.size() == 0) {
            return true;
        }

        Loan loan = loans.get(loans.size() - 1);

        long year = FinancialYearAndMonth.getDateDiff(new java.sql.Date(loan.getUpdatedAtTimestamp())) / 365;

        return year >= 10;

    }


    private Boolean checkEligibilityFor99E(Employee employee, LoanType loanType, LoanStatus loanStatus) {

        List<Loan> loans = loanRepository.findAllByEmployeeAndLoanTypeAndLoanStatusAndIsActive(employee, loanType,
                loanStatus, true);

        long year = FinancialYearAndMonth.getDateDiff(employee.getDateOfBirth()) / 365;

        return year >= 59 && loans.size() == 0;

    }

    private Boolean checkEligibilityFor03B(Employee employee, LoanStatus loanStatus) {

        LoanType marriageLoan = getMarriageLoan();

        Integer count = loanRepository.countLoansByEmployeeAndLoanTypeAndLoanStatusAndIsActive(employee, marriageLoan,
                loanStatus, true);

        return count < 3;

    }

    private Boolean checkEligibilityInMaxWithdrawals(Employee employee, LoanType loanType, LoanStatus loanStatus) {

        List<Loan> loans = loanRepository.findAllByEmployeeAndLoanTypeAndLoanStatusAndIsActive(employee, loanType,
                loanStatus, true);

        if (loans == null || loans.size() == 0)
            return true;

        return loans.size() != loanType.getMaximumNumberOfWithdrawals();

    }

    @Override
    public void checkLoanTypeEligibility(Employee employee, LoanType loanType) throws JPAException{

        List<LoanType> eligibleLoanTypes = getEligibleLoanTypes(employee);

        for (LoanType eligibleLoanType:eligibleLoanTypes) {
            if(eligibleLoanType.getCode().equalsIgnoreCase(loanType.getCode())){
                return;
            }
        }

        throw new JPAException("Employee is not eligible for Selected Loan Type");

    }

    @Override
    public LoanType getLoanType(String entityId) {

        Optional<LoanType> optionalLoanType = loanTypeRepository.findByEntityIdAndIsActive(entityId, true);

        if(optionalLoanType.isEmpty()) throw new EntityNotFoundException("Loan Type not found");

        return optionalLoanType.get();
    }


    @Override
    public JSONObject getEligibleLoanAmount(Employee employee, BigDecimal appliedAmount, BigDecimal totalCost, LoanType loanType) {

        BigDecimal latestPfBase = contributionService.getLatestPfBase(employee);

        JSONObject amounts = getEligibleAmount(employee, latestPfBase, loanType, appliedAmount, totalCost);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amounts",amounts);

//        LoanAmount loanAmount = loanType.getLoanAmount();

        JSONObject loanAmountCondition = new JSONObject();
        loanAmountCondition.put("pfSalary", loanType.getPfBaseSalaryInMonth());
        loanAmountCondition.put("totalPfBalance", loanType.getCompanyBalanceInPercentage());
        loanAmountCondition.put("totalCost", loanType.getTotalCostInPercentage());
        loanAmountCondition.put("appliedAmount",loanType.getAppliedAmountInPercentage());
        loanAmountCondition.put("ownAccountPfBalance", loanType.getCompanyBalanceInPercentage()<=0 ? 0:loanType.getMemberBalanceInPercentage());

        jsonObject.put("loanAmountConditions", loanAmountCondition);

        return jsonObject;
    }

    private JSONObject getEligibleAmount(Employee employee, BigDecimal pfBase, LoanType loanType,
                                                      BigDecimal appliedAmount, BigDecimal totalCost) {

        LoanAmount loanAmount = loanType.getLoanAmount();

        TotalContributionDTO totalContribution = contributionService.getTotalContribution(employee,
                FinancialYearAndMonth.getFinancialYear(new Date()));

        TransferInFinalDetails totalTransferInContribution = transferInService
                .getTotalTransferInContribution(employee, FinancialYearAndMonth.getFinancialYear(new Date()));

        LoanWithDrawalsFinalDetails totalLoanWithDrawals = getTotalLoanWithDrawals(employee, FinancialYearAndMonth.getFinancialYear(new Date()));

        BigDecimal eligibleAmount = new BigDecimal(0);

        BigDecimal loanAmountOnBaseSalary = calculateLoanAmountBasedPfSalary(pfBase, loanType);

        BigDecimal loanAmountOnPfBalance = calculateLoanAmountBasedTotalPfBalance(totalContribution,
                totalTransferInContribution, totalLoanWithDrawals, loanAmount,loanType);

        BigDecimal loanAmountBasedOnTotalCost = new BigDecimal(0);
        if(loanType.getTotalCostInPercentage() != 0) {
            loanAmountBasedOnTotalCost = appliedAmount.divide(new BigDecimal(100));
            loanAmountBasedOnTotalCost = loanAmountBasedOnTotalCost.multiply(new BigDecimal(loanType.getTotalCostInPercentage()));
        }else{
            loanAmountBasedOnTotalCost = totalCost;
        }

        BigDecimal loanAmountBasedOnAppliedAmount = new BigDecimal(0);
        if(loanType.getAppliedAmountInPercentage() != 0) {
            loanAmountBasedOnAppliedAmount = totalCost.divide(new BigDecimal(100));
            loanAmountBasedOnAppliedAmount = loanAmountBasedOnAppliedAmount.multiply(new BigDecimal(loanType.getAppliedAmountInPercentage()));
        }else{
            loanAmountBasedOnAppliedAmount = appliedAmount;
        }

        eligibleAmount = calculateLoanAmountFor01A(loanAmountOnBaseSalary, loanAmountOnPfBalance,
                loanAmountBasedOnTotalCost, loanAmountBasedOnAppliedAmount,loanType);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loanAmountBasedOnSalary", loanAmountOnBaseSalary);
        jsonObject.put("loanAmountBasedPfBalance", loanAmountOnPfBalance);
        jsonObject.put("appliedAmount", appliedAmount);
        jsonObject.put("totalCost", totalCost);
        jsonObject.put("eligibleAmount", eligibleAmount);
        return jsonObject;
    }


    private BigDecimal calculateLoanAmountBasedOwnAccountPfBalancefor99E(TotalContributionDTO contributionTotalDTO,
                                                                         TransferInFinalDetails transferInContributionDTO,
                                                                         LoanWithDrawalsFinalDetails loanContributionDTO,
                                                                         LoanAmount loanAmount) {

        BigDecimal contribution = (contributionTotalDTO.getMemberContribution())
                .add(contributionTotalDTO.getVpfContribution())
                .add((contributionTotalDTO.getCompanyContribution()));
        System.out.println("Contribution : "+contribution);

        BigDecimal transferIn = transferInContributionDTO.getMemberContribution()
                .add(transferInContributionDTO.getCompanyContribution())
                .add(transferInContributionDTO.getVpfContribution());
        System.out.println("TransferIn : "+transferIn);

        BigDecimal loan = loanContributionDTO.getLoanAmountOnMemContribution()
                .add(loanContributionDTO.getLoanAmountOnCompanyContribution())
                .add(loanContributionDTO.getLoanAmountOnVPFContribution());
        System.out.println("Loan : "+loan);

        BigDecimal total = contribution.add(transferIn).subtract(loan);

        return total.multiply(BigDecimal.valueOf(loanAmount.getTotalPfBalance()))
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN);

    }


    private BigDecimal calculateLoanAmountBasedPfSalary(BigDecimal pfBase, LoanType loanType) {

        System.out.println("########### Pf Base Salary in Month : "+loanType.getPfBaseSalaryInMonth());

        if (loanType.getPfBaseSalaryInMonth() == 0) {
            return new BigDecimal(0);
        }

        BigDecimal bigDecimal = pfBase.multiply(new BigDecimal(loanType.getPfBaseSalaryInMonth()));

        System.out.println("pf base salary loan : "+bigDecimal);

        long round = Math.round(bigDecimal.doubleValue());

        return new BigDecimal(round);
    }

    private BigDecimal calculateLoanAmountBasedOwnAccountPfBalance(TotalContributionDTO contributionTotalDTO,
                                                                   TransferInFinalDetails transferInContributionDTO,
                                                                   LoanWithDrawalsFinalDetails loanContributionDTO,
                                                                   LoanAmount loanAmount) {

        BigDecimal contribution = contributionTotalDTO.getMemberContribution()
                .add(contributionTotalDTO.getVpfContribution());
        System.out.println("contribution : "+ contribution);

        BigDecimal transferIn = transferInContributionDTO.getMemberContribution()
                .add(transferInContributionDTO.getVpfContribution());
        System.out.println("transferIn : "+ transferIn);

        BigDecimal loan = loanContributionDTO.getLoanAmountOnMemContribution()
                .add(loanContributionDTO.getLoanAmountOnVPFContribution());
        System.out.println("loan : "+ loan);

        BigDecimal total = contribution.add(transferIn).subtract(loan);

        BigDecimal divide = total.multiply(BigDecimal.valueOf(loanAmount.getOwnAccountPfBalance()))
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN);

        return new BigDecimal(Math.round(divide.doubleValue()));

    }

    //Saran Calculation
    BigDecimal calculateContribution (TotalContributionDTO contributionTotalDTO, LoanType loanType){

        BigDecimal empContribution = new BigDecimal(0);
        if(loanType.getMemberBalanceInPercentage() != 0) {

            empContribution = contributionTotalDTO.getMemberContribution().divide(new BigDecimal(100));
            empContribution = empContribution.multiply(new BigDecimal(loanType.getMemberBalanceInPercentage()));

            BigDecimal difference = contributionTotalDTO.getMemberContribution().subtract(empContribution);
            BigDecimal minBalance =new BigDecimal(1000);

            if(empContribution.compareTo(contributionTotalDTO.getMemberContribution()) == 0){
                empContribution = empContribution.subtract(new BigDecimal(1000));
            }else if(minBalance.compareTo(difference) == 1){
                empContribution = contributionTotalDTO.getMemberContribution().subtract(new BigDecimal(1000));
            }

        }

        BigDecimal cc = new BigDecimal(0);
        if(loanType.getCompanyBalanceInPercentage() != 0) {

            cc = contributionTotalDTO.getCompanyContribution().divide(new BigDecimal(100));
            cc = cc.multiply(new BigDecimal(loanType.getCompanyBalanceInPercentage()));

            BigDecimal difference = contributionTotalDTO.getCompanyContribution().subtract(cc);
            BigDecimal minBalance =new BigDecimal(1000);

            if(cc.compareTo(contributionTotalDTO.getCompanyContribution()) == 0){
                cc = cc.subtract(new BigDecimal(1000));
            }else if(minBalance.compareTo(difference) == 1){
                cc = contributionTotalDTO.getCompanyContribution().subtract(new BigDecimal(1000));
            }

        }

        BigDecimal vpfc = new BigDecimal(0);
        if(loanType.getVpfBalanceInPercentage() != 0) {

            vpfc = contributionTotalDTO.getVpfContribution().divide(new BigDecimal(100));
            vpfc = vpfc.multiply(new BigDecimal(loanType.getVpfBalanceInPercentage()));

        }

        BigDecimal contribution1 = empContribution.add(cc).add(vpfc);

        return contribution1;
    }

    BigDecimal calculateTransferIn (TransferInFinalDetails transferInContributionDTO, LoanType loanType){

        BigDecimal empTransIn = new BigDecimal(0);
        if(loanType.getMemberBalanceInPercentage() != 0) {

            empTransIn = transferInContributionDTO.getMemberContribution().divide(new BigDecimal(100));
            empTransIn = empTransIn.multiply(new BigDecimal(loanType.getMemberBalanceInPercentage()));

        }

        BigDecimal companyTransIn = new BigDecimal(0);
        if(loanType.getCompanyBalanceInPercentage() != 0) {

            companyTransIn = transferInContributionDTO.getCompanyContribution().divide(new BigDecimal(100));
            companyTransIn = companyTransIn.multiply(new BigDecimal(loanType.getCompanyBalanceInPercentage()));

        }

        BigDecimal vpfTransIn = new BigDecimal(0);
        if(loanType.getVpfBalanceInPercentage() != 0) {

            vpfTransIn = transferInContributionDTO.getVpfContribution().divide(new BigDecimal(100));
            vpfTransIn = vpfTransIn.multiply(new BigDecimal(loanType.getVpfBalanceInPercentage()));

        }

        BigDecimal TransferIn1 = empTransIn.add(companyTransIn).add(vpfTransIn);

        return TransferIn1;
    }

    BigDecimal calculateLoans (LoanWithDrawalsFinalDetails loanContributionDTO, LoanType loanType){

        BigDecimal empLoan = new BigDecimal(0);
        if(loanType.getMemberBalanceInPercentage() != 0) {
            empLoan = loanContributionDTO.getLoanAmountOnMemContribution();
        }

        BigDecimal companyLoan = new BigDecimal(0);
        if(loanType.getCompanyBalanceInPercentage() != 0) {

            companyLoan = loanContributionDTO.getLoanAmountOnCompanyContribution();
        }

        BigDecimal vpfLoan = new BigDecimal(0);
        if(loanType.getVpfBalanceInPercentage() != 0) {

            vpfLoan = loanContributionDTO.getLoanAmountOnCompanyContribution();

        }

        BigDecimal Loan1 = empLoan.add(companyLoan).add(vpfLoan);

        return Loan1;
    }

    private BigDecimal calculateLoanAmountBasedTotalPfBalance(TotalContributionDTO contributionTotalDTO,
                                                              TransferInFinalDetails transferInContributionDTO,
                                                              LoanWithDrawalsFinalDetails loanContributionDTO,
                                                              LoanAmount loanAmount,LoanType loanType) {

        BigDecimal contribution1 = calculateContribution(contributionTotalDTO,loanType);
        System.out.println("Contribution1 : "+contribution1);

//        BigDecimal contribution = (contributionTotalDTO.getMemberContribution().subtract(new BigDecimal(1000)))
//                .add(contributionTotalDTO.getVpfContribution())
//                .add((contributionTotalDTO.getCompanyContribution().subtract(new BigDecimal(1000))));
//        System.out.println("Contribution : "+contribution);

        BigDecimal transferIn1 = calculateTransferIn(transferInContributionDTO,loanType);
//        System.out.println("TransferIn1 : "+ transferIn1);
//
//        BigDecimal transferIn = transferInContributionDTO.getMemberContribution()
//                .add(transferInContributionDTO.getCompanyContribution())
//                .add(transferInContributionDTO.getVpfContribution());
//        System.out.println("TransferIn : "+ transferIn);

        BigDecimal loan1 = calculateLoans(loanContributionDTO,loanType);
//        System.out.println("loan1 : "+ loan1);
//
//        BigDecimal loan = loanContributionDTO.getLoanAmountOnMemContribution()
//                .add(loanContributionDTO.getLoanAmountOnCompanyContribution())
//                .add(loanContributionDTO.getLoanAmountOnVPFContribution());
//        System.out.println("loan : "+ loan);
//
//        BigDecimal total = contribution.add(transferIn).subtract(loan);
//        System.out.println("Total Calculation : "+total);

        BigDecimal total1 = contribution1.add(transferIn1).subtract(loan1);
        System.out.println("Total1 Calculation : "+total1);

//        System.out.println("final Amount : "+ new BigDecimal(Math.round(total1.doubleValue())));

//        BigDecimal multiply = total.multiply(BigDecimal.valueOf(loanAmount.getTotalPfBalance()));
//        System.out.println("existing cal Result : "+multiply.divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN));
//        return multiply.divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN);
        return new BigDecimal(Math.round(total1.doubleValue()));
    }


    private BigDecimal calculateLoanAmountFor01A(BigDecimal amountBasedPfSalary, BigDecimal totalPfBalance,
                                                 BigDecimal totalCost, BigDecimal appliedAmount, LoanType loanType) {

        if(loanType.getPfBaseSalaryInMonth() == 0){
            amountBasedPfSalary = BigDecimal.valueOf(Long.MAX_VALUE);
        }
        if(Objects.equals(totalPfBalance, new BigDecimal(0))){
            totalPfBalance = BigDecimal.valueOf(Long.MAX_VALUE);
        }
        if(loanType.getTotalCostInPercentage() == 0){
            totalCost = BigDecimal.valueOf(Long.MAX_VALUE);
        }
        if(loanType.getAppliedAmountInPercentage() == 0){
            appliedAmount = BigDecimal.valueOf(Long.MAX_VALUE);
        }

        double min = Math.min(amountBasedPfSalary.doubleValue(), totalPfBalance.doubleValue());

        double min1 = Math.min(min, totalCost.doubleValue());

        double min2 = Math.min(min1, appliedAmount.doubleValue());

        return roundOfEligibleAmount(new BigDecimal(min2));

    }

    private BigDecimal calculateLoanAmountFor12A(BigDecimal amountBasedPfSalary, BigDecimal totalPfBalance,
                                                 BigDecimal totalCost, BigDecimal appliedAmount) {

        double min = Math.min(amountBasedPfSalary.doubleValue(), totalPfBalance.doubleValue());

        double min1 = Math.min(min, totalCost.doubleValue());

        double min2 = Math.min(min1, appliedAmount.doubleValue());

        return roundOfEligibleAmount(new BigDecimal(min2));

    }

    private BigDecimal calculateLoanAmountFor02A(BigDecimal amountBasedPfSalary, BigDecimal ownAccountPfBalance,
                                                 BigDecimal appliedAmount, BigDecimal totalCost) {

        double min = Math.min(amountBasedPfSalary.doubleValue(), ownAccountPfBalance.doubleValue());

        double min2 = Math.min(min, appliedAmount.doubleValue());

        double min3 = Math.min(min2, totalCost.doubleValue());

        return roundOfEligibleAmount(new BigDecimal(min3));

    }

    // calculate loan amount for 04C
    private BigDecimal calculateLoanAmountFor04C(BigDecimal loanAmountOnBaseSalary, BigDecimal loanAmountOnPfBalance,
                                                 BigDecimal appliedAmount, BigDecimal totalCost) {
        double min = Math.min(loanAmountOnBaseSalary.doubleValue(), loanAmountOnPfBalance.doubleValue());

        double min1 = Math.min(min, totalCost.doubleValue());

        double min2 = Math.min(min1, appliedAmount.doubleValue());

        return roundOfEligibleAmount(new BigDecimal(min2));
    }

    private BigDecimal calculateLoanAmountFor03B(BigDecimal ownAccountPfBalance, BigDecimal appliedAmount) {

        double min = Math.min(ownAccountPfBalance.doubleValue(), appliedAmount.doubleValue());

        return roundOfEligibleAmount(new BigDecimal(min));

    }

    private BigDecimal calculateLoanAmountFor99E(BigDecimal totalAccountPfBalance, BigDecimal appliedAmount) {

        double min = Math.min(totalAccountPfBalance.doubleValue(), appliedAmount.doubleValue());

        return roundOfEligibleAmount(new BigDecimal(min));

    }

    private BigDecimal calculateLoanAmountFor98E(BigDecimal totalAccountPfBalance, BigDecimal appliedAmount, BigDecimal loanAmountOnBaseSalary) {

        double min = Math.min(loanAmountOnBaseSalary.doubleValue(), totalAccountPfBalance.doubleValue());

        double min1 = Math.min(min, appliedAmount.doubleValue());

        return roundOfEligibleAmountfor98(new BigDecimal(min1));

    }

    private BigDecimal roundOfEligibleAmountfor98(BigDecimal amount) {

        double v = (amount.doubleValue() % 100);

        int h = new BigDecimal(v).shortValue();

        BigDecimal x = amount.subtract(new BigDecimal(v));

        double y = 50.00;
        if (v > 50 || v == 50 || v < 0.00) {

            return x.add(new BigDecimal(y));
        } else {

            return x;
        }


    }

    private BigDecimal roundOfEligibleAmount(BigDecimal amount) {
        double v = amount.doubleValue() % 1000;
        return amount.subtract(new BigDecimal(v));
    }

    @Override
    public Loan create(Employee employee, LoanType appliedLoanType, Loan loan) throws JPAException {

        if (employee.getContributionStatus().getSymbol().equals("S")) {
            throw new JPAException("Account with Pern. Number: " + employee.getPernNumber() + " has been already settled.");
        }

        try {
            Contribution openingBalance = contributionService.getContribution(employee,
                    FinancialYearAndMonth.getFinancialYear(new Date()), 0);
        }catch (EntityNotFoundException exception){
            throw new JPAException("Please perform year end process for the employee before proceeding.");
        }

        LoanStatus pending = loanStatusRepository.findByTitleAndIsActive("Pending", true);

        Integer pendingLoanCount = loanRepository.countLoansByEmployeeAndLoanStatusAndIsActive(employee, pending, true);

        if (pendingLoanCount > 0) {
            throw new JPAException(
                    "A pending loan application already exists for the account with Pern. Number: "
                            + employee.getPernNumber());
        }


        if (!checkEligibilityForMembershipTenure(employee, appliedLoanType)) {
            throw new JPAException("Not Eligible for the Loan Type.");
        }

        if (!checkEligibilityByMaxNoOfWithdrawals(employee, appliedLoanType)) {
            throw new JPAException("Not Eligible for the Loan Type.");
        }

        if(!checkEligibilityByRetirementDate(employee, appliedLoanType)){
            throw new JPAException("Not Eligible for the Loan Type.");
        }

        if(!checkEligibilityByNextEligibility(employee, appliedLoanType)){
            throw new JPAException("Not Eligible for the Loan Type.");
        }

        List<LoanType> list = new ArrayList<>();

        list.add(appliedLoanType);

        List<LoanType> loanTypes = checkForSpecialConditions(employee, list);

        if (loanTypes.size() == 0 || !loanTypes.get(0).getCode().equals(appliedLoanType.getCode())) {
            throw new JPAException("Not Eligible for the Loan Type.");
        }

        BigDecimal latestPfBase = contributionService.getLatestPfBase(employee);

        JSONObject eligibleAmount = getEligibleAmount(employee, latestPfBase, appliedLoanType, loan.getAppliedAmount(),
                loan.getTotalCost());

        loan.setEligibleAmount(eligibleAmount.getBigDecimal("eligibleAmount"));

        loan.setLoanAmountOnPfBaseSalary(eligibleAmount.getBigDecimal("loanAmountBasedOnSalary"));

        loan.setLoanAmountOnPfBalance(eligibleAmount.getBigDecimal("loanAmountBasedPfBalance"));

        loan.setEmployee(employee);

        loan.setLoanStatus(pending);

        loan.setLoanType(appliedLoanType);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(1L);

        loan.setSapStatus(sapStatus);

        Date lastRecoveryDate = contributionService.getLastRecoveryDate(employee);

        loan.setPfBase(latestPfBase);
        loan.setLastRecoveryDate(lastRecoveryDate);

        Loan savedLoan = loanRepository.save(loan);

        LoanWithDrawalsFinalDetails loanFinalWithDrawalDetails = getLoanFinalWithDrawalDetails(employee, savedLoan);

        loanFinalWithDrawalDetails.setLoan(savedLoan);
        loanFinalWithDrawalDetails.setEmployee(employee);

        LoanWithDrawalsFinalDetails savedLFWD = loanWithDrawalsFinalDetailsRepository.save(loanFinalWithDrawalDetails);

        savedLoan.setLoanWithDrawalsFinalDetails(savedLFWD);

        return savedLoan;

    }

    private LoanWithDrawalsFinalDetails getLoanFinalWithDrawalDetails(Employee employee, Loan loan){
        LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = new LoanWithDrawalsFinalDetails();
        return getLoanFinalWithDrawalDetails(employee, loan, loanWithDrawalsFinalDetails);
    }

    private LoanWithDrawalsFinalDetails getLoanFinalWithDrawalDetails(Employee employee, Loan loan,
                                                                      LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails) {

        TotalContributionDTO totalContribution = contributionService
                .getTotalContribution(employee, FinancialYearAndMonth.getFinancialYear(new Date()));

        TransferInFinalDetails totalTransferInContribution = transferInService
                .getTotalTransferInContribution(employee, FinancialYearAndMonth.getFinancialYear(new Date()));

        //        current year loan withdrawals before this one
        LoanWithDrawalsFinalDetails totalLoanWithDrawals = getTotalLoanWithDrawals(employee,
                FinancialYearAndMonth.getFinancialYear(new Date()));

        String loanCode = loan.getLoanType().getCode();

        if (loanCode.equals("01") || loanCode.equals("11") || loanCode.equals("13") || loanCode.equals("12")) {

            loanWithDrawalsFinalDetails = distributeInTotalAccountBalance(loan, totalContribution,
                    totalTransferInContribution, totalLoanWithDrawals, loanWithDrawalsFinalDetails);
        }
        if (loanCode.equals("99")) {
            loanWithDrawalsFinalDetails = distributeInTotalAccountBalanceFor99(loan,  totalContribution,
                    totalTransferInContribution, totalLoanWithDrawals, loanWithDrawalsFinalDetails);

        }
        if (loanCode.equals("98")) {
            loanWithDrawalsFinalDetails = distributeInTotalAccountBalanceFor98(loan,  totalContribution,
                    totalTransferInContribution, totalLoanWithDrawals, loanWithDrawalsFinalDetails);

        }
        if (loanCode.equals("02") || loanCode.equals("03") || loanCode.equals("04") || loanCode.equals("08") || loanCode.equals("14")) {

            loanWithDrawalsFinalDetails = distributeInOwnAccountBalance(loan,  totalContribution,
                    totalTransferInContribution, totalLoanWithDrawals, loanWithDrawalsFinalDetails);
        }

        Contribution yearOpeningContribution = contributionService.getContribution(employee,
                FinancialYearAndMonth.getFinancialYear(new Date()), 0);

        loanWithDrawalsFinalDetails.setMemberContributionYearOpening(yearOpeningContribution.getMemberContribution());
        loanWithDrawalsFinalDetails.setCompanyContributionYearOpening(yearOpeningContribution.getCompanyContribution());
        loanWithDrawalsFinalDetails.setVpfContributionYearOpening(yearOpeningContribution.getVpfContribution());

        loanWithDrawalsFinalDetails.setTotalContributionYearOpening(yearOpeningContribution.getTotalContribution());

        TotalContributionDTO totalCurrentYearContribution = contributionService.getTotalCurrentYearContribution(
                employee, FinancialYearAndMonth.getFinancialYear(new Date()));

        loanWithDrawalsFinalDetails.setMemberContributionCurrentYear(totalCurrentYearContribution.getMemberContribution());
        loanWithDrawalsFinalDetails.setCompanyContributionCurrentYear(totalCurrentYearContribution.getCompanyContribution());
        loanWithDrawalsFinalDetails.setVpfContributionCurrentYear(totalCurrentYearContribution.getVpfContribution());
        loanWithDrawalsFinalDetails.setTotalContributionCurrentYear(totalCurrentYearContribution.total());

        loanWithDrawalsFinalDetails
                .setMemberContributionCurrentYearTi(totalTransferInContribution.getMemberContribution());
        loanWithDrawalsFinalDetails
                .setCompanyContributionCurrentYearTi(totalTransferInContribution.getCompanyContribution());
        loanWithDrawalsFinalDetails
                .setVpfContributionCurrentYearTi(totalTransferInContribution.getVpfContribution());
        loanWithDrawalsFinalDetails
                .setTotalContributionCurrentYearTi(totalTransferInContribution.getTotal());

        loanWithDrawalsFinalDetails.setCurrentYearMemberLoanWithDrawal(totalLoanWithDrawals.getLoanAmountOnMemContribution());
        loanWithDrawalsFinalDetails.setCurrentYearCompanyLoanWithDrawal(totalLoanWithDrawals.getLoanAmountOnCompanyContribution());
        loanWithDrawalsFinalDetails.setCurrentYearVpfLoanWithDrawal(totalLoanWithDrawals.getLoanAmountOnVPFContribution());
        loanWithDrawalsFinalDetails.setCurrentYearTotalLoanWithDrawal(totalLoanWithDrawals.getTotalLoanWithDrawal());

        return loanWithDrawalsFinalDetails;

    }


    private LoanWithDrawalsFinalDetails distributeInTotalAccountBalance(Loan loan,
                                                                        TotalContributionDTO contributionTotalDTO,
                                                                        TransferInFinalDetails transferInContributionDTO,
                                                                        LoanWithDrawalsFinalDetails totalLoanWithDrawals,
                                                                        LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails) {
        BigDecimal loanEligibleAmount = loan.getEligibleAmount();
        BigDecimal memberContribution = contributionTotalDTO.getMemberContribution()
                .add(transferInContributionDTO.getMemberContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnMemContribution()).subtract(new BigDecimal(1000));

        BigDecimal companyContribution = contributionTotalDTO.getCompanyContribution()
                .add(transferInContributionDTO.getCompanyContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnCompanyContribution()).subtract(new BigDecimal(1000));

        BigDecimal vpfContribution = contributionTotalDTO.getVpfContribution()
                .add(contributionTotalDTO.getVpfContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnVPFContribution());

        BigDecimal total = memberContribution.add(companyContribution).add(vpfContribution);

        BigDecimal perMemberInTotal = memberContribution.multiply(new BigDecimal(100))
                .divide(total, 4, RoundingMode.CEILING);

        BigDecimal perCompanyInTotal = companyContribution.multiply(new BigDecimal(100))
                .divide(total, 4, RoundingMode.CEILING);

        BigDecimal perVpfInTotal =
                vpfContribution.multiply(new BigDecimal(100))
                        .divide(total, 4, RoundingMode.CEILING);

        BigDecimal loanAmountOnMemberContributionNo = loanEligibleAmount.multiply(perMemberInTotal)
                .divide(new BigDecimal(100), 4, RoundingMode.CEILING);

        double loanAmountOnMemberContribution = loanAmountOnMemberContributionNo.doubleValue();

        double loanAmountOnMemberContributionInt = Math.rint(loanAmountOnMemberContribution);


        BigDecimal loanAmountOnCompanyContributionNo = loanEligibleAmount.multiply(perCompanyInTotal)
                .divide(new BigDecimal(100), 4, RoundingMode.CEILING);

        double loanAmountOnCompanyContribution = loanAmountOnCompanyContributionNo.doubleValue();

        double loanAmountOnCompanyContributionInt = Math.rint(loanAmountOnCompanyContribution);


        BigDecimal loanAmountOnVpfContributionNo = loanEligibleAmount.multiply(perVpfInTotal).divide(new BigDecimal(100),
                4, RoundingMode.CEILING);

        double loanAmountOnVpfContribution = loanAmountOnVpfContributionNo.doubleValue();

        double loanAmountOnVpfContributionInt = Math.rint(loanAmountOnVpfContribution);


        BigDecimal memCont = new BigDecimal(loanAmountOnMemberContributionInt);
        BigDecimal companyCont = new BigDecimal(loanAmountOnCompanyContributionInt);

        BigDecimal vpfCont = new BigDecimal(loanAmountOnVpfContributionInt);

        BigDecimal totalCalculate = memCont.add(companyCont).add(vpfCont);

        BigDecimal eligibleAmount = loan.getEligibleAmount();

        if (totalCalculate.doubleValue() > eligibleAmount.doubleValue()) {
            BigDecimal extra = totalCalculate.subtract(eligibleAmount);


            if (memCont.doubleValue() > companyCont.doubleValue()) {

                memCont = memCont.subtract(extra);
            } else {
                companyCont = companyCont.subtract(extra);

            }
        }

        loanWithDrawalsFinalDetails.setLoanAmountOnMemContribution(memCont);
        loanWithDrawalsFinalDetails.setLoanAmountOnCompanyContribution(companyCont);

        loanWithDrawalsFinalDetails.setLoanAmountOnVPFContribution(vpfCont);


        loanWithDrawalsFinalDetails.setYear(FinancialYearAndMonth.getFinancialYear(new Date()));
        loanWithDrawalsFinalDetails.setMonth(FinancialYearAndMonth.getFinancialMonth(new Date()));

        return loanWithDrawalsFinalDetails;

    }

    private LoanWithDrawalsFinalDetails distributeInOwnAccountBalance(Loan loan, TotalContributionDTO contributionTotalDTO,
                                                                      TransferInFinalDetails transferInContributionDTO,
                                                                      LoanWithDrawalsFinalDetails totalLoanWithDrawals,
                                                                      LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails) {

        BigDecimal loanEligibleAmount = loan.getEligibleAmount();
        BigDecimal memberContribution = contributionTotalDTO.getMemberContribution()
                .add(transferInContributionDTO.getMemberContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnMemContribution());


        BigDecimal vpfContribution = contributionTotalDTO.getVpfContribution()
                .add(transferInContributionDTO.getVpfContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnVPFContribution());

        BigDecimal total = memberContribution.add(vpfContribution);

        BigDecimal perMemberInTotal = memberContribution.multiply(new BigDecimal(100))
                .divide(total, 4, RoundingMode.CEILING);

        BigDecimal perVpfInTotal =
                vpfContribution.multiply(new BigDecimal(100)).divide(total, 4, RoundingMode.CEILING);

        BigDecimal loanAmountOnMemberContribution = loanEligibleAmount.multiply(perMemberInTotal)
                .divide(new BigDecimal(100), 4, RoundingMode.CEILING);

        double loanAmountOnMemberContributionAmt = loanAmountOnMemberContribution.doubleValue();

        double loanAmountOnMemberContributionAmtInt = Math.rint(loanAmountOnMemberContributionAmt);

        BigDecimal loanAmountOnVpfContribution = loanEligibleAmount.multiply(perVpfInTotal).divide(new BigDecimal(100),
                4, RoundingMode.CEILING);

        double loanAmountOnVpfContributionAmt = loanAmountOnVpfContribution.doubleValue();

        double loanAmountOnVpfContributionAmtInt = Math.rint(loanAmountOnVpfContributionAmt);

        BigDecimal memCont = new BigDecimal(loanAmountOnMemberContributionAmtInt);
        BigDecimal vpfCont = new BigDecimal(loanAmountOnVpfContributionAmtInt);
        BigDecimal totalCalculate = memCont.add(vpfCont);

        BigDecimal eligibleAmount = loan.getEligibleAmount();

        if (totalCalculate.doubleValue() > eligibleAmount.doubleValue()) {
            BigDecimal extra = totalCalculate.subtract(eligibleAmount);
            memCont = memCont.subtract(extra);

        }

        loanWithDrawalsFinalDetails.setLoanAmountOnMemContribution(memCont);
        loanWithDrawalsFinalDetails.setLoanAmountOnCompanyContribution(new BigDecimal(0));
        loanWithDrawalsFinalDetails.setLoanAmountOnVPFContribution(vpfCont);
        loanWithDrawalsFinalDetails.setYear(FinancialYearAndMonth.getFinancialYear(new Date()));
        loanWithDrawalsFinalDetails.setMonth(FinancialYearAndMonth.getFinancialMonth(new Date()));

        return loanWithDrawalsFinalDetails;

    }



    private LoanWithDrawalsFinalDetails distributeInTotalAccountBalanceFor99(Loan loan,
                                                                             TotalContributionDTO contributionTotalDTO,
                                                                             TransferInFinalDetails transferInContributionDTO,
                                                                             LoanWithDrawalsFinalDetails totalLoanWithDrawals,
                                                                             LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails) {
        BigDecimal loanEligibleAmount = loan.getEligibleAmount();

        BigDecimal memberContribution = contributionTotalDTO.getMemberContribution()
                .add(transferInContributionDTO.getMemberContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnMemContribution());

        BigDecimal companyContribution = contributionTotalDTO.getCompanyContribution()
                .add(transferInContributionDTO.getCompanyContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnCompanyContribution());

        BigDecimal vpfContribution = contributionTotalDTO.getVpfContribution()
                .add(transferInContributionDTO.getVpfContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnVPFContribution());

        BigDecimal total = memberContribution.add(companyContribution).add(vpfContribution);

        BigDecimal perMemberInTotal = memberContribution.multiply(new BigDecimal(100))
                .divide(total, 4, RoundingMode.CEILING);

        BigDecimal perCompanyInTotal = companyContribution.multiply(new BigDecimal(100))
                .divide(total, 4, RoundingMode.CEILING);

        BigDecimal perVpfInTotal =
                vpfContribution.multiply(new BigDecimal(100))
                        .divide(total, 4, RoundingMode.CEILING);

        BigDecimal loanAmountOnMemberContributionNo = loanEligibleAmount.multiply(perMemberInTotal)
                .divide(new BigDecimal(100), 4, RoundingMode.CEILING);
        double loanAmountOnMemberContribution = loanAmountOnMemberContributionNo.doubleValue();

        double loanAmountOnMemberContributionInt = Math.rint(loanAmountOnMemberContribution);


        BigDecimal loanAmountOnCompanyContributionNo = loanEligibleAmount.multiply(perCompanyInTotal)
                .divide(new BigDecimal(100), 4, RoundingMode.CEILING);
        double loanAmountOnCompanyContribution = loanAmountOnCompanyContributionNo.doubleValue();

        double loanAmountOnCompanyContributionInt = Math.rint(loanAmountOnCompanyContribution);


        BigDecimal loanAmountOnVpfContributionNo = loanEligibleAmount.multiply(perVpfInTotal).divide(new BigDecimal(100),
                4, RoundingMode.CEILING);
        double loanAmountOnVpfContribution = loanAmountOnVpfContributionNo.doubleValue();

        double loanAmountOnVpfContributionInt = Math.rint(loanAmountOnVpfContribution);


        BigDecimal memCont = new BigDecimal(loanAmountOnMemberContributionInt);

        BigDecimal companyCont = new BigDecimal(loanAmountOnCompanyContributionInt);

        BigDecimal vpfCont = new BigDecimal(loanAmountOnVpfContributionInt);

        BigDecimal totalCalculate = memCont.add(companyCont).add(vpfCont);

        BigDecimal eligibleAmount = loan.getEligibleAmount();

        if (totalCalculate.doubleValue() > eligibleAmount.doubleValue()) {
            BigDecimal extra = totalCalculate.subtract(eligibleAmount);

            if (memCont.doubleValue() > companyCont.doubleValue()) {

                companyCont = companyCont.subtract(extra);

            } else {

                memCont = memCont.subtract(extra);

            }
        }

        loanWithDrawalsFinalDetails.setLoanAmountOnMemContribution(memCont);
        loanWithDrawalsFinalDetails.setLoanAmountOnCompanyContribution(companyCont);
        loanWithDrawalsFinalDetails.setLoanAmountOnVPFContribution(vpfCont);

        loanWithDrawalsFinalDetails.setYear(FinancialYearAndMonth.getFinancialYear(new Date()));
        loanWithDrawalsFinalDetails.setMonth(FinancialYearAndMonth.getFinancialMonth(new Date()));

        return loanWithDrawalsFinalDetails;

    }


    private LoanWithDrawalsFinalDetails distributeInTotalAccountBalanceFor98(Loan loan,
                                                                             TotalContributionDTO contributionTotalDTO,
                                                                             TransferInFinalDetails transferInContributionDTO,
                                                                             LoanWithDrawalsFinalDetails totalLoanWithDrawals,
                                                                             LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails) {
        BigDecimal loanEligibleAmount = loan.getEligibleAmount();

        BigDecimal memberContribution = contributionTotalDTO.getMemberContribution()
                .add(transferInContributionDTO.getMemberContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnMemContribution());

        BigDecimal companyContribution = contributionTotalDTO.getCompanyContribution()
                .add(transferInContributionDTO.getCompanyContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnCompanyContribution());

        BigDecimal vpfContribution = contributionTotalDTO.getVpfContribution()
                .add(transferInContributionDTO.getCompanyContribution())
                .subtract(totalLoanWithDrawals.getLoanAmountOnVPFContribution());

        BigDecimal total = memberContribution.add(companyContribution).add(vpfContribution);

        BigDecimal perMemberInTotal = memberContribution.multiply(new BigDecimal(100))
                .divide(total, 4, RoundingMode.CEILING);

        BigDecimal perCompanyInTotal = companyContribution.multiply(new BigDecimal(100))
                .divide(total, 4, RoundingMode.CEILING);

        BigDecimal perVpfInTotal =
                vpfContribution.multiply(new BigDecimal(100))
                        .divide(total, 4, RoundingMode.CEILING);

        BigDecimal loanAmountOnMemberContributionNo = loanEligibleAmount.multiply(perMemberInTotal)
                .divide(new BigDecimal(100), 4, RoundingMode.CEILING);

        double loanAmountOnMemberContribution = loanAmountOnMemberContributionNo.doubleValue();

        double loanAmountOnMemberContributionInt = Math.rint(loanAmountOnMemberContribution);

        BigDecimal loanAmountOnCompanyContributionNo = loanEligibleAmount.multiply(perCompanyInTotal)
                .divide(new BigDecimal(100), 4, RoundingMode.CEILING);

        double loanAmountOnCompanyContribution = loanAmountOnCompanyContributionNo.doubleValue();

        double loanAmountOnCompanyContributionInt = Math.rint(loanAmountOnCompanyContribution);


        BigDecimal loanAmountOnVpfContributionNo = loanEligibleAmount.multiply(perVpfInTotal).divide(new BigDecimal(100),
                4, RoundingMode.CEILING);

        double loanAmountOnVpfContribution = loanAmountOnVpfContributionNo.doubleValue();

        double loanAmountOnVpfContributionInt = Math.rint(loanAmountOnVpfContribution);

        BigDecimal memCont = new BigDecimal(loanAmountOnMemberContributionInt);

        BigDecimal companyCont = new BigDecimal(loanAmountOnCompanyContributionInt);

        BigDecimal vpfCont = new BigDecimal(loanAmountOnVpfContributionInt);

        BigDecimal totalCalculate = memCont.add(companyCont).add(vpfCont);

        BigDecimal eligibleAmount = loan.getEligibleAmount();

        if (totalCalculate.doubleValue() > eligibleAmount.doubleValue()) {
            BigDecimal extra = totalCalculate.subtract(eligibleAmount);

            if (memCont.doubleValue() > companyCont.doubleValue()) {

                companyCont = companyCont.subtract(extra);

            } else {

                memCont = memCont.subtract(extra);

            }
        }

        loanWithDrawalsFinalDetails.setLoanAmountOnMemContribution(memCont);
        loanWithDrawalsFinalDetails.setLoanAmountOnCompanyContribution(companyCont);

        loanWithDrawalsFinalDetails.setLoanAmountOnVPFContribution(vpfCont);

        loanWithDrawalsFinalDetails.setYear(FinancialYearAndMonth.getFinancialYear(new Date()));
        loanWithDrawalsFinalDetails.setMonth(FinancialYearAndMonth.getFinancialMonth(new Date()));

        return loanWithDrawalsFinalDetails;

    }

    @Override
    public List<LoanDocument> getLoanDocuments(JSONArray documents) {

        List<LoanDocument> loanDocumentList = new ArrayList<>();

        for (int i=0; i< documents.length(); i++){

            JSONObject jsonObject = documents.getJSONObject(i);

            Optional<Document> optionalDocument = documentRepository
                    .findByEntityIdAndIsActive(jsonObject.getString("entityId"), true);

            if(optionalDocument.isEmpty()) throw new EntityNotFoundException("Document Not Found.");

            Document document = optionalDocument.get();

            LoanDocument loanDocument = new LoanDocument();

            loanDocument.setDocument(document);
            loanDocument.setPath(jsonObject.getString("path"));

            loanDocumentList.add(loanDocument);

        }

        return loanDocumentList;
    }

    @Override
    public Loan update(Employee employee, LoanType appliedLoanType, Loan loan) throws JPAException {

        if (!checkEligibilityForMembershipTenure(employee, appliedLoanType)) {
            throw new JPAException("Not Eligible for the Loan Type.");
        }

        List<LoanType> list = new ArrayList<>();

        list.add(appliedLoanType);

        List<LoanType> loanTypes = checkForSpecialConditions(employee, list);

        if (loanTypes.size() == 0 || !loanTypes.get(0).getCode().equals(appliedLoanType.getCode())) {
            throw new JPAException("Not Eligible for the Loan Type.");
        }

        BigDecimal latestPfBase = contributionService.getLatestPfBase(employee);

        JSONObject eligibleAmount = getEligibleAmount(employee, latestPfBase, appliedLoanType, loan.getAppliedAmount(),
                loan.getTotalCost());

        loan.setEligibleAmount(eligibleAmount.getBigDecimal("eligibleAmount"));

        loan.setLoanAmountOnPfBaseSalary(eligibleAmount.getBigDecimal("loanAmountBasedOnSalary"));

        loan.setLoanAmountOnPfBalance(eligibleAmount.getBigDecimal("loanAmountBasedPfBalance"));

        loan.setLoanType(appliedLoanType);

        Date lastRecoveryDate = contributionService.getLastRecoveryDate(employee);

        loan.setPfBase(latestPfBase);
        loan.setLastRecoveryDate(lastRecoveryDate);

        Loan savedLoan = loanRepository.save(loan);

        LoanWithDrawalsFinalDetails loanFinalWithDrawalDetails = getLoanFinalWithDrawalDetails(employee, savedLoan,
                savedLoan.getLoanWithDrawalsFinalDetails());

        loanFinalWithDrawalDetails.setLoan(savedLoan);
        loanFinalWithDrawalDetails.setEmployee(employee);

        LoanWithDrawalsFinalDetails savedLFWD = loanWithDrawalsFinalDetailsRepository.save(loanFinalWithDrawalDetails);

        savedLoan.setLoanWithDrawalsFinalDetails(savedLFWD);

        return savedLoan;

    }

    @Override
    public void saveLoanDocuments(List<LoanDocument> loanDocuments, Loan loan) {

        List<LoanDocument> mappedDocuments = loanDocuments.stream()
                .peek(loanDocument -> loanDocument.setLoan(loan))
                .collect(Collectors.toList());

        loanDocumentRepository.saveAll(mappedDocuments);

    }

    @Override
    public void updateLoanDocuments(JSONArray jsonArray) {

        for (int i=0; i< jsonArray.length(); i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Optional<LoanDocument> optionalLoanDocument = loanDocumentRepository
                    .findByEntityIdAndIsActive(jsonObject.getString("entityId"), true);

            if(optionalLoanDocument.isPresent()){

                LoanDocument loanDocument = optionalLoanDocument.get();

                loanDocument.setPath(jsonObject.getString("path"));

                loanDocumentRepository.save(loanDocument);
            }

        }

    }

    @Override
    public List<LoanEmailStatus> getEmailStatusListNotSent() {
        return loanEmailStatusRepository.findAllByIsSentAndIsActive(false, true);
    }

    @Override
    public List<LoanEmailStatus> getLoanEmailStatusList(List<String> entityIds) {
        return loanEmailStatusRepository.findAllByIsActiveAndEntityIdIn(true, new HashSet<>(entityIds));
    }

    @Override
    public Loan getPendingLoan(Employee employee) {

        LoanStatus pending = loanStatusRepository.findByTitleAndIsActive("Pending", true);

        List<Loan> loans = loanRepository.findAllByEmployeeAndLoanStatusAndIsActive(employee,
                pending, true);

        if(loans.isEmpty()){
            throw new EntityNotFoundException("Loan not found for the Employee");
        }

        return loans.get(0);

    }

    @Override
    public void approveLoan(Loan loan, JSONObject jsonObject) throws ParseException {

        Date paymentDate = FinancialYearAndMonth.getDate(jsonObject.getString("paymentDate"), "yyyy-MM-dd");

        Date approvalDate = FinancialYearAndMonth.getDate(jsonObject.getString("approvalDate"), "yyyy-MM-dd");

        Optional<Bank> optionalBank = bankRepository.findByEntityIdAndIsActive(jsonObject.getString("bank"), true);

        if(optionalBank.isEmpty()) throw new EntityNotFoundException("Selected Bank Not Found");

        Optional<PaymentMode> optionalPaymentMode = paymentModeRepository
                .findByEntityIdAndIsActive(jsonObject.getString("paymentMode"), true);

        if(optionalPaymentMode.isEmpty()) throw new EntityNotFoundException("Payment Mode Not Found");

        savePaymentDetails(loan, optionalBank.get(), optionalPaymentMode.get(),
                jsonObject.getString("referenceNumber"), approvalDate, paymentDate);

        LoanEmailStatus emailStatus = createEmailStatus(loan);

    }
    @Override
    public List<LoanType> getLoanTypes() {

        return loanTypeRepository.findAllByIsActive(true);

    }

    @Override
    public Boolean saveLoanType(LoanType loanType){

        List<LoanType> loanTypeList = loanTypeRepository.findAllByIsActive(true);

        boolean duplicateCode = true;
        for (LoanType type : loanTypeList) {
            if (type.getCode().equals(loanType.getCode())) {
                duplicateCode = false;
                break;
            }
        }

        if(duplicateCode) {
            loanTypeRepository.save(loanType);
        }

        return duplicateCode;

    }
    
    @Override
    public void updateLoanStatus(Loan loan) {
    	loanRepository.save(loan);
    }


    @Override
    public DistributedLoanAmountDTO distributeLoanOnMemberContribution(BigDecimal openingBalanceNonTaxable,
                                                               BigDecimal openingBalanceTaxable,
                                                               BigDecimal contributionNonTaxable,
                                                               BigDecimal contributionTaxable,
                                                               BigDecimal loanAmount) {

        return distributeLoanAmount(openingBalanceNonTaxable, openingBalanceTaxable,
                contributionNonTaxable, contributionTaxable, loanAmount);

    }

    @Override
    public DistributedLoanAmountDTO distributeLoanOnVpfContribution(BigDecimal openingBalanceNonTaxable,
                                                            BigDecimal openingBalanceTaxable,
                                                            BigDecimal contributionNonTaxable,
                                                            BigDecimal contributionTaxable,
                                                            BigDecimal loanAmount) {

        return distributeLoanAmount(openingBalanceNonTaxable, openingBalanceTaxable,
                contributionNonTaxable, contributionTaxable, loanAmount);

    }


    DistributedLoanAmountDTO distributeLoanAmount(BigDecimal openingBalanceNonTaxable,
                                                  BigDecimal openingBalanceTaxable,
                                                  BigDecimal contributionNonTaxable,
                                                  BigDecimal contributionTaxable,
                                                  BigDecimal loanAmount){

        BigDecimal loanAmountOnOpeningBalanceNonTaxable = BigDecimal.ZERO;
        BigDecimal loanAmountOnOpeningBalanceTaxable =  BigDecimal.ZERO;
        BigDecimal loanAmountOnContributionNonTaxable =  BigDecimal.ZERO;
        BigDecimal loanAmountOnContributionTaxable =  BigDecimal.ZERO;

        if(openingBalanceTaxable.compareTo(BigDecimal.ZERO) > 0 &&
                openingBalanceTaxable.compareTo(loanAmount) >= 0 &&
                loanAmount.compareTo(BigDecimal.ZERO) > 0){
            loanAmountOnOpeningBalanceTaxable = loanAmount;
            loanAmount = BigDecimal.ZERO;
        }

        if(openingBalanceTaxable.compareTo(BigDecimal.ZERO) > 0 &&
                openingBalanceTaxable.compareTo(loanAmount) < 0 &&
                loanAmount.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal subtract = loanAmount.subtract(openingBalanceTaxable);
            loanAmountOnOpeningBalanceTaxable = openingBalanceTaxable;
            loanAmount = subtract;
        }

        if(contributionTaxable.compareTo(BigDecimal.ZERO) > 0 &&
                contributionTaxable.compareTo(loanAmount) >= 0 &&
                loanAmount.compareTo(BigDecimal.ZERO) > 0){
            loanAmountOnContributionTaxable = loanAmount;
            loanAmount = BigDecimal.ZERO;
        }

        if(contributionTaxable.compareTo(BigDecimal.ZERO) > 0 &&
                contributionTaxable.compareTo(loanAmount) < 0 &&
                loanAmount.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal subtract = loanAmount.subtract(contributionTaxable);
            loanAmountOnContributionTaxable = contributionTaxable;
            loanAmount = subtract;
        }

        if(openingBalanceNonTaxable.compareTo(BigDecimal.ZERO) > 0 &&
                openingBalanceNonTaxable.compareTo(loanAmount) >= 0 &&
                loanAmount.compareTo(BigDecimal.ZERO) > 0){
            loanAmountOnOpeningBalanceNonTaxable = loanAmount;
            loanAmount = BigDecimal.ZERO;
        }

        if(openingBalanceNonTaxable.compareTo(BigDecimal.ZERO) > 0 &&
                openingBalanceNonTaxable.compareTo(loanAmount) < 0 &&
                loanAmount.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal subtract = loanAmount.subtract(openingBalanceNonTaxable);
            loanAmountOnOpeningBalanceNonTaxable = openingBalanceNonTaxable;
            loanAmount = subtract;
        }

        if(contributionNonTaxable.compareTo(BigDecimal.ZERO) > 0 &&
                contributionNonTaxable.compareTo(loanAmount) >= 0 &&
                loanAmount.compareTo(BigDecimal.ZERO) > 0){
            loanAmountOnContributionNonTaxable = loanAmount;
            loanAmount = BigDecimal.ZERO;
        }

        if(contributionNonTaxable.compareTo(BigDecimal.ZERO) > 0 &&
                contributionNonTaxable.compareTo(loanAmount) < 0 &&
                loanAmount.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal subtract = loanAmount.subtract(contributionNonTaxable);
            loanAmountOnContributionNonTaxable = contributionNonTaxable;
            loanAmount = subtract;
        }

        return new DistributedLoanAmountDTO(loanAmountOnOpeningBalanceNonTaxable,
                loanAmountOnOpeningBalanceTaxable,
                loanAmountOnContributionNonTaxable,
                loanAmountOnContributionTaxable);
    }


    @Override
    public List<Loan> distributeLoanAmountIntoTaxableAndNonTaxable(List<Loan> loans, Employee employee) {

        BigDecimal totalLoanAmountOnOpeningBalanceNonTaxableMember = BigDecimal.ZERO;
        BigDecimal totalLoanAmountOnOpeningBalanceTaxableMember = BigDecimal.ZERO;
        BigDecimal totalLoanAmountOnContributionNonTaxableMember = BigDecimal.ZERO;
        BigDecimal totalLoanAmountOnContributionTaxableMember = BigDecimal.ZERO;

        BigDecimal totalLoanAmountOnOpeningBalanceNonTaxableVpf = BigDecimal.ZERO;
        BigDecimal totalLoanAmountOnOpeningBalanceTaxableVpf = BigDecimal.ZERO;
        BigDecimal totalLoanAmountOnContributionNonTaxableVpf = BigDecimal.ZERO;
        BigDecimal totalLoanAmountOnContributionTaxableVpf = BigDecimal.ZERO;

        List<Loan> distributedLoans = new ArrayList<>();

        for (Loan loan:loans) {

            LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

            TotalContributionDTO totalCurrentYearContributionTillMonth = contributionService
                    .getTotalCurrentYearContributionTillMonth(employee,
                            loanWithDrawalsFinalDetails.getYear(),
                            loanWithDrawalsFinalDetails.getMonth());

            // adjust the amounts
            totalCurrentYearContributionTillMonth.setNonTaxableMemberContribution(
                    totalCurrentYearContributionTillMonth.getNonTaxableMemberContribution()
                            .subtract(totalLoanAmountOnContributionNonTaxableMember)
            );

            totalCurrentYearContributionTillMonth.setTaxableMemberContribution(
                    totalCurrentYearContributionTillMonth.getTaxableMemberContribution()
                            .subtract(totalLoanAmountOnContributionTaxableMember)
            );

            totalCurrentYearContributionTillMonth.setNontaxableVpfContribution(
                    totalCurrentYearContributionTillMonth.getNontaxableVpfContribution()
                            .subtract(totalLoanAmountOnContributionNonTaxableVpf)
            );

            totalCurrentYearContributionTillMonth.setTaxableVpfContribution(
                    totalCurrentYearContributionTillMonth.getTaxableVpfContribution()
                            .subtract(totalLoanAmountOnContributionTaxableVpf)
            );


            TotalContributionDTO currentYearOpeningBalanceContribution = contributionService
                    .getTotalCurrentYearOpeningBalance(employee,
                            loanWithDrawalsFinalDetails.getYear());

            // adjust the amounts
            currentYearOpeningBalanceContribution.setNonTaxableMemberContribution(
                    currentYearOpeningBalanceContribution.getNonTaxableMemberContribution()
                            .subtract(totalLoanAmountOnOpeningBalanceNonTaxableMember)
            );

            currentYearOpeningBalanceContribution.setTaxableMemberContribution(
                    currentYearOpeningBalanceContribution.getTaxableMemberContribution()
                            .subtract(totalLoanAmountOnOpeningBalanceTaxableMember)
            );

            currentYearOpeningBalanceContribution.setNontaxableVpfContribution(
                    currentYearOpeningBalanceContribution.getNontaxableVpfContribution()
                            .subtract(totalLoanAmountOnOpeningBalanceNonTaxableVpf)
            );

            currentYearOpeningBalanceContribution.setTaxableVpfContribution(
                    currentYearOpeningBalanceContribution.getTaxableVpfContribution()
                            .subtract(totalLoanAmountOnOpeningBalanceTaxableVpf)
            );



            DistributedLoanAmountDTO distributedLoanAmountOnMember =
                    distributeLoanAmountIntoTaxableAndNonTaxableMemberContribution(
                            totalCurrentYearContributionTillMonth,
                            currentYearOpeningBalanceContribution,
                            loanWithDrawalsFinalDetails);


            // adjust the amounts
            totalLoanAmountOnOpeningBalanceNonTaxableMember = totalLoanAmountOnOpeningBalanceNonTaxableMember
                            .add(distributedLoanAmountOnMember.getLoanAmountOnOpeningBalanceNonTaxable());

            totalLoanAmountOnOpeningBalanceTaxableMember = totalLoanAmountOnOpeningBalanceTaxableMember
                    .add(distributedLoanAmountOnMember.getLoanAmountOnOpeningBalanceTaxable());

            totalLoanAmountOnContributionNonTaxableMember = totalLoanAmountOnContributionNonTaxableMember
                    .add(distributedLoanAmountOnMember.getLoanAmountOnContributionNonTaxable());

            totalLoanAmountOnContributionTaxableMember = totalLoanAmountOnContributionTaxableMember
                    .add(distributedLoanAmountOnMember.getLoanAmountOnContributionTaxable());


            DistributedLoanAmountDTO distributedLoanAmountOnVpf =
                    distributeLoanAmountIntoTaxableAndNonTaxableVpfContribution(
                            totalCurrentYearContributionTillMonth,
                            currentYearOpeningBalanceContribution,
                            loanWithDrawalsFinalDetails);

            totalLoanAmountOnOpeningBalanceNonTaxableVpf = totalLoanAmountOnOpeningBalanceNonTaxableVpf
                    .add(distributedLoanAmountOnVpf.getLoanAmountOnOpeningBalanceNonTaxable());

            totalLoanAmountOnOpeningBalanceTaxableVpf = totalLoanAmountOnOpeningBalanceTaxableVpf
                    .add(distributedLoanAmountOnVpf.getLoanAmountOnOpeningBalanceTaxable());

            totalLoanAmountOnContributionNonTaxableVpf = totalLoanAmountOnContributionNonTaxableVpf
                    .add(distributedLoanAmountOnVpf.getLoanAmountOnContributionNonTaxable());

            totalLoanAmountOnContributionTaxableVpf = totalLoanAmountOnContributionTaxableVpf
                    .add(distributedLoanAmountOnVpf.getLoanAmountOnContributionTaxable());

            //member
            loanWithDrawalsFinalDetails.setLoanAmountOnNonTaxableMemberContribution(
                    distributedLoanAmountOnMember.getLoanAmountOnOpeningBalanceNonTaxable()
                            .add(distributedLoanAmountOnMember.getLoanAmountOnContributionNonTaxable())
            );

            loanWithDrawalsFinalDetails.setLoanAmountOnTaxableMemberContribution(
                    distributedLoanAmountOnMember.getLoanAmountOnOpeningBalanceTaxable()
                            .add(distributedLoanAmountOnMember.getLoanAmountOnContributionTaxable())
            );

            //vpf
            loanWithDrawalsFinalDetails.setLoanAmountOnNonTaxableVpfContribution(
                    distributedLoanAmountOnVpf.getLoanAmountOnOpeningBalanceNonTaxable()
                            .add(distributedLoanAmountOnVpf.getLoanAmountOnContributionNonTaxable())
            );

            loanWithDrawalsFinalDetails.setLoanAmountOnTaxableVpfContribution(
                    distributedLoanAmountOnVpf.getLoanAmountOnOpeningBalanceTaxable()
                            .add(distributedLoanAmountOnVpf.getLoanAmountOnContributionTaxable())
            );

            LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails1 = loanWithDrawalsFinalDetailsRepository.save(loanWithDrawalsFinalDetails);

            loan.setLoanWithDrawalsFinalDetails(loanWithDrawalsFinalDetails1);

            distributedLoans.add(loan);
        }

        return distributedLoans;
    }

    private DistributedLoanAmountDTO distributeLoanAmountIntoTaxableAndNonTaxableMemberContribution(
                                                             TotalContributionDTO totalCurrentYearContributionTillMonth,
                                                             TotalContributionDTO currentYearOpeningBalanceContribution,
                                                             LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails
    ) {

        return distributeLoanOnMemberContribution(
                currentYearOpeningBalanceContribution.getNonTaxableMemberContribution(),
                currentYearOpeningBalanceContribution.getTaxableMemberContribution(),
                totalCurrentYearContributionTillMonth.getNonTaxableMemberContribution(),
                totalCurrentYearContributionTillMonth.getTaxableMemberContribution(),
                loanWithDrawalsFinalDetails.getLoanAmountOnMemContribution()
        );

    }

    private DistributedLoanAmountDTO distributeLoanAmountIntoTaxableAndNonTaxableVpfContribution(
                                                             TotalContributionDTO totalCurrentYearContributionTillMonth,
                                                             TotalContributionDTO currentYearOpeningBalanceContribution,
                                                             LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails
    ) {

        return distributeLoanOnVpfContribution(
                currentYearOpeningBalanceContribution.getNontaxableVpfContribution(),
                currentYearOpeningBalanceContribution.getTaxableVpfContribution(),
                totalCurrentYearContributionTillMonth.getNontaxableVpfContribution(),
                totalCurrentYearContributionTillMonth.getTaxableVpfContribution(),
                loanWithDrawalsFinalDetails.getLoanAmountOnVPFContribution()
        );

    }






}
