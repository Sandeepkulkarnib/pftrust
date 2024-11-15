package com.coreintegra.pftrust.entities.administration.dtos;

import java.util.List;

public class UserDepartmentPermissionsDto {

    private String name;
    private String label;
    private String type;
    private String icon;
    private String path;
    private List<String> permissions;
    private List<SubDepartmentDto> subDepartments;

    public UserDepartmentPermissionsDto(String name, String label, String type, String icon, String path, List<String> permissions, List<SubDepartmentDto> subDepartmentDtos) {
        this.name = name;
        this.label = label;
        this.type = type;
        this.icon = icon;
        this.path = path;
        this.permissions = permissions;
        this.subDepartments = subDepartmentDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SubDepartmentDto> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(List<SubDepartmentDto> subDepartments) {
        this.subDepartments = subDepartments;
    }
}
