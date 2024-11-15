package com.coreintegra.pftrust.repositories.administration;

import com.coreintegra.pftrust.entities.administration.PfDepartmentRoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PfDepartmentRoleGroupRepository extends JpaRepository<PfDepartmentRoleGroup, Long> {

    List<PfDepartmentRoleGroup> findAllByIsActiveAndEntityIdIn(Boolean isActive, Set<String> entityIds);

    List<PfDepartmentRoleGroup> findAllByIsActive(Boolean isActive);

}
