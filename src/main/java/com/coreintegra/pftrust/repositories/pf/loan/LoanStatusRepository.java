package com.coreintegra.pftrust.repositories.pf.loan;

import com.coreintegra.pftrust.entities.pf.loan.LoanStatus;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanStatusRepository extends JpaRepository<LoanStatus, Long> {

    List<LoanStatus> findAllByIsActive(Boolean isActive);

    LoanStatus findByTitleAndIsActive(String title, Boolean isActive);

}
