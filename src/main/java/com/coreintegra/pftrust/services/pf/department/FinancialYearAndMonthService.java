package com.coreintegra.pftrust.services.pf.department;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FinancialYearAndMonthService {

        List<Integer> getYears();

        Map<Integer, String> getMonths();

        Map<Integer, String> getFinancialMonths();

        Integer getFinancialYear();

        Integer getFinancialYear(Date date);

        Integer getFinancialMonth();

        Integer getFinancialMonth(Date date);

}
