package com.coreintegra.pftrust.services.pf.contribution;

import com.coreintegra.pftrust.dtos.pdf.settlement.CalculatedAmount;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.TotalContributionDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ContributionService {

    CompletableFuture<String> saveAsync(Contribution contribution);

    Contribution save(Contribution contribution);

    Contribution saveOpeningBalance(Contribution contribution);

    CompletableFuture<String> updateContributionWithEmployee(Employee employee);

    Page<Contribution> getContributions(String search, Integer size, Integer page);

    List<Contribution> getContributions(Employee employee);

    List<Contribution> getContributions(Employee employee, Integer year);

    Date getLastRecoveryDate(Employee employee);

    Contribution getContribution(Employee employee, Integer year, Integer month);

    TotalContributionDTO getTotalContribution(Employee employee);

    TotalContributionDTO getTotalContribution(Employee employee, Integer year);

    List<CalculatedAmount> getMonthWiseMemberContributions(List<Contribution> contributions);

    List<CalculatedAmount> getMonthWiseCompanyContributions(List<Contribution> contributions);

    List<CalculatedAmount> getMonthWiseVpfContributions(List<Contribution> contributions);

    List<CalculatedAmount> getMonthWiseInterestOnMemberContributions(List<Contribution> contributions);

    List<CalculatedAmount> getMonthWiseInterestOnCompanyContributions(List<Contribution> contributions);

    List<CalculatedAmount> getMonthWiseInterestOnVpfContributions(List<Contribution> contributions);

    List<CalculatedAmount> getMonthWiseTotalContribution(List<Contribution> contributions);

    List<CalculatedAmount> getMonthWiseTotalInterest(List<Contribution> contributions);

    BigDecimal getLatestPfBase(Employee employee);

    List<Contribution> getCurrentYearContribution(Employee employee, Integer year);

    TotalContributionDTO getTotalCurrentYearContribution(Employee employee, Integer year);

    TotalContributionDTO getTotalCurrentYearContributionTillMonth(Employee employee, Integer year, Integer month);

    TotalContributionDTO getTotalCurrentYearOpeningBalance(Employee employee, Integer year);
}
