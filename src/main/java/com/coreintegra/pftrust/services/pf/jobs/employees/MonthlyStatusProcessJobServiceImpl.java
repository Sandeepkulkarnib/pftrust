package com.coreintegra.pftrust.services.pf.jobs.employees;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.EmployeeMonthlyContributionStatus;
import com.coreintegra.pftrust.entities.pf.employee.EmployeeMonthlyStatusReport;
import com.coreintegra.pftrust.entities.pf.employee.IoInterestMonths;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutStatus;
import com.coreintegra.pftrust.repositories.pf.contribution.ContributionRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeMonthlyContributionStatusRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeMonthlyStatusReportRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.pf.employee.IoInterestMonthsRepository;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.repositories.pf.settlement.SettlementRepository;
import com.coreintegra.pftrust.repositories.pf.settlement.SettlementStatusRepository;
import com.coreintegra.pftrust.repositories.pf.transferout.TransferOutRepository;
import com.coreintegra.pftrust.repositories.pf.transferout.TransferOutStatusRepository;
import com.coreintegra.pftrust.searchutil.SearchEmployeeSpecification;
import com.coreintegra.pftrust.services.pf.employees.MonthlyStatusProcessService;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.coreintegra.pftrust.util.NumberFormatter;
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
public class MonthlyStatusProcessJobServiceImpl implements MonthlyStatusProcessJobService {

    private final Logger logger = LoggerFactory.getLogger(MonthlyStatusProcessJobService.class);

    private final EmployeeRepository employeeRepository;
    private final CustomJobRepository customJobRepository;

    private final MonthlyStatusProcessService monthlyStatusProcessService;

    private final EmployeeMonthlyStatusReportRepository employeeMonthlyStatusReportRepository;

    public MonthlyStatusProcessJobServiceImpl(EmployeeRepository employeeRepository,
                                              CustomJobRepository customJobRepository,
                                              MonthlyStatusProcessService monthlyStatusProcessService,
                                              EmployeeMonthlyStatusReportRepository employeeMonthlyStatusReportRepository) {
        this.employeeRepository = employeeRepository;
        this.customJobRepository = customJobRepository;
        this.monthlyStatusProcessService = monthlyStatusProcessService;
        this.employeeMonthlyStatusReportRepository = employeeMonthlyStatusReportRepository;
    }


    @Override
    public Job performUnitCodeWise(String unitCode, Integer financialYear, Integer financialMonth, Boolean isDryRun, Job job) {

        Instant now = Instant.now();

        JSONArray unitCodes = new JSONArray();
        unitCodes.put(unitCode);

        SearchEmployeeSpecification employeeSpecification = new SearchEmployeeSpecification();
        Specification<Employee> unitCodeSpecification = employeeSpecification.unitCodeSpecification(unitCodes);

        Pageable pageable = PageRequest.of(0, 5000, Sort.by(Sort.Direction.ASC,
                "unitCode", "pernNumber")).first();

        boolean hasNext = true;

        int run = 1;

        while (hasNext) {

            Instant processEmployeeInstant = Instant.now();

            Page<Employee> page = employeeRepository.findAll(unitCodeSpecification, pageable);

            JSONArray details = new JSONArray();

            JSONObject jsonObject = getJobStatus(unitCode, "in progress", page.getNumberOfElements(),
                    "starting monthly status processing and saving", 0, new ArrayList<>(),
                    processEmployeeInstant, Instant.now());

            details.put(jsonObject);

            saveJobProgress(job, unitCode + " - run - " + run, details);

            logger.info("starting monthly status processing and saving");

            List<String> result = page.stream().map(employee -> {

                try {
                    return monthlyStatusProcessService.performMemberWiseAsync(employee, financialYear, financialMonth,
                            isDryRun, job).join();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return "failed";

            }).collect(Collectors.toList());

            List<String> done = result.stream()
                    .filter(r -> r.equalsIgnoreCase("done"))
                    .collect(Collectors.toList());

            List<String> failed = result.stream()
                    .filter(r -> !r.equalsIgnoreCase("done"))
                    .collect(Collectors.toList());

            JSONObject jsonObject1 = getJobStatus(unitCode, "completed", page.getNumberOfElements(),
                    "completed monthly status processing and saving", done.size(), failed,
                    processEmployeeInstant, Instant.now());

            details.put(jsonObject1);

            saveJobProgress(job, unitCode + " - run - " + run, details);

            hasNext = page.hasNext();

            pageable = page.nextPageable();

            run++;

        }

        logger.info("monthly status processing and saving completed in -> {}",
                Duration.between(now, Instant.now()).getSeconds());

        return job;
    }

    @Override
    public String generateEmployeeReport(String params) throws IOException {

        JSONObject jsonObject = new JSONObject(params);

        String jobId = jsonObject.getString("jobId");

        Job processJob = customJobRepository.findByEntityIdAndIsActive(jobId, true);

        JSONObject jobDetailsJson = processJob.getJobDetailsJson();

        if(jobDetailsJson.has("fileName")){
            String fileName = jobDetailsJson.getString("fileName");

            logger.info("report file name already exists {}", fileName);

            File file = new File(fileName);
            if(file.exists()){

                logger.info("report exists {}", file.exists());

                return fileName;
            }
        }

        logger.info("report does not exists, generating report.");

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

        String fileName = "employee_status_report_"+ DateFormatterUtil.format(new Date(), "dd_MM_yyyy_HH_mm_ss")+".xlsx";

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(1)) {

            List<String> columns = new ArrayList<>(Arrays.asList("S.NO.","Personnel Number", "Name", "PF Number",
                    "Token Number", "Unit Code","Gender", "Date Of Joining PF","Date Of Birth","Last Recovery Date",
                    "Member Contribution", "Company Contribution","EPF Contribution","Total Balance","Status",
                    "Settlement Due Date","Inoperative Date"));

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

            int srNum = 1;

            Pageable pageable = PageRequest.of(0, 5000, Sort.by(Sort.Direction.ASC,
                    "unitCode", "pernNumber")).first();

            boolean hasNext = true;

            int run = 1;

            while (hasNext) {

                logger.info("executing run {} for status report", run);

                Instant now = Instant.now();

                Page<EmployeeMonthlyStatusReport> page = employeeMonthlyStatusReportRepository
                        .findAllByJobAndIsActive(processJob, true, pageable);

                List<LinkedList<Object>> rows = new ArrayList<>();

                page.get().forEach(reportRow -> {

                    JSONObject reportRowJson = reportRow.getReportRowJson();

                    LinkedList<Object> colList = new LinkedList<>();

                    for (String column:columns) {

                        if(!column.equalsIgnoreCase("S.NO.")){
                            if(reportRowJson.has(column)){
                                colList.add(reportRowJson.get(column));
                            }else {
                                colList.add("");
                            }
                        }

                    }

                    rows.add(colList);

                });

                for (LinkedList<Object> colList : rows) {

                    colList.addFirst(rowNum);

                    addRowToSheet(workbook, sheet, rowNum, colList, cellStyle);

                    rowNum++;

                }

                hasNext = page.hasNext();

                pageable = page.nextPageable();

                logger.info("completed run {} in {} sec", run, Duration.between(now, Instant.now()).getSeconds());

                run++;

            }

            try (FileOutputStream out = new FileOutputStream(fileName)) {
                workbook.write(out);
            }

            // dispose of temporary files backing this workbook on disk
            workbook.dispose();

        }

        return fileName;
    }

    private void addRowToSheet(SXSSFWorkbook workbook, Sheet sheet, int rowNum, LinkedList<Object> colList, CellStyle style) {

        Row row = sheet.createRow(rowNum);

        for (int cellNo = 0; cellNo < colList.size(); cellNo++) {

            createCell(workbook, colList, row, cellNo, style);

        }
    }

    private void createCell(SXSSFWorkbook workbook, LinkedList<Object> colList, Row row, int cellNo, CellStyle style) {

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

    public static void applyNumericFormat(Workbook outWorkbook, Row row, Cell cell, Double value, String styleFormat, CellStyle style) {

        DataFormat format = outWorkbook.createDataFormat();
        style.setDataFormat(format.getFormat(styleFormat));

        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

}
