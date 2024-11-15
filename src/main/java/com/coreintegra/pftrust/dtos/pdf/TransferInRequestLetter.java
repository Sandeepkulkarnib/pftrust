package com.coreintegra.pftrust.dtos.pdf;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.*;

@XmlRootElement(name = "transferInRequest")
public class TransferInRequestLetter {

    private String refNo;
    private String srNo;
    private Date applicationDate;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

    private String name;

    private String prevCompanyName;

    private String fromPfAccount;
    private String toPfAccount;

    private String fromEpsAccount;
    private String toEpsAccount;

    private List<TransferInReminders> reminders;

    @XmlElement(name = "refNo")
    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    @XmlElement(name = "srNo")
    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    @XmlElement(name = "date")
    public String getApplicationDate() {
        return fomateDate(applicationDate);
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @XmlElement(name = "addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @XmlElement(name = "addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @XmlElement(name = "addressLine3")
    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @XmlElement(name = "addressLine4")
    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "prevCompanyName")
    public String getPrevCompanyName() {
        return prevCompanyName;
    }

    public void setPrevCompanyName(String prevCompanyName) {
        this.prevCompanyName = prevCompanyName;
    }

    @XmlElement(name = "fromPfAccount")
    public String getFromPfAccount() {
        return fromPfAccount;
    }

    public void setFromPfAccount(String fromPfAccount) {
        this.fromPfAccount = fromPfAccount;
    }

    @XmlElement(name = "toPfAccount")
    public String getToPfAccount() {
        return toPfAccount;
    }

    public void setToPfAccount(String toPfAccount) {
        this.toPfAccount = toPfAccount;
    }

    @XmlElement(name = "fromEpsAccount")
    public String getFromEpsAccount() {
        return fromEpsAccount;
    }

    public void setFromEpsAccount(String fromEpsAccount) {
        this.fromEpsAccount = fromEpsAccount;
    }

    @XmlElement(name = "toEpsAccount")
    public String getToEpsAccount() {
        return toEpsAccount;
    }

    public void setToEpsAccount(String toEpsAccount) {
        this.toEpsAccount = toEpsAccount;
    }


    public String getLastReference(Date date, Long id){

        if(date == null || id == null){
            return null;
        }

        String rfno = "SPF";

        int financialYear = getYear(date);

        rfno += "/F" + (financialYear-1) + "-" + (financialYear) + "/" + id + " Dated: " + fomateDate(date);

        return rfno;

    }

    @XmlElement(name = "reminder")
    public List<Ref> getReminders() {

        List<Ref> ref = new ArrayList<>();

        for (TransferInReminders r:reminders) {
            ref.add(new Ref(getLastReference(r.getCreatedAt(), r.getId())));
        }

        return ref;
    }

    public void setReminders(List<TransferInReminders> reminders) {
        this.reminders = reminders;
    }

    private String fomateDate(Date date){
        if(date == null){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        String strDate= formatter.format(date);
        return strDate;
    }

    private Integer getYear(Date date){

        if(date == null){
            return null;
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int year = Calendar.getInstance().get(Calendar.YEAR);

        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;

        return month <= 3 ? year : (year+1);

    }

}
