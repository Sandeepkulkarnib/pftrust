package com.coreintegra.pftrust.entities.administration;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SubDepartment extends BaseAuditingEntity {

    private String name;
    private String label;
    private String type;
    private String mdiIcon;
    private String path;
    private String permission;

    @ManyToOne
    @JsonIgnore
    private PfDepartment pfDepartment;

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

    public PfDepartment getPfDepartment() {
        return pfDepartment;
    }

    public void setPfDepartment(PfDepartment pfDepartment) {
        this.pfDepartment = pfDepartment;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
