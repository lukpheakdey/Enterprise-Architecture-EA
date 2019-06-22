package edu.mum.validation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Member;
import edu.mum.domain.Product;
import edu.mum.validation.aop.dto.MemberValidationDtoImpl;
import edu.mum.validation.aop.dto.ProductValidationDtoImpl;

@Aspect
@Component
public class ServiceValidationAspect {


	@Autowired
	ServiceValidationProcess<ProductValidationDtoImpl> productServiceValidation;

	// If we also were handling member in the process
	@Autowired
	ServiceValidationProcess<MemberValidationDtoImpl> memberServiceValidation;

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
			ProductValidationDtoImpl productValidationDto = new ProductValidationDtoImpl((Product)object);
 			productServiceValidation.doValidate(joinPoint, productValidationDto);
 			break;
// If we were doing member also
		case "Member" :
			MemberValidationDtoImpl memberValidationDto = new MemberValidationDtoImpl((Member)object);
 			memberServiceValidation.doValidate(joinPoint, memberValidationDto);

 			}
     }

}

 


