package com.coreintegra.pftrust.dtos.pdf;

import com.coreintegra.pftrust.util.DateFormatterUtil;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.Date;

public class TransferInDetailsForMonthlyStatement {

    private Long createdAtDate;
    private Date receiptAtDate;

    private BigDecimal employeeContribution;
    private BigDecimal employerContribution;
    private String companyName;
    private String documentNumber;

    public TransferInDetailsForMonthlyStatement(Long createdAtDate, Date receiptAtDate,
                                  BigDecimal employeeContribution, BigDecimal employerContribution,
                                  String companyName, String documentNumber) {
        this.createdAtDate = createdAtDate;
        this.receiptAtDate = receiptAtDate;
        this.employeeContribution = employeeContribution;
        this.employerContribution = employerContribution;
        this.companyName = companyName;
        this.documentNumber = documentNumber;
    }


    @XmlElement(name = "createdAtDate")
    public String getCreatedAtDate() {
        return DateFormatterUtil.formatDate(new Date(createdAtDate));
    }

    public void setCreatedAtDate(Long createdAtDate) {
        this.createdAtDate = createdAtDate;
    }

    @XmlElement(name = "receiptAtDate")
    public String getReceiptAtDate() {
        return DateFormatterUtil.formatDate(receiptAtDate);
    }

    public void setReceiptAtDate(Date receiptAtDate) {
        this.receiptAtDate = receiptAtDate;
    }

    public BigDecimal getEmployeeContribution() {
        return employeeContribution;
    }

    public void setEmployeeContribution(BigDecimal employeeContribution) {
        this.employeeContribution = employeeContribution;
    }

    public BigDecimal getEmployerContribution() {
        return employerContribution;
    }

    public void setEmployerContribution(BigDecimal employerContribution) {
        this.employerContribution = employerContribution;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @XmlElement(name = "total")
    public BigDecimal getTotal(){

        return (this.employeeContribution != null ? this.employeeContribution : new BigDecimal(0))
                .add(this.employerContribution != null ? this.employerContribution : new BigDecimal(0));


    }

}
