package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.EmployeeMonthlyContributionStatus;
import com.coreintegra.pftrust.entities.pf.employee.IoInterestMonths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IoInterestMonthsRepository extends JpaRepository<IoInterestMonths, Long>,
        JpaSpecificationExecutor<IoInterestMonths> {


    Optional<IoInterestMonths> findIoInterestMonthsByEmployeeAndEmployeeMonthlyContributionStatusAndIsActive(
            Employee employee, EmployeeMonthlyContributionStatus status, Boolean isActive);


    Optional<IoInterestMonths> findIoInterestMonthsByEmployeeMonthlyContributionStatusAndIsActive(
            EmployeeMonthlyContributionStatus status, Boolean isActive);


    Optional<IoInterestMonths> findByEmployeeAndYearAndIsPublishedAndIsActive(Employee employee, Integer year,
                                                                              Boolean isPublished, Boolean isActive);

    Optional<IoInterestMonths> findByEmployeeAndIsPublishedAndIsActive(Employee employee, Boolean isPublished,
                                                                       Boolean isActive);

}
