package com.coreintegra.pftrust.searchutil;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.json.JSONArray;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchEmployeeSpecification {

    public Specification<Employee> getSearchEmployeeSpecification(String search){
        return pernNumberSpecification(search)
                .or(pfNumberSpecification(search))
                .or(tokenNumberSpecification(search))
                .or(nameSpecification(search))
                .or(unitCodeSpecificationSearch(search));
    }

    private Specification<Employee> pernNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("pernNumber"),
                "%" + search + "%");
    }

    private Specification<Employee> pfNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("pfNumber"),
                "%" + search + "%");
    }

    private Specification<Employee> tokenNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("tokenNumber"),
                "%" + search + "%");
    }

    private Specification<Employee> nameSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),
                "%" + search + "%");
    }

    private Specification<Employee> unitCodeSpecificationSearch(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("unitCode"),
                "%" + search + "%");
    }

    public Specification<Employee> dateSpecification(String dateType, JSONArray jsonArray){

        List<Date> dates = new ArrayList<>();

        String selectedDateType = dateType.equalsIgnoreCase("Date of Joining Service") ? "dateOfJoining" : "dateOfJoiningPF";

        for(int i=0; i< jsonArray.length(); i++){
            try {
                dates.add(FinancialYearAndMonth.getDate(jsonArray.getString(i)));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }

        if(dates.isEmpty()) return null;

        if(dates.size() < 2)
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(selectedDateType), dates.get(0));

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(selectedDateType), dates.get(0), dates.get(1));

    }


    public Specification<Employee> unitCodeSpecification(JSONArray jsonArray){

        List<String> unitCodes = new ArrayList<>();

        for(int i=0; i<jsonArray.length(); i++){
            unitCodes.add(jsonArray.getString(i));
        }

        if(unitCodes.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {

            CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("unitCode"));

            unitCodes.forEach(in::value);

            return in;
        };
    }

    public Specification<Employee> contributionStatusSpecification(JSONArray jsonArray){

        List<Long> contributionStatusList = new ArrayList<>();

        for(int i=0; i<jsonArray.length(); i++){
            contributionStatusList.add(jsonArray.getLong(i));
        }

        if(contributionStatusList.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("contributionStatus");

            CriteriaBuilder.In<Object> in = criteriaBuilder.in(join.get("id"));

            contributionStatusList.forEach(in::value);

            return in;
        };
    }

    public Specification<Employee> contributionStatusSpecification(List<String> symbols){

        if(symbols.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {

            Join<Object, Object> join = root.join("contributionStatus");

            CriteriaBuilder.In<Object> in = criteriaBuilder.in(join.get("symbol"));

            symbols.forEach(in::value);

            return in;
        };
    }

}
