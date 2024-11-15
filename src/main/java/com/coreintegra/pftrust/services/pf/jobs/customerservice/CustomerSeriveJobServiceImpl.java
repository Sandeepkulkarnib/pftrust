package com.coreintegra.pftrust.services.pf.jobs.customerservice;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.projections.MibsCustomerDetails;
import com.coreintegra.pftrust.projections.PfStartYearMonth;
import com.coreintegra.pftrust.projections.PfUserFiscalYearProjection;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.customerservice.CustomerService;
import com.coreintegra.pftrust.services.pf.jobs.yearend.YearEndProcessJobService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerSeriveJobServiceImpl implements CustomerServieJobService {

    private final Logger logger = LoggerFactory.getLogger(YearEndProcessJobService.class);
    private final CustomJobRepository customJobRepository;

    private final CustomerService customerService;

    public CustomerSeriveJobServiceImpl(CustomJobRepository customJobRepository, CustomerService customerService) {

        this.customJobRepository = customJobRepository;

        this.customerService = customerService;
    }

//    @Override
//    public String generateReport(String params) throws IOException{
////
////        JSONObject jsonObject = new JSONObject(params);
////
////        String jobId = jsonObject.getString("jobId");
////
////        Job processJob = customJobRepository.findByEntityIdAndIsActive(jobId, true);
//
////        JSONObject jobDetailsJson = processJob.getJobDetailsJson();
//
////        String report = generateCorpusReport(processJob, params);
//
//        logger.info("report generated -> {}", report);
//
//        jobDetailsJson.put("fileName", report);
//
//        processJob.setJobDetails(jobDetailsJson.toString());
//
//        logger.info("save report file name in process job");
//
//        customJobRepository.save(processJob);
//
//        logger.info("saved");
//
//
//        Job job = customJobRepository.findByEntityIdAndIsActive(jobId, true);
//
//        logger.info(job.getJobDetailsJson().getString("fileName"));
//
//        return report;
////        return "fileNmae";
//    }

    @Override
    public String generateCorpusReport(String params) throws IOException {

        List<MibsCustomerDetails> mibsCustomerDetailsList = customerService.getMibsCustomerDetailsList(params);

        JSONArray dataObjectArray = new JSONArray();

        String[] monthName = {"April", "May", "June", "July", "August", "September", "October", "November", "December", "January", "February", "March"};

        System.out.println("length " + mibsCustomerDetailsList.size());

        int z = 0;
        for (MibsCustomerDetails mibsCustomerDetails : mibsCustomerDetailsList) {
            System.out.println("employee count " + z++);
            List<PfUserFiscalYearProjection> fiscalYears = customerService.getFiscalYears(mibsCustomerDetails.getPfNumber());
            int size = fiscalYears.size();
            if (fiscalYears.size() > 0) {

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
                response.put("to", fiscalYears.get(size - 1).getFiscalYear());
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
            String fileName = "corpus_report_"+ DateFormatterUtil.format(new Date(), "dd_MM_yyyy_HH_mm_ss")+".xlsx";

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

            String[] headers = {"Sr. No.", "Member ID", "Name", "PF Number", "Establishment Code", "Service", "Tenure (Yrs) : 26 + 2 = 28",
                    "Admin Charges: 1.16%", "Total Payback Corpus", "Total Payback : Admin charges + Corpus"};

            int mainRowIndex = 0;
            XSSFRow headerRow = sheet.createRow(mainRowIndex++);
            for (int colIndex = 0; colIndex < headers.length; colIndex++) {

                int index = colIndex;
                if (colIndex == 6) {
                    index = colIndex + 1;
                } else if (colIndex >= 7) {
                    index = colIndex + 3;
                }

                Cell cell = headerRow.createCell(index);
                cell.setCellValue(headers[colIndex]);
                cell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(index);

                switch (colIndex) {
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

            String[] subHeaders = {"start date", "End Date", "Service years", "additional Year (>20yr)", "Total Tenure"};

            XSSFRow subHeaderRow = sheet.createRow(mainRowIndex++);
            for (int colIndex = 5; colIndex <= subHeaders.length + 4; colIndex++) {

                Cell cell = subHeaderRow.createCell(colIndex);
                cell.setCellValue(subHeaders[colIndex - 5]);

                cell.setCellStyle(borderStyle);
                sheet.autoSizeColumn(colIndex);
                cell.setCellStyle(headerStyle);
            }

//            Example data
            for (int i = 0; i < dataObjectArray.length(); i++) {
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

                cell0.setCellValue(i + 1);
                cell1.setCellValue(obj.getString("memberId"));
                cell2.setCellValue(obj.getString("name"));
                cell3.setCellValue(obj.getString("pfNumber"));
                cell4.setCellValue(obj.getString("establishmentCode"));
                cell5.setCellValue(obj.getString("from"));
                cell6.setCellValue(obj.getString("to"));
                cell7.setCellValue(obj.getInt("tenure"));
                cell8.setCellValue(obj.getInt("tenure") > 20 ? 2 : 0);
                cell9.setCellValue(obj.getInt("tenure") > 20 ? obj.getInt("tenure") + 2 : obj.getInt("tenure"));
                cell10.setCellValue(obj.getInt("adminInterest"));
                cell11.setCellValue(obj.getInt("corpusPayBack"));
                cell12.setCellValue(obj.getInt("corpusPayBack") + obj.getInt("adminInterest"));

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

            try (FileOutputStream out = new FileOutputStream(fileName)) {
                workbook.write(out);
                System.out.println("Workbook saved successfully to " + fileName);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return fileName;
        }
    }
}
