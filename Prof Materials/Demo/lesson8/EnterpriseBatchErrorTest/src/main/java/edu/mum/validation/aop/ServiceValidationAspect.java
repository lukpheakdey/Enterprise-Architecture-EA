package edu.mum.validation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Member;
import edu.mum.domain.Product;
import edu.mum.validation.GenericValidator;
import edu.mum.validation.dto.MemberValidationDto;
import edu.mum.validation.dto.ProductValidationDto;

@Aspect
@Component
public class ServiceValidationAspect {


	@Autowired
	GenericValidator<ProductValidationDto> productValidator;

	// If we also were handling member in the process
/*	@Autowired
	GenericValidator<MemberValidationDtoImpl> memberValidator;
*/
	// ALL service methods...
	 @Pointcut("execution(* edu.mum.service..*(..))")
	    public void applicationMethod() {}

	   @Pointcut("@annotation(edu.mum.validation.aop.ServiceValidation)")
	    public void validate() {}
	
	   // Save & Update contain domain  Object
		 @Pointcut("args(object)")
		    public void argsMethod(Object object) {}

    @Around("validate() && applicationMethod() && argsMethod(object)")	
    public void  doValidate( ProceedingJoinPoint joinPoint, Object object) throws Throwable {
     	
    	String objectName = object.getClass().getSimpleName();
    	
    	switch (objectName) {
    		
		case "Product" :
			ProductValidationDto productValidationDto = new ProductValidationDto((Product)object);
			productValidator.doValidate(joinPoint, productValidationDto);
// If we were doing member also
/*		case "Member" :
			MemberValidationDtoImpl memberValidationDto = new MemberValidationDtoImpl((Member)object);
 			memberServiceValidation.doValidate(joinPoint, memberValidationDto);
*/
 			}
     }

}

 


