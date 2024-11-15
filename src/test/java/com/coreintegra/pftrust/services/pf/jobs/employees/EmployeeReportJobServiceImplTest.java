package com.coreintegra.pftrust.services.pf.jobs.employees;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import net.minidev.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@SpringBootTest
class EmployeeReportJobServiceImplTest {

    @Autowired
    private CustomJobRepository customJobRepository;

    @Autowired
    private EmployeeReportJobService employeeReportJobService;

    @Test
    void generateEmployeeReport() throws IOException {
        Instant now = Instant.now();

        Job employeeReportJob = createJob("employees_report_job", now);

        JSONObject params = new JSONObject();
        params.put("unitCodes", new JSONArray());
        params.put("dateType", "Date of Joining PF");
        params.put("dates", new JSONArray());
        params.put("contributionStatusList", new JSONArray());

        employeeReportJobService.generateEmployeeReport(employeeReportJob, now, params.toString());
    }

    private Job createJob(String type, Instant startedAt){

        Job job = new Job();
        job.setEntityId(UUID.randomUUID().toString());
        job.setName(type);

        JSONObject details = new JSONObject();
        details.put("status", "started");
        details.put("startedAt", startedAt);

        job.setJobDetails(details.toString());

        return customJobRepository.save(job);
    }

}