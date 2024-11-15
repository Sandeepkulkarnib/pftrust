package com.coreintegra.pftrust.services.pf.jobs;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.jobs.contributions.ContributionReportJobService;
import com.coreintegra.pftrust.services.pf.jobs.customerservice.CustomerServieJobService;
import com.coreintegra.pftrust.services.pf.jobs.employees.EmployeeReportJobService;
import com.coreintegra.pftrust.services.pf.jobs.employees.MonthlyStatusProcessJobService;
import com.coreintegra.pftrust.services.pf.jobs.loans.LoanReportJobService;
import com.coreintegra.pftrust.services.pf.jobs.settlements.SettlementReportService;
import com.coreintegra.pftrust.services.pf.jobs.transferin.TransferInReportService;
import com.coreintegra.pftrust.services.pf.jobs.transferout.TransferOutReportService;
import com.coreintegra.pftrust.services.pf.jobs.yearend.YearEndProcessJobService;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class ReportJobServiceImpl implements ReportJobService {

    private final EmployeeReportJobService employeeReportJobService;
    private final ContributionReportJobService contributionReportJobService;
    private final LoanReportJobService loanReportJobService;
    private final CustomJobRepository customJobRepository;
    private final TransferInReportService transferInReportService;
    private final SettlementReportService settlementReportService;
    private final TransferOutReportService transferOutReportService;
    private final MonthlyStatusProcessJobService monthlyStatusProcessJobService;
    private final YearEndProcessJobService yearEndProcessJobService;
    private final CustomerServieJobService customerServieJobService;


    public ReportJobServiceImpl(EmployeeReportJobService employeeReportJobService,
                                ContributionReportJobService contributionReportJobService,
                                LoanReportJobService loanReportJobService, CustomJobRepository customJobRepository,
                                TransferInReportService transferInReportService,
                                SettlementReportService settlementReportService,
                                TransferOutReportService transferOutReportService,
                                MonthlyStatusProcessJobService monthlyStatusProcessJobService,
                                YearEndProcessJobService yearEndProcessJobService, CustomerServieJobService customerServieJobService) {
        this.employeeReportJobService = employeeReportJobService;
        this.contributionReportJobService = contributionReportJobService;
        this.loanReportJobService = loanReportJobService;
        this.customJobRepository = customJobRepository;
        this.transferInReportService = transferInReportService;
        this.settlementReportService = settlementReportService;
        this.transferOutReportService = transferOutReportService;
        this.monthlyStatusProcessJobService = monthlyStatusProcessJobService;
        this.yearEndProcessJobService = yearEndProcessJobService;
        this.customerServieJobService = customerServieJobService;
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateEmployeeReport(Job job, Instant startedAt, String params) {

        try {
            String employeeReport = employeeReportJobService.generateEmployeeReport(job, startedAt, params);

            saveJobSuccess(job, startedAt, params, employeeReport);

        } catch (IOException e) {

            saveJobFailure(job, startedAt, params, e);
        }

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateContributionReport(Job job, Instant startedAt, String params) {

        try {
            String employeeReport = contributionReportJobService.generateContributionReport(job, startedAt, params);

            saveJobSuccess(job, startedAt, params, employeeReport);

        } catch (Exception e) {

            saveJobFailure(job, startedAt, params, e);
        }

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateLoanReport(Job job, Instant startedAt, String params) {
        try {
            String loanReport = loanReportJobService.generateLoanReport(job, startedAt, params);

            saveJobSuccess(job, startedAt, params, loanReport);

        } catch (IOException e) {

            saveJobFailure(job, startedAt, params, e);
        }
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateTransferInReport(Job job, Instant startedAt, String params) {
        try {
            String loanReport = transferInReportService.generateTransferInReport(job, startedAt, params);

            saveJobSuccess(job, startedAt, params, loanReport);

        } catch (IOException e) {

            saveJobFailure(job, startedAt, params, e);
        }
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateSettlementReport(Job job, Instant startedAt, String params) {
        try {
            String loanReport = settlementReportService.generateSettlementReport(job, startedAt, params);

            saveJobSuccess(job, startedAt, params, loanReport);

        } catch (IOException e) {

            saveJobFailure(job, startedAt, params, e);
        }
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateTransferOutReport(Job job, Instant startedAt, String params) {
        try {
            String loanReport = transferOutReportService.generateTransferOutReport(job, startedAt, params);

            saveJobSuccess(job, startedAt, params, loanReport);

        } catch (IOException e) {

            saveJobFailure(job, startedAt, params, e);
        }
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateMonthlyStatusReport(Job job, Instant startedAt, String params) {

        try {
            String report = monthlyStatusProcessJobService.generateEmployeeReport(params);

            saveJobSuccess(job, startedAt, params, report);

        } catch (IOException e) {

            saveJobFailure(job, startedAt, params, e);
        }

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateYearEndReport(Job job, Instant startedAt, String params) {

        try {
            String report = yearEndProcessJobService.generateReport(params);

            saveJobSuccess(job, startedAt, params, report);

        } catch (IOException e) {

            saveJobFailure(job, startedAt, params, e);
        }
    }

    private void saveJobFailure(Job job, Instant startedAt, String params, Exception e) {

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        jobDetailsJson.put("status", "failed");
        jobDetailsJson.put("message", e.getMessage());
        jobDetailsJson.put("stacktrace", Arrays.toString(e.getStackTrace()));
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job);
    }

    private void saveJobSuccess(Job job, Instant startedAt, String params, String employeeReport) {

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("fileName", employeeReport);
        jobDetailsJson.put("params", params);

        job.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job);
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void generateCorpusReport(Job job, Instant startedAt, String params) {

        try {
            String report = customerServieJobService.generateCorpusReport(params);

            saveJobSuccess(job, startedAt, params, report);

        } catch (IOException e) {

            saveJobFailure(job, startedAt, params, e);
        }
    }

}
