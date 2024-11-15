package com.coreintegra.pftrust.controllers.pf.department;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.entities.pf.department.dtos.InterestRateDTO;
import com.coreintegra.pftrust.services.pf.department.InterestRateService;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.coreintegra.pftrust.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.coreintegra.pftrust.controllers.pf.department.DepartmentServiceEndpoints.*;

@RestController
@RequestMapping(DEPARTMENT_SERVICE_ENDPOINT)
public class InterestRateController {

    private final InterestRateService interestRateService;

    public InterestRateController(InterestRateService interestRateService) {
        this.interestRateService = interestRateService;
    }

    @GetMapping(INTEREST_RATE_SERVICE_ENDPOINT)
    @ApplyTenantFilter
    public ResponseEntity<Object> getInterestRates(){
        List<InterestRate> interestRates = interestRateService.get();

        List<InterestRateDTO> interestRateDTOList = interestRates.stream().map(interestRate -> new InterestRateDTO(interestRate.getDescription(),
                        interestRate.getInterestRate(),
                        interestRate.getYear(), interestRate.getCreatedAt(), interestRate.getUpdatedAt(),
                        interestRate.getCreatedByUser().getUsername(),
                        interestRate.getLastModifiedByUser().getUsername(),
                        interestRate.getIsActive()))
                .collect(Collectors.toList());

        return Response.success(interestRateDTOList);
    }

    @PostMapping(INTEREST_RATE_SERVICE_ENDPOINT)
    @ApplyTenantFilter
    public ResponseEntity<Object> saveInterestRate(@RequestBody InterestRate newInterestRate) throws ParseException {

        int year = FinancialYearAndMonth.getFinancialYear(new Date());

        if(newInterestRate.getInterestRate() < 0.1){
            throw new RuntimeException("Invalid Interest Rate.");
        }

        newInterestRate.setYear(year);
        newInterestRate.setDescription("Interest Rate for Year - " + year);

        interestRateService.save(newInterestRate);

        List<InterestRate> interestRates = interestRateService.get();

        List<InterestRateDTO> interestRateDTOList = interestRates.stream()
                .map(interestRate -> new InterestRateDTO(interestRate.getDescription(),
                        interestRate.getInterestRate(),
                        interestRate.getYear(), interestRate.getCreatedAt(), interestRate.getUpdatedAt(),
                        interestRate.getCreatedByUser().getUsername(),
                        interestRate.getLastModifiedByUser().getUsername(),
                        interestRate.getIsActive()))
                .collect(Collectors.toList());

        return Response.success(interestRateDTOList);
    }

}
