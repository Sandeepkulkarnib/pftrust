package com.coreintegra.pftrust.entities.pf.loan.dtos;

import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanWithDrawalsFinalDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LoanSanctionDetails {

    private BigDecimal amountDisbursed;
    private Date paymentDate;
    private String documentNo;

    private String bank;
    private String paymentMode;
    private String chequeNumber;

    private BigDecimal loanAmountOnPfBaseSalary;
    private BigDecimal loanAmountOnPfBalance;
    private BigDecimal loanAmountOnMemContribution;
    private BigDecimal loanAmountOnCompanyContribution;
    private BigDecimal loanAmountOnVPFContribution;

    List<LoanDocumentDTO> loanDocumentDTOS;

    public LoanSanctionDetails(BigDecimal amountDisbursed, Date paymentDate, String documentNo, String bank,
                               String paymentMode, String chequeNumber, BigDecimal loanAmountOnPfBaseSalary,
                               BigDecimal loanAmountOnPfBalance, BigDecimal loanAmountOnMemContribution,
                               BigDecimal loanAmountOnCompanyContribution, BigDecimal loanAmountOnVPFContribution,
                               List<LoanDocumentDTO> loanDocumentDTOS) {
        this.amountDisbursed = amountDisbursed;
        this.paymentDate = paymentDate;
        this.documentNo = documentNo;
        this.bank = bank;
        this.paymentMode = paymentMode;
        this.chequeNumber = chequeNumber;
        this.loanAmountOnPfBaseSalary = loanAmountOnPfBaseSalary;
        this.loanAmountOnPfBalance = loanAmountOnPfBalance;
        this.loanAmountOnMemContribution = loanAmountOnMemContribution;
        this.loanAmountOnCompanyContribution = loanAmountOnCompanyContribution;
        this.loanAmountOnVPFContribution = loanAmountOnVPFContribution;
        this.loanDocumentDTOS = loanDocumentDTOS;
    }

    public BigDecimal getAmountDisbursed() {
        return amountDisbursed;
    }

    public void setAmountDisbursed(BigDecimal amountDisbursed) {
        this.amountDisbursed = amountDisbursed;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
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

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public BigDecimal getLoanAmountOnPfBaseSalary() {
        return loanAmountOnPfBaseSalary;
    }

    public void setLoanAmountOnPfBaseSalary(BigDecimal loanAmountOnPfBaseSalary) {
        this.loanAmountOnPfBaseSalary = loanAmountOnPfBaseSalary;
    }

    public BigDecimal getLoanAmountOnPfBalance() {
        return loanAmountOnPfBalance;
    }

    public void setLoanAmountOnPfBalance(BigDecimal loanAmountOnPfBalance) {
        this.loanAmountOnPfBalance = loanAmountOnPfBalance;
    }

    public BigDecimal getLoanAmountOnMemContribution() {
        return loanAmountOnMemContribution;
    }

    public void setLoanAmountOnMemContribution(BigDecimal loanAmountOnMemContribution) {
        this.loanAmountOnMemContribution = loanAmountOnMemContribution;
    }

    public BigDecimal getLoanAmountOnCompanyContribution() {
        return loanAmountOnCompanyContribution;
    }

    public void setLoanAmountOnCompanyContribution(BigDecimal loanAmountOnCompanyContribution) {
        this.loanAmountOnCompanyContribution = loanAmountOnCompanyContribution;
    }

    public BigDecimal getLoanAmountOnVPFContribution() {
        return loanAmountOnVPFContribution;
    }

    public void setLoanAmountOnVPFContribution(BigDecimal loanAmountOnVPFContribution) {
        this.loanAmountOnVPFContribution = loanAmountOnVPFContribution;
    }

    public List<LoanDocumentDTO> getLoanDocumentDTOS() {
        return loanDocumentDTOS;
    }

    public void setLoanDocumentDTOS(List<LoanDocumentDTO> loanDocumentDTOS) {
        this.loanDocumentDTOS = loanDocumentDTOS;
    }

    public static LoanSanctionDetails build(Loan loan) {

        LoanWithDrawalsFinalDetails finalDetails = loan.getLoanWithDrawalsFinalDetails();

        List<LoanDocumentDTO> documentDTOList = loan.getLoanDocuments().stream()
                .map(loanDocument -> new LoanDocumentDTO(loanDocument.getDocument().getName(), loanDocument.getPath()))
                .collect(Collectors.toList());

        return new LoanSanctionDetails(loan.getAmountDisbursed(), loan.getPaymentDate(), loan.getDocumentNo(),
                loan.getBank().getName(), loan.getPaymentMode().getMode(), loan.getChequeNumber(),
                loan.getLoanAmountOnPfBaseSalary(), loan.getLoanAmountOnPfBalance(),
                finalDetails.getLoanAmountOnMemContribution(), finalDetails.getLoanAmountOnCompanyContribution(),
                finalDetails.getLoanAmountOnVPFContribution(), documentDTOList);

    }

}
