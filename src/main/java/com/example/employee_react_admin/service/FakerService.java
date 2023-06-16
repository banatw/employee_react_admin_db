package com.example.employee_react_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_react_admin.employee.Employee;
import com.example.employee_react_admin.employee.EmployeeRepos;
import com.github.javafaker.Faker;

@Service
public class FakerService {
    @Autowired
    private Faker faker;

    private EmployeeRepos repository;

    public FakerService(EmployeeRepos employeeRepos) {
        this.repository = employeeRepos; 
    }
    public void generate() {
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setName(faker.name().fullName());
            employee.setAddress(faker.address().fullAddress());
            employee.setJob(faker.job().title());
            repository.save(employee);
        }
    }
}
