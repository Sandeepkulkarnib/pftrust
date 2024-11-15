package com.coreintegra.pftrust.dtos;

import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.entities.pf.loan.dtos.StatusDTO;
import com.coreintegra.pftrust.entities.pf.loan.dtos.TypeDTO;

import java.util.List;
import java.util.Map;

public class EssentialsDTO {

    private List<TypeDTO> typeList;
    private List<StatusDTO> statusList;
    private List<UnitCode> unitCodeList;
    private List<Integer> years;
    private Map<Integer, String> months;

    public EssentialsDTO(List<TypeDTO> typeList, List<StatusDTO> statusList,
                         List<UnitCode> unitCodeList, List<Integer> years, Map<Integer, String> months) {
        this.typeList = typeList;
        this.statusList = statusList;
        this.unitCodeList = unitCodeList;
        this.years = years;
        this.months = months;
    }

    public List<TypeDTO> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeDTO> typeList) {
        this.typeList = typeList;
    }

    public List<StatusDTO> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusDTO> statusList) {
        this.statusList = statusList;
    }

    public List<UnitCode> getUnitCodeList() {
        return unitCodeList;
    }

    public void setUnitCodeList(List<UnitCode> unitCodeList) {
        this.unitCodeList = unitCodeList;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public Map<Integer, String> getMonths() {
        return months;
    }

    public void setMonths(Map<Integer, String> months) {
        this.months = months;
    }
}
