package com.coreintegra.pftrust.entities.pf.employee;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.Entity;

@Entity
public class Department extends BaseAuditingEntity {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
