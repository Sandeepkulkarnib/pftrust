package com.coreintegra.pftrust.entities.pf;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.Entity;

@Entity
public class FinancialYear extends BaseAuditingEntity {

    private Integer year;
    private Boolean isCurrent;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }
}
