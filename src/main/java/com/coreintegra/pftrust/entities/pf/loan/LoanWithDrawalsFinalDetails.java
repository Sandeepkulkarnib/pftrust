package com.coreintegra.pftrust.entities.pf.loan;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
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
public class LoanWithDrawalsFinalDetails extends BaseAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private BigDecimal loanAmountOnMemContribution;
    private BigDecimal loanAmountOnCompanyContribution;
    private BigDecimal loanAmountOnVPFContribution;

    private Integer year;
    private Integer month;

    private BigDecimal interestRate;
    private BigDecimal monthlyInterestRate;
    private Integer interestPeriod;

    //    Year Opening
    private BigDecimal memberContributionYearOpening;
    private BigDecimal companyContributionYearOpening;
    private BigDecimal vpfContributionYearOpening;

    private BigDecimal totalContributionYearOpening;


    //    Current Year
    private BigDecimal memberContributionCurrentYear;
    private BigDecimal companyContributionCurrentYear;
    private BigDecimal vpfContributionCurrentYear;

    private BigDecimal totalContributionCurrentYear;



    //    Current Year Transfer in
    private BigDecimal memberContributionCurrentYearTi;
    private BigDecimal companyContributionCurrentYearTi;
    private BigDecimal vpfContributionCurrentYearTi;

    private BigDecimal totalContributionCurrentYearTi;


    //    Current Year Loan Withdrawals
    private BigDecimal currentYearMemberLoanWithDrawal;
    private BigDecimal currentYearCompanyLoanWithDrawal;
    private BigDecimal currentYearVpfLoanWithDrawal;

    private BigDecimal currentYearTotalLoanWithDrawal;

    private BigDecimal interestOnloanAmountOnMemContribution;
    private BigDecimal interestOnloanAmountOnCompanyContribution;
    private BigDecimal interestOnloanAmountOnVPFContribution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Loan loan;

    private BigDecimal loanAmountOnTaxableMemberContribution;
    private BigDecimal loanAmountOnNonTaxableMemberContribution;

    private BigDecimal loanAmountOnTaxableVpfContribution;
    private BigDecimal loanAmountOnNonTaxableVpfContribution;

    private BigDecimal interestOnLoanAmountOnTaxableMemberContribution;
    private BigDecimal interestOnLoanAmountOnNonTaxableMemberContribution;

    private BigDecimal interestOnLoanAmountOnTaxableVpfContribution;
    private BigDecimal interestOnLoanAmountOnNonTaxableVpfContribution;

    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public LoanWithDrawalsFinalDetails() {
    }

    public LoanWithDrawalsFinalDetails(BigDecimal loanAmountOnMemContribution,
                                       BigDecimal loanAmountOnCompanyContribution,
                                       BigDecimal loanAmountOnVPFContribution) {
        this.loanAmountOnMemContribution = loanAmountOnMemContribution;
        this.loanAmountOnCompanyContribution = loanAmountOnCompanyContribution;
        this.loanAmountOnVPFContribution = loanAmountOnVPFContribution;
    }

    public LoanWithDrawalsFinalDetails(BigDecimal loanAmountOnMemContribution,
                                       BigDecimal loanAmountOnCompanyContribution,
                                       BigDecimal loanAmountOnVPFContribution,
                                       Integer year, Integer month) {
        this.loanAmountOnMemContribution = loanAmountOnMemContribution;
        this.loanAmountOnCompanyContribution = loanAmountOnCompanyContribution;
        this.loanAmountOnVPFContribution = loanAmountOnVPFContribution;
        this.year = year;
        this.month = month;
    }


    public LoanWithDrawalsFinalDetails(BigDecimal loanAmountOnMemContribution,
                                       BigDecimal loanAmountOnCompanyContribution,
                                       BigDecimal loanAmountOnVPFContribution,
                                       BigDecimal interestOnloanAmountOnMemContribution,
                                       BigDecimal interestOnloanAmountOnCompanyContribution,
                                       BigDecimal interestOnloanAmountOnVPFContribution) {
        this.loanAmountOnMemContribution = loanAmountOnMemContribution;
        this.loanAmountOnCompanyContribution = loanAmountOnCompanyContribution;
        this.loanAmountOnVPFContribution = loanAmountOnVPFContribution;
        this.interestOnloanAmountOnMemContribution = interestOnloanAmountOnMemContribution;
        this.interestOnloanAmountOnCompanyContribution = interestOnloanAmountOnCompanyContribution;
        this.interestOnloanAmountOnVPFContribution = interestOnloanAmountOnVPFContribution;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public BigDecimal getLoanAmountOnMemContribution() {
        return loanAmountOnMemContribution != null ? loanAmountOnMemContribution : new BigDecimal(0);
    }

    public void setLoanAmountOnMemContribution(BigDecimal loanAmountOnMemContribution) {
        this.loanAmountOnMemContribution = loanAmountOnMemContribution;
    }

    public BigDecimal getLoanAmountOnCompanyContribution() {
        return loanAmountOnCompanyContribution != null ? loanAmountOnCompanyContribution  : new BigDecimal(0);
    }

    public void setLoanAmountOnCompanyContribution(BigDecimal loanAmountOnCompanyContribution) {
        this.loanAmountOnCompanyContribution = loanAmountOnCompanyContribution;
    }

    public BigDecimal getLoanAmountOnVPFContribution() {
        return loanAmountOnVPFContribution != null ? loanAmountOnVPFContribution  : new BigDecimal(0);
    }

    public void setLoanAmountOnVPFContribution(BigDecimal loanAmountOnVPFContribution) {
        this.loanAmountOnVPFContribution = loanAmountOnVPFContribution;
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

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public BigDecimal getMemberContributionYearOpening() {
        return memberContributionYearOpening != null ? memberContributionYearOpening  : new BigDecimal(0);
    }

    public void setMemberContributionYearOpening(BigDecimal memberContributionYearOpening) {
        this.memberContributionYearOpening = memberContributionYearOpening;
    }

    public BigDecimal getCompanyContributionYearOpening() {
        return companyContributionYearOpening != null ? companyContributionYearOpening  : new BigDecimal(0);
    }

    public void setCompanyContributionYearOpening(BigDecimal companyContributionYearOpening) {
        this.companyContributionYearOpening = companyContributionYearOpening;
    }

    public BigDecimal getVpfContributionYearOpening() {
        return vpfContributionYearOpening != null ? vpfContributionYearOpening  : new BigDecimal(0);
    }

    public void setVpfContributionYearOpening(BigDecimal vpfContributionYearOpening) {
        this.vpfContributionYearOpening = vpfContributionYearOpening;
    }

    public BigDecimal getTotalContributionYearOpening() {
        return totalContributionYearOpening != null ? totalContributionYearOpening  : new BigDecimal(0);
    }

    public void setTotalContributionYearOpening(BigDecimal totalContributionYearOpening) {
        this.totalContributionYearOpening = totalContributionYearOpening;
    }

    public BigDecimal getMemberContributionCurrentYear() {
        return memberContributionCurrentYear != null ? memberContributionCurrentYear  : new BigDecimal(0);
    }

    public void setMemberContributionCurrentYear(BigDecimal memberContributionCurrentYear) {
        this.memberContributionCurrentYear = memberContributionCurrentYear;
    }

    public BigDecimal getCompanyContributionCurrentYear() {
        return companyContributionCurrentYear != null ? companyContributionCurrentYear : new BigDecimal(0) ;
    }

    public void setCompanyContributionCurrentYear(BigDecimal companyContributionCurrentYear) {
        this.companyContributionCurrentYear = companyContributionCurrentYear;
    }

    public BigDecimal getVpfContributionCurrentYear() {
        return vpfContributionCurrentYear != null ? vpfContributionCurrentYear : new BigDecimal(0);
    }

    public void setVpfContributionCurrentYear(BigDecimal vpfContributionCurrentYear) {
        this.vpfContributionCurrentYear = vpfContributionCurrentYear;
    }

    public BigDecimal getTotalContributionCurrentYear() {
        return totalContributionCurrentYear != null ? totalContributionCurrentYear  : new BigDecimal(0);
    }

    public void setTotalContributionCurrentYear(BigDecimal totalContributionCurrentYear) {
        this.totalContributionCurrentYear = totalContributionCurrentYear;
    }

    public BigDecimal getMemberContributionCurrentYearTi() {
        return memberContributionCurrentYearTi != null ? memberContributionCurrentYearTi : new BigDecimal(0);
    }

    public void setMemberContributionCurrentYearTi(BigDecimal memberContributionCurrentYearTi) {
        this.memberContributionCurrentYearTi = memberContributionCurrentYearTi;
    }

    public BigDecimal getCompanyContributionCurrentYearTi() {
        return companyContributionCurrentYearTi != null ? companyContributionCurrentYearTi : new BigDecimal(0);
    }

    public void setCompanyContributionCurrentYearTi(BigDecimal companyContributionCurrentYearTi) {
        this.companyContributionCurrentYearTi = companyContributionCurrentYearTi;
    }

    public BigDecimal getVpfContributionCurrentYearTi() {
        return vpfContributionCurrentYearTi != null ? vpfContributionCurrentYearTi : new BigDecimal(0);
    }

    public void setVpfContributionCurrentYearTi(BigDecimal vpfContributionCurrentYearTi) {
        this.vpfContributionCurrentYearTi = vpfContributionCurrentYearTi;
    }

    public BigDecimal getTotalContributionCurrentYearTi() {
        return totalContributionCurrentYearTi != null ? totalContributionCurrentYearTi : new BigDecimal(0);
    }

    public void setTotalContributionCurrentYearTi(BigDecimal totalContributionCurrentYearTi) {
        this.totalContributionCurrentYearTi = totalContributionCurrentYearTi;
    }

    public BigDecimal getCurrentYearMemberLoanWithDrawal() {
        return currentYearMemberLoanWithDrawal != null ? currentYearMemberLoanWithDrawal : new BigDecimal(0);
    }

    public void setCurrentYearMemberLoanWithDrawal(BigDecimal currentYearMemberLoanWithDrawal) {
        this.currentYearMemberLoanWithDrawal = currentYearMemberLoanWithDrawal;
    }

    public BigDecimal getCurrentYearCompanyLoanWithDrawal() {
        return currentYearCompanyLoanWithDrawal != null ? currentYearCompanyLoanWithDrawal : new BigDecimal(0);
    }

    public void setCurrentYearCompanyLoanWithDrawal(BigDecimal currentYearCompanyLoanWithDrawal) {
        this.currentYearCompanyLoanWithDrawal = currentYearCompanyLoanWithDrawal;
    }

    public BigDecimal getCurrentYearVpfLoanWithDrawal() {
        return currentYearVpfLoanWithDrawal != null ? currentYearVpfLoanWithDrawal : new BigDecimal(0);
    }

    public void setCurrentYearVpfLoanWithDrawal(BigDecimal currentYearVpfLoanWithDrawal) {
        this.currentYearVpfLoanWithDrawal = currentYearVpfLoanWithDrawal;
    }

    public BigDecimal getCurrentYearTotalLoanWithDrawal() {
        return currentYearTotalLoanWithDrawal != null ? currentYearTotalLoanWithDrawal : new BigDecimal(0);
    }

    public void setCurrentYearTotalLoanWithDrawal(BigDecimal currentYearTotalLoanWithDrawal) {
        this.currentYearTotalLoanWithDrawal = currentYearTotalLoanWithDrawal;
    }



    public BigDecimal getTotalMemberContrbution(){
        return this.getMemberContributionYearOpening()
                .add(this.getMemberContributionCurrentYear())
                .add(this.getMemberContributionCurrentYearTi())
                .subtract(this.getCurrentYearMemberLoanWithDrawal());
    }


    public BigDecimal getTotalCompanyContrbution(){
        return this.getCompanyContributionYearOpening()
                .add(this.getCompanyContributionCurrentYear())
                .add(this.getCompanyContributionCurrentYearTi())
                .subtract(this.getCurrentYearCompanyLoanWithDrawal());
    }


    public BigDecimal getTotalVpfContrbution(){
        return this.getVpfContributionYearOpening()
                .add(this.getVpfContributionCurrentYear())
                .add(this.getVpfContributionCurrentYearTi())
                .subtract(this.getCurrentYearVpfLoanWithDrawal());
    }


    public BigDecimal getTotal(){
        return this.getTotalMemberContrbution()
                .add(this.getTotalCompanyContrbution())
                .add(this.getTotalVpfContrbution());
    }


    public BigDecimal getTotalLoanWithDrawal(){
        return this.getLoanAmountOnMemContribution()
                .add(this.getLoanAmountOnCompanyContribution())
                .add(this.getLoanAmountOnVPFContribution());
    }

    public BigDecimal getTotalInterestLoanWithDrawal(){
        return this.getInterestOnloanAmountOnMemContribution()
                .add(this.getInterestOnloanAmountOnCompanyContribution())
                .add(this.getInterestOnloanAmountOnVPFContribution());
    }


    public BigDecimal getTotalOwnAccountLoanWithDrawal(){
        return this.getLoanAmountOnMemContribution()
                .add(this.getLoanAmountOnCompanyContribution());
    }



    public BigDecimal getInterestOnloanAmountOnMemContribution() {
        return interestOnloanAmountOnMemContribution != null ? interestOnloanAmountOnMemContribution : new BigDecimal(0);
    }

    public void setInterestOnloanAmountOnMemContribution(BigDecimal interestOnloanAmountOnMemContribution) {
        this.interestOnloanAmountOnMemContribution = interestOnloanAmountOnMemContribution;
    }

    public BigDecimal getInterestOnloanAmountOnCompanyContribution() {
        return interestOnloanAmountOnCompanyContribution != null ? interestOnloanAmountOnCompanyContribution : new BigDecimal(0);
    }

    public void setInterestOnloanAmountOnCompanyContribution(BigDecimal interestOnloanAmountOnCompanyContribution) {
        this.interestOnloanAmountOnCompanyContribution = interestOnloanAmountOnCompanyContribution;
    }

    public BigDecimal getInterestOnloanAmountOnVPFContribution() {
        return interestOnloanAmountOnVPFContribution != null ? interestOnloanAmountOnVPFContribution : new BigDecimal(0);
    }

    public void setInterestOnloanAmountOnVPFContribution(BigDecimal interestOnloanAmountOnVPFContribution) {
        this.interestOnloanAmountOnVPFContribution = interestOnloanAmountOnVPFContribution;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(BigDecimal monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public Integer getInterestPeriod() {
        return interestPeriod;
    }

    public void setInterestPeriod(Integer interestPeriod) {
        this.interestPeriod = interestPeriod;
    }

    public BigDecimal getLoanAmountOnTaxableMemberContribution() {
        return loanAmountOnTaxableMemberContribution;
    }

    public void setLoanAmountOnTaxableMemberContribution(BigDecimal loanAmountOnTaxableMemberContribution) {
        this.loanAmountOnTaxableMemberContribution = loanAmountOnTaxableMemberContribution;
    }

    public BigDecimal getLoanAmountOnNonTaxableMemberContribution() {
        return loanAmountOnNonTaxableMemberContribution;
    }

    public void setLoanAmountOnNonTaxableMemberContribution(BigDecimal loanAmountOnNonTaxableMemberContribution) {
        this.loanAmountOnNonTaxableMemberContribution = loanAmountOnNonTaxableMemberContribution;
    }

    public BigDecimal getLoanAmountOnTaxableVpfContribution() {
        return loanAmountOnTaxableVpfContribution;
    }

    public void setLoanAmountOnTaxableVpfContribution(BigDecimal loanAmountOnTaxableVpfContribution) {
        this.loanAmountOnTaxableVpfContribution = loanAmountOnTaxableVpfContribution;
    }

    public BigDecimal getLoanAmountOnNonTaxableVpfContribution() {
        return loanAmountOnNonTaxableVpfContribution;
    }

    public void setLoanAmountOnNonTaxableVpfContribution(BigDecimal loanAmountOnNonTaxableVpfContribution) {
        this.loanAmountOnNonTaxableVpfContribution = loanAmountOnNonTaxableVpfContribution;
    }

    public BigDecimal getInterestOnLoanAmountOnTaxableMemberContribution() {
        return interestOnLoanAmountOnTaxableMemberContribution;
    }

    public void setInterestOnLoanAmountOnTaxableMemberContribution(BigDecimal interestOnLoanAmountOnTaxableMemberContribution) {
        this.interestOnLoanAmountOnTaxableMemberContribution = interestOnLoanAmountOnTaxableMemberContribution;
    }

    public BigDecimal getInterestOnLoanAmountOnNonTaxableMemberContribution() {
        return interestOnLoanAmountOnNonTaxableMemberContribution;
    }

    public void setInterestOnLoanAmountOnNonTaxableMemberContribution(BigDecimal interestOnLoanAmountOnNonTaxableMemberContribution) {
        this.interestOnLoanAmountOnNonTaxableMemberContribution = interestOnLoanAmountOnNonTaxableMemberContribution;
    }

    public BigDecimal getInterestOnLoanAmountOnTaxableVpfContribution() {
        return interestOnLoanAmountOnTaxableVpfContribution;
    }

    public void setInterestOnLoanAmountOnTaxableVpfContribution(BigDecimal interestOnLoanAmountOnTaxableVpfContribution) {
        this.interestOnLoanAmountOnTaxableVpfContribution = interestOnLoanAmountOnTaxableVpfContribution;
    }

    public BigDecimal getInterestOnLoanAmountOnNonTaxableVpfContribution() {
        return interestOnLoanAmountOnNonTaxableVpfContribution;
    }

    public void setInterestOnLoanAmountOnNonTaxableVpfContribution(BigDecimal interestOnLoanAmountOnNonTaxableVpfContribution) {
        this.interestOnLoanAmountOnNonTaxableVpfContribution = interestOnLoanAmountOnNonTaxableVpfContribution;
    }
}
