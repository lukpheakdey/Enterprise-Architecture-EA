package edu.mum.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import edu.mum.validation.EmptyOrSize;
import edu.mum.validation.NullMinNumber;


@Entity 
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="member_id")
	private Long id;
	
	@Column(length = 16)
	@NotEmpty
	private String firstName;
	
	@Column(length = 16)
	@EmptyOrSize(min=5, max = 9, message= "{EmptyOrSize}")
	private String lastName;

	@NullMinNumber(value=6)
	private Integer age;
 	
	@Column(length = 32)
	@NotEmpty
	private String title;
	
	@NullMinNumber(value=6)
 	private Integer memberNumber;

	@OneToOne(fetch=FetchType.EAGER,  cascade = CascadeType.ALL) 
 	@JoinColumn(name="memberId") 
 	private UserCredentials userCredentials;
 	
    @Valid
//  @Size(min = 1, message="{List.empty}" )
   @OneToOne(mappedBy="member",fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })  
   private  Address address = new Address();  

  
    @OneToMany(mappedBy="supervisor",fetch=FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })  
    private Set<Member> admins = new HashSet<Member>();

    @ManyToOne( fetch=FetchType.EAGER)  
    private Member supervisor;

    @SuppressWarnings("rawtypes")
/*    @OneToMany(fetch=FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })  
    @Fetch(FetchMode.SELECT)
*/
    @ElementCollection
    private List<Long> workQueue = new ArrayList<Long>();
    
    
 	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
 	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}
	
 	public UserCredentials getUserCredentials() {
		return userCredentials;
	}
	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
	public Set<Member> getAdmins() {
		return admins;
	}
	public void setAdmins(Set<Member> admins) {
		this.admins = admins;
	}
 	public void addAddress(Member admin) {
		this.admins.add(admin);
		admin.setSupervisor(this);
	}
	public Member getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Member supervisor) {
		this.supervisor = supervisor;
	}
	public   List<Long> getWorkQueue() {
		return   workQueue;
	}
	public void setWorkQueue(List<Long> workQueue) {
		this.workQueue = workQueue;
	}
  }
