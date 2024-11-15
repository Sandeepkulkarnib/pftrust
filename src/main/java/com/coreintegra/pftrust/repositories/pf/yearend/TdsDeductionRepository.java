package com.coreintegra.pftrust.repositories.pf.yearend;

import com.coreintegra.pftrust.entities.pf.yearend.TdsDeduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TdsDeductionRepository extends JpaRepository<TdsDeduction, Long> {

    Optional<TdsDeduction> findByYearAndIsActive(Integer year, Boolean isActive);

}
