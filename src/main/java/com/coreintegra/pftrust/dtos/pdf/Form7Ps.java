package com.coreintegra.pftrust.dtos.pdf;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "form7ps")
public class Form7Ps {

    private String name;
    
    private String accountNo;
    
    private String fatherName;
    
    private String nameAndAdddress;
    
    private String satutoryRate;
    
    private String fiscalYear;
    
    private String closureDate;
    
    private String pfBaseTotal;
    
    private String epsTotal;
    
    private String reasonOfLeaving;
    
    private String dateOfLeaving;
    
    private String remarks;
    
    @XmlElement(name = "remarks")
    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@XmlElement(name = "reasonOfLeaving")
    public String getReasonOfLeaving() {
		return reasonOfLeaving;
	}

	public void setReasonOfLeaving(String reasonOfLeaving) {
		this.reasonOfLeaving = reasonOfLeaving;
	}

	@XmlElement(name = "dateOfLeaving")
	public String getDateOfLeaving() {
		return dateOfLeaving;
	}

	public void setDateOfLeaving(String dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	@XmlElement(name = "pfBaseTotal")
    public String getPfBaseTotal() {
		return pfBaseTotal;
	}

	public void setPfBaseTotal(String pfBaseTotal) {
		this.pfBaseTotal = pfBaseTotal;
	}

	@XmlElement(name = "epsTotal")
	public String getEpsTotal() {
		return epsTotal;
	}

	public void setEpsTotal(String epsTotal) {
		this.epsTotal = epsTotal;
	}

	@XmlElement(name = "closureDate")
    public String getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(String closureDate) {
		this.closureDate = closureDate;
	}

	private List<Form7PsContribution> form7PsContribution; 
     
    public List<Form7PsContribution> getForm7PsContribution() {
		return form7PsContribution;
	}

	public void setForm7PsContribution(List<Form7PsContribution> form7PsContribution) {
		this.form7PsContribution = form7PsContribution;
	}

	@XmlElement(name = "fiscalYear")
    public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	@XmlElement(name = "accountNo")
    public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@XmlElement(name = "fatherName")
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	@XmlElement(name = "nameAndAdddress")
	public String getNameAndAdddress() {
		return nameAndAdddress;
	}

	public void setNameAndAdddress(String nameAndAdddress) {
		this.nameAndAdddress = nameAndAdddress;
	}

	@XmlElement(name = "satutoryRate")
	public String getSatutoryRate() {
		return satutoryRate;
	}

	public void setSatutoryRate(String satutoryRate) {
		this.satutoryRate = satutoryRate;
	}

	@XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
}
