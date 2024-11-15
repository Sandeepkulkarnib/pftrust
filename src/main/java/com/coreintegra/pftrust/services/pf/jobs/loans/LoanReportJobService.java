package com.coreintegra.pftrust.services.pf.jobs.loans;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.io.IOException;
import java.time.Instant;

public interface LoanReportJobService {

    String generateLoanReport(Job job, Instant startedAt, String params) throws IOException;

}
