package com.coreintegra.pftrust.processors;

import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.ContributionDTO;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;

import java.math.BigDecimal;
import java.text.ParseException;

public class ContributionProcessor {

    public Contribution process(final ContributionDTO contributionDTO) throws Exception {
        return mapContribution(contributionDTO);
    }

    private Contribution mapContribution(ContributionDTO contributionDTO) throws ParseException {

        Contribution contribution = new Contribution();

        contribution.setUnitCode(contributionDTO.getUnitCode());

        contribution.setPfNumber(contributionDTO.getPfNo() == null ? null : contributionDTO.getPfNo().trim());
        contribution.setPernNumber(contributionDTO.getPernr() == null ? null : contributionDTO.getPernr().trim());

        if(contributionDTO.getRecoveryDate() != null && !contributionDTO.getRecoveryDate().trim().isEmpty()){
            contribution.setRecoveryDate(FinancialYearAndMonth.getDate(contributionDTO.getRecoveryDate()));
        }

        if(contributionDTO.getPfBase() != null
                && !contributionDTO.getPfBase().trim().isEmpty()){

            contribution.setPfBase(new BigDecimal(contributionDTO.getPfBase().trim()));

        }else {
            contribution.setPfBase(new BigDecimal(0));
        }

        contribution.setSubType(Integer.valueOf(contributionDTO.getMonat() == null ? "0" : contributionDTO.getMonat().trim()));
        contribution.setYear(Integer.valueOf(contributionDTO.getFyear() == null ? "0" : contributionDTO.getFyear().trim()));

        contribution.setCompanyContribution(new BigDecimal(contributionDTO.getComCont() == null ? "0" : contributionDTO.getComCont().trim()));
        contribution.setMemberContribution(new BigDecimal(contributionDTO.getMemCont() == null ? "0" : contributionDTO.getMemCont().trim() ));
        contribution.setVpfContribution(new BigDecimal(contributionDTO.getEpfCont() == null ? "0" : contributionDTO.getEpfCont().trim()));

        contribution.setInterestCompanyContribution(new BigDecimal(contributionDTO.getComInt() == null ? "0" : contributionDTO.getComInt().trim()));
        contribution.setInterestMemContribution(new BigDecimal(contributionDTO.getMemInt() == null ? "0" : contributionDTO.getMemInt().trim()));
        contribution.setVpfContributionInterest(new BigDecimal(contributionDTO.getEpfInt() == null ? "0" : contributionDTO.getEpfInt().trim()));

        if((contribution.getInterestMemContribution() != null &&
                contribution.getInterestMemContribution().floatValue() > 0) ||
                (contribution.getInterestCompanyContribution() != null &&
                        contribution.getInterestCompanyContribution().floatValue() > 0) ||
                (contribution.getVpfContributionInterest() != null &&
                        contribution.getVpfContributionInterest().floatValue() > 0)){

            SAPStatus sapStatus = new SAPStatus();
            sapStatus.setId(3L);

            contribution.setSapStatus(sapStatus);

        }else {

            SAPStatus sapStatus = new SAPStatus();
            sapStatus.setId(1L);

            contribution.setSapStatus(sapStatus);

        }

        return contribution;

    }


}
