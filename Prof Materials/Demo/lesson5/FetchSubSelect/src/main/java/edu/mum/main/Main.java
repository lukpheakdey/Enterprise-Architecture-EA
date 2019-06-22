package edu.mum.main;


import java.util.ArrayList;
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
 * N+1 issue when Many side is declare EAGER WITH SUBSELECT
 * One Select for Members
  * One SUB Select for Addresses
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
  
   //  fetch=FetchType.Lazy -- Subselect - 2 SELECTS  - "hydrated in Dao 
   List<Member> members = memberService.findAllSubSelect();

  // Manual version of subselect -  Comment out Fetch mode in Member.java
//  List<Member> members = memberService.findAddressBySubSelect();

// fetch=FetchType.EAGER -- Subselect - 2 SELECTS  - "hydrated in By ORM
//List<Member> members = memberService.findAll();


   System.out.println("SubSelect 2 selects");

   for (Member membere : members) {
	   
	   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
	
	   for (Address addresse : membere.getAddresses()) {
	       System.out.println("Address : " + addresse.getCity() + 
					"   " + addresse.getState());
	   }
	
    }


   
   

  } 
  }