package com.coreintegra.pftrust.repositories.pf.loan;

import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanType;
import org.apache.xpath.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanTypeRepository extends JpaRepository<LoanType, Long> {

    Optional<LoanType> findByCode(String code);

    List<LoanType> findAllByIsActive(Boolean isActive);

    Optional<LoanType> findByEntityIdAndIsActive(String entityId, Boolean isActive);

    LoanType findByCodeAndIsActive(String code, Boolean isActive);
}
