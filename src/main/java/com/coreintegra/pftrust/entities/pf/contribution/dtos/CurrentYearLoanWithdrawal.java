package com.coreintegra.pftrust.entities.pf.contribution.dtos;

import java.math.BigDecimal;

public class CurrentYearLoanWithdrawal {

    private BigDecimal member;
    private BigDecimal company;
    private BigDecimal vpf;
    private Integer contributedIn;

    private BigDecimal interestOnMember;
    private BigDecimal interestOnCompany;
    private BigDecimal interestOnVpf;

    public CurrentYearLoanWithdrawal(BigDecimal member, BigDecimal company, BigDecimal vpf,
                                     Integer contributedIn) {
        this.member = member;
        this.company = company;
        this.vpf = vpf;
        this.contributedIn = contributedIn;
    }

    public CurrentYearLoanWithdrawal(BigDecimal member, BigDecimal company, BigDecimal vpf,
                                     Integer contributedIn, BigDecimal interestOnMember,
                                     BigDecimal interestOnCompany, BigDecimal interestOnVpf) {
        this.member = member;
        this.company = company;
        this.vpf = vpf;
        this.contributedIn = contributedIn;
        this.interestOnMember = interestOnMember;
        this.interestOnCompany = interestOnCompany;
        this.interestOnVpf = interestOnVpf;
    }

    public BigDecimal getMember() {
        return member;
    }

    public void setMember(BigDecimal member) {
        this.member = member;
    }

    public BigDecimal getCompany() {
        return company;
    }

    public void setCompany(BigDecimal company) {
        this.company = company;
    }

    public BigDecimal getVpf() {
        return vpf;
    }

    public void setVpf(BigDecimal vpf) {
        this.vpf = vpf;
    }

    public Integer getContributedIn() {
        return contributedIn;
    }

    public void setContributedIn(Integer contributedIn) {
        this.contributedIn = contributedIn;
    }

    public BigDecimal getInterestOnMember() {
        return interestOnMember;
    }

    public void setInterestOnMember(BigDecimal interestOnMember) {
        this.interestOnMember = interestOnMember;
    }

    public BigDecimal getInterestOnCompany() {
        return interestOnCompany;
    }

    public void setInterestOnCompany(BigDecimal interestOnCompany) {
        this.interestOnCompany = interestOnCompany;
    }

    public BigDecimal getInterestOnVpf() {
        return interestOnVpf;
    }

    public void setInterestOnVpf(BigDecimal interestOnVpf) {
        this.interestOnVpf = interestOnVpf;
    }



    public void addMemberLoanAmount(BigDecimal memberLoanAmount){
        this.member = this.member.add(memberLoanAmount);
    }

    public void addCompanyLoanAmount(BigDecimal companyLoanAmount){
        this.company = this.company.add(companyLoanAmount);
    }

    public void addVpfLoanAmount(BigDecimal vpfLoanAmount){
        this.vpf = this.vpf.add(vpfLoanAmount);
    }

    public void addInterestOnMemberLoanAmount(BigDecimal interestOnMemberLoanAmount){
        this.interestOnMember = this.interestOnMember
                .add(interestOnMemberLoanAmount);
    }

    public void addInterestOnCompanyLoanAmount(BigDecimal interestOnCompanyLoanAmount){
        this.interestOnCompany = this.interestOnCompany
                .add(interestOnCompanyLoanAmount);
    }

    public void addInterestOnVpfLoanAmount(BigDecimal interestOnVpfLoanAmount){
        this.interestOnVpf = this.interestOnVpf
                .add(interestOnVpfLoanAmount);
    }



    public void addLoanAmounts(CurrentYearLoanWithdrawal withdrawal){

        this.addMemberLoanAmount(withdrawal.getMember());
        this.addCompanyLoanAmount(withdrawal.getCompany());
        this.addVpfLoanAmount(withdrawal.getVpf());

        this.addInterestOnMemberLoanAmount(withdrawal.getInterestOnMember());
        this.addInterestOnCompanyLoanAmount(withdrawal.getInterestOnCompany());
        this.addInterestOnVpfLoanAmount(withdrawal.getInterestOnVpf());

    }


    public BigDecimal getTotal(){
        return this.getMember()
                .add(this.getCompany())
                .add(this.getVpf())
                .add(this.getInterestOnMember())
                .add(this.getInterestOnCompany())
                .add(this.getInterestOnVpf());
    }

    public BigDecimal getTotalContribution(){
        return this.getMember()
                .add(this.company)
                .add(this.vpf);
    }

    public BigDecimal getTotalInterest(){
        return this.getInterestOnMember()
                .add(this.interestOnCompany)
                .add(this.interestOnVpf);

    }

}
