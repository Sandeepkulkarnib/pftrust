package com.coreintegra.pftrust.repositories.pf;

import com.coreintegra.pftrust.entities.tenant.PdfDocumentDesign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PdfDocumentDesignRepository extends JpaRepository<PdfDocumentDesign, Long> {

    Optional<PdfDocumentDesign> findByDocumentType(String documentType);

    List<PdfDocumentDesign> findAllByIsActive(Boolean isActive);

}
