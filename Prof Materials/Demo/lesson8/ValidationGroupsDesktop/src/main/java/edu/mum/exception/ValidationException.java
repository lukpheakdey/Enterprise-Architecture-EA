package edu.mum.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Set<ConstraintViolation<Object>> errors;
	
    //Parameterless Constructor
    public ValidationException() {}

    //Constructor that accepts a message
    public ValidationException(Set<ConstraintViolation<Object>> errors)
    {
       this.errors = errors;      
    }

	public Set<ConstraintViolation<Object>> getErrors() {
		return errors;
	}

}
