package com.coreintegra.pftrust.repositories.administration;

import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.entities.administration.UserTenantPfDepartmentRoleMapping;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTenantPfDepartmentRoleMappingRepository
        extends JpaRepository<UserTenantPfDepartmentRoleMapping, Long> {

    List<UserTenantPfDepartmentRoleMapping> findByUserAndTenantAndIsActive(User user,
                                                                           Tenant tenant,
                                                                           Boolean isActive);


}
