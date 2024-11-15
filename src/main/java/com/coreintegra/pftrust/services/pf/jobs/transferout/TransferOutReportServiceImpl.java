package com.coreintegra.pftrust.services.pf.jobs.transferout;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.repositories.pf.transferout.TransferOutRepository;
import com.coreintegra.pftrust.searchutil.SearchSettlementSpecification;
import com.coreintegra.pftrust.searchutil.SearchTransferOutSpecification;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class TransferOutReportServiceImpl implements TransferOutReportService {

    private final TransferOutRepository transferOutRepository;

    public TransferOutReportServiceImpl(TransferOutRepository transferOutRepository) {
        this.transferOutRepository = transferOutRepository;
    }

    @Override
    @ApplyTenantFilter
    public String generateTransferOutReport(Job job, Instant startedAt, String params) throws IOException {
        List<String> COLUMNS = new ArrayList<>(Arrays.asList("S.NO.", "PER NO",	"PF NO.",
                "TOKEN NO",	"EMP. NAME",
                "UNIT CODE",	"DUE DATE",	"SETTLEMENT CODE",	"CESSATION DATE",
                "SETTLEMENT APPLICATION NUMBER",	"FI DOCUMENT NUMBER",	"Payment Bank",
                "Bank Account Number",	"Bank Payment Date",	"Gross Amount",
                "Tax Amount",	"Education Cess",	"Net Amount",	"STATUS",	"PAYMENT MODE",
                "PAYEENAME",	"ADDRESS1",	"ADDRESS2",	"ADDRESS3",	"ADDRESS4",
                "MEMBER BANK NAME",	"BRANCH	MICR CODE",	"IFSC CODE",	"MOBILE_NO",
                "EMAIL_ID","RECORD CHANGED DATE","USER NAME"));

        String fileName = "transfer_out_report_" +
                DateFormatterUtil.format(new java.util.Date(), "dd_MM_yyyy_HH_mm_ss") + ".xlsx";

        JSONObject jsonObject = new JSONObject(params);

        Specification<TransferOut> specifications = getReportSpecifications(jsonObject);

        Instant now = Instant.now();

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(1)) {

            Sheet sheet = workbook.createSheet();

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNS.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNS.get(col));
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;

            Pageable pageable = PageRequest.of(0, 5000,
                    Sort.by(Sort.Direction.DESC, SearchTransferOutSpecification.getDateType(jsonObject.getString("dateType")))).first();

            boolean hasNext = true;

            int run = 1;

            while (hasNext){

                Instant queryStart = Instant.now();

                Page<TransferOut> page = transferOutRepository.findAll(specifications, pageable);

                System.out.println(run + " Query completed in -> " + Duration.between(queryStart, Instant.now()).getSeconds());

                Instant employeeToColListStart = Instant.now();

                List<LinkedList<String>> rows = new ArrayList<>();

                page.get().forEach(transferOut -> {

                    Employee employee = transferOut.getEmployee();

                    LinkedList<String> colList = new LinkedList<>();

                    colList.add(employee.getPernNumber());
                    colList.add(employee.getPfNumber());
                    colList.add(employee.getTokenNumber());
                    colList.add(employee.getName());
                    colList.add(employee.getUnitCode());

                    colList.add(DateFormatterUtil.formatDateR(transferOut.getDueDate()));
                    colList.add(String.valueOf(transferOut.getTransferOutType().getTitle()));
                    colList.add(DateFormatterUtil.formatDateR(transferOut.getDateOfLeavingService()));
                    colList.add(transferOut.getId().toString());

                    colList.add(transferOut.getDocumentNumber());
                    colList.add(transferOut.getBankName() == null ? "" : transferOut.getBank().getName());

                    colList.add(transferOut.getAccountNumber());

                    colList.add(DateFormatterUtil.formatDateR(transferOut.getPaymentDate()));

                    colList.add(transferOut.getTransferOutFinalDetails().getTotalContribution() == null ? "0" : transferOut.getTransferOutFinalDetails().getTotalContribution().toString());

                    colList.add("0");
                    colList.add("0");
                    colList.add(transferOut.getTransferOutFinalDetails().getTotalNetCreditAmount() == null ? "0" : transferOut.getTransferOutFinalDetails().getTotalNetCreditAmount().toString());

                    colList.add(transferOut.getTransferOutStatus().getTitle());

                    colList.add(transferOut.getPaymentMode() == null ? "" : transferOut.getPaymentMode().getMode());

                    colList.add(transferOut.getPayeeName());

                    colList.add(transferOut.getAddressLine1());
                    colList.add(transferOut.getAddressLine2());
                    colList.add(transferOut.getAddressLine3());
                    colList.add(transferOut.getAddressLine4());

                    colList.add(transferOut.getBankName());
                    colList.add(transferOut.getMicrCode());
                    colList.add(transferOut.getIfscCode());
                    colList.add(transferOut.getAlternateContactNumber());
                    colList.add(transferOut.getAlternateEmailId());
                    colList.add(transferOut.getUpdatedAtTimestamp() == null ? "" : DateFormatterUtil.formatDateR(new java.util.Date(transferOut.getUpdatedAtTimestamp())));
                    colList.add(transferOut.getTransferOutCreatedBy());

                    rows.add(colList);

                });

                System.out.println(run + "Employee to col list completed in -> " + Duration.between(employeeToColListStart, Instant.now()).getSeconds());

                Instant addToSheetStart = Instant.now();

                for (LinkedList<String> colList : rows) {

                    Row row = sheet.createRow(rowNum);

                    createCell(row, 0, String.valueOf(rowNum));

                    for (int cellNo = 0; cellNo < colList.size(); cellNo++) {

                        createCell(row, cellNo+1, colList.get(cellNo));

                    }

                    rowNum++;
                }

                System.out.println(run + " Add to sheet completed in -> " + Duration.between(addToSheetStart, Instant.now()).getSeconds());

                hasNext = page.hasNext();

                pageable = page.nextPageable();

                run++;
            }

            Instant writeToFileStart = Instant.now();

            try (FileOutputStream out = new FileOutputStream(fileName)) {
                workbook.write(out);
            }

            // dispose of temporary files backing this workbook on disk
            workbook.dispose();

            System.out.println("Write to file completed in -> " + Duration.between(writeToFileStart, Instant.now()).getSeconds());

        }

        System.out.println("Excel Sheet Generated Successfully in -> " + Duration.between(now, Instant.now()).getSeconds());

        return fileName;
    }




    private Specification<TransferOut> getReportSpecifications(JSONObject jsonObject) {

        String dateType = jsonObject.getString("dateType");

        JSONArray unitCodes = jsonObject.getJSONArray("unitCodes");

        JSONArray dates = jsonObject.getJSONArray("dates");

        JSONArray months = jsonObject.getJSONArray("months");

        JSONArray statusList = jsonObject.getJSONArray("statusList");

        JSONArray types = jsonObject.getJSONArray("types");

        Integer year = jsonObject.get("year") instanceof Integer ? jsonObject.getInt("year") : null;

        SearchTransferOutSpecification specification = new SearchTransferOutSpecification();

        Specification<TransferOut> monthAndYearSpecifications = specification.monthAndYearSpecification(year, months, dateType);

        Specification<TransferOut> dateSpecification = specification.dateSpecification(dates, dateType);

        Specification<TransferOut> unitCodeSpecification = specification.unitCodeSpecification(unitCodes);

        Specification<TransferOut> typeSpecification = specification.typeSpecification(types);

        Specification<TransferOut> statusSpecification = specification.statusSpecification(statusList);

        return Specification.where(unitCodeSpecification)
                .and(dateSpecification).and(typeSpecification).and(statusSpecification)
                .and(monthAndYearSpecifications);

    }






}
