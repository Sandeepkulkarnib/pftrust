package com.coreintegra.pftrust.services.pf.jobs.contributions;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.io.IOException;
import java.time.Instant;

public interface ContributionReportJobService {

    String generateContributionReport(Job job, Instant startedAt, String params) throws Exception;

}
