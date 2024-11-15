
package com.coreintegra.pftrust.entities.pf.transferOut;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.SettlementInterface;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
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
public class TransferOut extends BaseAuditingEntity implements SettlementInterface {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private String documentNumber;
    private float interestRate;

    private Date dateOfLeavingService;
    private Date dueDate;

	private String payeeName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String fromPfNumber;
    private String fromEpsNumber;
    private String fromUanNumber;

	private String toPfNumber;
    private String toEpsNumber;
    private String toUanNumber;

    private String bankName;
    private String accountNumber;
    private String branch;
    private String micrCode;
    private String ifscCode;

    private String chequeNo;
    private Date paymentDate;
    private BigDecimal amount;

    private String currentEmployerName;
    private String currentEmployerAddressLine1;
    private String currentEmployerAddressLine2;
    private String currentEmployerAddressLine3;
    private String currentEmployerAddressLine4;

    private String alternateContactNumber;
    private String alternateEmailId;

    private String transferOutCreatedBy;

    private Long createdAtTimestamp;
    private Long updatedAtTimestamp;
    
    private BigDecimal incomeTax;
    private BigDecimal eduCess;

    @OneToOne
    private Bank bank;

    @OneToOne
    private PaymentMode paymentMode;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private TransferOutType transferOutType;

    @OneToOne
    private TransferOutStatus transferOutStatus;

    @OneToMany(mappedBy = "transferOut")
    private List<TransferOutDocument> transferOutDocuments;

    @OneToOne(mappedBy = "transferOut")
    private TransferOutFinalDetails transferOutFinalDetails;
    
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

    public SAPStatus getSapStatus() {
		return sapStatus;
	}

	public void setSapStatus(SAPStatus sapStatus) {
		this.sapStatus = sapStatus;
	}

	public Date getDateOfLeavingService() {
        return dateOfLeavingService;
    }

    public void setDateOfLeavingService(Date dateOfLeavingService) {
        this.dateOfLeavingService = dateOfLeavingService;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
    	return ifscCode;
    	}
	  
	  public void setIfscCode(String ifscCode) { 
		  this.ifscCode = ifscCode; 
		  }
    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
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

	  public String getAccountNumber() { 
		  return accountNumber;
		  }
	  
	  public void setAccountNumber(String accountNumber) { 
		  this.accountNumber =
	  accountNumber;
		  }
    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getFromPfNumber() {
        return fromPfNumber;
    }

    public void setFromPfNumber(String fromPfNumber) {
        this.fromPfNumber = fromPfNumber;
    }

    public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getFromEpsNumber() {
        return fromEpsNumber;
    }

    public void setFromEpsNumber(String fromEpsNumber) {
        this.fromEpsNumber = fromEpsNumber;
    }

    public String getFromUanNumber() {
        return fromUanNumber;
    }

    public void setFromUanNumber(String fromUanNumber) {
        this.fromUanNumber = fromUanNumber;
    }

    public String getToPfNumber() {
        return toPfNumber;
    }

    public void setToPfNumber(String toPfNumber) {
        this.toPfNumber = toPfNumber;
    }

    public String getToEpsNumber() {
        return toEpsNumber;
    }

    public void setToEpsNumber(String toEpsNumber) {
        this.toEpsNumber = toEpsNumber;
    }

    public String getToUanNumber() {
        return toUanNumber;
    }

    public void setToUanNumber(String toUanNumber) {
        this.toUanNumber = toUanNumber;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrentEmployerName() {
        return currentEmployerName;
    }

    public void setCurrentEmployerName(String currentEmployerName) {
        this.currentEmployerName = currentEmployerName;
    }

    public String getCurrentEmployerAddressLine1() {
        return currentEmployerAddressLine1;
    }

    public void setCurrentEmployerAddressLine1(String currentEmployerAddressLine1) {
        this.currentEmployerAddressLine1 = currentEmployerAddressLine1;
    }

    public String getCurrentEmployerAddressLine2() {
        return currentEmployerAddressLine2;
    }

    public void setCurrentEmployerAddressLine2(String currentEmployerAddressLine2) {
        this.currentEmployerAddressLine2 = currentEmployerAddressLine2;
    }

    public String getCurrentEmployerAddressLine3() {
        return currentEmployerAddressLine3;
    }

    public void setCurrentEmployerAddressLine3(String currentEmployerAddressLine3) {
        this.currentEmployerAddressLine3 = currentEmployerAddressLine3;
    }

    public String getCurrentEmployerAddressLine4() {
        return currentEmployerAddressLine4;
    }

    public void setCurrentEmployerAddressLine4(String currentEmployerAddressLine4) {
        this.currentEmployerAddressLine4 = currentEmployerAddressLine4;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TransferOutType getTransferOutType() {
        return transferOutType;
    }

    public void setTransferOutType(TransferOutType transferOutType) {
        this.transferOutType = transferOutType;
    }

    public TransferOutStatus getTransferOutStatus() {
        return transferOutStatus;
    }

    public void setTransferOutStatus(TransferOutStatus transferOutStatus) {
        this.transferOutStatus = transferOutStatus;
    }

    public List<TransferOutDocument> getTransferOutDocuments() {
        return transferOutDocuments;
    }

    public void setTransferOutDocuments(List<TransferOutDocument> transferOutDocuments) {
        this.transferOutDocuments = transferOutDocuments;
    }

    public TransferOutFinalDetails getTransferOutFinalDetails() {
        return transferOutFinalDetails;
    }

    public void setTransferOutFinalDetails(TransferOutFinalDetails transferOutFinalDetails) {
        this.transferOutFinalDetails = transferOutFinalDetails;
    }

    public String getTransferOutCreatedBy() {
        return transferOutCreatedBy;
    }

    public void setTransferOutCreatedBy(String createdBy) {
        this.transferOutCreatedBy = createdBy;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getAlternateEmailId() {
        return alternateEmailId;
    }

    public void setAlternateEmailId(String alternateEmailId) {
        this.alternateEmailId = alternateEmailId;
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

    public static TransferOut build(JSONObject jsonObject) throws ParseException {
        TransferOut transferOut = new TransferOut();
        return build(transferOut, jsonObject);
    }

    public static TransferOut build(TransferOut transferOut, JSONObject jsonObject) throws ParseException {

        transferOut.setDateOfLeavingService(FinancialYearAndMonth.getDate(jsonObject.getString("dateOfLeavingService")));
        transferOut.setDueDate(FinancialYearAndMonth.getDate(jsonObject.getString("dueDate")));

        transferOut.setAlternateEmailId(jsonObject.getString("altEmailId"));
        transferOut.setAlternateContactNumber(jsonObject.getString("altContactNumber"));

        transferOut.setPayeeName(jsonObject.getString("payeeName"));
        transferOut.setAddressLine1(jsonObject.getString("addressLine1"));
        transferOut.setAddressLine2(jsonObject.getString("addressLine2"));
        transferOut.setAddressLine3(jsonObject.getString("addressLine3"));
        transferOut.setAddressLine4(jsonObject.getString("addressLine4"));

        transferOut.setBankName(jsonObject.getString("bankName"));
        transferOut.setBranch(jsonObject.getString("branch"));
        transferOut.setAccountNumber(jsonObject.getString("accountNumber"));
        transferOut.setIfscCode(jsonObject.getString("ifscCode"));
        transferOut.setMicrCode(jsonObject.getString("micrCode"));

        transferOut.setFromPfNumber(jsonObject.getString("fromPfNumber"));
        transferOut.setFromEpsNumber(jsonObject.getString("fromEpsNumber"));
        transferOut.setFromUanNumber(jsonObject.getString("fromUanNumber"));

        transferOut.setToPfNumber(jsonObject.getString("toPfNumber"));
        transferOut.setToEpsNumber(jsonObject.getString("toEpsNumber"));
        transferOut.setToUanNumber(jsonObject.getString("toUanNumber"));

        transferOut.setCurrentEmployerName(jsonObject.getString("currentEmployerName"));
        transferOut.setCurrentEmployerAddressLine1(jsonObject.getString("currentEmployerAddressLine1"));
        transferOut.setCurrentEmployerAddressLine2(jsonObject.getString("currentEmployerAddressLine2"));
        transferOut.setCurrentEmployerAddressLine3(jsonObject.getString("currentEmployerAddressLine3"));
        transferOut.setCurrentEmployerAddressLine4(jsonObject.getString("currentEmployerAddressLine4"));

        transferOut.setCreatedAtTimestamp(new Date().getTime());
        transferOut.setUpdatedAtTimestamp(new Date().getTime());

        return transferOut;

    }

	public BigDecimal getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	public BigDecimal getEduCess() {
		return eduCess;
	}

	public void setEduCess(BigDecimal eduCess) {
		this.eduCess = eduCess;
	}

    
}
