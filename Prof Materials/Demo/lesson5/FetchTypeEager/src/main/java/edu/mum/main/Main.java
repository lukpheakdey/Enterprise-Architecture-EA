package edu.mum.main;


import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.domain.Order;
import edu.mum.service.AddressService;
import edu.mum.service.MemberService;

/*
 * N+1 issue when Many side is declare EAGER
 * One Fetch of LIST of Members
 * N Fetches of each Members List of Addresses
 */
@Component
public class Main {
 
	@Autowired
	TestData testData;

	@Autowired
	AddressService addressService;

	@Autowired
    MemberService memberService;
    
	public static void main(String[] args) {

	    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
	            "context/applicationContext.xml");
	        applicationContext.getBean(Main.class).mainInternal(applicationContext);
	      }
  
	private void mainInternal(ApplicationContext applicationContext) {
  
   testData.setupData();
     
  //  fetch=FetchType.EAGER -- N+1
   List<Member> members = memberService.findAll();
  
   System.out.println("N+1 ISSUE");

   for (Member membere : members) {
	   
	   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
	
	   for (Address addresse : membere.getAddresses()) {
	       System.out.println("Address : " + addresse.getCity() + 
					"   " + addresse.getState());
	   }
   }

 

  } 
  }