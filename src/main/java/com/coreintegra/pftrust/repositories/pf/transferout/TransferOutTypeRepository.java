package com.coreintegra.pftrust.repositories.pf.transferout;

import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransferOutTypeRepository extends JpaRepository<TransferOutType, Long> {

    TransferOutType findByCodeAndIsActive(String code, Boolean isActive);

    List<TransferOutType> findAllByIsActive(Boolean isActive);

    Optional<TransferOutType> findByEntityIdAndIsActive(String entityId, Boolean isActive);

}
