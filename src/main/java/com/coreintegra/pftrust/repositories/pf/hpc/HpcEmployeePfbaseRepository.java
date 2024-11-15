package com.coreintegra.pftrust.repositories.pf.hpc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeeMaster;
import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeePfbase;

public interface HpcEmployeePfbaseRepository extends JpaRepository<HpcEmployeePfbase, Long> {

	List<HpcEmployeePfbase> findAllByHpcEmployeeMasterAndIsActiveOrderByYearAscMonthAsc(HpcEmployeeMaster employee, Boolean isActive);
	
	@Query(value = "select distinct fiscal_year from hpc_employee_pfbase where hpc_employee_master_id= :hpc_employee_master_id", nativeQuery = true)
	List<String> getFiscalYears(@Param("hpc_employee_master_id") Long hpcEmplyeeMasterId);
	
	List<HpcEmployeePfbase> findAllByHpcEmployeeMasterAndFiscalYearAndIsActiveOrderByYearAscMonthAsc(HpcEmployeeMaster employee,String fiscalYear ,Boolean isActive);
}
