package com.coreintegra.pftrust.entities.administration;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role extends BaseAuditingEntity {

    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> privileges;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
