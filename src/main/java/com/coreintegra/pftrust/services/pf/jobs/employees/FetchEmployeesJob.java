package com.coreintegra.pftrust.services.pf.jobs.employees;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import org.json.JSONObject;

import java.util.List;

public interface FetchEmployeesJob {

    Job fetchForUnitCode(String unitCode, Job job);
    
    Job fetchForUnitCodeAndPern(String unitCode, String PernNumber,Job job);

    Job hireEmployees(String unitCode, List<JSONObject> employeesToHire, Job job);

    Employee fetchEmployeesByPern(String unitCode, String PernNumber) throws Exception;

}
