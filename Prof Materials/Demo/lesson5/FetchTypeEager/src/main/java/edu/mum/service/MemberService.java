package edu.mum.service;

import java.util.List;
import java.util.Set;

import edu.mum.domain.Member;
 
public interface MemberService {

	public void save(Member member);
	public void update(Member member);
	public List<Member> findAll();
	public Member findByMemberNumber(Integer memberId);

	public Member findOne(Long id);
	public Member findOneFull(Long id);
	
	public List<Member> findAllJoinFetch();
	public List<Member> findByGraph();
		
}
