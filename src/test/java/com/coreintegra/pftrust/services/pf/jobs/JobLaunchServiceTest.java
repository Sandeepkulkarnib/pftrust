package com.coreintegra.pftrust.services.pf.jobs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobLaunchServiceTest {

    @Autowired
    private JobLaunchService jobLaunchService;

    @Test
    void launchReportJob() {

        jobLaunchService.launchReportJob("monthly_status_report_job",
                "{\"jobId\": \"9ee2919e-b220-47f2-9050-aaa8135a826a\"}");

    }
}