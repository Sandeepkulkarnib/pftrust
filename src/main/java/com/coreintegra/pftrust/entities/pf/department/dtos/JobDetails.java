package com.coreintegra.pftrust.entities.pf.department.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class JobDetails {

    private String unitCode;
    private Instant startedAt;
    private Instant completedAt;
    private Long duration;
    private Long success;
    private Long failed;
    private String status;
    private Long total;
    private Integer year;
    private Integer month;

    public JobDetails(String unitCode, Instant startedAt, Instant completedAt, Long duration, Long success,
                      Long failed, String status, Long total) {
        this.unitCode = unitCode;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.duration = duration;
        this.success = success;
        this.failed = failed;
        this.status = status;
        this.total = total;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Kolkata")
    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Kolkata")
    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }

    public Long getFailed() {
        return failed;
    }

    public void setFailed(Long failed) {
        this.failed = failed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
