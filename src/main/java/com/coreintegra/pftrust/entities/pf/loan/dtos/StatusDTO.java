package com.coreintegra.pftrust.entities.pf.loan.dtos;

public class StatusDTO {

    private String entityId;
    private String status;

    public StatusDTO(String entityId, String status) {
        this.entityId = entityId;
        this.status = status;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
