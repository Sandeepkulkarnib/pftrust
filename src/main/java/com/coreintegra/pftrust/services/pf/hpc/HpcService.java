package com.coreintegra.pftrust.services.pf.hpc;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;

import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeeMaster;
import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeePfbase;
import com.coreintegra.pftrust.entities.pf.hpc.HpcInterestRates;
import com.coreintegra.pftrust.entities.pf.hpc.HpcPfslab;

public interface HpcService {

	Optional<HpcEmployeeMaster> getEmployee(String memberId, Boolean isActive);
	
	List<HpcEmployeePfbase> getPfbaseList(HpcEmployeeMaster employee, Boolean isActive);
	
	Optional<HpcInterestRates> getInterestList(Integer actualYear,Integer month,Boolean isActive);
	
	Optional<HpcPfslab> getSlabAmount(String date,Boolean isActive);
	
	JSONArray getWageInterestAndSlabData(HpcEmployeeMaster employee, List<HpcEmployeePfbase> pfbaseList);
	
	JSONArray getCreateMwdData(JSONArray wageInterestAndSlabData);
	
	List<String> getMWDSheetLines(JSONArray mwdData, String mid);
	
	List<String> getFiscalYear(Long hpcEmployeeMasterId);
	
	List<HpcEmployeePfbase> getPfbaseListByFiscalYear(HpcEmployeeMaster employee,String fiscalYear ,Boolean isActive);
}
