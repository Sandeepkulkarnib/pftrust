package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.EmployeeMonthlyContributionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmployeeMonthlyContributionStatusRepository
        extends JpaRepository<EmployeeMonthlyContributionStatus, Long>,
        JpaSpecificationExecutor<EmployeeMonthlyContributionStatus> {

    Page<EmployeeMonthlyContributionStatus> findAllByJobAndIsActive(Job job, Boolean isActive, Pageable pageable);

}
