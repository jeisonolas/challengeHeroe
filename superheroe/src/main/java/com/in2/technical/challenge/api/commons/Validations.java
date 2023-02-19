package com.in2.technical.challenge.api.commons;

import java.util.List;
import java.util.Optional;

import com.in2.technical.challenge.api.commons.enums.ApiErrorType;
import com.in2.technical.challenge.api.exceptions.ApiBussinessException;

public class Validations {

	public Validations() {
		
	}
	
	public static void validationList(List<?> list) throws ApiBussinessException {
	
		if(list.isEmpty()) {
			throw new ApiBussinessException(ApiErrorType.DATA_NOT_FOUND);
		}
	}
	
	public static void validationObject(Optional<?> item) throws ApiBussinessException {
		
		if(!item.isPresent()) {
			throw new ApiBussinessException(ApiErrorType.DATA_NOT_FOUND);
		}
	}
}
