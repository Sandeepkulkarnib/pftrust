package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.exceptions.JPAException;

import java.text.ParseException;
import java.util.concurrent.CompletableFuture;

public interface MonthlyStatusProcessService {

    void performMemberWise(String pernNumber, Integer financialYear, Integer financialMonth, Boolean isDryRun) throws JPAException, ParseException;

    void performUnitCodeWise(String unitCode, Integer financialYear, Integer financialMonth, Boolean isDryRun);

    CompletableFuture<String> performMemberWiseAsync(Employee employee, Integer financialYear,
                                                     Integer financialMonth, Boolean isDryRun, Job job);

}
