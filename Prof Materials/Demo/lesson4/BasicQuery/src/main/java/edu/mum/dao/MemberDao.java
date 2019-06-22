package edu.mum.dao;

import java.util.List;

import edu.mum.domain.Member;

public interface MemberDao extends GenericDao<Member> {
      
	public Member findByMemberNumber(Integer number);
	public Member findByNativeQuery(Integer number);
	public Member findByNamedQuery(Integer number);

	public List<Member> findByAddressState(String state,Integer age);

	}
