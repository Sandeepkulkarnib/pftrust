package com.coreintegra.pftrust.repositories.pf.yearend;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.yearend.YearEndProcess;
import com.coreintegra.pftrust.projections.YearEndProcessYearAndVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface YearEndProcessRepository extends JpaRepository<YearEndProcess, Long> {

    List<YearEndProcess> findAllByEmployeeAndYearAndIsActiveAndIsPublishedAndEntityType(Employee employee,
                                                                                       Integer year,
                                                                                       Boolean isActive,
                                                                                       Boolean isPublished,
                                                                                       String entityType);

    Optional<YearEndProcess> findByEmployeeAndYearAndMonthAndEntityTypeAndIsActiveAndIsPublished(Employee employee,
                                                                                                 Integer year,
                                                                                                 Integer month,
                                                                                                 String entityType,
                                                                                                 Boolean isActive,
                                                                                                 Boolean isPublished);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update YearEndProcess yep set yep.isPublished = false where yep.employee = :employee and yep.year = :year")
    void unPublish(@Param("employee") Employee employee, @Param("year") Integer year);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update YearEndProcess yep set yep.isPublished = false where yep.employee = :employee and yep.year = :year " +
            "and yep.month = :month")
    void unPublishOpeningBalance(@Param("employee") Employee employee, @Param("year") Integer year,
                                 @Param("month")Integer month);

    @Query("select yep.year from YearEndProcess yep where yep.employee = :employee and yep.isPublished = :isPublished" +
            " and yep.isActive = true and yep.month > 0")
    List<Integer> getAvailableYears(@Param("employee") Employee employee,
                                    @Param("isPublished") Boolean isPublished);

    @Query("select yp from YearEndProcess yp where yp.employee = :employee and yp.year = :year and yp.month = 0 and yp.isActive = true " +
            " and yp.isPublished = true and yp.processType = 'PROCESS_FOR_SETTLEMENT'")
    Optional<YearEndProcess> getPublishedYearEndProcessForSettlement(@Param("employee") Employee employee,
                                                        @Param("year") Integer year);

    @Query(value = "select year, job_id from year_end_process where employee_id=:employeeId " +
            "                                          group by employee_id, year, job_id " +
            "                                          order by job_id", nativeQuery = true)
    List<YearEndProcessYearAndVersion> getYearsAndVersions(@Param("employeeId") Long employeeId);



}
