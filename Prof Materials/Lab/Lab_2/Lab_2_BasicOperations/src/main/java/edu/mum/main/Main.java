package edu.mum.main;


import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException; 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.User;
import edu.mum.service.UserService;

public class Main {
	public static void main(String[] args) {

	    ApplicationContext cxt = new ClassPathXmlApplicationContext("context/applicationContext.xml");
	    UserService userService = (UserService) cxt.getBean("userServiceImpl");
	
	    List<User> listUser = userService.findAll();
	    System.out.println("User count: " + listUser.size());
	
	    User user = new User();
	    user.setFirstName("John");
	    user.setLastName("Doe");
	    user.setEmail("johndoe@mum.edu");
	    userService.save(user);
	    System.out.println("User has been inserted!!!");
	    
	    listUser = userService.findAll();
	    System.out.println("User count after inserted : " + listUser.size());
	    
	    User readUser = userService.findByEmail("johndoe@mum.edu");
	    System.out.println();
	    System.out.println("*************  User *************");
	    System.out.println("User Name: " + readUser.getFirstName() + " " + readUser.getLastName());
	    
	    System.out.println();
	    System.out.println("************* User Merge *************");
	
		user.setEmail("lukpheakdey@gmail.com");
	    User resultMerge = userService.update(user);
		user.setEmail("lukpheakdey.com");
	
	    try {
	    	resultMerge = userService.update(user);
	    } catch (ObjectOptimisticLockingFailureException e) {
	 		System.out.println("\nState object exception");
	    }
	
		System.out.println("\n************* User refresh or flush *************");
	
		user.setId(null);
	   	try {
	   		userService.testRefresh(user);
	   	} catch (Exception e) {
	   		System.out.println("***** Done!!!! ******");
	   }
	}
}