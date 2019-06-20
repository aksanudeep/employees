package com.company.employees.exceptions;

import com.company.employees.controller.LoggingController;
import com.company.employees.intf.FieldErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LogManager.getLogger(LoggingController.class);

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<FieldErrorMessage> handleBadRequestException(BadRequestException ex, WebRequest webRequest) {

        FieldErrorMessage fieldErrorMessage = new FieldErrorMessage(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false));
        log.error("Please Find the Exception : " + ex.getMessage());
        return new ResponseEntity<FieldErrorMessage>(fieldErrorMessage, HttpStatus.BAD_REQUEST);
    // This is a test for Brnach
        // This is test 2
        // This is merge conflits


    }
}
