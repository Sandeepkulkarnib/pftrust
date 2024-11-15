package com.coreintegra.pftrust.entities.pf.transferIn.dtos;

import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PreviousCompanyDetailsDTO {

    private String name;

    private Date dateOfJoining;
    private Date dateOfLeaving;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String pinCode;

    public PreviousCompanyDetailsDTO(String name, Date dateOfJoining, Date dateOfLeaving, String addressLine1,
                                     String addressLine2, String addressLine3, String addressLine4,
                                     String pinCode) {
        this.name = name;
        this.dateOfJoining = dateOfJoining;
        this.dateOfLeaving = dateOfLeaving;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.pinCode = pinCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(Date dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public static PreviousCompanyDetailsDTO build(TransferIn transferIn) {

        PreviousCompany previousCompany = transferIn.getPreviousCompany();

        return new PreviousCompanyDetailsDTO(previousCompany.getName(), previousCompany.getDateOfJoining(),
                previousCompany.getDateOfLeaving(), previousCompany.getAddressLine1(),
                previousCompany.getAddressLine2(), previousCompany.getAddressLine3(),
                previousCompany.getAddressLine4(), previousCompany.getPinCode());

    }

}
