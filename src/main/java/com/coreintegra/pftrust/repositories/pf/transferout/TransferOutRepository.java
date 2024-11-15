package com.coreintegra.pftrust.repositories.pf.transferout;

import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TransferOutRepository extends JpaRepository<TransferOut, Long>,
        JpaSpecificationExecutor<TransferOut> {

    Optional<TransferOut> getTransferOutByEmployeeAndTransferOutStatusAndIsActive(Employee employee,
                                                                                  TransferOutStatus transferOutStatus,
                                                                                  Boolean isActive);

    Optional<TransferOut> findByEntityIdAndIsActive(String entityId, Boolean isActive);


    List<TransferOut> findAllByCreatedAtBetweenAndBankAndPaymentModeAndTransferOutStatusAndIsActive(Date startDate,
                                                            Date endDate, Bank bank, PaymentMode paymentMode,
                                                            TransferOutStatus transferOutStatus, Boolean isActive);
    
    List<TransferOut> findAllByDueDateBetweenAndBankAndPaymentModeAndTransferOutStatusAndIsActive(Date startDate,
            Date endDate, Bank bank, PaymentMode paymentMode,
            TransferOutStatus transferOutStatus, Boolean isActive);

    List<TransferOut> findAllByEntityIdInAndIsActive(Set<String> entityIds, Boolean isActive);

}
