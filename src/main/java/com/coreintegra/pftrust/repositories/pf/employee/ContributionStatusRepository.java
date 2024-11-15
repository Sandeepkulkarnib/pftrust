package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.employee.ContributionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ContributionStatusRepository extends JpaRepository<ContributionStatus, Long> {

    ContributionStatus findBySymbolAndIsActive(String symbol, Boolean active);


}
