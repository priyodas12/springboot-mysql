package com.springlab.springboot_mysql.service;

import com.springlab.springboot_mysql.model.Employee;
import com.springlab.springboot_mysql.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee emp) {
        return employeeRepository.save(emp);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }
}
