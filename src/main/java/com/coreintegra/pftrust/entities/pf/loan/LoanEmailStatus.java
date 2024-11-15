package com.coreintegra.pftrust.entities.pf.loan;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
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
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class LoanEmailStatus extends BaseAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private String emailId;
    private String name;
    private String unitCode;
    private String pfNumber;
    private String pernNumber;
    private String accountNumber;
    private String employeeBank;
    private Date paymentDate;
    private BigDecimal netCredit;

    public LoanEmailStatus(String emailId, String name, String unitCode, String pfNumber, String pernNumber,
                           String accountNumber, String employeeBank, Date paymentDate, BigDecimal netCredit,
                           Loan loan, Boolean isSent) {
        this.emailId = emailId;
        this.name = name;
        this.unitCode = unitCode;
        this.pfNumber = pfNumber;
        this.pernNumber = pernNumber;
        this.accountNumber = accountNumber;
        this.employeeBank = employeeBank;
        this.paymentDate = paymentDate;
        this.netCredit = netCredit;
        this.loan = loan;
        this.isSent = isSent;
    }

    @ManyToOne
    @JsonIgnore
    private Loan loan;

    private Boolean isSent;

    public LoanEmailStatus() {

    }

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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmployeeBank() {
        return employeeBank;
    }

    public void setEmployeeBank(String employeeBank) {
        this.employeeBank = employeeBank;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getNetCredit() {
        return netCredit;
    }

    public void setNetCredit(BigDecimal netCredit) {
        this.netCredit = netCredit;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }
}
