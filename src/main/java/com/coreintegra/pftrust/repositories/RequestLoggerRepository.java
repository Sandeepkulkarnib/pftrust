package com.coreintegra.pftrust.repositories;

import com.coreintegra.pftrust.entities.RequestLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RequestLoggerRepository extends JpaRepository<RequestLogger, Long>, JpaSpecificationExecutor<RequestLogger> {
}
