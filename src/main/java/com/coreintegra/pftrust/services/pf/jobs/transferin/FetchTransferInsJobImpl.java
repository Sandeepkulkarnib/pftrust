package com.coreintegra.pftrust.services.pf.jobs.transferin;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.TransferInDTO;
import com.coreintegra.pftrust.processors.TransferInProcessor;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.transferin.TransferInService;
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
public class FetchTransferInsJobImpl implements FetchTransferInsJob {

    private final Logger logger = LoggerFactory.getLogger(FetchTransferInsJob.class);

    private final String URL = "https://emss.mahindra.com/sap/bc/ZZHR_PF_TRANS?UNIT_CODE=";

    private final RestTemplate restTemplate = new RestTemplate();

    private final TransferInProcessor processor = new TransferInProcessor();

    private final TransferInService transferInService;
    private final CustomJobRepository customJobRepository;

    public FetchTransferInsJobImpl(TransferInService transferInService,
                                   CustomJobRepository customJobRepository) {
        this.transferInService = transferInService;
        this.customJobRepository = customJobRepository;
    }

    @Override
    public Job fetchForUnitCode(String unitCode, Job job) {

        Instant now = Instant.now();

        ResponseEntity<TransferInDTO[]> response = restTemplate.getForEntity(URL + unitCode, TransferInDTO[].class);

        TransferInDTO[] transferInDTOS = response.getBody();

        logger.info("TransferIn fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(transferInDTOS == null || transferInDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, "completed", 0, "no transferin found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", transferInDTOS.length,
                "starting transferin processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        logger.info("starting transferin processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(transferInDTOS).map(loanDTO -> {

            try {

                TransferIn transferIn = processor.process(loanDTO);

                Employee employee = transferIn.getEmployee();
                TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();
                PreviousCompany previousCompany = transferIn.getPreviousCompany();

                transferIn.setEmployee(null);
                transferIn.setTransferInFinalDetails(null);
                transferIn.setPreviousCompany(null);

                return transferInService.saveFetchedTransferInAsync(transferIn, employee.getPernNumber(),
                        transferInFinalDetails, previousCompany).join();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());

        logger.info("trasferin processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful transferin insert -> {}", done.size());
        logger.info("Failed transferin insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", transferInDTOS.length,
                "completed transferin processing and saving", done.size(), failed,
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
            jsonObject.put("failedTransferin", failedLoans);
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

        ResponseEntity<TransferInDTO[]> response = restTemplate.getForEntity(URL + unitCode, TransferInDTO[].class);

        TransferInDTO[] transferInDTOS = response.getBody();

        logger.info("TransferIn fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(transferInDTOS == null || transferInDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, "completed", 0, "no transferin found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", transferInDTOS.length,
                "starting transferin processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        logger.info("starting transferin processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(transferInDTOS).map(loanDTO -> {

            try {

                TransferIn transferIn = processor.process(loanDTO);

                Employee employee = transferIn.getEmployee();
                TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();
                PreviousCompany previousCompany = transferIn.getPreviousCompany();

                transferIn.setEmployee(null);
                transferIn.setTransferInFinalDetails(null);
                transferIn.setPreviousCompany(null);
                
                if(employee.getPernNumber().equalsIgnoreCase(pernNumber)) {

                	return transferInService.saveFetchedTransferInAsync(transferIn, employee.getPernNumber(),
                        transferInFinalDetails, previousCompany).join();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());

        logger.info("trasferin processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful transferin insert -> {}", done.size());
        logger.info("Failed transferin insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", transferInDTOS.length,
                "completed transferin processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode, details);

        return job;

    }

}
