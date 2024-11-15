package com.coreintegra.pftrust.searchutil;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.Loan;
import com.coreintegra.pftrust.entities.pf.transferIn.TransferIn;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.json.JSONArray;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import java.text.ParseException;
import java.util.*;

@Component
public class SearchLoanSpecification {

    public Specification<Loan> dateSpecification(JSONArray dates, String type){

        String dateType = getDateType(type);

        List<Date> selectedDates = new ArrayList<>();

        for(int i=0; i< dates.length(); i++){
            try {
                selectedDates.add(FinancialYearAndMonth.getDate(dates.getString(i)));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }

        return dateSpecification(dateType, selectedDates);

    }

    private Specification<Loan> dateSpecification(String dateType, List<Date> selectedDates) {

        if(selectedDates.isEmpty()) return null;

        if(selectedDates.size() < 2)
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(dateType), selectedDates.get(0));

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(dateType), selectedDates.get(0), selectedDates.get(1));
    }

    public Specification<Loan> monthAndYearSpecification(Integer year, JSONArray months, String type){

        List<Integer> selectedMonths = new ArrayList<>();

        for(int i=0; i< months.length(); i++){
            selectedMonths.add(months.getInt(i));
        }

        if(selectedMonths.isEmpty()) return null;

        Collections.sort(selectedMonths);

        Date firstDate = getFirstDate(year, selectedMonths.get(0));

        Date lastDate = getLastDate(year, selectedMonths.get(selectedMonths.size() - 1));

        String dateType = getDateType(type);

        return dateSpecification(dateType, List.of(firstDate, lastDate));
    }

    private Date getFirstDate(Integer year, Integer month) {
        Calendar instance = getCalendar(year, month);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        return instance.getTime();
    }

    private Date getLastDate(Integer year, Integer month) {
        Calendar instance = getCalendar(year, month);
        instance.add(Calendar.MONTH, 1);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        instance.add(Calendar.DATE, -1);
        return instance.getTime();
    }

    private Calendar getCalendar(Integer year, Integer month) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.MONTH, month -1);
        instance.set(Calendar.YEAR, year);
        return instance;
    }


    public Specification<Loan> unitCodeSpecification(JSONArray jsonArray){

        List<String> unitCodes = new ArrayList<>();

        for(int i=0; i<jsonArray.length(); i++){
            unitCodes.add(jsonArray.getString(i));
        }

        if(unitCodes.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("employee");

            CriteriaBuilder.In<Object> in = criteriaBuilder.in(join.get("unitCode"));

            unitCodes.forEach(in::value);

            return in;
        };
    }


    public Specification<Loan> typeSpecification(JSONArray types){

        List<String> selectedTypes = new ArrayList<>();

        for(int i=0; i< types.length(); i++){
            selectedTypes.add(types.getString(i));
        }

        if(selectedTypes.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("loanType");

            CriteriaBuilder.In<Object> in = criteriaBuilder.in(join.get("entityId"));

            selectedTypes.forEach(in::value);

            return in;
        };

    }


    public Specification<Loan> statusSpecification(JSONArray statusList){

        List<String> selectedStatus = new ArrayList<>();

        for(int i=0; i< statusList.length(); i++){
            selectedStatus.add(statusList.getString(i));
        }

        if(selectedStatus.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("loanStatus");

            CriteriaBuilder.In<Object> in = criteriaBuilder.in(join.get("entityId"));

            selectedStatus.forEach(in::value);

            return in;
        };

    }


    public Specification<Loan> yearSpecification(Integer year){

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("loanWithDrawalsFinalDetails");

            return criteriaBuilder.equal(join.get("year"), year);

        };
    }

    public Specification<Loan> monthSpecification(Integer month){

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("loanWithDrawalsFinalDetails");

            return criteriaBuilder.equal(join.get("month"), month);

        };
    }

    public Specification<Loan> employeeSpecification(Employee employee){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("employee"), employee);
    }


    public Specification<Loan> statusSpecification(String statusCode){

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("loanStatus");

            return criteriaBuilder.equal(join.get("code"), statusCode);

        };

    }


    public static String getDateType(String type){
        switch (type) {
            case "Approval Date":
                return "loanApprovalDate";
            case "Payment Date":
                return "paymentDate";
            case "Application Received Date":
                return "loanApplicationReceivedDate";
            case "Disbursal Date":
                return "loanDisbursalDate";
            default:
                return "loanApplicationDate";
        }
    }

}
