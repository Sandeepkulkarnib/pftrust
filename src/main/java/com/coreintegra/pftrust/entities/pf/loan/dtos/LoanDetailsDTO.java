package com.coreintegra.pftrust.entities.pf.loan.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class LoanDetailsDTO {

    private String entityId;
    private String type;
    private Date applicationDate;
    private BigDecimal appliedAmount;
    private String status;
    private Date approvalDate;
    private BigDecimal eligibleAmount;
    private Date disbursalDate;
    private String documentNo;
    private Integer financialYear;
    private Integer financialMonth;

    public LoanDetailsDTO(String entityId, String type, Date applicationDate, BigDecimal appliedAmount,
                          String status, Date approvalDate, BigDecimal eligibleAmount, Date disbursalDate,
                          String documentNo, Integer financialYear, Integer financialMonth) {
        this.entityId = entityId;
        this.type = type;
        this.applicationDate = applicationDate;
        this.appliedAmount = appliedAmount;
        this.status = status;
        this.approvalDate = approvalDate;
        this.eligibleAmount = eligibleAmount;
        this.disbursalDate = disbursalDate;
        this.documentNo = documentNo;
        this.financialYear = financialYear;
        this.financialMonth = financialMonth;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public BigDecimal getEligibleAmount() {
        return eligibleAmount;
    }

    public void setEligibleAmount(BigDecimal eligibleAmount) {
        this.eligibleAmount = eligibleAmount;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDisbursalDate() {
        return disbursalDate;
    }

    public void setDisbursalDate(Date disbursalDate) {
        this.disbursalDate = disbursalDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Integer getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(Integer financialYear) {
        this.financialYear = financialYear;
    }

    public Integer getFinancialMonth() {
        return financialMonth;
    }

    public void setFinancialMonth(Integer financialMonth) {
        this.financialMonth = financialMonth;
    }
}
