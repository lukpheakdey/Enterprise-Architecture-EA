package edu.mum.builder.instance;

import edu.mum.builder.AuthorityBuilder;
import edu.mum.builder.MemberBuilder;
import edu.mum.builder.UserCredentialsBuilder;

public class SeanMemberBuilder {

	private  MemberBuilder seanBuilder;
	
	public SeanMemberBuilder () {
		seanBuilder = new MemberBuilder()
        .withMemberNumber(1)
        .withUserCredentials(new UserCredentialsBuilder()
                .withUsername("JohnDoe")
                .withPassword("DoeNuts")
                .withAuthority(new AuthorityBuilder()
                		.withUsername("JohnDoe")
                		.withAuthority("ROLE_USER")
                		.build())
                .withAuthority(new AuthorityBuilder()
                		.withUsername("JohnDoe")
                		.withAuthority("ROLE_ADMIN")
                		.build())
                .withAuthority(new AuthorityBuilder()
                		.withUsername("JohnDoe")
                		.withAuthority("ROLE_SUPERVISOR")
                		.build())
               .build())
        .withFirstName("Sean")
			.withLastName("Smith");
	}
	
  	    public  MemberBuilder getMemberBuilderInstance() {
	            return seanBuilder;
	    }

	
}
