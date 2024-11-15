package com.coreintegra.pftrust.repositories.pf.hpc;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coreintegra.pftrust.entities.pf.hpc.HpcInterestRates;

public interface HpcInterestRatesRepository extends JpaRepository<HpcInterestRates, Long> {

	Optional<HpcInterestRates> findAllByActualYearAndMonthAndIsActive(Integer actualYear,Integer month,Boolean isActive);
}
