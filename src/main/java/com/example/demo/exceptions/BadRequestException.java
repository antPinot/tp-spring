/**
 * 
 */
package com.example.demo.exceptions;

/**
 * @author antPinot
 *
 */
public class BadRequestException extends RuntimeException {

	/** serialVersionUID */
	
	private static final long serialVersionUID = 8371547576918650856L;
	
	public BadRequestException(String message) {
		super(message);
	}

}
