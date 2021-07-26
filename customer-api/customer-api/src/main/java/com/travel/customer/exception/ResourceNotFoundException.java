package com.travel.customer.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel.customer.model.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4126765775145864713L;
	private String exceptionCode;
	private String exceptionMessage;

}
