package com.coreintegra.pftrust.entities.administration;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PfDepartmentRoleGroup extends BaseAuditingEntity {

    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pf_department_role_group_mapping",
            joinColumns = @JoinColumn(name = "pf_department_role_group_id"),
            inverseJoinColumns = @JoinColumn(name = "pf_department_role_id")
    )
    private List<PfDepartmentRole> pfDepartmentRoles;

    @OneToMany(mappedBy = "pfDepartmentRoleGroup")
    private List<UserTenantPfDepartmentRoleMapping> userTenantPfDepartmentRoleMappings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PfDepartmentRole> getPfDepartmentRoles() {
        return pfDepartmentRoles;
    }

    public void setPfDepartmentRoles(List<PfDepartmentRole> pfDepartmentRoles) {
        this.pfDepartmentRoles = pfDepartmentRoles;
    }

    public List<UserTenantPfDepartmentRoleMapping> getUserTenantPfDepartmentRoleMappings() {
        return userTenantPfDepartmentRoleMappings;
    }

    public void setUserTenantPfDepartmentRoleMappings(List<UserTenantPfDepartmentRoleMapping> userTenantPfDepartmentRoleMappings) {
        this.userTenantPfDepartmentRoleMappings = userTenantPfDepartmentRoleMappings;
    }
}
