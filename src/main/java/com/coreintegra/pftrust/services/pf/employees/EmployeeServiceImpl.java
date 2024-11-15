package com.coreintegra.pftrust.services.pf.employees;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.employee.*;
import com.coreintegra.pftrust.entities.pf.employee.dtos.*;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.repositories.pf.contribution.ContributionRepository;
import com.coreintegra.pftrust.repositories.pf.department.UnitCodeRepository;
import com.coreintegra.pftrust.repositories.pf.employee.*;
import com.coreintegra.pftrust.searchutil.SearchEmployeeSpecification;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final BankDetailsRepository bankDetailsRepository;
    private final NomineeRepository nomineeRepository;
    private final ContributionStatusRepository contributionStatusRepository;
    private final UnitCodeRepository unitCodeRepository;

    private final ContributionRepository contributionRepository;
    private final PreviousCompanyRepository previousCompanyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               AddressRepository addressRepository,
                               BankDetailsRepository bankDetailsRepository,
                               NomineeRepository nomineeRepository,
                               ContributionStatusRepository contributionStatusRepository,
                               UnitCodeRepository unitCodeRepository,
                               ContributionRepository contributionRepository,
                               PreviousCompanyRepository previousCompanyRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.bankDetailsRepository = bankDetailsRepository;
        this.nomineeRepository = nomineeRepository;
        this.contributionStatusRepository = contributionStatusRepository;
        this.unitCodeRepository = unitCodeRepository;
        this.contributionRepository = contributionRepository;
        this.previousCompanyRepository = previousCompanyRepository;
    }

    @Transactional
    @Override
    @Async
    @ApplyTenantFilter
    public CompletableFuture<String> saveAsync(Employee employee, List<Address> addressList, List<Nominee> nominees, List<BankDetails> bankDetails) {

        try {

            Optional<Employee> optionalEmployee = employeeRepository
                    .findByPernNumberOrPfNumberAndIsActive(employee.getPernNumber(),
                            employee.getPfNumber(), true);

            if (optionalEmployee.isPresent()) {
                return CompletableFuture.completedFuture("failed");
            }

            if (employee.getContributionStatus() == null ||
                    employee.getContributionStatus().getSymbol() == null ||
                    employee.getContributionStatus().getSymbol().trim().isEmpty()) {
                ContributionStatus contributionStatus = new ContributionStatus();
                contributionStatus.setSymbol("NA");

                employee.setContributionStatus(contributionStatus);
            }

            ContributionStatus contributionStatus =
                    contributionStatusRepository.findBySymbolAndIsActive(employee.getContributionStatus().
                            getSymbol(), true);

            employee.setContributionStatus(contributionStatus);

            Employee save = employeeRepository.save(employee);

            List<Address> addresses = addressList.stream().map(address -> {
                address.setEmployee(save);
                return address;
            }).collect(Collectors.toList());

            addressRepository.saveAll(addresses);

            List<BankDetails> bankDetailsList = bankDetails.stream().map(bankDetail -> {
                bankDetail.setEmployee(save);
                return bankDetail;
            }).collect(Collectors.toList());

            bankDetailsRepository.saveAll(bankDetailsList);

            List<Nominee> nomineeList = nominees.stream().map(nominee -> {
                nominee.setEmployee(save);
                return nominee;
            }).collect(Collectors.toList());

            nomineeRepository.saveAll(nomineeList);

            logger.info("employee saved successfully -> {}", save.getId());


        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(employee.getPernNumber() + " =|= " + e.getLocalizedMessage() + "=|=" + Arrays.toString(e.getStackTrace()));
        }

        return CompletableFuture.completedFuture("done");

    }

    @Override
    @Transactional
    public Employee save(Employee employee, List<Address> addressList, List<Nominee> nominees,
                         List<BankDetails> bankDetails) throws JPAException {

            Optional<Employee> optionalEmployee = employeeRepository
                    .findByPernNumberOrPfNumberAndIsActive(employee.getPernNumber(),
                            employee.getPfNumber(), true);

            if (optionalEmployee.isPresent()) {
                throw new JPAException("Employee Alread Exists with this Pern Number.");
            }

            if (employee.getContributionStatus() == null ||
                    employee.getContributionStatus().getSymbol() == null ||
                    employee.getContributionStatus().getSymbol().trim().isEmpty()) {
                ContributionStatus contributionStatus = new ContributionStatus();
                contributionStatus.setSymbol("NA");

                employee.setContributionStatus(contributionStatus);
            }

            ContributionStatus contributionStatus =
                    contributionStatusRepository.findBySymbolAndIsActive(employee.getContributionStatus().
                            getSymbol(), true);

            employee.setContributionStatus(contributionStatus);

            Employee save = employeeRepository.save(employee);

            List<Address> addresses = addressList.stream().map(address -> {
                address.setEmployee(save);
                return address;
            }).collect(Collectors.toList());

            addressRepository.saveAll(addresses);

            List<BankDetails> bankDetailsList = bankDetails.stream().map(bankDetail -> {
                bankDetail.setEmployee(save);
                return bankDetail;
            }).collect(Collectors.toList());

            bankDetailsRepository.saveAll(bankDetailsList);

            List<Nominee> nomineeList = nominees.stream().map(nominee -> {
                nominee.setEmployee(save);
                return nominee;
            }).collect(Collectors.toList());

            nomineeRepository.saveAll(nomineeList);

            logger.info("employee saved successfully -> {}", save.getId());

            return employee;
    }

    @Override
    public Page<Employee> getEmployees(String search, Integer size, Integer page) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,
                "pernNumber", "unitCode"));

        return employeeRepository.findAll(
                new SearchEmployeeSpecification().getSearchEmployeeSpecification(search),
                pageable);

    }

    @Override
    public JSONObject validateNewEmployees(JSONObject employees) {

        JSONArray employeesJSONArray = employees.getJSONArray("data");

        Set<String> unitCodes = new HashSet<>();
        Set<String> pernNumbers = new HashSet<>();
        Set<String> pfNumbers = new HashSet<>();
        Set<String> tokenNumbers = new HashSet<>();

        employeesJSONArray.forEach(object -> {

            JSONObject jsonObject = (JSONObject) object;

            unitCodes.add(jsonObject.getString("Unit Code").trim());
            pfNumbers.add(jsonObject.getString("Provident Fund").trim());
            pernNumbers.add(jsonObject.getString("Employee No (PERN NUMBER)").trim());
            tokenNumbers.add(jsonObject.getString("Token Number").trim());

        });

        Set<UnitCode> validUnitCodes = unitCodeRepository.findAllByUnitCodeInAndIsActive(unitCodes, true);
        Set<String> validUnitCodeStrings = validUnitCodes.stream()
                .map(UnitCode::getUnitCode)
                .collect(Collectors.toSet());

        Set<Employee> existingEmployeesByPern = employeeRepository.findAllByPernNumberIn(pernNumbers);
        Set<String> existingPernNumbers = existingEmployeesByPern.stream()
                .map(Employee::getPernNumber).collect(Collectors.toSet());


        Set<Employee> existingEmployeesByPfNumber = employeeRepository.findAllByPfNumberIn(pfNumbers);
        Set<String> existingPfNumbers = existingEmployeesByPfNumber.stream()
                .map(Employee::getPfNumber).collect(Collectors.toSet());


        Set<Employee> existingEmployeesByTokenNumbers = employeeRepository.findAllByTokenNumberIn(tokenNumbers);
        Set<String> existingTokenNumbers = existingEmployeesByTokenNumbers.stream()
                .map(Employee::getTokenNumber).collect(Collectors.toSet());

        int unitCodeInvalidCount = 0;
        int pernNumberInvalidCount = 0;
        int pfNumberInvalidCount = 0;
        int tokenNumberInvalidCount = 0;

        for (Object object : employeesJSONArray) {

            JSONObject jsonObject = (JSONObject) object;

            if (mapEmployeeValidity(validUnitCodeStrings, jsonObject,
                    "Unit Code")) {
                jsonObject.put("isValidUnitCode", true);
            } else {
                jsonObject.put("isValidUnitCode", false);
                unitCodeInvalidCount++;
            }

            if(!mapEmployeeValidity(existingPernNumbers, jsonObject,
                    "Employee No (PERN NUMBER)")){
                jsonObject.put("isValidPernNumber", true);
            }else {
                jsonObject.put("isValidPernNumber", false);
                pernNumberInvalidCount++;
            }

            if(!mapEmployeeValidity(existingPfNumbers, jsonObject,
                    "Provident Fund")){
                jsonObject.put("isValidPfNumber", true);
            }else {
                jsonObject.put("isValidPfNumber", false);
                pfNumberInvalidCount++;
            }

            if(!mapEmployeeValidity(existingTokenNumbers, jsonObject,
                    "Token Number")){
                jsonObject.put("isValidTokenNumber", true);
            }else {
                jsonObject.put("isValidTokenNumber", false);
                tokenNumberInvalidCount++;
            }

            jsonObject.put("color", getColor(jsonObject));

        }

        JSONObject errors = new JSONObject();

        formatErrorFor(unitCodeInvalidCount, errors, "isValidUnitCode",
                "Found " + unitCodeInvalidCount + " Invalid Unit Codes.", "red");

        formatErrorFor(pernNumberInvalidCount, errors, "isValidPernNumber",
                "Found " + pernNumberInvalidCount + " Pern Numbers Which already exists.",
                "orange");

        formatErrorFor(pfNumberInvalidCount, errors, "isValidPfNumber",
                "Found " + pfNumberInvalidCount + " Pf Account Numbers which already exists.",
                "yellow");

        formatErrorFor(tokenNumberInvalidCount, errors, "isValidTokenNumber",
                "Found " + tokenNumberInvalidCount + " Token Numbers which already exists.",
                "pink");

        employees.put("errors", errors);

        return employees;

    }

    @Override
    public List<ContributionStatus> getContributionStatusList() {
        return contributionStatusRepository.findAll();
    }

    @Override
    public List<Employee> getEmployees(String unitCode) {
        return employeeRepository.findAllByUnitCodeAndIsActive(unitCode, true);
    }

    @Override
    public Employee getEmployee(String entityId) throws JPAException {

        Optional<Employee> optionalEmployee = employeeRepository.findByEntityIdAndIsActive(entityId, true);

        if(optionalEmployee.isEmpty()) throw new JPAException("Employee Not Found");

        return optionalEmployee.get();
    }

    @Override
    public EmployeeDetailsDTO getDetails(Employee employee) {

        BasicDetailsDTO basicDetails = getBasicDetails(employee);

        OtherDetailsDTO otherDetails = getOtherDetails(employee);

        return new EmployeeDetailsDTO(basicDetails, otherDetails);
    }

    @Override
    public Employee getEmployeeByPern(String pernNumber) {
        Optional<Employee> optionalEmployee = employeeRepository.findByPernNumberAndIsActive(pernNumber, true);
        if(optionalEmployee.isEmpty()) throw new EntityNotFoundException("Employee not found for -> " + pernNumber);
        return optionalEmployee.get();
    }

    public Employee getEmployeeByPf(String pfNumber) {
        Optional<Employee> optionalEmployee = employeeRepository.findByPfNumberAndIsActive(pfNumber, true);
        if(optionalEmployee.isEmpty()) throw new EntityNotFoundException("Employee not found for -> " + pfNumber);
        return optionalEmployee.get();
    }

    private BasicDetailsDTO getBasicDetails(Employee employee) {

        Optional<Contribution> optionalContribution = contributionRepository
                .findTop1ByEmployeeAndSubTypeGreaterThanAndIsActiveOrderByYearDescSubTypeDesc(employee,
                0, true);

        BasicDetailsDTO basicDetailsDTO = BasicDetailsDTO.build(employee);

        basicDetailsDTO.setContributionStatus(employee.getContributionStatus().getDescription());

        if(optionalContribution.isPresent()) {

            Contribution contribution = optionalContribution.get();

            basicDetailsDTO.setBasePfSalary(contribution.getPfBase());
            basicDetailsDTO.setLastRecoveryDate(contribution.getRecoveryDate());

        }

        return basicDetailsDTO;
    }

    private OtherDetailsDTO getOtherDetails(Employee employee) {

        List<AddressDetailsDTO> addressDetailsDTOList = employee.getAddresses().stream()
                .map(address -> new AddressDetailsDTO(address.getAddressLine1(), address.getAddressLine2(),
                        address.getAddressLine3(), address.getAddressLine4()))
                .collect(Collectors.toList());

        List<BankDetailsDTO> bankDetailsDTOList = employee.getBankDetailsList().stream()
                .map(bankDetails -> new BankDetailsDTO(bankDetails.getName(), bankDetails.getAccountNumber(),
                        bankDetails.getIfscCode(), bankDetails.getMicrCode()))
                .collect(Collectors.toList());

        List<com.coreintegra.pftrust.dtos.pdf.Nominee> nomineeList = employee.getNominees().stream()
                .map(nominee -> new com.coreintegra.pftrust.dtos.pdf.Nominee(nominee.getName(),
                        nominee.getRelationship(), nominee.getShare()))
                .collect(Collectors.toList());

        return new OtherDetailsDTO(nomineeList, bankDetailsDTOList, addressDetailsDTOList);

    }

    private void formatErrorFor(int count, JSONObject errors, String key, String message, String color) {
        if(count > 0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", count);
            jsonObject.put("message", message);
            jsonObject.put("color", color);
            errors.put(key, jsonObject);
        }
    }

    private Boolean mapEmployeeValidity(Set<String> validUnitCodeStrings, JSONObject jsonObject, String key) {
        return validUnitCodeStrings.contains(jsonObject.get(key).toString());
    }

    private String getColor(JSONObject jsonObject){

        if(!jsonObject.getBoolean("isValidUnitCode")){
            return "red";
        }

        if(!jsonObject.getBoolean("isValidPernNumber")){
            return "orange";
        }

        if(!jsonObject.getBoolean("isValidPfNumber")){
            return "yellow";
        }

        if(!jsonObject.getBoolean("isValidTokenNumber")){
            return "pink";
        }

        return "success";
    }

    @Override
    public Date getDateOfJoiningPrior(Employee employee) {

        List<PreviousCompany> previousCompanies = previousCompanyRepository
                .findAllByEmployeeAndIsActive(employee, true);

        if(!previousCompanies.isEmpty()){
            return previousCompanies.get(0).getDateOfJoining();
        }

        return null;
    }

    @Override
    public Integer getMembershipYears(Employee employee) {

        List<PreviousCompany> previousCompanies = employee.getPreviousCompanies();

        Date date = employee.getDateOfJoiningPF();

        if(!previousCompanies.isEmpty()){
            if(previousCompanies.get(0).getDateOfJoining() != null){
                date = previousCompanies.get(0).getDateOfJoining();
            }
        }

        return DateFormatterUtil.getPeriodBetweenDate(date, new Date()).getYears();
    }

}
