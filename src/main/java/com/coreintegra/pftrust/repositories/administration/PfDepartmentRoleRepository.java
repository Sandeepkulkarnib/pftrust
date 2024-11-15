package com.coreintegra.pftrust.repositories.administration;

import com.coreintegra.pftrust.entities.administration.PfDepartmentRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PfDepartmentRoleRepository extends JpaRepository<PfDepartmentRole, Long> {

    List<PfDepartmentRole> findAllByIsActiveAndEntityIdIn(Boolean isActive, Set<String> entityIds);

    List<PfDepartmentRole> findAllByIsActive(Boolean isActive);

}
