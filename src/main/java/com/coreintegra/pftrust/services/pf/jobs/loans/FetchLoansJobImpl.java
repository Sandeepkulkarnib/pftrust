package com.coreintegra.pftrust.services.pf.jobs.loans;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanWithDrawalsFinalDetails;
import com.coreintegra.pftrust.entities.pf.loan.dtos.LoanDTO;
import com.coreintegra.pftrust.processors.LoanProcessor;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.loans.LoanService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FetchLoansJobImpl implements FetchLoansJob {

    private final Logger logger = LoggerFactory.getLogger(FetchLoansJob.class);

    private final String URL = "https://emss.mahindra.com/sap/bc/ZZHR_PF_LOAN?UNIT_CODE=";

    private final RestTemplate restTemplate = new RestTemplate();

    private final LoanProcessor processor = new LoanProcessor();

    private final LoanService loanService;
    private final CustomJobRepository customJobRepository;

    public FetchLoansJobImpl(LoanService loanService, CustomJobRepository customJobRepository) {
        this.loanService = loanService;
        this.customJobRepository = customJobRepository;
    }

    @Override
    public Job fetchForUnitCode(String unitCode, Job job) {

        Instant now = Instant.now();

        ResponseEntity<LoanDTO[]> response = restTemplate.getForEntity(URL + unitCode, LoanDTO[].class);

        LoanDTO[] loanDTOS = response.getBody();

        logger.info("Loan fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(loanDTOS == null || loanDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, "completed", 0, "no loans found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", loanDTOS.length,
                "starting loan processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        logger.info("starting loan processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(loanDTOS).map(loanDTO -> {

            try {

                Loan loan = processor.process(loanDTO);

                Employee employee = loan.getEmployee();
                LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

                loan.setEmployee(null);
                loan.setLoanWithDrawalsFinalDetails(null);

                return loanService.saveFetchedLoanAsync(loan, employee.getPernNumber(), loanWithDrawalsFinalDetails).join();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());

        logger.info("loan processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful loans insert -> {}", done.size());
        logger.info("Failed loans insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", loanDTOS.length,
                "completed loan processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode, details);

        return job;
    }


    private JSONObject getJobStatus(String unitCode, String status, Integer total, String message, Integer success,
                                    List<String> failed, Instant startedAt, Instant completedAt) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("unitCode", unitCode);
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
    public Job fetchForUnitCodeAndPern(String unitCode,String pernNumber, Job job) {

        Instant now = Instant.now();

        ResponseEntity<LoanDTO[]> response = restTemplate.getForEntity(URL + unitCode, LoanDTO[].class);

        LoanDTO[] loanDTOS = response.getBody();

        logger.info("Loan fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(loanDTOS == null || loanDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, "completed", 0, "no loans found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", loanDTOS.length,
                "starting loan processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        logger.info("starting loan processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(loanDTOS).map(loanDTO -> {

            try {

                Loan loan = processor.process(loanDTO);

                Employee employee = loan.getEmployee();
                LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

                loan.setEmployee(null);
                loan.setLoanWithDrawalsFinalDetails(null);
                
                if(employee.getPernNumber().equalsIgnoreCase(pernNumber)) {

                	return loanService.saveFetchedLoanAsync(loan, employee.getPernNumber(), loanWithDrawalsFinalDetails).join();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());

        logger.info("loan processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful loans insert -> {}", done.size());
        logger.info("Failed loans insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", loanDTOS.length,
                "completed loan processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode, details);

        return job;
    }

}
