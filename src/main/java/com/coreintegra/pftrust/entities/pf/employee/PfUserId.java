package com.coreintegra.pftrust.entities.pf.employee;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PfUserId implements Serializable {

    private static final long serialVersionUID = -1525220286561488084L;
    private String personnelNumber;
    private Integer fiscalYear;
    private String subtype;

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}
