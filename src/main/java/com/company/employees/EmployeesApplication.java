package com.company.employees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmployeesApplication {
    private static final Logger log = LoggerFactory.getLogger(EmployeesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
        log.info("**********Spring Boot Application for Employee Started*************");
    }
}
