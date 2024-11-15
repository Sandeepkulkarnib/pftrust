package com.coreintegra.pftrust.entities.pf.employee.dtos;

public class AddressDetailsDTO {

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    public AddressDetailsDTO(String addressLine1, String addressLine2, String addressLine3, String addressLine4) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
    }

    public String getAddressLine1() {
        return addressLine1 == null ? "NA" : addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2 == null ? "NA" : addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3 == null ? "NA" : addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4 == null ? "NA" : addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }
}
