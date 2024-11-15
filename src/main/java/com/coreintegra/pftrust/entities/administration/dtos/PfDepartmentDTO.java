package com.coreintegra.pftrust.entities.administration.dtos;

import java.util.List;

public class PfDepartmentDTO {

    private String name;

    List<PfDepartmentRoleDTO> pfDepartmentRoles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PfDepartmentRoleDTO> getPfDepartmentRoles() {
        return pfDepartmentRoles;
    }

    public void setPfDepartmentRoles(List<PfDepartmentRoleDTO> pfDepartmentRoles) {
        this.pfDepartmentRoles = pfDepartmentRoles;
    }

}
