package com.coreintegra.pftrust.services.pf.jobs.settlements;

import com.coreintegra.pftrust.entities.pf.department.Job;

public interface FetchSettlementsJob {

    Job fetchForUnitCode(String unitCode, Job job);
    
    Job fetchForUnitCodeAndPern(String unitCode, String perNumber, Job job);

}
