package com.coreintegra.pftrust.dtos;

import com.coreintegra.pftrust.projections.ContributionByMonth;
import com.coreintegra.pftrust.projections.EmployeeCountByStatus;
import com.coreintegra.pftrust.projections.EmployeeHiringCountByMonth;

import java.util.List;

public class EmployeeCountsDTO {

    private List<EmployeeCountByStatus> employeeCountByStatuses;
    private List<EmployeeHiringCountByMonth> employeeHiringCountByMonths;
    private List<ContributionByMonth> contributionByMonths;

    public List<EmployeeCountByStatus> getEmployeeCountByStatuses() {
        return employeeCountByStatuses;
    }

    public void setEmployeeCountByStatuses(List<EmployeeCountByStatus> employeeCountByStatuses) {
        this.employeeCountByStatuses = employeeCountByStatuses;
    }

    public List<EmployeeHiringCountByMonth> getEmployeeHiringCountByMonths() {
        return employeeHiringCountByMonths;
    }

    public void setEmployeeHiringCountByMonths(List<EmployeeHiringCountByMonth> employeeHiringCountByMonths) {
        this.employeeHiringCountByMonths = employeeHiringCountByMonths;
    }

    public List<ContributionByMonth> getContributionByMonths() {
        return contributionByMonths;
    }

    public void setContributionByMonths(List<ContributionByMonth> contributionByMonths) {
        this.contributionByMonths = contributionByMonths;
    }
}
