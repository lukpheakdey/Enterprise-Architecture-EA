package edu.mum.service;

import edu.mum.domain.Member;
 
public interface TransactionServiceNew {

//	public void readUncommitted(Member member) throws Exception;
//	public void readCommitted(Member member) throws Exception;
	public void nonRepeatableSave(Member member,Integer number);
	public void phantomSave();
	//	public void phantomSave(Member member);

 }
