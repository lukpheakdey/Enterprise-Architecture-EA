package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;
import edu.mum.service.TransactionService;
import edu.mum.service.TransactionServiceNew;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
public class ReadCommittedServiceImpl implements edu.mum.service.ReadCommittedService {

	@Autowired 
	MemberService memberService;

	@Autowired 
	TransactionService transactionService;
	
	@Autowired 
	TransactionServiceNew transactionServiceNew;
	
	public void readCommitted(Member member) {
		Member memberRead = memberService.findOne(member.getId());
		System.out.println( "Read Committed Member Number: " + memberRead.getMemberNumber() + " - [this is GOOD-NO Dirty READ]");
	}

	public void nonRepeatableRead(Member member) {
		Member memberRead = memberService.findOne(member.getId());
		System.out.println( "Non Repeatable BEFORE COMMIT Member Number: " + memberRead.getMemberNumber());
 	
		transactionServiceNew.nonRepeatableSave(member,44);
		
		// IF REPEATABLE - This would be the SAME as the before value IF REPEATABLE
		 memberService.refresh(memberRead);
		System.out.println( "Non Repeatable AFTER COMMIT Member Number: " + memberRead.getMemberNumber() + "- [this is BAD - should be 1]");
	
	}

}
