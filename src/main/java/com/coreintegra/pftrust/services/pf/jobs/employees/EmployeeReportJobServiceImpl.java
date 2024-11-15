package com.coreintegra.pftrust.services.pf.jobs.employees;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.*;
import com.coreintegra.pftrust.reports.employee.EmployeeReportUtil;
import com.coreintegra.pftrust.repositories.pf.contribution.ContributionRepository;
import com.coreintegra.pftrust.repositories.pf.employee.*;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.searchutil.SearchEmployeeSpecification;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class EmployeeReportJobServiceImpl implements EmployeeReportJobService {

    private final EmployeeRepository employeeRepository;
    private final NomineeRepository nomineeRepository;
    private final PreviousCompanyRepository previousCompanyRepository;
    private final ContributionRepository contributionRepository;
    private final CustomJobRepository customJobRepository;
    private final AddressRepository addressRepository;
    private final BankDetailsRepository bankDetailsRepository;

    public EmployeeReportJobServiceImpl(EmployeeRepository employeeRepository, NomineeRepository nomineeRepository,
                                        PreviousCompanyRepository previousCompanyRepository,
                                        ContributionRepository contributionRepository, CustomJobRepository customJobRepository,
                                        AddressRepository addressRepository, BankDetailsRepository bankDetailsRepository) {
        this.employeeRepository = employeeRepository;
        this.nomineeRepository = nomineeRepository;
        this.previousCompanyRepository = previousCompanyRepository;
        this.contributionRepository = contributionRepository;
        this.customJobRepository = customJobRepository;
        this.addressRepository = addressRepository;
        this.bankDetailsRepository = bankDetailsRepository;
    }

    @Override
    @ApplyTenantFilter
    public String generateEmployeeReport(Job job, Instant startedAt, String params) throws IOException {

        JSONObject jsonObject = new JSONObject(params);

        Specification<Employee> reportSpecifications = getReportSpecifications(jsonObject);

        String fileName = "employee_report_"+DateFormatterUtil.format(new Date(), "dd_MM_yyyy_HH_mm_ss")+".xlsx";

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(1)) {

            List<String> columns = EmployeeReportUtil.getCOLUMNS();

            Sheet sheet = workbook.createSheet();

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);


            // Header
            for (int col = 0; col < columns.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns.get(col));
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;

            Pageable pageable = PageRequest.of(0, 5000, Sort.by(Sort.Direction.ASC, "unitCode", "pernNumber")).first();

            boolean hasNext = true;

            int run = 1;

            while (hasNext) {

                Instant now = Instant.now();

                JSONArray jsonArray = new JSONArray();

                saveJobStatus(job, run, "in progress", 0L, now, now, jsonArray);

                Page<Employee> page = employeeRepository.findAll(reportSpecifications, pageable);

                List<LinkedList<String>> rows = new ArrayList<>();

                page.get().forEach(employee -> {

                    Optional<Contribution> contribution = contributionRepository
                            .findTop1ByEmployeeAndSubTypeGreaterThanAndIsActiveOrderByYearDescSubTypeDesc(
                                    employee, 0, true);

                    LinkedList<String> colList = getColumValues(employee, contribution.orElse(new Contribution()));

                    rows.add(colList);

                    System.out.println("completed for -> " + employee.getId());

                });

                rowNum = addRowsAndValuesToSheet(sheet, rowNum, rows);

                hasNext = page.hasNext();

                pageable = page.nextPageable();

                saveJobStatus(job, run, "completed", page.getTotalElements(), now, Instant.now(), jsonArray);

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

    private Specification<Employee> getReportSpecifications(JSONObject jsonObject) {

        String dateType = jsonObject.getString("dateType");

        JSONArray unitCodes = jsonObject.getJSONArray("unitCodes");

        JSONArray dates = jsonObject.getJSONArray("dates");

        JSONArray contributionStatusList = jsonObject.getJSONArray("contributionStatusList");

        SearchEmployeeSpecification specification = new SearchEmployeeSpecification();

        Specification<Employee> unitCodeSpecification = specification.unitCodeSpecification(unitCodes);

        Specification<Employee> dateSpecification = specification.dateSpecification(dateType, dates);

        Specification<Employee> contributionStatusSpecification = specification.contributionStatusSpecification(contributionStatusList);

        if(unitCodeSpecification != null && dateSpecification != null && contributionStatusSpecification != null){
            return unitCodeSpecification.and(dateSpecification).and(contributionStatusSpecification);
        }

        return Specification.where(unitCodeSpecification).and(dateSpecification).and(contributionStatusSpecification);

    }

    private LinkedList<String> getColumValues(Employee employee, Contribution contribution) {

        LinkedList<String> colList = new LinkedList<>();

        addColumn(colList, employee.getPfNumber(), employee.getPernNumber(), employee.getName(),
                employee.getTokenNumber(), employee.getUnitCode(),
                DateFormatterUtil.formatDateR(employee.getDateOfJoiningPF()),
                DateFormatterUtil.formatDateR(employee.getDateOfJoining()),
                employee.getUanNumber(), DateFormatterUtil.formatDateR(employee.getDateOfBirth()),
                employee.getLocation(), employee.getPayrollArea(), employee.getContactNumber(),
                employee.getAlternateContactNumber(), employee.getEmail(), employee.getPersonalEmail(),
                employee.getDesignation(), employee.getFromEPS(), employee.getToEPS(),
                employee.getPanNumber(), employee.getAadharNumber());

        addColumn(colList, DateFormatterUtil.formatDateR(contribution.getRecoveryDate()),
                NumberFormatter.formatNumbers(contribution.getPfBase()), employee.getGender().getSymbol(),
                nullToString(employee.getContributionStatus().getSymbol()),
                employee.getDepartment() == null ? "" : employee.getDepartment().getTitle());

        addAddresses(employee, colList);

        addBankDetails(employee, colList);

        addPreviousCompanies(employee, colList);

        addNominees(employee, colList);

        return colList;
    }

    private int addRowsAndValuesToSheet(Sheet sheet, int rowNum, List<LinkedList<String>> rows) {
        for (LinkedList<String> colList : rows) {

            Row row = sheet.createRow(rowNum);

            createCell(row, 0, String.valueOf(rowNum));

            for (int cellNo = 0; cellNo < colList.size(); cellNo++) {

                createCell(row, cellNo + 1, colList.get(cellNo));

            }

            rowNum++;
        }
        return rowNum;
    }

    private void addColumn(LinkedList<String> colList, String... values) {
        colList.addAll(Arrays.asList(values));
    }

    private void addNominees(Employee employee, LinkedList<String> colList) {

        List<Nominee> nominees = nomineeRepository.findAllByEmployeeAndIsActive(employee, true);

        int nomineeCount = 4;
        if (nominees != null && nominees.size() > 0) {
            nominees.forEach(nominee -> {
                addColumn(colList, nominee.getName(), nominee.getRelationship(),
                        nominee.getShare().toString());
            });
            nomineeCount = nomineeCount - nominees.size();
        }

        addBlankColumns(colList, nomineeCount, 3);
    }

    private void addPreviousCompanies(Employee employee, LinkedList<String> colList) {

        List<PreviousCompany> previousCompanies = previousCompanyRepository
                .findAllByEmployeeAndIsActive(employee, true);

        int prevCompanyCount = 6;

        if (previousCompanies.size() > 0) {
            previousCompanies.forEach(company -> {
                addColumn(colList, company.getName(),
                        DateFormatterUtil.formatDateR(company.getDateOfJoining()),
                        DateFormatterUtil.formatDateR(company.getDateOfLeaving()),
                        company.getAddressLine1(), company.getAddressLine2(),
                        company.getAddressLine3(), company.getAddressLine4());
            });
            prevCompanyCount = prevCompanyCount - previousCompanies.size();
        }

        addBlankColumns(colList, prevCompanyCount, 7);
    }

    private void addBlankColumns(LinkedList<String> colList, int colCount, int repeat) {
        for (int i = 0; i < colCount; i++) {
            for(int k = 0; k < repeat; k++) {
                addColumn(colList, "");
            }
        }
    }

    private void addBankDetails(Employee employee, LinkedList<String> colList) {

//        List<BankDetails> bankDetailsList = employee.getBankDetailsList();

        List<BankDetails> bankDetailsList = bankDetailsRepository.findAllByEmployeeAndIsActive(employee, true);

        BankDetails bankDetails = bankDetailsList.size() > 0 ? bankDetailsList.get(0) : new BankDetails();

        addColumn(colList, nullToString(bankDetails.getName()), nullToString(bankDetails.getAccountNumber()),
                nullToString(bankDetails.getIfscCode()), nullToString(bankDetails.getMicrCode()));

    }

    private void addAddresses(Employee employee, LinkedList<String> colList) {

//        List<Address> addresses = employee.getAddresses();

        List<Address> addresses = addressRepository.findAllByEmployeeAndIsActive(employee, true);

        Address address = addresses.size() > 0 ? addresses.get(0) : new Address();

        addColumn(colList, nullToString(address.getAddressLine1()), nullToString(address.getAddressLine2()),
                nullToString(address.getAddressLine3()), nullToString(address.getAddressLine4()));

    }

    private String nullToString(Object o){
        return o == null ? "" : o.toString();
    }

    private void saveJobStatus(Job job, int run, String status, Long total, Instant startedAt,
                               Instant completedAt, JSONArray jsonArray) {

        JSONObject jobStatus = getJobStatus("run-" + run, status, total, startedAt,
                completedAt);

        jsonArray.put(jobStatus);

        saveJobProgress(job, "run-" + run, jsonArray);
    }

    private void saveJobProgress(Job job, String key, JSONArray details) {

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        if(jobDetailsJson.has("details")){
            jobDetailsJson.getJSONObject("details").put(key, details);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(key, details);
            jobDetailsJson.put("details", jsonObject);
        }

        job.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job);

    }

    private JSONObject getJobStatus(String run, String status, Long total,
                                    Instant startedAt, Instant completedAt) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("run", run);
        jsonObject.put("status", status);
        jsonObject.put("total", total);
        jsonObject.put("startedAt", startedAt);
        jsonObject.put("completedAt", completedAt);
        jsonObject.put("duration", Duration.between(startedAt, completedAt).getSeconds());

        return jsonObject;
    }

}
