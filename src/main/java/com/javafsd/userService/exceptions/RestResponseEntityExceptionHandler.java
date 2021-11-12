package com.javafsd.userService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> userNotFoundException(
			UserNotFoundException userNotFoundExceptions, WebRequest request){
		
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, userNotFoundExceptions.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

}
