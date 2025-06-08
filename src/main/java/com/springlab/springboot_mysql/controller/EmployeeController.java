package com.springlab.springboot_mysql.controller;


import com.springlab.springboot_mysql.model.Employee;
import com.springlab.springboot_mysql.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Log4j2
@RequestMapping("/api/v2/emp") //main api uri
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        log.info("fetching employee by id : {} at {}", id, Instant.now());
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        log.info("creating employee  : {} at {}", emp, Instant.now());
        return employeeService.save(emp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee emp) {
        log.info("updating employee  by id : {} at {}", id, Instant.now());
        return employeeService.findById(id)
                .map(updatedEmp -> {
                    updatedEmp.setDateOfBirth(emp.getDateOfBirth());
                    updatedEmp.setJobLocation(emp.getJobLocation());
                    updatedEmp.setSalary(emp.getSalary());
                    updatedEmp.setPinCode(emp.getPinCode());
                    updatedEmp.setRole(emp.getRole());
                    return ResponseEntity.ok(employeeService.save(updatedEmp));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("deleting employee by id: {} at {}", id, Instant.now());
        if (employeeService.existsById(id)) {
            employeeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
