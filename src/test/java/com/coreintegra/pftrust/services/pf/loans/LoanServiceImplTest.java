package com.coreintegra.pftrust.services.pf.loans;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanServiceImplTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void distributeLoanAmountIntoTaxableAndNonTaxable() {

        Employee employeeByPern = employeeService.getEmployeeByPern("90020570");

        List<Loan> completedLoans = loanService.getCompletedLoans(employeeByPern, 2023);

        loanService.distributeLoanAmountIntoTaxableAndNonTaxable(completedLoans, employeeByPern);

    }
}