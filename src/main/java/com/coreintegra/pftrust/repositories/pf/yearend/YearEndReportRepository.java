package com.coreintegra.pftrust.repositories.pf.yearend;

import com.coreintegra.pftrust.entities.pf.department.Job;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface YearEndReportRepository extends JpaRepository<YearEndReport, Long> {

    Page<YearEndReport> findAllByJobAndIsActive(Job job, Boolean isActive, Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update YearEndReport yer set yer.isPublished = false where yer.pernNumber = :pernNumber and yer.year = :year")
    void unPublish(@Param("pernNumber")String pernNumber,
                   @Param("year")Integer year);

}
