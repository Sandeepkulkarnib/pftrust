package com.coreintegra.pftrust.services.pf.department;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.repositories.pf.department.InterestRateRepository;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterestRateServiceImpl implements InterestRateService {

    private final InterestRateRepository interestRateRepository;
    private final FinancialYearAndMonthService financialYearAndMonthService;

    public InterestRateServiceImpl(InterestRateRepository interestRateRepository,
                                   FinancialYearAndMonthService financialYearAndMonthService) {
        this.interestRateRepository = interestRateRepository;
        this.financialYearAndMonthService = financialYearAndMonthService;
    }

    @Override
    public InterestRate save(InterestRate interestRate) {
        List<InterestRate> interestRateList = interestRateRepository.findAll();

        List<InterestRate> inActiveInterestRates = interestRateList.stream()
                .peek(interest -> interest.setIsActive(false))
                .collect(Collectors.toList());

        interestRateRepository.saveAll(inActiveInterestRates);

        return interestRateRepository.save(interestRate);
    }

    @Override
    public List<InterestRate> get() {
        return interestRateRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public InterestRate getActive() {
        Integer financialYear = financialYearAndMonthService.getFinancialYear();
        Optional<InterestRate> optional = interestRateRepository
                .findByYearAndIsActive(financialYear, true);
        if(optional.isEmpty()) throw new EntityNotFoundException("no active interest rates not found");
        return optional.get();
    }

    @Override
    public InterestRate getActive(Integer year) {
        Optional<InterestRate> optional = interestRateRepository.findByYearAndIsActive(year, true);
        if(optional.isEmpty()) throw new EntityNotFoundException("active interest rate not found for given year");
        return optional.get();
    }

    @Override
    public InterestRate getInterestRateForLoan() {
        return new InterestRate(0d);
    }

}
