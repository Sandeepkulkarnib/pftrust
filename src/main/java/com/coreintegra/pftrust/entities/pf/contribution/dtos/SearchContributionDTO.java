package com.coreintegra.pftrust.entities.pf.contribution.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SearchContributionDTO {

    private String pernNumber;
    private String pfNumber;
    private String tokenNumber;
    private String name;
    private String unitCode;

    private Date recoveryDate;

    private Integer year;
    private Integer month;

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal vpfContribution;

    public SearchContributionDTO(String pernNumber, String pfNumber, String tokenNumber, String name, String unitCode,
                                 Date recoveryDate, Integer year, Integer month, BigDecimal memberContribution,
                                 BigDecimal companyContribution, BigDecimal vpfContribution) {
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.tokenNumber = tokenNumber;
        this.name = name;
        this.unitCode = unitCode;
        this.recoveryDate = recoveryDate;
        this.year = year;
        this.month = month;
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getMemberContribution() {
        return memberContribution;
    }

    public void setMemberContribution(BigDecimal memberContribution) {
        this.memberContribution = memberContribution;
    }

    public BigDecimal getCompanyContribution() {
        return companyContribution;
    }

    public void setCompanyContribution(BigDecimal companyContribution) {
        this.companyContribution = companyContribution;
    }

    public BigDecimal getVpfContribution() {
        return vpfContribution;
    }

    public void setVpfContribution(BigDecimal vpfContribution) {
        this.vpfContribution = vpfContribution;
    }

    public BigDecimal getTotalContribution(){
        return memberContribution.add(companyContribution)
                .add(vpfContribution == null ? BigDecimal.ZERO : vpfContribution);
    }
}
