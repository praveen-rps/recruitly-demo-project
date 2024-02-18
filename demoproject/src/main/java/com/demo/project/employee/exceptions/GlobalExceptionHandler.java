package com.demo.project.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	
	
	
    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleCustomException(InvalidCredentialsException ex) {
        // Create a custom error response
      //  ErrorResponse errorResponse = new ErrorResponse("Custom Error", ex.getMessage());
        // Return ResponseEntity with appropriate HTTP status code
        //return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        return "login";
    }

    
}
