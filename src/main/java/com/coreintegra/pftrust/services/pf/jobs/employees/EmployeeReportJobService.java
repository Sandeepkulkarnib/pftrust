package com.coreintegra.pftrust.services.pf.jobs.employees;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.io.IOException;
import java.time.Instant;

public interface EmployeeReportJobService {

    String generateEmployeeReport(Job job, Instant startedAt, String params) throws IOException;

}
