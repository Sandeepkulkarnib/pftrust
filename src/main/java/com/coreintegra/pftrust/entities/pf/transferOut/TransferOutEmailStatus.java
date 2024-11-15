package com.coreintegra.pftrust.entities.pf.transferOut;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
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
public class TransferOutEmailStatus extends BaseAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private String fromPfAccount;
    private String toPfAccount;
    private String employeeName;
    private String unitCode;
    private Date paymentDate;
    private BigDecimal totalContribution;
    private String pfTrustName;
    private String currentEmployerName;
    private String emailId;

    public TransferOutEmailStatus() {
    }

    public TransferOutEmailStatus(String fromPfAccount, String toPfAccount, String employeeName, String unitCode,
                                  Date paymentDate, BigDecimal totalContribution, String pfTrustName,
                                  String currentEmployerName, String emailId, TransferOut transferOut,
                                  Boolean isSent) {
        this.fromPfAccount = fromPfAccount;
        this.toPfAccount = toPfAccount;
        this.employeeName = employeeName;
        this.unitCode = unitCode;
        this.paymentDate = paymentDate;
        this.totalContribution = totalContribution;
        this.pfTrustName = pfTrustName;
        this.currentEmployerName = currentEmployerName;
        this.emailId = emailId;
        this.transferOut = transferOut;
        this.isSent = isSent;
    }

    @ManyToOne
    @JsonIgnore
    private TransferOut transferOut;

    private Boolean isSent;

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

    public String getFromPfAccount() {
        return fromPfAccount;
    }

    public void setFromPfAccount(String fromPfAccount) {
        this.fromPfAccount = fromPfAccount;
    }

    public String getToPfAccount() {
        return toPfAccount;
    }

    public void setToPfAccount(String toPfAccount) {
        this.toPfAccount = toPfAccount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalContribution() {
        return totalContribution;
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    public String getPfTrustName() {
        return pfTrustName;
    }

    public void setPfTrustName(String pfTrustName) {
        this.pfTrustName = pfTrustName;
    }

    public String getCurrentEmployerName() {
        return currentEmployerName;
    }

    public void setCurrentEmployerName(String currentEmployerName) {
        this.currentEmployerName = currentEmployerName;
    }

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
