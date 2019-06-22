package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.MemberDao;
import edu.mum.domain.Member;

@Service
@Transactional 
public class MemberServiceImpl implements edu.mum.service.MemberService {
	
 	@Autowired
	private MemberDao memberDao;

 /*   @Autowired
    public void setDao( GenericDao<MemberDao> memberDao ){
    	this.memberDao = memberDao;
    	memberDao.setDaoType(Member.class);
    }

*/ 	
     public void save( Member member) {  		
  		memberDao.save(member);
 	}
  	
  	
	public List<Member> findAll() {
		return (List<Member>)memberDao.findAll();
	}

	public Member findByMemberNumber(Integer memberId) {
/*		Member member = memberDao.findByMemberNumber(memberId);
		System.out.println("First name " + member.getFirstName());
		member.setFirstName("XXXX");
		member = memberDao.findOne(member.getId());
		System.out.println("First name " + member.getFirstName());

*/	
		return memberDao.findByMemberNumber(memberId);
	}
 

}
