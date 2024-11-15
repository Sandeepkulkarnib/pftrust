package com.coreintegra.pftrust.repositories.pf;

import com.coreintegra.pftrust.entities.tenant.NotificationEmailDesign;
import com.coreintegra.pftrust.entities.tenant.PdfDocumentDesign;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationEmailDesignRepository extends JpaRepository<NotificationEmailDesign, Long> {

    Optional<NotificationEmailDesign> findByDocumentType(String documentType);

    Optional<NotificationEmailDesign> findByDocumentTypeAndTenant(String documentType, Tenant tenant);

    List<NotificationEmailDesign> findAllByIsActive(Boolean isActive);

}
