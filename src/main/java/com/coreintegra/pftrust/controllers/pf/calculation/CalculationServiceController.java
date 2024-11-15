package com.coreintegra.pftrust.controllers.pf.calculation;

import java.io.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.pdf.FormA;
import com.coreintegra.pftrust.services.pf.pdf.GenerateFormA;
import com.coreintegra.pftrust.services.pf.pdf.GenerateFormB;

@RestController
@RequestMapping(CalculationServiceEndpoints.CALCULATION_SERVICE_ENDPOINT)
public class CalculationServiceController {
	
	@Autowired
    private GenerateFormA generateFormA;
	
	@Autowired
    private GenerateFormB generateFormB;

	@PostMapping("/generateExcel")
	@ApplyTenantFilter
    public ResponseEntity<byte[]> getMwdExcelFile(@RequestBody String data) {

		JSONObject jsonObject = new JSONObject(data);
		
		FormA formA = new FormA();
		
		formA.setGrossProfit(jsonObject.getString("grossProfit"));
		formA.setDepreciationAddmissible(jsonObject.getString("depreciationAdmissible"));
		formA.setDirectTax(jsonObject.getString("directTax"));
		formA.setTaxAmount(jsonObject.getString("taxAmount"));
		formA.setDividendPayable(jsonObject.getString("dividendPayable"));
		formA.setInterest(jsonObject.getString("interest"));
		formA.setCarryForward(jsonObject.getString("carryForward"));
		formA.setPayableBonus(jsonObject.getString("payableBonus"));
		formA.setTotalDeduction(jsonObject.getString("totalDeduction"));
		formA.setAvailableSurplus(jsonObject.getString("availableSurplus"));
		formA.setAllocableSurplus(jsonObject.getString("allocableSurplus"));
		formA.setTotalAllocableBonus(jsonObject.getString("totalAllocableBonus"));
		formA.setSetOnOff(jsonObject.getString("setOnOff"));
		formA.setCarriedForward(jsonObject.getString("carriedForward"));

		JSONObject Excelheaders = new JSONObject();
		Excelheaders.put("sr", "SR");
		Excelheaders.put("fiscalYear", "FY");
		Excelheaders.put("grossProfit", "Gross Profit for the accounting year");
		Excelheaders.put("depreciationAdmissible", "Depreciation admissible u/s 32 of the Income tax Act");
		Excelheaders.put("taxAmount", "Development rebate or investment allowance or development allowance");
		Excelheaders.put("directTax", "Direct taxes payable for the accounting year");
		Excelheaders.put("dividendPayable", "Sum Specified In third Schedule (Fill the bewlo information)");
		Excelheaders.put("totalDeduction", "Total Deduction");
		Excelheaders.put("availableSurplus", "Available Surplus");
		Excelheaders.put("allocableSurplus", "Allocable Surplus");
		Excelheaders.put("carryForward", "Set-on / Set-off Carried forward (To be filled in case of bonus payable less than 20%) (Carried forward allowed up to 3 subsequent FY)");
		Excelheaders.put("totalAllocableBonus","Total Allocable bonus");
		Excelheaders.put("payableBonus", "Amount payable as Bonus");
		Excelheaders.put("setOnOff", "Amount of Set-on or Set-off - FY");
		Excelheaders.put("carriedForward", "Total set-on or set-off carried forward");
		Excelheaders.put("interest", "Indian Company please fill 60 / If non-Indian company to fill 67");
		
		JSONArray excelData =  new JSONArray();
		excelData.put(Excelheaders);
		excelData.put(jsonObject);
		
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Allocable Bonus Calculation");
                
        for(int i=1;i<excelData.length()+1;i++) {
        	Row row = sheet.createRow(i);
        	
        	JSONObject object = excelData.getJSONObject(i-1);
        	
        	Cell cell = row.createCell(0);
            cell.setCellValue(object.getString("sr"));
            
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(object.getString("fiscalYear"));
            
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(object.getString("grossProfit"));
            
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(object.getString("depreciationAdmissible"));
            
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(object.getString("taxAmount"));
            
            Cell cell5 = row.createCell(5);
            cell5.setCellValue(object.getString("directTax"));
            
            Cell cell6 = row.createCell(6);
            cell6.setCellValue(object.getString("dividendPayable"));
            
            Cell cell7 = row.createCell(7);
            cell7.setCellValue(object.getString("totalDeduction"));
            
            Cell cell8 = row.createCell(8);
            cell8.setCellValue(object.getString("availableSurplus"));
            
            Cell cell9 = row.createCell(9);
            cell9.setCellValue(object.getString("allocableSurplus"));
            
            Cell cell10 = row.createCell(10);
            cell10.setCellValue(object.getString("carryForward"));
            
            Cell cell11 = row.createCell(11);
            cell11.setCellValue(object.getString("totalAllocableBonus"));
            
            Cell cell12 = row.createCell(12);
            cell12.setCellValue(object.getString("payableBonus"));
            
            Cell cell13 = row.createCell(13);
            cell13.setCellValue(object.getString("setOnOff"));
            
            Cell cell14 = row.createCell(14);
            cell14.setCellValue(object.getString("carriedForward"));
            
            if(i == 1) {
            	Row row1 = sheet.createRow(0);
            	Cell cell15 = row1.createCell(8);
                cell15.setCellValue(object.getString("interest"));
                
                JSONObject object1 = excelData.getJSONObject(i);
                Cell cell16 = row1.createCell(9);
                cell16.setCellValue(object1.getString("interest"));
            }
            
        }
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        String fileName = "Allocable_Bonus_Calculatiob_"+jsonObject.getString("fiscalYear")+".xlsx";
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
        
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
	}
	
	@PostMapping("/generateFormA")
	@ApplyTenantFilter
    public ResponseEntity<byte[]> getFormA(@RequestBody String data) {

		JSONObject jsonObject = new JSONObject(data);
		
		FormA formA = new FormA();
		
		formA.setEstablishmentName(jsonObject.getString("establishmentName"));
		formA.setFiscalYear(jsonObject.getString("fiscalYear"));
		formA.setGrossProfit(jsonObject.getString("grossProfit"));
		formA.setDepreciationAddmissible(jsonObject.getString("depreciationAdmissible"));
		formA.setDirectTax(jsonObject.getString("directTax"));
		formA.setTaxAmount(jsonObject.getString("taxAmount"));
		formA.setDividendPayable(jsonObject.getString("dividendPayable"));
		formA.setInterest(jsonObject.getString("interest"));
		formA.setCarryForward(jsonObject.getString("carryForward"));
		formA.setPayableBonus(jsonObject.getString("payableBonus"));
		formA.setTotalDeduction(jsonObject.getString("totalDeduction"));
		formA.setAvailableSurplus(jsonObject.getString("availableSurplus"));
		formA.setAllocableSurplus(jsonObject.getString("allocableSurplus"));
		formA.setTotalAllocableBonus(jsonObject.getString("totalAllocableBonus"));
		formA.setSetOnOff(jsonObject.getString("setOnOff"));
		formA.setCarriedForward(jsonObject.getString("carriedForward"));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
        	ByteArrayOutputStream generate = generateFormA.generate(formA);     
                generate.writeTo(out);
                out.close();
                generate.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+"FormA.pdf");
        headers.add("X-Suggested-Filename", "FormA.pdf");
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
	}
    
    @PostMapping("/generateFormB")
	@ApplyTenantFilter
    public ResponseEntity<byte[]> getFormB(@RequestBody String data) {

		JSONObject jsonObject = new JSONObject(data);
		
		FormA formA = new FormA();
		
		formA.setEstablishmentName(jsonObject.getString("establishmentName"));
		formA.setFiscalYear(jsonObject.getString("fiscalYear"));
		formA.setGrossProfit(jsonObject.getString("grossProfit"));
		formA.setDepreciationAddmissible(jsonObject.getString("depreciationAdmissible"));
		formA.setDirectTax(jsonObject.getString("directTax"));
		formA.setTaxAmount(jsonObject.getString("taxAmount"));
		formA.setDividendPayable(jsonObject.getString("dividendPayable"));
		formA.setInterest(jsonObject.getString("interest"));
		formA.setCarryForward(jsonObject.getString("carryForward"));
		formA.setPayableBonus(jsonObject.getString("payableBonus"));
		formA.setTotalDeduction(jsonObject.getString("totalDeduction"));
		formA.setAvailableSurplus(jsonObject.getString("availableSurplus"));
		formA.setAllocableSurplus(jsonObject.getString("allocableSurplus"));
		formA.setTotalAllocableBonus(jsonObject.getString("totalAllocableBonus"));
		formA.setSetOnOff(jsonObject.getString("setOnOff"));
		formA.setCarriedForward(jsonObject.getString("carriedForward"));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
        	ByteArrayOutputStream generate = generateFormB.generate(formA);     
                generate.writeTo(out);
                out.close();
                generate.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        }

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("Content-Disposition", "attachment; filename="+"FormB.pdf");
        headers.add("X-Suggested-Filename", "FormB.pdf");
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-Suggested-Filename");
        
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
	}
}
