package com.coreintegra.pftrust.services.pf.pdf;

import com.coreintegra.pftrust.dtos.pdf.yearend.newdtos.NewAnnualStatement;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.Nominee;
import com.coreintegra.pftrust.entities.pf.yearend.ClosingBalance;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.employee.NomineeRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.ClosingBalanceRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.YearEndProcessRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class NewGenerateAnnualStatementTest {

    @Autowired
    private NewGenerateAnnualStatement newGenerateAnnualStatement;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private NomineeRepository nomineeRepository;

    @Autowired
    private YearEndProcessRepository yearEndProcessRepository;

    @Autowired
    private ClosingBalanceRepository closingBalanceRepository;

    @Test
    void new_annual_statement() throws Exception {

        NewAnnualStatement newAnnualStatement = new NewAnnualStatement();

        Optional<Employee> optionalEmployee = employeeRepository.findByPernNumberAndIsActive("90020570", true);

        Employee employee = optionalEmployee.get();

        List<Nominee> nominees = nomineeRepository.findAllByEmployeeAndIsActive(employee, true);

        List<Nominee> nominees1 = new ArrayList<>(nominees);

        for(int i=0; i<4-nominees.size(); i++){
            nominees1.add(new Nominee());
        }

        Optional<YearEndProcess> optionalYearEndProcess = yearEndProcessRepository
                .findByEmployeeAndYearAndMonthAndEntityTypeAndIsActiveAndIsPublished(
                        employee, 2023, 0,
                        YearEndProcess.ENTITY_CONTRIBUTION,
                        true, false);

        YearEndProcess openingContribution = optionalYearEndProcess.get();

        List<YearEndProcess> contributions = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee,
                2023, true, false, YearEndProcess.ENTITY_CONTRIBUTION);

        List<YearEndProcess> duringTheYearContributions = contributions.stream().filter(yearEndProcess -> yearEndProcess.getMonth() > 0)
                .collect(Collectors.toList());

        Optional<YearEndProcess> optionalYearEndProcessNext = yearEndProcessRepository
                .findByEmployeeAndYearAndMonthAndEntityTypeAndIsActiveAndIsPublished(
                        employee, 2024, 0,
                        YearEndProcess.ENTITY_CONTRIBUTION,
                        true, false);

        YearEndProcess openingContributionNext = optionalYearEndProcessNext.get();

        Optional<ClosingBalance> optionalClosingBalance = closingBalanceRepository
                .findByYearEndProcessAndIsActive(openingContributionNext, true);

        ClosingBalance closingBalance = optionalClosingBalance.get();


        List<YearEndProcess> loans = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee,
                        2023, true, false, YearEndProcess.ENTITY_LOAN);

        List<YearEndProcess> transferIns = yearEndProcessRepository
                .findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(employee,
                        2023, true, false, YearEndProcess.ENTITY_TRANSFER_IN);

        newAnnualStatement.setBasicDetails(employee,
                        nominees1, 2023, openingContribution)
                .setOpeningBalanceDetails(openingContribution)
                .setCurrentYearContributionDetails(duringTheYearContributions)
                .setCurrentYearContributionDetailsNew(duringTheYearContributions)
                .setCurrentYearTransferInDetails(transferIns)
                .setCurrentYearLoanDetails(loans)
                .setTotalContributionDetails(openingContributionNext,
                        closingBalance.getTdsOnTaxableMemberContribution(),
                        closingBalance.getTdsOnTaxableVpfContribution());

        ByteArrayOutputStream byteArrayOutputStream = newGenerateAnnualStatement.generate(newAnnualStatement);

        try(OutputStream outputStream = new FileOutputStream("annual_statement.pdf")) {
            byteArrayOutputStream.writeTo(outputStream);
        }

    }
}