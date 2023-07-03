package com.example.employee_react_admin.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@Configuration
public class AllConfig implements WebMvcConfigurer {

    @Bean
    public Faker getFaker() {
        return new Faker();
    }

    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

   
   
}
