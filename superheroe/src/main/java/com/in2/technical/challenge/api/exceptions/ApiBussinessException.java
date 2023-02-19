package com.in2.technical.challenge.api.exceptions;

import com.in2.technical.challenge.api.commons.enums.ApiErrorType;

public class ApiBussinessException extends Exception {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private final ApiErrorType errorType;

	public ApiBussinessException(ApiErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}

	public ApiErrorType getErrorType() {
		return errorType;
	}
}
