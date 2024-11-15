package com.coreintegra.pftrust.controllers.pf.employee;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.pdf.MonthlyStatement;
import com.coreintegra.pftrust.dtos.pdf.NewMonthlyStatement;
import com.coreintegra.pftrust.dtos.pdf.yearend.AnnualStatement;
import com.coreintegra.pftrust.dtos.pdf.yearend.newdtos.NewAnnualStatement;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDTO;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDetails;
import com.coreintegra.pftrust.entities.pf.department.dtos.LaunchJobDTO;
import com.coreintegra.pftrust.entities.pf.employee.ContributionStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.dtos.EmployeeDetailsDTO;
import com.coreintegra.pftrust.entities.pf.employee.dtos.SearchEmployeeDTO;
import com.coreintegra.pftrust.entities.pf.loan.dtos.LoanDetailsDTO;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementDetailsDTO;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.TransferInDetailsDTO;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.projections.YearEndProcessYearAndVersion;
import com.coreintegra.pftrust.services.pf.department.FinancialYearAndMonthService;
import com.coreintegra.pftrust.services.pf.department.UnitCodeService;
import com.coreintegra.pftrust.services.pf.employees.AnnualStatementService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.services.pf.employees.MonthlyStatementService;
import com.coreintegra.pftrust.services.pf.employees.NewMonthlyStatementService;
import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import com.coreintegra.pftrust.services.pf.jobs.employees.FetchEmployeesJob;
import com.coreintegra.pftrust.services.pf.loans.LoanService;
import com.coreintegra.pftrust.services.pf.pdf.GenerateAnnualStatement;
import com.coreintegra.pftrust.services.pf.pdf.GenerateMonthlyStatement;
import com.coreintegra.pftrust.services.pf.pdf.NewGenerateAnnualStatement;
import com.coreintegra.pftrust.services.pf.pdf.NewGenerateMonthlyStatement;
import com.coreintegra.pftrust.services.pf.settlement.SettlementService;
import com.coreintegra.pftrust.services.pf.transferin.TransferInService;
import com.coreintegra.pftrust.services.pf.transferout.TransferOutService;
import com.coreintegra.pftrust.util.Response;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(EmployeeServiceEndpoints.EMPLOYEE_SERVICE_ENDPOINT)
public class EmployeeServiceController {

    private final Path fileStorageLocation = Paths.get("")
            .toAbsolutePath().normalize();

    private final EmployeeService employeeService;
    private final JobLaunchService jobLaunchService;
    private final UnitCodeService unitCodeService;
    private final FinancialYearAndMonthService financialYearAndMonthService;
    private final FetchEmployeesJob fetchEmployeesJob;

    private final GenerateMonthlyStatement generateMonthlyStatement;
    private final NewGenerateMonthlyStatement newGenerateMonthlyStatement;

    private final MonthlyStatementService monthlyStatementService;
    private final NewMonthlyStatementService newMonthlyStatementService;

    private final LoanService loanService;
    private final TransferInService transferInService;
    private final TransferOutService transferOutService;
    private final SettlementService settlementService;
    private final AnnualStatementService annualStatementService;
    private final NewGenerateAnnualStatement newGenerateAnnualStatement;

    public EmployeeServiceController(EmployeeService employeeService,
                                     JobLaunchService jobLaunchService,
                                     UnitCodeService unitCodeService,
                                     FinancialYearAndMonthService financialYearAndMonthService,
                                     GenerateMonthlyStatement generateMonthlyStatement,
                                     NewGenerateMonthlyStatement newGenerateMonthlyStatement,
                                     FetchEmployeesJob fetchEmployeesJob,
                                     MonthlyStatementService monthlyStatementService,
                                     NewMonthlyStatementService newMonthlyStatementService,
                                     LoanService loanService, TransferInService transferInService,
                                     TransferOutService transferOutService, SettlementService settlementService,
                                     AnnualStatementService annualStatementService,
                                     NewGenerateAnnualStatement newGenerateAnnualStatement) {
        this.employeeService = employeeService;
        this.jobLaunchService = jobLaunchService;
        this.unitCodeService = unitCodeService;
        this.financialYearAndMonthService = financialYearAndMonthService;
        this.fetchEmployeesJob = fetchEmployeesJob;
        this.generateMonthlyStatement = generateMonthlyStatement;
        this.newGenerateMonthlyStatement = newGenerateMonthlyStatement;
        this.monthlyStatementService = monthlyStatementService;
        this.newMonthlyStatementService = newMonthlyStatementService;
        this.loanService = loanService;
        this.transferInService = transferInService;
        this.transferOutService = transferOutService;
        this.settlementService = settlementService;
        this.annualStatementService = annualStatementService;
        this.newGenerateAnnualStatement = newGenerateAnnualStatement;
    }

    @GetMapping
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployees(
            @RequestParam(value = "search", required = false, defaultValue = "")String search,
            @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10")Integer size){

        Page<Employee> employees = employeeService.getEmployees(search, size, page);

        Page<SearchEmployeeDTO> searchEmployeeDTOS = employees.map(employee -> new SearchEmployeeDTO(
                employee.getEntityId(),
                employee.getTokenNumber(), employee.getPernNumber(),
                employee.getPfNumber(), employee.getName(), employee.getDateOfJoining(),
                employee.getDateOfJoiningPF(), employee.getDateOfBirth(),
                employee.getUnitCode(), employee.getContributionStatus().getSymbol()));

        return Response.success(searchEmployeeDTOS);
    }

    @PostMapping
    @ApplyTenantFilter
    public ResponseEntity<Object> validateEmployees(@RequestBody String newEmployeesRequest){

        JSONObject jsonObject = new JSONObject(newEmployeesRequest);

        JSONObject validateNewEmployees = employeeService.validateNewEmployees(jsonObject);

        return ResponseEntity.ok(validateNewEmployees.toString());
    }

    @PostMapping("/hire")
    @ApplyTenantFilter
    public ResponseEntity<Object> hireEmployees(@RequestBody String newEmployeesRequest) throws JPAException {

        JSONObject jsonObject = new JSONObject(newEmployeesRequest);

        JSONObject validateNewEmployees = employeeService.validateNewEmployees(jsonObject);

        JSONObject errors = validateNewEmployees.getJSONObject("errors");

        if(errors.keySet().size() > 0){
            throw new JPAException("invalid employees exists in the list");
        }

        Job job = jobLaunchService.launchJob("hire_new_employees_job", newEmployeesRequest);

        return Response.success(job);

    }

    @GetMapping("/jobs")
    @ApplyTenantFilter
    public ResponseEntity<Object> getJobs(){

        List<Job> jobs = jobLaunchService.getJobs("hire_new_employees_job");

        List<JobDTO> jobDTOList = formatJobList(jobs);

        return Response.success(jobDTOList);
    }

    private List<JobDTO> formatJobList(List<Job> jobs) {
        return jobs.stream().map(job -> {

            JSONObject jobDetailsJson = job.getJobDetailsJson();

            JobDTO jobDTO = new JobDTO(job.getEntityId(), job.getCreatedAt(), job.getUpdatedAt(),
                    Instant.parse(jobDetailsJson.getString("startedAt")),
                    jobDetailsJson.has("completedAt") ? Instant.parse(jobDetailsJson.getString("completedAt")) : null,
                    job.getName(), jobDetailsJson.getString("status"),
                    jobDetailsJson.has("duration") ? jobDetailsJson.getLong("duration") : 0);

            if(!jobDetailsJson.has("details")){
                jobDTO.setJobDetailsList(new ArrayList<>());
                return jobDTO;
            }

            JSONObject details = jobDetailsJson.getJSONObject("details");

            List<JobDetails> jobDetailsList = details.keySet().stream().map(key -> {

                JSONArray jsonArray = details.getJSONArray(key);

                if (jsonArray.length() == 2) {
                    JSONObject jsonObject = jsonArray.getJSONObject(1);
                    JobDetails jobDetails = new JobDetails(key, Instant.parse(jsonObject.getString("startedAt")),
                            jsonObject.has("completedAt") ? Instant.parse(jsonObject.getString("completedAt")) : null,
                            jsonObject.has("duration") ? jsonObject.getLong("duration") : 0,
                            jsonObject.getLong("success"),
                            jsonObject.getLong("failed"), jsonObject.getString("status"),
                            jsonObject.getLong("total"));

                    if(jsonObject.has("year")){
                        jobDetails.setYear(jsonObject.getInt("year"));
                    }

                    if(jsonObject.has("month")){
                        jobDetails.setMonth(jsonObject.getInt("month"));
                    }

                    return jobDetails;

                } else {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JobDetails jobDetails = new JobDetails(key, Instant.parse(jsonObject.getString("startedAt")),
                            jsonObject.has("completedAt") ? Instant.parse(jsonObject.getString("completedAt")) : null,
                            jsonObject.has("duration") ? jsonObject.getLong("duration") : 0,
                            jsonObject.getLong("success"),
                            jsonObject.getLong("failed"), jsonObject.getString("status"),
                            jsonObject.getLong("total"));

                    if(jsonObject.has("year")){
                        jobDetails.setYear(jsonObject.getInt("year"));
                    }

                    if(jsonObject.has("month")){
                        jobDetails.setMonth(jsonObject.getInt("month"));
                    }
                    return jobDetails;
                }

            }).collect(Collectors.toList());

            jobDTO.setJobDetailsList(jobDetailsList);

            return jobDTO;

        }).collect(Collectors.toList());
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
                        jsonObject.has("success") ? jsonObject.getLong("success") : 0,
                        jsonObject.has("failed") ? jsonObject.getLong("failed") : 0,
                        jsonObject.getString("status"),
                        jsonObject.has("total") ? jsonObject.getLong("total") : 0);
            }

        }).collect(Collectors.toList());

        jobDTO.setJobDetailsList(jobDetailsList);

        return Response.success(jobDTO);
    }

    @PostMapping("/report")
    @ApplyTenantFilter
    public ResponseEntity<Object> generateEmployeeReport(@RequestBody LaunchJobDTO jobDTO){
        Job job = jobLaunchService.launchReportJob("employees_report_job", jobDTO.getParams());
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

    @GetMapping("/commons")
    @ApplyTenantFilter
    public ResponseEntity<Object> getUnitCodes(){

        List<ContributionStatus> contributionStatusList = employeeService.getContributionStatusList();
        List<UnitCode> unitCodes = unitCodeService.get();

        List<Integer> years = financialYearAndMonthService.getYears();
        Map<Integer, String> financialMonths = financialYearAndMonthService.getFinancialMonths();

        financialMonths.remove(0);

        Map<String, Object> map = new HashMap<>();
        map.put("unitCodes", unitCodes);
        map.put("contributionStatusList", contributionStatusList);
        map.put("years", years);
        map.put("financialMonths", financialMonths);

        return Response.success(map);
    }

    @PostMapping("/monthly-status-process")
    @ApplyTenantFilter
    public ResponseEntity<Object> runMonthlyStatusProcess(@RequestBody LaunchJobDTO launchJobDTO) {
        Job job = jobLaunchService.launchJob("monthly_status_process_job", launchJobDTO.getParams());
        return Response.success(job);
    }

    @GetMapping("/monthly-status-process/jobs")
    @ApplyTenantFilter
    public ResponseEntity<Object> getMonthlyStatusProcessJobs(){

        List<Job> jobs = jobLaunchService.getJobs("monthly_status_process_job");

        List<JobDTO> jobDTOList = formatJobList(jobs);

        return Response.success(jobDTOList);

    }

    @PostMapping("/monthly-status-process/jobs/report")
    @ApplyTenantFilter
    public ResponseEntity<Object> generateMonthlyStatusReport(@RequestBody LaunchJobDTO jobDTO){
        Job job = jobLaunchService.launchReportJob("monthly_status_report_job", jobDTO.getParams());
        return Response.success(job);
    }



    @GetMapping(value = "/getMonthlyStatement")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateMonthlyStatement(@RequestParam("entityId") String entityId,
                                         @RequestParam("year") Integer year) throws ParseException {

        try {

            ByteArrayOutputStream out = null;
            String pfnumber = "";

            if(year >= 2022){
                NewMonthlyStatement monthlyStatementDetails = newMonthlyStatementService.getMonthlyStatementDetails(entityId, year);
                out = newGenerateMonthlyStatement.generate(monthlyStatementDetails);
                pfnumber = monthlyStatementDetails.getPfNumber();
            }else {
                MonthlyStatement monthlyStatementDetails = monthlyStatementService.getMonthlyStatementDetails(entityId, year);
                out = generateMonthlyStatement.generate(monthlyStatementDetails);
                pfnumber = monthlyStatementDetails.getPfNumber();
            }

            String filename = "monthly_statement"+"_"+pfnumber+"_"+year+".pdf";

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.add("Content-Disposition", "attachment; filename="+filename);
            headers.add("X-Suggested-Filename", filename);
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/details/{entityId}")
    public ResponseEntity<Object> details(@PathVariable String entityId) throws JPAException {

        Employee employee = employeeService.getEmployee(entityId);

        EmployeeDetailsDTO details = employeeService.getDetails(employee);

        List<LoanDetailsDTO> loanDetails = loanService.getLoanDetails(employee);

        List<TransferInDetailsDTO> transferInDetails = transferInService.getTransferInDetails(employee);

        List<SettlementDetailsDTO> transferOutDetails = transferOutService.getTransferOutDetails(employee);

        List<SettlementDetailsDTO> settlementDetails = settlementService.getSettlementDetails(employee);

        List<Integer> availableYears = annualStatementService.getAvailableYears(employee, true);

        Set<Integer> years = Set.copyOf(availableYears);

        settlementDetails.addAll(transferOutDetails);

        details.setLoanDetailsDTOS(loanDetails);

        details.setTransferInDetailsDTOS(transferInDetails);

        details.setSettlementDetailsDTOS(settlementDetails);

        details.setAnnualStatementYears(years);

        return Response.success(details);

    }


    @GetMapping(value = "/getAnnualStatement")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> generateAnnualStatement(@RequestParam("entityId") String entityId,
                                                          @RequestParam("year") Integer year) throws ParseException, JPAException {

        Employee employee = employeeService.getEmployee(entityId);

        NewAnnualStatement annualStatement = annualStatementService.getAnnualStatementNew(employee, year);

        String pfnumber = annualStatement.getPfNumber();

        String filename = "annual_statement"+"_"+pfnumber+"_"+year+".pdf";

        try {

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            headers.add("Content-Disposition", "attachment; filename="+filename);
            headers.add("X-Suggested-Filename", filename);
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

            ByteArrayOutputStream out = newGenerateAnnualStatement.generate(annualStatement);

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);

    }

    @GetMapping(value = "/getHiringTemplate")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getHiringTemplate() throws ParseException {

        try (InputStream in = Model.class.getClassLoader().getResourceAsStream("hiring_template/New-Employee-Hiring-Template.xlsx")) {

            if(in == null) {
                return ResponseEntity.badRequest().body(null);
            }

            return ResponseEntity.ok().body(IOUtils.toByteArray(in));

        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);
    }


    @PostMapping("/fetch")
    @ApplyTenantFilter
    public ResponseEntity<Object> launchByPern(@RequestParam String unitCode,@RequestParam String pernNumber ) throws Exception {

        Employee employee = fetchEmployeesJob.fetchEmployeesByPern(unitCode, pernNumber);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entityId", employee.getEntityId());
        jsonObject.put("message", "Employee Created Successfully.");

        return Response.success(jsonObject.toString());

    }


    @GetMapping("/getAnnualStatement/yearsAndVersions")
    public ResponseEntity<Object> getAnnualStatementYearsAndVersions(@RequestParam("entityId") String entityId) throws JPAException {

        Employee employee = employeeService.getEmployee(entityId);

        List<YearEndProcessYearAndVersion> yearsAndVersions = annualStatementService.getYearsAndVersions(employee);

        return Response.success(yearsAndVersions);

    }



}
