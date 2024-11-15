package com.coreintegra.pftrust.services.pf.jobs.yearend;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndReport;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.YearEndReportRepository;
import com.coreintegra.pftrust.services.pf.yearend.YearEndProcessService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class YearEndProcessJobServiceImplTest {

    @Autowired
    private CustomJobRepository customJobRepository;

    @Autowired
    private YearEndProcessJobService yearEndProcessJobService;

    @Autowired
    private YearEndReportRepository yearEndReportRepository;

    @Test
    void generateEmployeeReport() throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jobId", "465d010e-43d8-4412-bc23-a61cdc643b0d");

        String report = yearEndProcessJobService.generateReport(jsonObject.toString());

    }
}