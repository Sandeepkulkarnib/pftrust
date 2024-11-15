package com.coreintegra.pftrust.repositories.tenant;

import com.coreintegra.pftrust.entities.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findByTenantIdAndIsActive(String tenantId, Boolean isActive);

    Optional<Tenant> findByNameAndIsActive(String name, Boolean isActive);

}
