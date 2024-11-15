package com.coreintegra.pftrust.repositories.pf.department;

import com.coreintegra.pftrust.entities.pf.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findByEntityIdAndIsActive(String entityId, Boolean isActive);

}
