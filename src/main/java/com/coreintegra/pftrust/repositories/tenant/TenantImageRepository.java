package com.coreintegra.pftrust.repositories.tenant;

import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.entities.tenant.TenantImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantImageRepository extends JpaRepository<TenantImage, Long> {

    List<TenantImage> getAllByTenant(Tenant tenant);

}
