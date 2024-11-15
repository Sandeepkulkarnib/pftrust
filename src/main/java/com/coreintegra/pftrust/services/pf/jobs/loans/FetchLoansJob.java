package com.coreintegra.pftrust.services.pf.jobs.loans;

import com.coreintegra.pftrust.entities.pf.department.Job;

public interface FetchLoansJob {

    Job fetchForUnitCode(String unitCode, Job job);
    
    Job fetchForUnitCodeAndPern(String unitCode,String pernNumber, Job job);

}
