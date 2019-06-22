package edu.mum.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.validation.Errors;

public class ValidationExceptionGroup extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Set<ConstraintViolation<Object>> errors;
	
    //Parameterless Constructor
    public ValidationExceptionGroup() {}

    //Constructor that accepts a message
    public ValidationExceptionGroup(Set<ConstraintViolation<Object>> errors)
    {
       this.errors = errors;      
    }

	public Set<ConstraintViolation<Object>> getErrors() {
		return errors;
	}

}
