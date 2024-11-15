package com.coreintegra.pftrust.repositories.pf.settlement;

import com.coreintegra.pftrust.entities.pf.settlement.SettlementDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettlementDocumentRepository extends JpaRepository<SettlementDocument, Long> {

    Optional<SettlementDocument> findByEntityIdAndIsActive(String entityId, Boolean isActive);

}
