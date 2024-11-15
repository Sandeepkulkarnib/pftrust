package com.coreintegra.pftrust.repositories.pf.hpc;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coreintegra.pftrust.entities.pf.hpc.HpcPfslab;

public interface HpcPfslabRepository extends JpaRepository<HpcPfslab, Long> {

	@Query(value = "SELECT * FROM hpc_pfslab hpc WHERE hpc.end_date >= STR_TO_DATE(:date,'%Y-%m-%d') AND hpc.start_date <= STR_TO_DATE(:date,'%Y-%m-%d') AND is_active=1;", nativeQuery = true)
	Optional<HpcPfslab> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqualAndIsActive(@Param("date") String date);
	
}
