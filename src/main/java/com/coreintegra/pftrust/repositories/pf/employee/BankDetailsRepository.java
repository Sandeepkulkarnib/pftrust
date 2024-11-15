package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.employee.BankDetails;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {

    List<BankDetails> findAllByEmployeeAndIsActive(Employee employee, Boolean isActive);

}
