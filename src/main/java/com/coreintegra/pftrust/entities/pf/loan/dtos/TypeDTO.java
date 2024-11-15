package com.coreintegra.pftrust.entities.pf.loan.dtos;

public class TypeDTO {

    private String entityId;
    private String type;

    public TypeDTO(String entityId, String type) {
        this.entityId = entityId;
        this.type = type;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
