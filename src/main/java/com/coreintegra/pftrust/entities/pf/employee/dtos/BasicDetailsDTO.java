package com.coreintegra.pftrust.entities.pf.employee.dtos;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.util.DataUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class BasicDetailsDTO {

    private String pernNumber;
    private String tokenNumber;
    private String pfNumber;
    private String UnitCode;

    private String epfNumber;
    private String panNumber;
    private String aadharNumber;
    private String uanNumber;

    private String contributionStatus;
    private BigDecimal basePfSalary;
    private Date lastRecoveryDate;

    private String name;
    private Integer age;
    private String gender;

    private Date dob;
    private Date dateOfJoiningPf;
    private Date dateOfJoiningService;

    private String payrollArea;
    private String designation;
    private String department;
    private String location;

    private String email;
    private String personalEmail;
    private String contactNumber;
    private String alternateContactNumber;

    public BasicDetailsDTO(String pernNumber, String tokenNumber, String pfNumber, String unitCode, String epfNumber,
                           String panNumber, String aadharNumber, String uanNumber, String name, Integer age,
                           String gender, Date dob, Date dateOfJoiningPf, Date dateOfJoiningService,
                           String payrollArea, String designation, String department, String location, String email,
                           String personalEmail, String contactNumber, String alternateContactNumber) {
        this.pernNumber = pernNumber;
        this.tokenNumber = tokenNumber;
        this.pfNumber = pfNumber;
        UnitCode = unitCode;
        this.epfNumber = epfNumber;
        this.panNumber = panNumber;
        this.aadharNumber = aadharNumber;
        this.uanNumber = uanNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dob = dob;
        this.dateOfJoiningPf = dateOfJoiningPf;
        this.dateOfJoiningService = dateOfJoiningService;
        this.payrollArea = payrollArea;
        this.designation = designation;
        this.department = department;
        this.location = location;
        this.email = email;
        this.personalEmail = personalEmail;
        this.contactNumber = contactNumber;
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(String unitCode) {
        UnitCode = unitCode;
    }

    public String getEpfNumber() {
        return epfNumber == null ? "NA" : epfNumber;
    }

    public void setEpfNumber(String epfNumber) {
        this.epfNumber = epfNumber;
    }

    public String getPanNumber() {
        return panNumber == null ? "NA" : panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadharNumber() {
        return aadharNumber == null ? "NA" : aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getUanNumber() {
        return uanNumber == null ? "NA" : uanNumber;
    }

    public void setUanNumber(String uanNumber) {
        this.uanNumber = uanNumber;
    }

    public String getContributionStatus() {
        return contributionStatus;
    }

    public void setContributionStatus(String contributionStatus) {
        this.contributionStatus = contributionStatus;
    }

    public BigDecimal getBasePfSalary() {
        return basePfSalary;
    }

    public void setBasePfSalary(BigDecimal basePfSalary) {
        this.basePfSalary = basePfSalary;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getLastRecoveryDate() {
        return lastRecoveryDate;
    }

    public void setLastRecoveryDate(Date lastRecoveryDate) {
        this.lastRecoveryDate = lastRecoveryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoiningPf() {
        return dateOfJoiningPf;
    }

    public void setDateOfJoiningPf(Date dateOfJoiningPf) {
        this.dateOfJoiningPf = dateOfJoiningPf;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoiningService() {
        return dateOfJoiningService;
    }

    public void setDateOfJoiningService(Date dateOfJoiningService) {
        this.dateOfJoiningService = dateOfJoiningService;
    }

    public String getPayrollArea() {
        return payrollArea == null ? "NA" : payrollArea;
    }

    public void setPayrollArea(String payrollArea) {
        this.payrollArea = payrollArea;
    }

    public String getDesignation() {
        return designation == null ? "NA" : designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department == null ? "NA" : department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? "NA" : location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalEmail() {
        return personalEmail == null ? "NA" : personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getContactNumber() {
        return contactNumber == null ? "NA" : contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber == null ? "NA" : alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public static BasicDetailsDTO build(Employee employee) {
        return new BasicDetailsDTO(employee.getPernNumber(), employee.getTokenNumber(), employee.getPfNumber(),
                employee.getUnitCode(), employee.getToEPS(), employee.getPanNumber(), employee.getAadharNumber(),
                employee.getUanNumber(), employee.getName(), employee.getEmployeeAge(), employee.getGender().getDetails(),
                employee.getDateOfBirth(), employee.getDateOfJoiningPF(), employee.getDateOfJoining(),
                employee.getPayrollArea(), employee.getDesignation(),
                DataUtil.avoidNull(employee.getDepartment(), "getTitle"),
                employee.getLocation(), employee.getEmail(), employee.getPersonalEmail(), employee.getContactNumber(),
                employee.getAlternateContactNumber());
    }

}
