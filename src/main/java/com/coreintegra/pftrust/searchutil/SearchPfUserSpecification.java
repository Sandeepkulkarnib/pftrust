package com.coreintegra.pftrust.searchutil;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.employee.PfUser;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import org.json.JSONArray;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchPfUserSpecification {

    public Specification<PfUser> getSearchPfUserSpecification(String search){
        return pernNumberSpecification(search)
                .or(pfNumberSpecification(search))
                .or(tokenNumberSpecification(search))
                .or(nameSpecification(search))
                .or(unitCodeSpecificationSearch(search));
    }

    private Specification<PfUser> pernNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("pfUserId.personnelNumber"),
                "%" + search + "%");
    }

    private Specification<PfUser> pfNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("pfNumber"),
                "%" + search + "%");
    }

    private Specification<PfUser> tokenNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("tokenNumber"),
                "%" + search + "%");
    }

    private Specification<PfUser> nameSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nameOfMember"),
                "%" + search + "%");
    }

    private Specification<PfUser> unitCodeSpecificationSearch(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("unitCode"),
                "%" + search + "%");
    }

}
