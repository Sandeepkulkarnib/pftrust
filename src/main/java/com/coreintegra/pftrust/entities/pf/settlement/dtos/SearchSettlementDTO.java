package com.coreintegra.pftrust.entities.pf.settlement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SearchSettlementDTO {

    private String entityId;
    private String pernNumber;
    private String pfNumber;
    private String name;
    private String unitCode;

    private Date dateOfLeavingService;
    private Date dateOfSettlement;

    private String payeeName;

    private Date paymentDate;

    private String settlementType;
    private String status;

    public SearchSettlementDTO(String entityId, String pernNumber, String pfNumber, String name, String unitCode,
                               Date dateOfLeavingService, Date dateOfSettlement, String payeeName,
                               Date paymentDate, String settlementType, String status) {
        this.entityId = entityId;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.name = name;
        this.unitCode = unitCode;
        this.dateOfLeavingService = dateOfLeavingService;
        this.dateOfSettlement = dateOfSettlement;
        this.payeeName = payeeName;
        this.paymentDate = paymentDate;
        this.settlementType = settlementType;
        this.status = status;
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

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfLeavingService() {
        return dateOfLeavingService;
    }

    public void setDateOfLeavingService(Date dateOfLeavingService) {
        this.dateOfLeavingService = dateOfLeavingService;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfSettlement() {
        return dateOfSettlement;
    }

    public void setDateOfSettlement(Date dateOfSettlement) {
        this.dateOfSettlement = dateOfSettlement;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
