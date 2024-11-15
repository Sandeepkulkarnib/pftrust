package com.coreintegra.pftrust.services.pf.jobs.contributions;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.repositories.pf.contribution.ContributionRepository;
import com.coreintegra.pftrust.searchutil.SearchContributionSpecification;
import com.coreintegra.pftrust.searchutil.SearchEmployeeSpecification;
import com.coreintegra.pftrust.util.DataUtil;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.NumberFormatter;
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

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class ContributionReportJobServiceImpl implements ContributionReportJobService {

    private final ContributionRepository contributionRepository;

    public ContributionReportJobServiceImpl(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }


    @Override
    public String generateContributionReport(Job job, Instant startedAt, String params) throws Exception {
        JSONObject jsonObject = new JSONObject(params);

        Integer year = jsonObject.get("year") instanceof Integer ? jsonObject.getInt("year") : null;

        if(year == null) throw new Exception("Year is Required.");

        if(year >= 2022){
            return generateContributionReportNew(job, startedAt, params);
        }

        return generateContributionReportOld(job, startedAt, params);

    }

    private String generateContributionReportOld(Job job, Instant startedAt, String params) throws IOException {

        String fileName = "contribution_report_for_year=" +
                DateFormatterUtil.format(new Date(), "dd_MM_yyyy_HH_mm_ss") + ".xlsx";

        List<String> COLUMNS = new ArrayList<>(Arrays.asList("S.NO.", "PROVIDENT FUND",
                "PERN NO", "EMPLOEE NAME", "TOKEN NUMBER", "UNIT CODE", "DATE OF JOINING PF",
                "DATE OF JOINING", "YEAR", "MONTH", "LAST RECOVERY DATE",
                "MONTHLY PF BASE", "MEM CONTR", "COMP CONTR", "VPF CONTR",
                "TOTAL CONTR"));

        JSONObject jsonObject = new JSONObject(params);

        Specification<Contribution> specifications = getReportSpecifications(jsonObject);

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
                    Sort.by(Sort.Direction.DESC, "year", "subType")
                        .and(Sort.by(Sort.Direction.ASC, "employee.unitCode", "employee.pernNumber")))
                    .first();

            boolean hasNext = true;

            int run = 1;

            BigDecimal totalMemberContribution = BigDecimal.ZERO;
            BigDecimal totalCompanyContribution = BigDecimal.ZERO;
            BigDecimal totalVpfContribution = BigDecimal.ZERO;

            while (hasNext){

                Instant queryStart = Instant.now();

                Page<Contribution> page = contributionRepository.findAll(specifications, pageable);

                System.out.println(run + " Query completed in -> " + Duration.between(queryStart, Instant.now()).getSeconds());

                Instant employeeToColListStart = Instant.now();

                List<LinkedList<String>> rows = new ArrayList<>();

                List<Contribution> contributions = page.get().collect(Collectors.toList());

                for (Contribution contribution:contributions) {
                    LinkedList<String> colList = new LinkedList<>();

                    Employee employee = contribution.getEmployee();

                    colList.add(employee.getPfNumber());
                    colList.add(employee.getPernNumber());
                    colList.add(employee.getName());
                    colList.add(employee.getTokenNumber());
                    colList.add(employee.getUnitCode());
                    colList.add(DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
                    colList.add(DateFormatterUtil.formatDateR(employee.getDateOfJoining()));

                    colList.add(contribution.getYear().toString());
                    colList.add(contribution.getSubType().toString());

                    colList.add(contribution.getRecoveryDate() == null ? "": DateFormatterUtil.formatDateR(contribution.getRecoveryDate()));

                    colList.add(contribution.getPfBase() == null ? "0" : contribution.getPfBase().toString());

                    BigDecimal memberContribution = contribution.getMemberContribution() == null ? BigDecimal.ZERO : contribution.getMemberContribution();
                    BigDecimal companyContribution = contribution.getCompanyContribution() == null ? BigDecimal.ZERO : contribution.getCompanyContribution();
                    BigDecimal vpfContribution = contribution.getVpfContribution() == null ? BigDecimal.ZERO : contribution.getVpfContribution();

                    colList.add(memberContribution.toString());
                    colList.add(companyContribution.toString());
                    colList.add(vpfContribution.toString());

                    BigDecimal totalContribution = memberContribution.add(companyContribution).add(vpfContribution);

                    colList.add(totalContribution.toString());

                    rows.add(colList);

                    totalMemberContribution = totalMemberContribution.add(memberContribution);
                    totalCompanyContribution = totalCompanyContribution.add(companyContribution);
                    totalVpfContribution = totalVpfContribution.add(vpfContribution);

                }

                System.out.println(run + "Contribution to col list completed in -> " + Duration.between(employeeToColListStart, Instant.now()).getSeconds());

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

            Row row = sheet.createRow(rowNum);

            createCell(row, 0, String.valueOf(rowNum));

            createCell(row, 1, "");
            createCell(row, 2, "");
            createCell(row, 3, "");
            createCell(row, 4, "");
            createCell(row, 5, "");
            createCell(row, 6, "");
            createCell(row, 7, "");
            createCell(row, 8, "");
            createCell(row, 9, "");
            createCell(row, 10, "");
            createCell(row, 11, "TOTAL");

            BigDecimal totalContr = totalMemberContribution.add(totalCompanyContribution).add(totalVpfContribution);

            createCell(row, 12, totalMemberContribution.toString());
            createCell(row, 13, totalCompanyContribution.toString());
            createCell(row, 14, totalVpfContribution.toString());

            createCell(row, 15, NumberFormatter.formatNumbers(totalContr));

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


    private Specification<Contribution> getReportSpecifications(JSONObject jsonObject) {

        String reportType = jsonObject.getString("reportType");

        JSONArray unitCodes = jsonObject.getJSONArray("unitCodes");

        JSONArray dates = jsonObject.getJSONArray("dates");

        JSONArray months = jsonObject.getJSONArray("months");

        Integer year = jsonObject.get("year") instanceof Integer ? jsonObject.getInt("year") : null;

        SearchContributionSpecification specification = new SearchContributionSpecification();

        Specification<Contribution> dateSpecification = specification.dateSpecification(dates);

        Specification<Contribution> unitCodeSpecification = specification.unitCodeSpecification(unitCodes);

        Specification<Contribution> yearSpecification = specification.yearSpecification(year);

        Specification<Contribution> monthSpecification = specification.monthSpecification(months);

        if(reportType.equalsIgnoreCase("Recovery Date")){
            return Specification.where(unitCodeSpecification).and(dateSpecification);
        }

        return Specification.where(unitCodeSpecification).and(yearSpecification).and(monthSpecification);

    }


    private String generateContributionReportNew(Job job, Instant startedAt, String params) throws IOException {

        String fileName = "contribution_report_for_year=" +
                DateFormatterUtil.format(new Date(), "dd_MM_yyyy_HH_mm_ss") + ".xlsx";

        List<String> COLUMNS = new ArrayList<>(Arrays.asList("S.NO.", "PROVIDENT FUND",
                "PERN NO", "EMPLOEE NAME", "TOKEN NUMBER", "UNIT CODE", "DATE OF JOINING PF",
                "DATE OF JOINING", "YEAR", "MONTH", "LAST RECOVERY DATE",
                "MONTHLY PF BASE", "NON TAXABLE MEM CONTR", "TAXABLE MEM CONTR", "TOTAL MEM CONTR",
                "COMP CONTR", "NON TAXABLE VPF CONTR", "TAXABLE VPF CONTR", "VPF CONTR",
                "TOTAL CONTR"));

        JSONObject jsonObject = new JSONObject(params);

        Specification<Contribution> specifications = getReportSpecifications(jsonObject);

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
                            Sort.by(Sort.Direction.DESC, "year", "subType")
                                    .and(Sort.by(Sort.Direction.ASC, "employee.unitCode", "employee.pernNumber")))
                    .first();

            boolean hasNext = true;

            int run = 1;

            BigDecimal nonTaxableTotalMemberContribution = BigDecimal.ZERO;
            BigDecimal taxableTotalMemberContribution = BigDecimal.ZERO;
            BigDecimal totalMemberContribution = BigDecimal.ZERO;

            BigDecimal totalCompanyContribution = BigDecimal.ZERO;

            BigDecimal nonTaxableTotalVpfContribution = BigDecimal.ZERO;
            BigDecimal taxableTotalVpfContribution = BigDecimal.ZERO;
            BigDecimal totalVpfContribution = BigDecimal.ZERO;


            while (hasNext){

                Instant queryStart = Instant.now();

                Page<Contribution> page = contributionRepository.findAll(specifications, pageable);

                System.out.println(run + " Query completed in -> " + Duration.between(queryStart, Instant.now()).getSeconds());

                Instant employeeToColListStart = Instant.now();

                List<LinkedList<String>> rows = new ArrayList<>();

                List<Contribution> contributions = page.get().collect(Collectors.toList());

                for (Contribution contribution:contributions) {
                    LinkedList<String> colList = new LinkedList<>();

                    Employee employee = contribution.getEmployee();

                    colList.add(employee.getPfNumber());
                    colList.add(employee.getPernNumber());
                    colList.add(employee.getName());
                    colList.add(employee.getTokenNumber());
                    colList.add(employee.getUnitCode());
                    colList.add(DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()));
                    colList.add(DateFormatterUtil.formatDateR(employee.getDateOfJoining()));

                    colList.add(contribution.getYear().toString());
                    colList.add(contribution.getSubType().toString());

                    colList.add(contribution.getRecoveryDate() == null ? "": DateFormatterUtil.formatDateR(contribution.getRecoveryDate()));

                    colList.add(contribution.getPfBase() == null ? "0" : contribution.getPfBase().toString());

                    BigDecimal nonTaxableMemberContribution = DataUtil.avoidNull(contribution.getNonTaxableMemberContribution());
                    BigDecimal taxableMemberContribution = DataUtil.avoidNull(contribution.getTaxableMemberContribution());
                    BigDecimal memberContribution = DataUtil.avoidNull(contribution.getMemberContribution());

                    BigDecimal companyContribution =  DataUtil.avoidNull(contribution.getCompanyContribution());

                    BigDecimal nonTaxableVpfContribution = DataUtil.avoidNull(contribution.getNonTaxableVpfContribution());
                    BigDecimal taxableVpfContribution = DataUtil.avoidNull(contribution.getTaxableVpfContribution());
                    BigDecimal vpfContribution = DataUtil.avoidNull(contribution.getVpfContribution());

                    colList.add(nonTaxableMemberContribution.toString());
                    colList.add(taxableMemberContribution.toString());
                    colList.add(memberContribution.toString());

                    colList.add(companyContribution.toString());

                    colList.add(nonTaxableVpfContribution.toString());
                    colList.add(taxableVpfContribution.toString());
                    colList.add(vpfContribution.toString());

                    BigDecimal totalContribution = memberContribution.add(companyContribution).add(vpfContribution);

                    colList.add(totalContribution.toString());

                    rows.add(colList);

                    nonTaxableTotalMemberContribution = nonTaxableTotalMemberContribution.add(nonTaxableMemberContribution);
                    taxableTotalMemberContribution = taxableTotalMemberContribution.add(taxableMemberContribution);
                    totalMemberContribution = totalMemberContribution.add(memberContribution);

                    totalCompanyContribution = totalCompanyContribution.add(companyContribution);

                    nonTaxableTotalVpfContribution = nonTaxableTotalVpfContribution.add(nonTaxableVpfContribution);
                    taxableTotalVpfContribution = taxableTotalVpfContribution.add(taxableVpfContribution);
                    totalVpfContribution = totalVpfContribution.add(vpfContribution);

                }

                System.out.println(run + "Contribution to col list completed in -> " + Duration.between(employeeToColListStart, Instant.now()).getSeconds());

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

            Row row = sheet.createRow(rowNum);

            createCell(row, 0, String.valueOf(rowNum));

            createCell(row, 1, "");
            createCell(row, 2, "");
            createCell(row, 3, "");
            createCell(row, 4, "");
            createCell(row, 5, "");
            createCell(row, 6, "");
            createCell(row, 7, "");
            createCell(row, 8, "");
            createCell(row, 9, "");
            createCell(row, 10, "");
            createCell(row, 11, "TOTAL");

            BigDecimal totalContr = totalMemberContribution.add(totalCompanyContribution).add(totalVpfContribution);

            createCell(row, 12, nonTaxableTotalMemberContribution.toString());
            createCell(row, 13, taxableTotalMemberContribution.toString());
            createCell(row, 14, totalMemberContribution.toString());

            createCell(row, 15, totalCompanyContribution.toString());

            createCell(row, 16, nonTaxableTotalVpfContribution.toString());
            createCell(row, 17, taxableTotalVpfContribution.toString());
            createCell(row, 18, totalVpfContribution.toString());

            createCell(row, 19, NumberFormatter.formatNumbers(totalContr));

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

}
