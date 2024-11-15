package com.coreintegra.pftrust.dtos.pdf;

import javax.xml.bind.annotation.XmlElement;

public class Form7PsContribution {

	private String monthAndYear;
	
	private String pfBase;
	
	private String pensionFund;
	
	private String remarks = "";
	
	private String srNo;

	@XmlElement(name = "srNo")
	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	@XmlElement(name = "monthAndYear")
	public String getMonthAndYear() {
		return monthAndYear;
	}

	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}

	@XmlElement(name = "pfBase")
	public String getPfBase() {
		return pfBase;
	}

	public void setPfBase(String pfBase) {
		this.pfBase = pfBase;
	}

	@XmlElement(name = "pensionFund")
	public String getPensionFund() {
		return pensionFund;
	}

	public void setPensionFund(String pensionFund) {
		this.pensionFund = pensionFund;
	}

	@XmlElement(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
