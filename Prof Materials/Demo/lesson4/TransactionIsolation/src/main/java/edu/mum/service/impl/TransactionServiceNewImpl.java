package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;
import edu.mum.service.TransactionServiceNew;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={Exception.class})
public class TransactionServiceNewImpl implements TransactionServiceNew {
 	@Autowired
	private MemberService memberService;
 

	public void nonRepeatableSave(Member member, Integer number){
		member.setMemberNumber(number);
		memberService.update(member);
 	    System.out.println("Transaction Service  NON Repeatable Member Number: " + member.getMemberNumber());
	
 	}
	
	public void phantomSave(){
 
		// Get Middle member & MODIFY
		Member  member = memberService.findOne(4L);

		    member.setFirstName("Jimmy");
		    member.setLastName("Durante");
		    member.setMemberNumber(7);
		    memberService.update(member);
	  	    System.out.println("Transaction Service  Phantom Member Name: " + member.getFirstName() + " " 
			   +   member.getLastName() + " ID: " + member.getId() );

  	}



  	}

 
