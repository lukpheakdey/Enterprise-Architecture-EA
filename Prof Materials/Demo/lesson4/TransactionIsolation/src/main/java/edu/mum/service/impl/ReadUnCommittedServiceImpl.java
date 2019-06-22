package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_UNCOMMITTED)
public class ReadUnCommittedServiceImpl implements edu.mum.service.ReadUnCommittedService {

	@Autowired 
	MemberService memberService;

	public void readUncommitted(Member member) {
		Member memberRead = memberService.findOne(member.getId());
		System.out.println( " DIRTY Read UnCommitted Member Number: " + memberRead.getMemberNumber() + "- [this is BAD - should be 1]");
	}

}
