package edu.mum.dao;

import edu.mum.domain.Member;

public interface MemberDao extends GenericDao<Member> {
      
	public Member findByMemberNumber(Integer number);
	public void flush();
	public void refresh(Member member);
	public void clear();
}
