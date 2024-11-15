package com.coreintegra.pftrust.entities.pf.contribution;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"pernNumber", "year", "subType"}))
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Contribution extends BaseAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private String pernNumber;
    private String pfNumber;

    private Date recoveryDate;
    private BigDecimal pfBase;

    private Integer subType;

    private Date endDate;

    private Date startDate;

    private String unitCode;
    private Integer year;

    private BigDecimal memberContribution = BigDecimal.ZERO;
    private BigDecimal companyContribution = BigDecimal.ZERO;
    private BigDecimal vpfContribution = BigDecimal.ZERO;

    private BigDecimal interestMemContribution = BigDecimal.ZERO;
    private BigDecimal interestCompanyContribution = BigDecimal.ZERO;
    private BigDecimal vpfContributionInterest = BigDecimal.ZERO;

    private BigDecimal interestRate;
    private BigDecimal monthlyInterestRate;
    private Integer interestPeriod;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @OneToOne
    private SAPStatus sapStatus;

    private BigDecimal taxableMemberContribution = BigDecimal.ZERO;
    private BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableVpfContribution = BigDecimal.ZERO;

    private BigDecimal interestTaxableMemberContribution = BigDecimal.ZERO;
    private BigDecimal interestNonTaxableMemberContribution = BigDecimal.ZERO;

    private BigDecimal interestTaxableVpfContribution = BigDecimal.ZERO;
    private BigDecimal interestNonTaxableVpfContribution = BigDecimal.ZERO;

    public Contribution() {
    }

    public Contribution(BigDecimal memberContribution, BigDecimal companyContribution,
                        BigDecimal vpfContribution) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
    }

    public Contribution(Tenant tenant, Integer subType, Integer year, BigDecimal memberContribution,
                        BigDecimal companyContribution, BigDecimal vpfContribution, Employee employee) {
        this.tenant = tenant;
        this.subType = subType;
        this.year = year;
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
        this.employee = employee;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public Date getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    public BigDecimal getPfBase() {
        return pfBase;
    }

    public void setPfBase(BigDecimal pfBase) {
        this.pfBase = pfBase;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public BigDecimal getInterestMemContribution() {
        return interestMemContribution;
    }

    public void setInterestMemContribution(BigDecimal interestMemContribution) {
        this.interestMemContribution = interestMemContribution;
    }

    public BigDecimal getInterestCompanyContribution() {
        return interestCompanyContribution;
    }

    public void setInterestCompanyContribution(BigDecimal interestCompanyContribution) {
        this.interestCompanyContribution = interestCompanyContribution;
    }

    public BigDecimal getVpfContributionInterest() {
        return vpfContributionInterest;
    }

    public void setVpfContributionInterest(BigDecimal vpfContributionInterest) {
        this.vpfContributionInterest = vpfContributionInterest;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public SAPStatus getSapStatus() {
        return sapStatus;
    }

    public void setSapStatus(SAPStatus sapStatus) {
        this.sapStatus = sapStatus;
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

    public void setInterestPeriod(Integer period) {
        this.interestPeriod = period;
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

    public BigDecimal getTotal(){
        return (this.getMemberContribution() != null ? this.getMemberContribution() : new BigDecimal(0))
                .add(this.companyContribution != null ? this.companyContribution : new BigDecimal(0))
                .add(this.vpfContribution != null ? this.vpfContribution : new BigDecimal(0))
                .add(this.interestMemContribution != null ? this.interestMemContribution : new BigDecimal(0))
                .add(this.interestCompanyContribution != null ? this.interestCompanyContribution : new BigDecimal(0))
                .add(this.vpfContributionInterest != null ? this.vpfContributionInterest : new BigDecimal(0));

    }

    public BigDecimal getTotalContribution(){
        return (this.getMemberContribution() != null ? this.getMemberContribution() : new BigDecimal(0))
                .add(this.companyContribution != null ? this.companyContribution : new BigDecimal(0))
                .add(this.vpfContribution != null ? this.vpfContribution : new BigDecimal(0));
    }

    public BigDecimal getTotalInterest(){
        return (this.interestMemContribution != null ? this.interestMemContribution :
                new BigDecimal(0))
                .add(this.interestCompanyContribution != null ? this.interestCompanyContribution :
                        new BigDecimal(0))
                .add(this.vpfContributionInterest != null ? this.vpfContributionInterest :
                        new BigDecimal(0));
    }

}
