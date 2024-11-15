package com.coreintegra.pftrust.services.pf.department;

import com.coreintegra.pftrust.entities.pf.FinancialYear;
import com.coreintegra.pftrust.repositories.pf.department.FinancialMonthRepository;
import com.coreintegra.pftrust.repositories.pf.department.FinancialYearRepository;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinancialYearAndMonthServiceImpl implements FinancialYearAndMonthService {

    private final FinancialYearRepository financialYearRepository;
    private final FinancialMonthRepository financialMonthRepository;

    public FinancialYearAndMonthServiceImpl(FinancialYearRepository financialYearRepository,
                                            FinancialMonthRepository financialMonthRepository) {
        this.financialYearRepository = financialYearRepository;
        this.financialMonthRepository = financialMonthRepository;
    }

    @Override
    public List<Integer> getYears() {
      return financialYearRepository.findAllByIsActiveOrderByYearDesc(true)
              .stream().map(FinancialYear::getYear).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, String> getMonths() {

        Map<Integer, String> months = new LinkedHashMap<>();

        financialMonthRepository.findAllByIsActive(true)
                .forEach(financialMonth -> months.put(financialMonth.getCalenderMonth(),
                        financialMonth.getLabel()));

        return months;
    }

    @Override
    public Map<Integer, String> getFinancialMonths() {

        Map<Integer, String> financialMonths = new LinkedHashMap<>();

        financialMonthRepository.findAllByIsActive(true)
                .forEach(financialMonth -> financialMonths.put(financialMonth.getFinancialMonth(),
                        financialMonth.getLabel()));

        return financialMonths;
    }

    @Override
    public Integer getFinancialYear() {
       return FinancialYearAndMonth.getFinancialYear(new Date());
    }

    @Override
    public Integer getFinancialYear(Date date) {
        return FinancialYearAndMonth.getFinancialYear(date);
    }

    @Override
    public Integer getFinancialMonth() {
        return FinancialYearAndMonth.getFinancialMonth(new Date());
    }

    @Override
    public Integer getFinancialMonth(Date date) {
        return FinancialYearAndMonth.getFinancialMonth(date);
    }

}
