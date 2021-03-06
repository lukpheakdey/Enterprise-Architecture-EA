package edu.mum.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

 
@Entity(name = "CREDENTIALS")
public class UserCredentials {

	 @Id
	 @Column(nullable = false, unique = true)
 	String username;
	 @Column(nullable = false)
	String password;
	String verifyPassword;
	Boolean enabled;

   @Transient
	List<Authority> authority;

   //mappedBy="userCredentials" if you remove mappedBy - you'll get foreign key
   // on both sides
	@OneToOne(mappedBy="userCredentials", cascade = CascadeType.ALL) 
 	private Member member;
	
 	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyPassword() {
		return verifyPassword;
	}
	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<Authority> getAuthority() {
		return authority;
	}
	public void setAuthority(List<Authority> authority) {
		this.authority = authority;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
 	public void addMember(Member member) {
 		this.member = member;
 		member.setUserCredentials(this);
 	}
}
