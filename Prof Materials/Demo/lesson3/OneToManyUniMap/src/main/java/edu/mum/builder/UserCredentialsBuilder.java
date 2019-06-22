package edu.mum.builder;

import edu.mum.domain.Authority;
import edu.mum.domain.UserCredentials;

public class UserCredentialsBuilder {

	private UserCredentials userCredentials;
  
	public UserCredentialsBuilder() {
		this.userCredentials = new UserCredentials();
	}
	
    public UserCredentialsBuilder withUsername(String name) {
        this.userCredentials.setUsername(name);
        return this;
    }

    public UserCredentialsBuilder withPassword(String password) {
        this.userCredentials.setPassword(password);
        return this;
    }

    // Create map entry ...use lowercase authority name "minus" ROLE_  [e.g. ROLE_ADMIN has admin as key]
    public UserCredentialsBuilder withAuthority(Authority authority) {
    	String key = authority.getAuthority();
    	key = key.substring(key.indexOf('_')+1);
        this.userCredentials.getAuthority().put(key.toLowerCase(),authority);
        return this;
    }

      public UserCredentials build() {
        return userCredentials;
    }

	
}
