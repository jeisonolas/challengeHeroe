package com.in2.technical.challenge.infraestructure.utilities;

import java.util.List;
import java.util.Optional;

import com.in2.technical.challenge.domain.enums.ApiErrorType;
import com.in2.technical.challenge.domain.exceptions.ApiBussinessException;

public class Validations {

	private Validations(){}
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
