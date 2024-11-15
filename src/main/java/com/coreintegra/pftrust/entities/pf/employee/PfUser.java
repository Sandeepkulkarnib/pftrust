package com.coreintegra.pftrust.entities.pf.employee;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "MIBS_PF_User")
public class PfUser {

    @EmbeddedId
    private PfUserId pfUserId;

    private String enddate;
    private String startdate;
    private String changedon;
    private String changedby;
    private Integer postingPeriod;
    private Integer pfNumber;
    private String unitCode;
    private Integer tokenNumber;
    private String departmentCode;
    private String status;
    private BigDecimal memberContribution;
    private BigDecimal companyContribution;
    private BigDecimal memberExtraContribution;
    private String nameOfMember;

    public PfUserId getPfUserId() {
        return pfUserId;
    }

    public void setPfUserId(PfUserId pfUserId) {
        this.pfUserId = pfUserId;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getChangedon() {
        return changedon;
    }

    public void setChangedon(String changedon) {
        this.changedon = changedon;
    }

    public String getChangedby() {
        return changedby;
    }

    public void setChangedby(String changedby) {
        this.changedby = changedby;
    }

    public Integer getPostingPeriod() {
        return postingPeriod;
    }

    public void setPostingPeriod(Integer postingPeriod) {
        this.postingPeriod = postingPeriod;
    }

    public Integer getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(Integer pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Integer getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(Integer tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public BigDecimal getMemberExtraContribution() {
        return memberExtraContribution;
    }

    public void setMemberExtraContribution(BigDecimal memberExtraContribution) {
        this.memberExtraContribution = memberExtraContribution;
    }

    public String getNameOfMember() {
        return nameOfMember;
    }

    public void setNameOfMember(String nameOfMember) {
        this.nameOfMember = nameOfMember;
    }
}
