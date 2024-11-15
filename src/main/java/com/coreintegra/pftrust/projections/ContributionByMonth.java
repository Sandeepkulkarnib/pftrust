package com.coreintegra.pftrust.projections;

import java.math.BigDecimal;

public interface ContributionByMonth {

    Integer getYear();
    Integer getMonth();
    BigDecimal getContribution();

}
