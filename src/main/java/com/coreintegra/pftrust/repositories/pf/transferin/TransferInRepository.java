package com.coreintegra.pftrust.repositories.pf.transferin;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.TransferInEmailDTO;
import com.coreintegra.pftrust.projections.TransferInContributionDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransferInRepository extends JpaRepository<TransferIn, Long>,
        JpaSpecificationExecutor<TransferIn> {

    Optional<TransferIn> findByEntityIdAndIsActive(String entityId, Boolean isActive);

    @Query(value = "select sum(trf.memberContribution) as memberContribution, " +
            " sum(trf.companyContribution) as companyContribution, sum(trf.vpfContribution) as vpfContribution " +
            " from TransferIn trn inner join TransferInFinalDetails trf on trf.transferIn = trn " +
            "where trn.employee = :employee and trn.isActive = true and trf.isActive = true ")
    TransferInContributionDTO getTotalTransferInContribution(@Param("employee") Employee employee);

    @Query(value = "select sum(trf.memberContribution) as memberContribution, " +
            " sum(trf.companyContribution) as companyContribution, sum(trf.vpfContribution) as vpfContribution " +
            " from TransferIn trn inner join TransferInFinalDetails trf on trf.transferIn = trn " +
            "where trn.employee = :employee and trn.isActive = true and trf.year = :year and trf.isActive = true ")
    TransferInContributionDTO getTotalTransferInContribution(@Param("employee") Employee employee,
                                                             @Param("year") Integer year);

    @Query("select tr from TransferIn tr inner join TransferInEmailStatus em on em.transferIn = tr " +
            "where em.isSent = true and em.isActive = true ")
    List<TransferIn> getTransferInEmailNotSentList();

}
