package com.coreintegra.pftrust.controllers.pf.dashboard;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.dtos.EmployeeCountsDTO;
import com.coreintegra.pftrust.services.pf.dashboard.DashboardService;
import com.coreintegra.pftrust.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(DashboardServiceEndpoints.DASHBOARD_SERVICE_ENDPOINT)
@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/employees")
    @ApplyTenantFilter
    public ResponseEntity<Object> getEmployeeCount() {
        EmployeeCountsDTO employeeCounts = dashboardService.getEmployeeCounts();
        return Response.success(employeeCounts);
    }

}
