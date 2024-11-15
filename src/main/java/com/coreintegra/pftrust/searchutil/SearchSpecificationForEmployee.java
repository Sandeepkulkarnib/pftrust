package com.coreintegra.pftrust.searchutil;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class SearchSpecificationForEmployee<T> {

    public Specification<T> getSearchSpecificationForEmployee(String search){
        return pernNumberSpecification(search)
                .or(pfNumberSpecification(search))
                .or(tokenNumberSpecification(search))
                .or(nameSpecification(search));
    }


    private Specification<T> pernNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join("employee");
            return criteriaBuilder.like(join.get("pernNumber"),
                    "%" + search + "%");
        };
    }

    private Specification<T> pfNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join("employee");
            return criteriaBuilder.like(join.get("pfNumber"),
                    "%" + search + "%");
        };
    }

    private Specification<T> tokenNumberSpecification(String search) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join("employee");
            return criteriaBuilder.like(join.get("tokenNumber"),
                    "%" + search + "%");
        };
    }

    private Specification<T> nameSpecification(String search) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join("employee");
            return criteriaBuilder.like(join.get("name"),
                    "%" + search + "%");
        };
    }

}
