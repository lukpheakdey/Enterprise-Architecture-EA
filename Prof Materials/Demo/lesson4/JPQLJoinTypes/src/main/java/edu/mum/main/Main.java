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
 * The is A specific Join Fetch 
 * Declared in memberService.findAllJoinFetch()
 * Demonstrates the Cartesian Product Problem
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
     
   List<Member> memberss = memberService.findAllJoinFetch();
     
   //  fetch=FetchType.LAZY
  System.out.println("                      N+1 GONE - Inner Join Fetch Cartesian Product");

  for (Member membere : memberss) {
  
	   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
	
	   for (Address addresse : membere.getAddresses()) {
	       System.out.println("          Address : " + addresse.getCity() + 
					"   " + addresse.getState());
	   }
   }

 memberss = memberService.findAllOuterJoinFetch();
 System.out.println();
System.out.println("                      N+1 GONE - Left Outer Join Fetch Cartesian Product");

for (Member membere : memberss) {

	   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
	
	   for (Address addresse : membere.getAddresses()) {
	       System.out.println("          Address : " + addresse.getCity() + 
					"   " + addresse.getState());
	   }
}

  

List<Address> addresses = addressService.findAllOuterJoinFetch();
System.out.println();
System.out.println("                      N+1 GONE - RIGHT Outer Join Fetch Cartesian Product");

String memberName = "start";
for (Address addres : addresses) {

	   if (addres.getMember() != null && (memberName.equals("start") || !(addres.getMember().getFirstName().equals(memberName)) )) {
		   System.out.println("Member Name : " + addres.getMember().getFirstName() + "  " +  addres.getMember().getLastName() );
		   memberName = addres.getMember().getFirstName();
	   }
	   else if (addres.getMember() == null) System.out.println("Member Name : ");
	   System.out.println("              Address: " + addres.getCity() + "  " + addres.getState() );
 
}


  
  }
  
  }