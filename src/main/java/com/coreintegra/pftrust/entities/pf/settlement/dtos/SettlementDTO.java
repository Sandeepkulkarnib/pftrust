package com.coreintegra.pftrust.entities.pf.settlement.dtos;

import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentTransferInContribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentYearContribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentYearLoanWithdrawal;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.YearOpeningContribution;
import com.coreintegra.pftrust.entities.pf.employee.Nominee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SettlementDTO {

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



    public BigDecimal getTotalMemberContrbution(){
        return (this.getYearOpeningContribution().getMemberContribution() != null ?
                this.getYearOpeningContribution().getMemberContribution() : new BigDecimal(0))
                .add(this.getYearOpeningContribution().getInterestOnMemberContribution() != null ?
                        this.getYearOpeningContribution().getInterestOnMemberContribution() : new BigDecimal(0))
                .add(this.getCurrentTransferInContribution().getMemberContribution() != null ?
                        this.getCurrentTransferInContribution().getMemberContribution() : new BigDecimal(0))
                .add(this.getCurrentTransferInContribution().getInterestOnMemberContribution() != null ?
                        this.getCurrentTransferInContribution().getInterestOnMemberContribution() : new BigDecimal(0))
                .add(this.getCurrentYearContribution().getMemberContribution() != null ?
                        this.getCurrentYearContribution().getMemberContribution() : new BigDecimal(0))
                .add(this.getCurrentYearContribution().getInterestOnMemberContribution() != null ?
                        this.getCurrentYearContribution().getInterestOnMemberContribution() : new BigDecimal(0))
                .subtract(this.currentYearLoanWithdrawal.getMember() != null ?
                        this.currentYearLoanWithdrawal.getMember() : new BigDecimal(0))
                .subtract(this.currentYearLoanWithdrawal.getInterestOnMember() != null ?
                        this.currentYearLoanWithdrawal.getInterestOnMember() : new BigDecimal(0));
    }


    public BigDecimal getTotalCompanyContrbution(){
        return (this.getYearOpeningContribution().getCompanyContribution() != null ?
                this.getYearOpeningContribution().getCompanyContribution() : new BigDecimal(0))
                .add(this.getYearOpeningContribution().getInterestOnCompanyContribution() != null ?
                        this.getYearOpeningContribution().getInterestOnCompanyContribution() : new BigDecimal(0))
                .add(this.getCurrentTransferInContribution().getCompanyContribution() != null ?
                        this.getCurrentTransferInContribution().getCompanyContribution() : new BigDecimal(0))
                .add(this.getCurrentTransferInContribution().getInterestOnCompanyContribution() != null ?
                        this.getCurrentTransferInContribution().getInterestOnCompanyContribution() : new BigDecimal(0))
                .add(this.getCurrentYearContribution().getCompanyContribution() != null ?
                        this.getCurrentYearContribution().getCompanyContribution() : new BigDecimal(0))
                .add(this.getCurrentYearContribution().getInterestOnCompanyContribution() != null ?
                        this.getCurrentYearContribution().getInterestOnCompanyContribution() : new BigDecimal(0))
                .subtract(this.currentYearLoanWithdrawal.getCompany() != null ?
                        this.currentYearLoanWithdrawal.getCompany() : new BigDecimal(0))
                .subtract(this.currentYearLoanWithdrawal.getInterestOnCompany() != null ?
                        this.currentYearLoanWithdrawal.getInterestOnCompany() : new BigDecimal(0));
    }


    public BigDecimal getTotalVpfContrbution(){
        return (this.getYearOpeningContribution().getVpfContribution() != null ?
                this.getYearOpeningContribution().getVpfContribution() : new BigDecimal(0))
                .add(this.getYearOpeningContribution().getInterestOnVpfContribution() != null ?
                        this.getYearOpeningContribution().getInterestOnVpfContribution() : new BigDecimal(0))
                .add(this.getCurrentTransferInContribution().getVpfContribution() != null ?
                        this.getCurrentTransferInContribution().getVpfContribution() : new BigDecimal(0))
                .add(this.getCurrentTransferInContribution().getInterestOnVpfContribution() != null ?
                        this.getCurrentTransferInContribution().getInterestOnVpfContribution() : new BigDecimal(0))
                .add(this.getCurrentYearContribution().getVpfContribution() != null ?
                        this.getCurrentYearContribution().getVpfContribution() : new BigDecimal(0))
                .add(this.getCurrentYearContribution().getInterestOnVpfContribution() != null ?
                        this.getCurrentYearContribution().getInterestOnVpfContribution() : new BigDecimal(0))
                .subtract(this.currentYearLoanWithdrawal.getVpf() != null ?
                        this.currentYearLoanWithdrawal.getVpf() : new BigDecimal(0))
                .subtract(this.currentYearLoanWithdrawal.getInterestOnVpf() != null ?
                        this.currentYearLoanWithdrawal.getInterestOnVpf() : new BigDecimal(0));
    }


    public BigDecimal getTotal(){
        return this.getTotalMemberContrbution()
                .add(this.getTotalCompanyContrbution())
                .add(this.getTotalVpfContrbution());
    }



}
