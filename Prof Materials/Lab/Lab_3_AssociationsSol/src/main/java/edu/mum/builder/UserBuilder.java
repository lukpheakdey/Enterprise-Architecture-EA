package edu.mum.builder;

import edu.mum.domain.Address;
import edu.mum.domain.User;
import edu.mum.domain.UserCredentials;

public class UserBuilder {

     private User user;
    
 	public UserBuilder() {
		this.user = new User();
	}

 	
    public UserBuilder withUserCredentials(UserCredentials credentials) {
        this.user.setUserCredentials(credentials);
        return this;
    }

    public UserBuilder withAddress(Address address) {
        this.user.addAddress(address);
        return this;
    }

    public UserBuilder withFirstName(String name) {
        this.user.setFirstName(name);
        return this;
    }

    public UserBuilder withLastName(String name) {
        this.user.setLastName(name);
        return this;
    }

    public UserBuilder withUserNumber(Integer number) {
        this.user.setRanking(number);
        return this;
    }


    public UserBuilder withAdmin(Boolean admin) {
        this.user.setAdmin(admin);
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder withId(Long id) {
        this.user.setId(id);
        return this;
    }

    public User build() {
        return user;
    }

	
}
