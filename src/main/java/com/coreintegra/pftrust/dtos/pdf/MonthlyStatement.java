package com.coreintegra.pftrust.dtos.pdf;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@XmlRootElement(name = "monthlyStatement")
public class MonthlyStatement {

    private String name;
    private String tokenNumber;
    private String pfNumber;
    private String unitCode;

    private Date lastRecoveryDate;
    private String status;
    private Date statusDate;

    private List<Nominee> nominees;

    private Date yearOpeningDate;

    private BigDecimal yearOpeningMemberContribution;
    private BigDecimal yearOpeningCompanyContribution;
    private BigDecimal yearOpeningVpfContribution;

    private BigDecimal totalYearOpeningContribution;

    private Integer finantialYear;

    private List<ContributionDetailsForMonthlyStatement> contributionDetails;

    private BigDecimal totalMemberContribution;
    private BigDecimal totalCompanyContribution;
    private BigDecimal totalVpfContribution;

    private BigDecimal totalContribution;

    private BigDecimal transferInMemberContribution;
    private BigDecimal transferInCompanyContribution;
    private BigDecimal transferInVpfContribution;

    private BigDecimal totalTransferInContribution;

    private BigDecimal loanOnMemberContribution;
    private BigDecimal loanOnCompanyContribution;
    private BigDecimal loanOnVpfContribution;

    private BigDecimal totalLoanOnContribution;

    private Date closingDate;

    private List<TransferInDetailsForMonthlyStatement> transferInDetailsForMS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @XmlElement(name = "lastRecoveryDate")
    public String getLastRecoveryDate() {
        return fomateDate(lastRecoveryDate);
    }

    public void setLastRecoveryDate(Date lastRecoveryDate) {
        this.lastRecoveryDate = lastRecoveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "statusDate")
    public String getStatusDate() {
        return fomateDate(statusDate);
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public List<Nominee> getNominees() {
        return nominees;
    }

    public void setNominees(List<Nominee> nominees) {
        this.nominees = nominees;
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

    @XmlElement(name = "yearOpeningVpfContribution")
    public String getYearOpeningVpfContribution() {
        return formateNumbers(yearOpeningVpfContribution);
    }

    public void setYearOpeningVpfContribution(BigDecimal yearOpeningVpfContribution) {
        this.yearOpeningVpfContribution = yearOpeningVpfContribution;
    }

    @XmlElement(name = "yearOpeningTotalContribution")
    public String getTotalYearOpeningContribution() {
        return formateNumbers(totalYearOpeningContribution);
    }

    public void setTotalYearOpeningContribution(BigDecimal totalYearOpeningContribution) {
        this.totalYearOpeningContribution = totalYearOpeningContribution;
    }

    public Integer getFinantialYear() {
        return finantialYear;
    }

    public void setFinantialYear(Integer finantialYear) {
        this.finantialYear = finantialYear;
    }

    public List<ContributionDetailsForMonthlyStatement> getContributionDetails() {
        return contributionDetails;
    }

    public void setContributionDetails(List<ContributionDetailsForMonthlyStatement> contributionDetails) {
        this.contributionDetails = contributionDetails;
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

    @XmlElement(name = "transferInMemberContribution")
    public String getTransferInMemberContribution() {
        return formateNumbers(transferInMemberContribution);
    }

    public void setTransferInMemberContribution(BigDecimal transferInMemberContribution) {
        this.transferInMemberContribution = transferInMemberContribution;
    }

    @XmlElement(name = "transferInCompanyContribution")
    public String getTransferInCompanyContribution() {
        return formateNumbers(transferInCompanyContribution);
    }

    public void setTransferInCompanyContribution(BigDecimal transferInCompanyContribution) {
        this.transferInCompanyContribution = transferInCompanyContribution;
    }

    @XmlElement(name = "transferInVpfContribution")
    public String getTransferInVpfContribution() {
        return formateNumbers(transferInVpfContribution);
    }

    public void setTransferInVpfContribution(BigDecimal transferInVpfContribution) {
        this.transferInVpfContribution = transferInVpfContribution;
    }

    @XmlElement(name = "transferInTotalContribution")
    public String getTotalTransferInContribution() {
        return formateNumbers(totalTransferInContribution);
    }

    public void setTotalTransferInContribution(BigDecimal totalTransferInContribution) {
        this.totalTransferInContribution = totalTransferInContribution;
    }

    @XmlElement(name = "loanOnMemberContribution")
    public String getLoanOnMemberContribution() {
        return formateNumbers(loanOnMemberContribution);
    }

    public void setLoanOnMemberContribution(BigDecimal loanOnMemberContribution) {
        this.loanOnMemberContribution = loanOnMemberContribution;
    }

    @XmlElement(name = "loanOnCompanyContribution")
    public String getLoanOnCompanyContribution() {
        return formateNumbers(loanOnCompanyContribution);
    }

    public void setLoanOnCompanyContribution(BigDecimal loanOnCompanyContribution) {
        this.loanOnCompanyContribution = loanOnCompanyContribution;
    }

    @XmlElement(name = "loanOnVpfContribution")
    public String getLoanOnVpfContribution() {
        return formateNumbers(loanOnVpfContribution);
    }

    public void setLoanOnVpfContribution(BigDecimal loanOnVpfContribution) {
        this.loanOnVpfContribution = loanOnVpfContribution;
    }

    @XmlElement(name = "loanOnTotalContribution")
    public String getTotalLoanOnContribution() {
        return formateNumbers(totalLoanOnContribution);
    }

    public void setTotalLoanOnContribution(BigDecimal totalLoanOnContribution) {
        this.totalLoanOnContribution = totalLoanOnContribution;
    }

    private BigDecimal netMemberContribution(){
        return this.totalMemberContribution
                .add(this.transferInMemberContribution != null ?
                        this.transferInMemberContribution : new BigDecimal(0))
                .add(yearOpeningMemberContribution != null ?
                        yearOpeningMemberContribution : new BigDecimal(0))
                .subtract(this.loanOnMemberContribution != null ?
                        this.loanOnMemberContribution : new BigDecimal(0));
    }


    @XmlElement(name = "netMemberContribution")
    public String getNetMemberContribution() {
        return formateNumbers(netMemberContribution());
    }

    private BigDecimal netCompanyContribution(){
        return this.totalCompanyContribution
                .add(this.transferInCompanyContribution != null ?
                        this.transferInCompanyContribution : new BigDecimal(0))
                .add(yearOpeningCompanyContribution != null ? yearOpeningCompanyContribution : new BigDecimal(0))
                .subtract(this.loanOnCompanyContribution != null ?
                        this.loanOnCompanyContribution : new BigDecimal(0));
    }

    @XmlElement(name = "netCompanyContribution")
    public String getNetCompanyContribution() {
        return formateNumbers(netCompanyContribution());
    }

    private BigDecimal netVpfContribution(){
        return this.totalVpfContribution
                .add(this.transferInVpfContribution != null ?
                        this.transferInVpfContribution : new BigDecimal(0))
                .add(yearOpeningVpfContribution != null ? yearOpeningVpfContribution : new BigDecimal(0))
                .subtract(this.loanOnVpfContribution != null ?
                        this.loanOnVpfContribution : new BigDecimal(0));
    }


    @XmlElement(name = "netVpfContribution")
    public String getNetVpfContribution() {
        return formateNumbers(netVpfContribution());
    }

    private BigDecimal netContribution(){
        return this.netMemberContribution()
                .add(netCompanyContribution())
                .add(netVpfContribution());
    }


    @XmlElement(name = "netContribution")
    public String getNetContribution() {
        return formateNumbers(netContribution());
    }

    @XmlElement(name = "closingDate")
    public String getClosingDate() {
        return fomateDate(closingDate);
    }

    @XmlElement(name = "finantialPeriod")
    public String getFinancialPeriod(){
        return (finantialYear - 1) + " - " + finantialYear;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public List<TransferInDetailsForMonthlyStatement> getTransferInDetailsForMS() {
        return transferInDetailsForMS;
    }

    public void setTransferInDetailsForMS(List<TransferInDetailsForMonthlyStatement> transferInDetailsForMS) {
        this.transferInDetailsForMS = transferInDetailsForMS;
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
