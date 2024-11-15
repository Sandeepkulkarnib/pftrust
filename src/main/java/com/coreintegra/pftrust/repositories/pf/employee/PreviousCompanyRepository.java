package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreviousCompanyRepository extends JpaRepository<PreviousCompany, Long> {

    List<PreviousCompany> findAllByEmployeeAndIsActive(Employee employee, Boolean isActive);

}
