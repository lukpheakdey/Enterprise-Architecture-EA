package edu.mum.main;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.builder.AddressBuilder;
import edu.mum.builder.AuthorityBuilder;
import edu.mum.builder.UserBuilder;
import edu.mum.builder.UserCredentialsBuilder;
import edu.mum.domain.Address;
import edu.mum.domain.Authority;
import edu.mum.domain.User;
import edu.mum.domain.UserCredentials;
import edu.mum.service.UserCredentialsService;
import edu.mum.service.UserService;

@Component
public class TestUser {

	@Autowired
	UserService userService;
	
	@Autowired
	UserCredentialsService userCredentialsService;
	

 public void testUser() {
	 
		User user =  new UserBuilder()
				.withFirstName("John")
				.withLastName("Doe")
				.withEmail("john@Doe.com")
				.withAddress(new AddressBuilder()
						.withCity("Batavia")
 				 		.build())
				.withAddress(new AddressBuilder()
						.withCity("Red Rock")
 			 			.build())
 				.build();    
	    
   UserCredentials userCredentials = new UserCredentialsBuilder() 
		   .withUserName("JohnDoe")
		   .withPassword("DoeNuts")
		   .withVerifyPassword("DoeNuts")
		   .withAuthority(new AuthorityBuilder()
		   	   .withAuthority("ROLE_ADMIN")
		       .withUsername("JohnDoe")
		       .build())
		   .withAuthority(new AuthorityBuilder()
			   	   .withAuthority("ROLE_USER")
			       .withUsername("JohnDoe")
			       .build())
		   .withUser(user)
		   .build();
		   
   userCredentialsService.update(userCredentials);
   
    User readUser = userService.findByEmail("john@Doe.com");
   
   System.out.println();
   System.out.println("        *********  User **********");

   System.out.println("User Name: " + readUser.getFirstName() + " " + readUser.getLastName());
   System.out.println("UserCredentials user Name: " + readUser.getUserCredentials().getUserName() );

   System.out.println("        *********  Authorities **********");
   List<Authority> authorities = readUser.getUserCredentials().getAuthority();
   for (Authority authority : authorities)
	   System.out.println("Authority  : " + authority.getAuthority() );

   System.out.println("        *********  Addresses **********");
  for (Address address :  new ArrayList<Address>(readUser.getAddresses()) )
		   System.out.println("Address City  : " + address.getCity() );
   
}
}
