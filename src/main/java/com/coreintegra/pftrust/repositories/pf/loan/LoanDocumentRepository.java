package com.coreintegra.pftrust.repositories.pf.loan;

import com.coreintegra.pftrust.entities.pf.loan.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanDocumentRepository extends JpaRepository<LoanDocument, Long> {

    Optional<LoanDocument> findByEntityIdAndIsActive(String entityId, Boolean isActive);

}
