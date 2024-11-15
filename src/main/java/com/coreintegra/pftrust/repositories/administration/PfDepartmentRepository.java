package com.coreintegra.pftrust.repositories.administration;

import com.coreintegra.pftrust.entities.administration.PfDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PfDepartmentRepository extends JpaRepository<PfDepartment, Long> {

    List<PfDepartment> findAllByIsActive(Boolean isActive);

}
