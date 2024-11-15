package com.coreintegra.pftrust.services.pf.pdf;

import com.coreintegra.pftrust.dtos.pdf.yearend.AnnualStatement;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.services.pf.employees.AnnualStatementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenerateAnnualStatementTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AnnualStatementService annualStatementService;

    @Autowired
    private GenerateAnnualStatement generateAnnualStatement;

    @Test
    void generate() throws Exception {

        Optional<Employee> optionalEmployee = employeeRepository.findById(1L);

        Employee employee = optionalEmployee.get();

        AnnualStatement annualStatement = annualStatementService.getAnnualStatement(employee, 2022);

        ByteArrayOutputStream byteArrayOutputStream = generateAnnualStatement.generate(annualStatement);

        try(OutputStream outputStream = new FileOutputStream("annual_statement.pdf")) {
            byteArrayOutputStream.writeTo(outputStream);
        }

    }
}