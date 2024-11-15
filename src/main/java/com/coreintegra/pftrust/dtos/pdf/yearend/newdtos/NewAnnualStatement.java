package com.coreintegra.pftrust.dtos.pdf.yearend.newdtos;

import com.coreintegra.pftrust.dtos.pdf.Nominee;
import com.coreintegra.pftrust.dtos.pdf.yearend.ContributionDTO;
import com.coreintegra.pftrust.dtos.pdf.yearend.LoanDetailsDTO;
import com.coreintegra.pftrust.dtos.pdf.yearend.TransferInDetailsDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanType;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.coreintegra.pftrust.util.NumberFormatter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;
import static com.coreintegra.pftrust.util.DateFormatterUtil.formatDate;
import static com.coreintegra.pftrust.util.DateFormatterUtil.formateDateInWord;

@XmlRootElement(name = "annualStatement")
public class NewAnnualStatement {

    private String name;
    private String pernNumber;
    private String pfNumber;
    private String tokenNumber;
    private String unitCode;
    private String deptCode;
    private String status;

    private List<Nominee> nominees;

    private Date yearOpeningDate;

    private Float interestRate;

    //    year opening
    private BigDecimal nonTaxableYearOpeningMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableYearOpeningMemberContribution = BigDecimal.ZERO;

    private BigDecimal yearOpeningCompanyContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableYearOpeningVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableYearOpeningVpfContribution = BigDecimal.ZERO;

    private BigDecimal yearOpeningTotalContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableInterestOnYearOpeningMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableInterestOnYearOpeningMemberContribution = BigDecimal.ZERO;

    private BigDecimal interestOnYearOpeningCompanyContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableInterestOnYearOpeningVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableInterestOnYearOpeningVpfContribution = BigDecimal.ZERO;

    private BigDecimal interestOnYearOpeningTotalContribution = BigDecimal.ZERO;


    //    current year

    private BigDecimal nonTaxableCurrentYearMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableCurrentYearMemberContribution = BigDecimal.ZERO;

    private BigDecimal currentYearCompanyContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableCurrentYearVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableCurrentYearVpfContribution = BigDecimal.ZERO;

    private BigDecimal currentYearTotalContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableInterestOnCurrentYearMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableInterestOnCurrentYearMemberContribution = BigDecimal.ZERO;

    private BigDecimal interestOnCurrentYearCompanyContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableInterestOnCurrentYearVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableInterestOnCurrentYearVpfContribution = BigDecimal.ZERO;

    private BigDecimal interestOnCurrentYearTotalContribution = BigDecimal.ZERO;

    //    transfer in
    private BigDecimal nonTaxableCurrentYearTransferInMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableCurrentYearTransferInMemberContribution = BigDecimal.ZERO;

    private BigDecimal currentYearTransferInCompanyContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableCurrentYearTransferInVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableCurrentYearTransferInVpfContribution = BigDecimal.ZERO;

    private BigDecimal currentYearTransferInTotalContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableInterestOnCurrentYearTransferInMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableInterestOnCurrentYearTransferInMemberContribution = BigDecimal.ZERO;

    private BigDecimal interestOnCurrentYearTransferInCompanyContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableInterestOnCurrentYearTransferInVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableInterestOnCurrentYearTransferInVpfContribution = BigDecimal.ZERO;

    private BigDecimal interestOnCurrentYearTransferInTotalContribution = BigDecimal.ZERO;

    //    transfer wl
    private BigDecimal nonTaxableCurrentYearLoanWithdrawalMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableCurrentYearLoanWithdrawalMemberContribution = BigDecimal.ZERO;

    private BigDecimal currentYearLoanWithdrawalCompanyContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableCurrentYearLoanWithdrawalVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableCurrentYearLoanWithdrawalVpfContribution = BigDecimal.ZERO;

    private BigDecimal currentYearLoanWithdrawalTotalContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableInterestOnCurrentYearLoanWithdrawalMemberContribution = BigDecimal.ZERO;

    private BigDecimal interestOnCurrentYearLoanWithdrawalCompanyContribution = BigDecimal.ZERO;

    private BigDecimal nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableInterestOnCurrentYearLoanWithdrawalVpfContribution = BigDecimal.ZERO;

    private BigDecimal interestOnCurrentYearLoanWithdrawalTotalContribution = BigDecimal.ZERO;

    private BigDecimal tdsOnInterestOnTaxableMemberContribution = BigDecimal.ZERO;
    private BigDecimal tdsOnInterestOnTaxableVpfContribution = BigDecimal.ZERO;

    private Date closingDate;

    private Date date;

    private Integer finantialYear;

    //    total contribution
    private BigDecimal totalMemberContribution = BigDecimal.ZERO;
    private BigDecimal totalCompanyContribution = BigDecimal.ZERO;
    private BigDecimal totalVpfContribution = BigDecimal.ZERO;
    private BigDecimal totalContribution = BigDecimal.ZERO;

    // total non taxable contribution
    private BigDecimal nonTaxableTotalMemberContribution = BigDecimal.ZERO;
    private BigDecimal nonTaxableTotalCompanyContribution = BigDecimal.ZERO;
    private BigDecimal nonTaxableTotalVpfContribution = BigDecimal.ZERO;
    private BigDecimal nonTaxableTotalContribution = BigDecimal.ZERO;

    // total taxable contribution
    private BigDecimal taxableTotalMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableTotalCompanyContribution = BigDecimal.ZERO;
    private BigDecimal taxableTotalVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableTotalContribution = BigDecimal.ZERO;

    private BigDecimal netContribution = BigDecimal.ZERO;

    private List<ContributionDTO> currentYearContributions;

    private ContributionDTO currentYearContributionTotal;

    private List<TransferInDetailsDTO> transferInDetails;

    private List<LoanDetailsDTO> loanDetailsDTOS;

    private List<NewContributionDTO> currentYearContributionsNew;

    private NewContributionDTO currentYearContributionsNewTotal;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "pernNumber")
    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    @XmlElement(name = "pfNumber")
    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    @XmlElement(name = "tokenNumber")
    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    @XmlElement(name = "unitCode")
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "nominee")
    public List<Nominee> getNominees() {
        return nominees;
    }

    public void setNominees(List<Nominee> nominees) {
        this.nominees = nominees;
    }

    @XmlElement(name = "yearOpeningDate")
    public String getYearOpeningDate() {
        return formatDate(yearOpeningDate);
    }

    public void setYearOpeningDate(Date yearOpeningDate) {
        this.yearOpeningDate = yearOpeningDate;
    }

    @XmlElement(name = "interestRate")
    public String getInterestRate() {
        return NumberFormatter.formatNumbers(this.interestRate);
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    @XmlElement(name = "nonTaxableYearOpeningMemberContribution")
    public String getNonTaxableYearOpeningMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableYearOpeningMemberContribution);
    }

    public void setNonTaxableYearOpeningMemberContribution(BigDecimal nonTaxableYearOpeningMemberContribution) {
        this.nonTaxableYearOpeningMemberContribution = nonTaxableYearOpeningMemberContribution;
    }

    @XmlElement(name = "taxableYearOpeningMemberContribution")
    public String getTaxableYearOpeningMemberContribution() {
        return NumberFormatter.formatNumbers(taxableYearOpeningMemberContribution);
    }

    public void setTaxableYearOpeningMemberContribution(BigDecimal taxableYearOpeningMemberContribution) {
        this.taxableYearOpeningMemberContribution = taxableYearOpeningMemberContribution;
    }

    @XmlElement(name = "yearOpeningMemberContribution")
    public String getYearOpeningMemberContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(nonTaxableYearOpeningMemberContribution)
                        .add(avoidNull(taxableYearOpeningMemberContribution)));
    }

    @XmlElement(name = "yearOpeningCompanyContribution")
    public String getYearOpeningCompanyContribution() {
        return NumberFormatter.formatNumbers(yearOpeningCompanyContribution);
    }

    public void setYearOpeningCompanyContribution(BigDecimal yearOpeningCompanyContribution) {
        this.yearOpeningCompanyContribution = yearOpeningCompanyContribution;
    }

    @XmlElement(name = "nonTaxableYearOpeningVpfContribution")
    public String getNonTaxableYearOpeningVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableYearOpeningVpfContribution);
    }

    public void setNonTaxableYearOpeningVpfContribution(BigDecimal nonTaxableYearOpeningVpfContribution) {
        this.nonTaxableYearOpeningVpfContribution = nonTaxableYearOpeningVpfContribution;
    }

    @XmlElement(name = "taxableYearOpeningVpfContribution")
    public String getTaxableYearOpeningVpfContribution() {
        return NumberFormatter.formatNumbers(taxableYearOpeningVpfContribution);
    }

    public void setTaxableYearOpeningVpfContribution(BigDecimal taxableYearOpeningVpfContribution) {
        this.taxableYearOpeningVpfContribution = taxableYearOpeningVpfContribution;
    }

    @XmlElement(name = "yearOpeningVpfContribution")
    public String getYearOpeningVpfContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableYearOpeningVpfContribution)
                        .add(avoidNull(taxableYearOpeningVpfContribution)));
    }


    @XmlElement(name = "nonTaxableYearOpeningTotalContribution")
    public String getNonTaxableYearOpeningTotalContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(nonTaxableYearOpeningMemberContribution)
                        .add(avoidNull(yearOpeningCompanyContribution))
                        .add(avoidNull(nonTaxableYearOpeningVpfContribution)));
    }

    @XmlElement(name = "taxableYearOpeningTotalContribution")
    public String getTaxableYearOpeningTotalContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(taxableYearOpeningMemberContribution)
                        .add(avoidNull(taxableYearOpeningVpfContribution)));
    }

    @XmlElement(name = "yearOpeningTotalContribution")
    public String getYearOpeningTotalContribution() {
        return NumberFormatter
                .formatNumbers(yearOpeningTotalContribution);
    }

    public void setYearOpeningTotalContribution(BigDecimal yearOpeningTotalContribution) {
        this.yearOpeningTotalContribution = yearOpeningTotalContribution;
    }

    @XmlElement(name = "nonTaxableInterestOnYearOpeningMemberContribution")
    public String getNonTaxableInterestOnYearOpeningMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableInterestOnYearOpeningMemberContribution);
    }

    public void setNonTaxableInterestOnYearOpeningMemberContribution(BigDecimal nonTaxableInterestOnYearOpeningMemberContribution) {
        this.nonTaxableInterestOnYearOpeningMemberContribution = nonTaxableInterestOnYearOpeningMemberContribution;
    }

    @XmlElement(name = "taxableInterestOnYearOpeningMemberContribution")
    public String getTaxableInterestOnYearOpeningMemberContribution() {
        return NumberFormatter.formatNumbers(taxableInterestOnYearOpeningMemberContribution);
    }

    public void setTaxableInterestOnYearOpeningMemberContribution(BigDecimal taxableInterestOnYearOpeningMemberContribution) {
        this.taxableInterestOnYearOpeningMemberContribution = taxableInterestOnYearOpeningMemberContribution;
    }

    @XmlElement(name = "interestOnYearOpeningCompanyContribution")
    public String getInterestOnYearOpeningCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnYearOpeningCompanyContribution);
    }

    public void setInterestOnYearOpeningCompanyContribution(BigDecimal interestOnYearOpeningCompanyContribution) {
        this.interestOnYearOpeningCompanyContribution = interestOnYearOpeningCompanyContribution;
    }

    @XmlElement(name = "nonTaxableInterestOnYearOpeningVpfContribution")
    public String getNonTaxableInterestOnYearOpeningVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableInterestOnYearOpeningVpfContribution);
    }

    public void setNonTaxableInterestOnYearOpeningVpfContribution(BigDecimal nonTaxableInterestOnYearOpeningVpfContribution) {
        this.nonTaxableInterestOnYearOpeningVpfContribution = nonTaxableInterestOnYearOpeningVpfContribution;
    }


    @XmlElement(name = "nonTaxableInterestOnYearOpeningTotalContribution")
    public String getNonTaxableInterestOnYearOpeningTotalContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(nonTaxableInterestOnYearOpeningMemberContribution)
                        .add(avoidNull(interestOnYearOpeningCompanyContribution))
                        .add(avoidNull(nonTaxableInterestOnYearOpeningVpfContribution)));
    }


    @XmlElement(name = "taxableInterestOnYearOpeningVpfContribution")
    public String getTaxableInterestOnYearOpeningVpfContribution() {
        return NumberFormatter.formatNumbers(taxableInterestOnYearOpeningVpfContribution);
    }

    public void setTaxableInterestOnYearOpeningVpfContribution(BigDecimal taxableInterestOnYearOpeningVpfContribution) {
        this.taxableInterestOnYearOpeningVpfContribution = taxableInterestOnYearOpeningVpfContribution;
    }

    @XmlElement(name = "taxableInterestOnYearOpeningTotalContribution")
    public String getTaxableInterestOnYearOpeningTotalContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(taxableInterestOnYearOpeningMemberContribution)
                        .add(avoidNull(taxableInterestOnYearOpeningVpfContribution)));
    }

    @XmlElement(name = "interestOnYearOpeningMemberContribution")
    public String getInterestOnYearOpeningMemberContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(nonTaxableInterestOnYearOpeningMemberContribution)
                        .add(avoidNull(taxableInterestOnYearOpeningMemberContribution)));
    }

    @XmlElement(name = "interestOnYearOpeningVpfContribution")
    public String getInterestOnYearOpeningVpfContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(nonTaxableInterestOnYearOpeningVpfContribution)
                        .add(avoidNull(taxableInterestOnYearOpeningVpfContribution)));
    }

    @XmlElement(name = "interestOnYearOpeningTotalContribution")
    public String getInterestOnYearOpeningTotalContribution() {
        return NumberFormatter.formatNumbers(interestOnYearOpeningTotalContribution);
    }

    public void setInterestOnYearOpeningTotalContribution(BigDecimal interestOnYearOpeningTotalContribution) {
        this.interestOnYearOpeningTotalContribution = interestOnYearOpeningTotalContribution;
    }

    @XmlElement(name = "nonTaxableCurrentYearMemberContribution")
    public String getNonTaxableCurrentYearMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableCurrentYearMemberContribution);
    }

    public void setNonTaxableCurrentYearMemberContribution(BigDecimal nonTaxableCurrentYearMemberContribution) {
        this.nonTaxableCurrentYearMemberContribution = nonTaxableCurrentYearMemberContribution;
    }

    @XmlElement(name = "taxableCurrentYearMemberContribution")
    public String getTaxableCurrentYearMemberContribution() {
        return NumberFormatter.formatNumbers(taxableCurrentYearMemberContribution);
    }

    public void setTaxableCurrentYearMemberContribution(BigDecimal taxableCurrentYearMemberContribution) {
        this.taxableCurrentYearMemberContribution = taxableCurrentYearMemberContribution;
    }

    @XmlElement(name = "currentYearCompanyContribution")
    public String getCurrentYearCompanyContribution() {
        return NumberFormatter.formatNumbers(currentYearCompanyContribution);
    }

    public void setCurrentYearCompanyContribution(BigDecimal currentYearCompanyContribution) {
        this.currentYearCompanyContribution = currentYearCompanyContribution;
    }

    @XmlElement(name = "nonTaxableCurrentYearVpfContribution")
    public String getNonTaxableCurrentYearVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableCurrentYearVpfContribution);
    }

    public void setNonTaxableCurrentYearVpfContribution(BigDecimal nonTaxableCurrentYearVpfContribution) {
        this.nonTaxableCurrentYearVpfContribution = nonTaxableCurrentYearVpfContribution;
    }


    @XmlElement(name = "nonTaxableCurrentYearTotalContribution")
    public String getNonTaxableCurrentYearTotalContribution() {
        return NumberFormatter.formatNumbers(        avoidNull(nonTaxableCurrentYearMemberContribution)
                .add(avoidNull(currentYearCompanyContribution))
                .add(avoidNull(nonTaxableCurrentYearVpfContribution)));
    }


    @XmlElement(name = "taxableCurrentYearVpfContribution")
    public String getTaxableCurrentYearVpfContribution() {
        return NumberFormatter.formatNumbers(taxableCurrentYearVpfContribution);
    }

    public void setTaxableCurrentYearVpfContribution(BigDecimal taxableCurrentYearVpfContribution) {
        this.taxableCurrentYearVpfContribution = taxableCurrentYearVpfContribution;
    }

    @XmlElement(name = "taxableCurrentYearTotalContribution")
    public String getTaxableCurrentYearTotalContribution() {
        return NumberFormatter.formatNumbers(
                avoidNull(taxableCurrentYearMemberContribution)
                .add(avoidNull(taxableCurrentYearVpfContribution)));
    }

    @XmlElement(name = "currentYearTotalContribution")
    public String getCurrentYearTotalContribution() {
        return NumberFormatter.formatNumbers(currentYearTotalContribution);
    }

    public void setCurrentYearTotalContribution(BigDecimal currentYearTotalContribution) {
        this.currentYearTotalContribution = currentYearTotalContribution;
    }


    @XmlElement(name = "currentYearMemberContribution")
    public String getCurrentYearMemberContribution() {
        return NumberFormatter.formatNumbers(
                avoidNull(nonTaxableCurrentYearMemberContribution)
                        .add(avoidNull(taxableCurrentYearMemberContribution))
        );
    }


    @XmlElement(name = "currentYearVpfContribution")
    public String getCurrentYearVpfContribution() {
        return NumberFormatter.formatNumbers(
                avoidNull(nonTaxableCurrentYearVpfContribution)
                        .add(avoidNull(taxableCurrentYearVpfContribution))
        );
    }



    @XmlElement(name = "nonTaxableInterestOnCurrentYearMemberContribution")
    public String getNonTaxableInterestOnCurrentYearMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableInterestOnCurrentYearMemberContribution);
    }

    public void setNonTaxableInterestOnCurrentYearMemberContribution(BigDecimal nonTaxableInterestOnCurrentYearMemberContribution) {
        this.nonTaxableInterestOnCurrentYearMemberContribution = nonTaxableInterestOnCurrentYearMemberContribution;
    }

    @XmlElement(name = "taxableInterestOnCurrentYearMemberContribution")
    public String getTaxableInterestOnCurrentYearMemberContribution() {
        return NumberFormatter.formatNumbers(taxableInterestOnCurrentYearMemberContribution);
    }

    public void setTaxableInterestOnCurrentYearMemberContribution(BigDecimal taxableInterestOnCurrentYearMemberContribution) {
        this.taxableInterestOnCurrentYearMemberContribution = taxableInterestOnCurrentYearMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearCompanyContribution")
    public String getInterestOnCurrentYearCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearCompanyContribution);
    }

    public void setInterestOnCurrentYearCompanyContribution(BigDecimal interestOnCurrentYearCompanyContribution) {
        this.interestOnCurrentYearCompanyContribution = interestOnCurrentYearCompanyContribution;
    }


    @XmlElement(name = "nonTaxableInterestOnCurrentYearVpfContribution")
    public String getNonTaxableInterestOnCurrentYearVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableInterestOnCurrentYearVpfContribution);
    }

    public void setNonTaxableInterestOnCurrentYearVpfContribution(BigDecimal nonTaxableInterestOnCurrentYearVpfContribution) {
        this.nonTaxableInterestOnCurrentYearVpfContribution = nonTaxableInterestOnCurrentYearVpfContribution;
    }

    @XmlElement(name = "taxableInterestOnCurrentYearVpfContribution")
    public String getTaxableInterestOnCurrentYearVpfContribution() {
        return NumberFormatter.formatNumbers(taxableInterestOnCurrentYearVpfContribution);
    }

    public void setTaxableInterestOnCurrentYearVpfContribution(BigDecimal taxableInterestOnCurrentYearVpfContribution) {
        this.taxableInterestOnCurrentYearVpfContribution = taxableInterestOnCurrentYearVpfContribution;
    }


    @XmlElement(name = "nonTaxableInterestOnCurrentYearTotalContribution")
    public String getNonTaxableInterestOnCurrentYearTotalContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(nonTaxableInterestOnCurrentYearMemberContribution)
                        .add(avoidNull(interestOnCurrentYearCompanyContribution))
                        .add(avoidNull(nonTaxableInterestOnCurrentYearVpfContribution)));
    }

    @XmlElement(name = "taxableInterestOnCurrentYearTotalContribution")
    public String getTaxableInterestOnCurrentYearTotalContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(taxableInterestOnCurrentYearMemberContribution)
                        .add(avoidNull(taxableInterestOnCurrentYearVpfContribution)));
    }

    @XmlElement(name = "interestOnCurrentYearMemberContribution")
    public String getInterestOnCurrentYearMemberContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(nonTaxableInterestOnCurrentYearMemberContribution)
                        .add(avoidNull(taxableInterestOnCurrentYearMemberContribution)));
    }

    @XmlElement(name = "interestOnCurrentYearVpfContribution")
    public String getInterestOnCurrentYearVpfContribution() {
        return NumberFormatter
                .formatNumbers(avoidNull(nonTaxableInterestOnCurrentYearVpfContribution)
                        .add(avoidNull(taxableInterestOnCurrentYearVpfContribution)));
    }

    @XmlElement(name = "interestOnCurrentYearTotalContribution")
    public String getInterestOnCurrentYearTotalContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearTotalContribution);
    }

    public void setInterestOnCurrentYearTotalContribution(BigDecimal interestOnCurrentYearTotalContribution) {
        this.interestOnCurrentYearTotalContribution = interestOnCurrentYearTotalContribution;
    }

    @XmlElement(name = "nonTaxableCurrentYearTransferInMemberContribution")
    public String getNonTaxableCurrentYearTransferInMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableCurrentYearTransferInMemberContribution);
    }

    public void setNonTaxableCurrentYearTransferInMemberContribution(BigDecimal nonTaxableCurrentYearTransferInMemberContribution) {
        this.nonTaxableCurrentYearTransferInMemberContribution = nonTaxableCurrentYearTransferInMemberContribution;
    }

    @XmlElement(name = "taxableCurrentYearTransferInMemberContribution")
    public String getTaxableCurrentYearTransferInMemberContribution() {
        return NumberFormatter.formatNumbers(taxableCurrentYearTransferInMemberContribution);
    }

    public void setTaxableCurrentYearTransferInMemberContribution(BigDecimal taxableCurrentYearTransferInMemberContribution) {
        this.taxableCurrentYearTransferInMemberContribution = taxableCurrentYearTransferInMemberContribution;
    }

    @XmlElement(name = "currentYearTransferInCompanyContribution")
    public String getCurrentYearTransferInCompanyContribution() {
        return NumberFormatter.formatNumbers(currentYearTransferInCompanyContribution);
    }

    public void setCurrentYearTransferInCompanyContribution(BigDecimal currentYearTransferInCompanyContribution) {
        this.currentYearTransferInCompanyContribution = currentYearTransferInCompanyContribution;
    }

    @XmlElement(name = "nonTaxableCurrentYearTransferInVpfContribution")
    public String getNonTaxableCurrentYearTransferInVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableCurrentYearTransferInVpfContribution);
    }

    public void setNonTaxableCurrentYearTransferInVpfContribution(BigDecimal nonTaxableCurrentYearTransferInVpfContribution) {
        this.nonTaxableCurrentYearTransferInVpfContribution = nonTaxableCurrentYearTransferInVpfContribution;
    }

    @XmlElement(name = "taxableCurrentYearTransferInVpfContribution")
    public String getTaxableCurrentYearTransferInVpfContribution() {
        return NumberFormatter.formatNumbers(taxableCurrentYearTransferInVpfContribution);
    }

    public void setTaxableCurrentYearTransferInVpfContribution(BigDecimal taxableCurrentYearTransferInVpfContribution) {
        this.taxableCurrentYearTransferInVpfContribution = taxableCurrentYearTransferInVpfContribution;
    }

    @XmlElement(name = "nonTaxableCurrentYearTransferInTotalContribution")
    public String getNonTaxableCurrentYearTransferInTotalContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableCurrentYearTransferInMemberContribution)
                                .add(avoidNull(currentYearTransferInCompanyContribution))
                                .add(avoidNull(nonTaxableCurrentYearTransferInVpfContribution))
                );
    }

    @XmlElement(name = "taxableCurrentYearTransferInTotalContribution")
    public String getTaxableCurrentYearTransferInTotalContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(taxableCurrentYearTransferInMemberContribution)
                                .add(avoidNull(taxableCurrentYearTransferInVpfContribution))
                );
    }

    @XmlElement(name = "currentYearTransferInMemberContribution")
    public String getCurrentYearTransferInMemberContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableCurrentYearTransferInMemberContribution)
                                .add(avoidNull(taxableCurrentYearTransferInMemberContribution))
                );
    }

    @XmlElement(name = "currentYearTransferInVpfContribution")
    public String getCurrentYearTransferInVpfContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableCurrentYearTransferInVpfContribution)
                                .add(avoidNull(taxableCurrentYearTransferInVpfContribution))
                );
    }

    @XmlElement(name = "currentYearTransferInTotalContribution")
    public String getCurrentYearTransferInTotalContribution() {
        return NumberFormatter.formatNumbers(currentYearTransferInTotalContribution);
    }

    public void setCurrentYearTransferInTotalContribution(BigDecimal currentYearTransferInTotalContribution) {
        this.currentYearTransferInTotalContribution = currentYearTransferInTotalContribution;
    }

    @XmlElement(name = "nonTaxableInterestOnCurrentYearTransferInMemberContribution")
    public String getNonTaxableInterestOnCurrentYearTransferInMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableInterestOnCurrentYearTransferInMemberContribution);
    }

    public void setNonTaxableInterestOnCurrentYearTransferInMemberContribution(BigDecimal nonTaxableInterestOnCurrentYearTransferInMemberContribution) {
        this.nonTaxableInterestOnCurrentYearTransferInMemberContribution = nonTaxableInterestOnCurrentYearTransferInMemberContribution;
    }

    @XmlElement(name = "taxableInterestOnCurrentYearTransferInMemberContribution")
    public String getTaxableInterestOnCurrentYearTransferInMemberContribution() {
        return NumberFormatter.formatNumbers(taxableInterestOnCurrentYearTransferInMemberContribution);
    }

    public void setTaxableInterestOnCurrentYearTransferInMemberContribution(BigDecimal taxableInterestOnCurrentYearTransferInMemberContribution) {
        this.taxableInterestOnCurrentYearTransferInMemberContribution = taxableInterestOnCurrentYearTransferInMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInCompanyContribution")
    public String getInterestOnCurrentYearTransferInCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearTransferInCompanyContribution);
    }

    public void setInterestOnCurrentYearTransferInCompanyContribution(BigDecimal interestOnCurrentYearTransferInCompanyContribution) {
        this.interestOnCurrentYearTransferInCompanyContribution = interestOnCurrentYearTransferInCompanyContribution;
    }

    @XmlElement(name = "nonTaxableInterestOnCurrentYearTransferInVpfContribution")
    public String getNonTaxableInterestOnCurrentYearTransferInVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableInterestOnCurrentYearTransferInVpfContribution);
    }

    public void setNonTaxableInterestOnCurrentYearTransferInVpfContribution(BigDecimal nonTaxableInterestOnCurrentYearTransferInVpfContribution) {
        this.nonTaxableInterestOnCurrentYearTransferInVpfContribution = nonTaxableInterestOnCurrentYearTransferInVpfContribution;
    }

    @XmlElement(name = "taxableInterestOnCurrentYearTransferInVpfContribution")
    public String getTaxableInterestOnCurrentYearTransferInVpfContribution() {
        return NumberFormatter.formatNumbers(taxableInterestOnCurrentYearTransferInVpfContribution);
    }

    public void setTaxableInterestOnCurrentYearTransferInVpfContribution(BigDecimal taxableInterestOnCurrentYearTransferInVpfContribution) {
        this.taxableInterestOnCurrentYearTransferInVpfContribution = taxableInterestOnCurrentYearTransferInVpfContribution;
    }


    @XmlElement(name = "nonTaxableInterestOnCurrentYearTransferInTotalContribution")
    public String getNonTaxableInterestOnCurrentYearTransferInTotalContribution() {
        return NumberFormatter.formatNumbers(
                avoidNull(nonTaxableInterestOnCurrentYearTransferInMemberContribution)
                        .add(avoidNull(interestOnCurrentYearTransferInCompanyContribution))
                        .add(avoidNull(nonTaxableInterestOnCurrentYearTransferInVpfContribution))
        );
    }

    @XmlElement(name = "taxableInterestOnCurrentYearTransferInTotalContribution")
    public String getTaxableInterestOnCurrentYearTransferInTotalContribution() {
        return NumberFormatter.formatNumbers(
                avoidNull(taxableInterestOnCurrentYearTransferInMemberContribution)
                        .add(avoidNull(taxableInterestOnCurrentYearTransferInVpfContribution))
        );
    }

    @XmlElement(name = "interestOnCurrentYearTransferInMemberContribution")
    public String getInterestOnCurrentYearTransferInMemberContribution() {
        return NumberFormatter.formatNumbers(
                avoidNull(nonTaxableInterestOnCurrentYearTransferInMemberContribution)
                .add(avoidNull(taxableInterestOnCurrentYearTransferInMemberContribution))
        );
    }

    @XmlElement(name = "interestOnCurrentYearTransferInVpfContribution")
    public String getInterestOnCurrentYearTransferInVpfContribution() {
        return NumberFormatter.formatNumbers(
                avoidNull(nonTaxableInterestOnCurrentYearTransferInVpfContribution)
                .add(avoidNull(taxableInterestOnCurrentYearTransferInVpfContribution))
        );
    }

    @XmlElement(name = "interestOnCurrentYearTransferInTotalContribution")
    public String getInterestOnCurrentYearTransferInTotalContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearTransferInTotalContribution);
    }

    public void setInterestOnCurrentYearTransferInTotalContribution(BigDecimal interestOnCurrentYearTransferInTotalContribution) {
        this.interestOnCurrentYearTransferInTotalContribution = interestOnCurrentYearTransferInTotalContribution;
    }


    @XmlElement(name = "nonTaxableCurrentYearLoanWithdrawalMemberContribution")
    public String getNonTaxableCurrentYearLoanWithdrawalMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableCurrentYearLoanWithdrawalMemberContribution);
    }

    public void setNonTaxableCurrentYearLoanWithdrawalMemberContribution(BigDecimal nonTaxableCurrentYearLoanWithdrawalMemberContribution) {
        this.nonTaxableCurrentYearLoanWithdrawalMemberContribution = nonTaxableCurrentYearLoanWithdrawalMemberContribution;
    }

    @XmlElement(name = "taxableCurrentYearLoanWithdrawalMemberContribution")
    public String getTaxableCurrentYearLoanWithdrawalMemberContribution() {
        return NumberFormatter.formatNumbers(taxableCurrentYearLoanWithdrawalMemberContribution);
    }

    public void setTaxableCurrentYearLoanWithdrawalMemberContribution(BigDecimal taxableCurrentYearLoanWithdrawalMemberContribution) {
        this.taxableCurrentYearLoanWithdrawalMemberContribution = taxableCurrentYearLoanWithdrawalMemberContribution;
    }

    @XmlElement(name = "currentYearLoanWithdrawalCompanyContribution")
    public String getCurrentYearLoanWithdrawalCompanyContribution() {
        return NumberFormatter.formatNumbers(currentYearLoanWithdrawalCompanyContribution);
    }

    public void setCurrentYearLoanWithdrawalCompanyContribution(BigDecimal currentYearLoanWithdrawalCompanyContribution) {
        this.currentYearLoanWithdrawalCompanyContribution = currentYearLoanWithdrawalCompanyContribution;
    }

    @XmlElement(name = "nonTaxableCurrentYearLoanWithdrawalVpfContribution")
    public String getNonTaxableCurrentYearLoanWithdrawalVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableCurrentYearLoanWithdrawalVpfContribution);
    }

    public void setNonTaxableCurrentYearLoanWithdrawalVpfContribution(BigDecimal nonTaxableCurrentYearLoanWithdrawalVpfContribution) {
        this.nonTaxableCurrentYearLoanWithdrawalVpfContribution = nonTaxableCurrentYearLoanWithdrawalVpfContribution;
    }

    @XmlElement(name = "taxableCurrentYearLoanWithdrawalVpfContribution")
    public String getTaxableCurrentYearLoanWithdrawalVpfContribution() {
        return NumberFormatter.formatNumbers(taxableCurrentYearLoanWithdrawalVpfContribution);
    }

    public void setTaxableCurrentYearLoanWithdrawalVpfContribution(BigDecimal taxableCurrentYearLoanWithdrawalVpfContribution) {
        this.taxableCurrentYearLoanWithdrawalVpfContribution = taxableCurrentYearLoanWithdrawalVpfContribution;
    }

    @XmlElement(name = "nonTaxableCurrentYearLoanWithdrawalTotalContribution")
    public String getNonTaxableCurrentYearLoanWithdrawalTotalContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableCurrentYearLoanWithdrawalMemberContribution)
                                .add(avoidNull(currentYearLoanWithdrawalCompanyContribution))
                                .add(avoidNull(nonTaxableCurrentYearLoanWithdrawalVpfContribution))
                );
    }

    @XmlElement(name = "taxableCurrentYearLoanWithdrawalTotalContribution")
    public String getTaxableCurrentYearLoanWithdrawalTotalContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(taxableCurrentYearLoanWithdrawalMemberContribution)
                                .add(avoidNull(taxableCurrentYearLoanWithdrawalVpfContribution))
                );
    }

    @XmlElement(name = "currentYearLoanWithdrawalMemberContribution")
    public String getCurrentYearLoanWithdrawalMemberContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableCurrentYearLoanWithdrawalMemberContribution)
                        .add(taxableCurrentYearLoanWithdrawalMemberContribution)
                );
    }


    @XmlElement(name = "currentYearLoanWithdrawalVpfContribution")
    public String getCurrentYearLoanWithdrawalVpfContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableCurrentYearLoanWithdrawalVpfContribution)
                                .add(taxableCurrentYearLoanWithdrawalVpfContribution)
                );
    }

    @XmlElement(name = "currentYearLoanWithdrawalTotalContribution")
    public String getCurrentYearLoanWithdrawalTotalContribution() {
        return NumberFormatter.formatNumbers(currentYearLoanWithdrawalTotalContribution);
    }

    public void setCurrentYearLoanWithdrawalTotalContribution(BigDecimal currentYearLoanWithdrawalTotalContribution) {
        this.currentYearLoanWithdrawalTotalContribution = currentYearLoanWithdrawalTotalContribution;
    }

    @XmlElement(name = "nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution")
    public String getNonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution);
    }

    public void setNonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution(BigDecimal nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution) {
        this.nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution = nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution;
    }

    @XmlElement(name = "taxableInterestOnCurrentYearLoanWithdrawalMemberContribution")
    public String getTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution() {
        return NumberFormatter.formatNumbers(taxableInterestOnCurrentYearLoanWithdrawalMemberContribution);
    }

    public void setTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution(BigDecimal taxableInterestOnCurrentYearLoanWithdrawalMemberContribution) {
        this.taxableInterestOnCurrentYearLoanWithdrawalMemberContribution = taxableInterestOnCurrentYearLoanWithdrawalMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalCompanyContribution")
    public String getInterestOnCurrentYearLoanWithdrawalCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearLoanWithdrawalCompanyContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalCompanyContribution(BigDecimal interestOnCurrentYearLoanWithdrawalCompanyContribution) {
        this.interestOnCurrentYearLoanWithdrawalCompanyContribution = interestOnCurrentYearLoanWithdrawalCompanyContribution;
    }

    @XmlElement(name = "nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution")
    public String getNonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution);
    }

    public void setNonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution(BigDecimal nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution) {
        this.nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution = nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution;
    }

    @XmlElement(name = "taxableInterestOnCurrentYearLoanWithdrawalVpfContribution")
    public String getTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution() {
        return NumberFormatter.formatNumbers(taxableInterestOnCurrentYearLoanWithdrawalVpfContribution);
    }

    public void setTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution(BigDecimal taxableInterestOnCurrentYearLoanWithdrawalVpfContribution) {
        this.taxableInterestOnCurrentYearLoanWithdrawalVpfContribution = taxableInterestOnCurrentYearLoanWithdrawalVpfContribution;
    }

    @XmlElement(name = "nonTaxableInterestOnCurrentYearLoanWithdrawalTotalContribution")
    public String getNonTaxableInterestOnCurrentYearLoanWithdrawalTotalContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution)
                                .add(avoidNull(nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution))
                );
    }

    @XmlElement(name = "taxableInterestOnCurrentYearLoanWithdrawalTotalContribution")
    public String getTaxableInterestOnCurrentYearLoanWithdrawalTotalContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(taxableInterestOnCurrentYearLoanWithdrawalMemberContribution)
                                .add(avoidNull(taxableInterestOnCurrentYearLoanWithdrawalVpfContribution))
                );
    }


    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalMemberContribution")
    public String getInterestOnCurrentYearLoanWithdrawalMemberContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution)
                        .add(avoidNull(taxableInterestOnCurrentYearLoanWithdrawalMemberContribution))
                );
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalVpfContribution")
    public String getInterestOnCurrentYearLoanWithdrawalVpfContribution() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution)
                                .add(avoidNull(taxableInterestOnCurrentYearLoanWithdrawalVpfContribution))
                );
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalTotalContribution")
    public String getInterestOnCurrentYearLoanWithdrawalTotalContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearLoanWithdrawalTotalContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalTotalContribution(BigDecimal interestOnCurrentYearLoanWithdrawalTotalContribution) {
        this.interestOnCurrentYearLoanWithdrawalTotalContribution = interestOnCurrentYearLoanWithdrawalTotalContribution;
    }

    @XmlElement(name = "tdsOnInterestOnTaxableMemberContribution")
    public String getTdsOnInterestOnTaxableMemberContribution() {
        return NumberFormatter.formatNumbers(tdsOnInterestOnTaxableMemberContribution);
    }

    public void setTdsOnInterestOnTaxableMemberContribution(BigDecimal tdsOnInterestOnTaxableMemberContribution) {
        this.tdsOnInterestOnTaxableMemberContribution = tdsOnInterestOnTaxableMemberContribution;
    }

    @XmlElement(name = "tdsOnInterestOnTaxableVpfContribution")
    public String getTdsOnInterestOnTaxableVpfContribution() {
        return NumberFormatter.formatNumbers(tdsOnInterestOnTaxableVpfContribution);
    }

    public void setTdsOnInterestOnTaxableVpfContribution(BigDecimal tdsOnInterestOnTaxableVpfContribution) {
        this.tdsOnInterestOnTaxableVpfContribution = tdsOnInterestOnTaxableVpfContribution;
    }

    @XmlElement(name = "tdsOnInterestTotal")
    public String getTdsOnInterestTotal() {
        return NumberFormatter
                .formatNumbers(
                        avoidNull(tdsOnInterestOnTaxableMemberContribution)
                                .add(avoidNull(tdsOnInterestOnTaxableVpfContribution))
                );
    }


    @XmlElement(name = "closingDate")
    public String getClosingDate() {
        return formatDate(closingDate);
    }

    @XmlElement(name = "closingDateWord")
    public String getClosingDateWord() {
        return formateDateInWord(closingDate);
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    @XmlElement(name = "date")
    public String getDate() {
        return formatDate(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFinantialYear() {
        return finantialYear;
    }

    public void setFinantialYear(Integer finantialYear) {
        this.finantialYear = finantialYear;
    }

    @XmlElement(name = "totalMemberContribution")
    public String getTotalMemberContribution() {
        return NumberFormatter.formatNumbers(totalMemberContribution);
    }

    public void setTotalMemberContribution(BigDecimal totalMemberContribution) {
        this.totalMemberContribution = totalMemberContribution;
    }

    @XmlElement(name = "totalCompanyContribution")
    public String getTotalCompanyContribution() {
        return NumberFormatter.formatNumbers(totalCompanyContribution);
    }

    public void setTotalCompanyContribution(BigDecimal totalCompanyContribution) {
        this.totalCompanyContribution = totalCompanyContribution;
    }

    @XmlElement(name = "totalVpfContribution")
    public String getTotalVpfContribution() {
        return NumberFormatter.formatNumbers(totalVpfContribution);
    }

    public void setTotalVpfContribution(BigDecimal totalVpfContribution) {
        this.totalVpfContribution = totalVpfContribution;
    }

    @XmlElement(name = "totalContribution")
    public String getTotalContribution() {
        return NumberFormatter.formatNumbers(totalContribution);
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    @XmlElement(name = "netContribution")
    public String getNetContribution() {
        return NumberFormatter.formatNumbers(netContribution);
    }

    public void setNetContribution(BigDecimal netContribution) {
        this.netContribution = netContribution;
    }

    @XmlElement(name = "nonTaxableTotalMemberContribution")
    public String getNonTaxableTotalMemberContribution() {
        return NumberFormatter.formatNumbers(nonTaxableTotalMemberContribution);
    }

    public void setNonTaxableTotalMemberContribution(BigDecimal nonTaxableTotalMemberContribution) {
        this.nonTaxableTotalMemberContribution = nonTaxableTotalMemberContribution;
    }

    @XmlElement(name = "nonTaxableTotalCompanyContribution")
    public String getNonTaxableTotalCompanyContribution() {
        return NumberFormatter.formatNumbers(nonTaxableTotalCompanyContribution);
    }

    public void setNonTaxableTotalCompanyContribution(BigDecimal nonTaxableTotalCompanyContribution) {
        this.nonTaxableTotalCompanyContribution = nonTaxableTotalCompanyContribution;
    }

    @XmlElement(name = "nonTaxableTotalVpfContribution")
    public String getNonTaxableTotalVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableTotalVpfContribution);
    }

    public void setNonTaxableTotalVpfContribution(BigDecimal nonTaxableTotalVpfContribution) {
        this.nonTaxableTotalVpfContribution = nonTaxableTotalVpfContribution;
    }

    @XmlElement(name = "nonTaxableTotalContribution")
    public String getNonTaxableTotalContribution() {
        return NumberFormatter.formatNumbers(nonTaxableTotalContribution);
    }

    public void setNonTaxableTotalContribution(BigDecimal nonTaxableTotalContribution) {
        this.nonTaxableTotalContribution = nonTaxableTotalContribution;
    }


    @XmlElement(name = "taxableTotalMemberContribution")
    public String getTaxableTotalMemberContribution() {
        return NumberFormatter.formatNumbers(taxableTotalMemberContribution);
    }

    public void setTaxableTotalMemberContribution(BigDecimal taxableTotalMemberContribution) {
        this.taxableTotalMemberContribution = taxableTotalMemberContribution;
    }

    @XmlElement(name = "taxableTotalCompanyContribution")
    public String getTaxableTotalCompanyContribution() {
        return NumberFormatter.formatNumbers(taxableTotalCompanyContribution);
    }

    public void setTaxableTotalCompanyContribution(BigDecimal taxableTotalCompanyContribution) {
        this.taxableTotalCompanyContribution = taxableTotalCompanyContribution;
    }

    @XmlElement(name = "taxableTotalVpfContribution")
    public String getTaxableTotalVpfContribution() {
        return NumberFormatter.formatNumbers(taxableTotalVpfContribution);
    }

    public void setTaxableTotalVpfContribution(BigDecimal taxableTotalVpfContribution) {
        this.taxableTotalVpfContribution = taxableTotalVpfContribution;
    }

    @XmlElement(name = "taxableTotalContribution")
    public String getTaxableTotalContribution() {
        return NumberFormatter.formatNumbers(taxableTotalContribution);
    }

    public void setTaxableTotalContribution(BigDecimal taxableTotalContribution) {
        this.taxableTotalContribution = taxableTotalContribution;
    }

    @XmlElement(name = "currentYearContributions")
    public List<ContributionDTO> getCurrentYearContributions() {
        return currentYearContributions;
    }

    public void setCurrentYearContributions(List<ContributionDTO> currentYearContributions) {
        this.currentYearContributions = currentYearContributions;
    }

    @XmlElement(name = "currentYearContributionsTotal")
    public ContributionDTO getCurrentYearContributionTotal() {
        return currentYearContributionTotal;
    }

    public void setCurrentYearContributionTotal(ContributionDTO currentYearContributionTotal) {
        this.currentYearContributionTotal = currentYearContributionTotal;
    }

    @XmlElement(name = "transferInDetails")
    public List<TransferInDetailsDTO> getTransferInDetails() {
        return transferInDetails;
    }

    public void setTransferInDetails(List<TransferInDetailsDTO> transferInDetails) {
        this.transferInDetails = transferInDetails;
    }

    @XmlElement(name = "loanDetails")
    public List<LoanDetailsDTO> getLoanDetailsDTOS() {
        return loanDetailsDTOS;
    }

    public void setLoanDetailsDTOS(List<LoanDetailsDTO> loanDetailsDTOS) {
        this.loanDetailsDTOS = loanDetailsDTOS;
    }

    @XmlElement(name = "finantialPeriod")
    public String getFinancialPeriod(){
        return (finantialYear - 1) + " - " + finantialYear;
    }

    @XmlElement(name = "currentYearContributionsNew")
    public List<NewContributionDTO> getCurrentYearContributionsNew() {
        return currentYearContributionsNew;
    }

    public void setCurrentYearContributionsNew(List<NewContributionDTO> currentYearContributionsNew) {
        this.currentYearContributionsNew = currentYearContributionsNew;
    }

    @XmlElement(name = "currentYearContributionsNewTotal")
    public NewContributionDTO getCurrentYearContributionsNewTotal() {
        return currentYearContributionsNewTotal;
    }

    public void setCurrentYearContributionsNewTotal(NewContributionDTO currentYearContributionsNewTotal) {
        this.currentYearContributionsNewTotal = currentYearContributionsNewTotal;
    }

    public NewAnnualStatement setBasicDetails(String name, String pernNumber,
                                              String pfNumber, String tokenNumber,
                                              String unitCode, String deptCode,
                                              String status, List<Nominee> nominees,
                                              Date yearOpeningDate, Float interestRate,
                                              Date closingDate, Date date,
                                              Integer finantialYear){

        this.name = name;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.tokenNumber = tokenNumber;
        this.unitCode = unitCode;
        this.deptCode = deptCode;
        this.status = status;
        this.nominees = nominees;
        this.yearOpeningDate = yearOpeningDate;
        this.interestRate = interestRate;
        this.closingDate = closingDate;
        this.date = date;
        this.finantialYear = finantialYear;

        return this;
    }

    public NewAnnualStatement setBasicDetails(Employee employee,
                                              List<com.coreintegra.pftrust.entities.pf.employee.Nominee> nominees,
                                              Integer year, YearEndProcess openingContribution) throws ParseException {

        List<Nominee> nomineeList = nominees.stream()
                .map(nominee -> new Nominee(nominee.getName(),
                        nominee.getRelationship(), nominee.getShare()))
                .collect(Collectors.toList());

        this.setBasicDetails(employee.getName(), employee.getPernNumber(), employee.getPfNumber(),
                employee.getTokenNumber(), employee.getUnitCode(), "",
                employee.getContributionStatus().getSymbol(), nomineeList,
                FinancialYearAndMonth.getYearOpeningFromFY(year),
                openingContribution.getInterestRate().floatValue(),
                FinancialYearAndMonth.getClosingDate(year), new Date(),
                openingContribution.getYear());

        return this;
    }

    public NewAnnualStatement setOpeningBalanceDetails(YearEndProcess yearEndProcess) {

        this.nonTaxableYearOpeningMemberContribution = yearEndProcess.getNonTaxableMemberContribution();
        this.taxableYearOpeningMemberContribution = yearEndProcess.getTaxableMemberContribution();

        this.yearOpeningCompanyContribution = yearEndProcess.getCompanyContribution();

        this.nonTaxableYearOpeningVpfContribution = yearEndProcess.getNonTaxableVpfContribution();
        this.taxableYearOpeningVpfContribution = yearEndProcess.getTaxableVpfContribution();

        this.yearOpeningTotalContribution = yearEndProcess.getTotalContribution();

        this.nonTaxableInterestOnYearOpeningMemberContribution = yearEndProcess.getInterestOnNonTaxableMemberContribution();
        this.taxableInterestOnYearOpeningMemberContribution = yearEndProcess.getInterestOnTaxableMemberContribution();

        this.interestOnYearOpeningCompanyContribution = yearEndProcess.getInterestOnCompanyContribution();

        this.nonTaxableInterestOnYearOpeningVpfContribution = yearEndProcess.getInterestOnNonTaxableVpfContribution();
        this.taxableInterestOnYearOpeningVpfContribution = yearEndProcess.getInterestOnTaxableVpfContribution();

        this.interestOnYearOpeningTotalContribution = yearEndProcess.getTotalInterest();

        return this;

    }

    public NewAnnualStatement setCurrentYearContributionDetails(List<YearEndProcess> contributionDetails) {

        BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;
        BigDecimal taxableMemberContribution = BigDecimal.ZERO;

        BigDecimal companyContribution = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfContribution = BigDecimal.ZERO;
        BigDecimal taxableVpfContribution = BigDecimal.ZERO;

        BigDecimal totalContribution = BigDecimal.ZERO;

        BigDecimal nonTaxableMemberContributionInterest = BigDecimal.ZERO;
        BigDecimal taxableMemberContributionInterest = BigDecimal.ZERO;

        BigDecimal companyContributionInterest = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfContributionInterest = BigDecimal.ZERO;
        BigDecimal taxableVpfContributionInterest = BigDecimal.ZERO;

        BigDecimal totalInterest = BigDecimal.ZERO;

        this.currentYearContributions = new ArrayList<>();

        Set<Integer> foundContributions = new HashSet<>();

        for (YearEndProcess contribution:contributionDetails) {

            foundContributions.add(contribution.getMonth());

            nonTaxableMemberContribution = nonTaxableMemberContribution.add(avoidNull(contribution.getNonTaxableMemberContribution()));
            taxableMemberContribution = taxableMemberContribution.add(avoidNull(contribution.getTaxableMemberContribution()));

            companyContribution = companyContribution.add(avoidNull(contribution.getCompanyContribution()));

            nonTaxableVpfContribution = nonTaxableVpfContribution.add(avoidNull(contribution.getNonTaxableVpfContribution()));
            taxableVpfContribution = taxableVpfContribution.add(avoidNull(contribution.getTaxableVpfContribution()));

            totalContribution = totalContribution.add(contribution.getTotalContribution());

            nonTaxableMemberContributionInterest = nonTaxableMemberContributionInterest.add(avoidNull(contribution.getInterestOnNonTaxableMemberContribution()));
            taxableMemberContributionInterest = taxableMemberContributionInterest.add(avoidNull(contribution.getInterestOnTaxableMemberContribution()));

            companyContributionInterest = companyContributionInterest.add(avoidNull(contribution.getInterestOnCompanyContribution()));

            nonTaxableVpfContributionInterest = nonTaxableVpfContributionInterest.add(avoidNull(contribution.getInterestOnNonTaxableVpfContribution()));
            taxableVpfContributionInterest = taxableVpfContributionInterest.add(avoidNull(contribution.getInterestOnTaxableVpfContribution()));

            totalInterest = totalInterest.add(contribution.getTotalInterest());

            ContributionDTO contributionDTO = new ContributionDTO(
                    contribution.getNonTaxableMemberContribution().add(contribution.getTaxableMemberContribution()),
                    contribution.getCompanyContribution(),
                    contribution.getNonTaxableVpfContribution().add(contribution.getTaxableVpfContribution()),
                    contribution.getMonth(),
                    contribution.getInterestOnNonTaxableMemberContribution().add(contribution.getInterestOnTaxableMemberContribution()),
                    contribution.getInterestOnCompanyContribution(),
                    contribution.getInterestOnNonTaxableVpfContribution().add(contribution.getInterestOnTaxableVpfContribution()));

            this.currentYearContributions.add(contributionDTO);

        }

        for(int i=0; i<12; i++) {

            ContributionDTO contributionDTO = new ContributionDTO(BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO,
                    i + 1, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO);

            if(!foundContributions.contains(i+1)){
                this.currentYearContributions.add(contributionDTO);
            }

        }

        this.currentYearContributions.sort((o1, o2) -> o1.getContributedIn() - o2.getContributedIn());

        this.nonTaxableCurrentYearMemberContribution = nonTaxableMemberContribution;
        this.taxableCurrentYearMemberContribution = taxableMemberContribution;

        this.currentYearCompanyContribution = companyContribution;

        this.nonTaxableCurrentYearVpfContribution = nonTaxableVpfContribution;
        this.taxableCurrentYearVpfContribution = taxableVpfContribution;

        this.currentYearTotalContribution = totalContribution;

        this.nonTaxableInterestOnCurrentYearMemberContribution = nonTaxableMemberContributionInterest;
        this.taxableInterestOnCurrentYearMemberContribution = taxableMemberContributionInterest;

        this.interestOnCurrentYearCompanyContribution = companyContributionInterest;

        this.nonTaxableInterestOnCurrentYearVpfContribution = nonTaxableVpfContributionInterest;
        this.taxableInterestOnCurrentYearVpfContribution = taxableVpfContributionInterest;

        this.interestOnCurrentYearTotalContribution = totalInterest;

        this.currentYearContributionTotal = new ContributionDTO(
                nonTaxableMemberContribution.add(taxableMemberContribution),
                companyContribution,
                nonTaxableVpfContribution.add(taxableVpfContribution),
                null,
                nonTaxableMemberContributionInterest.add(taxableMemberContributionInterest),
                companyContributionInterest,
                nonTaxableVpfContributionInterest.add(taxableVpfContributionInterest)
        );

        return this;

    }

    public NewAnnualStatement setCurrentYearLoanDetails(List<YearEndProcess> loanDetails) {

        BigDecimal nonTaxableMemberLoan = BigDecimal.ZERO;
        BigDecimal taxableMemberLoan = BigDecimal.ZERO;

        BigDecimal companyLoan = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfLoan = BigDecimal.ZERO;
        BigDecimal taxableVpfLoan = BigDecimal.ZERO;

        BigDecimal totalLoan = BigDecimal.ZERO;

        BigDecimal nonTaxableMemberLoanInterest = BigDecimal.ZERO;
        BigDecimal taxableMemberLoanInterest = BigDecimal.ZERO;

        BigDecimal companyLoanInterest = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfLoanInterest = BigDecimal.ZERO;
        BigDecimal taxableVpfLoanInterest = BigDecimal.ZERO;

        BigDecimal totalInterest = BigDecimal.ZERO;

        this.loanDetailsDTOS = new ArrayList<>();

        for (YearEndProcess loanDetail:loanDetails) {

            nonTaxableMemberLoan = nonTaxableMemberLoan.add(avoidNull(loanDetail.getNonTaxableMemberContribution()));
            taxableMemberLoan = taxableMemberLoan.add(avoidNull(loanDetail.getTaxableMemberContribution()));

            companyLoan = companyLoan.add(avoidNull(loanDetail.getCompanyContribution()));

            nonTaxableVpfLoan = nonTaxableVpfLoan.add(avoidNull(loanDetail.getNonTaxableVpfContribution()));
            taxableVpfLoan = taxableVpfLoan.add(avoidNull(loanDetail.getTaxableVpfContribution()));

            totalLoan = totalLoan.add(loanDetail.getTotalContribution());

            nonTaxableMemberLoanInterest = nonTaxableMemberLoanInterest.add(avoidNull(loanDetail.getInterestOnNonTaxableMemberContribution()));
            taxableMemberLoanInterest = taxableMemberLoanInterest.add(avoidNull(loanDetail.getInterestOnTaxableMemberContribution()));

            companyLoanInterest = companyLoanInterest.add(avoidNull(loanDetail.getInterestOnCompanyContribution()));

            nonTaxableVpfLoanInterest = nonTaxableVpfLoanInterest.add(avoidNull(loanDetail.getInterestOnNonTaxableVpfContribution()));
            taxableVpfLoanInterest = taxableVpfLoanInterest.add(avoidNull(loanDetail.getInterestOnTaxableVpfContribution()));

            totalInterest = totalInterest.add(loanDetail.getTotalInterest());

            Loan loan = loanDetail.getLoan();

            LoanType loanType = loan.getLoanType();

            LoanDetailsDTO loanDetailsDTO = new LoanDetailsDTO(loanType.getCode(), loanType.getTitle(),
                    loan.getCreatedAtTimestamp(),
                    loan.getLoanApprovalDate(),
                    loanDetail.getNonTaxableMemberContribution().add(loanDetail.getTaxableMemberContribution()),
                    loanDetail.getCompanyContribution(),
                    loanDetail.getNonTaxableVpfContribution().add(loanDetail.getTaxableVpfContribution()));

            this.loanDetailsDTOS.add(loanDetailsDTO);

        }

        this.nonTaxableCurrentYearLoanWithdrawalMemberContribution = nonTaxableMemberLoan;
        this.taxableCurrentYearLoanWithdrawalMemberContribution = taxableMemberLoan;

        this.currentYearLoanWithdrawalCompanyContribution = companyLoan;

        this.nonTaxableCurrentYearLoanWithdrawalVpfContribution = nonTaxableVpfLoan;
        this.taxableCurrentYearLoanWithdrawalVpfContribution = taxableVpfLoan;

        this.currentYearLoanWithdrawalTotalContribution = totalLoan;

        this.nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution = nonTaxableMemberLoanInterest;
        this.taxableInterestOnCurrentYearLoanWithdrawalMemberContribution = taxableMemberLoanInterest;

        this.interestOnCurrentYearLoanWithdrawalCompanyContribution = companyLoanInterest;

        this.nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution = nonTaxableVpfLoanInterest;
        this.taxableInterestOnCurrentYearLoanWithdrawalVpfContribution = taxableVpfLoanInterest;

        this.interestOnCurrentYearLoanWithdrawalTotalContribution = totalInterest;

        return this;

    }


    public NewAnnualStatement setCurrentYearTransferInDetails(List<YearEndProcess> transferInDetails) {

        BigDecimal nonTaxableMemberTransferIn = BigDecimal.ZERO;
        BigDecimal taxableMemberTransferIn = BigDecimal.ZERO;

        BigDecimal companyTransferIn = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfTransferIn = BigDecimal.ZERO;
        BigDecimal taxableVpfTransferIn = BigDecimal.ZERO;

        BigDecimal totalTransferIn = BigDecimal.ZERO;

        BigDecimal nonTaxableMemberTransferInInterest = BigDecimal.ZERO;
        BigDecimal taxableMemberTransferInInterest = BigDecimal.ZERO;

        BigDecimal companyTransferInInterest = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfTransferInInterest = BigDecimal.ZERO;
        BigDecimal taxableVpfTransferInInterest = BigDecimal.ZERO;

        BigDecimal totalInterest = BigDecimal.ZERO;

        this.transferInDetails = new ArrayList<>();

        for (YearEndProcess transferInDetail:transferInDetails) {

            nonTaxableMemberTransferIn = nonTaxableMemberTransferIn.add(avoidNull(transferInDetail.getNonTaxableMemberContribution()));
            taxableMemberTransferIn = taxableMemberTransferIn.add(avoidNull(transferInDetail.getTaxableMemberContribution()));

            companyTransferIn = companyTransferIn.add(avoidNull(transferInDetail.getCompanyContribution()));

            nonTaxableVpfTransferIn = nonTaxableVpfTransferIn.add(avoidNull(transferInDetail.getNonTaxableVpfContribution()));
            taxableVpfTransferIn = taxableVpfTransferIn.add(avoidNull(transferInDetail.getTaxableVpfContribution()));

            totalTransferIn = totalTransferIn.add(transferInDetail.getTotalContribution());

            nonTaxableMemberTransferInInterest = nonTaxableMemberTransferInInterest.add(avoidNull(transferInDetail.getInterestOnNonTaxableMemberContribution()));
            taxableMemberTransferInInterest = taxableMemberTransferInInterest.add(avoidNull(transferInDetail.getInterestOnTaxableMemberContribution()));

            companyTransferInInterest = companyTransferInInterest.add(avoidNull(transferInDetail.getInterestOnCompanyContribution()));

            nonTaxableVpfTransferInInterest = nonTaxableVpfTransferInInterest.add(avoidNull(transferInDetail.getInterestOnNonTaxableVpfContribution()));
            taxableVpfTransferInInterest = taxableVpfTransferInInterest.add(avoidNull(transferInDetail.getInterestOnTaxableVpfContribution()));

            totalInterest = totalInterest.add(transferInDetail.getTotalInterest());

            TransferIn transferIn = transferInDetail.getTransferIn();

            PreviousCompany previousCompany = transferIn.getPreviousCompany();

            TransferInDetailsDTO transferInDetailsDTO = new TransferInDetailsDTO(
                    transferIn.getCreatedAtTimestamp(),
                    transferIn.getPostingDate(),
                    transferInDetail.getTaxableMemberContribution().add(transferInDetail.getNonTaxableMemberContribution()),
                    transferInDetail.getCompanyContribution(),
                    previousCompany.getName(),
                    transferIn.getSapDocumentNumber());

            this.transferInDetails.add(transferInDetailsDTO);

        }

        this.nonTaxableCurrentYearTransferInMemberContribution = nonTaxableMemberTransferIn;
        this.taxableCurrentYearTransferInMemberContribution = taxableMemberTransferIn;

        this.currentYearTransferInCompanyContribution = companyTransferIn;

        this.nonTaxableCurrentYearTransferInVpfContribution = nonTaxableVpfTransferIn;
        this.taxableCurrentYearTransferInVpfContribution = taxableVpfTransferIn;

        this.currentYearTransferInTotalContribution = totalTransferIn;

        this.nonTaxableInterestOnCurrentYearTransferInMemberContribution = nonTaxableMemberTransferInInterest;
        this.taxableInterestOnCurrentYearTransferInMemberContribution = taxableMemberTransferInInterest;

        this.interestOnCurrentYearTransferInCompanyContribution = companyTransferInInterest;

        this.nonTaxableInterestOnCurrentYearTransferInVpfContribution = nonTaxableVpfTransferInInterest;
        this.taxableInterestOnCurrentYearTransferInVpfContribution = taxableVpfTransferInInterest;

        this.interestOnCurrentYearTransferInTotalContribution = totalInterest;

        return this;

    }

    public NewAnnualStatement setTotalContributionDetails(YearEndProcess totalContributionDetails,
                                                          BigDecimal tdsOnMember, BigDecimal tdsOnVpf) {

        this.totalMemberContribution = totalContributionDetails.getTaxableMemberContribution()
                .add(totalContributionDetails.getNonTaxableMemberContribution());

        this.totalCompanyContribution = totalContributionDetails.getCompanyContribution();

        this.totalVpfContribution = totalContributionDetails.getTaxableVpfContribution()
                .add(totalContributionDetails.getNonTaxableVpfContribution());

        this.totalContribution = totalContributionDetails.getTotalContribution();

        this.netContribution = totalContributionDetails.getTotalContribution();


        // non taxable
        this.nonTaxableTotalMemberContribution = totalContributionDetails.getNonTaxableMemberContribution();

        this.nonTaxableTotalCompanyContribution = totalContributionDetails.getCompanyContribution();

        this.nonTaxableTotalVpfContribution = totalContributionDetails.getNonTaxableVpfContribution();

        this.nonTaxableTotalContribution = totalContributionDetails.getNonTaxableMemberContribution()
                .add(totalContributionDetails.getCompanyContribution())
                .add(totalContributionDetails.getNonTaxableVpfContribution());


        //taxable
        this.taxableTotalMemberContribution = totalContributionDetails.getTaxableMemberContribution();

        this.taxableTotalCompanyContribution = BigDecimal.ZERO;

        this.taxableTotalVpfContribution = totalContributionDetails.getTaxableVpfContribution();

        this.taxableTotalContribution = totalContributionDetails.getTaxableMemberContribution()
                .add(totalContributionDetails.getTaxableVpfContribution());

        this.tdsOnInterestOnTaxableMemberContribution = tdsOnMember;

        this.tdsOnInterestOnTaxableVpfContribution = tdsOnVpf;

        return this;

    }

    public static NewAnnualStatement builder(){
        return new NewAnnualStatement();
    }

    public NewAnnualStatement setCurrentYearContributionDetailsNew(List<YearEndProcess> contributionDetails) {

        BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;
        BigDecimal taxableMemberContribution = BigDecimal.ZERO;

        BigDecimal companyContribution = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfContribution = BigDecimal.ZERO;
        BigDecimal taxableVpfContribution = BigDecimal.ZERO;

        BigDecimal totalContribution = BigDecimal.ZERO;

        BigDecimal nonTaxableMemberContributionInterest = BigDecimal.ZERO;
        BigDecimal taxableMemberContributionInterest = BigDecimal.ZERO;

        BigDecimal companyContributionInterest = BigDecimal.ZERO;

        BigDecimal nonTaxableVpfContributionInterest = BigDecimal.ZERO;
        BigDecimal taxableVpfContributionInterest = BigDecimal.ZERO;

        BigDecimal totalInterest = BigDecimal.ZERO;

        this.currentYearContributionsNew = new ArrayList<>();

        Set<Integer> foundContributions = new HashSet<>();

        for (YearEndProcess contribution:contributionDetails) {

            foundContributions.add(contribution.getMonth());

            nonTaxableMemberContribution = nonTaxableMemberContribution.add(avoidNull(contribution.getNonTaxableMemberContribution()));
            taxableMemberContribution = taxableMemberContribution.add(avoidNull(contribution.getTaxableMemberContribution()));

            companyContribution = companyContribution.add(avoidNull(contribution.getCompanyContribution()));

            nonTaxableVpfContribution = nonTaxableVpfContribution.add(avoidNull(contribution.getNonTaxableVpfContribution()));
            taxableVpfContribution = taxableVpfContribution.add(avoidNull(contribution.getTaxableVpfContribution()));

            totalContribution = totalContribution.add(contribution.getTotalContribution());

            nonTaxableMemberContributionInterest = nonTaxableMemberContributionInterest.add(avoidNull(contribution.getInterestOnNonTaxableMemberContribution()));
            taxableMemberContributionInterest = taxableMemberContributionInterest.add(avoidNull(contribution.getInterestOnTaxableMemberContribution()));

            companyContributionInterest = companyContributionInterest.add(avoidNull(contribution.getInterestOnCompanyContribution()));

            nonTaxableVpfContributionInterest = nonTaxableVpfContributionInterest.add(avoidNull(contribution.getInterestOnNonTaxableVpfContribution()));
            taxableVpfContributionInterest = taxableVpfContributionInterest.add(avoidNull(contribution.getInterestOnTaxableVpfContribution()));

            totalInterest = totalInterest.add(contribution.getTotalInterest());

            NewContributionDTO contributionDTO = new NewContributionDTO(
                    contribution.getMonth(),
                    avoidNull(contribution.getNonTaxableMemberContribution())
                            .add(avoidNull(contribution.getNonTaxableVpfContribution())),
                    avoidNull(contribution.getTaxableMemberContribution())
                            .add(avoidNull(contribution.getTaxableVpfContribution())),
                    avoidNull(contribution.getInterestOnNonTaxableMemberContribution())
                            .add(avoidNull(contribution.getInterestOnNonTaxableVpfContribution())),
                    avoidNull(contribution.getInterestOnTaxableMemberContribution())
                            .add(avoidNull(contribution.getInterestOnTaxableVpfContribution())),
                    contribution.getCompanyContribution(),
                    contribution.getInterestOnCompanyContribution()
            );

            this.currentYearContributionsNew.add(contributionDTO);

        }

        for(int i=0; i<12; i++) {

            NewContributionDTO contributionDTO = new NewContributionDTO(i+1,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO);

            if(!foundContributions.contains(i+1)){
                this.currentYearContributionsNew.add(contributionDTO);
            }

        }

        this.currentYearContributionsNew.sort((o1, o2) -> o1.getContributedIn() - o2.getContributedIn());

        this.currentYearContributionsNewTotal = new NewContributionDTO(null,
                nonTaxableMemberContribution.add(nonTaxableVpfContribution),
                taxableMemberContribution.add(taxableVpfContribution),
                nonTaxableMemberContributionInterest.add(nonTaxableVpfContributionInterest),
                taxableMemberContributionInterest.add(taxableVpfContributionInterest),
                companyContribution, companyContributionInterest);

        return this;

    }

}
