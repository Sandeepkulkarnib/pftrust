package com.coreintegra.pftrust.controllers.pf.loan;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.loan.LoanHistorySheet;
import com.coreintegra.pftrust.dtos.pdf.loan.LoanReceipt;
import com.coreintegra.pftrust.dtos.pdf.loan.LoanWorkSheet;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.Document;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDTO;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDetails;
import com.coreintegra.pftrust.entities.pf.department.dtos.LaunchJobDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanDocument;
import com.coreintegra.pftrust.entities.pf.loan.LoanEmailStatus;
import com.coreintegra.pftrust.entities.pf.loan.LoanStatus;
import com.coreintegra.pftrust.entities.pf.loan.LoanType;
import com.coreintegra.pftrust.entities.pf.loan.dtos.LoanTypeDTO;
import com.coreintegra.pftrust.entities.pf.loan.dtos.SearchLoanDTO;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementEmailDTO;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.department.BankRepository;
import com.coreintegra.pftrust.repositories.pf.department.PaymentModeRepository;
import com.coreintegra.pftrust.repositories.pf.loan.LoanStatusRepository;
import com.coreintegra.pftrust.services.pf.contribution.ContributionService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import com.coreintegra.pftrust.services.pf.loans.LoanService;
import com.coreintegra.pftrust.services.pf.pdf.GenerateLoanHistorySheet;
import com.coreintegra.pftrust.services.pf.pdf.GenerateLoanReceipt;
import com.coreintegra.pftrust.services.pf.pdf.GenerateLoanWorksheet;
import com.coreintegra.pftrust.services.storage.FileStorageService;
import com.coreintegra.pftrust.util.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
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

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(LoanServiceEndpoints.LOAN_SERVICE_ENDPOINT)
public class LoanServiceController {

    private final Path fileStorageLocation = Paths.get("")
            .toAbsolutePath().normalize();

    private final LoanService loanService;
    private final JobLaunchService jobLaunchService;
    private final GenerateLoanHistorySheet generateLoanHistorySheet;
    private final EmployeeService employeeService;
    private final BankRepository bankRepository;
    private final PaymentModeRepository paymentModeRepository;
    private final GenerateLoanWorksheet generateLoanWorksheet;
    private final FileStorageService fileStorageService;
    private final GenerateLoanReceipt generateLoanReceipt;
    private final ContributionService contributionService;
    private final LoanStatusRepository loanStatusRepository;

    public LoanServiceController(LoanService loanService, JobLaunchService jobLaunchService,
                                 GenerateLoanHistorySheet generateLoanHistorySheet,
                                 EmployeeService employeeService,
                                 BankRepository bankRepository,
                                 PaymentModeRepository paymentModeRepository,
                                 GenerateLoanWorksheet generateLoanWorksheet,
                                 FileStorageService fileStorageService,
                                 GenerateLoanReceipt generateLoanReceipt,
                                 ContributionService contributionService,
                                 LoanStatusRepository loanStatusRepository) {
        this.loanService = loanService;
        this.jobLaunchService = jobLaunchService;
        this.generateLoanHistorySheet = generateLoanHistorySheet;
        this.employeeService = employeeService;
        this.bankRepository = bankRepository;
        this.paymentModeRepository = paymentModeRepository;
        this.generateLoanWorksheet = generateLoanWorksheet;
        this.fileStorageService = fileStorageService;
        this.generateLoanReceipt = generateLoanReceipt;
        this.contributionService = contributionService;
        this.loanStatusRepository = loanStatusRepository;
    }

    @GetMapping
    @ApplyTenantFilter
    public ResponseEntity<Object> getLoans(
            @RequestParam(value = "search", required = false, defaultValue = "")String search,
            @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10")Integer size){

        Page<Loan> loans = loanService.getLoans(search, size, page);

        Page<SearchLoanDTO> searchLoanDTOS = loans.map(loan -> new SearchLoanDTO(
                loan.getEntityId(),
                loan.getEmployee().getPernNumber(), loan.getEmployee().getPfNumber(),
                loan.getEmployee().getName(), loan.getEmployee().getUnitCode(),
                loan.getLoanApplicationDate(), loan.getLoanApprovalDate(),
                loan.getPaymentDate(), loan.getLoanType().getTitle(), loan.getLoanStatus().getCode()));

        return Response.success(searchLoanDTOS);
    }


    @GetMapping("/essentials")
    @ApplyTenantFilter
    public ResponseEntity<Object> getLoanEssentials(){
        EssentialsDTO loanEssentials = loanService.getLoanEssentials();
        return Response.success(loanEssentials);
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
        Job job = jobLaunchService.launchReportJob("loan_report_job", jobDTO.getParams());
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

    @GetMapping("/details/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getLoan(@PathVariable String entityId){

        Loan loan = loanService.getLoan(entityId);

        Employee employee = loan.getEmployee();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("emailId", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("dateOfJoiningService", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("unitCode", employee.getUnitCode());

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
        jsonObject.put("pfBase", loan.getPfBase());

        jsonObject.put("totalCost", NumberFormatter.formatNumbers(loan.getTotalCost()));
        jsonObject.put("appliedAmount", NumberFormatter.formatNumbers(loan.getAppliedAmount()));
        jsonObject.put("eligibleAmount", NumberFormatter.formatNumbers(loan.getEligibleAmount()));

        jsonObject.put("altContactNumber", loan.getAltContactNumber());
        jsonObject.put("altEmailId", loan.getAltEmail());

        jsonObject.put("employeeBankName", loan.getEmployeeBank());
        jsonObject.put("employeeBankBranch", loan.getEmployeeBankBranch());
        jsonObject.put("employeeAccountNumber", loan.getEmployeeAccountNumber());
        jsonObject.put("employeeBankIfscCode", loan.getEmployeeBankIfscCode());
        jsonObject.put("employeeBankMicrCode", loan.getEmployeeBankMicrCode());

        jsonObject.put("loanType", loan.getLoanType().getTitle());

        jsonObject.put("loanStatus", loan.getLoanStatus().getTitle());
        jsonObject.put("isInProgress", loan.getLoanStatus().getTitle().equalsIgnoreCase("Pending"));
        jsonObject.put("isCanceled", loan.getLoanStatus().getTitle().equalsIgnoreCase("Rejected"));
        jsonObject.put("isCompleted", loan.getLoanStatus().getTitle().equalsIgnoreCase("Approved"));
        jsonObject.put("isPaymentPending", loan.getLoanStatus().getTitle().equalsIgnoreCase("Disbursement Pending"));

        jsonObject.put("approvalDate", DateFormatterUtil.formatDateR(loan.getLoanApprovalDate()));
        jsonObject.put("applicationDate", DateFormatterUtil.formatDateR(loan.getLoanApplicationDate()));
        jsonObject.put("applicationReceivedDate", DateFormatterUtil.formatDateR(loan.getLoanApplicationReceivedDate()));
        jsonObject.put("disbursalDate", DateFormatterUtil.formatDateR(loan.getLoanDisbursalDate()));

        jsonObject.put("repaymentBankName", loan.getRepaymentBank());
        jsonObject.put("repaymentBankBranch", loan.getRepaymentBankBranch());
        jsonObject.put("repaymentAccountNumber", loan.getRepaymentBankAccountNumber());
        jsonObject.put("repaymentBankIfscCode", loan.getRepaymentBankIfscCode());
        jsonObject.put("repaymentBankMicrCode", loan.getRepaymentBankMicrCode());

        jsonObject.put("paymentDate", DateFormatterUtil.formatDateR(loan.getPaymentDate()));
        jsonObject.put("paymentMode", loan.getPaymentMode() != null? loan.getPaymentMode().getMode() : "");
        jsonObject.put("amountDisbursed", NumberFormatter.formatNumbers(loan.getAmountDisbursed()));

        List<LoanDocument> loanDocuments = loan.getLoanDocuments();

        JSONArray uploadedDocuments = new JSONArray();

        for (LoanDocument loanDocument:loanDocuments) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("name", loanDocument.getDocument().getName());
            jsonObject1.put("path", loanDocument.getPath());
            uploadedDocuments.put(jsonObject1);
        }

        jsonObject.put("uploadedDocuments", uploadedDocuments);

        jsonObject.put("dateOfCompletionOfHouse", DateFormatterUtil.formatDateR(loan.getDateOfCompletionOfHouse()));
        jsonObject.put("dateOfFirstAlteration", DateFormatterUtil.formatDateR(loan.getDateOfFirstAlteration()));
        jsonObject.put("propertyEstimatedPrice", NumberFormatter.formatNumbers(loan.getPropertyEstimatedPrice()));
        jsonObject.put("stampDuty", NumberFormatter.formatNumbers(loan.getStampDuty()));
        jsonObject.put("propertyRegistration", NumberFormatter.formatNumbers(loan.getPropertyRegistration()));
        jsonObject.put("insurance", NumberFormatter.formatNumbers(loan.getInsurance()));
        jsonObject.put("other", NumberFormatter.formatNumbers(loan.getOther()));
        jsonObject.put("total", NumberFormatter.formatNumbers(loan.getTotal()));
        jsonObject.put("financialInstName", loan.getFinancialInstName());
        jsonObject.put("repaymentBank", loan.getRepaymentBank());
        jsonObject.put("repaymentBankBranch", loan.getRepaymentBankBranch());
        jsonObject.put("repaymentBankAccountNumber", loan.getRepaymentBankAccountNumber());
        jsonObject.put("repaymentBankIfscCode", loan.getRepaymentBankIfscCode());
        jsonObject.put("repaymentBankMicrCode", loan.getRepaymentBankMicrCode());

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

    @GetMapping("/getLoanHistorySheet/{pernNumber}")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateLoanHistorySheet(@PathVariable String pernNumber) throws Exception {

        Employee employee = employeeService.getEmployeeByPern(pernNumber);

        LoanHistorySheet loanHistorySheet = loanService.getLoanHistorySheet(employee);

        String filename = "loan_history_sheet" + employee.getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateLoanHistorySheet.generate(loanHistorySheet);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

    }


    @PutMapping("/{entityId}/savePaymentDetails")
    @ApplyTenantFilter
    public ResponseEntity<Object> savePaymentDetails(@PathVariable String entityId, @RequestBody String request) throws ParseException {

        Loan loan = loanService.getLoan(entityId);

        JSONObject jsonObject = new JSONObject(request);

        Date paymentDate = FinancialYearAndMonth.getDate(jsonObject.getString("paymentDate"), "yyyy-MM-dd");

        Date approvalDate = FinancialYearAndMonth.getDate(jsonObject.getString("approvalDate"), "yyyy-MM-dd");

        Optional<Bank> optionalBank = bankRepository.findByEntityIdAndIsActive(jsonObject.getString("bank"), true);

        if(optionalBank.isEmpty()) throw new EntityNotFoundException("Selected Bank Not Found");

        Optional<PaymentMode> optionalPaymentMode = paymentModeRepository
                .findByEntityIdAndIsActive(jsonObject.getString("paymentMode"), true);

        if(optionalPaymentMode.isEmpty()) throw new EntityNotFoundException("Payment Mode Not Found");

        loanService.savePaymentDetails(loan, optionalBank.get(), optionalPaymentMode.get(),
                jsonObject.getString("referenceNumber"), approvalDate, paymentDate);

        LoanEmailStatus emailStatus = loanService.createEmailStatus(loan);

        if(jsonObject.getBoolean("sendConfirmationMail")){

            try {

                loanService.sendConfirmationEmail(emailStatus, new ArrayList<>());

                loanService.updateEmailStatus(emailStatus);

            }catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }

        }

        return Response.success("payment details updated successfully.");

    }


    @PutMapping("/{entityId}/reject")
    @ApplyTenantFilter
    public ResponseEntity<Object> reject(@PathVariable String entityId) {
        Loan loan = loanService.getLoan(entityId);
        loanService.rejectLoan(loan);
        return Response.success("status updated successfully.");
    }

    @GetMapping("/{entityId}/getLoanWorkSheet")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateLoanWorksheet(@PathVariable String entityId) throws Exception {

        Loan loan = loanService.getLoan(entityId);

        LoanWorkSheet loanWorkSheet = loanService.getWorkSheet(loan);

        String filename = "loan_work_sheet_" + loan.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateLoanWorksheet.generate(loanWorkSheet);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

    }


    @GetMapping("/{entityId}/getLoanHistorySheetWithLoan")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateLoanHistorySheetWithLoan(@PathVariable String entityId) throws Exception {

        Loan loan = loanService.getLoan(entityId);

        LoanHistorySheet loanHistorySheet = loanService.getLoanHistorySheet(loan);

        String filename = "loan_work_sheet_" + loan.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateLoanHistorySheet.generate(loanHistorySheet);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

    }

    @GetMapping("/{entityId}/getLoanReceipt")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateLoanReceipt(@PathVariable String entityId) throws Exception {

        Loan loan = loanService.getLoan(entityId);

        LoanReceipt loanReceipt = loanService.getLoanReceipt(loan);

        String filename = "loan_receipt_" + loan.getEmployee().getPfNumber() + ".pdf";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        ByteArrayOutputStream out = generateLoanReceipt.generate(loanReceipt);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

    }

    @GetMapping("/eligibleLoanTypes/{pernNumber}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEligibleLoanTypes(@PathVariable String pernNumber) throws JPAException {

        Employee employee = employeeService.getEmployeeByPern(pernNumber);

        List<LoanType> eligibleLoanTypes = loanService.getEligibleLoanTypes(employee);

        JSONArray jsonArray = new JSONArray();

        for (LoanType loanType:eligibleLoanTypes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", loanType.getTitle());
            jsonObject.put("entityId", loanType.getEntityId());
            jsonObject.put("code", loanType.getCode());
            jsonArray.put(jsonObject);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loanTypes", jsonArray);

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

        BigDecimal latestPfBase = contributionService.getLatestPfBase(employee);

        jsonObject.put("pfBase", NumberFormatter.formatNumbers(latestPfBase));

        return Response.success(jsonObject.toString());
    }

    @GetMapping("/eligibleLoanTypesByPf/{pfNumber}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEligibleLoanTypesByPf(@PathVariable String pfNumber) throws JPAException {

        Employee employee = employeeService.getEmployeeByPf(pfNumber);

        List<LoanType> eligibleLoanTypes = loanService.getEligibleLoanTypes(employee);

        JSONArray jsonArray = new JSONArray();

        for (LoanType loanType:eligibleLoanTypes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", loanType.getTitle());
            jsonObject.put("entityId", loanType.getEntityId());
            jsonObject.put("code", loanType.getCode());
            jsonArray.put(jsonObject);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loanTypes", jsonArray);

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

        BigDecimal latestPfBase = contributionService.getLatestPfBase(employee);

        jsonObject.put("pfBase", NumberFormatter.formatNumbers(latestPfBase));

        return Response.success(jsonObject.toString());
    }


    @GetMapping("/{entityId}/loanDocuments")
    @ApplyTenantFilter
    public ResponseEntity<Object> getLoanDocuments(@PathVariable String entityId) {

        LoanType loanType = loanService.getLoanType(entityId);

        JSONArray documents = new JSONArray();

        for (Document document:loanType.getDocuments()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", document.getName());
            jsonObject.put("entityId", document.getEntityId());
            documents.put(jsonObject);
        }

        return Response.success(documents.toString());

    }

    @PostMapping("/checkEligibility")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEligibleLoanAmount(@RequestBody String request) {

        JSONObject jsonObject = new JSONObject(request);

        BigDecimal appliedAmount = jsonObject.getBigDecimal("appliedAmount");
        BigDecimal totalCost = jsonObject.getBigDecimal("totalCost");
        String pernNumber = jsonObject.getString("pernNumber");
        String loanType = jsonObject.getString("loanType");

        Employee employee = employeeService.getEmployeeByPern(pernNumber);

        LoanType selectedLoanType = loanService.getLoanType(loanType);

        JSONObject eligibleLoanAmount = loanService.getEligibleLoanAmount(employee,
                appliedAmount, totalCost, selectedLoanType);

        return Response.success(eligibleLoanAmount.toString());

    }

    @PostMapping("")
    @ApplyTenantFilter
    public ResponseEntity<Object> create(@RequestBody String requestBody) throws ParseException, JPAException {

        JSONObject jsonObject = new JSONObject(requestBody);

        Employee employee = employeeService.getEmployeeByPern(jsonObject.getString("pernNumber"));

        LoanType loanType = loanService.getLoanType(jsonObject.getString("selectedLoanType"));

        Loan loan = Loan.build(jsonObject);

        List<LoanDocument> loanDocumentList = loanService.getLoanDocuments(jsonObject.getJSONArray("documents"));

        Loan savedLoan = loanService.create(employee, loanType, loan);

        loanService.saveLoanDocuments(loanDocumentList, savedLoan);

        JSONObject responseObj = new JSONObject();
        responseObj.put("id", savedLoan.getEntityId());
        responseObj.put("message", "Loan Application Created Successfully with Application ID " + savedLoan.getEntityId());

        return Response.success(responseObj.toString());

    }

    @PostMapping(value = "/uploadDocuments/{entityId}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApplyTenantFilter
    public ResponseEntity<Object> uploadDocuments(@PathVariable String entityId,
                                                  HttpServletRequest httpServletRequest){

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

    @GetMapping("/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> get(@PathVariable String entityId) throws JPAException {

        Loan loan = loanService.getLoan(entityId);

        JSONObject jsonObject = new JSONObject();

        Employee employee = loan.getEmployee();

        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("emailId", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("dateOfJoiningService", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("dateOfJoiningPrior", DateFormatterUtil.formatDateR(employeeService.getDateOfJoiningPrior(employee)));
        jsonObject.put("membershipYears", employeeService.getMembershipYears(employee));
        jsonObject.put("dateOfBirth", DateFormatterUtil.formatDateR(employee.getDateOfBirth()));
        jsonObject.put("age", employee.getEmployeeAge());
        jsonObject.put("status", employee.getContributionStatus().getSymbol());
        jsonObject.put("pfBase", contributionService.getLatestPfBase(employee));
        jsonObject.put("unitCode", employee.getUnitCode());

        List<LoanType> eligibleLoanTypes = loanService.getEligibleLoanTypes(loan);

        JSONArray jsonArray = new JSONArray();

        for (LoanType loanType:eligibleLoanTypes) {
            JSONObject loanTypeObject = new JSONObject();
            loanTypeObject.put("name", loanType.getTitle());
            loanTypeObject.put("entityId", loanType.getEntityId());
            loanTypeObject.put("code", loanType.getCode());
            jsonArray.put(loanTypeObject);
        }

        jsonObject.put("loanTypes", jsonArray);

        jsonObject.put("selectedLoanType", loan.getLoanType().getEntityId());

        JSONArray documents = new JSONArray();

        for (Document document:loan.getLoanType().getDocuments()) {
            JSONObject documentJsonObject = new JSONObject();
            documentJsonObject.put("name", document.getName());
            documentJsonObject.put("entityId", document.getEntityId());
            documents.put(documentJsonObject);
        }

        jsonObject.put("documents", documents);

        JSONArray uploadedDocuments = new JSONArray();

        loan.getLoanDocuments().forEach(loanDocument -> {
            JSONObject documentObject = new JSONObject();
            documentObject.put("path", loanDocument.getPath());
            documentObject.put("documentId", loanDocument.getDocument().getEntityId());
            documentObject.put("entityId", loanDocument.getEntityId());
            uploadedDocuments.put(documentObject);
        });

        jsonObject.put("uploadedDocuments", uploadedDocuments);

        jsonObject.put("alternateEmailId", loan.getAltEmail());
        jsonObject.put("alternateMobileNumber", loan.getAltContactNumber());
        jsonObject.put("applicationReceivedDate", loan.getLoanApplicationReceivedDate());
        jsonObject.put("totalCost", loan.getTotalCost());
        jsonObject.put("appliedAmount", loan.getAppliedAmount());
        jsonObject.put("dateOfCompletionOfHouse", loan.getDateOfCompletionOfHouse());
        jsonObject.put("dateOfFirstAlteration", loan.getDateOfFirstAlteration());
        jsonObject.put("propertyEstimatedPrice", loan.getPropertyEstimatedPrice());
        jsonObject.put("stampDuty", loan.getStampDuty());
        jsonObject.put("registration", loan.getPropertyRegistration());
        jsonObject.put("insurance", loan.getInsurance());
        jsonObject.put("others", loan.getOther());
        jsonObject.put("total", loan.getTotal());
        jsonObject.put("financialInstituteName", loan.getFinancialInstName());
        jsonObject.put("bank", loan.getRepaymentBank());
        jsonObject.put("branch", loan.getRepaymentBankBranch());
        jsonObject.put("accountNumber", loan.getRepaymentBankAccountNumber());
        jsonObject.put("ifscCode", loan.getRepaymentBankIfscCode());
        jsonObject.put("micrCode", loan.getRepaymentBankMicrCode());
        jsonObject.put("employeeBankName", loan.getEmployeeBank());
        jsonObject.put("employeeAccountNumber", loan.getEmployeeAccountNumber());
        jsonObject.put("employeeBankBranch", loan.getEmployeeBankBranch());
        jsonObject.put("employeeIfscCode", loan.getEmployeeBankIfscCode());
        jsonObject.put("employeeMicrCode", loan.getEmployeeBankMicrCode());
        jsonObject.put("employeeBankSwiftCode", loan.getEmployeeBankSwiftCode());

        JSONObject eligibleLoanAmount = loanService.getEligibleLoanAmount(employee,
                loan.getAppliedAmount(), loan.getTotalCost(), loan.getLoanType());

        jsonObject.put("eligibleLoanAmount", eligibleLoanAmount);

        return Response.success(jsonObject.toString());
    }

    @PutMapping("/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> update(@PathVariable String entityId, @RequestBody String requestBody) throws ParseException, JPAException {

        Loan fetchedLoan = loanService.getLoan(entityId);

        JSONObject jsonObject = new JSONObject(requestBody);

        Employee employee = fetchedLoan.getEmployee();

        Loan loan = Loan.build(jsonObject, fetchedLoan);

        LoanType loanType = loanService.getLoanType(jsonObject.getString("selectedLoanType"));

        loan.setLoanType(loanType);

        Loan createLoan = loanService.update(employee, loanType, loan);

        loanService.updateLoanDocuments(jsonObject.getJSONArray("documents"));

        JSONObject responseObj = new JSONObject();
        responseObj.put("id", createLoan.getEntityId());
        responseObj.put("message", "Loan Application Updated Successfully with Application ID " + createLoan.getEntityId());

        return Response.success(responseObj.toString());

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

    @GetMapping("/emailList")
    @ApplyTenantFilter
    public ResponseEntity<Object> getTransferOutListToSendEmails(){

        List<LoanEmailStatus> emailStatusListNotSent = loanService.getEmailStatusListNotSent();

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

        List<LoanEmailStatus> loanEmailStatusList = loanService.getLoanEmailStatusList(entityIds);

        loanEmailStatusList.forEach(loanEmailStatus -> {
            try {

                loanService.sendConfirmationEmail(loanEmailStatus, new ArrayList<>());

                loanService.updateEmailStatus(loanEmailStatus);

            }catch (Exception exception){
                System.out.println(exception.getLocalizedMessage());
            }
        });

        return Response.success("all emails sent successfully.");
    }


    @PostMapping("/bulk/checkEligibility")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEligibleLoanAmountBulk(@RequestBody String request) {

        JSONObject requestObj = new JSONObject(request);

        JSONArray applications = requestObj.getJSONArray("applications");

        int count = applications.length();

        JSONArray eligibility = new JSONArray();

        for(int i=0; i<count; i++){

            JSONObject jsonObject = applications.getJSONObject(i);
            jsonObject.put("S.NO.", i);
            jsonObject.put("applicationId", UUID.randomUUID().toString());

            try {

                BigDecimal appliedAmount = jsonObject.getBigDecimal("Applying For");
                BigDecimal totalCost = jsonObject.getBigDecimal("Total Cost");
                String pernNumber = jsonObject.getString("Pern No");
                String loanType = jsonObject.getString("Loan Type");

                Employee employee = employeeService.getEmployeeByPern(pernNumber);

                jsonObject.put("pfNumber", employee.getPfNumber());
                jsonObject.put("name", employee.getName());

                LoanType selectedLoanType = loanService.getLoanTypeByCode(loanType);

                jsonObject.put("appliedLoanType", selectedLoanType.getTitle());
                jsonObject.put("loanTypeId", selectedLoanType.getEntityId());

                JSONArray documents = new JSONArray();

                for (Document document:selectedLoanType.getDocuments()) {
                    JSONObject documentObj = new JSONObject();
                    documentObj.put("name", document.getName());
                    documentObj.put("entityId", document.getEntityId());
                    documents.put(documentObj);
                }

                jsonObject.put("loanDocuments", documents);

                loanService.checkLoanTypeEligibility(employee, selectedLoanType);

                JSONObject eligibleLoanAmount = loanService.getEligibleLoanAmount(employee,
                        appliedAmount, totalCost, selectedLoanType);

                jsonObject.put("status", "success");
                jsonObject.put("eligibleLoanAmount", eligibleLoanAmount);
                jsonObject.put("reason", "eligible");

            }catch (Exception ex){
                jsonObject.put("status", "failed");
                jsonObject.put("reason", ex.getLocalizedMessage());
            }

            eligibility.put(jsonObject);

        }

        return Response.success(eligibility.toString());

    }

    @GetMapping(value = "/getTemplate")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getTemplate(@RequestParam(required = false,
            defaultValue = "LoanProcessingTemplate") String template) {

        String templateFileName = "";

        if ("ApprovePendingLoansTemplate".equals(template)) {
            templateFileName = "ApprovePendingLoansTemplate.xlsx";
        }else {
            templateFileName = "LoanProcessingTemplate.xlsx";
        }

        try (InputStream in = Model.class.getClassLoader()
                .getResourceAsStream("data_templates/" + templateFileName)) {

            if(in == null) {
                return ResponseEntity.badRequest().body(null);
            }

            return ResponseEntity.ok().body(IOUtils.toByteArray(in));

        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);

    }

    @PostMapping("/bulk/process-applications")
    @ApplyTenantFilter
    public ResponseEntity<Object> launchProcessLoanApplicationsJob(@RequestBody LaunchJobDTO jobDTO){

        Job job = jobLaunchService.launchJob(jobDTO.getType(), jobDTO.getParams());

        return Response.success(job);
    }

    @GetMapping("/bulk/process-applications/{jobId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getProcessLoanApplicationsJobDetails(@PathVariable String jobId){

        Job job = jobLaunchService.getJob(jobId);

        JSONObject jobDetailsJson = job.getJobDetailsJson();
        jobDetailsJson.remove("params");

        return Response.success(jobDetailsJson.toString());

    }

    @GetMapping("/bulk/process-applications")
    @ApplyTenantFilter
    public ResponseEntity<Object> getProcessLoanJobs(){

        List<Job> jobs = jobLaunchService.getJobs("process_loan_applications_job");

        List<JobDTO> jobDTOList = jobs.stream().map(job -> {

                    JSONObject jobDetailsJson = job.getJobDetailsJson();

                    JobDTO jobDTO = new JobDTO(job.getEntityId(), job.getCreatedAt(), job.getUpdatedAt(),
                            Instant.parse(jobDetailsJson.getString("startedAt")),
                            jobDetailsJson.has("completedAt") ? Instant.parse(jobDetailsJson.getString("completedAt")) : null,
                            job.getName(), jobDetailsJson.getString("status"),
                            jobDetailsJson.has("duration") ? jobDetailsJson.getLong("duration") : 0);

                    jobDTO.setParams(jobDetailsJson.getJSONArray("processedApplications").toString());

                    return jobDTO;
                })
                .collect(Collectors.toList());

        return Response.success(jobDTOList);

    }

    @PostMapping("/bulk/pending-applications")
    @ApplyTenantFilter
    public ResponseEntity<Object> getPendingLoanApplications(@RequestBody String request){

        JSONObject jsonRequest = new JSONObject(request);

        JSONArray pernNumbers = jsonRequest.getJSONArray("pernNumbers");

        JSONArray responseArr = new JSONArray();

        for(int i=0; i<pernNumbers.length(); i++){

            try {

                Employee employee = employeeService.getEmployeeByPern(pernNumbers.getString(i));

                Loan pendingLoan = loanService.getPendingLoan(employee);

                JSONObject responseObj = new JSONObject();
                responseObj.put("pernNumber", employee.getPernNumber());
                responseObj.put("name", employee.getName());
                responseObj.put("appliedAt", DateFormatterUtil.formatDateR(pendingLoan.getCreatedAt()));
                responseObj.put("loanType", pendingLoan.getLoanType().getTitle());
                responseObj.put("loanAmount", pendingLoan.getEligibleAmount());
                responseObj.put("id", pendingLoan.getEntityId());
                responseObj.put("status", "success");

                responseArr.put(responseObj);

            }catch (Exception e){

                JSONObject responseObj = new JSONObject();
                responseObj.put("pernNumber", pernNumbers.getString(i));
                responseObj.put("name", "-");
                responseObj.put("appliedAt", "-");
                responseObj.put("loanType", e.getLocalizedMessage());
                responseObj.put("loanAmount", "-");
                responseObj.put("id", UUID.randomUUID().toString());
                responseObj.put("status", "failed");

                responseArr.put(responseObj);

            }

        }

        JSONObject response = new JSONObject();
        response.put("applications", responseArr);

        return Response.success(response.toString());

    }

    @PostMapping("/bulk/process-applications/approve")
    @ApplyTenantFilter
    public ResponseEntity<Object> launchApproveLoansJob(@RequestBody LaunchJobDTO jobDTO){

        Job job = jobLaunchService.launchJob(jobDTO.getType(), jobDTO.getParams());

        return Response.success(job);
    }

    @GetMapping("/getLoanTypes")
    @ApplyTenantFilter
    public ResponseEntity<Object> getLoanTypes(
           ){

        List<LoanType> loanTypes = loanService.getLoanTypes();

        List<LoanTypeDTO> loanTypeDTOS = loanTypes.stream().map(loanType -> {
            return new LoanTypeDTO(loanType.getTitle(), loanType.getCode(), loanType.getLoanGroup(),
                    loanType.getMinimumMembershipTenureInMonths(), loanType.getMaximumNumberOfWithdrawals(),loanType.getEntityId(),
                    loanType.getFromRetirementDate(),loanType.getNextEligibility());
        }).collect(Collectors.toList());

        return Response.success(loanTypeDTOS);
    }

    @PostMapping("/loan-types/saveLoanType")
    @ApplyTenantFilter
    public ResponseEntity<LoanType> saveLoanType( @RequestBody String requestBody){

        JSONObject jsonObject = new JSONObject(requestBody);

        LoanType loanType = new LoanType();
        loanType.setCode(jsonObject.getString("loanCode"));
        loanType.setLoanGroup(jsonObject.getString("loanGroup"));
        loanType.setTitle(jsonObject.getString("loanTitle"));
        loanType.setMinimumMembershipTenureInMonths(
                Integer.parseInt(jsonObject.getString("tenure")));
        loanType.setMaximumNumberOfWithdrawals(
                Integer.parseInt(jsonObject.getString("withdrawal")));
        loanType.setFromRetirementDate(
                Integer.parseInt(jsonObject.getString("retirementDate")));
        loanType.setNextEligibility(
                Integer.parseInt(jsonObject.getString("nextEligibility")));
        loanType.setPfBaseSalaryInMonth(
                Integer.parseInt(jsonObject.getString("pfBaseSalary")));
        loanType.setMemberBalanceInPercentage(
                Integer.parseInt(jsonObject.getString("memberContribution")));
        loanType.setCompanyBalanceInPercentage(
                Integer.parseInt(jsonObject.getString("companyContribution")));
        loanType.setVpfBalanceInPercentage(
                Integer.parseInt(jsonObject.getString("vpfContribution")));
        loanType.setTotalCostInPercentage(
                Integer.parseInt(jsonObject.getString("totalCost")));
        loanType.setAppliedAmountInPercentage(
                Integer.parseInt(jsonObject.getString("appliedAmount")));

        boolean saved = loanService.saveLoanType(loanType);

        if(saved){
            return new ResponseEntity<>(loanType, HttpStatus.OK);
        }
       return new ResponseEntity<>(loanType, HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/approveApplication/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> changeStatus(@PathVariable String entityId) throws ParseException {

        Loan loan = loanService.getLoan(entityId);

        String loanStatusTitile = loan.getLoanStatus().getTitle();

        if(loanStatusTitile.equalsIgnoreCase("Pending")) {
        	
        	LoanStatus loanStatus = loanStatusRepository.findByTitleAndIsActive("Payment Pending", true);
        	loan.setLoanStatus(loanStatus);
        	loanService.updateLoanStatus(loan);
        	return Response.success("Application Aproved");
        } 
        
        return Response.failure("Approval failed");

    }
}
