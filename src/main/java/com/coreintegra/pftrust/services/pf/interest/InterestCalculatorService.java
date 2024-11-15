package com.coreintegra.pftrust.services.pf.interest;

import com.coreintegra.pftrust.util.Pair;

import java.math.BigDecimal;

public interface InterestCalculatorService {

    Pair<BigDecimal, BigDecimal> calculate(BigDecimal amount, Double interestRate, Integer period);

    BigDecimal getMonthlyInterestRate(Double interestRate);

}
