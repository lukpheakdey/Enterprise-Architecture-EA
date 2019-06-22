package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;
import edu.mum.service.TransactionService;
import edu.mum.service.TransactionServiceNew;

@Service
//Change to READ_COMMITTED for Phantom...
@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED ) 
//@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ) 
public class RepeatableReadServiceImpl implements edu.mum.service.RepeatableReadService {

	@Autowired 
	MemberService memberService;

	@Autowired 
	TransactionService transactionService;
	
	@Autowired 
	TransactionServiceNew transactionServiceNew;
	
	public void repeatableRead(Member member) {
		Member memberRead = memberService.findOne(member.getId());
		System.out.println( "Repeatable BEFORE COMMIT Member Number: " + memberRead.getMemberNumber());

		try {
		transactionServiceNew.nonRepeatableSave(member,100);
	 }
    catch (ObjectOptimisticLockingFailureException exception) {
    	System.out.println("Optimistic Locking Failed Exception" );
    }
		
		// Since REPEATABLE - This should be the SAME as the before value 
		 memberService.refresh(memberRead);
		System.out.println( "Repeatable AFTER COMMIT Member Number: " + memberRead.getMemberNumber()+ "- [this is GOOD]");
	
	}

	// TODO: implement phantom...
	public void phantomRead() {
		List<Member> memberRead = memberService.findAll();

		// Get "middle" member
		Member member = memberRead.get(3);
  	    System.out.println("Transaction Service  BEFORE Phantom Modify Member Name: " + member.getFirstName() + " "  	    		
		   +   member.getLastName() + " ID: " + member.getId() );

  	    String beforeFirstName = member.getFirstName();
  	    String beforeLastName = member.getLastName();
 		transactionServiceNew.phantomSave( );

		// Clear Cache
 		memberService.clear();
		memberRead = memberService.findAll();

		// After Phantom - This should be the Different from before value 
	    member = memberRead.get(3);
  	    System.out.println("Transaction Service AFTER Phantom Modify Member Name: " + member.getFirstName() + " " 
  			   +   member.getLastName() + " ID: " + member.getId() );
 	    System.out.println("IF BEFORE Name: " + beforeFirstName + " " + beforeLastName + " is DIFFERENT from AFTER Name: " 
   			   +   member.getFirstName() + " " +   member.getLastName() + " then Phantom READ" );
 	    		  
 
 	}


}
