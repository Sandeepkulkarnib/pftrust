package com.coreintegra.pftrust.dtos.pdf.loan;

import com.coreintegra.pftrust.util.EnglishNumberToWords;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@XmlRootElement(name = "loanReceipt")
public class LoanReceipt {

    private Date date;
    private String applicationNumber;
    private String accountNumber;
    private String bankName;
    private Date paymentDate;
    private String paymentMethod;
    private String paymentBank;
    private BigDecimal amount;
    private String name;
    private String deptCode;
    private String pfNumber;
    private String tokenNumber;
    private Date approvalDate;

    @XmlElement(name = "date")
    public String getDate() {
        return fomateDate(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    @XmlElement(name = "approvalDate")
    public String getApprovalDate() {
        return fomateDate(approvalDate);
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @XmlElement(name = "paymentDate")
    public String getPaymentDate() {
        return fomateDate(paymentDate);
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    @XmlElement(name = "amount")
    public String getAmount() {
        return formateNumbers(amount);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    @XmlElement(name = "amountInWords")
    public String getAmountInWords(){
        return EnglishNumberToWords.convert(this.amount.longValue()).toUpperCase(Locale.ENGLISH) + " RUPEES";
    }


    private String fomateDate(Date date){

        if(date == null) return null;

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        String strDate= formatter.format(date);
        return strDate;
    }


    private String formateNumbers(BigDecimal bigDecimal){

        if (bigDecimal == null || bigDecimal.doubleValue() <= 1){
            return "0.00";
        }

        NumberFormat formatter = new DecimalFormat("##,##,###.00");
        return formatter.format(bigDecimal);

    }

}
