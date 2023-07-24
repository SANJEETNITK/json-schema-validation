package com.springboot.spring_email_example.service;

import com.springboot.spring_email_example.dto.request.EmailRequest;

public interface AwsSesService {
    void sendEmail(EmailRequest emailRequest);
}
