package com.coreintegra.pftrust.dtos.pdf.yearend;

import com.coreintegra.pftrust.dtos.pdf.Nominee;
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
public class AnnualStatement {

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
    private BigDecimal yearOpeningMemberContribution;
    private BigDecimal yearOpeningCompanyContribution;
    private BigDecimal yearOpeningVpfContribution;
    private BigDecimal yearOpeningTotalContribution;

    private BigDecimal interestOnYearOpeningMemberContribution;
    private BigDecimal interestOnYearOpeningCompanyContribution;
    private BigDecimal interestOnYearOpeningVpfContribution;
    private BigDecimal interestOnYearOpeningTotalContribution;

    //    current year
    private BigDecimal currentYearMemberContribution;
    private BigDecimal currentYearCompanyContribution;
    private BigDecimal currentYearVpfContribution;
    private BigDecimal currentYearTotalContribution;

    private BigDecimal interestOnCurrentYearMemberContribution;
    private BigDecimal interestOnCurrentYearCompanyContribution;
    private BigDecimal interestOnCurrentYearVpfContribution;
    private BigDecimal interestOnCurrentYearTotalContribution;

    //    transfer in
    private BigDecimal currentYearTransferInMemberContribution;
    private BigDecimal currentYearTransferInCompanyContribution;
    private BigDecimal currentYearTransferInVpfContribution;

    private BigDecimal currentYearTransferInTotalContribution;

    private BigDecimal interestOnCurrentYearTransferInMemberContribution;
    private BigDecimal interestOnCurrentYearTransferInCompanyContribution;
    private BigDecimal interestOnCurrentYearTransferInVpfContribution;

    private BigDecimal interestOnCurrentYearTransferInTotalContribution;

    //    transfer wl
    private BigDecimal currentYearLoanWithdrawalMemberContribution;
    private BigDecimal currentYearLoanWithdrawalCompanyContribution;
    private BigDecimal currentYearLoanWithdrawalVpfContribution;
    private BigDecimal currentYearLoanWithdrawalTotalContribution;

    private BigDecimal interestOnCurrentYearLoanWithdrawalMemberContribution;
    private BigDecimal interestOnCurrentYearLoanWithdrawalCompanyContribution;
    private BigDecimal interestOnCurrentYearLoanWithdrawalVpfContribution;
    private BigDecimal interestOnCurrentYearLoanWithdrawalTotalContribution;

    private Date closingDate;

    private Date date;

    private Integer finantialYear;

    //    total contribution
    private BigDecimal totalMemberContribution;
    private BigDecimal totalCompanyContribution;
    private BigDecimal totalVpfContribution;
    private BigDecimal totalContribution;

    private BigDecimal netContribution;

    private List<ContributionDTO> currentYearContributions;

    private ContributionDTO currentYearContributionTotal;

    private List<TransferInDetailsDTO> transferInDetails;

    private List<LoanDetailsDTO> loanDetailsDTOS;

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
    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    @XmlElement(name = "yearOpeningMemberContribution")
    public String getYearOpeningMemberContribution() {
        return NumberFormatter.formatNumbers(yearOpeningMemberContribution);
    }

    public void setYearOpeningMemberContribution(BigDecimal yearOpeningMemberContribution) {
        this.yearOpeningMemberContribution = yearOpeningMemberContribution;
    }

    @XmlElement(name = "yearOpeningCompanyContribution")
    public String getYearOpeningCompanyContribution() {
        return NumberFormatter.formatNumbers(yearOpeningCompanyContribution);
    }

    public void setYearOpeningCompanyContribution(BigDecimal yearOpeningCompanyContribution) {
        this.yearOpeningCompanyContribution = yearOpeningCompanyContribution;
    }

    @XmlElement(name = "yearOpeningVpfContribution")
    public String getYearOpeningVpfContribution() {
        return NumberFormatter.formatNumbers(yearOpeningVpfContribution);
    }

    public void setYearOpeningVpfContribution(BigDecimal yearOpeningVpfContribution) {
        this.yearOpeningVpfContribution = yearOpeningVpfContribution;
    }

    @XmlElement(name = "yearOpeningTotalContribution")
    public String getYearOpeningTotalContribution() {
        return NumberFormatter.formatNumbers(yearOpeningTotalContribution);
    }

    public void setYearOpeningTotalContribution(BigDecimal yearOpeningTotalContribution) {
        this.yearOpeningTotalContribution = yearOpeningTotalContribution;
    }

    @XmlElement(name = "interestOnYearOpeningMemberContribution")
    public String getInterestOnYearOpeningMemberContribution() {
        return NumberFormatter.formatNumbers(interestOnYearOpeningMemberContribution);
    }

    public void setInterestOnYearOpeningMemberContribution(BigDecimal interestOnYearOpeningMemberContribution) {
        this.interestOnYearOpeningMemberContribution = interestOnYearOpeningMemberContribution;
    }

    @XmlElement(name = "interestOnYearOpeningCompanyContribution")
    public String getInterestOnYearOpeningCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnYearOpeningCompanyContribution);
    }

    public void setInterestOnYearOpeningCompanyContribution(BigDecimal interestOnYearOpeningCompanyContribution) {
        this.interestOnYearOpeningCompanyContribution = interestOnYearOpeningCompanyContribution;
    }

    @XmlElement(name = "interestOnYearOpeningVpfContribution")
    public String getInterestOnYearOpeningVpfContribution() {
        return NumberFormatter.formatNumbers(interestOnYearOpeningVpfContribution);
    }

    public void setInterestOnYearOpeningVpfContribution(BigDecimal interestOnYearOpeningVpfContribution) {
        this.interestOnYearOpeningVpfContribution = interestOnYearOpeningVpfContribution;
    }

    @XmlElement(name = "interestOnYearOpeningTotalContribution")
    public String getInterestOnYearOpeningTotalContribution() {
        return NumberFormatter.formatNumbers(interestOnYearOpeningTotalContribution);
    }

    public void setInterestOnYearOpeningTotalContribution(BigDecimal interestOnYearOpeningTotalContribution) {
        this.interestOnYearOpeningTotalContribution = interestOnYearOpeningTotalContribution;
    }

    @XmlElement(name = "currentYearMemberContribution")
    public String getCurrentYearMemberContribution() {
        return NumberFormatter.formatNumbers(currentYearMemberContribution);
    }

    public void setCurrentYearMemberContribution(BigDecimal currentYearMemberContribution) {
        this.currentYearMemberContribution = currentYearMemberContribution;
    }

    @XmlElement(name = "currentYearCompanyContribution")
    public String getCurrentYearCompanyContribution() {
        return NumberFormatter.formatNumbers(currentYearCompanyContribution);
    }

    public void setCurrentYearCompanyContribution(BigDecimal currentYearCompanyContribution) {
        this.currentYearCompanyContribution = currentYearCompanyContribution;
    }

    @XmlElement(name = "currentYearVpfContribution")
    public String getCurrentYearVpfContribution() {
        return NumberFormatter.formatNumbers(currentYearVpfContribution);
    }

    public void setCurrentYearVpfContribution(BigDecimal currentYearVpfContribution) {
        this.currentYearVpfContribution = currentYearVpfContribution;
    }

    @XmlElement(name = "currentYearTotalContribution")
    public String getCurrentYearTotalContribution() {
        return NumberFormatter.formatNumbers(currentYearTotalContribution);
    }

    public void setCurrentYearTotalContribution(BigDecimal currentYearTotalContribution) {
        this.currentYearTotalContribution = currentYearTotalContribution;
    }

    @XmlElement(name = "interestOnCurrentYearMemberContribution")
    public String getInterestOnCurrentYearMemberContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearMemberContribution);
    }

    public void setInterestOnCurrentYearMemberContribution(BigDecimal interestOnCurrentYearMemberContribution) {
        this.interestOnCurrentYearMemberContribution = interestOnCurrentYearMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearCompanyContribution")
    public String getInterestOnCurrentYearCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearCompanyContribution);
    }

    public void setInterestOnCurrentYearCompanyContribution(BigDecimal interestOnCurrentYearCompanyContribution) {
        this.interestOnCurrentYearCompanyContribution = interestOnCurrentYearCompanyContribution;
    }

    @XmlElement(name = "interestOnCurrentYearVpfContribution")
    public String getInterestOnCurrentYearVpfContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearVpfContribution);
    }

    public void setInterestOnCurrentYearVpfContribution(BigDecimal interestOnCurrentYearVpfContribution) {
        this.interestOnCurrentYearVpfContribution = interestOnCurrentYearVpfContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTotalContribution")
    public String getInterestOnCurrentYearTotalContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearTotalContribution);
    }

    public void setInterestOnCurrentYearTotalContribution(BigDecimal interestOnCurrentYearTotalContribution) {
        this.interestOnCurrentYearTotalContribution = interestOnCurrentYearTotalContribution;
    }

    @XmlElement(name = "currentYearTransferInMemberContribution")
    public String getCurrentYearTransferInMemberContribution() {
        return NumberFormatter.formatNumbers(currentYearTransferInMemberContribution);
    }

    public void setCurrentYearTransferInMemberContribution(BigDecimal currentYearTransferInMemberContribution) {
        this.currentYearTransferInMemberContribution = currentYearTransferInMemberContribution;
    }

    @XmlElement(name = "currentYearTransferInCompanyContribution")
    public String getCurrentYearTransferInCompanyContribution() {
        return NumberFormatter.formatNumbers(currentYearTransferInCompanyContribution);
    }

    public void setCurrentYearTransferInCompanyContribution(BigDecimal currentYearTransferInCompanyContribution) {
        this.currentYearTransferInCompanyContribution = currentYearTransferInCompanyContribution;
    }

    @XmlElement(name = "currentYearTransferInVpfContribution")
    public String getCurrentYearTransferInVpfContribution() {
        return NumberFormatter.formatNumbers(currentYearTransferInVpfContribution);
    }

    public void setCurrentYearTransferInVpfContribution(BigDecimal currentYearTransferInVpfContribution) {
        this.currentYearTransferInVpfContribution = currentYearTransferInVpfContribution;
    }

    @XmlElement(name = "currentYearTransferInTotalContribution")
    public String getCurrentYearTransferInTotalContribution() {
        return NumberFormatter.formatNumbers(currentYearTransferInTotalContribution);
    }

    public void setCurrentYearTransferInTotalContribution(BigDecimal currentYearTransferInTotalContribution) {
        this.currentYearTransferInTotalContribution = currentYearTransferInTotalContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInMemberContribution")
    public String getInterestOnCurrentYearTransferInMemberContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearTransferInMemberContribution);
    }

    public void setInterestOnCurrentYearTransferInMemberContribution(BigDecimal interestOnCurrentYearTransferInMemberContribution) {
        this.interestOnCurrentYearTransferInMemberContribution = interestOnCurrentYearTransferInMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInCompanyContribution")
    public String getInterestOnCurrentYearTransferInCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearTransferInCompanyContribution);
    }

    public void setInterestOnCurrentYearTransferInCompanyContribution(BigDecimal interestOnCurrentYearTransferInCompanyContribution) {
        this.interestOnCurrentYearTransferInCompanyContribution = interestOnCurrentYearTransferInCompanyContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInVpfContribution")
    public String getInterestOnCurrentYearTransferInVpfContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearTransferInVpfContribution);
    }

    public void setInterestOnCurrentYearTransferInVpfContribution(BigDecimal interestOnCurrentYearTransferInVpfContribution) {
        this.interestOnCurrentYearTransferInVpfContribution = interestOnCurrentYearTransferInVpfContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInTotalContribution")
    public String getInterestOnCurrentYearTransferInTotalContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearTransferInTotalContribution);
    }

    public void setInterestOnCurrentYearTransferInTotalContribution(BigDecimal interestOnCurrentYearTransferInTotalContribution) {
        this.interestOnCurrentYearTransferInTotalContribution = interestOnCurrentYearTransferInTotalContribution;
    }

    @XmlElement(name = "currentYearLoanWithdrawalMemberContribution")
    public String getCurrentYearLoanWithdrawalMemberContribution() {
        return NumberFormatter.formatNumbers(currentYearLoanWithdrawalMemberContribution);
    }

    public void setCurrentYearLoanWithdrawalMemberContribution(BigDecimal currentYearLoanWithdrawalMemberContribution) {
        this.currentYearLoanWithdrawalMemberContribution = currentYearLoanWithdrawalMemberContribution;
    }

    @XmlElement(name = "currentYearLoanWithdrawalCompanyContribution")
    public String getCurrentYearLoanWithdrawalCompanyContribution() {
        return NumberFormatter.formatNumbers(currentYearLoanWithdrawalCompanyContribution);
    }

    public void setCurrentYearLoanWithdrawalCompanyContribution(BigDecimal currentYearLoanWithdrawalCompanyContribution) {
        this.currentYearLoanWithdrawalCompanyContribution = currentYearLoanWithdrawalCompanyContribution;
    }


    @XmlElement(name = "currentYearLoanWithdrawalVpfContribution")
    public String getCurrentYearLoanWithdrawalVpfContribution() {
        return NumberFormatter.formatNumbers(currentYearLoanWithdrawalVpfContribution);
    }

    public void setCurrentYearLoanWithdrawalVpfContribution(BigDecimal currentYearLoanWithdrawalVpfContribution) {
        this.currentYearLoanWithdrawalVpfContribution = currentYearLoanWithdrawalVpfContribution;
    }

    @XmlElement(name = "currentYearLoanWithdrawalTotalContribution")
    public String getCurrentYearLoanWithdrawalTotalContribution() {
        return NumberFormatter.formatNumbers(currentYearLoanWithdrawalTotalContribution);
    }

    public void setCurrentYearLoanWithdrawalTotalContribution(BigDecimal currentYearLoanWithdrawalTotalContribution) {
        this.currentYearLoanWithdrawalTotalContribution = currentYearLoanWithdrawalTotalContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalMemberContribution")
    public String getInterestOnCurrentYearLoanWithdrawalMemberContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearLoanWithdrawalMemberContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalMemberContribution(BigDecimal interestOnCurrentYearLoanWithdrawalMemberContribution) {
        this.interestOnCurrentYearLoanWithdrawalMemberContribution = interestOnCurrentYearLoanWithdrawalMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalCompanyContribution")
    public String getInterestOnCurrentYearLoanWithdrawalCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearLoanWithdrawalCompanyContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalCompanyContribution(BigDecimal interestOnCurrentYearLoanWithdrawalCompanyContribution) {
        this.interestOnCurrentYearLoanWithdrawalCompanyContribution = interestOnCurrentYearLoanWithdrawalCompanyContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalVpfContribution")
    public String getInterestOnCurrentYearLoanWithdrawalVpfContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearLoanWithdrawalVpfContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalVpfContribution(BigDecimal interestOnCurrentYearLoanWithdrawalVpfContribution) {
        this.interestOnCurrentYearLoanWithdrawalVpfContribution = interestOnCurrentYearLoanWithdrawalVpfContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalTotalContribution")
    public String getInterestOnCurrentYearLoanWithdrawalTotalContribution() {
        return NumberFormatter.formatNumbers(interestOnCurrentYearLoanWithdrawalTotalContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalTotalContribution(BigDecimal interestOnCurrentYearLoanWithdrawalTotalContribution) {
        this.interestOnCurrentYearLoanWithdrawalTotalContribution = interestOnCurrentYearLoanWithdrawalTotalContribution;
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


    public AnnualStatement setBasicDetails(String name, String pernNumber,
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

    public AnnualStatement setBasicDetails(Employee employee,
                                           List<com.coreintegra.pftrust.entities.pf.employee.Nominee> nominees,
                                           Integer year, YearEndProcess openingContribution) throws ParseException {

        List<com.coreintegra.pftrust.dtos.pdf.Nominee> nomineeList = nominees.stream()
                .map(nominee -> new com.coreintegra.pftrust.dtos.pdf.Nominee(nominee.getName(),
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

    public AnnualStatement setOpeningBalanceDetails(YearEndProcess yearEndProcess) {

        this.yearOpeningMemberContribution = yearEndProcess.getMemberContribution();
        this.yearOpeningCompanyContribution = yearEndProcess.getCompanyContribution();
        this.yearOpeningVpfContribution = yearEndProcess.getVpfContribution();

        this.yearOpeningTotalContribution = yearEndProcess.getTotalContribution();

        this.interestOnYearOpeningMemberContribution = yearEndProcess.getInterestOnMemberContribution();
        this.interestOnYearOpeningCompanyContribution = yearEndProcess.getInterestOnCompanyContribution();
        this.interestOnYearOpeningVpfContribution = yearEndProcess.getInterestOnVpfContribution();
        this.interestOnYearOpeningTotalContribution = yearEndProcess.getTotalInterest();

        return this;

    }


    public AnnualStatement setCurrentYearContributionDetails(List<YearEndProcess> contributionDetails) {

        BigDecimal memberContribution = BigDecimal.ZERO;
        BigDecimal companyContribution = BigDecimal.ZERO;
        BigDecimal vpfContribution = BigDecimal.ZERO;

        BigDecimal totalContribution = BigDecimal.ZERO;

        BigDecimal memberContributionInterest = BigDecimal.ZERO;
        BigDecimal companyContributionInterest = BigDecimal.ZERO;
        BigDecimal vpfContributionInterest = BigDecimal.ZERO;

        BigDecimal totalInterest = BigDecimal.ZERO;

        this.currentYearContributions = new ArrayList<>();

        Set<Integer> foundContributions = new HashSet<>();

        for (YearEndProcess contribution:contributionDetails) {

            foundContributions.add(contribution.getMonth());

            memberContribution = memberContribution.add(avoidNull(contribution.getMemberContribution()));
            companyContribution = companyContribution.add(avoidNull(contribution.getCompanyContribution()));
            vpfContribution = vpfContribution.add(avoidNull(contribution.getVpfContribution()));

            totalContribution = totalContribution.add(contribution.getTotalContribution());

            memberContributionInterest = memberContributionInterest.add(avoidNull(contribution.getInterestOnMemberContribution()));
            companyContributionInterest = companyContributionInterest.add(avoidNull(contribution.getInterestOnCompanyContribution()));
            vpfContributionInterest = vpfContributionInterest.add(avoidNull(contribution.getInterestOnVpfContribution()));

            totalInterest = totalInterest.add(contribution.getTotalInterest());

            ContributionDTO contributionDTO = new ContributionDTO(contribution.getMemberContribution(),
                    contribution.getCompanyContribution(),
                    contribution.getVpfContribution(),
                    contribution.getMonth(),
                    contribution.getInterestOnMemberContribution(),
                    contribution.getInterestOnCompanyContribution(),
                    contribution.getInterestOnVpfContribution());

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

        this.currentYearMemberContribution = memberContribution;
        this.currentYearCompanyContribution = companyContribution;
        this.currentYearVpfContribution = vpfContribution;

        this.currentYearTotalContribution = totalContribution;

        this.interestOnCurrentYearMemberContribution = memberContributionInterest;
        this.interestOnCurrentYearCompanyContribution = companyContributionInterest;
        this.interestOnCurrentYearVpfContribution = vpfContributionInterest;

        this.interestOnCurrentYearTotalContribution = totalInterest;

        return this;

    }

    public AnnualStatement setCurrentYearLoanDetails(List<YearEndProcess> loanDetails) {

        BigDecimal memberLoan = BigDecimal.ZERO;
        BigDecimal companyLoan = BigDecimal.ZERO;
        BigDecimal vpfLoan = BigDecimal.ZERO;

        BigDecimal totalLoan = BigDecimal.ZERO;

        BigDecimal memberLoanInterest = BigDecimal.ZERO;
        BigDecimal companyLoanInterest = BigDecimal.ZERO;
        BigDecimal vpfLoanInterest = BigDecimal.ZERO;

        BigDecimal totalInterest = BigDecimal.ZERO;

        this.loanDetailsDTOS = new ArrayList<>();

        for (YearEndProcess loanDetail:loanDetails) {

            memberLoan = memberLoan.add(avoidNull(loanDetail.getMemberContribution()));
            companyLoan = companyLoan.add(avoidNull(loanDetail.getCompanyContribution()));
            vpfLoan = vpfLoan.add(avoidNull(loanDetail.getVpfContribution()));

            totalLoan = totalLoan.add(loanDetail.getTotalContribution());

            memberLoanInterest = memberLoanInterest.add(avoidNull(loanDetail.getInterestOnMemberContribution()));
            companyLoanInterest = companyLoanInterest.add(avoidNull(loanDetail.getInterestOnCompanyContribution()));
            vpfLoanInterest = vpfLoanInterest.add(avoidNull(loanDetail.getInterestOnVpfContribution()));

            totalInterest = totalInterest.add(loanDetail.getTotalInterest());

            Loan loan = loanDetail.getLoan();

            LoanType loanType = loan.getLoanType();

            LoanDetailsDTO loanDetailsDTO = new LoanDetailsDTO(loanType.getCode(), loanType.getTitle(),
                    loan.getCreatedAtTimestamp(),
                    loan.getLoanApprovalDate(),
                    loanDetail.getMemberContribution(),
                    loanDetail.getCompanyContribution(),
                    loanDetail.getVpfContribution());

            this.loanDetailsDTOS.add(loanDetailsDTO);

        }

        this.currentYearLoanWithdrawalMemberContribution = memberLoan;
        this.currentYearLoanWithdrawalCompanyContribution = companyLoan;
        this.currentYearLoanWithdrawalVpfContribution = vpfLoan;

        this.currentYearLoanWithdrawalTotalContribution = totalLoan;

        this.interestOnCurrentYearLoanWithdrawalMemberContribution = memberLoanInterest;
        this.interestOnCurrentYearLoanWithdrawalCompanyContribution = companyLoanInterest;
        this.interestOnCurrentYearLoanWithdrawalVpfContribution = vpfLoanInterest;

        this.interestOnCurrentYearLoanWithdrawalTotalContribution = totalInterest;

        return this;

    }


    public AnnualStatement setCurrentYearTransferInDetails(List<YearEndProcess> transferInDetails) {

        BigDecimal memberTransferIn = BigDecimal.ZERO;
        BigDecimal companyTransferIn = BigDecimal.ZERO;
        BigDecimal vpfTransferIn = BigDecimal.ZERO;

        BigDecimal totalTransferIn = BigDecimal.ZERO;

        BigDecimal memberTransferInInterest = BigDecimal.ZERO;
        BigDecimal companyTransferInInterest = BigDecimal.ZERO;
        BigDecimal vpfTransferInInterest = BigDecimal.ZERO;

        BigDecimal totalInterest = BigDecimal.ZERO;

        this.transferInDetails = new ArrayList<>();

        for (YearEndProcess transferInDetail:transferInDetails) {

            memberTransferIn = memberTransferIn.add(avoidNull(transferInDetail.getMemberContribution()));
            companyTransferIn = companyTransferIn.add(avoidNull(transferInDetail.getCompanyContribution()));
            vpfTransferIn = vpfTransferIn.add(avoidNull(transferInDetail.getVpfContribution()));

            totalTransferIn = totalTransferIn.add(transferInDetail.getTotalContribution());

            memberTransferInInterest = memberTransferInInterest.add(avoidNull(transferInDetail.getInterestOnMemberContribution()));
            companyTransferInInterest = companyTransferInInterest.add(avoidNull(transferInDetail.getInterestOnCompanyContribution()));
            vpfTransferInInterest = vpfTransferInInterest.add(avoidNull(transferInDetail.getInterestOnVpfContribution()));

            totalInterest = totalInterest.add(transferInDetail.getTotalInterest());

            TransferIn transferIn = transferInDetail.getTransferIn();

            PreviousCompany previousCompany = transferIn.getPreviousCompany();

            TransferInDetailsDTO transferInDetailsDTO = new TransferInDetailsDTO(
                    transferIn.getCreatedAtTimestamp(),
                    transferIn.getPostingDate(),
                    transferInDetail.getMemberContribution(),
                    transferInDetail.getCompanyContribution(),
                    previousCompany.getName(),
                    transferIn.getSapDocumentNumber());

            this.transferInDetails.add(transferInDetailsDTO);

        }

        this.currentYearTransferInMemberContribution = memberTransferIn;
        this.currentYearTransferInCompanyContribution = companyTransferIn;
        this.currentYearTransferInVpfContribution = vpfTransferIn;

        this.currentYearTransferInTotalContribution = totalTransferIn;

        this.interestOnCurrentYearTransferInMemberContribution = memberTransferInInterest;
        this.interestOnCurrentYearTransferInCompanyContribution = companyTransferInInterest;
        this.interestOnCurrentYearTransferInVpfContribution = vpfTransferInInterest;

        this.interestOnCurrentYearTransferInTotalContribution = totalInterest;

        return this;

    }

    public AnnualStatement setTotalContributionDetails(YearEndProcess totalContributionDetails) {

        this.totalMemberContribution = totalContributionDetails.getMemberContribution();
        this.totalCompanyContribution = totalContributionDetails.getCompanyContribution();
        this.totalVpfContribution = totalContributionDetails.getVpfContribution();

        this.totalContribution = totalContributionDetails.getTotalContribution();

        this.netContribution = totalContributionDetails.getTotalContribution();

        return this;

    }

    public static AnnualStatement builder(){
        return new AnnualStatement();
    }


}
