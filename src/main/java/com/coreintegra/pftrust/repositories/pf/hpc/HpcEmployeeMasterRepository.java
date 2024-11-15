package com.coreintegra.pftrust.repositories.pf.hpc;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeeMaster;

public interface HpcEmployeeMasterRepository extends JpaRepository<HpcEmployeeMaster, Long> {
	
	Optional<HpcEmployeeMaster> findByMemberIdAndIsActive(String memberId, Boolean isActive);
}
