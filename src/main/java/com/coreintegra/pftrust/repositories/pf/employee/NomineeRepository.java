package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.Nominee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NomineeRepository extends JpaRepository<Nominee, Long> {

    List<Nominee> findAllByEmployeeAndIsActive(Employee employee, Boolean isActive);

}
