package com.coreintegra.pftrust.repositories.pf.settlement;

import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SettlementRepository extends JpaRepository<Settlement, Long>, JpaSpecificationExecutor<Settlement> {

    Optional<Settlement> getSettlementByEmployeeAndSettlementStatusAndIsActive(Employee employee,
                                                                               SettlementStatus settlementStatus,
                                                                               Boolean isActive);

    Optional<Settlement> findByEntityIdAndIsActive(String entityId, Boolean isActive);

    
    List<Settlement> findAllByCreatedAtBetweenAndBankAndPaymentModeAndSettlementStatusAndIsActive(Date startDate, Date endDate,
                                                                               Bank bank, PaymentMode paymentMode,
                                                                               SettlementStatus settlementStatus,
                                                                               Boolean isActive);
    
    List<Settlement> findAllByDateOfSettlementBetweenAndBankAndPaymentModeAndSettlementStatusAndIsActive(Date startDate, Date endDate,
            Bank bank, PaymentMode paymentMode,
            SettlementStatus settlementStatus,
            Boolean isActive);

    List<Settlement> findAllByEntityIdInAndIsActive(Set<String> entityIds, Boolean isActive);

}
