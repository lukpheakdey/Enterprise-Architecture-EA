package edu.mum.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity 
@NamedEntityGraph(name = "graph.Member.addresses", 
attributeNodes = { @NamedAttributeNode(value = "addresses",subgraph = "addresses") }  ,
		subgraphs = @NamedSubgraph(name = "addresses", 
				attributeNodes = { @NamedAttributeNode("city"), @NamedAttributeNode("state") }))

public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="member_id")
	private long id;
	
	@Column(length = 16)
	private String firstName;
	@Column(length = 16)
	private String lastName;
 	private int age;
	@Column(length = 32)
	private String title;
 	private int memberNumber;

/*	@OneToOne(fetch=FetchType.LAZY,  cascade = CascadeType.ALL) 
	@JoinColumn(name="member_id") 
*/
 	@Transient
 	UserCredentials userCredentials;
 	
/*    @OneToMany(fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
*/
 	@Transient
 	private List<Order> orders =  new ArrayList<Order>();

     @OneToMany(mappedBy="member",fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
 	private Set<Address> addresses = new HashSet<Address>();

    @OneToMany(mappedBy="member",fetch=FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<MemberRole> memberRoles = new ArrayList<MemberRole>();
 	
 	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
 	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	
 	public UserCredentials getUserCredentials() {
		return userCredentials;
	}
	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
	public Set<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
		address.setMember(this);
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
 	}
	public List<MemberRole> getMemberRoles() {
		return memberRoles;
	}
	public void setMemberRoles(List<MemberRole> memberRoles) {
		this.memberRoles = memberRoles;
	}
	
	public void addRole(MemberRole memberRole) {
		memberRole.setMember(this);
		this.memberRoles.add(memberRole);
		};
		
	public  MemberRole getRole (String role) {
			
		for (MemberRole memberRole : memberRoles)
			if (memberRole.isType(role)) return memberRole;

			return null;
		}
		



 }
