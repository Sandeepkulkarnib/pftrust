package com.coreintegra.pftrust.repositories.pf.customerservice;

import com.coreintegra.pftrust.entities.pf.employee.PfUser;
import com.coreintegra.pftrust.entities.pf.employee.PfUserId;
import com.coreintegra.pftrust.projections.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PfUserRepository extends JpaRepository<PfUser, PfUserId>,
        JpaSpecificationExecutor<PfUser> {

    @Query(value = "select pfuser.personnel_number pernNumber, pfuser.pf_number pfNumber,\n" +
            "            pfuser.token_number tokenNumber, pfuser.unit_code unitCode,\n" +
            "            pfuser.name_of_member name from MIBS_PF_User pfuser\n" +
            "            where (pfuser.personnel_number like %:search% or pfuser.pf_number like %:search% \n" +
            "            or pfuser.token_number like %:search% or pfuser.unit_code like %:search% \n" +
            "            or pfuser.name_of_member like %:search% ) \n" +
            "            group by pfuser.personnel_number , pfuser.pf_number,"
            + " pfuser.token_number,pfuser.unit_code, pfuser.name_of_member",
            nativeQuery = true,
            countQuery = "select count(pfuser.personnel_number) count from MIBS_PF_User pfuser \n" +
                    " where(pfuser.personnel_number like %:search% or pfuser.pf_number like %:search% \n" +
                    "    or pfuser.token_number like %:search% or pfuser.unit_code like %:search% \n" +
                    "    or pfuser.name_of_member like %:search% ) ", countName = "count")
    Page<PfUserProjection> findAllGroupBypfNumber(Pageable pageable, @Param("search") String search);


    @Query(value = "select fiscal_year fiscalYear, pf_number pfNumber from MIBS_PF_User" +
            " where pf_number = :pfNumber group by fiscal_year order by fiscal_year", nativeQuery = true)
    List<PfUserFiscalYearProjection> getFiscalYears(@Param("pfNumber") String pfNumber);

    @Query(value = "select fiscal_year fiscalYear, posting_period subtype from MIBS_PF_User "
            + "where pf_number = :pfNumber order by fiscal_year limit 1", nativeQuery = true)
    Optional<PfStartYearMonth> getStartYearAndMonth(@Param("pfNumber") String pfNumber);

    @Query(value = "select count(distinct(fiscal_year)) as tenure from MIBS_PF_User "
            + "where pf_number= :pfNumber", nativeQuery = true)
    Integer getTenure(@Param("pfNumber") String pfNumber);


    @Query(value = "select pf_user.pf_number pfNumber,pf_user.member_contribution memberContribution,\r\n"
            + "    		pf_user.company_contribution companyContribution,\r\n"
            + "    		pf_user.member_extra_contribution memberExtraContribution,"
            + "			pf_user.actual_year actualYear, pf_user.posting_period subType from MIBS_PF_User pf_user\r\n"
            + "    		where pf_user.pf_number=:pfNumber and pf_user.fiscal_year= :fiscalYear", nativeQuery = true)
    List<PfMonthlyContribution> getMonthlyContribution(@Param("pfNumber") String pfNumber,
                                                       @Param("fiscalYear") String fiscalYear);

    @Query(value = "select interest.id id,interest.fiscal_year fiscalYear,"
            + "	interest.interest_rate_1 interestRate1, interest.Interest_rate_2 interestRate2,"
            + "    interest.is_break_down isBreakDown, interest.first_break firstBreak,"
            + "    interest.second_break secondBreak"
            + " from mibs_interest_rate  interest where fiscal_year=:fiscalYear", nativeQuery = true)
    Optional<PfUserInterestRates> getInterestRate(@Param("fiscalYear") String fiscalYear);

    @Query(value = "select distinct(fiscal_year) fiscalYear,actual_year subType from MIBS_PF_User "
            + "where pf_number= :pfNumber and fiscal_year between :firstFiscalYear and :secondFiscalYear order by fiscal_year;", nativeQuery = true)
    List<PfStartYearMonth> getFiscalYearBetween(@Param("pfNumber") String pfNumber, @Param("firstFiscalYear") String firstFiscalYear, @Param("secondFiscalYear") String secondFiscalYear);

    @Query(value = "SELECT amount FROM mibs_slabs "
            + "WHERE start_date <= :date AND end_date >= :date ;",
            nativeQuery = true)
    String amount(@Param("date") String date);

    @Query(value = "select fiscal_year fiscalYear,actual_year actualYear,posting_period subType,\r\n"
            + "member_contribution memberContribution,company_contribution companyContribution \r\n"
            + "from MIBS_PF_User where pf_number= :pfNumber order by actual_year desc limit 60;\r\n"
            + "", nativeQuery = true)
    List<PfMonthlyContribution> get60MonthContribution(@Param("pfNumber") String pfNumber);

    @Query(value = "select interest_rate from admin_wages_interest where start_date <= :date", nativeQuery = true)
    String getAdminInterest(@Param("date") String date);

    @Query(value = "select m_id from mwd_member where pf_number = :pfNumber", nativeQuery = true)
    String getMemberId(@Param("pfNumber") String pfNumber);

    @Modifying
    @Query(value = "insert into mwd_member(m_id,pf_number,is_active)\r\n"
            + "values(:mid, :pfNumber ,1);", nativeQuery = true)
    @Transactional
    void setMemberId(@Param("mid") String mid, @Param("pfNumber") String pfNumber);

    @Query(value = "select name_of_member from MIBS_PF_User where pf_number= :pfNumber group by name_of_member limit 1;", nativeQuery = true)
    String getMemberName(@Param("pfNumber") String pfNumber);

    @Query(value = "select id as id,name as name ,father_name as fatherName, name_address as nameAndAddresss,code_number as codeNumber,"
            + "pf_number as pfNumber,member_id as memberId,establishment_name as establishmentName,reason_of_leaving as reasonOfLeaving,leaving_fiscal_year as leavingFiscalYear,"
            + "date_of_leaving as dateOfLeaving from mibs_customer_details where pf_number = :pfNumber ;", nativeQuery = true)
    Optional<Customer7PsDetails> getMemberDetails(@Param("pfNumber") String pfNumber);

    @Modifying
    @Query(value = "INSERT INTO mibs_customer_details(name,father_name,pf_number,member_id,leaving_fiscal_year,reason_of_leaving,date_of_leaving,name_address,code_number) "
            + "VALUES(:name,:fatherName,:pfNumber,:memberId,:leavingFiscalYear,:reasonOfLeaving,:dateOfLeaving,:establishmentName,:establishmentCode);", nativeQuery = true)
    @Transactional
    void setMemberDetails(@Param("name") String name, @Param("fatherName") String fatherName, @Param("pfNumber") String pfNumber,
                          @Param("memberId") String memberId, @Param("leavingFiscalYear") String leavingFiscalYear, @Param("reasonOfLeaving") String reasonOfLeaving,
                          @Param("dateOfLeaving") String dateOfLeaving, @Param("establishmentName") String establishmentName, @Param("establishmentCode") String establishmentCode);

    @Query(value = "select id as id, name as name, father_name as fatherName, name_address as nameAndAddress, pf_number as pfNumber,\n" +
            "member_id as memberId, code_number as codeNumber, establishment_name as establishmentName, leaving_fiscal_year as leavingFiscalYear,\n" +
            "reason_of_leaving as reasonOfLeaving, date_of_leaving as dateOfLeaving from mibs_customer_details where code_number = :establishmentCode", nativeQuery = true)
    List<MibsCustomerDetails> getMibsCustomerDetails(@Param("establishmentCode") String establishmentCode);

    @Query(value = "select distinct(code_number) from mibs_customer_details", nativeQuery = true)
    List<String> getEstablishmentCodeList();

}
