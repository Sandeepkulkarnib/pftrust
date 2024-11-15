package com.coreintegra.pftrust.controllers.pf.transferin;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.TransferInReminders;
import com.coreintegra.pftrust.dtos.pdf.TransferInRequestLetter;
import com.coreintegra.pftrust.entities.pf.PfAccountHolder;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDTO;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDetails;
import com.coreintegra.pftrust.entities.pf.department.dtos.LaunchJobDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.transferIn.*;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.*;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import com.coreintegra.pftrust.services.pf.pdf.GenerateTransferInRequestLetter;
import com.coreintegra.pftrust.services.pf.transferin.TransferInService;
import com.coreintegra.pftrust.services.pf.transferin.dtos.SaveAnnexurekDTO;
import com.coreintegra.pftrust.services.storage.FileStorageService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.coreintegra.pftrust.util.NumberFormatter;
import com.coreintegra.pftrust.util.Response;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.coreintegra.pftrust.util.FinancialYearAndMonth.getDate;

@RestController
@RequestMapping(TransferInServiceEndpoints.TRANSFER_IN_SERVICE_ENDPOINT)
public class TransferInServiceController {

    private final Path fileStorageLocation = Paths.get("")
            .toAbsolutePath().normalize();

    private final TransferInService transferInService;
    private final JobLaunchService jobLaunchService;
    private final FileStorageService fileStorageService;
    private final GenerateTransferInRequestLetter generateTransferInRequestLetter;
    private final EmployeeService employeeService;


    public TransferInServiceController(TransferInService transferInService,
                                       JobLaunchService jobLaunchService,
                                       FileStorageService fileStorageService,
                                       GenerateTransferInRequestLetter generateTransferInRequestLetter,
                                       EmployeeService employeeService) {
        this.transferInService = transferInService;
        this.jobLaunchService = jobLaunchService;
        this.fileStorageService = fileStorageService;
        this.generateTransferInRequestLetter = generateTransferInRequestLetter;
        this.employeeService = employeeService;
    }

    @GetMapping
    @ApplyTenantFilter
    public ResponseEntity<Object> getTransferIns(
            @RequestParam(value = "search", required = false, defaultValue = "")String search,
            @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10")Integer size){

        Page<TransferIn> transferIns = transferInService.getTransferIns(search, size, page);

        Page<SearchTransferInDTO> searchTransferInDTOS = transferIns.map(transferIn -> new SearchTransferInDTO(
                transferIn.getEntityId(),
                transferIn.getEmployee().getPernNumber(), transferIn.getEmployee().getPfNumber(),
                transferIn.getEmployee().getName(), transferIn.getEmployee().getUnitCode(),
                transferIn.getPreviousCompany().getName(),
                transferIn.getPrevPfNumber(), transferIn.getPostingDate(),
                transferIn.getCreatedAtTimestamp(), transferIn.getTransferInStatus().getCode()));

        return Response.success(searchTransferInDTOS);
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
        Job job = jobLaunchService.launchReportJob("transfer_in_report_job", jobDTO.getParams());
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
        EssentialsDTO essentials = transferInService.getEssentials();
        return Response.success(essentials);
    }

    @GetMapping("/details/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getDetails(@PathVariable String entityId){

        TransferIn transferIn = transferInService.getTransferIn(entityId);

        TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

        if(transferInFinalDetails == null){
            transferInFinalDetails = new TransferInFinalDetails();
        }

        Employee employee = transferIn.getEmployee();

        TransferInStatus transferInStatus = transferIn.getTransferInStatus();

        PreviousCompany previousCompany = transferIn.getPreviousCompany();

        PfAccountHolder prevPfAccountHolder = transferIn.getPrevPfAccountHolder();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("unitCode", employee.getUnitCode());
        jsonObject.put("name", employee.getName());
        jsonObject.put("dateOfJoiningService", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("dateOfJoiningPf", DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
        jsonObject.put("email", employee.getEmail());
        jsonObject.put("contactNumber", employee.getContactNumber());
        jsonObject.put("altEmailId", transferIn.getAlternateEmailId());
        jsonObject.put("altContactNumber", transferIn.getAlternateContactNumber());

        jsonObject.put("prevPfNumber", transferIn.getPrevPfNumber());
        jsonObject.put("prevEPSNumber", transferIn.getPresEPSNumber());
        jsonObject.put("prevAccountHolder", prevPfAccountHolder.getName());
        jsonObject.put("prevDateOfJoining", DateFormatterUtil.formatDateR(transferIn.getPrevDateOfJoining()));
        jsonObject.put("prevDateOfLeaving", DateFormatterUtil.formatDateR(transferIn.getPrevDateOfLeaving()));
        jsonObject.put("addressLine1", transferIn.getAddressLine1());
        jsonObject.put("addressLine2", transferIn.getAddressLine2());
        jsonObject.put("addressLine3", transferIn.getAddressLine3());
        jsonObject.put("addressLine4", transferIn.getAddressLine4());

        jsonObject.put("prevCompanyName", previousCompany.getName());
        jsonObject.put("prevCompanyDateOfJoining", DateFormatterUtil.formatDateR(previousCompany.getDateOfJoining()));
        jsonObject.put("prevCompanyDateOfLeaving", DateFormatterUtil.formatDateR(previousCompany.getDateOfLeaving()));
        jsonObject.put("prevCompanyAddressLine1", previousCompany.getAddressLine1());
        jsonObject.put("prevCompanyAddressLine2", previousCompany.getAddressLine2());
        jsonObject.put("prevCompanyAddressLine3", previousCompany.getAddressLine3());
        jsonObject.put("prevCompanyAddressLine4", previousCompany.getAddressLine4());

        jsonObject.put("presPfNumber", transferIn.getPresPfNumber());
        jsonObject.put("presEPSNumber", transferIn.getPresEPSNumber());

        jsonObject.put("createdAt", DateFormatterUtil.formatDateR(transferIn.getCreatedAtTimestamp()));

        jsonObject.put("memberContribution", NumberFormatter.formatNumbers(transferInFinalDetails.getMemberContribution()));
        jsonObject.put("companyContribution", NumberFormatter.formatNumbers(transferInFinalDetails.getCompanyContribution()));
        jsonObject.put("totalContribution", NumberFormatter.formatNumbers(transferInFinalDetails.getTotal()));

        jsonObject.put("annexureKFile", transferIn.getAnnexureKFile());
        jsonObject.put("dispatchLetterFile", transferIn.getDispatchLetterFile());

        jsonObject.put("status", transferInStatus.getTitle());
        jsonObject.put("isCompleted", transferInStatus.getCode().equalsIgnoreCase("R"));
        jsonObject.put("isInProgress", transferInStatus.getCode().equalsIgnoreCase("P"));
        jsonObject.put("isCanceled", transferInStatus.getCode().equalsIgnoreCase("C"));

        List<TransferInReminder> reminders = transferInService.getAllReminders(transferIn);

        TransferInReminder transferInReminder = reminders.get(reminders.size() - 1) ;

        jsonObject.put("reminderId", transferInReminder.getEntityId());

        return Response.success(jsonObject.toString());

    }

    @PutMapping("/reject/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> reject(@PathVariable String entityId) {
        transferInService.reject(entityId);
        return Response.success("status updated successfully.");
    }

    @PostMapping(value = "/uploadAnnexureK/{entityId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApplyTenantFilter
    public ResponseEntity<Object> uploadAnnexureK(@PathVariable String entityId,
                                                  HttpServletRequest httpServletRequest) throws ParseException {

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        MultipartHttpServletRequest request = commonsMultipartResolver.resolveMultipart(httpServletRequest);

        Iterator<String> itr =  request.getFileNames();

        SaveAnnexurekDTO saveAnnexurekDTO = new SaveAnnexurekDTO();

        while (itr.hasNext()){

            String next = itr.next();

            MultipartFile file = request.getFile(next);

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            String fileName = next + "_" + new java.util.Date().getTime() + "_" + entityId + "." + extension;

            String storeFile = fileStorageService.storeFile(file, fileName);

            if(next.equals("annexurk")){
                saveAnnexurekDTO.setAnnexureKFile(fileName);
            }

            if(next.equals("dispatchLetter")){
                saveAnnexurekDTO.setDispatchLetterFile(fileName);
            }

        }

        saveAnnexurekDTO.setMemberContribution(new BigDecimal(request.getParameter("memberContribution")));
        saveAnnexurekDTO.setCompanyContribution(new BigDecimal(request.getParameter("companyContribution")));

        saveAnnexurekDTO.setPostingDate(getDate(request.getParameter("postingDate")));
        saveAnnexurekDTO.setDateOfJoiningPrior(getDate(request.getParameter("dateOfJoiningPrior")));

        saveAnnexurekDTO.setBank(request.getParameter("bank"));
        saveAnnexurekDTO.setRefNumber(request.getParameter("refNumber"));
        saveAnnexurekDTO.setChequeNumber(request.getParameter("chequeNumber"));

        TransferIn transferIn = transferInService.getTransferIn(entityId);

        TransferIn savedTransferIn = transferInService.saveAnnexueKDetails(transferIn, saveAnnexurekDTO);

        TransferInEmailStatus emailStatus = transferInService.createEmailStatus(savedTransferIn);

        String sendConfirmationEmail = request.getParameter("sendConfirmationEmail");

        if(sendConfirmationEmail != null && !sendConfirmationEmail.isEmpty() && !sendConfirmationEmail.isBlank() &&
        sendConfirmationEmail.equalsIgnoreCase("true")){

            transferInService.sendConfirmationEmail(emailStatus);

            transferInService.updateTransferInEmailStatus(emailStatus);

        }

        return Response.success("details uploaded successfully.");

    }


    @GetMapping("/{entityId}/getRequestLetter")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateAn(@PathVariable("entityId") String entityId) {

        try {

            TransferInReminder transferInReminder = transferInService.getTransferInReminder(entityId);

            TransferIn transferIn = transferInReminder.getTransferIn();

            TransferInRequestLetter transferInRequestLetter = new TransferInRequestLetter();

            String rfno = "SPF";

            int financialYear = FinancialYearAndMonth.getFinancialYear(new Date());

            rfno += "/F" + (financialYear-1) + "-" + (financialYear) + "/" + transferInReminder.getId();

            transferInRequestLetter.setRefNo(rfno);

            transferInRequestLetter.setApplicationDate(transferInReminder.getCreatedAt());

            transferInRequestLetter.setAddressLine1(transferIn.getAddressLine1());
            transferInRequestLetter.setAddressLine2(transferIn.getAddressLine2());
            transferInRequestLetter.setAddressLine3(transferIn.getAddressLine3());
            transferInRequestLetter.setAddressLine4(transferIn.getAddressLine4());

            Employee employee = transferIn.getEmployee();

            transferInRequestLetter.setName(employee.getName());

            transferInRequestLetter.setFromPfAccount(transferIn.getPrevPfNumber());
            transferInRequestLetter.setToPfAccount(transferIn.getPresPfNumber());

            transferInRequestLetter.setFromEpsAccount(transferIn.getPrevPensionAccountNumber());
            transferInRequestLetter.setToEpsAccount(transferIn.getPresEPSNumber());

            transferInRequestLetter.setPrevCompanyName(transferIn.getPreviousCompany().getName());

            List<TransferInReminder> transferInReminders = transferInService.getAllReminders(transferIn);

            transferInReminders.removeIf(reminder -> reminder.getEntityId().equalsIgnoreCase(entityId));

            List<TransferInReminders> reminders = new ArrayList<>();

            for (TransferInReminder reminder:transferInReminders) {
                reminders.add(new TransferInReminders(reminder.getId(), reminder.getCreatedAt()));
            }

            transferInRequestLetter.setReminders(reminders);

            String filename = "requestLetter_" + employee.getPfNumber() + ".pdf";

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.add("Content-Disposition", "attachment; filename="+filename);
            headers.add("X-Suggested-Filename", filename);
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

            ByteArrayOutputStream out = generateTransferInRequestLetter.generate(transferInRequestLetter);

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);

    }

    @PostMapping("/generateReminder/{reminderId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> generateReminder(@PathVariable(value = "reminderId") String reminderId){

        TransferInReminder transferInReminder = transferInService.generateTransferInReminder(reminderId);

        Map<String, Object> response = new HashMap<>();

        response.put("reminderId", transferInReminder.getEntityId());

        return Response.success(response);

    }


    @GetMapping("/forEmailList")
    @ApplyTenantFilter
    public ResponseEntity<Object> getTransferInListToSendEmails(){

        List<TransferInEmailStatus> emailStatusListNotSent = transferInService.getEmailStatusListNotSent();

        List<TransferInEmailDTO> transferInEmailDTOList = emailStatusListNotSent.stream()
                .map(emailStatus -> new TransferInEmailDTO(emailStatus.getEntityId(), emailStatus.getPfNumber(),
                        emailStatus.getName(), emailStatus.getUnitCode(), emailStatus.getPostingDate(),
                        emailStatus.getPrevCompany(), emailStatus.getEmailId()))
                .collect(Collectors.toList());

        return Response.success(transferInEmailDTOList);

    }

    @PostMapping("/sendEmails")
    @ApplyTenantFilter
    public ResponseEntity<Object> sendConfirmationEmails(@RequestBody List<String> entityIds){

        List<TransferInEmailStatus> transferInEmailStatusList = transferInService.getTransferInEmailStatusList(entityIds);

        transferInEmailStatusList.forEach(transferInEmailStatus -> {
            try {
                transferInService.sendConfirmationEmail(transferInEmailStatus);
                transferInService.updateTransferInEmailStatus(transferInEmailStatus);
            }catch (Exception exception){
                System.out.println(exception.getLocalizedMessage());
            }

        });

        return Response.success("all emails sent successfully.");
    }


    @GetMapping("/employeeDetails/{pernNumber}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable String pernNumber){

        Employee employee = employeeService.getEmployeeByPern(pernNumber);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("mobileNumber", employee.getContactNumber());
        jsonObject.put("emailId", employee.getEmail());
        jsonObject.put("epsNumber", employee.getToEPS());
        jsonObject.put("dateOfJoining", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("unitCode",employee.getUnitCode());

        Map<String, Object> map = new HashMap<>();
        map.put("details", jsonObject.toString());

        return Response.success(map);

    }

    @GetMapping("/employeeDetailsByPf/{pfNumber}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployeeDetailsByPf(@PathVariable String pfNumber){

        Employee employee = employeeService.getEmployeeByPf(pfNumber);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("mobileNumber", employee.getContactNumber());
        jsonObject.put("emailId", employee.getEmail());
        jsonObject.put("epsNumber", employee.getToEPS());
        jsonObject.put("dateOfJoining", DateFormatterUtil.formatDateR(employee.getDateOfJoining()));
        jsonObject.put("unitCode",employee.getUnitCode());

        Map<String, Object> map = new HashMap<>();
        map.put("details", jsonObject.toString());

        return Response.success(map);

    }


    @PostMapping()
    @ApplyTenantFilter
    public ResponseEntity<Object> create(@RequestBody String request) throws ParseException {

        JSONObject jsonObject = new JSONObject(request);

        TransferIn transferIn = TransferIn.build(jsonObject);

        JSONObject previousCompanyObject = jsonObject.getJSONObject("previousCompany");

        PreviousCompany previousCompany = PreviousCompany.build(previousCompanyObject);

        Employee employee = employeeService.getEmployeeByPern(jsonObject.getString("pernNumber"));

        PfAccountHolder pfAccountHolder = new PfAccountHolder();

        if(jsonObject.getString("prevAccountHolder").equalsIgnoreCase("06")){
            pfAccountHolder.setId(1L);
        }else {
            pfAccountHolder.setId(2L);
        }

        transferIn.setPrevPfAccountHolder(pfAccountHolder);

        TransferIn savedTransferIn = transferInService.save(transferIn, employee, previousCompany);

        JSONObject responseObj = new JSONObject();
        responseObj.put("id", savedTransferIn.getEntityId());
        responseObj.put("message", "Transfer In Application Created Successfully with Application ID " + savedTransferIn.getEntityId());

        return Response.success(responseObj.toString());

    }

    @GetMapping("/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> get(@PathVariable String entityId){

        TransferIn transferIn = transferInService.getTransferIn(entityId);

        Employee employee = transferIn.getEmployee();

        PreviousCompany previousCompany = transferIn.getPreviousCompany();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("pernNumber", employee.getPernNumber());
        jsonObject.put("pfNumber", employee.getPfNumber());
        jsonObject.put("tokenNumber", employee.getTokenNumber());
        jsonObject.put("name", employee.getName());
        jsonObject.put("mobileNumber", employee.getContactNumber());
        jsonObject.put("email", employee.getEmail());

        jsonObject.put("alternateMobileNumber", transferIn.getAlternateContactNumber());
        jsonObject.put("alternateEmailId", transferIn.getAlternateEmailId());
        jsonObject.put("prevPfNumber", transferIn.getPrevPfNumber());
        jsonObject.put("prevEpsNumber", transferIn.getPrevPensionAccountNumber());
        jsonObject.put("prevAccountHolder", transferIn.getPrevPfAccountHolder().getCode());
        jsonObject.put("addressLine1", transferIn.getAddressLine1());
        jsonObject.put("addressLine2", transferIn.getAddressLine2());
        jsonObject.put("addressLine3", transferIn.getAddressLine3());
        jsonObject.put("addressLine4", transferIn.getAddressLine4());
        jsonObject.put("pfAccountNumber", transferIn.getPresPfNumber());
        jsonObject.put("epsAccountNumber", transferIn.getPresEPSNumber());
        jsonObject.put("dateOfJoining", DateFormatterUtil.format(employee.getDateOfJoiningPF(), "yyyy-MM-dd"));
        jsonObject.put("unitCode",employee.getUnitCode());

        JSONObject previousCompanyObject = new JSONObject();

        previousCompanyObject.put("companyName", previousCompany.getName());
        previousCompanyObject.put("companyDateOfJoining", DateFormatterUtil.format(previousCompany.getDateOfJoining(), "yyyy-MM-dd"));
        previousCompanyObject.put("companyDateOfLeaving", DateFormatterUtil.format(previousCompany.getDateOfLeaving(), "yyyy-MM-dd"));
        previousCompanyObject.put("addressLine1", previousCompany.getAddressLine1());
        previousCompanyObject.put("addressLine2", previousCompany.getAddressLine2());
        previousCompanyObject.put("addressLine3", previousCompany.getAddressLine3());
        previousCompanyObject.put("addressLine4", previousCompany.getAddressLine4());

        jsonObject.put("previousCompany", previousCompanyObject);

        return Response.success(jsonObject.toString());

    }


    @PutMapping("/{entityId}")
    @ApplyTenantFilter
    public ResponseEntity<Object> update(@PathVariable String entityId, @RequestBody String request) throws ParseException {

        TransferIn savedTransferIn = transferInService.getTransferIn(entityId);

        JSONObject jsonObject = new JSONObject(request);

        savedTransferIn.set(jsonObject);

        PreviousCompany savePreviousCompany = savedTransferIn.getPreviousCompany();

        JSONObject previousCompanyObject = jsonObject.getJSONObject("previousCompany");

        savePreviousCompany.set(previousCompanyObject);

        Employee employee = employeeService.getEmployeeByPern(jsonObject.getString("pernNumber"));

        PfAccountHolder pfAccountHolder = new PfAccountHolder();

        if(jsonObject.getString("prevAccountHolder").equalsIgnoreCase("06")){
            pfAccountHolder.setId(1L);
        }else {
            pfAccountHolder.setId(2L);
        }

        savedTransferIn.setPrevPfAccountHolder(pfAccountHolder);

        TransferIn updatedTransferIn = transferInService.save(savedTransferIn, employee, savePreviousCompany);

        JSONObject responseObj = new JSONObject();
        responseObj.put("id", updatedTransferIn.getEntityId());
        responseObj.put("message", "Transfer In Application Update Successfully with Application ID " + updatedTransferIn.getEntityId());

        return Response.success(responseObj.toString());

    }



}
