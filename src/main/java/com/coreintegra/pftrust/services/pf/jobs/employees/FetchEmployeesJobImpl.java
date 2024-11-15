package com.coreintegra.pftrust.services.pf.jobs.employees;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.*;
import com.coreintegra.pftrust.entities.pf.employee.dtos.EmployeeDTO;
import com.coreintegra.pftrust.processors.EmployeeProcessor;
import com.coreintegra.pftrust.repositories.pf.job.CustomJobRepository;
import com.coreintegra.pftrust.services.pf.employees.EmployeeService;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FetchEmployeesJobImpl implements FetchEmployeesJob {

    private final Logger logger = LoggerFactory.getLogger(FetchEmployeesJob.class);

    private final String URL = "https://emss.mahindra.com/sap/bc/zzhr_pf_empl?UNIT_CODE=";

    private final RestTemplate restTemplate = new RestTemplate();

    private final EmployeeProcessor processor = new EmployeeProcessor();

    private final EmployeeService employeeService;
    private final CustomJobRepository customJobRepository;

    public FetchEmployeesJobImpl(EmployeeService employeeService, CustomJobRepository customJobRepository) {
        this.employeeService = employeeService;
        this.customJobRepository = customJobRepository;
    }

    @Override
    public Job fetchForUnitCode(String unitCode, Job job) {

        Instant now = Instant.now();

        ResponseEntity<EmployeeDTO[]> response = restTemplate.getForEntity(URL + unitCode, EmployeeDTO[].class);

        EmployeeDTO[] employeeDTOS = response.getBody();

        logger.info("Employee fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(employeeDTOS == null || employeeDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, "completed", 0, "no employees found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", employeeDTOS.length,
                "starting employee processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        logger.info("starting employee processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(employeeDTOS).map(employeeDTO -> {

            try {

                Employee employee = processor.process(employeeDTO);
                
                System.out.println("Employee : "+ employee.getPernNumber());

                List<Address> addresses = employee.getAddresses();
                List<BankDetails> bankDetailsList = employee.getBankDetailsList();

                List<Nominee> nominees = employee.getNominees();

                employee.setAddresses(null);
                employee.setNominees(null);
                employee.setBankDetailsList(null);

                return employeeService.saveAsync(employee, addresses, nominees,
                        bankDetailsList).join();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());

        logger.info("Employee processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful employees insert -> {}", done.size());
        logger.info("Failed employees insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", employeeDTOS.length,
                "completed employee processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode, details);

        return job;

    }

    @Override
    public Job hireEmployees(String unitCode, List<JSONObject> employeesToHire, Job job) {

        Instant now = Instant.now();

        JSONArray details = new JSONArray();

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", employeesToHire.size(),
                "starting employee processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        Instant processEmployeeInstant = Instant.now();

        List<String> result = employeesToHire.stream().map(employeeToHire -> {

            try {

                Employee employee = mapToEmployee(employeeToHire);

                List<Address> addresses = new ArrayList<>();
                List<BankDetails> bankDetailsList = new ArrayList<>();

                List<Nominee> nominees = new ArrayList<>();

                employee.setAddresses(null);
                employee.setNominees(null);
                employee.setBankDetailsList(null);

                return employeeService.saveAsync(employee, addresses, nominees,
                        bankDetailsList).join();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());


        logger.info("Employee processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful employees insert -> {}", done.size());
        logger.info("Failed employees insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", employeesToHire.size(),
                "completed employee processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode, details);

        return job;
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
            JSONArray failedEmployees = new JSONArray();
            failed.forEach(failedEmployees::put);
            jsonObject.put("failedEmployees", failedEmployees);
        }

        jsonObject.put("startedAt", startedAt);
        jsonObject.put("completedAt", completedAt);
        jsonObject.put("duration", Duration.between(startedAt, completedAt).getSeconds());

        return jsonObject;
    }


    private Job saveJobProgress(Job job, String unitCode, JSONArray details) {

        JSONObject jobDetailsJson = job.getJobDetailsJson();

        if(jobDetailsJson.has("details")){
            jobDetailsJson.getJSONObject("details").put(unitCode, details);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(unitCode, details);
            jobDetailsJson.put("details", jsonObject);
        }

        job.setJobDetails(jobDetailsJson.toString());

        return customJobRepository.save(job);

    }


    private Employee mapToEmployee(JSONObject jsonObject) throws ParseException {

        Employee employee = new Employee();

        employee.setPernNumber(jsonObject.getString("Employee No (PERN NUMBER)"));
        employee.setPfNumber(jsonObject.getString("Provident Fund"));
        employee.setTokenNumber(jsonObject.getString("Token Number"));

        employee.setName(jsonObject.getString("Employee Name"));
        employee.setUnitCode(jsonObject.getString("Unit Code"));

        employee.setDateOfJoiningPF(FinancialYearAndMonth.getDate(jsonObject.getString("DOJ PF TRUST"),
                "dd-MM-yyyy"));

        employee.setDateOfJoining(FinancialYearAndMonth.getDate(jsonObject.getString("DOJ SERVICE"),
                "dd-MM-yyyy"));

        employee.setDateOfBirth(FinancialYearAndMonth.getDate(jsonObject.getString("DOB"),
                "dd-MM-yyyy"));

        Gender gender = new Gender();
        gender.setId(Long.parseLong(jsonObject.getString("Gender CODE")));

        employee.setGender(gender);

        employee.setEmail(jsonObject.has("Employee EmailId") ?
                jsonObject.getString("Employee EmailId") : null);

        return employee;
    }

    @Override
    public Job fetchForUnitCodeAndPern(String unitCode, String PernNumber,Job job) {

        Instant now = Instant.now();

        ResponseEntity<EmployeeDTO[]> response = restTemplate.getForEntity(URL + unitCode, EmployeeDTO[].class);

        EmployeeDTO[] employeeDTOS = response.getBody();

        logger.info("Employee fetch completed for -> {} in -> {}", unitCode, Duration.between(now, Instant.now()).getSeconds());

        JSONArray details = new JSONArray();

        if(employeeDTOS == null || employeeDTOS.length < 1){

            JSONObject jsonObject = getJobStatus(unitCode, "completed", 0, "no employees found",
                    0, new ArrayList<>(), now, Instant.now());

            details.put(jsonObject);

            job = saveJobProgress(job, unitCode, details);

            return job;
        }

        JSONObject jsonObject = getJobStatus(unitCode, "in progress", employeeDTOS.length,
                "starting employee processing and saving", 0, new ArrayList<>(),
                now, Instant.now());

        details.put(jsonObject);

        saveJobProgress(job, unitCode, details);

        logger.info("starting employee processing and saving");

        Instant processEmployeeInstant = Instant.now();

        List<String> result = Arrays.stream(employeeDTOS).map(employeeDTO -> {

            try {

                Employee employee = processor.process(employeeDTO);

                List<Address> addresses = employee.getAddresses();
                List<BankDetails> bankDetailsList = employee.getBankDetailsList();

                List<Nominee> nominees = employee.getNominees();

                employee.setAddresses(null);
                employee.setNominees(null);
                employee.setBankDetailsList(null);
                
                if(employee.getPernNumber().equals(PernNumber)) {

                return employeeService.saveAsync(employee, addresses, nominees,
                        bankDetailsList).join();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "failed";

        }).collect(Collectors.toList());

        logger.info("Employee processing and saving completed in -> {}",
                Duration.between(processEmployeeInstant, Instant.now()).getSeconds());

        List<String> done = result.stream()
                .filter(r -> r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        List<String> failed = result.stream()
                .filter(r -> !r.equalsIgnoreCase("done"))
                .collect(Collectors.toList());

        logger.info("Successful employees insert -> {}", done.size());
        logger.info("Failed employees insert -> {}", failed.size());

        JSONObject jsonObject1 = getJobStatus(unitCode, "completed", employeeDTOS.length,
                "completed employee processing and saving", done.size(), failed,
                now, Instant.now());

        details.put(jsonObject1);

        saveJobProgress(job, unitCode, details);

        return job;

    }


    @Override
    public Employee fetchEmployeesByPern(String unitCode, String pernNumber) throws Exception {

        ResponseEntity<EmployeeDTO[]> response = restTemplate.getForEntity(URL + unitCode, EmployeeDTO[].class);

        EmployeeDTO[] employeeDTOS = response.getBody();

        if(employeeDTOS == null || employeeDTOS.length < 1){
            throw new Exception("No Employee found for give Unit Code");
        }


        List<EmployeeDTO> filteredEmployee = Arrays.stream(employeeDTOS).filter(employeeDTO -> employeeDTO.getPernNo().equalsIgnoreCase(pernNumber))
                .collect(Collectors.toList());

        if(filteredEmployee.size() < 1){
            throw new Exception("No Employee found for give Pern Number");
        }

        Employee employee = processor.process(filteredEmployee.get(0));

        List<Address> addresses = employee.getAddresses();
        List<BankDetails> bankDetailsList = employee.getBankDetailsList();

        List<Nominee> nominees = employee.getNominees();

        employee.setAddresses(null);
        employee.setNominees(null);
        employee.setBankDetailsList(null);

        return employeeService.save(employee, addresses, nominees,
                bankDetailsList);

    }


}
