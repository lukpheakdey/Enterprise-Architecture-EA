package edu.mum.main;


import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException; 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.User;
import edu.mum.service.UserService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    UserService userService = (UserService) ctx.getBean("userServiceImpl");

    List<User> list = userService.findAll();
    System.out.println("User count: " + list.size());

    User user = new User();
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setEmail("john@Doe.com");
    userService.save(user);
    System.out.println("User inserted!");

    list = userService.findAll();
    System.out.println("User count: " + list.size());
    
    User readUser = userService.findByEmail("john@Doe.com");
    
    System.out.println();
    System.out.println("        *********  User **********");

    System.out.println("User Name: " + readUser.getFirstName() + " " + readUser.getLastName());
    
    System.out.println();
    System.out.println("        *********  User Merge Test **********");

     // Now test merge return value
	user.setEmail("Lotta@Doe.com");
    User mergeResult = userService.update(user);
	user.setEmail("xxx.com");

    try {
    	mergeResult = userService.update(user);
    }
    catch (ObjectOptimisticLockingFailureException e) {
 		System.out.println();
 		System.out.println("State object exception - Need to use RETURN value from update/merge");
    }

    System.out.println();
    System.out.println("        *********  User Flush/Refresh Test **********");

    user.setId(null);
   try {
    userService.testRefresh(user);
   }
   catch (Exception e) {
	   System.out.println();
	    System.out.println("All Done");

   }

 }
  
  
  }