package com.example.jsonschemavalidation.request;

import com.example.jsonschemavalidation.pojo.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private Employee employee;

}
