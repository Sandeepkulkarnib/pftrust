package com.coreintegra.pftrust.entities.pf.loan.dtos;

import com.coreintegra.pftrust.entities.pf.employee.ContributionStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanStatus;
import com.coreintegra.pftrust.entities.pf.loan.LoanType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class LoanBasicDetails {

    private String entityId;

    private String pernNumber;
    private String pfNumber;
    private String tokenNumber;
    private String unitCode;

    private String name;
    private String status;
    private Date dateOfJoiningService;
    private Date dateOfJoiningPf;

    private String loanType;
    private BigDecimal appliedAmount;
    private BigDecimal totalCost;
    private BigDecimal eligibleAmount;

    private Date loanApplicationDate;
    private Date loanApprovalDate;
    private Date loanDisbursalDate;
    private Date loanApplicationReceivedDate;

    private String loanStatus;
    private Date paymentDate;
    private String altEmail;
    private String altContactNumber;

    private String loanCreatedBy;
    private String approvedBy;

    public LoanBasicDetails(String entityId, String pernNumber, String pfNumber, String tokenNumber, String unitCode,
                            String name, String status, Date dateOfJoiningService, Date dateOfJoiningPf,
                            String loanType, BigDecimal appliedAmount, BigDecimal totalCost, BigDecimal eligibleAmount,
                            Date loanApplicationDate, Date loanApprovalDate, Date loanDisbursalDate,
                            Date loanApplicationReceivedDate, String loanStatus, Date paymentDate, String altEmail,
                            String altContactNumber, String loanCreatedBy, String approvedBy) {
        this.entityId = entityId;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.tokenNumber = tokenNumber;
        this.unitCode = unitCode;
        this.name = name;
        this.status = status;
        this.dateOfJoiningService = dateOfJoiningService;
        this.dateOfJoiningPf = dateOfJoiningPf;
        this.loanType = loanType;
        this.appliedAmount = appliedAmount;
        this.totalCost = totalCost;
        this.eligibleAmount = eligibleAmount;
        this.loanApplicationDate = loanApplicationDate;
        this.loanApprovalDate = loanApprovalDate;
        this.loanDisbursalDate = loanDisbursalDate;
        this.loanApplicationReceivedDate = loanApplicationReceivedDate;
        this.loanStatus = loanStatus;
        this.paymentDate = paymentDate;
        this.altEmail = altEmail;
        this.altContactNumber = altContactNumber;
        this.loanCreatedBy = loanCreatedBy;
        this.approvedBy = approvedBy;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoiningService() {
        return dateOfJoiningService;
    }

    public void setDateOfJoiningService(Date dateOfJoiningService) {
        this.dateOfJoiningService = dateOfJoiningService;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoiningPf() {
        return dateOfJoiningPf;
    }

    public void setDateOfJoiningPf(Date dateOfJoiningPf) {
        this.dateOfJoiningPf = dateOfJoiningPf;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getEligibleAmount() {
        return eligibleAmount;
    }

    public void setEligibleAmount(BigDecimal eligibleAmount) {
        this.eligibleAmount = eligibleAmount;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getLoanApplicationDate() {
        return loanApplicationDate;
    }

    public void setLoanApplicationDate(Date loanApplicationDate) {
        this.loanApplicationDate = loanApplicationDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getLoanApprovalDate() {
        return loanApprovalDate;
    }

    public void setLoanApprovalDate(Date loanApprovalDate) {
        this.loanApprovalDate = loanApprovalDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getLoanDisbursalDate() {
        return loanDisbursalDate;
    }

    public void setLoanDisbursalDate(Date loanDisbursalDate) {
        this.loanDisbursalDate = loanDisbursalDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getLoanApplicationReceivedDate() {
        return loanApplicationReceivedDate;
    }

    public void setLoanApplicationReceivedDate(Date loanApplicationReceivedDate) {
        this.loanApplicationReceivedDate = loanApplicationReceivedDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getAltContactNumber() {
        return altContactNumber;
    }

    public void setAltContactNumber(String altContactNumber) {
        this.altContactNumber = altContactNumber;
    }

    public String getLoanCreatedBy() {
        return loanCreatedBy;
    }

    public void setLoanCreatedBy(String loanCreatedBy) {
        this.loanCreatedBy = loanCreatedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public static LoanBasicDetails build(Loan loan){

        Employee employee = loan.getEmployee();

        ContributionStatus contributionStatus = employee.getContributionStatus();

        LoanStatus loanStatus1 = loan.getLoanStatus();

        LoanType loanType1 = loan.getLoanType();

        return new LoanBasicDetails(loan.getEntityId(), employee.getPernNumber(), employee.getPfNumber(),
                employee.getTokenNumber(), employee.getUnitCode(), employee.getName(),
                contributionStatus.getDescription(), employee.getDateOfJoining(), employee.getDateOfJoiningPF(),
                loanType1.getTitle(), loan.getAppliedAmount(), loan.getTotalCost(), loan.getEligibleAmount(),
                loan.getLoanApplicationDate(), loan.getLoanApprovalDate(), loan.getLoanDisbursalDate(),
                loan.getLoanApplicationReceivedDate(), loanStatus1.getTitle(), loan.getPaymentDate(),
                loan.getAltEmail(), loan.getAltContactNumber(), loan.getLoanCreatedBy(), loan.getApprovedBy());
    }

}
