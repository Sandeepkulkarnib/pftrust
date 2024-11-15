package com.coreintegra.pftrust.services.pf.transferin;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.entities.pf.PfAccountHolder;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.loan.dtos.StatusDTO;
import com.coreintegra.pftrust.entities.pf.loan.dtos.TypeDTO;
import com.coreintegra.pftrust.entities.pf.transferIn.*;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.TransferInDetailsDTO;
import com.coreintegra.pftrust.projections.TransferInContributionDTO;
import com.coreintegra.pftrust.repositories.pf.NotificationEmailDesignRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.employee.PreviousCompanyRepository;
import com.coreintegra.pftrust.repositories.pf.transferin.*;
import com.coreintegra.pftrust.searchutil.SearchSpecificationForEmployee;
import com.coreintegra.pftrust.searchutil.SearchTransferInSpecification;
import com.coreintegra.pftrust.services.email.EmailService;
import com.coreintegra.pftrust.services.pf.department.FinancialYearAndMonthService;
import com.coreintegra.pftrust.services.pf.department.UnitCodeService;
import com.coreintegra.pftrust.services.pf.transferin.dtos.SaveAnnexurekDTO;
import com.coreintegra.pftrust.util.DataUtil;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.coreintegra.pftrust.util.NumberFormatter;
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
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TransferInServiceImpl implements TransferInService {

    private final EmployeeRepository employeeRepository;
    private final TransferInRepository transferInRepository;
    private final PreviousCompanyRepository previousCompanyRepository;
    private final TransferInFinalDetailsRepository transferInFinalDetailsRepository;
    private final TransferInReminderRepository transferInReminderRepository;

    private final UnitCodeService unitCodeService;
    private final FinancialYearAndMonthService financialYearAndMonthService;
    private final PfAccountHolderRepository pfAccountHolderRepository;
    private final TransferInStatusRepository transferInStatusRepository;
    private final TransferInEmailStatusRepository transferInEmailStatusRepository;

    private final SearchTransferInSpecification searchTransferInSpecification;
    private final EmailService emailService;
    private final NotificationEmailDesignRepository notificationEmailDesignRepository;

    public TransferInServiceImpl(EmployeeRepository employeeRepository,
                                 TransferInRepository transferInRepository,
                                 PreviousCompanyRepository previousCompanyRepository,
                                 TransferInFinalDetailsRepository transferInFinalDetailsRepository,
                                 TransferInReminderRepository transferInReminderRepository,
                                 UnitCodeService unitCodeService,
                                 FinancialYearAndMonthService financialYearAndMonthService,
                                 PfAccountHolderRepository pfAccountHolderRepository,
                                 TransferInStatusRepository transferInStatusRepository,
                                 TransferInEmailStatusRepository transferInEmailStatusRepository,
                                 SearchTransferInSpecification searchTransferInSpecification,
                                 EmailService emailService,
                                 NotificationEmailDesignRepository notificationEmailDesignRepository) {
        this.employeeRepository = employeeRepository;
        this.transferInRepository = transferInRepository;
        this.previousCompanyRepository = previousCompanyRepository;
        this.transferInFinalDetailsRepository = transferInFinalDetailsRepository;
        this.transferInReminderRepository = transferInReminderRepository;
        this.unitCodeService = unitCodeService;
        this.financialYearAndMonthService = financialYearAndMonthService;
        this.pfAccountHolderRepository = pfAccountHolderRepository;
        this.transferInStatusRepository = transferInStatusRepository;
        this.transferInEmailStatusRepository = transferInEmailStatusRepository;
        this.searchTransferInSpecification = searchTransferInSpecification;
        this.emailService = emailService;
        this.notificationEmailDesignRepository = notificationEmailDesignRepository;
    }

    @Override
    @Async
    @Transactional
    @ApplyTenantFilter
    public CompletableFuture<String> saveFetchedTransferInAsync(TransferIn transferIn, String pernNumber, TransferInFinalDetails transferInFinalDetails, PreviousCompany previousCompany) {


        Optional<Employee> optionalEmployee = employeeRepository
                .findByPernNumberAndIsActive(pernNumber, true);

        if(!optionalEmployee.isPresent()){
            throw new EntityNotFoundException("employee not found");
        }

        Employee employee = optionalEmployee.get();

        previousCompany.setEmployee(employee);

        PreviousCompany company = previousCompanyRepository.save(previousCompany);

        transferIn.setEmployee(employee);
        transferIn.setPreviousCompany(company);

        TransferIn save = transferInRepository.save(transferIn);

        transferInFinalDetails.setTransferIn(save);

        transferInFinalDetailsRepository.save(transferInFinalDetails);

        TransferInReminder transferInReminder = new TransferInReminder();

        transferInReminder.setTransferIn(save);

        transferInReminderRepository.save(transferInReminder);

        return CompletableFuture.completedFuture("done");

    }

    @Override
    public TransferIn save(TransferIn transferIn) {
        transferInFinalDetailsRepository.save(transferIn.getTransferInFinalDetails());
        return transferInRepository.save(transferIn);
    }

    @Override
    public TransferIn save(TransferIn transferIn, Employee employee, PreviousCompany previousCompany) {

        TransferInStatus transferInStatus = transferInStatusRepository.findByCodeAndIsActive("P", true);

        previousCompany.setEmployee(employee);
        PreviousCompany savedPreviousCompany = previousCompanyRepository.save(previousCompany);

        transferIn.setPreviousCompany(savedPreviousCompany);
        transferIn.setEmployee(employee);
        transferIn.setTransferInStatus(transferInStatus);

        transferIn.setCreatedAtTimestamp(new Date().getTime());
        transferIn.setUpdatedAtTimestamp(new Date().getTime());

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(1L);

        transferIn.setSapStatus(sapStatus);

        TransferIn save = transferInRepository.save(transferIn);

        TransferInReminder transferInReminder = new TransferInReminder();

        transferInReminder.setTransferIn(save);

        transferInReminderRepository.save(transferInReminder);

        return save;
    }

    @Override
    public Page<TransferIn> getTransferIns(String search, Integer size, Integer page) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,
                "employee.pernNumber", "employee.unitCode"));

        Specification<TransferIn> specification = new SearchSpecificationForEmployee<TransferIn>().getSearchSpecificationForEmployee(search);

        return transferInRepository.findAll(specification, pageable);

    }

    @Override
    public EssentialsDTO getEssentials() {

        List<PfAccountHolder> pfAccountHolders = pfAccountHolderRepository.findAllByIsActive(true);

        List<TypeDTO> typeDTOList = pfAccountHolders.stream()
                .map(type -> new TypeDTO(type.getEntityId(), type.getName()))
                .collect(Collectors.toList());

        List<StatusDTO> statusDTOList = transferInStatusRepository.findAllByIsActive(true).stream()
                .map(status -> new StatusDTO(status.getEntityId(), status.getTitle()))
                .collect(Collectors.toList());

        List<UnitCode> unitCodes = unitCodeService.get();

        List<Integer> years = financialYearAndMonthService.getYears();

        Map<Integer, String> months = financialYearAndMonthService.getMonths();

        return new EssentialsDTO(typeDTOList, statusDTOList, unitCodes, years, months);
    }

    @Override
    public List<TransferIn> getTransferIns(Employee employee) {
        return transferInRepository.findAll(searchTransferInSpecification.employeeSpecification(employee));
    }

    @Override
    public List<TransferIn> getTransferIns(Employee employee, Integer year) {
        Specification<TransferIn> transferInSpecification = searchTransferInSpecification.employeeSpecification(employee)
                .and(searchTransferInSpecification.yearSpecification(year));
        return transferInRepository.findAll(transferInSpecification);
    }

    @Override
    public List<TransferInDetailsDTO> getTransferInDetails(Employee employee) {

        return getTransferIns(employee).stream()
                .map(transferIn -> {

                    PreviousCompany previousCompany = transferIn.getPreviousCompany();

                    TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

                    if(transferInFinalDetails == null){
                        transferInFinalDetails = new TransferInFinalDetails(BigDecimal.ZERO,
                                BigDecimal.ZERO, BigDecimal.ZERO);
                    }

                    PfAccountHolder prevPfAccountHolder = transferIn.getPrevPfAccountHolder();

                    TransferInStatus transferInStatus = transferIn.getTransferInStatus();

                    return new TransferInDetailsDTO(transferIn.getEntityId(),
                            previousCompany.getName(), transferIn.getPrevPfNumber(),
                            transferIn.getPrevPensionAccountNumber(), transferIn.getPrevDateOfJoining(),
                            transferIn.getPrevDateOfLeaving(), prevPfAccountHolder.getName(),
                            transferInStatus.getTitle(), transferIn.getPostingDate(),
                            transferInFinalDetails.getMemberContribution(),
                            transferInFinalDetails.getCompanyContribution(), transferIn.getSapDocumentNumber(),
                            transferIn.getAnnexureKFile(), transferIn.getDispatchLetterFile(),
                            transferInFinalDetails.getYear(), transferInFinalDetails.getContributedIn());

                }).collect(Collectors.toList());

    }

    @Override
    public TransferIn getTransferIn(String entityId) {

        Optional<TransferIn> optionalTransferIn = transferInRepository
                .findByEntityIdAndIsActive(entityId, true);

        if(optionalTransferIn.isEmpty()) throw new EntityNotFoundException("TransferIn not found");

        return optionalTransferIn.get();
    }

    @Override
    public TransferInContributionDTO getTotalTransferInContribution(Employee employee) {
        return transferInRepository.getTotalTransferInContribution(employee);
    }

    @Override
    public TransferInFinalDetails getTotalTransferInContribution(Employee employee, Integer year) {

        List<TransferIn> completedTransferIns = getCompletedTransferIns(employee, year);

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        for (TransferIn transferIn:completedTransferIns) {
            memberContribution = memberContribution.add(DataUtil.avoidNull(transferIn.getTransferInFinalDetails().getMemberContribution()));
            companyContribution = companyContribution.add(DataUtil.avoidNull(transferIn.getTransferInFinalDetails().getCompanyContribution()));
            vpfContribution = vpfContribution.add(DataUtil.avoidNull(transferIn.getTransferInFinalDetails().getVpfContribution()));
        }

        return new TransferInFinalDetails(memberContribution, companyContribution, vpfContribution);

    }

    @Override
    public List<TransferIn> getCompletedTransferIns(Employee employee, Integer year) {
        Specification<TransferIn> transferInSpecification = searchTransferInSpecification.employeeSpecification(employee)
                .and(searchTransferInSpecification.yearSpecification(year))
                .and(searchTransferInSpecification.statusSpecification("R"));
        return transferInRepository.findAll(transferInSpecification);
    }

    private Specification<TransferIn> getTransferInSpecification(Employee employee) {

        SearchTransferInSpecification transferInSpecification = new SearchTransferInSpecification();

        return transferInSpecification.employeeSpecification(employee);

    }

    private Specification<TransferIn> getTransferInSpecification(Employee employee, Integer year) {

        SearchTransferInSpecification transferInSpecification = new SearchTransferInSpecification();

        return transferInSpecification.employeeSpecification(employee)
                .and(transferInSpecification.yearSpecification(year));

    }


    @Override
    public void reject(String entityId) {

        TransferIn transferIn = getTransferIn(entityId);

        TransferInStatus transferInStatus = transferIn.getTransferInStatus();

        if(transferInStatus.getCode().equalsIgnoreCase("C") || transferInStatus.getCode().equalsIgnoreCase("R")){
            throw new EntityExistsException("Transfer In Application is already in " + transferInStatus.getTitle());
        }

        TransferInStatus rejectedStatus = transferInStatusRepository.findByCodeAndIsActive("C", true);

        transferIn.setTransferInStatus(rejectedStatus);

        transferInRepository.save(transferIn);

    }

    @Override
    public TransferIn saveAnnexueKDetails(TransferIn transferIn, SaveAnnexurekDTO saveAnnexurekDTO) {

        TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

        if(transferInFinalDetails == null){
            transferInFinalDetails = new TransferInFinalDetails();
        }

        transferInFinalDetails.setTransferIn(transferIn);

        transferInFinalDetails.setYear(FinancialYearAndMonth
                .getFinancialYear(saveAnnexurekDTO.getPostingDate()));
        transferInFinalDetails.setContributedIn(FinancialYearAndMonth
                .getFinancialMonth(saveAnnexurekDTO.getPostingDate()));

        transferInFinalDetails.setMemberContribution(saveAnnexurekDTO.getMemberContribution());
        transferInFinalDetails.setCompanyContribution(saveAnnexurekDTO.getCompanyContribution());
        transferInFinalDetails.setVpfContribution(new BigDecimal(0));

        transferInFinalDetails.setTransferIn(transferIn);

        TransferInFinalDetails savedFinalDetails = transferInFinalDetailsRepository.save(transferInFinalDetails);

        TransferInStatus transferInStatus = new TransferInStatus();
        transferInStatus.setId(2L);

        transferIn.setTransferInStatus(transferInStatus);

        transferIn.setUpdatedAtTimestamp(new Date().getTime());

        transferIn.setPostingDate(new java.sql.Date(saveAnnexurekDTO.getPostingDate().getTime()));
        transferIn.setDateOfJoiningPrior(new java.sql.Date(saveAnnexurekDTO.getDateOfJoiningPrior().getTime()));

        transferIn.setBank(saveAnnexurekDTO.getBank());
        transferIn.setChequeNumber(saveAnnexurekDTO.getChequeNumber());
        transferIn.setRefNumber(saveAnnexurekDTO.getRefNumber());

        transferIn.setAnnexureKFile(saveAnnexurekDTO.getAnnexureKFile());
        transferIn.setDispatchLetterFile(saveAnnexurekDTO.getDispatchLetterFile());

        transferIn.setTransferInFinalDetails(savedFinalDetails);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(2L);

        transferIn.setSapStatus(sapStatus);

        return transferInRepository.save(transferIn);

    }

    @Override
    public TransferInReminder getTransferInReminder(String entityId) {

        Optional<TransferInReminder> optional = transferInReminderRepository.findByEntityIdAndIsActive(entityId, true);

        if(optional.isEmpty()) throw new EntityNotFoundException("reminder not found");

        return optional.get();
    }


    @Override
    public List<TransferInReminder> getAllReminders(TransferIn transferIn) {
        return transferInReminderRepository.findAllReminders(transferIn, true);
    }


    @Override
    public TransferInReminder generateTransferInReminder(String entityId) {

        Optional<TransferInReminder> optional = transferInReminderRepository.findByEntityIdAndIsActive(entityId, true);

        if(optional.isEmpty()){
            throw new EntityNotFoundException("Transfer in reminder not found");
        }

        TransferInReminder reminder = optional.get();

        TransferInReminder reminder2 = new TransferInReminder();

        reminder2.setReminder(reminder);

        reminder2.setTransferIn(reminder.getTransferIn());

        return transferInReminderRepository.save(reminder2);
    }

    @Override
    public TransferInEmailStatus createEmailStatus(TransferIn transferIn) {

        Employee employee = transferIn.getEmployee();

        PreviousCompany previousCompany = transferIn.getPreviousCompany();

        TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

        TransferInEmailStatus transferInEmailStatus = new TransferInEmailStatus();
        transferInEmailStatus.setPfNumber(employee.getPfNumber());
        transferInEmailStatus.setName(employee.getName());
        transferInEmailStatus.setUnitCode(employee.getUnitCode());
        transferInEmailStatus.setPostingDate(transferIn.getPostingDate());

        transferInEmailStatus.setTotalContribution(transferInFinalDetails.getTotal());

        transferInEmailStatus.setPrevCompany(previousCompany.getName());

        transferInEmailStatus.setTransferIn(transferIn);
        transferInEmailStatus.setSent(false);

        String alternateEmailId = transferIn.getAlternateEmailId();
        String email = employee.getEmail();

        if(alternateEmailId != null && !alternateEmailId.isEmpty() && !alternateEmailId.isBlank()){
            transferInEmailStatus.setEmailId(alternateEmailId);
        } else if (email != null && !email.isEmpty() && !email.isBlank()) {
            transferInEmailStatus.setEmailId(email);
        }

        return transferInEmailStatusRepository.save(transferInEmailStatus);

    }

    @Override
    public void updateTransferInEmailStatus(TransferInEmailStatus transferInEmailStatus) {
        transferInEmailStatus.setSent(true);
        transferInEmailStatusRepository.save(transferInEmailStatus);
    }

    @Override
    public void sendConfirmationEmail(List<TransferInEmailStatus> transferInEmailStatusList) {
        for (TransferInEmailStatus transferInEmailStatus:transferInEmailStatusList) {
            sendConfirmationEmail(transferInEmailStatus);
        }
    }

    @Override
    public void sendConfirmationEmail(TransferInEmailStatus transferInEmailStatus) {

        sendConfirmationEmail(transferInEmailStatus.getEmailId(), transferInEmailStatus.getName(),
                transferInEmailStatus.getTotalContribution(),
                transferInEmailStatus.getPfNumber(), transferInEmailStatus.getPostingDate(),
                transferInEmailStatus.getPrevCompany());

    }

    @Async
    @ApplyTenantFilter
    public void sendConfirmationEmail(String emailId, String name, BigDecimal totalAmount, String pfNumber,
                                      Date postingDate, String prevEmployer) {

        String subject = " Subject: PF Notifications";

        Optional<NotificationEmailDesign> optional = notificationEmailDesignRepository
                .findByDocumentType(NotificationEmailDesign.TRANSFER_IN_COMPLETION);

        if(optional.isEmpty()) throw new EntityNotFoundException("Email Design Not Found");

        NotificationEmailDesign notificationEmailDesign = optional.get();

        String emailBody = notificationEmailDesign.getDocument().replace("{{name}}", name)
                .replace("{{amount}}", NumberFormatter.formatNumbers(totalAmount))
                .replace("{{pfnumber}}", pfNumber)
                .replace("{{date}}", DateFormatterUtil.formatDateR(postingDate))
                .replace("{{company}}", prevEmployer);

        emailService.SendEmail(emailId, subject, emailBody);

    }

    @Override
    public List<TransferInEmailStatus> getEmailStatusListNotSent() {
        return transferInEmailStatusRepository.findAllByIsSentAndIsActive(false, true);
    }

    @Override
    public List<TransferInEmailStatus> getTransferInEmailStatusList(List<String> entityIds) {

        Set<String> set = new HashSet<>(entityIds);

        return transferInEmailStatusRepository.findAllByIsActiveAndEntityIdIn(true, set);

    }

}
