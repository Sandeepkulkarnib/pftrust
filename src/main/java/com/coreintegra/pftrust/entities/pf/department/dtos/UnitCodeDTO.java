package com.coreintegra.pftrust.entities.pf.department.dtos;

public class UnitCodeDTO {

    private String unitcode;
    private Boolean active;
    private String createdAt;
    private String updatedAt;

    public UnitCodeDTO(String unitcode, Boolean active, String createdAt, String updatedAt) {
        this.unitcode = unitcode;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
