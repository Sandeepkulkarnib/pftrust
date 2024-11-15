package com.coreintegra.pftrust.entities.pf.transferOut.dtos;

import com.coreintegra.pftrust.util.NumberFormatter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TransferOutEmailDTO {

    private String entityId;
    private String fromPfAccount;
    private String toPfAccount;
    private String employeeName;
    private String unitCode;
    private Date paymentDate;
    private BigDecimal totalContribution;
    private String pfTrustName;
    private String currentEmployerName;
    private String emailId;

    public TransferOutEmailDTO() {
    }

    public TransferOutEmailDTO(String entityId, String fromPfAccount, String toPfAccount, String employeeName,
                               String unitCode, Date paymentDate, BigDecimal totalContribution, String pfTrustName,
                               String currentEmployerName, String emailId) {
        this.entityId = entityId;
        this.fromPfAccount = fromPfAccount;
        this.toPfAccount = toPfAccount;
        this.employeeName = employeeName;
        this.unitCode = unitCode;
        this.paymentDate = paymentDate;
        this.totalContribution = totalContribution;
        this.pfTrustName = pfTrustName;
        this.currentEmployerName = currentEmployerName;
        this.emailId = emailId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getFromPfAccount() {
        return fromPfAccount;
    }

    public void setFromPfAccount(String fromPfAccount) {
        this.fromPfAccount = fromPfAccount;
    }

    public String getToPfAccount() {
        return toPfAccount;
    }

    public void setToPfAccount(String toPfAccount) {
        this.toPfAccount = toPfAccount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTotalContribution() {
        return NumberFormatter.formatNumbers(totalContribution);
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    public String getPfTrustName() {
        return pfTrustName;
    }

    public void setPfTrustName(String pfTrustName) {
        this.pfTrustName = pfTrustName;
    }

    public String getCurrentEmployerName() {
        return currentEmployerName;
    }

    public void setCurrentEmployerName(String currentEmployerName) {
        this.currentEmployerName = currentEmployerName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
