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

    public UserCredentialsBuilder withAuthority(Authority authority) {
        this.userCredentials.getAuthority().add(authority);
        return this;
    }

      public UserCredentials build() {
        return userCredentials;
    }

	
}
