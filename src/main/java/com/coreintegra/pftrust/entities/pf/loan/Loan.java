package com.coreintegra.pftrust.entities.pf.loan;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.util.DataUtil;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.poi.ss.usermodel.DateUtil;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Loan extends BaseAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;
    private Date loanApplicationDate;
    private Date loanApprovalDate;
    private Date loanDisbursalDate;
    private Date loanApplicationReceivedDate;

    private BigDecimal appliedAmount;

    private BigDecimal totalCost;

    private BigDecimal eligibleAmount;

    private BigDecimal loanAmountOnPfBaseSalary;
    private BigDecimal loanAmountOnPfBalance;

    
    private Date dateOfCompletionOfHouse;
    private Date dateOfFirstAlteration;

    private BigDecimal propertyEstimatedPrice;
    private BigDecimal stampDuty;
    private BigDecimal propertyRegistration;
    private BigDecimal insurance;
    private BigDecimal other;
    private BigDecimal total;

    private String financialInstName;

    private String repaymentBank;
    private String repaymentBankBranch;
    private String repaymentBankAccountNumber;
    private String repaymentBankIfscCode;
    private String repaymentBankMicrCode;

    private String employeeBank;
    private String employeeAccountNumber;
    private String employeeBankBranch;
    private String employeeBankIfscCode;
    private String employeeBankMicrCode;
    private String employeeBankSwiftCode;

    private String altEmail;
    private String altContactNumber;

    private String loanCreatedBy;
    private String approvedBy;

    private BigDecimal amountDisbursed;

    private Long createdAtTimestamp;
    private Long updatedAtTimestamp;
    
    private String documentNo;

    @OneToOne
    private Bank bank;

    @OneToOne
    private PaymentMode paymentMode;

    private Date paymentDate;

    private String chequeNumber;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @OneToOne
    @JsonIgnore
    private LoanType loanType;

    @ManyToOne
    private LoanStatus loanStatus;

    @OneToOne(mappedBy = "loan")
    private LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails;

    @OneToMany(mappedBy = "loan")
    private List<LoanDocument> loanDocuments;

    @OneToOne(fetch = FetchType.LAZY)
    private SAPStatus sapStatus;

    private BigDecimal pfBase;
    private Date lastRecoveryDate;

    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Employee getEmployee() {
        return employee;
    }
      
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public LoanWithDrawalsFinalDetails getLoanWithDrawalsFinalDetails() {
        return loanWithDrawalsFinalDetails;
    }

    public void setLoanWithDrawalsFinalDetails(LoanWithDrawalsFinalDetails loanWithDrawalsFinalDetails) {
        this.loanWithDrawalsFinalDetails = loanWithDrawalsFinalDetails;
    }

    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDateOfCompletionOfHouse() {
        return dateOfCompletionOfHouse;
    }

    public void setDateOfCompletionOfHouse(Date dateOfCompletionOfHouse) {
        this.dateOfCompletionOfHouse = dateOfCompletionOfHouse;
    }

    public BigDecimal getPropertyEstimatedPrice() {
        return propertyEstimatedPrice;
    }

    public void setPropertyEstimatedPrice(BigDecimal propertyEstimatedPrice) {
        this.propertyEstimatedPrice = propertyEstimatedPrice;
    }

    public BigDecimal getStampDuty() {
        return stampDuty == null ? BigDecimal.valueOf(0) : stampDuty;
    }

    public void setStampDuty(BigDecimal stampDuty) {
        this.stampDuty = stampDuty;
    }

    public BigDecimal getPropertyRegistration() {
        return propertyRegistration == null ? BigDecimal.ZERO: propertyRegistration;
    }

    public void setPropertyRegistration(BigDecimal propertyRegistration) {
        this.propertyRegistration = propertyRegistration;
    }

    public BigDecimal getInsurance() {
        return insurance == null ? BigDecimal.ZERO : insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getOther() {
        return other == null ? BigDecimal.ZERO : other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getFinancialInstName() {
        return financialInstName;
    }

    public void setFinancialInstName(String financialInstName) {
        this.financialInstName = financialInstName;
    }

    public String getEmployeeBank() {
        return employeeBank;
    }

    public void setEmployeeBank(String employeeBank) {
        this.employeeBank = employeeBank;
    }

    public String getEmployeeAccountNumber() {
        return employeeAccountNumber;
    }

    public void setEmployeeAccountNumber(String employeeAccountNumber) {
        this.employeeAccountNumber = employeeAccountNumber;
    }

    public String getEmployeeBankBranch() {
        return employeeBankBranch;
    }

    public void setEmployeeBankBranch(String employeeBankBranch) {
        this.employeeBankBranch = employeeBankBranch;
    }

    public String getEmployeeBankIfscCode() {
        return employeeBankIfscCode;
    }

    public void setEmployeeBankIfscCode(String employeeBankIfscCode) {
        this.employeeBankIfscCode = employeeBankIfscCode;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public List<LoanDocument> getLoanDocuments() {
        return loanDocuments;
    }

    public void setLoanDocuments(List<LoanDocument> loanDocuments) {
        this.loanDocuments = loanDocuments;
    }

    public BigDecimal getEligibleAmount() {
        return eligibleAmount;
    }

    public void setEligibleAmount(BigDecimal eligibleAmount) {
        this.eligibleAmount = eligibleAmount;
    }

    public BigDecimal getLoanAmountOnPfBaseSalary() {
        return loanAmountOnPfBaseSalary;
    }

    public void setLoanAmountOnPfBaseSalary(BigDecimal loanAmountOnPfBaseSalary) {
        this.loanAmountOnPfBaseSalary = loanAmountOnPfBaseSalary;
    }

    public BigDecimal getLoanAmountOnPfBalance() {
        return loanAmountOnPfBalance;
    }

    public void setLoanAmountOnPfBalance(BigDecimal loanAmountOnPfBalance) {
        this.loanAmountOnPfBalance = loanAmountOnPfBalance;
    }

    public String getRepaymentBank() {
        return repaymentBank;
    }

    public void setRepaymentBank(String repaymentBank) {
        this.repaymentBank = repaymentBank;
    }

    public String getRepaymentBankBranch() {
        return repaymentBankBranch;
    }

    public void setRepaymentBankBranch(String repaymentBankBranch) {
        this.repaymentBankBranch = repaymentBankBranch;
    }

    public String getRepaymentBankAccountNumber() {
        return repaymentBankAccountNumber;
    }

    public void setRepaymentBankAccountNumber(String repaymentBankAccountNumber) {
        this.repaymentBankAccountNumber = repaymentBankAccountNumber;
    }

    public String getRepaymentBankIfscCode() {
        return repaymentBankIfscCode;
    }

    public void setRepaymentBankIfscCode(String repaymentBankIfscCode) {
        this.repaymentBankIfscCode = repaymentBankIfscCode;
    }

    public String getRepaymentBankMicrCode() {
        return repaymentBankMicrCode;
    }

    public void setRepaymentBankMicrCode(String repaymentBankMicrCode) {
        this.repaymentBankMicrCode = repaymentBankMicrCode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getEmployeeBankMicrCode() {
        return employeeBankMicrCode;
    }

    public void setEmployeeBankMicrCode(String employeeBankMicrCode) {
        this.employeeBankMicrCode = employeeBankMicrCode;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getAltContactNumber() {
        return altContactNumber;
    }

    public void setAltContactNumber(String altContactNumber) {
        this.altContactNumber = altContactNumber;
    }

    public Date getLoanApplicationDate() {
        return loanApplicationDate;
    }

    public void setLoanApplicationDate(Date loanApplicationDate) {
        this.loanApplicationDate = loanApplicationDate;
    }

    public Date getLoanApprovalDate() {
        return loanApprovalDate;
    }

    public void setLoanApprovalDate(Date loanApprovalDate) {
        this.loanApprovalDate = loanApprovalDate;
    }

    public Date getLoanDisbursalDate() {
        return loanDisbursalDate;
    }

    public void setLoanDisbursalDate(Date loanDisbursalDate) {
        this.loanDisbursalDate = loanDisbursalDate;
    }

    public Date getLoanApplicationReceivedDate() {
        return  loanApplicationReceivedDate;
    }

    public void setLoanApplicationReceivedDate(Date loanApplicationReceivedDate) {
        this.loanApplicationReceivedDate = loanApplicationReceivedDate;
    }

    public Date getDateOfFirstAlteration() {
        return dateOfFirstAlteration;
    }

    public void setDateOfFirstAlteration(Date dateOfFirstAlteration) {
        this.dateOfFirstAlteration = dateOfFirstAlteration;
    }

    public String getLoanCreatedBy() {
        return loanCreatedBy;
    }

    public void setLoanCreatedBy(String createdBy) {
        this.loanCreatedBy = createdBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public BigDecimal getAmountDisbursed() {
        return amountDisbursed;
    }

    public void setAmountDisbursed(BigDecimal amountDisbursed) {
        this.amountDisbursed = amountDisbursed;
    }

    public String getEmployeeBankSwiftCode() {
        return employeeBankSwiftCode;
    }

    public void setEmployeeBankSwiftCode(String employeeBankSwiftCode) {
        this.employeeBankSwiftCode = employeeBankSwiftCode;
    }

    public Long getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public void setCreatedAtTimestamp(Long createdAtTimestamp) {
        this.createdAtTimestamp = createdAtTimestamp;
    }

    public Long getUpdatedAtTimestamp() {
        return updatedAtTimestamp;
    }

    public void setUpdatedAtTimestamp(Long updatedAtTimestamp) {
        this.updatedAtTimestamp = updatedAtTimestamp;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public SAPStatus getSapStatus() {
        return sapStatus;
    }

    public void setSapStatus(SAPStatus sapStatus) {
        this.sapStatus = sapStatus;
    }
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

    public BigDecimal getPfBase() {
        return pfBase;
    }

    public void setPfBase(BigDecimal pfBase) {
        this.pfBase = pfBase;
    }

    public Date getLastRecoveryDate() {
        return lastRecoveryDate;
    }

    public void setLastRecoveryDate(Date lastRecoveryDate) {
        this.lastRecoveryDate = lastRecoveryDate;
    }

    public BigDecimal getTotalValue(){
        return getStampDuty()
                .add(getPropertyRegistration())
                .add(getInsurance())
                .add(getOther());
    }


    public static Loan build(JSONObject jsonObject) throws ParseException {

        Loan loan = new Loan();

        return build(jsonObject, loan);
    }


    public static Loan build(JSONObject jsonObject, Loan loan) throws ParseException {

        loan.setTotalCost(jsonObject.getBigDecimal("totalCost"));
        loan.setAppliedAmount(jsonObject.getBigDecimal("appliedAmount"));

        loan.setDateOfCompletionOfHouse(FinancialYearAndMonth.getDate(DataUtil.getString(jsonObject, "dateOfCompletionOfHouse")));
        loan.setPropertyRegistration(jsonObject.getBigDecimal("propertyRegistration"));
        loan.setPropertyEstimatedPrice(jsonObject.getBigDecimal("propertyEstimatedPrice"));
        loan.setStampDuty(jsonObject.getBigDecimal("stampDuty"));

        loan.setInsurance(jsonObject.getBigDecimal("insurance"));
        loan.setOther(jsonObject.getBigDecimal("others"));
        loan.setTotal(jsonObject.getBigDecimal("total"));

        loan.setFinancialInstName(DataUtil.getString(jsonObject, "financialInstituteName"));
        loan.setRepaymentBank(DataUtil.getString(jsonObject, "repaymentBank"));
        loan.setRepaymentBankBranch(DataUtil.getString(jsonObject, "repaymentBankBranch"));
        loan.setRepaymentBankAccountNumber(DataUtil.getString(jsonObject, "repaymentBankAccountNumber"));
        loan.setRepaymentBankIfscCode(DataUtil.getString(jsonObject, "repaymentBankIfscCode"));
        loan.setRepaymentBankMicrCode(DataUtil.getString(jsonObject, "repaymentBankMicrCode"));

        loan.setEmployeeBank(DataUtil.getString(jsonObject, "employeeBank"));
        loan.setEmployeeAccountNumber(DataUtil.getString(jsonObject, "employeeAccountNumber"));
        loan.setEmployeeBankBranch(DataUtil.getString(jsonObject, "employeeBankBranch"));
        loan.setEmployeeBankIfscCode(DataUtil.getString(jsonObject, "employeeBankIfscCode"));
        loan.setEmployeeBankMicrCode(DataUtil.getString(jsonObject, "employeeBankMicrCode"));

        loan.setAltContactNumber(jsonObject.getString("altContactNumber"));
        loan.setAltEmail(jsonObject.getString("altEmailId"));

        loan.setCreatedAtTimestamp(new Date().getTime());
        loan.setUpdatedAtTimestamp(new Date().getTime());

        loan.setLoanApplicationDate(new Date());
        loan.setLoanApplicationReceivedDate(FinancialYearAndMonth.getDate(DataUtil.getString(jsonObject,
                "loanApplicationReceivedDate")));

        return loan;

    }





}
