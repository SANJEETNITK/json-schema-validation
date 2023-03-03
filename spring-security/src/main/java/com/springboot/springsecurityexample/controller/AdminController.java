package com.springboot.springsecurityexample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("I am admin");
    }

    @GetMapping("/contact")
    public ResponseEntity<String> contact() {
        return ResponseEntity.ok("Contact Admin!");
    }


}
