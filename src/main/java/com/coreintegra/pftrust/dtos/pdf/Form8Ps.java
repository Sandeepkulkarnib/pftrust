package com.coreintegra.pftrust.dtos.pdf;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "form8ps")
public class Form8Ps {

	private String nameAndEstablishment;
	
	private String codeNumber;
	
	private Integer srNo;
	
	private String acctNo;
	
	private String name;
	
	private String pfBase;
	
	private String pension;
	
	private String remarks;
	
	private String contributionFrom;
	
	private String contributioTo;
	
	private String closureDate;
	
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

	@XmlElement(name = "nameAndEstablishment")
	public String getNameAndEstablishment() {
		return nameAndEstablishment;
	}

	public void setNameAndEstablishment(String nameAndEstablishment) {
		this.nameAndEstablishment = nameAndEstablishment;
	}

	@XmlElement(name = "codeNumber")
	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	@XmlElement(name = "srNo")
	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	@XmlElement(name = "acctNo")
	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "pfBase")
	public String getPfBase() {
		return pfBase;
	}

	public void setPfBase(String pfBase) {
		this.pfBase = pfBase;
	}

	@XmlElement(name = "pension")
	public String getPension() {
		return pension;
	}

	public void setPension(String pension) {
		this.pension = pension;
	}

	@XmlElement(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@XmlElement(name = "contributionFrom")
	public String getContributionFrom() {
		return contributionFrom;
	}

	public void setContributionFrom(String contributionFrom) {
		this.contributionFrom = contributionFrom;
	}

	@XmlElement(name = "contributioTo")
	public String getContributioTo() {
		return contributioTo;
	}

	public void setContributioTo(String contributioTo) {
		this.contributioTo = contributioTo;
	}
	
}
