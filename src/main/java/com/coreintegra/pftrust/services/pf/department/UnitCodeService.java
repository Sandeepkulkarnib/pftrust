package com.coreintegra.pftrust.services.pf.department;

import com.coreintegra.pftrust.entities.pf.department.UnitCode;

import java.util.List;

public interface UnitCodeService {

    UnitCode save(UnitCode unitCode);

    List<UnitCode> get();

    UnitCode get(String unitCode);

    List<UnitCode> getActiveUnitCodes();

}
