package com.example.employee_react_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_react_admin.employee.Employee;
import com.example.employee_react_admin.employee.EmployeeRepos;
import com.example.employee_react_admin.job.Job;
import com.example.employee_react_admin.job.JobRepos;
import com.github.javafaker.Faker;

@Service
public class FakerService {
    @Autowired
    private Faker faker;

    private final JobRepos repository;
    private final EmployeeRepos repos;

    public FakerService(JobRepos jobRepos,EmployeeRepos repo) {
        this.repository = jobRepos; 
        this.repos = repo;
    }
    public void generate() {
        for (int i = 0; i < 100; i++) {
            Job job = new Job();
            job.setName(faker.job().title());
            repository.save(job);
        }

        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setName(faker.name().fullName());
            employee.setAddress(faker.address().fullAddress());
            repos.save(employee);
        }
    }
}
