package com.coreintegra.pftrust.repositories.pf.loan;

import com.coreintegra.pftrust.entities.pf.loan.LoanEmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface LoanEmailStatusRepository extends JpaRepository<LoanEmailStatus, Long> {

    List<LoanEmailStatus> findAllByIsSentAndIsActive(Boolean isSent, Boolean isActive);

    List<LoanEmailStatus> findAllByIsActiveAndEntityIdIn(Boolean isActive, Set<String> entityId);

}
