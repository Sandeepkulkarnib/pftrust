package com.coreintegra.pftrust.entities.pf.settlement.dtos;

public class FetchedSettlementDTO {

    private String pernr;
    private String subty;

    private String begda;
    private String aedtm;
    private String uname;

    private String fromdate;
    private String entrydate;

    private String applno;
    private String status;
    private String statusdate;

    private String settlementtype;
    private String cessationdate;
    private String duedate;


    private String toaccnum;
    private String epsno;

    private String paymet;

    private String payeename;

    private String address1;
    private String address2;
    private String address3;
    private String address4;

    private String compname;

    private String coaddress1;
    private String coaddress2;
    private String coaddress3;
    private String coaddress4;

    private String bankl;
    private String brnch;
    private String bankn;
    private String swift;
    private String ifsc;

    private String docno;
    private String txndate;
    private String txnamt = "0";
    private String chqnum;
    private String pfbank;
    private String chqdate;
    private String chqamt = "0";

    private String taxamt = "0";
    private String educess  = "0";

    private String memCont = "0";
    private String compCont = "0";
    private String epfCont = "0";

    private String memInt = "0";
    private String compInt = "0";
    private String epfInt = "0";

    private String memDy = "0";
    private String compDy = "0";
    private String epfDy = "0";

    private String memDyInt = "0";
    private String compDyInt = "0";
    private String epfDyInt = "0";

    private String memTi = "0";
    private String comTi = "0";
    private String epfTi = "0";

    private String memTiInt = "0";
    private String comTiInt = "0";
    private String epfTiInt = "0";

    private String memLoan = "0";
    private String comLoan = "0";
    private String epfLoan = "0";

    private String memLoanInt = "0";
    private String comLoanInt = "0";
    private String epfLoanInt = "0";

    private String lrDate;

    private String mobileNo;
    private String emailId;

    public String getPernr() {
        return pernr;
    }

    public void setPernr(String pernr) {
        this.pernr = pernr;
    }

    public String getSubty() {
        return subty;
    }

    public void setSubty(String subty) {
        this.subty = subty;
    }

    public String getBegda() {
        return begda;
    }

    public void setBegda(String begda) {
        this.begda = begda;
    }

    public String getAedtm() {
        return aedtm;
    }

    public void setAedtm(String aedtm) {
        this.aedtm = aedtm;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getApplno() {
        return applno;
    }

    public void setApplno(String applno) {
        this.applno = applno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(String statusdate) {
        this.statusdate = statusdate;
    }

    public String getSettlementtype() {
        return settlementtype;
    }

    public void setSettlementtype(String settlementtype) {
        this.settlementtype = settlementtype;
    }

    public String getCessationdate() {
        return cessationdate;
    }

    public void setCessationdate(String cessationdate) {
        this.cessationdate = cessationdate;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getToaccnum() {
        return toaccnum;
    }

    public void setToaccnum(String toaccnum) {
        this.toaccnum = toaccnum;
    }

    public String getEpsno() {
        return epsno;
    }

    public void setEpsno(String epsno) {
        this.epsno = epsno;
    }

    public String getPaymet() {
        return paymet;
    }

    public void setPaymet(String paymet) {
        this.paymet = paymet;
    }

    public String getPayeename() {
        return payeename;
    }

    public void setPayeename(String payeename) {
        this.payeename = payeename;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getCoaddress1() {
        return coaddress1;
    }

    public void setCoaddress1(String coaddress1) {
        this.coaddress1 = coaddress1;
    }

    public String getCoaddress2() {
        return coaddress2;
    }

    public void setCoaddress2(String coaddress2) {
        this.coaddress2 = coaddress2;
    }

    public String getCoaddress3() {
        return coaddress3;
    }

    public void setCoaddress3(String coaddress3) {
        this.coaddress3 = coaddress3;
    }

    public String getCoaddress4() {
        return coaddress4;
    }

    public void setCoaddress4(String coaddress4) {
        this.coaddress4 = coaddress4;
    }

    public String getBankl() {
        return bankl;
    }

    public void setBankl(String bankl) {
        this.bankl = bankl;
    }

    public String getBrnch() {
        return brnch;
    }

    public void setBrnch(String brnch) {
        this.brnch = brnch;
    }

    public String getBankn() {
        return bankn;
    }

    public void setBankn(String bankn) {
        this.bankn = bankn;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public String getTxndate() {
        return txndate;
    }

    public void setTxndate(String txndate) {
        this.txndate = txndate;
    }

    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public String getChqnum() {
        return chqnum;
    }

    public void setChqnum(String chqnum) {
        this.chqnum = chqnum;
    }

    public String getPfbank() {
        return pfbank;
    }

    public void setPfbank(String pfbank) {
        this.pfbank = pfbank;
    }

    public String getChqdate() {
        return chqdate;
    }

    public void setChqdate(String chqdate) {
        this.chqdate = chqdate;
    }

    public String getChqamt() {
        return chqamt;
    }

    public void setChqamt(String chqamt) {
        this.chqamt = chqamt;
    }

    public String getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(String taxamt) {
        this.taxamt = taxamt;
    }

    public String getEducess() {
        return educess;
    }

    public void setEducess(String educess) {
        this.educess = educess;
    }

    public String getMemCont() {
        return memCont;
    }

    public void setMemCont(String memCont) {
        this.memCont = memCont;
    }

    public String getCompCont() {
        return compCont;
    }

    public void setCompCont(String compCont) {
        this.compCont = compCont;
    }

    public String getEpfCont() {
        return epfCont;
    }

    public void setEpfCont(String epfCont) {
        this.epfCont = epfCont;
    }

    public String getMemInt() {
        return memInt;
    }

    public void setMemInt(String memInt) {
        this.memInt = memInt;
    }

    public String getCompInt() {
        return compInt;
    }

    public void setCompInt(String compInt) {
        this.compInt = compInt;
    }

    public String getEpfInt() {
        return epfInt;
    }

    public void setEpfInt(String epfInt) {
        this.epfInt = epfInt;
    }

    public String getMemDy() {
        return memDy;
    }

    public void setMemDy(String memDy) {
        this.memDy = memDy;
    }

    public String getCompDy() {
        return compDy;
    }

    public void setCompDy(String compDy) {
        this.compDy = compDy;
    }

    public String getEpfDy() {
        return epfDy;
    }

    public void setEpfDy(String epfDy) {
        this.epfDy = epfDy;
    }

    public String getMemDyInt() {
        return memDyInt;
    }

    public void setMemDyInt(String memDyInt) {
        this.memDyInt = memDyInt;
    }

    public String getCompDyInt() {
        return compDyInt;
    }

    public void setCompDyInt(String compDyInt) {
        this.compDyInt = compDyInt;
    }

    public String getEpfDyInt() {
        return epfDyInt;
    }

    public void setEpfDyInt(String epfDyInt) {
        this.epfDyInt = epfDyInt;
    }

    public String getMemTi() {
        return memTi;
    }

    public void setMemTi(String memTi) {
        this.memTi = memTi;
    }

    public String getComTi() {
        return comTi;
    }

    public void setComTi(String comTi) {
        this.comTi = comTi;
    }

    public String getEpfTi() {
        return epfTi;
    }

    public void setEpfTi(String epfTi) {
        this.epfTi = epfTi;
    }

    public String getMemTiInt() {
        return memTiInt;
    }

    public void setMemTiInt(String memTiInt) {
        this.memTiInt = memTiInt;
    }

    public String getComTiInt() {
        return comTiInt;
    }

    public void setComTiInt(String comTiInt) {
        this.comTiInt = comTiInt;
    }

    public String getEpfTiInt() {
        return epfTiInt;
    }

    public void setEpfTiInt(String epfTiInt) {
        this.epfTiInt = epfTiInt;
    }

    public String getMemLoan() {
        return memLoan;
    }

    public void setMemLoan(String memLoan) {
        this.memLoan = memLoan;
    }

    public String getComLoan() {
        return comLoan;
    }

    public void setComLoan(String comLoan) {
        this.comLoan = comLoan;
    }

    public String getEpfLoan() {
        return epfLoan;
    }

    public void setEpfLoan(String epfLoan) {
        this.epfLoan = epfLoan;
    }

    public String getMemLoanInt() {
        return memLoanInt;
    }

    public void setMemLoanInt(String memLoanInt) {
        this.memLoanInt = memLoanInt;
    }

    public String getComLoanInt() {
        return comLoanInt;
    }

    public void setComLoanInt(String comLoanInt) {
        this.comLoanInt = comLoanInt;
    }

    public String getEpfLoanInt() {
        return epfLoanInt;
    }

    public void setEpfLoanInt(String epfLoanInt) {
        this.epfLoanInt = epfLoanInt;
    }

    public String getLrDate() {
        return lrDate;
    }

    public void setLrDate(String lrDate) {
        this.lrDate = lrDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
