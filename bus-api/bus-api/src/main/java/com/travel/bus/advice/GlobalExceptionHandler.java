package com.travel.bus.advice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.travel.bus.exception.ResourceNotFoundException;

import feign.FeignException;
import feign.FeignException.NotFound;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {
		logger.info("Excetion Occurred {} ", exception);
		return new ResponseEntity<>("Unexpected error has occured", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception) {
		logger.info("Excetion Occurred {} ", exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception) {
		logger.info("Excetion Occurred {} ", exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
		logger.info("Excetion Occurred {} ", resourceNotFoundException);
		return new ResponseEntity<>(resourceNotFoundException.getExceptionMessage(), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info("Excetion Occurred {} ", exception);
		List<String> validationList = exception.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
				.collect(Collectors.toList());
		return new ResponseEntity<>(validationList, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<?> handleEntityNotFoundException(FeignException feignException) {
		logger.info("Excetion Occurred {} ", feignException);
		if (feignException instanceof NotFound)
			return new ResponseEntity<>("Requested resource not found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>("Internal Service not available/timedout, Please try again later",
					HttpStatus.SERVICE_UNAVAILABLE);
	}

}
