package com.coreintegra.pftrust.services.pf.jobs.yearend;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndReport;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.repositories.pf.yearend.YearEndReportRepository;
import com.coreintegra.pftrust.searchutil.SearchEmployeeSpecification;
import com.coreintegra.pftrust.services.pf.department.UnitCodeService;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.services.pf.yearend.YearEndProcessService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class YearEndProcessJobServiceImpl implements YearEndProcessJobService {

    private final Logger logger = LoggerFactory.getLogger(YearEndProcessJobService.class);

    private final EmployeeService employeeService;
    private final UnitCodeService unitCodeService;
    private final CustomJobRepository customJobRepository;
    private final YearEndProcessService yearEndProcessService;
    private final EmployeeRepository employeeRepository;
    private final YearEndReportRepository yearEndReportRepository;

    public YearEndProcessJobServiceImpl(EmployeeService employeeService, UnitCodeService unitCodeService,
                                        CustomJobRepository customJobRepository,
                                        YearEndProcessService yearEndProcessService,
                                        EmployeeRepository employeeRepository,
                                        YearEndReportRepository yearEndReportRepository) {
        this.employeeService = employeeService;
        this.unitCodeService = unitCodeService;
        this.customJobRepository = customJobRepository;
        this.yearEndProcessService = yearEndProcessService;
        this.employeeRepository = employeeRepository;
        this.yearEndReportRepository = yearEndReportRepository;
    }

    @Override
    public Job performForEmployees(Set<String> pernNumbers, Boolean isDryRun, String processFor, Job job) {

        for (String pernNumber:pernNumbers) {

            Instant processEmployeeInstant = Instant.now();

            Employee employee = employeeService.getEmployeeByPern(pernNumber);

            JSONArray details = new JSONArray();

            JSONObject jsonObject = getJobStatus(employee.getPernNumber(), "in progress", 1,
                    "starting year end process", 0, new ArrayList<>(),
                    processEmployeeInstant, Instant.now());

            details.put(jsonObject);

            saveJobProgress(job, employee.getPernNumber(), details);

            try {

                if(processFor.equalsIgnoreCase(YearEndProcess.PROCESS_FOR_LOAN)){

                    yearEndProcessService.performForLoan(employee,
                            FinancialYearAndMonth.getFinancialYear(new Date()) - 1, getDate(), isDryRun, job);

                }

                if(processFor.equalsIgnoreCase(YearEndProcess.PROCESS_FOR_SETTLEMENT) ||
                        processFor.equalsIgnoreCase(YearEndProcess.PROCESS_FOR_YEAR_END)){

                    yearEndProcessService.performForSettlement(employee,
                            FinancialYearAndMonth.getFinancialYear(new Date()) - 1, getDate(), isDryRun, job);

                }

                JSONObject jsonObject1 = getJobStatus(employee.getPernNumber(), "completed", 1,
                        "completed year end process", 1, new ArrayList<>(),
                        processEmployeeInstant, Instant.now());

                details.put(jsonObject1);

                saveJobProgress(job, employee.getPernNumber(), details);

            }catch (Exception e) {

                e.printStackTrace();

                JSONObject jsonObject1 = getJobStatus(employee.getPernNumber(), "failed", 1,
                        "completed year end process", 0, List.of(e.getMessage()),
                        processEmployeeInstant, Instant.now());

                details.put(jsonObject1);

                saveJobProgress(job, employee.getPernNumber(), details);

            }

        }

        return job;

    }

    @Override
    public Job performForUnitCode(Set<String> unitCodes, Boolean isDryRun, String processFor, Job job) {
        for (String unitCode:unitCodes) {
            performForUnitCode(unitCode, isDryRun, processFor, job);
        }
        return job;
    }

    private Job performForUnitCode(String unitCode, Boolean isDryRun, String processFor, Job job){

        JSONArray unitCodes = new JSONArray();
        unitCodes.put(unitCode);

        SearchEmployeeSpecification employeeSpecification = new SearchEmployeeSpecification();
        Specification<Employee> unitCodeSpecification = employeeSpecification.unitCodeSpecification(unitCodes);

        Specification<Employee> statusSpecification = employeeSpecification.contributionStatusSpecification(
                List.of("A", "I", "IO", "F")
        );

        Specification<Employee> specification = unitCodeSpecification.and(statusSpecification);

        Pageable pageable = PageRequest.of(0, 5000, Sort.by(Sort.Direction.ASC,
                "unitCode", "pernNumber")).first();

        boolean hasNext = true;

        int run = 1;

        while (hasNext) {

            Instant processEmployeeInstant = Instant.now();

            Page<Employee> page = employeeRepository.findAll(specification, pageable);

            JSONArray details = new JSONArray();

            JSONObject jsonObject = getJobStatus(unitCode, "in progress", page.getNumberOfElements(),
                    "starting year end process", 0, new ArrayList<>(),
                    processEmployeeInstant, Instant.now());

            details.put(jsonObject);

            saveJobProgress(job, unitCode + " - run - " + run, details);

            List<String> result = page.stream().map(employee -> {

                try {

                    if(processFor.equalsIgnoreCase(YearEndProcess.PROCESS_FOR_LOAN)){

                        return yearEndProcessService.performForLoanAsync(employee,
                                FinancialYearAndMonth.getFinancialYear(new Date())-1, getDate(), isDryRun, job)
                                .join();

                    }

                    if(processFor.equalsIgnoreCase(YearEndProcess.PROCESS_FOR_SETTLEMENT)){

                        return yearEndProcessService.performForSettlementAsync(employee,
                                FinancialYearAndMonth.getFinancialYear(new Date())-1, getDate(), isDryRun, job)
                                .join();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return e.getMessage();
                }

                return "done";

            }).collect(Collectors.toList());

            List<String> done = result.stream()
                    .filter(r -> r.equalsIgnoreCase("done"))
                    .collect(Collectors.toList());

            List<String> failed = result.stream()
                    .filter(r -> !r.equalsIgnoreCase("done"))
                    .collect(Collectors.toList());

            JSONObject jsonObject1 = getJobStatus(unitCode, "completed", page.getNumberOfElements(),
                    "completed year end process", done.size(), failed,
                    processEmployeeInstant, Instant.now());

            details.put(jsonObject1);

            saveJobProgress(job, unitCode + " - run - " + run, details);

            hasNext = page.hasNext();

            pageable = page.nextPageable();

            run++;

        }

        return job;
    }



    @Override
    public Job perform(Boolean isDryRun, String processFor, Job job) {

        List<UnitCode> activeUnitCodes = unitCodeService.getActiveUnitCodes();

        activeUnitCodes.forEach(unitCode -> performForUnitCode(unitCode.unitCode, isDryRun, processFor, job));

        return job;

    }

    @Override
    public String generateReport(String params) throws IOException {

        JSONObject jsonObject = new JSONObject(params);

        String jobId = jsonObject.getString("jobId");

        Job processJob = customJobRepository.findByEntityIdAndIsActive(jobId, true);

        JSONObject jobDetailsJson = processJob.getJobDetailsJson();

        String report = generateEmployeeReport(processJob);

        logger.info("report generated -> {}", report);

        jobDetailsJson.put("fileName", report);

        processJob.setJobDetails(jobDetailsJson.toString());

        logger.info("save report file name in process job");

        customJobRepository.save(processJob);

        logger.info("saved");


        Job job = customJobRepository.findByEntityIdAndIsActive(jobId, true);

        logger.info(job.getJobDetailsJson().getString("fileName"));

        return report;

    }

    @ApplyTenantFilter
    public String generateEmployeeReport(Job processJob) throws IOException {

        String fileName = "year_end_report_"+ DateFormatterUtil.format(new Date(), "dd_MM_yyyy_HH_mm_ss")+".xlsx";

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(1)) {

            List<String> columns = new ArrayList<>(Arrays.asList(
                    "Personnel number",
                    "PF Number",
                    "Unit Code",
                    "Token Number",
                    "Name",
                    "DATE OF JOINING PF TRUST",
                    "DATE OF BIRTH",
                    "LAST RECOVERY DATE",
                    "AGE",
                    "INOPERATIVE DATE AS PER STATUS REPORT",
                    "STATUS AS PER STATUS REPORT",
                    "Mem Open Bal",
                    "Com open Bal",
                    "EPF Open Bal",
                    "Mem Open INT",
                    "Com Open INT",
                    "EPF Open INT",
                    "April Non Tax Member Cont",
                    "May Non Tax Member Cont",
                    "June Non Tax Member Cont",
                    "July Non Tax Member Cont",
                    "August Non Tax Member Cont",
                    "September Non Tax Member Cont",
                    "October Non Tax Member Cont",
                    "November Non Tax Member Cont",
                    "December Non Tax Member Cont",
                    "January Non Tax Member Cont",
                    "February Non Tax Member Cont",
                    "March Non Tax Member Cont",
                    "April Tax Member Cont",
                    "May Tax Member Cont",
                    "June Tax Member Cont",
                    "July Tax Member Cont",
                    "August Tax Member Cont",
                    "September Tax Member Cont",
                    "October Tax Member Cont",
                    "November Tax Member Cont",
                    "December Tax Member Cont",
                    "January Tax Member Cont",
                    "February Tax Member Cont",
                    "March Tax Member Cont",
                    "April Company Cont",
                    "May Company Cont",
                    "June Company Cont",
                    "July Company Cont",
                    "August Company Cont",
                    "September Company Cont",
                    "October Company Cont",
                    "November Company Cont",
                    "December Company Cont",
                    "January Company Cont",
                    "February Company Cont",
                    "March Company Cont",
                    "April Non Tax EPF Cont",
                    "May Non Tax EPF Cont",
                    "June Non Tax EPF Cont",
                    "July Non Tax EPF Cont",
                    "August Non Tax EPF Cont",
                    "September Non Tax EPF Cont",
                    "October Non Tax EPF Cont",
                    "November Non Tax EPF Cont",
                    "December Non Tax EPF Cont",
                    "January Non Tax EPF Cont",
                    "February Non Tax EPF Cont",
                    "March Non Tax EPF Cont",
                    "April Tax EPF Cont",
                    "May Tax EPF Cont",
                    "June Tax EPF Cont",
                    "July Tax EPF Cont",
                    "August Tax EPF Cont",
                    "September Tax EPF Cont",
                    "October Tax EPF Cont",
                    "November Tax EPF Cont",
                    "December Tax EPF Cont",
                    "January Tax EPF Cont",
                    "February Tax EPF Cont",
                    "March Tax EPF Cont",
                    "April Non Tax Member Interest",
                    "May Non Tax Member Interest",
                    "June Non Tax Member Interest",
                    "July Non Tax Member Interest",
                    "August Non Tax Member Interest",
                    "September Non Tax Member Interest",
                    "October Non Tax Member Interest",
                    "November Non Tax Member Interest",
                    "December Non Tax Member Interest",
                    "January Non Tax Member Interest",
                    "February Non Tax Member Interest",
                    "March Non Tax Member Interest",
                    "April Tax Member Interest",
                    "May Tax Member Interest",
                    "June Tax Member Interest",
                    "July Tax Member Interest",
                    "August Tax Member Interest",
                    "September Tax Member Interest",
                    "October Tax Member Interest",
                    "November Tax Member Interest",
                    "December Tax Member Interest",
                    "January Tax Member Interest",
                    "February Tax Member Interest",
                    "March Tax Member Interest",
                    "April Company Interest",
                    "May Company Interest",
                    "June Company Interest",
                    "July Company Interest",
                    "August Company Interest",
                    "September Company Interest",
                    "October Company Interest",
                    "November Company Interest",
                    "December Company Interest",
                    "January Company Interest",
                    "February Company Interest",
                    "March Company Interest",
                    "April Non Tax EPF Interest",
                    "May Non Tax EPF Interest",
                    "June Non Tax EPF Interest",
                    "July Non Tax EPF Interest",
                    "August Non Tax EPF Interest",
                    "September Non Tax EPF Interest",
                    "October Non Tax EPF Interest",
                    "November Non Tax EPF Interest",
                    "December Non Tax EPF Interest",
                    "January Non Tax EPF Interest",
                    "February Non Tax EPF Interest",
                    "March Non Tax EPF Interest",
                    "April Tax EPF Interest",
                    "May Tax EPF Interest",
                    "June Tax EPF Interest",
                    "July Tax EPF Interest",
                    "August Tax EPF Interest",
                    "September Tax EPF Interest",
                    "October Tax EPF Interest",
                    "November Tax EPF Interest",
                    "December Tax EPF Interest",
                    "January Tax EPF Interest",
                    "February Tax EPF Interest",
                    "March Tax EPF Interest",
                    "Member Loan",
                    "Company Loan",
                    "EPF Loan",
                    "Member Loan Interest",
                    "Company Loan Interest",
                    "EPF Loan Interest",
                    "Member Trans In",
                    "Company Trans In",
                    "Member Trans In Interest",
                    "Company Trans In Interest",
                    "GRAND TOTAL",
                    "NON_TAX_MEM_CONT_INT_TI_A",
                    "TAX_MEM_CONT_INT_TI_A",
                    "MEM_CONT_INT_TI_A",
                    "TDS_MEM_CONT_INT_TI_A",
                    "COMP_CONT_INT_TI_B",
                    "NON_TAX_EPF_CONT_IBT_TI_C",
                    "TAX_EPF_CONT_IBT_TI_C",
                    "EPF_CONT_IBT_TI_C",
                    "TDS_EPF_CONT_IBT_TI_C"
                    ));

            Sheet sheet = workbook.createSheet();

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            CellStyle cellStyle = workbook.createCellStyle();

            // Row for Header
            Row headerRow = sheet.createRow(0);


            // Header
            for (int col = 0; col < columns.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns.get(col));
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;

            Pageable pageable = PageRequest.of(0, 5000, Sort.by(Sort.Direction.ASC,
                    "unitCode", "pernNumber")).first();

            boolean hasNext = true;

            int run = 1;

            while (hasNext) {

                logger.info("executing run {} for status report", run);

                Instant now = Instant.now();

                Page<YearEndReport> page = yearEndReportRepository
                        .findAllByJobAndIsActive(processJob, true, pageable);

                List<LinkedList<Object>> rows = new ArrayList<>();

                page.get().forEach(reportRow -> {

                    JSONObject reportRowJson = reportRow.getReportRowJson();

                    LinkedList<Object> colList = new LinkedList<>();

                    for (String column:columns) {
                        if(reportRowJson.has(column)){
                            colList.add(reportRowJson.get(column));
                        }else {
                            colList.add("");
                        }
                    }

                    rows.add(colList);

                });

                for (LinkedList<Object> colList : rows) {

                    addRowToSheet(workbook, sheet, rowNum, colList, cellStyle);

                    rowNum++;
                }

                hasNext = page.hasNext();

                pageable = page.nextPageable();

                logger.info("completed run {} in {} sec", run, Duration.between(now, Instant.now()).getSeconds());

                run++;

            }

            Row row = sheet.createRow(rowNum);

            for(int k=11; k<156; k++){

                Cell cell = row.createCell(k);

                String address = cell.getAddress().formatAsString();

                String cellId = address.replaceAll("[^a-zA-Z]+", "");

                cell.setCellFormula("SUM(" + cellId + "2:" + cellId + "" + rowNum + ")");

            }

            try (FileOutputStream out = new FileOutputStream(fileName)) {
                workbook.write(out);
            }

            // dispose of temporary files backing this workbook on disk
            workbook.dispose();

        }

        return fileName;
    }

    private void addRowToSheet(SXSSFWorkbook workbook, Sheet sheet, int rowNum,
                               LinkedList<Object> colList, CellStyle style) {

        Row row = sheet.createRow(rowNum);

        for (int cellNo = 0; cellNo < colList.size(); cellNo++) {

            createCell(workbook, colList, row, cellNo, style);

        }

    }

    private void createCell(SXSSFWorkbook workbook, LinkedList<Object> colList, Row row, int cellNo,
                            CellStyle style) {

        Cell cell = row.createCell(cellNo);

        if (colList.get(cellNo) instanceof Integer){

            Integer integer = (Integer) colList.get(cellNo);

            if(integer > 0){
                applyNumericFormat(workbook, row, cell, integer.doubleValue(), "##,##,###.00", style);
            }else {
                applyNumericFormat(workbook, row, cell, integer.doubleValue(), "#0.00", style);
            }

        }
        else if (colList.get(cellNo) instanceof Double) {

            Double aDouble = (Double) colList.get(cellNo);

            applyNumericFormat(workbook, row, cell, aDouble, "##,##,###.##", style);
        }
        else {

            cell.setCellValue(colList.get(cellNo).toString());

        }

    }

    private JSONObject getJobStatus(String unitCode, String status, Integer total, String message, Integer success,
                                    List<String> failed, Instant startedAt, Instant completedAt) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("unitCode", unitCode);
        jsonObject.put("status", status);
        jsonObject.put("total", total);
        jsonObject.put("message", message);
        jsonObject.put("success", success);
        jsonObject.put("failed", failed.size());

        if(failed.size() > 0){
            JSONArray failedLoans = new JSONArray();
            failed.forEach(failedLoans::put);
            jsonObject.put("failedLoans", failedLoans);
        }

        jsonObject.put("startedAt", startedAt);
        jsonObject.put("completedAt", completedAt);
        jsonObject.put("duration", Duration.between(startedAt, completedAt).getSeconds());

        return jsonObject;
    }


    private void saveJobProgress(Job job, String unitCode, JSONArray details) {

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        if(jobDetailsJson.has("details")){
            jobDetailsJson.getJSONObject("details").put(unitCode, details);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(unitCode, details);
            jobDetailsJson.put("details", jsonObject);
        }

        job.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job);

    }

    private Date getDate(){

        Calendar myCal = Calendar.getInstance();

        myCal.set(Calendar.YEAR, FinancialYearAndMonth.getFinancialYear(new Date()) - 1);
        myCal.set(Calendar.MONTH, 2);
        myCal.set(Calendar.DAY_OF_MONTH, 31);

        return myCal.getTime();

    }

    public static void applyNumericFormat(Workbook outWorkbook, Row row, Cell cell, Double value,
                                          String styleFormat, CellStyle style) {

        DataFormat format = outWorkbook.createDataFormat();
        style.setDataFormat(format.getFormat(styleFormat));

        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

}
