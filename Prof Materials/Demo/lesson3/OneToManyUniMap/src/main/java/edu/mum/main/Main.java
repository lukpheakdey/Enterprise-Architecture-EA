package edu.mum.main;


import java.util.Iterator;
import java.util.Map.Entry;

import javax.persistence.NoResultException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

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

/*    Authority authority = new Authority();
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
    
    userCredentials.getAuthority().put("user",authority);
    userCredentials.getAuthority().put("admin",authority2);
    userCredentials.getAuthority().put("supervisor",authority3);

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
  
	    Iterator<Entry<String, Authority>> entries = userCredentials.getAuthority().entrySet().iterator();

	    while  ( entries.hasNext()) {
	    	Entry<String, Authority> entry  =  entries.next();
	    	Authority authorite = (Authority) entry.getValue();
	        System.out.println("Authority User Name : " + authorite.getUsername() + 
					"  Authority: " + authorite.getAuthority() +  " Key: "  + entry.getKey());
	    }
	    
	    // Look up credentials by the existence of a specific authority [key=admin]
		userCredentials = userCredentialsService.findByMappedAuthority("admin");
        System.out.println("Lookup by mapped Authority"); 
        System.out.println("UserCredentials Name : " + userCredentials.getUsername()); 

	    // Exercise FAILURE of Look up credentials by the NON existence of a specific authority [key=adin]
        System.out.println("Demo FAILED Lookup by mapped Authority"); 
        try {
        	userCredentials = userCredentialsService.findByMappedAuthority("adin");  //"Bad" key
        }
        catch (EmptyResultDataAccessException ex ) {
        	System.out.println("UserCredentials does not exist"); 
        }
 
  }
}
   