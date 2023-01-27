package com.springboot.liquibaseexample.controller;

import com.springboot.liquibaseexample.entity.Employee;
import com.springboot.liquibaseexample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> welcome() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("")
    public String saveEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(employee.getId());
        return employeeOptional.isPresent() ? employeeOptional.get() + " saved!" : "Error while saving";
    }
}
