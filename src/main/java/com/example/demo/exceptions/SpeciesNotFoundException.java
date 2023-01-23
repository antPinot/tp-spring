/**
 * 
 */
package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author antPinot
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpeciesNotFoundException extends RuntimeException {
	
	/** serialVersionUID */
	
	private static final long serialVersionUID = 1L;
	
	public SpeciesNotFoundException(Integer id) {
		super("Specie with id " + id + " is not found");
	}

}