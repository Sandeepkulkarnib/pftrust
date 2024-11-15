package com.coreintegra.pftrust.entities.administration.dtos;

public class PfDepartmentRoleDTO {

    private String name;
    private String entity;

    public PfDepartmentRoleDTO(String name, String entity) {
        this.name = name;
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
