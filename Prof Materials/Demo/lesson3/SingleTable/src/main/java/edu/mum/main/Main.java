package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Customer;
import edu.mum.domain.Member;
import edu.mum.domain.MemberRole;
import edu.mum.domain.Volunteer;
import edu.mum.service.MemberService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("context/applicationContext.xml");

     MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");

  
    Member member = new Member();
    member.setFirstName("Sean");
    member.setLastName("Smith");
    member.setMemberNumber(1);

    Volunteer volunteer = new Volunteer("Cracker Jack", 3, 4);
    Customer customer = new Customer("Snicker", 17);
   
    member.addRole(volunteer);
    member.addRole(customer);
    
    memberService.save(member);
 
    Member membere = memberService.findOne(member.getId());
  
	   
	   System.out.println("Member Name : " + membere.getFirstName() + "  " +  membere.getLastName() );
	
	   System.out.println("-------------- Sub Classes ---------------------");

	   for (MemberRole memberRole : membere.getMemberRoles()) {
		   
	       System.out.println("Member Role : " + memberRole.getType());
	       System.out.println("Nickname : " + memberRole.getNickName());
		      
	       
//	       if (memberRole.isType("volunteer")) {
		       if (memberRole instanceof Volunteer) {
	    	   Volunteer volunteerRole  = (Volunteer) memberRole;     
		    	   System.out.println("Credits : " + volunteerRole.getCredits());
		   }
	       else   if (memberRole.isType("customer")) {
	    	   Customer customerRole  = (Customer) memberRole;     
		       System.out.println("Bonus Points : " + customerRole.getBonusPoints());
	       }
		   System.out.println();


	   }
	   
	   // Non Loop access example 
	       
/*		      Volunteer volunteerRole  = (Volunteer) membere.getRole("volunteer");       
		       if (volunteerRole != null) {
		    	   System.out.println("Credits : " + volunteerRole.getCredits());
		       }
		       
			  Customer customerRole  = (Customer) membere.getRole("Customer");       
			    if (customerRole != null) {
			       System.out.println("Bonus Points : " + customerRole.getBonusPoints());
			    }   

*/
	   }



  
  }