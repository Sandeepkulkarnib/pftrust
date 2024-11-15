package com.coreintegra.pftrust.repositories.pf.transferout;

import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferOutStatusRepository extends JpaRepository<TransferOutStatus, Long> {

    List<TransferOutStatus> findAllByIsActive(Boolean isActive);

    TransferOutStatus findByTitleAndIsActive(String title, Boolean isActive);

}
