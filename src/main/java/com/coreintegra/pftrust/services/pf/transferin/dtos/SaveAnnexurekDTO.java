package com.coreintegra.pftrust.services.pf.transferin.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class SaveAnnexurekDTO {

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;

    private Date postingDate;
    private Date dateOfJoiningPrior;
    private String bank;
    private String refNumber;
    private String chequeNumber;

    private String annexureKFile;
    private String dispatchLetterFile;

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

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getDateOfJoiningPrior() {
        return dateOfJoiningPrior;
    }

    public void setDateOfJoiningPrior(Date dateOfJoiningPrior) {
        this.dateOfJoiningPrior = dateOfJoiningPrior;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
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
}
