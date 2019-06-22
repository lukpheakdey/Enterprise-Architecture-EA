package edu.mum.dao.impl;

 

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.MemberDao;
import edu.mum.domain.Member;


@SuppressWarnings("unchecked")
@Repository
public class MemberDaoImpl extends GenericDaoImpl<Member> implements MemberDao {

	public MemberDaoImpl() {
		super.setDaoType(Member.class );
		}

	public Member findByMemberNumber(Integer number) {
	     
		Query query = entityManager.createQuery("select m from Member m  where m.memberNumber =:number");
		return (Member) query.setParameter("number", number).getSingleResult();
			     

	}

	public Member findByNamedQuery(Integer number) {

		return (Member) entityManager.createNamedQuery("Member.findByMemberNumber")
	    .setParameter("memberNumber", number)
		    .getSingleResult();
	}

	public Member findByNativeQuery(Integer number) {
		
//		Query query = entityManager.createNativeQuery("SELECT m.member_id, m.age, m.title ,m.firstname, m.lastname, m.memberNumber FROM Member m where m.memberNumber = ?", Member.class);
		Query query = entityManager.createNativeQuery("SELECT m.* FROM member m where m.memberNumber = ?", Member.class);
		Member member = (Member) query.setParameter(1,number).getSingleResult();

		return member;
 	}

	public List<Member> findByAddressState(String state,Integer age) {
 	    Query query =  entityManager.createQuery("SELECT m from Member as m,Address a  "
 	    		+ "where m.age > :age and a.state = :state and a member of m.addresses");
 	    // SAME AS: + "where m.age > :age and a.state = :state and a in (select a from m.addresses addrs)");

 	    return query.setParameter("state", state)
 	    		.setParameter("age", age)
 	    		.getResultList();
	}

 }