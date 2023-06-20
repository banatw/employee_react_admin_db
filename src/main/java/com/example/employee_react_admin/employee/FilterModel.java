package com.example.employee_react_admin.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterModel {
    private String column;
    private String value;
}
