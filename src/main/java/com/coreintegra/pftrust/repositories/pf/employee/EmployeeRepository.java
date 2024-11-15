package com.coreintegra.pftrust.repositories.pf.employee;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.projections.EmployeeCountByStatus;
import com.coreintegra.pftrust.projections.EmployeeHiringCountByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByPernNumberOrPfNumberAndIsActive(String pernNumber, String pfNumber, Boolean active);

    Optional<Employee> findByPernNumberAndIsActive(String pernNumber, Boolean isActive);

    Optional<Employee> findByPfNumberAndIsActive(String pfNumber, Boolean isActive);

    Set<Employee> findAllByPernNumberIn(Set<String> pernNumbers);

    Set<Employee> findAllByPfNumberIn(Set<String> pfNumbers);

    Set<Employee> findAllByTokenNumberIn(Set<String> tokenNumbers);

    List<Employee> findAllByUnitCodeAndIsActive(String unitCode, Boolean isActive);

    @Query(value = "select count(e) as count, cs.description as name from Employee e " +
            "inner join ContributionStatus cs " +
            "on e.contributionStatus = cs and cs.isActive = true where e.tenant = :tenant and e.isActive = true " +
            "group by cs")
    List<EmployeeCountByStatus> getEmployeeCountByStatus(@Param("tenant") Tenant tenant);


    @Query(value = "select count(e.id) count, month(e.date_of_joiningpf) month, year(e.date_of_joiningpf) year from employee e\n " +
            " where e.tenant_id = :tenantId " +
            "group by year(e.date_of_joiningpf), month(e.date_of_joiningpf)\n" +
            "order by year(e.date_of_joiningpf) desc, month(e.date_of_joiningpf) desc limit 12", nativeQuery = true)
    List<EmployeeHiringCountByMonth> getEmployeeHiringCountByMonth(@Param("tenantId")Long tenantId);


    Optional<Employee> findByEntityIdAndIsActive(String entityId, Boolean isActive);

}
