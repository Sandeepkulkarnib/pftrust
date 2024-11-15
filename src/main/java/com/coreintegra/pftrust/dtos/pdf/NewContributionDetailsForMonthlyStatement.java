package com.coreintegra.pftrust.dtos.pdf;

import com.coreintegra.pftrust.util.NumberFormatter;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class NewContributionDetailsForMonthlyStatement {

    private String[] months = new String[]{"April", "May", "June", "July", "August", "September", "October",
            "November", "December", "January", "February", "March"};

    private BigDecimal memberContributionTaxFree = new BigDecimal(0);
    private BigDecimal memberContributionTaxable = new BigDecimal(0);
    private BigDecimal memberContribution = new BigDecimal(0);

    private BigDecimal companyContribution = new BigDecimal(0);

    private BigDecimal vpfContributionTaxFree = new BigDecimal(0);
    private BigDecimal vpfContributionTaxable = new BigDecimal(0);
    private BigDecimal vpfContribution = new BigDecimal(0);

    private Integer year;
    private Integer month;

    private BigDecimal pfBase = BigDecimal.ZERO;

    public NewContributionDetailsForMonthlyStatement() {
    }

    public NewContributionDetailsForMonthlyStatement(BigDecimal memberContributionTaxFree,
                                                     BigDecimal memberContributionTaxable,
                                                     BigDecimal memberContribution, BigDecimal companyContribution,
                                                     BigDecimal vpfContributionTaxFree,
                                                     BigDecimal vpfContributionTaxable, BigDecimal vpfContribution,
                                                     Integer year, Integer month, BigDecimal pfBase) {
        this.memberContributionTaxFree = memberContributionTaxFree;
        this.memberContributionTaxable = memberContributionTaxable;
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContributionTaxFree = vpfContributionTaxFree;
        this.vpfContributionTaxable = vpfContributionTaxable;
        this.vpfContribution = vpfContribution;
        this.year = year;
        this.month = month;
        this.pfBase = pfBase;
    }

    public NewContributionDetailsForMonthlyStatement(BigDecimal memberContributionTaxFree,
                                                     BigDecimal memberContributionTaxable,
                                                     BigDecimal memberContribution,
                                                     BigDecimal companyContribution,
                                                     BigDecimal vpfContributionTaxFree,
                                                     BigDecimal vpfContributionTaxable,
                                                     BigDecimal vpfContribution) {
        this.memberContributionTaxFree = memberContributionTaxFree;
        this.memberContributionTaxable = memberContributionTaxable;
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContributionTaxFree = vpfContributionTaxFree;
        this.vpfContributionTaxable = vpfContributionTaxable;
        this.vpfContribution = vpfContribution;
    }

    public BigDecimal getMemberContribution() {
        return memberContribution != null ? memberContribution : new BigDecimal(0);
    }

    public BigDecimal getMemberContributionTaxFree() {
        return memberContributionTaxFree;
    }

    public void setMemberContributionTaxFree(BigDecimal memberContributionTaxFree) {
        this.memberContributionTaxFree = memberContributionTaxFree;
    }

    @XmlElement(name = "pfMemberContributionTaxFree")
    public String getPfMemberContributionTaxFree() {
        return NumberFormatter.formatNumbers(memberContributionTaxFree);
    }

    public BigDecimal getMemberContributionTaxable() {
        return memberContributionTaxable;
    }

    public void setMemberContributionTaxable(BigDecimal memberContributionTaxable) {
        this.memberContributionTaxable = memberContributionTaxable;
    }

    @XmlElement(name = "pfMemberContributionTaxable")
    public String getPfMemberContributionTaxable() {
        return NumberFormatter.formatNumbers(memberContributionTaxable);
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

    public BigDecimal getVpfContributionTaxFree() {
        return vpfContributionTaxFree;
    }

    public void setVpfContributionTaxFree(BigDecimal vpfContributionTaxFree) {
        this.vpfContributionTaxFree = vpfContributionTaxFree;
    }

    @XmlElement(name = "pfVpfContributionTaxFree")
    public String getPfVpfContributionTaxFree() {
        return NumberFormatter.formatNumbers(vpfContributionTaxFree);
    }

    public BigDecimal getVpfContribution() {
        return vpfContribution != null ? vpfContribution : new BigDecimal(0);
    }

    public BigDecimal getVpfContributionTaxable() {
        return vpfContributionTaxable;
    }

    public void setVpfContributionTaxable(BigDecimal vpfContributionTaxable) {
        this.vpfContributionTaxable = vpfContributionTaxable;
    }

    @XmlElement(name = "pfVpfContributionTaxable")
    public String getPfVpfContributionTaxable() {
        return NumberFormatter.formatNumbers(vpfContributionTaxable);
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
