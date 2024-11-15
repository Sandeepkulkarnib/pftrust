package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.dtos.pdf.MonthlyStatement;

import java.text.ParseException;

public interface MonthlyStatementService {

    MonthlyStatement getMonthlyStatementDetails(String entityId, Integer year) throws ParseException;

    void generateMonthlyStatement(String entityId, Integer year) throws Exception;

}
