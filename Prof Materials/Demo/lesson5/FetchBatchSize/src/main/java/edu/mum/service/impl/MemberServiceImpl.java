package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
    public void update( Member member) {  		
		memberDao.update(member);
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
		
// OR 		"SELECT p FROM Member m JOIN FETCH m.userCredentials WHERE m.id = (:id)"
		member.getUserCredentials();
		
		return  member;
	}
	
	public List<Member> findAllJoinFetch() {
		return memberDao.findAllJoinFetch();
	}
	
	public List<Member> findAllBatch() {
		List<Member> members =  (List<Member>)this.findAll();
 		// hydrate - need to access ALL since we don't know batch Size
		// e.g. if size =2  AND there are 6 members we need to hydrate members #1 & #3 & #5
       	for (Member member : members)
       		if (!member.getAddresses().isEmpty()) member.getAddresses().get(0);
		
		// This will work for our example as there are size =2 & there are 3 members
/*      members.get(0).getAddresses().get(0);
      	members.get(2).getAddresses().get(0);
*/
        return members;

	}
	
 	@Override
	public List<Member> findByGraph() {
		return  memberDao.findByGraph();
	}

 

}
