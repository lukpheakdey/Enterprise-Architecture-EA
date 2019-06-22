package edu.mum.main;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import edu.mum.domain.Address;
import edu.mum.domain.Authority;
import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;
import edu.mum.security.AuthenticateUser;
import edu.mum.service.MemberService;

public class TestData {

	public void setup (MemberService memberService) {
		
	    Authority authority = new Authority();
	    authority.setAuthority("ROLE_USER");
	    authority.setUsername("Sean");
	    

	    UserCredentials userCredentials = new UserCredentials();
	    userCredentials.setUsername("Sean");
	    userCredentials.setPassword("Sean");
	    userCredentials.setEnabled(true);
	 
	    userCredentials.getAuthority().add(authority);

	 
	  
	    Member member = new Member();
	    member.setFirstName("Sean");
	    member.setLastName("Smith");
	    member.setMemberNumber(1);

	    member.setUserCredentials(userCredentials);
	    
	    
	    Address address =  new Address();
	    address.setCity("Batavia");
	    address.setState("Iowa");
	  
	    Address  address2 =  new Address();
	    address2.setCity("Red Rock");
	    address2.setState("Iowa");
	          
	    member.addAddress(address);
	    member.addAddress(address2);

	    memberService.saveFull(member);

	    
	    // THIS IS BILL

	     authority = new Authority();
	    authority.setAuthority("ROLE_ADMIN");
	    authority.setUsername("Bill");
	    

	     userCredentials = new UserCredentials();
	    userCredentials.setUsername("Bill");
	    userCredentials.setPassword("Bill");
	    userCredentials.setEnabled(true);
	 
	    userCredentials.getAuthority().add(authority);

	    
	    
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
	     
	     authority = new Authority();
	    authority.setAuthority("ROLE_USER");
	    authority.setUsername("Pete");
	    

	     userCredentials = new UserCredentials();
	    userCredentials.setUsername("Pete");
	    userCredentials.setPassword("Pete");
	    userCredentials.setEnabled(true);
	 
	    userCredentials.getAuthority().add(authority);

	    
	    
	     member = new Member();
	    member.setFirstName("Pete");
	    member.setLastName("Moss");
	    member.setMemberNumber(4);

	    member.setUserCredentials(userCredentials);
	    
	    memberService.saveFull(member);
	    
	     
	  } 
}
