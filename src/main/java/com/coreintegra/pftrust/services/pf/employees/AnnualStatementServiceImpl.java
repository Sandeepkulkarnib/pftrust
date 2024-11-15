package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.dtos.pdf.yearend.AnnualStatement;
import com.coreintegra.pftrust.dtos.pdf.yearend.newdtos.NewAnnualStatement;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.Nominee;
import com.coreintegra.pftrust.entities.pf.yearend.ClosingBalance;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.projections.YearEndProcessYearAndVersion;
import com.coreintegra.pftrust.repositories.pf.employee.NomineeRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.YearEndProcessRepository;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnualStatementServiceImpl implements AnnualStatementService {

    private final YearEndProcessRepository yearEndProcessRepository;
    private final NomineeRepository nomineeRepository;

    public AnnualStatementServiceImpl(YearEndProcessRepository yearEndProcessRepository, NomineeRepository nomineeRepository) {
        this.yearEndProcessRepository = yearEndProcessRepository;
        this.nomineeRepository = nomineeRepository;
    }

    @Override
    public AnnualStatement getAnnualStatement(Employee employee, Integer year) throws ParseException {

        List<YearEndProcess> contributions = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee, year, true,
                        true, YearEndProcess.ENTITY_CONTRIBUTION);

        List<YearEndProcess> filteredContributions = contributions.stream()
                .filter(yearEndProcess -> yearEndProcess.getMonth() != 0)
                .collect(Collectors.toList());

        List<YearEndProcess> loans = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee, year, true,
                        true, YearEndProcess.ENTITY_LOAN);

        List<YearEndProcess> transferIns = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee, year, true,
                        true, YearEndProcess.ENTITY_TRANSFER_IN);

        Optional<YearEndProcess> optionalOpeningContribution = yearEndProcessRepository
                .findByEmployeeAndYearAndMonthAndEntityTypeAndIsActiveAndIsPublished(employee, year, 0,
                        YearEndProcess.ENTITY_CONTRIBUTION, true, true);

        Optional<YearEndProcess> optionalClosingContribution = yearEndProcessRepository
                .findByEmployeeAndYearAndMonthAndEntityTypeAndIsActiveAndIsPublished(employee, year + 1,
                        0, YearEndProcess.ENTITY_CONTRIBUTION, true, true);

        YearEndProcess openingContribution = optionalOpeningContribution.get();

        List<Nominee> nomineeList = nomineeRepository.findAllByEmployeeAndIsActive(employee, true);

        return AnnualStatement.builder()
                .setBasicDetails(employee, nomineeList, year, openingContribution)
                .setOpeningBalanceDetails(openingContribution)
                .setCurrentYearContributionDetails(filteredContributions)
                .setCurrentYearLoanDetails(loans)
                .setCurrentYearTransferInDetails(transferIns)
                .setTotalContributionDetails(optionalClosingContribution.get());
    }

    @Override
    public List<Integer> getAvailableYears(Employee employee, Boolean isPublished) {
        return yearEndProcessRepository.getAvailableYears(employee, isPublished);
    }

    @Override
    public NewAnnualStatement getAnnualStatementNew(Employee employee, Integer year) throws ParseException {

        List<YearEndProcess> contributions = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee, year, true,
                        true, YearEndProcess.ENTITY_CONTRIBUTION);

        List<YearEndProcess> filteredContributions = contributions.stream()
                .filter(yearEndProcess -> yearEndProcess.getMonth() != 0)
                .collect(Collectors.toList());

        List<YearEndProcess> loans = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee, year, true,
                        true, YearEndProcess.ENTITY_LOAN);

        List<YearEndProcess> transferIns = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee, year, true,
                        true, YearEndProcess.ENTITY_TRANSFER_IN);

        Optional<YearEndProcess> optionalOpeningContribution = yearEndProcessRepository
                .findByEmployeeAndYearAndMonthAndEntityTypeAndIsActiveAndIsPublished(employee, year, 0,
                        YearEndProcess.ENTITY_CONTRIBUTION, true, true);

        Optional<YearEndProcess> optionalClosingContribution = yearEndProcessRepository
                .findByEmployeeAndYearAndMonthAndEntityTypeAndIsActiveAndIsPublished(employee, year + 1,
                        0, YearEndProcess.ENTITY_CONTRIBUTION, true, true);

        YearEndProcess openingContribution = optionalOpeningContribution.get();

        YearEndProcess closingBalance = optionalClosingContribution.get();

        List<Nominee> nomineeList = nomineeRepository.findAllByEmployeeAndIsActive(employee, true);

        List<Nominee> nominees1 = new ArrayList<>(nomineeList);

        for(int i=0; i<4-nomineeList.size(); i++){
            nominees1.add(new Nominee());
        }

        return NewAnnualStatement.builder()
                .setBasicDetails(employee, nominees1, year, openingContribution)
                .setOpeningBalanceDetails(openingContribution)
                .setCurrentYearContributionDetails(filteredContributions)
                .setCurrentYearContributionDetailsNew(filteredContributions)
                .setCurrentYearLoanDetails(loans)
                .setCurrentYearTransferInDetails(transferIns)
                .setTotalContributionDetails(closingBalance,
                        closingBalance.getTdsOnTaxableMemberContribution(),
                        closingBalance.getTdsOnTaxableVpfContribution());

    }


    @Override
    public List<YearEndProcessYearAndVersion> getYearsAndVersions(Employee employee) {
        return yearEndProcessRepository.getYearsAndVersions(employee.getId());
    }


}
