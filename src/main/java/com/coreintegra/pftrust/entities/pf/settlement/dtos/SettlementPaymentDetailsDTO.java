package com.coreintegra.pftrust.entities.pf.settlement.dtos;

import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementFinalDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SettlementPaymentDetailsDTO {

    //    total calculated amounts
    private BigDecimal totalMemberContribution;
    private BigDecimal totalCompanyContribution;
    private BigDecimal totalVpfContribution;

    //    total
    private BigDecimal totalContribution;

    //    net credit amount
    private BigDecimal netCreditAmount;

    private BigDecimal incomeTax;
    private BigDecimal eduCess;

    private Date paymentDate;
    private String chequeNo;
    private String bank;
    private String paymentMode;
    private String documentNumber;


    public SettlementPaymentDetailsDTO(BigDecimal totalMemberContribution, BigDecimal totalCompanyContribution,
                                       BigDecimal totalVpfContribution, BigDecimal totalContribution,
                                       BigDecimal netCreditAmount, String documentNumber, BigDecimal incomeTax,
                                       BigDecimal eduCess, String chequeNo, Date paymentDate, String bank,
                                       String paymentMode) {
        this.totalMemberContribution = totalMemberContribution;
        this.totalCompanyContribution = totalCompanyContribution;
        this.totalVpfContribution = totalVpfContribution;
        this.totalContribution = totalContribution;
        this.netCreditAmount = netCreditAmount;
        this.documentNumber = documentNumber;
        this.incomeTax = incomeTax;
        this.eduCess = eduCess;
        this.chequeNo = chequeNo;
        this.paymentDate = paymentDate;
        this.bank = bank;
        this.paymentMode = paymentMode;
    }

    public BigDecimal getTotalMemberContribution() {
        return totalMemberContribution;
    }

    public void setTotalMemberContribution(BigDecimal totalMemberContribution) {
        this.totalMemberContribution = totalMemberContribution;
    }

    public BigDecimal getTotalCompanyContribution() {
        return totalCompanyContribution;
    }

    public void setTotalCompanyContribution(BigDecimal totalCompanyContribution) {
        this.totalCompanyContribution = totalCompanyContribution;
    }

    public BigDecimal getTotalVpfContribution() {
        return totalVpfContribution;
    }

    public void setTotalVpfContribution(BigDecimal totalVpfContribution) {
        this.totalVpfContribution = totalVpfContribution;
    }

    public BigDecimal getTotalContribution() {
        return totalContribution;
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    public BigDecimal getNetCreditAmount() {
        return netCreditAmount;
    }

    public void setNetCreditAmount(BigDecimal netCreditAmount) {
        this.netCreditAmount = netCreditAmount;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getEduCess() {
        return eduCess;
    }

    public void setEduCess(BigDecimal eduCess) {
        this.eduCess = eduCess;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public static SettlementPaymentDetailsDTO build(Settlement settlement) {

        SettlementFinalDetails settlementFinalDetails = settlement.getSettlementFinalDetails();

        return new SettlementPaymentDetailsDTO(settlementFinalDetails.getTotalMemberContribution(),
                settlementFinalDetails.getTotalCompanyContribution(),
                settlementFinalDetails.getTotalVpfContribution(),
                settlementFinalDetails.getTotalContribution(),
                settlementFinalDetails.getNetCreditAmount(),
                settlement.getDocumentNumber(), settlement.getIncomeTax(),
                settlement.getEduCess(), settlement.getChequeNo(), settlement.getPaymentDate(),
                settlement.getBank().getName(), settlement.getPaymentMode().getMode());

    }

}
