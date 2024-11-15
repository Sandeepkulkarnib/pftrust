package com.coreintegra.pftrust.services.pf.jobs.loans;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.repositories.pf.loan.LoanRepository;
import com.coreintegra.pftrust.searchutil.SearchContributionSpecification;
import com.coreintegra.pftrust.searchutil.SearchLoanSpecification;
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

import javax.persistence.Tuple;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class LoanReportJobServiceImpl implements LoanReportJobService {

    private final LoanRepository loanRepository;

    public LoanReportJobServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    @ApplyTenantFilter
    public String generateLoanReport(Job job, Instant startedAt, String params) throws IOException {

        String fileName = "loan_report_" +
                DateFormatterUtil.format(new java.util.Date(), "dd_MM_yyyy_HH_mm_ss") + ".xlsx";

        List<String> COLUMNS = new ArrayList<>(Arrays.asList("Sr.No", "Personnel No.", "Provident Fund No", "Token No", "Member Name",
                "Unit Code", "Unit Name", "Loan Application No", "APPLICATION_RECEIPT_DATE", "Sanctioned Date",
                "Payment Bank", "Bank Payment Date", "Member Amount", "Company Amount",
                "V P F Amount", "Sanctioned Amount", "FI Document Number", "Reason Code", "Reason Description",
                "TOTAL COST", "STAMPDUTY AMT", "REGISTRATION AMT", "INSURANCE AMT",
                "ANYOTHER AMT", "TOTAL VALUE OF SUPPORTING", "BANK NAME", "Bank Account Number", "BRANCH", "MICR CODE", "IFCS CODE",
                "MOBILE_NO", "EMAIL_ID", "RECORD CHANGED DATE", "USER NAME", "LOAN COUNT"
        ));

        JSONObject jsonObject = new JSONObject(params);

        Specification<Loan> specifications = getReportSpecifications(jsonObject);

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
                            Sort.by(Sort.Direction.DESC, SearchLoanSpecification.getDateType(jsonObject.getString("dateType"))))
                    .first();

            boolean hasNext = true;

            int run = 1;

            while (hasNext){

                Instant queryStart = Instant.now();

                Page<Loan> page = loanRepository.findAll(specifications, pageable);

                System.out.println(run + " Query completed in -> " + Duration.between(queryStart, Instant.now()).getSeconds());

                Instant employeeToColListStart = Instant.now();

                List<LinkedList<String>> rows = new ArrayList<>();

                List<Employee> loanEmployees = page.get().map(Loan::getEmployee).collect(Collectors.toList());

                List<Tuple> loanCount = loanRepository.getLoanCount(loanEmployees);

                Map<Long, Long> employeeLoanCount = new HashMap<>();

                loanCount.forEach(tuple -> employeeLoanCount.put(tuple.get(1, Long.class), tuple.get(0, Long.class)));

                page.get().forEach(loan -> {

                    Employee employee = loan.getEmployee();

                    LinkedList<String> colList = new LinkedList<>();

                    colList.add(employee.getPernNumber());
                    colList.add(employee.getPfNumber());
                    colList.add(employee.getTokenNumber());
                    colList.add(employee.getName());
                    colList.add(employee.getUnitCode());
                    colList.add("");

                    colList.add(loan.getId().toString());
                    colList.add(DateFormatterUtil.formatDateR(loan.getLoanApplicationReceivedDate()));
                    colList.add(DateFormatterUtil.formatDateR(loan.getLoanApprovalDate()));

                    colList.add(loan.getBank() == null ? "" : loan.getBank().getName());
                    colList.add(DateFormatterUtil.formatDateR(loan.getPaymentDate()));

                    colList.add(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnMemContribution() == null ? "0" : loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnMemContribution().toString());
                    colList.add(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnCompanyContribution() == null ? "0" : loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnCompanyContribution().toString());
                    colList.add(loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnVPFContribution() == null ? "0" : loan.getLoanWithDrawalsFinalDetails().getLoanAmountOnVPFContribution().toString());
                    colList.add(loan.getLoanWithDrawalsFinalDetails().getTotalLoanWithDrawal() == null ? "0" : loan.getLoanWithDrawalsFinalDetails().getTotalLoanWithDrawal().toString());

                    colList.add(loan.getDocumentNo());
                    colList.add(loan.getLoanType() == null ? "" : loan.getLoanType().getCode());
                    colList.add(loan.getLoanType() == null ? "" : loan.getLoanType().getTitle());

                    colList.add(loan.getTotalCost() == null ? "0" : loan.getTotalCost().toString());
                    colList.add(loan.getStampDuty() == null ? "0" : loan.getStampDuty().toString());
                    colList.add(loan.getPropertyRegistration() == null ? "0" : loan.getPropertyRegistration().toString());
                    colList.add(loan.getInsurance() == null ? "0" : loan.getInsurance().toString());
                    colList.add(loan.getOther() == null ? "0" : loan.getOther().toString());
                    colList.add(loan.getTotalValue() == null ? "0" : loan.getTotalValue().toString());

                    colList.add(loan.getEmployeeBank());
                    colList.add(loan.getEmployeeAccountNumber());
                    colList.add(loan.getEmployeeBankBranch());
                    colList.add(loan.getEmployeeBankMicrCode());
                    colList.add(loan.getEmployeeBankIfscCode());

                    colList.add(loan.getAltContactNumber());
                    colList.add(loan.getAltEmail());

                    colList.add(loan.getUpdatedAtTimestamp() == null ? "" : DateFormatterUtil.formatDateR(new Date(loan.getUpdatedAtTimestamp())));
                    colList.add(loan.getLoanCreatedBy());

                    colList.add(employeeLoanCount.get(employee.getId()).toString());

                    rows.add(colList);

                });

                System.out.println(run + "loan to col list completed in -> " + Duration.between(employeeToColListStart, Instant.now()).getSeconds());

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


    private Specification<Loan> getReportSpecifications(JSONObject jsonObject) {

        String dateType = jsonObject.getString("dateType");

        JSONArray unitCodes = jsonObject.getJSONArray("unitCodes");

        JSONArray dates = jsonObject.getJSONArray("dates");

        JSONArray months = jsonObject.getJSONArray("months");

        JSONArray statusList = jsonObject.getJSONArray("statusList");

        JSONArray types = jsonObject.getJSONArray("types");

        Integer year = jsonObject.get("year") instanceof Integer ? jsonObject.getInt("year") : null;

        SearchLoanSpecification specification = new SearchLoanSpecification();

        Specification<Loan> monthAndYearSpecifications = specification.monthAndYearSpecification(year, months, dateType);

        Specification<Loan> dateSpecification = specification.dateSpecification(dates, dateType);

        Specification<Loan> unitCodeSpecification = specification.unitCodeSpecification(unitCodes);

        Specification<Loan> typeSpecification = specification.typeSpecification(types);

        Specification<Loan> statusSpecification = specification.statusSpecification(statusList);

        return Specification.where(unitCodeSpecification)
                .and(dateSpecification).and(typeSpecification).and(statusSpecification)
                .and(monthAndYearSpecifications);

    }


}
