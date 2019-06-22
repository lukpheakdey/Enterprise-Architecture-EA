package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.builder.AddressBuilder;
import edu.mum.builder.MemberBuilder;
import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.service.AddressService;
import edu.mum.service.MemberService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    AddressService addressService = (AddressService) ctx.getBean("addressServiceImpl");
    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");

  
     	  Address address =  new AddressBuilder()
    		   .withCity("Batavia")
    		   .withState("Iowa")
     		   .build();
    	  addressService.save(address);

    	 Member member =  new MemberBuilder()
    	      	.withFirstName("Sean")
    	    	.withLastName("Smith")
    	    	.withAddress(address)
    	    	.withMemberNumber(1)
    	    	.build();     	      
 
     // Address is NOT updated with FK - so will NOT be saved
    // good practice - SET BOTH SIDES -
    	 //   	 ADD THIS so when Address is saved [cascade] it will have Member FK:
    	 //		address.setMember(member);
    	 member = memberService.update(member);
   
   member = memberService.findOne(member.getId());
   
   System.out.println();
   System.out.println("***********       Save Member ONLY - saves address  BUT no FK **************");

   System.out.println("Member Name : " + member.getFirstName() + "  " +  member.getLastName() );

   // No Addresses will Show up
   for (Address addresse : member.getAddresses()) {
       System.out.println("Address : " + addresse.getCity() + 
				"   " + addresse.getState());
   }


 //--------------------------------------------------------------------
  
	  address =  new AddressBuilder()
			.withCity("Red Rock")
			.withState("Iowa")
			.withMember(member)
			.build();
     
    // will Have relationship WHEN saving side with Foreign key
    // HOWEVER BEST practice - SET BOTH SIDES -
    addressService.save(address);
   
    member = memberService.findOne(member.getId());

    System.out.println();
    System.out.println("***********       Save Address ONLY - saves BOTH **************");
    System.out.println("Member Name : " + member.getFirstName() + "  " +  member.getLastName() );

    // Since saved on Foreign Key side - both sides will be saved...
    for (Address addresse : member.getAddresses()) {
        System.out.println("Address : " + addresse.getCity() + 
 				"   " + addresse.getState());
    }


  }
  
  }