package com.coreintegra.pftrust.dtos.pdf.yearend;

import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.NumberFormatter;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.Date;

public class LoanDetailsDTO {

    private String code;
    private String description;
    private Long date;
    private Date loanApprovalDate;

    private BigDecimal employeeContribution;
    private BigDecimal employerContribution;
    private BigDecimal vpfContribution;

    public LoanDetailsDTO(String code, String description, Long date, Date loanApprovalDate,
                          BigDecimal employeeContribution, BigDecimal employerContribution,
                          BigDecimal vpfContribution) {
        this.code = code;
        this.description = description;
        this.date = date;
        this.loanApprovalDate = loanApprovalDate;
        this.employeeContribution = employeeContribution;
        this.employerContribution = employerContribution;
        this.vpfContribution = vpfContribution;
    }


    @XmlElement(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "type")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "date")
    public String getDate() {
        return DateFormatterUtil.formatDate(new Date(date));
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @XmlElement(name = "loanApprovalDate")
    public String getLoanApprovalDate() {
        return DateFormatterUtil.formatDate(loanApprovalDate);
    }

    public void setLoanApprovalDate(Date loanApprovalDate) {
        this.loanApprovalDate = loanApprovalDate;
    }

    @XmlElement(name = "employeeContribution")
    public String getEmployeeContribution() {
        return NumberFormatter.formatNumbers(employeeContribution);
    }

    public void setEmployeeContribution(BigDecimal employeeContribution) {
        this.employeeContribution = employeeContribution;
    }

    @XmlElement(name = "employerContribution")
    public String getEmployerContribution() {
        return NumberFormatter.formatNumbers(employerContribution);
    }

    public void setEmployerContribution(BigDecimal employerContribution) {
        this.employerContribution = employerContribution;
    }

    @XmlElement(name = "vpfContribution")
    public String getVpfContribution() {
        return NumberFormatter.formatNumbers(vpfContribution);
    }

    public void setVpfContribution(BigDecimal vpfContribution) {
        this.vpfContribution = vpfContribution;
    }


    @XmlElement(name = "totalContribution")
    public String getTotal(){
        return NumberFormatter.formatNumbers((this.employeeContribution != null ? this.employeeContribution : new BigDecimal(0))
                .add(this.employerContribution != null ? this.employerContribution : new BigDecimal(0))
                .add(this.vpfContribution != null ? this.vpfContribution : new BigDecimal(0)));
    }

}
