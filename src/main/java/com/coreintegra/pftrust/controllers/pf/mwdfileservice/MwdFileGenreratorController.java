package com.coreintegra.pftrust.controllers.pf.mwdfileservice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.coreintegra.pftrust.dtos.pdf.SearchPfUserDTO;
import com.coreintegra.pftrust.entities.pf.employee.PfUser;
import com.coreintegra.pftrust.projections.PfStartYearMonth;
import com.coreintegra.pftrust.projections.PfUserFiscalYearProjection;
import com.coreintegra.pftrust.projections.PfUserProjection;
import com.coreintegra.pftrust.projections.PfMonthlyContribution;
import com.coreintegra.pftrust.services.pf.customerservice.CustomerService;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.dtos.SearchEmployeeDTO;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.Response;

import net.bytebuddy.implementation.bytecode.collection.ArrayLength;


@RestController
@RequestMapping(MwdFileGeneratorEndPoints.MWD_ENDPOINT)
public class MwdFileGenreratorController {

	private final CustomerService customerService;

    public MwdFileGenreratorController(CustomerService customerService) {
        this.customerService = customerService;
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
    
    @GetMapping(value = "/downloadMWDTemplate")
    @ApplyTenantFilter
    public ResponseEntity<byte[]> getHiringTemplate() throws ParseException {

        try (InputStream in = Model.class.getClassLoader().getResourceAsStream("mwd_template/MWD_File_Template.xlsx")) {

            if(in == null) {
                return ResponseEntity.badRequest().body(null);
            }

            return ResponseEntity.ok().body(IOUtils.toByteArray(in));

        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body(null);
    }
}

