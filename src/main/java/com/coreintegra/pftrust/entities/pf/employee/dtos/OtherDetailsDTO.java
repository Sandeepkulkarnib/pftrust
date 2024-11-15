package com.coreintegra.pftrust.entities.pf.employee.dtos;

import com.coreintegra.pftrust.dtos.pdf.Nominee;

import java.util.List;

public class OtherDetailsDTO {

    private List<Nominee> nominees;
    private List<BankDetailsDTO> bankDetailsDTOS;
    private List<AddressDetailsDTO> addressDetailsDTOS;

    public OtherDetailsDTO(List<Nominee> nominees, List<BankDetailsDTO> bankDetailsDTOS,
                           List<AddressDetailsDTO> addressDetailsDTOS) {
        this.nominees = nominees;
        this.bankDetailsDTOS = bankDetailsDTOS;
        this.addressDetailsDTOS = addressDetailsDTOS;
    }

    public List<Nominee> getNominees() {
        return nominees;
    }

    public void setNominees(List<Nominee> nominees) {
        this.nominees = nominees;
    }

    public List<BankDetailsDTO> getBankDetailsDTOS() {
        return bankDetailsDTOS;
    }

    public void setBankDetailsDTOS(List<BankDetailsDTO> bankDetailsDTOS) {
        this.bankDetailsDTOS = bankDetailsDTOS;
    }

    public List<AddressDetailsDTO> getAddressDetailsDTOS() {
        return addressDetailsDTOS;
    }

    public void setAddressDetailsDTOS(List<AddressDetailsDTO> addressDetailsDTOS) {
        this.addressDetailsDTOS = addressDetailsDTOS;
    }
}
