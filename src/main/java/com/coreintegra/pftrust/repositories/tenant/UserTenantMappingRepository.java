package com.coreintegra.pftrust.repositories.tenant;

import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.entities.administration.UserTenantMapping;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTenantMappingRepository extends JpaRepository<UserTenantMapping, Long> {

    List<UserTenantMapping> getUserTenantMappingByUserAndIsActive(User user, Boolean isActive);

    List<UserTenantMapping> getUserTenantMappingByUserAndTenantAndIsActive(User user,
                                                                           Tenant tenant,
                                                                           Boolean isActive);

    Page<UserTenantMapping> getAllByTenant(Tenant tenant, Pageable pageable);

}
