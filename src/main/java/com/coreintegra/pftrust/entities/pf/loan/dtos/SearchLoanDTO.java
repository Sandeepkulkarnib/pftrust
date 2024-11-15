package com.coreintegra.pftrust.entities.pf.loan.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SearchLoanDTO {

    private String entityId;
    private String pernNumber;
    private String pfNumber;
    private String name;
    private String unitCode;

    private Date applicationDate;
    private Date approvalDate;

    private Date paymentDate;
    private String reason;
    private String status;

    public SearchLoanDTO(String entityId, String pernNumber, String pfNumber, String name, String unitCode, Date applicationDate,
                         Date approvalDate, Date paymentDate, String reason, String status) {
        this.entityId = entityId;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.name = name;
        this.unitCode = unitCode;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
        this.paymentDate = paymentDate;
        this.reason = reason;
        this.status = status;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
