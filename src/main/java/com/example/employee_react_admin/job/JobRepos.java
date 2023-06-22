package com.example.employee_react_admin.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobRepos extends JpaRepository<Job,Long>,JpaSpecificationExecutor<Job> {
    
}
