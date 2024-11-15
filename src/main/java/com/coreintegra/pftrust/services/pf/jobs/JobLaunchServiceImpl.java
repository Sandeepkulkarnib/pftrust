package com.coreintegra.pftrust.services.pf.jobs;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.jobs.customerservice.CustomerServieJobService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class JobLaunchServiceImpl implements JobLaunchService {

    private final String FETCH_EMPLOYEES_JOB = "fetch_employees_job";
    private final String FETCH_LOAN_JOB = "fetch_loan_job";
    private final String FETCH_TRANSFER_IN_JOB = "fetch_transfer_in_job";
    private final String FETCH_SETTLEMENT_JOB = "fetch_settlement_job";
    private final String FETCH_CONTRIBUTION_JOB = "fetch_contribution_job";
    private final String HIRE_NEW_EMPLOYEES_JOB = "hire_new_employees_job";

    private final String EMPLOYEES_REPORT_JOB = "employees_report_job";
    private final String CONTRIBUTION_REPORT_JOB = "contribution_report_job";
    private final String LOAN_REPORT_JOB = "loan_report_job";
    private final String TRANSFER_IN_REPORT_JOB = "transfer_in_report_job";
    private final String SETTLEMENT_REPORT_JOB = "settlement_report_job";
    private final String TRANSFER_OUT_REPORT_JOB = "transfer_out_report_job";

    private final String MONTHLY_STATUS_PROCESS_JOB = "monthly_status_process_job";
    private final String MONTHLY_STATUS_REPORT_JOB = "monthly_status_report_job";

    private final String YEAR_END_PROCESS_JOB = "year_end_process_job";
    private final String YEAR_END_REPORT_JOB = "year_end_report_job";

    private final String CORPUS_REPORT_JOB = "corpus_report_jop";

    private final String PROCESS_LOAN_APPLICATIONS_JOB = "process_loan_applications_job";
    private final String APPROVE_LOAN_APPLICATIONS_JOB = "approve_loan_applications_job";

    private final JobService jobService;
    private final CustomJobRepository customJobRepository;
    private final ReportJobService reportJobService;

    private final CustomerServieJobService  customerServieJobService;

    public JobLaunchServiceImpl(JobService jobService, CustomJobRepository customJobRepository,
                                ReportJobService reportJobService, CustomerServieJobService customerServieJobService) {
        this.jobService = jobService;
        this.customJobRepository = customJobRepository;
        this.reportJobService = reportJobService;
        this.customerServieJobService = customerServieJobService;
    }

    @Override
    public Job launchJob(String type, String params) {

        Instant now = Instant.now();

        switch (type) {
            case FETCH_EMPLOYEES_JOB:
                Job employeeJob = createJob(FETCH_EMPLOYEES_JOB, now);
                jobService.fetchEmployeesAsync(employeeJob, now, params);
                return employeeJob;
            case FETCH_CONTRIBUTION_JOB:
                Job contributionJob = createJob(FETCH_CONTRIBUTION_JOB, now);
                jobService.fetchContributionsAsync(contributionJob, now, params);
                return contributionJob;
            case FETCH_LOAN_JOB:
                Job loanJob = createJob(FETCH_LOAN_JOB, now);
                jobService.fetchLoansAsync(loanJob, now, params);
                return loanJob;
            case FETCH_TRANSFER_IN_JOB:
                Job transferInJob = createJob(FETCH_TRANSFER_IN_JOB, now);
                jobService.fetchTransferInsAsync(transferInJob, now, params);
                return transferInJob;
            case FETCH_SETTLEMENT_JOB:
                Job settlementJob = createJob(FETCH_SETTLEMENT_JOB, now);
                jobService.fetchSettlementsAsync(settlementJob, now, params);
                return settlementJob;
            case HIRE_NEW_EMPLOYEES_JOB:
                Job hireNewEmployeesJob = createJob(HIRE_NEW_EMPLOYEES_JOB, now);
                jobService.hireNewEmployeesAsync(hireNewEmployeesJob, now, params);
                return hireNewEmployeesJob;
            case MONTHLY_STATUS_PROCESS_JOB:
                Job monthlyStatusProcessJob = createJob(MONTHLY_STATUS_PROCESS_JOB, now);
                jobService.runMonthlyStatusProcess(monthlyStatusProcessJob, now, params);
                return monthlyStatusProcessJob;
            case YEAR_END_PROCESS_JOB:
                JSONObject jsonObject = new JSONObject(params);
                Job yearEndProcessJob = createJob(YEAR_END_PROCESS_JOB, now, jsonObject.getString("processFor"));
                jobService.runYearEndProcess(yearEndProcessJob, now, params);
                return yearEndProcessJob;
            case PROCESS_LOAN_APPLICATIONS_JOB:
                Job processLoanApplications = createJob(PROCESS_LOAN_APPLICATIONS_JOB, now);
                jobService.processLoanApplications(processLoanApplications, now, params);
                return processLoanApplications;
            case APPROVE_LOAN_APPLICATIONS_JOB:
                Job approveLoanApplicationsJob = createJob(APPROVE_LOAN_APPLICATIONS_JOB, now);
                jobService.approveLoanApplications(approveLoanApplicationsJob, now, params);
                return approveLoanApplicationsJob;
        }

        return null;
    }

    @Override
    public List<Job> getJobs(String type) {
        return jobService.getJobs(type);
    }

    @Override
    public List<Job> getJobs() {
        return jobService.getJobs();
    }

    @Override
    public Job getJob(String entityId) {
        return customJobRepository.findByEntityIdAndIsActive(entityId, true);
    }

    @Override
    public Job launchReportJob(String type, String params) {

        Instant now = Instant.now();

        switch (type) {
            case EMPLOYEES_REPORT_JOB:
                Job employeeReportJob = createJob(EMPLOYEES_REPORT_JOB, now);
                reportJobService.generateEmployeeReport(employeeReportJob, now, params);
                return employeeReportJob;
            case CONTRIBUTION_REPORT_JOB:
                Job contributionReportJob = createJob(CONTRIBUTION_REPORT_JOB, now);
                reportJobService.generateContributionReport(contributionReportJob, now, params);
                return contributionReportJob;
            case LOAN_REPORT_JOB:
                Job loanReportJob = createJob(LOAN_REPORT_JOB, now);
                reportJobService.generateLoanReport(loanReportJob, now, params);
                return loanReportJob;
            case TRANSFER_IN_REPORT_JOB:
                Job transferInReportJob = createJob(TRANSFER_IN_REPORT_JOB, now);
                reportJobService.generateTransferInReport(transferInReportJob, now, params);
                return transferInReportJob;
            case SETTLEMENT_REPORT_JOB:
                Job settlementReportJob = createJob(SETTLEMENT_REPORT_JOB, now);
                reportJobService.generateSettlementReport(settlementReportJob, now, params);
                return settlementReportJob;
            case TRANSFER_OUT_REPORT_JOB:
                Job transferOutReportJob = createJob(TRANSFER_OUT_REPORT_JOB, now);
                reportJobService.generateTransferOutReport(transferOutReportJob, now, params);
                return transferOutReportJob;
            case MONTHLY_STATUS_REPORT_JOB:
                Job monthlyStatusReportJob = createJob(MONTHLY_STATUS_REPORT_JOB, now);
                reportJobService.generateMonthlyStatusReport(monthlyStatusReportJob, now, params);
                return monthlyStatusReportJob;
            case YEAR_END_REPORT_JOB:
                Job yearEndReportJob = createJob(YEAR_END_REPORT_JOB, now);
                reportJobService.generateYearEndReport(yearEndReportJob, now, params);
                return yearEndReportJob;
            case CORPUS_REPORT_JOB:
                Job corpusReportJob = createJob(CORPUS_REPORT_JOB, now);
                // Add your custom job
                reportJobService.generateCorpusReport(corpusReportJob, now, params);
                return corpusReportJob;
        }

        return null;
    }

    private Job createJob(String type, Instant startedAt){

        Job job = new Job();
        job.setEntityId(UUID.randomUUID().toString());
        job.setName(type);

        JSONObject details = new JSONObject();
        details.put("status", "started");
        details.put("startedAt", startedAt);

        job.setJobDetails(details.toString());

        return customJobRepository.save(job);
    }

    private Job createJob(String type, Instant startedAt, String ext){

        Job job = new Job();
        job.setEntityId(UUID.randomUUID().toString());
        job.setName(type);

        JSONObject details = new JSONObject();
        details.put("status", "started");
        details.put("startedAt", startedAt);
        details.put("ext", ext);

        job.setJobDetails(details.toString());

        return customJobRepository.save(job);
    }
    
    @Override
    public Job launchJobByPern(String type, String unitCode, String pernNumber) {

        Instant now = Instant.now();
        
                Job employeeJob = createJob(FETCH_EMPLOYEES_JOB, now);
                jobService.fetchEmployeesByPernAsync(employeeJob,now,unitCode,pernNumber);
                return employeeJob;
            
    }
    
    @Override
    public Job launchJobByUnitCodeAndPern(String unitCode,String pernNumber,Integer year,Integer month) {
    	
    	Instant now = Instant.now();
    	Job contributionJob = createJob(FETCH_CONTRIBUTION_JOB, now);
    	jobService.fetchContributionsByPernAsync(contributionJob, now, unitCode, pernNumber, year, month);
    	
    	return contributionJob;
    }
    
    @Override
    public Job launchJobForLoan(String unitCode,String pernNumber) {
    	
    	Instant now = Instant.now();
    	Job loanJob = createJob(FETCH_LOAN_JOB, now);
    	jobService.fetchLoansByPernAsync(loanJob, now, unitCode, pernNumber);
    	
    	return loanJob;
    }
    
    @Override
    public Job launchJobForTransferIn(String unitCode,String pernNumber) {
    	
    	Instant now = Instant.now();
    	Job transferInJob = createJob(FETCH_TRANSFER_IN_JOB, now);
    	jobService.fetchTransferInsByPernAsync(transferInJob, now, unitCode, pernNumber);
    	
    	return transferInJob;
    }
    
    @Override
    public Job launchJobForSettlement(String unitCode,String pernNumber) {
    	
    	Instant now = Instant.now();
    	Job settlementJob = createJob(FETCH_SETTLEMENT_JOB, now);
    	jobService.fetchSettlementsByPernAsync(settlementJob, now, unitCode, pernNumber);
    	
    	return settlementJob;
    }
}
