package com.coreintegra.pftrust.entities.pf.contribution.dtos;

import java.math.BigDecimal;

public class CurrentYearContribution {

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal vpfContribution;

    private Integer contributedIn;

    private BigDecimal interestOnMemberContribution;
    private BigDecimal interestOnCompanyContribution;
    private BigDecimal interestOnVpfContribution;


    public CurrentYearContribution(BigDecimal memberContribution,
                                   BigDecimal companyContribution,
                                   BigDecimal vpfContribution, Integer contributedIn) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.contributedIn = contributedIn;
    }

    public CurrentYearContribution(BigDecimal memberContribution, BigDecimal companyContribution,
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

    public CurrentYearContribution(BigDecimal memberContribution, BigDecimal companyContribution, BigDecimal vpfContribution, BigDecimal interestOnMemberContribution, BigDecimal interestOnCompanyContribution, BigDecimal interestOnVpfContribution) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.interestOnMemberContribution = interestOnMemberContribution;
        this.interestOnCompanyContribution = interestOnCompanyContribution;
        this.interestOnVpfContribution = interestOnVpfContribution;
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

    public BigDecimal getVpfContribution() {
        return vpfContribution;
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

    public void setInterestOnMemberContribution(BigDecimal interestOnMemberContribution) {
        this.interestOnMemberContribution = interestOnMemberContribution;
    }

    public BigDecimal getInterestOnCompanyContribution() {
        return interestOnCompanyContribution;
    }

    public void setInterestOnCompanyContribution(BigDecimal interestOnCompanyContribution) {
        this.interestOnCompanyContribution = interestOnCompanyContribution;
    }

    public BigDecimal getInterestOnVpfContribution() {
        return interestOnVpfContribution;
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


    public void addContribution(CurrentYearContribution contribution){

        this.addMemberContribution(contribution.getMemberContribution());
        this.addCompanyContribution(contribution.getCompanyContribution());
        this.addVpfContribution(contribution.getVpfContribution());

        this.addInterestOnMemberContribution(contribution.getInterestOnMemberContribution());
        this.addInterestOnCompanyContribution(contribution.getInterestOnCompanyContribution());
        this.addInterestOnVpfContribution(contribution.getInterestOnVpfContribution());

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

    public BigDecimal getTotalInterest(){
        return (this.getInterestOnMemberContribution() != null ? this.getInterestOnMemberContribution() : new BigDecimal(0))
                .add(this.interestOnCompanyContribution != null ? this.interestOnCompanyContribution : new BigDecimal(0))
                .add(this.interestOnVpfContribution != null ? this.interestOnVpfContribution : new BigDecimal(0));
    }

}
