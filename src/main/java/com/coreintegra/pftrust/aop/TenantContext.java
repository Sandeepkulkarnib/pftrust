package com.coreintegra.pftrust.aop;

import com.coreintegra.pftrust.entities.tenant.Tenant;

public class TenantContext {

    private static ThreadLocal<Tenant> currentTenant = new InheritableThreadLocal<>();
    private static ThreadLocal<Long> currentTenantId = new InheritableThreadLocal<>();

    public static Tenant getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(Tenant tenant) {
        currentTenant.set(tenant);
        currentTenantId.set(tenant.getId());
    }

    public static Long getCurrentTenantId() {
        return currentTenantId.get();
    }

    public static void setCurrentTenantId(Long currentTenantId) {
        TenantContext.currentTenantId.set(currentTenantId);
    }

    public static void clear() {
        currentTenant.set(null);
        currentTenantId.set(null);
    }

}
