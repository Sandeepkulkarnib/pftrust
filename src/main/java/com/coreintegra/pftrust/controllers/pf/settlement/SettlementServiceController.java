package com.coreintegra.pftrust.controllers.pf.settlement;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.BankSheetDTO;
import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.settlement.SettlementAnnexure;
import com.coreintegra.pftrust.dtos.pdf.settlement.SettlementDispatchLetter;
import com.coreintegra.pftrust.dtos.pdf.settlement.Worksheet;
import com.coreintegra.pftrust.entities.base.EmailAttachment;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.Document;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDTO;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDetails;
import com.coreintegra.pftrust.entities.pf.department.dtos.LaunchJobDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.Nominee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.loan.LoanDocument;
import com.coreintegra.pftrust.entities.pf.settlement.*;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SearchSettlementDTO;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementEmailDTO;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.department.BankRepository;
import com.coreintegra.pftrust.repositories.pf.department.PaymentModeRepository;
import com.coreintegra.pftrust.repositories.pf.settlement.SettlementStatusRepository;
import com.coreintegra.pftrust.services.pf.contribution.ContributionService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import com.coreintegra.pftrust.services.pf.pdf.GenerateSettlementAnnuxure;
import com.coreintegra.pftrust.services.pf.pdf.GenerateSettlementDispatchLetter;
import com.coreintegra.pftrust.services.pf.pdf.GenerateSettlementWorkSheet;
import com.coreintegra.pftrust.services.pf.settlement.SettlementService;
import com.coreintegra.pftrust.services.pf.yearend.YearEndProcessService;
import com.coreintegra.pftrust.services.storage.FileStorageService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.coreintegra.pftrust.util.NumberFormatter;
import com.coreintegra.pftrust.util.Response;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.mail.util.ByteArrayDataSource;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(SettlementServiceEndpoints.SETTLEMENT_SERVICE_ENDPOINT)
public class SettlementServiceController {

    private final Path fileStorageLocation = Paths.get("")
            .toAbsolutePath().normalize();

    private final SettlementService settlementService;
    private final JobLaunchService jobLaunchService;
    private final BankRepository bankRepository;
    private final PaymentModeRepository paymentModeRepository;
    private final EmployeeService employeeService;
    private final GenerateSettlementWorkSheet generateSettlementWorkSheet;
    private final GenerateSettlementDispatchLetter generateSettlementDispatchLetter;
    private final GenerateSettlementAnnuxure generateSettlementAnnuxure;
    private final FileStorageService fileStorageService;
    private final ContributionService contributionService;
    private final SettlementStatusRepository settlementStatusRepository;

    public SettlementServiceController(SettlementService settlementService,
                                       JobLaunchService jobLaunchService,
                                       BankRepository bankRepository,
                                       PaymentModeRepository paymentModeRepository,
                                       EmployeeService employeeService,
                                       YearEndProcessService yearEndProcessService,
                                       GenerateSettlementWorkSheet generateSettlementWorkSheet,
                                       GenerateSettlementDispatchLetter generateSettlementDispatchLetter,
                                       GenerateSettlementAnnuxure generateSettlementAnnuxure,
                                       FileStorageService fileStorageService, ContributionService contributionService,SettlementStatusRepository settlementStatusRepository) {
        this.settlementService = settlementService;
        this.jobLaunchService = jobLaunchService;
        this.bankRepository = bankRepository;
        this.paymentModeRepository = paymentModeRepository;
        this.employeeService = employeeService;
        this.generateSettlementWorkSheet = generateSettlementWorkSheet;
        this.generateSettlementDispatchLetter = generateSettlementDispatchLetter;
        this.generateSettlementAnnuxure = generateSettlementAnnuxure;
        this.fileStorageService = fileStorageService;
        this.contributionService = contributionService;
        this.settlementStatusRepository = settlementStatusRepository;
    }

    @GetMapping
    @ApplyTenantFilter
    public ResponseEntity<Object> searchSettlements(
            @RequestParam(value = "search", required = false, defaultValue = "")String search,
            @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10")Integer size){

        Page<Settlement> settlements = settlementService.getSettlements(search, size, page);

        Page<SearchSettlementDTO> searchSettlementDTOS = settlements.map(settlement -> new SearchSettlementDTO(
                settlement.getEntityId(),
                settlement.getEmployee().getPernNumber(), settlement.getEmployee().getPfNumber(),
                settlement.getEmployee().getName(), settlement.getEmployee().getUnitCode(),
                settlement.getDateOfLeavingService(), settlement.getDateOfSettlement(),
                settlement.getPayeeName(), settlement.getPaymentDate(),
                settlement.getSettlementType().getTitle(), settlement.getSettlementStatus().getTitle()));

        return Response.success(searchSettlementDTOS);
    }

    @GetMapping("/jobs/details")
    @ApplyTenantFilter
    public ResponseEntity<Object> getJob(@RequestParam("entityId")String entityId){

        Job job = jobLaunchService.getJob(entityId);

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        JobDTO jobDTO = new JobDTO(job.getEntityId(), job.getCreatedAt(), job.getUpdatedAt(),
                Instant.parse(jobDetailsJson.getString("startedAt")),
                jobDetailsJson.has("completedAt") ? Instant.parse(jobDetailsJson.getString("completedAt")) : null,
                job.getName(), jobDetailsJson.getString("status"),
                jobDetailsJson.has("duration") ? jobDetailsJson.getLong("duration") : 0);

        jobDTO.setFileName(jobDetailsJson.has("fileName") ? jobDetailsJson.getString("fileName") : "");

        if(!jobDetailsJson.has("details")){
            jobDTO.setJobDetailsList(new ArrayList<>());
            return Response.success(jobDTO);
        }

        JSONObject details = jobDetailsJson.getJSONObject("details");

        List<JobDetails> jobDetailsList = details.keySet().stream().map(key -> {

            JSONArray jsonArray = details.getJSONArray(key);

            if (jsonArray.length() == 2) {
                JSONObject jsonObject = jsonArray.getJSONObject(1);

                return new JobDetails(key, Instant.parse(jsonObject.getString("startedAt")),
                        jsonObject.has("completedAt") ? Instant.parse(jsonObject.getString("completedAt")) : null,
                        jsonObject.has("duration") ? jsonObject.getLong("duration") : 0,
                        jsonObject.has("success") ? jsonObject.getLong("success") : 0,
                        jsonObject.has("failed") ? jsonObject.getLong("failed") : 0,
                        jsonObject.getString("status"),
                        jsonObject.has("total") ? jsonObject.getLong("total") : 0);

            } else {
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                return new JobDetails(key, Instant.parse(jsonObject.getString("startedAt")),
                        jsonObject.has("completedAt") ? Instant.parse(jsonObject.getString("completedAt")) : null,
                        jsonObject.has("duration") ? jsonObject.getLong("duration") : 0,
                        jsonObject.getLong("success"),
                        jsonObject.getLong("failed"), jsonObject.getString("status"),
                        jsonObject.getLong("total"));
            }

        }).collect(Collectors.toList());

        jobDTO.setJobDetailsList(jobDetailsList);

        return Response.success(jobDTO);
    }


    @PostMapping("/report")
    @ApplyTenantFilter
    public ResponseEntity<Object> generateEmployeeReport(@RequestBody LaunchJobDTO jobDTO){
        Job job = jobLaunchService.launchReportJob("settlement_report_job", jobDTO.getParams());
        return Response.success(job);
    }

    @GetMapping("/download")
    @ApplyTenantFilter
    public void downloadfile(@RequestParam("filePath") String filePath,
                             HttpServletResponse response) {
        try {
            Path targetLocation = this.fileStorageLocation.resolve(filePath);
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filePath + "\"");
            FileInputStream fileInputStream = new FileInputStream(targetLocation.toFile());

            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1048576];
            int i;
            while ((i = fileInputStream.read(b)) != -1) {
                out.write(b, 0, i);
            }
            fileInputStream.close();
            out.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @GetMapping("/essentials")
    @ApplyTenantFilter
    public ResponseEntity<Object> getUnitCodes(){
        EssentialsDTO essentials = settlementService.getEssentials();
        return Response.success(essentials);
    }

    @GetMapping("/details/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getSettlement(@PathVariable String entityId) {

        Settlement settlement = settlementService.getSettlement(entityId);

        Employee employee = settlement.getEmployee();
        SettlementFinalDetails settlementFinalDetails = settlement.getSettlementFinalDetails();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("dateOfJoining", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("email", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("dateOfLeaving", DateFormatterUtil.formatDateR(settlement.getDateOfLeavingService()));
        jsonObject.put("unitCode",employee.getUnitCode());
        jsonObject.put("dateOfSettlement", settlement.getDateOfSettlement());
        jsonObject.put("paymentBank", settlement.getBank().getName());
        jsonObject.put("paymentMode", settlement.getPaymentMode().getMode());
        
        if(settlement.getSettlementStatus().getTitle().equals("Completed")) {
            
            jsonObject.put("paymentNumber", settlement.getChequeNo());
            jsonObject.put("paymentDate", DateFormatterUtil.formatDateR(settlement.getPaymentDate()));
        }
        
        List<PreviousCompany> previousCompanies = employee.getPreviousCompanies();

        Date date = employee.getDateOfJoiningPF();

        if(!previousCompanies.isEmpty()){
            jsonObject.put("dateOfJoiningPrior", DateFormatterUtil.formatDateR(previousCompanies.get(0).getDateOfJoining()));
            if(previousCompanies.get(0).getDateOfJoining() != null){
                date = previousCompanies.get(0).getDateOfJoining();
            }
        }

        jsonObject.put("membershipYears", DateFormatterUtil.getPeriodBetweenDate(date, new Date()).getYears());

        jsonObject.put("dateOfBirth", DateFormatterUtil.formatDateR(employee.getDateOfBirth()));
        jsonObject.put("status", employee.getContributionStatus().getDescription());
        jsonObject.put("age", employee.getEmployeeAge());

        jsonObject.put("netCredit", NumberFormatter.formatNumbers(settlementFinalDetails.getNetCreditAmount()));

        jsonObject.put("altEmailId", settlement.getAltEmailId());
        jsonObject.put("altContactNumber", settlement.getAltContactNumber());

        jsonObject.put("settlementType", settlement.getSettlementType().getTitle());
        jsonObject.put("settlementStatus", settlement.getSettlementStatus().getTitle());

        jsonObject.put("isInProgress", settlement.getSettlementStatus().getTitle().equalsIgnoreCase("In Process"));
        jsonObject.put("isCompleted", settlement.getSettlementStatus().getTitle().equalsIgnoreCase("Completed"));
        jsonObject.put("isCanceled", settlement.getSettlementStatus().getTitle().equalsIgnoreCase("Canceled"));
        jsonObject.put("isPaymentPending", settlement.getSettlementStatus().getTitle().equalsIgnoreCase("Payment Pending"));

        jsonObject.put("dateOfLeavingService", DateFormatterUtil.formatDateR(settlement.getDateOfLeavingService()));
        jsonObject.put("settlementDate", DateFormatterUtil.formatDateR(settlement.getDateOfSettlement()));

        jsonObject.put("addressLine1", settlement.getAddressLine1());
        jsonObject.put("addressLine2", settlement.getAddressLine2());
        jsonObject.put("addressLine3", settlement.getAddressLine3());
        jsonObject.put("addressLine4", settlement.getAddressLine4());

        jsonObject.put("eduCess", NumberFormatter.formatNumbers(settlementFinalDetails.getEduCess()));
        jsonObject.put("incomeTax", NumberFormatter.formatNumbers(settlementFinalDetails.getIncomeTax()));
        jsonObject.put("tds", NumberFormatter.formatNumbers(BigDecimal.ZERO.add(settlementFinalDetails.getEduCess() == null ? BigDecimal.ZERO : settlementFinalDetails.getEduCess())
                .add(settlementFinalDetails.getIncomeTax() == null ? BigDecimal.ZERO: settlementFinalDetails.getIncomeTax())));

        jsonObject.put("bankName", settlement.getBankName());
        jsonObject.put("accountNumber", settlement.getAccountNumber());
        jsonObject.put("payeeName", settlement.getPayeeName());
        jsonObject.put("micrCode", settlement.getMicrCode());
        jsonObject.put("ifscCode", settlement.getIfscCode());

        List<SettlementDocument> settlementDocuments = settlement.getSettlementDocuments();

        JSONArray uploadedDocuments = new JSONArray();

        for (SettlementDocument settlementDocument:settlementDocuments) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("name", settlementDocument.getDocument().getName());
            jsonObject1.put("path", settlementDocument.getPath());
            uploadedDocuments.put(jsonObject1);
        }

        jsonObject.put("uploadedDocuments", uploadedDocuments);

        List<PaymentMode> paymentModeList = paymentModeRepository.findAllByIsActive(true);
        List<Bank> bankList = bankRepository.findAllByIsActive(true);

        List<JSONObject> mappedPaymentModes = paymentModeList.stream().map(paymentMode -> {
                    JSONObject paymentModeObject = new JSONObject();
                    paymentModeObject.put("name", paymentMode.getMode());
                    paymentModeObject.put("code", paymentMode.getCode());
                    paymentModeObject.put("entityId", paymentMode.getEntityId());
                    return paymentModeObject;
                })
                .collect(Collectors.toList());

        jsonObject.put("paymentModes", new JSONArray(mappedPaymentModes));

        List<JSONObject> mappedBanks = bankList.stream().map(bank -> {
                    JSONObject bankObject = new JSONObject();
                    bankObject.put("name", bank.getName());
                    bankObject.put("entityId", bank.getEntityId());
                    return bankObject;
                })
                .collect(Collectors.toList());

        jsonObject.put("banks", new JSONArray(mappedBanks));

        return Response.success(jsonObject.toString());

    }

    @PutMapping("/savePaymentDetails/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> savePaymentDetails(@PathVariable String entityId, @RequestBody String request) throws ParseException, JSONException, JPAException {

        Settlement settlement = settlementService.getSettlement(entityId);

        JSONObject jsonObject = new JSONObject(request);

        Date paymentDate = FinancialYearAndMonth.getDate(jsonObject.getString("paymentDate"), "yyyy-MM-dd");

        Optional<Bank> optionalBank = bankRepository.findByEntityIdAndIsActive(jsonObject.getString("bank"), true);

        if(optionalBank.isEmpty()) throw new EntityNotFoundException("Selected Bank Not Found");

        Optional<PaymentMode> optionalPaymentMode = paymentModeRepository
                .findByEntityIdAndIsActive(jsonObject.getString("paymentMode"), true);

        if(optionalPaymentMode.isEmpty()) throw new EntityNotFoundException("Payment Mode Not Found");

        Settlement savedSettlement = settlementService.savePaymentDetails(settlement, paymentDate, jsonObject.getString("referenceNumber"),
                optionalBank.get(), optionalPaymentMode.get());

        SettlementEmailStatus emailStatus = settlementService.createEmailStatus(savedSettlement);

        if(jsonObject.getBoolean("sendConfirmationMail")){

            try {

                Worksheet workSheet = settlementService.getWorkSheet(settlement);

                String worksheet_file = "worksheet_" + settlement.getEmployee().getPfNumber() + ".pdf";

                ByteArrayOutputStream worksheet = generateSettlementWorkSheet.generate(workSheet);


                SettlementDispatchLetter dispatchLetter = settlementService.getDispatchLetter(settlement);

                String dispatch_letter_file = "dispatch_letter_" + settlement.getEmployee().getPfNumber() + ".pdf";

                ByteArrayOutputStream dispatch_letter = generateSettlementDispatchLetter.generate(dispatchLetter);

                EmailAttachment worksheetAttachment = new EmailAttachment(worksheet_file,
                        new ByteArrayDataSource(worksheet.toByteArray(), "application/octet-stream"));

                EmailAttachment dispatchLetterAttachment = new EmailAttachment(dispatch_letter_file,
                        new ByteArrayDataSource(dispatch_letter.toByteArray(),
                                "application/octet-stream"));

                settlementService.sendConfirmationEmail(emailStatus, List.of(worksheetAttachment, dispatchLetterAttachment));

                settlementService.updateEmailStatus(emailStatus);

            }catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

        }

        return Response.success("payment details updated successfully.");

    }

    @PutMapping("/reject/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> reject(@PathVariable String entityId) {
        settlementService.reject(entityId);
        return Response.success("status updated successfully.");
    }

    @GetMapping("/emailList")
    @ApplyTenantFilter
    public ResponseEntity<Object> getTransferOutListToSendEmails(){

        List<SettlementEmailStatus> emailStatusListNotSent = settlementService.getEmailStatusListNotSent();

        List<SettlementEmailDTO> settlementEmailDTOList = emailStatusListNotSent.stream()
                .map(settlementEmailStatus -> new SettlementEmailDTO(
                        settlementEmailStatus.getEntityId(),
                        settlementEmailStatus.getEmailId(),
                        settlementEmailStatus.getName(), settlementEmailStatus.getUnitCode(),
                        settlementEmailStatus.getPfNumber(), settlementEmailStatus.getPernNumber(),
                        settlementEmailStatus.getAccountNumber(), settlementEmailStatus.getEmployeeBank(),
                        settlementEmailStatus.getPaymentDate(), settlementEmailStatus.getNetCredit()))
                .collect(Collectors.toList());

        return Response.success(settlementEmailDTOList);

    }

    @PostMapping("/sendEmails")
    @ApplyTenantFilter
    public ResponseEntity<Object> sendConfirmationEmails(@RequestBody List<String> entityIds){

        List<SettlementEmailStatus> settlementEmailStatusList = settlementService.getSettlementEmailStatusList(entityIds);

        settlementEmailStatusList.forEach(settlementEmailStatus -> {
            try {

                Settlement settlement = settlementEmailStatus.getSettlement();

                Worksheet workSheet = settlementService.getWorkSheet(settlement);

                String worksheet_file = "worksheet_" + settlement.getEmployee().getPfNumber() + ".pdf";

                ByteArrayOutputStream worksheet = generateSettlementWorkSheet.generate(workSheet);


                SettlementDispatchLetter dispatchLetter = settlementService.getDispatchLetter(settlement);

                String dispatch_letter_file = "dispatch_letter_" + settlement.getEmployee().getPfNumber() + ".pdf";

                ByteArrayOutputStream dispatch_letter = generateSettlementDispatchLetter.generate(dispatchLetter);

                EmailAttachment worksheetAttachment = new EmailAttachment(worksheet_file,
                        new ByteArrayDataSource(worksheet.toByteArray(), "application/octet-stream"));

                EmailAttachment dispatchLetterAttachment = new EmailAttachment(dispatch_letter_file,
                        new ByteArrayDataSource(dispatch_letter.toByteArray(),
                        "application/octet-stream"));

                settlementService.sendConfirmationEmail(settlementEmailStatus,
                        List.of(worksheetAttachment, dispatchLetterAttachment));

                settlementService.updateEmailStatus(settlementEmailStatus);
            }catch (Exception exception){
                System.out.println(exception.getLocalizedMessage());
            }
        });

        return Response.success("all emails sent successfully.");
    }

    @GetMapping("/employeeDetails/{pernNumber}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable String pernNumber) throws JPAException {

        Employee employee = employeeService.getEmployeeByPern(pernNumber);

        if(employee.isSettled() || employee.isMerged()){
            throw new JPAException("Employee is Already " + employee.getContributionStatus().getDescription());
        }

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("emailId", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("dateOfJoiningService", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("unitCode",employee.getUnitCode());

        List<PreviousCompany> previousCompanies = employee.getPreviousCompanies();

        Date date = employee.getDateOfJoiningPF();

        if(!previousCompanies.isEmpty()){
            jsonObject.put("dateOfJoiningPrior", DateFormatterUtil.formatDateR(previousCompanies.get(0).getDateOfJoining()));
            if(previousCompanies.get(0).getDateOfJoining() != null){
                date = previousCompanies.get(0).getDateOfJoining();
            }
        }

        jsonObject.put("membershipYears", DateFormatterUtil.getPeriodBetweenDate(date, new Date()).getYears());
        jsonObject.put("dateOfBirth", DateFormatterUtil.formatDateR(employee.getDateOfBirth()));
        jsonObject.put("age", employee.getEmployeeAge());
        jsonObject.put("status", employee.getContributionStatus().getSymbol());

        List<Nominee> nominees = employee.getNominees();

        JSONArray nomineesArray = new JSONArray();

        for (Nominee nominee:nominees) {

            JSONObject nomineeObject = new JSONObject();
            nomineeObject.put("name", nominee.getName());
            nomineeObject.put("relationship", nominee.getRelationship());
            nomineeObject.put("share", nominee.getShare());
            nomineeObject.put("entityId", nominee.getEntityId());

            nomineesArray.put(nomineeObject);

        }

        jsonObject.put("nominees", nomineesArray);

        try {
            Contribution openingBalance = contributionService.getContribution(employee,
                    FinancialYearAndMonth.getFinancialYear(new Date()), 0);
        }catch (EntityNotFoundException exception){
            jsonObject.put("yearEndProcessError", true);
            jsonObject.put("yearEndProcessErrorMessage", "Please perform year end process for the employee before proceeding.");
        }

        List<SettlementType> settlementTypes = settlementService.getSettlementTypes();

        JSONArray settlementTypeArray = new JSONArray();

        for (SettlementType settlementType:settlementTypes) {
            JSONObject settlementTypeObject = new JSONObject();
            settlementTypeObject.put("title", settlementType.getTitle());
            settlementTypeObject.put("entityId", settlementType.getEntityId());
            settlementTypeObject.put("fromDateOfLeaving",settlementType.getFromDateOfLeaving());
            settlementTypeArray.put(settlementTypeObject);
        }

        jsonObject.put("settlementTypes", settlementTypeArray);
        jsonObject.put("maxDate", DateFormatterUtil.format(new Date(), "yyyy-MM-dd"));
        
        List<Bank> bankList = bankRepository.findAllByIsActive(true);

        List<JSONObject> mappedBanks = bankList.stream().map(bank -> {
                            JSONObject bankObject = new JSONObject();
                            bankObject.put("name", bank.getName());
                            bankObject.put("entityId", bank.getEntityId());
                            return bankObject;
                        })
                        .collect(Collectors.toList());

        jsonObject.put("banks", new JSONArray(mappedBanks));
        
        List<PaymentMode> paymentModeList = paymentModeRepository.findAllByIsActive(true);

        List<JSONObject> mappedPaymentModes = paymentModeList.stream().map(paymentMode -> {
                    JSONObject paymentModeObject = new JSONObject();
                    paymentModeObject.put("id", paymentMode.getId());
                    paymentModeObject.put("name", paymentMode.getMode());
                    paymentModeObject.put("code", paymentMode.getCode());
                    paymentModeObject.put("entityId", paymentMode.getEntityId());
                    return paymentModeObject;
                })
                .collect(Collectors.toList());

        jsonObject.put("paymentModes", new JSONArray(mappedPaymentModes));

        return Response.success(jsonObject.toString());

    }

    @GetMapping("/employeeDetailsByPf/{pfNumber}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployeeDetailsByPf(@PathVariable String pfNumber) throws JPAException {

        Employee employee = employeeService.getEmployeeByPf(pfNumber);

        if(employee.isSettled() || employee.isMerged()){
            throw new JPAException("Employee is Already " + employee.getContributionStatus().getDescription());
        }

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("emailId", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("dateOfJoiningService", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("unitCode",employee.getUnitCode());

        List<PreviousCompany> previousCompanies = employee.getPreviousCompanies();

        Date date = employee.getDateOfJoiningPF();

        if(!previousCompanies.isEmpty()){
            jsonObject.put("dateOfJoiningPrior", DateFormatterUtil.formatDateR(previousCompanies.get(0).getDateOfJoining()));
            if(previousCompanies.get(0).getDateOfJoining() != null){
                date = previousCompanies.get(0).getDateOfJoining();
            }
        }

        jsonObject.put("membershipYears", DateFormatterUtil.getPeriodBetweenDate(date, new Date()).getYears());
        jsonObject.put("dateOfBirth", DateFormatterUtil.formatDateR(employee.getDateOfBirth()));
        jsonObject.put("age", employee.getEmployeeAge());
        jsonObject.put("status", employee.getContributionStatus().getSymbol());

        List<Nominee> nominees = employee.getNominees();

        JSONArray nomineesArray = new JSONArray();

        for (Nominee nominee:nominees) {

            JSONObject nomineeObject = new JSONObject();
            nomineeObject.put("name", nominee.getName());
            nomineeObject.put("relationship", nominee.getRelationship());
            nomineeObject.put("share", nominee.getShare());
            nomineeObject.put("entityId", nominee.getEntityId());

            nomineesArray.put(nomineeObject);

        }

        jsonObject.put("nominees", nomineesArray);

        try {
            Contribution openingBalance = contributionService.getContribution(employee,
                    FinancialYearAndMonth.getFinancialYear(new Date()), 0);
        }catch (EntityNotFoundException exception){
            jsonObject.put("yearEndProcessError", true);
            jsonObject.put("yearEndProcessErrorMessage", "Please perform year end process for the employee before proceeding.");
        }

        List<SettlementType> settlementTypes = settlementService.getSettlementTypes();

        JSONArray settlementTypeArray = new JSONArray();

        for (SettlementType settlementType:settlementTypes) {
            JSONObject settlementTypeObject = new JSONObject();
            settlementTypeObject.put("title", settlementType.getTitle());
            settlementTypeObject.put("entityId", settlementType.getEntityId());
            settlementTypeObject.put("fromDateOfLeaving",settlementType.getFromDateOfLeaving());
            settlementTypeArray.put(settlementTypeObject);
        }

        jsonObject.put("settlementTypes", settlementTypeArray);
        jsonObject.put("maxDate", DateFormatterUtil.format(new Date(), "yyyy-MM-dd"));
        
        List<Bank> bankList = bankRepository.findAllByIsActive(true);

        List<JSONObject> mappedBanks = bankList.stream().map(bank -> {
                            JSONObject bankObject = new JSONObject();
                            bankObject.put("name", bank.getName());
                            bankObject.put("entityId", bank.getEntityId());
                            return bankObject;
                        })
                        .collect(Collectors.toList());

        jsonObject.put("banks", new JSONArray(mappedBanks));
        
        List<PaymentMode> paymentModeList = paymentModeRepository.findAllByIsActive(true);

        List<JSONObject> mappedPaymentModes = paymentModeList.stream().map(paymentMode -> {
                    JSONObject paymentModeObject = new JSONObject();
                    paymentModeObject.put("id", paymentMode.getId());
                    paymentModeObject.put("name", paymentMode.getMode());
                    paymentModeObject.put("code", paymentMode.getCode());
                    paymentModeObject.put("entityId", paymentMode.getEntityId());
                    return paymentModeObject;
                })
                .collect(Collectors.toList());

        jsonObject.put("paymentModes", new JSONArray(mappedPaymentModes));

        return Response.success(jsonObject.toString());

    }

    @GetMapping("/documents")
    @ApplyTenantFilter
    public ResponseEntity<Object> getDocuments(@RequestParam String entityId){

        List<Document> documents = settlementService.getDocumentsForSettlementType(entityId);

        JSONArray documentsArray = new JSONArray();

        for (Document document:documents) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("entityId", document.getEntityId());
            jsonObject.put("name", document.getName());
            documentsArray.put(jsonObject);
        }

        return Response.success(documentsArray.toString());

    }

    @PostMapping("")
    @ApplyTenantFilter
    public ResponseEntity<Object> create(@RequestBody String requestBody) throws ParseException, JPAException {

        JSONObject jsonObject = new JSONObject(requestBody);

        Employee employee = employeeService.getEmployeeByPern(jsonObject.getString("pernNumber"));

        Settlement settlement = Settlement.build(jsonObject);

        SettlementType settlementType = settlementService.getSettlementType(jsonObject.getString("settlementType"));

        settlement.setSettlementType(settlementType);
        
        Bank bank = bankRepository.findByNameAndIsActive(jsonObject.getString("paymentBank"), true);
        
        PaymentMode paymentMode = paymentModeRepository.findByIdAndIsActive(jsonObject.getLong("paymentModeId"), true);
        
        settlement.setPaymentMode(paymentMode);
        
        List<SettlementDocument> settlementDocuments = settlementService.getSettlementDocuments(jsonObject.getJSONArray("documents"));

        Settlement createdSettlement = settlementService.create(settlement, employee, bank);

        settlementService.saveSettlementDocuments(settlementDocuments, createdSettlement);

        JSONObject responseObj = new JSONObject();
        responseObj.put("id", createdSettlement.getEntityId());
        responseObj.put("message", "Settlement Application Created Successfully with Application ID " + createdSettlement.getEntityId());

        return Response.success(responseObj.toString());
    }

    @GetMapping("/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> get(@PathVariable String entityId){

        Settlement settlement = settlementService.getSettlement(entityId);

        Employee employee = settlement.getEmployee();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("emailId", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("dateOfJoiningService", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("unitCode",employee.getUnitCode());

        List<PreviousCompany> previousCompanies = employee.getPreviousCompanies();

        Date date = employee.getDateOfJoiningPF();

        if(!previousCompanies.isEmpty()){
            jsonObject.put("dateOfJoiningPrior", DateFormatterUtil.formatDateR(previousCompanies.get(0).getDateOfJoining()));
            if(previousCompanies.get(0).getDateOfJoining() != null){
                date = previousCompanies.get(0).getDateOfJoining();
            }
        }

        jsonObject.put("membershipYears", DateFormatterUtil.getPeriodBetweenDate(date, new Date()).getYears());
        jsonObject.put("dateOfBirth", DateFormatterUtil.formatDateR(employee.getDateOfBirth()));
        jsonObject.put("age", employee.getEmployeeAge());
        jsonObject.put("status", employee.getContributionStatus().getSymbol());

        List<Nominee> nominees = employee.getNominees();

        JSONArray nomineesArray = new JSONArray();

        for (Nominee nominee:nominees) {

            JSONObject nomineeObject = new JSONObject();
            nomineeObject.put("name", nominee.getName());
            nomineeObject.put("relationship", nominee.getRelationship());
            nomineeObject.put("share", nominee.getShare());

            nomineesArray.put(nomineeObject);

        }

        jsonObject.put("nominees", nomineesArray);

        jsonObject.put("dateOfLeavingService", DateFormatterUtil.format(settlement.getDateOfLeavingService(), "yyyy-MM-dd"));
        jsonObject.put("dateOfSettlement", DateFormatterUtil.format(settlement.getDateOfSettlement(), "yyyy-MM-dd"));

        jsonObject.put("altEmailId", settlement.getAltEmailId());
        jsonObject.put("altContactNumber", settlement.getAltContactNumber());

        jsonObject.put("payeeName", settlement.getPayeeName());
        jsonObject.put("addressLine1", settlement.getAddressLine1());
        jsonObject.put("addressLine2", settlement.getAddressLine2());
        jsonObject.put("addressLine3", settlement.getAddressLine3());
        jsonObject.put("addressLine4", settlement.getAddressLine4());

        jsonObject.put("bankName", settlement.getBankName());
        jsonObject.put("branch", settlement.getBranch());
        jsonObject.put("accountNumber", settlement.getAccountNumber());
        jsonObject.put("ifscCode", settlement.getIfscCode());
        jsonObject.put("micrCode", settlement.getMicrCode());
        jsonObject.put("swift", settlement.getSwift());

        jsonObject.put("incomeTax", settlement.getIncomeTax());
        jsonObject.put("eduCess", settlement.getEduCess());
        jsonObject.put("settlementType", settlement.getSettlementType().getEntityId());
        jsonObject.put("paymentBank", settlement.getBank().getName());

        List<Document> documents = settlementService
                .getDocumentsForSettlementType(settlement.getSettlementType().getEntityId());

        JSONArray documentsArray = new JSONArray();

        for (Document document:documents) {
            JSONObject documentObject = new JSONObject();
            documentObject.put("entityId", document.getEntityId());
            documentObject.put("name", document.getName());
            documentsArray.put(documentObject);
        }

        jsonObject.put("documents", documentsArray);

        JSONArray uploadedDocuments = new JSONArray();

        settlement.getSettlementDocuments().forEach(settlementDocument -> {
            JSONObject documentObject = new JSONObject();
            documentObject.put("path", settlementDocument.getPath());
            documentObject.put("documentId", settlementDocument.getDocument().getEntityId());
            documentObject.put("entityId", settlementDocument.getEntityId());
            uploadedDocuments.put(documentObject);
        });

        jsonObject.put("uploadedDocuments", uploadedDocuments);

        List<SettlementType> settlementTypes = settlementService.getSettlementTypes();

        JSONArray settlementTypeArray = new JSONArray();

        for (SettlementType settlementType:settlementTypes) {
            JSONObject settlementTypeObject = new JSONObject();
            settlementTypeObject.put("title", settlementType.getTitle());
            settlementTypeObject.put("entityId", settlementType.getEntityId());
            settlementTypeArray.put(settlementTypeObject);
        }

        jsonObject.put("settlementTypes", settlementTypeArray);
        jsonObject.put("maxDate", DateFormatterUtil.format(new Date(), "yyyy-MM-dd"));
        
        List<Bank> bankList = bankRepository.findAllByIsActive(true);

        List<JSONObject> mappedBanks = bankList.stream().map(bank -> {
                            JSONObject bankObject = new JSONObject();
                            bankObject.put("name", bank.getName());
                            bankObject.put("entityId", bank.getEntityId());
                            return bankObject;
                        })
                        .collect(Collectors.toList());

        jsonObject.put("banks", new JSONArray(mappedBanks));

        return Response.success(jsonObject.toString());

    }

    @PutMapping("{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> update(@PathVariable String entityId, @RequestBody String requestBody) throws ParseException, JPAException {

        Settlement fetchedSettlement = settlementService.getSettlement(entityId);

        JSONObject jsonObject = new JSONObject(requestBody);

        Employee employee = employeeService.getEmployeeByPern(jsonObject.getString("pernNumber"));

        Settlement settlement = Settlement.build(fetchedSettlement, jsonObject);

        SettlementType settlementType = settlementService.getSettlementType(jsonObject.getString("settlementType"));
        
        Bank bank = bankRepository.findByNameAndIsActive(jsonObject.getString("paymentBank"), true);

        settlement.setSettlementType(settlementType);
        settlement.setBank(bank);

        Settlement createdSettlement = settlementService.update(settlement, employee);

        settlementService.updateSettlementDocuments(jsonObject.getJSONArray("documents"));

        JSONObject responseObj = new JSONObject();
        responseObj.put("id", createdSettlement.getEntityId());
        responseObj.put("message", "Settlement Application Updated Successfully with Application ID " + createdSettlement.getEntityId());

        return Response.success(responseObj.toString());

    }

    @GetMapping("{entityId}/getWorkSheet")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateWorkSheet(@PathVariable String entityId) throws Exception {

        Settlement settlement = settlementService.getSettlement(entityId);

        Worksheet workSheet = settlementService.getWorkSheet(settlement);

        String filename = "settlement_worksheet_" + settlement.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateSettlementWorkSheet.generate(workSheet);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/{entityId}/getDispatchLetter")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateDispatchLetter(@PathVariable String entityId) throws Exception {

        Settlement settlement = settlementService.getSettlement(entityId);

        SettlementDispatchLetter dispatchLetter = settlementService.getDispatchLetter(settlement);

        String filename = "settlement_dispatch_letter_" + settlement.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateSettlementDispatchLetter.generate(dispatchLetter);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/{entityId}/getSettlementAnnexure")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateSettlementAnnexure(@PathVariable String entityId) throws Exception {

        Settlement settlement = settlementService.getSettlement(entityId);

        SettlementAnnexure settlementAnnexure = settlementService.getSettlementAnnexure(settlement);

        String filename = "annexure_" + settlement.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateSettlementAnnuxure.generate(settlementAnnexure);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }


    @PostMapping(value = "/uploadDocuments/{entityId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApplyTenantFilter
    public ResponseEntity<Object> uploadDocuments(@PathVariable String entityId, HttpServletRequest httpServletRequest){

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        MultipartHttpServletRequest request = commonsMultipartResolver.resolveMultipart(httpServletRequest);

        Iterator<String> itr =  request.getFileNames();

        JSONObject jsonObject = new JSONObject();

        while (itr.hasNext()){

            String next = itr.next();

            MultipartFile file = request.getFile(next);

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            String fileName = next + "_" + new java.util.Date().getTime() + "_" + entityId + "." + extension;

            String storeFile = fileStorageService.storeFile(file, fileName);

            jsonObject.put("path", storeFile);
            jsonObject.put("entityId", entityId);

        }

        return Response.success(jsonObject.toString());

    }

    @GetMapping("/getForBankSheet")
    @ApplyTenantFilter
    public ResponseEntity<Object> getSettlementsForDates(@RequestParam String startDate,
                                                         @RequestParam String endDate,
                                                         @RequestParam String bankId) throws ParseException, JPAException {

        List<Settlement> settlements = settlementService.getSettlementsForBankSheet(
                FinancialYearAndMonth.getDate(startDate),
                FinancialYearAndMonth.getDate(endDate), bankId);

        if(settlements.isEmpty()) throw new JPAException("Settlements not found for given range and bank");

        List<BankSheetDTO> settlementDTOS = settlements.stream().map(settlement -> new BankSheetDTO(
                        settlement.getEntityId(), settlement.getEmployee().getPernNumber(),
                        settlement.getEmployee().getPfNumber(), settlement.getEmployee().getName(),
                        settlement.getEmployee().getUnitCode(), settlement.getDateOfLeavingService(),
                        settlement.getDateOfSettlement(), settlement.getPayeeName(), settlement.getPaymentDate(),
                        NumberFormatter.formatNumbers(settlement.getSettlementFinalDetails().getNetCreditAmount()),
                        settlement.getSettlementType().getTitle(), settlement.getSettlementStatus().getTitle()))
                .collect(Collectors.toList());

        return Response.success(settlementDTOS);
    }


    @PutMapping("/generateBankSheet")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateBankSheet(@RequestBody String body) throws Exception {

        JSONObject jsonObject = new JSONObject(body);

        Date paymentDate = FinancialYearAndMonth.getDate(jsonObject.getString("paymentDate"));

        List<String> entityIds = new ArrayList<>();

        JSONArray entityIdsArr = jsonObject.getJSONArray("entityIds");

        for (int i=0; i<entityIdsArr.length(); i++){
            String entityId = entityIdsArr.getString(i);
            entityIds.add(entityId);
        }

        List<Settlement> settlements = settlementService.getSettlements(entityIds);

        List<Settlement> updatedSettlements = settlementService.updatePaymentDate(settlements, paymentDate);

        List<String> bankSheetLines = settlementService.getBankSheetLines(updatedSettlements, paymentDate);

        String filename = "PF_SETTLEMENT_KOTAK_PD" + DateFormatterUtil.formatDatePay(paymentDate) + ".txt";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (String line:bankSheetLines) {
            out.writeBytes(line.getBytes(StandardCharsets.UTF_8));
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

    }

    @GetMapping("/banks")
    @ApplyTenantFilter
    public ResponseEntity<Object> getBanks(){

        List<Bank> banks = bankRepository.findAllByIsActive(true);

        JSONArray jsonArray = new JSONArray();

        banks.forEach(bank -> {
            JSONObject bankObject = new JSONObject();
            bankObject.put("name", bank.getName());
            bankObject.put("entityId", bank.getEntityId());
            jsonArray.put(bankObject);
        });

        return Response.success(jsonArray.toString());

    }

    @GetMapping("/downloadDocuments")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> downloadDocuments(@RequestParam String document) throws Exception {

        Path targetLocation = fileStorageService.resolvePath(document);

        byte[] bytes = Files.readAllBytes(targetLocation);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+document);
        headers.add("X-Suggested-Filename", document);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);

    }
    
    @GetMapping("/approveApplication/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> changeStatus(@PathVariable String entityId) throws ParseException {

        Settlement settlement = settlementService.getSettlement(entityId);

        String settlementStatusTitile = settlement.getSettlementStatus().getTitle();

        if(settlementStatusTitile.equalsIgnoreCase("In Process")) {
        	
        	SettlementStatus settlementStatus = settlementStatusRepository.findByTitleAndIsActive("Payment Pending", true);
        	settlement.setSettlementStatus(settlementStatus);
        	settlementService.updateSetllementStatus(settlement);
        	return Response.success("Application Aproved");
        } 
        
        return Response.failure("Approval failed");

    }


}
