package com.coreintegra.pftrust.entities.pf.employee;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.UUID;

@Entity
public class EmployeeMonthlyStatusReport extends BaseAuditingEntity {

    @ManyToOne
    private Job job;

    @Column(columnDefinition = "LONGTEXT")
    private String report_row;

    private String pernNumber;
    private String unitCode;

    private Integer year;

    private Integer month;

    public EmployeeMonthlyStatusReport() {
    }

    public EmployeeMonthlyStatusReport(Job job, String report_row, String pernNumber, String unitCode, Integer year,
                                       Integer month) {
        this.job = job;
        this.report_row = report_row;
        this.pernNumber = pernNumber;
        this.unitCode = unitCode;
        this.year = year;
        this.month = month;
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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @JsonIgnore
    public JSONObject getReportRowJson(){
        return new JSONObject(report_row);
    }

}
