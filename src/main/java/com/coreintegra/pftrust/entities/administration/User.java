package com.coreintegra.pftrust.entities.administration;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Entity
public class User extends BaseAuditingEntity implements UserDetails {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String profileImage;

    private Boolean isDefaultPassword;

    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private List<UserTenantPfDepartmentRoleMapping> userTenantPfDepartmentRoleMappingList;

    @OneToMany(mappedBy = "user")
    private List<UserTenantMapping> userTenantMappingList;

    public User() {
    }

    public User(String firstName, String lastName, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;

        this.setIsActive(false);
        this.setEntityId(UUID.randomUUID().toString());

        this.isDefaultPassword = true;

        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = false;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Boolean getDefaultPassword() {
        return isDefaultPassword;
    }

    public void setDefaultPassword(Boolean defaultPassword) {
        isDefaultPassword = defaultPassword;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return new HashSet<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public List<UserTenantPfDepartmentRoleMapping> getUserTenantPfDepartmentRoleMappingList() {
        return userTenantPfDepartmentRoleMappingList;
    }

    public void setUserTenantPfDepartmentRoleMappingList(List<UserTenantPfDepartmentRoleMapping> userTenantPfDepartmentRoleMappingList) {
        this.userTenantPfDepartmentRoleMappingList = userTenantPfDepartmentRoleMappingList;
    }

    public List<UserTenantMapping> getUserTenantMappingList() {
        return userTenantMappingList;
    }

    public void setUserTenantMappingList(List<UserTenantMapping> userTenantMappingList) {
        this.userTenantMappingList = userTenantMappingList;
    }
}
