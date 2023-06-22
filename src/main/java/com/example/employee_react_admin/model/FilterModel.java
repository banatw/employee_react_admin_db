package com.example.employee_react_admin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterModel {
    private String column;
    private String value;
}
