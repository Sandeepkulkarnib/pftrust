package com.coreintegra.pftrust.services.pf.interest;

import com.coreintegra.pftrust.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class InterestCalculatorServiceImpl implements InterestCalculatorService {

    @Override
    public Pair<BigDecimal, BigDecimal> calculate(BigDecimal amount, Double interestRate, Integer period) {

        BigDecimal monthlyInterestRate = getMonthlyInterestRate(interestRate);

        BigDecimal interest = (amount.multiply(monthlyInterestRate)
                .divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_EVEN))
                .multiply(BigDecimal.valueOf(period));

        String s = String.format("%.2f", interest.floatValue());
        Float f =  Float.valueOf(s);

        return new Pair<>() {
            @Override
            public BigDecimal getFirst() {
                return new BigDecimal(Math.round(f));
            }

            @Override
            public BigDecimal getSecond() {
                return monthlyInterestRate;
            }
        };
    }

    @Override
    public BigDecimal getMonthlyInterestRate(Double interestRate) {

        return BigDecimal.valueOf(interestRate).divide(BigDecimal.valueOf(12), 5, RoundingMode.HALF_EVEN);

    }

}
