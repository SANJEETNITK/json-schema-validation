package com.springboot.spring_email_example.service.impl;

import com.springboot.spring_email_example.dto.request.EmailRequest;
import com.springboot.spring_email_example.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailRequest emailRequest) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailRequest.getFrom());
        simpleMailMessage.setTo(emailRequest.getTo());
        simpleMailMessage.setText(emailRequest.getBody());
        simpleMailMessage.setSubject(emailRequest.getSubject());

        javaMailSender.send(simpleMailMessage);
    }
}
