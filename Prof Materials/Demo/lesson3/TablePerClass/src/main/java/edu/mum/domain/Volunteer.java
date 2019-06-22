package edu.mum.domain;

import javax.persistence.Entity;

// Has MemberRole & Volunteer in Volunteer DB TABLE
@Entity
 public class Volunteer extends MemberRole {
/*
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="memberrole_id")
	private long id;
*/
	public Volunteer() {}
			
	public Volunteer(String nickName, Integer credits) {
		super.setNickName(nickName);
		super.setType("Volunteer");
		this.setCredits(credits);
	}

//	private List<> tasks;
	
	private Integer yearsOfService;
	private Integer credits;
	
/*	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

*/
	public Integer getYearsOfService() {
		return yearsOfService;
	}

	public void setYearsOfService(Integer yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}
}
