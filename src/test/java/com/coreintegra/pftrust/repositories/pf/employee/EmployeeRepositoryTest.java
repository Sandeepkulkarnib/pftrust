package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.projections.EmployeeHiringCountByMonth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void getEmployeeHiringCountByMonth() {

    }
}