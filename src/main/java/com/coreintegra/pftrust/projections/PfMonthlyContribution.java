package com.coreintegra.pftrust.projections;

public interface PfMonthlyContribution {
	String getPfNumber();
	String getMemberContribution();
	String getCompanyContribution();
	String getMemberExtraContribution();
	Integer getSubType();
	Integer getActualYear();
	String getFiscalYear();
}
