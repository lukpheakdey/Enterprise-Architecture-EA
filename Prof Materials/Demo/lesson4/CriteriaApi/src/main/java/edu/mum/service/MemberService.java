package edu.mum.service;

import java.util.List;

import edu.mum.domain.Member;
 
public interface MemberService {

	public void save(Member member);
	public void delete(Member member);
	public List<Member> findAll();
	public Member findByMemberNumber(Integer memberId);

	public Member findOne(Long id);
	public Member findOneFull(Long id);
	
	public List<Member> findMemberCriteria(Integer number, String firstName, String lastName);

}
