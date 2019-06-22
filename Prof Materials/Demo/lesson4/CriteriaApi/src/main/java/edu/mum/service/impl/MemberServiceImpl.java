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
 
 	public void save( Member member) {  		
  		memberDao.save(member);
 	}
  	
  	
 	public void delete( Member member) {  		
  		memberDao.delete(member.getId());
 	}
  	
  	
	public List<Member> findAll() {
		return (List<Member>)memberDao.findAll();
	}

	public Member findByMemberNumber(Integer memberId) {
		return memberDao.findByMemberNumber(memberId);
	}
 
	public Member findOne(Long id) {
		return memberDao.findOne(id);
	}
	
	public Member findOneFull(Long id) {
		Member member = this.findOne(id);
		
		// Since we are in the Session "holding area" AKA Cache
		// We can reference the Credentials...
		// Alternative:	"SELECT p FROM Member m JOIN FETCH m.userCredentials WHERE m.id = (:id)"
		member.getUserCredentials();
		
		return  member;
	}
	
	public List<Member> findMemberCriteria(Integer number, String firstName, String lastName) {
		
		return memberDao.findMemberCriteria(number, firstName, lastName);
	}


}
