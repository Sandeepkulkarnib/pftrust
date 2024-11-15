package com.coreintegra.pftrust.projections;

public interface PfUserInterestRates {
	Integer getId();
	String getFiscalYear();
	Double getInterestRate1();
	Double getInterestRate2();
	Integer getIsBreakDown();
	Integer getFirstBreak();
	Integer getSecondBreak();
}
