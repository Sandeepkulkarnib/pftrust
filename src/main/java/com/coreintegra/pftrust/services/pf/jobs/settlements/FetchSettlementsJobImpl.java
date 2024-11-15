package com.coreintegra.pftrust.services.pf.jobs.settlements;

import com.coreintegra.pftrust.entities.pf.SettlementInterface;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementFinalDetails;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.FetchedSettlementDTO;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutFinalDetails;
import com.coreintegra.pftrust.processors.SettlementProcessor;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.settlement.SettlementService;
import com.coreintegra.pftrust.services.pf.transferout.TransferOutService;
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
public class FetchSettlementsJobImpl implements FetchSettlementsJob {

    private final Logger logger = LoggerFactory.getLogger(FetchSettlementsJob.class);

    private final String URL = "https://emss.mahindra.com/sap/bc/ZZHR_PF_SETTLE?UNIT_CODE=";

    private final RestTemplate restTemplate = new RestTemplate();

    private final SettlementProcessor processor = new SettlementProcessor();

    private final SettlementService settlementService;
    private final TransferOutService transferOutService;
    private final CustomJobRepository customJobRepository;

    public FetchSettlementsJobImpl(SettlementService settlementService,
                                   TransferOutService transferOutService,
                                   CustomJobRepository customJobRepository) {
        this.settlementService = settlementService;
        this.transferOutService = transferOutService;
        this.customJobRepository = customJobRepository;
    }

    @Override
    public Job fetchForUnitCode(String unitCode, Job job) {

        Instant now = Instant.now();

        ResponseEntity<FetchedSettlementDTO[]> response = restTemplate.getForEntity(URL + unitCode, FetchedSettlementDTO[].class);

        FetchedSettlementDTO[] settlementDTOS = response.getBody();

        logger.info("Settlement fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(settlementDTOS == null || settlementDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, "completed", 0, "no settlements found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", settlementDTOS.length,
                "starting settlement processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        logger.info("starting settlement processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(settlementDTOS).map(settlementDTO -> {

            try {

                SettlementInterface settlement = processor.process(settlementDTO);

                if(settlement instanceof Settlement){

                    Settlement settlement1 = (Settlement) settlement;

                    SettlementFinalDetails settlementFinalDetails = settlement1.getSettlementFinalDetails();
                    Employee employee = settlement1.getEmployee();

                    settlement1.setSettlementFinalDetails(null);
                    settlement1.setEmployee(null);

                    return settlementService.saveFetchedSettlement(settlement1, employee.getPernNumber(),
                            settlementFinalDetails).join();
                }

                if(settlement instanceof TransferOut){

                    TransferOut transferOut = (TransferOut) settlement;

                    TransferOutFinalDetails transferOutFinalDetails = transferOut.getTransferOutFinalDetails();
                    Employee employee = transferOut.getEmployee();

                    transferOut.setTransferOutFinalDetails(null);
                    transferOut.setEmployee(null);

                    return transferOutService.saveFetchedTransferOut(transferOut, employee.getPernNumber(),
                            transferOutFinalDetails).join();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());

        logger.info("settlement processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful settlement insert -> {}", done.size());
        logger.info("Failed settlement insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", settlementDTOS.length,
                "completed settlement processing and saving", done.size(), failed,
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
    public Job fetchForUnitCodeAndPern(String unitCode, String pernNumber, Job job) {

        Instant now = Instant.now();

        ResponseEntity<FetchedSettlementDTO[]> response = restTemplate.getForEntity(URL + unitCode, FetchedSettlementDTO[].class);

        FetchedSettlementDTO[] settlementDTOS = response.getBody();

        logger.info("Settlement fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(settlementDTOS == null || settlementDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, "completed", 0, "no settlements found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", settlementDTOS.length,
                "starting settlement processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        logger.info("starting settlement processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(settlementDTOS).map(settlementDTO -> {

            try {

                SettlementInterface settlement = processor.process(settlementDTO);

                if(settlement instanceof Settlement){

                    Settlement settlement1 = (Settlement) settlement;

                    SettlementFinalDetails settlementFinalDetails = settlement1.getSettlementFinalDetails();
                    Employee employee = settlement1.getEmployee();

                    settlement1.setSettlementFinalDetails(null);
                    settlement1.setEmployee(null);

                    if(employee.getPernNumber().equalsIgnoreCase(pernNumber)) {
                    
                    	return settlementService.saveFetchedSettlement(settlement1, employee.getPernNumber(),
                            settlementFinalDetails).join();

                    }
                }
                if(settlement instanceof TransferOut){

                    TransferOut transferOut = (TransferOut) settlement;

                    TransferOutFinalDetails transferOutFinalDetails = transferOut.getTransferOutFinalDetails();
                    Employee employee = transferOut.getEmployee();

                    transferOut.setTransferOutFinalDetails(null);
                    transferOut.setEmployee(null);

                    return transferOutService.saveFetchedTransferOut(transferOut, employee.getPernNumber(),
                            transferOutFinalDetails).join();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());

        logger.info("settlement processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful settlement insert -> {}", done.size());
        logger.info("Failed settlement insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", settlementDTOS.length,
                "completed settlement processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode, details);

        return job;

    }

}
