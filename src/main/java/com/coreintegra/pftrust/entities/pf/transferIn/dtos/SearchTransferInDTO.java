package com.coreintegra.pftrust.entities.pf.transferIn.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class SearchTransferInDTO {

    private String entityId;
    private String pernNumber;
    private String pfNumber;
    private String name;
    private String unitCode;

    private String prevCompany;
    private String prevPfNumber;

    private Date postingDate;

    @JsonIgnore
    private final Long createdAt;

    private String status;

    public SearchTransferInDTO(String entityId,String pernNumber, String pfNumber, String name, String unitCode, String prevCompany,
                               String prevPfNumber, Date postingDate, Long createdAt, String status) {
        this.entityId = entityId;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.name = name;
        this.unitCode = unitCode;
        this.prevCompany = prevCompany;
        this.prevPfNumber = prevPfNumber;
        this.postingDate = postingDate;
        this.createdAt = createdAt;
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

    public String getPrevCompany() {
        return prevCompany;
    }

    public void setPrevCompany(String prevCompany) {
        this.prevCompany = prevCompany;
    }

    public String getPrevPfNumber() {
        return prevPfNumber;
    }

    public void setPrevPfNumber(String prevPfNumber) {
        this.prevPfNumber = prevPfNumber;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getCreatedAt(){
        if(this.createdAt != null){
            return new Date(this.createdAt);
        }
        return null;
    }

}
