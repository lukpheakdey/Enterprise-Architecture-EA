package edu.mum.service;

import edu.mum.domain.Member;
 
public interface RepeatableReadService {

	public void repeatableRead(Member member);
	public void phantomRead();

 }
