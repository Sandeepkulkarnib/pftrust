package com.coreintegra.pftrust.repositories.administration;

import com.coreintegra.pftrust.entities.administration.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
