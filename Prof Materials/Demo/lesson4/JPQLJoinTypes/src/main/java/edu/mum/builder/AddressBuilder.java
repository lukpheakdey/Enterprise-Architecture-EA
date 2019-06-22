package edu.mum.builder;

import edu.mum.domain.Address;
import edu.mum.domain.Member;

public class AddressBuilder {

     private Address address;
    
 	public AddressBuilder() {
		this.address = new Address();
	}

 	
    public AddressBuilder withUsername(String street) {
        this.address.setStreet(street);
        return this;
    }

 
    public AddressBuilder withCity(String city) {
        this.address.setCity(city);
        return this;
    }

    public AddressBuilder withState(String state) {
        this.address.setState(state);
        return this;
    }

    public AddressBuilder withZipCode(String zipCode) {
        this.address.setZipCode(zipCode);
        return this;
    }

    public AddressBuilder withMember(Member member) {
        this.address.setMember(member);
        return this;
    }

    public AddressBuilder withId(Long id) {
        this.address.setId(id);
        return this;
    }

    public Address build() {
        return address;
    }

	
}
