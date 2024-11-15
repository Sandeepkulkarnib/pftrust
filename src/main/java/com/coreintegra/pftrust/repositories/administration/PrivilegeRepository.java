package com.coreintegra.pftrust.repositories.administration;

import com.coreintegra.pftrust.entities.administration.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
