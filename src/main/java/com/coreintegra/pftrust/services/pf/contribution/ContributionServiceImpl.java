package com.coreintegra.pftrust.services.pf.contribution;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.pdf.settlement.CalculatedAmount;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.TotalContributionDTO;
import com.coreintegra.pftrust.repositories.pf.contribution.ContributionRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.searchutil.SearchSpecificationForEmployee;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

@Service
public class ContributionServiceImpl implements ContributionService {

    private final Logger logger = LoggerFactory.getLogger(ContributionService.class);

    private final ContributionRepository contributionRepository;

    public ContributionServiceImpl(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    @Override
    @Transactional
    @ApplyTenantFilter
    public CompletableFuture<String> saveAsync(Contribution contribution) {

        if(contribution.getRecoveryDate() == null){

            Date standardRecoveryDate = FinancialYearAndMonth.getStandardRecoveryDate(contribution.getYear(),
                    contribution.getSubType());

            contribution.setRecoveryDate(standardRecoveryDate);

        }

        if(contribution.getYear() >= 2022 && contribution.getSubType() > 0){
            calculateTaxableContribution(contribution);
        }

        if(contribution.getYear() >= 2022 && contribution.getSubType() == 0){
            contribution.setNonTaxableMemberContribution(contribution.getMemberContribution());
            contribution.setNonTaxableVpfContribution(contribution.getVpfContribution());
        }

        Contribution save = contributionRepository.save(contribution);

        logger.info("contribution saved successfully -> {}", save.getId());

        return CompletableFuture.completedFuture("done");

    }

    private void calculateTaxableContribution(Contribution contribution) {

        contribution.setNonTaxableMemberContribution(contribution.getMemberContribution());
        contribution.setTaxableMemberContribution(BigDecimal.ZERO);

        contribution.setNonTaxableVpfContribution(contribution.getVpfContribution());
        contribution.setTaxableVpfContribution(BigDecimal.ZERO);

        List<Contribution> contributions = getContributionsForYearByPernNumber(contribution.getPernNumber(),
                contribution.getYear());

        TotalContributionDTO totalContribution = getTotalContribution(contributions);

        if(totalContribution.getMemberContribution()
                .add(totalContribution.getVpfContribution())
                .compareTo(BigDecimal.valueOf(250000)) > 0){

            contribution.setTaxableMemberContribution(contribution.getMemberContribution());
            contribution.setNonTaxableMemberContribution(BigDecimal.ZERO);

            contribution.setTaxableVpfContribution(contribution.getVpfContribution());
            contribution.setNonTaxableVpfContribution(BigDecimal.ZERO);

            return;

        }

        BigDecimal totalMemberAndVpfContribution = totalContribution.getMemberContribution()
                .add(totalContribution.getVpfContribution())
                .add(contribution.getMemberContribution())
                .add(contribution.getVpfContribution());

        if(totalMemberAndVpfContribution.compareTo(BigDecimal.valueOf(250000)) > 0){

            BigDecimal taxableMemberAndVpfContribution = totalMemberAndVpfContribution.subtract(BigDecimal.valueOf(250000));

            if(contribution.getVpfContribution().compareTo(taxableMemberAndVpfContribution) >= 0){

                contribution.setTaxableVpfContribution(taxableMemberAndVpfContribution);
                contribution.setNonTaxableVpfContribution(contribution.getVpfContribution().subtract(taxableMemberAndVpfContribution));

                contribution.setTaxableMemberContribution(BigDecimal.ZERO);
                contribution.setNonTaxableMemberContribution(contribution.getMemberContribution());

            }
            else {

                contribution.setTaxableVpfContribution(contribution.getVpfContribution());
                contribution.setNonTaxableVpfContribution(BigDecimal.ZERO);

                BigDecimal taxableMemberContribution = taxableMemberAndVpfContribution.subtract(contribution.getVpfContribution());

                contribution.setTaxableMemberContribution(taxableMemberContribution);
                contribution.setNonTaxableMemberContribution(contribution.getMemberContribution().subtract(taxableMemberContribution));

            }

        }

    }

    @Override
    public Contribution save(Contribution contribution) {
        return contributionRepository.save(contribution);
    }

    @Override
    public Contribution saveOpeningBalance(Contribution contribution) {

        Optional<Contribution> optionalContribution = contributionRepository
                .findByEmployeeAndYearAndSubTypeAndIsActive(contribution.getEmployee(),
                contribution.getYear(), contribution.getSubType(), true);

        if(optionalContribution.isEmpty()){
            return contributionRepository.save(contribution);
        }

        Contribution contribution1 = optionalContribution.get();

        contribution1.setMemberContribution(contribution.getMemberContribution());
        contribution1.setCompanyContribution(contribution.getCompanyContribution());
        contribution1.setVpfContribution(contribution.getVpfContribution());

        contribution1.setInterestMemContribution(contribution.getInterestMemContribution());
        contribution1.setInterestCompanyContribution(contribution.getInterestCompanyContribution());
        contribution1.setVpfContributionInterest(contribution.getVpfContributionInterest());

        contribution1.setYear(contribution.getYear());
        contribution1.setSubType(contribution.getSubType());

        contribution1.setEmployee(contribution.getEmployee());
        contribution1.setTenant(contribution.getTenant());

        return contributionRepository.save(contribution1);
    }

    @Override
    @Async
    @Transactional
    @ApplyTenantFilter
    public CompletableFuture<String> updateContributionWithEmployee(Employee employee) {

        contributionRepository.updateEmployee(employee.getId(), employee.getPernNumber());

        logger.info("updated contributions for -> {}", employee.getId());

        return CompletableFuture.completedFuture("done");

    }

    @Override
    public Page<Contribution> getContributions(String search, Integer size, Integer page) {

        Pageable pageable = PageRequest.of(page-1, size,
                Sort.by(Sort.Direction.DESC, "year", "subType")
                        .and(Sort.by(Sort.Direction.ASC, "employee.unitCode", "employee.pernNumber")));

        SearchSpecificationForEmployee<Contribution> specification = new SearchSpecificationForEmployee<>();

        return contributionRepository.findAll(specification.getSearchSpecificationForEmployee(search), pageable);
    }


    @Override
    public List<Contribution> getContributions(Employee employee) {
        return contributionRepository.findAllByEmployeeAndIsActive(employee, true);
    }

    @Override
    public List<Contribution> getContributions(Employee employee, Integer year) {
        return contributionRepository.findAllByEmployeeAndYearAndIsActiveOrderBySubTypeDesc(employee,
                year, true);
    }

    @Override
    public Contribution getContribution(Employee employee, Integer year, Integer month) {
        Optional<Contribution> optionalContribution = contributionRepository
                .findByEmployeeAndYearAndSubTypeAndIsActive(employee, year, month, true);
        if(optionalContribution.isEmpty()) throw new EntityNotFoundException("no contribution found");
        return optionalContribution.get();
    }

    @Override
    public TotalContributionDTO getTotalContribution(Employee employee) {

        Optional<Contribution> optionalContribution = contributionRepository
                .findTop1ByEmployeeAndIsActiveOrderByYearDescSubTypeDesc(employee, true);

        if(optionalContribution.isEmpty()) {
            return new TotalContributionDTO(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        Contribution lastContribution = optionalContribution.get();

        return getTotalContribution(employee, lastContribution.getYear());

    }

    public List<Contribution> getContributionsForYearByPernNumber(String pernNumber, Integer year){

        return contributionRepository.findAllByPernNumberAndYearAndSubTypeGreaterThanAndIsActive(pernNumber,
                year, 0, true);

    }

    @Override
    public TotalContributionDTO getTotalContribution(Employee employee, Integer year) {

        List<Contribution> contributions = getContributions(employee, year);

        return getTotalContribution(contributions);
    }

    private TotalContributionDTO getTotalContribution(List<Contribution> contributions) {
        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;
        BigDecimal taxableMemberContribution = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfContribution = BigDecimal.ZERO;
        BigDecimal taxableVpfContribution = BigDecimal.ZERO;

        for (Contribution contribution: contributions) {

            memberContribution = memberContribution.add(contribution.getMemberContribution());
            companyContribution = companyContribution.add(contribution.getCompanyContribution());
            vpfContribution = vpfContribution.add(contribution.getVpfContribution());

            nonTaxableMemberContribution = nonTaxableMemberContribution.add(contribution.getNonTaxableMemberContribution());
            taxableMemberContribution = taxableMemberContribution.add(contribution.getTaxableMemberContribution());

            nonTaxableVpfContribution = nonTaxableVpfContribution.add(contribution.getNonTaxableVpfContribution());
            taxableVpfContribution = taxableVpfContribution.add(contribution.getTaxableVpfContribution());

        }

        TotalContributionDTO totalContributionDTO = new TotalContributionDTO(memberContribution, companyContribution, vpfContribution);

        totalContributionDTO.setNonTaxableMemberContribution(nonTaxableMemberContribution);
        totalContributionDTO.setTaxableMemberContribution(taxableMemberContribution);

        totalContributionDTO.setNontaxableVpfContribution(nonTaxableVpfContribution);
        totalContributionDTO.setTaxableVpfContribution(taxableVpfContribution);

        return totalContributionDTO;
    }


    @Override
    public Date getLastRecoveryDate(Employee employee) {

        Optional<Contribution> optionalContribution = contributionRepository
                .findTop1ByEmployeeAndSubTypeGreaterThanAndIsActiveOrderByYearDescSubTypeDesc(employee, 0,
                        true);

        if (optionalContribution.isEmpty()) return null;

        Contribution contribution = optionalContribution.get();

        if(contribution.getRecoveryDate() == null){
            return FinancialYearAndMonth.getStandardRecoveryDate(contribution.getYear(), contribution.getSubType());
        }

        return contribution.getRecoveryDate();

    }

    @Override
    public List<CalculatedAmount> getMonthWiseMemberContributions(List<Contribution> contributions){

        List<CalculatedAmount> calculatedAmountList = contributions.stream()
                .map(contribution -> new CalculatedAmount(contribution.getMemberContribution(), contribution.getSubType()))
                .collect(Collectors.toList());

        return equalizeList(calculatedAmountList);

    }

    @Override
    public List<CalculatedAmount> getMonthWiseCompanyContributions(List<Contribution> contributions){

        List<CalculatedAmount> calculatedAmountList = contributions.stream()
                .map(contribution -> new CalculatedAmount(contribution.getCompanyContribution(), contribution.getSubType()))
                .collect(Collectors.toList());

        return equalizeList(calculatedAmountList);

    }

    @Override
    public List<CalculatedAmount> getMonthWiseVpfContributions(List<Contribution> contributions){

        List<CalculatedAmount> calculatedAmountList = contributions.stream()
                .map(contribution -> new CalculatedAmount(contribution.getVpfContribution(), contribution.getSubType()))
                .collect(Collectors.toList());

        return equalizeList(calculatedAmountList);

    }

    @Override
    public List<CalculatedAmount> getMonthWiseInterestOnMemberContributions(List<Contribution> contributions){

        List<CalculatedAmount> calculatedAmountList = contributions.stream()
                .map(contribution -> new CalculatedAmount(contribution.getInterestMemContribution(), contribution.getSubType()))
                .collect(Collectors.toList());

        return equalizeList(calculatedAmountList);

    }

    @Override
    public List<CalculatedAmount> getMonthWiseInterestOnCompanyContributions(List<Contribution> contributions){

        List<CalculatedAmount> calculatedAmountList = contributions.stream()
                .map(contribution -> new CalculatedAmount(contribution.getInterestCompanyContribution(), contribution.getSubType()))
                .collect(Collectors.toList());

        return equalizeList(calculatedAmountList);

    }

    @Override
    public List<CalculatedAmount> getMonthWiseInterestOnVpfContributions(List<Contribution> contributions){

        List<CalculatedAmount> calculatedAmountList = contributions.stream()
                .map(contribution -> new CalculatedAmount(contribution.getVpfContributionInterest(), contribution.getSubType()))
                .collect(Collectors.toList());

        return equalizeList(calculatedAmountList);

    }

    @Override
    public List<CalculatedAmount> getMonthWiseTotalContribution(List<Contribution> contributions){

        List<CalculatedAmount> calculatedAmountList = contributions.stream().map(contribution -> {
            BigDecimal totalContribution = BigDecimal.ZERO.add(avoidNull(contribution.getMemberContribution()))
                    .add(avoidNull(contribution.getCompanyContribution()))
                    .add(avoidNull(contribution.getVpfContribution()));
            return new CalculatedAmount(totalContribution, contribution.getSubType());
        }).collect(Collectors.toList());

        return equalizeList(calculatedAmountList);
    }

    @Override
    public List<CalculatedAmount> getMonthWiseTotalInterest(List<Contribution> contributions){

        List<CalculatedAmount> calculatedAmountList = contributions.stream().map(contribution -> {
            BigDecimal totalContribution = BigDecimal.ZERO.add(avoidNull(contribution.getInterestMemContribution()))
                    .add(avoidNull(contribution.getInterestCompanyContribution()))
                    .add(avoidNull(contribution.getVpfContributionInterest()));
            return new CalculatedAmount(totalContribution, contribution.getSubType());
        }).collect(Collectors.toList());

        return equalizeList(calculatedAmountList);
    }


    private List<CalculatedAmount> equalizeList(List<CalculatedAmount> calculatedAmounts) {

        if (calculatedAmounts.size() >= 12) {
            return calculatedAmounts;
        }

        List<CalculatedAmount> list = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            list.add(new CalculatedAmount(new BigDecimal(0), i));
        }

        for (CalculatedAmount amount : calculatedAmounts) {

            list.set(amount.getMonth() - 1, amount);

        }

        return list;

    }


    @Override
    public BigDecimal getLatestPfBase(Employee employee) {

        Optional<Contribution> optionalContribution = contributionRepository
                .findTop1ByEmployeeAndSubTypeGreaterThanAndIsActiveOrderByYearDescSubTypeDesc(employee, 0,
                        true);

        if (optionalContribution.isEmpty()) return BigDecimal.ZERO;

        Contribution contribution = optionalContribution.get();

        return contribution.getPfBase();
    }

    @Override
    public List<Contribution> getCurrentYearContribution(Employee employee, Integer year) {
        return contributionRepository.findAllByEmployeeAndYearAndSubTypeGreaterThanAndIsActive(employee, year,
                0, true);
    }

    @Override
    public TotalContributionDTO getTotalCurrentYearContribution(Employee employee, Integer year) {

        List<Contribution> contributions = contributionRepository
                .findAllByEmployeeAndYearAndSubTypeGreaterThanAndIsActive(employee, year, 0, true);

        return getTotalContribution(contributions);
    }


    @Override
    public TotalContributionDTO getTotalCurrentYearContributionTillMonth(Employee employee, Integer year,
                                                                         Integer month) {

        List<Contribution> contributions = contributionRepository
                .findAllByEmployeeAndYearAndSubTypeGreaterThanAndIsActive(employee, year, 0,true)
                .stream().filter(contribution -> contribution.getSubType() < month)
                .collect(Collectors.toList());

        return getTotalContribution(contributions);
    }


    @Override
    public TotalContributionDTO getTotalCurrentYearOpeningBalance(Employee employee, Integer year) {

        Optional<Contribution> optionalContribution = contributionRepository
                .findByEmployeeAndYearAndSubTypeAndIsActive(employee, year, 0, true);

        if(optionalContribution.isEmpty()){
            return new TotalContributionDTO(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        Contribution contribution = optionalContribution.get();

        TotalContributionDTO totalContributionDTO = new TotalContributionDTO(
                contribution.getMemberContribution(),
                contribution.getCompanyContribution(),
                contribution.getVpfContribution());

        totalContributionDTO.setNonTaxableMemberContribution(contribution.getNonTaxableMemberContribution());
        totalContributionDTO.setTaxableMemberContribution(contribution.getTaxableMemberContribution());

        totalContributionDTO.setNontaxableVpfContribution(contribution.getNonTaxableVpfContribution());
        totalContributionDTO.setTaxableVpfContribution(contribution.getTaxableVpfContribution());

        return totalContributionDTO;

    }


}
