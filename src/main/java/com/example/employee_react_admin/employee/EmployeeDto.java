package com.example.employee_react_admin.employee;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeDto implements Serializable {
    private Long id;
    private String name;
    private String address;
    private Long job;
    private String jobName;
}
