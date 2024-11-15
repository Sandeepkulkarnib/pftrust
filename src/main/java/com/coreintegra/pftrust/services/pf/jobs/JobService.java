package com.coreintegra.pftrust.services.pf.jobs;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;

import java.time.Instant;
import java.util.List;

public interface JobService {

    void fetchEmployeesAsync(Job job, Instant startedAt, String params);

    void fetchContributionsAsync(Job job, Instant startedAtr, String params);

    void fetchLoansAsync(Job job, Instant startedAt, String params);

    void fetchTransferInsAsync(Job job, Instant startedAt, String params);

    void fetchSettlementsAsync(Job job, Instant startedAt, String params);

    void hireNewEmployeesAsync(Job job, Instant startedAt, String params);

    void runMonthlyStatusProcess(Job job, Instant startedAt, String params);

    void runYearEndProcess(Job job, Instant startedAt, String params);

    List<Job> getJobs(String type);

    List<Job> getJobs();

    void processLoanApplications(Job job, Instant startedAt, String params);

    void approveLoanApplications(Job job, Instant statedAt, String params);
    
    void fetchEmployeesByPernAsync(Job job, Instant startedAt, String unitCode,String pernNumber);
    
    void fetchContributionsByPernAsync(Job job, Instant startedAtr, String unitCode,String pernNumber, Integer year, Integer month);
    
    void fetchLoansByPernAsync(Job job, Instant startedAt, String unitCode, String pernNumber);
    
    void fetchTransferInsByPernAsync(Job job, Instant startedAt, String unitCode, String pernNumber);
    
    void fetchSettlementsByPernAsync(Job job, Instant startedAt, String unitCode, String pernNumber);

}
