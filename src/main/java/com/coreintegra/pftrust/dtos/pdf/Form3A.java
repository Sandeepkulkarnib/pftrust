package com.coreintegra.pftrust.dtos.pdf;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "form3A")
public class Form3A {

	private String accountNo;
	
	private String name;
	
	private String fatherName;
	
	private String nameAndAdddress;
	
	private List<Form3AContribution> form3AContribution;
	
	private String pfBaseTotal;
	
	private String epfTotal;
	
	private String epfAndTotal;
	
	private String pensionFundTotal;
	
	private String sumOfEpfAndEpfand;
	
	private String contributionRate;
	
	private String closureDate;
	
	private String startDate;
	
	private String endDate;

	public Form3A() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Form3A(String accountNo, String name, String fatherName, String nameAndAdddress, String pfBaseTotal,
			String epfTotal, String epfAndTotal, String pensionFundTotal, String sumOfEpfAndEpfand, String contributionRate,
			String closureDate, String startDate, String endDate) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.fatherName = fatherName;
		this.nameAndAdddress = nameAndAdddress;
		this.pfBaseTotal = pfBaseTotal;
		this.epfTotal = epfTotal;
		this.epfAndTotal = epfAndTotal;
		this.pensionFundTotal = pensionFundTotal;
		this.sumOfEpfAndEpfand = sumOfEpfAndEpfand;
		this.contributionRate = contributionRate;
		this.closureDate = closureDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@XmlElement(name = "startDate")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@XmlElement(name = "endDate")
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@XmlElement(name = "closureDate")
	public String getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(String closureDate) {
		this.closureDate = closureDate;
	}

	@XmlElement(name = "contributionRate")
	 public String getContributionRate() {
		return contributionRate;
	}

	public void setContributionRate(String contributionRate) {
		this.contributionRate = contributionRate;
	}

	@XmlElement(name = "accountNo")
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@XmlElement(name = "pfBaseTotal")
	public String getPfBaseTotal() {
		return pfBaseTotal;
	}

	public void setPfBaseTotal(String pfBaseTotal) {
		this.pfBaseTotal = pfBaseTotal;
	}

	@XmlElement(name = "epfTotal")
	public String getEpfTotal() {
		return epfTotal;
	}

	public void setEpfTotal(String epfTotal) {
		this.epfTotal = epfTotal;
	}

	@XmlElement(name = "epfAndTotal")
	public String getEpfAndTotal() {
		return epfAndTotal;
	}

	public void setEpfAndTotal(String epfAndTotal) {
		this.epfAndTotal = epfAndTotal;
	}

	@XmlElement(name = "pensionFundTotal")
	public String getPensionFundTotal() {
		return pensionFundTotal;
	}

	public void setPensionFundTotal(String pensionFundTotal) {
		this.pensionFundTotal = pensionFundTotal;
	}

	@XmlElement(name = "sumOfEpfAndEpfand")
	public String getSumOfEpfAndEpfand() {
		return sumOfEpfAndEpfand;
	}

	public void setSumOfEpfAndEpfand(String sumOfEpfAndEpfand) {
		this.sumOfEpfAndEpfand = sumOfEpfAndEpfand;
	}

	@XmlElement(name = "form3AContribution")
	public List<Form3AContribution> getForm3AContribution() {
		return form3AContribution;
	}

	public void setForm3AContribution(List<Form3AContribution> form3aContribution) {
		form3AContribution = form3aContribution;
	}
	
	
}
