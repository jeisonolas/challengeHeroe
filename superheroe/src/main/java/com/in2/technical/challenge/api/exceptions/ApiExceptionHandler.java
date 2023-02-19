package com.in2.technical.challenge.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ApiExceptionHandler {

	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleException(Exception ex) {
        ApiExceptionResponse response = new ApiExceptionResponse("Se ha producido un error General","500", ex.getMessage());      
       return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
   
    }
	
}
