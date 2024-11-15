package com.coreintegra.pftrust.services.pf.department;

import com.coreintegra.pftrust.entities.pf.department.InterestRate;

import java.util.List;

public interface InterestRateService {

    InterestRate save(InterestRate interestRate);

    List<InterestRate> get();

    InterestRate getActive();

    InterestRate getActive(Integer year);

    InterestRate getInterestRateForLoan();

}
