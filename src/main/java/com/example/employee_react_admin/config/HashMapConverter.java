package com.example.employee_react_admin.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper;
    public HashMapConverter(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String jobJson = null;
        try {
            jobJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            
        }

        return jobJson;
        
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        // TODO Auto-generated method stub
        Map<String, Object> jobInfo = null;
        try {
            jobInfo = objectMapper.readValue(dbData, new TypeReference<HashMap<String, Object>>() {});
        } catch (final IOException e) {
        }

        return jobInfo;
    }
    
}
