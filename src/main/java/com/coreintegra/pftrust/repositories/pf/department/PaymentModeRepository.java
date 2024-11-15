package com.coreintegra.pftrust.repositories.pf.department;

import com.coreintegra.pftrust.entities.pf.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long> {

    List<PaymentMode> findAllByIsActive(Boolean isActive);

    Optional<PaymentMode> findByEntityIdAndIsActive(String entityId, Boolean isActive);

    Optional<PaymentMode> findByModeAndIsActive(String mode, Boolean isActive);
    
    PaymentMode findByIdAndIsActive(Long id, Boolean isActive);

}
