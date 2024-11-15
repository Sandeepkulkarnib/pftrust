package com.coreintegra.pftrust.searchutil;

import com.coreintegra.pftrust.entities.pf.contribution.Contribution;
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

public class SearchContributionSpecification {

    public Specification<Contribution> dateSpecification(JSONArray jsonArray){

        List<Date> dates = new ArrayList<>();

        for(int i=0; i< jsonArray.length(); i++){
            try {
                dates.add(FinancialYearAndMonth.getDate(jsonArray.getString(i)));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }

        if(dates.isEmpty()) return null;

        if(dates.size() < 2)
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("recoveryDate"), dates.get(0));

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("recoveryDate"), dates.get(0), dates.get(1));

    }

    public Specification<Contribution> yearSpecification(Integer year){

        if (year == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("year"), year);
    }

    public Specification<Contribution> monthSpecification(JSONArray jsonArray){

        List<Integer> months = new ArrayList<>();

        for(int i=0; i< jsonArray.length(); i++){
            months.add(jsonArray.getInt(i));
        }

        if(months.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("subType"));
            months.forEach(in::value);
            return in;
        };

    }


    public Specification<Contribution> unitCodeSpecification(JSONArray jsonArray){

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

}
