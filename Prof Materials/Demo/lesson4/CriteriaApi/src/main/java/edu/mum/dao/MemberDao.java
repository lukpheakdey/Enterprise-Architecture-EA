package edu.mum.dao;

import java.util.List;

import edu.mum.domain.Member;

public interface MemberDao extends GenericDao<Member> {
      
	public Member findByMemberNumber(Integer number);
	public List<Member> findMemberCriteria(Integer number, String firstName, String lastName);

}
