package edu.mum.main;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;

public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = 
    		new ClassPathXmlApplicationContext("context/applicationContext.xml");

    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");

    List<Member> list = memberService.findAll();
    System.out.println("Member count: " + list.size());

    Member member = new Member();
    member.setFirstName("John");
    member.setLastName("Doe");
    member.setMemberNumber(1);
    memberService.save(member);
    System.out.println("Member inserted!");

    list = memberService.findAll();
    System.out.println("Member count: " + list.size());
    
    Member readMember = memberService.findByMemberNumber(1);
    
    System.out.println();
    System.out.println("        *********  MEMBER **********");

    System.out.println("Member Name: " + readMember.getFirstName() + " " + readMember.getLastName());
 }
  
  
  }