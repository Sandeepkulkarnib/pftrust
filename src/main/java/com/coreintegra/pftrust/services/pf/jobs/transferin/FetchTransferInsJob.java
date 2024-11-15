package com.coreintegra.pftrust.services.pf.jobs.transferin;

import com.coreintegra.pftrust.entities.pf.department.Job;

public interface FetchTransferInsJob {

    Job fetchForUnitCode(String unitCode, Job job);
    
    Job fetchForUnitCodeAndPern(String unitCode,String pernNumber, Job job);

}
