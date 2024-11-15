package com.coreintegra.pftrust.services.pf.interest;

import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.util.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class InterestCalculatorServiceImplTest {

    @Autowired
    private InterestCalculatorService interestCalculatorService;

    @Test
    void calculate() {

        Pair<BigDecimal, BigDecimal> interest = interestCalculatorService.calculate(BigDecimal.valueOf(10000),
                8.10d, 11);
    }


    @Test
    void getMonthlyInterestRate() {

        BigDecimal monthlyInterestRate = interestCalculatorService.getMonthlyInterestRate(8.10);
    }
}