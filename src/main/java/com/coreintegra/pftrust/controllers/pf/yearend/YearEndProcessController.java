package com.coreintegra.pftrust.controllers.pf.yearend;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.YearEndProcessRequestDTO;
import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDTO;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDetails;
import com.coreintegra.pftrust.entities.pf.department.dtos.LaunchJobDTO;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.services.pf.department.InterestRateService;
import com.coreintegra.pftrust.services.pf.department.UnitCodeService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.coreintegra.pftrust.util.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(YearEndProcessServiceEndpoints.YEAR_END_PROCESS_SERVICE_ENDPOINT)
public class YearEndProcessController {

    private final Path fileStorageLocation = Paths.get("")
            .toAbsolutePath().normalize();

    private final UnitCodeService unitCodeService;

    private final JobLaunchService jobLaunchService;
    private final InterestRateService interestRateService;

    public YearEndProcessController(EmployeeService employeeService, UnitCodeService unitCodeService,
                                    JobLaunchService jobLaunchService, InterestRateService interestRateService) {
        this.unitCodeService = unitCodeService;
        this.jobLaunchService = jobLaunchService;
        this.interestRateService = interestRateService;
    }

    @GetMapping("/jobs/{process}")
    @ApplyTenantFilter
    public ResponseEntity<Object> getJobs(@PathVariable String process){

        List<Job> jobs = jobLaunchService.getJobs("year_end_process_job");

        List<JobDTO> jobDTOList = formatJobList(jobs, process);

        return Response.success(jobDTOList);
    }

    private List<JobDTO> formatJobList(List<Job> jobs, String process) {
        return jobs.stream().filter(job -> {

            JSONObject jobDetailsJson = job.getJobDetailsJson();

            if(jobDetailsJson.getString("ext").equalsIgnoreCase(YearEndProcess.PROCESS_FOR_LOAN) &&
            process.equalsIgnoreCase("loan")) {
                return true;
            }

            if(jobDetailsJson.getString("ext").equalsIgnoreCase(YearEndProcess.PROCESS_FOR_SETTLEMENT) &&
                    process.equalsIgnoreCase("settlement")) {
                return true;
            }

            return jobDetailsJson.getString("ext").equalsIgnoreCase(YearEndProcess.PROCESS_FOR_YEAR_END) &&
                    process.equalsIgnoreCase("year-end");

        }).map(job -> {

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

            if(jobDetailsJson.has("params")){

                jobDTO.setParams(jobDetailsJson.getString("params"));

                JSONObject params = new JSONObject(jobDetailsJson.getString("params"));

                jobDTO.setDryRun(params.getBoolean("isDryRun"));

            }

            JSONObject details = jobDetailsJson.getJSONObject("details");

            List<JobDetails> jobDetailsList = details.keySet().stream().map(key -> {

                JSONArray jsonArray = details.getJSONArray(key);

                if (jsonArray.length() == 2) {
                    JSONObject jsonObject = jsonArray.getJSONObject(1);

                    return new JobDetails(key, Instant.parse(jsonObject.getString("startedAt")),
                            jsonObject.has("completedAt") ? Instant.parse(jsonObject.getString("completedAt")) : null,
                            jsonObject.has("duration") ? jsonObject.getLong("duration") : 0,
                            jsonObject.getLong("success"),
                            jsonObject.getLong("failed"),
                            jsonObject.getString("status"),
                            jsonObject.getLong("total"));

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

            return jobDTO;

        }).collect(Collectors.toList());
    }

    @PostMapping("/loan")
    @ApplyTenantFilter
    public ResponseEntity<Object> performForLoan(@RequestBody YearEndProcessRequestDTO requestDTO) throws JPAException {

        try {
            interestRateService.getActive();
        }catch (Exception e){
            throw new EntityNotFoundException("no active interest rates found for current financial year.");
        }

        JSONObject params = new JSONObject(requestDTO.getParams());

        JSONArray unitcodes = params.getJSONArray("unitcodes");
        JSONArray pernNumbers = params.getJSONArray("pernNumbers");

        if(unitcodes.length() == 0 && pernNumbers.length() == 0){
            throw new JPAException("Please select a Unit Code or Employee Pern No.");
        }

        params.put("processFor", YearEndProcess.PROCESS_FOR_LOAN);

        if(unitcodes.length() > 0){
            params.put("processRunFor", YearEndProcess.PROCESS_RUN_FOR_UNIT_CODE);
        }else {
            params.put("processRunFor", YearEndProcess.PROCESS_RUN_FOR_EMPLOYEE);
        }

        Job job = jobLaunchService.launchJob("year_end_process_job", params.toString());

        return Response.success(job);

    }


    @PostMapping("/settlement")
    @ApplyTenantFilter
    public ResponseEntity<Object> performForSettlement(@RequestBody YearEndProcessRequestDTO requestDTO) throws JPAException {

        try {
            interestRateService.getActive();
        }catch (Exception e){
            throw new EntityNotFoundException("no active interest rates found for current financial year.");
        }

        JSONObject params = new JSONObject(requestDTO.getParams());

        JSONArray unitcodes = params.getJSONArray("unitcodes");
        JSONArray pernNumbers = params.getJSONArray("pernNumbers");

        if(unitcodes.length() == 0 && pernNumbers.length() == 0){
            throw new JPAException("Please select a unitcode or employee pern number");
        }

        params.put("processFor", YearEndProcess.PROCESS_FOR_SETTLEMENT);

        if(unitcodes.length() > 0){
            params.put("processRunFor", YearEndProcess.PROCESS_RUN_FOR_UNIT_CODE);
        }else {
            params.put("processRunFor", YearEndProcess.PROCESS_RUN_FOR_EMPLOYEE);
        }

        Job job = jobLaunchService.launchJob("year_end_process_job", params.toString());

        return Response.success(job);

    }

    @PostMapping("")
    @ApplyTenantFilter
    public ResponseEntity<Object> performForYearEndProcess(@RequestBody YearEndProcessRequestDTO requestDTO) {

        try {
            interestRateService.getActive();
        }catch (Exception e){
            throw new EntityNotFoundException("no active interest rates found for current financial year.");
        }

        JSONObject params = new JSONObject(requestDTO.getParams());

        params.put("processFor", YearEndProcess.PROCESS_FOR_YEAR_END);

        Job job = jobLaunchService.launchJob("year_end_process_job", params.toString());

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

        List<UnitCode> unitCodes = unitCodeService.get();

        InterestRate active = interestRateService.getActive();

        Map<String, Object> map = new HashMap<>();
        map.put("unitCodes", unitCodes);
        map.put("interestRate", active.getInterestRate());
        map.put("financialYear", FinancialYearAndMonth.getFinancialYear(new Date()));

        return Response.success(map);
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

        if(jobDetailsJson.has("params")){

            jobDTO.setParams(jobDetailsJson.getString("params"));

            JSONObject params = new JSONObject(jobDetailsJson.getString("params"));

            jobDTO.setDryRun(params.getBoolean("isDryRun"));

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
        Job job = jobLaunchService.launchReportJob("year_end_report_job", jobDTO.getParams());
        return Response.success(job);
    }

}
