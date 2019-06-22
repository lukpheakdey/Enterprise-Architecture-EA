package edu.mum.service;

import edu.mum.domain.Member;
 
public interface ReadCommittedService {

	public void readCommitted(Member member);
	public void nonRepeatableRead(Member member);

 }
