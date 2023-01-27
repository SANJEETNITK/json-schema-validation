package com.springboot.liquibaseexample.service;

import com.springboot.liquibaseexample.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployees();

    void save(Employee employee);

    Optional<Employee> getEmployeeById(int id);
}
