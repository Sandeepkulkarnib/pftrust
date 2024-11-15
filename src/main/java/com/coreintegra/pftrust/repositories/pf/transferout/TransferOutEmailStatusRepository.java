package com.coreintegra.pftrust.repositories.pf.transferout;

import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutEmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TransferOutEmailStatusRepository extends JpaRepository<TransferOutEmailStatus, Long> {

    List<TransferOutEmailStatus> findAllByIsSentAndIsActive(Boolean isSent, Boolean isActive);

    List<TransferOutEmailStatus> findAllByIsActiveAndEntityIdIn(Boolean isActive, Set<String> entityId);

}
