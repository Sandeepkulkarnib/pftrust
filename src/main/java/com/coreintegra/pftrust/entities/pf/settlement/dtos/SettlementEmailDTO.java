package com.coreintegra.pftrust.entities.pf.settlement.dtos;

import com.coreintegra.pftrust.util.NumberFormatter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SettlementEmailDTO {

    private String entityId;
    private String emailId;
    private String name;
    private String unitCode;
    private String pfNumber;
    private String pernNumber;
    private String accountNumber;
    private String employeeBank;
    private Date paymentDate;
    private BigDecimal netCredit;

    public SettlementEmailDTO(String entityId, String emailId, String name, String unitCode,
                              String pfNumber, String pernNumber, String accountNumber,
                              String employeeBank, Date paymentDate, BigDecimal netCredit) {
        this.entityId = entityId;
        this.emailId = emailId;
        this.name = name;
        this.unitCode = unitCode;
        this.pfNumber = pfNumber;
        this.pernNumber = pernNumber;
        this.accountNumber = accountNumber;
        this.employeeBank = employeeBank;
        this.paymentDate = paymentDate;
        this.netCredit = netCredit;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmployeeBank() {
        return employeeBank;
    }

    public void setEmployeeBank(String employeeBank) {
        this.employeeBank = employeeBank;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getNetCredit() {
        return NumberFormatter.formatNumbers(this.netCredit);
    }

    public void setNetCredit(BigDecimal netCredit) {
        this.netCredit = netCredit;
    }
}
