package com.coreintegra.pftrust.dtos.pdf;

import com.coreintegra.pftrust.util.NumberFormatter;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class ContributionDetailsForMonthlyStatement {

    private String[] months = new String[]{"April", "May", "June", "July", "August", "September", "October",
            "November", "December", "January", "February", "March"};

    private BigDecimal memberContribution = new BigDecimal(0);
    private BigDecimal companyContribution = new BigDecimal(0);
    private BigDecimal vpfContribution = new BigDecimal(0);

    private Integer year;
    private Integer month;

    private BigDecimal pfBase;

    public ContributionDetailsForMonthlyStatement() {
    }

    public ContributionDetailsForMonthlyStatement(BigDecimal memberContribution,
                                    BigDecimal companyContribution,
                                    BigDecimal vpfContribution,
                                    Integer year, Integer month) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.year = year;
        this.month = month;
    }

    public ContributionDetailsForMonthlyStatement(BigDecimal memberContribution, BigDecimal companyContribution, BigDecimal vpfContribution) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
    }

    public ContributionDetailsForMonthlyStatement(BigDecimal memberContribution, BigDecimal companyContribution,
                                                  BigDecimal vpfContribution, Integer year) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.year = year;
    }

    public ContributionDetailsForMonthlyStatement(BigDecimal memberContribution, BigDecimal companyContribution,
                                    BigDecimal vpfContribution, Integer year,
                                    Integer month, BigDecimal pfBase) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.year = year;
        this.month = month;
        this.pfBase = pfBase;
    }

    public BigDecimal getMemberContribution() {
        return memberContribution != null ? memberContribution : new BigDecimal(0);
    }

    @XmlElement(name = "pfMemberContribution")
    public String getPfMemberContribution(){
        return NumberFormatter.formatNumbers(memberContribution);
    }

    public void setMemberContribution(BigDecimal memberContribution) {
        this.memberContribution = memberContribution;
    }


    public BigDecimal getCompanyContribution() {
        return companyContribution != null ? companyContribution : new BigDecimal(0);
    }

    @XmlElement(name = "pfCompanyContribution")
    public String getPfCompanyContribution() {
        return NumberFormatter.formatNumbers(companyContribution);
    }


    public void setCompanyContribution(BigDecimal companyContribution) {
        this.companyContribution = companyContribution;
    }

    public BigDecimal getVpfContribution() {
        return vpfContribution != null ? vpfContribution : new BigDecimal(0);
    }

    @XmlElement(name = "pfVpfContribution")
    public String getPfVpfContribution() {
        return NumberFormatter.formatNumbers(vpfContribution);
    }


    public void setVpfContribution(BigDecimal vpfContribution) {
        this.vpfContribution = vpfContribution;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }


    @XmlElement(name = "monthName")
    public String getCnMonth() {

        if(month == null || month == 0){
            return "";
        }

        return this.months[month-1];
    }


    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getTotalContribution(){
        return (memberContribution != null ? memberContribution : new BigDecimal(0))
                .add(companyContribution != null ? companyContribution : new BigDecimal(0))
                .add(vpfContribution != null ? vpfContribution : new BigDecimal(0));
    }

    @XmlElement(name = "pfTotalContribution")
    public String getPfTotalContribution(){
        return NumberFormatter.formatNumbers((memberContribution != null ? memberContribution : new BigDecimal(0))
                .add(companyContribution != null ? companyContribution : new BigDecimal(0))
                .add(vpfContribution != null ? vpfContribution : new BigDecimal(0)));
    }


    public BigDecimal getPfBase() {
        return pfBase;
    }

    @XmlElement(name = "pfBaseSalary")
    public String getpfBaseSalary() {
        return NumberFormatter.formatNumbers(pfBase);
    }


}
