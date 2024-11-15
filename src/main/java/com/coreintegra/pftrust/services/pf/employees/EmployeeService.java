package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.entities.pf.employee.*;
import com.coreintegra.pftrust.entities.pf.employee.dtos.EmployeeDetailsDTO;
import com.coreintegra.pftrust.exceptions.JPAException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeService {

    CompletableFuture<String> saveAsync(Employee employee, List<Address> addresses, List<Nominee> nominees,
                                        List<BankDetails> bankDetails);

    Employee save(Employee employee, List<Address> addresses, List<Nominee> nominees,
                  List<BankDetails> bankDetails) throws JPAException;

    Page<Employee> getEmployees(String search, Integer size, Integer page);

    JSONObject validateNewEmployees(JSONObject employees);

    List<ContributionStatus> getContributionStatusList();

    List<Employee> getEmployees(String unitCode);

    Employee getEmployee(String entityId) throws JPAException;

    Employee getEmployeeByPern(String pernNumber);

    Employee getEmployeeByPf(String pfNumber);

    EmployeeDetailsDTO getDetails(Employee employee);

    Date getDateOfJoiningPrior(Employee employee);

    Integer getMembershipYears(Employee employee);

}
