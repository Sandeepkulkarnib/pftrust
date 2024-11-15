package com.coreintegra.pftrust.entities.pf.transferIn.dtos;

public class TransferInFullDetailsDTO {

    private TransferInBasicDetailsDTO transferInBasicDetails;
    private TransferInContributionDetailsDTO transferInContributionDetails;
    private PreviousCompanyDetailsDTO previousCompanyDetails;

    public TransferInFullDetailsDTO(TransferInBasicDetailsDTO transferInBasicDetails,
                                    TransferInContributionDetailsDTO transferInContributionDetails,
                                    PreviousCompanyDetailsDTO previousCompanyDetails) {
        this.transferInBasicDetails = transferInBasicDetails;
        this.transferInContributionDetails = transferInContributionDetails;
        this.previousCompanyDetails = previousCompanyDetails;
    }

    public TransferInBasicDetailsDTO getTransferInBasicDetails() {
        return transferInBasicDetails;
    }

    public void setTransferInBasicDetails(TransferInBasicDetailsDTO transferInBasicDetails) {
        this.transferInBasicDetails = transferInBasicDetails;
    }

    public TransferInContributionDetailsDTO getTransferInContributionDetails() {
        return transferInContributionDetails;
    }

    public void setTransferInContributionDetails(TransferInContributionDetailsDTO transferInContributionDetails) {
        this.transferInContributionDetails = transferInContributionDetails;
    }

    public PreviousCompanyDetailsDTO getPreviousCompanyDetails() {
        return previousCompanyDetails;
    }

    public void setPreviousCompanyDetails(PreviousCompanyDetailsDTO previousCompanyDetails) {
        this.previousCompanyDetails = previousCompanyDetails;
    }
}
