package com.coreintegra.pftrust.entities.pf.loan.dtos;

import com.coreintegra.pftrust.entities.pf.loan.Loan;

public class LoanFullDetailsDTO {

    private LoanBasicDetails loanBasicDetails;
    private LoanTypeDetails loanTypeDetails;
    private LoanSanctionDetails loanSanctionDetails;

    public LoanFullDetailsDTO(LoanBasicDetails loanBasicDetails, LoanTypeDetails loanTypeDetails,
                              LoanSanctionDetails loanSanctionDetails) {
        this.loanBasicDetails = loanBasicDetails;
        this.loanTypeDetails = loanTypeDetails;
        this.loanSanctionDetails = loanSanctionDetails;
    }

    public LoanBasicDetails getLoanBasicDetails() {
        return loanBasicDetails;
    }

    public void setLoanBasicDetails(LoanBasicDetails loanBasicDetails) {
        this.loanBasicDetails = loanBasicDetails;
    }

    public LoanTypeDetails getLoanTypeDetails() {
        return loanTypeDetails;
    }

    public void setLoanTypeDetails(LoanTypeDetails loanTypeDetails) {
        this.loanTypeDetails = loanTypeDetails;
    }

    public LoanSanctionDetails getLoanSanctionDetails() {
        return loanSanctionDetails;
    }

    public void setLoanSanctionDetails(LoanSanctionDetails loanSanctionDetails) {
        this.loanSanctionDetails = loanSanctionDetails;
    }

    public static LoanFullDetailsDTO build(Loan loan){

        LoanBasicDetails basicDetails = LoanBasicDetails.build(loan);
        LoanTypeDetails typeDetails = LoanTypeDetails.build(loan);
        LoanSanctionDetails sanctionDetails = LoanSanctionDetails.build(loan);

        return new LoanFullDetailsDTO(basicDetails, typeDetails, sanctionDetails);
    }


}
