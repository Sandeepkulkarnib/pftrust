package com.coreintegra.pftrust.controllers.pf.department;

import com.coreintegra.pftrust.aop.ApplyTenantFilter;
import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.services.pf.department.UnitCodeService;
import com.coreintegra.pftrust.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.coreintegra.pftrust.controllers.pf.department.DepartmentServiceEndpoints.DEPARTMENT_SERVICE_ENDPOINT;
import static com.coreintegra.pftrust.controllers.pf.department.DepartmentServiceEndpoints.UNIT_CODE_SERVICE_ENDPOINT;

@RestController
@RequestMapping(DEPARTMENT_SERVICE_ENDPOINT)
public class UnitCodeController {

    private final UnitCodeService unitCodeService;

    public UnitCodeController(UnitCodeService unitCodeService) {
        this.unitCodeService = unitCodeService;
    }

    @GetMapping(UNIT_CODE_SERVICE_ENDPOINT)
    @ApplyTenantFilter
    public ResponseEntity<Object> getUnitCodes(){
        List<UnitCode> unitCodes = unitCodeService.get();
        return Response.success(unitCodes);
    }

    @PostMapping(UNIT_CODE_SERVICE_ENDPOINT)
    @ApplyTenantFilter
    public ResponseEntity<Object> saveUnitCode(@RequestBody UnitCode unitCode){
        UnitCode save = unitCodeService.save(unitCode);
        return Response.success(save);
    }

}
