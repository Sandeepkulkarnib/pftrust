package com.coreintegra.pftrust.entities.pf.employee;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class PreviousCompany extends BaseAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private String name;

    private Date dateOfJoining;
    private Date dateOfLeaving;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String pinCode;

    @ManyToOne
    private Employee employee;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Date getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(Date dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PreviousCompany set(JSONObject jsonObject) throws ParseException {
        this.setName(jsonObject.getString("companyName"));
        this.setDateOfJoining(FinancialYearAndMonth.getDate(jsonObject.getString("companyDateOfJoining")));
        this.setDateOfLeaving(FinancialYearAndMonth.getDate(jsonObject.getString("companyDateOfLeaving")));
        this.setAddressLine1(jsonObject.getString("addressLine1"));
        this.setAddressLine2(jsonObject.getString("addressLine2"));
        this.setAddressLine3(jsonObject.getString("addressLine3"));
        this.setAddressLine4(jsonObject.getString("addressLine4"));

        return this;
    }

    public static PreviousCompany build(JSONObject jsonObject) throws ParseException {

        PreviousCompany previousCompany = new PreviousCompany();

        return previousCompany.set(jsonObject);
    }


}
