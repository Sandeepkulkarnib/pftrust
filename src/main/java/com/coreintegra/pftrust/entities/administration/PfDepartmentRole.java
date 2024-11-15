package com.coreintegra.pftrust.entities.administration;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PfDepartmentRole extends BaseAuditingEntity {

    private String name;

    private String path;

    @ManyToOne
    private PfDepartment pfDepartment;

    @ManyToMany(mappedBy = "pfDepartmentRoles")
    private List<PfDepartmentRoleGroup> pfDepartmentRoleGroups;

    @OneToOne
    private Role role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PfDepartment getPfDepartment() {
        return pfDepartment;
    }

    public void setPfDepartment(PfDepartment pfDepartment) {
        this.pfDepartment = pfDepartment;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<PfDepartmentRoleGroup> getPfDepartmentRoleGroups() {
        return pfDepartmentRoleGroups;
    }

    public void setPfDepartmentRoleGroups(List<PfDepartmentRoleGroup> pfDepartmentRoleGroups) {
        this.pfDepartmentRoleGroups = pfDepartmentRoleGroups;
    }
}
