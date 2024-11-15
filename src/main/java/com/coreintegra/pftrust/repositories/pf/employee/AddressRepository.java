package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.employee.Address;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByEmployeeAndIsActive(Employee employee, Boolean isActive);

}
