package edu.mum.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;
import edu.mum.service.ReadCommittedService;
import edu.mum.service.RepeatableReadService;
import edu.mum.service.TransactionService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");
    TransactionService transactionService = (TransactionService) ctx.getBean("transactionServiceImpl");
    ReadCommittedService readCommittedService = (ReadCommittedService) ctx.getBean("readCommittedServiceImpl");
    RepeatableReadService repeatableReadService = (RepeatableReadService) ctx.getBean("repeatableReadServiceImpl");

     Member member = new Member();
    member.setFirstName("John");
    member.setLastName("Doe");
    member.setMemberNumber(1);
    memberService.save(member);
  
    System.out.println("Member Number: " + member.getMemberNumber());

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    System.out.println();
    System.out.print("*************HIT RETURN To DEMO Read UnCommitted - Dirty Read*********************::   ");
    System.out.println();
    try {
		in.readLine();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  
    try {
    	transactionService.readUncommitted(member);
    }
    catch (Exception e) {System.out.println("Rollback Exception - purposely thrown in readUncommitted!!"); }
    
    Member readMember = memberService.findByMemberNumber(1);
    System.out.println("Member Number: " + readMember.getMemberNumber());

    
    
    System.out.println();
    System.out.print("*************HIT RETURN To DEMO Read Committed - NO Dirty Read*********************::   ");
    System.out.println();
    try {
		in.readLine();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 
     try {
    	transactionService.readCommitted(member);
    }
    catch (Exception e) {System.out.println("Rollback Exception - purposely thrown in readCommitted!!"); }
    
    readMember = memberService.findByMemberNumber(1);
    System.out.println("Member Number: " + readMember.getMemberNumber());
 
    
    System.out.println();
    System.out.print("*************HIT RETURN To DEMO Read Committed - NON Repeatable Read*********************::   ");
    System.out.println();
    try {
		in.readLine();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

    try {
    	readCommittedService.nonRepeatableRead(member);
    }
    catch (Exception e) {
    	System.out.println("Rollback Exception!! - " + e.getMessage()); 
    	}

    
    
    System.out.println();
    System.out.print("*************HIT RETURN To DEMO Repeatable Read  - There is NO non-repeatable read  *********************::   ");
    System.out.println();
    try {
		in.readLine();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
 try {
	 repeatableReadService.repeatableRead(member);
 }
    catch (ObjectOptimisticLockingFailureException exception) {
    	System.out.println("Optimistic Locking Failed Exception" );
    }
    
    System.out.println();
    System.out.print("*************HIT RETURN To DEMO Repeatable Read  - Phantom read  *********************::   ");
    System.out.println();
    try {
		in.readLine();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 
    // Add 4 more members... total = 5
    member = new Member();
    member.setFirstName("Jim");
    member.setLastName("Dandy");
    member.setMemberNumber(2);
    memberService.save(member);
  
    member = new Member();
    member.setFirstName("Joan");
    member.setLastName("Rivers");
    member.setMemberNumber(3);
    memberService.save(member);
  
    member = new Member();
    member.setFirstName("Bill");
    member.setLastName("Bixby");
    member.setMemberNumber(4);
    memberService.save(member);
  
    member = new Member();
    member.setFirstName("Lou");
    member.setLastName("Ferringo");
    member.setMemberNumber(5);
    memberService.save(member);
  
    repeatableReadService.phantomRead();

    
  }
  
  
  }