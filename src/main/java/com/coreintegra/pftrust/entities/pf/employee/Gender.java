package com.coreintegra.pftrust.entities.pf.employee;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.Entity;

@Entity
public class Gender extends BaseAuditingEntity {

    private String symbol;
    private String details;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
