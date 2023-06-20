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
    private EmployeeRepos repos;

    public EmployeeDao(EmployeeRepos employeeRepos) {
        this.repos = employeeRepos;
    }

    public Page<Employee> getList(int page,int size, Optional<String> sort,Optional<String> filter) throws JsonMappingException, JsonProcessingException {
        Pageable pageable = null;
        String[] arrSort = null;
        if(sort.isPresent() && filter.isPresent()) {
            if(!sort.get().equals("[]")) {
                arrSort = sort.get().replace("[", "").replace("]", "").split(",");
                ObjectMapper objectMapper = new ObjectMapper();
                List<FilterModel> listCar = objectMapper.readValue(filter.get(), new TypeReference<List<FilterModel>>(){});
                pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(arrSort[1].replace("\"", "")),arrSort[0].replace("\"", "")));
                return repos.findAll(new MySpecificationFilter(listCar), pageable);
            }
            else {
                pageable = PageRequest.of(page, size);
            } 
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
