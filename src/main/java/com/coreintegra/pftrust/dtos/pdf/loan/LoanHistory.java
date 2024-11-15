package com.coreintegra.pftrust.dtos.pdf.loan;

import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.NumberFormatter;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.Date;

public class LoanHistory {

    private String loanCode;
    private String loanType;

    private Long loanDate;
    private BigDecimal loanAmount;

    public LoanHistory(String loanCode, String loanType, Long loanDate, BigDecimal loanAmount) {
        this.loanCode = loanCode;
        this.loanType = loanType;
        this.loanDate = loanDate;
        this.loanAmount = loanAmount;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    @XmlElement(name = "date")
    public String getLoanDate() {
        return DateFormatterUtil.formatDate(new Date(loanDate));
    }

    public void setLoanDate(Long loanDate) {
        this.loanDate = loanDate;
    }

    @XmlElement(name = "amount")
    public String getLoanAmount() {
        return NumberFormatter.formatNumbers(loanAmount);
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }
}
