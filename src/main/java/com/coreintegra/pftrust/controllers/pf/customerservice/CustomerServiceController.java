package com.coreintegra.pftrust.controllers.pf.customerservice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.coreintegra.pftrust.dtos.pdf.Form7Ps;
import com.coreintegra.pftrust.dtos.pdf.Form7PsContribution;
import com.coreintegra.pftrust.dtos.pdf.Form8Ps;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDTO;
import com.coreintegra.pftrust.entities.pf.department.dtos.JobDetails;
import com.coreintegra.pftrust.entities.pf.department.dtos.LaunchJobDTO;
import com.coreintegra.pftrust.projections.*;
import com.coreintegra.pftrust.services.pf.customerservice.CustomerService;

import com.coreintegra.pftrust.services.pf.jobs.JobLaunchService;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.services.pf.pdf.GenerateForm7Ps;
import com.coreintegra.pftrust.services.pf.pdf.GenerateForm8Ps;
import com.coreintegra.pftrust.services.storage.FileStorageService;
import com.coreintegra.pftrust.util.Response;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(CustomerServiceEndpoints.CUSTOMER_SERVICE_ENDPOINT)
public class CustomerServiceController {

	private final Path fileStorageLocation = Paths.get("")
            .toAbsolutePath().normalize();
	
	private final CustomerService customerService;
	private final FileStorageService fileStorageService;

    private final JobLaunchService jobLaunchService;
	
	@Autowired
    private GenerateForm7Ps generateForm7Ps;
	
	@Autowired
    private GenerateForm8Ps generateForm8Ps;

    public CustomerServiceController(CustomerService customerService,
                                     FileStorageService fileStorageService, JobLaunchService jobLaunchService) {
        this.customerService = customerService;
        this.fileStorageService = fileStorageService;
        this.jobLaunchService = jobLaunchService;
    }

    @GetMapping("/getPfNumber")
    public String getEmployeePfNumber() {
    	return "12345";
    }
    
    @GetMapping("/getFinancialYear")
    public ResponseEntity<Object> getFinancialYear(@RequestParam String pfNumber) {

        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONArray fiscalList = new JSONArray();
        for(PfUserFiscalYearProjection year:fiscalYears) {
        	JSONObject fiscalYear = new JSONObject();
        	fiscalYear.put("fiscalYear",year.getFiscalYear());
        	fiscalYear.put("pfNumber", year.getPfNumber());
        	fiscalList.put(fiscalYear);
        }
        
        return Response.success(fiscalList.toString());
    }
    
    @GetMapping("searchemployee")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployees(
            @RequestParam(value = "search", required = false, defaultValue = "")String search,
            @RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10")Integer size){

        Page<PfUserProjection> pfUsers = customerService.getPfUsers(search, size, page);

        return Response.success(pfUsers);
    }
    
    @GetMapping("/employeedetails")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployeeDetails(@RequestParam String pfNumber) {

    	String[] monthName = {"April","May","June","July","August","September","October","Novermber","December","January","February","March"};    	
        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        int size = fiscalYears.size(); 
        String dataFromTo = fiscalYears.get(0).getFiscalYear()+" to "+fiscalYears.get(size-1).getFiscalYear();
        System.out.println("tenure period : "+dataFromTo);
        
        Optional<PfStartYearMonth> startYearMonth = customerService.getPfStartYearMonth(pfNumber);
        
        System.out.println("size of startYearMonth "+startYearMonth);
        String first_contribution_year = startYearMonth == null ? "NA":startYearMonth.get().getFiscalYear();
        
        String first_contribution_month = startYearMonth == null ? "NA":startYearMonth.get().getSubType();
        System.out.println(first_contribution_year +" , "+first_contribution_month);
//        if(pfNumber.equals("18580")) {
//        	first_contribution_month = monthName[Integer.parseInt(first_contribution_month) -12];
//        }else {
        	first_contribution_month = monthName[Integer.parseInt(first_contribution_month) -1];
//        }
        System.out.println(first_contribution_month+" , "+first_contribution_year);
                
        System.out.println("last fiscal year" + fiscalYears.get(size-1).getFiscalYear());
        JSONArray higherPensionMonthly = customerService.getMonthlyHigherPension(pfNumber, fiscalYears.get(size-1).getFiscalYear(),first_contribution_year);
        
        int size1 = higherPensionMonthly.length();
        double corpusPayback = Math.round(higherPensionMonthly.getJSONObject(size1-2).getDouble("totalAmount"));
        double adminInterest = Math.round(higherPensionMonthly.getJSONObject(size1-2).getDouble("admin_interest"));
        double corpusValue = Math.round(higherPensionMonthly.getJSONObject(size1-2).getDouble("corpusForTheMonth"));
        
        System.out.println("corpusValue = "+corpusValue);
        
        JSONArray pfBaseArray = higherPensionMonthly.getJSONArray(size1-1);
//        
//		JSONArray last_60_pfBase_array = new JSONArray();
		
		System.out.println("length pfbasearray "+pfBaseArray.length());
		
//		Integer tenure = customerService.getTenure(pfNumber);
//        System.out.println(tenure);

		double tenure = pfBaseArray.length()/12;
        System.out.println(tenure);
		
		double avrgPfBase = 0;
		double avrgSlab = 0;
		double calc = 0;
		double calc1 = 0;
//		double totatCalc1 = 0;
//		totatCalc1 = higherPensionMonthly.getDouble(size1-4);
		
		if(pfBaseArray.length() > 60) {
			System.out.println("im inside");
		    int i = 0;
		    for(i = pfBaseArray.length()-60;i<pfBaseArray.length();i++) {
		    	calc += pfBaseArray.getJSONObject(i).getDouble("pfBase");
		    	calc1 += pfBaseArray.getJSONObject(i).getDouble("amount");
		    }
		    avrgPfBase = Math.round(calc/60);
		    avrgSlab = Math.round(calc1/60);
		}else if(pfBaseArray.length() <= 60) {
			for(int i=0; i<pfBaseArray.length(); i++) {
		    	calc += pfBaseArray.getJSONObject(i).getDouble("pfBase");
		    	calc1 += pfBaseArray.getJSONObject(i).getDouble("amount");
		    }
			avrgPfBase = Math.round(calc/pfBaseArray.length());
			avrgSlab = Math.round(calc1/pfBaseArray.length());
		}
		
		double tenurePlus = 0;
		if(tenure > 20) {
			tenurePlus = tenure + 2;
			if(tenurePlus > 35 || tenure > 35) {
				tenurePlus = 35;
			}
		}else {
			tenurePlus = tenure;
		}
		
		System.out.println("avrg pfBase : "+avrgPfBase);
		System.out.println("avrg slab :"+avrgSlab);
		
		double newPension = (avrgPfBase*tenurePlus)/70;
//		double corpus = 0;
//		corpus = Math.round(higherPensionMonthly.getDouble(size1-2)+higherPensionMonthly.getDouble(size1-3));
//		
//		System.out.println(tenure+","+totatCalc1);
        double epsContributionActual = Math.round(((tenurePlus*avrgSlab*12)/100)*8.33);
        double epsPfJudgement = Math.round(((corpusValue+epsContributionActual)/100)*35);
//        double corpusPayBack = Math.round(epsPfJudgement - epsContributionActual);
        
        response.put("cuntributionPeriod", dataFromTo);
        response.put("contributionYear", first_contribution_year);
        response.put("contributionMonth", first_contribution_month);
        response.put("tenure", tenure);
        response.put("corpusValue",corpusValue);
        response.put("epsContributionActual", epsContributionActual);
        response.put("epsPfJudgement", epsPfJudgement);
        response.put("corpusPayBack", corpusPayback + adminInterest);
        response.put("adminInterest", adminInterest);
        response.put("newPension", Math.round(newPension));
//        response.put("monthly",higherPensionMonthly);

        return Response.success(response.toString());
    }
    
    @GetMapping("/monthlydetails")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployeeMonthlyDetails(@RequestParam String pfNumber,
    		@RequestParam String fiscalYear) {

//        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        Optional<PfStartYearMonth> startYearMonth = customerService.getPfStartYearMonth(pfNumber);
        String first_contribution_year = startYearMonth == null ? "NA":startYearMonth.get().getFiscalYear();

        JSONArray higherPensionMonthly = customerService.getMonthlyHigherPension1(pfNumber, fiscalYear,first_contribution_year);

        response.put("monthly",higherPensionMonthly);

        return Response.success(response.toString());
    }
    
    @GetMapping("/servicesummary")
    @ApplyTenantFilter
    public ResponseEntity<Object> getServiceSummary(@RequestParam String pfNumber) {

        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        Optional<PfStartYearMonth> startYearMonth = customerService.getPfStartYearMonth(pfNumber);
        String first_contribution_year = startYearMonth == null ? "NA":startYearMonth.get().getFiscalYear();
        
//        String lastFiscalYear = fiscalYears.get(fiscalYears.size()-1).getFiscalYear();
        
        JSONArray higherPensionMonthly = customerService.getServiceSummary(pfNumber,fiscalYears.get(fiscalYears.size()-1).getFiscalYear(),first_contribution_year);

        int size = higherPensionMonthly.length();
        double corpusValue = Math.round(higherPensionMonthly.getJSONObject(size-1).getDouble("totalAmount"));
        double adminTotal = Math.round(higherPensionMonthly.getJSONObject(size-1).getDouble("admin_interest"));

        JSONArray finalArray = new JSONArray();
        for(int i=1;i<size;i+=2) {
        	
        	JSONObject object0 = higherPensionMonthly.getJSONObject(i-1);
        	JSONObject object1 = higherPensionMonthly.getJSONObject(i);
        	
        	double openingBalance = 0;
        	if(i != 1 ) {
        		JSONObject object2 = higherPensionMonthly.getJSONObject(i-2);
        		openingBalance = Math.round(object2.getDouble("totalAmount"));
        	}

        	JSONObject object3 = new JSONObject();

        	double totAmountPayable = object1.getDouble("differenceForHigherPension")+ object1.getDouble("interestOnDifferential") ;
	        	object3.put("fisccal_year", object1.get("fisccal_year"));
	        	object3.put("openingBalance", openingBalance);
	        	object3.put("differenceForHigherPension", object1.get("differenceForHigherPension"));
	        	object3.put("interesRate", object1.get("interestRate"));
	        	object3.put("interestOnDifferential", object1.get("interestOnDifferential"));

	        	object3.put("totalAmountPayableOnAccount",totAmountPayable);
	        	object3.put("ytdPayableAmount", object0.get("totalAmount"));
	        	object3.put("closingBalance", object1.get("totalAmount"));
	        	finalArray.put(object3);

        }
        
        
//        response.put("monthly",higherPensionMonthly);
        response.put("monthly", finalArray);
        response.put("tenure", fiscalYears.size());
        response.put("totalAmount", corpusValue);
        response.put("adminTotal", adminTotal);
        
        return Response.success(response.toString());
    }
    
//    @GetMapping("/lastsixtymonthdata")
//    @ApplyTenantFilter
//    public ResponseEntity<Object> getLast5YrData(@RequestParam String pfNumber) {
//    	
//        JSONObject response = new JSONObject();
//        
//
//        Integer tenure = customerService.getTenure(pfNumber);
//        System.out.println(tenure);
//        
//        JSONArray pfBaseArray = customerService.get60MonthData(pfNumber);
//        
////        JSONArray pfBaseArray = higherPensionMonthly.getJSONArray(size1-1);
////        
////		JSONArray last_60_pfBase_array = new JSONArray();
//		
//		System.out.println("length pfbasearray "+pfBaseArray.length());
//
//		
//		double avrgPfBase = 0;
//		double avrgSlab = 0;
//		double calc = 0;
//		double calc1 = 0;
////		double totatCalc1 = 0;
////		totatCalc1 = higherPensionMonthly.getDouble(size1-4);
//		
//		if(pfBaseArray.length() <= 60) {
//			System.out.println("im inside");
//		    for(int i = 0;i<pfBaseArray.length();i++) {
//		    	calc += pfBaseArray.getJSONObject(i).getDouble("pfBase");
//		    	calc1 += pfBaseArray.getJSONObject(i).getDouble("amount");
//		    }
//		    avrgPfBase = Math.round(calc/pfBaseArray.length());
//		    avrgSlab = Math.round(calc1/pfBaseArray.length());
//		}
//		
//		int tenurePlus = 0;
//		if(tenure > 20) {
//			tenurePlus = tenure + 2;
//			if(tenurePlus > 35 || tenure > 35) {
//				tenurePlus = 35;
//			}
//		}
//		
//		System.out.println("avrg pfBase : "+avrgPfBase);
//		System.out.println("avrg slab :"+avrgSlab);
//		
//		double newPension = Math.round((avrgSlab*tenurePlus)/70);
//		JSONObject yearAndMonth = new JSONObject();
//		yearAndMonth.put("amount", newPension);
//		yearAndMonth.put("pfBase", avrgPfBase);
//		yearAndMonth.put("subType","");
//		yearAndMonth.put("fiscalYear", "Average");
//		pfBaseArray.put(yearAndMonth);
//
//        response.put("newPensionArray", pfBaseArray);
//        response.put("newPension", newPension);
//
//        return Response.success(response.toString());
//    }
    
    @GetMapping("/corpusaccumulated")
    @ApplyTenantFilter
    public ResponseEntity<Object> getaccumulatedCorpus(@RequestParam String pfNumber,@RequestParam String fiscalYear) {

    	String[] monthName = {"April","May","June","July","August","September","October","Novermber","December","January","February","March"};    	
        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        int size = fiscalYears.size(); 
        String dataFromTo = fiscalYears.get(0).getFiscalYear()+" to "+fiscalYears.get(size-1).getFiscalYear();
        System.out.println("tenure period : "+dataFromTo);
        
        Optional<PfStartYearMonth> startYearMonth = customerService.getPfStartYearMonth(pfNumber);
        String first_contribution_year = startYearMonth == null ? "NA":startYearMonth.get().getFiscalYear();
        
        String first_contribution_month = startYearMonth == null ? "NA":startYearMonth.get().getSubType();
//        if(pfNumber.equals("18580")) {
//        	first_contribution_month = monthName[Integer.parseInt(first_contribution_month) -12];
//        }else {
        	first_contribution_month = monthName[Integer.parseInt(first_contribution_month) -1];
//        }
        System.out.println(first_contribution_month+" , "+first_contribution_year);
        
        Integer tenure = customerService.getTenure(pfNumber);
        System.out.println(tenure);
        
        
        System.out.println("last fiscal year" + fiscalYears.get(size-1).getFiscalYear());
        JSONArray higherPensionMonthly = customerService.getMonthlyCorpusAccumulation(pfNumber, fiscalYear,first_contribution_year);
        
        response.put("monthly",higherPensionMonthly);

        return Response.success(response.toString());
    }
    
    @GetMapping("/corpussummary")
    @ApplyTenantFilter
    public ResponseEntity<Object> getCorpusSummary(@RequestParam String pfNumber) {

        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        Optional<PfStartYearMonth> startYearMonth = customerService.getPfStartYearMonth(pfNumber);
        String first_contribution_year = startYearMonth == null ? "NA":startYearMonth.get().getFiscalYear();
        
        JSONArray higherPensionMonthly = customerService.getCorpusSummary(pfNumber,fiscalYears.get(fiscalYears.size()-1).getFiscalYear(),first_contribution_year);

        int size = higherPensionMonthly.length();

        JSONArray finalArray = new JSONArray();
        for(int i=1;i<=size-1;i+=2) {
        	
        	JSONObject object0 = higherPensionMonthly.getJSONObject(i-1);
        	JSONObject object1 = higherPensionMonthly.getJSONObject(i);
        	
        	double closingBalance = 0;
        	JSONObject object2 = new JSONObject();
        	if( i != size-1) {
        		object2 = higherPensionMonthly.getJSONObject(i+1);
        		closingBalance = object2.getDouble("corpusForTheMonth");
        	}else {
        		closingBalance = object1.getDouble("corpusForTheMonth");
        	}
        	
        	JSONObject finalObject =  new JSONObject();
        	
        	if(i == 1) {
        		finalObject.put("openingBalance",0);
        	}else {
        		finalObject.put("openingBalance",object0.get("corpusForTheMonth"));
        	}
        	
        	finalObject.put("employeeControbution", object1.get("employeeControbution"));
        	finalObject.put("companyContribution", object1.get("companyContribution"));
        	finalObject.put("eps", object1.get("eps"));
        	finalObject.put("corpus", object1.get("corpus"));
        	finalObject.put("corpusPercentage", object1.get("corpusPercentage"));
        	finalObject.put("corpusForTheMonth", object1.get("corpusForTheMonth"));
        	finalObject.put("closingBalance", closingBalance);
        	finalObject.put("interestRate", object1.get("interestRate"));
        	finalObject.put("fisccal_year", object1.get("fisccal_year"));
        	finalArray.put(finalObject);
        }
//        
        
        response.put("monthly",finalArray);
//        response.put("monthly", finalArray);
//        response.put("tenure", fiscalYears.size());
//        response.put("totalAmount", corpusValue);
        
        return Response.success(response.toString());
    }
    
    @GetMapping("/generate-mwd-file")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getMWDFile(@RequestParam String pfNumber) {

//    	String[] monthName = {"April","May","June","July","August","September","October","Novermber","December","January","February","March"};    	
        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        int size = fiscalYears.size(); 

        System.out.println("first year "+fiscalYears.get(0).getFiscalYear());
        System.out.println("last year "+fiscalYears.get(size-1).getFiscalYear());
        
        JSONArray higherPensionMonthly = customerService.getMonthlyHigherPensionForMWDSheet(pfNumber,
        		fiscalYears.get(0).getFiscalYear(),fiscalYears.get(size-1).getFiscalYear());
        
        List<String> mwdSheetLines = customerService.getMWDSheetLines(higherPensionMonthly,pfNumber);
        
        String filename = pfNumber+".txt";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (String line:mwdSheetLines) {
            out.writeBytes(line.getBytes(StandardCharsets.UTF_8));
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        
        response.put("monthly",higherPensionMonthly);
        response.put("file lines",mwdSheetLines);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
    
    @GetMapping("getMemberId")
    @ApplyTenantFilter
    public ResponseEntity<Object> getMemberIdByPfNumber(@RequestParam String pfNumber) {
    	
    	JSONObject response = new JSONObject();
    	
    	String member_id = customerService.getMemberId(pfNumber);
    	
    	if(member_id == null || member_id == "") {
    		response.put("memberId", "failed");
    		return  Response.success(response.toString());
    	}
    	
    	response.put("memberId", member_id);
    	
    	return  Response.success(response.toString());
    }
    
    @GetMapping("/create-mid")
    @ApplyTenantFilter
    public ResponseEntity<Object> setMemberId(@RequestParam String mid, @RequestParam String pfNumber){
    	
    	JSONObject response = new JSONObject();
    	
    	String member_id = customerService.getMemberId(pfNumber);
    	
    	if(member_id == null || member_id == "") {
    		System.out.println(mid+" , "+pfNumber);
    		customerService.setMemberId(mid, pfNumber);
//    		System.out.println("syso "+id);
    		response.put("status", true);
    	}
    	else {
    		response.put("status", false);
    	}
    	
    	return Response.success(response.toString());
    }
    
    @GetMapping("/populate-mwd-data")
    @ApplyTenantFilter
    public ResponseEntity<Object> getMWDData(@RequestParam String pfNumber) {

//    	String[] monthName = {"April","May","June","July","August","September","October","Novermber","December","January","February","March"};    	
        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        int size = fiscalYears.size(); 

//        System.out.println("first year "+fiscalYears.get(0).getFiscalYear());
//        System.out.println("last year "+fiscalYears.get(size-1).getFiscalYear());
        JSONArray higherPensionMonthly = customerService.getMonthlyHigherPensionForMWDSheet(pfNumber,
        		fiscalYears.get(0).getFiscalYear(),fiscalYears.get(size-1).getFiscalYear());
        
        response.put("monthly",higherPensionMonthly);
        
//        response.put("file lines",mwdSheetLines);

        return Response.success(response.toString());
    }
    
    @GetMapping("/create-mwd-excel")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getMwdExcelData(@RequestParam String pfNumber) {

//    	String[] monthName = {"April","May","June","July","August","September","October","Novermber","December","January","February","March"};    	
//        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);
    	List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MWD");
        int rowNum = 0;
        
        int size = fiscalYears.size(); 

//        System.out.println("first year "+fiscalYears.get(0).getFiscalYear());
//        System.out.println("last year "+fiscalYears.get(size-1).getFiscalYear());
        JSONArray higherPensionMonthly = customerService.getMonthlyHigherPensionForMWDExcelSheet(pfNumber,
        		fiscalYears.get(0).getFiscalYear(),fiscalYears.get(size-1).getFiscalYear());
        
        List<String> header = new ArrayList<>();
        
//        headers.add("(1) Member ID");
//        headers.add("(2) Wage Month & Year");
//        headers.add("(3) Wages on which PF contribution was paid");
//        headers.add("(4) Pension Contribution from employer 8.33%");
//        headers.add("(5) Pension Contribution from employee @ 1.16%");
//        headers.add("(6) Pension Contribution Paid by the employer earlier");
//        headers.add("(7) Pension Contribution to be paid along with interest");
//        headers.add("(8) Yearly PF Interest rate");
        
//        List<String> headers = new ArrayList<>();
        List<List<Object>> rows = new ArrayList<>();
        for (int i = 0; i < higherPensionMonthly.length(); i++) {
            JSONObject monthly = higherPensionMonthly.getJSONObject(i);
            List<Object> row = new ArrayList<>();
            for (String key : monthly.keySet()) {
                Object value = monthly.get(key);
                if (i == 0) {
                	header.add(key);
                }
                row.add(value);
            }
            rows.add(row);
        }
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Monthly");
//        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        int colNum = 0;
        for (String head : header) {
            Cell cell = row.createCell(colNum);
            cell.setCellValue(head);
            colNum++;
        }
        rowNum++;
        for (List<Object> rowData : rows) {
            row = sheet.createRow(rowNum);
            colNum = 0;
            for (Object obj : rowData) {
                Cell cell = row.createCell(colNum);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
                colNum++;
            }
            rowNum++;
        }
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String fileName = pfNumber+"_MWD.xlsx";
        try {
        	System.out.println("error here");
//            FileOutputStream outputStream = new FileOutputStream("workbook.xlsx");
//            workbook.write(outputStream);
            
            workbook.write(out);
            out.close();
//            outputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+fileName);
        headers.add("X-Suggested-Filename", fileName);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        // Close the workbook
        
        response.put("monthly",higherPensionMonthly);
        
//        return Response.success(response.toString());
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
    
    @PostMapping("/create-manual-mwd-excel")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getManualMwdExcelData(@RequestBody String data) {

    	System.out.println("Hi Saran");
        JSONObject response = new JSONObject();
        
        JSONArray higherPensionMonthly = new JSONArray(data);
        
        String str = higherPensionMonthly.getJSONObject(0).getString("memberId");
        
        List<String> mwdSheetLines = customerService.getMWDSheetLines(higherPensionMonthly);
        
        String filename = str+".txt";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (String line:mwdSheetLines) {
            out.writeBytes(line.getBytes(StandardCharsets.UTF_8));
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        
        response.put("monthly",higherPensionMonthly);
        response.put("file lines",mwdSheetLines);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/mwd-data-for-7p")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getMWDDataFor7P(@RequestParam String pfNumber) throws NoSuchFieldException, SecurityException {

//    	String[] monthName = {"April","May","June","July","August","September","October","Novermber","December","January","February","March"};    	
        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("MWD");
        int rowNum = 0;
        
        int size = fiscalYears.size(); 

//        System.out.println("first year "+fiscalYears.get(0).getFiscalYear());
//        System.out.println("last year "+fiscalYears.get(size-1).getFiscalYear());
        JSONArray higherPensionMonthly = customerService.mwdDataFor7P(pfNumber,
        		fiscalYears.get(0).getFiscalYear(),fiscalYears.get(size-1).getFiscalYear());
        
//        int i = 0;
//        int j =0;

        int count =0;
        int valueCount = 0;
        for(int j=0;j<size;j++) {
        	XSSFSheet sheet = (XSSFSheet) workbook.createSheet(fiscalYears.get(j).getFiscalYear());
    		
        	for(int i=0;i<higherPensionMonthly.length();i++) {
        		
        		JSONObject obj1 = higherPensionMonthly.getJSONObject(i);
        	
        		if(obj1.getString("fisccal_year").equals(fiscalYears.get(j).getFiscalYear()) && count == 0) {
        			System.out.println(fiscalYears.get(j).getFiscalYear());
        			
        			XSSFRow row0 = sheet.createRow(0);
                	
        			XSSFCell cell0 = row0.createCell(0);
                	cell0.setCellValue(1);
                	
                	XSSFCell cell1 = row0.createCell(1);
                	cell1.setCellValue(2);
                	
                	XSSFCell cell2 = row0.createCell(2);
                	cell2.setCellValue(3);
                	
                	XSSFCell cell3 = row0.createCell(3);
                	cell3.setCellValue(4);
                	
                	XSSFCell cell4 = row0.createCell(4);
                	cell4.setCellValue(5);
                	
                	XSSFRow row1 = sheet.createRow(1);
                	
                	XSSFCell cell5 = row1.createCell(0);
                	cell5.setCellValue("MONTH");
                	
                	XSSFCell cell6 = row1.createCell(1);
                	cell6.setCellValue("Amount of Wages, retaining allowance, if any and DA including cash value of food concession paid.");
                	
                	XSSFCell cell7 = row1.createCell(2);
                	cell7.setCellValue("Employees Share");
                	
                	XSSFCell cell8 = row1.createCell(3);
                	cell8.setCellValue("Employer Share");
                	
                	XSSFCell cell9 = row1.createCell(4);
                	cell9.setCellValue("Remarks");
                	
        			count += 1;
        		}
        		if(obj1.getString("fisccal_year").equals(fiscalYears.get(j).getFiscalYear()) && count != 0) {
//        			System.out.println(obj1.getString("yearAndMonth")+",");
//        			System.out.print(obj1.getDouble("pfBase")+",");
//        			System.out.print(obj1.getDouble("employeeControbution"));
//        			System.out.print(obj1.getDouble("companyContribution"));
//        			
        			XSSFRow row1 = sheet.createRow(valueCount+2);
                	
                	XSSFCell cell5 = row1.createCell(0);
                	cell5.setCellValue(obj1.getString("yearAndMonth"));
                	
                	XSSFCell cell6 = row1.createCell(1);
                	cell6.setCellValue(obj1.getDouble("pfBase"));
                	
                	XSSFCell cell7 = row1.createCell(2);
                	cell7.setCellValue(obj1.getDouble("employeeControbution"));
                	
                	XSSFCell cell8 = row1.createCell(3);
                	cell8.setCellValue(obj1.getDouble("companyContribution"));
                	
                	XSSFCell cell9 = row1.createCell(4);
                	cell9.setCellValue("");
                	
                	valueCount += 1;
        		}
        	}
        	count = 0;
        	valueCount = 0;
        }
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String fileName = pfNumber+"_7PS.xlsx";
        try {
        	System.out.println("error here");
//            FileOutputStream outputStream = new FileOutputStream("workbook.xlsx");
//            workbook.write(outputStream);
            
            workbook.write(out);
            out.close();
//            outputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+fileName);
        headers.add("X-Suggested-Filename", fileName);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        
        response.put("monthly",higherPensionMonthly);
        
//        response.put("file lines",mwdSheetLines);

//        return Response.success(response.toString());
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/mwd-data-for-8p")
    public ResponseEntity<byte[]> getMWDDataFor8P(@RequestParam String pfNumber) throws NoSuchFieldException, SecurityException, IOException {

//    	String[] monthName = {"April","May","June","July","August","September","October","Novermber","December","January","February","March"};    	
        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        JSONObject response = new JSONObject();
        
        File theDir = new File("uploaded_files/"+pfNumber+"_8PS");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        
//        Sheet sheet = workbook.createSheet("MWD");
        int rowNum = 0;
        
        int size = fiscalYears.size(); 

//        System.out.println("first year "+fiscalYears.get(0).getFiscalYear());
//        System.out.println("last year "+fiscalYears.get(size-1).getFiscalYear());
        JSONArray higherPensionMonthly = customerService.mwdDataFor7P(pfNumber,
        		fiscalYears.get(0).getFiscalYear(),fiscalYears.get(size-1).getFiscalYear());
        
//        int i = 0;
//        int j =0;

        int count =0;
        int valueCount = 0;
        double pfBase = 0;
        double eps = 0;
        String year = null;
        String name = customerService.getMemberName(pfNumber);
        for(int j=0;j<size;j++) {
        	Workbook workbook = new XSSFWorkbook();
        	XSSFSheet sheet = (XSSFSheet) workbook.createSheet("8PS");
    		XSSFSheet sheet1 = (XSSFSheet) workbook.createSheet("8PS MONTHLY EPS");

        	for(int i=0;i<higherPensionMonthly.length();i++) {
        		
        		JSONObject obj1 = higherPensionMonthly.getJSONObject(i);
        	
        		if(obj1.getString("fisccal_year").equals(fiscalYears.get(j).getFiscalYear()) && count == 0) {
        			year = obj1.getString("fisccal_year");
        			
        			XSSFRow row0 = sheet.createRow(0);
                	
        			XSSFCell cell0 = row0.createCell(0);
                	cell0.setCellValue(1);
                	
                	XSSFCell cell1 = row0.createCell(1);
                	cell1.setCellValue(2);
                	
                	XSSFCell cell2 = row0.createCell(2);
                	cell2.setCellValue(3);
                	
                	XSSFCell cell3 = row0.createCell(3);
                	cell3.setCellValue(4);
                	
                	XSSFCell cell4 = row0.createCell(4);
                	cell4.setCellValue(5);
                	
                	XSSFCell cell11 = row0.createCell(5);
                	cell11.setCellValue(6);
                	
                	XSSFRow row1 = sheet.createRow(1);
                	
                	XSSFCell cell5 = row1.createCell(0);
                	cell5.setCellValue("Sr.No.");
                	
                	XSSFCell cell6 = row1.createCell(1);
                	cell6.setCellValue("Account No.");
                	
                	XSSFCell cell7 = row1.createCell(2);
                	cell7.setCellValue("Name of the member");
                	
                	XSSFCell cell8 = row1.createCell(3);
                	cell8.setCellValue("Wages, retaining allowances (if any) and D.A. including cash value of food consession paid during the currency period");
                	
                	XSSFCell cell9 = row1.createCell(4);
                	cell9.setCellValue("Contribution to Pension Fund 8.33%");
                	
                	XSSFCell cell10 = row1.createCell(5);
                	cell10.setCellValue("Remarks");
                	
                	XSSFRow row2 = sheet1.createRow(0);
                	
                	XSSFCell cell12 = row2.createCell(0);
                	cell12.setCellValue("Sr.No.");
                	
                	XSSFCell cell13 = row2.createCell(1);
                	cell13.setCellValue("Month");
                	
                	XSSFCell cell14 = row2.createCell(2);
                	cell14.setCellValue("Pension Fund contribution account no. 10");
                	
        			count += 1;
        		}
        		if(obj1.getString("fisccal_year").equals(fiscalYears.get(j).getFiscalYear()) && count != 0) {
//        			System.out.println(obj1.getString("yearAndMonth")+",");
//        			System.out.print(obj1.getDouble("pfBase")+",");
//        			System.out.print(obj1.getDouble("employeeControbution"));
//        			System.out.print(obj1.getDouble("companyContribution"));
        			
        			XSSFRow row2 = sheet.createRow(2);
                	
                	XSSFCell cell12 = row2.createCell(0);
                	cell12.setCellValue(1);
                	
                	XSSFCell cell13 = row2.createCell(1);
                	cell13.setCellValue(obj1.getString("memberId"));
                	
                	XSSFCell cell14 = row2.createCell(2);
                	cell14.setCellValue(name);
                	
                	XSSFCell cell15 = row2.createCell(3);
                	pfBase += obj1.getDouble("pfBase");
                	cell15.setCellValue(pfBase);
                	
                	XSSFCell cell16 = row2.createCell(4);
                	eps += obj1.getDouble("eps");
                	cell16.setCellValue(eps);
                	
                	XSSFCell cell17 = row2.createCell(5);
                	cell17.setCellValue("");
//        			
        			
                	XSSFRow row1 = sheet1.createRow(valueCount+1);
                	
                	XSSFCell cell5 = row1.createCell(0);
                	cell5.setCellValue(valueCount+1);
                	
                	
                	XSSFCell cell6 = row1.createCell(1);
                	cell6.setCellValue(obj1.getString("yearAndMonth"));
                	
                	XSSFCell cell7 = row1.createCell(2);
                	cell7.setCellValue(obj1.getDouble("eps"));
                	
                	valueCount += 1;
        		}
        	}
        	XSSFRow row1 = sheet1.createRow(valueCount+1);
        	
        	XSSFCell cell5 = row1.createCell(0);
        	cell5.setCellValue("Total");
        	
        	
        	XSSFCell cell6 = row1.createCell(1);
        	cell6.setCellValue("Rs.");
        	
        	XSSFCell cell7 = row1.createCell(2);
        	cell7.setCellValue(eps);
        	count = 0;
        	valueCount = 0;
        	eps = 0;
        	
        	if(year != null) {
	        	ByteArrayOutputStream out = new ByteArrayOutputStream();
	            String fileName = pfNumber+"_8PS_"+year+".xlsx";
	            try {
		                FileOutputStream outputStream = new FileOutputStream(theDir+"/"+fileName);
		                workbook.write(outputStream);
		                
		                workbook.write(out);
		                out.close();
		                outputStream.close();
		                workbook.close();
	            	} catch (Exception e) {
	            		e.printStackTrace();
	            }
        	}
        }

        byte[] bytes = generateZip(theDir, pfNumber, "_8PS.zip");

        String fileName = pfNumber+"_8PS.zip";
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+fileName);
        headers.add("X-Suggested-Filename", fileName);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

        response.put("status","success");
        
        File[] files = theDir.listFiles();
        for(File file : files){
            if(file.isFile()) file.delete();
        }
        
        theDir.delete();
        
//        return Response.success(response.toString());
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
    
    @GetMapping(value = "/get7PsForm")
    public ResponseEntity<byte[]> generate7PsForm(@RequestParam String pfNumber,
                                                  @RequestParam(required = false, defaultValue = "false") String generateSinglePdf) throws Exception {
        	
    	Optional<Customer7PsDetails> customer7PsDetails = customerService.getMemberDetails(pfNumber);
    	
        String fileName = pfNumber+"_Form_7Ps.pdf";
        String[] monthArr = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        File theDir = new File("uploaded_files/"+pfNumber+"_Form_7Ps");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);

        int size = fiscalYears.size();

        String closureDate = new String();

        JSONArray higherPensionMonthly = customerService.mwdDataFor7P(pfNumber,
                fiscalYears.get(0).getFiscalYear(),fiscalYears.get(size-1).getFiscalYear());

        double pfBaseTotal = 0;
        double epsTotal = 0;
        String year = null;

        String leavingYear = customer7PsDetails.get().getLeavingFiscalYear();
        for(int j=0;j<size;j++) {

            List<Form7PsContribution> form7PsContribution = new ArrayList<>();

            List<Form7PsContribution> specailForm7PsContribution = new ArrayList<>();

            Form7Ps form7Ps = new Form7Ps();

            int count = 0;
            for(int i=0;i<higherPensionMonthly.length();i++) {

                JSONObject obj1 = higherPensionMonthly.getJSONObject(i);

                if(obj1.getString("fisccal_year").equals(fiscalYears.get(j).getFiscalYear())) {
                    year = obj1.getString("fisccal_year");

                    Form7PsContribution obj = new Form7PsContribution();
                    String[] date = obj1.getString("yearAndMonth").split("/");
                    String dateString = monthArr[Integer.parseInt(date[0])-1]+"-"+date[1];

                    if(Integer.valueOf(date[0]) == 3) {
                        closureDate = "25-"+monthArr[3]+"-"+date[1];
                    }

                    obj.setMonthAndYear(dateString);
                    obj.setPensionFund(String.valueOf(obj1.getDouble("eps")));
//            			obj.setMonthAndYear(obj1.getString("yearAndMonth"));
//                    	obj.setPensionFund(String.valueOf(obj1.getDouble("actualPfBase")));
                    obj.setPfBase(obj1.getString("pfBase"));

                    if(!(customer7PsDetails.get().getDateOfLeaving() == null)) {
                        String[] dateLeave = customer7PsDetails.get().getDateOfLeaving().split("/");

                        if(leavingYear.equalsIgnoreCase(fiscalYears.get(j).getFiscalYear()) && Integer.parseInt(date[0]) == Integer.parseInt(dateLeave[1])) {

                            form7Ps.setReasonOfLeaving(customer7PsDetails.get().getReasonOfLeaving());
                            form7Ps.setDateOfLeaving(customer7PsDetails.get().getDateOfLeaving());
                            form7Ps.setRemarks("(a) Date Of Leaving Service : "+form7Ps.getDateOfLeaving()+" (b) Reason Of Leaving Service : "+form7Ps.getReasonOfLeaving());
                            obj.setRemarks(form7Ps.getRemarks());
                        }
                    }


//            	      	if(leavingYear.equalsIgnoreCase(fiscalYears.get(j).getFiscalYear()) && Integer.parseInt(date[0]) == Integer.parseInt(dateLeave[1])) {
//            	      		
//                    		form7Ps.setReasonOfLeaving(customer7PsDetails.get().getReasonOfLeaving());
//                    		form7Ps.setDateOfLeaving(customer7PsDetails.get().getDateOfLeaving());
//                    		form7Ps.setRemarks("(a) Date Of Leaving Service : "+form7Ps.getDateOfLeaving()+" (b) Reason Of Leaving Service : "+form7Ps.getReasonOfLeaving());
//                    		obj.setRemarks(form7Ps.getRemarks());
//            	      	}


                    pfBaseTotal += new Double(obj1.getString("pfBase"));
                    epsTotal += new Double(obj1.getDouble("eps"));

                    if(year.equals("FY2009-2010")) {
                        if(Integer.parseInt(date[0]) < 4) {
                            specailForm7PsContribution.add(obj);
                        }else if(Integer.parseInt(date[0]) > 3){
                            form7PsContribution.add(obj);
                        }
                    }else if(year.equals("FY1997-1998")) {
        	      		System.out.println("FY1997-1998");
        	      		if(Integer.parseInt(date[0]) < 4) {
        	      			specailForm7PsContribution.add(obj);
        	      		}else if(Integer.parseInt(date[0]) > 3){
        	      			form7PsContribution.add(obj);
        	      		}
        	      	}else{
                        form7PsContribution.add(obj);
                    }

//            	      	form7PsContribution.add(obj);
                    count += 1;
                }
            }

            if(form7PsContribution.size() > 0) {
                for(Form7PsContribution e :specailForm7PsContribution) {
                    form7PsContribution.add(e);
                }
            }


            form7Ps.setFiscalYear(year != null ? year.substring(2):year);
            form7Ps.setName(customer7PsDetails.get().getName());
            form7Ps.setFatherName(customer7PsDetails.get().getFatherName());
            form7Ps.setAccountNo(customer7PsDetails.get().getMemberId());
//        		form7Ps.setNameAndAdddress(customer7PsDetails.get().getNameAndAddresss());
            form7Ps.setNameAndAdddress(customer7PsDetails.get().getNameAndAddresss());
            form7Ps.setForm7PsContribution(form7PsContribution);

            if(closureDate == null) {
                if(!(customer7PsDetails.get().getDateOfLeaving() == null)) {
                    String[] date = customer7PsDetails.get().getLeavingFiscalYear().split("-");
                    form7Ps.setClosureDate("25/04/"+date[1]);
                }
            }else {
                form7Ps.setClosureDate(closureDate);
            }

            form7Ps.setPfBaseTotal(String.valueOf(pfBaseTotal));
            form7Ps.setEpsTotal(String.valueOf(epsTotal));

            closureDate = null;

            epsTotal = 0;
            pfBaseTotal = 0;

            if(year != null) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                try {
                    ByteArrayOutputStream generate = generateForm7Ps.generate(form7Ps);
                        FileOutputStream outputStream = new FileOutputStream(theDir+"/"+pfNumber+"_Form_7Ps_"+year+".pdf");
                        generate.writeTo(outputStream);

                        generate.writeTo(out);
                        out.close();
                        outputStream.close();
                        generate.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                }
            }

        }

        byte[] bytes = null;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        if(generateSinglePdf.equalsIgnoreCase("true")){
            bytes = generatePdf(theDir, pfNumber, "_Form_7Ps.pdf");
            headers.add("Content-Disposition", "attachment; filename="+pfNumber+"_Form_7Ps.pdf");
            headers.add("X-Suggested-Filename", pfNumber+"_Form_7Ps.pdf");
        }else {
            bytes = generateZip(theDir, pfNumber, "_Form_7Ps.zip");
            headers.add("Content-Disposition", "attachment; filename="+pfNumber+"_Form_7Ps.zip");
            headers.add("X-Suggested-Filename", pfNumber+"_Form_7Ps.zip");
        }

          headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

          File[] files = theDir.listFiles();
          for(File file : files){
              if(file.isFile()) file.delete();
          }

          theDir.delete();

          return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    private byte[] generateZip(File theDir, String pfNumber, String x) throws IOException {
        List<String> filesListInDir = new ArrayList<>();

        try {
              filesListInDir = customerService.populateFilesList(theDir);
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }

        // called zip directory method
        customerService.zipDirectory(theDir, theDir +".zip", filesListInDir);

        Path targetLocation = fileStorageService.resolvePath(pfNumber + x);

        return Files.readAllBytes(targetLocation);
    }

    private byte[] generatePdf(File theDir, String pfNumber, String x) throws IOException {

        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        File[] files = theDir.listFiles();

        pdfMergerUtility.setDestinationFileName(theDir +".pdf");

        for (File file:files) {
            pdfMergerUtility.addSource(file);
        }

        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

        Path targetLocation = fileStorageService.resolvePath(pfNumber + x);

        return Files.readAllBytes(targetLocation);
    }


    @GetMapping(value = "/get8PsForm")
    public ResponseEntity<byte[]> generate8PsForm(@RequestParam String pfNumber,
                                                  @RequestParam(required = false, defaultValue = "false") String generateSinglePdf) throws Exception {
    
    	Optional<Customer7PsDetails> customer7PsDetails = customerService.getMemberDetails(pfNumber);
    	
    	String[] lastDayArr = {"31","28","31","30","31","30","31","31","30","31","30","31"};
    	String[] monthArr = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    	
//    	int index = 10;
    	String newMemberId = customer7PsDetails.get().getMemberId();
    	
    	File theDir = new File("uploaded_files/"+pfNumber+"_Form_8Ps");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        
        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);
    	
        int size = fiscalYears.size();
        
        JSONArray higherPensionMonthly = customerService.mwdDataFor7P(pfNumber,
        		fiscalYears.get(0).getFiscalYear(),fiscalYears.get(size-1).getFiscalYear());
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
    	
    	String fileName = pfNumber+"_Form_8Ps";
    	String year = null;
        
        double pfBaseTotal = 0;
    	double epsTotal = 0;
    	String contributionFrom = new String();
    	String contributionTo = new String();
    	String closureDate = new String();
    	for(int j=0;j<size;j++) {
    		
    		List<Form7PsContribution> form7PsContribution = new ArrayList<>();
    		
    		List<Form7PsContribution> specailForm7PsContribution = new ArrayList<>();
    		
    		List<Form7PsContribution> finalForm7PsContribution = new ArrayList<>();
    		
    		Form8Ps form8Ps = new Form8Ps();
    		
    		int count = 0;
    		for(int i=0;i<higherPensionMonthly.length();i++) {
    			
    			JSONObject obj1 = higherPensionMonthly.getJSONObject(i);

    			if(obj1.getString("fisccal_year").equals(fiscalYears.get(j).getFiscalYear())) {
        			year = obj1.getString("fisccal_year");
        			
        			
        				contributionFrom = "01/04/"+year.substring(2,6);
        				contributionTo = "31/03/"+year.substring(7);
        			
        			Form7PsContribution obj = new Form7PsContribution();
        			
        			String[] date = obj1.getString("yearAndMonth").split("/");
        			String dateString = monthArr[Integer.parseInt(date[0])-1]+"-"+date[1];
        			
//        			contributionTo = lastDayArr[Integer.parseInt(date[0])-1]+"/"+obj1.getString("yearAndMonth");
    				
//    				if(Integer.parseInt(date[0])-1 == 1 && Integer.parseInt(date[1])/4 == 0) {
//    					contributionTo = "29"+"/"+obj1.getString("yearAndMonth");
//    				}
    				
    				pfBaseTotal += new Double(obj1.getString("pfBase"));
        	      	epsTotal += new Double(obj1.getDouble("eps"));
    				
        	      	obj.setMonthAndYear(dateString);
        	      	obj.setPensionFund(String.valueOf(obj1.getDouble("eps")));
        	      	
        	      	count += 1;
        	      	obj.setSrNo(String.valueOf(count));
        	      	
        	      	if(year.equals("FY2009-2010")) {
        	      		if(Integer.parseInt(date[0]) < 4) {
        	      			specailForm7PsContribution.add(obj);
        	      		}else if(Integer.parseInt(date[0]) > 3){
        	      			form7PsContribution.add(obj);
        	      		}
        	      	}else if(year.equals("FY1997-1998")) {
        	      		System.out.println("FY1997-1998");
        	      		if(Integer.parseInt(date[0]) < 4) {
        	      			specailForm7PsContribution.add(obj);
        	      		}else if(Integer.parseInt(date[0]) > 3){
        	      			form7PsContribution.add(obj);
        	      		}
        	      	}
        	      	else {
        	      		form7PsContribution.add(obj);
        	      	}
        	      	
//        	      	form7PsContribution.add(obj);
        	      	
        	      	String[] date1 = fiscalYears.get(j).getFiscalYear().split("-");
        	      	closureDate = "April-"+date1[1];
        	      	
    			}
    		}
    		count = 0;
    		
    		if(form7PsContribution.size() > 0) {
    			for(Form7PsContribution e :specailForm7PsContribution) {
        			form7PsContribution.add(e);
        		}
    		}
    		
    		int sr =0;
    		for(Form7PsContribution e :form7PsContribution) {
    			sr+=1;
    			e.setSrNo(String.valueOf(sr));
    			finalForm7PsContribution.add(e);
    		}
    		
    		form8Ps.setAcctNo(newMemberId);
    		form8Ps.setNameAndEstablishment(customer7PsDetails.get().getNameAndAddresss());
        	form8Ps.setCodeNumber(customer7PsDetails.get().getCodeNumber());
        	form8Ps.setName(customer7PsDetails.get().getName());
        	form8Ps.setPfBase(String.valueOf(pfBaseTotal));
        	form8Ps.setPension(String.valueOf(epsTotal));
        	form8Ps.setCodeNumber(customer7PsDetails.get().getCodeNumber());
        	form8Ps.setSrNo(1);
//        	form8Ps.setForm7PsContribution(form7PsContribution);
        	form8Ps.setForm7PsContribution(finalForm7PsContribution);
        	form8Ps.setContributionFrom(contributionFrom);
        	form8Ps.setContributioTo(contributionTo);

        	form8Ps.setClosureDate(closureDate);

        	contributionFrom = null;
	      	contributionTo = null;
    		
    		pfBaseTotal = 0;
	      	epsTotal = 0;
	      	
	      	if(year != null) {
		      	try {
		        	ByteArrayOutputStream generate = generateForm8Ps.generate(form8Ps);
		                FileOutputStream outputStream = new FileOutputStream(theDir+"/"+fileName+"_"+year+".pdf");
		                generate.writeTo(outputStream);
		                
		                generate.writeTo(out);
		                out.close();
		                outputStream.close();
		                generate.close();
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        }
	      	}
    	}

        byte[] bytes = null;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();


        if(generateSinglePdf.equalsIgnoreCase("true")){
            bytes = generatePdf(theDir, pfNumber, "_Form_8Ps.pdf");
            headers.add("Content-Disposition", "attachment; filename="+pfNumber+"_Form_8Ps.pdf");
            headers.add("X-Suggested-Filename", pfNumber+"_Form_8Ps.pdf");
        }else {
            bytes = generateZip(theDir, pfNumber, "_Form_8Ps.zip");
            headers.add("Content-Disposition", "attachment; filename="+pfNumber+"_Form_8Ps.zip");
            headers.add("X-Suggested-Filename", pfNumber+"_Form_8Ps.zip");
        }

        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        
        File[] files = theDir.listFiles();
        for(File file : files){
            if(file.isFile()) file.delete();
        }
        
        theDir.delete();
        
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
    
    @GetMapping("getMember-details")
    @ApplyTenantFilter
    public ResponseEntity<Object> getMemberDetailsByPfNumber(@RequestParam String pfNumber) {
    	
    	JSONObject response = new JSONObject();
    	
    	Optional<Customer7PsDetails> customer7PsDetails = customerService.getMemberDetails(pfNumber);
    	
    	if(customer7PsDetails.isEmpty()) {
    		response.put("memberId", "failed");
    		return  Response.success(response.toString());
    	}
    	
    	response.put("memberId", customer7PsDetails.get().getMemberId());
    	response.put("name", customer7PsDetails.get().getName());
    	response.put("fatherName", customer7PsDetails.get().getFatherName());
    	response.put("establishment", customer7PsDetails.get().getEstablishmentName());
    	response.put("code", customer7PsDetails.get().getCodeNumber());
    	response.put("address", customer7PsDetails.get().getNameAndAddresss());
    	response.put("pfNumber", customer7PsDetails.get().getPfNumber());
    	
    	return  Response.success(response.toString());
    }
    
    @PutMapping("/save-member-details")
    @ApplyTenantFilter
    public ResponseEntity<Object> setMemberDetails(@RequestBody String memberDetails){
    	
    	JSONObject response = new JSONObject();
    	
    	JSONObject memberDetailsObject = new JSONObject(memberDetails);
    	
    	String member_id = memberDetailsObject.getString("memberId");
    	String name = memberDetailsObject.getString("name");
    	String fatherName = memberDetailsObject.getString("fatherName");
//    	String retirementDate = memberDetailsObject.getString("retirementDate");
    	String retirementDate = memberDetailsObject.isNull("retirementDate") ? null : memberDetailsObject.getString("retirementDate");
    	String leavingFiscalYear = memberDetailsObject.isNull("leavingFiscalYear") ? null : memberDetailsObject.getString("leavingFiscalYear");
    	String reasonOfLeaving = memberDetailsObject.isNull("retirementType") ? null : memberDetailsObject.getString("retirementType");
    	String pfNumber = memberDetailsObject.getString("pfNumber");
    	String establishmentName = memberDetailsObject.getString("establishmentName");
    	String establishmentCode = memberDetailsObject.getString("establishmentCode");
    	
    	if(retirementDate != null) {
    		String[] date = retirementDate.split("-");
        	
        	retirementDate = date[2]+"/"+date[1]+"/"+date[0];
    	}
    	
    	if(pfNumber != null || pfNumber != "") {
    		System.out.println(pfNumber+","+member_id+","+name+","+fatherName+","+retirementDate+","+leavingFiscalYear+","+reasonOfLeaving);
    		customerService.setMemberDetails(name, fatherName, pfNumber, member_id, leavingFiscalYear, reasonOfLeaving, retirementDate, establishmentName, establishmentCode);
    		
    		response.put("status","true");
    	}else {
    		response.put("status","false");
    	}

    	return Response.success(response.toString());
    }

    @GetMapping("/create-corpus-report")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getEmployeeDetailsForCorpusReport(@RequestParam String establishmentCode) throws IOException {
// add to customerserive
        List<MibsCustomerDetails> mibsCustomerDetailsList = customerService.getMibsCustomerDetailsList(establishmentCode);

        JSONArray dataObjectArray = new JSONArray();

        String[] monthName = {"April","May","June","July","August","September","October","November","December","January","February","March"};

        System.out.println("length "+mibsCustomerDetailsList.size());

        int z =0;
        for(MibsCustomerDetails mibsCustomerDetails: mibsCustomerDetailsList){
            System.out.println("employee count "+ z++);
            List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(mibsCustomerDetails.getPfNumber());
            int size = fiscalYears.size();
            if(fiscalYears.size() > 0) {

                JSONObject response = new JSONObject();
//                String dataFromTo = fiscalYears.get(0).getFiscalYear() + " to " + fiscalYears.get(size - 1).getFiscalYear();

                Optional<PfStartYearMonth> startYearMonth = customerService.getPfStartYearMonth(mibsCustomerDetails.getPfNumber());

                String first_contribution_year = startYearMonth == null ? "NA" : startYearMonth.get().getFiscalYear();

                String first_contribution_month = startYearMonth == null ? "NA" : startYearMonth.get().getSubType();

                first_contribution_month = monthName[Integer.parseInt(first_contribution_month) - 1];

                JSONArray higherPensionMonthly = customerService.getMonthlyHigherPension(mibsCustomerDetails.getPfNumber(), fiscalYears.get(size - 1).getFiscalYear(), first_contribution_year);

                int size1 = higherPensionMonthly.length();
                double corpusPayback = Math.round(higherPensionMonthly.getJSONObject(size1 - 2).getDouble("totalAmount"));
                double adminInterest = Math.round(higherPensionMonthly.getJSONObject(size1 - 2).getDouble("admin_interest"));
                double corpusValue = Math.round(higherPensionMonthly.getJSONObject(size1 - 2).getDouble("corpusForTheMonth"));

                JSONArray pfBaseArray = higherPensionMonthly.getJSONArray(size1 - 1);

                double tenure = pfBaseArray.length() / 12;

                double avrgPfBase = 0;
                double avrgSlab = 0;
                double calc = 0;
                double calc1 = 0;

                if (pfBaseArray.length() > 60) {
                    int i = 0;
                    for (i = pfBaseArray.length() - 60; i < pfBaseArray.length(); i++) {
                        calc += pfBaseArray.getJSONObject(i).getDouble("pfBase");
                        calc1 += pfBaseArray.getJSONObject(i).getDouble("amount");
                    }
                    avrgPfBase = Math.round(calc / 60);
                    avrgSlab = Math.round(calc1 / 60);
                } else if (pfBaseArray.length() <= 60) {
                    for (int i = 0; i < pfBaseArray.length(); i++) {
                        calc += pfBaseArray.getJSONObject(i).getDouble("pfBase");
                        calc1 += pfBaseArray.getJSONObject(i).getDouble("amount");
                    }
                    avrgPfBase = Math.round(calc / pfBaseArray.length());
                    avrgSlab = Math.round(calc1 / pfBaseArray.length());
                }

                double tenurePlus = 0;
                if (tenure > 20) {
                    tenurePlus = tenure + 2;
                    if (tenurePlus > 35 || tenure > 35) {
                        tenurePlus = 35;
                    }
                } else {
                    tenurePlus = tenure;
                }

                double newPension = (avrgPfBase * tenurePlus) / 70;

                double epsContributionActual = Math.round(((tenurePlus * avrgSlab * 12) / 100) * 8.33);
                double epsPfJudgement = Math.round(((corpusValue + epsContributionActual) / 100) * 35);

                response.put("name", mibsCustomerDetails.getName());
                response.put("memberId", mibsCustomerDetails.getMemberId());
                response.put("name", mibsCustomerDetails.getName());
                response.put("pfNumber", mibsCustomerDetails.getPfNumber());
                response.put("establishmentCode", mibsCustomerDetails.getCodeNumber());
                response.put("from", fiscalYears.get(0).getFiscalYear());
                response.put("to",fiscalYears.get(size - 1).getFiscalYear());
                response.put("contributionYear", first_contribution_year);
                response.put("contributionMonth", first_contribution_month);
                response.put("tenure", tenure);
                response.put("corpusValue", corpusValue);
                response.put("epsContributionActual", epsContributionActual);
                response.put("epsPfJudgement", epsPfJudgement);
                response.put("corpusPayBack", corpusPayback);
                response.put("adminInterest", adminInterest);
                response.put("newPension", Math.round(newPension));

                dataObjectArray.put(response);
            }
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Corpus Report");

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            XSSFCellStyle headerStyle = (XSSFCellStyle) customerService.createHeaderCellStyle(workbook);
            XSSFCellStyle dataCellStyle = (XSSFCellStyle) customerService.createDataCellStyle(workbook);
            XSSFCellStyle centerTextStyle = style;
            XSSFCellStyle wrapTextStyle = (XSSFCellStyle) customerService.createWrapTextStyle(workbook);
            XSSFCellStyle borderStyle = (XSSFCellStyle) customerService.createSolidBordersStyle(workbook);

            CellRangeAddress RowCellRange1 = new CellRangeAddress(0, 1, 0, 0);
            CellRangeAddress RowCellRange2 = new CellRangeAddress(0, 1, 1, 1);
            CellRangeAddress RowCellRange3 = new CellRangeAddress(0, 1, 2, 2);
            CellRangeAddress RowCellRange4 = new CellRangeAddress(0, 1, 3, 3);
            CellRangeAddress RowCellRange5 = new CellRangeAddress(0, 1, 4, 4);
//            CellRangeAddress RowCellRange6 = new CellRangeAddress(0, 1, 5, 5);

            CellRangeAddress RowCellRange6 = new CellRangeAddress(0, 0, 5, 6);
            CellRangeAddress RowCellRange7 = new CellRangeAddress(0, 0, 7, 9);

            CellRangeAddress RowCellRange8 = new CellRangeAddress(0, 1, 10, 10);
            CellRangeAddress RowCellRange9 = new CellRangeAddress(0, 1, 11, 11);
            CellRangeAddress RowCellRange10 = new CellRangeAddress(0, 1, 12, 12);

            String[] headers = {"Sr. No.","Member ID","Name","PF Number","Establishment Code","Service","Tenure (Yrs) : 26 + 2 = 28",
            "Admin Charges: 1.16%","Total Payback Corpus","Total Payback : Admin charges + Corpus"};

            int mainRowIndex = 0;
            XSSFRow headerRow = sheet.createRow(mainRowIndex++);
            for (int colIndex = 0; colIndex < headers.length; colIndex++) {

                int index = colIndex;
                if(colIndex == 6){
                    index = colIndex+1;
                }else if(colIndex >= 7){
                    index = colIndex +3;
                }

                Cell cell = headerRow.createCell(index);
                cell.setCellValue(headers[colIndex]);
                cell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(index);

                switch(colIndex) {
                    case 0:
                        sheet.addMergedRegion(RowCellRange1);
                        break;
                    case 1:
                        sheet.addMergedRegion(RowCellRange2);
                        break;
                    case 2:
                        sheet.addMergedRegion(RowCellRange3);
                        break;
                    case 3:
                        sheet.addMergedRegion(RowCellRange4);
                        break;
                    case 4:
                        sheet.addMergedRegion(RowCellRange5);
                        break;
                    case 5:
                        sheet.addMergedRegion(RowCellRange6);
                        break;
                    case 6:
                        sheet.addMergedRegion(RowCellRange7);
                        break;
                    case 7:
                        sheet.addMergedRegion(RowCellRange8);
                        break;
                    case 8:
                        sheet.addMergedRegion(RowCellRange9);
                        break;
                    case 9:
                        sheet.addMergedRegion(RowCellRange10);
                        break;
                }

            }

            String[] subHeaders = {"start date","End Date","Service years","additional Year (>20yr)","Total Tenure"};

            XSSFRow subHeaderRow = sheet.createRow(mainRowIndex++);
            for (int colIndex = 5; colIndex <= subHeaders.length+4; colIndex++) {

                Cell cell = subHeaderRow.createCell(colIndex);
                cell.setCellValue(subHeaders[colIndex-5]);

                cell.setCellStyle(borderStyle);
                sheet.autoSizeColumn(colIndex);
                cell.setCellStyle(headerStyle);
            }

//            Example data
            for(int i=0; i< dataObjectArray.length(); i++){
                XSSFRow dataRow = sheet.createRow(mainRowIndex++);

                Cell cell0 = dataRow.createCell(0);
                Cell cell1 = dataRow.createCell(1);
                Cell cell2 = dataRow.createCell(2);
                Cell cell3 = dataRow.createCell(3);
                Cell cell4 = dataRow.createCell(4);
                Cell cell5 = dataRow.createCell(5);
                Cell cell6 = dataRow.createCell(6);
                Cell cell7 = dataRow.createCell(7);
                Cell cell8 = dataRow.createCell(8);
                Cell cell9 = dataRow.createCell(9);
                Cell cell10 = dataRow.createCell(10);
                Cell cell11 = dataRow.createCell(11);
                Cell cell12 = dataRow.createCell(12);

                JSONObject obj = (JSONObject) dataObjectArray.get(i);

                cell0.setCellValue(i+1);
                cell1.setCellValue(obj.getString("memberId"));
                cell2.setCellValue(obj.getString("name"));
                cell3.setCellValue(obj.getString("pfNumber"));
                cell4.setCellValue(obj.getString("establishmentCode"));
                cell5.setCellValue(obj.getString("from"));
                cell6.setCellValue(obj.getString("to"));
                cell7.setCellValue(obj.getInt("tenure"));
                cell8.setCellValue(obj.getInt("tenure") > 20 ? 2 : 0);
                cell9.setCellValue(obj.getInt("tenure") > 20 ? obj.getInt("tenure")+2 : obj.getInt("tenure"));
                cell10.setCellValue(obj.getInt("adminInterest"));
                cell11.setCellValue(obj.getInt("corpusPayBack"));
                cell12.setCellValue(obj.getInt("corpusPayBack")+obj.getInt("adminInterest"));

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(4);
                sheet.autoSizeColumn(5);
                sheet.autoSizeColumn(6);
                sheet.autoSizeColumn(7);
                sheet.autoSizeColumn(8);
                sheet.autoSizeColumn(9);

            }

            customerService.setBorderStyleSolidThin(sheet, RowCellRange1);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange1);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange2);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange3);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange4);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange5);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange6);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange7);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange8);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange9);
            customerService.setBorderStyleSolidThin(sheet, RowCellRange10);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                workbook.write(out);
                out.close();
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            MultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();

            responseHeaders.add("Content-Disposition", "attachment; filename=" + "Corpus_report.xlsx");
            responseHeaders.add("X-Suggested-Filename", "Corpus_Report.xlsx");
            responseHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

            return new ResponseEntity<>(out.toByteArray(), responseHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("/get-establishment-code")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEstablishmentCode(){


        List<String> establishmentCodes = customerService.getEstablishmentCodeList();

        JSONArray responseArray = new JSONArray();

        int id =0;
        for(String establishmentCode: establishmentCodes){
            JSONObject response = new JSONObject();

            response.put("id",id++);
            response.put("code", establishmentCode);

            responseArray.put(response);
        }

        return Response.success(responseArray.toString());
    }

    @PostMapping("/report")
    @ApplyTenantFilter
    public ResponseEntity<Object> generateCorpusReport(@RequestBody LaunchJobDTO jobDTO){

        System.out.println(jobDTO.getParams());
        Job job = jobLaunchService.launchReportJob("corpus_report_jop", jobDTO.getParams());
        return Response.success(job);
    }

    @GetMapping("/download")
    @ApplyTenantFilter
    public void downloadReport(@RequestParam("filePath") String filePath,
                             HttpServletResponse response) {
        try {
            Path targetLocation = this.fileStorageLocation.resolve(filePath);
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filePath + "\"");
            FileInputStream fileInputStream = new FileInputStream(targetLocation.toFile());

            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1048576];
            int i;
            while ((i = fileInputStream.read(b)) != -1) {
                out.write(b, 0, i);
            }
            fileInputStream.close();
            out.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @GetMapping("/jobs")
    @ApplyTenantFilter
    public ResponseEntity<Object> getJobs(){

        List<Job> jobs = jobLaunchService.getJobs("corpus_report_jop");

        List<JobDTO> jobDTOList = formatJobList(jobs);

        return Response.success(jobDTOList);
    }

    @GetMapping("/jobs/details")
    @ApplyTenantFilter
    public ResponseEntity<Object> getJob(@RequestParam("entityId")String entityId){

        Job job = jobLaunchService.getJob(entityId);

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        JobDTO jobDTO = new JobDTO(job.getEntityId(), job.getCreatedAt(), job.getUpdatedAt(),
                Instant.parse(jobDetailsJson.getString("startedAt")),
                jobDetailsJson.has("completedAt") ? Instant.parse(jobDetailsJson.getString("completedAt")) : null,
                job.getName(), jobDetailsJson.getString("status"),
                jobDetailsJson.has("duration") ? jobDetailsJson.getLong("duration") : 0);

        jobDTO.setFileName(jobDetailsJson.has("fileName") ? jobDetailsJson.getString("fileName") : "");

        return Response.success(jobDTO);
    }

    private List<JobDTO> formatJobList(List<Job> jobs) {

        return jobs.stream().map(job -> {

            JSONObject jobDetailsJson = job.getJobDetailsJson();

            JobDTO jobDTO = new JobDTO(job.getEntityId(), job.getCreatedAt(), job.getUpdatedAt(),
                    Instant.parse(jobDetailsJson.getString("startedAt")),
                    jobDetailsJson.has("completedAt") ? Instant.parse(jobDetailsJson.getString("completedAt")) : null,
                    job.getName(), jobDetailsJson.getString("status"),
                    jobDetailsJson.has("duration") ? jobDetailsJson.getLong("duration") : 0);

            jobDTO.setFileName(jobDetailsJson.has("fileName") ? jobDetailsJson.getString("fileName") : null);

            return jobDTO;

        }).collect(Collectors.toList());
    }

}
