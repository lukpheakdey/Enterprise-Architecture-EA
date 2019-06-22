package edu.mum.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

// Table in DB is empty
@Entity 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 public  class MemberRole {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="memberrole_id")
	private long id;
	
	private String nickName;
	
	private String type;
	
	@ManyToOne
	private Member member;
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public  boolean isType (String value) {
		
		if (type.equalsIgnoreCase(value)) return true;
		
		return false;
	}
	
	
	
	
	

}
