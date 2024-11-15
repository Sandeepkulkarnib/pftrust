package com.coreintegra.pftrust.dtos.pdf;

import javax.xml.bind.annotation.XmlElement;

public class Form3AContribution {

	private String monthAndYear;
	
	private String pfBase;
	
	private String epf;
	
	private String epfAnd;
	
	private String pensionFund;
	
	private String remarks;

	public Form3AContribution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Form3AContribution(String monthAndYear, String pfBase, String epf, String epfAnd, String pensionFund,
			String remarks) {
		super();
		this.monthAndYear = monthAndYear;
		this.pfBase = pfBase;
		this.epf = epf;
		this.epfAnd = epfAnd;
		this.pensionFund = pensionFund;
		this.remarks = remarks;
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

	@XmlElement(name = "epf")
	public String getEpf() {
		return epf;
	}

	public void setEpf(String epf) {
		this.epf = epf;
	}

	@XmlElement(name = "epfAnd")
	public String getEpfAnd() {
		return epfAnd;
	}

	public void setEpfAnd(String epfAnd) {
		this.epfAnd = epfAnd;
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
