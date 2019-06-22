package edu.mum.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.builder.AddressBuilder;
import edu.mum.builder.MemberBuilder;
import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.service.AddressService;
import edu.mum.service.MemberService;

@Component
public class TestData {

	@Autowired
	MemberService memberService;
	
	@Autowired
	AddressService addressService;
	

	public void setupData() {
	
		// Sean
 		Member member =  new MemberBuilder()
				.withFirstName("Sean")
				.withLastName("Smith")
				.withAddress(new AddressBuilder()
						.withCity("Batavia")
						.withState("Iowa")
				 		.build())
				.withAddress(new AddressBuilder()
						.withCity("Red Rock")
						.withState("Iowa")
			 			.build())
				.withMemberNumber(1)
				.build();    
	    memberService.save(member);
		
	    // Peat
	    // No Addresses - will show ONLY in  Left Outer Join
 		member =  new MemberBuilder()
				.withFirstName("Peat")
				.withLastName("Moss")
				.withMemberNumber(2)
				.build();    
	    memberService.save(member);
		
	    // Bill
 		member =  new MemberBuilder()
				.withFirstName("Bill")
				.withLastName("Due")
				.withAddress(new AddressBuilder()
						.withCity("Washington")
						.withState("Iowa")
				 		.build())
				.withAddress(new AddressBuilder()
						.withCity("Mexico")
						.withState("Iowa")
			 			.build())
				.withAddress(new AddressBuilder()
						.withCity("Paris")
						.withState("Iowa")
			 			.build())
				.withMemberNumber(3)
				.build();    
	    memberService.save(member);
			
	    // "Extra" Address
		Address address = new AddressBuilder()
		    		.withCity("Russia")
		    		.withState("Iowa")
		    		.build();
 		    addressService.save(address);
	    
	}
	
}
