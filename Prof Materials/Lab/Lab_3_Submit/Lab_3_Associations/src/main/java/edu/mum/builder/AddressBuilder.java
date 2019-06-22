package edu.mum.builder;

import edu.mum.domain.Address;

public class AddressBuilder {

     private Address address;
    
 	public AddressBuilder() {
		this.address = new Address();
	}

 	
    public AddressBuilder withStreet(String street) {
        this.address.setStreet(street);
        return this;
    }

 
    public AddressBuilder withCity(String city) {
        this.address.setCity(city);
        return this;
    }

     public AddressBuilder withZipCode(String zipCode) {
        this.address.setZipcode(zipCode);
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
