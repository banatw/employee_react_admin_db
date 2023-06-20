package com.example.employee_react_admin.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepos extends JpaRepository<Employee,Long>,JpaSpecificationExecutor<Employee> {
    
}
