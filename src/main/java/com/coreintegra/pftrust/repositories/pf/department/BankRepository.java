package com.coreintegra.pftrust.repositories.pf.department;

import com.coreintegra.pftrust.entities.pf.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {

    List<Bank> findAllByIsActive(Boolean isActive);

    Optional<Bank> findByEntityIdAndIsActive(String entityId, Boolean isActive);
    
    Bank findByNameAndIsActive(String name, Boolean isActive);

}
