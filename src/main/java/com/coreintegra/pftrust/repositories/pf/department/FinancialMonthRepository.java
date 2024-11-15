package com.coreintegra.pftrust.repositories.pf.department;

import com.coreintegra.pftrust.entities.pf.FinancialMonth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialMonthRepository extends JpaRepository<FinancialMonth, Long> {

    List<FinancialMonth> findAllByIsActive(Boolean isActive);

}
