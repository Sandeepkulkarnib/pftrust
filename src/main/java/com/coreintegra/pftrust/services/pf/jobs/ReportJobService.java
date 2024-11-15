package com.coreintegra.pftrust.services.pf.jobs;

import com.coreintegra.pftrust.entities.pf.department.Job;

import java.time.Instant;

public interface ReportJobService {

    void generateEmployeeReport(Job job, Instant startedAt, String params);

    void generateContributionReport(Job job, Instant startedAt, String params);

    void generateLoanReport(Job job, Instant startedAt, String params);

    void generateTransferInReport(Job job, Instant startedAt, String params);

    void generateSettlementReport(Job job, Instant startedAt, String params);

    void generateTransferOutReport(Job job, Instant startedAt, String params);

    void generateMonthlyStatusReport(Job job, Instant startedAt, String params);

    void generateYearEndReport(Job job, Instant startedAt, String params);

    void generateCorpusReport(Job job, Instant startedAt, String params);

}
