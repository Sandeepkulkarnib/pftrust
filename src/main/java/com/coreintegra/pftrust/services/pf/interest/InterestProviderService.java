package com.coreintegra.pftrust.services.pf.interest;

import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;

import java.util.Date;
import java.util.List;

public interface InterestProviderService {

    void perform(Employee employee, Integer year, InterestRate interestRate, Date date);

    List<Contribution> performForContributions(Employee employee, Integer year, List<Contribution> contributions,
                                 InterestRate interestRate, Date date);

    Contribution performForContribution(Employee employee, Integer year, Contribution contribution,
                                InterestRate interestRate, Date date);

    List<Loan> performForLoans(Integer year, List<Loan> loans, InterestRate interestRate, Date date);

    Loan performForLoan(Integer year, Loan loan, InterestRate interestRate, Date date);

    List<TransferIn> performForTransferIns(Integer year, List<TransferIn> transferIns,
                               InterestRate interestRate, Date date);

    TransferIn performForTransferIn(Integer year, TransferIn transferIn,
                                    InterestRate interestRate, Date date);

}
