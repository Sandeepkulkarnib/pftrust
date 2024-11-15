package com.coreintegra.pftrust.entities.pf.employee;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.util.DateFormatterUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Employee extends BaseAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private String tokenNumber;
    private String pernNumber;
    private String pfNumber;

    private String name;

    private Date dateOfJoining;
    private Date dateOfJoiningPF;

    private String uanNumber;

    private Date dateOfBirth;

    private String location;

    private String payrollArea;
    private String unitCode;

    private String contactNumber;
    private String alternateContactNumber;

    private String email;
    private String personalEmail;

    private String designation;

    private String fromEPS;
    private String toEPS;

    private String panNumber;

    private String aadharNumber;

    @OneToOne
    private Gender gender;

    @ManyToOne
    private ContributionStatus contributionStatus;

    @ManyToOne
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Address> addresses;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<PreviousCompany> previousCompanies;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<BankDetails> bankDetailsList;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Nominee> nominees;

    public Employee() {
    }

    public Employee(Tenant tenant, String tokenNumber, String pernNumber, String pfNumber,
                    String name, String unitCode) {
        this.tenant = tenant;
        this.tokenNumber = tokenNumber;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.name = name;
        this.unitCode = unitCode;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoiningPF() {
        return dateOfJoiningPF;
    }

    public void setDateOfJoiningPF(Date dateOfJoiningPF) {
        this.dateOfJoiningPF = dateOfJoiningPF;
    }

    public String getUanNumber() {
        return uanNumber;
    }

    public void setUanNumber(String uanNumber) {
        this.uanNumber = uanNumber;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPayrollArea() {
        return payrollArea;
    }

    public void setPayrollArea(String payrollArea) {
        this.payrollArea = payrollArea;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFromEPS() {
        return fromEPS;
    }

    public void setFromEPS(String fromEPS) {
        this.fromEPS = fromEPS;
    }

    public String getToEPS() {
        return toEPS;
    }

    public void setToEPS(String toEPS) {
        this.toEPS = toEPS;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ContributionStatus getContributionStatus() {
        return contributionStatus;
    }

    public void setContributionStatus(ContributionStatus contributionStatus) {
        this.contributionStatus = contributionStatus;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<PreviousCompany> getPreviousCompanies() {
        return previousCompanies;
    }

    public void setPreviousCompanies(List<PreviousCompany> previousCompanies) {
        this.previousCompanies = previousCompanies;
    }

    public List<BankDetails> getBankDetailsList() {
        return bankDetailsList;
    }

    public void setBankDetailsList(List<BankDetails> bankDetailsList) {
        this.bankDetailsList = bankDetailsList;
    }

    public List<Nominee> getNominees() {
        return nominees;
    }

    public void setNominees(List<Nominee> nominees) {
        this.nominees = nominees;
    }

    public Integer getEmployeeAge() {
        return DateFormatterUtil.period(dateOfBirth).getYears();
    }

    public Boolean isSettled(){
        return contributionStatus.getSymbol().equalsIgnoreCase(ContributionStatus.SETTLED);
    }

    public Boolean isMerged(){
        return contributionStatus.getSymbol().equalsIgnoreCase(ContributionStatus.MERGED);
    }

    public Boolean isIO(){
        return contributionStatus.getSymbol().equalsIgnoreCase(ContributionStatus.IN_OPERABLE);
    }

    public Boolean isF(){
        return contributionStatus.getSymbol().equalsIgnoreCase(ContributionStatus.F);
    }

    public Boolean isInActive(){
        return contributionStatus.getSymbol().equalsIgnoreCase(ContributionStatus.IN_ACTIVE);
    }

    public Boolean isActive(){
        return contributionStatus.getSymbol().equalsIgnoreCase(ContributionStatus.ACTIVE);
    }

    public Boolean isC(){
        return contributionStatus.getSymbol().equalsIgnoreCase(ContributionStatus.C);
    }

    public Boolean isNoStatus(){
        return contributionStatus.getSymbol().equalsIgnoreCase(ContributionStatus.NO_STATUS);
    }

}
