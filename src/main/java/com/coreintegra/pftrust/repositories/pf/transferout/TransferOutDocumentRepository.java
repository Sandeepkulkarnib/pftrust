package com.coreintegra.pftrust.repositories.pf.transferout;

import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferOutDocumentRepository extends JpaRepository<TransferOutDocument, Long> {

    Optional<TransferOutDocument> findByEntityIdAndIsActive(String entityId, Boolean isActive);

}
