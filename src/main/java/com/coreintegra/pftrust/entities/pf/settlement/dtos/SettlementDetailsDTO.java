package com.coreintegra.pftrust.entities.pf.settlement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SettlementDetailsDTO {

    private String entityId;

    private String payeeName;
    private Date dateLeavingService;
    private Date dateOfSettlement;
    private String email;
    private String contactNumber;
    private String type;
    private String status;
    private Date paymentDate;
    private BigDecimal totalContribution;
    private BigDecimal netCreditAmount;
    private String documentNumber;

    private Boolean isSettlement;

    public SettlementDetailsDTO(String entityId, String payeeName, Date dateLeavingService,
                                Date dateOfSettlement, String email, String contactNumber, String type,
                                String status, Date paymentDate, BigDecimal totalContribution,
                                BigDecimal netCreditAmount, String documentNumber, Boolean isSettlement) {
        this.entityId = entityId;
        this.payeeName = payeeName;
        this.dateLeavingService = dateLeavingService;
        this.dateOfSettlement = dateOfSettlement;
        this.email = email;
        this.contactNumber = contactNumber;
        this.type = type;
        this.status = status;
        this.paymentDate = paymentDate;
        this.totalContribution = totalContribution;
        this.netCreditAmount = netCreditAmount;
        this.documentNumber = documentNumber;
        this.isSettlement = isSettlement;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateLeavingService() {
        return dateLeavingService;
    }

    public void setDateLeavingService(Date dateLeavingService) {
        this.dateLeavingService = dateLeavingService;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfSettlement() {
        return dateOfSettlement;
    }

    public void setDateOfSettlement(Date dateOfSettlement) {
        this.dateOfSettlement = dateOfSettlement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BigDecimal getTotalContribution() {
        return totalContribution;
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    public BigDecimal getNetCreditAmount() {
        return netCreditAmount;
    }

    public void setNetCreditAmount(BigDecimal netCreditAmount) {
        this.netCreditAmount = netCreditAmount;
    }

    public void setSettlement(Boolean settlement) {
        isSettlement = settlement;
    }

    public Boolean getSettlement() {
        return isSettlement;
    }
}
