package com.coreintegra.pftrust.services.pf.jobs.contributions;

import com.coreintegra.pftrust.entities.pf.department.Job;

public interface FetchContributionsJob {

    Job fetchForUnitCode(String unitCode, Integer year, Integer month, Job job);

    Job updateContributionWithEmployee(Job job);
    
    Job fetchForUnitCodeAndPern(String unitCode,String pernNumber ,Integer year, Integer month, Job job);

}
