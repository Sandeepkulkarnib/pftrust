package com.coreintegra.pftrust.entities.pf.loan.dtos;

import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class LoanTypeDetails {

    private Date dateOfCompletionOfHouse;
    private Date dateOfFirstAlteration;
    private BigDecimal propertyEstimatedPrice;
    private BigDecimal stampDuty;

    private BigDecimal propertyRegistration;
    private BigDecimal insurance;
    private BigDecimal other;
    private BigDecimal total;

    private String financialInstName;
    private String repaymentBank;
    private String repaymentBankBranch;

    private String repaymentBankAccountNumber;
    private String repaymentBankIfscCode;
    private String repaymentBankMicrCode;

    private String employeeBank;
    private String employeeAccountNumber;
    private String employeeBankBranch;

    private String employeeBankIfscCode;
    private String employeeBankMicrCode;
    private String employeeBankSwiftCode;

    public LoanTypeDetails(Date dateOfCompletionOfHouse, Date dateOfFirstAlteration, BigDecimal propertyEstimatedPrice,
                           BigDecimal stampDuty, BigDecimal propertyRegistration, BigDecimal insurance,
                           BigDecimal other, BigDecimal total, String financialInstName, String repaymentBank,
                           String repaymentBankBranch, String repaymentBankAccountNumber, String repaymentBankIfscCode,
                           String repaymentBankMicrCode, String employeeBank, String employeeAccountNumber,
                           String employeeBankBranch, String employeeBankIfscCode, String employeeBankMicrCode,
                           String employeeBankSwiftCode) {
        this.dateOfCompletionOfHouse = dateOfCompletionOfHouse;
        this.dateOfFirstAlteration = dateOfFirstAlteration;
        this.propertyEstimatedPrice = propertyEstimatedPrice;
        this.stampDuty = stampDuty;
        this.propertyRegistration = propertyRegistration;
        this.insurance = insurance;
        this.other = other;
        this.total = total;
        this.financialInstName = financialInstName;
        this.repaymentBank = repaymentBank;
        this.repaymentBankBranch = repaymentBankBranch;
        this.repaymentBankAccountNumber = repaymentBankAccountNumber;
        this.repaymentBankIfscCode = repaymentBankIfscCode;
        this.repaymentBankMicrCode = repaymentBankMicrCode;
        this.employeeBank = employeeBank;
        this.employeeAccountNumber = employeeAccountNumber;
        this.employeeBankBranch = employeeBankBranch;
        this.employeeBankIfscCode = employeeBankIfscCode;
        this.employeeBankMicrCode = employeeBankMicrCode;
        this.employeeBankSwiftCode = employeeBankSwiftCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfCompletionOfHouse() {
        return dateOfCompletionOfHouse;
    }

    public void setDateOfCompletionOfHouse(Date dateOfCompletionOfHouse) {
        this.dateOfCompletionOfHouse = dateOfCompletionOfHouse;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfFirstAlteration() {
        return dateOfFirstAlteration;
    }

    public void setDateOfFirstAlteration(Date dateOfFirstAlteration) {
        this.dateOfFirstAlteration = dateOfFirstAlteration;
    }

    public BigDecimal getPropertyEstimatedPrice() {
        return propertyEstimatedPrice;
    }

    public void setPropertyEstimatedPrice(BigDecimal propertyEstimatedPrice) {
        this.propertyEstimatedPrice = propertyEstimatedPrice;
    }

    public BigDecimal getStampDuty() {
        return stampDuty;
    }

    public void setStampDuty(BigDecimal stampDuty) {
        this.stampDuty = stampDuty;
    }

    public BigDecimal getPropertyRegistration() {
        return propertyRegistration;
    }

    public void setPropertyRegistration(BigDecimal propertyRegistration) {
        this.propertyRegistration = propertyRegistration;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getFinancialInstName() {
        return financialInstName;
    }

    public void setFinancialInstName(String financialInstName) {
        this.financialInstName = financialInstName;
    }

    public String getRepaymentBank() {
        return repaymentBank;
    }

    public void setRepaymentBank(String repaymentBank) {
        this.repaymentBank = repaymentBank;
    }

    public String getRepaymentBankBranch() {
        return repaymentBankBranch;
    }

    public void setRepaymentBankBranch(String repaymentBankBranch) {
        this.repaymentBankBranch = repaymentBankBranch;
    }

    public String getRepaymentBankAccountNumber() {
        return repaymentBankAccountNumber;
    }

    public void setRepaymentBankAccountNumber(String repaymentBankAccountNumber) {
        this.repaymentBankAccountNumber = repaymentBankAccountNumber;
    }

    public String getRepaymentBankIfscCode() {
        return repaymentBankIfscCode;
    }

    public void setRepaymentBankIfscCode(String repaymentBankIfscCode) {
        this.repaymentBankIfscCode = repaymentBankIfscCode;
    }

    public String getRepaymentBankMicrCode() {
        return repaymentBankMicrCode;
    }

    public void setRepaymentBankMicrCode(String repaymentBankMicrCode) {
        this.repaymentBankMicrCode = repaymentBankMicrCode;
    }

    public String getEmployeeBank() {
        return employeeBank;
    }

    public void setEmployeeBank(String employeeBank) {
        this.employeeBank = employeeBank;
    }

    public String getEmployeeAccountNumber() {
        return employeeAccountNumber;
    }

    public void setEmployeeAccountNumber(String employeeAccountNumber) {
        this.employeeAccountNumber = employeeAccountNumber;
    }

    public String getEmployeeBankBranch() {
        return employeeBankBranch;
    }

    public void setEmployeeBankBranch(String employeeBankBranch) {
        this.employeeBankBranch = employeeBankBranch;
    }

    public String getEmployeeBankIfscCode() {
        return employeeBankIfscCode;
    }

    public void setEmployeeBankIfscCode(String employeeBankIfscCode) {
        this.employeeBankIfscCode = employeeBankIfscCode;
    }

    public String getEmployeeBankMicrCode() {
        return employeeBankMicrCode;
    }

    public void setEmployeeBankMicrCode(String employeeBankMicrCode) {
        this.employeeBankMicrCode = employeeBankMicrCode;
    }

    public String getEmployeeBankSwiftCode() {
        return employeeBankSwiftCode;
    }

    public void setEmployeeBankSwiftCode(String employeeBankSwiftCode) {
        this.employeeBankSwiftCode = employeeBankSwiftCode;
    }

    public static LoanTypeDetails build(Loan loan){

        return new LoanTypeDetails(loan.getDateOfCompletionOfHouse(), loan.getDateOfFirstAlteration(),
                loan.getPropertyEstimatedPrice(), loan.getStampDuty(), loan.getPropertyRegistration(),
                loan.getInsurance(), loan.getOther(), loan.getTotal(), loan.getFinancialInstName(),
                loan.getRepaymentBank(), loan.getRepaymentBankBranch(), loan.getRepaymentBankAccountNumber(),
                loan.getRepaymentBankIfscCode(), loan.getRepaymentBankMicrCode(), loan.getEmployeeBank(),
                loan.getEmployeeAccountNumber(), loan.getEmployeeBankBranch(), loan.getEmployeeBankIfscCode(),
                loan.getEmployeeBankMicrCode(), loan.getEmployeeBankSwiftCode());

    }



}
