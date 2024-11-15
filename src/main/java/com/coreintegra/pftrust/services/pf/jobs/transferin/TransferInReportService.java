package com.coreintegra.pftrust.services.pf.jobs.transferin;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.io.IOException;
import java.time.Instant;

public interface TransferInReportService {

    String generateTransferInReport(Job job, Instant startedAt, String params) throws IOException;

}
