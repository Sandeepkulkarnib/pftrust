package com.coreintegra.pftrust.entities.administration;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserTenantPfDepartmentRoleMapping extends BaseAuditingEntity {

    @ManyToOne
    private PfDepartmentRoleGroup pfDepartmentRoleGroup;

    @ManyToOne
    private Tenant tenant;

    @ManyToOne
    private User user;

    public PfDepartmentRoleGroup getPfDepartmentRoleGroup() {
        return pfDepartmentRoleGroup;
    }

    public void setPfDepartmentRoleGroup(PfDepartmentRoleGroup pfDepartmentRoleGroup) {
        this.pfDepartmentRoleGroup = pfDepartmentRoleGroup;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
