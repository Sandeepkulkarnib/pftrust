package com.coreintegra.pftrust.entities.pf.hpc;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class HpcInterestRates extends BaseAuditingEntity {

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;
	
	@PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }
	
	private String fiscalYear;
	private Integer month;
	private Integer actualYear;
	private BigDecimal interestRate;

	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getActualYear() {
		return actualYear;
	}
	public void setActualYear(Integer actualYear) {
		this.actualYear = actualYear;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

}
