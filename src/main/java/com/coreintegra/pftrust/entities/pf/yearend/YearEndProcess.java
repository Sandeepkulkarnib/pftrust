package com.coreintegra.pftrust.entities.pf.yearend;

import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.util.UUID;

import static com.coreintegra.pftrust.util.DataUtil.avoidNull;

@Entity
public class YearEndProcess extends BaseAuditingEntity {

    public static final String PROCESS_FOR_LOAN = "PROCESS_FOR_LOAN";
    public static final String PROCESS_FOR_SETTLEMENT = "PROCESS_FOR_SETTLEMENT";
    public static final String PROCESS_FOR_YEAR_END = "PROCESS_FOR_YEAR_END";

    public static final String ENTITY_LOAN = "ENTITY_LOAN";
    public static final String ENTITY_CONTRIBUTION = "ENTITY_CONTRIBUTION";
    public static final String ENTITY_TRANSFER_IN = "ENTITY_TRANSFER_IN";

    public static final String PROCESS_RUN_FOR_EMPLOYEE = "PROCESS_RUN_FOR_EMPLOYEE";
    public static final String PROCESS_RUN_FOR_UNIT_CODE = "PROCESS_RUN_FOR_UNIT_CODE";
    public static final String PROCESS_RUN_FOR_ALL = "PROCESS_RUN_FOR_ALL";


    private BigDecimal nonTaxableMemberContribution;
    private BigDecimal taxableMemberContribution;
    private BigDecimal memberContribution;

    private BigDecimal companyContribution;

    private BigDecimal nonTaxableVpfContribution;
    private BigDecimal taxableVpfContribution;
    private BigDecimal vpfContribution;

    private BigDecimal interestOnNonTaxableMemberContribution;
    private BigDecimal interestOnTaxableMemberContribution;
    private BigDecimal interestOnMemberContribution;
    private BigDecimal tdsOnTaxableMemberContribution;

    private BigDecimal interestOnCompanyContribution;

    private BigDecimal interestOnNonTaxableVpfContribution;
    private BigDecimal interestOnTaxableVpfContribution;
    private BigDecimal interestOnVpfContribution;
    private BigDecimal tdsOnTaxableVpfContribution;


    private Integer year;
    private Integer month;

    private BigDecimal interestRate;
    private BigDecimal monthlyInterestRate;
    private Integer period;

    private String entityType;

    private String processType;

    @ManyToOne
    private Contribution contribution;

    @ManyToOne
    private Loan loan;

    @ManyToOne
    private TransferIn transferIn;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Job job;

    private Boolean isPublished;

    @PrePersist
    public void prePersist(){
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public BigDecimal getMemberContribution() {
        return memberContribution;
    }

    public void setMemberContribution(BigDecimal memberContribution) {
        this.memberContribution = memberContribution;
    }

    public BigDecimal getCompanyContribution() {
        return companyContribution;
    }

    public void setCompanyContribution(BigDecimal companyContribution) {
        this.companyContribution = companyContribution;
    }

    public BigDecimal getVpfContribution() {
        return vpfContribution;
    }

    public void setVpfContribution(BigDecimal vpfContribution) {
        this.vpfContribution = vpfContribution;
    }

    public BigDecimal getInterestOnMemberContribution() {
        return interestOnMemberContribution;
    }

    public void setInterestOnMemberContribution(BigDecimal interestOnMemberContribution) {
        this.interestOnMemberContribution = interestOnMemberContribution;
    }

    public BigDecimal getInterestOnCompanyContribution() {
        return interestOnCompanyContribution;
    }

    public void setInterestOnCompanyContribution(BigDecimal interestOnCompanyContribution) {
        this.interestOnCompanyContribution = interestOnCompanyContribution;
    }

    public BigDecimal getInterestOnVpfContribution() {
        return interestOnVpfContribution;
    }

    public void setInterestOnVpfContribution(BigDecimal interestOnVpfContribution) {
        this.interestOnVpfContribution = interestOnVpfContribution;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(BigDecimal monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Contribution getContribution() {
        return contribution;
    }

    public void setContribution(Contribution contribution) {
        this.contribution = contribution;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public TransferIn getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(TransferIn transferIn) {
        this.transferIn = transferIn;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public BigDecimal getTotalContribution(){
        return avoidNull(nonTaxableMemberContribution)
                .add(avoidNull(taxableMemberContribution))
                .add(avoidNull(companyContribution))
                .add(avoidNull(nonTaxableVpfContribution))
                .add(avoidNull(taxableVpfContribution));
    }

    public BigDecimal getTotalInterest(){
        return avoidNull(interestOnNonTaxableMemberContribution)
                .add(avoidNull(interestOnTaxableMemberContribution))
                .add(avoidNull(interestOnCompanyContribution))
                .add(avoidNull(interestOnNonTaxableVpfContribution))
                .add(avoidNull(interestOnTaxableVpfContribution));
    }

    public BigDecimal getNonTaxableMemberContribution() {
        return nonTaxableMemberContribution;
    }

    public void setNonTaxableMemberContribution(BigDecimal nonTaxableMemberContribution) {
        this.nonTaxableMemberContribution = nonTaxableMemberContribution;
    }

    public BigDecimal getTaxableMemberContribution() {
        return taxableMemberContribution;
    }

    public void setTaxableMemberContribution(BigDecimal taxableMemberContribution) {
        this.taxableMemberContribution = taxableMemberContribution;
    }

    public BigDecimal getNonTaxableVpfContribution() {
        return nonTaxableVpfContribution;
    }

    public void setNonTaxableVpfContribution(BigDecimal nonTaxableVpfContribution) {
        this.nonTaxableVpfContribution = nonTaxableVpfContribution;
    }

    public BigDecimal getTaxableVpfContribution() {
        return taxableVpfContribution;
    }

    public void setTaxableVpfContribution(BigDecimal taxableVpfContribution) {
        this.taxableVpfContribution = taxableVpfContribution;
    }

    public BigDecimal getInterestOnNonTaxableMemberContribution() {
        return interestOnNonTaxableMemberContribution;
    }

    public void setInterestOnNonTaxableMemberContribution(BigDecimal interestOnNonTaxableMemberContribution) {
        this.interestOnNonTaxableMemberContribution = interestOnNonTaxableMemberContribution;
    }

    public BigDecimal getInterestOnTaxableMemberContribution() {
        return interestOnTaxableMemberContribution;
    }

    public void setInterestOnTaxableMemberContribution(BigDecimal interestOnTaxableMemberContribution) {
        this.interestOnTaxableMemberContribution = interestOnTaxableMemberContribution;
    }

    public BigDecimal getInterestOnNonTaxableVpfContribution() {
        return interestOnNonTaxableVpfContribution;
    }

    public void setInterestOnNonTaxableVpfContribution(BigDecimal interestOnNonTaxableVpfContribution) {
        this.interestOnNonTaxableVpfContribution = interestOnNonTaxableVpfContribution;
    }

    public BigDecimal getInterestOnTaxableVpfContribution() {
        return interestOnTaxableVpfContribution;
    }

    public void setInterestOnTaxableVpfContribution(BigDecimal interestOnTaxableVpfContribution) {
        this.interestOnTaxableVpfContribution = interestOnTaxableVpfContribution;
    }

    public BigDecimal getTdsOnTaxableMemberContribution() {
        return tdsOnTaxableMemberContribution;
    }

    public void setTdsOnTaxableMemberContribution(BigDecimal tdsOnTaxableMemberContribution) {
        this.tdsOnTaxableMemberContribution = tdsOnTaxableMemberContribution;
    }

    public BigDecimal getTdsOnTaxableVpfContribution() {
        return tdsOnTaxableVpfContribution;
    }

    public void setTdsOnTaxableVpfContribution(BigDecimal tdsOnTaxableVpfContribution) {
        this.tdsOnTaxableVpfContribution = tdsOnTaxableVpfContribution;
    }
}
