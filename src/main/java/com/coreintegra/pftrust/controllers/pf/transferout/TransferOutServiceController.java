package com.coreintegra.pftrust.controllers.pf.transferout;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.settlement.Worksheet;
import com.coreintegra.pftrust.dtos.pdf.transferout.AnnuxureK;
import com.coreintegra.pftrust.dtos.pdf.transferout.TransferOutDispatchLetter;
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
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.loan.LoanDocument;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SearchSettlementDTO;
import com.coreintegra.pftrust.entities.pf.transferOut.*;
import com.coreintegra.pftrust.entities.pf.transferOut.dtos.SearchTransferOutDTO;
import com.coreintegra.pftrust.entities.pf.transferOut.dtos.TransferOutEmailDTO;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.department.BankRepository;
import com.coreintegra.pftrust.repositories.pf.department.PaymentModeRepository;
import com.coreintegra.pftrust.repositories.pf.transferout.TransferOutStatusRepository;
import com.coreintegra.pftrust.services.pf.contribution.ContributionService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import com.coreintegra.pftrust.services.pf.pdf.GenerateAnnuxurek;
import com.coreintegra.pftrust.services.pf.pdf.GenerateTransferOutDispatchLetter;
import com.coreintegra.pftrust.services.pf.pdf.GenerateTransferOutWorkSheet;
import com.coreintegra.pftrust.services.pf.transferout.TransferOutService;
import com.coreintegra.pftrust.services.pf.yearend.YearEndProcessService;
import com.coreintegra.pftrust.services.storage.FileStorageService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.coreintegra.pftrust.util.NumberFormatter;
import com.coreintegra.pftrust.util.Response;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.text.ParseException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(TransferOutServiceEndpoints.TRANSFER_OUT_SERVICE_ENDPOINT)
public class TransferOutServiceController {

    private final Path fileStorageLocation = Paths.get("")
            .toAbsolutePath().normalize();

    private final TransferOutService transferOutService;
    private final JobLaunchService jobLaunchService;
    private final PaymentModeRepository paymentModeRepository;
    private final BankRepository bankRepository;
    private final EmployeeService employeeService;
    private final GenerateAnnuxurek generateAnnuxurek;
    private final GenerateTransferOutDispatchLetter generateTransferOutDispatchLetter;
    private final GenerateTransferOutWorkSheet generateTransferOutWorkSheet;
    private final FileStorageService fileStorageService;
    private final ContributionService contributionService;
    private final TransferOutStatusRepository transferOutStatusRepository;

    public TransferOutServiceController(TransferOutService transferOutService,
                                        JobLaunchService jobLaunchService,
                                        PaymentModeRepository paymentModeRepository,
                                        BankRepository bankRepository, EmployeeService employeeService,
                                        YearEndProcessService yearEndProcessService,
                                        GenerateAnnuxurek generateAnnuxurek,
                                        GenerateTransferOutDispatchLetter generateTransferOutDispatchLetter,
                                        GenerateTransferOutWorkSheet generateTransferOutWorkSheet,
                                        FileStorageService fileStorageService,
                                        ContributionService contributionService,
                                        TransferOutStatusRepository transferOutStatusRepository) {
        this.transferOutService = transferOutService;
        this.jobLaunchService = jobLaunchService;
        this.paymentModeRepository = paymentModeRepository;
        this.bankRepository = bankRepository;
        this.employeeService = employeeService;
        this.generateAnnuxurek = generateAnnuxurek;
        this.generateTransferOutDispatchLetter = generateTransferOutDispatchLetter;
        this.generateTransferOutWorkSheet = generateTransferOutWorkSheet;
        this.fileStorageService = fileStorageService;
        this.contributionService = contributionService;
        this.transferOutStatusRepository = transferOutStatusRepository;
    }

    @GetMapping
    @ApplyTenantFilter
    public ResponseEntity<Object> getTransferOuts(
            @RequestParam(value = "search", required = false, defaultValue = "")String search,
            @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10")Integer size){

        Page<TransferOut> transferOuts = transferOutService.getTransferOuts(search, size, page);

        Page<SearchTransferOutDTO> searchTransferOutDTOS = transferOuts.map(transferOut -> new SearchTransferOutDTO(
                transferOut.getEntityId(),
                transferOut.getEmployee().getPernNumber(), transferOut.getEmployee().getPfNumber(),
                transferOut.getEmployee().getName(), transferOut.getEmployee().getUnitCode(),
                transferOut.getDateOfLeavingService(), transferOut.getDueDate(),
                transferOut.getPayeeName(), transferOut.getPaymentDate(),
                transferOut.getTransferOutType().getTitle(), transferOut.getTransferOutStatus().getTitle()));

        return Response.success(searchTransferOutDTOS);
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
        Job job = jobLaunchService.launchReportJob("transfer_out_report_job", jobDTO.getParams());
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
        EssentialsDTO essentials = transferOutService.getEssentials();
        return Response.success(essentials);
    }


    @GetMapping("/details/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getTransferOut(@PathVariable String entityId) {

        TransferOut transferOut = transferOutService.getTransferOut(entityId);

        Employee employee = transferOut.getEmployee();
        TransferOutFinalDetails transferOutFinalDetails = transferOut.getTransferOutFinalDetails();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("dateOfJoining", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("email", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("dateOfLeaving", DateFormatterUtil.formatDateR(transferOut.getDateOfLeavingService()));
        jsonObject.put("dueDate", DateFormatterUtil.formatDateR(transferOut.getDueDate()));
        jsonObject.put("requestCreatedAt", DateFormatterUtil.formatDateR(new Date(transferOut.getCreatedAtTimestamp())));
        jsonObject.put("netCreditAmount", NumberFormatter.formatNumbers(transferOutFinalDetails.getTotalNetCreditAmount()));
        jsonObject.put("status", employee.getContributionStatus().getDescription());
        jsonObject.put("unitCode",employee.getUnitCode());
        jsonObject.put("paymentBank", transferOut.getBank().getName());
        jsonObject.put("incomeTax", transferOut.getIncomeTax());
        jsonObject.put("eduCess", transferOut.getEduCess());
        jsonObject.put("tds", transferOut.getIncomeTax().add(transferOut.getEduCess()));
        
        if(transferOut.getTransferOutStatus().getTitle().equals("Completed")) {
            jsonObject.put("paymentNumber", transferOut.getChequeNo());
            jsonObject.put("paymentDate", DateFormatterUtil.formatDateR(transferOut.getPaymentDate()));
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
        jsonObject.put("age", employee.getEmployeeAge());

        jsonObject.put("altEmailId", transferOut.getAlternateEmailId());
        jsonObject.put("altContactNumber", transferOut.getAlternateContactNumber());

        jsonObject.put("transferOutStatus", transferOut.getTransferOutStatus().getTitle());
        jsonObject.put("isInProgress", transferOut.getTransferOutStatus().getTitle().equalsIgnoreCase("In Process"));
        jsonObject.put("isCompleted", transferOut.getTransferOutStatus().getTitle().equalsIgnoreCase("Completed"));
        jsonObject.put("isCanceled", transferOut.getTransferOutStatus().getTitle().equalsIgnoreCase("Canceled"));
        jsonObject.put("isPaymentPending", transferOut.getTransferOutStatus().getTitle().equalsIgnoreCase("Payment Pending"));

        jsonObject.put("fromPfAccount", transferOut.getFromPfNumber());
        jsonObject.put("fromEpsAccount", transferOut.getFromEpsNumber());
        jsonObject.put("fromUan", transferOut.getFromUanNumber());

        jsonObject.put("toPfAccount", transferOut.getToPfNumber());
        jsonObject.put("toEpsAccount", transferOut.getToEpsNumber());
        jsonObject.put("toUan", transferOut.getToUanNumber());

        jsonObject.put("companyName", transferOut.getCurrentEmployerName());
        jsonObject.put("companyAddressLine1", transferOut.getCurrentEmployerAddressLine1());
        jsonObject.put("companyAddressLine2", transferOut.getCurrentEmployerAddressLine2());
        jsonObject.put("companyAddressLine3", transferOut.getCurrentEmployerAddressLine3());
        jsonObject.put("companyAddressLine4", transferOut.getCurrentEmployerAddressLine4());

        jsonObject.put("type", transferOut.getTransferOutType().getTitle());
        jsonObject.put("addressLine1", transferOut.getAddressLine1());
        jsonObject.put("addressLine2", transferOut.getAddressLine2());
        jsonObject.put("addressLine3", transferOut.getAddressLine3());
        jsonObject.put("addressLine4", transferOut.getAddressLine4());

        jsonObject.put("bankName", transferOut.getBankName());
        jsonObject.put("accountNumber", transferOut.getAccountNumber());
        jsonObject.put("branch", transferOut.getBranch());
        jsonObject.put("ifscCode", transferOut.getIfscCode());
        jsonObject.put("micrCode", transferOut.getMicrCode());
        jsonObject.put("paymentMode", transferOut.getPaymentMode().getMode());

        List<TransferOutDocument> transferOutDocuments = transferOut.getTransferOutDocuments();

        JSONArray uploadedDocuments = new JSONArray();

        for (TransferOutDocument transferOutDocument:transferOutDocuments) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("name", transferOutDocument.getDocument().getName());
            jsonObject1.put("path", transferOutDocument.getPath());
            uploadedDocuments.put(jsonObject1);
        }

        jsonObject.put("uploadedDocuments", uploadedDocuments);
        
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

    @PutMapping("/savePaymentDetails/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> savePaymentDetails(@PathVariable String entityId, @RequestBody String request) throws ParseException, JSONException, JPAException {

        TransferOut transferOut = transferOutService.getTransferOut(entityId);

        JSONObject jsonObject = new JSONObject(request);

        Date paymentDate = FinancialYearAndMonth.getDate(jsonObject.getString("paymentDate"), "yyyy-MM-dd");

        Optional<Bank> optionalBank = bankRepository.findByEntityIdAndIsActive(jsonObject.getString("bank"), true);

        if(optionalBank.isEmpty()) throw new EntityNotFoundException("Selected Bank Not Found");

        Optional<PaymentMode> optionalPaymentMode = paymentModeRepository
                .findByEntityIdAndIsActive(jsonObject.getString("paymentMode"), true);

        if(optionalPaymentMode.isEmpty()) throw new EntityNotFoundException("Payment Mode Not Found");

        TransferOut savedTransferOut = transferOutService.savePaymentDetails(transferOut, paymentDate, jsonObject.getString("referenceNumber"),
                optionalBank.get(), optionalPaymentMode.get());

        TransferOutEmailStatus emailStatus = transferOutService.createEmailStatus(savedTransferOut);

        if(jsonObject.getBoolean("sendConfirmationMail")){

            try {

                AnnuxureK annexureK = transferOutService.getAnnexureK(savedTransferOut);

                String annexurek_file = "annexurek_" + transferOut.getEmployee().getPfNumber() + ".pdf";

                ByteArrayOutputStream annexurek = generateAnnuxurek.generate(annexureK);

                EmailAttachment annexurekAttachment = new EmailAttachment(annexurek_file,
                        new ByteArrayDataSource(annexurek.toByteArray(), "application/octet-stream"));

                TransferOutDispatchLetter dispatchLetter = transferOutService.getDispatchLetter(transferOut);

                String dispatch_letter_file = "dispatch_letter_" + transferOut.getEmployee().getPfNumber() + ".pdf";

                ByteArrayOutputStream dispatch_letter = generateTransferOutDispatchLetter.generate(dispatchLetter);

                EmailAttachment dispatchLetterAttachment = new EmailAttachment(dispatch_letter_file,
                        new ByteArrayDataSource(dispatch_letter.toByteArray(), "application/octet-stream"));


                transferOutService.sendConfirmationEmail(emailStatus,
                        List.of(annexurekAttachment, dispatchLetterAttachment));
                transferOutService.updateEmailStatus(emailStatus);

            }catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

        }

        return Response.success("payment details updated successfully.");

    }

    @PutMapping("/reject/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> reject(@PathVariable String entityId) {
        transferOutService.reject(entityId);
        return Response.success("status updated successfully.");
    }

    @GetMapping("/emailList")
    @ApplyTenantFilter
    public ResponseEntity<Object> getTransferOutListToSendEmails(){

        List<TransferOutEmailStatus> emailStatusListNotSent = transferOutService.getEmailStatusListNotSent();

        List<TransferOutEmailDTO> transferOutEmailDTOList = emailStatusListNotSent.stream()
                .map(transferOutEmailStatus -> new TransferOutEmailDTO(
                        transferOutEmailStatus.getEntityId(), transferOutEmailStatus.getFromPfAccount(),
                        transferOutEmailStatus.getToPfAccount(), transferOutEmailStatus.getEmployeeName(),
                        transferOutEmailStatus.getUnitCode(), transferOutEmailStatus.getPaymentDate(),
                        transferOutEmailStatus.getTotalContribution(), transferOutEmailStatus.getPfTrustName(),
                        transferOutEmailStatus.getCurrentEmployerName(), transferOutEmailStatus.getEmailId()))
                .collect(Collectors.toList());

        return Response.success(transferOutEmailDTOList);

    }

    @PostMapping("/sendEmails")
    @ApplyTenantFilter
    public ResponseEntity<Object> sendConfirmationEmails(@RequestBody List<String> entityIds){

        List<TransferOutEmailStatus> transferOutEmailStatusList = transferOutService.getTransferOutEmailStatusList(entityIds);

        transferOutEmailStatusList.forEach(transferOutEmailStatus -> {
            try {

                TransferOut transferOut = transferOutEmailStatus.getTransferOut();

                AnnuxureK annexureK = transferOutService.getAnnexureK(transferOut);

                String annexurek_file = "annexurek_" + transferOut.getEmployee().getPfNumber() + ".pdf";

                ByteArrayOutputStream annexurek = generateAnnuxurek.generate(annexureK);

                EmailAttachment annexurekAttachment = new EmailAttachment(annexurek_file,
                        new ByteArrayDataSource(annexurek.toByteArray(), "application/octet-stream"));

                TransferOutDispatchLetter dispatchLetter = transferOutService.getDispatchLetter(transferOut);

                String dispatch_letter_file = "dispatch_letter_" + transferOut.getEmployee().getPfNumber() + ".pdf";

                ByteArrayOutputStream dispatch_letter = generateTransferOutDispatchLetter.generate(dispatchLetter);

                EmailAttachment dispatchLetterAttachment = new EmailAttachment(dispatch_letter_file,
                        new ByteArrayDataSource(dispatch_letter.toByteArray(), "application/octet-stream"));

                transferOutService.sendConfirmationEmail(transferOutEmailStatus,
                        List.of(annexurekAttachment, dispatchLetterAttachment));
                transferOutService.updateEmailStatus(transferOutEmailStatus);

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
        jsonObject.put("dateOfBirth", employee.getDateOfBirth());
        jsonObject.put("age", employee.getEmployeeAge());
        jsonObject.put("status", employee.getContributionStatus().getSymbol());

        try {

            Contribution openingBalance = contributionService.getContribution(employee,
                    FinancialYearAndMonth.getFinancialYear(new Date()), 0);

        }catch (EntityNotFoundException exception){
            jsonObject.put("yearEndProcessError", true);
            jsonObject.put("yearEndProcessErrorMessage", "Please perform year end process for the employee before proceeding.");
        }

        List<TransferOutType> transferOutTypes = transferOutService.getTransferOutTypes();

        JSONArray settlementTypeArray = new JSONArray();

        for (TransferOutType transferOutType:transferOutTypes) {
            JSONObject settlementTypeObject = new JSONObject();
            settlementTypeObject.put("title", transferOutType.getTitle());
            settlementTypeObject.put("entityId", transferOutType.getEntityId());
            settlementTypeArray.put(settlementTypeObject);
        }

        jsonObject.put("transferOutTypes", settlementTypeArray);
        jsonObject.put("maxDate", DateFormatterUtil.format(new Date(), "yyyy-MM-dd"));

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
        jsonObject.put("dateOfBirth", employee.getDateOfBirth());
        jsonObject.put("age", employee.getEmployeeAge());
        jsonObject.put("status", employee.getContributionStatus().getSymbol());

        try {

            Contribution openingBalance = contributionService.getContribution(employee,
                    FinancialYearAndMonth.getFinancialYear(new Date()), 0);

        }catch (EntityNotFoundException exception){
            jsonObject.put("yearEndProcessError", true);
            jsonObject.put("yearEndProcessErrorMessage", "Please perform year end process for the employee before proceeding.");
        }

        List<TransferOutType> transferOutTypes = transferOutService.getTransferOutTypes();

        JSONArray settlementTypeArray = new JSONArray();

        for (TransferOutType transferOutType:transferOutTypes) {
            JSONObject settlementTypeObject = new JSONObject();
            settlementTypeObject.put("title", transferOutType.getTitle());
            settlementTypeObject.put("entityId", transferOutType.getEntityId());
            settlementTypeArray.put(settlementTypeObject);
        }

        jsonObject.put("transferOutTypes", settlementTypeArray);
        jsonObject.put("maxDate", DateFormatterUtil.format(new Date(), "yyyy-MM-dd"));

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


    @GetMapping("/documents")
    @ApplyTenantFilter
    public ResponseEntity<Object> getDocuments(@RequestParam String entityId){

        List<Document> documents = transferOutService.getDocumentsForTransferOutType(entityId);

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

        TransferOut transferOut = TransferOut.build(jsonObject);
        
        TransferOutType transferOutType = transferOutService.getTransferOutType(jsonObject.getString("transferOutType"));

        transferOut.setTransferOutType(transferOutType);

        List<TransferOutDocument> transferOutDocuments = transferOutService.getTransferOutDocuments(jsonObject.getJSONArray("documents"));
        
        String paymentid = jsonObject.getString("paymentModeId");
        
        PaymentMode paymentMode = paymentModeRepository.findByIdAndIsActive(Long.parseLong(paymentid), true);
        
        Bank bank = bankRepository.findByNameAndIsActive(jsonObject.getString("paymentBank"), true);
        
        transferOut.setBank(bank);
        
        transferOut.setIncomeTax(new BigDecimal(jsonObject.getInt("incomeTax")));
        
        transferOut.setEduCess(new BigDecimal(jsonObject.getInt("eduCess")));
        
        TransferOut createdTransferOut = transferOutService.create(transferOut, employee, paymentMode);

        transferOutService.saveTransferOutDocuments(transferOutDocuments, createdTransferOut);
       
        JSONObject responseObj = new JSONObject();
        responseObj.put("id", createdTransferOut.getEntityId());
        responseObj.put("message", "Transfer Out Application Created Successfully with Application ID " + createdTransferOut.getEntityId());

        return Response.success(responseObj.toString());
    }


    @GetMapping("/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> get(@PathVariable String entityId){

        TransferOut transferOut = transferOutService.getTransferOut(entityId);

        JSONObject jsonObject = new JSONObject();

        Employee employee = transferOut.getEmployee();

        jsonObject.put("applicationId", transferOut.getEntityId());
        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("emailId", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("dateOfJoiningService", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("unitCode",employee.getUnitCode());
        jsonObject.put("incomeTax", transferOut.getIncomeTax());
        jsonObject.put("eduCess", transferOut.getEduCess());
        jsonObject.put("tds", transferOut.getIncomeTax().add(transferOut.getEduCess()));

        List<PreviousCompany> previousCompanies = employee.getPreviousCompanies();

        Date date = employee.getDateOfJoiningPF();

        if(!previousCompanies.isEmpty()){
            jsonObject.put("dateOfJoiningPrior", DateFormatterUtil.formatDateR(previousCompanies.get(0).getDateOfJoining()));
            if(previousCompanies.get(0).getDateOfJoining() != null){
                date = previousCompanies.get(0).getDateOfJoining();
            }
        }

        jsonObject.put("membershipYears", DateFormatterUtil.getPeriodBetweenDate(date, new Date()).getYears());
        jsonObject.put("dateOfBirth", employee.getDateOfBirth());
        jsonObject.put("age", employee.getEmployeeAge());
        jsonObject.put("status", employee.getContributionStatus().getSymbol());

        List<TransferOutType> transferOutTypes = transferOutService.getTransferOutTypes();

        JSONArray settlementTypeArray = new JSONArray();

        for (TransferOutType transferOutType:transferOutTypes) {
            JSONObject settlementTypeObject = new JSONObject();
            settlementTypeObject.put("title", transferOutType.getTitle());
            settlementTypeObject.put("entityId", transferOutType.getEntityId());
            settlementTypeArray.put(settlementTypeObject);
        }

        jsonObject.put("transferOutTypes", settlementTypeArray);

        jsonObject.put("dateOfLeavingService", DateFormatterUtil.format(transferOut.getDateOfLeavingService(), "yyyy-MM-dd"));
        jsonObject.put("dueDate", DateFormatterUtil.format(transferOut.getDueDate(), "yyyy-MM-dd"));

        jsonObject.put("altEmailId", transferOut.getAlternateEmailId());
        jsonObject.put("altContactNumber", transferOut.getAlternateContactNumber());

        jsonObject.put("payeeName", transferOut.getPayeeName());
        jsonObject.put("addressLine1", transferOut.getAddressLine1());
        jsonObject.put("addressLine2", transferOut.getAddressLine2());
        jsonObject.put("addressLine3", transferOut.getAddressLine3());
        jsonObject.put("addressLine4", transferOut.getAddressLine4());

        jsonObject.put("bankName", transferOut.getBankName());
        jsonObject.put("branch", transferOut.getBranch());
        jsonObject.put("accountNumber", transferOut.getAccountNumber());
        jsonObject.put("ifscCode", transferOut.getIfscCode());
        jsonObject.put("micrCode", transferOut.getMicrCode());

        jsonObject.put("fromPfNumber", transferOut.getFromPfNumber());
        jsonObject.put("fromEpsNumber", transferOut.getFromEpsNumber());
        jsonObject.put("fromUanNumber", transferOut.getFromUanNumber());

        jsonObject.put("toPfNumber", transferOut.getToPfNumber());
        jsonObject.put("toEpsNumber", transferOut.getToEpsNumber());
        jsonObject.put("toUanNumber", transferOut.getToUanNumber());

        jsonObject.put("currentEmployerName", transferOut.getCurrentEmployerName());
        jsonObject.put("currentEmployerAddressLine1", transferOut.getCurrentEmployerAddressLine1());
        jsonObject.put("currentEmployerAddressLine2", transferOut.getCurrentEmployerAddressLine2());
        jsonObject.put("currentEmployerAddressLine3", transferOut.getCurrentEmployerAddressLine3());
        jsonObject.put("currentEmployerAddressLine4", transferOut.getCurrentEmployerAddressLine4());

        jsonObject.put("transferOutType", transferOut.getTransferOutType().getEntityId());

        List<Document> documents = transferOutService
                .getDocumentsForTransferOutType(transferOut.getTransferOutType().getEntityId());

        JSONArray documentsArray = new JSONArray();

        for (Document document:documents) {
            JSONObject documentObject = new JSONObject();
            documentObject.put("entityId", document.getEntityId());
            documentObject.put("name", document.getName());
            documentsArray.put(documentObject);
        }

        jsonObject.put("documents", documentsArray);

        JSONArray uploadedDocuments = new JSONArray();

        transferOut.getTransferOutDocuments().forEach(settlementDocument -> {
            JSONObject documentObject = new JSONObject();
            documentObject.put("path", settlementDocument.getPath());
            documentObject.put("documentId", settlementDocument.getDocument().getEntityId());
            documentObject.put("entityId", settlementDocument.getEntityId());
            uploadedDocuments.put(documentObject);
        });

        jsonObject.put("uploadedDocuments", uploadedDocuments);
        jsonObject.put("maxDate", DateFormatterUtil.format(new Date(), "yyyy-MM-dd"));

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
        jsonObject.put("paymentMode", transferOut.getPaymentMode().getEntityId());
        jsonObject.put("paymentModes", new JSONArray(mappedPaymentModes));
        
        List<Bank> bankList = bankRepository.findAllByIsActive(true);

        List<JSONObject> mappedBanks = bankList.stream().map(bank -> {
                            JSONObject bankObject = new JSONObject();
                            bankObject.put("name", bank.getName());
                            bankObject.put("entityId", bank.getEntityId());
                            return bankObject;
                        })
                        .collect(Collectors.toList());

        jsonObject.put("banks", new JSONArray(mappedBanks));
        jsonObject.put("paymentBank", transferOut.getBank().getName());

        return Response.success(jsonObject.toString());

    }

    @PutMapping("{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> update(@PathVariable String entityId, @RequestBody String requestBody) throws ParseException, JPAException {

        TransferOut fetchedTransferOut = transferOutService.getTransferOut(entityId);

        JSONObject jsonObject = new JSONObject(requestBody);

        Employee employee = employeeService.getEmployeeByPern(jsonObject.getString("pernNumber"));

        TransferOut transferOut = TransferOut.build(fetchedTransferOut, jsonObject);

        TransferOutType transferOutType = transferOutService.getTransferOutType(jsonObject.getString("transferOutType"));

        transferOut.setTransferOutType(transferOutType);
        
        PaymentMode paymentMode = paymentModeRepository.findByIdAndIsActive(jsonObject.getLong("paymentModeId"), true);
        
        Bank bank = bankRepository.findByNameAndIsActive(jsonObject.getString("paymentBank"), true);
        
        transferOut.setBank(bank);
        
        transferOut.setIncomeTax(new BigDecimal(jsonObject.getInt("incomeTax")));

        transferOut.setEduCess(new BigDecimal(jsonObject.getInt("eduCess")));
        
        TransferOut createdTransferOut = transferOutService.update(transferOut, employee, paymentMode);

        transferOutService.updateSettlementDocuments(jsonObject.getJSONArray("documents"));

        JSONObject responseObj = new JSONObject();
        responseObj.put("id", createdTransferOut.getEntityId());
        responseObj.put("message", "Transfer Out Application Updated Successfully with Application ID " + createdTransferOut.getEntityId());

        return Response.success(responseObj.toString());

    }

    @GetMapping("/{entityId}/getAnnexurK")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateSettlementAnnexure(@PathVariable String entityId) throws Exception {

        TransferOut transferOut = transferOutService.getTransferOut(entityId);

        AnnuxureK annexureK = transferOutService.getAnnexureK(transferOut);

        String filename = "transferout_annexurek_" + transferOut.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateAnnuxurek.generate(annexureK);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/{entityId}/getDispatchLetter")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateTransferOutDispatchLetter(@PathVariable String entityId) throws Exception {

        TransferOut transferOut = transferOutService.getTransferOut(entityId);

        TransferOutDispatchLetter dispatchLetter = transferOutService.getDispatchLetter(transferOut);

        String filename = "transferout_dispatch_letter_" + transferOut.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateTransferOutDispatchLetter.generate(dispatchLetter);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/{entityId}/getWorkSheet")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateTransferOurWorkSheet(@PathVariable String entityId) throws Exception {

        TransferOut transferOut = transferOutService.getTransferOut(entityId);

        Worksheet dispatchLetter = transferOutService.getWorkSheet(transferOut);

        String filename = "transferout_worksheet_" + transferOut.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateTransferOutWorkSheet.generate(dispatchLetter);

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

        List<TransferOut> transferOuts = transferOutService.getTransferOutsForBankSheet(
                FinancialYearAndMonth.getDate(startDate),
                FinancialYearAndMonth.getDate(endDate), bankId);

        if(transferOuts.isEmpty()) throw new JPAException("Transfer outs not found for given range and bank");

        List<SearchTransferOutDTO> transferOutDTOS = transferOuts.stream().map(settlement -> new SearchTransferOutDTO(
                        settlement.getEntityId(),
                        settlement.getEmployee().getPernNumber(), settlement.getEmployee().getPfNumber(),
                        settlement.getEmployee().getName(), settlement.getEmployee().getUnitCode(),
                        settlement.getDateOfLeavingService(), settlement.getDueDate(),
                        settlement.getPayeeName(), settlement.getPaymentDate(),
                        settlement.getTransferOutType().getTitle(), settlement.getTransferOutStatus().getTitle()))
                .collect(Collectors.toList());

        return Response.success(transferOutDTOS);
    }


    @GetMapping("/generateBankSheet")
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

        List<TransferOut> transferOuts = transferOutService.getTransferOuts(entityIds);

        List<TransferOut> updatedTransferOuts = transferOutService.updatePaymentDate(transferOuts, paymentDate);

        List<String> bankSheetLines = transferOutService.getBankSheetLines(updatedTransferOuts, paymentDate);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (String line:bankSheetLines) {
            out.writeBytes(line.getBytes(StandardCharsets.UTF_8));
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        String filename = "PF_TRANSFER_OUT_KOTAK_PD" + DateFormatterUtil.formatDatePay(paymentDate) + ".txt";

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

        TransferOut transferOut = transferOutService.getTransferOut(entityId);

        String settlementStatusTitile = transferOut.getTransferOutStatus().getTitle();

        if(settlementStatusTitile.equalsIgnoreCase("In Process")) {
        	
        	TransferOutStatus transferOutStatus = transferOutStatusRepository.findByTitleAndIsActive("Payment Pending", true);
        	transferOut.setTransferOutStatus(transferOutStatus);
        	transferOutService.updateTransferOutStatus(transferOut);
        	return Response.success("Application Aproved");
        } 
        
        return Response.failure("Approval failed");

    }


}
