package com.coreintegra.pftrust.entities.administration;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class PfDepartment extends BaseAuditingEntity {

    private String name;
    private String label;
    private String type;
    private String mdiIcon;
    private String path;

    @OneToMany(mappedBy = "pfDepartment")
    private List<SubDepartment> subDepartments;

    @OneToMany(mappedBy = "pfDepartment")
    private List<PfDepartmentRole> pfDepartmentRoles;

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

    public List<SubDepartment> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(List<SubDepartment> subDepartments) {
        this.subDepartments = subDepartments;
    }

    public List<PfDepartmentRole> getPfDepartmentRoles() {
        return pfDepartmentRoles;
    }

    public void setPfDepartmentRoles(List<PfDepartmentRole> pfDepartmentRoles) {
        this.pfDepartmentRoles = pfDepartmentRoles;
    }
}
