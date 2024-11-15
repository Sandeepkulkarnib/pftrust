package com.coreintegra.pftrust.services.pf.customerservice;

import com.coreintegra.pftrust.dtos.pdf.SearchPfUserDTO;
import com.coreintegra.pftrust.entities.pf.employee.PfUser;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.projections.*;
import com.coreintegra.pftrust.repositories.pf.customerservice.PfUserRepository;
import com.coreintegra.pftrust.searchutil.SearchEmployeeSpecification;
import com.coreintegra.pftrust.searchutil.SearchPfUserSpecification;
import com.coreintegra.pftrust.util.DateFormatterUtil;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final PfUserRepository pfUserRepository;

    public CustomerServiceImpl(PfUserRepository pfUserRepository) {
        this.pfUserRepository = pfUserRepository;
    }

    @Override
    public Page<PfUserProjection> getPfUsers(String search, Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page-1, size);

        return pfUserRepository.findAllGroupBypfNumber(pageable, search);
    }


    @Override
    public List<PfUserFiscalYearProjection> getFiscalYears(String pfNumber) {
        return pfUserRepository.getFiscalYears(pfNumber);
    }
    
    @Override
    public Optional<PfStartYearMonth> getPfStartYearMonth(String pfNumber) {
    	System.out.println("pfnumber is "+pfNumber);
        return pfUserRepository.getStartYearAndMonth(pfNumber);
    }
    
    @Override
    public Integer getTenure(String pfNumber) {
    	return pfUserRepository.getTenure(pfNumber);
    }
    
    @Override
    public List<PfMonthlyContribution> getMonthlyContribution(String pfNumber,String fiscalYear){
    	return pfUserRepository.getMonthlyContribution(pfNumber, fiscalYear);
    }
    
    @Override
    public JSONArray getMonthlyHigherPension(String pfNumber, String fiscalYear, String first_contribution_year) {
    	
    	JSONArray monthlyHigherPension = new JSONArray();
    	
    	List<JSONObject> YearlyClosingBalance = new ArrayList<>();
    	
    	JSONArray pfBaseArray = new JSONArray();
    	
    	int counter = 0;
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
		Double pfBaseTotal = 0.00;
		Double actualPfBaseTotal = 0.00;
		Double differenceForHigherPensionTotal = 0.00;
		Double averageInterest = 0.00;
		Double interestDiffTotal = 0.00;
		Double totalAmountTotal = 0.00;
		double corpusTotal = 0;
		double corpusGrandTotal = 0;
		double admin_interest_value_total = 0;
    	List<PfStartYearMonth> listOfYears = getFiscalYearBetween(pfNumber, first_contribution_year, fiscalYear);
    	
    	for(PfStartYearMonth year: listOfYears) {
    		
    		System.out.println("Year is "+year.getFiscalYear());
	    	Optional<PfUserInterestRates> interestRate = pfUserRepository.getInterestRate(year.getFiscalYear());
	    	
	    	System.out.println("breakDown "+interestRate.get().getFiscalYear()+" , "+interestRate.get().getIsBreakDown());
	    	System.out.println("c "+year.getFiscalYear());
	    	List<PfMonthlyContribution> pfMonthlyContribution = getMonthlyContribution(pfNumber, year.getFiscalYear());
	    	System.out.println("fetched fiscal year "+year.getFiscalYear());
	    	//12 considered sicnce year consists of 12 months
//	    	int monthNumber = 12-1;
	    	int monthNumber = pfMonthlyContribution.size() > 12? 11:pfMonthlyContribution.size()-1;
	    	// 1 for counting month
	    	int month = 0;
	    	
	    	if(!interestRate.isEmpty()) {
	    		int breakCount = 0;
				for(PfMonthlyContribution con: pfMonthlyContribution) {

						month++;
						
						JSONObject highpension = new JSONObject();
			    		
			    		highpension.put("monthNumber", monthNumber);
			    		
			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
			    		double openingBalance = 0.00;
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
//					    double pfBaseInterest = 12.00;
					    double pfBaseInterest = 0;
					    if (year.getFiscalYear().equals("FY1994-1995") || year.getFiscalYear().equals("FY1995-1996")
					    		|| (year.getFiscalYear().equals("FY1996-1997") && con.getSubType() < 7 )) {
					    	pfBaseInterest = 10;
					    }else {
					    	pfBaseInterest = 12;
					    }
					    System.out.println("pfabse int calc "+pfBaseInterest +","+con.getSubType());
					    
					    double actualPfBseInterest = 8.33;
			    				
					    double eps = memberContribution-companyContribution;
	
//			    		epsTotal += eps;

			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
			    		JSONObject yearAndMonth = new JSONObject();
			    		yearAndMonth.put("pfBase", pfBase);
			    		yearAndMonth.put("subType",con.getSubType());
			    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		 
			    		  if (resultMonth > 12) {
			    		    resultMonth = 1+ (resultMonth - 12);
			    		    mon = Integer.toString(resultMonth);
			    		    resultYear = con.getActualYear();
			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		  System.out.println("result month is "+mon);
			    		String amount = getSlabAmount(resultYear+"-"+mon+"-01");  
			    		if(amount == null) {
			    			mon = "0"+(resultMonth+1);
			    		}
			    		
			    		yearAndMonth.put("actualYear", con.getActualYear());
			    		yearAndMonth.put("amount", getSlabAmount(resultYear+"-"+mon+"-01"));
			    		System.out.println("slab amount : "+getSlabAmount(resultYear+"-"+mon+"-01"));
			    		pfBaseArray.put(yearAndMonth);
			    		
			    		String admin = pfUserRepository.getAdminInterest(resultYear+"-"+mon+"-01");
			    		
//			    		Admin Interest Calculation
			    		System.out.println(resultYear+"-"+mon+"-01");
			    		double admin_interest = new Double(admin == null? "0":admin);
			    		
			    		System.out.println("admin interest "+admin_interest);
			    		
			    		System.out.println("pf-Base = "+pfBase);
			    		double admin_interest_value = Math.round(((pfBase-15000)/100)*admin_interest);
			    		if(admin_interest_value < 0) {
			    			admin_interest_value = 0;
			    		}
			    		System.out.println("admin interes Value "+admin_interest_value);
			    		admin_interest_value_total += admin_interest_value;

			    		pfBaseTotal += pfBase;
			    		
			    		String slab1 =  pfUserRepository.amount(resultYear+"-"+mon+"-01");
			    		if(slab1 == null || slab1.equals("")) {
			    			mon =  String.valueOf(Integer.parseInt(mon)+1);
			    			slab1 =  pfUserRepository.amount(resultYear+"-"+(mon)+"-01");
			    		}
			    		
			    		double slab  = new Double(slab1);
			    		System.out.println("slab amount "+slab);
			    		double slabeps = 0;
			    		if( eps != 0 && (slab  == 5000 && slab < pfBase)) {
			    			eps = 417;
			    		}else if(eps != 0 && (slab  == 5000 && slab > pfBase)) {
			    			eps = 417;
			    		}else if (eps != 0 && (slab == 6500 && slab < pfBase)) {
			    			eps = 541;
			    		}else if (eps != 0 && (slab == 6500 && slab > pfBase)) {
			    			eps = 541;
			    		}
			    		else if(eps != 0) {
			    			eps = 1250;
			    		}
			    		
			    		double actualPfBase = Math.round((pfBase/100)*actualPfBseInterest);
			   
			    		double restrictForEps =0;
			    		if (actualPfBase <= eps) {
			    			restrictForEps = actualPfBase;
			    		}else {
			    			restrictForEps = eps;
			    		}
			    		
			    		if(actualPfBase < eps) {
			    			eps =actualPfBase;
			    		}
			    		if(eps == 0 ) {
			    			actualPfBase = eps;
			    		}
			    		
			    		actualPfBaseTotal += actualPfBase;
			    		
			    		epsTotal += eps;
			    		
			    		double differenceForHigherPension = actualPfBase-eps;
	
			    		differenceForHigherPensionTotal += differenceForHigherPension;
			    		
			    		double interest = 0.00;
			    		double a = 0;
			    		if(interestRate.get().getIsBreakDown() != 1) {
			    			a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    			interest = interestRate.get().getInterestRate1();
			    			
			    			corpusTotal = memberContribution + companyContribution + eps;
				    		double corpusInterest = ((corpusTotal*interestRate.get().getInterestRate1())/100)/12*monthNumber;
				    		corpusTotal = corpusTotal + corpusInterest;
				    		
			    		}else if(interestRate.get().getIsBreakDown() == 1) {
			    			if(breakCount < interestRate.get().getFirstBreak()) {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    				interest = interestRate.get().getInterestRate1();
			    				
			    				corpusTotal = memberContribution + companyContribution + eps;
			    				System.out.println("corpus "+corpusTotal);
			    				double corpusInterest = ((corpusTotal*interestRate.get().getInterestRate1())/100)/12*monthNumber;
					    		corpusTotal = corpusTotal + corpusInterest;
			    			}else {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate2();
			    				interest = interestRate.get().getInterestRate2();
			    				
			    				corpusTotal = memberContribution + companyContribution + eps;
			    				System.out.println("corpus "+corpusTotal);
			    				double corpusInterest = ((corpusTotal*interestRate.get().getInterestRate2())/100)/12*monthNumber;
					    		corpusTotal = corpusTotal + corpusInterest;
			    			}
			    		}
			    		// adding each month corpus to its grand corpus which will be added to every year closing
			    		corpusGrandTotal += corpusTotal;
			    		
			    		double b = a/12;

			    		double interestOnDifferential = 0.00;
	
			    		if(monthNumber != 0) {
			    			interestOnDifferential =Math.round(b*monthNumber);
			    		}

			    		interestDiffTotal += interestOnDifferential;
			    		
			    		double totalAmount = Math.round(differenceForHigherPension+interestOnDifferential);
	
			    		totalAmountTotal += totalAmount;
					    
			    		if(listOfYears.get(0).getFiscalYear() ==  year.getFiscalYear() && month == 1) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", 12);
			    			highpension1.put("employeeControbution", "Opening Balance");
				    		highpension1.put("companyContribution", "");
				    		highpension1.put("vpfContribution", "");
				    		highpension1.put("eps", "");
				    		highpension1.put("PfBase", "");
				    		highpension1.put("actualPfBase", "");
				    		highpension1.put("differenceForHigherPension", 0);
				    		highpension1.put("interestRate", interest+"%");
				    		highpension1.put("interestOnDifferential", 0);
				    		highpension1.put("corpusForTheMonth", 0);
				    		highpension1.put("totalAmount",Math.round(corpusGrandTotal));
				    		totalAmountTotal += openingBalance;
				    		monthlyHigherPension.put(highpension1);
			    		}
			    		if(listOfYears.get(0).getFiscalYear() !=  year.getFiscalYear() && month == 1) {
			  
			    				Double objectTotalAmount = YearlyClosingBalance.get(counter-1).getDouble("totalAmount");
//			    				System.out.println("totalAmount : "+objectTotalAmount);
			    				Double objectInterestRate =  interest;
			    				
			    				double corpusForTheYear = YearlyClosingBalance.get(counter-1).getDouble("corpusForTheMonth");
			    				
			    				double corpusForTheYearInterest = Math.round((corpusForTheYear/100)*interest);
			    				
				    			System.out.println(objectTotalAmount+" , "+objectInterestRate);
				    			int div = 12;
				    			openingBalance = (((objectTotalAmount/100)*objectInterestRate)/div)*12;
				    			
				    			JSONObject highpension1 =  new JSONObject();
				    			highpension1.put("monthNumber", 12);
				    			highpension1.put("employeeControbution", "Opening Balance");
					    		highpension1.put("companyContribution", "");
					    		highpension1.put("vpfContribution", "");
					    		highpension1.put("eps", "");
					    		highpension1.put("PfBase", "");
					    		highpension1.put("actualPfBase", "");
					    		highpension1.put("differenceForHigherPension", Math.round(objectTotalAmount));
					    		highpension1.put("interestRate", interest+"%");
					    		highpension1.put("interestOnDifferential",Math.round(openingBalance));
					    		highpension1.put("corpusForTheMonth",Math.round(corpusForTheYear + corpusForTheYearInterest));
					    		highpension1.put("totalAmount", Math.round(Math.round(objectTotalAmount)+Math.round(openingBalance)));
//					    		totalAmountTotal = totalAmountTotal + objectTotalAmount + openingBalance;
					    		totalAmountTotal = totalAmountTotal + openingBalance;
					    		corpusGrandTotal = Math.round(corpusGrandTotal + corpusForTheYearInterest);
					    		monthlyHigherPension.put(highpension1);
			    		}
			    		
//			    		if(fiscalYear == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
			    			
			    			highpension.put("employeeControbution", memberContribution);
							highpension.put("companyContribution", companyContribution);
							highpension.put("vpfContribution", vpfContribution);
							highpension.put("eps", Math.round(eps));
							highpension.put("PfBase", Math.round(pfBase));
							highpension.put("actualPfBase", Math.round(actualPfBase));
							highpension.put("differenceForHigherPension", Math.round(differenceForHigherPension));
							highpension.put("interestRate", interest+"%");
							highpension.put("fisccal_year", year.getFiscalYear());
							highpension.put("interestOnDifferential", Math.round(interestOnDifferential));
							highpension.put("corpusForTheMonth",Math.round( corpusTotal));
							highpension.put("totalAmount", Math.round(totalAmount));
							highpension.put("admin_interest", admin_interest_value);
				    		
				    		monthlyHigherPension.put(highpension);
//			    		}
						
	
			    		monthNumber = monthNumber-1;

			    		if(month == 12) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "Total");
			    			highpension1.put("employeeControbution", Math.round(employeeTotal));
				    		highpension1.put("companyContribution", Math.round(employerTotal));
				    		highpension1.put("vpfContribution", Math.round(vpfTotal));
				    		highpension1.put("eps", Math.round(epsTotal));
				    		highpension1.put("PfBase", Math.round(pfBaseTotal));
				    		highpension1.put("actualPfBase", Math.round(actualPfBaseTotal));
				    		highpension1.put("differenceForHigherPension", Math.round(differenceForHigherPensionTotal));
				    		highpension1.put("interestRate", interest);
				    		highpension.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("interestOnDifferential", Math.round(interestDiffTotal));
				    		highpension1.put("corpusForTheMonth",Math.round(corpusGrandTotal));
				    		highpension1.put("totalAmount",Math.round(totalAmountTotal));
				    		highpension1.put("admin_interest", admin_interest_value_total);
				    		monthlyHigherPension.put(highpension1);
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		} 
			    		if(pfMonthlyContribution.size() < 12 && pfMonthlyContribution.size() == month) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "Total");
			    			highpension1.put("employeeControbution",Math.round(employeeTotal));
				    		highpension1.put("companyContribution", Math.round(employerTotal));
				    		highpension1.put("vpfContribution", Math.round(vpfTotal));
				    		highpension1.put("eps", Math.round(epsTotal));
				    		highpension1.put("PfBase", Math.round(pfBaseTotal));
				    		highpension1.put("actualPfBase", Math.round(actualPfBaseTotal));
				    		highpension1.put("differenceForHigherPension",Math.round(differenceForHigherPensionTotal));
				    		highpension1.put("interestRate", interest);
				    		highpension.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("interestOnDifferential", Math.round(interestDiffTotal));
				    		highpension1.put("corpusForTheMonth",Math.round(corpusGrandTotal));
				    		highpension1.put("totalAmount", Math.round(totalAmountTotal));
				    		highpension1.put("admin_interest", admin_interest_value_total);
				    		monthlyHigherPension.put(highpension1);
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		}
			    		System.out.println("corpus Grand for year "+ Math.round(corpusGrandTotal));
//			    	}
			    		breakCount += 1;
				}
			}
	    	counter += 1;
    	}
    	System.out.println("final admin interest "+admin_interest_value_total);
    	System.out.println("corpus Total : "+corpusTotal);
//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
    	monthlyHigherPension.put(pfBaseArray);
    	return monthlyHigherPension;
    }

    
    @Override
    public Optional<PfUserInterestRates> getInterestRate(String fiscalYear){
    	return pfUserRepository.getInterestRate(fiscalYear);
    }
    
    @Override
    public List<PfStartYearMonth> getFiscalYearBetween(String pfNumber,String firstFiscalYear, String secondFiscalYear){
    	return pfUserRepository.getFiscalYearBetween(pfNumber, firstFiscalYear, secondFiscalYear);
    }
    
    public String getSlabAmount(String date) {
    	return pfUserRepository.amount(date);
    }
    
    @Override
    public JSONArray getMonthlyHigherPension1(String pfNumber, String fiscalYear, String first_contribution_year) {

    	JSONArray monthlyHigherPension = new JSONArray();
    	
    	List<JSONObject> YearlyClosingBalance = new ArrayList<>();

    	int counter = 0;
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
		Double pfBaseTotal = 0.00;
		Double actualPfBaseTotal = 0.00;
		Double differenceForHigherPensionTotal = 0.00;
		Double interestDiffTotal = 0.00;
		Double totalAmountTotal = 0.00;
		double admin_interest_value_total = 0;
    	List<PfStartYearMonth> listOfYears = getFiscalYearBetween(pfNumber,first_contribution_year, fiscalYear);
    	
    	for(PfStartYearMonth year: listOfYears) {
    		
    	
	    	Optional<PfUserInterestRates> interestRate = pfUserRepository.getInterestRate(year.getFiscalYear());
	    	
	    	System.out.println("breakDown "+interestRate.get().getFiscalYear()+" , "+interestRate.get().getIsBreakDown());
	    	
	    	List<PfMonthlyContribution> pfMonthlyContribution = getMonthlyContribution(pfNumber, year.getFiscalYear());
	    
	    	//12 considered sicnce year consists of 12 months
	    	int monthNumber = 12-1;
	    	
	    	// 1 for counting month
	    	int month = 0;
	    	
	    	if(!interestRate.isEmpty()) {
	    		int breakCount = 0;
				for(PfMonthlyContribution con: pfMonthlyContribution) {

						month++;
						
						JSONObject highpension = new JSONObject();
			    		
			    		highpension.put("monthNumber", monthNumber);
			    		
			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
			    		double openingBalance = 0.00;
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
//					    double pfBaseInterest = 12.00;
					    double pfBaseInterest = 0;
					    if (year.getFiscalYear().equals("FY1994-1995") || year.getFiscalYear().equals("FY1995-1996")
					    		|| (year.getFiscalYear().equals("FY1996-1997") && con.getSubType() < 7 )) {
					    	pfBaseInterest = 10;
					    }else {
					    	pfBaseInterest = 12;
					    }
					    System.out.println("pfabse int calc "+pfBaseInterest +","+con.getSubType());
					    double actualPfBseInterest = 8.33;
			    				
					    double eps = memberContribution-companyContribution;
	
//			    		epsTotal += eps;
			    		
			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
			    		JSONObject yearAndMonth = new JSONObject();
			    		yearAndMonth.put("pfBase", pfBase);
			    		yearAndMonth.put("subType",con.getSubType());
			    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		 
			    		  if (resultMonth > 12) {
//			    		    resultMonth = 1+ (resultMonth - 12);
			    			  resultMonth = resultMonth - 12;
			    		    mon = Integer.toString(resultMonth);
			    		    resultYear = con.getActualYear();
			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		  
			    		 String date = mon+"/"+resultYear;

			    		pfBaseTotal += pfBase;
			    		
			    		String slab1 =  pfUserRepository.amount(resultYear+"-"+mon+"-01");
			    		if(slab1 == null || slab1.equals("")) {
			    			mon =  String.valueOf(Integer.parseInt(mon)+1);
			    			slab1 =  pfUserRepository.amount(resultYear+"-"+(mon)+"-01");
			    		}
			    		
			    		double slab  = new Double(slab1);
			    		System.out.println("slab amount "+slab);
			    		double slabeps = 0;
			    		if(eps != 0 && (slab  == 5000 && slab < pfBase)) {
			    			eps = 417;
			    		}else if(eps != 0 &&( slab  == 5000 && slab > pfBase)) {
			    			eps = 417;
			    		}else if (eps != 0 && (slab == 6500 && slab < pfBase)) {
			    			eps = 541;
			    		}else if (eps != 0 && (slab == 6500 && slab > pfBase)) {
			    			eps = 541;
			    		}
			    		else if(eps != 0) {
			    			eps = 1250;
			    		}
			    		
			    		
			    		
			    		//Admin Interest Calculation
			    		String admin = pfUserRepository.getAdminInterest(resultYear+"-"+mon+"-01");
			    		
			    		System.out.println(resultYear+"-"+mon+"-01");
			    		double admin_interest = new Double(admin == null? "0":admin);
			    		
			    		System.out.println("admin interest "+admin_interest);
			    		
			    		System.out.println("pf-Base = "+pfBase);
			    		double admin_interest_value = Math.round(((pfBase-15000)/100)*admin_interest);
			    		if(admin_interest_value < 0) {
			    			admin_interest_value = 0;
			    		}
			    		System.out.println("admin interes Value "+admin_interest_value);
			    		admin_interest_value_total += admin_interest_value;
			    		
			    		
			    		double actualPfBase = Math.round((pfBase/100)*actualPfBseInterest);
			   
			    		double restrictForEps =0;
			    		if (actualPfBase <= eps) {
			    			restrictForEps = actualPfBase;
			    		}else {
			    			restrictForEps = eps;
			    		}
			    		
			    		if(actualPfBase < eps) {
			    			eps =actualPfBase;
			    		}
			    		if(eps == 0 ) {
			    			actualPfBase = eps;
			    		}
			    		actualPfBaseTotal += actualPfBase;
			    		
			    		epsTotal += eps;
//			    		if(actualPfBase > eps ) {
//			    			actualPfBase = eps ;
//			    		}
			    					    		
			    		double differenceForHigherPension = actualPfBase-eps;
	
			    		differenceForHigherPensionTotal += differenceForHigherPension;
			    		
			    		double interest = 0.00;
			    		double a = 0;
			    		if(interestRate.get().getIsBreakDown() != 1) {
			    			a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    			interest = interestRate.get().getInterestRate1();
			    		}else if(interestRate.get().getIsBreakDown() == 1) {
			    			if(breakCount < interestRate.get().getFirstBreak()) {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    				interest = interestRate.get().getInterestRate1();
			    			}else {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate2();
			    				interest = interestRate.get().getInterestRate2();
			    			}
			    		}
	
			    		double b = a/12;

			    		double interestOnDifferential = 0.00;
	
			    		if(monthNumber != 0) {
			    			interestOnDifferential =Math.round(b*monthNumber);
			    		}

			    		interestDiffTotal += interestOnDifferential;
			    		
			    		double totalAmount = Math.round(differenceForHigherPension+interestOnDifferential);
	
			    		totalAmountTotal += totalAmount;
					    
			    		if(listOfYears.get(0).getFiscalYear() ==  year.getFiscalYear() && month == 1) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", 12);
			    			highpension1.put("yearAndMonth", date);
			    			highpension1.put("employeeControbution", "Opening Balance");
				    		highpension1.put("companyContribution", "");
				    		highpension1.put("vpfContribution", "");
				    		highpension1.put("eps", "");
				    		highpension1.put("PfBase", "");
				    		highpension1.put("actualPfBase", "");
				    		highpension1.put("differenceForHigherPension", 0);
				    		highpension1.put("interestRate", interest+"%");
				    		highpension1.put("interestOnDifferential", 0);
				    		highpension1.put("totalAmount",Math.round(openingBalance));
				    		totalAmountTotal += openingBalance;
				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
				    		}
			    		}
			    		if(listOfYears.get(0).getFiscalYear() !=  year.getFiscalYear() && month == 1) {
			  
			    				Double objectTotalAmount = YearlyClosingBalance.get(counter-1).getDouble("totalAmount");
			    				System.out.println("totalAmount : "+objectTotalAmount);
			    				Double objectInterestRate =  interest;
			    				
			    				double admin_interest_for_the_year = YearlyClosingBalance.get(counter-1).getDouble("admin_interest");

				    			System.out.println(objectTotalAmount+" , "+objectInterestRate);
				    			int div = 12;
				    			openingBalance = (((objectTotalAmount/100)*objectInterestRate)/div)*12;
				    			
				    			System.out.println("openig balance "+Math.round(openingBalance));
				    			
				    			JSONObject highpension1 =  new JSONObject();
				    			highpension1.put("monthNumber", 12);
				    			highpension1.put("employeeControbution", "Opening Balance");
					    		highpension1.put("companyContribution", "");
					    		highpension1.put("vpfContribution", "");
					    		highpension1.put("eps", "");
					    		highpension1.put("PfBase", "");
					    		highpension1.put("actualPfBase", "");
					    		highpension1.put("differenceForHigherPension", Math.round(objectTotalAmount));
					    		highpension1.put("interestRate", interest+"%");
					    		highpension1.put("interestOnDifferential",Math.round(openingBalance));
					    		highpension.put("admin_interest", admin_interest_for_the_year);
					    		highpension1.put("totalAmount", Math.round(Math.round(objectTotalAmount)+Math.round(openingBalance)));
//					    		totalAmountTotal = totalAmountTotal + objectTotalAmount + openingBalance;
					    		totalAmountTotal = totalAmountTotal + openingBalance;
					    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
					    			monthlyHigherPension.put(highpension1);
					    		}
			    		}
			    		
			    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
			    			highpension.put("yearAndMonth", date);
			    			highpension.put("fisccal_year", year.getFiscalYear());
			    			highpension.put("employeeControbution", memberContribution);
							highpension.put("companyContribution", companyContribution);
							highpension.put("vpfContribution", vpfContribution);
							highpension.put("eps", Math.round(eps));
							highpension.put("PfBase", Math.round(pfBase));
							highpension.put("actualPfBase", Math.round(actualPfBase));
							highpension.put("differenceForHigherPension", Math.round(differenceForHigherPension));
							highpension.put("interestRate", interest+"%");
							highpension.put("interestOnDifferential", Math.round(interestOnDifferential));
							highpension.put("admin_interest", admin_interest_value);
							highpension.put("totalAmount", Math.round(totalAmount));
							if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
								monthlyHigherPension.put(highpension);
							}
			    		}
						
	
			    		monthNumber = monthNumber-1;

			    		if(month == 12) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "Total");
//			    			highpension1.put("yearAndMonth", date);
//			    			highpension.put("fisccal_year", year.getFiscalYear());
			    			highpension1.put("employeeControbution", Math.round(employeeTotal));
				    		highpension1.put("companyContribution", Math.round(employerTotal));
				    		highpension1.put("vpfContribution", Math.round(vpfTotal));
				    		highpension1.put("eps", Math.round(epsTotal));
				    		highpension1.put("PfBase", Math.round(pfBaseTotal));
				    		highpension1.put("actualPfBase", Math.round(actualPfBaseTotal));
				    		highpension1.put("differenceForHigherPension", Math.round(differenceForHigherPensionTotal));
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("interestOnDifferential", Math.round(interestDiffTotal));
				    		highpension1.put("admin_interest", admin_interest_value_total);
				    		highpension1.put("totalAmount",Math.round(totalAmountTotal));
				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
				    		}
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		} 
			    		if(pfMonthlyContribution.size() < 12 && pfMonthlyContribution.size() == month) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "Total");
//			    			highpension1.put("yearAndMonth", date);
//			    			highpension.put("fisccal_year", year.getFiscalYear());
			    			highpension1.put("employeeControbution",Math.round(employeeTotal));
				    		highpension1.put("companyContribution", Math.round(employerTotal));
				    		highpension1.put("vpfContribution", Math.round(vpfTotal));
				    		highpension1.put("eps", Math.round(epsTotal));
				    		highpension1.put("PfBase", Math.round(pfBaseTotal));
				    		highpension1.put("actualPfBase", Math.round(actualPfBaseTotal));
				    		highpension1.put("differenceForHigherPension",Math.round(differenceForHigherPensionTotal));
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("interestOnDifferential", Math.round(interestDiffTotal));
				    		highpension1.put("admin_interest", admin_interest_value_total);
				    		highpension1.put("totalAmount", Math.round(totalAmountTotal));
				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
				    		}
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		}
//			    	}
			    		breakCount += 1;
				}
			}
	    	counter += 1;
    	}
//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
//    	monthlyHigherPension.put(pfBaseArray);
    	return monthlyHigherPension;
    }
   
    
    @Override
    public JSONArray getServiceSummary(String pfNumber, String fiscalYear, String first_contribution_year) {
    	
    	JSONArray monthlyHigherPension = new JSONArray();
    	
    	List<JSONObject> YearlyClosingBalance = new ArrayList<>();
    	
    	JSONArray pfBaseArray = new JSONArray();
    	
    	int counter = 0;
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
		Double pfBaseTotal = 0.00;
		Double actualPfBaseTotal = 0.00;
//		Double differenceForHigherPensionTotal = 0.00;
		Double averageInterest = 0.00;
//		Double interestDiffTotal = 0.00;
		Double totalAmountTotal = 0.00;
		double admin_interest_value_total = 0;
    	List<PfStartYearMonth> listOfYears = getFiscalYearBetween(pfNumber,first_contribution_year, fiscalYear);
    	
    	for(PfStartYearMonth year: listOfYears) {
    		
    		Double differenceForHigherPensionTotal = 0.00;
    		Double interestDiffTotal = 0.00;
    		
	    	Optional<PfUserInterestRates> interestRate = pfUserRepository.getInterestRate(year.getFiscalYear());
	    	
	    	System.out.println("breakDown "+interestRate.get().getFiscalYear()+" , "+interestRate.get().getIsBreakDown());
	    	
	    	List<PfMonthlyContribution> pfMonthlyContribution = getMonthlyContribution(pfNumber, year.getFiscalYear());
	    
	    	//12 considered sicnce year consists of 12 months
	    	int monthNumber = 12-1;
	    	
	    	// 1 for counting month
	    	int month = 0;
	    	
	    	if(!interestRate.isEmpty()) {
	    		int breakCount = 0;
				for(PfMonthlyContribution con: pfMonthlyContribution) {

						month++;
						
						JSONObject highpension = new JSONObject();
			    		
			    		highpension.put("monthNumber", monthNumber);
			    		
			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
			    		double openingBalance = 0.00;
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
//					    double pfBaseInterest = 12.00;
					    double pfBaseInterest = 0;
					    if (year.getFiscalYear().equals("FY1994-1995") || year.getFiscalYear().equals("FY1995-1996")
					    		|| (year.getFiscalYear().equals("FY1996-1997") && con.getSubType() < 7 )) {
					    	pfBaseInterest = 10;
					    }else {
					    	pfBaseInterest = 12;
					    }
					    System.out.println("pfabse int calc "+pfBaseInterest +","+con.getSubType());
					    
					    double actualPfBseInterest = 8.33;
			    				
					    double eps = memberContribution-companyContribution;
	
//			    		epsTotal += eps;
			    		
			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
			    		JSONObject yearAndMonth = new JSONObject();
			    		yearAndMonth.put("pfBase", pfBase);
			    		yearAndMonth.put("subType",con.getSubType());
			    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		 
			    		  if (resultMonth > 12) {
			    		    resultMonth = 1+ (resultMonth - 12);
			    		    mon = Integer.toString(resultMonth);
			    		    resultYear = con.getActualYear();
			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		  
			    		yearAndMonth.put("actualYear", con.getActualYear());
			    		yearAndMonth.put("amount", getSlabAmount(resultYear+"-"+mon+"-01"));
			    		pfBaseArray.put(yearAndMonth);
		
			    		pfBaseTotal += pfBase;
			    		
			    		String slab1 =  pfUserRepository.amount(resultYear+"-"+mon+"-01");
			    		if(slab1 == null || slab1.equals("")) {
			    			mon =  String.valueOf(Integer.parseInt(mon)+1);
			    			slab1 =  pfUserRepository.amount(resultYear+"-"+(mon)+"-01");
			    		}
			    		
			    		double slab  = new Double(slab1);
			    		System.out.println("slab amount "+slab);
			    		double slabeps = 0;
			    		if(eps != 0 && (slab  == 5000 && slab < pfBase)) {
			    			eps = 417;
			    		}else if(eps != 0 && (slab  == 5000 && slab > pfBase)) {
			    			eps = 417;
			    		}else if (eps != 0 && (slab == 6500 && slab < pfBase)) {
			    			eps = 541;
			    		}else if (eps != 0 && (slab == 6500 && slab > pfBase)) {
			    			eps = 541;
			    		}
			    		else if(eps != 0) {
			    			eps = 1250;
			    		}
			    		
			    		//Admin Interest Calculation
			    		String admin = pfUserRepository.getAdminInterest(resultYear+"-"+mon+"-01");
			    		
			    		System.out.println(resultYear+"-"+mon+"-01");
			    		double admin_interest = new Double(admin == null? "0":admin);
			    		
			    		System.out.println("admin interest "+admin_interest);
			    		
			    		System.out.println("pf-Base = "+pfBase);
			    		double admin_interest_value = Math.round(((pfBase-15000)/100)*admin_interest);
			    		if(admin_interest_value < 0) {
			    			admin_interest_value = 0;
			    		}
			    		System.out.println("admin interes Value "+admin_interest_value);
			    		admin_interest_value_total += admin_interest_value;
			    		
			    		double actualPfBase = Math.round((pfBase/100)*actualPfBseInterest);
			   
			    		double restrictForEps =0;
			    		if (actualPfBase <= eps) {
			    			restrictForEps = actualPfBase;
			    		}else {
			    			restrictForEps = eps;
			    		}
			    		
			    		if(actualPfBase < eps) {
			    			eps =actualPfBase;
			    		}
			    		if(eps == 0) {
			    			actualPfBase = eps;
			    		}
			    		
			    		actualPfBaseTotal += actualPfBase;
			    		
			    		epsTotal += eps;
			    		
			    		double differenceForHigherPension = actualPfBase-eps;
	
			    		differenceForHigherPensionTotal += differenceForHigherPension;
			    		
			    		double interest = 0.00;
			    		double a = 0;
			    		if(interestRate.get().getIsBreakDown() != 1) {
			    			a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    			interest = interestRate.get().getInterestRate1();
			    		}else if(interestRate.get().getIsBreakDown() == 1) {
			    			if(breakCount < interestRate.get().getFirstBreak()) {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    				interest = interestRate.get().getInterestRate1();
			    			}else {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate2();
			    				interest = interestRate.get().getInterestRate2();
			    			}
			    		}
	
			    		double b = a/12;

			    		double interestOnDifferential = 0.00;
	
			    		if(monthNumber != 0) {
			    			interestOnDifferential =Math.round(b*monthNumber);
			    		}

			    		interestDiffTotal += interestOnDifferential;
			    		
			    		double totalAmount = Math.round(differenceForHigherPension+interestOnDifferential);
	
			    		totalAmountTotal += totalAmount;
					    
			    		if(listOfYears.get(0).getFiscalYear() ==  year.getFiscalYear() && month == 1) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", 12);
			    			highpension1.put("employeeControbution", "Opening Balance");
				    		highpension1.put("differenceForHigherPension", 0);
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("interestOnDifferential", 0);
				    		highpension1.put("totalAmount",Math.round(openingBalance));
				    		highpension1.put("admin_interest","");
				    		totalAmountTotal += openingBalance;
				    		monthlyHigherPension.put(highpension1);
			    		}
			    		if(listOfYears.get(0).getFiscalYear() !=  year.getFiscalYear() && month == 1) {
			  
			    				Double objectTotalAmount = YearlyClosingBalance.get(counter-1).getDouble("totalAmount");
			    				System.out.println("totalAmount : "+objectTotalAmount);
			    				Double objectInterestRate =  interest;

			    				double admin_interest_for_year = YearlyClosingBalance.get(counter-1).getDouble("admin_interest");
			    				
				    			System.out.println(objectTotalAmount+" , "+objectInterestRate);
				    			int div = 12;
				    			openingBalance = (((objectTotalAmount/100)*objectInterestRate)/div)*12;
				    			
				    			System.out.println("openig balance "+Math.round(openingBalance));
				    			
				    			JSONObject highpension1 =  new JSONObject();
				    			highpension1.put("monthNumber", 12);
				    			highpension1.put("employeeControbution", "Opening Balance");
					    		highpension1.put("differenceForHigherPension", Math.round(objectTotalAmount));
					    		highpension1.put("interestRate", interest);
					    		highpension1.put("fisccal_year", year.getFiscalYear());
					    		highpension1.put("interestOnDifferential",Math.round(openingBalance));
					    		highpension1.put("admin_interest",admin_interest_for_year);
					    		highpension1.put("totalAmount", Math.round(Math.round(objectTotalAmount)+Math.round(openingBalance)));
					    		totalAmountTotal = totalAmountTotal + openingBalance;
					    		monthlyHigherPension.put(highpension1);
			    		}
			    			
			    		monthNumber = monthNumber-1;

			    		if(month == 12) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "");
			    			highpension1.put("employeeControbution", "Closing Balance");
				    		highpension1.put("differenceForHigherPension", Math.round(differenceForHigherPensionTotal));
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("interestOnDifferential", Math.round(interestDiffTotal));
				    		highpension1.put("admin_interest", admin_interest_value_total);
				    		System.out.println("admin_interest_total = "+admin_interest_value_total);
				    		highpension1.put("totalAmount",Math.round(totalAmountTotal));
				    		monthlyHigherPension.put(highpension1);
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		} 
			    		if(pfMonthlyContribution.size() < 12 && pfMonthlyContribution.size() == month) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "");
			    			highpension1.put("employeeControbution","Closing Balance");
				    		highpension1.put("differenceForHigherPension",Math.round(differenceForHigherPensionTotal));
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("interestOnDifferential", Math.round(interestDiffTotal));
				    		highpension1.put("admin_interest", admin_interest_value_total);
				    		highpension1.put("totalAmount", Math.round(totalAmountTotal));
				    		monthlyHigherPension.put(highpension1);
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		}
//			    	}
			    		breakCount += 1;
				}
			}
	    	counter += 1;
    	}
//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
//    	monthlyHigherPension.put(pfBaseArray);
    	return monthlyHigherPension;
    }

    @Override
    public JSONArray get60MonthData(String pfNumber) {
    	
    	JSONArray pfBaseArray = new JSONArray();
    	
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
System.out.println("im called saran");
	    	List<PfMonthlyContribution> pfMonthlyContribution = pfUserRepository.get60MonthContribution(pfNumber);
	    	System.out.println(pfMonthlyContribution.size());
	    	System.out.println("crossed");
				for(PfMonthlyContribution con: pfMonthlyContribution) {

			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
					    double pfBaseInterest = 12.00;
		
					    double eps = memberContribution-companyContribution;
	
			    		epsTotal += eps;
			    		
			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
			    		JSONObject yearAndMonth = new JSONObject();
			    					    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		 
			    		  if (resultMonth > 12) {
			    		    resultMonth = 1+ (resultMonth - 12);
			    		    mon = Integer.toString(resultMonth);
			    		    resultYear = con.getActualYear();
			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		 
			    		String dateString = resultYear+"-"+mon+"-01";
//			    		System.out.println(dateString);
//			    		yearAndMonth.put("actualYear", con.getActualYear());
			    		yearAndMonth.put("amount", getSlabAmount(resultYear+"-"+mon+"-01"));
			    		yearAndMonth.put("pfBase", pfBase);
			    		yearAndMonth.put("subType",con.getSubType());
			    		yearAndMonth.put("fiscalYear", con.getFiscalYear());
//			    		yearAndMonth.put("date", dateString);
			    		pfBaseArray.put(yearAndMonth);
		
				}

//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
//    	monthlyHigherPension.put(pfBaseArray);
    	return pfBaseArray;
    }
    
    @Override
    public JSONArray getMonthlyCorpusAccumulation(String pfNumber, String fiscalYear, String first_contribution_year) {
    	
    	JSONArray monthlyHigherPension = new JSONArray();
    	
    	List<JSONObject> YearlyClosingBalance = new ArrayList<>();
    	
    	JSONArray pfBaseArray = new JSONArray();
    	
    	int counter = 0;
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
		Double pfBaseTotal = 0.00;
		Double actualPfBaseTotal = 0.00;
		Double differenceForHigherPensionTotal = 0.00;
		Double averageInterest = 0.00;
		Double interestDiffTotal = 0.00;
		Double totalAmountTotal = 0.00;
		double corpusTotal = 0;
		double corpusGrandTotal = 0;
		double corpusInterestTotal = 0;
		double admin_interest_value_total = 0;
    	List<PfStartYearMonth> listOfYears = getFiscalYearBetween(pfNumber, first_contribution_year, fiscalYear);
    	
    	for(PfStartYearMonth year: listOfYears) {
    		
    	
	    	Optional<PfUserInterestRates> interestRate = pfUserRepository.getInterestRate(year.getFiscalYear());
	    	
	    	System.out.println("breakDown "+interestRate.get().getFiscalYear()+" , "+interestRate.get().getIsBreakDown());
	    	System.out.println("c "+year.getFiscalYear());
	    	List<PfMonthlyContribution> pfMonthlyContribution = getMonthlyContribution(pfNumber, year.getFiscalYear());
	    	System.out.println("fetched fiscal year "+year.getFiscalYear());
	    	//12 considered sicnce year consists of 12 months
//	    	int monthNumber = 12-1;
	    	int monthNumber = pfMonthlyContribution.size() > 12? 11:pfMonthlyContribution.size()-1;
	    	
	    	// 1 for counting month
	    	int month = 0;
	    	
	    	if(!interestRate.isEmpty()) {
	    		int breakCount = 0;
				for(PfMonthlyContribution con: pfMonthlyContribution) {

						month++;
						
						JSONObject highpension = new JSONObject();
			    		
			    		highpension.put("monthNumber", monthNumber);
			    		
			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
			    		double openingBalance = 0.00;
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
//					    double pfBaseInterest = 12.00;
					    double pfBaseInterest = 0;
					    if (year.getFiscalYear().equals("FY1994-1995") || year.getFiscalYear().equals("FY1995-1996")
					    		|| (year.getFiscalYear().equals("FY1996-1997") && con.getSubType() < 7 )) {
					    	pfBaseInterest = 10;
					    }else {
					    	pfBaseInterest = 12;
					    }
					    System.out.println("pfabse int calc "+pfBaseInterest +","+con.getSubType());
					    
					    
					    double actualPfBseInterest = 8.33;
			    				
					    double eps = memberContribution-companyContribution;
	
//			    		epsTotal += eps;

			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
			    		JSONObject yearAndMonth = new JSONObject();
			    		yearAndMonth.put("pfBase", pfBase);
			    		yearAndMonth.put("subType",con.getSubType());
			    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		 
			    		  if (resultMonth > 12) {
			    		    resultMonth = 1+ (resultMonth - 12);
			    		    mon = Integer.toString(resultMonth);
			    		    resultYear = con.getActualYear();
			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		  
			    		yearAndMonth.put("actualYear", con.getActualYear());
			    		yearAndMonth.put("amount", getSlabAmount(resultYear+"-"+mon+"-01"));
			    		pfBaseArray.put(yearAndMonth);
			    		
			    		String admin = pfUserRepository.getAdminInterest(resultYear+"-"+mon+"-01");
			    		
			    		System.out.println(resultYear+"-"+mon+"-01");
			    		double admin_interest = new Double(admin == null? "0":admin);
			    		
			    		System.out.println("admin interest "+admin_interest);
			    		
			    		System.out.println("pf-Base = "+pfBase);
			    		double admin_interest_value = Math.round(((pfBase-15000)/100)*admin_interest);
			    		if(admin_interest_value < 0) {
			    			admin_interest_value = 0;
			    		}
			    		System.out.println("admin interes Value "+admin_interest_value);
			    		admin_interest_value_total += admin_interest_value;
			    		
			    		pfBaseTotal += pfBase;
			    		
			    		String slab1 =  pfUserRepository.amount(resultYear+"-"+mon+"-01");
			    		if(slab1 == null || slab1.equals("")) {
			    			mon =  String.valueOf(Integer.parseInt(mon)+1);
			    			slab1 =  pfUserRepository.amount(resultYear+"-"+(mon)+"-01");
			    		}
			    		
			    		double slab  = new Double(slab1);
			    		System.out.println("slab amount "+slab);
			    		double slabeps = 0;
			    		if(eps != 0 && (slab  == 5000 && slab < pfBase)) {
			    			eps = 417;
			    		}else if( eps != 0 && (slab  == 5000 && slab > pfBase)) {
			    			eps = 417;
			    		}else if (eps != 0 && (slab == 6500 && slab < pfBase)) {
			    			eps = 541;
			    		}else if (eps != 0 && (slab == 6500 && slab > pfBase)) {
			    			eps = 541;
			    		}
			    		else if(eps != 0){
			    			eps = 1250;
			    		}
			    		
			    		
			    		double actualPfBase = Math.round((pfBase/100)*actualPfBseInterest);
			   
			    		double restrictForEps =0;
			    		if (actualPfBase <= eps) {
			    			restrictForEps = actualPfBase;
			    		}else {
			    			restrictForEps = eps;
			    		}
			    		
			    		if(actualPfBase < eps) {
			    			eps =actualPfBase;
			    		}
			    		if(eps == 0) {
			    			actualPfBase = eps;
			    		}
			    		
			    		actualPfBaseTotal += actualPfBase;
			    		
			    		epsTotal += eps;
			    		
			    		double differenceForHigherPension = actualPfBase-eps;
	
			    		differenceForHigherPensionTotal += differenceForHigherPension;
			    		
			    		double interest = 0.00;
			    		double corpusInterest = 0;
			    		double corpusForTheMonth = 0;
			    		double corpus = 0;
			    		double a = 0;
			    		if(interestRate.get().getIsBreakDown() != 1) {
			    			a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    			interest = interestRate.get().getInterestRate1();
			    			
			    			System.out.println("pf-Base = "+pfBase);
			    			corpus = memberContribution + companyContribution + eps;
			    			System.out.println("corpus = "+corpus);
				    		corpusInterest = ((corpus*interestRate.get().getInterestRate1())/100)/12*monthNumber;
				    		System.out.println("corpusInterest = "+corpusInterest);
				    		corpusInterestTotal += corpusInterest;
				    		corpusForTheMonth = corpus + corpusInterest;
				    		System.out.println("corpusForTheMonth = "+corpusForTheMonth);
				    		corpusTotal += corpus;
				    		
			    		}else if(interestRate.get().getIsBreakDown() == 1) {
			    			if(breakCount < interestRate.get().getFirstBreak()) {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    				interest = interestRate.get().getInterestRate1();
			    				
			    				System.out.println("pf-Base = "+pfBase);
			    				corpus = memberContribution + companyContribution + eps;
			    				System.out.println("corpus = "+corpus);
					    		corpusInterest = ((corpus*interestRate.get().getInterestRate1())/100)/12*monthNumber;
					    		System.out.println("corpusInterest = "+corpusInterest);
					    		corpusInterestTotal += corpusInterest;
					    		corpusForTheMonth = corpus + corpusInterest;
					    		System.out.println("corpusForTheMonth = "+corpusForTheMonth);
					    		corpusTotal += corpus;
			    			}else {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate2();
			    				interest = interestRate.get().getInterestRate2();
			    				
			    				System.out.println("pf-Base = "+pfBase);
			    				corpus = memberContribution + companyContribution + eps;
			    				System.out.println("corpus = "+corpus);
					    		corpusInterest = ((corpus*interestRate.get().getInterestRate2())/100)/12*monthNumber;
					    		System.out.println("corpusInterest = "+corpusInterest);
					    		corpusInterestTotal += corpusInterest;
					    		corpusForTheMonth = corpus + corpusInterest;
					    		System.out.println("corpusForTheMonth = "+corpusForTheMonth);
					    		corpusTotal += corpus;
			    			}
			    		}
			    		// adding each month corpus to its grand corpus which will be added to every year closing
			    		corpusGrandTotal += corpusForTheMonth;
			    		
			    		double b = a/12;

			    		double interestOnDifferential = 0.00;
	
			    		if(monthNumber != 0) {
			    			interestOnDifferential =Math.round(b*monthNumber);
			    		}

			    		interestDiffTotal += interestOnDifferential;
			    		
			    		double totalAmount = Math.round(differenceForHigherPension+interestOnDifferential);
	
			    		totalAmountTotal += totalAmount;
					    
			    		if(listOfYears.get(0).getFiscalYear() ==  year.getFiscalYear() && month == 1) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", 12);
			    			highpension1.put("employeeControbution", "Opening Balance");
				    		highpension1.put("companyContribution", "");
				    		highpension1.put("eps", "");
				    		highpension1.put("corpus", Math.round(pfBase));
				    		highpension1.put("interestRate", interest+"%");
				    		highpension.put("corpusPercentage", "");
				    		highpension.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("corpusForTheMonth", 0);
				    		highpension1.put("totalAmount",Math.round(corpusGrandTotal));
				    		totalAmountTotal += openingBalance;
				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
				    		}
			    		}
			    		if(listOfYears.get(0).getFiscalYear() !=  year.getFiscalYear() && month == 1) {
			  
			    				Double objectTotalAmount = YearlyClosingBalance.get(counter-1).getDouble("totalAmount");
//			    				System.out.println("totalAmount : "+objectTotalAmount);
			    				Double objectInterestRate =  interest;
			    				
			    				double corpusForTheYear = YearlyClosingBalance.get(counter-1).getDouble("corpusForTheMonth");
			    				
			    				double corpusForTheYearInterest = Math.round((corpusForTheYear/100)*interest);
			    				
				    			System.out.println(objectTotalAmount+" , "+objectInterestRate);
				    			int div = 12;
				    			openingBalance = (((objectTotalAmount/100)*objectInterestRate)/div)*12;
				    			
				    			JSONObject highpension1 =  new JSONObject();
				    			highpension1.put("monthNumber", 12);
				    			highpension1.put("employeeControbution", "Opening Balance");
					    		highpension1.put("companyContribution", "");
					    		highpension1.put("eps", "");
					    		highpension1.put("corpus", "");
					    		highpension1.put("interestRate", interest+"%");
					    		highpension.put("corpusPercentage", "");
					    		highpension.put("fisccal_year", year.getFiscalYear());
					    		highpension1.put("corpusForTheMonth",Math.round(corpusForTheYear + corpusForTheYearInterest));
					    		highpension1.put("totalAmount", Math.round(Math.round(objectTotalAmount)+Math.round(openingBalance)));
//					    		totalAmountTotal = totalAmountTotal + objectTotalAmount + openingBalance;
					    		totalAmountTotal = totalAmountTotal + openingBalance;
					    		corpusGrandTotal = Math.round(corpusGrandTotal + corpusForTheYearInterest);
					    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
					    			monthlyHigherPension.put(highpension1);
					    		}
			    		}
			    		
			    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
			    			
			    			highpension.put("employeeControbution", memberContribution);
							highpension.put("companyContribution", companyContribution);
							highpension.put("eps", Math.round(eps));
							highpension.put("corpus", Math.round(corpus));
							highpension.put("interestRate", interest+"%");
							highpension.put("corpusPercentage", Math.round(corpusInterest));
							highpension.put("fisccal_year", year.getFiscalYear());
							highpension.put("corpusForTheMonth",Math.round(corpusForTheMonth));
							highpension.put("totalAmount", Math.round(totalAmount));				    		
				    		monthlyHigherPension.put(highpension);
			    		}
						
	
			    		monthNumber = monthNumber-1;

			    		if(month == 12) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "Total");
			    			highpension1.put("employeeControbution", Math.round(employeeTotal));
				    		highpension1.put("companyContribution", Math.round(employerTotal));
				    		highpension1.put("eps", Math.round(epsTotal));
				    		highpension1.put("corpus", Math.round(corpus));
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("corpusPercentage", Math.round(corpusInterestTotal));
				    		highpension.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("corpusForTheMonth",Math.round(corpusGrandTotal));
				    		highpension1.put("totalAmount",Math.round(totalAmountTotal));
				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
				    		}
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		} 
			    		if(pfMonthlyContribution.size() < 12 && pfMonthlyContribution.size() == month) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "Total");
			    			highpension1.put("employeeControbution",Math.round(employeeTotal));
				    		highpension1.put("companyContribution", Math.round(employerTotal));
				    		highpension1.put("eps", Math.round(epsTotal));
				    		highpension1.put("corpus", Math.round(corpus));
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("corpusPercentage", Math.round(corpusInterestTotal));
				    		highpension.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("corpusForTheMonth",Math.round(corpusGrandTotal));
				    		highpension1.put("totalAmount", Math.round(totalAmountTotal));
				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
				    		}
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		}
			    		System.out.println("corpus Grand for year "+ Math.round(corpusGrandTotal));
//			    	}
			    		breakCount += 1;
				}
			}
	    	counter += 1;
    	}
    	System.out.println("corpus Total : "+corpusTotal);
//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
//    	monthlyHigherPension.put(pfBaseArray);
    	return monthlyHigherPension;
    }
    
    @Override
    public JSONArray getCorpusSummary(String pfNumber, String fiscalYear, String first_contribution_year) {
    	
    	JSONArray monthlyHigherPension = new JSONArray();
    	
    	List<JSONObject> YearlyClosingBalance = new ArrayList<>();
    	
    	JSONArray pfBaseArray = new JSONArray();
    	
    	int counter = 0;
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
		Double pfBaseTotal = 0.00;
		Double actualPfBaseTotal = 0.00;
		Double differenceForHigherPensionTotal = 0.00;
		Double averageInterest = 0.00;
		Double interestDiffTotal = 0.00;
		Double totalAmountTotal = 0.00;
		double corpusTotal = 0;
		double corpusGrandTotal = 0;
		double corpusInterestTotal = 0;
		double admin_interest_value_total = 0;
    	List<PfStartYearMonth> listOfYears = getFiscalYearBetween(pfNumber, first_contribution_year, fiscalYear);
    	
    	for(PfStartYearMonth year: listOfYears) {
    		
    	
	    	Optional<PfUserInterestRates> interestRate = pfUserRepository.getInterestRate(year.getFiscalYear());
	    	
	    	System.out.println("breakDown "+interestRate.get().getFiscalYear()+" , "+interestRate.get().getIsBreakDown());
	    	System.out.println("c "+year.getFiscalYear());
	    	List<PfMonthlyContribution> pfMonthlyContribution = getMonthlyContribution(pfNumber, year.getFiscalYear());
	    	System.out.println("fetched fiscal year "+year.getFiscalYear());
	    	//12 considered sicnce year consists of 12 months
//	    	int monthNumber = 12-1;
	    	int monthNumber = pfMonthlyContribution.size() > 12? 11:pfMonthlyContribution.size()-1;
	    	
	    	// 1 for counting month
	    	int month = 0;
	    	
	    	if(!interestRate.isEmpty()) {
	    		int breakCount = 0;
				for(PfMonthlyContribution con: pfMonthlyContribution) {

						month++;
						
						JSONObject highpension = new JSONObject();
			    		
			    		highpension.put("monthNumber", monthNumber);
			    		
			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
			    		double openingBalance = 0.00;
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
//					    double pfBaseInterest = 12.00;
					    double pfBaseInterest = 0;
					    if (year.getFiscalYear().equals("FY1994-1995") || year.getFiscalYear().equals("FY1995-1996")
					    		|| (year.getFiscalYear().equals("FY1996-1997") && con.getSubType() < 7 )) {
					    	pfBaseInterest = 10;
					    }else {
					    	pfBaseInterest = 12;
					    }
					    System.out.println("pfabse int calc "+pfBaseInterest +","+con.getSubType());
					    
					    
					    double actualPfBseInterest = 8.33;
			    				
					    double eps = memberContribution-companyContribution;
	
//			    		epsTotal += eps;

			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
			    		JSONObject yearAndMonth = new JSONObject();
			    		yearAndMonth.put("pfBase", pfBase);
			    		yearAndMonth.put("subType",con.getSubType());
			    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		 
			    		  if (resultMonth > 12) {
			    		    resultMonth = 1+ (resultMonth - 12);
			    		    mon = Integer.toString(resultMonth);
			    		    resultYear = con.getActualYear();
			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		  
			    		yearAndMonth.put("actualYear", con.getActualYear());
			    		yearAndMonth.put("amount", getSlabAmount(resultYear+"-"+mon+"-01"));
			    		pfBaseArray.put(yearAndMonth);
			    		
			    		String admin = pfUserRepository.getAdminInterest(resultYear+"-"+mon+"-01");
			    		
			    		System.out.println(resultYear+"-"+mon+"-01");
			    		double admin_interest = new Double(admin == null? "0":admin);
			    		
			    		System.out.println("admin interest "+admin_interest);
			    		
			    		System.out.println("pf-Base = "+pfBase);
			    		double admin_interest_value = Math.round(((pfBase-15000)/100)*admin_interest);
			    		if(admin_interest_value < 0) {
			    			admin_interest_value = 0;
			    		}
			    		System.out.println("admin interes Value "+admin_interest_value);
			    		admin_interest_value_total += admin_interest_value;
			    		
			    		pfBaseTotal += pfBase;
			    		
			    		String slab1 =  pfUserRepository.amount(resultYear+"-"+mon+"-01");
			    		if(slab1 == null || slab1.equals("")) {
			    			mon =  String.valueOf(Integer.parseInt(mon)+1);
			    			slab1 =  pfUserRepository.amount(resultYear+"-"+(mon)+"-01");
			    		}
			    		
			    		double slab  = new Double(slab1);
			    		System.out.println("slab amount "+slab);
			    		double slabeps = 0;
			    		if(eps != 0 && (slab  == 5000 && slab < pfBase)) {
			    			eps = 417;
			    		}else if(eps != 0 && (slab  == 5000 && slab > pfBase)) {
			    			eps = 417;
			    		}else if (eps != 0 && (slab == 6500 && slab < pfBase)) {
			    			eps = 541;
			    		}else if (eps != 0 && (slab == 6500 && slab > pfBase)) {
			    			eps = 541;
			    		}
			    		else if(eps != 0){
			    			eps = 1250;
			    		}
			    		
			    		
			    		double actualPfBase = Math.round((pfBase/100)*actualPfBseInterest);
			   
			    		double restrictForEps =0;
			    		if (actualPfBase <= eps) {
			    			restrictForEps = actualPfBase;
			    		}else {
			    			restrictForEps = eps;
			    		}
			    		
			    		if(actualPfBase < eps) {
			    			eps =actualPfBase;
			    		}
			    		if(eps == 0) {
			    			actualPfBase = eps;
			    		}
			    		
			    		actualPfBaseTotal += actualPfBase;
			    		
			    		epsTotal += eps;
			    		
			    		double differenceForHigherPension = actualPfBase-eps;
	
			    		differenceForHigherPensionTotal += differenceForHigherPension;
			    		
			    		double interest = 0.00;
			    		double corpusInterest = 0;
			    		double corpusForTheMonth = 0;
			    		double corpus = 0;
			    		double a = 0;
			    		if(interestRate.get().getIsBreakDown() != 1) {
			    			a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    			interest = interestRate.get().getInterestRate1();
			    			
			    			System.out.println("pf-Base = "+pfBase);
			    			corpus = memberContribution + companyContribution + eps;
			    			System.out.println("corpus = "+corpus);
				    		corpusInterest = ((corpus*interestRate.get().getInterestRate1())/100)/12*monthNumber;
				    		System.out.println("corpusInterest = "+corpusInterest);
				    		corpusInterestTotal += corpusInterest;
				    		corpusForTheMonth = corpus + corpusInterest;
				    		System.out.println("corpusForTheMonth = "+corpusForTheMonth);
				    		corpusTotal += corpus;
				    		
			    		}else if(interestRate.get().getIsBreakDown() == 1) {
			    			if(breakCount < interestRate.get().getFirstBreak()) {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    				interest = interestRate.get().getInterestRate1();
			    				
			    				System.out.println("pf-Base = "+pfBase);
			    				corpus = memberContribution + companyContribution + eps;
			    				System.out.println("corpus = "+corpus);
					    		corpusInterest = ((corpus*interestRate.get().getInterestRate1())/100)/12*monthNumber;
					    		System.out.println("corpusInterest = "+corpusInterest);
					    		corpusInterestTotal += corpusInterest;
					    		corpusForTheMonth = corpus + corpusInterest;
					    		System.out.println("corpusForTheMonth = "+corpusForTheMonth);
					    		corpusTotal += corpus;
			    			}else {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate2();
			    				interest = interestRate.get().getInterestRate2();
			    				
			    				System.out.println("pf-Base = "+pfBase);
			    				corpus = memberContribution + companyContribution + eps;
			    				System.out.println("corpus = "+corpus);
					    		corpusInterest = ((corpus*interestRate.get().getInterestRate2())/100)/12*monthNumber;
					    		System.out.println("corpusInterest = "+corpusInterest);
					    		corpusInterestTotal += corpusInterest;
					    		corpusForTheMonth = corpus + corpusInterest;
					    		System.out.println("corpusForTheMonth = "+corpusForTheMonth);
					    		corpusTotal += corpus;
			    			}
			    		}
			    		// adding each month corpus to its grand corpus which will be added to every year closing
			    		corpusGrandTotal += corpusForTheMonth;
			    		
			    		double b = a/12;

			    		double interestOnDifferential = 0.00;
	
			    		if(monthNumber != 0) {
			    			interestOnDifferential =Math.round(b*monthNumber);
			    		}

			    		interestDiffTotal += interestOnDifferential;
			    		
			    		double totalAmount = Math.round(differenceForHigherPension+interestOnDifferential);
	
			    		totalAmountTotal += totalAmount;
					    
			    		if(listOfYears.get(0).getFiscalYear() ==  year.getFiscalYear() && month == 1) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", 12);
			    			highpension1.put("employeeControbution", "Opening Balance");
				    		highpension1.put("companyContribution", "");
				    		highpension1.put("eps", "");
				    		highpension1.put("corpus", 0);
				    		highpension1.put("interestRate", interest+"%");
				    		highpension1.put("corpusPercentage", "");
				    		highpension1.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("corpusForTheMonth", 0);
				    		highpension1.put("totalAmount",Math.round(corpusGrandTotal));
				    		totalAmountTotal += openingBalance;
//				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
//				    		}
			    		}
			    		if(listOfYears.get(0).getFiscalYear() !=  year.getFiscalYear() && month == 1) {
			  
			    				Double objectTotalAmount = YearlyClosingBalance.get(counter-1).getDouble("totalAmount");
//			    				System.out.println("totalAmount : "+objectTotalAmount);
			    				Double objectInterestRate =  interest;
			    				
			    				double corpusForTheYear = YearlyClosingBalance.get(counter-1).getDouble("corpusForTheMonth");
			    				
			    				double corpusForTheYearInterest = Math.round((corpusForTheYear/100)*interest);
			    				
				    			System.out.println(objectTotalAmount+" , "+objectInterestRate);
				    			int div = 12;
				    			openingBalance = (((objectTotalAmount/100)*objectInterestRate)/div)*12;
				    			
				    			JSONObject highpension1 =  new JSONObject();
				    			highpension1.put("monthNumber", 12);
				    			highpension1.put("employeeControbution", "Opening Balance");
					    		highpension1.put("companyContribution", "");
					    		highpension1.put("eps", "");
					    		highpension1.put("corpus", "");
					    		highpension1.put("interestRate", interest+"%");
					    		highpension1.put("corpusPercentage", "");
					    		highpension1.put("fisccal_year", year.getFiscalYear());
					    		highpension1.put("corpusForTheMonth",Math.round(corpusForTheYear + corpusForTheYearInterest));
					    		highpension1.put("totalAmount", Math.round(Math.round(objectTotalAmount)+Math.round(openingBalance)));
//					    		totalAmountTotal = totalAmountTotal + objectTotalAmount + openingBalance;
					    		totalAmountTotal = totalAmountTotal + openingBalance;
					    		corpusGrandTotal = Math.round(corpusGrandTotal + corpusForTheYearInterest);
//					    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
					    			monthlyHigherPension.put(highpension1);
//					    		}
			    		}
			    		
//			    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
			    			
//			    			highpension.put("employeeControbution", memberContribution);
//							highpension.put("companyContribution", companyContribution);
//							highpension.put("eps", Math.round(eps));
//							highpension.put("corpus", Math.round(corpus));
//							highpension.put("interestRate", interest+"%");
//							highpension.put("corpusPercentage", Math.round(corpusInterest));
//							highpension.put("fisccal_year", year.getFiscalYear());
//							highpension.put("corpusForTheMonth",Math.round(corpusForTheMonth));
//							highpension.put("totalAmount", Math.round(totalAmount));				    		
//				    		monthlyHigherPension.put(highpension);
//			    		}
						
	
			    		monthNumber = monthNumber-1;

			    		if(month == 12) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "Total");
			    			highpension1.put("employeeControbution", Math.round(employeeTotal));
				    		highpension1.put("companyContribution", Math.round(employerTotal));
				    		highpension1.put("eps", Math.round(epsTotal));
				    		highpension1.put("corpus", Math.round(corpusTotal));
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("corpusPercentage", Math.round(corpusInterestTotal));
				    		highpension1.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("corpusForTheMonth",Math.round(corpusGrandTotal));
				    		highpension1.put("totalAmount",Math.round(totalAmountTotal));
//				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
//				    		}
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		} 
			    		if(pfMonthlyContribution.size() < 12 && pfMonthlyContribution.size() == month) {
			    			JSONObject highpension1 =  new JSONObject();
			    			highpension1.put("monthNumber", "Total");
			    			highpension1.put("employeeControbution",Math.round(employeeTotal));
				    		highpension1.put("companyContribution", Math.round(employerTotal));
				    		highpension1.put("eps", Math.round(epsTotal));
				    		highpension1.put("corpus", Math.round(corpusTotal));
				    		highpension1.put("interestRate", interest);
				    		highpension1.put("corpusPercentage", Math.round(corpusInterestTotal));
				    		highpension1.put("fisccal_year", year.getFiscalYear());
				    		highpension1.put("corpusForTheMonth",Math.round(corpusGrandTotal));
				    		highpension1.put("totalAmount", Math.round(totalAmountTotal));
//				    		if(year.getFiscalYear() == listOfYears.get(listOfYears.size()-1).getFiscalYear()) {
				    			monthlyHigherPension.put(highpension1);
//				    		}
				    		YearlyClosingBalance.add(highpension1);
				    		break;
			    		}
			    		System.out.println("corpus Grand for year "+ Math.round(corpusGrandTotal));
//			    	}
			    		breakCount += 1;
				}
			}
	    	counter += 1;
    	}
    	System.out.println("corpus Total : "+corpusTotal);
//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
//    	monthlyHigherPension.put(pfBaseArray);
    	return monthlyHigherPension;
    }
    
    @Override
    public JSONArray getMonthlyHigherPensionForMWDSheet(String pfNumber, String fiscalYear, String first_contribution_year) {

    	JSONArray monthlyHigherPension = new JSONArray();
    	
    	String memberId = pfUserRepository.getMemberId(pfNumber);
//    	List<JSONObject> YearlyClosingBalance = new ArrayList<>();

//    	int counter = 0;
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
		Double pfBaseTotal = 0.00;
		Double actualPfBaseTotal = 0.00;
		Double differenceForHigherPensionTotal = 0.00;
		Double interestDiffTotal = 0.00;
		Double totalAmountTotal = 0.00;
//		double admin_interest_value_total = 0;

		
		System.out.println(pfNumber+","+first_contribution_year+","+fiscalYear);
		List<PfStartYearMonth> listOfYears = getFiscalYearBetween(pfNumber,fiscalYear,first_contribution_year );
    	
    	System.out.println(listOfYears.size());
    	for(PfStartYearMonth year: listOfYears) {
    		
    	System.out.println("im called "+year.getFiscalYear());
	    	Optional<PfUserInterestRates> interestRate = pfUserRepository.getInterestRate(year.getFiscalYear());
	    	
	    	System.out.println("breakDown "+interestRate.get().getFiscalYear()+" , "+interestRate.get().getIsBreakDown());
	    	
	    	List<PfMonthlyContribution> pfMonthlyContribution = getMonthlyContribution(pfNumber, year.getFiscalYear());
	    
	    	//12 considered sicnce year consists of 12 months
	    	int monthNumber = 12-1;
	    	
	    	// 1 for counting month
	    	int month = 0;
	    	
	    	if(!interestRate.isEmpty()) {
	    		int breakCount = 0;
				for(PfMonthlyContribution con: pfMonthlyContribution) {

						month++;
						
						JSONObject highpension = new JSONObject();
			    		
			    		highpension.put("monthNumber", monthNumber);
			    		
			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
//			    		double openingBalance = 0.00;
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
//					    double pfBaseInterest = 12.00;
					    double pfBaseInterest = 0;
					    if (year.getFiscalYear().equals("FY1994-1995") || year.getFiscalYear().equals("FY1995-1996")
					    		|| (year.getFiscalYear().equals("FY1996-1997") && con.getSubType() < 7 )) {
					    	pfBaseInterest = 10;
					    }else {
					    	pfBaseInterest = 12;
					    }
					    System.out.println("pfabse int calc "+pfBaseInterest +","+con.getSubType());
					    
					    double actualPfBseInterest = 8.33;
			    				
					    double eps = memberContribution-companyContribution;
	
//			    		epsTotal += eps;
			    		
			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
//			    		JSONObject yearAndMonth = new JSONObject();
//			    		yearAndMonth.put("pfBase", pfBase);
//			    		yearAndMonth.put("subType",con.getSubType());
			    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		  if(resultMonth == 13) {
			    			  mon = "01";
			    			  resultYear = con.getActualYear();
			    		  }
			    		  if(resultMonth == 14) {
			    			  mon = "02";
			    			  resultYear = con.getActualYear();
			    		  }
			    		  if(resultMonth == 15) {
			    			  mon = "03";
			    			  resultYear = con.getActualYear();
			    		  }
			    		 
//			    		  if (resultMonth > 12) {
////			    		    resultMonth = 1+ (resultMonth - 12);
//			    			  resultMonth = resultMonth - 12;
//			    		    mon = Integer.toString(resultMonth);
//			    		    resultYear = con.getActualYear();
//			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		  
			    		 String date = mon+"/"+resultYear;

			    		pfBaseTotal += pfBase;
			    		
			    		String slab1 =  pfUserRepository.amount(resultYear+"-"+mon+"-01");
			    		if(slab1 == null || slab1.equals("")) {
			    			mon =  String.valueOf(Integer.parseInt(mon)+1);
			    			slab1 =  pfUserRepository.amount(resultYear+"-"+(mon)+"-01");
			    		}
			    		
			    		double slab  = new Double(slab1);
			    		System.out.println("slab amount "+slab);
			    		double slabeps = 0;
			    		if(eps != 0 && (slab  == 5000 && slab < pfBase)) {
			    			eps = 417;
			    		}else if(eps != 0 && (slab  == 5000 && slab > pfBase)) {
			    			eps = 417;
			    		}else if (eps != 0 && (slab == 6500 && slab < pfBase)) {
			    			eps = 541;
			    		}else if (eps != 0 && (slab == 6500 && slab > pfBase)) {
			    			eps = 541;
			    		}
			    		else if(eps != 0) {
			    			eps = 1250;
			    		}
			    		
			    		//Admin Interest Calculation
			    		String admin = pfUserRepository.getAdminInterest(resultYear+"-"+mon+"-01");
			    		System.out.println("admin me "+admin);
			    		System.out.println(resultYear+"-"+mon+"-01");
			    		double admin_interest = new Double(admin == null? "0":admin);
			    		
			    		System.out.println("admin interest "+admin_interest);
			    		
			    		System.out.println("pf-Base = "+pfBase);
			    		double admin_interest_value = Math.round(((pfBase-15000)/100)*admin_interest);
			    		if(admin_interest_value < 0) {
			    			admin_interest_value = 0;
			    		}
			    		System.out.println("admin interes Value "+admin_interest_value);
//			    		admin_interest_value_total += admin_interest_value;
			    		
			    		
			    		double actualPfBase = Math.round((pfBase/100)*actualPfBseInterest);
			   
			    		double restrictForEps =0;
			    		if (actualPfBase <= eps) {
			    			restrictForEps = actualPfBase;
			    		}else {
			    			restrictForEps = eps;
			    		}
			    		
			    		if(actualPfBase < eps) {
			    			eps =actualPfBase;
			    		}
			    		if(eps == 0) {
			    			actualPfBase = 0 ;
			    		}
			    		
			    		actualPfBaseTotal += actualPfBase;
			    		
			    		epsTotal += eps;
			    		
//			    		double restrictForEps =0;
//			    		if (actualPfBase <= eps) {
//			    			restrictForEps = actualPfBase;
//			    			eps = actualPfBase;
//			    		}else {
//			    			restrictForEps = eps;
//			    		}
			    		
			    		double differenceForHigherPension = actualPfBase-eps;
	
			    		differenceForHigherPensionTotal += differenceForHigherPension;
			    		
			    		double interest = 0.00;
			    		double a = 0;
			    		if(interestRate.get().getIsBreakDown() != 1) {
			    			a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    			interest = interestRate.get().getInterestRate1();
			    		}else if(interestRate.get().getIsBreakDown() == 1) {
			    			if(breakCount < interestRate.get().getFirstBreak()) {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    				interest = interestRate.get().getInterestRate1();
			    			}else {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate2();
			    				interest = interestRate.get().getInterestRate2();
			    			}
			    		}
	
			    		double b = a/12;

			    		double interestOnDifferential = 0.00;
	
			    		if(monthNumber != 0) {
			    			interestOnDifferential =Math.round(b*monthNumber);
			    		}

			    		interestDiffTotal += interestOnDifferential;
			    		
			    		double totalAmount = Math.round(differenceForHigherPension+interestOnDifferential);
	
			    		totalAmountTotal += totalAmount;
			    		NumberFormat formatter = new DecimalFormat("#0.0");
			    		
//			    		System.out.println(formatter.format(4.0));
			    			highpension.put("memberId", memberId);
			    			highpension.put("yearAndMonth", date);
//			    			highpension.put("fisccal_year", year.getFiscalYear());
			    			highpension.put("pfBase",String.valueOf(Math.round(pfBase)));
//			    			highpension.put("employeeControbution", memberContribution);	
							highpension.put("companyContribution", String.valueOf(Math.round(actualPfBase)));
//							highpension.put("vpfContribution", vpfContribution);
							highpension.put("admin_interest", String.valueOf(admin_interest_value));
							highpension.put("eps", String.valueOf(Math.round(eps)));
							highpension.put("totalAmount", String.valueOf(Math.round(differenceForHigherPension+interestOnDifferential)));
							highpension.put("interestRate", String.valueOf(formatter.format(interest)));
//							highpension.put("actualPfBase", Math.round(actualPfBase));
//							highpension.put("differenceForHigherPension", Math.round(differenceForHigherPension));
//							
//							highpension.put("interestOnDifferential", Math.round(interestOnDifferential));
							
							
							monthlyHigherPension.put(highpension);
							if(month == 12) {
								break;
							}
			    		monthNumber = monthNumber-1;
//			    	}
			    		breakCount += 1;
				}
			}
//	    	counter += 1;
    	}
//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
//    	monthlyHigherPension.put(pfBaseArray);
    	return monthlyHigherPension;
    }
    
    @Override
    public List<String> getMWDSheetLines(JSONArray listOfContribution, String pfNumber) {

        DecimalFormat df = new DecimalFormat("####0.00");
        
        String mid = pfUserRepository.getMemberId(pfNumber);
        
        List<String> textMWDFileLine = new ArrayList<>();
        
        if(!(mid.equals(null) || mid.equals(""))){
        	
        	for(int i = 0; i<listOfContribution.length();i++) {
        		
//              String modeCode = settlement.getIfscCode() == null ? "" : settlement.getIfscCode().substring(0, 4).equalsIgnoreCase("KKBK") ? "IFT" : "NEFT";
        		// Need to add member id, from the database
	          	JSONObject obj = listOfContribution.getJSONObject(i);

//	          	String[] date = obj.getString("yearAndMonth").split("/");
//	          	int month = Integer.valueOf(date[0]);
//	          	int year = Integer.valueOf(date[0]);
	          	
	          	String pfBase = df.format(obj.getDouble("pfBase"));
	          	String compContubition = df.format(obj.getDouble("companyContribution"));
	          	String adminInterest = df.format(obj.getDouble("admin_interest"));
	          	String eps = df.format(obj.getDouble("eps"));
	          	String totalAmount = df.format(obj.getDouble("totalAmount"));
	          	String interestRate = df.format(obj.getDouble("interestRate"));
	          	
	//              String MWDFileLine = "MMLSPFSALPAY0000018580" + "#~#" + obj.getString("yearAndMonth") + "#~#" +
	//              		obj.getDouble("pfBase") + "#~#" + obj.getDouble("companyContribution") + "#~#" +
	//              		obj.getDouble("admin_interest") + "#~#" + obj.getDouble("eps") + "#~#" + obj.getDouble("totalAmount") + "#~#" +
	//              		obj.getString("interestRate")+"\n";
	          	
//	          	String MWDFileLine = mid + "#~#" + obj.getString("yearAndMonth") + "#~#" +
//	          			pfBase + "#~#" + compContubition + "#~#" + adminInterest + "#~#" + eps + "#~#" + totalAmount + "#~#" +
//	          			interestRate+"\n";
//
//              textMWDFileLine.add(MWDFileLine);
	          	
	          	String[] date = obj.getString("yearAndMonth").split("/");
	          	int month = Integer.valueOf(date[0]);
	          	int year = Integer.valueOf(date[1]);
	          	if(year == 1995) {
	          		if(month > 10) {
	          			String MWDFileLine = mid + "#~#" + obj.getString("yearAndMonth") + "#~#" +
			          			pfBase + "#~#" + compContubition + "#~#" + adminInterest + "#~#" + eps + "#~#" + totalAmount + "#~#" +
			          			interestRate+"\n";

		              textMWDFileLine.add(MWDFileLine);
	          		}
//	          		else if (month < 4){
//	          			
//	          		}
	          	}else if(year > 1995) {
	          		String MWDFileLine = mid + "#~#" + obj.getString("yearAndMonth") + "#~#" +
		          			pfBase + "#~#" + compContubition + "#~#" + adminInterest + "#~#" + eps + "#~#" + totalAmount + "#~#" +
		          			interestRate+"\n";

	              textMWDFileLine.add(MWDFileLine);
	          	}

          };
        }

        return textMWDFileLine;
    }
    
    @Override
    public List<String> getMWDSheetLines(JSONArray listOfContribution) {

        DecimalFormat df = new DecimalFormat("####0.00");
        
//        String mid = pfUserRepository.getMemberId(pfNumber);
        
        List<String> textMWDFileLine = new ArrayList<>();
        
        	for(int i = 0; i<listOfContribution.length();i++) {
        		
	          	JSONObject obj = listOfContribution.getJSONObject(i);

	          	String mid = obj.getString("memberId");
	          	String yearAndMonth = obj.getString("yearAndMonth");
	          	String pfBase = df.format(obj.getDouble("pfBase"));
	          	String compContubition = df.format(obj.getDouble("companyContribution"));
	          	String adminInterest = df.format(obj.getDouble("admin_interest"));
	          	String eps = df.format(obj.getDouble("eps"));
	          	String totalAmount = df.format(obj.getDouble("totalAmount"));
	          	String interestRate = df.format(obj.getDouble("interestRate"));
	          	
	//              String MWDFileLine = "MMLSPFSALPAY0000018580" + "#~#" + obj.getString("yearAndMonth") + "#~#" +
	//              		obj.getDouble("pfBase") + "#~#" + obj.getDouble("companyContribution") + "#~#" +
	//              		obj.getDouble("admin_interest") + "#~#" + obj.getDouble("eps") + "#~#" + obj.getDouble("totalAmount") + "#~#" +
	//              		obj.getString("interestRate")+"\n";
	          	
	          	String MWDFileLine = mid + "#~#" + yearAndMonth + "#~#" +
	          			pfBase + "#~#" + compContubition + "#~#" + adminInterest + "#~#" + eps + "#~#" + totalAmount + "#~#" +
	          			interestRate+"\n";
//
              textMWDFileLine.add(MWDFileLine);

          };
//        }

        return textMWDFileLine;
    }
    
    @Override
    public String getMemberId(String pfNumber) {
    	return pfUserRepository.getMemberId(pfNumber);
    }

    @Override
    public void setMemberId(String mid,String pfNumber) {
    	 pfUserRepository.setMemberId(mid, pfNumber);
    }
    
    @Override
    public JSONArray getMonthlyHigherPensionForMWDExcelSheet(String pfNumber, String fiscalYear, String first_contribution_year) {

    	JSONArray monthlyHigherPension = new JSONArray();
    	
    	String memberId = pfUserRepository.getMemberId(pfNumber);
//    	List<JSONObject> YearlyClosingBalance = new ArrayList<>();

//    	int counter = 0;
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
		Double pfBaseTotal = 0.00;
		Double actualPfBaseTotal = 0.00;
		Double differenceForHigherPensionTotal = 0.00;
		Double interestDiffTotal = 0.00;
		Double totalAmountTotal = 0.00;
//		double admin_interest_value_total = 0;

		
		System.out.println(pfNumber+","+first_contribution_year+","+fiscalYear);
		List<PfStartYearMonth> listOfYears = getFiscalYearBetween(pfNumber,fiscalYear,first_contribution_year );
    	
    	System.out.println(listOfYears.size());
    	for(PfStartYearMonth year: listOfYears) {
    		
    	System.out.println("im called "+year.getFiscalYear());
	    	Optional<PfUserInterestRates> interestRate = pfUserRepository.getInterestRate(year.getFiscalYear());
	    	
	    	System.out.println("breakDown "+interestRate.get().getFiscalYear()+" , "+interestRate.get().getIsBreakDown());
	    	
	    	List<PfMonthlyContribution> pfMonthlyContribution = getMonthlyContribution(pfNumber, year.getFiscalYear());
	    
	    	//12 considered sicnce year consists of 12 months
	    	int monthNumber = 12-1;
	    	
	    	// 1 for counting month
	    	int month = 0;
	    	
	    	if(!interestRate.isEmpty()) {
	    		int breakCount = 0;
				for(PfMonthlyContribution con: pfMonthlyContribution) {

						month++;
						
						JSONObject highpension = new JSONObject();
			    		
//			    		highpension.put("monthNumber", monthNumber);
			    		
			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
//			    		double openingBalance = 0.00;
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
//					    double pfBaseInterest = 12.00;
					    double pfBaseInterest = 0;
					    if (year.getFiscalYear().equals("FY1994-1995") || year.getFiscalYear().equals("FY1995-1996")
					    		|| (year.getFiscalYear().equals("FY1996-1997") && con.getSubType() < 7 )) {
					    	pfBaseInterest = 10;
					    }else {
					    	pfBaseInterest = 12;
					    }
					    System.out.println("pfabse int calc "+pfBaseInterest +","+con.getSubType());
					    
					    double actualPfBseInterest = 8.33;
			    				
					    double eps = memberContribution-companyContribution;
	
//			    		epsTotal += eps;
			    		
			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
//			    		JSONObject yearAndMonth = new JSONObject();
//			    		yearAndMonth.put("pfBase", pfBase);
//			    		yearAndMonth.put("subType",con.getSubType());
			    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		  if(resultMonth == 13) {
			    			  mon = "01";
			    			  resultYear = con.getActualYear();
			    		  }
			    		  if(resultMonth == 14) {
			    			  mon = "02";
			    			  resultYear = con.getActualYear();
			    		  }
			    		  if(resultMonth == 15) {
			    			  mon = "03";
			    			  resultYear = con.getActualYear();
			    		  }
			    		 
//			    		  if (resultMonth > 12) {
////			    		    resultMonth = 1+ (resultMonth - 12);
//			    			  resultMonth = resultMonth - 12;
//			    		    mon = Integer.toString(resultMonth);
//			    		    resultYear = con.getActualYear();
//			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		  
			    		 String date = mon+"/"+resultYear;

			    		pfBaseTotal += pfBase;
			    		
			    		String slab1 =  pfUserRepository.amount(resultYear+"-"+mon+"-01");
			    		if(slab1 == null || slab1.equals("")) {
			    			mon =  String.valueOf(Integer.parseInt(mon)+1);
			    			slab1 =  pfUserRepository.amount(resultYear+"-"+(mon)+"-01");
			    		}
			    		
			    		double slab  = new Double(slab1);
			    		System.out.println("slab amount "+slab);
			    		double slabeps = 0;
			    		if(eps != 0 && (slab  == 5000 && slab < pfBase)) {
			    			eps = 417;
			    		}else if(eps != 0 && (slab  == 5000 && slab > pfBase)) {
			    			eps = 417;
			    		}else if (eps != 0 && (slab == 6500 && slab < pfBase)) {
			    			eps = 541;
			    		}else if (eps != 0 && (slab == 6500 && slab > pfBase)) {
			    			eps = 541;
			    		}
			    		else if(eps != 0){
			    			eps = 1250;
			    		}
			    		
			    		//Admin Interest Calculation
			    		String admin = pfUserRepository.getAdminInterest(resultYear+"-"+mon+"-01");
			    		System.out.println("admin me "+admin);
			    		System.out.println(resultYear+"-"+mon+"-01");
			    		double admin_interest = new Double(admin == null? "0":admin);
			    		
			    		System.out.println("admin interest "+admin_interest);
			    		
			    		System.out.println("pf-Base = "+pfBase);
			    		double admin_interest_value = Math.round(((pfBase-15000)/100)*admin_interest);
			    		if(admin_interest_value < 0) {
			    			admin_interest_value = 0;
			    		}
			    		System.out.println("admin interes Value "+admin_interest_value);
//			    		admin_interest_value_total += admin_interest_value;
			    		
			    		
			    		double actualPfBase = Math.round((pfBase/100)*actualPfBseInterest);
			   
			    		double restrictForEps =0;
			    		if (actualPfBase <= eps) {
			    			restrictForEps = actualPfBase;
			    		}else {
			    			restrictForEps = eps;
			    		}
			    		
			    		if(actualPfBase < eps) {
			    			eps =actualPfBase;
			    		}
			    		if(eps == 0) {
			    			actualPfBase = 0;
			    		}
			    		
			    		actualPfBaseTotal += actualPfBase;
			    		
			    		epsTotal += eps;
			    		
			    		double differenceForHigherPension = actualPfBase-eps;
	
			    		differenceForHigherPensionTotal += differenceForHigherPension;
			    		
			    		double interest = 0.00;
			    		double a = 0;
			    		if(interestRate.get().getIsBreakDown() != 1) {
			    			a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    			interest = interestRate.get().getInterestRate1();
			    		}else if(interestRate.get().getIsBreakDown() == 1) {
			    			if(breakCount < interestRate.get().getFirstBreak()) {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    				interest = interestRate.get().getInterestRate1();
			    			}else {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate2();
			    				interest = interestRate.get().getInterestRate2();
			    			}
			    		}
	
			    		double b = a/12;

			    		double interestOnDifferential = 0.00;
	
			    		if(monthNumber != 0) {
			    			interestOnDifferential =Math.round(b*monthNumber);
			    		}

			    		interestDiffTotal += interestOnDifferential;
			    		
			    		double totalAmount = Math.round(differenceForHigherPension+interestOnDifferential);
	
			    		totalAmountTotal += totalAmount;
			    		NumberFormat formatter = new DecimalFormat("#0.0");
			    		
//			    		System.out.println(formatter.format(4.0));
			    			highpension.put("(1) Member ID", memberId);
			    			highpension.put("(2) Wage Month & Year", date);
//			    			highpension.put("fisccal_year", year.getFiscalYear());
			    			highpension.put("(3) Wages on which PF contribution was paid",String.valueOf(Math.round(pfBase)));
//			    			highpension.put("employeeControbution", memberContribution);	
							highpension.put("(4) Pension Contribution from employer 8.33%", String.valueOf(Math.round(actualPfBase)));
//							highpension.put("vpfContribution", vpfContribution);
							highpension.put("(5) Pension Contribution from employee @ 1.16%", String.valueOf(admin_interest_value));
							highpension.put("(6) Pension Contribution Paid by the employer earlier", String.valueOf(Math.round(eps)));
							highpension.put("(7) Pension Contribution to be paid along with interest", String.valueOf(Math.round(differenceForHigherPension+interestOnDifferential)));
							highpension.put("(8) Yearly PF Interest rate", String.valueOf(formatter.format(interest)));
//							highpension.put("actualPfBase", Math.round(actualPfBase));
//							highpension.put("differenceForHigherPension", Math.round(differenceForHigherPension));
//							
//							highpension.put("interestOnDifferential", Math.round(interestOnDifferential));
							
							
							monthlyHigherPension.put(highpension);
							if(month == 12) {
								break;
							}
			    		monthNumber = monthNumber-1;
//			    	}
			    		breakCount += 1;
				}
			}
//	    	counter += 1;
    	}
//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
//    	monthlyHigherPension.put(pfBaseArray);
    	return monthlyHigherPension;
    }
    
    @Override
    public JSONArray mwdDataFor7P(String pfNumber, String fiscalYear, String first_contribution_year) {

    	JSONArray monthlyHigherPension = new JSONArray();
    	
    	String memberId = pfUserRepository.getMemberId(pfNumber);
//    	List<JSONObject> YearlyClosingBalance = new ArrayList<>();

//    	int counter = 0;
    	Double employeeTotal = 0.00;
		Double employerTotal = 0.00;
		Double vpfTotal = 0.00;
		Double epsTotal = 0.00;
		Double pfBaseTotal = 0.00;
		Double actualPfBaseTotal = 0.00;
		Double differenceForHigherPensionTotal = 0.00;
		Double interestDiffTotal = 0.00;
		Double totalAmountTotal = 0.00;
//		double admin_interest_value_total = 0;

		
		System.out.println(pfNumber+","+first_contribution_year+","+fiscalYear);
		List<PfStartYearMonth> listOfYears = getFiscalYearBetween(pfNumber,fiscalYear,first_contribution_year );
    	
    	System.out.println(listOfYears.size());
    	for(PfStartYearMonth year: listOfYears) {
    		
    	System.out.println("im called "+year.getFiscalYear());
	    	Optional<PfUserInterestRates> interestRate = pfUserRepository.getInterestRate(year.getFiscalYear());
	    	
	    	System.out.println("breakDown "+interestRate.get().getFiscalYear()+" , "+interestRate.get().getIsBreakDown());
	    	
	    	List<PfMonthlyContribution> pfMonthlyContribution = getMonthlyContribution(pfNumber, year.getFiscalYear());
	    
	    	//12 considered sicnce year consists of 12 months
	    	int monthNumber = 12-1;
	    	
	    	// 1 for counting month
	    	int month = 0;
	    	
	    	if(!interestRate.isEmpty()) {
	    		int breakCount = 0;
				for(PfMonthlyContribution con: pfMonthlyContribution) {

						month++;
						
						JSONObject highpension = new JSONObject();
			    		
			    		highpension.put("monthNumber", monthNumber);
			    		
			    		double memberContribution = Math.round(new Double(con.getMemberContribution()==null? "0.00":con.getMemberContribution()));
			    		double companyContribution = Math.round(new Double(con.getCompanyContribution()==null? "0.00":con.getCompanyContribution()));
			    		double vpfContribution = Math.round(new Double(con.getMemberExtraContribution()==null? "0.00":con.getMemberExtraContribution()));
//			    		double openingBalance = 0.00;
					    
					    employeeTotal += memberContribution;
					    employerTotal += companyContribution;
					    
					    vpfTotal += vpfContribution;
			    		
//					    double pfBaseInterest = 12.00;
					    double pfBaseInterest = 0;
					    if (year.getFiscalYear().equals("FY1994-1995") || year.getFiscalYear().equals("FY1995-1996")
					    		|| (year.getFiscalYear().equals("FY1996-1997") && con.getSubType() < 7 )) {
					    	pfBaseInterest = 10;
					    }else {
					    	pfBaseInterest = 12;
					    }
					    System.out.println("pfabse int calc "+pfBaseInterest +","+con.getSubType());
					    
					    double actualPfBseInterest = 8.33;
			    				
					    double eps = memberContribution-companyContribution;
	
//			    		epsTotal += eps;
			    		
			    		double pfBase = Math.round(memberContribution/pfBaseInterest*100);
			    		
//			    		JSONObject yearAndMonth = new JSONObject();
//			    		yearAndMonth.put("pfBase", pfBase);
//			    		yearAndMonth.put("subType",con.getSubType());
			    		
			    		  int resultMonth = con.getSubType() + 3;
			    		  int resultYear = con.getActualYear()-1;
			    		  // check if resultMonth is within the valid range
			    		  String mon = "";

			    		  if(resultMonth == 10 || resultMonth == 11 || resultMonth == 12 ) {
			    			  mon= Integer.toString(resultMonth);
			    		  }
			    		  if(resultMonth == 13) {
			    			  mon = "01";
			    			  resultYear = con.getActualYear();
			    		  }
			    		  if(resultMonth == 14) {
			    			  mon = "02";
			    			  resultYear = con.getActualYear();
			    		  }
			    		  if(resultMonth == 15) {
			    			  mon = "03";
			    			  resultYear = con.getActualYear();
			    		  }
			    		 
//			    		  if (resultMonth > 12) {
////			    		    resultMonth = 1+ (resultMonth - 12);
//			    			  resultMonth = resultMonth - 12;
//			    		    mon = Integer.toString(resultMonth);
//			    		    resultYear = con.getActualYear();
//			    		  }
			    		  
			    		  if(resultMonth < 10) {
			    			  mon = "0"+resultMonth;
			    		  }
			    		  
			    		 String date = mon+"/"+resultYear;

			    		pfBaseTotal += pfBase;
			    		
			    		String slab1 =  pfUserRepository.amount(resultYear+"-"+mon+"-01");
			    		if(slab1 == null || slab1.equals("")) {
			    			mon =  String.valueOf(Integer.parseInt(mon)+1);
			    			slab1 =  pfUserRepository.amount(resultYear+"-"+(mon)+"-01");
			    		}
			    		
			    		double slab  = new Double(slab1);
			    		System.out.println("slab amount "+slab);
			    		double slabeps = 0;
			    		if(eps != 0 && (slab  == 5000 && slab < pfBase)) {
			    			eps = 417;
			    		}else if(eps != 0 && (slab  == 5000 && slab > pfBase)) {
			    			eps = 417;
			    		}else if (eps != 0 && (slab == 6500 && slab < pfBase)) {
			    			eps = 541;
			    		}else if (eps != 0 && (slab == 6500 && slab > pfBase)) {
			    			eps = 541;
			    		}
			    		else if(eps != 0) {
			    			eps = 1250;
			    		}
			    		
			    		//Admin Interest Calculation
			    		String admin = pfUserRepository.getAdminInterest(resultYear+"-"+mon+"-01");
			    		System.out.println("admin me "+admin);
			    		System.out.println(resultYear+"-"+mon+"-01");
			    		double admin_interest = new Double(admin == null? "0":admin);
			    		
			    		System.out.println("admin interest "+admin_interest);
			    		
			    		System.out.println("pf-Base = "+pfBase);
			    		double admin_interest_value = Math.round(((pfBase-15000)/100)*admin_interest);
			    		if(admin_interest_value < 0) {
			    			admin_interest_value = 0;
			    		}
			    		System.out.println("admin interes Value "+admin_interest_value);
//			    		admin_interest_value_total += admin_interest_value;
			    		
			    		
			    		double actualPfBase = Math.round((pfBase/100)*actualPfBseInterest);
			   
			    		double restrictForEps =0;
			    		if (actualPfBase <= eps) {
			    			restrictForEps = actualPfBase;
			    		}else {
			    			restrictForEps = eps;
			    		}
			    		
			    		if(actualPfBase < eps) {
			    			eps =actualPfBase;
			    		}
			    		if(eps == 0) {
			    			actualPfBase = 0 ;
			    		}
			    		
			    		actualPfBaseTotal += actualPfBase;
			    		
			    		epsTotal += eps;
			    		
//			    		double restrictForEps =0;
//			    		if (actualPfBase <= eps) {
//			    			restrictForEps = actualPfBase;
//			    			eps = actualPfBase;
//			    		}else {
//			    			restrictForEps = eps;
//			    		}
			    		
			    		double differenceForHigherPension = actualPfBase-eps;
	
			    		differenceForHigherPensionTotal += differenceForHigherPension;
			    		
			    		double interest = 0.00;
			    		double a = 0;
			    		if(interestRate.get().getIsBreakDown() != 1) {
			    			a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    			interest = interestRate.get().getInterestRate1();
			    		}else if(interestRate.get().getIsBreakDown() == 1) {
			    			if(breakCount < interestRate.get().getFirstBreak()) {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate1();
			    				interest = interestRate.get().getInterestRate1();
			    			}else {
			    				a = (differenceForHigherPension/100)*interestRate.get().getInterestRate2();
			    				interest = interestRate.get().getInterestRate2();
			    			}
			    		}
	
			    		double b = a/12;

			    		double interestOnDifferential = 0.00;
	
			    		if(monthNumber != 0) {
			    			interestOnDifferential =Math.round(b*monthNumber);
			    		}

			    		interestDiffTotal += interestOnDifferential;
			    		
			    		double totalAmount = Math.round(differenceForHigherPension+interestOnDifferential);
	
			    		totalAmountTotal += totalAmount;
			    		NumberFormat formatter = new DecimalFormat("#0.0");
			    		
//			    		System.out.println(formatter.format(4.0));
			    			highpension.put("memberId", memberId);
			    			highpension.put("yearAndMonth", date);
			    			highpension.put("fisccal_year", year.getFiscalYear());
			    			highpension.put("pfBase",String.valueOf(Math.round(pfBase)));
			    			highpension.put("employeeControbution", memberContribution);	
//							highpension.put("companyContribution", String.valueOf(Math.round(actualPfBase)));
			    			highpension.put("companyContribution", companyContribution);
//							highpension.put("vpfContribution", vpfContribution);
							highpension.put("admin_interest", String.valueOf(admin_interest_value));
							highpension.put("eps", String.valueOf(Math.round(eps)));
							highpension.put("totalAmount", String.valueOf(Math.round(differenceForHigherPension+interestOnDifferential)));
							highpension.put("interestRate", String.valueOf(formatter.format(interest)));
							highpension.put("actualPfBase", Math.round(actualPfBase));
//							highpension.put("differenceForHigherPension", Math.round(differenceForHigherPension));
//							
//							highpension.put("interestOnDifferential", Math.round(interestOnDifferential));
							String[] date1 = date.split("/");
				          	int month1 = Integer.valueOf(date1[0]);
				          	int year1 = Integer.valueOf(date1[1]);
							if(year1 == 1995) {
				          		if(month1 > 10) {
				          			monthlyHigherPension.put(highpension);
				          		}
				          	}else if(year1 > 1995) {
				          		monthlyHigherPension.put(highpension);
				          	}
//							monthlyHigherPension.put(highpension);
							if(month == 12) {
								break;
							}
			    		monthNumber = monthNumber-1;
//			    	}
			    		breakCount += 1;
				}
			}
//	    	counter += 1;
    	}
//    	monthlyHigherPension.put(employeeTotal);
//    	monthlyHigherPension.put(employerTotal);
//    	monthlyHigherPension.put(pfBaseArray);
    	return monthlyHigherPension;
    }

    @Override
    public String getMemberName(String pfNumber) {
    	return pfUserRepository.getMemberName(pfNumber);
    }
    
    @Override
    public Optional<Customer7PsDetails> getMemberDetails(String pfNumber){
    	
    	Optional<Customer7PsDetails> customer7PsDetails = pfUserRepository.getMemberDetails(pfNumber);

    	if(customer7PsDetails.isEmpty()) throw new EntityNotFoundException("Details Not Found");;    
    	
    	return customer7PsDetails;
    }
    
    /**
     * This method populates all the files in a directory to a List
     * @param dir
     * @throws IOException
     */
    @Override
    public List<String> populateFilesList(File dir) throws IOException {
    	
    	List<String> filesListInDir = new ArrayList<String>();
    	 
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
            else populateFilesList(file);
        }
        
        return filesListInDir;
    }
    
    /**
     * This method zips the directory
     * @param dir
     * @param zipDirName
     */
    @Override
    public void zipDirectory(File dir, String zipDirName, List<String> filesListInDir) {
        try {
            populateFilesList(dir);
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for(String filePath : filesListInDir){
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void setMemberDetails(String name, String fatherName, String pfNumber, String memberId, 
    		String leavingFiscalYear, String reasonOfLeaving,String dateOfLeaving,String establishmentName,String establishmentCode) {
    	pfUserRepository.setMemberDetails(name, fatherName, pfNumber, memberId, leavingFiscalYear, reasonOfLeaving, dateOfLeaving, establishmentName, establishmentCode);
    }

	@Override
	public XSSFCellStyle createHeaderCellStyle(Workbook workbook) {
		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setWrapText(true);
		return style;
	}

	@Override
	public XSSFCellStyle createDataCellStyle(Workbook workbook) {

		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		return style;
	}

	@Override
	public XSSFCellStyle createWrapTextStyle(Workbook workbook) {

		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		style.setWrapText(true); // Enable text wrapping
		return style;
	}

	@Override
	public XSSFCellStyle createSolidBordersStyle(Workbook workbook) {

		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();

		// Set border style for all sides to solid
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		return style;
	}

	@Override
	public void setBorderStyleSolidThin(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {

		RegionUtil.setBorderTop(BorderStyle.THIN, cellRangeAddress, sheet);
		RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangeAddress, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangeAddress, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, cellRangeAddress, sheet);
	}

	@Override
	public List<MibsCustomerDetails> getMibsCustomerDetailsList(String establishmentCode){
		return pfUserRepository.getMibsCustomerDetails(establishmentCode);
	}

	@Override
	public List<String> getEstablishmentCodeList(){

		return pfUserRepository.getEstablishmentCodeList();
	}
}
