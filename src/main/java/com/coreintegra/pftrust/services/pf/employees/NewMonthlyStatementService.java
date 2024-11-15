package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.dtos.pdf.MonthlyStatement;
import com.coreintegra.pftrust.dtos.pdf.NewMonthlyStatement;

import java.text.ParseException;

public interface NewMonthlyStatementService {

    NewMonthlyStatement getMonthlyStatementDetails(String entityId, Integer year) throws ParseException;

    void generateMonthlyStatement(String entityId, Integer year) throws Exception;

}
