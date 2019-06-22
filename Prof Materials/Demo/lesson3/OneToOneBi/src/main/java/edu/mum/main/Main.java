package edu.mum.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.builder.MemberBuilder;
import edu.mum.builder.UserCredentialsBuilder;
import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.service.UserCredentialsService;

@Component
public class Main {
 
	  @Autowired
	  UserCredentialsService userCredentialsService;

	public static void main(String[] args) {

	  
 	  ApplicationContext applicationContext = new AnnotationConfigApplicationContext( Persistence.class );

       applicationContext.getBean(Main.class).mainInternal(applicationContext);
	 }
	 
	   private void mainInternal(ApplicationContext applicationContext) {

     UserCredentials userCredentials = new UserCredentialsBuilder()
            .withUsername("JohnDoe")
            .withPassword("DoeNuts")
           .build();

    Member member = new MemberBuilder()
            .withFirstName("Sean")
   			.withLastName("Smith")
            .withUserCredentials(userCredentials)
            .withMemberNumber(1)
  			.build();

     // Cascade to Member [ Saving from "other side" of the bidirectional relationship]
    userCredentialsService.save(userCredentials);
    
    userCredentials = userCredentialsService.findByUserName(userCredentials.getUsername());

	    System.out.println();

	    System.out.println("UserCredentials Name : " + userCredentials.getUsername() + 
	    						"  Password: " + userCredentials.getPassword());
	     // Access MEMBER from Credentials
	    System.out.println("Member Name: " + userCredentials.getMember().getFirstName() + " " + userCredentials.getMember().getLastName());
	    System.out.println();

  }
  
  
  }