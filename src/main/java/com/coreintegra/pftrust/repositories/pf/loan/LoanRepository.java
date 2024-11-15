package com.coreintegra.pftrust.repositories.pf.loan;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.loan.LoanStatus;
import com.coreintegra.pftrust.entities.pf.loan.LoanType;
import com.coreintegra.pftrust.projections.LoanContributionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {

    @Query(value = "select count(ln.id), ln.employee.id from Loan ln where ln.employee in (:employees) group by ln.employee")
    List<Tuple> getLoanCount(@Param("employees")List<Employee> employees);

    Optional<Loan> findByEntityIdAndIsActive(String entityId, Boolean isActive);

    @Query(value = "select sum(lw.loanAmountOnMemContribution) as loanAmountOnMemberContribution, " +
            "sum(lw.loanAmountOnCompanyContribution) as loanAmountOnCompanyContribution, " +
            "sum(lw.loanAmountOnVPFContribution) as loanAmountOnVpfContribution " +
            "from Loan ln inner join LoanWithDrawalsFinalDetails lw on lw.loan = ln " +
            "where ln.employee = :employee and ln.isActive = true and lw.isActive = true")
    LoanContributionDTO getTotalLoanContribution(@Param("employee") Employee employee);

    @Query(value = "select sum(lw.loanAmountOnMemContribution) as loanAmountOnMemberContribution, " +
            "sum(lw.loanAmountOnCompanyContribution) as loanAmountOnCompanyContribution, " +
            "sum(lw.loanAmountOnVPFContribution) as loanAmountOnVpfContribution " +
            "from Loan ln inner join LoanWithDrawalsFinalDetails lw on lw.loan = ln " +
            "where ln.employee = :employee and lw.year = :year and ln.isActive = true and lw.isActive = true")
    LoanContributionDTO getTotalLoanContribution(@Param("employee") Employee employee,
                                                 @Param("year") Integer year);

    Integer countLoansByEmployeeAndLoanTypeAndLoanStatusAndIsActive(Employee employee, LoanType loanType,
                                                                    LoanStatus loanStatus, Boolean isActive);

    Integer countLoansByEmployeeAndLoanStatusAndIsActive(Employee employee, LoanStatus loanStatus, Boolean isActive);

    List<Loan> findAllByEmployeeAndLoanTypeAndIsActive(Employee employee, LoanType loanType, Boolean isActive);

    List<Loan> findAllByEmployeeAndLoanTypeAndLoanStatusAndIsActive(Employee employee, LoanType loanType,
                                                                    LoanStatus loanStatus, Boolean isActive);

    List<Loan> findAllByEmployeeAndLoanStatusAndIsActive(Employee employee, LoanStatus loanStatus,
                                                         Boolean isActive);

//    List<Loan> findAllByEmployeeAndLoanTypeAndIsActiveAndLoanStatusOrderByLoanDisbursalDateDesc(Employee employee,LoanType loanType,Boolean isActive, LoanStatus loanStatus);

    @Query(value = "select ln.id,ln.loan_disbursal_date,ln.loan_type_id, ln.employee_id " +
            "from loan ln where ln.employee_id = :employeeId and ln.loan_type_id = :loanTypeId " +
            "and ln.loan_status_id = :loanStatus and ln.is_active = true order by ln.id desc", nativeQuery = true)
    List<Loan> findAllByEmployeeAndLoanTypeAndLoanStatusAndIsActiveOrderById
            (@Param("employeeId") int employeeId,@Param("loanTypeId") int loanTypeId,@Param("loanStatus") int loanStatus);
}
