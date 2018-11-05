package com.company.employees.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
    private static final Logger log = LogManager.getLogger(LoggingController.class);

}

