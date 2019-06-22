package edu.mum.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import edu.mum.domain.User;

@Aspect
@Component
public class AuctionAspect {
	
	// #2 Explicit pointcut ANY parameters
	// @Pointcut("execution(* edu.mum.service..*(..))")
	
	// #3 Long 
 	// @Pointcut("execution(* edu.mum.service..*(Long))")
 	
 	// #4 ()  no parameters
 	@Pointcut("execution(* edu.mum.service..*())")
 	
	public void applicationMethod() {}

	// #1 implcit pointcut
	// @Before("execution(* edu.mum.service..*(..))")
 	
	// #2 Explicit pointcut
	// @Before("applicationMethod()")
 	
	// #6 within & args pointcut
	//@Before("within(edu.mum.service..*) && args(Long)")
 	
	public void logResource(JoinPoint joinPoint) {
		System.out.println();
		System.out.println( "*** Target Class : " + 
		joinPoint.getSignature().getDeclaringTypeName() + "." +
		joinPoint.getSignature().getName() + " ***");
	}
	 
	// #7 within & args pointcut -- binding User
	@Before("within(edu.mum.service..*) && args(user)")
	public void logResourceName(JoinPoint joinPoint, User user) {
		System.out.println();
		System.out.println( "*** Target Class : " + 
		joinPoint.getSignature().getDeclaringTypeName() + "." +
		joinPoint.getSignature().getName() + " ***" + "User First Name: " + user.getFirstName());
	}

}
