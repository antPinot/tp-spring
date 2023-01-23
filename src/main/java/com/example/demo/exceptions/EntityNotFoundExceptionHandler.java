/**
 * 
 */
package com.example.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author antPinot
 *
 */

@ControllerAdvice
public class EntityNotFoundExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({AnimalNotFoundException.class, PersonNotFoundException.class, SpeciesNotFoundException.class})
	public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request){
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.NOT_FOUND, request);
	}

}
