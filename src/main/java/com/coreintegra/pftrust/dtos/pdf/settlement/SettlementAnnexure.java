package com.coreintegra.pftrust.dtos.pdf.settlement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@XmlRootElement(name = "settlementAnnexure")
public class SettlementAnnexure {

    private String name;
    private String tokenNumber;
    private String pfNumber;
    private String unitCode;
    private String deptCode;
    private Date dateOfSettlement;

    private float interestRate;

    private List<CalculatedAmount> memberContributions;
    private List<CalculatedAmount> companyContributions;
    private List<CalculatedAmount> vpfContributions;

    private List<CalculatedAmount> interestOnMemberContributions;
    private List<CalculatedAmount> interestOnCompanyContributions;
    private List<CalculatedAmount> interestOnVpfContributions;

    private List<CalculatedAmount> totalContributionsMonthWise;
    private List<CalculatedAmount> totalInterestMonthWise;


    public SettlementAnnexure() {
    }

    public SettlementAnnexure(String name, String tokenNumber, String pfNumber, String unitCode, String deptCode, float interestRate, List<CalculatedAmount> memberContributions, List<CalculatedAmount> companyContributions, List<CalculatedAmount> vpfContributions, List<CalculatedAmount> interestOnMemberContributions, List<CalculatedAmount> interestOnCompanyContributions, List<CalculatedAmount> interestOnVpfContributions, List<CalculatedAmount> totalContributionsMonthWise, List<CalculatedAmount> totalInterestMonthWise) {
        this.name = name;
        this.tokenNumber = tokenNumber;
        this.pfNumber = pfNumber;
        this.unitCode = unitCode;
        this.deptCode = deptCode;
        this.interestRate = interestRate;
        this.memberContributions = memberContributions;
        this.companyContributions = companyContributions;
        this.vpfContributions = vpfContributions;
        this.interestOnMemberContributions = interestOnMemberContributions;
        this.interestOnCompanyContributions = interestOnCompanyContributions;
        this.interestOnVpfContributions = interestOnVpfContributions;
        this.totalContributionsMonthWise = totalContributionsMonthWise;
        this.totalInterestMonthWise = totalInterestMonthWise;
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

    @XmlElement(name = "deptCode")
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @XmlElement(name = "interestRate")
    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    @XmlElement(name = "memberContributions")
    public List<CalculatedAmount> getMemberContributions() {
        return memberContributions;
    }

    public void setMemberContributions(List<CalculatedAmount> memberContributions) {
        this.memberContributions = memberContributions;
    }

    @XmlElement(name = "CompanyContributions")
    public List<CalculatedAmount> getCompanyContributions() {
        return companyContributions;
    }

    public void setCompanyContributions(List<CalculatedAmount> companyContributions) {
        this.companyContributions = companyContributions;
    }

    @XmlElement(name = "vpfContributions")
    public List<CalculatedAmount> getVpfContributions() {
        return vpfContributions;
    }

    public void setVpfContributions(List<CalculatedAmount> vpfContributions) {
        this.vpfContributions = vpfContributions;
    }

    @XmlElement(name = "interestOnMemberContributions")
    public List<CalculatedAmount> getInterestOnMemberContributions() {
        return interestOnMemberContributions;
    }

    public void setInterestOnMemberContributions(List<CalculatedAmount> interestOnMemberContributions) {
        this.interestOnMemberContributions = interestOnMemberContributions;
    }

    @XmlElement(name = "interestOnCompanyContributions")
    public List<CalculatedAmount> getInterestOnCompanyContributions() {
        return interestOnCompanyContributions;
    }

    public void setInterestOnCompanyContributions(List<CalculatedAmount> interestOnCompanyContributions) {
        this.interestOnCompanyContributions = interestOnCompanyContributions;
    }

    @XmlElement(name = "interestOnVpfContributions")
    public List<CalculatedAmount> getInterestOnVpfContributions() {
        return interestOnVpfContributions;
    }

    public void setInterestOnVpfContributions(List<CalculatedAmount> interestOnVpfContributions) {
        this.interestOnVpfContributions = interestOnVpfContributions;
    }

    @XmlElement(name = "totalMemberContributions")
    public String getTotalMemberContribution(){
        return formateNumbers(addList(this.memberContributions));
    }

    @XmlElement(name = "totalCompanyContributions")
    public String getTotalCompanyContribution(){
        return formateNumbers(addList(this.companyContributions));
    }

    @XmlElement(name = "totalVpfContributions")
    public String getTotalVpfContribution(){
        return formateNumbers(addList(this.vpfContributions));
    }


    @XmlElement(name = "totalInterestOnMemberContribution")
    public String getTotalInterestOnMemberContribution(){
        return formateNumbers(addList(this.interestOnMemberContributions));
    }

    @XmlElement(name = "totalInterestOnCompanyContribution")
    public String getTotalInterestOnCompanyContribution(){
        return formateNumbers(addList(this.interestOnCompanyContributions));
    }

    @XmlElement(name = "totalInterestOnVpfContribution")
    public String getTotalInterestOnVpfContribution(){
        return formateNumbers(addList(this.interestOnVpfContributions));
    }

    public void setTotalContributionsMonthWise(List<CalculatedAmount> totalContributionsMonthWise) {
        this.totalContributionsMonthWise = totalContributionsMonthWise;
    }

    public void setTotalInterestMonthWise(List<CalculatedAmount> totalInterestMonthWise) {
        this.totalInterestMonthWise = totalInterestMonthWise;
    }

    @XmlElement(name = "totalContributionsMonthWise")
    public List<CalculatedAmount> getTotalContributionsMonthWise() {
        return totalContributionsMonthWise;
    }

    @XmlElement(name = "totalInterestMonthWise")
    public List<CalculatedAmount> getTotalInterestMonthWise() {
        return totalInterestMonthWise;
    }

    @XmlElement(name = "totalContribution")
    public String getTotalContribution(){
        return formateNumbers(addList(this.totalContributionsMonthWise));
    }

    @XmlElement(name = "totalInterest")
    public String getTotalInterest(){
        return formateNumbers(addList(this.totalInterestMonthWise));
    }

    @XmlElement(name = "date")
    public String getDateOfSettlement() {
        return fomateDate(dateOfSettlement);
    }

    public void setDateOfSettlement(Date dateOfSettlement) {
        this.dateOfSettlement = dateOfSettlement;
    }

    private BigDecimal addList(List<CalculatedAmount> bigDecimals){

        BigDecimal bigDecimal = new BigDecimal(0);

        for (CalculatedAmount b:bigDecimals) {
            bigDecimal = bigDecimal.add(b.bigDecimal());
        }

        return bigDecimal;

    }


    private String formateNumbers(BigDecimal bigDecimal){

        if (bigDecimal == null || bigDecimal.doubleValue() < 1){
            return "0.00";
        }

        NumberFormat formatter = new DecimalFormat("##,##,###.00");
        return formatter.format(bigDecimal);
    }

    private String fomateDate(Date date){

        if(date == null){
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("MMMMM yyyy", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        String strDate= formatter.format(date);

        Calendar instance = Calendar.getInstance();

        instance.setTimeInMillis(date.getTime());

        return instance.get(Calendar.DATE) + getDayOfMonthSuffix(instance.get(Calendar.DATE)) + " " + strDate;
    }

    String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }


}
