package edu.mum.validation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import edu.mum.domain.Member;
import edu.mum.domain.Product;
import edu.mum.exception.ValidationException;
import edu.mum.validation.GenericValidator;
import edu.mum.validation.dto.MemberValidationDto;
import edu.mum.validation.dto.ProductValidationDto;

/*
 * Validation AOP is invoked by an annotation [@ServiceValidation] that is on Service Save & Update methods
 * 
 */
@Aspect
@Component
public class ServiceValidationAspect {

	@Autowired
	GenericValidator<ProductValidationDto> productValidator;

	// If we also were handling member in the process
	@Autowired
	GenericValidator<MemberValidationDto> memberValidator;

	// ALL service methods...
	 @Pointcut("execution(* edu.mum.service..*(..))")
	    public void applicationMethod() {}

	   @Pointcut("@annotation(edu.mum.validation.aop.ServiceValidation)")
	    public void validate() {}
	
	   // Save & Update contain domain  Object
		 @Pointcut("args(object)")
		    public void argsMethod(Object object) {}

	//Pass in Object under validation
    @Around("validate() && applicationMethod() && argsMethod(object)")	
    public void  doValidate( ProceedingJoinPoint joinPoint, Object object) throws Throwable {
     	
    	// Validate based on bind parameter [object] 
    	String objectName = object.getClass().getSimpleName();
    	
    	switch (objectName) {
    		
		case "Product" :
			// Create wrapper to avoid reflection
			ProductValidationDto productValidationDto = new ProductValidationDto((Product)object);
 			productValidator.doValidate(joinPoint, productValidationDto);
 			break;
// If we were doing member also
		case "Member" :
			MemberValidationDto memberValidationDto = new MemberValidationDto((Member)object);
 			memberValidator.doValidate(joinPoint, memberValidationDto);

 			}
     }

}

 


