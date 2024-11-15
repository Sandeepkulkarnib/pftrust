package com.coreintegra.pftrust.entities.pf.contribution.dtos;

import java.math.BigDecimal;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

public class TotalContributionDTO {

    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal vpfContribution;

    private BigDecimal nonTaxableMemberContribution = BigDecimal.ZERO;
    private BigDecimal taxableMemberContribution = BigDecimal.ZERO;

    private BigDecimal nontaxableVpfContribution = BigDecimal.ZERO;
    private BigDecimal taxableVpfContribution = BigDecimal.ZERO;

    public TotalContributionDTO(BigDecimal memberContribution, BigDecimal companyContribution,
                                BigDecimal vpfContribution) {
        this.memberContribution = memberContribution;
        this.companyContribution = companyContribution;
        this.vpfContribution = vpfContribution;
    }

    public BigDecimal getMemberContribution() {
        return avoidNull(memberContribution);
    }

    public void setMemberContribution(BigDecimal memberContribution) {
        this.memberContribution = memberContribution;
    }

    public BigDecimal getCompanyContribution() {
        return avoidNull(companyContribution);
    }

    public void setCompanyContribution(BigDecimal companyContribution) {
        this.companyContribution = companyContribution;
    }

    public BigDecimal getVpfContribution() {
        return avoidNull(vpfContribution);
    }

    public void setVpfContribution(BigDecimal vpfContribution) {
        this.vpfContribution = vpfContribution;
    }

    public BigDecimal getNonTaxableMemberContribution() {
        return nonTaxableMemberContribution;
    }

    public void setNonTaxableMemberContribution(BigDecimal nonTaxableMemberContribution) {
        this.nonTaxableMemberContribution = nonTaxableMemberContribution;
    }

    public BigDecimal getTaxableMemberContribution() {
        return taxableMemberContribution;
    }

    public void setTaxableMemberContribution(BigDecimal taxableMemberContribution) {
        this.taxableMemberContribution = taxableMemberContribution;
    }

    public BigDecimal getNontaxableVpfContribution() {
        return nontaxableVpfContribution;
    }

    public void setNontaxableVpfContribution(BigDecimal nontaxableVpfContribution) {
        this.nontaxableVpfContribution = nontaxableVpfContribution;
    }

    public BigDecimal getTaxableVpfContribution() {
        return taxableVpfContribution;
    }

    public void setTaxableVpfContribution(BigDecimal taxableVpfContribution) {
        this.taxableVpfContribution = taxableVpfContribution;
    }

    public BigDecimal total(){
        return getMemberContribution().add(getCompanyContribution()).add(getVpfContribution());
    }

}
