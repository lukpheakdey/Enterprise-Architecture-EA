package edu.mum.builder;

import edu.mum.domain.Authority;
import edu.mum.domain.User;
import edu.mum.domain.UserCredentials;

public class UserCredentialsBuilder {

	private UserCredentials userCredentials;
  
	public UserCredentialsBuilder() {
		this.userCredentials = new UserCredentials();
	}
	
    public UserCredentialsBuilder withUserName(String name) {
        this.userCredentials.setUserName(name);
        return this;
    }

    public UserCredentialsBuilder withPassword(String password) {
        this.userCredentials.setPassword(password);
        return this;
    }

    public UserCredentialsBuilder withVerifyPassword(String password) {
        this.userCredentials.setVerifyPassword(password);
        return this;
    }

    public UserCredentialsBuilder withAuthority(Authority authority) {
        this.userCredentials.getAuthority().add(authority);
        return this;
    }

    public UserCredentialsBuilder withUser(User user) {
        this.userCredentials.addUser(user);
        return this;
    }


    public UserCredentialsBuilder withEnabled(Boolean enabled) {
        this.userCredentials.setEnabled(enabled);
        return this;
    }


      public UserCredentials build() {
        return userCredentials;
    }

	
}
