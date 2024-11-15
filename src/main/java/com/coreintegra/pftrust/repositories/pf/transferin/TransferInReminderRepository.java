package com.coreintegra.pftrust.repositories.pf.transferin;

import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransferInReminderRepository extends JpaRepository<TransferInReminder, Long> {

    Optional<TransferInReminder> findByEntityIdAndIsActive(String entityId, Boolean isActive);

    @Query("select tr from TransferInReminder tr where tr.transferIn = :transferIn and tr.isActive = :isActive")
    List<TransferInReminder> findAllReminders(@Param("transferIn") TransferIn transferIn,
                                              @Param("isActive") Boolean isActive);

}
