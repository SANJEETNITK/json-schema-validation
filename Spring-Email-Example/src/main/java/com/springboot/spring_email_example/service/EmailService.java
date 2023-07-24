package com.springboot.spring_email_example.service;

import com.springboot.spring_email_example.dto.request.EmailRequest;

public interface EmailService {
    void sendEmail(EmailRequest emailRequest);

}
