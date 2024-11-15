package com.coreintegra.pftrust.services.pf.jobs;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanDocument;
import com.coreintegra.pftrust.entities.pf.loan.LoanType;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.department.UnitCodeRepository;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.services.pf.jobs.contributions.FetchContributionsJob;
import com.coreintegra.pftrust.services.pf.jobs.employees.FetchEmployeesJob;
import com.coreintegra.pftrust.services.pf.jobs.employees.MonthlyStatusProcessJobService;
import com.coreintegra.pftrust.services.pf.jobs.loans.FetchLoansJob;
import com.coreintegra.pftrust.services.pf.jobs.settlements.FetchSettlementsJob;
import com.coreintegra.pftrust.services.pf.jobs.transferin.FetchTransferInsJob;
import com.coreintegra.pftrust.services.pf.jobs.yearend.YearEndProcessJobService;
import com.coreintegra.pftrust.services.pf.loans.LoanService;
import com.coreintegra.pftrust.util.DataUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class JobServiceImpl implements JobService {

    private final Logger logger = LoggerFactory.getLogger(JobService.class);

    private final UnitCodeRepository unitCodeRepository;
    private final CustomJobRepository customJobRepository;
    private final FetchEmployeesJob fetchEmployeesJob;
    private final FetchContributionsJob fetchContributionsJob;
    private final FetchLoansJob fetchLoansJob;
    private final FetchTransferInsJob fetchTransferInsJob;
    private final FetchSettlementsJob fetchSettlementsJob;
    private final MonthlyStatusProcessJobService monthlyStatusProcessJobService;
    private final YearEndProcessJobService yearEndProcessJobService;
    private final EmployeeService employeeService;
    private final LoanService loanService;

    public JobServiceImpl(UnitCodeRepository unitCodeRepository, CustomJobRepository customJobRepository,
                          FetchEmployeesJob fetchEmployeesJob, FetchContributionsJob fetchContributionsJob,
                          FetchLoansJob fetchLoansJob, FetchTransferInsJob fetchTransferInsJob,
                          FetchSettlementsJob fetchSettlementsJob,
                          MonthlyStatusProcessJobService monthlyStatusProcessJobService,
                          YearEndProcessJobService yearEndProcessJobService,
                          EmployeeService employeeService, LoanService loanService) {
        this.unitCodeRepository = unitCodeRepository;
        this.customJobRepository = customJobRepository;
        this.fetchEmployeesJob = fetchEmployeesJob;
        this.fetchContributionsJob = fetchContributionsJob;
        this.fetchLoansJob = fetchLoansJob;
        this.fetchTransferInsJob = fetchTransferInsJob;
        this.fetchSettlementsJob = fetchSettlementsJob;
        this.monthlyStatusProcessJobService = monthlyStatusProcessJobService;
        this.yearEndProcessJobService = yearEndProcessJobService;
        this.employeeService = employeeService;
        this.loanService = loanService;
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void fetchEmployeesAsync(Job job, Instant startedAt, String params) {

        List<String> selectedUnitCodes = new ArrayList<>();

        if(params != null && !params.isEmpty()){

            JSONObject parameters = new JSONObject(params);

            JSONArray unitcodes = parameters.getJSONArray("unitcodes");

            if(unitcodes.length() > 0){
                for (int i=0; i<unitcodes.length(); i++){
                    selectedUnitCodes.add(unitcodes.getString(i));
                }
            }

        }else {

            List<UnitCode> unitCodes = unitCodeRepository.findAllByIsActive(true);

            unitCodes.forEach(unitCode -> selectedUnitCodes.add(unitCode.unitCode));

        }

        selectedUnitCodes.forEach(unitCode -> {
            fetchEmployeesJob.fetchForUnitCode(unitCode, job);
        });

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void fetchContributionsAsync(Job job, Instant startedAtr, String params) {

        JSONObject parameters = new JSONObject(params);

        JSONArray unitcodes = parameters.getJSONArray("unitcodes");

        List<String> selectedUnitCodes = new ArrayList<>();

        if(unitcodes.length() > 0){
            for (int i=0; i<unitcodes.length(); i++){
                selectedUnitCodes.add(unitcodes.getString(i));
            }
        }else {

            List<UnitCode> unitCodes = unitCodeRepository.findAllByIsActive(true);

            unitCodes.forEach(unitCode -> selectedUnitCodes.add(unitCode.unitCode));

        }

        JSONArray years = parameters.getJSONArray("years");

        List<Integer> selectedYears = new ArrayList<>();

        if(years.length() > 0){
            for (int i=0; i<years.length(); i++){
                selectedYears.add(years.getInt(i));
            }
        }

        JSONArray months = parameters.getJSONArray("months");

        List<Integer> selectedMonths = new ArrayList<>();

        if(months.length() > 0){
            for (int i=0; i<months.length(); i++){
                selectedMonths.add(months.getInt(i));
            }
        }

        selectedUnitCodes.forEach(unitCode -> selectedYears.forEach(year -> selectedMonths.forEach(month -> {
            fetchContributionsJob.fetchForUnitCode(unitCode, year, month, job);
        })));

        logger.info("update employees for contributions");

        Job updateEmployeesJob = new Job();
        updateEmployeesJob.setEntityId(UUID.randomUUID().toString());
        updateEmployeesJob.setName("update_contribution_job");

        Instant employeeUpdatedAt = Instant.now();

        JSONObject details = new JSONObject();
        details.put("status", "started");
        details.put("startedAt", employeeUpdatedAt);

        updateEmployeesJob.setJobDetails(details.toString());

        customJobRepository.save(updateEmployeesJob);

        updateContributionsWithEmployee(updateEmployeesJob, employeeUpdatedAt);


        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAtr, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    private void updateContributionsWithEmployee(Job job, Instant startedAt) {

        fetchContributionsJob.updateContributionWithEmployee(job);

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void fetchLoansAsync(Job job, Instant startedAt, String params) {

        List<String> selectedUnitCodes = new ArrayList<>();

        if(params != null && !params.isEmpty()){

            JSONObject parameters = new JSONObject(params);

            JSONArray unitcodes = parameters.getJSONArray("unitcodes");

            if(unitcodes.length() > 0){
                for (int i=0; i<unitcodes.length(); i++){
                    selectedUnitCodes.add(unitcodes.getString(i));
                }
            }

        }else {

            List<UnitCode> unitCodes = unitCodeRepository.findAllByIsActive(true);

            unitCodes.forEach(unitCode -> selectedUnitCodes.add(unitCode.unitCode));

        }

        selectedUnitCodes.forEach(unitCode -> {
            fetchLoansJob.fetchForUnitCode(unitCode, job);
        });

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void fetchTransferInsAsync(Job job, Instant startedAt, String params) {

        List<String> selectedUnitCodes = new ArrayList<>();

        if(params != null && !params.isEmpty()){

            JSONObject parameters = new JSONObject(params);

            JSONArray unitcodes = parameters.getJSONArray("unitcodes");

            if(unitcodes.length() > 0){
                for (int i=0; i<unitcodes.length(); i++){
                    selectedUnitCodes.add(unitcodes.getString(i));
                }
            }

        }else {

            List<UnitCode> unitCodes = unitCodeRepository.findAllByIsActive(true);

            unitCodes.forEach(unitCode -> selectedUnitCodes.add(unitCode.unitCode));

        }

        selectedUnitCodes.forEach(unitCode -> {
            fetchTransferInsJob.fetchForUnitCode(unitCode, job);
        });

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void fetchSettlementsAsync(Job job, Instant startedAt, String params) {

        List<String> selectedUnitCodes = new ArrayList<>();

        if(params != null && !params.isEmpty()){

            JSONObject parameters = new JSONObject(params);

            JSONArray unitcodes = parameters.getJSONArray("unitcodes");

            if(unitcodes.length() > 0){
                for (int i=0; i<unitcodes.length(); i++){
                    selectedUnitCodes.add(unitcodes.getString(i));
                }
            }

        }else {

            List<UnitCode> unitCodes = unitCodeRepository.findAllByIsActive(true);

            unitCodes.forEach(unitCode -> selectedUnitCodes.add(unitCode.unitCode));

        }

        selectedUnitCodes.forEach(unitCode -> {
            fetchSettlementsJob.fetchForUnitCode(unitCode, job);
        });

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void hireNewEmployeesAsync(Job job, Instant startedAt, String params) {

        JSONObject parameters = new JSONObject(params);

        JSONArray employees = parameters.getJSONArray("data");

        Map<String, List<JSONObject>> employeesByUnitCode = new HashMap<>();

        for (Object employee: employees) {
            JSONObject jsonObject = (JSONObject) employee;
            if(employeesByUnitCode.containsKey(jsonObject.getString("Unit Code"))){
                employeesByUnitCode.get(jsonObject.getString("Unit Code")).add(jsonObject);
            }else {
                List<JSONObject> list = new ArrayList<>();
                list.add(jsonObject);
                employeesByUnitCode.put(jsonObject.getString("Unit Code"), list);
            }
        }

        employeesByUnitCode.keySet().forEach(unitCode -> {
            fetchEmployeesJob.hireEmployees(unitCode, employeesByUnitCode.get(unitCode), job);
        });

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void runMonthlyStatusProcess(Job job, Instant startedAt, String params) {

        List<String> selectedUnitCodes = new ArrayList<>();

        JSONObject parameters = new JSONObject(params);

        JSONArray unitcodes = parameters.getJSONArray("unitcodes");

        boolean isDryRun = parameters.getBoolean("isDryRun");

        int financialYear = parameters.getInt("year");

        Integer financialMonth = parameters.getInt("month");

        if(unitcodes.length() > 0){
            for (int i=0; i<unitcodes.length(); i++){
                selectedUnitCodes.add(unitcodes.getString(i));
            }
        }else {
            List<UnitCode> unitCodes = unitCodeRepository.findAllByIsActive(true);
            unitCodes.forEach(unitCode -> selectedUnitCodes.add(unitCode.unitCode));
        }

        selectedUnitCodes.forEach(unitCode -> monthlyStatusProcessJobService
                .performUnitCodeWise(unitCode,financialYear, financialMonth, isDryRun, job));

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    public List<Job> getJobs(String type) {
        return customJobRepository.findAllByNameAndIsActive(type, true);
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void runYearEndProcess(Job job, Instant startedAt, String params) {

        JSONObject jsonObject = new JSONObject(params);

        JSONArray unitCodes = jsonObject.getJSONArray("unitcodes");

        boolean isDryRun = jsonObject.getBoolean("isDryRun");

        JSONArray pernNumbers = jsonObject.getJSONArray("pernNumbers");

        String processFor = jsonObject.getString("processFor");

        String processRunFor = jsonObject.getString("processRunFor");

        if(processRunFor.equalsIgnoreCase(YearEndProcess.PROCESS_RUN_FOR_EMPLOYEE)) {

            Set<String> pernNumbers1 = new HashSet<>();

            for (int i=0; i< pernNumbers.length(); i++){
                pernNumbers1.add(pernNumbers.getString(i));
            }

           yearEndProcessJobService.performForEmployees(pernNumbers1, isDryRun, processFor, job);
        }

        if(processRunFor.equalsIgnoreCase(YearEndProcess.PROCESS_RUN_FOR_UNIT_CODE)) {

            Set<String> unitCodes1 = new HashSet<>();

            for(int i=0; i< unitCodes.length(); i++) {
                unitCodes1.add(unitCodes.getString(i));
            }

            yearEndProcessJobService.performForUnitCode(unitCodes1, isDryRun, processFor, job);
        }

        if(processRunFor.equalsIgnoreCase(YearEndProcess.PROCESS_RUN_FOR_ALL)){
            yearEndProcessJobService.perform(isDryRun, processFor, job);
        }

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    public List<Job> getJobs() {
        return customJobRepository.findAll();
    }

    @Override
    @Async
    @ApplyTenantFilter
    public void processLoanApplications(Job job, Instant startedAt, String params) {

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject request = new JSONObject(params);

        JSONArray applications = request.getJSONArray("applications");

        int length = applications.length();

        JSONArray processedApplications = new JSONArray();

        for(int i=0; i<length; i++){

            try {

                JSONObject jsonObject = applications.getJSONObject(i);

                String dateOfCompletionOfHouse = DataUtil.getString(jsonObject, "dateOfCompletionOfHouse");

                if(dateOfCompletionOfHouse != null){
                    String[] split = dateOfCompletionOfHouse.split("-");
                    jsonObject.put("dateOfCompletionOfHouse", split[2]+"-"+split[1]+"-"+split[0]);
                }

                String loanApplicationReceivedDate = DataUtil.getString(jsonObject, "loanApplicationReceivedDate");

                if(loanApplicationReceivedDate != null){
                    String[] split1 = loanApplicationReceivedDate.split("-");
                    jsonObject.put("loanApplicationReceivedDate", split1[2]+"-"+split1[1]+"-"+split1[0]);
                }

                Employee employee = employeeService.getEmployeeByPern(jsonObject.getString("pernNumber"));

                LoanType loanType = loanService.getLoanType(jsonObject.getString("selectedLoanType"));

                Loan loan = Loan.build(jsonObject);

                List<LoanDocument> loanDocumentList = loanService.getLoanDocuments(jsonObject.getJSONArray("documents"));

                Loan savedLoan = loanService.create(employee, loanType, loan);

                loanService.saveLoanDocuments(loanDocumentList, savedLoan);

                JSONObject responseObj = new JSONObject();
                responseObj.put("id", savedLoan.getEntityId());
                responseObj.put("pernNumber", employee.getPernNumber());
                responseObj.put("name", employee.getName());
                responseObj.put("loanAmount", savedLoan.getEligibleAmount());
                responseObj.put("appliedAmount", savedLoan.getAppliedAmount());
                responseObj.put("message", "Loan Application Created Successfully with Application ID " + savedLoan.getEntityId());
                responseObj.put("status", "success");

                processedApplications.put(responseObj);

                JSONObject jobDetailsJson = job1.getJobDetailsJson();

                jobDetailsJson.put("status", "started");
                jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
                jobDetailsJson.put("params", params);
                jobDetailsJson.put("processedApplications", processedApplications);

                job1.setJobDetails(jobDetailsJson.toString());

                customJobRepository.save(job1);

            } catch (ParseException | JPAException e) {

                JSONObject responseObj = new JSONObject();
                responseObj.put("message", e.getLocalizedMessage());
                responseObj.put("status", "failed");

                processedApplications.put(responseObj);

                JSONObject jobDetailsJson = job1.getJobDetailsJson();

                jobDetailsJson.put("status", "started");
                jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
                jobDetailsJson.put("params", params);
                jobDetailsJson.put("processedApplications", processedApplications);

                job1.setJobDetails(jobDetailsJson.toString());

                customJobRepository.save(job1);

            }

        }

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);
        jobDetailsJson.put("processedApplications", processedApplications);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void approveLoanApplications(Job job, Instant startedAt, String params) {

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject request = new JSONObject(params);

        JSONArray applications = request.getJSONArray("applications");

        int length = applications.length();

        JSONArray approvedApplications = new JSONArray();

        for(int i=0; i<length; i++){

            try {

                JSONObject jsonObject = applications.getJSONObject(i);

                String id = jsonObject.getString("id");

                Loan loan = loanService.getLoan(id);

                loanService.approveLoan(loan, jsonObject);

                JSONObject responseObj = new JSONObject();
                responseObj.put("id", loan.getEntityId());
                responseObj.put("status", "success");

                approvedApplications.put(responseObj);

                JSONObject jobDetailsJson = job1.getJobDetailsJson();

                jobDetailsJson.put("status", "started");
                jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
                jobDetailsJson.put("params", params);
                jobDetailsJson.put("approvedApplications", approvedApplications);

                job1.setJobDetails(jobDetailsJson.toString());

                customJobRepository.save(job1);

            } catch (Exception e) {

                JSONObject responseObj = new JSONObject();
                responseObj.put("message", e.getLocalizedMessage());
                responseObj.put("status", "failed");

                approvedApplications.put(responseObj);

                JSONObject jobDetailsJson = job1.getJobDetailsJson();

                jobDetailsJson.put("status", "started");
                jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
                jobDetailsJson.put("params", params);
                jobDetailsJson.put("approvedApplications", approvedApplications);

                job1.setJobDetails(jobDetailsJson.toString());

                customJobRepository.save(job1);

            }

        }

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", params);
        jobDetailsJson.put("approvedApplications", approvedApplications);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void fetchEmployeesByPernAsync(Job job, Instant startedAt, String unitCode, String pernNumber) {

        fetchEmployeesJob.fetchForUnitCodeAndPern(unitCode, pernNumber, job);

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", unitCode);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }
    
    @Override
    @Async
    @ApplyTenantFilter
    public void fetchContributionsByPernAsync(Job job, Instant startedAtr, String unitCode,String pernNumber, Integer year, Integer month) {

        
        fetchContributionsJob.fetchForUnitCodeAndPern(unitCode, pernNumber,year, month, job);


        logger.info("update employees for contributions");

        Job updateEmployeesJob = new Job();
        updateEmployeesJob.setEntityId(UUID.randomUUID().toString());
        updateEmployeesJob.setName("update_contribution_job");

        Instant employeeUpdatedAt = Instant.now();

        JSONObject details = new JSONObject();
        details.put("status", "started");
        details.put("startedAt", employeeUpdatedAt);

        updateEmployeesJob.setJobDetails(details.toString());

        customJobRepository.save(updateEmployeesJob);

        updateContributionsWithEmployee(updateEmployeesJob, employeeUpdatedAt);


        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAtr, Instant.now()).getSeconds());
        jobDetailsJson.put("params", unitCode);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }
    
    @Override
    @Async
    @ApplyTenantFilter
    public void fetchLoansByPernAsync(Job job, Instant startedAt, String unitCode, String pernNumber) {

        
        fetchLoansJob.fetchForUnitCodeAndPern(unitCode, pernNumber, job);

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", unitCode);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }

    @Override
    @Async
    @ApplyTenantFilter
    public void fetchTransferInsByPernAsync(Job job, Instant startedAt, String unitCode, String pernNumber) {

        fetchTransferInsJob.fetchForUnitCodeAndPern(unitCode, pernNumber, job);

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", unitCode);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }
    
    @Override
    @Async
    @ApplyTenantFilter
    public void fetchSettlementsByPernAsync(Job job, Instant startedAt, String unitCode, String pernNumber) {

        fetchSettlementsJob.fetchForUnitCodeAndPern(unitCode, pernNumber, job);

        Job job1 = customJobRepository.findById(job.getId()).get();

        JSONObject jobDetailsJson = job1.getJobDetailsJson();

        jobDetailsJson.put("status", "completed");
        jobDetailsJson.put("completedAt", Instant.now());
        jobDetailsJson.put("duration", Duration.between(startedAt, Instant.now()).getSeconds());
        jobDetailsJson.put("params", unitCode);

        job1.setJobDetails(jobDetailsJson.toString());

        customJobRepository.save(job1);

    }
}
