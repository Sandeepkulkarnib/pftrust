package com.coreintegra.pftrust.projections;

import java.math.BigDecimal;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

public interface TransferInContributionDTO {

    BigDecimal getMemberContribution();
    BigDecimal getCompanyContribution();
    BigDecimal getVpfContribution();

    default BigDecimal getTotalContribution() {
        return BigDecimal.ZERO.add(avoidNull(getMemberContribution()))
                .add(avoidNull(getCompanyContribution()))
                .add(avoidNull(getVpfContribution()));
    }

}
