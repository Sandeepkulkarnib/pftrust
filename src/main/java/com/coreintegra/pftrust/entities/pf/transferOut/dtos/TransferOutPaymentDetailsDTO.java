package com.coreintegra.pftrust.entities.pf.transferOut.dtos;

import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutFinalDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TransferOutPaymentDetailsDTO {

    private String bankName;
    private String branch;
    private String accountNumber;
    private String ifscCode;
    private String micrCode;

    private BigDecimal amount;
    private String chequeNo;
    private Date paymentDate;
    private String bank;
    private String paymentMode;

    //    total calculated amounts
    private BigDecimal totalMemberContribution;
    private BigDecimal totalCompanyContribution;
    private BigDecimal totalVpfContribution;

    //    total
    private BigDecimal totalContribution;

    public TransferOutPaymentDetailsDTO(String bankName, String branch, String accountNumber, String ifscCode,
                                        String micrCode, String chequeNo, Date paymentDate, BigDecimal amount,
                                        String bank, String paymentMode, BigDecimal totalMemberContribution,
                                        BigDecimal totalCompanyContribution, BigDecimal totalVpfContribution,
                                        BigDecimal totalContribution) {
        this.bankName = bankName;
        this.branch = branch;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.micrCode = micrCode;
        this.chequeNo = chequeNo;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.bank = bank;
        this.paymentMode = paymentMode;
        this.totalMemberContribution = totalMemberContribution;
        this.totalCompanyContribution = totalCompanyContribution;
        this.totalVpfContribution = totalVpfContribution;
        this.totalContribution = totalContribution;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public static TransferOutPaymentDetailsDTO build(TransferOut transferOut) {

        TransferOutFinalDetails transferOutFinalDetails = transferOut.getTransferOutFinalDetails();

        return new TransferOutPaymentDetailsDTO(transferOut.getBankName(), transferOut.getBranch(),
                transferOut.getAccountNumber(), transferOut.getIfscCode(), transferOut.getMicrCode(),
                transferOut.getChequeNo(), transferOut.getPaymentDate(), transferOut.getAmount(),
                transferOut.getBank().getName(), transferOut.getPaymentMode().getMode(),
                transferOutFinalDetails.getTotalMemberContribution(),
                transferOutFinalDetails.getTotalCompanyContribution(),
                transferOutFinalDetails.getTotalVpfContribution(), transferOutFinalDetails.getTotalContribution());

    }

}
