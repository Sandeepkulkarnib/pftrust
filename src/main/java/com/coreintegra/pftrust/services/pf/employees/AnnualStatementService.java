package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.dtos.pdf.yearend.AnnualStatement;
import com.coreintegra.pftrust.dtos.pdf.yearend.newdtos.NewAnnualStatement;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.projections.YearEndProcessYearAndVersion;
import org.json.JSONArray;

import java.text.ParseException;
import java.util.List;

public interface AnnualStatementService {

    AnnualStatement getAnnualStatement(Employee employee, Integer year) throws ParseException;

    List<Integer> getAvailableYears(Employee employee, Boolean isPublished);

    NewAnnualStatement getAnnualStatementNew(Employee employee, Integer year) throws ParseException;

    List<YearEndProcessYearAndVersion> getYearsAndVersions(Employee employee);

}
