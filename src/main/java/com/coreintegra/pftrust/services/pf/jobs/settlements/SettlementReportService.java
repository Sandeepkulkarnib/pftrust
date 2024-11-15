package com.coreintegra.pftrust.services.pf.jobs.settlements;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.io.IOException;
import java.time.Instant;

public interface SettlementReportService {

    String generateSettlementReport(Job job, Instant startedAt, String params) throws IOException;

}
