package com.coreintegra.pftrust.processors;

import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.PfAccountHolder;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PreviousCompany;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInFinalDetails;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferInStatus;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.TransferInDTO;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;

import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class TransferInProcessor {

    public TransferIn process(TransferInDTO item) throws Exception {

        TransferIn transferIn = new TransferIn();

        Employee employee = new Employee();
        employee.setPernNumber(item.getPernr());

        transferIn.setEmployee(employee);

        PfAccountHolder pfAccountHolder = new PfAccountHolder();

        if(item.getSettype() == null || item.getSettype().equals("06")){
            pfAccountHolder.setId(1L);
        }else {
            pfAccountHolder.setId(2L);
        }

        transferIn.setPrevPfAccountHolder(pfAccountHolder);

        PreviousCompany previousCompany = new PreviousCompany();

        previousCompany.setName(item.getCompname());

        previousCompany.setDateOfJoining(getDate(item.getDat02()));

        previousCompany.setAddressLine1(item.getCoaddress1());
        previousCompany.setAddressLine2(item.getCoaddress2());
        previousCompany.setAddressLine3(item.getCoaddress3());
        previousCompany.setAddressLine4(item.getCoaddress4());

        transferIn.setPreviousCompany(previousCompany);

        transferIn.setTransferInCreatedBy(item.getUname());

        transferIn.setCreatedAtTimestamp(getDate(item.getBegda()).getTime());

        if(item.getAedtm() != null){
            transferIn.setUpdatedAtTimestamp(getDate(item.getAedtm()).getTime());
        }

        transferIn.setPrevPfNumber(item.getPfacfr());

        transferIn.setPresPfNumber(item.getPfacto());

        transferIn.setPrevPensionAccountNumber(item.getEpsacfr());
        transferIn.setPresEPSNumber(item.getEpsacto());

        if(item.getTraddress1() != null && !item.getTraddress1().trim().isEmpty()
                && !item.getTraddress1().trim().equals("")){

            transferIn.setAddressLine1(item.getTraddress1());
            transferIn.setAddressLine2(item.getTraddress2());
            transferIn.setAddressLine3(item.getTraddress3());
            transferIn.setAddressLine4(item.getTraddress4());

        }else {

            transferIn.setAddressLine1(item.getCompname());

            transferIn.setAddressLine2(item.getCoaddress1());
            transferIn.setAddressLine3(item.getCoaddress2());
            transferIn.setAddressLine4(item.getCoaddress3() + " " + (item.getCoaddress4() == null ? "" : item.getCoaddress4()));

        }

        TransferInStatus transferInStatus = new TransferInStatus();

        if(item.getStatus() != null && !item.getStatus().isEmpty() && item.getStatus().equals("R")){
            transferInStatus.setId(2L);
        }else {
            transferInStatus.setId(1L);
        }

        transferIn.setTransferInStatus(transferInStatus);

        transferIn.setAlternateEmailId(item.getEmailId());
        transferIn.setAlternateContactNumber(item.getMobileNo());

        transferIn.setChequeNumber(item.getChqno());
        transferIn.setBank(item.getBank());
        transferIn.setPostingDate(getDate(item.getDat03()));
        transferIn.setDateOfJoiningPrior(getDate(item.getDat02()));
        transferIn.setRefNumber(item.getRefno());

        SAPStatus sapStatus = new SAPStatus();
        sapStatus.setId(3L);

        transferIn.setSapStatus(sapStatus);

        TransferInFinalDetails transfer = new TransferInFinalDetails();
        transfer.setMemberContribution(new BigDecimal(item.getEmpcont()));
        transfer.setCompanyContribution(new BigDecimal(item.getEmrcont()));
        transfer.setVpfContribution(new BigDecimal(0));

        transferIn.setTransferInFinalDetails(transfer);

        transferIn.setChequeNumber(item.getChqno());
        transferIn.setBank(item.getBank());

        transferIn.setSapDocumentNumber(item.getDocno());

        if(item.getDat03() != null && !item.getDat03().isEmpty() && !item.getDat03().equals("")) {

            transfer.setYear(FinancialYearAndMonth.getFinancialYear(getDate(item.getDat03())));

            int financialMonth = FinancialYearAndMonth.getFinancialMonth(getDate(item.getDat03()));

            transfer.setContributedIn(financialMonth);
        }else {
            transfer.setContributedIn(0);
        }

        return transferIn;
    }



    private Date getDate(String date) throws ParseException {

        if(date == null || date.equals("null") || date.isEmpty()) {
            return null;
        }else {

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            format.setTimeZone(TimeZone.getTimeZone("IST"));
            return format.parse(date);
        }
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
