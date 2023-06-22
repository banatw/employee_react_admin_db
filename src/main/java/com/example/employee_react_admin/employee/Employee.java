package com.example.employee_react_admin.employee;

import com.example.employee_react_admin.job.Job;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String address;
    @OneToOne
    @JoinColumn(
        name = "job_id",
        referencedColumnName = "id"
    )
    @JsonBackReference
    private Job job;

}
