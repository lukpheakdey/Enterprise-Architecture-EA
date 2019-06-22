package edu.mum.main;


import java.security.Principal;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import edu.mum.domain.Address;
import edu.mum.domain.Authority;
import edu.mum.domain.Group;
import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.security.AuthenticateUser;
import edu.mum.service.MemberService;
import edu.mum.service.CredentialsService;
import edu.mum.service.GroupService;

 
public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("context/applicationContext.xml");

    AuthenticationManager authenticationManager = (AuthenticationManager) ctx.getBean("authenticationManager");

    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");
    GroupService groupService = (GroupService) ctx.getBean("groupServiceImpl");
    CredentialsService credentialsService = (CredentialsService) ctx.getBean("credentialsServiceImpl");
    UserDetailsService userDetailsService = (UserDetailsService) ctx.getBean("userDetailsServiceImpl");
    
    Authority authority = new Authority();
    authority.setAuthority("ROLE_USER");
     
    Group group = new Group();
    group.setGroup_name("USER");
    group.getAuthority().add(authority);

    // Create ADMIN group ADD ROLE_ADMIN [ will assume ROLE_USER because of hierarchy ]
    Group groupAdmin = new Group();
    groupAdmin.setGroup_name("ADMIN");
 
   authority = new Authority();
   authority.setAuthority("ROLE_ADMIN");
   groupAdmin.getAuthority().add(authority); 


    UserCredentials userCredentials = new UserCredentials();
    userCredentials.setUsername("Sean");
    userCredentials.setPassword("Sean");
    userCredentials.setEnabled(true);
 
    // Add Sean's credentials to user group
    group.getUserCredentials().add(userCredentials);
 
    Member member = new Member();
    member.setFirstName("Sean");
    member.setLastName("Smith");
    member.setMemberNumber(1);

    // Link Sean's credentials to his member
    member.setUserCredentials(userCredentials);
    
    
    Address address =  new Address();
    address.setCity("Batavia");
    address.setState("Iowa");
  
    Address  address2 =  new Address();
    address2.setCity("Red Rock");
    address2.setState("Iowa");
          
    member.addAddress(address);
    member.addAddress(address2);

    // save Sean & credentials
    memberService.saveFull(member);

    
    // THIS IS BILL

    userCredentials = new UserCredentials();
    userCredentials.setUsername("Bill");
    userCredentials.setPassword("Bill");
    userCredentials.setEnabled(true);
 
    groupAdmin.getUserCredentials().add(userCredentials);
    
     member = new Member();
    member.setFirstName("Bill");
    member.setLastName("Due");
    member.setMemberNumber(3);

    member.setUserCredentials(userCredentials);
    
    Address address3 =  new Address();
    address3.setCity("Washington");
    address3.setState("Iowa");
 
    Address address4 =  new Address();
    address4.setCity("Mexico");
    address4.setState("Iowa");

     member.addAddress(address3);
    member.addAddress(address4);
     
     memberService.saveFull(member);
  
     // THIS is PETE
     
    userCredentials = new UserCredentials();
    userCredentials.setUsername("Pete");
    userCredentials.setPassword("Pete");
    userCredentials.setEnabled(true);
 
    group.getUserCredentials().add(userCredentials);
    
    
     member = new Member();
    member.setFirstName("Pete");
    member.setLastName("Moss");
    member.setMemberNumber(4);

    member.setUserCredentials(userCredentials);
    
    memberService.saveFull(member);
    
    groupService.save(groupAdmin);
     groupService.save(group);
    
     try {
     List<Member> members = memberService.findAll();
     }
     catch (AuthenticationCredentialsNotFoundException e) {
    	 System.out.println( );
    	 System.out.println( " ******** ANONYMOUS USER Attempted to access a secure resource *********"  );
       	 System.out.println( );
     }
 
 while (true)  {    
     AuthenticateUser authenticateUser = new AuthenticateUser();
     try {
  		authenticateUser.authenticate(authenticationManager);
  	} catch (Exception e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
     
//     authenticateUser.logout();
     
  // ANYBODY but Bill will get access Denied - AS Bill is An ADMIN & 
  // findAll requires Admin
     
    
      try {
    	 List<Member> members = memberService.findAll();
    	   
    	 for (Member membere : members) {
    		   
    		   System.out.println( );
    		   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
    		
    		   for (Address addresse : membere.getAddresses()) {
    		       System.out.println("Address : " + addresse.getCity() + 
    						"   " + addresse.getState());
    		   }
    	   }
      }
     catch ( AccessDeniedException e) {
 		   System.out.println( );
 		   System.out.println("****** ACCESS DENIED ! You Need ROLE_ADMIN to access Member findAll()  **********");
		   System.out.println( );

     }
      
   }
  } 
  }







//_--------------------------------------VERIFY Multiple groups per user

// This would verify that a member CAN be in multiple groups
// Put following code AFTER setting up Sean's first group
/*
// Create SUPER group  ...ADD SEAN [userCredentials]
Group groupSuper = new Group();
groupSuper.setGroup_name("SUPER");

authority = new Authority();
authority.setAuthority("ROLE_SUPER");
groupSuper.getAuthority().add(authority); 

groupSuper.getUserCredentials().add(userCredentials);

//THIS goes AFTER saving OTHER two groups [Right before try for Anonymous user]
//groupService.update(groupSuper);
// CAN TEST with member.findOne

//_--------------------------------------DYNAMICALLy CHANGE Authorities Move FROM one group to Another
//-------------------------Could be used to declare "Active" group based on session BUT would need separate copy of groups
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     userCredentials = credentialsService.getByUsername(authentication.getName());
     System.out.println(userCredentials.getUsername());

     group.getUserCredentials().remove(userCredentials);
      groupService.update(group);

      groupAdmin.getUserCredentials().add(userCredentials);
      groupService.update(groupAdmin);
  
      UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

 
      Authentication token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getUsername(),userDetails.getAuthorities());
  try {
      authentication = authenticationManager.authenticate(token);
  }
  catch (BadCredentialsException e ) {
	  System.out.println(e.getMessage());
  }
  
  SecurityContextHolder.getContext().setAuthentication(authentication); 


*/
