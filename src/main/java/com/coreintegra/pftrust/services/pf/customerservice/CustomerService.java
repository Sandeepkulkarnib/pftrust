package com.coreintegra.pftrust.services.pf.customerservice;

import com.coreintegra.pftrust.projections.*;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Page<PfUserProjection> getPfUsers(String search, Integer size, Integer page);

    List<PfUserFiscalYearProjection> getFiscalYears(String pfNumber);
    
    Optional<PfStartYearMonth> getPfStartYearMonth(String pfNumber);
    
    Integer getTenure(String pfNumber);
    
    List<PfMonthlyContribution> getMonthlyContribution(String pfNumber, String fiscalYear);
    
    JSONArray getMonthlyHigherPension(String pfNumber,String fiscalYear, String first_contribution_year);
    
    JSONArray getMonthlyHigherPension1(String pfNumber,String fiscalYear, String first_contribution_year);
    
    JSONArray getServiceSummary(String pfNumber,String fiscalYear, String first_contribution_year);
    
    JSONArray get60MonthData(String pfNumber);
    
    Optional<PfUserInterestRates> getInterestRate(String fiscalYear);
    
    List<PfStartYearMonth> getFiscalYearBetween(String pfNumber,String firstFiscalYear, String secondFiscalYear);
    
    String getSlabAmount(String date);
    
    JSONArray getMonthlyCorpusAccumulation(String pfNumber, String fiscalYear, String first_contribution_year);
    
    JSONArray getCorpusSummary(String pfNumber, String fiscalYear, String first_contribution_year);

    JSONArray getMonthlyHigherPensionForMWDSheet(String pfNumber, String fiscalYear, String first_contribution_year);

    JSONArray getMonthlyHigherPensionForMWDExcelSheet(String pfNumber, String fiscalYear, String first_contribution_year);

    List<String> getMWDSheetLines(JSONArray listOfContribution, String pfNumber);
    
    List<String> getMWDSheetLines(JSONArray listOfContribution);
    
    String getMemberId(String pfNumber);
    
    void setMemberId(String mid,String pfNumber);
    
    JSONArray mwdDataFor7P(String pfNumber, String fiscalYear, String first_contribution_year);
    
    String getMemberName(String pfNumber);
    
    List<String> populateFilesList(File dir) throws IOException;
    
    void zipDirectory(File dir, String zipDirName, List<String> filesListInDir);
    
    Optional<Customer7PsDetails> getMemberDetails(String pfNumber);
    
    void setMemberDetails(String name, String fatherName, String pfNumber, String memberId, String leavingFiscalYear, String reasonOfLeaving,
    		String dateOfLeaving,String establishmentName,String establishmentCode);

    CellStyle createHeaderCellStyle(Workbook workbook);

    CellStyle createDataCellStyle(Workbook workbook);

    CellStyle createWrapTextStyle(Workbook workbook);

    CellStyle createSolidBordersStyle(Workbook workbook);

    void setBorderStyleSolidThin(XSSFSheet sheet, CellRangeAddress cellRangeAddress);

    List<MibsCustomerDetails> getMibsCustomerDetailsList(String establishmentCode);

    List<String> getEstablishmentCodeList();
}
