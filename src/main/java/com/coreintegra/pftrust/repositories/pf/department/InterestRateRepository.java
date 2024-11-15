package com.coreintegra.pftrust.repositories.pf.department;

import com.coreintegra.pftrust.entities.pf.department.InterestRate;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRateRepository extends JpaRepository<InterestRate, Long> {

    Optional<InterestRate> findByTenantAndYearAndIsActive(Tenant tenant, Integer year, Boolean isActive);

    Optional<InterestRate> findByYearAndIsActive(Integer year, Boolean isActive);

}
