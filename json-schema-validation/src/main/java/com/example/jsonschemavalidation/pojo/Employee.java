package com.example.jsonschemavalidation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String id;
    private String name;
    private double salary;
    private int age;
    private Date dob;
    private String designation;
    private String aadhar;
    private List<String> skills;
    private String mobile;
    private String email;
}
