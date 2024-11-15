package com.coreintegra.pftrust.entities.pf.employee.dtos;

public class BankDetailsDTO {

    private String name;
    private String accountNumber;
    private String ifscCode;
    private String micrCode;

    public BankDetailsDTO(String name, String accountNumber, String ifscCode, String micrCode) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.micrCode = micrCode;
    }

    public String getName() {
        return name == null ? "NA" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber == null ? "NA" : accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode == null ? "NA" : ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getMicrCode() {
        return micrCode == null ? "NA" : micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }
}
