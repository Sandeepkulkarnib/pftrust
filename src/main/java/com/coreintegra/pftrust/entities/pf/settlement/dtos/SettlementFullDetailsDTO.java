package com.coreintegra.pftrust.entities.pf.settlement.dtos;

public class SettlementFullDetailsDTO {

    private SettlementBasicDetailsDTO settlementBasicDetails;
    private SettlementPaymentDetailsDTO settlementPaymentDetails;

    public SettlementFullDetailsDTO(SettlementBasicDetailsDTO settlementBasicDetails,
                                    SettlementPaymentDetailsDTO settlementPaymentDetails) {
        this.settlementBasicDetails = settlementBasicDetails;
        this.settlementPaymentDetails = settlementPaymentDetails;
    }

    public SettlementBasicDetailsDTO getSettlementBasicDetails() {
        return settlementBasicDetails;
    }

    public void setSettlementBasicDetails(SettlementBasicDetailsDTO settlementBasicDetails) {
        this.settlementBasicDetails = settlementBasicDetails;
    }

    public SettlementPaymentDetailsDTO getSettlementPaymentDetails() {
        return settlementPaymentDetails;
    }

    public void setSettlementPaymentDetails(SettlementPaymentDetailsDTO settlementPaymentDetails) {
        this.settlementPaymentDetails = settlementPaymentDetails;
    }
}
