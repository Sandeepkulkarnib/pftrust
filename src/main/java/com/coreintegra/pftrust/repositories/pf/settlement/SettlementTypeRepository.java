package com.coreintegra.pftrust.repositories.pf.settlement;

import com.coreintegra.pftrust.entities.pf.settlement.SettlementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SettlementTypeRepository extends JpaRepository<SettlementType, Long> {

    SettlementType findByCodeAndIsActive(Integer code, Boolean isActive);

    List<SettlementType> findAllByIsActive(Boolean isActive);

    Optional<SettlementType> findByEntityIdAndIsActive(String entityId, Boolean isActive);

}
