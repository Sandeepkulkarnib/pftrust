package com.coreintegra.pftrust.dtos.pdf;

public class SearchPfUserDTO {

    private String pernNumber;
    private String pfNumber;
    private String tokenNumber;
    private String unitCode;
    private String name;

    public SearchPfUserDTO(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public SearchPfUserDTO(String pernNumber, String pfNumber, String tokenNumber,
                           String unitCode, String name) {
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.tokenNumber = tokenNumber;
        this.unitCode = unitCode;
        this.name = name;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
