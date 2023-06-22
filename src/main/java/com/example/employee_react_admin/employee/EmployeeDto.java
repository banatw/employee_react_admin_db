package com.example.employee_react_admin.employee;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String address;
    private Long job;
}
