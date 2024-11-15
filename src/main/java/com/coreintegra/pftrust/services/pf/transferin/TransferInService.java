package com.coreintegra.pftrust.services.pf.transferin;

import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInEmailStatus;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInReminder;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.TransferInDetailsDTO;
import com.coreintegra.pftrust.projections.TransferInContributionDTO;
import com.coreintegra.pftrust.services.pf.transferin.dtos.SaveAnnexurekDTO;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TransferInService {

    CompletableFuture<String> saveFetchedTransferInAsync(TransferIn transferIn, String pernNumber,
                                                         TransferInFinalDetails transferInFinalDetails,
                                                         PreviousCompany previousCompany);

    TransferIn save(TransferIn transferIn);

    TransferIn save(TransferIn transferIn, Employee employee, PreviousCompany previousCompany);

    Page<TransferIn> getTransferIns(String search, Integer size, Integer page);

    EssentialsDTO getEssentials();

    List<TransferIn> getTransferIns(Employee employee);

    List<TransferIn> getTransferIns(Employee employee, Integer year);

    List<TransferInDetailsDTO> getTransferInDetails(Employee employee);

    TransferIn getTransferIn(String entityId);

    TransferInContributionDTO getTotalTransferInContribution(Employee employee);

    TransferInFinalDetails getTotalTransferInContribution(Employee employee, Integer year);

    List<TransferIn> getCompletedTransferIns(Employee employee, Integer year);

    void reject(String entityId);

    TransferIn saveAnnexueKDetails(TransferIn transferIn, SaveAnnexurekDTO annexurekDTO);

    TransferInReminder getTransferInReminder(String entityId);

    List<TransferInReminder> getAllReminders(TransferIn transferIn);

    TransferInReminder generateTransferInReminder(String entityId);

    TransferInEmailStatus createEmailStatus(TransferIn transferIn);

    void updateTransferInEmailStatus(TransferInEmailStatus transferInEmailStatus);

    void sendConfirmationEmail(TransferInEmailStatus transferInEmailStatus);

    void sendConfirmationEmail(List<TransferInEmailStatus> transferInEmailStatusList);

    List<TransferInEmailStatus> getEmailStatusListNotSent();

    List<TransferInEmailStatus> getTransferInEmailStatusList(List<String> entityIds);

}
