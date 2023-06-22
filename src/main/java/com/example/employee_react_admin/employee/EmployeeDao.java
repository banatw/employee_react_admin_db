package com.example.employee_react_admin.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.example.employee_react_admin.model.FilterModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class EmployeeDao {
    private final EmployeeRepos repos;

    public EmployeeDao(EmployeeRepos employeeRepos) {
        this.repos = employeeRepos;
    }

    public Page<Employee> getList(int page,int size, Optional<String> sort,Optional<String> filter) throws JsonMappingException, JsonProcessingException {
        Pageable pageable = null;
        if(sort.isPresent() && filter.isPresent() ) {
            if(!sort.get().equals("{}")) {
                ObjectMapper objectMapper = new ObjectMapper();
                List<FilterModel> listFilter = objectMapper.readValue(filter.get(), new TypeReference<List<FilterModel>>(){});
                FilterModel sortModel = objectMapper.readValue(sort.get(), FilterModel.class);
                pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(sortModel.getValue()), sortModel.getColumn()));
                return repos.findAll(new MySpecificationFilter(listFilter), pageable);
            }
            else {
                pageable = PageRequest.of(page, size);
            }
        }
            else {
                pageable = PageRequest.of(page, size);
            } 
        
        return repos.findAll(pageable);
        // if(sort.isPresent() && filter.isPresent()) {
        //     String[] urut = sort.get().split(",");
        //     pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(urut[1]),urut[0]));
        //     return repos.findAll(new MySpecificationFilter(filter.get()), pageable);
        // }
        // else if(sort.isPresent() && filter.isPresent()==false) {
        //     String[] urut = sort.get().split(",");
        //     pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(urut[1]),urut[0]));
        //     return repos.findAll(pageable);
        // }
        // else {
        //     pageable = PageRequest.of(page, size);
        //     return repos.findAll(pageable);
        // }   
        // pageable = PageRequest.of(page, size);
        // return repos.findAll(pageable);
    }

    public void insert(Employee employee) {
        repos.save(employee);
    }

    public void update(Employee employee) {
        repos.save(employee);
    }

    public void delete(Long id) {
        repos.deleteById(id);
    }

    public Employee getOne(Long id) {
        return repos.findById(id).get();
    }

    
}
