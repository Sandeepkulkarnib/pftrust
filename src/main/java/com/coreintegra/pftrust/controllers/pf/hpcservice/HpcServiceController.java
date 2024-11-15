package com.coreintegra.pftrust.controllers.pf.hpcservice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.pdf.Form3A;
import com.coreintegra.pftrust.dtos.pdf.Form3AContribution;
import com.coreintegra.pftrust.dtos.pdf.Form7Ps;
import com.coreintegra.pftrust.dtos.pdf.Form7PsContribution;
import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeeMaster;
import com.coreintegra.pftrust.entities.pf.hpc.HpcEmployeePfbase;
import com.coreintegra.pftrust.entities.pf.hpc.HpcInterestRates;
import com.coreintegra.pftrust.entities.pf.hpc.HpcPfslab;
import com.coreintegra.pftrust.projections.Customer7PsDetails;
import com.coreintegra.pftrust.projections.PfUserFiscalYearProjection;
import com.coreintegra.pftrust.services.pf.customerservice.CustomerService;
import com.coreintegra.pftrust.services.pf.hpc.HpcService;
import com.coreintegra.pftrust.services.pf.pdf.GenerateForm3A;
import com.coreintegra.pftrust.services.storage.FileStorageService;
import com.coreintegra.pftrust.util.Response;

@RestController
@RequestMapping(HpcServiceEndpoints.HPC_SERVICE_ENDPOINT)
public class HpcServiceController {

	@Autowired
	HpcService hpcService;
	
	@Autowired
    private GenerateForm3A generateForm3A;
	
	private final CustomerService customerService;
	private final FileStorageService fileStorageService;
	
	public HpcServiceController(CustomerService customerService,FileStorageService fileStorageService) {
		this.customerService = customerService;
		this.fileStorageService = fileStorageService;
	}

	@ApplyTenantFilter
	@GetMapping("/getEmployee")
    public ResponseEntity<Object> getFinancialYear(@RequestParam String memberId) {
        
        JSONObject response = new JSONObject();
        
        Optional<HpcEmployeeMaster> employee = hpcService.getEmployee(memberId, true);   
        
        response.put("memberId", employee.get().getMemberId());
        response.put("acknowledgementNo", employee.get().getAcknowledgementNo());
        response.put("employeeId", employee.get().getEmployeeId());
        response.put("uanNo", employee.get().getUanNo());
        response.put("category", employee.get().getCategory());
        response.put("ppNo", employee.get().getPpoNo());
        response.put("name", employee.get().getName());
        response.put("dob", employee.get().getDob());
        response.put("doj", employee.get().getDoj());
        response.put("dol", employee.get().getDol());
        response.put("submittedOn", employee.get().getSubmittedOn());
        
        return Response.success(response.toString());
    }
	
	@ApplyTenantFilter
	@GetMapping("/getMwdData")
    public ResponseEntity<Object> getMwdFile(@RequestParam String memberId) {
		
		JSONObject response = new JSONObject();
		
		JSONArray wageInterestAndSlabData = new JSONArray();
		
		JSONArray mwdData = new JSONArray();
        
        Optional<HpcEmployeeMaster> employee = hpcService.getEmployee(memberId, true);   
        List<HpcEmployeePfbase> pfbaseList = hpcService.getPfbaseList(employee.get(), true);

        wageInterestAndSlabData = hpcService.getWageInterestAndSlabData(employee.get(), pfbaseList);
        mwdData = hpcService.getCreateMwdData(wageInterestAndSlabData);
        
        response.put("pfbaseList", mwdData);
        
        return Response.success(response.toString());
	}
	
	@ApplyTenantFilter
	@GetMapping("/getMwdExcelFile")
    public ResponseEntity<byte[]> getMwdExcelFile(@RequestParam String memberId) {
		
		JSONObject response = new JSONObject();
		
		JSONArray wageInterestAndSlabData = new JSONArray();
		
		JSONArray mwdData = new JSONArray();
        
        Optional<HpcEmployeeMaster> employee = hpcService.getEmployee(memberId, true);   
        List<HpcEmployeePfbase> pfbaseList = hpcService.getPfbaseList(employee.get(), true);

        wageInterestAndSlabData = hpcService.getWageInterestAndSlabData(employee.get(), pfbaseList);
        mwdData = hpcService.getCreateMwdData(wageInterestAndSlabData);
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MWD");
        int rowNum = 0;
        
        List<String> header = new ArrayList<>();
        
        List<List<Object>> rows = new ArrayList<>();
        for (int i = 0; i < mwdData.length(); i++) {
            JSONObject monthly = mwdData.getJSONObject(i);
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
        
        String fileName = employee.get().getMemberId()+"_MWD.xlsx";
        try {
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+fileName);
        headers.add("X-Suggested-Filename", fileName);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        // Close the workbook
//        response.put("pfbaseList", mwdData);
        
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
	}
	
	@ApplyTenantFilter
	@GetMapping("/getMwdTextFile")
    public ResponseEntity<byte[]> getMwdtextFile(@RequestParam String memberId) {
		
		JSONObject response = new JSONObject();
		
		JSONArray wageInterestAndSlabData = new JSONArray();
		
		JSONArray mwdData = new JSONArray();
        
        Optional<HpcEmployeeMaster> employee = hpcService.getEmployee(memberId, true);   
        List<HpcEmployeePfbase> pfbaseList = hpcService.getPfbaseList(employee.get(), true);

        wageInterestAndSlabData = hpcService.getWageInterestAndSlabData(employee.get(), pfbaseList);
        mwdData = hpcService.getCreateMwdData(wageInterestAndSlabData);
        
//        response.put("pfbaseList", mwdData);
//        
//        return Response.success(response.toString());
        List<String> mwdSheetLines = hpcService.getMWDSheetLines(mwdData,employee.get().getMemberId());
        
        String filename = employee.get().getMemberId()+".txt";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (String line:mwdSheetLines) {
            out.writeBytes(line.getBytes(StandardCharsets.UTF_8));
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+filename);
        headers.add("X-Suggested-Filename", filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        
//        response.put("monthly",higherPensionMonthly);
//        response.put("file lines",mwdSheetLines);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
	}
    
    @GetMapping(value = "/getForm3A")
    public ResponseEntity<byte[]> generateFrom3A(@RequestParam String memberId) throws Exception {
        	
//    	Optional<Customer7PsDetails> customer7PsDetails = customerService.getMemberDetails(pfNumber);
    	
        	String[] monthArr = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        	Form3A form3A = new Form3A();
        	File theDir = new File("uploaded_files/"+memberId+"_Form3A");
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            
            JSONArray wageInterestAndSlabData = new JSONArray();
    		
    		JSONArray mwdData = new JSONArray();
            
            Optional<HpcEmployeeMaster> employee = hpcService.getEmployee(memberId, true);   
        	
            List<String> fiscalYears = hpcService.getFiscalYear(employee.get().getId());
        	
//            int size = fiscalYears.size();
            
            int size = fiscalYears.size();
            
            String closureDate = new String();
            
        	double epsTotal = 0;
        	System.out.println("no errors above");

        	// loops through every year for which employee contribution is present
        	for(int j=0;j<size;j++) {
        		
        		String[] years = fiscalYears.get(j).split("-");
        		
        		String startYear = years[0].substring(2);
        		
        		String endYear = years[1];
        		
        		form3A.setStartDate(startYear);
        		form3A.setEndDate(endYear);
        		
        		// Getting year wise PF-base data from the database
        		List<HpcEmployeePfbase> pfbaseList = hpcService.getPfbaseListByFiscalYear(employee.get(),fiscalYears.get(j) ,true);

                wageInterestAndSlabData = hpcService.getWageInterestAndSlabData(employee.get(), pfbaseList);
                
                mwdData = hpcService.getCreateMwdData(wageInterestAndSlabData);     		
                
                // List of contribution which needs to be put on PDF
        		List<Form3AContribution> form3AContribution = new ArrayList<>();
        		
//        		 unComment this Once all logic is set
        		double pfBaseTotal = 0;
        		double epfTotal = 0;
        		double epfAndTotal = 0;
        		double pensionFundTotal = 0;
        		
        			// Looping through the yearly data and calculating mandatory fields.
					for(int i=0;i<mwdData.length();i++) {
                	
                	JSONObject obj = mwdData.getJSONObject(i);
                	Form3AContribution form3ACon = new Form3AContribution();
                	
                	form3ACon.setPfBase(obj.getString("pfBase"));

                	// need to do calculation for EPF
        			String[] date = obj.getString("wageMonthYear").split("/");
        			Integer contributionPercentage = 0;
        			form3ACon.setMonthAndYear(monthArr[Integer.parseInt(date[0])-1]+"-"+date[1]);
        			// Percentage Setup
        			if(Integer.parseInt(date[1]) == 1995 && Integer.parseInt(date[0]) > 10) {
        				contributionPercentage = 10;
        			}else if((Integer.parseInt(date[1]) == 1996 && Integer.parseInt(date[0]) <= 12) || (Integer.parseInt(date[1]) == 1997 && Integer.parseInt(date[0]) <= 9)) {
        				contributionPercentage = 10;
        			}else if((Integer.parseInt(date[1]) == 2020 && (Integer.parseInt(date[0]) <= 7 && Integer.parseInt(date[0]) >= 5))) {
        				contributionPercentage = 10;
        			}else {
        				contributionPercentage = 12;
        			}
        			
        			form3A.setContributionRate(contributionPercentage+"%");
        			form3A.setClosureDate("25/04/"+date[1]);
        			
        			double epf;
        			
        			double pfBase = new Double(obj.getString("pfBase"));
        			
        			epf = (pfBase/100)*contributionPercentage;
        			
        			DecimalFormat df = new DecimalFormat("####0.00");
        			
        			form3ACon.setEpf(String.valueOf(df.format(epf)));

                	form3ACon.setEpfAnd(obj.getString("amountToBePaid"));
                	form3ACon.setPensionFund(obj.getString("pensionContributionEmployerEarlier"));
                	
//                	need to get exit date of the employee then set remark for date of leaving and reason for leaving.
                	
                	if(employee.get().getDol() != null) {
                		
                		Date leavingDate = employee.get().getDol();
                    	
                    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    	
                    	String date1 = formatter.format(leavingDate);
                    	
                    	String[] leavingDateArr = date1.split("/");
                    	
                    	if(date[0].equals(leavingDateArr[1]) && date[1].equals(leavingDateArr[2])) {
                    		form3ACon.setRemarks("a) Date of leaving service if any "+formatter.format(employee.get().getDol())+"\n\n b)Reasons for leaving service if any "+employee.get().getRetirementReason());
                    	}
                	}
        			form3AContribution.add(form3ACon);
        			
        			pfBaseTotal += pfBase;
        			epfTotal += epf;
        			epfAndTotal += obj.getDouble("amountToBePaid");
                	pensionFundTotal += obj.getDouble("pensionContributionEmployerEarlier");
                }
        		
        		
        		
        		form3A.setForm3AContribution(form3AContribution);
        		form3A.setAccountNo(employee.get().getMemberId());
        		form3A.setName(employee.get().getName());
        		form3A.setFatherName(employee.get().getFatherName());
        		form3A.setNameAndAdddress("MAHINDRA AND MAHINDRA LIMITED Plot No 2 Industrial Area 1 Pithampur Distt. Dhar (M.P)");
        		form3A.setPfBaseTotal(String.valueOf(Math.round(pfBaseTotal)));
        		form3A.setEpfTotal(String.valueOf(Math.round(epfTotal)));
        		form3A.setEpfAndTotal(String.valueOf(Math.round(epfAndTotal)));
        		form3A.setSumOfEpfAndEpfand(String.valueOf(Math.round(epfTotal)+Math.round(epfAndTotal)));
        		form3A.setPensionFundTotal(String.valueOf(Math.round(pensionFundTotal)));
            	
        		if(fiscalYears.get(j) != null || fiscalYears.get(j) != "") {
    	        	ByteArrayOutputStream out = new ByteArrayOutputStream();

    	            try {
    	            	ByteArrayOutputStream generate = generateForm3A.generate(form3A);
    		                FileOutputStream outputStream = new FileOutputStream(theDir+"/"+memberId+"_Form_3A_"+fiscalYears.get(j)+".pdf");
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

        	List<String> filesListInDir = new ArrayList<String>();
            
        	try {
  				filesListInDir = customerService.populateFilesList(theDir);
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          
       // called zip directory method
          customerService.zipDirectory(theDir, theDir+".zip", filesListInDir);
          
          Path targetLocation = fileStorageService.resolvePath(memberId+"_Form3A.zip");

          byte[] bytes = Files.readAllBytes(targetLocation);

          MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

          headers.add("Content-Disposition", "attachment; filename="+memberId+"_Form3A.zip");
          headers.add("X-Suggested-Filename", memberId+"_Form3A.zip");
          headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");

          File[] files = theDir.listFiles();
          for(File file : files){
              if(file.isFile()) file.delete();
          }
          
          theDir.delete();

          return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
    
    @GetMapping("/form3AExcel")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getForm3AExcel(@RequestParam String memberId) throws NoSuchFieldException, SecurityException {

//    	String[] monthName = {"April","May","June","July","August","September","October","Novermber","December","January","February","March"};    	
//        List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(pfNumber);
    	DecimalFormat df = new DecimalFormat("####0.00");
    	Optional<HpcEmployeeMaster> employee = hpcService.getEmployee(memberId, true);   
    	
        List<String> fiscalYears = hpcService.getFiscalYear(employee.get().getId());

        JSONObject response = new JSONObject();
        
        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("MWD");
        int rowNum = 0;
        
        int size = fiscalYears.size(); 

        int count =0;
        int valueCount = 0;
        for(int j=0;j<size;j++) {
        	
        	int rowNumber = 0;
        	
        	XSSFSheet sheet = (XSSFSheet) workbook.createSheet(fiscalYears.get(j));
    		
        	List<HpcEmployeePfbase> pfbaseList = hpcService.getPfbaseListByFiscalYear(employee.get(),fiscalYears.get(j) ,true);
        	
        	JSONArray wageInterestAndSlabData = new JSONArray();
            wageInterestAndSlabData = hpcService.getWageInterestAndSlabData(employee.get(), pfbaseList);
            
            JSONArray mwdData = new JSONArray();
            mwdData = hpcService.getCreateMwdData(wageInterestAndSlabData);
            
            XSSFRow row0 = sheet.createRow(rowNumber++);
        	
			XSSFCell cell0 = row0.createCell(0);
        	cell0.setCellValue(employee.get().getName());
        	
        	XSSFCell cell1 = row0.createCell(1);
        	cell1.setCellValue(employee.get().getFatherName());
        	
        	XSSFCell cell2 = row0.createCell(2);
        	cell2.setCellValue(employee.get().getMemberId());
        	
        	XSSFRow row1 = sheet.createRow(rowNumber++);
        	
        	double pfBaseTotal = 0;
        	double epfTotal = 0;
        	double epfAndTotal = 0;
        	double pensionTotal = 0;
        	for(int i=0;i<mwdData.length();i++) {
        		
        		JSONObject obj = mwdData.getJSONObject(i);
        			
        		if(i==0) {
        			XSSFRow row2 = sheet.createRow(rowNumber++);
        			
        			XSSFCell cell5 = row2.createCell(0);
                	cell5.setCellValue("MONTH");
                	
                	XSSFCell cell6 = row2.createCell(1);
                	cell6.setCellValue("PF-BASE");
                	
                	XSSFCell cell7 = row2.createCell(2);
                	cell7.setCellValue("EPF");
                	
                	XSSFCell cell8 = row2.createCell(3);
                	cell8.setCellValue("EPF 8-1/3%");
                	
                	XSSFCell cell9 = row2.createCell(4);
                	cell9.setCellValue("PENSION FUND");
        		}
        			
        		XSSFRow row3 = sheet.createRow(rowNumber++);
        			XSSFCell cell3 = row3.createCell(0);
                	cell3.setCellValue(obj.getString("wageMonthYear"));
                	
                	XSSFCell cell4 = row3.createCell(1);
                	cell4.setCellValue(obj.getString("pfBase"));
                	
                	//EPF Calculation
                	String[] date = obj.getString("wageMonthYear").split("/");
        			Integer contributionPercentage = 0;
        			
        			// Percentage Setup
        			if(Integer.parseInt(date[1]) == 1995 && Integer.parseInt(date[0]) > 10) {
        				contributionPercentage = 10;
        			}else if((Integer.parseInt(date[1]) == 1996 && Integer.parseInt(date[0]) <= 12) || (Integer.parseInt(date[1]) == 1997 && Integer.parseInt(date[0]) <= 9)) {
        				contributionPercentage = 10;
        			}else if((Integer.parseInt(date[1]) == 2020 && (Integer.parseInt(date[0]) <= 7 && Integer.parseInt(date[0]) >= 5))) {
        				contributionPercentage = 10;
        			}else {
        				contributionPercentage = 12;
        			}
        			
        			double epf;
        			
        			double pfBase = new Double(obj.getString("pfBase"));
        			
        			epf = (pfBase/100)*contributionPercentage;
                	
        			epfTotal += epf;
        			epfAndTotal += new Double(obj.getString("amountToBePaid"));
        			pensionTotal += new Double(obj.getString("pensionContributionEmployerEarlier"));
        			pfBaseTotal += pfBase;
        			
                	XSSFCell cell5 = row3.createCell(2);
                	cell5.setCellValue(df.format(epf));
                	
                	XSSFCell cell6 = row3.createCell(3);
                	cell6.setCellValue(obj.getString("amountToBePaid"));
                	
                	XSSFCell cell7 = row3.createCell(4);
                	cell7.setCellValue(obj.getString("pensionContributionEmployerEarlier"));
                	
                	if(i == mwdData.length()-1) {
                		XSSFRow row2 = sheet.createRow(rowNumber++);
            			
            			XSSFCell cell10 = row2.createCell(0);
                    	cell10.setCellValue("TOTAL");
                    	
                    	XSSFCell cell11 = row2.createCell(1);
                    	cell11.setCellValue(pfBaseTotal);
                    	
                    	XSSFCell cell12 = row2.createCell(2);
                    	cell12.setCellValue(epfTotal);
                    	
                    	XSSFCell cell13 = row2.createCell(3);
                    	cell13.setCellValue(epfAndTotal);
                    	
                    	XSSFCell cell14 = row2.createCell(4);
                    	cell14.setCellValue(pensionTotal);
                	}
        	}
        }
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String fileName = memberId+"_From3A.xlsx";
        try {
        	System.out.println("error here");
        	
	            workbook.write(out);
	            out.close();
//	            outputStream.close();
	            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+fileName);
        headers.add("X-Suggested-Filename", fileName);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
//        response.put("file lines",mwdSheetLines);

//        return Response.success(response.toString());
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }
}
