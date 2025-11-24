package com.devrito.tasks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.devrito.tasks.domain.dto.ErrorResponse;
import com.devrito.tasks.exceptions.ResourceNotFoundException;
import com.devrito.tasks.exceptions.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<ErrorResponse> handleExceptions(RuntimeException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<ErrorResponse> handleValidationExceptions(RuntimeException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<ErrorResponse> handleResourceNotFoundExceptions(RuntimeException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// @ExceptionHandler({ NoHandlerFoundException.class })
	// @ResponseStatus(HttpStatus.NOT_FOUND)
	// public ResponseEntity<ErrorResponse>
	// handleNoHandlerFoundException(RuntimeException ex, WebRequest request) {
	// ErrorResponse errorResponse = new ErrorResponse(
	// HttpStatus.NOT_FOUND.value(),
	// "Route Not Found: Invalid URL.",
	// request.getDescription(false));

	// return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	// }
}
