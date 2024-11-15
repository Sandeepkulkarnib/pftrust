package com.coreintegra.pftrust.repositories.pf.transferin;

import com.coreintegra.pftrust.entities.pf.PfAccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PfAccountHolderRepository extends JpaRepository<PfAccountHolder, Long> {

    List<PfAccountHolder> findAllByIsActive(Boolean isActive);

}
