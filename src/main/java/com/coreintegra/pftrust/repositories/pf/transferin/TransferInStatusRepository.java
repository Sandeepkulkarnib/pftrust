package com.coreintegra.pftrust.repositories.pf.transferin;

import com.coreintegra.pftrust.entities.pf.transferIn.TransferInStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferInStatusRepository extends JpaRepository<TransferInStatus, Long> {

    List<TransferInStatus> findAllByIsActive(Boolean isActive);

    TransferInStatus findByCodeAndIsActive(String code, Boolean isActive);

}
