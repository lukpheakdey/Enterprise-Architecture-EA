package edu.mum.main;


import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Address;
import edu.mum.domain.Member;
import edu.mum.domain.Order;
import edu.mum.service.AddressService;
import edu.mum.service.MemberService;

 		
public class Main {
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "context/applicationContext.xml");

    MemberService memberService = (MemberService) ctx.getBean("memberServiceImpl");

    Address address = new Address();
    address.setState("Iowa");
      
    Address address2 = new Address();
    address2.setState("NewYork");
      
    Address address3 = new Address();
    address3.setState("Texas");
      
    Member member = new Member();
    member.setFirstName("Sean");
    member.setLastName("Smith");
    member.setAge(22);
    member.setMemberNumber(1);

    member.addAddress(address);
    member.addAddress(address2);

    memberService.save(member);
     
    Member member2 = new Member();
    member2.setFirstName("John");
    member2.setLastName("Doe");
    member2.setMemberNumber(6);
    member2.setAge(12);

    address = new Address();
    address.setState("Iowa");

    member2.addAddress(address);
    member2.addAddress(address3);

    memberService.save(member2);
     
 
    Member findMember = memberService.findByMemberNumber(member.getMemberNumber());
    System.out.println();
    System.out.println("                      Find by JPQL Query");
  	System.out.println("Member Name : " + findMember.getFirstName() + "  " +  findMember.getLastName() );

     findMember = memberService.findByNamedQuery(member.getMemberNumber());
    System.out.println();
    System.out.println("                      Find by Named Query");
  	System.out.println("Member Name : " + findMember.getFirstName() + "  " +  findMember.getLastName() );

  	findMember = memberService.findByNativeQuery(member.getMemberNumber());
  	System.out.println();
  	System.out.println("                      Find by Native Query");
  	System.out.println("Member Name : " + findMember.getFirstName() + 
  			"  " +  findMember.getLastName() + "  Age: " +  findMember.getAge());

    List<Member> findMembers = memberService.findByAddressState("Iowa",18);
    System.out.println();
    System.out.println("                      Find with subQuery -- using 'member of' ");
  	System.out.println("Member Name : " + findMembers.get(0).getFirstName() + "  " +  findMember.getLastName() );
  	for (Address addres : findMembers.get(0).getAddresses()) 
  	  	System.out.println("State : " + addres.getState() );

	
  	
    }

 
  
  }