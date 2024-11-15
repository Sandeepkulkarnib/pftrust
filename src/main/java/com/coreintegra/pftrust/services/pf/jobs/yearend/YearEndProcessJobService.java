package com.coreintegra.pftrust.services.pf.jobs.yearend;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.io.IOException;
import java.util.Set;

public interface YearEndProcessJobService {

    Job performForEmployees(Set<String> pernNumbers, Boolean isDryRun, String processFor, Job job);

    Job performForUnitCode(Set<String> unitCodes, Boolean isDryRun, String processFor, Job job);

    Job perform(Boolean isDryRun, String processFor, Job job);

    String generateReport(String params) throws IOException;

}
