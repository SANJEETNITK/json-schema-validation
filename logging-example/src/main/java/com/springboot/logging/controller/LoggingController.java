package com.springboot.logging.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    private static final Logger LOGGER = LogManager.getLogger(LoggingController.class);

    @GetMapping("/info-log")
    public String infoLog() {
        LOGGER.info("info log called");
        return "info logging";
    }

    @GetMapping("/error-log")
    public String errorLog() {
        LOGGER.info("error log called");
        return "error logging";
    }

}
