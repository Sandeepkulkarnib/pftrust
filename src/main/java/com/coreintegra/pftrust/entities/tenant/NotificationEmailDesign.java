package com.coreintegra.pftrust.entities.tenant;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class NotificationEmailDesign extends BaseAuditingEntity {

    public final static String USER_ACCOUNT_INVITATION_EMAIL = "USER_ACCOUNT_INVITATION_EMAIL";
    public final static String USER_ACCOUNT_DETAILS = "USER_ACCOUNT_DETAILS";
    public final static String LOAN_COMPLETION = "LOAN_COMPLETION";
    public final static String SETTLEMENT_COMPLETION = "SETTLEMENT_COMPLETION";
    public final static String TRANSFER_IN_COMPLETION = "TRANSFER_IN_COMPLETION";
    public final static String TRANSFER_OUT_COMPLETION = "TRANSFER_OUT_COMPLETION";

    public final static List<String> supportedTemplates = List.of(USER_ACCOUNT_INVITATION_EMAIL,
            USER_ACCOUNT_DETAILS, LOAN_COMPLETION, SETTLEMENT_COMPLETION, TRANSFER_IN_COMPLETION,
            TRANSFER_OUT_COMPLETION);

    @Column(columnDefinition = "LONGTEXT")
    private String document;

    private String documentType;

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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
