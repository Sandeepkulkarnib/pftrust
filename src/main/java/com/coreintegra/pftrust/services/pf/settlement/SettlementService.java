package com.coreintegra.pftrust.services.pf.settlement;

import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.settlement.SettlementAnnexure;
import com.coreintegra.pftrust.dtos.pdf.settlement.SettlementDispatchLetter;
import com.coreintegra.pftrust.dtos.pdf.settlement.Worksheet;
import com.coreintegra.pftrust.entities.base.EmailAttachment;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.Document;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.settlement.*;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementDetailsDTO;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutEmailStatus;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.util.Pair;
import org.json.JSONArray;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SettlementService {

    CompletableFuture<String> saveFetchedSettlement(Settlement settlement, String pernNumber,
                                                    SettlementFinalDetails settlementFinalDetails);

    Page<Settlement> getSettlements(String search, Integer size, Integer page);

    EssentialsDTO getEssentials();

    List<Settlement> getSettlements(Employee employee);

    List<SettlementDetailsDTO> getSettlementDetails(Employee employee);

    List<Settlement> getSettlements(List<String> entityIds);

    Settlement getSettlement(String entityId);

    Optional<Settlement> getCompletedSettlement(Employee employee);

    Settlement savePaymentDetails(Settlement settlement, Date paymentDate, String referenceNumber, Bank bank,
                                   PaymentMode paymentMode) throws JPAException;

    SettlementEmailStatus createEmailStatus(Settlement settlement);

    void updateEmailStatus(SettlementEmailStatus settlementEmailStatus);

    void sendConfirmationEmail(List<Pair<SettlementEmailStatus, List<EmailAttachment>>> settlementEmailStatusList);

    void sendConfirmationEmail(SettlementEmailStatus settlementEmailStatus, List<EmailAttachment> emailAttachments);

    void sendConfirmationEmail(String emailId, String employeeName, String accountNumber, String employeeBank,
                               Date paymentDate, BigDecimal netCredit, List<EmailAttachment> emailAttachments);

    void reject(String entityId);

    List<SettlementEmailStatus> getEmailStatusListNotSent();

    List<SettlementEmailStatus> getSettlementEmailStatusList(List<String> entityIds);

    List<Document> getDocumentsForSettlementType(String entityId);

    Settlement create(Settlement settlement, Employee employee, Bank bank) throws JPAException;

    Settlement update(Settlement settlement, Employee employee) throws JPAException;

    SettlementType getSettlementType(String entityId);

    List<SettlementDocument> getSettlementDocuments(JSONArray documents);

    void saveSettlementDocuments(List<SettlementDocument> settlementDocuments, Settlement settlement);

    void updateSettlementDocuments(JSONArray jsonArray);

    List<SettlementType> getSettlementTypes();

    Worksheet getWorkSheet(Settlement settlement) throws ParseException;

    SettlementAnnexure getSettlementAnnexure(Settlement settlement);

    SettlementDispatchLetter getDispatchLetter(Settlement settlement);

    List<Settlement> getSettlementsForBankSheet(Date startDate, Date endDate, String bankId);

    List<Settlement> updatePaymentDate(List<Settlement> settlements, Date paymentDate);

    List<String> getBankSheetLines(List<Settlement> settlements, Date paymentDate);
    
    void updateSetllementStatus(Settlement settlement);

}
