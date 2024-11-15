package com.coreintegra.pftrust.entities.pf.yearend;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.department.Job;
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
public class ClosingBalance extends BaseAuditingEntity {

        private BigDecimal nonTaxableMemberContribution;
        private BigDecimal taxableMemberContribution;

        private BigDecimal interestNonTaxableMemberContribution;
        private BigDecimal interestTaxableMemberContribution;
        private BigDecimal tdsOnTaxableMemberContribution;

        private BigDecimal companyContribution;

        private BigDecimal nonTaxableVpfContribution;
        private BigDecimal taxableVpfContribution;

        private BigDecimal interestNonTaxableVpfContribution;
        private BigDecimal interestTaxableVpfContribution;
        private BigDecimal tdsOnTaxableVpfContribution;

        private Integer year;

        @ManyToOne
        private Employee employee;

        @ManyToOne
        private Job job;

        private Boolean isPublished;

        @ManyToOne
        private TdsDeduction tdsDeduction;

        @ManyToOne
        @JoinColumn(name = "tenant_id")
        @JsonIgnore
        private Tenant tenant;

        @OneToOne
        private YearEndProcess yearEndProcess;

        @PrePersist
        public void prePersist(){
                tenant = TenantContext.getCurrentTenant();
                setIsActive(true);
                setEntityId(UUID.randomUUID().toString());
        }

        public ClosingBalance() {
        }

        public ClosingBalance(BigDecimal nonTaxableMemberContribution,
                              BigDecimal taxableMemberContribution,
                              BigDecimal interestNonTaxableMemberContribution,
                              BigDecimal interestTaxableMemberContribution,
                              BigDecimal tdsOnTaxableMemberContribution,
                              BigDecimal companyContribution,
                              BigDecimal nonTaxableVpfContribution,
                              BigDecimal taxableVpfContribution,
                              BigDecimal interestNonTaxableVpfContribution,
                              BigDecimal interestTaxableVpfContribution,
                              BigDecimal tdsOnTaxableVpfContribution,
                              Integer year,
                              Employee employee,
                              Job job,
                              Boolean isPublished,
                              TdsDeduction tdsDeduction,
                              YearEndProcess yearEndProcess) {
                this.nonTaxableMemberContribution = nonTaxableMemberContribution;
                this.taxableMemberContribution = taxableMemberContribution;
                this.interestNonTaxableMemberContribution = interestNonTaxableMemberContribution;
                this.interestTaxableMemberContribution = interestTaxableMemberContribution;
                this.tdsOnTaxableMemberContribution = tdsOnTaxableMemberContribution;
                this.companyContribution = companyContribution;
                this.nonTaxableVpfContribution = nonTaxableVpfContribution;
                this.taxableVpfContribution = taxableVpfContribution;
                this.interestNonTaxableVpfContribution = interestNonTaxableVpfContribution;
                this.interestTaxableVpfContribution = interestTaxableVpfContribution;
                this.tdsOnTaxableVpfContribution = tdsOnTaxableVpfContribution;
                this.year = year;
                this.employee = employee;
                this.job = job;
                this.isPublished = isPublished;
                this.tdsDeduction = tdsDeduction;
                this.yearEndProcess = yearEndProcess;
        }

        public BigDecimal getNonTaxableMemberContribution() {
                return nonTaxableMemberContribution;
        }

        public void setNonTaxableMemberContribution(BigDecimal nonTaxableMemberContribution) {
                this.nonTaxableMemberContribution = nonTaxableMemberContribution;
        }

        public BigDecimal getTaxableMemberContribution() {
                return taxableMemberContribution;
        }

        public void setTaxableMemberContribution(BigDecimal taxableMemberContribution) {
                this.taxableMemberContribution = taxableMemberContribution;
        }

        public BigDecimal getTdsOnTaxableMemberContribution() {
                return tdsOnTaxableMemberContribution;
        }

        public void setTdsOnTaxableMemberContribution(BigDecimal tdsOnTaxableMemberContribution) {
                this.tdsOnTaxableMemberContribution = tdsOnTaxableMemberContribution;
        }

        public BigDecimal getCompanyContribution() {
                return companyContribution;
        }

        public void setCompanyContribution(BigDecimal companyContribution) {
                this.companyContribution = companyContribution;
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

        public BigDecimal getTdsOnTaxableVpfContribution() {
                return tdsOnTaxableVpfContribution;
        }

        public void setTdsOnTaxableVpfContribution(BigDecimal tdsOnVpfContribution) {
                this.tdsOnTaxableVpfContribution = tdsOnVpfContribution;
        }

        public Integer getYear() {
                return year;
        }

        public void setYear(Integer year) {
                this.year = year;
        }

        public Employee getEmployee() {
                return employee;
        }

        public void setEmployee(Employee employee) {
                this.employee = employee;
        }

        public Job getJob() {
                return job;
        }

        public void setJob(Job job) {
                this.job = job;
        }

        public Boolean getPublished() {
                return isPublished;
        }

        public void setPublished(Boolean published) {
                isPublished = published;
        }

        public Tenant getTenant() {
                return tenant;
        }

        public void setTenant(Tenant tenant) {
                this.tenant = tenant;
        }

        public BigDecimal getInterestNonTaxableMemberContribution() {
                return interestNonTaxableMemberContribution;
        }

        public void setInterestNonTaxableMemberContribution(BigDecimal interestNonTaxableMemberContribution) {
                this.interestNonTaxableMemberContribution = interestNonTaxableMemberContribution;
        }

        public BigDecimal getInterestTaxableMemberContribution() {
                return interestTaxableMemberContribution;
        }

        public void setInterestTaxableMemberContribution(BigDecimal interestTaxableMemberContribution) {
                this.interestTaxableMemberContribution = interestTaxableMemberContribution;
        }

        public BigDecimal getInterestNonTaxableVpfContribution() {
                return interestNonTaxableVpfContribution;
        }

        public void setInterestNonTaxableVpfContribution(BigDecimal interestNonTaxableVpfContribution) {
                this.interestNonTaxableVpfContribution = interestNonTaxableVpfContribution;
        }

        public BigDecimal getInterestTaxableVpfContribution() {
                return interestTaxableVpfContribution;
        }

        public void setInterestTaxableVpfContribution(BigDecimal interestTaxableVpfContribution) {
                this.interestTaxableVpfContribution = interestTaxableVpfContribution;
        }

        public TdsDeduction getTdsDeduction() {
                return tdsDeduction;
        }

        public void setTdsDeduction(TdsDeduction tdsDeduction) {
                this.tdsDeduction = tdsDeduction;
        }

        public YearEndProcess getYearEndProcess() {
                return yearEndProcess;
        }

        public void setYearEndProcess(YearEndProcess yearEndProcess) {
                this.yearEndProcess = yearEndProcess;
        }
}
