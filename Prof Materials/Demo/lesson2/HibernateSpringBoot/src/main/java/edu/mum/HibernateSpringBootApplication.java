package edu.mum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import edu.mum.domain.Member;
import edu.mum.service.MemberService;

@SpringBootApplication
public class HibernateSpringBootApplication {

	  @Autowired
	  MemberService memberService;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(HibernateSpringBootApplication.class, args);
	      applicationContext.getBean(HibernateSpringBootApplication.class).mainInternal(applicationContext);
		 }
		 
		   private void mainInternal(ApplicationContext applicationContext) {

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
