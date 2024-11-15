package com.coreintegra.pftrust.entities.pf.loan;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class LoanAmount extends BaseAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private Integer pfSalary;
    private Double totalPfBalance;
    private Double totalCost;
    private Double appliedAmount;
    private Double ownAccountPfBalance;

    @OneToOne
    @JsonIgnore
    private LoanType loanType;

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

    public Integer getPfSalary() {
        return pfSalary;
    }

    public void setPfSalary(Integer pfSalary) {
        this.pfSalary = pfSalary;
    }

    public Double getTotalPfBalance() {
        return totalPfBalance;
    }

    public void setTotalPfBalance(Double totalPfBalance) {
        this.totalPfBalance = totalPfBalance;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(Double appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public Double getOwnAccountPfBalance() {
        return ownAccountPfBalance;
    }

    public void setOwnAccountPfBalance(Double ownAccountPfBalance) {
        this.ownAccountPfBalance = ownAccountPfBalance;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

}
