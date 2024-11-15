package com.coreintegra.pftrust.entities.pf.transferIn;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
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
public class TransferInFinalDetails extends BaseAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal vpfContribution = new BigDecimal(0);

    private Integer year;
    private Integer contributedIn;

    private BigDecimal interestOnMemberContribution;
    private BigDecimal interestOnCompanyContribution;
    private BigDecimal interestOnVpfContribution;

    private BigDecimal interestRate;
    private BigDecimal monthlyInterestRate;
    private Integer interestPeriod;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private TransferIn transferIn;

    private BigDecimal taxableMemberContribution = BigDecimal.ZERO;
    private BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableVpfContribution = BigDecimal.ZERO;

    private BigDecimal interestTaxableMemberContribution = BigDecimal.ZERO;
    private BigDecimal interestNonTaxableMemberContribution = BigDecimal.ZERO;

    private BigDecimal interestTaxableVpfContribution = BigDecimal.ZERO;
    private BigDecimal interestNonTaxableVpfContribution = BigDecimal.ZERO;

    private String transferInCreatedBy;

    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public TransferInFinalDetails() {
    }

    public TransferInFinalDetails(BigDecimal memberContribution, BigDecimal companyContribution, BigDecimal vpfContribution) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
    }

    public TransferInFinalDetails(BigDecimal memberContribution, BigDecimal companyContribution,
                                  BigDecimal vpfContribution, Integer year, Integer contributedIn) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.year = year;
        this.contributedIn = contributedIn;
    }

    public TransferInFinalDetails(BigDecimal memberContribution, BigDecimal companyContribution,
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

    public TransferInFinalDetails(BigDecimal memberContribution, BigDecimal companyContribution,
                                  BigDecimal vpfContribution, Integer year, Integer contributedIn,
                                  BigDecimal interestOnMemberContribution,
                                  BigDecimal interestOnCompanyContribution,
                                  BigDecimal interestOnVpfContribution) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.year = year;
        this.contributedIn = contributedIn;
        this.interestOnMemberContribution = interestOnMemberContribution;
        this.interestOnCompanyContribution = interestOnCompanyContribution;
        this.interestOnVpfContribution = interestOnVpfContribution;
    }

    public TransferInFinalDetails(BigDecimal nonTaxableMemberContribution,
                                  BigDecimal taxableMemberContribution,
                                  BigDecimal memberContribution,
                                  BigDecimal companyContribution,
                                  BigDecimal nonTaxableVpfContribution,
                                  BigDecimal taxableVpfContribution,
                                  BigDecimal vpfContribution,
                                  Integer year,
                                  Integer contributedIn,
                                  BigDecimal interestNonTaxableMemberContribution,
                                  BigDecimal interestTaxableMemberContribution,
                                  BigDecimal interestOnMemberContribution,
                                  BigDecimal interestOnCompanyContribution,
                                  BigDecimal interestNonTaxableVpfContribution,
                                  BigDecimal interestTaxableVpfContribution,
                                  BigDecimal interestOnVpfContribution) {

        this.taxableMemberContribution = taxableMemberContribution;
        this.nonTaxableMemberContribution = nonTaxableMemberContribution;
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.taxableVpfContribution = taxableVpfContribution;
        this.nonTaxableVpfContribution = nonTaxableVpfContribution;
        this.vpfContribution = vpfContribution;
        this.year = year;
        this.contributedIn = contributedIn;
        this.interestNonTaxableMemberContribution = interestNonTaxableMemberContribution;
        this.interestTaxableMemberContribution = interestTaxableMemberContribution;
        this.interestOnMemberContribution = interestOnMemberContribution;
        this.interestOnCompanyContribution = interestOnCompanyContribution;
        this.interestNonTaxableVpfContribution = interestNonTaxableVpfContribution;
        this.interestTaxableVpfContribution = interestTaxableVpfContribution;
        this.interestOnVpfContribution = interestOnVpfContribution;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
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

    public TransferIn getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(TransferIn transferIn) {
        this.transferIn = transferIn;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getTransferInCreatedBy() {
        return transferInCreatedBy;
    }

    public void setTransferInCreatedBy(String createdBy) {
        this.transferInCreatedBy = createdBy;
    }

    public BigDecimal getTotal(){

        BigDecimal bigDecimal = new BigDecimal(0);

        return bigDecimal.add(this.getMemberContribution() == null ? BigDecimal.ZERO : this.memberContribution)
                .add(this.getCompanyContribution() == null ? BigDecimal.ZERO : this.companyContribution)
                .add(this.getVpfContribution() == null ? BigDecimal.ZERO : this.vpfContribution);

    }


    public BigDecimal getTotalInterest(){

        BigDecimal bigDecimal = new BigDecimal(0);

        return bigDecimal.add(this.getInterestOnMemberContribution() != null ? this.getInterestOnMemberContribution() : new BigDecimal(0))
                .add(this.getInterestOnCompanyContribution() != null ? this.getInterestOnCompanyContribution() : new BigDecimal(0))
                .add(this.getInterestOnVpfContribution() != null ? this.getInterestOnVpfContribution() : new BigDecimal(0));


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

    public BigDecimal getTaxableMemberContribution() {
        return taxableMemberContribution;
    }

    public void setTaxableMemberContribution(BigDecimal taxableMemberContribution) {
        this.taxableMemberContribution = taxableMemberContribution;
    }

    public BigDecimal getNonTaxableMemberContribution() {
        return nonTaxableMemberContribution;
    }

    public void setNonTaxableMemberContribution(BigDecimal nonTaxableMemberContribution) {
        this.nonTaxableMemberContribution = nonTaxableMemberContribution;
    }

    public BigDecimal getNonTaxableVpfContribution() {
        return nonTaxableVpfContribution;
    }

    public void setNonTaxableVpfContribution(BigDecimal nonTaxableVpfContribution) {
        this.nonTaxableVpfContribution = nonTaxableVpfContribution;
    }

    public BigDecimal getTaxableVpfContribution() {
        return taxableVpfContribution;
    }

    public void setTaxableVpfContribution(BigDecimal taxableVpfContribution) {
        this.taxableVpfContribution = taxableVpfContribution;
    }

    public BigDecimal getInterestTaxableMemberContribution() {
        return interestTaxableMemberContribution;
    }

    public void setInterestTaxableMemberContribution(BigDecimal interestTaxableMemberContribution) {
        this.interestTaxableMemberContribution = interestTaxableMemberContribution;
    }

    public BigDecimal getInterestNonTaxableMemberContribution() {
        return interestNonTaxableMemberContribution;
    }

    public void setInterestNonTaxableMemberContribution(BigDecimal interestNonTaxableMemberContribution) {
        this.interestNonTaxableMemberContribution = interestNonTaxableMemberContribution;
    }

    public BigDecimal getInterestTaxableVpfContribution() {
        return interestTaxableVpfContribution;
    }

    public void setInterestTaxableVpfContribution(BigDecimal interestTaxableVpfContribution) {
        this.interestTaxableVpfContribution = interestTaxableVpfContribution;
    }

    public BigDecimal getInterestNonTaxableVpfContribution() {
        return interestNonTaxableVpfContribution;
    }

    public void setInterestNonTaxableVpfContribution(BigDecimal interestNonTaxableVpfContribution) {
        this.interestNonTaxableVpfContribution = interestNonTaxableVpfContribution;
    }
}
