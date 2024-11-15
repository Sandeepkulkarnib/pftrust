package com.coreintegra.pftrust.repositories.pf.job;

import com.coreintegra.pftrust.entities.pf.department.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomJobRepository extends JpaRepository<Job, Long> {

    List<Job> findAllByNameAndIsActive(String name, Boolean isActive);

    Job findByEntityIdAndIsActive(String entityId, Boolean isActive);

}
