package com.coreintegra.pftrust.entities.pf.transferOut.dtos;

public class TransferOutFullDetailsDTO {

    private TransferOutBasicDetailsDTO transferOutBasicDetails;
    private TransferOutPaymentDetailsDTO transferOutPaymentDetails;

    public TransferOutFullDetailsDTO(TransferOutBasicDetailsDTO transferOutBasicDetails,
                                     TransferOutPaymentDetailsDTO transferOutPaymentDetails) {
        this.transferOutBasicDetails = transferOutBasicDetails;
        this.transferOutPaymentDetails = transferOutPaymentDetails;
    }

    public TransferOutBasicDetailsDTO getTransferOutBasicDetails() {
        return transferOutBasicDetails;
    }

    public void setTransferOutBasicDetails(TransferOutBasicDetailsDTO transferOutBasicDetails) {
        this.transferOutBasicDetails = transferOutBasicDetails;
    }

    public TransferOutPaymentDetailsDTO getTransferOutPaymentDetails() {
        return transferOutPaymentDetails;
    }

    public void setTransferOutPaymentDetails(TransferOutPaymentDetailsDTO transferOutPaymentDetails) {
        this.transferOutPaymentDetails = transferOutPaymentDetails;
    }
}
