package com.coreintegra.pftrust.services.pf.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonthlyStatementServiceImplTest {

    @Autowired
    private MonthlyStatementService monthlyStatementService;

    @Test
    void generateMonthlyStatement() {

        try {
            monthlyStatementService.generateMonthlyStatement("4d65a9a6-ea8e-417d-b927-d762fa5bc0f2", 2019);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}