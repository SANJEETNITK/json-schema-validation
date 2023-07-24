package com.springboot.spring_email_example.service.impl;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.springboot.spring_email_example.dto.request.EmailRequest;
import com.springboot.spring_email_example.service.AwsSesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AwsSesServiceImpl implements AwsSesService {

    Logger logger = LoggerFactory.getLogger(AwsSesServiceImpl.class);

    private final AmazonSimpleEmailService emailService;

    private static final String CHAR_SET = "UTF-8";

    public AwsSesServiceImpl(AmazonSimpleEmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendEmail(EmailRequest emailRequest) {
        try {
            // The time for request/response round trip to aws in milliseconds
            int requestTimeout = 3000;
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(emailRequest.getTo()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset(CHAR_SET)
                                            .withData(emailRequest.getBody())))
                            .withSubject(new Content()
                                    .withCharset(CHAR_SET)
                                    .withData(emailRequest.getSubject())))
                    .withSource(emailRequest.getFrom())
                    .withSdkRequestTimeout(requestTimeout);
            emailService.sendEmail(request);
        } catch (RuntimeException e) {
            logger.error("Error occurred sending email | request {}", emailRequest, e);
        }
    }
}
