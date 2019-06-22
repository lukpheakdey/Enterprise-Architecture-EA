package edu.mum.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.builder.AddressBuilder;
import edu.mum.builder.AuthorityBuilder;
import edu.mum.builder.MemberBuilder;
import edu.mum.builder.UserCredentialsBuilder;
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

    Member member =  new MemberBuilder()
    	.withFirstName("Sean")
		.withLastName("Smith")
	    .withMemberNumber(1)
	    .build();    
  
	    // NOTE in Builder: BOTH sides are set in Address class through addMember "convenience" method...
	  Address address =  new AddressBuilder()
		   .withCity("Batavia")
		   .withState("Iowa")
		   .withMember(member)
		   .build();
	  addressService.save(address);
	 
	  address =  new AddressBuilder()
			.withCity("Red Rock")
			.withState("Iowa")
			.withMember(member)
			.build();
   
    // Need to UPDATE this SECOND address - even though NEVER saved...This way it will MERGE Member 
    // OTHERWISE with SAVE will fail on PERSIST of Member [since it is detached]
    addressService.update(address);

	  address =  new AddressBuilder()
			.withCity("Fairfield")
			.withState("Iowa")
			.withMember(member)
			.build();
	addressService.update(address);
 
    memberService.findOne(member.getId());
    
    System.out.println();
    System.out.println("Member Name : " + member.getFirstName() + "  " +  member.getLastName() );
    for (Address addresse : member.getAddresses()) {
        System.out.println("Address : " + addresse.getCity() + 
				"   " + addresse.getState());
    }

 
  }
  
  }