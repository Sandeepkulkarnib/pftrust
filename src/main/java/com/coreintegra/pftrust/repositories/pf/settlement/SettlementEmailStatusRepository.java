package com.coreintegra.pftrust.repositories.pf.settlement;

import com.coreintegra.pftrust.entities.pf.settlement.SettlementEmailStatus;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutEmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SettlementEmailStatusRepository extends JpaRepository<SettlementEmailStatus, Long> {

    List<SettlementEmailStatus> findAllByIsSentAndIsActive(Boolean isSent, Boolean isActive);

    List<SettlementEmailStatus> findAllByIsActiveAndEntityIdIn(Boolean isActive, Set<String> entityId);

}
