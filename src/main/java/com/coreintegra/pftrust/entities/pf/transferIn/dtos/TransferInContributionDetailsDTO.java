package com.coreintegra.pftrust.entities.pf.transferIn.dtos;

import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TransferInContributionDetailsDTO {

    private Date postingDate;
    private String chequeNumber;
    private String bank;

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal totalContribution;

    public TransferInContributionDetailsDTO(Date postingDate, String chequeNumber, String bank,
                                            BigDecimal memberContribution, BigDecimal companyContribution,
                                            BigDecimal totalContribution) {
        this.postingDate = postingDate;
        this.chequeNumber = chequeNumber;
        this.bank = bank;
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.totalContribution = totalContribution;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public BigDecimal getTotalContribution() {
        return totalContribution;
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    public static TransferInContributionDetailsDTO build(TransferIn transferIn) {

        TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

        return new TransferInContributionDetailsDTO(transferIn.getPostingDate(), transferIn.getChequeNumber(),
                transferIn.getBank(), transferInFinalDetails.getMemberContribution(),
                transferInFinalDetails.getCompanyContribution(), transferInFinalDetails.getTotal());

    }


}
