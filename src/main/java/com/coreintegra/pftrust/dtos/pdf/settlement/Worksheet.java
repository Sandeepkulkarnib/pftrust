package com.coreintegra.pftrust.dtos.pdf.settlement;

import com.coreintegra.pftrust.dtos.pdf.Nominee;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@XmlRootElement(name = "workSheet")
public class Worksheet {

    private String settlementNo;
    private Date dateOfCessation;
    private String documentNo;

    private String name;
    private String pernNumber;
    private String pfNumber;
  

	private String tokenNumber;
    private String unitCode;
    private String deptCode;

    private String status;

    private Date dateOfBirth;
    private Date dateOfJoining;
    private Date dateOfJoiningPf;
    private Date dateOfJoiningPrior;
    private Date lastRecoverDate;

    private List<Nominee> nominees;

    private Date yearOpeningDate;

    private Float interestRate;

//    year opening
    private BigDecimal yearOpeningMemberContribution;
    private BigDecimal yearOpeningCompanyContribution;
    private BigDecimal yearOpeningVpfContribution;
    private BigDecimal yearOpeningTotalContribution;

    private BigDecimal interestOnYearOpeningMemberContribution;
    private BigDecimal interestOnYearOpeningCompanyContribution;
    private BigDecimal interestOnYearOpeningVpfContribution;
    private BigDecimal interestOnYearOpeningTotalContribution;

//    current year
    private BigDecimal currentYearMemberContribution;
    private BigDecimal currentYearCompanyContribution;
    private BigDecimal currentYearVpfContribution;
    private BigDecimal currentYearTotalContribution;

    private BigDecimal interestOnCurrentYearMemberContribution;
    private BigDecimal interestOnCurrentYearCompanyContribution;
    private BigDecimal interestOnCurrentYearVpfContribution;
    private BigDecimal interestOnCurrentYearTotalContribution;

//    transfer in
    private BigDecimal currentYearTransferInMemberContribution;
    private BigDecimal currentYearTransferInCompanyContribution;
    private BigDecimal currentYearTransferInVpfContribution;
    private BigDecimal currentYearTransferInTotalContribution;

    private BigDecimal interestOnCurrentYearTransferInMemberContribution;
    private BigDecimal interestOnCurrentYearTransferInCompanyContribution;
    private BigDecimal interestOnCurrentYearTransferInVpfContribution;
    private BigDecimal interestOnCurrentYearTransferInTotalContribution;

    //    transfer in
    private BigDecimal currentYearLoanWithdrawalMemberContribution;
    private BigDecimal currentYearLoanWithdrawalCompanyContribution;
    private BigDecimal currentYearLoanWithdrawalVpfContribution;
    private BigDecimal currentYearLoanWithdrawalTotalContribution;

    private BigDecimal interestOnCurrentYearLoanWithdrawalMemberContribution;
    private BigDecimal interestOnCurrentYearLoanWithdrawalCompanyContribution;
    private BigDecimal interestOnCurrentYearLoanWithdrawalVpfContribution;
    private BigDecimal interestOnCurrentYearLoanWithdrawalTotalContribution;

    private Date dueDate;
    private Date processedOnDate;

//    total contribution
    private BigDecimal totalMemberContribution;
    private BigDecimal totalCompanyContribution;
    private BigDecimal totalVpfContribution;
    private BigDecimal totalContribution;

    //    tax
    private BigDecimal incomeTax;
    private BigDecimal eduCess;
    private BigDecimal tds;

    private BigDecimal netContribution;


    private SettlementAnnexure settlementAnnexure;


    @XmlElement(name = "settlementNumber")
    public String getSettlementNo() {
        return settlementNo;
    }

    public void setSettlementNo(String settlementNo) {
        this.settlementNo = settlementNo;
    }
    @XmlElement(name = "dateOfJoiningPf")
    public String getDateOfJoiningPf() {
		return fomateDate(dateOfJoiningPf);
	}

	public void setDateOfJoiningPf(Date dateOfJoiningPf) {
		this.dateOfJoiningPf = dateOfJoiningPf;
	}

	@XmlElement(name = "dateOfCessation")
    public String getDateOfCessation() {
        return fomateDate(dateOfCessation);
    }

    public void setDateOfCessation(Date dateOfCessation) {
        this.dateOfCessation = dateOfCessation;
    }

    @XmlElement(name = "documentNo")
    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "pernNumber")
    public String getPernNumber() {
        return pernNumber;
    }

    public void setPernNumber(String pernNumber) {
        this.pernNumber = pernNumber;
    }

    @XmlElement(name = "pfNumber")
    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    @XmlElement(name = "unitCode")
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @XmlElement(name = "tokenNumber")
    public String getTokenNumber() {
		return tokenNumber;
	}

	public void setTokenNumber(String tokenNumber) {
		this.tokenNumber = tokenNumber;
	}
	
    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "dateOfBirth")
    public String getDateOfBirth() {
        return fomateDate(dateOfBirth);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    @XmlElement(name = "deptCode")
    public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@XmlElement(name = "dateOfJoining")
    public String getDateOfJoining() {
        return fomateDate(dateOfJoining);
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @XmlElement(name = "dateOfJoiningPrior")
    public String getDateOfJoiningPrior() {
        return fomateDate(dateOfJoiningPrior);
    }

    public void setDateOfJoiningPrior(Date dateOfJoiningPrior) {
        this.dateOfJoiningPrior = dateOfJoiningPrior;
    }

    @XmlElement(name = "lastRecoveryDate")
    public String getLastRecoverDate() {
        return fomateDate(lastRecoverDate);
    }

    public void setLastRecoverDate(Date lastRecoverDate) {
        this.lastRecoverDate = lastRecoverDate;
    }

    @XmlElement(name = "nominee")
    public List<Nominee> getNominees() {
        return nominees;
    }

    public void setNominees(List<Nominee> nominees) {
        this.nominees = nominees;
    }

    @XmlElement(name = "yearOpeningDate")
    public String getYearOpeningDate() {
        return fomateDate(yearOpeningDate);
    }

    public void setYearOpeningDate(Date yearOpeningDate) {
        this.yearOpeningDate = yearOpeningDate;
    }

    @XmlElement(name = "interestRate")
    public Float getInterestRate() {
        return interestRate;
    }


    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    @XmlElement(name = "yearOpeningMemberContribution")
    public String getYearOpeningMemberContribution() {
        return formateNumbers(yearOpeningMemberContribution);
    }

    public void setYearOpeningMemberContribution(BigDecimal yearOpeningMemberContribution) {
        this.yearOpeningMemberContribution = yearOpeningMemberContribution;
    }

    @XmlElement(name = "yearOpeningCompanyContribution")
    public String getYearOpeningCompanyContribution() {
        return formateNumbers(yearOpeningCompanyContribution);
    }

    public void setYearOpeningCompanyContribution(BigDecimal yearOpeningCompanyContribution) {
        this.yearOpeningCompanyContribution = yearOpeningCompanyContribution;
    }

    @XmlElement(name = "yearOpeningVpfContribution")
    public String getYearOpeningVpfContribution() {
        return formateNumbers(yearOpeningVpfContribution);
    }

    public void setYearOpeningVpfContribution(BigDecimal yearOpeningVpfContribution) {
        this.yearOpeningVpfContribution = yearOpeningVpfContribution;
    }

    @XmlElement(name = "yearOpeningTotalContribution")
    public String getYearOpeningTotalContribution() {
        return formateNumbers(yearOpeningTotalContribution);
    }

    public void setYearOpeningTotalContribution(BigDecimal yearOpeningTotalContribution) {
        this.yearOpeningTotalContribution = yearOpeningTotalContribution;
    }

    @XmlElement(name = "interestOnYearOpeningMemberContribution")
    public String getInterestOnYearOpeningMemberContribution() {
        return formateNumbers(interestOnYearOpeningMemberContribution);
    }

    public void setInterestOnYearOpeningMemberContribution(BigDecimal interestOnYearOpeningMemberContribution) {
        this.interestOnYearOpeningMemberContribution = interestOnYearOpeningMemberContribution;
    }

    @XmlElement(name = "interestOnYearOpeningCompanyContribution")
    public String getInterestOnYearOpeningCompanyContribution() {
        return formateNumbers(interestOnYearOpeningCompanyContribution);
    }

    public void setInterestOnYearOpeningCompanyContribution(BigDecimal interestOnYearOpeningCompanyContribution) {
        this.interestOnYearOpeningCompanyContribution = interestOnYearOpeningCompanyContribution;
    }

    @XmlElement(name = "interestOnYearOpeningVpfContribution")
    public String getInterestOnYearOpeningVpfContribution() {
        return formateNumbers(interestOnYearOpeningVpfContribution);
    }

    public void setInterestOnYearOpeningVpfContribution(BigDecimal interestOnYearOpeningVpfContribution) {
        this.interestOnYearOpeningVpfContribution = interestOnYearOpeningVpfContribution;
    }

    @XmlElement(name = "interestOnYearOpeningTotalContribution")
    public String getInterestOnYearOpeningTotalContribution() {
        return formateNumbers(interestOnYearOpeningTotalContribution);
    }

    public void setInterestOnYearOpeningTotalContribution(BigDecimal interestOnYearOpeningTotalContribution) {
        this.interestOnYearOpeningTotalContribution = interestOnYearOpeningTotalContribution;
    }

    @XmlElement(name = "currentYearMemberContribution")
    public String getCurrentYearMemberContribution() {
        return formateNumbers(currentYearMemberContribution);
    }

    public void setCurrentYearMemberContribution(BigDecimal currentYearMemberContribution) {
        this.currentYearMemberContribution = currentYearMemberContribution;
    }

    @XmlElement(name = "currentYearCompanyContribution")
    public String getCurrentYearCompanyContribution() {
        return formateNumbers(currentYearCompanyContribution);
    }

    public void setCurrentYearCompanyContribution(BigDecimal currentYearCompanyContribution) {
        this.currentYearCompanyContribution = currentYearCompanyContribution;
    }

    @XmlElement(name = "currentYearVpfContribution")
    public String getCurrentYearVpfContribution() {
        return formateNumbers(currentYearVpfContribution);
    }

    public void setCurrentYearVpfContribution(BigDecimal currentYearVpfContribution) {
        this.currentYearVpfContribution = currentYearVpfContribution;
    }

    @XmlElement(name = "currentYearTotalContribution")
    public String getCurrentYearTotalContribution() {
        return formateNumbers(currentYearTotalContribution);
    }

    public void setCurrentYearTotalContribution(BigDecimal currentYearTotalContribution) {
        this.currentYearTotalContribution = currentYearTotalContribution;
    }

    @XmlElement(name = "interestOnCurrentYearMemberContribution")
    public String getInterestOnCurrentYearMemberContribution() {
        return formateNumbers(interestOnCurrentYearMemberContribution);
    }

    public void setInterestOnCurrentYearMemberContribution(BigDecimal interestOnCurrentYearMemberContribution) {
        this.interestOnCurrentYearMemberContribution = interestOnCurrentYearMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearCompanyContribution")
    public String getInterestOnCurrentYearCompanyContribution() {
        return formateNumbers(interestOnCurrentYearCompanyContribution);
    }

    public void setInterestOnCurrentYearCompanyContribution(BigDecimal interestOnCurrentYearCompanyContribution) {
        this.interestOnCurrentYearCompanyContribution = interestOnCurrentYearCompanyContribution;
    }

    @XmlElement(name = "interestOnCurrentYearVpfContribution")
    public String getInterestOnCurrentYearVpfContribution() {
        return formateNumbers(interestOnCurrentYearVpfContribution);
    }

    public void setInterestOnCurrentYearVpfContribution(BigDecimal interestOnCurrentYearVpfContribution) {
        this.interestOnCurrentYearVpfContribution = interestOnCurrentYearVpfContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTotalContribution")
    public String getInterestOnCurrentYearTotalContribution() {
        return formateNumbers(interestOnCurrentYearTotalContribution);
    }

    public void setInterestOnCurrentYearTotalContribution(BigDecimal interestOnCurrentYearTotalContribution) {
        this.interestOnCurrentYearTotalContribution = interestOnCurrentYearTotalContribution;
    }

    @XmlElement(name = "currentYearTransferInMemberContribution")
    public String getCurrentYearTransferInMemberContribution() {
        return formateNumbers(currentYearTransferInMemberContribution);
    }

    public void setCurrentYearTransferInMemberContribution(BigDecimal currentYearTransferInMemberContribution) {
        this.currentYearTransferInMemberContribution = currentYearTransferInMemberContribution;
    }

    @XmlElement(name = "currentYearTransferInCompanyContribution")
    public String getCurrentYearTransferInCompanyContribution() {
        return formateNumbers(currentYearTransferInCompanyContribution);
    }

    public void setCurrentYearTransferInCompanyContribution(BigDecimal currentYearTransferInCompanyContribution) {
        this.currentYearTransferInCompanyContribution = currentYearTransferInCompanyContribution;
    }

    @XmlElement(name = "currentYearTransferInVpfContribution")
    public String getCurrentYearTransferInVpfContribution() {
        return formateNumbers(currentYearTransferInVpfContribution);
    }

    public void setCurrentYearTransferInVpfContribution(BigDecimal currentYearTransferInVpfContribution) {
        this.currentYearTransferInVpfContribution = currentYearTransferInVpfContribution;
    }

    @XmlElement(name = "currentYearTransferInTotalContribution")
    public String getCurrentYearTransferInTotalContribution() {
        return formateNumbers(currentYearTransferInTotalContribution);
    }

    public void setCurrentYearTransferInTotalContribution(BigDecimal currentYearTransferInTotalContribution) {
        this.currentYearTransferInTotalContribution = currentYearTransferInTotalContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInMemberContribution")
    public String getInterestOnCurrentYearTransferInMemberContribution() {
        return formateNumbers(interestOnCurrentYearTransferInMemberContribution);
    }

    public void setInterestOnCurrentYearTransferInMemberContribution(BigDecimal interestOnCurrentYearTransferInMemberContribution) {
        this.interestOnCurrentYearTransferInMemberContribution = interestOnCurrentYearTransferInMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInCompanyContribution")
    public String getInterestOnCurrentYearTransferInCompanyContribution() {
        return formateNumbers(interestOnCurrentYearTransferInCompanyContribution);
    }

    public void setInterestOnCurrentYearTransferInCompanyContribution(BigDecimal interestOnCurrentYearTransferInCompanyContribution) {
        this.interestOnCurrentYearTransferInCompanyContribution = interestOnCurrentYearTransferInCompanyContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInVpfContribution")
    public String getInterestOnCurrentYearTransferInVpfContribution() {
        return formateNumbers(interestOnCurrentYearTransferInVpfContribution);
    }

    public void setInterestOnCurrentYearTransferInVpfContribution(BigDecimal interestOnCurrentYearTransferInVpfContribution) {
        this.interestOnCurrentYearTransferInVpfContribution = interestOnCurrentYearTransferInVpfContribution;
    }

    @XmlElement(name = "interestOnCurrentYearTransferInTotalContribution")
    public String getInterestOnCurrentYearTransferInTotalContribution() {
        return formateNumbers(interestOnCurrentYearTransferInTotalContribution);
    }

    public void setInterestOnCurrentYearTransferInTotalContribution(BigDecimal interestOnCurrentYearTransferInTotalContribution) {
        this.interestOnCurrentYearTransferInTotalContribution = interestOnCurrentYearTransferInTotalContribution;
    }

    @XmlElement(name = "currentYearLoanWithdrawalMemberContribution")
    public String getCurrentYearLoanWithdrawalMemberContribution() {
        return formateNumbers(currentYearLoanWithdrawalMemberContribution);
    }

    public void setCurrentYearLoanWithdrawalMemberContribution(BigDecimal currentYearLoanWithdrawalMemberContribution) {
        this.currentYearLoanWithdrawalMemberContribution = currentYearLoanWithdrawalMemberContribution;
    }

    @XmlElement(name = "currentYearLoanWithdrawalCompanyContribution")
    public String getCurrentYearLoanWithdrawalCompanyContribution() {
        return formateNumbers(currentYearLoanWithdrawalCompanyContribution);
    }

    public void setCurrentYearLoanWithdrawalCompanyContribution(BigDecimal currentYearLoanWithdrawalCompanyContribution) {
        this.currentYearLoanWithdrawalCompanyContribution = currentYearLoanWithdrawalCompanyContribution;
    }

    @XmlElement(name = "currentYearLoanWithdrawalVpfContribution")
    public String getCurrentYearLoanWithdrawalVpfContribution() {
        return formateNumbers(currentYearLoanWithdrawalVpfContribution);
    }

    public void setCurrentYearLoanWithdrawalVpfContribution(BigDecimal currentYearLoanWithdrawalVpfContribution) {
        this.currentYearLoanWithdrawalVpfContribution = currentYearLoanWithdrawalVpfContribution;
    }

    @XmlElement(name = "currentYearLoanWithdrawalTotalContribution")
    public String getCurrentYearLoanWithdrawalTotalContribution() {
        return formateNumbers(currentYearLoanWithdrawalTotalContribution);
    }

    public void setCurrentYearLoanWithdrawalTotalContribution(BigDecimal currentYearLoanWithdrawalTotalContribution) {
        this.currentYearLoanWithdrawalTotalContribution = currentYearLoanWithdrawalTotalContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalMemberContribution")
    public String getInterestOnCurrentYearLoanWithdrawalMemberContribution() {
        return formateNumbers(interestOnCurrentYearLoanWithdrawalMemberContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalMemberContribution(BigDecimal interestOnCurrentYearLoanWithdrawalMemberContribution) {
        this.interestOnCurrentYearLoanWithdrawalMemberContribution = interestOnCurrentYearLoanWithdrawalMemberContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalCompanyContribution")
    public String getInterestOnCurrentYearLoanWithdrawalCompanyContribution() {
        return formateNumbers(interestOnCurrentYearLoanWithdrawalCompanyContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalCompanyContribution(BigDecimal interestOnCurrentYearLoanWithdrawalCompanyContribution) {
        this.interestOnCurrentYearLoanWithdrawalCompanyContribution = interestOnCurrentYearLoanWithdrawalCompanyContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalVpfContribution")
    public String getInterestOnCurrentYearLoanWithdrawalVpfContribution() {
        return formateNumbers(interestOnCurrentYearLoanWithdrawalVpfContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalVpfContribution(BigDecimal interestOnCurrentYearLoanWithdrawalVpfContribution) {
        this.interestOnCurrentYearLoanWithdrawalVpfContribution = interestOnCurrentYearLoanWithdrawalVpfContribution;
    }

    @XmlElement(name = "interestOnCurrentYearLoanWithdrawalTotalContribution")
    public String getInterestOnCurrentYearLoanWithdrawalTotalContribution() {
        return formateNumbers(interestOnCurrentYearLoanWithdrawalTotalContribution);
    }

    public void setInterestOnCurrentYearLoanWithdrawalTotalContribution(BigDecimal interestOnCurrentYearLoanWithdrawalTotalContribution) {
        this.interestOnCurrentYearLoanWithdrawalTotalContribution = interestOnCurrentYearLoanWithdrawalTotalContribution;
    }

    @XmlElement(name = "dueDate")
    public String getDueDate() {
        return fomateDate(dueDate);
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @XmlElement(name = "processedOnDate")
    public String getProcessedOnDate() {
        return fomateDate(processedOnDate);
    }

    public void setProcessedOnDate(Date processedOnDate) {
        this.processedOnDate = processedOnDate;
    }

    @XmlElement(name = "totalMemberContribution")
    public String getTotalMemberContribution() {
        return formateNumbers(totalMemberContribution);
    }

    public void setTotalMemberContribution(BigDecimal totalMemberContribution) {
        this.totalMemberContribution = totalMemberContribution;
    }

    @XmlElement(name = "totalCompanyContribution")
    public String getTotalCompanyContribution() {
        return formateNumbers(totalCompanyContribution);
    }

    public void setTotalCompanyContribution(BigDecimal totalCompanyContribution) {
        this.totalCompanyContribution = totalCompanyContribution;
    }

    @XmlElement(name = "totalVpfContribution")
    public String getTotalVpfContribution() {
        return formateNumbers(totalVpfContribution);
    }

    public void setTotalVpfContribution(BigDecimal totalVpfContribution) {
        this.totalVpfContribution = totalVpfContribution;
    }

    @XmlElement(name = "totalContribution")
    public String getTotalContribution() {
        return formateNumbers(totalContribution);
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    @XmlElement(name = "netContribution")
    public String getNetContribution() {
        return formateNumbers(netContribution);
    }

    public void setNetContribution(BigDecimal netContribution) {
        this.netContribution = netContribution;
    }

    @XmlElement(name = "incomeTax")
    public String getIncomeTax() {
        return formateNumbers(incomeTax);
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    @XmlElement(name = "eduCess")
    public String getEduCess() {
        return formateNumbers(eduCess);
    }

    public void setEduCess(BigDecimal eduCess) {
        this.eduCess = eduCess;
    }

    @XmlElement(name = "tds")
    public String getTds() {
        return formateNumbers(tds);
    }

    public void setTds(BigDecimal tds) {
        this.tds = tds;
    }

    @XmlElement(name = "settlementAnnexure")
    public SettlementAnnexure getSettlementAnnexure() {
        return settlementAnnexure;
    }

    public void setSettlementAnnexure(SettlementAnnexure settlementAnnexure) {
        this.settlementAnnexure = settlementAnnexure;
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

    private String formateNumbers(BigDecimal bigDecimal){

        if (bigDecimal == null || bigDecimal.doubleValue() <= 1){
            return "0.00";
        }

        NumberFormat formatter = new DecimalFormat("##,##,###.00");
        return formatter.format(bigDecimal);
    }



}
