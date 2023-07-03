package com.example.employee_react_admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.employee_react_admin.service.FakerService;

@SpringBootApplication
public class EmployeeReactAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeReactAdminApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired FakerService service) {
		return (x)->{
			service.generate();
		};
	}

}
