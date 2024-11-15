package com.coreintegra.pftrust.dtos.pdf.settlement;

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

@XmlRootElement(name = "settlementDispatchLetter")
public class SettlementDispatchLetter {

    private String refNo;
    private String stlNo;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String name;

    private String fromPfNumber;

    private String paymentMode;
    private String bank;

    private Date paymentDate;
    private String chequeNumber;
    private BigDecimal amount;

    private String memberAccountNo;
    private String memberBank;

    private String createdBy;

    @XmlElement(name = "refNo")
    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    @XmlElement(name = "stlNo")
    public String getStlNo() {
        return stlNo;
    }

    public void setStlNo(String stlNo) {
        this.stlNo = stlNo;
    }

    @XmlElement(name = "addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @XmlElement(name = "addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @XmlElement(name = "addressLine3")
    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @XmlElement(name = "addressLine4")
    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "fromPfNumber")
    public String getFromPfNumber() {
        return fromPfNumber;
    }

    public void setFromPfNumber(String fromPfNumber) {
        this.fromPfNumber = fromPfNumber;
    }

    @XmlElement(name = "paymentMode")
    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @XmlElement(name = "bank")
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @XmlElement(name = "paymentDate")
    public String getPaymentDate() {
        return fomateDate(paymentDate);
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @XmlElement(name = "chequeNumber")
    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    @XmlElement(name = "amount")
    public String getAmount() {
        return formateNumbers(amount);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @XmlElement(name = "createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @XmlElement(name = "date")
    public String getDate(){
        return fomateDate(new Date());
    }


    @XmlElement(name = "accountNo")
    public String getMemberAccountNo() {
        return memberAccountNo;
    }

    public void setMemberAccountNo(String memberAccountNo) {
        this.memberAccountNo = memberAccountNo;
    }

    @XmlElement(name = "memberBank")
    public String getMemberBank() {
        return memberBank;
    }

    public void setMemberBank(String memberBank) {
        this.memberBank = memberBank;
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
