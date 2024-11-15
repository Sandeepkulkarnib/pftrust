package com.coreintegra.pftrust.dtos.pdf.yearend;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ContributionDTO {

    private final String[] months = new String[]{"April", "May", "June", "July", "August", "September", "October",
            "November", "December", "January", "February", "March"};

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal vpfContribution;

    private Integer contributedIn;

    private BigDecimal interestOnMemberContribution;
    private BigDecimal interestOnCompanyContribution;
    private BigDecimal interestOnVpfContribution;

    public ContributionDTO(BigDecimal memberContribution, BigDecimal companyContribution,
                           BigDecimal vpfContribution, Integer contributedIn,
                           BigDecimal interestOnMemberContribution,
                           BigDecimal interestOnCompanyContribution,
                           BigDecimal interestOnVpfContribution) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.contributedIn = contributedIn;
        this.interestOnMemberContribution = interestOnMemberContribution;
        this.interestOnCompanyContribution = interestOnCompanyContribution;
        this.interestOnVpfContribution = interestOnVpfContribution;
    }

    public BigDecimal getMemberContribution() {
        return memberContribution;
    }

    @XmlElement(name = "memberContributionfr")
    public String getMemberContributionfr() {
        return formateNumbers(memberContribution);
    }

    public void setMemberContribution(BigDecimal memberContribution) {
        this.memberContribution = memberContribution;
    }

    public String getCompanyContribution() {
        return formateNumbers(companyContribution);
    }

    @XmlElement(name = "companyContributionfr")
    public String getCompanyContributionfr() {
        return formateNumbers(companyContribution);
    }

    public void setCompanyContribution(BigDecimal companyContribution) {
        this.companyContribution = companyContribution;
    }

    public BigDecimal getVpfContribution() {
        return vpfContribution;
    }

    @XmlElement(name = "vpfContributionfr")
    public String getVpfContributionfr() {
        return formateNumbers(vpfContribution);
    }

    public void setVpfContribution(BigDecimal vpfContribution) {
        this.vpfContribution = vpfContribution;
    }

    public Integer getContributedIn() {
        return contributedIn;
    }

    public void setContributedIn(Integer contributedIn) {
        this.contributedIn = contributedIn;
    }

    public BigDecimal getInterestOnMemberContribution() {
        return interestOnMemberContribution;
    }

    @XmlElement(name = "interestOnMemberContributionfr")
    public String getInterestOnMemberContributionfr() {
        return formateNumbers(interestOnMemberContribution);
    }

    public void setInterestOnMemberContribution(BigDecimal interestOnMemberContribution) {
        this.interestOnMemberContribution = interestOnMemberContribution;
    }

    public BigDecimal getInterestOnCompanyContribution() {
        return interestOnCompanyContribution;
    }

    @XmlElement(name = "interestOnCompanyContributionfr")
    public String getInterestOnCompanyContributionfr() {
        return formateNumbers(interestOnCompanyContribution);
    }

    public void setInterestOnCompanyContribution(BigDecimal interestOnCompanyContribution) {
        this.interestOnCompanyContribution = interestOnCompanyContribution;
    }

    public BigDecimal getInterestOnVpfContribution() {
        return interestOnVpfContribution;
    }

    @XmlElement(name = "interestOnVpfContributionfr")
    public String getInterestOnVpfContributionfr() {
        return formateNumbers(interestOnVpfContribution);
    }

    public void setInterestOnVpfContribution(BigDecimal interestOnVpfContribution) {
        this.interestOnVpfContribution = interestOnVpfContribution;
    }

    //    add to get one
    public void addMemberContribution(BigDecimal memberContribution){
        this.memberContribution = this.memberContribution.add(memberContribution);
    }

    public void addCompanyContribution(BigDecimal companyContribution){
        this.companyContribution = this.companyContribution.add(companyContribution);
    }

    public void addVpfContribution(BigDecimal vpfContribution){
        this.vpfContribution = this.vpfContribution.add(vpfContribution);
    }

    public void addInterestOnMemberContribution(BigDecimal interestOnMemberContribution){
        this.interestOnMemberContribution = this.interestOnMemberContribution
                .add(interestOnMemberContribution);
    }

    public void addInterestOnCompanyContribution(BigDecimal interestOnCompanyContribution){
        this.interestOnCompanyContribution = this.interestOnCompanyContribution
                .add(interestOnCompanyContribution);
    }

    public void addInterestOnVpfContribution(BigDecimal interestOnVpfContribution){
        this.interestOnVpfContribution = this.interestOnVpfContribution
                .add(interestOnVpfContribution);
    }

    public BigDecimal getTotal(){
        return this.getTotalContribution()
                .add(this.getTotalInterest());
    }


    public BigDecimal getTotalContribution(){
        return (this.getMemberContribution() != null ? this.getMemberContribution() : new BigDecimal(0))
                .add(this.companyContribution != null ? this.companyContribution : new BigDecimal(0))
                .add(this.vpfContribution != null ? this.vpfContribution : new BigDecimal(0));
    }

    @XmlElement(name = "totalContributionfr")
    public String getTotalContributionfr(){
        return formateNumbers((this.getMemberContribution() != null ? this.getMemberContribution() : new BigDecimal(0))
                .add(this.companyContribution != null ? this.companyContribution : new BigDecimal(0))
                .add(this.vpfContribution != null ? this.vpfContribution : new BigDecimal(0)));
    }



    public BigDecimal getTotalInterest(){
        return (this.getInterestOnMemberContribution() != null ? this.getInterestOnMemberContribution() : new BigDecimal(0))
                .add(this.interestOnCompanyContribution != null ? this.interestOnCompanyContribution : new BigDecimal(0))
                .add(this.interestOnVpfContribution != null ? this.interestOnVpfContribution : new BigDecimal(0));
    }


    @XmlElement(name = "totalInterestfr")
    public String getTotalInterestfr(){
        return formateNumbers((this.getInterestOnMemberContribution() != null ? this.getInterestOnMemberContribution() : new BigDecimal(0))
                .add(this.interestOnCompanyContribution != null ? this.interestOnCompanyContribution : new BigDecimal(0))
                .add(this.interestOnVpfContribution != null ? this.interestOnVpfContribution : new BigDecimal(0)));
    }


    @XmlElement(name = "monthName")
    public String getCnMonth() {

        if(contributedIn == null || contributedIn == 0){
            return "";
        }

        return this.months[contributedIn-1];
    }


    private String formateNumbers(BigDecimal bigDecimal){

        if (bigDecimal == null || bigDecimal.doubleValue() <= 1){
            return "0.00";
        }

        NumberFormat formatter = new DecimalFormat("##,##,###.00");

        return formatter.format(bigDecimal);

    }

}
