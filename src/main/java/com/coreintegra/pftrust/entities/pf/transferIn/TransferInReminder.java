package com.coreintegra.pftrust.entities.pf.transferIn;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class TransferInReminder extends BaseAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    @Nullable
    @OneToOne
    @XmlTransient
    private TransferInReminder reminder;

    @ManyToOne
    @XmlTransient
    private TransferIn transferIn;

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

    public TransferIn getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(TransferIn transferIn) {
        this.transferIn = transferIn;
    }

    @Nullable
    public TransferInReminder getReminder() {
        return reminder;
    }

    public void setReminder(@Nullable TransferInReminder reminder) {
        this.reminder = reminder;
    }
}
