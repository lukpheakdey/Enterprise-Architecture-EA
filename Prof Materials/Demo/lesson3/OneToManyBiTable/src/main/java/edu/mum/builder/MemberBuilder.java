package edu.mum.builder;

import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.domain.UserCredentials;

public class MemberBuilder {

     private Member member;
    
 	public MemberBuilder() {
		this.member = new Member();
	}

 	
    public MemberBuilder withUserCredentials(UserCredentials credentials) {
        this.member.setUserCredentials(credentials);
        return this;
    }

    public MemberBuilder withAddress(Address address) {
        this.member.getAddresses().add(address);
        return this;
    }

    public MemberBuilder withFirstName(String name) {
        this.member.setFirstName(name);
        return this;
    }

    public MemberBuilder withLastName(String name) {
        this.member.setLastName(name);
        return this;
    }

    public MemberBuilder withMemberNumber(Integer number) {
        this.member.setMemberNumber(number);
        return this;
    }


    public MemberBuilder withAge(Integer age) {
        this.member.setAge(age);
        return this;
    }

    public MemberBuilder withId(Long id) {
        this.member.setId(id);
        return this;
    }

    public Member build() {
        return member;
    }

	
}
