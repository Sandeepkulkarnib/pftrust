package com.coreintegra.pftrust.repositories.pf.contribution;

import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.projections.ContributionByMonth;
import com.coreintegra.pftrust.projections.TotalContributionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ContributionRepository extends JpaRepository<Contribution, Long>,
        JpaSpecificationExecutor<Contribution> {

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query(value = "update contribution set employee_id = :employee_id where pern_number = :pern_number", nativeQuery = true)
    void updateEmployee(@Param("employee_id")Long employee_id,
                        @Param("pern_number")String pern_number);

    Optional<Contribution> findTop1ByEmployeeAndSubTypeGreaterThanAndIsActiveOrderByYearDescSubTypeDesc(
            Employee employee, Integer subType, Boolean isActive);

    @Query(value = "select sum(c.member_contribution + c.company_contribution + c.vpf_contribution) contribution," +
            " c.year as year, c.sub_type as month from contribution c where c.tenant_id = :tenantId \n" +
            " group by c.year, c.sub_type order by c.year desc, c.sub_type desc limit 12", nativeQuery = true)
    List<ContributionByMonth> getContributionByMonth(@Param("tenantId") Long tenantId);


    Optional<Contribution> findTop1ByEmployeeAndYearAndSubTypeAndIsActive(Employee employee,
                                                                          Integer year,
                                                                          Integer subType,
                                                                          Boolean isActive);

    List<Contribution> findAllByEmployeeAndYearAndIsActiveOrderBySubTypeAsc(Employee employee, Integer year, Boolean isActive);

    Optional<Contribution> findTop1ByEmployeeAndYearAndIsActiveOrderBySubTypeDesc(Employee employee,
                                                                                  Integer year,
                                                                                  Boolean isActive);

    List<Contribution> findAllByEmployeeAndYearAndIsActiveOrderBySubTypeDesc(Employee employee,
                                                                             Integer year,
                                                                             Boolean isActive);

    List<Contribution> findAllByEmployeeAndIsActive(Employee employee, Boolean isActive);


    Optional<Contribution> findByEmployeeAndYearAndSubTypeAndIsActive(Employee employee,
                                                                         Integer year,
                                                                         Integer month,
                                                                         Boolean isActive);

    Optional<Contribution> findTop1ByEmployeeAndIsActiveOrderByYearDescSubTypeDesc(Employee employee, Boolean isActive);

    @Query(value = "select coalesce(sum(contribution.memberContribution), 0) as memberContribution, " +
            "coalesce(sum (contribution.companyContribution), 0) as companyContribution, " +
            "coalesce(sum (contribution.vpfContribution), 0) as vpfContribution " +
            "from Contribution contribution where contribution.employee = :employee " +
            "and contribution.isActive = :isActive")
    TotalContributionDTO getTotalContribution(@Param("employee") Employee employee,
                                              @Param("isActive") Boolean isActive);

    @Query(value = "select coalesce(sum(contribution.memberContribution), 0) as memberContribution, " +
            "coalesce(sum (contribution.companyContribution), 0) as companyContribution, " +
            "coalesce(sum (contribution.vpfContribution), 0) as vpfContribution " +
            "from Contribution contribution where contribution.employee = :employee " +
            "and contribution.year = :year and contribution.isActive = :isActive")
    TotalContributionDTO getTotalContribution(@Param("employee") Employee employee,
                                              @Param("year") Integer year,
                                              @Param("isActive") Boolean isActive);

    List<Contribution> findAllByEmployeeAndYearAndSubTypeGreaterThanAndIsActive(Employee employee, Integer year,
                                                                                Integer month, Boolean isActive);

    List<Contribution> findAllByPernNumberAndYearAndSubTypeGreaterThanAndIsActive(String pernNumber, Integer year,
                                                                                Integer month, Boolean isActive);

}
