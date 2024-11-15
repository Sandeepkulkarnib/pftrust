package com.coreintegra.pftrust.entities.tenant.dtos;

import java.util.Date;

public class UserTenant {

    private String name;
    private String tenantId;

    public UserTenant(String name, String tenantId) {
        this.name = name;
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

}
