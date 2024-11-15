package com.coreintegra.pftrust.entities.pf.loan.dtos;


public class LoanTypeDTO {

    private String title;
    private String Code;
    private String group;
    private int minimumTenure;
    private Integer maximumNumberOfWithdrawals;

    private String id;

    private Integer from_retirement_date;

    private Integer next_eligibility;

    public LoanTypeDTO(String title, String code, String group, int minimumTenure, Integer maximumNumberOfWithdrawals, String id, Integer from_retirement_date, Integer next_eligibility) {
        this.title = title;
        Code = code;
        this.group = group;
        this.minimumTenure = minimumTenure;
        this.maximumNumberOfWithdrawals = maximumNumberOfWithdrawals;
        this.id = id;
        this.from_retirement_date = from_retirement_date;
        this.next_eligibility = next_eligibility;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return Code;
    }

    public String getGroup() {
        return group;
    }

    public int getMinimumTenure() {
        return minimumTenure;
    }

    public Integer getMaximumNumberOfWithdrawals() {
        return maximumNumberOfWithdrawals;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setMinimumTenure(int minimumTenure) {
        this.minimumTenure = minimumTenure;
    }

    public void setMaximumNumberOfWithdrawals(Integer maximumNumberOfWithdrawals) {
        this.maximumNumberOfWithdrawals = maximumNumberOfWithdrawals;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFrom_retirement_date() {
        return from_retirement_date;
    }

    public Integer getNext_eligibility() {
        return next_eligibility;
    }

    public void setFrom_retirement_date(Integer from_retirement_date) {
        this.from_retirement_date = from_retirement_date;
    }

    public void setNext_eligibility(Integer next_eligibility) {
        this.next_eligibility = next_eligibility;
    }
}
