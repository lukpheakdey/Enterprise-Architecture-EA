package edu.mum.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.AddressDao;
import edu.mum.dao.GenericDao;
import edu.mum.dao.MemberDao;
import edu.mum.domain.Address;
import edu.mum.domain.Member;

@Service
@Transactional 
public class MemberServiceImpl implements edu.mum.service.MemberService {
	
 	@Autowired
	private MemberDao memberDao;

 	@Autowired
	private AddressDao addressDao;

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
		
// OR 		"SELECT m FROM Member m JOIN FETCH m.userCredentials WHERE m.id = (:id)"
		member.getUserCredentials();
		
		return  member;
	}
	
	public List<Member> findAllJoinFetch() {
		return memberDao.findAllJoinFetch();
	}
	
 	@Override
	public List<Member> findByGraph() {
		return  memberDao.findByGraph();
	}

	public List<Member> findAllSubSelect() {
	 		// hydrate since LAZY load
       	List<Member> members =  (List<Member>)this.findAll();
       	members.get(0).getAddresses().get(0);
			           
        return members;

}

	// "Manual" example of Hibernate FetchMode.SUBSELECT
	public List<Member> findAddressBySubSelect() {
		
		   List<Member> members = this.findAll();
		   List<Address> addressList = addressDao.findAddressBySubSelect();
		   
		   // Need to align Addresses with Members
		   // addresses are from query - therefore not in cache
 		   int index = 0;
 		   for (Member membere : members) {
			   membere.setAddresses(new ArrayList<Address>());
 			
 			   while ( addressList.size() > index) {
 				   Address addresse = addressList.get(index);
				   if (addresse.getMember().getId() != membere.getId() ) {break;}
			       membere.getAddresses().add(addresse);
 			       index++;
			   }
		   }
		   
		   return members;
   
	}
	 
}
