package com.coreintegra.pftrust.services.pf.jobs.settlements;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.repositories.pf.settlement.SettlementRepository;
import com.coreintegra.pftrust.searchutil.SearchSettlementSpecification;
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
public class SettlementReportServiceImpl implements SettlementReportService {

    private final SettlementRepository settlementRepository;

    public SettlementReportServiceImpl(SettlementRepository settlementRepository) {
        this.settlementRepository = settlementRepository;
    }

    @Override
    @ApplyTenantFilter
    public String generateSettlementReport(Job job, Instant startedAt, String params) throws IOException {


        List<String> COLUMNS = new ArrayList<>(Arrays.asList("S.NO.", "PER NO",
                "PF NO.", "TOKEN NO", "EMP. NAME", "UNIT CODE",
                "DUE DATE", "SETTLEMENT CODE", "CESSATION DATE",
                "SETTLEMENT APPLICATION NUMBER", "FI DOCUMENT NUMBER", "Payment Bank",
                "Bank Payment Date", "Gross Amount", "Tax Amount", "Education Cess", "Net Amount",
                "STATUS", "PAYMENT MODE", "PAYEE NAME", "ADDRESS1", "ADDRESS2", "ADDRESS3",
                "ADDRESS4", "MEMBER BANK NAME", "Bank Account Number", "BRANCH", "MICR CODE",
                "IFSC CODE", "MOBILE_NO", "EMAIL_ID",
                "RECORD CHANGED DATE", "USER NAME"));

        String fileName = "settlement_report_" +
                DateFormatterUtil.format(new java.util.Date(), "dd_MM_yyyy_HH_mm_ss") + ".xlsx";

        JSONObject jsonObject = new JSONObject(params);

        Specification<Settlement> specifications = getReportSpecifications(jsonObject);

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
                    Sort.by(Sort.Direction.DESC, SearchSettlementSpecification.getDateType(jsonObject.getString("dateType")))).first();

            boolean hasNext = true;

            int run = 1;

            while (hasNext){

                Instant queryStart = Instant.now();

                Page<Settlement> page = settlementRepository.findAll(specifications, pageable);

                System.out.println(run + " Query completed in -> " + Duration.between(queryStart, Instant.now()).getSeconds());

                Instant employeeToColListStart = Instant.now();

                List<LinkedList<String>> rows = new ArrayList<>();

                page.get().forEach(settlement -> {

                    Employee employee = settlement.getEmployee();

                    LinkedList<String> colList = new LinkedList<>();

                    colList.add(employee.getPernNumber());
                    colList.add(employee.getPfNumber());
                    colList.add(employee.getTokenNumber());
                    colList.add(employee.getName());
                    colList.add(employee.getUnitCode());

                    colList.add(DateFormatterUtil.formatDateR(settlement.getDateOfSettlement()));
                    colList.add(String.valueOf(settlement.getSettlementType().getTitle()));
                    colList.add(DateFormatterUtil.formatDateR(settlement.getDateOfLeavingService()));
                    colList.add(settlement.getId().toString());

                    colList.add(settlement.getDocumentNumber());
                    colList.add(settlement.getBankName() == null ? "" : settlement.getBank().getName());

                    colList.add(DateFormatterUtil.formatDateR(settlement.getPaymentDate()));

                    colList.add(settlement.getSettlementFinalDetails().getTotalContribution() == null ? "0" : settlement.getSettlementFinalDetails().getTotalContribution().toString());

                    colList.add(settlement.getSettlementFinalDetails().getIncomeTax() == null ? "0" : settlement.getSettlementFinalDetails().getIncomeTax().toString());
                    colList.add(settlement.getSettlementFinalDetails().getEduCess() == null ? "0" : settlement.getSettlementFinalDetails().getEduCess().toString());
                    colList.add(settlement.getSettlementFinalDetails().getNetCreditAmount() == null ? "0" : settlement.getSettlementFinalDetails().getNetCreditAmount().toString());

                    colList.add(settlement.getSettlementStatus().getTitle());

                    colList.add(settlement.getPaymentMode() == null ? "" : settlement.getPaymentMode().getMode());

                    colList.add(settlement.getPayeeName());

                    colList.add(settlement.getAddressLine1());
                    colList.add(settlement.getAddressLine2());
                    colList.add(settlement.getAddressLine3());
                    colList.add(settlement.getAddressLine4());

                    colList.add(settlement.getBankName());
                    colList.add(settlement.getAccountNumber());
                    colList.add(settlement.getBranch());
                    colList.add(settlement.getMicrCode());
                    colList.add(settlement.getIfscCode());
                    colList.add(settlement.getAltContactNumber());
                    colList.add(settlement.getAltEmailId());
                    colList.add(settlement.getUpdatedAtTimestamp() == null ? "" : DateFormatterUtil.formatDateR(new Date(settlement.getUpdatedAtTimestamp())));
                    colList.add(settlement.getSettlementCreatedBy());

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

        return fileName;

    }


    private Specification<Settlement> getReportSpecifications(JSONObject jsonObject) {

        String dateType = jsonObject.getString("dateType");

        JSONArray unitCodes = jsonObject.getJSONArray("unitCodes");

        JSONArray dates = jsonObject.getJSONArray("dates");

        JSONArray months = jsonObject.getJSONArray("months");

        JSONArray statusList = jsonObject.getJSONArray("statusList");

        JSONArray types = jsonObject.getJSONArray("types");

        Integer year = jsonObject.get("year") instanceof Integer ? jsonObject.getInt("year") : null;

        SearchSettlementSpecification specification = new SearchSettlementSpecification();

        Specification<Settlement> monthAndYearSpecifications = specification.monthAndYearSpecification(year, months, dateType);

        Specification<Settlement> dateSpecification = specification.dateSpecification(dates, dateType);

        Specification<Settlement> unitCodeSpecification = specification.unitCodeSpecification(unitCodes);

        Specification<Settlement> typeSpecification = specification.typeSpecification(types);

        Specification<Settlement> statusSpecification = specification.statusSpecification(statusList);

        return Specification.where(unitCodeSpecification)
                .and(dateSpecification).and(typeSpecification).and(statusSpecification)
                .and(monthAndYearSpecifications);

    }

}
