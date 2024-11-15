package com.coreintegra.pftrust.repositories.administration;

import com.coreintegra.pftrust.entities.administration.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndIsActive(String username, Boolean isActive);

    Optional<User> findByEmailAndIsActive(String email, Boolean isActive);

    Optional<User> findByUsernameAndIsActiveAndIsEnabled(String username, Boolean isActive, Boolean isEnabled);

    List<User> findAllByIsActive(Boolean isActive);

}
