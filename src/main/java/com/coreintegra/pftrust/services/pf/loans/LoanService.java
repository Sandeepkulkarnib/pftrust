package com.coreintegra.pftrust.services.pf.loans;

import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.loan.DistributedLoanAmountDTO;
import com.coreintegra.pftrust.dtos.pdf.loan.LoanHistorySheet;
import com.coreintegra.pftrust.dtos.pdf.loan.LoanReceipt;
import com.coreintegra.pftrust.dtos.pdf.loan.LoanWorkSheet;
import com.coreintegra.pftrust.entities.base.EmailAttachment;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.Document;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.*;
import com.coreintegra.pftrust.entities.pf.loan.dtos.LoanDetailsDTO;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementDocument;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementEmailStatus;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.projections.LoanContributionDTO;
import com.coreintegra.pftrust.util.Pair;
import org.apache.xpath.operations.Bool;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LoanService {

    CompletableFuture<String> saveFetchedLoanAsync(Loan loan, String pernNumber,
                                                   LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails);

    Loan save(Loan loan);

    Page<Loan> getLoans(String search, Integer size, Integer page);

    EssentialsDTO getLoanEssentials();

    List<Loan> getLoans(Employee employee);

    List<Loan> getLoans(Employee employee, Integer year);

    List<Loan> getLoans(Employee employee, Integer year, Integer month);

    List<LoanDetailsDTO> getLoanDetails(Employee employee);

    LoanWithDrawalsFinalDetails getTotalLoanWithDrawals(Employee employee, Integer year);

    Loan getLoan(String entityId);

    List<Loan> getCompletedLoans(Employee employee, Integer year);

    List<Loan> getCompletedLoans(Employee employee);

    void savePaymentDetails(Loan loan, Bank bank, PaymentMode paymentMode, String referenceNumber,
                            Date approvalDate, Date paymentDate);

    void approveLoan(Loan loan, JSONObject jsonObject) throws ParseException;

    void rejectLoan(Loan loan);

    LoanWorkSheet getWorkSheet(Loan loan) throws ParseException;

    LoanHistorySheet getLoanHistorySheet(Employee employee);

    LoanHistorySheet getLoanHistorySheet(Loan loan);

    LoanEmailStatus createEmailStatus(Loan loan);

    void updateEmailStatus(LoanEmailStatus loanEmailStatus);

    void sendConfirmationEmail(List<Pair<LoanEmailStatus, List<EmailAttachment>>> loanEmailStatusList);

    void sendConfirmationEmail(LoanEmailStatus loanEmailStatus, List<EmailAttachment> emailAttachments);

    void sendConfirmationEmail(String emailId, String employeeName, String accountNumber, String bankName,
                               Date paymentDate, BigDecimal amount, List<EmailAttachment> emailAttachments);

    LoanReceipt getLoanReceipt(Loan loan);

    List<LoanType> getEligibleLoanTypes(Employee employee) throws JPAException;

    List<LoanType> getEligibleLoanTypes(Loan loan) throws JPAException;

    void checkLoanTypeEligibility(Employee employee, LoanType loanType) throws JPAException;

    LoanType getLoanType(String entityId);

    LoanType getLoanTypeByCode(String code);

    JSONObject getEligibleLoanAmount(Employee employee, BigDecimal appliedAmount, BigDecimal totalCost,
                                     LoanType loanType);

    Loan create(Employee employee, LoanType loanType, Loan loan) throws JPAException;

    Loan update(Employee employee, LoanType loanType, Loan loan) throws JPAException;

    List<LoanDocument> getLoanDocuments(JSONArray jsonArray);

    void saveLoanDocuments(List<LoanDocument> loanDocuments, Loan loan);

    void updateLoanDocuments(JSONArray jsonArray);

    List<LoanEmailStatus> getEmailStatusListNotSent();

    List<LoanEmailStatus> getLoanEmailStatusList(List<String> entityIds);

    Loan getPendingLoan(Employee employee);

    List<LoanType> getLoanTypes();

    Boolean checkEligibilityByMaxNoOfWithdrawals(Employee employee, LoanType loanType);

    Boolean checkEligibilityByRetirementDate(Employee employee, LoanType loanType);

    Boolean checkEligibilityByNextEligibility(Employee employee, LoanType loanType);

    Boolean saveLoanType(LoanType loanType);
    
    void updateLoanStatus(Loan loan);

    List<Loan> distributeLoanAmountIntoTaxableAndNonTaxable(List<Loan> loans, Employee employee);

    DistributedLoanAmountDTO distributeLoanOnMemberContribution(BigDecimal openingBalanceNonTaxable, BigDecimal openingBalanceTaxable,
                                                                BigDecimal contributionNonTaxable, BigDecimal contributionTaxable,
                                                                BigDecimal loanAmount);

    DistributedLoanAmountDTO distributeLoanOnVpfContribution(BigDecimal openingBalanceNonTaxable, BigDecimal openingBalanceTaxable,
                                                  BigDecimal contributionNonTaxable, BigDecimal contributionTaxable,
                                                  BigDecimal loanAmount);



}
