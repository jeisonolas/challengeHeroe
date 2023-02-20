package com.in2.technical.challenge.domain.enums;

import org.springframework.http.HttpStatus;

public enum ApiErrorType {
	DATA_NOT_FOUND(HttpStatus.NO_CONTENT, "Data not found"), 
	INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),
	NOT_CREATED(HttpStatus.CONFLICT, "Heroe wasn't created, check the logs");

	private final HttpStatus status;
	private final String message;

	ApiErrorType(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
