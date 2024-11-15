package com.coreintegra.pftrust.services.pf.hpc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeeMaster;
import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeePfbase;
import com.coreintegra.pftrust.entities.pf.hpc.HpcInterestRates;
import com.coreintegra.pftrust.entities.pf.hpc.HpcPfslab;
import com.coreintegra.pftrust.repositories.pf.hpc.HpcEmployeeMasterRepository;
import com.coreintegra.pftrust.repositories.pf.hpc.HpcEmployeePfbaseRepository;
import com.coreintegra.pftrust.repositories.pf.hpc.HpcInterestRatesRepository;
import com.coreintegra.pftrust.repositories.pf.hpc.HpcPfslabRepository;

@Service
public class HpcServiceImpl implements HpcService {
	
	@Autowired
	HpcEmployeeMasterRepository hpcEmployeeMasterRepository; 
	
	@Autowired
	HpcEmployeePfbaseRepository hpcEmployeePfbaseRepository; 
	
	@Autowired
	HpcInterestRatesRepository hpcInterestRatesRepository;
	
	@Autowired
	HpcPfslabRepository hpcPfslabRepository;

	@Override
	public Optional<HpcEmployeeMaster> getEmployee(String memberId, Boolean isActive){
		
		Optional<HpcEmployeeMaster> employee =  hpcEmployeeMasterRepository.findByMemberIdAndIsActive(memberId, isActive);
		
		return employee;
	}
	
	@Override
	public List<HpcEmployeePfbase> getPfbaseList(HpcEmployeeMaster employee, Boolean isActive){
		
		List<HpcEmployeePfbase> pfbaseList = hpcEmployeePfbaseRepository.findAllByHpcEmployeeMasterAndIsActiveOrderByYearAscMonthAsc(employee, isActive);
		
		return pfbaseList;
	}
	
	@Override
	public Optional<HpcInterestRates> getInterestList(Integer actualYear,Integer month,Boolean isActive){
	
		Optional<HpcInterestRates> interestList = hpcInterestRatesRepository.findAllByActualYearAndMonthAndIsActive(actualYear, month, isActive);
		
		return interestList;
	}
	
	@Override
	public Optional<HpcPfslab> getSlabAmount(String date,Boolean isActive){

		Optional<HpcPfslab> slabAmountObject = hpcPfslabRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqualAndIsActive(date);
	
		return slabAmountObject;
	}
	
	@Override
	public JSONArray getWageInterestAndSlabData(HpcEmployeeMaster employee, List<HpcEmployeePfbase> pfbaseList) {
		
		JSONArray array = new JSONArray();
		
		for(HpcEmployeePfbase pfbase:pfbaseList) {
        	
        	JSONObject object = new JSONObject();
        	
        	Integer month = 0;
        	Integer year = pfbase.getYear();
        	if(pfbase.getMonth() < 10) {
        		month = pfbase.getMonth() +3;
        		year = pfbase.getYear() - 1;
        	}else if(pfbase.getMonth() == 10) {
        		month = 1;
        	}
        	else if(pfbase.getMonth() == 11) {
        		month = 2;
        	}
        	else if(pfbase.getMonth() == 12) {
        		month = 3;
        	}

        	if(pfbase.getMonth() < 13) {
//        		Optional<HpcInterestRates> interest = getInterestList(pfbase.getYear(),pfbase.getMonth(), true);
        		Optional<HpcInterestRates> interest = getInterestList(year,month, true);
        		System.out.println(" year and month for interest : "+pfbase.getYear()+","+pfbase.getMonth());
        		Optional<HpcPfslab> slabAmount = getSlabAmount(year+"-"+month+"-"+"01", true);
        		System.out.println(" year and month for slabAmount : "+year+"-"+month+"-"+"01");
        		object.put("interestRate", interest.get().getInterestRate());
        		System.out.println(" year and month for slabAmount : "+year+"-"+month+"-"+"01");
        		object.put("slabAmount", slabAmount.get().getSlabAmount());
        		
        		object.put("wageMonthYear", (month<10? "0"+month:month)+"/"+year);
        		object.put("memberId", employee.getMemberId());
            	object.put("pfBase", pfbase.getPfBase());
            	object.put("year", pfbase.getYear());
            	object.put("month", pfbase.getMonth());
            	object.put("fiscalYear", pfbase.getFiscalYear());
            	object.put("monthNumber", 12-pfbase.getMonth());
            	array.put(object);
        	} 	
        }
		
		return array;
	}
	
	@Override
	public JSONArray getCreateMwdData(JSONArray wageInterestAndSlabData) {
		
		JSONArray mwdDataArray = new JSONArray();
		
		if(wageInterestAndSlabData.length() > 0) {

//			Month Number taken as 11 since interest for differenceForHigherPension will be paid for remaing month of the current year
//			int monthNumber = 11;
			for(int i=0; i < wageInterestAndSlabData.length(); i++) {
				
				JSONObject mwdDataObject = new JSONObject(); 
				
				double pfBase = new Double(wageInterestAndSlabData.getJSONObject(i).getDouble("pfBase"));
				double slab = new Double(wageInterestAndSlabData.getJSONObject(i).getDouble("slabAmount"));
				double interestRate = new Double(wageInterestAndSlabData.getJSONObject(i).getDouble("interestRate"));
				String wageMonthYear = wageInterestAndSlabData.getJSONObject(i).getString("wageMonthYear");
				double pensionContribution = 0;
				double pensionContributionEmployerEarlier = 0;
				double differenceForHigherPension = 0;
				double intrestOnDifferential = 0;
				double adminCharges = 0;
				
				int monthNumber = wageInterestAndSlabData.getJSONObject(i).getInt("monthNumber");
				
				if(slab == 5000) {
					pensionContributionEmployerEarlier = 417;
				}else if(slab == 6500){
					pensionContributionEmployerEarlier = 541;
				}else if(slab == 15000) {
					pensionContributionEmployerEarlier = 1250;
				}
				
				pensionContribution = Math.round((pfBase/100)*8.33);
				
				if(pensionContribution < pensionContributionEmployerEarlier) {
//					differenceForHigherPension = 0;
					pensionContributionEmployerEarlier = Math.round(pensionContribution);
				}
//				else {
//					differenceForHigherPension = Math.round(pensionContribution - pensionContributionEmployerEarlier);
//				}
				
				differenceForHigherPension = Math.round(pensionContribution - pensionContributionEmployerEarlier);
				
				intrestOnDifferential = Math.round(((differenceForHigherPension/100)*interestRate)/12*monthNumber);
				
				String[] date = wageInterestAndSlabData.getJSONObject(i).getString("wageMonthYear").split("/");
				int month = Integer.parseInt(date[0]);
				int year = Integer.parseInt(date[1]);
				if(year > 2013) {
					if(year == 2014) {
						if(month > 8) {
							if(pfBase >= 15000) {
								adminCharges = ((pfBase-15000)/100)*1.16;
							}
						}
					}else if(year > 2014) {
						if(pfBase >= 15000) {
							adminCharges = ((pfBase-15000)/100)*1.16;
						}
					}
				}
				
				mwdDataObject.put("memberId", wageInterestAndSlabData.getJSONObject(i).getString("memberId"));
				mwdDataObject.put("wageMonthYear", wageMonthYear);
				mwdDataObject.put("pfBase", String.valueOf(pfBase));
				mwdDataObject.put("pensionContribution", String.valueOf(Math.round(pensionContribution)));
				mwdDataObject.put("adminCharges", String.valueOf(Math.round(adminCharges)));
				mwdDataObject.put("pensionContributionEmployerEarlier", String.valueOf(Math.round(pensionContributionEmployerEarlier)));
				mwdDataObject.put("amountToBePaid", String.valueOf(Math.round(differenceForHigherPension + intrestOnDifferential)));
				mwdDataObject.put("interestRate",String.valueOf(interestRate));
				
				mwdDataArray.put(mwdDataObject);
				
//				monthNumber -= 1;
//				if(monthNumber < 0) {
//					 monthNumber = 11;
//				}
			}
		}
		
		return mwdDataArray;
	}
	
	@Override
    public List<String> getMWDSheetLines(JSONArray mwdData, String mid) {

        DecimalFormat df = new DecimalFormat("####0.00");
        
        List<String> textMWDFileLine = new ArrayList<>();
        
        if(!(mid.equals(null) || mid.equals(""))){
        	
        	for(int i = 0; i<mwdData.length();i++) {
        		
	          	JSONObject obj = mwdData.getJSONObject(i);

	          	String pfBase = df.format(new Double(obj.getString("pfBase")));
	          	String employerEps = df.format(new Double(obj.getString("pensionContribution")));
	          	String adminInterest = df.format(new Double(obj.getString("adminCharges")));
	          	String eps = df.format(new Double(obj.getString("pensionContributionEmployerEarlier")));
	          	String totalAmount = df.format(new Double(obj.getString("amountToBePaid")));
	          	String interestRate = df.format(new Double(obj.getString("interestRate")));
	          	
	          	String[] date = obj.getString("wageMonthYear").split("/");
	          	int month = Integer.valueOf(date[0]);
	          	int year = Integer.valueOf(date[1]);
	          	if(year == 1995) {
	          		if(month > 10) {
	          			String MWDFileLine = mid + "#~#" + obj.getString("wageMonthYear") + "#~#" +
			          			pfBase + "#~#" + employerEps + "#~#" + adminInterest + "#~#" + eps + "#~#" + totalAmount + "#~#" +
			          			"0.00"+"\n";

		              textMWDFileLine.add(MWDFileLine);
	          		}

	          	}else if(year > 1995) {
	          		String MWDFileLine = mid + "#~#" + obj.getString("wageMonthYear") + "#~#" +
		          			pfBase + "#~#" + employerEps + "#~#" + adminInterest + "#~#" + eps + "#~#" + totalAmount + "#~#" +
		          			"0.00"+"\n";

	              textMWDFileLine.add(MWDFileLine);
	          	}

          };
        }

        return textMWDFileLine;
    }
	
	@Override
	public List<String> getFiscalYear(Long hpcEmployeeMasterId){
		
		List<String> fiscalYears = hpcEmployeePfbaseRepository.getFiscalYears(hpcEmployeeMasterId);
		
		return fiscalYears;
	}
	
	@Override
	public List<HpcEmployeePfbase> getPfbaseListByFiscalYear(HpcEmployeeMaster employee,String fiscalYear ,Boolean isActive){
		
		List<HpcEmployeePfbase> pfbaseList = hpcEmployeePfbaseRepository.findAllByHpcEmployeeMasterAndFiscalYearAndIsActiveOrderByYearAscMonthAsc(employee,fiscalYear ,isActive);
		
		return pfbaseList;
	}
}
