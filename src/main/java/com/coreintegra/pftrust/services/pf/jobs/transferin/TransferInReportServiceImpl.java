package com.coreintegra.pftrust.services.pf.jobs.transferin;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.repositories.pf.transferin.TransferInRepository;
import com.coreintegra.pftrust.searchutil.SearchLoanSpecification;
import com.coreintegra.pftrust.searchutil.SearchTransferInSpecification;
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
import java.util.*;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class TransferInReportServiceImpl implements TransferInReportService {

    private final TransferInRepository transferInRepository;

    public TransferInReportServiceImpl(TransferInRepository transferInRepository) {
        this.transferInRepository = transferInRepository;
    }

    @Override
    @ApplyTenantFilter
    public String generateTransferInReport(Job job, Instant startedAt, String params) throws IOException {

        String fileName = "transfer_in_report_" +
                DateFormatterUtil.format(new java.util.Date(), "dd_MM_yyyy_HH_mm_ss") + ".xlsx";

        List<String> COLUMNS = new ArrayList<>(Arrays.asList("Sr.No","PER NO","PF Number",
                "TOKEN NO",	"NAME OF MEMBER", "UNIT CODE", "DOCUMENT DATE",
                "DATE OF TRANSFER IN",	"TRANSFER-IN TYPE.","DATE OF JOINING PRIOR",
                "COMPANY NAME FROM WHERE IS TRANSFER-IN IS RECEIVED",	"FROM PF ACCOUNT",	"TO PF ACCOUNT",
                "EMP CONT.",	"EMR CONT.",	"TOT CONT.",	"STATUS",	"FI DOCUMENT NUMBER",
                "TRANSFER-IN LETTER REF",	"MOBILE_NO", "EMAIL_ID",	 "USER NAME", "RECORD CHANGED DATE"));

        JSONObject jsonObject = new JSONObject(params);

        Specification<TransferIn> specifications = getReportSpecifications(jsonObject);

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

            Pageable pageable = PageRequest.of(0, 100,
                    Sort.by(Sort.Direction.DESC, SearchTransferInSpecification.getDateType(jsonObject.getString("dateType")))).first();

            boolean hasNext = true;

            int run = 1;

            while (hasNext){

                Instant queryStart = Instant.now();

                Page<TransferIn> page = transferInRepository.findAll(specifications, pageable);

                System.out.println(run + " Query completed in -> " + Duration.between(queryStart, Instant.now()).getSeconds());

                Instant employeeToColListStart = Instant.now();

                List<LinkedList<String>> rows = new ArrayList<>();

                page.get().forEach(transferIn -> {

                    Employee employee = transferIn.getEmployee();

                    LinkedList<String> colList = new LinkedList<>();

                    colList.add(employee.getPernNumber());
                    colList.add(employee.getPfNumber());
                    colList.add(employee.getTokenNumber());
                    colList.add(employee.getName());
                    colList.add(employee.getUnitCode());

                    colList.add(transferIn.getCreatedAtTimestamp() == null ? "" : DateFormatterUtil.formatDateR(new Date(transferIn.getCreatedAtTimestamp())));
                    colList.add(DateFormatterUtil.formatDateR(transferIn.getPostingDate()));
                    colList.add(transferIn.getPrevPfAccountHolder().getName());
                    colList.add(DateFormatterUtil.formatDateR(transferIn.getDateOfJoiningPrior()));

                    PreviousCompany previousCompany = transferIn.getPreviousCompany();

                    colList.add(previousCompany.getName());
                    colList.add(transferIn.getPrevPfNumber());

                    colList.add(transferIn.getPresPfNumber());
                    colList.add(transferIn.getTransferInFinalDetails().getMemberContribution().toString());
                    colList.add(transferIn.getTransferInFinalDetails().getCompanyContribution().toString());
                    colList.add(transferIn.getTransferInFinalDetails().getTotal().toString());

                    colList.add(transferIn.getTransferInStatus().getTitle());

                    colList.add(transferIn.getSapDocumentNumber());

                    TransferInFinalDetails transferInFinalDetails = transferIn.getTransferInFinalDetails();

                    String ref = "";

                    Integer year = transferInFinalDetails.getYear();

                    if(transferIn.getTransferInReminders() != null && transferIn.getTransferInReminders().size() > 0 && year != null){

                        Long id = transferIn.getTransferInReminders().get(transferIn.getTransferInReminders().size() - 1).getId();

                        ref = "SPF/F" + year + "-" + (year + 1) + "/" + id;
                    }

                    colList.add(ref);
                    colList.add(transferIn.getAlternateContactNumber());

                    colList.add(transferIn.getAlternateEmailId());

                    colList.add(transferIn.getTransferInCreatedBy());
                    colList.add(transferIn.getUpdatedAtTimestamp() == null ? "" : DateFormatterUtil.formatDateR(new Date(transferIn.getUpdatedAtTimestamp())));

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

    private Specification<TransferIn> getReportSpecifications(JSONObject jsonObject) {

        String dateType = jsonObject.getString("dateType");

        JSONArray unitCodes = jsonObject.getJSONArray("unitCodes");

        JSONArray dates = jsonObject.getJSONArray("dates");

        JSONArray months = jsonObject.getJSONArray("months");

        JSONArray statusList = jsonObject.getJSONArray("statusList");

        JSONArray types = jsonObject.getJSONArray("types");

        Integer year = jsonObject.get("year") instanceof Integer ? jsonObject.getInt("year") : null;

        SearchTransferInSpecification specification = new SearchTransferInSpecification();

        Specification<TransferIn> monthAndYearSpecifications = specification.monthAndYearSpecification(year, months, dateType);

        Specification<TransferIn> dateSpecification = specification.dateSpecification(dates, dateType);

        Specification<TransferIn> unitCodeSpecification = specification.unitCodeSpecification(unitCodes);

        Specification<TransferIn> typeSpecification = specification.typeSpecification(types);

        Specification<TransferIn> statusSpecification = specification.statusSpecification(statusList);

        return Specification.where(unitCodeSpecification)
                .and(dateSpecification).and(typeSpecification).and(statusSpecification)
                .and(monthAndYearSpecifications);

    }


}
