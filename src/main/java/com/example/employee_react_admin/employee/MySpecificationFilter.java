package com.example.employee_react_admin.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class MySpecificationFilter implements Specification<Employee> {

    private List<FilterModel> filters;

    public MySpecificationFilter(List<FilterModel> paramFilters) {
        this.filters = paramFilters;
    }

    @Override
    @Nullable
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // TODO Auto-generated method stub
        List<Predicate> predicates = new ArrayList<>();
        for (FilterModel filterModel : filters) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(filterModel.getColumn())), "%" + filterModel.getValue() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));   
    }
    
}
