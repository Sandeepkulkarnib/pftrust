package com.coreintegra.pftrust.entities.pf.transferIn.dtos;

import com.coreintegra.pftrust.entities.pf.PfAccountHolder;
import com.coreintegra.pftrust.entities.pf.State;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TransferInBasicDetailsDTO {

    private String pernNumber;
    private String pfNumber;
    private String tokenNumber;
    private String unitCode;

    private String name;
    private String status;
    private Date dateOfJoiningService;
    private Date dateOfJoiningPf;

    private String companyName;
    private String transferInStatus;
    private String refNumber;

    private String prevPfNumber;
    private String prevEPSNumber;
    private Date prevDateOfJoining;
    private Date prevDateOfLeaving;

    private String alternateContactNumber;
    private String alternateEmailId;

    private String prevAccountHolder;
    private String addressLine1;
    private String addressLine2;

    private String addressLine3;
    private String addressLine4;
    private String pincode;
    private String state;

    private String annexureKFile;
    private String dispatchLetterFile;

    public TransferInBasicDetailsDTO(String pernNumber, String pfNumber, String tokenNumber, String unitCode,
                                     String name, String status, Date dateOfJoiningService, Date dateOfJoiningPf,
                                     String companyName, String transferInStatus, String refNumber, String prevPfNumber,
                                     String prevEPSNumber, Date prevDateOfJoining, Date prevDateOfLeaving,
                                     String prevAccountHolder, String addressLine1, String addressLine2,
                                     String addressLine3, String addressLine4, String pincode, String state,
                                     String annexureKFile, String dispatchLetterFile) {
        this.pernNumber = pernNumber;
        this.pfNumber = pfNumber;
        this.tokenNumber = tokenNumber;
        this.unitCode = unitCode;
        this.name = name;
        this.status = status;
        this.dateOfJoiningService = dateOfJoiningService;
        this.dateOfJoiningPf = dateOfJoiningPf;
        this.companyName = companyName;
        this.transferInStatus = transferInStatus;
        this.refNumber = refNumber;
        this.prevPfNumber = prevPfNumber;
        this.prevEPSNumber = prevEPSNumber;
        this.prevDateOfJoining = prevDateOfJoining;
        this.prevDateOfLeaving = prevDateOfLeaving;
        this.prevAccountHolder = prevAccountHolder;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.pincode = pincode;
        this.state = state;
        this.annexureKFile = annexureKFile;
        this.dispatchLetterFile = dispatchLetterFile;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTransferInStatus() {
        return transferInStatus;
    }

    public void setTransferInStatus(String transferInStatus) {
        this.transferInStatus = transferInStatus;
    }

    public String getPrevAccountHolder() {
        return prevAccountHolder;
    }

    public void setPrevAccountHolder(String prevAccountHolder) {
        this.prevAccountHolder = prevAccountHolder;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getPrevPfNumber() {
        return prevPfNumber;
    }

    public void setPrevPfNumber(String prevPfNumber) {
        this.prevPfNumber = prevPfNumber;
    }

    public String getPrevEPSNumber() {
        return prevEPSNumber;
    }

    public void setPrevEPSNumber(String prevEPSNumber) {
        this.prevEPSNumber = prevEPSNumber;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPrevDateOfJoining() {
        return prevDateOfJoining;
    }

    public void setPrevDateOfJoining(Date prevDateOfJoining) {
        this.prevDateOfJoining = prevDateOfJoining;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    public Date getPrevDateOfLeaving() {
        return prevDateOfLeaving;
    }

    public void setPrevDateOfLeaving(Date prevDateOfLeaving) {
        this.prevDateOfLeaving = prevDateOfLeaving;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAnnexureKFile() {
        return annexureKFile;
    }

    public void setAnnexureKFile(String annexureKFile) {
        this.annexureKFile = annexureKFile;
    }

    public String getDispatchLetterFile() {
        return dispatchLetterFile;
    }

    public void setDispatchLetterFile(String dispatchLetterFile) {
        this.dispatchLetterFile = dispatchLetterFile;
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

    public static TransferInBasicDetailsDTO build(TransferIn transferIn){

        Employee employee = transferIn.getEmployee();
        TransferInStatus transferInStatus1 = transferIn.getTransferInStatus();
        PfAccountHolder prevPfAccountHolder = transferIn.getPrevPfAccountHolder();
        PreviousCompany previousCompany = transferIn.getPreviousCompany();

        State state1 = transferIn.getState();

        TransferInBasicDetailsDTO transferInBasicDetailsDTO = new TransferInBasicDetailsDTO(employee.getPernNumber(), employee.getPfNumber(), employee.getTokenNumber(),
                employee.getUnitCode(), employee.getName(), employee.getContributionStatus().getDescription(),
                employee.getDateOfJoining(), employee.getDateOfJoiningPF(),
                previousCompany == null || previousCompany.getName() == null ? "" : previousCompany.getName(),
                transferInStatus1.getTitle(), transferIn.getRefNumber(),
                transferIn.getPrevPfNumber(), transferIn.getPrevPfNumber(),
                transferIn.getPrevDateOfJoining(), transferIn.getPrevDateOfLeaving(),
                prevPfAccountHolder.getName(), transferIn.getAddressLine1(), transferIn.getAddressLine2(),
                transferIn.getAddressLine3(), transferIn.getAddressLine4(), transferIn.getPincode(),
                state1 == null || state1.getName() == null ? "" : state1.getName(),
                transferIn.getAnnexureKFile(),
                transferIn.getDispatchLetterFile());

        transferInBasicDetailsDTO.setAlternateEmailId(transferIn.getAlternateEmailId());
        transferInBasicDetailsDTO.setAlternateContactNumber(transferIn.getAlternateContactNumber());

        return transferInBasicDetailsDTO;
    }


}
