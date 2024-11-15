package com.coreintegra.pftrust.entities.pf.yearend;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.Pair;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class YearEndReport extends BaseAuditingEntity {

    public static String[] taxableMemberContribution = {"April Tax Member Cont",
            "May Tax Member Cont", "June Tax Member Cont", "July Tax Member Cont",
            "August Tax Member Cont", "September Tax Member Cont", "October Tax Member Cont",
            "November Tax Member Cont", "December Tax Member Cont",
            "January Tax Member Cont", "February Tax Member Cont",
            "March Tax Member Cont"};

    public static String[] nonTaxableMemberContribution = {"April Non Tax Member Cont",
            "May Non Tax Member Cont", "June Non Tax Member Cont", "July Non Tax Member Cont",
            "August Non Tax Member Cont", "September Non Tax Member Cont", "October Non Tax Member Cont",
            "November Non Tax Member Cont", "December Non Tax Member Cont",
            "January Non Tax Member Cont", "February Non Tax Member Cont",
            "March Non Tax Member Cont"};

    public static String[] memberContribution = {"April Member Cont",
        "May Member Cont", "June Member Cont", "July Member Cont",
        "August Member Cont", "September Member Cont", "October Member Cont",
        "November Member Cont", "December Member Cont",
        "January Member Cont", "February Member Cont",
        "March Member Cont"};

    public static String[] companyContribution = {"April Company Cont",
            "May Company Cont", "June Company Cont", "July Company Cont",
            "August Company Cont", "September Company Cont", "October Company Cont",
            "November Company Cont", "December Company Cont",
            "January Company Cont", "February Company Cont",
            "March Company Cont"};

    public static String[] taxableEpfContribution = {"April Tax EPF Cont",
            "May Tax EPF Cont", "June Tax EPF Cont", "July Tax EPF Cont",
            "August Tax EPF Cont", "September Tax EPF Cont", "October Tax EPF Cont",
            "November Tax EPF Cont", "December Tax EPF Cont",
            "January Tax EPF Cont", "February Tax EPF Cont",
            "March Tax EPF Cont"};

    public static String[] nonTaxableEpfContribution = {"April Non Tax EPF Cont",
            "May Non Tax EPF Cont", "June Non Tax EPF Cont", "July Non Tax EPF Cont",
            "August Non Tax EPF Cont", "September Non Tax EPF Cont", "October Non Tax EPF Cont",
            "November Non Tax EPF Cont", "December Non Tax EPF Cont",
            "January Non Tax EPF Cont", "February Non Tax EPF Cont",
            "March Non Tax EPF Cont"};

    public static String[] epfContribution = {"April EPF Cont",
            "May EPF Cont", "June EPF Cont", "July EPF Cont",
            "August EPF Cont", "September EPF Cont", "October EPF Cont",
            "November EPF Cont", "December EPF Cont",
            "January EPF Cont", "February EPF Cont",
            "March EPF Cont"};

    public static String[] taxableMemberInterest = {"April Tax Member Interest",
            "May Tax Member Interest", "June Tax Member Interest", "July Tax Member Interest",
            "August Tax Member Interest", "September Tax Member Interest", "October Tax Member Interest",
            "November Tax Member Interest", "December Tax Member Interest",
            "January Tax Member Interest", "February Tax Member Interest",
            "March Tax Member Interest"};

    public static String[] nonTaxableMemberInterest = {"April Non Tax Member Interest",
            "May Non Tax Member Interest", "June Non Tax Member Interest", "July Non Tax Member Interest",
            "August Non Tax Member Interest", "September Non Tax Member Interest", "October Non Tax Member Interest",
            "November Non Tax Member Interest", "December Non Tax Member Interest",
            "January Non Tax Member Interest", "February Non Tax Member Interest",
            "March Non Tax Member Interest"};

    public static String[] memberInterest = {"April Member Interest",
            "May Member Interest", "June Member Interest", "July Member Interest",
            "August Member Interest", "September Member Interest", "October Member Interest",
            "November Member Interest", "December Member Interest",
            "January Member Interest", "February Member Interest",
            "March Member Interest"};

    public static String[] companyInterest = {"April Company Interest",
            "May Company Interest", "June Company Interest", "July Company Interest",
            "August Company Interest", "September Company Interest", "October Company Interest",
            "November Company Interest", "December Company Interest",
            "January Company Interest", "February Company Interest",
            "March Company Interest"};

    public static String[] taxableEpfInterest = {"April Tax EPF Interest",
            "May Tax EPF Interest", "June Tax EPF Interest", "July Tax EPF Interest",
            "August Tax EPF Interest", "September Tax EPF Interest", "October Tax EPF Interest",
            "November Tax EPF Interest", "December Tax EPF Interest",
            "January Tax EPF Interest", "February Tax EPF Interest",
            "March Tax EPF Interest"};

    public static String[] nonTaxableEpfInterest = {"April Non Tax EPF Interest",
            "May Non Tax EPF Interest", "June Non Tax EPF Interest", "July Non Tax EPF Interest",
            "August Non Tax EPF Interest", "September Non Tax EPF Interest", "October Non Tax EPF Interest",
            "November Non Tax EPF Interest", "December Non Tax EPF Interest",
            "January Non Tax EPF Interest", "February Non Tax EPF Interest",
            "March Non Tax EPF Interest"};

    public static String[] epfInterest = {"April EPF Interest",
            "May EPF Interest", "June EPF Interest", "July EPF Interest",
            "August EPF Interest", "September EPF Interest", "October EPF Interest",
            "November EPF Interest", "December EPF Interest",
            "January EPF Interest", "February EPF Interest",
            "March EPF Interest"};

    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;

    @Column(columnDefinition = "LONGTEXT")
    private String report_row;

    private String pernNumber;
    private String unitCode;

    private Integer year;

    private Boolean isPublished;

    public YearEndReport() {
    }

    public YearEndReport(Job job, String report_row,
                         String pernNumber, String unitCode,
                         Integer year,
                         Boolean isPublished) {
        this.job = job;
        this.report_row = report_row;
        this.pernNumber = pernNumber;
        this.unitCode = unitCode;
        this.year = year;
        this.isPublished = isPublished;

    }

    @PrePersist
    public void prePersist(){
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getReport_row() {
        return report_row;
    }

    @JsonIgnore
    public JSONObject getReportRowJson(){
        return new JSONObject(report_row);
    }

    public void setReport_row(String report_row) {
        this.report_row = report_row;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public static void addBasicDetails(Date lastRecoveryDate, Employee employee, JSONObject row) {
        // basic details
        row.put("Personnel number", employee.getPernNumber());
        row.put("PF Number", employee.getPfNumber());
        row.put("Unit Code", employee.getUnitCode());
        row.put("Token Number", employee.getTokenNumber());
        row.put("Name", employee.getName());
        row.put("DATE OF JOINING PF TRUST", DateFormatterUtil.format(employee.getDateOfJoiningPF(), "dd-MM-yyyy"));
        row.put("DATE OF BIRTH", DateFormatterUtil.format(employee.getDateOfBirth(), "dd-MM-yyyy"));
        row.put("LAST RECOVERY DATE", DateFormatterUtil.format(lastRecoveryDate, "dd-MM-yyyy"));
        row.put("AGE", employee.getEmployeeAge());
        row.put("STATUS AS PER STATUS REPORT", employee.getContributionStatus().getSymbol());
    }

    public static void addOpeningBalanceDetails(JSONObject row, Contribution contribution) {

        row.put("Mem Open Bal Non Taxable", contribution.getNonTaxableMemberContribution());
        row.put("Mem Open Bal Taxable", contribution.getTaxableMemberContribution());

        row.put("Com open Bal", contribution.getCompanyContribution());

        row.put("EPF Open Bal Non Taxable", contribution.getNonTaxableVpfContribution());
        row.put("EPF Open Bal Taxable", contribution.getTaxableVpfContribution());

        row.put("Mem Open INT Non Taxable", contribution.getInterestNonTaxableMemberContribution());
        row.put("Mem Open INT Taxable", contribution.getInterestTaxableMemberContribution());

        row.put("Com Open INT", contribution.getInterestCompanyContribution());

        row.put("EPF Open INT Non Taxable", contribution.getInterestNonTaxableVpfContribution());
        row.put("EPF Open INT Taxable", contribution.getInterestTaxableVpfContribution());

    }

    public static void addContribution(JSONObject row, Contribution contribution) {

        row.put(nonTaxableMemberContribution[contribution.getSubType() - 1], contribution.getNonTaxableMemberContribution());
        row.put(taxableMemberContribution[contribution.getSubType() - 1], contribution.getTaxableMemberContribution());
        row.put(memberContribution[contribution.getSubType() - 1], contribution.getMemberContribution());

        row.put(companyContribution[contribution.getSubType() - 1], contribution.getCompanyContribution());

        row.put(nonTaxableEpfContribution[contribution.getSubType() - 1], contribution.getNonTaxableVpfContribution());
        row.put(taxableEpfContribution[contribution.getSubType() - 1], contribution.getTaxableVpfContribution());
        row.put(epfContribution[contribution.getSubType() - 1], contribution.getVpfContribution());


        row.put(nonTaxableMemberInterest[contribution.getSubType() - 1], contribution.getInterestNonTaxableMemberContribution());
        row.put(taxableMemberInterest[contribution.getSubType() - 1], contribution.getInterestTaxableMemberContribution());
        row.put(memberInterest[contribution.getSubType() - 1], contribution.getInterestMemContribution());

        row.put(companyInterest[contribution.getSubType() - 1], contribution.getInterestCompanyContribution());

        row.put(nonTaxableEpfInterest[contribution.getSubType() - 1], contribution.getInterestNonTaxableVpfContribution());
        row.put(taxableEpfInterest[contribution.getSubType() - 1], contribution.getInterestTaxableVpfContribution());
        row.put(epfInterest[contribution.getSubType() - 1], contribution.getVpfContributionInterest());

    }

    public static void addLoanDetails(JSONObject yearEndReportRow,
                                                BigDecimal nonTaxableMemberLoan,
                                                BigDecimal taxableMemberLoan,
                                                BigDecimal companyLoan,
                                                BigDecimal nonTaxableEpfLoan,
                                                BigDecimal taxableEpfLoan,
                                                BigDecimal nonTaxableMemberLoanInterest,
                                                BigDecimal taxableMemberLoanInterest,
                                                BigDecimal companyLoanInterest,
                                                BigDecimal nonTaxableEpfLoanInterest,
                                                BigDecimal taxableEpfLoanInterest
    ) {

        yearEndReportRow.put("Member Loan Non Taxable", nonTaxableMemberLoan);
        yearEndReportRow.put("Member Loan Taxable", taxableMemberLoan);

        yearEndReportRow.put("Company Loan", companyLoan);

        yearEndReportRow.put("EPF Loan Non Taxable", nonTaxableEpfLoan);
        yearEndReportRow.put("EPF Loan Taxable", taxableEpfLoan);

        yearEndReportRow.put("Member Loan Interest Non Taxable", nonTaxableMemberLoanInterest);
        yearEndReportRow.put("Member Loan Interest Taxable", taxableMemberLoanInterest);

        yearEndReportRow.put("Company Loan Interest", companyLoanInterest);

        yearEndReportRow.put("EPF Loan Interest Non Taxable", nonTaxableEpfLoanInterest);
        yearEndReportRow.put("EPF Loan Interest Taxable", taxableEpfLoanInterest);

    }


    public static void addTransferInDetails(JSONObject yearEndReportRow,
                                            BigDecimal nonTaxableMemberTransIn,
                                            BigDecimal taxableMemberTransIn,
                                      BigDecimal companyTransIn,
                                      BigDecimal nonTaxableMemberTransInInterest,
                                      BigDecimal taxableMemberTransInInterest,
                                      BigDecimal companyTransInInterest
    ) {

        yearEndReportRow.put("Member Trans In Non Taxable", nonTaxableMemberTransIn);
        yearEndReportRow.put("Member Trans In Taxable", taxableMemberTransIn);

        yearEndReportRow.put("Company Trans In", companyTransIn);

        yearEndReportRow.put("Member Trans In Interest Non Taxable", nonTaxableMemberTransInInterest);
        yearEndReportRow.put("Member Trans In Interest Taxable", taxableMemberTransInInterest);

        yearEndReportRow.put("Company Trans In Interest", companyTransInInterest);

    }


}
