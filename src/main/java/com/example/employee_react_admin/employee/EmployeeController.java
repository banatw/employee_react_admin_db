package com.example.employee_react_admin.employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_react_admin.job.Job;
import com.example.employee_react_admin.job.JobDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Console;
import java.util.Optional;

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
    // @Autowired
    // private EmployeeDao dao;

    private final EmployeeDao dao;
    private final JobDao jobDao;

    public EmployeeController(EmployeeDao employeeDao,JobDao job) {
        this.dao = employeeDao;
        this.jobDao = job;
    }

    @PostMapping(value="")
    public ResponseEntity<Employee> create(@RequestBody EmployeeDto empString) throws JsonMappingException, JsonProcessingException {
        // System.out.println("fdfdfd " + empString.getJobId());
        Employee employee = new Employee();
        employee.setName(empString.getName());
        employee.setAddress(empString.getAddress());
        Job job = jobDao.getOne(empString.getJob());
        employee.setJob(job);
        dao.insert(employee);
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping(value="/update")
    public ResponseEntity<Employee> update(@RequestParam String id, @RequestBody EmployeeDto empString) throws JsonMappingException, JsonProcessingException {
        //TODO: process PUT request
        
        Employee employee =  dao.getOne(Long.parseLong(id));
        employee.setAddress(empString.getAddress());
        employee.setName(empString.getName());
        Job job = jobDao.getOne(empString.getJob());
        employee.setJob(job);
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
        return ResponseEntity.ok().body(dao.getList(page, size, sort,filter));
    }

    @GetMapping(value="/get")
    public ResponseEntity<EmployeeDto> get(@RequestParam Long id) {
        Employee employee = dao.getOne(id);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setAddress(employee.getAddress());
        employeeDto.setName(employee.getName());
        employeeDto.setJob(employee.getJob().getId());
        return ResponseEntity.ok().body(employeeDto);
    }

    
}
