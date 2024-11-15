package com.coreintegra.pftrust.entities.administration;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserTenantMapping extends BaseAuditingEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Tenant tenant;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
