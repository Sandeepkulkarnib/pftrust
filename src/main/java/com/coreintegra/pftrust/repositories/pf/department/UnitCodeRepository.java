package com.coreintegra.pftrust.repositories.pf.department;

import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UnitCodeRepository extends JpaRepository<UnitCode, Long> {

    List<UnitCode> findAllByIsActive(Boolean isActive);

    Optional<UnitCode> findByUnitCodeAndIsActive(String unitCode, Boolean isActive);

    Set<UnitCode> findAllByUnitCodeInAndIsActive(Set<String> unitCodes, Boolean isActive);



}
