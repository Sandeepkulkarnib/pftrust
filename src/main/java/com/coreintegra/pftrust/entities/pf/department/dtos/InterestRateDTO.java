package com.coreintegra.pftrust.entities.pf.department.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InterestRateDTO {

    private String description;

    private Double interestRate;

    private Integer year;

    private Date createdAt;
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;

    private Boolean isActive;

    public InterestRateDTO(String description, Double interestRate, Integer year, Date createdAt,
                           Date updatedAt, String createdBy, String updatedBy, Boolean isActive) {
        this.description = description;
        this.interestRate = interestRate;
        this.year = year;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm", timezone = "Asia/Kolkata")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm", timezone = "Asia/Kolkata")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
