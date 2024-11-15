package com.coreintegra.pftrust.entities.pf.transferOut;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentTransferInContribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentYearContribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentYearLoanWithdrawal;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.YearOpeningContribution;
import com.coreintegra.pftrust.entities.pf.transferOut.dtos.TransferOutDTO;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class TransferOutFinalDetails extends BaseAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    @OneToOne
    @JsonIgnore
    private TransferOut transferOut;

//    Year Opening
    private BigDecimal memberContributionYearOpening;
    private BigDecimal companyContributionYearOpening;
    private BigDecimal vpfContributionYearOpening;

    private BigDecimal interestOnMemberContributionYearOpening;
    private BigDecimal interestOnCompanyContributionYearOpening;
    private BigDecimal interestOnVpfContributionYearOpening;

    private BigDecimal totalContributionYearOpening;
    private BigDecimal totalInterestYearOpening;


    //    Current Year
    private BigDecimal memberContributionCurrentYear;
    private BigDecimal companyContributionCurrentYear;
    private BigDecimal vpfContributionCurrentYear;

    private BigDecimal interestOnMemberContributionCurrentYear;
    private BigDecimal interestOnCompanyContributionCurrentYear;
    private BigDecimal interestOnVpfContributionCurrentYear;

    private BigDecimal totalContributionCurrentYear;
    private BigDecimal totalInterestCurrentYear;



    //    Current Year Transfer in
    private BigDecimal memberContributionCurrentYearTi;
    private BigDecimal companyContributionCurrentYearTi;
    private BigDecimal vpfContributionCurrentYearTi;

    private BigDecimal interestOnMemberContributionCurrentYearTi;
    private BigDecimal interestOnCompanyContributionCurrentYearTi;
    private BigDecimal interestOnVpfContributionCurrentYearTi;

    private BigDecimal totalContributionCurrentYearTi;
    private BigDecimal totalInterestCurrentYearTi;


//    Current Year Loan Withdrawals
    private BigDecimal memberLoanWithDrawal;
    private BigDecimal companyLoanWithDrawal;
    private BigDecimal vpfLoanWithDrawal;

    private BigDecimal interestOnMemberLoanWithDrawal;
    private BigDecimal interestOnCompanyLoanWithDrawal;
    private BigDecimal interestOnVpfLoanWithDrawal;

    private BigDecimal totalLoanWithDrawal;
    private BigDecimal totalInterestLoanWithDrawal;


//    total calculated amounts
    private BigDecimal totalMemberContribution;
    private BigDecimal totalCompanyContribution;
    private BigDecimal totalVpfContribution;

    private BigDecimal totalLoan;

//    total
    private BigDecimal totalContribution;


    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public TransferOut getTransferOut() {
        return transferOut;
    }

    public void setTransferOut(TransferOut transferOut) {
        this.transferOut = transferOut;
    }

    public BigDecimal getMemberContributionYearOpening() {
        return memberContributionYearOpening;
    }

    public void setMemberContributionYearOpening(BigDecimal memberContributionYearOpening) {
        this.memberContributionYearOpening = memberContributionYearOpening;
    }

    public BigDecimal getCompanyContributionYearOpening() {
        return companyContributionYearOpening;
    }

    public void setCompanyContributionYearOpening(BigDecimal companyContributionYearOpening) {
        this.companyContributionYearOpening = companyContributionYearOpening;
    }

    public BigDecimal getVpfContributionYearOpening() {
        return vpfContributionYearOpening;
    }

    public void setVpfContributionYearOpening(BigDecimal vpfContributionYearOpening) {
        this.vpfContributionYearOpening = vpfContributionYearOpening;
    }

    public BigDecimal getInterestOnMemberContributionYearOpening() {
        return interestOnMemberContributionYearOpening;
    }

    public void setInterestOnMemberContributionYearOpening(BigDecimal interestOnMemberContributionYearOpening) {
        this.interestOnMemberContributionYearOpening = interestOnMemberContributionYearOpening;
    }

    public BigDecimal getInterestOnCompanyContributionYearOpening() {
        return interestOnCompanyContributionYearOpening;
    }

    public void setInterestOnCompanyContributionYearOpening(BigDecimal interestOnCompanyContributionYearOpening) {
        this.interestOnCompanyContributionYearOpening = interestOnCompanyContributionYearOpening;
    }

    public BigDecimal getInterestOnVpfContributionYearOpening() {
        return interestOnVpfContributionYearOpening;
    }

    public void setInterestOnVpfContributionYearOpening(BigDecimal interestOnVpfContributionYearOpening) {
        this.interestOnVpfContributionYearOpening = interestOnVpfContributionYearOpening;
    }

    public BigDecimal getTotalContributionYearOpening() {
        return totalContributionYearOpening;
    }

    public void setTotalContributionYearOpening(BigDecimal totalContributionYearOpening) {
        this.totalContributionYearOpening = totalContributionYearOpening;
    }

    public BigDecimal getTotalInterestYearOpening() {
        return totalInterestYearOpening;
    }

    public void setTotalInterestYearOpening(BigDecimal totalInterestYearOpening) {
        this.totalInterestYearOpening = totalInterestYearOpening;
    }

    public BigDecimal getMemberContributionCurrentYear() {
        return memberContributionCurrentYear;
    }

    public void setMemberContributionCurrentYear(BigDecimal memberContributionCurrentYear) {
        this.memberContributionCurrentYear = memberContributionCurrentYear;
    }

    public BigDecimal getCompanyContributionCurrentYear() {
        return companyContributionCurrentYear;
    }

    public void setCompanyContributionCurrentYear(BigDecimal companyContributionCurrentYear) {
        this.companyContributionCurrentYear = companyContributionCurrentYear;
    }

    public BigDecimal getVpfContributionCurrentYear() {
        return vpfContributionCurrentYear;
    }

    public void setVpfContributionCurrentYear(BigDecimal vpfContributionCurrentYear) {
        this.vpfContributionCurrentYear = vpfContributionCurrentYear;
    }

    public BigDecimal getInterestOnMemberContributionCurrentYear() {
        return interestOnMemberContributionCurrentYear;
    }

    public void setInterestOnMemberContributionCurrentYear(BigDecimal interestOnMemberContributionCurrentYear) {
        this.interestOnMemberContributionCurrentYear = interestOnMemberContributionCurrentYear;
    }

    public BigDecimal getInterestOnCompanyContributionCurrentYear() {
        return interestOnCompanyContributionCurrentYear;
    }

    public void setInterestOnCompanyContributionCurrentYear(BigDecimal interestOnCompanyContributionCurrentYear) {
        this.interestOnCompanyContributionCurrentYear = interestOnCompanyContributionCurrentYear;
    }

    public BigDecimal getInterestOnVpfContributionCurrentYear() {
        return interestOnVpfContributionCurrentYear;
    }

    public void setInterestOnVpfContributionCurrentYear(BigDecimal interestOnVpfContributionCurrentYear) {
        this.interestOnVpfContributionCurrentYear = interestOnVpfContributionCurrentYear;
    }

    public BigDecimal getTotalContributionCurrentYear() {
        return totalContributionCurrentYear;
    }

    public void setTotalContributionCurrentYear(BigDecimal totalContributionCurrentYear) {
        this.totalContributionCurrentYear = totalContributionCurrentYear;
    }

    public BigDecimal getTotalInterestCurrentYear() {
        return totalInterestCurrentYear;
    }

    public void setTotalInterestCurrentYear(BigDecimal totalInterestCurrentYear) {
        this.totalInterestCurrentYear = totalInterestCurrentYear;
    }

    public BigDecimal getMemberContributionCurrentYearTi() {
        return memberContributionCurrentYearTi;
    }

    public void setMemberContributionCurrentYearTi(BigDecimal memberContributionCurrentYearTi) {
        this.memberContributionCurrentYearTi = memberContributionCurrentYearTi;
    }

    public BigDecimal getCompanyContributionCurrentYearTi() {
        return companyContributionCurrentYearTi;
    }

    public void setCompanyContributionCurrentYearTi(BigDecimal companyContributionCurrentYearTi) {
        this.companyContributionCurrentYearTi = companyContributionCurrentYearTi;
    }

    public BigDecimal getVpfContributionCurrentYearTi() {
        return vpfContributionCurrentYearTi;
    }

    public void setVpfContributionCurrentYearTi(BigDecimal vpfContributionCurrentYearTi) {
        this.vpfContributionCurrentYearTi = vpfContributionCurrentYearTi;
    }

    public BigDecimal getInterestOnMemberContributionCurrentYearTi() {
        return interestOnMemberContributionCurrentYearTi;
    }

    public void setInterestOnMemberContributionCurrentYearTi(BigDecimal interestOnMemberContributionCurrentYearTi) {
        this.interestOnMemberContributionCurrentYearTi = interestOnMemberContributionCurrentYearTi;
    }

    public BigDecimal getInterestOnCompanyContributionCurrentYearTi() {
        return interestOnCompanyContributionCurrentYearTi;
    }

    public void setInterestOnCompanyContributionCurrentYearTi(BigDecimal interestOnCompanyContributionCurrentYearTi) {
        this.interestOnCompanyContributionCurrentYearTi = interestOnCompanyContributionCurrentYearTi;
    }

    public BigDecimal getInterestOnVpfContributionCurrentYearTi() {
        return interestOnVpfContributionCurrentYearTi;
    }

    public void setInterestOnVpfContributionCurrentYearTi(BigDecimal interestOnVpfContributionCurrentYearTi) {
        this.interestOnVpfContributionCurrentYearTi = interestOnVpfContributionCurrentYearTi;
    }

    public BigDecimal getTotalContributionCurrentYearTi() {
        return totalContributionCurrentYearTi;
    }

    public void setTotalContributionCurrentYearTi(BigDecimal totalContributionCurrentYearTi) {
        this.totalContributionCurrentYearTi = totalContributionCurrentYearTi;
    }

    public BigDecimal getTotalInterestCurrentYearTi() {
        return totalInterestCurrentYearTi;
    }

    public void setTotalInterestCurrentYearTi(BigDecimal totalInterestCurrentYearTi) {
        this.totalInterestCurrentYearTi = totalInterestCurrentYearTi;
    }

    public BigDecimal getMemberLoanWithDrawal() {
        return memberLoanWithDrawal;
    }

    public void setMemberLoanWithDrawal(BigDecimal memberLoanWithDrawal) {
        this.memberLoanWithDrawal = memberLoanWithDrawal;
    }

    public BigDecimal getCompanyLoanWithDrawal() {
        return companyLoanWithDrawal;
    }

    public void setCompanyLoanWithDrawal(BigDecimal companyLoanWithDrawal) {
        this.companyLoanWithDrawal = companyLoanWithDrawal;
    }

    public BigDecimal getVpfLoanWithDrawal() {
        return vpfLoanWithDrawal;
    }

    public void setVpfLoanWithDrawal(BigDecimal vpfLoanWithDrawal) {
        this.vpfLoanWithDrawal = vpfLoanWithDrawal;
    }

    public BigDecimal getInterestOnMemberLoanWithDrawal() {
        return interestOnMemberLoanWithDrawal;
    }

    public void setInterestOnMemberLoanWithDrawal(BigDecimal interestOnMemberLoanWithDrawal) {
        this.interestOnMemberLoanWithDrawal = interestOnMemberLoanWithDrawal;
    }

    public BigDecimal getInterestOnCompanyLoanWithDrawal() {
        return interestOnCompanyLoanWithDrawal;
    }

    public void setInterestOnCompanyLoanWithDrawal(BigDecimal interestOnCompanyLoanWithDrawal) {
        this.interestOnCompanyLoanWithDrawal = interestOnCompanyLoanWithDrawal;
    }

    public BigDecimal getInterestOnVpfLoanWithDrawal() {
        return interestOnVpfLoanWithDrawal;
    }

    public void setInterestOnVpfLoanWithDrawal(BigDecimal interestOnVpfLoanWithDrawal) {
        this.interestOnVpfLoanWithDrawal = interestOnVpfLoanWithDrawal;
    }

    public BigDecimal getTotalLoanWithDrawal() {
        return totalLoanWithDrawal;
    }

    public void setTotalLoanWithDrawal(BigDecimal totalLoanWithDrawal) {
        this.totalLoanWithDrawal = totalLoanWithDrawal;
    }

    public BigDecimal getTotalInterestLoanWithDrawal() {
        return totalInterestLoanWithDrawal;
    }

    public void setTotalInterestLoanWithDrawal(BigDecimal totalInterestLoanWithDrawal) {
        this.totalInterestLoanWithDrawal = totalInterestLoanWithDrawal;
    }

    public BigDecimal getTotalMemberContribution() {
        return totalMemberContribution;
    }

    public void setTotalMemberContribution(BigDecimal totalMemberContribution) {
        this.totalMemberContribution = totalMemberContribution;
    }

    public BigDecimal getTotalCompanyContribution() {
        return totalCompanyContribution;
    }

    public void setTotalCompanyContribution(BigDecimal totalCompanyContribution) {
        this.totalCompanyContribution = totalCompanyContribution;
    }

    public BigDecimal getTotalVpfContribution() {
        return totalVpfContribution;
    }

    public void setTotalVpfContribution(BigDecimal totalVpfContribution) {
        this.totalVpfContribution = totalVpfContribution;
    }

    public BigDecimal getTotalContribution() {
        return totalContribution;
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    public BigDecimal getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(BigDecimal totalLoan) {
        this.totalLoan = totalLoan;
    }

    public void map(TransferOutDTO transferOutDTO){

//        year opening
        YearOpeningContribution yearOpeningContribution = transferOutDTO.getYearOpeningContribution();

        this.setMemberContributionYearOpening(yearOpeningContribution.getMemberContribution());
        this.setCompanyContributionYearOpening(yearOpeningContribution.getCompanyContribution());
        this.setVpfContributionYearOpening(yearOpeningContribution.getVpfContribution());

        this.setInterestOnMemberContributionYearOpening(yearOpeningContribution.getInterestOnMemberContribution());
        this.setInterestOnCompanyContributionYearOpening(yearOpeningContribution.getInterestOnCompanyContribution());
        this.setInterestOnVpfContributionYearOpening(yearOpeningContribution.getInterestOnVpfContribution());

        this.setTotalContributionYearOpening(yearOpeningContribution.getTotalContribution());
        this.setTotalInterestYearOpening(yearOpeningContribution.getTotalInterest());

//      current year
        CurrentYearContribution currentYearContribution = transferOutDTO.getCurrentYearContribution();

        this.setMemberContributionCurrentYear(currentYearContribution.getMemberContribution());
        this.setCompanyContributionCurrentYear(currentYearContribution.getCompanyContribution());
        this.setVpfContributionCurrentYear(currentYearContribution.getVpfContribution());

        this.setInterestOnMemberContributionCurrentYear(currentYearContribution.getInterestOnMemberContribution());
        this.setInterestOnCompanyContributionCurrentYear(currentYearContribution.getInterestOnCompanyContribution());
        this.setInterestOnVpfContributionCurrentYear(currentYearContribution.getInterestOnVpfContribution());

        this.setTotalContributionCurrentYear(currentYearContribution.getTotalContribution());
        this.setTotalInterestCurrentYear(currentYearContribution.getTotalInterest());


        //      current year ti
        CurrentTransferInContribution currentTransferInContribution = transferOutDTO.getCurrentTransferInContribution();

        this.setMemberContributionCurrentYearTi(currentTransferInContribution.getMemberContribution());
        this.setCompanyContributionCurrentYearTi(currentTransferInContribution.getCompanyContribution());
        this.setVpfContributionCurrentYearTi(currentTransferInContribution.getVpfContribution());

        this.setInterestOnMemberContributionCurrentYearTi(currentTransferInContribution.getInterestOnMemberContribution());
        this.setInterestOnCompanyContributionCurrentYearTi(currentTransferInContribution.getInterestOnCompanyContribution());
        this.setInterestOnVpfContributionCurrentYearTi(currentTransferInContribution.getInterestOnVpfContribution());

        this.setTotalContributionCurrentYearTi(currentTransferInContribution.getTotalContribution());
        this.setTotalInterestCurrentYearTi(currentTransferInContribution.getTotalInterest());


//        Current year loan withdrawal
        CurrentYearLoanWithdrawal currentYearLoanWithdrawal = transferOutDTO.getCurrentYearLoanWithdrawal();

        this.setMemberLoanWithDrawal(currentYearLoanWithdrawal.getMember());
        this.setCompanyLoanWithDrawal(currentYearLoanWithdrawal.getCompany());
        this.setVpfLoanWithDrawal(currentYearLoanWithdrawal.getVpf());

        this.setInterestOnMemberLoanWithDrawal(currentYearLoanWithdrawal.getInterestOnMember());
        this.setInterestOnCompanyLoanWithDrawal(currentYearLoanWithdrawal.getInterestOnCompany());
        this.setInterestOnVpfLoanWithDrawal(currentYearLoanWithdrawal.getInterestOnVpf());

        this.setTotalLoanWithDrawal(currentYearLoanWithdrawal.getTotalContribution());
        this.setTotalInterestLoanWithDrawal(currentYearLoanWithdrawal.getTotalInterest());

//        total calculated amounts
        this.setTotalMemberContribution(transferOutDTO.getTotalMemberContrbution());
        this.setTotalCompanyContribution(transferOutDTO.getTotalCompanyContrbution());
        this.setTotalVpfContribution(transferOutDTO.getTotalVpfContrbution());

//        total Contribution
        this.setTotalContribution(transferOutDTO.getTotal());
       // this.setTotalContribution(new BigDecimal(transferOutDTO.getTotalCont()));

    }


//    year opening calculation
    public BigDecimal getYearOpeningTotalMemberContributionWI(){
        return this.getMemberContributionYearOpening();
    }

    public BigDecimal getYearOpeningCompanyContributionWI(){
        return this.getCompanyContributionYearOpening();
    }


    public BigDecimal getYearOpeningTotalVpfContributionWI(){
        return this.getVpfContributionYearOpening();
    }



//    interest on employee contribution total
    public BigDecimal getInterestOnMemberContributionTotal(){
        return this.getInterestOnMemberContributionYearOpening()
                .add(this.getInterestOnMemberContributionCurrentYear())
                .add(this.getInterestOnMemberContributionCurrentYearTi())
                .subtract(this.getInterestOnMemberLoanWithDrawal());
    }


    //    interest on company contribution total
    public BigDecimal getInterestOnCompanyContributionTotal(){
        return this.getInterestOnCompanyContributionYearOpening()
                .add(this.getInterestOnCompanyContributionCurrentYear())
                .add(this.getInterestOnCompanyContributionCurrentYearTi())
                .subtract(this.getInterestOnCompanyLoanWithDrawal());
    }


    //    interest on vpf contribution total
    public BigDecimal getInterestOnVpfContributionTotal(){
        return this.getInterestOnVpfContributionYearOpening()
                .add(this.getInterestOnVpfContributionCurrentYear())
                .add(this.getInterestOnVpfContributionCurrentYearTi())
                .subtract(this.getInterestOnVpfLoanWithDrawal());
    }



    public BigDecimal getTotalContributions(){
        return this.getYearOpeningTotalMemberContributionWI() //a
                .add(this.getYearOpeningCompanyContributionWI()) //b
                .add(this.getYearOpeningTotalVpfContributionWI()) //c
                .add(this.getMemberContributionCurrentYear()) //d
                .add(this.getMemberContributionCurrentYearTi()) //e
                .add(this.getInterestOnMemberContributionTotal()) //f
                .add(this.getCompanyContributionCurrentYear()) //g
                .add(this.getCompanyContributionCurrentYearTi()) //h
                .add(this.getInterestOnCompanyContributionTotal()) //i
                .add(this.getVpfContributionCurrentYear()) //j
                .add(this.getInterestOnVpfContributionTotal()); //k
    }


    public BigDecimal getTotalNetCreditAmount(){
        return this.getTotalContributions()
                .subtract(this.getTotalLoanWithDrawal());
    }


}
