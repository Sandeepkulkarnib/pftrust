package com.coreintegra.pftrust.entities.pf.employee.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SearchEmployeeDTO {

    private String entityId;
    private String tokenNumber;
    private String pernNumber;
    private String pfNumber;

    private String name;

    private Date dateOfJoining;
    private Date dateOfJoiningPF;
    private Date dateOfBirth;

    private String unitCode;

    private String status;

    public SearchEmployeeDTO(String entityId, String tokenNumber, String pernNumber, String pfNumber, String name,
                             Date dateOfJoining, Date dateOfJoiningPF, Date dateOfBirth,
                             String unitCode, String status) {
        this.entityId = entityId;
        this.tokenNumber = tokenNumber;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.name = name;
        this.dateOfJoining = dateOfJoining;
        this.dateOfJoiningPF = dateOfJoiningPF;
        this.dateOfBirth = dateOfBirth;
        this.unitCode = unitCode;
        this.status = status;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
