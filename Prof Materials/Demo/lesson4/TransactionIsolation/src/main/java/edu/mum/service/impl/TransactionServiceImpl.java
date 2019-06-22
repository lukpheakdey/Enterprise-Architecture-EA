package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.MemberDao;
import edu.mum.domain.Member;
import edu.mum.service.ReadUnCommittedService;
import edu.mum.service.MemberService;
import edu.mum.service.ReadCommittedService;

@Service
@Transactional(rollbackFor={Exception.class})
public class TransactionServiceImpl implements edu.mum.service.TransactionService {
 	@Autowired
	private MemberService memberService;
	@Autowired
	private ReadUnCommittedService readUnCommittedService;

	@Autowired
	private ReadCommittedService readCommittedService;

	public void readUncommitted(Member member) throws Exception {
		member.setMemberNumber(12);
		memberService.update(member);
		 memberService.flush();
	    System.out.println("Transaction Service Member Number: " + member.getMemberNumber());
	
	    readUnCommittedService.readUncommitted(member);

		throw(new  Exception());
	}

	public void readCommitted(Member member) throws Exception {
		member.setMemberNumber(12);
		memberService.update(member);
		memberService.flush();
	    System.out.println("Transaction Service Member Number: " + member.getMemberNumber());
	
	    readCommittedService.readCommitted(member);

		throw(new  Exception());
	}

	public void nonRepeatableSave(Member member, Integer number){
		member.setMemberNumber(number);
		memberService.update(member);
//		memberService.flush();
	    System.out.println("Transaction Service  NON Repeatable Member Number: " + member.getMemberNumber());
	
 	}

	public void phantomSave(Member member){
		member.setMemberNumber(777);
		memberService.update(member);
//		memberService.flush();
	    System.out.println("Transaction Service  Phantom Member Number: " + member.getMemberNumber());
	
 	}

 
}
