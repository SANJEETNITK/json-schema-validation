package com.springboot.spring_email_example.controller;


import com.springboot.spring_email_example.dto.request.EmailRequest;
import com.springboot.spring_email_example.service.AwsSesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    private final AwsSesService awsSesService;

    public EmailController(AwsSesService awsSesService) {
        this.awsSesService = awsSesService;
    }

    @PostMapping("/email")
    ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        awsSesService.sendEmail(emailRequest);
        return ResponseEntity.ok("Email sent!");
    }

}
