package com.coreintegra.pftrust.repositories.pf.department;

import com.coreintegra.pftrust.entities.pf.FinancialYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialYearRepository extends JpaRepository<FinancialYear, Long> {

    List<FinancialYear> findAllByIsActiveOrderByYearDesc(Boolean isActive);

}
