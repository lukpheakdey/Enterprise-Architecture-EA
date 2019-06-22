package edu.mum.validation.aop.dto;

import edu.mum.domain.Member;
import edu.mum.domain.ProductionStatus;

public class MemberValidationDtoImpl implements ValidationDto{

	private Member member;
	
	MemberValidationDtoImpl() {}

	public MemberValidationDtoImpl(Member member) {
		this.setMember(member);
	}
	
	@Override
	public Long getId() {
		return member.getId();
 	}

	@Override
	public ProductionStatus getStatus() {
		return null;
 	}

	@Override
	public void setStatus(ProductionStatus status) {
		;
		
	}

	@Override
	public Object getValidationObject() {
		 
		return member;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
