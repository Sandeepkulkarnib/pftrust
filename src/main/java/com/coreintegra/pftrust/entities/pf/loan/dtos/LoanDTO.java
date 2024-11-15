package com.coreintegra.pftrust.entities.pf.loan.dtos;

public class LoanDTO {

    private String pernr; // pern number
    private String subty; // Subtype

    private String begda; // Start Date
    private String aedtm; // Changed on

    private String uname; // Changed by

    private String dlart; // Loan type
    private String dladt; // Loan Application Dat

    private String darat = "0"; // Loan Amount Applied

    private String recod; // Reason Code for Loan
    private String applnoPf; // Application No for P

    private String dlaet = "0"; // Eligible Amt
    private String pfbat = "0"; // PF Base Amt

    private String dataw; // Loan Approval Date
    private String datdw; // Loan Disbursal Date

    private String appro; // Approved By

    private String dardt = "0"; // Amount Disbursed

    private String pel = "0"; // Property/Estimated/L

    private String stampduty = "0"; // Stamp Duty
    private String registration = "0"; // Registration
    private String insurance = "0"; // Insurance
    private String anyother = "0"; // Any Other

    private String total1 = "0"; // Total1

    private String loanfromfi = "0"; // Loan From Fin.Instit

    private String incidental = "0"; // Incidental
    private String anyother1 = "0"; // Any Other 1
    private String advance = "0"; // Advance
    private String withdrawlformeal = "0"; // Withdrawl For MAEL

    private String datecomplhouse; // Date of completion O

    private String data1stalt; // Date of 1st Alterat

    private String valueofsupport = "0"; // Value Of Supporting

    private String elpfbat = "0"; // Eligible PF Base Amo

    private String costInvolved = "0"; // Cost Involved

    private String contrifrompf = "0"; // Contribution From PF

    private String bankl; // Bank Keys
    private String brnch; // Bank Branch
    private String bankn; // Bank Account
    private String swift; // SWIFT/BIC
    private String ifsc; // IFCSCode
    private String ifcs; // IFCSCode

    private String status; // Status

    private String openBal = "0"; // Opening balance
    private String loanInYr = "0"; // Last recovery date

    private String memCont = "0"; // Member contribution
    private String compCont = "0"; // Company contribution
    private String epfCont = "0"; // EPF contribution
    private String totOwn = "0"; // Total Owned
    private String totAll = "0"; // Total All

    private String aplRecDate; // Application received


    private String memContPay = "0"; // Member contribution
    private String compContPay = "0"; // Company contribution
    private String epfContPay = "0"; // EPF contribution Pa
    private String totPay = "0"; // Total Payment

    private String paymet; // PAYMENT METHOD FOR P

    private String docno; // Document Number
    private String remarks; // Remarks
    private String chqnum; // Check number
    private String chqdate; // Payment date
    private String pfbank = ""; // Payment Bank

    private String finInt; // Financial Institute
    private String finInBr; // Bank Branch
    private String finInAcno; // Financial account Nu

    private String mobileNo; // Character Field of L mobile number
    private String emailId; // E-Mail Address

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

    public String getDlart() {
        return dlart;
    }

    public void setDlart(String dlart) {
        this.dlart = dlart;
    }

    public String getDladt() {
        return dladt;
    }

    public void setDladt(String dladt) {
        this.dladt = dladt;
    }

    public String getDarat() {
        return darat;
    }

    public void setDarat(String darat) {
        this.darat = darat;
    }

    public String getRecod() {
        return recod;
    }

    public void setRecod(String recod) {
        this.recod = recod;
    }

    public String getApplnoPf() {
        return applnoPf;
    }

    public void setApplnoPf(String applnoPf) {
        this.applnoPf = applnoPf;
    }

    public String getDlaet() {
        return dlaet;
    }

    public void setDlaet(String dlaet) {
        this.dlaet = dlaet;
    }

    public String getPfbat() {
        return pfbat;
    }

    public void setPfbat(String pfbat) {
        this.pfbat = pfbat;
    }

    public String getDataw() {
        return dataw;
    }

    public void setDataw(String dataw) {
        this.dataw = dataw;
    }

    public String getDatdw() {
        return datdw;
    }

    public void setDatdw(String datdw) {
        this.datdw = datdw;
    }

    public String getAppro() {
        return appro;
    }

    public void setAppro(String appro) {
        this.appro = appro;
    }

    public String getDardt() {
        return dardt;
    }

    public void setDardt(String dardt) {
        this.dardt = dardt;
    }

    public String getPel() {
        return pel;
    }

    public void setPel(String pel) {
        this.pel = pel;
    }

    public String getStampduty() {
        return stampduty;
    }

    public void setStampduty(String stampduty) {
        this.stampduty = stampduty;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getAnyother() {
        return anyother;
    }

    public void setAnyother(String anyother) {
        this.anyother = anyother;
    }

    public String getTotal1() {
        return total1;
    }

    public void setTotal1(String total1) {
        this.total1 = total1;
    }

    public String getLoanfromfi() {
        return loanfromfi;
    }

    public void setLoanfromfi(String loanfromfi) {
        this.loanfromfi = loanfromfi;
    }

    public String getIncidental() {
        return incidental;
    }

    public void setIncidental(String incidental) {
        this.incidental = incidental;
    }

    public String getAnyother1() {
        return anyother1;
    }

    public void setAnyother1(String anyother1) {
        this.anyother1 = anyother1;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getWithdrawlformeal() {
        return withdrawlformeal;
    }

    public void setWithdrawlformeal(String withdrawlformeal) {
        this.withdrawlformeal = withdrawlformeal;
    }

    public String getDatecomplhouse() {
        return datecomplhouse;
    }

    public void setDatecomplhouse(String datecomplhouse) {
        this.datecomplhouse = datecomplhouse;
    }

    public String getData1stalt() {
        return data1stalt;
    }

    public void setData1stalt(String data1stalt) {
        this.data1stalt = data1stalt;
    }

    public String getValueofsupport() {
        return valueofsupport;
    }

    public void setValueofsupport(String valueofsupport) {
        this.valueofsupport = valueofsupport;
    }

    public String getElpfbat() {
        return elpfbat;
    }

    public void setElpfbat(String elpfbat) {
        this.elpfbat = elpfbat;
    }

    public String getCostInvolved() {
        return costInvolved;
    }

    public void setCostInvolved(String costInvolved) {
        this.costInvolved = costInvolved;
    }

    public String getContrifrompf() {
        return contrifrompf;
    }

    public void setContrifrompf(String contrifrompf) {
        this.contrifrompf = contrifrompf;
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

    public String getIfcs() {
        return ifcs;
    }

    public void setIfcs(String ifcs) {
        this.ifcs = ifcs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenBal() {
        return openBal;
    }

    public void setOpenBal(String openBal) {
        this.openBal = openBal;
    }

    public String getLoanInYr() {
        return loanInYr;
    }

    public void setLoanInYr(String loanInYr) {
        this.loanInYr = loanInYr;
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

    public String getTotOwn() {
        return totOwn;
    }

    public void setTotOwn(String totOwn) {
        this.totOwn = totOwn;
    }

    public String getTotAll() {
        return totAll;
    }

    public void setTotAll(String totAll) {
        this.totAll = totAll;
    }

    public String getAplRecDate() {
        return aplRecDate;
    }

    public void setAplRecDate(String aplRecDate) {
        this.aplRecDate = aplRecDate;
    }

    public String getMemContPay() {
        return memContPay;
    }

    public void setMemContPay(String memContPay) {
        this.memContPay = memContPay;
    }

    public String getCompContPay() {
        return compContPay;
    }

    public void setCompContPay(String compContPay) {
        this.compContPay = compContPay;
    }

    public String getEpfContPay() {
        return epfContPay;
    }

    public void setEpfContPay(String epfContPay) {
        this.epfContPay = epfContPay;
    }

    public String getTotPay() {
        return totPay;
    }

    public void setTotPay(String totPay) {
        this.totPay = totPay;
    }

    public String getPaymet() {
        return paymet;
    }

    public void setPaymet(String paymet) {
        this.paymet = paymet;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getChqnum() {
        return chqnum;
    }

    public void setChqnum(String chqnum) {
        this.chqnum = chqnum;
    }

    public String getChqdate() {
        return chqdate;
    }

    public void setChqdate(String chqdate) {
        this.chqdate = chqdate;
    }

    public String getPfbank() {
        return pfbank;
    }

    public void setPfbank(String pfbank) {
        this.pfbank = pfbank;
    }

    public String getFinInt() {
        return finInt;
    }

    public void setFinInt(String finInt) {
        this.finInt = finInt;
    }

    public String getFinInBr() {
        return finInBr;
    }

    public void setFinInBr(String finInBr) {
        this.finInBr = finInBr;
    }

    public String getFinInAcno() {
        return finInAcno;
    }

    public void setFinInAcno(String finInAcno) {
        this.finInAcno = finInAcno;
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
