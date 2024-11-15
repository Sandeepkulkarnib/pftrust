package com.coreintegra.pftrust.services.pf.transferout;

import com.coreintegra.pftrust.dtos.EssentialsDTO;
import com.coreintegra.pftrust.dtos.pdf.settlement.Worksheet;
import com.coreintegra.pftrust.dtos.pdf.transferout.AnnuxureK;
import com.coreintegra.pftrust.dtos.pdf.transferout.TransferOutDispatchLetter;
import com.coreintegra.pftrust.entities.base.EmailAttachment;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.Document;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementDocument;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementDetailsDTO;
import com.coreintegra.pftrust.entities.pf.transferOut.*;
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

public interface TransferOutService {

    CompletableFuture<String> saveFetchedTransferOut(TransferOut transferOut, String pernNumber,
                                                     TransferOutFinalDetails transferOutFinalDetails);

    Page<TransferOut> getTransferOuts(String search, Integer size, Integer page);

    EssentialsDTO getEssentials();

    List<TransferOut> getTransferOuts(Employee employee);

    List<TransferOut> getTransferOuts(List<String> entityIds);

    List<SettlementDetailsDTO> getTransferOutDetails(Employee employee);

    TransferOut getTransferOut(String entityId);

    Optional<TransferOut> getCompletedTransferOut(Employee employee);

    TransferOut savePaymentDetails(TransferOut transferOut, Date paymentDate, String referenceNumber, Bank bank,
                            PaymentMode paymentMode) throws JPAException;

    TransferOutEmailStatus createEmailStatus(TransferOut transferOut);

    void updateEmailStatus(TransferOutEmailStatus transferOutEmailStatus);

    void sendConfirmationEmail(List<Pair<TransferOutEmailStatus, List<EmailAttachment>>> transferOutEmailStatusList);

    void sendConfirmationEmail(TransferOutEmailStatus transferOutEmailStatus, List<EmailAttachment> emailAttachments);

    void sendConfirmationEmail(String emailId, String employeeName, String fromPfAccount, String toPfAccount, BigDecimal netCredit,
                               List<EmailAttachment> emailAttachments);

    void reject(String entityId);

    List<TransferOutEmailStatus> getEmailStatusListNotSent();

    List<TransferOutEmailStatus> getTransferOutEmailStatusList(List<String> entityIds);

    List<Document> getDocumentsForTransferOutType(String entityId);

    List<TransferOutType> getTransferOutTypes();

    TransferOutType getTransferOutType(String entityId);

    List<TransferOutDocument> getTransferOutDocuments(JSONArray documents);

    TransferOut create(TransferOut transferOut, Employee employee, PaymentMode paymentMode) throws JPAException;

    TransferOut update(TransferOut transferOut, Employee employee, PaymentMode paymentMode) throws JPAException;

    void saveTransferOutDocuments(List<TransferOutDocument> transferOutDocuments, TransferOut transferOut);

    void updateSettlementDocuments(JSONArray jsonArray);

    AnnuxureK getAnnexureK(TransferOut transferOut) throws ParseException;

    TransferOutDispatchLetter getDispatchLetter(TransferOut transferOut);

    Worksheet getWorkSheet(TransferOut transferOut) throws ParseException;

    List<TransferOut> getTransferOutsForBankSheet(Date startDate, Date endDate, String bankId);

    List<TransferOut> updatePaymentDate(List<TransferOut> transferOuts, Date paymentDate);

    List<String> getBankSheetLines(List<TransferOut> transferOuts, Date paymentDate);
    
    void updateTransferOutStatus(TransferOut transferOut);
}
