package com.coreintegra.pftrust.dtos.pdf;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formA")
public class FormA {
	
	private String establishmentName;

	private String fiscalYear;
	
	private String grossProfit;
	
	private String depreciationAddmissible;
	
	private String directTax;
	
	private String taxAmount;
	
	private String dividendPayable;
	
	private String interest;
	
	private String carryForward;
	
	private String payableBonus;
	
	private String totalDeduction;
	
	private String availableSurplus;
	
	private String allocableSurplus;
	
	private String totalAllocableBonus;
	
	private String setOnOff;
	
	private String carriedForward;

	@XmlElement(name = "establishmentName")
	public String getEstablishmentName() {
		return establishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	@XmlElement(name = "fiscalYear")
	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	@XmlElement(name = "grossProfit")
	public String getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}

	@XmlElement(name = "depreciationAddmissible")
	public String getDepreciationAddmissible() {
		return depreciationAddmissible;
	}

	public void setDepreciationAddmissible(String depreciationAddmissible) {
		this.depreciationAddmissible = depreciationAddmissible;
	}

	@XmlElement(name = "directTax")
	public String getDirectTax() {
		return directTax;
	}

	public void setDirectTax(String directTax) {
		this.directTax = directTax;
	}

	@XmlElement(name = "taxAmount")
	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	@XmlElement(name = "dividendPayable")
	public String getDividendPayable() {
		return dividendPayable;
	}

	public void setDividendPayable(String dividendPayable) {
		this.dividendPayable = dividendPayable;
	}

	@XmlElement(name = "interest")
	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	@XmlElement(name = "carryForward")
	public String getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(String carryForward) {
		this.carryForward = carryForward;
	}

	@XmlElement(name = "payableBonus")
	public String getPayableBonus() {
		return payableBonus;
	}

	public void setPayableBonus(String payableBonus) {
		this.payableBonus = payableBonus;
	}

	@XmlElement(name = "totalDeduction")
	public String getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(String totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	@XmlElement(name = "availableSurplus")
	public String getAvailableSurplus() {
		return availableSurplus;
	}

	public void setAvailableSurplus(String availableSurplus) {
		this.availableSurplus = availableSurplus;
	}

	@XmlElement(name = "allocableSurplus")
	public String getAllocableSurplus() {
		return allocableSurplus;
	}

	public void setAllocableSurplus(String allocableSurplus) {
		this.allocableSurplus = allocableSurplus;
	}

	@XmlElement(name = "totalAllocableBonus")
	public String getTotalAllocableBonus() {
		return totalAllocableBonus;
	}

	public void setTotalAllocableBonus(String totalAllocableBonus) {
		this.totalAllocableBonus = totalAllocableBonus;
	}

	@XmlElement(name = "setOnOff")
	public String getSetOnOff() {
		return setOnOff;
	}

	public void setSetOnOff(String setOnOff) {
		this.setOnOff = setOnOff;
	}

	@XmlElement(name = "carriedForward")
	public String getCarriedForward() {
		return carriedForward;
	}

	public void setCarriedForward(String carriedForward) {
		this.carriedForward = carriedForward;
	}
	
}
