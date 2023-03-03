package com.springboot.springsecurityexample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/details")
    public ResponseEntity<String> userDetails() {
        return ResponseEntity.ok("Hi, I am Sanjeet kumar. Thank you :)");
    }

}
