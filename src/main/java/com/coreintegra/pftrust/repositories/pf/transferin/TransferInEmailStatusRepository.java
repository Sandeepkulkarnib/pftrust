package com.coreintegra.pftrust.repositories.pf.transferin;

import com.coreintegra.pftrust.entities.pf.transferIn.TransferInEmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TransferInEmailStatusRepository extends JpaRepository<TransferInEmailStatus, Long> {

    List<TransferInEmailStatus> findAllByIsSentAndIsActive(Boolean isSent, Boolean isActive);

    List<TransferInEmailStatus> findAllByIsActiveAndEntityIdIn(Boolean isActive, Set<String> entityId);

}
