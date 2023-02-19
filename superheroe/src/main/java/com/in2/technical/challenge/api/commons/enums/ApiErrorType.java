package com.in2.technical.challenge.api.commons.enums;

import org.springframework.http.HttpStatus;

public enum ApiErrorType {
	DATA_NOT_FOUND(HttpStatus.NO_CONTENT, "Data not found"), 
	INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request");

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
