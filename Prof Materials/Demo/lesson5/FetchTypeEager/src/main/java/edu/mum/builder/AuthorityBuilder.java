package edu.mum.builder;

import edu.mum.domain.Authority;
import edu.mum.domain.UserCredentials;

public class AuthorityBuilder {

     private Authority authority;
    
 	public AuthorityBuilder() {
		this.authority = new Authority();
	}

 	
    public AuthorityBuilder withUsername(String name) {
        this.authority.setUsername(name);
        return this;
    }

 
    public AuthorityBuilder withAuthority(String authority) {
        this.authority.setAuthority(authority);
        return this;
    }

    public AuthorityBuilder withId(Long id) {
        this.authority.setId(id);
        return this;
    }

    public Authority build() {
        return authority;
    }

	
}
