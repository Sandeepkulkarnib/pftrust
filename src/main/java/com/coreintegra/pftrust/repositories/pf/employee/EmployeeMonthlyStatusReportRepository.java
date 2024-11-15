package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.EmployeeMonthlyStatusReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeMonthlyStatusReportRepository extends
        JpaRepository<EmployeeMonthlyStatusReport, Long>, JpaSpecificationExecutor<EmployeeMonthlyStatusReport> {

    Page<EmployeeMonthlyStatusReport> findAllByJobAndIsActive(Job job, Boolean isActive, Pageable pageable);

}
