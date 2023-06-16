package com.example.employee_react_admin.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepos extends JpaRepository<Employee,Long> {
    
}
