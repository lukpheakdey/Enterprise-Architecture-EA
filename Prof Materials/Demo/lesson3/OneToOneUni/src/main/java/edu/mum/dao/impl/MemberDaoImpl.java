package edu.mum.dao.impl;

 

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	// Select from Member m where m.memberNumber = :number
	public Member findByMemberNumberCriteria(Integer number) {
	     CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    // Typed query - expected results are of the type Member
		CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
		// From part of the clause
		Root<Member> memberRoot = query.from(Member.class);
		// The Select
		query.select(memberRoot);
		// Where
		query.where(criteriaBuilder.equal(memberRoot.get("memberNumber"),  number) );

		return (Member)  entityManager.createQuery( query ).getSingleResult();
	}
	
	
 }