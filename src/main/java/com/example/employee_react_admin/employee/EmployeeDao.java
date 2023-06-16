package com.example.employee_react_admin.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDao {
    private EmployeeRepos repos;

    public EmployeeDao(EmployeeRepos employeeRepos) {
        this.repos = employeeRepos;
    }

    public Page<Employee> getList(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repos.findAll(pageable);
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
