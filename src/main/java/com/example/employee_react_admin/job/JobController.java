package com.example.employee_react_admin.job;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
@RequestMapping(path = "/api/job",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@CrossOrigin(allowedHeaders = "*")
public class JobController {
    private final JobDao dao;

    public JobController(JobDao jobDao) {
        this.dao = jobDao;
    }

    @PostMapping(value="")
    public ResponseEntity<Job> create(@RequestBody Job job) {
        dao.insert(job);
        return ResponseEntity.ok().body(job);
    }

    @PutMapping(value="/update")
    public ResponseEntity<Job> update(@RequestParam String id, @RequestBody Job jobNew) {
        //TODO: process PUT request
        Job job =  dao.getOne(Long.parseLong(id));
        job.setName(jobNew.getName());
        dao.update(job);
        return ResponseEntity.ok().body(job);
    }

    @DeleteMapping(value="/delete")
    public ResponseEntity<?> delete(@RequestParam String id) {
        //TODO: process PUT request
        dao.delete(Long.parseLong(id));
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value="/list")
    public ResponseEntity<Page<Job>> list(@RequestParam int page,@RequestParam int size,
       @RequestParam(name = "sort",required = false) Optional<String> sort,
       @RequestParam(name = "filter",required = false) Optional<String> filter) throws JsonMappingException, JsonProcessingException {
        return ResponseEntity.ok().body(dao.getList(page, size, sort,filter));
    }

    @GetMapping(value="/get")
    public ResponseEntity<Job> get(@RequestParam Long id) {
        return ResponseEntity.ok().body(dao.getOne(id));
    }

    
}
