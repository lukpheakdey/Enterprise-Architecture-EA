package edu.mum.service;

import java.util.List;

import edu.mum.domain.Member;
 
public interface MemberService {

	public void save(Member member);
	public void update(Member member);
	public List<Member> findAll();
	public Member findOne(Long id);
	public Member findByMemberNumber(Integer memberId);
	
	public void flush();
	public void refresh(Member member);
	public void clear();
}
