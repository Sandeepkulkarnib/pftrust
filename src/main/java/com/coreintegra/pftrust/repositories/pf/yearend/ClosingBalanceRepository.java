package com.coreintegra.pftrust.repositories.pf.yearend;

import com.coreintegra.pftrust.entities.pf.yearend.ClosingBalance;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClosingBalanceRepository extends JpaRepository<ClosingBalance, Long> {

    Optional<ClosingBalance> findByYearEndProcessAndIsActive(YearEndProcess yearEndProcess, Boolean isActive);

}
