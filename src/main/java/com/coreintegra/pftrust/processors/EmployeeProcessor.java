package com.coreintegra.pftrust.processors;

import com.coreintegra.pftrust.entities.pf.employee.*;
import com.coreintegra.pftrust.entities.pf.employee.dtos.EmployeeDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class EmployeeProcessor {

    public Employee process(EmployeeDTO employeeDTO) throws Exception {

        Employee employee = new Employee();

//        set employee basic info.
        employee.setTokenNumber(employeeDTO.getPernrHr());
        employee.setPernNumber(employeeDTO.getPernNo());
        employee.setPfNumber(employeeDTO.getPfNo());
        employee.setAadharNumber(employeeDTO.getAadharNo());
        employee.setAlternateContactNumber(employeeDTO.getEmployeeAlternativeContactNo());
        employee.setContactNumber(employeeDTO.getEmployeeContactNo());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setEmail(employeeDTO.getEmployeeEmailId());
        employee.setFromEPS(employeeDTO.getFromEps());
        employee.setToEPS(employeeDTO.getToEps());
        employee.setLocation(employeeDTO.getLocation());
        employee.setName(employeeDTO.getEmployeeName());
        employee.setPanNumber(employeeDTO.getPanNo());
        employee.setPayrollArea(employeeDTO.getPayrollArea());
        employee.setPersonalEmail(employeeDTO.getEmployeePersonalEmailId());
        employee.setUanNumber(employeeDTO.getUanNumber());
        employee.setUnitCode(employeeDTO.getUnitCode());
        employee.setDateOfBirth(employeeDTO.getDob());
        employee.setDateOfJoining(employeeDTO.getDateOfJoiningService());
        employee.setDateOfJoiningPF(employeeDTO.getDateOfJoiningPf());

        Gender gender = new Gender();

        if(employeeDTO.getGender() == null || employeeDTO.getGender().trim().isEmpty() ||
                employeeDTO.getGender().trim().equals("Male")){
            gender.setId(1L);
        }else {
            gender.setId(2L);
        }

        employee.setGender(gender);


//        set Bank Details
        BankDetails bankDetails = new BankDetails();
        bankDetails.setAccountNumber(employeeDTO.getBankAccountNo());
        bankDetails.setIfscCode(employeeDTO.getIfscCode());
        bankDetails.setMicrCode(employeeDTO.getMicrCode());
        bankDetails.setName(employeeDTO.getBankName());

        employee.setBankDetailsList(List.of(bankDetails));

//        set Employee Address Details
        Address address = new Address();
        address.setAddressLine1(employeeDTO.getResidentialAddress1());
        address.setAddressLine2(employeeDTO.getResidentialAddress2());

        employee.setAddresses(List.of(address));

//        set Nomination Details.
        List<Nominee> nomineeList = new ArrayList<>();

        Nominee nominee1 = employeeDTO.getNomination(employeeDTO.getNomination1(), employeeDTO.getShare1());

        if(nominee1 != null){
            nomineeList.add(nominee1);
        }


        Nominee nominee2 = employeeDTO.getNomination(employeeDTO.getNomination2(), employeeDTO.getShare2());

        if(nominee2 != null){
            nomineeList.add(nominee2);
        }


        Nominee nominee3 = employeeDTO.getNomination(employeeDTO.getNomination3(), employeeDTO.getShare3());

        if(nominee3 != null){
            nomineeList.add(nominee3);
        }


        Nominee nominee4 = employeeDTO.getNomination(employeeDTO.getNomination4(), employeeDTO.getShare4());

        if(nominee4 != null){
            nomineeList.add(nominee4);
        }

        employee.setNominees(nomineeList);

        ContributionStatus contributionStatus = new ContributionStatus();
        contributionStatus.setSymbol(employeeDTO.getStatusOfTheMember());

        employee.setContributionStatus(contributionStatus);

        return employee;
    }

}
