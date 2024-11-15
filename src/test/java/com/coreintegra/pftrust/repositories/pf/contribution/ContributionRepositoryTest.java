package com.coreintegra.pftrust.repositories.pf.contribution;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.projections.TotalContributionDTO;
import com.coreintegra.pftrust.repositories.pf.employee.EmployeeRepository;
import com.coreintegra.pftrust.repositories.tenant.TenantRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContributionRepositoryTest {

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TenantRepository tenantRepository;

    private Tenant tenant;
    private Employee employee;

    @BeforeEach
    void setUp() {
        tenant = setupTenant();
        employee = setupEmployee(tenant);
    }

    @AfterEach
    void tearDown() {
        contributionRepository.deleteAll();
        employeeRepository.deleteAll();
        tenantRepository.deleteAll();
    }

    @Test
    @DisplayName("get total contribution")
    void getTotalContribution() {

        createContributions(2022);

        TotalContributionDTO totalContribution = contributionRepository.getTotalContribution(employee, true);

        assertEquals(BigDecimal.valueOf(1000 * 10).intValue(), totalContribution.getMemberContribution().intValue());

        assertEquals(BigDecimal.valueOf(1000 * 10 * 3).intValue(), totalContribution.getTotalContribution().intValue());

    }

    private List<Contribution> createContributions(Integer year) {

        List<Contribution> contributions = new ArrayList<>();

        for (int i=0; i<10; i++){
            Contribution contribution = new Contribution(tenant, i, year, BigDecimal.valueOf(1000),
                    BigDecimal.valueOf(1000), BigDecimal.valueOf(1000),
                    employee);
            Contribution save = contributionRepository.save(contribution);
            contributions.add(save);
        }
        return contributions;
    }

    private Employee setupEmployee(Tenant tenant) {
        Employee testEmployee = new Employee(tenant, "TOKEN", "PERN", "PFNUMBER",
                "TEST_EMPLOYEE", "TEST_UNIT_CODE");

        Employee employee = employeeRepository.save(testEmployee);
        return employee;
    }

    private Tenant setupTenant() {
        Tenant test_tenant = new Tenant("6dd282f3-7d17-4501-8711-63f8e6d01fc0", "TEST_TENANT");

        Tenant tenant = tenantRepository.save(test_tenant);

        TenantContext.setCurrentTenant(tenant);
        TenantContext.setCurrentTenantId(tenant.getId());
        return tenant;
    }


    @Test
    @DisplayName("get total contribution with in active contributions")
    void getTotalContributionWithInActiveContributions() {

        List<Contribution> contributions = createContributions(2022);

        for (int i=3; i<6; i++){
            Contribution contribution = contributions.get(i);
            contribution.setIsActive(false);
            contributionRepository.save(contribution);
        }

        TotalContributionDTO totalContribution = contributionRepository.getTotalContribution(employee, true);

        assertEquals(BigDecimal.valueOf(1000 * 7).intValue(), totalContribution.getMemberContribution().intValue());

        assertEquals(BigDecimal.valueOf(1000 * 7 * 3).intValue(), totalContribution.getTotalContribution().intValue());

    }

    @Test
    void getTotalContributionForYear() {

        createContributions(2023);
        createContributions(2022);

        TotalContributionDTO totalContribution = contributionRepository.getTotalContribution(employee, true);

        assertEquals(BigDecimal.valueOf(1000 * 20).intValue(), totalContribution.getMemberContribution().intValue());

        TotalContributionDTO totalContributionForYear = contributionRepository.getTotalContribution(employee, 2023, true);

        assertEquals(BigDecimal.valueOf(1000 * 10).intValue(), totalContributionForYear.getMemberContribution().intValue());

        assertEquals(BigDecimal.valueOf(1000 * 10 * 3).intValue(), totalContribution.getTotalContribution().intValue());

    }

}