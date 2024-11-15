package com.coreintegra.pftrust.dtos.pdf.loan;

import java.math.BigDecimal;

public class DistributedLoanAmountDTO {

    private BigDecimal loanAmountOnOpeningBalanceNonTaxable;
    private BigDecimal loanAmountOnOpeningBalanceTaxable;
    private BigDecimal loanAmountOnContributionNonTaxable;
    private BigDecimal loanAmountOnContributionTaxable;

    public DistributedLoanAmountDTO(BigDecimal loanAmountOnOpeningBalanceNonTaxable,
                                    BigDecimal loanAmountOnOpeningBalanceTaxable,
                                    BigDecimal loanAmountOnContributionNonTaxable,
                                    BigDecimal loanAmountOnContributionTaxable) {
        this.loanAmountOnOpeningBalanceNonTaxable = loanAmountOnOpeningBalanceNonTaxable;
        this.loanAmountOnOpeningBalanceTaxable = loanAmountOnOpeningBalanceTaxable;
        this.loanAmountOnContributionNonTaxable = loanAmountOnContributionNonTaxable;
        this.loanAmountOnContributionTaxable = loanAmountOnContributionTaxable;
    }

    public BigDecimal getLoanAmountOnOpeningBalanceNonTaxable() {
        return loanAmountOnOpeningBalanceNonTaxable;
    }

    public void setLoanAmountOnOpeningBalanceNonTaxable(BigDecimal loanAmountOnOpeningBalanceNonTaxable) {
        this.loanAmountOnOpeningBalanceNonTaxable = loanAmountOnOpeningBalanceNonTaxable;
    }

    public BigDecimal getLoanAmountOnOpeningBalanceTaxable() {
        return loanAmountOnOpeningBalanceTaxable;
    }

    public void setLoanAmountOnOpeningBalanceTaxable(BigDecimal loanAmountOnOpeningBalanceTaxable) {
        this.loanAmountOnOpeningBalanceTaxable = loanAmountOnOpeningBalanceTaxable;
    }

    public BigDecimal getLoanAmountOnContributionNonTaxable() {
        return loanAmountOnContributionNonTaxable;
    }

    public void setLoanAmountOnContributionNonTaxable(BigDecimal loanAmountOnContributionNonTaxable) {
        this.loanAmountOnContributionNonTaxable = loanAmountOnContributionNonTaxable;
    }

    public BigDecimal getLoanAmountOnContributionTaxable() {
        return loanAmountOnContributionTaxable;
    }

    public void setLoanAmountOnContributionTaxable(BigDecimal loanAmountOnContributionTaxable) {
        this.loanAmountOnContributionTaxable = loanAmountOnContributionTaxable;
    }
}
