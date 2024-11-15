package com.coreintegra.pftrust.entities.pf.transferOut.dtos;

import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentTransferInContribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentYearContribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentYearLoanWithdrawal;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.YearOpeningContribution;
import com.coreintegra.pftrust.entities.pf.employee.Nominee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransferOutDTO {


    private String settlementSNO;
    private Date dateOfCessation;
    private String documentNumber;

    private String employeeName;
    private String pernNumber;
    private String pfNumber;
    private String unitCode;
    private String status;

    private Date dateOfBirth;
    private Date dateOfJoiningPF;
    private Date dateOfJoiningPreviousEmployer;
    private Date lastRecoveryDate;
    private String totalCont;

    private List<Nominee> nominees;

    private YearOpeningContribution yearOpeningContribution;

    private CurrentYearContribution currentYearContribution;

    private CurrentTransferInContribution currentTransferInContribution;

    private CurrentYearLoanWithdrawal currentYearLoanWithdrawal;

    public String getSettlementSNO() {
        return settlementSNO;
    }

    public void setSettlementSNO(String settlementSNO) {
        this.settlementSNO = settlementSNO;
    }

    public Date getDateOfCessation() {
        return dateOfCessation;
    }

    public void setDateOfCessation(Date dateOfCessation) {
        this.dateOfCessation = dateOfCessation;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoiningPF() {
        return dateOfJoiningPF;
    }

    public void setDateOfJoiningPF(Date dateOfJoiningPF) {
        this.dateOfJoiningPF = dateOfJoiningPF;
    }

    public Date getDateOfJoiningPreviousEmployer() {
        return dateOfJoiningPreviousEmployer;
    }

    public void setDateOfJoiningPreviousEmployer(Date dateOfJoiningPreviousEmployer) {
        this.dateOfJoiningPreviousEmployer = dateOfJoiningPreviousEmployer;
    }

    public Date getLastRecoveryDate() {
        return lastRecoveryDate;
    }

    public void setLastRecoveryDate(Date lastRecoveryDate) {
        this.lastRecoveryDate = lastRecoveryDate;
    }

    public List<Nominee> getNominees() {
        return nominees;
    }

    public void setNominees(List<Nominee> nominees) {
        this.nominees = nominees;
    }

    public YearOpeningContribution getYearOpeningContribution() {
        return yearOpeningContribution;
    }

    public void setYearOpeningContribution(YearOpeningContribution yearOpeningContribution) {
        this.yearOpeningContribution = yearOpeningContribution;
    }

    public CurrentYearContribution getCurrentYearContribution() {
        return currentYearContribution;
    }

    public void setCurrentYearContribution(CurrentYearContribution currentYearContribution) {
        this.currentYearContribution = currentYearContribution;
    }

    public CurrentTransferInContribution getCurrentTransferInContribution() {
        return currentTransferInContribution;
    }

    public void setCurrentTransferInContribution(CurrentTransferInContribution currentTransferInContribution) {
        this.currentTransferInContribution = currentTransferInContribution;
    }

    public CurrentYearLoanWithdrawal getCurrentYearLoanWithdrawal() {
        return currentYearLoanWithdrawal;
    }

    public void setCurrentYearLoanWithdrawal(CurrentYearLoanWithdrawal currentYearLoanWithdrawal) {
        this.currentYearLoanWithdrawal = currentYearLoanWithdrawal;
    }

    public String getTotalCont() {
        return totalCont;
    }

    public void setTotalCont(String totalCont) {
        this.totalCont = totalCont;
    }

    public BigDecimal getTotalMemberContrbution(){
        return this.getYearOpeningContribution().getMemberContribution()
                .add(this.getYearOpeningContribution().getInterestOnMemberContribution())
                .add(this.getCurrentTransferInContribution().getMemberContribution())
                .add(this.getCurrentTransferInContribution().getInterestOnMemberContribution())
                .add(this.getCurrentYearContribution().getMemberContribution())
                .add(this.getCurrentYearContribution().getInterestOnMemberContribution())
                .subtract(this.currentYearLoanWithdrawal.getMember())
                .subtract(this.currentYearLoanWithdrawal.getInterestOnMember());
    }



    public BigDecimal getTotalCompanyContrbution(){
        return this.getYearOpeningContribution().getCompanyContribution()
                .add(this.getYearOpeningContribution().getInterestOnCompanyContribution())
                .add(this.getCurrentTransferInContribution().getCompanyContribution())
                .add(this.getCurrentTransferInContribution().getInterestOnCompanyContribution())
                .add(this.getCurrentYearContribution().getCompanyContribution())
                .add(this.getCurrentYearContribution().getInterestOnCompanyContribution())
                .subtract(this.currentYearLoanWithdrawal.getCompany())
                .subtract(this.currentYearLoanWithdrawal.getInterestOnCompany());
    }


    public BigDecimal getTotalVpfContrbution(){
        return this.getYearOpeningContribution().getVpfContribution()
                .add(this.getYearOpeningContribution().getInterestOnVpfContribution())
                .add(this.getCurrentTransferInContribution().getVpfContribution())
                .add(this.getCurrentTransferInContribution().getInterestOnVpfContribution())
                .add(this.getCurrentYearContribution().getVpfContribution())
                .add(this.getCurrentYearContribution().getInterestOnVpfContribution())
                .subtract(this.currentYearLoanWithdrawal.getVpf())
                .subtract(this.currentYearLoanWithdrawal.getInterestOnVpf());
    }


    public BigDecimal getTotal(){
        return this.getTotalMemberContrbution()
                .add(this.getTotalCompanyContrbution())
                .add(this.getTotalVpfContrbution());
    }

}
