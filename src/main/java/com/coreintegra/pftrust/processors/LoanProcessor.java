package com.coreintegra.pftrust.processors;

import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanStatus;
import com.coreintegra.pftrust.entities.pf.loan.LoanType;
import com.coreintegra.pftrust.entities.pf.loan.LoanWithDrawalsFinalDetails;
import com.coreintegra.pftrust.entities.pf.loan.dtos.LoanDTO;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LoanProcessor {

    public Loan process(LoanDTO item) throws Exception {

        Loan loan = new Loan();

        loan.setCreatedAtTimestamp(getDate(item.getBegda()).getTime());
        loan.setUpdatedAtTimestamp(getDate(item.getAedtm()).getTime());

        loan.setLoanApplicationDate(getDate(item.getDladt()));
        loan.setLoanApplicationReceivedDate(getDate(item.getAplRecDate()));
        loan.setLoanApprovalDate(getDate(item.getDataw()));
        loan.setLoanDisbursalDate(getDate(item.getDatdw()));

        loan.setAppliedAmount(new BigDecimal(item.getDarat()));

        loan.setTotalCost(new BigDecimal(item.getCostInvolved()));

        loan.setEligibleAmount(new BigDecimal(item.getDlaet()));

        loan.setLoanAmountOnPfBalance(new BigDecimal(item.getContrifrompf()));
        loan.setLoanAmountOnPfBaseSalary(new BigDecimal(item.getContrifrompf()));

        loan.setDateOfCompletionOfHouse(getDate(item.getDatecomplhouse()));

        loan.setDateOfFirstAlteration(getDate(item.getData1stalt()));

        loan.setPropertyEstimatedPrice(new BigDecimal(item.getPel()));

        loan.setStampDuty(new BigDecimal(item.getStampduty()));
        loan.setPropertyRegistration(new BigDecimal(item.getRegistration()));
        loan.setInsurance(new BigDecimal(item.getInsurance()));
        loan.setOther(new BigDecimal(item.getAnyother()));
        loan.setTotal(new BigDecimal(item.getTotal1()));

        loan.setFinancialInstName(item.getFinInt());
        loan.setRepaymentBank(item.getFinInt());
        loan.setRepaymentBankBranch(item.getFinInBr());
        loan.setRepaymentBankAccountNumber(item.getFinInAcno());

        loan.setEmployeeBank(item.getBankl());
        loan.setEmployeeBankBranch(item.getBrnch());
        loan.setEmployeeAccountNumber(item.getBankn());
        loan.setEmployeeBankIfscCode(item.getIfsc());
        loan.setEmployeeBankSwiftCode(item.getSwift());

        loan.setAltContactNumber(item.getMobileNo());
        loan.setAltEmail(item.getEmailId());

        loan.setLoanCreatedBy(item.getUname());
        loan.setApprovedBy(item.getAppro());

        loan.setAmountDisbursed(new BigDecimal(item.getTotPay()));

        loan.setDocumentNo(item.getDocno());

        LoanStatus loanStatus = new LoanStatus();

        if(item.getStatus() == null || item.getStatus().trim().equals("A")){
            loanStatus.setId(3L);
        }else {
            loanStatus.setId(2L);
        }

        loan.setLoanStatus(loanStatus);

        Bank bank = new Bank();
        bank.setName(item.getPfbank());

        loan.setBank(bank);

        loan.setPaymentMode(getPaymentMode(item.getPaymet()));

        loan.setPaymentDate(getDate(item.getChqdate()));

        Employee employee = new Employee();
        employee.setPernNumber(item.getPernr());

        loan.setEmployee(employee);

        LoanType loanType = new LoanType();
        loanType.setCode(item.getSubty());

        loan.setLoanType(loanType);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(3L);

        loan.setSapStatus(sapStatus);
        loan.setAmountDisbursed(new BigDecimal(item.getTotPay()));


        LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails = new LoanWithDrawalsFinalDetails();

        loanWithDrawalsFinalDetails.setLoan(loan);

        loanWithDrawalsFinalDetails.setLoanAmountOnMemContribution(new BigDecimal(item.getMemContPay()));
        loanWithDrawalsFinalDetails.setLoanAmountOnCompanyContribution(new BigDecimal(item.getCompContPay()));
        loanWithDrawalsFinalDetails.setLoanAmountOnVPFContribution(new BigDecimal(item.getEpfContPay()));


        if(item.getDataw() != null && !item.getDataw().trim().isEmpty()) {

            loanWithDrawalsFinalDetails.setYear(FinancialYearAndMonth.getFinancialYear(getDate(item.getDataw())));

            loanWithDrawalsFinalDetails.setMonth(FinancialYearAndMonth.getFinancialMonth(getDate(item.getDataw())));

        }

        loanWithDrawalsFinalDetails.setTotalContributionYearOpening(new BigDecimal(item.getOpenBal()));

        loanWithDrawalsFinalDetails.setMemberContributionCurrentYear(new BigDecimal(item.getMemCont()));
        loanWithDrawalsFinalDetails.setCompanyContributionCurrentYear(new BigDecimal(item.getCompCont()));
        loanWithDrawalsFinalDetails.setVpfContributionCurrentYear(new BigDecimal(item.getEpfCont()));
        loanWithDrawalsFinalDetails.setTotalContributionCurrentYear(new BigDecimal(item.getTotAll()));

        loanWithDrawalsFinalDetails.setCurrentYearTotalLoanWithDrawal(new BigDecimal(item.getLoanInYr()));

        loan.setLoanWithDrawalsFinalDetails(loanWithDrawalsFinalDetails);

        return loan;
    }

    private Date getDate(String date) throws ParseException {

        if(date == null || date.equals("null") || date.isEmpty()) {
            return null;
        }else {

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            format.setTimeZone(TimeZone.getTimeZone("IST"));
            return format.parse(date);
        }
    }


    private PaymentMode getPaymentMode(String paymet){

        PaymentMode paymentMode = new PaymentMode();

        if(paymet == null || paymet.trim().equals("A")){
            paymentMode.setId(1L);
        }else if (paymet.trim().equals("L")){
            paymentMode.setId(3L);
        }else if(paymet.trim().equals("I")){
            paymentMode.setId(4L);
        }else {
            paymentMode.setId(2L);
        }

        return paymentMode;
    }


}
