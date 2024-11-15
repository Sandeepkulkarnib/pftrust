package com.coreintegra.pftrust.entities.administration.dtos;

public class SubDepartmentDto {

    private String name;
    private String label;
    private String type;
    private String mdiIcon;
    private String path;
    private String permission;

    public SubDepartmentDto(String name, String label, String type, String mdiIcon,
                            String path, String permission) {
        this.name = name;
        this.label = label;
        this.type = type;
        this.mdiIcon = mdiIcon;
        this.path = path;
        this.permission = permission;
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

    public String getMdiIcon() {
        return mdiIcon;
    }

    public void setMdiIcon(String mdiIcon) {
        this.mdiIcon = mdiIcon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
