package com.coreintegra.pftrust.dtos.pdf.transferout;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@XmlRootElement(name = "annuxurek")
public class AnnuxureK {

    private String unitCode;
    private String serialNumber;
    @XmlElement(name = "payeeName")

    public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	private String documentNumber;

    private String fromAccount;
    private String toAccount;

    private String transferOutType;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String payeeName;

    private String currentAddressLine1;
    private String currentAddressLine2;
    private String currentAddressLine3;
    private String currentAddressLine4;
    private String currentEmployerName;
    
    
    
    private String nameOfSubscriber;

    private Date yearOpeningDate;

    private BigDecimal yearOpeningEmployeeContribution;
    private BigDecimal yearOpeningEmployerContribution;
    private BigDecimal yearOpeningVoluntaryContribution;

    private BigDecimal employeeContribution;
    private BigDecimal employeeContributionTransferIn;
    private BigDecimal interestOnEmployeeContribution;

    private BigDecimal employerContribution;
    private BigDecimal employerContributionTransferIn;
    private BigDecimal interestOnEmployerContribution;

    private BigDecimal voluntaryContribution;
    private BigDecimal interestOnVoluntaryContribution;

    private BigDecimal totalContribution;

    private BigDecimal loanWithdrawals;

    private BigDecimal netCredit;

    private Date dueDate;

    private Date dateOfJoiningService;

    private Date dateOfJoiningPf;

    private String epsNumber;

    private Date dateOfLeavingService;

    private Date dateOfJoiningPreviousEmployer;

    private String createdBy;

    @XmlElement(name = "unitCode")
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @XmlElement(name = "serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @XmlElement(name = "documentNumber")
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @XmlElement(name = "fromAccountNumber")
    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    @XmlElement(name = "toAccountNumber")
    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    @XmlElement(name = "transferOutType")
    public String getTransferOutType() {
        return transferOutType;
    }

    public void setTransferOutType(String transferOutType) {
        this.transferOutType = transferOutType;
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

    @XmlElement(name = "subscriber")
    public String getNameOfSubscriber() {
        return nameOfSubscriber;
    }

    public void setNameOfSubscriber(String nameOfSubscriber) {
        this.nameOfSubscriber = nameOfSubscriber;
    }

    @XmlElement(name = "yearOpeningDate")
    public String getYearOpeningDate() {
        return fomateDate(yearOpeningDate);
    }

    public void setYearOpeningDate(Date yearOpeningDate) {
        this.yearOpeningDate = yearOpeningDate;
    }

    @XmlElement(name = "yearOpeningEmployeeContribution")
    public String getYearOpeningEmployeeContribution() {
        return formateNumbers(yearOpeningEmployeeContribution);
    }

    public void setYearOpeningEmployeeContribution(BigDecimal yearOpeningEmployeeContribution) {
        this.yearOpeningEmployeeContribution = yearOpeningEmployeeContribution;
    }

    @XmlElement(name = "yearOpeningEmployerContribution")
    public String getYearOpeningEmployerContribution() {
        return formateNumbers(yearOpeningEmployerContribution);
    }

    public void setYearOpeningEmployerContribution(BigDecimal yearOpeningEmployerContribution) {
        this.yearOpeningEmployerContribution = yearOpeningEmployerContribution;
    }

    @XmlElement(name = "yearOpeningVoluntaryContribution")
    public String getYearOpeningVoluntaryContribution() {
        return formateNumbers(yearOpeningVoluntaryContribution);
    }

    public void setYearOpeningVoluntaryContribution(BigDecimal yearOpeningVoluntaryContribution) {
        this.yearOpeningVoluntaryContribution = yearOpeningVoluntaryContribution;
    }

    @XmlElement(name = "employeeContribution")
    public String getEmployeeContribution() {
        return formateNumbers(employeeContribution);
    }

    public void setEmployeeContribution(BigDecimal employeeContribution) {
        this.employeeContribution = employeeContribution;
    }

    @XmlElement(name = "employeeContributionTransferIn")
    public String getEmployeeContributionTransferIn() {
        return formateNumbers(employeeContributionTransferIn);
    }

    public void setEmployeeContributionTransferIn(BigDecimal employeeContributionTransferIn) {
        this.employeeContributionTransferIn = employeeContributionTransferIn;
    }

    @XmlElement(name = "interestOnEmployeeContribution")
    public String getInterestOnEmployeeContribution() {
        return formateNumbers(interestOnEmployeeContribution);
    }

    public void setInterestOnEmployeeContribution(BigDecimal interestOnEmployeeContribution) {
        this.interestOnEmployeeContribution = interestOnEmployeeContribution;
    }

    @XmlElement(name = "employerContribution")
    public String getEmployerContribution() {
        return formateNumbers(employerContribution);
    }

    public void setEmployerContribution(BigDecimal employerContribution) {
        this.employerContribution = employerContribution;
    }

    @XmlElement(name = "employerContributionTransferIn")
    public String getEmployerContributionTransferIn() {
        return formateNumbers(employerContributionTransferIn);
    }

    public void setEmployerContributionTransferIn(BigDecimal employerContributionTransferIn) {
        this.employerContributionTransferIn = employerContributionTransferIn;
    }

    @XmlElement(name = "interestOnEmployerContribution")
    public String getInterestOnEmployerContribution() {
        return formateNumbers(interestOnEmployerContribution);
    }

    public void setInterestOnEmployerContribution(BigDecimal interestOnEmployerContribution) {
        this.interestOnEmployerContribution = interestOnEmployerContribution;
    }

    @XmlElement(name = "voluntaryContribution")
    public String getVoluntaryContribution() {
        return formateNumbers(voluntaryContribution);
    }

    public void setVoluntaryContribution(BigDecimal voluntaryContribution) {
        this.voluntaryContribution = voluntaryContribution;
    }

    @XmlElement(name = "interestOnVoluntaryContribution")
    public String getInterestOnVoluntaryContribution() {
        return formateNumbers(interestOnVoluntaryContribution);
    }

    public void setInterestOnVoluntaryContribution(BigDecimal interestOnVoluntaryContribution) {
        this.interestOnVoluntaryContribution = interestOnVoluntaryContribution;
    }

    @XmlElement(name = "totalContribution")
    public String getTotalContribution() {
        return formateNumbers(totalContribution);
    }

    public void setTotalContribution(BigDecimal totalContribution) {
        this.totalContribution = totalContribution;
    }

    @XmlElement(name = "loanWithdrawals")
    public String getLoanWithdrawals() {
        return formateNumbers(loanWithdrawals);
    }

    public void setLoanWithdrawals(BigDecimal loanWithdrawals) {
        this.loanWithdrawals = loanWithdrawals;
    }

    @XmlElement(name = "netCredit")
    public String getNetCredit() {

        return formateNumbers(netCredit);

    }


    public void setNetCredit(BigDecimal netCredit) {
        this.netCredit = netCredit;
    }

    @XmlElement(name = "dueDate")
    public String getDueDate() {
        return fomateDate(dueDate);
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @XmlElement(name = "dateOfJoiningService")
    public String getDateOfJoiningService() {
        return fomateDate(dateOfJoiningService);
    }

    public void setDateOfJoiningService(Date dateOfJoiningService) {
        this.dateOfJoiningService = dateOfJoiningService;
    }

    @XmlElement(name = "dateOfJoiningPf")
    public String getDateOfJoiningPf() {
        return fomateDate(dateOfJoiningPf);
    }

    public void setDateOfJoiningPf(Date dateOfJoiningPf) {
        this.dateOfJoiningPf = dateOfJoiningPf;
    }

    @XmlElement(name = "epsNumber")
    public String getEpsNumber() {
        return epsNumber;
    }

    public void setEpsNumber(String epsNumber) {
        this.epsNumber = epsNumber;
    }

    @XmlElement(name = "dateOfLeavingService")
    public String getDateOfLeavingService() {
        return fomateDate(dateOfLeavingService);
    }

    public void setDateOfLeavingService(Date dateOfLeavingService) {
        this.dateOfLeavingService = dateOfLeavingService;
    }

    @XmlElement(name = "dateOfJoiningPreviousEmployer")
    public String getDateOfJoiningPreviousEmployer() {
        return fomateDate(dateOfJoiningPreviousEmployer);
    }

    @XmlElement(name = "createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setDateOfJoiningPreviousEmployer(Date dateOfJoiningPreviousEmployer) {
        this.dateOfJoiningPreviousEmployer = dateOfJoiningPreviousEmployer;
    }

    @XmlElement(name = "currentAddressLine1")
    public String getCurrentAddressLine1() {
		return currentAddressLine1;
	}

	public void setCurrentAddressLine1(String currentAddressLine1) {
		this.currentAddressLine1 = currentAddressLine1;
	}
	@XmlElement(name = "currentAddressLine2")
	public String getCurrentAddressLine2() {
		return currentAddressLine2;
	}

	public void setCurrentAddressLine2(String currentAddressLine2) {
		this.currentAddressLine2 = currentAddressLine2;
	}
	@XmlElement(name = "currentAddressLine3")
	public String getCurrentAddressLine3() {
		return currentAddressLine3;
	}

	public void setCurrentAddressLine3(String currentAddressLine3) {
		this.currentAddressLine3 = currentAddressLine3;
	}
	@XmlElement(name = "currentAddressLine4")
	public String getCurrentAddressLine4() {
		return currentAddressLine4;
	}

	public void setCurrentAddressLine4(String currentAddressLine4) {
		this.currentAddressLine4 = currentAddressLine4;
	}
	@XmlElement(name = "currentEmployerName")
	public String getCurrentEmployerName() {
		return currentEmployerName;
	}

	public void setCurrentEmployerName(String currentEmployerName) {
		this.currentEmployerName = currentEmployerName;
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
