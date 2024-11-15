package com.coreintegra.pftrust.entities.pf.transferOut.dtos;

import com.coreintegra.pftrust.dtos.DocumentDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutStatus;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransferOutBasicDetailsDTO {

    private String entityId;

    private String pernNumber;
    private String pfNumber;
    private String tokenNumber;
    private String unitCode;

    private String name;
    private String status;
    private Date dateOfJoiningService;
    private Date dateOfJoiningPf;

    private String transferOutType;
    private String transferOutStatus;

    private String documentNumber;
    private float interestRate;

    private Date dateOfLeavingService;
    private Date dueDate;
    private String payeeName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String fromPfNumber;
    private String fromEpsNumber;
    private String fromUanNumber;

    private String toPfNumber;
    private String toEpsNumber;
    private String toUanNumber;

    private String currentEmployerName;
    private String currentEmployerAddressLine1;
    private String currentEmployerAddressLine2;
    private String currentEmployerAddressLine3;
    private String currentEmployerAddressLine4;

    private String alternateContactNumber;
    private String alternateEmailId;

    private List<DocumentDTO> documentDTOList;

    public TransferOutBasicDetailsDTO(String entityId, String pernNumber, String pfNumber, String tokenNumber,
                                      String unitCode, String name, String status, Date dateOfJoiningService,
                                      Date dateOfJoiningPf, String transferOutType, String transferOutStatus,
                                      String documentNumber, float interestRate, Date dateOfLeavingService,
                                      Date dueDate, String payeeName, String addressLine1, String addressLine2,
                                      String addressLine3, String addressLine4, String fromPfNumber,
                                      String fromEpsNumber, String fromUanNumber, String toPfNumber,
                                      String toEpsNumber, String toUanNumber, String currentEmployerName,
                                      String currentEmployerAddressLine1, String currentEmployerAddressLine2,
                                      String currentEmployerAddressLine3, String currentEmployerAddressLine4,
                                      String alternateContactNumber, String alternateEmailId) {
        this.entityId = entityId;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.tokenNumber = tokenNumber;
        this.unitCode = unitCode;
        this.name = name;
        this.status = status;
        this.dateOfJoiningService = dateOfJoiningService;
        this.dateOfJoiningPf = dateOfJoiningPf;
        this.transferOutType = transferOutType;
        this.transferOutStatus = transferOutStatus;
        this.documentNumber = documentNumber;
        this.interestRate = interestRate;
        this.dateOfLeavingService = dateOfLeavingService;
        this.dueDate = dueDate;
        this.payeeName = payeeName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.fromPfNumber = fromPfNumber;
        this.fromEpsNumber = fromEpsNumber;
        this.fromUanNumber = fromUanNumber;
        this.toPfNumber = toPfNumber;
        this.toEpsNumber = toEpsNumber;
        this.toUanNumber = toUanNumber;
        this.currentEmployerName = currentEmployerName;
        this.currentEmployerAddressLine1 = currentEmployerAddressLine1;
        this.currentEmployerAddressLine2 = currentEmployerAddressLine2;
        this.currentEmployerAddressLine3 = currentEmployerAddressLine3;
        this.currentEmployerAddressLine4 = currentEmployerAddressLine4;
        this.alternateContactNumber = alternateContactNumber;
        this.alternateEmailId = alternateEmailId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoiningService() {
        return dateOfJoiningService;
    }

    public void setDateOfJoiningService(Date dateOfJoiningService) {
        this.dateOfJoiningService = dateOfJoiningService;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfJoiningPf() {
        return dateOfJoiningPf;
    }

    public void setDateOfJoiningPf(Date dateOfJoiningPf) {
        this.dateOfJoiningPf = dateOfJoiningPf;
    }

    public String getTransferOutType() {
        return transferOutType;
    }

    public void setTransferOutType(String transferOutType) {
        this.transferOutType = transferOutType;
    }

    public String getTransferOutStatus() {
        return transferOutStatus;
    }

    public void setTransferOutStatus(String transferOutStatus) {
        this.transferOutStatus = transferOutStatus;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfLeavingService() {
        return dateOfLeavingService;
    }

    public void setDateOfLeavingService(Date dateOfLeavingService) {
        this.dateOfLeavingService = dateOfLeavingService;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getFromPfNumber() {
        return fromPfNumber;
    }

    public void setFromPfNumber(String fromPfNumber) {
        this.fromPfNumber = fromPfNumber;
    }

    public String getFromEpsNumber() {
        return fromEpsNumber;
    }

    public void setFromEpsNumber(String fromEpsNumber) {
        this.fromEpsNumber = fromEpsNumber;
    }

    public String getFromUanNumber() {
        return fromUanNumber;
    }

    public void setFromUanNumber(String fromUanNumber) {
        this.fromUanNumber = fromUanNumber;
    }

    public String getToPfNumber() {
        return toPfNumber;
    }

    public void setToPfNumber(String toPfNumber) {
        this.toPfNumber = toPfNumber;
    }

    public String getToEpsNumber() {
        return toEpsNumber;
    }

    public void setToEpsNumber(String toEpsNumber) {
        this.toEpsNumber = toEpsNumber;
    }

    public String getToUanNumber() {
        return toUanNumber;
    }

    public void setToUanNumber(String toUanNumber) {
        this.toUanNumber = toUanNumber;
    }

    public String getCurrentEmployerName() {
        return currentEmployerName;
    }

    public void setCurrentEmployerName(String currentEmployerName) {
        this.currentEmployerName = currentEmployerName;
    }

    public String getCurrentEmployerAddressLine1() {
        return currentEmployerAddressLine1;
    }

    public void setCurrentEmployerAddressLine1(String currentEmployerAddressLine1) {
        this.currentEmployerAddressLine1 = currentEmployerAddressLine1;
    }

    public String getCurrentEmployerAddressLine2() {
        return currentEmployerAddressLine2;
    }

    public void setCurrentEmployerAddressLine2(String currentEmployerAddressLine2) {
        this.currentEmployerAddressLine2 = currentEmployerAddressLine2;
    }

    public String getCurrentEmployerAddressLine3() {
        return currentEmployerAddressLine3;
    }

    public void setCurrentEmployerAddressLine3(String currentEmployerAddressLine3) {
        this.currentEmployerAddressLine3 = currentEmployerAddressLine3;
    }

    public String getCurrentEmployerAddressLine4() {
        return currentEmployerAddressLine4;
    }

    public void setCurrentEmployerAddressLine4(String currentEmployerAddressLine4) {
        this.currentEmployerAddressLine4 = currentEmployerAddressLine4;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getAlternateEmailId() {
        return alternateEmailId;
    }

    public void setAlternateEmailId(String alternateEmailId) {
        this.alternateEmailId = alternateEmailId;
    }

    public List<DocumentDTO> getDocumentDTOList() {
        return documentDTOList;
    }

    public void setDocumentDTOList(List<DocumentDTO> documentDTOList) {
        this.documentDTOList = documentDTOList;
    }

    public static TransferOutBasicDetailsDTO build(TransferOut transferOut) {

        Employee employee = transferOut.getEmployee();

        TransferOutType transferOutType1 = transferOut.getTransferOutType();

        TransferOutStatus transferOutStatus1 = transferOut.getTransferOutStatus();

        TransferOutBasicDetailsDTO transferOutBasicDetailsDTO = new TransferOutBasicDetailsDTO(transferOut.getEntityId(),
                employee.getPernNumber(), employee.getPfNumber(), employee.getTokenNumber(),
                employee.getUnitCode(), employee.getName(), employee.getContributionStatus().getDescription(),
                employee.getDateOfJoining(), employee.getDateOfJoiningPF(), transferOutType1.getTitle(),
                transferOutStatus1.getTitle(), transferOut.getDocumentNumber(),
                transferOut.getInterestRate(), transferOut.getDateOfLeavingService(),
                transferOut.getDueDate(), transferOut.getPayeeName(),
                transferOut.getAddressLine1(), transferOut.getAddressLine2(),
                transferOut.getAddressLine3(), transferOut.getAddressLine4(),
                transferOut.getFromPfNumber(), transferOut.getFromEpsNumber(),
                transferOut.getFromUanNumber(), transferOut.getToPfNumber(), transferOut.getToEpsNumber(),
                transferOut.getToUanNumber(), transferOut.getCurrentEmployerName(),
                transferOut.getCurrentEmployerAddressLine1(),
                transferOut.getCurrentEmployerAddressLine2(), transferOut.getCurrentEmployerAddressLine3(),
                transferOut.getCurrentEmployerAddressLine4(), transferOut.getAlternateContactNumber(),
                transferOut.getAlternateEmailId());

        List<DocumentDTO> documentDTOS = transferOut.getTransferOutDocuments().stream()
                .map(transferOutDocument -> new DocumentDTO(transferOutDocument.getDocument().getName(),
                        transferOutDocument.getPath()))
                .collect(Collectors.toList());

        transferOutBasicDetailsDTO.setDocumentDTOList(documentDTOS);

        return transferOutBasicDetailsDTO;
    }


}
