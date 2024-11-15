package com.coreintegra.pftrust.services.pf.yearend;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.exceptions.JPAException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface YearEndProcessService {

    void performForLoan(Employee employee, Integer year, Date date, Boolean isDryRun, Job job) throws JPAException;

    void performForLoan(List<Employee> employees, Integer year, Date date, Boolean isDryRun, Job job) throws JPAException;

    void performForSettlement(Employee employee, Integer year, Date date, Boolean isDryRun, Job job) throws JPAException;

    void performForSettlement(List<Employee> employees, Integer year, Date date, Boolean isDryRun, Job job) throws JPAException;

    CompletableFuture<String> performForLoanAsync(Employee employee, Integer year, Date date, Boolean isDryRun, Job job) throws JPAException;

    CompletableFuture<String> performForSettlementAsync(Employee employee, Integer year, Date date, Boolean isDryRun, Job job) throws JPAException;

    Optional<YearEndProcess> getPublishedYearEndProcessForSettlement(Employee employee, Integer year);

}
