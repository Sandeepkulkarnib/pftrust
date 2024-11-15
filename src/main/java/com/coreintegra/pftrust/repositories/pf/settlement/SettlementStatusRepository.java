package com.coreintegra.pftrust.repositories.pf.settlement;

import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementStatusRepository extends JpaRepository<SettlementStatus, Long> {

    List<SettlementStatus> findAllByIsActive(Boolean isActive);

    SettlementStatus findByTitleAndIsActive(String title, Boolean isActive);

}
