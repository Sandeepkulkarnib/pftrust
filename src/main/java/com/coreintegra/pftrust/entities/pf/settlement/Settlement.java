package com.coreintegra.pftrust.entities.pf.settlement;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.SettlementInterface;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Settlement extends BaseAuditingEntity implements SettlementInterface {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private String documentNumber;

    private float interestRate;

    private Date dateOfLeavingService;
    private Date dateOfSettlement;

    private String altEmailId;
    private String altContactNumber;

    private String payeeName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String bankName;
    private String branch;
    private String accountNumber;
    private String ifscCode;
    private String micrCode;
    private String swift;

    private BigDecimal incomeTax;
    private BigDecimal eduCess;

    private String chequeNo;
    private Date paymentDate;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private SettlementType settlementType;

    @OneToMany(mappedBy = "settlement")
    private List<SettlementDocument> settlementDocuments;

    @OneToOne
    private Bank bank;

    @OneToOne
    private PaymentMode paymentMode;

    @OneToOne(mappedBy = "settlement")
    @JsonIgnore
    private SettlementFinalDetails settlementFinalDetails;

    @OneToOne
    private SettlementStatus settlementStatus;

    private String settlementCreatedBy;

    private Long createdAtTimestamp;
    private Long updatedAtTimestamp;

    @OneToOne
    private SAPStatus sapStatus;

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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public Date getDateOfLeavingService() {
        return dateOfLeavingService;
    }

    public void setDateOfLeavingService(Date dateOfLeavingService) {
        this.dateOfLeavingService = dateOfLeavingService;
    }

    public Date getDateOfSettlement() {
        return dateOfSettlement;
    }

    public void setDateOfSettlement(Date dateOfSettlement) {
        this.dateOfSettlement = dateOfSettlement;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax == null ? new BigDecimal(0) : incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getEduCess() {
        return eduCess == null ? new BigDecimal(0) : eduCess;
    }

    public void setEduCess(BigDecimal eduCess) {
        this.eduCess = eduCess;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public SettlementType getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(SettlementType settlementType) {
        this.settlementType = settlementType;
    }

    public List<SettlementDocument> getSettlementDocuments() {
        return settlementDocuments;
    }

    public void setSettlementDocuments(List<SettlementDocument> settlementDocuments) {
        this.settlementDocuments = settlementDocuments;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public SettlementFinalDetails getSettlementFinalDetails() {
        return settlementFinalDetails;
    }

    public void setSettlementFinalDetails(SettlementFinalDetails settlementFinalDetails) {
        this.settlementFinalDetails = settlementFinalDetails;
    }

    public SettlementStatus getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(SettlementStatus settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getSettlementCreatedBy() {
        return settlementCreatedBy;
    }

    public void setSettlementCreatedBy(String createdBy) {
        this.settlementCreatedBy = createdBy;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getAltEmailId() {
        return altEmailId;
    }

    public void setAltEmailId(String altEmailId) {
        this.altEmailId = altEmailId;
    }

    public String getAltContactNumber() {
        return altContactNumber;
    }

    public void setAltContactNumber(String altContactNumber) {
        this.altContactNumber = altContactNumber;
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

    public SAPStatus getSapStatus() {
        return sapStatus;
    }

    public void setSapStatus(SAPStatus sapStatus) {
        this.sapStatus = sapStatus;
    }

    public static Settlement build(JSONObject jsonObject) throws ParseException {
        Settlement settlement = new Settlement();
        return build(settlement, jsonObject);
    }

    public static Settlement build(Settlement settlement, JSONObject jsonObject) throws ParseException {

        settlement.setDateOfLeavingService(FinancialYearAndMonth.getDate(jsonObject.getString("dateOfLeavingService")));
        settlement.setDateOfSettlement(FinancialYearAndMonth.getDate(jsonObject.getString("dateOfSettlement")));

        settlement.setAltEmailId(jsonObject.getString("altEmailId"));
        settlement.setAltContactNumber(jsonObject.getString("altContactNumber"));

        settlement.setPayeeName(jsonObject.getString("payeeName"));
        settlement.setAddressLine1(jsonObject.getString("addressLine1"));
        settlement.setAddressLine2(jsonObject.getString("addressLine2"));
        settlement.setAddressLine3(jsonObject.getString("addressLine3"));
        settlement.setAddressLine4(jsonObject.getString("addressLine4"));

        settlement.setBankName(jsonObject.getString("bankName"));
        settlement.setBranch(jsonObject.getString("branch"));
        settlement.setAccountNumber(jsonObject.getString("accountNumber"));
        settlement.setIfscCode(jsonObject.getString("ifscCode"));
        settlement.setMicrCode(jsonObject.getString("micrCode"));
        settlement.setSwift(jsonObject.getString("swift"));

        settlement.setIncomeTax(jsonObject.getBigDecimal("incomeTax"));
        settlement.setEduCess(jsonObject.getBigDecimal("eduCess"));

        settlement.setCreatedAtTimestamp(new Date().getTime());
        settlement.setUpdatedAtTimestamp(new Date().getTime());

        return settlement;

    }







}

