package com.coreintegra.pftrust.entities.pf.settlement.dtos;

import com.coreintegra.pftrust.dtos.DocumentDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.settlement.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SettlementBasicDetailsDTO {

    private String entityId;

    private String pernNumber;
    private String pfNumber;
    private String tokenNumber;
    private String unitCode;

    private String name;
    private String status;
    private Date dateOfJoiningService;
    private Date dateOfJoiningPf;

    private String settlementType;
    private String settlementStatus;

    private Date dateOfLeavingService;
    private Date dateOfSettlement;

    private String altEmailId;
    private String altContactNumber;

    private String payeeName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String bankName;
    private String branch;
    private String accountNumber;
    private String ifscCode;
    private String micrCode;
    private String swift;

    private List<DocumentDTO> documents;

    public SettlementBasicDetailsDTO(String entityId, String pernNumber, String pfNumber, String tokenNumber,
                                     String unitCode, String name, String status, Date dateOfJoiningService,
                                     Date dateOfJoiningPf, String settlementType, String settlementStatus,
                                     Date dateOfLeavingService, Date dateOfSettlement, String altEmailId,
                                     String altContactNumber, String payeeName, String addressLine1,
                                     String addressLine2, String addressLine3, String addressLine4,
                                     String bankName, String branch, String accountNumber, String ifscCode,
                                     String micrCode, String swift) {
        this.entityId = entityId;
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.tokenNumber = tokenNumber;
        this.unitCode = unitCode;
        this.name = name;
        this.status = status;
        this.dateOfJoiningService = dateOfJoiningService;
        this.dateOfJoiningPf = dateOfJoiningPf;
        this.settlementType = settlementType;
        this.settlementStatus = settlementStatus;
        this.dateOfLeavingService = dateOfLeavingService;
        this.dateOfSettlement = dateOfSettlement;
        this.altEmailId = altEmailId;
        this.altContactNumber = altContactNumber;
        this.payeeName = payeeName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.bankName = bankName;
        this.branch = branch;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.micrCode = micrCode;
        this.swift = swift;
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

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfLeavingService() {
        return dateOfLeavingService;
    }

    public void setDateOfLeavingService(Date dateOfLeavingService) {
        this.dateOfLeavingService = dateOfLeavingService;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getDateOfSettlement() {
        return dateOfSettlement;
    }

    public void setDateOfSettlement(Date dateOfSettlement) {
        this.dateOfSettlement = dateOfSettlement;
    }

    public String getAltEmailId() {
        return altEmailId;
    }

    public void setAltEmailId(String altEmailId) {
        this.altEmailId = altEmailId;
    }

    public String getAltContactNumber() {
        return altContactNumber;
    }

    public void setAltContactNumber(String altContactNumber) {
        this.altContactNumber = altContactNumber;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public static SettlementBasicDetailsDTO build(Settlement settlement) {

        Employee employee = settlement.getEmployee();
        SettlementType settlementType1 = settlement.getSettlementType();
        SettlementStatus settlementStatus1 = settlement.getSettlementStatus();

        List<SettlementDocument> settlementDocuments = settlement.getSettlementDocuments();

        SettlementBasicDetailsDTO settlementBasicDetailsDTO = new SettlementBasicDetailsDTO(settlement.getEntityId(), employee.getPernNumber(),
                employee.getPfNumber(), employee.getTokenNumber(), employee.getUnitCode(), employee.getName(),
                employee.getContributionStatus().getDescription(), employee.getDateOfJoining(),
                employee.getDateOfJoiningPF(), settlementType1.getTitle(),
                settlementStatus1.getTitle(), settlement.getDateOfLeavingService(),
                settlement.getDateOfSettlement(), settlement.getAltEmailId(),
                settlement.getAltContactNumber(), settlement.getPayeeName(), settlement.getAddressLine1(),
                settlement.getAddressLine2(), settlement.getAddressLine3(), settlement.getAddressLine4(),
                settlement.getBankName(), settlement.getBranch(), settlement.getAccountNumber(),
                settlement.getIfscCode(), settlement.getMicrCode(), settlement.getSwift());

        List<DocumentDTO> documentDTOList = settlementDocuments.stream()
                .map(settlementDocument -> new DocumentDTO(settlementDocument.getDocument().getName(),
                        settlementDocument.getPath()))
                .collect(Collectors.toList());

        settlementBasicDetailsDTO.setDocuments(documentDTOList);

        return settlementBasicDetailsDTO;
    }

}
