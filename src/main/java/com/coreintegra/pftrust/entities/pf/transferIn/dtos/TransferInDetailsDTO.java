package com.coreintegra.pftrust.entities.pf.transferIn.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TransferInDetailsDTO {

    private String entityId;

    private String company;
    private String pfNumber;
    private String pensionAccountNumber;

    private Date dateOfJoining;
    private Date dateOfLeaving;

    private String accountHolder;

    private String status;

    private Date postingDate;

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;

    private String documentNumber;

    private String annexureKFile;
    private String dispatchLetterFile;

    private Integer financialYear;
    private Integer financialMonth;

    public TransferInDetailsDTO(String entityId, String company, String pfNumber, String pensionAccountNumber,
                                Date dateOfJoining, Date dateOfLeaving, String accountHolder, String status,
                                Date postingDate, BigDecimal memberContribution, BigDecimal companyContribution,
                                String documentNumber, String annexureKFile, String dispatchLetterFile,
                                Integer financialYear, Integer financialMonth) {
        this.entityId = entityId;
        this.company = company;
        this.pfNumber = pfNumber;
        this.pensionAccountNumber = pensionAccountNumber;
        this.dateOfJoining = dateOfJoining;
        this.dateOfLeaving = dateOfLeaving;
        this.accountHolder = accountHolder;
        this.status = status;
        this.postingDate = postingDate;
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.documentNumber = documentNumber;
        this.annexureKFile = annexureKFile;
        this.dispatchLetterFile = dispatchLetterFile;
        this.financialYear = financialYear;
        this.financialMonth = financialMonth;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getPensionAccountNumber() {
        return pensionAccountNumber;
    }

    public void setPensionAccountNumber(String pensionAccountNumber) {
        this.pensionAccountNumber = pensionAccountNumber;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(Date dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public BigDecimal getMemberContribution() {
        return memberContribution;
    }

    public void setMemberContribution(BigDecimal memberContribution) {
        this.memberContribution = memberContribution;
    }

    public BigDecimal getCompanyContribution() {
        return companyContribution;
    }

    public void setCompanyContribution(BigDecimal companyContribution) {
        this.companyContribution = companyContribution;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getAnnexureKFile() {
        return annexureKFile;
    }

    public void setAnnexureKFile(String annexureKFile) {
        this.annexureKFile = annexureKFile;
    }

    public String getDispatchLetterFile() {
        return dispatchLetterFile;
    }

    public void setDispatchLetterFile(String dispatchLetterFile) {
        this.dispatchLetterFile = dispatchLetterFile;
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
