package com.coreintegra.pftrust.services.pf.interest;

import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.IoInterestMonths;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanWithDrawalsFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.repositories.pf.employee.IoInterestMonthsRepository;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.coreintegra.pftrust.util.Pair;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

@Service
public class InterestProviderServiceImpl implements InterestProviderService {

    private final InterestCalculatorService interestCalculatorService;
    private final IoInterestMonthsRepository ioInterestMonthsRepository;

    public InterestProviderServiceImpl(InterestCalculatorService interestCalculatorService,
                                       IoInterestMonthsRepository ioInterestMonthsRepository) {
        this.interestCalculatorService = interestCalculatorService;
        this.ioInterestMonthsRepository = ioInterestMonthsRepository;
    }


    @Override
    public void perform(Employee employee, Integer year, InterestRate interestRate, Date date) {

    }

    @Override
    public List<Contribution> performForContributions(Employee employee, Integer year, List<Contribution> contributions,
                                                      InterestRate interestRate, Date date) {
        return contributions.stream()
                .map(contribution -> performForContribution(employee, year, contribution, interestRate, date))
                .collect(Collectors.toList());
    }

    @Override
    public Contribution performForContribution(Employee employee, Integer year, Contribution contribution,
                                               InterestRate interestRate, Date date) {

        Integer period = getPeriod(date, contribution.getSubType());

        if(employee.isIO()){

            Optional<IoInterestMonths> ioInterestMonthsOptional = ioInterestMonthsRepository
                    .findByEmployeeAndYearAndIsPublishedAndIsActive(employee, year, true, true);
            if(ioInterestMonthsOptional.isPresent()){
                period = ioInterestMonthsOptional.get().getPeriod();
            }else {
                period = 0;
                interestRate.setInterestRate(0d);
            }

        }

        Pair<BigDecimal, BigDecimal> interestOnMemberContribution = calculateInterest(contribution.getMemberContribution(),
                interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnCompanyContribution = calculateInterest(contribution.getCompanyContribution(),
                interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnVpfContribution = calculateInterest(contribution.getVpfContribution(),
                interestRate, period);

        contribution.setInterestMemContribution(interestOnMemberContribution.getFirst());
        contribution.setInterestCompanyContribution(interestOnCompanyContribution.getFirst());
        contribution.setVpfContributionInterest(interestOnVpfContribution.getFirst());

        contribution.setInterestRate(BigDecimal.valueOf(interestRate.getInterestRate()));
        contribution.setMonthlyInterestRate(interestOnMemberContribution.getSecond());
        contribution.setInterestPeriod(period);

        Pair<BigDecimal, BigDecimal> interestOnNonTaxableMemberContribution =
                calculateInterest(contribution.getNonTaxableMemberContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnTaxableMemberContribution =
                calculateInterest(contribution.getTaxableMemberContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnNonTaxableVpfContribution =
                calculateInterest(contribution.getNonTaxableVpfContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnTaxableVpfContribution =
                calculateInterest(contribution.getTaxableVpfContribution(), interestRate, period);

        contribution.setInterestNonTaxableMemberContribution(interestOnNonTaxableMemberContribution.getFirst());
        contribution.setInterestTaxableMemberContribution(interestOnTaxableMemberContribution.getFirst());

        contribution.setInterestNonTaxableVpfContribution(interestOnNonTaxableVpfContribution.getFirst());
        contribution.setInterestTaxableVpfContribution(interestOnTaxableVpfContribution.getFirst());

        return contribution;
    }

    @Override
    public List<Loan> performForLoans(Integer year, List<Loan> loans, InterestRate interestRate, Date date) {
        return loans.stream().map(loan -> performForLoan(year, loan, interestRate, date))
                .collect(Collectors.toList());
    }

    @Override
    public Loan performForLoan(Integer year, Loan loan, InterestRate interestRate, Date date) {

        LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = loan.getLoanWithDrawalsFinalDetails();

        Integer period = getPeriod(date, loanWithDrawalsFinalDetails.getMonth()-1);

        Pair<BigDecimal, BigDecimal> interestOnLoanAmountOnMemberContribution = calculateInterest(
                loanWithDrawalsFinalDetails.getLoanAmountOnMemContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnLoanAmountOnCompanyContribution = calculateInterest(
                loanWithDrawalsFinalDetails.getLoanAmountOnCompanyContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnLoanAmountOnVpfContribution = calculateInterest(
                loanWithDrawalsFinalDetails.getLoanAmountOnVPFContribution(), interestRate, period);


        Pair<BigDecimal, BigDecimal> interestOnLoanAmountOnTaxableMemberContribution = calculateInterest(
                loanWithDrawalsFinalDetails.getLoanAmountOnTaxableMemberContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnLoanAmountOnNonTaxableMemberContribution = calculateInterest(
                loanWithDrawalsFinalDetails.getLoanAmountOnNonTaxableMemberContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnLoanAmountOnTaxableVpfContribution = calculateInterest(
                loanWithDrawalsFinalDetails.getLoanAmountOnTaxableVpfContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnLoanAmountOnNonTaxableVpfContribution = calculateInterest(
                loanWithDrawalsFinalDetails.getLoanAmountOnNonTaxableVpfContribution(), interestRate, period);


        loanWithDrawalsFinalDetails.setInterestOnloanAmountOnMemContribution(interestOnLoanAmountOnMemberContribution.getFirst());
        loanWithDrawalsFinalDetails.setInterestOnloanAmountOnCompanyContribution(interestOnLoanAmountOnCompanyContribution.getFirst());
        loanWithDrawalsFinalDetails.setInterestOnloanAmountOnVPFContribution(interestOnLoanAmountOnVpfContribution.getFirst());

        loanWithDrawalsFinalDetails.setInterestRate(BigDecimal.valueOf(interestRate.getInterestRate()));
        loanWithDrawalsFinalDetails.setMonthlyInterestRate(interestOnLoanAmountOnMemberContribution.getSecond());
        loanWithDrawalsFinalDetails.setInterestPeriod(period);

        loanWithDrawalsFinalDetails.setInterestOnLoanAmountOnTaxableMemberContribution(interestOnLoanAmountOnTaxableMemberContribution.getFirst());

        loanWithDrawalsFinalDetails.setInterestOnLoanAmountOnNonTaxableMemberContribution(interestOnLoanAmountOnNonTaxableMemberContribution.getFirst());

        loanWithDrawalsFinalDetails.setInterestOnLoanAmountOnTaxableVpfContribution(interestOnLoanAmountOnTaxableVpfContribution.getFirst());

        loanWithDrawalsFinalDetails.setInterestOnLoanAmountOnNonTaxableVpfContribution(interestOnLoanAmountOnNonTaxableVpfContribution.getFirst());

        return loan;
    }

    @Override
    public List<TransferIn> performForTransferIns(Integer year, List<TransferIn> transferIns,
                                                  InterestRate interestRate, Date date) {
        return transferIns.stream()
                .map(transferIn -> performForTransferIn(year, transferIn, interestRate, date))
                .collect(Collectors.toList());
    }

    @Override
    public TransferIn performForTransferIn(Integer year, TransferIn transferIn,
                                           InterestRate interestRate, Date date) {

        TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

        Integer period = getPeriod(date, transferInFinalDetails.getContributedIn());

        Pair<BigDecimal, BigDecimal> interestOnNonTaxableMemberContribution =
                calculateInterest(transferInFinalDetails.getNonTaxableMemberContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnTaxableMemberContribution =
                calculateInterest(transferInFinalDetails.getTaxableMemberContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnMemberContribution = calculateInterest(
                transferInFinalDetails.getMemberContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnCompanyContribution = calculateInterest(
                transferInFinalDetails.getCompanyContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnNonTaxableVpfContribution =
                calculateInterest(transferInFinalDetails.getNonTaxableVpfContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnTaxableVpfContribution =
                calculateInterest(transferInFinalDetails.getTaxableVpfContribution(), interestRate, period);

        Pair<BigDecimal, BigDecimal> interestOnVpfContribution = calculateInterest(
                transferInFinalDetails.getVpfContribution(), interestRate, period);

        transferInFinalDetails.setInterestNonTaxableMemberContribution(interestOnNonTaxableMemberContribution.getFirst());
        transferInFinalDetails.setInterestTaxableMemberContribution(interestOnTaxableMemberContribution.getFirst());
        transferInFinalDetails.setInterestOnMemberContribution(interestOnMemberContribution.getFirst());

        transferInFinalDetails.setInterestOnCompanyContribution(interestOnCompanyContribution.getFirst());

        transferInFinalDetails.setInterestNonTaxableVpfContribution(interestOnNonTaxableVpfContribution.getFirst());
        transferInFinalDetails.setInterestTaxableVpfContribution(interestOnTaxableVpfContribution.getFirst());
        transferInFinalDetails.setInterestOnVpfContribution(interestOnVpfContribution.getFirst());

        transferInFinalDetails.setInterestRate(BigDecimal.valueOf(interestRate.getInterestRate()));
        transferInFinalDetails.setMonthlyInterestRate(interestOnMemberContribution.getSecond());
        transferInFinalDetails.setInterestPeriod(period);

        return transferIn;
    }

    private Pair<BigDecimal, BigDecimal> calculateInterest(BigDecimal contribution, InterestRate interestRate, Integer period) {
        return interestCalculatorService.calculate(avoidNull(contribution),
                interestRate.getInterestRate(), period);
    }


    private Integer getPeriod(Date date, Integer contributedIn) {

        Calendar cal = FinancialYearAndMonth.getDateDetails(date);

        int month = cal.get(Calendar.MONTH);

        int day = cal.get(Calendar.DAY_OF_MONTH);

        return getFinancialMonth(month+1, day) - contributedIn;

    }

    public int getFinancialMonth(int month, int day){

        int fmonth;

        if(month < 4) {
            fmonth = month + 9;
        }
        else {
            fmonth = month - 3;
        }

        if(day >= 25){
            return fmonth;
        }else {
            return fmonth - 1;
        }

    }


}
