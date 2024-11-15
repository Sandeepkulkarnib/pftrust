package com.coreintegra.pftrust.services.pf.jobs.transferout;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.io.IOException;
import java.time.Instant;

public interface TransferOutReportService {

    String generateTransferOutReport(Job job, Instant startedAt, String params) throws IOException;

}
