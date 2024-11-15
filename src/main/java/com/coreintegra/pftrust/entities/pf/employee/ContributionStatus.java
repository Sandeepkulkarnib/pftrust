package com.coreintegra.pftrust.entities.pf.employee;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;

import javax.persistence.Entity;

@Entity
public class ContributionStatus extends BaseAuditingEntity {

    public static String ACTIVE = "A";
    public static String IN_ACTIVE = "I";
    public static String SETTLED = "S";
    public static String MERGED = "M";
    public static String IN_OPERABLE = "IO";
    public static String F = "F";
    public static String C = "C";
    public static String NO_STATUS = "NA";

    private String description;
    private String symbol;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
