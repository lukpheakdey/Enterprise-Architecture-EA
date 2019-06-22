package edu.mum.domain;

import javax.persistence.Entity;

@Entity
//@DiscriminatorValue        // Since single table this Identifies which subclass [DEFAULT is class name]
							 // Only need it if NOT using Class Name
public class Volunteer extends MemberRole {
/*  NO PK since single table
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="memberrole_id")
	private long id;
*/
	public Volunteer() {}
			
	public Volunteer(String nickName, Integer credits, Integer yearsOfService) {
		super.setNickName(nickName);
		super.setType("Volunteer");
		this.setCredits(credits);
		this.setYearsOfService(yearsOfService);
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
