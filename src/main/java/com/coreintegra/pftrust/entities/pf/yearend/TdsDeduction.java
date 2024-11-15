package com.coreintegra.pftrust.entities.pf.yearend;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
public class TdsDeduction extends BaseAuditingEntity {

    private BigDecimal tdsToDeduct;
    private BigDecimal minimumLimit;

    private Integer year;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    public TdsDeduction() {
    }

    public TdsDeduction(BigDecimal tdsToDeduct, BigDecimal minimumLimit, Integer year) {
        this.tdsToDeduct = tdsToDeduct;
        this.minimumLimit = minimumLimit;
        this.year = year;
    }

    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public BigDecimal getTdsToDeduct() {
        return tdsToDeduct;
    }

    public void setTdsToDeduct(BigDecimal tdsToDeduct) {
        this.tdsToDeduct = tdsToDeduct;
    }

    public BigDecimal getMinimumLimit() {
        return minimumLimit;
    }

    public void setMinimumLimit(BigDecimal minimumLimit) {
        this.minimumLimit = minimumLimit;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }


    public BigDecimal calculate(BigDecimal value){
        return value.multiply(this.tdsToDeduct).divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_EVEN);
    }

}
