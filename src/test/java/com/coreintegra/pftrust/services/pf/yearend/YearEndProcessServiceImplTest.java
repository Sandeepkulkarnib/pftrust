package com.coreintegra.pftrust.services.pf.yearend;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.services.email.EmailService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class YearEndProcessServiceImplTest {

    @Autowired
    private YearEndProcessService yearEndProcessService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void performForLoan() throws JPAException {

        Employee employee = employeeService.getEmployee("8581faf4-bf3f-4af8-96c9-e18a78bc0ed0");


        yearEndProcessService.performForLoan(employee,
                FinancialYearAndMonth.getFinancialYear(new Date())-1, getDate(), true, null);
    }

    @Test
    void performForSettlement() throws JPAException {

        Employee employee = employeeService.getEmployeeByPern("90020570");

        yearEndProcessService.performForSettlement(employee,
                FinancialYearAndMonth.getFinancialYear(new Date())-1, getDate(), true, null);

    }

    @Test
    void performForSettlementMultipleEmployee() throws JPAException {

        Instant now = Instant.now();

        List<Employee> employees = employeeService.getEmployees("899000");
        yearEndProcessService.performForSettlement(employees, FinancialYearAndMonth.getFinancialYear(new Date())-1,
                getDate(), true, null);

        System.out.println(Duration.between(now, Instant.now()).getSeconds());

    }

    private Date getDate(){

        Calendar myCal = Calendar.getInstance();

        myCal.set(Calendar.YEAR, FinancialYearAndMonth.getFinancialYear(new Date()) - 2);
        myCal.set(Calendar.MONTH, 2);
        myCal.set(Calendar.DAY_OF_MONTH, 31);

        return myCal.getTime();

    }

}