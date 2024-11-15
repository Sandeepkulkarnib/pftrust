package com.coreintegra.pftrust.services.pf.jobs;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.util.List;

public interface JobLaunchService {

    Job launchJob(String type, String params);

    List<Job> getJobs(String type);

    List<Job> getJobs();

    Job getJob(String entityId);

    Job launchReportJob(String type, String params);
    
    Job launchJobByPern(String type, String unitCode, String pernNumber);
    
    Job launchJobByUnitCodeAndPern(String unitCode,String pernNumber,Integer year,Integer month);
    
    Job launchJobForLoan(String unitCode,String pernNumber);
    
    Job launchJobForTransferIn(String unitCode,String pernNumber);

    Job launchJobForSettlement(String unitCode,String pernNumber);
}
