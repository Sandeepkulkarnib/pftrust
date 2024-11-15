package com.coreintegra.pftrust.controllers.pf.job;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDTO;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDetails;
import com.coreintegra.pftrust.entities.pf.department.dtos.LaunchJobDTO;
import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import com.coreintegra.pftrust.util.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.coreintegra.pftrust.controllers.pf.job.JobServiceEndpoints.*;

@RestController
@RequestMapping(JOB_SERVICE_ENDPOINT)
public class JobController {

    private final JobLaunchService jobLaunchService;

    public JobController(JobLaunchService jobLaunchService) {
        this.jobLaunchService = jobLaunchService;
    }

    @PostMapping()
    @ApplyTenantFilter
    public ResponseEntity<Object> launch(@RequestBody LaunchJobDTO jobDTO){

        Job job = jobLaunchService.launchJob(jobDTO.getType(), jobDTO.getParams());

        return Response.success(job);
    }

    @GetMapping()
    @ApplyTenantFilter
    public ResponseEntity<Object> getJobs(@RequestParam("type")String type){
        List<Job> jobs = jobLaunchService.getJobs(type);

        List<JobDTO> jobDTOList = jobs.stream().map(job -> {

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

        return Response.success(jobDTOList);
    }


    @GetMapping("/details")
    @ApplyTenantFilter
    public ResponseEntity<Object> getJob(@RequestParam("entityId")String entityId){

        Job job = jobLaunchService.getJob(entityId);

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        JobDTO jobDTO = new JobDTO(job.getEntityId(), job.getCreatedAt(), job.getUpdatedAt(),
                Instant.parse(jobDetailsJson.getString("startedAt")),
                jobDetailsJson.has("completedAt") ? Instant.parse(jobDetailsJson.getString("completedAt")) : null,
                job.getName(), jobDetailsJson.getString("status"),
                jobDetailsJson.has("duration") ? jobDetailsJson.getLong("duration") : 0);

        if(!jobDetailsJson.has("details")){
            jobDTO.setJobDetailsList(new ArrayList<>());
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

        return Response.success(jobDTO);
    }

    @PostMapping("/employee")
    @ApplyTenantFilter
    public ResponseEntity<Object> launchByPern(@RequestParam String unitCode,@RequestParam String pernNumber ){

        Job job = jobLaunchService.launchJobByPern("FETCH_EMPLOYEES_JOB", unitCode, pernNumber);

        return Response.success(job);
    }
    
    @PostMapping("/contribution")
    @ApplyTenantFilter
    public ResponseEntity<Object> launchByUnitCodeAndPern(@RequestParam String unitCode,@RequestParam String pernNumber,@RequestParam String year,@RequestParam String month){

        Job job = jobLaunchService.launchJobByUnitCodeAndPern(unitCode,pernNumber,Integer.parseInt(year),Integer.parseInt(month));

        return Response.success(job);
    }
    
    @PostMapping("/loans")
    @ApplyTenantFilter
    public ResponseEntity<Object> launchForLoanByUnitCodeAndPern(@RequestParam String unitCode,@RequestParam String pernNumber){

        Job job = jobLaunchService.launchJobForLoan(unitCode,pernNumber);

        return Response.success(job);
    }
    
    @PostMapping("/transfer-in")
    @ApplyTenantFilter
    public ResponseEntity<Object> launchForTransferInByUnitCodeAndPern(@RequestParam String unitCode,@RequestParam String pernNumber){

        Job job = jobLaunchService.launchJobForTransferIn(unitCode,pernNumber);

        return Response.success(job);
    }
    
    @PostMapping("/settlements")
    @ApplyTenantFilter
    public ResponseEntity<Object> launchForSettlementsByUnitCodeAndPern(@RequestParam String unitCode,@RequestParam String pernNumber){

        Job job = jobLaunchService.launchJobForSettlement(unitCode,pernNumber);

        return Response.success(job);
    }
    
}
