package com.coreintegra.pftrust.dtos.pdf.loan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@XmlRootElement(name = "loanHistorySheet")
public class LoanHistorySheet {

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
    private BigDecimal pfBase;

    private List<LoanHistory> loanHistories;

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

    @XmlElement(name = "applicationNo")
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

    @XmlElement(name = "loan")
    public List<LoanHistory> getLoanHistories() {
        return loanHistories;
    }

    public void setLoanHistories(List<LoanHistory> loanHistories) {
        this.loanHistories = loanHistories;
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
