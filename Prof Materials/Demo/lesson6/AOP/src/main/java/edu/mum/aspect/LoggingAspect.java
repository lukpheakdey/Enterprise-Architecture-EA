package edu.mum.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import edu.mum.domain.Order;

@Aspect
@Component
public class LoggingAspect {
	
	// Isolates/restricts WHERE @Logging can be applied
	 @Pointcut("execution(* edu.mum.service..*(..))")
//	 @Pointcut("execution(* edu.mum.service..update(..))")    // - If you want to AOP ONLY updates...IGNORES Save
 	    public void applicationMethod() {}

	 // Annotation
	   @Pointcut("@annotation(edu.mum.aspect.annotation.Logging)")
	    public void logging() {}
	
	      // Remove "logging() &&"  AND it will do a "regular"[no annotation] execution Point cut.
		  @Before("logging() && applicationMethod()")
//		  @Before("execution(* *.*.*..*(..))")                  // "indiscriminate" application Try it!

		  public void logResource(JoinPoint joinPoint) {
		  Logger log = Logger.getLogger("");
		    log.info("   **********     TARGET CLASS : " + joinPoint.getSignature().getName() + "    **********");
		    System.out.println();
				    System.out.println( "   **********     TARGET CLASS : " + 
	    			joinPoint.getSignature().getDeclaringTypeName() + "." +
	    			joinPoint.getSignature().getName() + 
	    				    			"    **********");
	  }
		  
}
