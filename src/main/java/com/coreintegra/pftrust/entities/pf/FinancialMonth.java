package com.coreintegra.pftrust.entities.pf;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.Entity;

@Entity
public class FinancialMonth extends BaseAuditingEntity {

    private Integer financialMonth;
    private Integer calenderMonth;
    private String label;
    private Boolean isCurrent;

    public Integer getFinancialMonth() {
        return financialMonth;
    }

    public void setFinancialMonth(Integer financialMonth) {
        this.financialMonth = financialMonth;
    }

    public Integer getCalenderMonth() {
        return calenderMonth;
    }

    public void setCalenderMonth(Integer calenderMonth) {
        this.calenderMonth = calenderMonth;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }
}
