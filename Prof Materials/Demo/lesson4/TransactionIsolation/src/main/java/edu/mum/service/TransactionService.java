package edu.mum.service;

import edu.mum.domain.Member;
 
public interface TransactionService {

	public void readUncommitted(Member member) throws Exception;
	public void readCommitted(Member member) throws Exception;
	public void nonRepeatableSave(Member member,Integer number);
	public void phantomSave(Member member);

 }
