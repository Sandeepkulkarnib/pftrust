package com.coreintegra.pftrust.dtos.pdf.loan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@XmlRootElement(name = "loan")
public class LoanWorkSheet {

    private String loanCode;
    private String loanType;

    private String name;

    private String tokenNumber;
    private String pfNumber;
    private String unitCode;

    private String departmentName;
    private String applicationNumber;

    private Date lastRecoveryDate;

    private Date dateOfBirth;
    private Date dateOfJoiningPf;
    private Date dateOfJoiningPrior;
    private Date applicationDate;
    private Date dateOfJoining;
    private int count;


    private BigDecimal pfBase;

    private BigDecimal loanAmountApplied;
    private BigDecimal costInvolved;

    private Date yearOpeningDate;

    //    year opening
    private BigDecimal yearOpeningMemberContribution;
    private BigDecimal yearOpeningCompanyContribution;
    private BigDecimal yearOpeningVpfContribution;
    private BigDecimal yearOpeningTotalContribution;

    //    year
    private BigDecimal currentYearMemberContribution;
    private BigDecimal currentYearCompanyContribution;
    private BigDecimal currentYearVpfContribution;
    private BigDecimal currentYearTotalContribution;

    //    year ti
    private BigDecimal currentYearTiMemberContribution;
    private BigDecimal currentYearTiCompanyContribution;
    private BigDecimal currentYearTiVpfContribution;
    private BigDecimal currentYearTiTotalContribution;

    //    year wl
    private BigDecimal currentYearLwMemberContribution;
    private BigDecimal currentYearLwCompanyContribution;
    private BigDecimal currentYearLwVpfContribution;
    private BigDecimal currentYearLwTotalContribution;


    //    year wl
    private BigDecimal totalMemberContribution;
    private BigDecimal totalCompanyContribution;
    private BigDecimal totalVpfContribution;
    private BigDecimal totalContribution;


    private BigDecimal loanAmountOnMemContribution;
    private BigDecimal loanAmountOnCompanyContribution;
    private BigDecimal loanAmountOnVPFContribution;
    private BigDecimal totalLoanAmount;

    private Integer noOfYearsOfMembership;
    private Integer minimumRequiredYears;
    private Integer actualNoOfYears;

    private Integer salaryMonths;
    private Integer totalPfBalance;

    private BigDecimal loanAmountBasedOnSalary;

    private BigDecimal loanAmountPF;

    private BigDecimal eligibleAmount;

    private String createdBy;

    private Date aprovedOnDate;

    private LoanHistorySheet loanHistorySheet;


    @XmlElement(name = "loanCode")
    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    @XmlElement(name = "loanType")
    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "tokenNumber")
    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    @XmlElement(name = "pfNumber")
    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    @XmlElement(name = "unitCode")
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @XmlElement(name = "department")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @XmlElement(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name = "applicationNumber")
    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    @XmlElement(name = "lastRecoveryDate")
    public String getLastRecoveryDate() {
        return fomateDate(lastRecoveryDate);
    }

    public void setLastRecoveryDate(Date lastRecoveryDate) {
        this.lastRecoveryDate = lastRecoveryDate;
    }

    @XmlElement(name = "dateOfBirth")
    public String getDateOfBirth() {
        return fomateDate(dateOfBirth);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlElement(name = "dateOfJoiningPf")
    public String getDateOfJoiningPf() {
        return fomateDate(dateOfJoiningPf);
    }

    public void setDateOfJoiningPf(Date dateOfJoiningPf) {
        this.dateOfJoiningPf = dateOfJoiningPf;
    }

    @XmlElement(name = "dateOfJoiningPrior")
    public String getDateOfJoiningPrior() {
        return fomateDate(dateOfJoiningPrior);
    }

    public void setDateOfJoiningPrior(Date dateOfJoiningPrior) {
        this.dateOfJoiningPrior = dateOfJoiningPrior;
    }

    @XmlElement(name = "applicationDate")
    public String getApplicationDate() {
        return fomateDate(applicationDate);
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @XmlElement(name = "pfBase")
    public String getPfBase() {
        return formateNumbers(pfBase);
    }

    public void setPfBase(BigDecimal pfBase) {
        this.pfBase = pfBase;
    }

    @XmlElement(name = "appliedAmount")
    public String getLoanAmountApplied() {
        return formateNumbers(loanAmountApplied);
    }

    public void setLoanAmountApplied(BigDecimal loanAmountApplied) {
        this.loanAmountApplied = loanAmountApplied;
    }

    @XmlElement(name = "cost")
    public String getCostInvolved() {
        return formateNumbers(costInvolved);
    }

    public void setCostInvolved(BigDecimal costInvolved) {
        this.costInvolved = costInvolved;
    }

    @XmlElement(name = "dateOfJoining")
    public String getDateOfJoining() {
        return fomateDate(dateOfJoining);
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @XmlElement(name = "yearOpeningDate")
    public String getYearOpeningDate() {
        return fomateDate(yearOpeningDate);
    }

    public void setYearOpeningDate(Date yearOpeningDate) {
        this.yearOpeningDate = yearOpeningDate;
    }

    @XmlElement(name = "yearOpeningMemberContribution")
    public String getYearOpeningMemberContribution() {
        return formateNumbers(yearOpeningMemberContribution);
    }

    public void setYearOpeningMemberContribution(BigDecimal yearOpeningMemberContribution) {
        this.yearOpeningMemberContribution = yearOpeningMemberContribution;
    }

    @XmlElement(name = "yearOpeningCompanyContribution")
    public String getYearOpeningCompanyContribution() {
        return formateNumbers(yearOpeningCompanyContribution);
    }

    public void setYearOpeningCompanyContribution(BigDecimal yearOpeningCompanyContribution) {
        this.yearOpeningCompanyContribution = yearOpeningCompanyContribution;
    }

    public Integer getTotalPfBalance() {
        return totalPfBalance;
    }

    public void setTotalPfBalance(Integer totalPfBalance) {
        this.totalPfBalance = totalPfBalance;
    }

    @XmlElement(name = "yearOpeningVpfContribution")
    public String getYearOpeningVpfContribution() {
        return formateNumbers(yearOpeningVpfContribution);
    }

    public void setYearOpeningVpfContribution(BigDecimal yearOpeningVpfContribution) {
        this.yearOpeningVpfContribution = yearOpeningVpfContribution;
    }

    @XmlElement(name = "yearOpeningTotalContribution")
    public String getYearOpeningTotalContribution() {
        return formateNumbers(yearOpeningTotalContribution);
    }

    public void setYearOpeningTotalContribution(BigDecimal yearOpeningTotalContribution) {
        this.yearOpeningTotalContribution = yearOpeningTotalContribution;
    }

    @XmlElement(name = "currentYearMemberContribution")
    public String getCurrentYearMemberContribution() {
        return formateNumbers(currentYearMemberContribution);
    }

    public void setCurrentYearMemberContribution(BigDecimal currentYearMemberContribution) {
        this.currentYearMemberContribution = currentYearMemberContribution;
    }

    @XmlElement(name = "currentYearCompanyContribution")
    public String getCurrentYearCompanyContribution() {
        return formateNumbers(currentYearCompanyContribution);
    }

    public void setCurrentYearCompanyContribution(BigDecimal currentYearCompanyContribution) {
        this.currentYearCompanyContribution = currentYearCompanyContribution;
    }

    @XmlElement(name = "currentYearVpfContribution")
    public String getCurrentYearVpfContribution() {
        return formateNumbers(currentYearVpfContribution);
    }

    public void setCurrentYearVpfContribution(BigDecimal currentYearVpfContribution) {
        this.currentYearVpfContribution = currentYearVpfContribution;
    }

    @XmlElement(name = "currentYearTotalContribution")
    public String getCurrentYearTotalContribution() {
        return formateNumbers(currentYearTotalContribution);
    }

    public void setCurrentYearTotalContribution(BigDecimal currentYearTotalContribution) {
        this.currentYearTotalContribution = currentYearTotalContribution;
    }

    @XmlElement(name = "currentYearTiMemberContribution")
    public String getCurrentYearTiMemberContribution() {
        return formateNumbers(currentYearTiMemberContribution);
    }

    public void setCurrentYearTiMemberContribution(BigDecimal currentYearTiMemberContribution) {
        this.currentYearTiMemberContribution = currentYearTiMemberContribution;
    }

    @XmlElement(name = "currentYearTiCompanyContribution")
    public String getCurrentYearTiCompanyContribution() {
        return formateNumbers(currentYearTiCompanyContribution);
    }

    public void setCurrentYearTiCompanyContribution(BigDecimal currentYearTiCompanyContribution) {
        this.currentYearTiCompanyContribution = currentYearTiCompanyContribution;
    }

    @XmlElement(name = "currentYearTiVpfContribution")
    public String getCurrentYearTiVpfContribution() {
        return formateNumbers(currentYearTiVpfContribution);
    }

    public void setCurrentYearTiVpfContribution(BigDecimal currentYearTiVpfContribution) {
        this.currentYearTiVpfContribution = currentYearTiVpfContribution;
    }

    @XmlElement(name = "currentYearTiTotalContribution")
    public String getCurrentYearTiTotalContribution() {
        return formateNumbers(currentYearTiTotalContribution);
    }

    public void setCurrentYearTiTotalContribution(BigDecimal currentYearTiTotalContribution) {
        this.currentYearTiTotalContribution = currentYearTiTotalContribution;
    }

    @XmlElement(name = "currentYearLwMemberContribution")
    public String getCurrentYearLwMemberContribution() {
        return formateNumbers(currentYearLwMemberContribution);
    }

    public void setCurrentYearLwMemberContribution(BigDecimal currentYearLwMemberContribution) {
        this.currentYearLwMemberContribution = currentYearLwMemberContribution;
    }

    @XmlElement(name = "currentYearLwCompanyContribution")
    public String getCurrentYearLwCompanyContribution() {
        return formateNumbers(currentYearLwCompanyContribution);
    }

    public void setCurrentYearLwCompanyContribution(BigDecimal currentYearLwCompanyContribution) {
        this.currentYearLwCompanyContribution = currentYearLwCompanyContribution;
    }

    @XmlElement(name = "currentYearLwVpfContribution")
    public String getCurrentYearLwVpfContribution() {
        return formateNumbers(currentYearLwVpfContribution);
    }

    public void setCurrentYearLwVpfContribution(BigDecimal currentYearLwVpfContribution) {
        this.currentYearLwVpfContribution = currentYearLwVpfContribution;
    }

    @XmlElement(name = "currentYearLwTotalContribution")
    public String getCurrentYearLwTotalContribution() {
        return formateNumbers(currentYearLwTotalContribution);
    }

    public void setCurrentYearLwTotalContribution(BigDecimal currentYearLwTotalContribution) {
        this.currentYearLwTotalContribution = currentYearLwTotalContribution;
    }

    @XmlElement(name = "noOfYearsOfMembership")
    public Integer getNoOfYearsOfMembership() {
        return noOfYearsOfMembership;
    }

    public void setNoOfYearsOfMembership(Integer noOfYearsOfMembership) {
        this.noOfYearsOfMembership = noOfYearsOfMembership;
    }

    @XmlElement(name = "minimumRequiredYears")
    public Integer getMinimumRequiredYears() {
        return minimumRequiredYears;
    }

    public void setMinimumRequiredYears(Integer minimumRequiredYears) {
        this.minimumRequiredYears = minimumRequiredYears;
    }

    @XmlElement(name = "actualNoOfYears")
    public Integer getActualNoOfYears() {
        return actualNoOfYears;
    }

    public void setActualNoOfYears(Integer actualNoOfYears) {
        this.actualNoOfYears = actualNoOfYears;
    }

    @XmlElement(name = "salaryMonths")
    public Integer getSalaryMonths() {
        return salaryMonths;
    }

    public void setSalaryMonths(Integer salaryMonths) {
        this.salaryMonths = salaryMonths;
    }

    @XmlElement(name = "loanAmountBasedOnSalary")
    public String getLoanAmountBasedOnSalary() {
        return formateNumbers(loanAmountBasedOnSalary);
    }

    public void setLoanAmountBasedOnSalary(BigDecimal loanAmountBasedOnSalary) {
        this.loanAmountBasedOnSalary = loanAmountBasedOnSalary;
    }

    @XmlElement(name = "loanAmountBasedOnPf")
    public String getLoanAmountPF() {
        return formateNumbers(loanAmountPF);
    }

    public void setLoanAmountPF(BigDecimal loanAmountPF) {
        this.loanAmountPF = loanAmountPF;
    }

    @XmlElement(name = "eligibleAmount")
    public String getEligibleAmount() {
        return formateNumbers(eligibleAmount);
    }

    public void setEligibleAmount(BigDecimal eligibleAmount) {
        this.eligibleAmount = eligibleAmount;
    }

    @XmlElement(name = "createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @XmlElement(name = "approvedDate")
    public String getAprovedOnDate() {
        return fomateDate(aprovedOnDate);
    }

    public void setAprovedOnDate(Date aprovedOnDate) {
        this.aprovedOnDate = aprovedOnDate;
    }

    @XmlElement(name = "totalMemberContribution")
    public String getTotalMemberContribution() {
        return formateNumbers(totalMemberContribution);
    }

    public void setTotalMemberContribution(BigDecimal totalMemberContribution) {
        this.totalMemberContribution = totalMemberContribution;
    }

    @XmlElement(name = "totalCompanyContribution")
    public String getTotalCompanyContribution() {
        return formateNumbers(totalCompanyContribution);
    }

    public void setTotalCompanyContribution(BigDecimal totalCompanyContribution) {
        this.totalCompanyContribution = totalCompanyContribution;
    }

    @XmlElement(name = "totalVpfContribution")
    public String getTotalVpfContribution() {
        return formateNumbers(totalVpfContribution);
    }

    public void setTotalVpfContribution(BigDecimal totalVpfContribution) {
        this.totalVpfContribution = totalVpfContribution;
    }

    @XmlElement(name = "totalContribution")
    public String getTotalContribution() {
        return formateNumbers(totalContribution);
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }


    @XmlElement(name = "netMemberContribution")
    public String getNetMemberContribution() {

        if(this.loanCode.equals("01") || this.loanCode.equals("12") || this.loanCode.equals("11")
                || this.loanCode.equals("13")){
            return formateNumbers(totalMemberContribution.subtract(new BigDecimal(1000)));
        }

        return formateNumbers(totalMemberContribution);
    }

    @XmlElement(name = "netCompanyContribution")
    public String getNetCompanyContribution() {

        if(this.loanCode.equals("01") || this.loanCode.equals("12") || this.loanCode.equals("11")
                || this.loanCode.equals("13")){
            return formateNumbers(totalCompanyContribution.subtract(new BigDecimal(1000)));
        }

        if(this.loanCode.equals("08")  || this.loanCode.equals("04") || this.loanCode.equals("02") || this.loanCode.equals("03")) {

            return formateNumbers(new BigDecimal(0.00));
        }
        return formateNumbers(totalCompanyContribution) ;
    }

    @XmlElement(name = "netVpfContribution")
    public String getNetVpfContribution() {
        return formateNumbers(totalVpfContribution);
    }


    @XmlElement(name = "netContribution")
    public String getNetContribution() {

        if(this.loanCode.equals("01") || this.loanCode.equals("12") || this.loanCode.equals("11")
                || this.loanCode.equals("13")){
            return formateNumbers(totalContribution.subtract(new BigDecimal(2000)));
        }
        if(this.loanCode.equals("08")  || this.loanCode.equals("04") || this.loanCode.equals("02") || this.loanCode.equals("03")) {

            return formateNumbers(totalContribution.subtract(totalCompanyContribution));
        }


        return formateNumbers(totalContribution);
    }

    @XmlElement(name = "loanAmountOnMember")
    public String getLoanAmountOnMemContribution() {
        return formateNumbers(loanAmountOnMemContribution);
    }

    public void setLoanAmountOnMemContribution(BigDecimal loanAmountOnMemContribution) {
        this.loanAmountOnMemContribution = loanAmountOnMemContribution;
    }

    @XmlElement(name = "loanAmountOnCompany")
    public String getLoanAmountOnCompanyContribution() {
        return formateNumbers(loanAmountOnCompanyContribution);
    }

    public void setLoanAmountOnCompanyContribution(BigDecimal loanAmountOnCompanyContribution) {
        this.loanAmountOnCompanyContribution = loanAmountOnCompanyContribution;
    }

    @XmlElement(name = "loanAmountOnVpf")
    public String getLoanAmountOnVPFContribution() {
        return formateNumbers(loanAmountOnVPFContribution);
    }

    public void setLoanAmountOnVPFContribution(BigDecimal loanAmountOnVPFContribution) {
        this.loanAmountOnVPFContribution = loanAmountOnVPFContribution;
    }

    @XmlElement(name = "totalLoanAmount")
    public String getTotalLoanAmount() {
        return formateNumbers(totalLoanAmount);
    }

    public void setTotalLoanAmount(BigDecimal totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }


    @XmlElement(name = "loanHistory")
    public LoanHistorySheet getLoanHistorySheet() {
        return loanHistorySheet;
    }

    public void setLoanHistorySheet(LoanHistorySheet loanHistorySheet) {
        this.loanHistorySheet = loanHistorySheet;
    }

    private String fomateDate(Date date){

        if(date == null){
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        String strDate= formatter.format(date);
        return strDate;
    }

    private String formateNumbers(BigDecimal bigDecimal){

        if (bigDecimal == null || bigDecimal.doubleValue() <= 1){
            return "0.00";
        }

        NumberFormat formatter = new DecimalFormat("##,##,###.00");
        return formatter.format(bigDecimal);
    }


}
