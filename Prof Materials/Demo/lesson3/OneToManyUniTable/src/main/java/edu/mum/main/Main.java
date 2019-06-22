package edu.mum.main;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.builder.AuthorityBuilder;
import edu.mum.builder.UserCredentialsBuilder;
import edu.mum.domain.Authority;
import edu.mum.domain.UserCredentials;
import edu.mum.service.UserCredentialsService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    UserCredentialsService userCredentialsService = (UserCredentialsService) ctx.getBean("userCredentialsServiceImpl");

 /*   Authority authority = new Authority();
    authority.setAuthority("USER");
    authority.setUsername("JohnDoe");
    
    Authority authority2 = new Authority();
    authority2.setAuthority("ADMIN");
    authority2.setUsername("JohnDoe");
    
    
    
    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUsername("JohnDoe");
    userCredentials.setPassword("DoeNuts");
    
    userCredentials.getAuthority().add(authority);
    userCredentials.getAuthority().add(authority2);
*/
    UserCredentials userCredentials =  new UserCredentialsBuilder()
    		.withUsername("JohnDoe")
    		.withPassword("DoeNuts")
    		.withAuthority(new AuthorityBuilder()
    				.withUsername("JohnDoe")
    				.withAuthority("ROLE_USER")
    				.build())
    		.withAuthority(new AuthorityBuilder()
    				.withUsername("JohnDoe")
    				.withAuthority("ROLE_ADMIN")
    				.build())
    		.withAuthority(new AuthorityBuilder()
    				.withUsername("JohnDoe")
    				.withAuthority("ROLE_SUPERVISOR")
    				.build())
    		.build();

    userCredentialsService.save(userCredentials);
 
    userCredentials = userCredentialsService.findByUserName(userCredentials.getUsername());

    System.out.println();
    System.out.println("UserCredentials Name : " + userCredentials.getUsername() + 
    						"  Password: " + userCredentials.getPassword());
    
    for (Authority authorite : userCredentials.getAuthority()) {
        System.out.println("Authority User Name : " + authorite.getUsername() + 
				"  Authority: " + authorite.getAuthority());
    }

  }
  
  
  }