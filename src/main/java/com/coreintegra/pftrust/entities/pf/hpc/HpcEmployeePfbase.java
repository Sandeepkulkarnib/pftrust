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
public class HpcEmployeePfbase extends BaseAuditingEntity{

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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private HpcEmployeeMaster hpcEmployeeMaster;
	
	private String employeeId;
	private String wageType;
	private String wageDescription;
	private Integer month;
	private Integer year;
	private BigDecimal pfBase;
	private String fiscalYear;

	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public HpcEmployeeMaster getHpcEmployeeMaster() {
		return hpcEmployeeMaster;
	}
	public void setHpcEmployeeMaster(HpcEmployeeMaster hpcEmployeeMaster) {
		this.hpcEmployeeMaster = hpcEmployeeMaster;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getWageType() {
		return wageType;
	}
	public void setWageType(String wageType) {
		this.wageType = wageType;
	}
	public String getWageDescription() {
		return wageDescription;
	}
	public void setWageDescription(String wageDescription) {
		this.wageDescription = wageDescription;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public BigDecimal getPfBase() {
		return pfBase;
	}
	public void setPfBase(BigDecimal pfBase) {
		this.pfBase = pfBase;
	}
	
}
