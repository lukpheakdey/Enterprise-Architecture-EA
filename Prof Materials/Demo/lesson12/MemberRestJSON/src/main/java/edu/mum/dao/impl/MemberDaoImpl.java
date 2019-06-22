package edu.mum.dao.impl;

 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.MemberDao;
import edu.mum.domain.Member;
import edu.mum.domain.Order;


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

	public List<Member> findAllJoinFetch() {
		  Query query =  entityManager.createQuery("SELECT DISTINCT m FROM Member AS m JOIN FETCH m.addresses AS a");
		  return (List<Member>) query.getResultList();

	}

	public List<Member> findByGraph() {

	    EntityGraph graph = entityManager.getEntityGraph("graph.Member.addresses");

	    return (List<Member>) this.findAll("javax.persistence.fetchgraph",graph);
 
	}



 }