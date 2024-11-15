package com.coreintegra.pftrust.entities.tenant;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;
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
public class PdfDocumentDesign extends BaseAuditingEntity {

    public static final String ANNUXURE_K = "ANNUXURE_K";
    public static final String ANNUAL_STATEMENT = "ANNUAL_STATEMENT";
    public static final String LOAN_HISTORY = "LOAN_HISTORY";
    public static final String LOAN_RECEIPT = "LOAN_RECEIPT";
    public static final String LOAN_WORKSHEET = "LOAN_WORKSHEET";
    public static final String MONTHLY_STATEMENT = "MONTHLY_STATEMENT";
    public static final String SETTLEMENT_ANNEXURE = "SETTLEMENT_ANNEXURE";
    public static final String SETTLEMENT_DISPATCH_LETTER = "SETTLEMENT_DISPATCH_LETTER";
    public static final String SETTLEMENT_WORKSHEET = "SETTLEMENT_WORKSHEET";
    public static final String TRANSFER_IN_REQUEST_LETTER = "TRANSFER_IN_REQUEST_LETTER";
    public static final String TRANSFER_OUT_DISPATCH_LETTER = "TRANSFER_OUT_DISPATCH_LETTER";
    public static final String TRANSFER_OUT_WORK_SHEET = "TRANSFER_OUT_WORK_SHEET";

    public static final List<String> supportedTemplates = List.of(ANNUXURE_K, ANNUAL_STATEMENT, LOAN_HISTORY,
            LOAN_RECEIPT, LOAN_WORKSHEET, MONTHLY_STATEMENT, SETTLEMENT_ANNEXURE,
            SETTLEMENT_WORKSHEET, SETTLEMENT_DISPATCH_LETTER, TRANSFER_IN_REQUEST_LETTER,
            TRANSFER_OUT_DISPATCH_LETTER, TRANSFER_OUT_WORK_SHEET);

    @Column(columnDefinition = "LONGTEXT")
    private String document;

    private String documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private Integer year;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
