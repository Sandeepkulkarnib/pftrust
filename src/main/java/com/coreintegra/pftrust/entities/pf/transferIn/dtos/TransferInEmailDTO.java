package com.coreintegra.pftrust.entities.pf.transferIn.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TransferInEmailDTO {

    private String entityId;
    private String pfNumber;
    private String name;
    private String unitCode;
    private Date postingDate;
    private String prevCompany;
    private String emailId;

    public TransferInEmailDTO() {
    }

    public TransferInEmailDTO(String entityId, String pfNumber, String name, String unitCode, Date postingDate,
                              String prevCompany, String emailId) {
        this.entityId = entityId;
        this.pfNumber = pfNumber;
        this.name = name;
        this.unitCode = unitCode;
        this.postingDate = postingDate;
        this.prevCompany = prevCompany;
        this.emailId = emailId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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
    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getPrevCompany() {
        return prevCompany;
    }

    public void setPrevCompany(String prevCompany) {
        this.prevCompany = prevCompany;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
