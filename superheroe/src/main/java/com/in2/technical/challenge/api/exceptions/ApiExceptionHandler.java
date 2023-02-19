package com.in2.technical.challenge.api.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@RestControllerAdvice
public class ApiExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiExceptionResponse> handleException(Exception ex) {
        
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ApiExceptionResponse response = new ApiExceptionResponse(status.name(), status.value(), ex.getMessage());      
       return new ResponseEntity<>(response,status);
    }
	
	@ExceptionHandler(Unauthorized.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiExceptionResponse> handleException(Unauthorized ex) {
        
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		ApiExceptionResponse response = new ApiExceptionResponse(status.name(), status.value(), ex.getMessage());      
       return new ResponseEntity<>(response,status);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiExceptionResponse> handleException(MethodArgumentNotValidException ex) {
        
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		
		ApiExceptionResponse response = new ApiExceptionResponse(status.name(), status.value(), errorMessage);      
       return new ResponseEntity<>(response,status);
    }
	
	@ExceptionHandler(ApiBussinessException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ApiExceptionResponse> handleException(ApiBussinessException ex) {
        
		HttpStatus status = ex.getErrorType().getStatus();
		
		ApiExceptionResponse response = new ApiExceptionResponse("API Bussiness Exception", status.value(), ex.getErrorType().getMessage());      
       return new ResponseEntity<>(response,status);
    }
	
}
