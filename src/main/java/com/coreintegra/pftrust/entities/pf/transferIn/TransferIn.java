package com.coreintegra.pftrust.entities.pf.transferIn;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.PfAccountHolder;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.State;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutType;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class TransferIn extends BaseAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    @ManyToOne
    private Employee employee;

    private String alternateContactNumber;
    private String alternateEmailId;

    @OneToOne
    private PreviousCompany previousCompany;

    private String presPfNumber;
    private String presEPSNumber;

    private String prevPfNumber;
    private String prevPensionAccountNumber;

    private Date prevDateOfJoining;
    private Date prevDateOfLeaving;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String sapDocumentNumber;
    private String pincode;

    private String chequeNumber;
    private String bank;
    private Date postingDate;
    private Date dateOfJoiningPrior;
    private String refNumber;

    private String annexureKFile;
    private String dispatchLetterFile;

    @OneToOne
    private State state;

    @OneToOne
    private PfAccountHolder prevPfAccountHolder;

    @OneToOne(mappedBy = "transferIn")
    private TransferInDocuments transferInDocuments;

    @OneToOne(mappedBy = "transferIn")
    private TransferInFinalDetails transferInFinalDetails;

    @OneToOne
    private TransferInStatus transferInStatus;

    @OneToMany(mappedBy = "transferIn", fetch = FetchType.EAGER)
    private List<TransferInReminder> transferInReminders;

    private String transferInCreatedBy;

    private Long createdAtTimestamp;
    private Long updatedAtTimestamp;

    @OneToOne(fetch = FetchType.LAZY)
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPrevPfNumber() {
        return prevPfNumber;
    }

    public void setPrevPfNumber(String prevPfNumber) {
        this.prevPfNumber = prevPfNumber;
    }

    public String getPrevPensionAccountNumber() {
        return prevPensionAccountNumber;
    }

    public void setPrevPensionAccountNumber(String prevPensionAccountNumber) {
        this.prevPensionAccountNumber = prevPensionAccountNumber;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getPrevDateOfJoining() {
        return prevDateOfJoining;
    }

    public void setPrevDateOfJoining(Date prevDateOfJoining) {
        this.prevDateOfJoining = prevDateOfJoining;
    }

    public Date getPrevDateOfLeaving() {
        return prevDateOfLeaving;
    }

    public void setPrevDateOfLeaving(Date prevDateOfLeaving) {
        this.prevDateOfLeaving = prevDateOfLeaving;
    }

    public PfAccountHolder getPrevPfAccountHolder() {
        return prevPfAccountHolder;
    }

    public void setPrevPfAccountHolder(PfAccountHolder prevPfAccountHolder) {
        this.prevPfAccountHolder = prevPfAccountHolder;
    }

    public TransferInDocuments getTransferInDocuments() {
        return transferInDocuments;
    }

    public void setTransferInDocuments(TransferInDocuments transferInDocuments) {
        this.transferInDocuments = transferInDocuments;
    }

    public TransferInFinalDetails getTransferInFinalDetails() {
        return transferInFinalDetails;
    }

    public void setTransferInFinalDetails(TransferInFinalDetails transferInFinalDetails) {
        this.transferInFinalDetails = transferInFinalDetails;
    }

    public PreviousCompany getPreviousCompany() {
        return previousCompany;
    }

    public void setPreviousCompany(PreviousCompany previousCompany) {
        this.previousCompany = previousCompany;
    }

    public TransferInStatus getTransferInStatus() {
        return transferInStatus;
    }

    public void setTransferInStatus(TransferInStatus transferInStatus) {
        this.transferInStatus = transferInStatus;
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

    public String getPresPfNumber() {
        return presPfNumber;
    }

    public void setPresPfNumber(String presPfNumber) {
        this.presPfNumber = presPfNumber;
    }

    public String getPresEPSNumber() {
        return presEPSNumber;
    }

    public void setPresEPSNumber(String presEPSNumber) {
        this.presEPSNumber = presEPSNumber;
    }

    public List<TransferInReminder> getTransferInReminders() {
        return transferInReminders;
    }

    public void setTransferInReminders(List<TransferInReminder> transferInReminders) {
        this.transferInReminders = transferInReminders;
    }

    public String getTransferInCreatedBy() {
        return transferInCreatedBy;
    }

    public void setTransferInCreatedBy(String createdBy) {
        this.transferInCreatedBy = createdBy;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getSapDocumentNumber() {
        return sapDocumentNumber;
    }

    public void setSapDocumentNumber(String sapDocumentNumber) {
        this.sapDocumentNumber = sapDocumentNumber;
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

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getDateOfJoiningPrior() {
        return dateOfJoiningPrior;
    }

    public void setDateOfJoiningPrior(Date dateOfJoiningPrior) {
        this.dateOfJoiningPrior = dateOfJoiningPrior;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getAnnexureKFile() {
        return annexureKFile;
    }

    public void setAnnexureKFile(String annexureKFile) {
        this.annexureKFile = annexureKFile;
    }

    public String getDispatchLetterFile() {
        return dispatchLetterFile;
    }

    public void setDispatchLetterFile(String dispatchLetterFile) {
        this.dispatchLetterFile = dispatchLetterFile;
    }

    public SAPStatus getSapStatus() {
        return sapStatus;
    }

    public void setSapStatus(SAPStatus sapStatus) {
        this.sapStatus = sapStatus;
    }

    public TransferIn set(JSONObject jsonObject){

        this.setAlternateContactNumber(jsonObject.getString("alternateMobileNumber"));
        this.setAlternateEmailId(jsonObject.getString("alternateEmailId"));

        this.setPresPfNumber(jsonObject.getString("pfAccountNumber"));
        this.setPresEPSNumber(jsonObject.getString("epsAccountNumber"));

        this.setPrevPfNumber(jsonObject.getString("prevPfNumber"));
        this.setPrevPensionAccountNumber(jsonObject.getString("prevEpsNumber"));

        this.setAddressLine1(jsonObject.getString("addressLine1"));
        this.setAddressLine2(jsonObject.getString("addressLine2"));
        this.setAddressLine3(jsonObject.getString("addressLine3"));
        this.setAddressLine4(jsonObject.getString("addressLine4"));

        return this;

    }

    public static TransferIn build(JSONObject jsonObject) throws ParseException {

        TransferIn transferIn = new TransferIn();

        return transferIn.set(jsonObject);

    }

}
