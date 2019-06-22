package edu.mum.dao.impl;

 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
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
	
	
	// Dynamically determine based on parameters  .
	// One, two or three parameters...
	//
	public List<Member> findMemberCriteria(Integer number, String firstName, String lastName) {
	
 		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    // Typed query - expected results are of the type Member
		CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
		// From part of the clause
		Root<Member> memberRoot = query.from(Member.class);
		// The Select
		query.select(memberRoot);
		
		 List<Predicate> predicateList = new ArrayList<Predicate>();
 		 
		    // if firstName is LIKE passed parameter
		    if ((firstName != null) && (!(firstName.isEmpty()))) {
		    	Predicate firstNamePredicate =   
		        		criteriaBuilder.like(
		        		criteriaBuilder.upper(memberRoot.get("firstName")), firstName.toUpperCase()+"%");
		        predicateList.add(firstNamePredicate);
		    }
		 
		    // if lastName is LIKE passed parameter
	    if ((lastName != null) && (!(lastName.isEmpty()))) {
	    	Predicate lastNamePredicate =    
		        		criteriaBuilder.like(
		        		criteriaBuilder.upper(memberRoot.get("lastName")),  lastName.toUpperCase()+"%");
		        predicateList.add(lastNamePredicate);
		    }
		    
	    // if memberNumber >   passed parameter

		    if ((number != null) ) {
		    	Predicate numberPredicate =    
		        		criteriaBuilder.gt(memberRoot.get("memberNumber"), number);
		        predicateList.add(numberPredicate);
		    }    
		 
		    // Move predicates to Array and "AND" them in where statement in query
		    Predicate[] predicates = new Predicate[predicateList.size()];
		    predicateList.toArray(predicates);
		    query.where(criteriaBuilder.and(predicates));
		 
		return (List<Member>)  entityManager.createQuery( query ).getResultList();

		
  		}
 }