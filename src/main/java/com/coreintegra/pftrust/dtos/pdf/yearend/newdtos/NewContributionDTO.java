package com.coreintegra.pftrust.dtos.pdf.yearend.newdtos;

import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.util.DataUtil;
import com.coreintegra.pftrust.util.NumberFormatter;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

public class NewContributionDTO {

    private final String[] months = new String[]{"April", "May", "June", "July", "August", "September", "October",
            "November", "December", "January", "February", "March"};

    private Integer contributedIn;

    private BigDecimal nonTaxableMemberVpfContribution;
    private BigDecimal taxableMemberVpfContribution;
    private BigDecimal interestOnNonTaxableMemberVpfContribution;
    private BigDecimal interestOnTaxableMemberVpfContribution;

    private BigDecimal companyContribution;
    private BigDecimal interestOnCompanyContribution;

    public NewContributionDTO(Integer contributedIn, BigDecimal nonTaxableMemberVpfContribution,
                              BigDecimal taxableMemberVpfContribution,
                              BigDecimal interestOnNonTaxableMemberVpfContribution,
                              BigDecimal interestOnTaxableMemberVpfContribution,
                              BigDecimal companyContribution, BigDecimal interestOnCompanyContribution) {
        this.contributedIn = contributedIn;
        this.nonTaxableMemberVpfContribution = nonTaxableMemberVpfContribution;
        this.taxableMemberVpfContribution = taxableMemberVpfContribution;
        this.interestOnNonTaxableMemberVpfContribution = interestOnNonTaxableMemberVpfContribution;
        this.interestOnTaxableMemberVpfContribution = interestOnTaxableMemberVpfContribution;
        this.companyContribution = companyContribution;
        this.interestOnCompanyContribution = interestOnCompanyContribution;
    }

    @XmlElement(name = "nonTaxableMemberVpfContribution")
    public String getNonTaxableMemberVpfContribution() {
        return NumberFormatter.formatNumbers(nonTaxableMemberVpfContribution);
    }

    @XmlElement(name = "taxableMemberVpfContribution")
    public String getTaxableMemberVpfContribution() {
        return NumberFormatter.formatNumbers(taxableMemberVpfContribution);
    }

    @XmlElement(name = "interestOnNonTaxableMemberVpfContribution")
    public String getInterestOnNonTaxableMemberVpfContribution() {
        return NumberFormatter.formatNumbers(interestOnNonTaxableMemberVpfContribution);
    }

    @XmlElement(name = "interestOnTaxableMemberVpfContribution")
    public String getInterestOnTaxableMemberVpfContribution() {
        return NumberFormatter.formatNumbers(interestOnTaxableMemberVpfContribution);
    }


    @XmlElement(name = "companyContribution")
    public String getCompanyContribution() {
        return NumberFormatter.formatNumbers(companyContribution);
    }

    @XmlElement(name = "interestOnCompanyContribution")
    public String getInterestOnCompanyContribution() {
        return NumberFormatter.formatNumbers(interestOnCompanyContribution);
    }

    @XmlElement(name = "totalContributionNonTaxable")
    public String getTotalContributionNonTaxable(){
        return NumberFormatter.formatNumbers(avoidNull(nonTaxableMemberVpfContribution).add(avoidNull(companyContribution)));
    }

    @XmlElement(name = "totalContributionTaxable")
    public String getTotalContributionTaxable(){
        return NumberFormatter.formatNumbers(avoidNull(taxableMemberVpfContribution));
    }


    @XmlElement(name = "totalInterestOnContributionNonTaxable")
    public String getTotalInterestOnContributionNonTaxable(){
        return NumberFormatter.formatNumbers(avoidNull(interestOnNonTaxableMemberVpfContribution)
                .add(avoidNull(interestOnCompanyContribution)));
    }

    @XmlElement(name = "totalInterestOnContributionTaxable")
    public String getTotalInterestOnContributionTaxable(){
        return NumberFormatter.formatNumbers(avoidNull(interestOnTaxableMemberVpfContribution));
    }

    @XmlElement(name = "monthName")
    public String getCnMonth() {

        if(contributedIn == null || contributedIn == 0){
            return "";
        }

        return this.months[contributedIn-1];
    }

    private String formateNumbers(BigDecimal bigDecimal){

        if (bigDecimal == null || bigDecimal.doubleValue() <= 1){
            return "0.00";
        }

        NumberFormat formatter = new DecimalFormat("##,##,###.##");
        return formatter.format(bigDecimal);

    }

    public Integer getContributedIn() {
        return contributedIn;
    }

    public void setContributedIn(Integer contributedIn) {
        this.contributedIn = contributedIn;
    }

    public void setContributionDetails(YearEndProcess yearEndProcess){

        this.nonTaxableMemberVpfContribution = avoidNull(yearEndProcess.getNonTaxableMemberContribution())
                .add(avoidNull(yearEndProcess.getNonTaxableVpfContribution()));

        this.taxableMemberVpfContribution = avoidNull(yearEndProcess.getTaxableMemberContribution())
                .add(avoidNull(yearEndProcess.getTaxableVpfContribution()));

        this.interestOnNonTaxableMemberVpfContribution = avoidNull(yearEndProcess.getInterestOnNonTaxableMemberContribution())
                .add(avoidNull(yearEndProcess.getInterestOnNonTaxableVpfContribution()));

        this.interestOnTaxableMemberVpfContribution = avoidNull(yearEndProcess.getInterestOnTaxableMemberContribution())
                .add(avoidNull(yearEndProcess.getInterestOnTaxableVpfContribution()));

        this.companyContribution = yearEndProcess.getCompanyContribution();
        this.interestOnCompanyContribution = yearEndProcess.getInterestOnCompanyContribution();

    }

}
