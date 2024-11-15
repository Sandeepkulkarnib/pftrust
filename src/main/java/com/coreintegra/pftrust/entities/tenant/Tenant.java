package com.coreintegra.pftrust.entities.tenant;

import com.coreintegra.pftrust.entities.administration.UserTenantMapping;
import com.coreintegra.pftrust.entities.administration.UserTenantPfDepartmentRoleMapping;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Tenant extends BaseAuditingEntity {

    private String tenantId;
    private String name;
    private String code;
    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String theme;

    private String clock;

    @OneToMany(mappedBy = "tenant")
    private List<UserTenantPfDepartmentRoleMapping> userTenantPfDepartmentRoleMappingList;

    @OneToMany(mappedBy = "tenant")
    private List<UserTenantMapping> userTenantMappingList;

    @Column(columnDefinition = "LONGTEXT")
    private String logoImage;
    private String height;
    private String width;
    private String styleClasses;

    @OneToMany(mappedBy = "tenant")
    private List<TenantImage> tenantImages;

    public Tenant() {
    }

    public Tenant(String tenantId, String name) {
        this.tenantId = tenantId;
        this.name = name;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getStyleClasses() {
        return styleClasses;
    }

    public void setStyleClasses(String styleClasses) {
        this.styleClasses = styleClasses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TenantImage> getTenantImages() {
        return tenantImages;
    }

    public void setTenantImages(List<TenantImage> tenantImages) {
        this.tenantImages = tenantImages;
    }

}
