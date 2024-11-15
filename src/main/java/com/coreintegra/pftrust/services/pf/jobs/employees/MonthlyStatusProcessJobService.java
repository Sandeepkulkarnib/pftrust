package com.coreintegra.pftrust.services.pf.jobs.employees;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.io.IOException;

public interface MonthlyStatusProcessJobService {

    Job performUnitCodeWise(String unitCode, Integer financialYear, Integer financialMonth,
                            Boolean isDryRun, Job job);

    String generateEmployeeReport(String params) throws IOException;

}
