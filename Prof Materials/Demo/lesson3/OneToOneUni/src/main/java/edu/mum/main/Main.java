package edu.mum.main;


import org.hibernate.LazyInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.builder.AuthorityBuilder;
import edu.mum.builder.MemberBuilder;
import edu.mum.builder.UserCredentialsBuilder;
import edu.mum.domain.Member;
import edu.mum.service.MemberService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");
 
/* THIS IS REPLACED BY MemberBuilder
    Authority authority = new Authority();
    authority.setAuthority("USER");
    authority.setUsername("JohnDoe");
    

    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUsername("JohnDoe");
    userCredentials.setPassword("DoeNuts");
 
    userCredentials.getAuthority().add(authority);

    
    Member member = new Member();
    member.setFirstName("Sean");
    member.setLastName("Smith");
    member.setMemberNumber(1);

    member.setUserCredentials(userCredentials);
*/
  
    Member member = new MemberBuilder()
    		.withFirstName("Sean")
   			.withLastName("Smith")
   			.withUserCredentials(new UserCredentialsBuilder()
                    .withUsername("JohnDoe")
                    .withPassword("DoeNuts")
                    .withAuthority(new AuthorityBuilder()
                    		.withUsername("JohnDoe")
                    		.withAuthority("USER")
                    		.build())
                   .build())
   			.withMemberNumber(1)
   			.build();


     // Automatically cascades Persist to UserCredentials
    memberService.save(member);

    member = memberService.findOneFull(member.getId());
       
    System.out.println("*************  Access Member & Credentials - even though LAZY loaded  ********");
    System.out.println("Member Name : " + member.getFirstName() + "  " +  member.getLastName() );
    System.out.println("UserCredentials Name : " + member.getUserCredentials().getUsername() + 
			"  Password: " +  member.getUserCredentials().getPassword());
    System.out.println();

    // Demonstrate Lazy load issue
     member = memberService.findOne(member.getId());

     try {
    	member.getUserCredentials().getUsername();
    }
    catch (LazyInitializationException e) {
        System.out.println("*************  Access Member TRY to access Credentials *******");
     	     System.out.println("EXCEPTION : UserCredentials Unavailable - LAZY loaded");
     	    System.out.println();
  }
    
 	    
    // Demonstrate DELETE 
    memberService.delete(member);
    

  }
  
  
  }