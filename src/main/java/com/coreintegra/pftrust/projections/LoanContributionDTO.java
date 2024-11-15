package com.coreintegra.pftrust.projections;

import java.math.BigDecimal;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

public interface LoanContributionDTO {

    BigDecimal getLoanAmountOnMemberContribution();
    BigDecimal getLoanAmountOnCompanyContribution();
    BigDecimal getLoanAmountOnVpfContribution();

    default BigDecimal getTotalLoanAmount() {
        return BigDecimal.ZERO.add(avoidNull(getLoanAmountOnMemberContribution()))
                .add(avoidNull(getLoanAmountOnCompanyContribution()))
                .add(avoidNull(getLoanAmountOnVpfContribution()));
    }

}
