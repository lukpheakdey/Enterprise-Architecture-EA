package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.builder.AuthorityBuilder;
import edu.mum.builder.UserCredentialsBuilder;
import edu.mum.dao.UserCredentialsDao;
import edu.mum.domain.Authority;
import edu.mum.domain.UserCredentials;
import edu.mum.service.UserCredentialsService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    UserCredentialsService userCredentialsService = (UserCredentialsService) ctx.getBean("userCredentialsServiceImpl");

 /*   Authority authority = new Authority();
    authority.setAuthority("ROLE_USER");
    authority.setUsername("JohnDoe");
    
    Authority authority2 = new Authority();
    authority2.setAuthority("ROLE_ADMIN");
    authority2.setUsername("JohnDoe");
    
    Authority authority3 = new Authority();
    authority3.setAuthority("ROLE_SUPERVISOR");
    authority3.setUsername("JohnDoe");
    
    
    
    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUsername("JohnDoe");
    userCredentials.setPassword("DoeNuts");
    
    userCredentials.getAuthority().add(authority);
    userCredentials.getAuthority().add(authority2);
    userCredentials.getAuthority().add(authority3);
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
 	    System.out.println("******************   Display UserCredentials  *********");

    System.out.println("UserCredentials Name : " + userCredentials.getUsername() + 
    						"  Password: " + userCredentials.getPassword());
	    System.out.println("******************   Display Authorities  *********");
  
	    for ( int index = 0;index<userCredentials.getAuthority().size();index++) {
 	    	Authority authorite = userCredentials.getAuthority().get(index);
 	    	System.out.println("Authority User Name : " + authorite.getUsername() + 
				"  Authority: " + authorite.getAuthority() + " Index: " + index);
	    }
    System.out.println();
       
    userCredentials.getAuthority().remove(0);
    userCredentials = userCredentialsService.update(userCredentials);
    System.out.println("******************   AFTER ORPHAN REMOVAL: Display Authorities for Index column SEE DB TABLE*********");
    
    for ( int index = 0;index<userCredentials.getAuthority().size();index++) {
	    	Authority authorite = userCredentials.getAuthority().get(index);
	    	System.out.println("Authority User Name : " + authorite.getUsername() + 
			"  Authority: " + authorite.getAuthority() + " Index: " + index);
    }


  }
}
   