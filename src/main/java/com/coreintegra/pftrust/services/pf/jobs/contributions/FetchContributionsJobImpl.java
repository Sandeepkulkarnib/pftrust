package com.coreintegra.pftrust.services.pf.jobs.contributions;

import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.ContributionDTO;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.processors.ContributionProcessor;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.contribution.ContributionService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FetchContributionsJobImpl implements FetchContributionsJob {

    private final Logger logger = LoggerFactory.getLogger(FetchContributionsJob.class);

    private final String URL = "https://emss.mahindra.com/sap/bc/zzhr_pf_contri?UNIT-CODE=";

    private final RestTemplate restTemplate = new RestTemplate();

    private final EmployeeService employeeService;
    private final CustomJobRepository customJobRepository;
    private final ContributionService contributionService;
    private final EmployeeRepository employeeRepository;

    private final ContributionProcessor processor = new ContributionProcessor();

    public FetchContributionsJobImpl(EmployeeService employeeService, CustomJobRepository customJobRepository,
                                     ContributionService contributionService,
                                     EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.customJobRepository = customJobRepository;
        this.contributionService = contributionService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Job fetchForUnitCode(String unitCode, Integer year, Integer month, Job job) {

        Instant now = Instant.now();

        ResponseEntity<ContributionDTO[]> response = restTemplate.getForEntity(
                URL + unitCode + "&MONAT=" + month + "&YEAR=" + year, ContributionDTO[].class);

        ContributionDTO[] contributionDTOS = response.getBody();

        logger.info("Contribution fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(contributionDTOS == null || contributionDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, year, month, "completed", 0,
                    "no contributions found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, year, month, "in progress", contributionDTOS.length,
                "starting contribution processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode + "-" + year + "-" + month, details);

        logger.info("starting contribution processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(contributionDTOS).map(contributionDTO -> {

            String reason = "failed";

            try {

                Contribution contribution = processor.process(contributionDTO);

                contribution.setUnitCode(unitCode);

                return contributionService.saveAsync(contribution).join();

            } catch (Exception e) {
                reason = e.getLocalizedMessage();
                e.printStackTrace();
            }

            return reason;

        }).collect(Collectors.toList());

        logger.info("contribution processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful contribution insert -> {}", done.size());
        logger.info("Failed contribution insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, year, month, "completed", contributionDTOS.length,
                "completed contribution processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode + "-" + year + "-" + month, details);

        return job;


    }

    @Override
    public Job updateContributionWithEmployee(Job job) {

        Pageable pageable = PageRequest.of(0, 5000).first();

        boolean hasNext = true;

        int run = 1;

        while (hasNext) {

            Instant now = Instant.now();

            Page<Employee> page = employeeRepository.findAll(pageable);

            List<String> result = page.get().map(employee -> contributionService.updateContributionWithEmployee(employee).join())
                    .collect(Collectors.toList());

            hasNext = page.hasNext();

            pageable = page.nextPageable();

            logger.info("{} completed update for {} employees in {}", run, result.size(),
                    Duration.between(now, Instant.now()).getSeconds());

            run++;
        }

        return job;

    }

    private JSONObject getJobStatus(String unitCode, Integer year, Integer month,
                                    String status, Integer total, String message, Integer success,
                                    List<String> failed, Instant startedAt, Instant completedAt) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("unitCode", unitCode);
        jsonObject.put("year", year);
        jsonObject.put("month", month);
        jsonObject.put("status", status);
        jsonObject.put("total", total);
        jsonObject.put("message", message);
        jsonObject.put("success", success);
        jsonObject.put("failed", failed.size());

        if(failed.size() > 0){
            JSONArray failedLoans = new JSONArray();
            failed.forEach(failedLoans::put);
            jsonObject.put("failedLoans", failedLoans);
        }

        jsonObject.put("startedAt", startedAt);
        jsonObject.put("completedAt", completedAt);
        jsonObject.put("duration", Duration.between(startedAt, completedAt).getSeconds());

        return jsonObject;
    }


    private Job saveJobProgress(Job job, String unitCode, JSONArray details) {

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        if(jobDetailsJson.has("details")){
            jobDetailsJson.getJSONObject("details").put(unitCode, details);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(unitCode, details);

            jobDetailsJson.put("details", jsonObject);
        }

        job.setJobDetails(jobDetailsJson.toString());

        return customJobRepository.save(job);

    }

    @Override
    public Job fetchForUnitCodeAndPern(String unitCode,String pernNumber ,Integer year, Integer month, Job job) {

        Instant now = Instant.now();

        ResponseEntity<ContributionDTO[]> response = restTemplate.getForEntity(
                URL + unitCode + "&MONAT=" + month + "&YEAR=" + year, ContributionDTO[].class);

        ContributionDTO[] contributionDTOS = response.getBody();

        logger.info("Contribution fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(contributionDTOS == null || contributionDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, year, month, "completed", 0,
                    "no contributions found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, year, month, "in progress", contributionDTOS.length,
                "starting contribution processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode + "-" + year + "-" + month, details);

        logger.info("starting contribution processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(contributionDTOS).map(contributionDTO -> {

            String reason = "failed";

            try {

                Contribution contribution = processor.process(contributionDTO);

                contribution.setUnitCode(unitCode);
                
                if(contribution.getPernNumber().equalsIgnoreCase(pernNumber)) {

                	return contributionService.saveAsync(contribution).join();
                }
            } catch (Exception e) {
                reason = e.getLocalizedMessage();
                e.printStackTrace();
            }

            return reason;

        }).collect(Collectors.toList());

        logger.info("contribution processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful contribution insert -> {}", done.size());
        logger.info("Failed contribution insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, year, month, "completed", contributionDTOS.length,
                "completed contribution processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode + "-" + year + "-" + month, details);

        return job;


    }

}
