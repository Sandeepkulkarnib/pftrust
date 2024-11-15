package com.coreintegra.pftrust.entities.pf.contribution.dtos;

import java.math.BigDecimal;

public class YearOpeningContribution {

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal vpfContribution;

    private BigDecimal interestOnMemberContribution;
    private BigDecimal interestOnCompanyContribution;
    private BigDecimal interestOnVpfContribution;

    public YearOpeningContribution(BigDecimal memberContribution, BigDecimal companyContribution,
                                   BigDecimal vpfContribution) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
    }

    public YearOpeningContribution(BigDecimal memberContribution, BigDecimal companyContribution,
                                   BigDecimal vpfContribution, BigDecimal interestOnMemberContribution,
                                   BigDecimal interestOnCompanyContribution,
                                   BigDecimal interestOnVpfContribution) {
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

    public BigDecimal getTotal(){
        return (this.getMemberContribution() != null ? this.getMemberContribution() : new BigDecimal(0))
                .add(this.companyContribution != null ? this.companyContribution : new BigDecimal(0))
                .add(this.vpfContribution != null ? this.vpfContribution : new BigDecimal(0))
                .add(this.interestOnMemberContribution != null ? this.interestOnMemberContribution : new BigDecimal(0))
                .add(this.interestOnCompanyContribution != null ? this.interestOnCompanyContribution : new BigDecimal(0))
                .add(this.interestOnVpfContribution != null ? this.interestOnVpfContribution : new BigDecimal(0));

    }

    public BigDecimal getTotalContribution(){
        return (this.getMemberContribution() != null ? this.getMemberContribution() : new BigDecimal(0))
                .add(this.companyContribution != null ? this.companyContribution : new BigDecimal(0))
                .add(this.vpfContribution != null ? this.vpfContribution : new BigDecimal(0));
    }

    public BigDecimal getTotalInterest(){
        return (this.getInterestOnMemberContribution() != null ? this.getInterestOnMemberContribution() :
                new BigDecimal(0))
                .add(this.interestOnCompanyContribution != null ? this.interestOnCompanyContribution :
                        new BigDecimal(0))
                .add(this.interestOnVpfContribution != null ? this.interestOnVpfContribution :
                        new BigDecimal(0));
    }

}
