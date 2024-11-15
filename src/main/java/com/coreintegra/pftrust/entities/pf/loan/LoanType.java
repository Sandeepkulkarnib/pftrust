package com.coreintegra.pftrust.entities.pf.loan;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.Document;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class LoanType extends BaseAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;

    private String loanGroup;
    private String code;
    private String title;
    private Integer minimumMembershipTenureInMonths;
    private Integer maximumNumberOfWithdrawals;
    private Integer fromRetirementDate;
    private Integer nextEligibility;
    private Integer pfBaseSalaryInMonth;
    private Integer memberBalanceInPercentage;
    private Integer companyBalanceInPercentage;
    private Integer vpfBalanceInPercentage;
    private Integer totalCostInPercentage;
    private Integer appliedAmountInPercentage;
    private String combinedEligibility;

    @JoinTable(name = "loan_documents",
            joinColumns = @JoinColumn(name = "loan_type_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Document> documents;

    @OneToOne(mappedBy = "loanType", fetch = FetchType.LAZY)
    @JsonIgnore
    private LoanAmount loanAmount;

    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getLoanGroup() {
        return loanGroup;
    }

    public void setLoanGroup(String loanGroup) {
        this.loanGroup = loanGroup;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinimumMembershipTenureInMonths() {
        return minimumMembershipTenureInMonths;
    }

    public void setMinimumMembershipTenureInMonths(Integer minimumMembershipTenureInMonths) {
        this.minimumMembershipTenureInMonths = minimumMembershipTenureInMonths;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public LoanAmount getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(LoanAmount loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getMaximumNumberOfWithdrawals() {
        return maximumNumberOfWithdrawals;
    }

    public void setMaximumNumberOfWithdrawals(Integer maximumNumberOfWithdrawals) {
        this.maximumNumberOfWithdrawals = maximumNumberOfWithdrawals;
    }

    public Integer getFromRetirementDate() {
        return fromRetirementDate;
    }

    public Integer getNextEligibility() {
        return nextEligibility;
    }

    public void setFromRetirementDate(Integer fromRetirementDate) {
        this.fromRetirementDate = fromRetirementDate;
    }

    public void setNextEligibility(Integer nextEligibility) {
        this.nextEligibility = nextEligibility;
    }

    public Integer getPfBaseSalaryInMonth() {
        return pfBaseSalaryInMonth;
    }

    public Integer getMemberBalanceInPercentage() {
        return memberBalanceInPercentage;
    }

    public Integer getCompanyBalanceInPercentage() {
        return companyBalanceInPercentage;
    }

    public Integer getVpfBalanceInPercentage() {
        return vpfBalanceInPercentage;
    }

    public Integer getTotalCostInPercentage() {
        return totalCostInPercentage;
    }

    public Integer getAppliedAmountInPercentage() {
        return appliedAmountInPercentage;
    }

    public void setPfBaseSalaryInMonth(Integer pfBaseSalaryInMonth) {
        this.pfBaseSalaryInMonth = pfBaseSalaryInMonth;
    }

    public void setMemberBalanceInPercentage(Integer memberBalanceInPercentage) {
        this.memberBalanceInPercentage = memberBalanceInPercentage;
    }

    public void setCompanyBalanceInPercentage(Integer companyBalanceInPercentage) {
        this.companyBalanceInPercentage = companyBalanceInPercentage;
    }

    public void setVpfBalanceInPercentage(Integer vpfBalanceInPercentage) {
        this.vpfBalanceInPercentage = vpfBalanceInPercentage;
    }

    public void setTotalCostInPercentage(Integer totalCostInPercentage) {
        this.totalCostInPercentage = totalCostInPercentage;
    }

    public void setAppliedAmountInPercentage(Integer appliedAmountInPercentage) {
        this.appliedAmountInPercentage = appliedAmountInPercentage;
    }

	public String getCombinedEligibility() {
		return combinedEligibility;
	}

	public void setCombinedEligibility(String combinedEligibility) {
		this.combinedEligibility = combinedEligibility;
	}
    
    

}
