package edu.mum.dao;

import java.util.List;
import java.util.Set;

import edu.mum.domain.Member;

public interface MemberDao extends GenericDao<Member> {
      
	public Member findByMemberNumber(Integer number);
	public List<Member> findAllJoinFetch();
	public List<Member> findByGraph();
	}
