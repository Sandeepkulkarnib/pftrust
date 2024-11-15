package com.coreintegra.pftrust.services.pf.department;

import com.coreintegra.pftrust.entities.pf.department.UnitCode;
import com.coreintegra.pftrust.repositories.pf.department.UnitCodeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UnitCodeServiceImpl implements UnitCodeService {

    private final UnitCodeRepository unitCodeRepository;

    public UnitCodeServiceImpl(UnitCodeRepository unitCodeRepository) {
        this.unitCodeRepository = unitCodeRepository;
    }

    @Override
    public UnitCode save(UnitCode unitCode) {

        Optional<UnitCode> optionalUnitCode = unitCodeRepository
                .findByUnitCodeAndIsActive(unitCode.unitCode, true);

        if(optionalUnitCode.isPresent()){
            throw new EntityExistsException("Unit Code already exists");
        }

        return unitCodeRepository.save(unitCode);
    }

    @Override
    public List<UnitCode> get() {
        return unitCodeRepository.findAll();
    }

    @Override
    public UnitCode get(String unitCode) {
        Optional<UnitCode> optionalUnitCode = unitCodeRepository.findByUnitCodeAndIsActive(unitCode, true);

        if(optionalUnitCode.isEmpty()) throw new EntityNotFoundException("unit code not found");

        return optionalUnitCode.get();
    }

    @Override
    public List<UnitCode> getActiveUnitCodes() {
        return unitCodeRepository.findAllByIsActive(true);
    }

}
