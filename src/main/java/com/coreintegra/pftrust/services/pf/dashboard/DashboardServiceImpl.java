package com.coreintegra.pftrust.services.pf.dashboard;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.dtos.EmployeeCountsDTO;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.projections.ContributionByMonth;
import com.coreintegra.pftrust.projections.EmployeeCountByStatus;
import com.coreintegra.pftrust.projections.EmployeeHiringCountByMonth;
import com.coreintegra.pftrust.repositories.pf.contribution.ContributionRepository;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import org.json.JSONArray;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeRepository employeeRepository;
    private final ContributionRepository contributionRepository;

    private final List<String> months = List.of("January", "February", "March", "April", "May", "June", "July",
            "September", "October", "November", "December");

    public DashboardServiceImpl(EmployeeRepository employeeRepository, ContributionRepository contributionRepository) {
        this.employeeRepository = employeeRepository;
        this.contributionRepository = contributionRepository;
    }

    @Override
    public EmployeeCountsDTO getEmployeeCounts() {

        Tenant currentTenant = TenantContext.getCurrentTenant();

        List<EmployeeCountByStatus> employeeCountByStatus = employeeRepository
                .getEmployeeCountByStatus(currentTenant);

        List<EmployeeHiringCountByMonth> hiringCountByMonths = employeeRepository
                .getEmployeeHiringCountByMonth(currentTenant.getId());

        List<ContributionByMonth> contributionByMonth = contributionRepository
                .getContributionByMonth(currentTenant.getId());

        Collections.reverse(hiringCountByMonths);

        Collections.reverse(contributionByMonth);

        EmployeeCountsDTO employeeCountsDTO = new EmployeeCountsDTO();

        employeeCountsDTO.setEmployeeCountByStatuses(employeeCountByStatus);
        employeeCountsDTO.setEmployeeHiringCountByMonths(hiringCountByMonths);
        employeeCountsDTO.setContributionByMonths(contributionByMonth);

        return employeeCountsDTO;
    }


    public Specification<Employee> contributionStatusSpecification(JSONArray jsonArray){

        List<Long> contributionStatusList = new ArrayList<>();

        for(int i=0; i<jsonArray.length(); i++){
            contributionStatusList.add(jsonArray.getLong(i));
        }

        if(contributionStatusList.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("contributionStatus");

            CriteriaBuilder.In<Object> in = criteriaBuilder.in(join.get("id"));

            contributionStatusList.forEach(in::value);

            return in;
        };
    }


}
