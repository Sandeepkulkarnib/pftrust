package com.coreintegra.pftrust.processors;

import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.SettlementInterface;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentTransferInContribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentYearContribution;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.CurrentYearLoanWithdrawal;
import com.coreintegra.pftrust.entities.pf.contribution.dtos.YearOpeningContribution;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.settlement.Settlement;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementFinalDetails;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementStatus;
import com.coreintegra.pftrust.entities.pf.settlement.SettlementType;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.FetchedSettlementDTO;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementDTO;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOut;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutStatus;
import com.coreintegra.pftrust.entities.pf.transferOut.TransferOutType;
import com.coreintegra.pftrust.entities.pf.transferOut.dtos.TransferOutDTO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SettlementProcessor {


    public SettlementInterface process(FetchedSettlementDTO item) throws Exception {

        String code = item.getSubty();

        if(code.trim().equals("06") || code.trim().equals("07")){
            return mapToTransferOut(item);
        }

        return mapToSettlement(item);

    }


    private Date getDate(String date) throws ParseException {

        if(date == null || date.equals("null") || date.isEmpty()) {
            return null;
        }else {

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return format.parse(date);
        }
    }


    private Settlement mapToSettlement(FetchedSettlementDTO settlementDTO) throws ParseException {

        Settlement settlement = new Settlement();

        settlement.setDocumentNumber(settlementDTO.getDocno());

        settlement.setDateOfLeavingService(getDate(settlementDTO.getCessationdate()));
        settlement.setDateOfSettlement(getDate(settlementDTO.getDuedate()));

        settlement.setAltEmailId(settlementDTO.getEmailId());
        settlement.setAltContactNumber(settlementDTO.getMobileNo());

        if(settlementDTO.getBegda() != null) {
            settlement.setCreatedAtTimestamp(getDate(settlementDTO.getBegda()).getTime());
        }

        if(settlementDTO.getAedtm() != null) {
            settlement.setUpdatedAtTimestamp(getDate(settlementDTO.getAedtm()).getTime());
        }

        settlement.setSettlementCreatedBy(settlementDTO.getUname());

        settlement.setPayeeName(settlementDTO.getPayeename());
        settlement.setAddressLine1(settlementDTO.getAddress1());
        settlement.setAddressLine2(settlementDTO.getAddress2());
        settlement.setAddressLine3(settlementDTO.getAddress3());
        settlement.setAddressLine4(settlementDTO.getAddress4());

        settlement.setBankName(settlementDTO.getBankl());
        settlement.setBranch(settlementDTO.getBrnch());
        settlement.setAccountNumber(settlementDTO.getBankn());
        settlement.setSwift(settlementDTO.getSwift());
        settlement.setIfscCode(settlementDTO.getIfsc());

        settlement.setIncomeTax(new BigDecimal(settlementDTO.getTaxamt()));
        settlement.setEduCess(new BigDecimal(settlementDTO.getEducess()));

        settlement.setChequeNo(settlementDTO.getChqnum());
        settlement.setPaymentDate(getDate(settlementDTO.getTxndate()));

        Employee employee = new Employee();
        employee.setPernNumber(settlementDTO.getPernr());

        settlement.setEmployee(employee);

        SettlementType settlementType = new SettlementType();
        settlementType.setCode(Integer.valueOf(settlementDTO.getSubty().trim()));

        settlement.setSettlementType(settlementType);

        settlement.setPaymentMode(getPaymentMode(settlementDTO.getPaymet()));

        Bank bank = new Bank();
        bank.setName(settlementDTO.getPfbank());

        settlement.setBank(bank);

        SettlementStatus settlementStatus = new SettlementStatus();

        if(settlementDTO.getStatus() == null || settlementDTO.getStatus().trim().equals("A")){
            settlementStatus.setId(2L);
        }else {
            settlementStatus.setId(3L);
        }

        settlement.setSettlementStatus(settlementStatus);

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(3L);

        settlement.setSapStatus(sapStatus);

        SettlementFinalDetails settlementFinalDetails = new SettlementFinalDetails();

        settlementFinalDetails.setIncomeTax(new BigDecimal(settlementDTO.getTaxamt()));
        settlementFinalDetails.setEduCess(new BigDecimal(settlementDTO.getEducess()));

        YearOpeningContribution yearOpeningContribution = new YearOpeningContribution(new BigDecimal(settlementDTO.getMemCont()),
                new BigDecimal(settlementDTO.getCompCont()), new BigDecimal(settlementDTO.getEpfCont()),
                new BigDecimal(settlementDTO.getMemInt()), new BigDecimal(settlementDTO.getCompInt()),
                new BigDecimal(settlementDTO.getEpfInt()));

        CurrentYearContribution currentYearContribution = new CurrentYearContribution(new BigDecimal(settlementDTO.getMemDy()),
                new BigDecimal(settlementDTO.getCompDy()), new BigDecimal(settlementDTO.getEpfDy()), 0,
                new BigDecimal(settlementDTO.getMemDyInt()), new BigDecimal(settlementDTO.getCompDyInt()),
                new BigDecimal(settlementDTO.getEpfDyInt()));

        CurrentTransferInContribution currentTransferInContribution = new CurrentTransferInContribution(
                new BigDecimal(settlementDTO.getMemTi()), new BigDecimal(settlementDTO.getComTi()),
                new BigDecimal(settlementDTO.getEpfTi()), 0, new BigDecimal(settlementDTO.getMemTiInt()),
                new BigDecimal(settlementDTO.getComTiInt()), new BigDecimal(settlementDTO.getEpfTiInt())
        );

        CurrentYearLoanWithdrawal currentYearLoanWithdrawal = new CurrentYearLoanWithdrawal(
                new BigDecimal(settlementDTO.getMemLoan()), new BigDecimal(settlementDTO.getComLoan()),
                new BigDecimal(settlementDTO.getEpfLoan()), 0, new BigDecimal(settlementDTO.getMemLoanInt()),
                new BigDecimal(settlementDTO.getComLoanInt()), new BigDecimal(settlementDTO.getEpfLoanInt()));

        SettlementDTO dto = new SettlementDTO();

        dto.setYearOpeningContribution(yearOpeningContribution);
        dto.setCurrentYearContribution(currentYearContribution);
        dto.setCurrentTransferInContribution(currentTransferInContribution);
        dto.setCurrentYearLoanWithdrawal(currentYearLoanWithdrawal);

        settlementFinalDetails.map(dto);

        settlementFinalDetails.setNetCreditAmount(new BigDecimal(settlementDTO.getChqamt()));

        settlement.setSettlementFinalDetails(settlementFinalDetails);

        return settlement;

    }


    private TransferOut mapToTransferOut(FetchedSettlementDTO settlementDTO) throws ParseException {

        TransferOut transferOut = new TransferOut();

        transferOut.setDocumentNumber(settlementDTO.getDocno());

        transferOut.setDateOfLeavingService(getDate(settlementDTO.getCessationdate()));
        transferOut.setDueDate(getDate(settlementDTO.getDuedate()));

        transferOut.setAlternateEmailId(settlementDTO.getEmailId());
        transferOut.setAlternateContactNumber(settlementDTO.getMobileNo());

        transferOut.setTransferOutCreatedBy(settlementDTO.getUname());


        if(settlementDTO.getBegda() != null) {
            transferOut.setCreatedAtTimestamp(getDate(settlementDTO.getBegda()).getTime());
        }

        if(settlementDTO.getAedtm() != null) {
            transferOut.setUpdatedAtTimestamp(getDate(settlementDTO.getAedtm()).getTime());
        }
        transferOut.setPayeeName(settlementDTO.getPayeename());
        transferOut.setAddressLine1(settlementDTO.getAddress1());
        transferOut.setAddressLine2(settlementDTO.getAddress2());
        transferOut.setAddressLine3(settlementDTO.getAddress3());
        transferOut.setAddressLine4(settlementDTO.getAddress4());

        transferOut.setToPfNumber(settlementDTO.getToaccnum());

        transferOut.setFromEpsNumber(settlementDTO.getEpsno());

        transferOut.setCurrentEmployerName(settlementDTO.getCompname());
        transferOut.setCurrentEmployerAddressLine1(settlementDTO.getCoaddress1());
        transferOut.setCurrentEmployerAddressLine2(settlementDTO.getCoaddress2());
        transferOut.setCurrentEmployerAddressLine3(settlementDTO.getCoaddress3());
        transferOut.setCurrentEmployerAddressLine4(settlementDTO.getCoaddress4());

        transferOut.setChequeNo(settlementDTO.getChqnum());
        transferOut.setPaymentDate(getDate(settlementDTO.getTxndate()));
        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(3L);
        Employee employee = new Employee();
        employee.setPernNumber(settlementDTO.getPernr());

        transferOut.setEmployee(employee);

        TransferOutType transferOutType = new TransferOutType();
        transferOutType.setCode(settlementDTO.getSubty().trim());

        transferOut.setTransferOutType(transferOutType);

        transferOut.setPaymentMode(getPaymentMode(settlementDTO.getPaymet()));

        Bank bank = new Bank();
        bank.setName(settlementDTO.getPfbank());

        transferOut.setBank(bank);


        TransferOutStatus transferOutStatus = new TransferOutStatus();

        if(settlementDTO.getStatus() == null || settlementDTO.getStatus().trim().equals("A")){
            transferOutStatus.setId(2L);
        }else {
            transferOutStatus.setId(3L);
        }

        transferOut.setTransferOutStatus(transferOutStatus);

        TransferOutFinalDetails transferOutFinalDetails = new TransferOutFinalDetails();

        YearOpeningContribution yearOpeningContribution = new YearOpeningContribution(new BigDecimal(settlementDTO.getMemCont()),
                new BigDecimal(settlementDTO.getCompCont()), new BigDecimal(settlementDTO.getEpfCont()),
                new BigDecimal(settlementDTO.getMemInt()), new BigDecimal(settlementDTO.getCompInt()),
                new BigDecimal(settlementDTO.getEpfInt()));

        CurrentYearContribution currentYearContribution = new CurrentYearContribution(new BigDecimal(settlementDTO.getMemDy()),
                new BigDecimal(settlementDTO.getCompDy()), new BigDecimal(settlementDTO.getEpfDy()), 0,
                new BigDecimal(settlementDTO.getMemDyInt()), new BigDecimal(settlementDTO.getCompDyInt()),
                new BigDecimal(settlementDTO.getEpfDyInt()));

        CurrentTransferInContribution currentTransferInContribution = new CurrentTransferInContribution(
                new BigDecimal(settlementDTO.getMemTi()), new BigDecimal(settlementDTO.getComTi()),
                new BigDecimal(settlementDTO.getEpfTi()), 0, new BigDecimal(settlementDTO.getMemTiInt()),
                new BigDecimal(settlementDTO.getComTiInt()), new BigDecimal(settlementDTO.getEpfTiInt())
        );

        CurrentYearLoanWithdrawal currentYearLoanWithdrawal = new CurrentYearLoanWithdrawal(
                new BigDecimal(settlementDTO.getMemLoan()), new BigDecimal(settlementDTO.getComLoan()),
                new BigDecimal(settlementDTO.getEpfLoan()), 0, new BigDecimal(settlementDTO.getMemLoanInt()),
                new BigDecimal(settlementDTO.getComLoanInt()), new BigDecimal(settlementDTO.getEpfLoanInt()));

        TransferOutDTO transferOutDTO = new TransferOutDTO();

        transferOutDTO.setYearOpeningContribution(yearOpeningContribution);
        transferOutDTO.setCurrentYearContribution(currentYearContribution);
        transferOutDTO.setCurrentTransferInContribution(currentTransferInContribution);
        transferOutDTO.setCurrentYearLoanWithdrawal(currentYearLoanWithdrawal);
        transferOutFinalDetails.map(transferOutDTO);

        transferOut.setTransferOutFinalDetails(transferOutFinalDetails);

        return transferOut;
    }



    private PaymentMode getPaymentMode(String paymet){

        PaymentMode paymentMode = new PaymentMode();

        if(paymet == null || paymet.trim().equals("A")){
            paymentMode.setId(1L);
        }else if (paymet.trim().equals("L")){
            paymentMode.setId(3L);
        }else if(paymet.trim().equals("I")){
            paymentMode.setId(4L);
        }else {
            paymentMode.setId(2L);
        }

        return paymentMode;
    }

}
