package com.example.employee_react_admin.employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(path = "/api/employee",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@CrossOrigin(allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private EmployeeDao dao;

    @PostMapping(value="")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        dao.insert(employee);
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping(value="/update")
    public ResponseEntity<Employee> update(@RequestParam String id, @RequestBody Employee employeeNew) {
        //TODO: process PUT request
        Employee employee =  dao.getOne(Long.parseLong(id));
        employee.setAddress(employeeNew.getAddress());
        employee.setName(employeeNew.getName());
        employee.setJob(employeeNew.getJob());
        dao.update(employee);
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping(value="/delete")
    public ResponseEntity<?> delete(@RequestParam String id) {
        //TODO: process PUT request
        dao.delete(Long.parseLong(id));
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value="/list")
    public ResponseEntity<Page<Employee>> list(@RequestParam int page,@RequestParam int size,
       @RequestParam(name = "sort",required = false) Optional<String> sort,
       @RequestParam(name = "filter",required = false) Optional<String> filter) throws JsonMappingException, JsonProcessingException {
        List<FilterModel> filterModels = new ArrayList<>();
        FilterModel filterModel = null;
        // if(filter.isPresent()) {
            
        //     for (int i = 0; i < filter.get().length; i++) {
        //         // String[] item =  filter.get()[i].split(",");
        //         // FilterModel filterModel = new FilterModel(item[0], item[1]);
        //         // filterModels.add(filterModel);
        //         // System.out.println(filter.get()[i]);
                
        //         if(Math.floorMod(i, 2)==0) {
        //             filterModel = new FilterModel();
        //             filterModel.setId(filter.get()[i]);
        //         }
        //         else {
        //             filterModel.setValue(filter.get()[i]);
        //         }
        //         filterModels.add(filterModel);
        //     }
        // }
        // Optional<java.util.List<FilterModel>> oLisOptional = Optional.of(filterModels);
        // return null;
        return ResponseEntity.ok().body(dao.getList(page, size, sort,filter));
    }

    @GetMapping(value="/get")
    public ResponseEntity<Employee> get(@RequestParam Long id) {
        return ResponseEntity.ok().body(dao.getOne(id));
    }

    
}
