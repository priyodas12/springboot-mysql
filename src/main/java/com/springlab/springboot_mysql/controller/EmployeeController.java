package com.springlab.springboot_mysql.controller;


import com.springlab.springboot_mysql.model.Employee;
import com.springlab.springboot_mysql.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return employeeService.save(emp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee emp) {
        return employeeService.findById(id)
                .map(updatedEmp -> {
                    updatedEmp.setDateOfBirth(emp.getDateOfBirth());
                    updatedEmp.setJobLocation(emp.getJobLocation());
                    updatedEmp.setSalary(emp.getSalary());
                    updatedEmp.setPinCode(emp.getPinCode());
                    return ResponseEntity.ok(employeeService.save(updatedEmp));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (employeeService.existsById(id)) {
            employeeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
