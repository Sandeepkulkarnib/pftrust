package com.coreintegra.pftrust.entities.pf.contribution.dtos;

import java.math.BigDecimal;

public class CurrentTransferInContribution {

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal vpfContribution;
    private Integer contributedIn;

    private BigDecimal interestOnMemberContribution;
    private BigDecimal interestOnCompanyContribution;
    private BigDecimal interestOnVpfContribution;


    public CurrentTransferInContribution(BigDecimal memberContribution,
                                         BigDecimal companyContribution,
                                         BigDecimal vpfContribution, Integer contributedIn) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.contributedIn = contributedIn;
    }

    public CurrentTransferInContribution(BigDecimal memberContribution, BigDecimal companyContribution,
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



    public void addMemberContribution(BigDecimal memberContribution){
        this.memberContribution = this.memberContribution.add(memberContribution);
    }

    public void addCompanyContribution(BigDecimal companyContribution){
        this.companyContribution = this.companyContribution.add(companyContribution);
    }

    public void addVpfContribution(BigDecimal vpfContribution){

        if(vpfContribution == null){
            this.vpfContribution = this.vpfContribution.add(new BigDecimal(0));
        }else {
            this.vpfContribution = this.vpfContribution.add(vpfContribution);
        }

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
        if(interestOnVpfContribution == null){
            this.interestOnVpfContribution = this.interestOnVpfContribution
                    .add(new BigDecimal(0));
        }else {
            this.interestOnVpfContribution = this.interestOnVpfContribution
                    .add(interestOnVpfContribution);
        }

    }



    public void addContribution(CurrentTransferInContribution contribution){

        this.addMemberContribution(contribution.getMemberContribution());
        this.addCompanyContribution(contribution.getCompanyContribution());
        this.addVpfContribution(contribution.getVpfContribution());

        this.addInterestOnMemberContribution(contribution.getInterestOnMemberContribution());
        this.addInterestOnCompanyContribution(contribution.getInterestOnCompanyContribution());
        this.addInterestOnVpfContribution(contribution.getInterestOnVpfContribution());

    }


    public BigDecimal getTotal(){
        return this.getMemberContribution()
                .add(this.companyContribution)
                .add(this.vpfContribution)
                .add(this.interestOnMemberContribution)
                .add(this.interestOnCompanyContribution)
                .add(this.interestOnVpfContribution);
    }

    public BigDecimal getTotalContribution(){
        return this.getMemberContribution()
                .add(this.companyContribution)
                .add(this.vpfContribution);
    }

    public BigDecimal getTotalInterest(){
        return this.getInterestOnMemberContribution()
                .add(this.interestOnCompanyContribution)
                .add(this.interestOnVpfContribution);

    }

}
