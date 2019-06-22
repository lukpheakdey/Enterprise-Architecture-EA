package edu.mum.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.LazyInitializationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.builder.AuthorityBuilder;
import edu.mum.builder.MemberBuilder;
import edu.mum.builder.UserCredentialsBuilder;
import edu.mum.dao.MemberDao;
import edu.mum.domain.Member;

@ContextConfiguration(value = "classpath:applicationContext-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToOneUni {

 	@Autowired
	MemberDao memberDao;
	
	@PersistenceContext
	EntityManager entityManager;
	
	// Demonstrate cascade save
	// NOTICE: @Transactional on test method - we INVOKE memberDao...
	// Rollback when leaving method...
	@Test
	@Transactional
	@Rollback(true)
	public void testOneToOneUni() {
 
		Member member = new MemberBuilder()
	    		.withFirstName("Sean")
	   			.withLastName("Smith")
	   			.withUserCredentials(new UserCredentialsBuilder()
	                    .withUsername("JohnDoe")
	                    .withPassword("DoeNuts")
	                    .withAuthority(new AuthorityBuilder()
	                    		.withUsername("JohnDoe")
	                    		.withAuthority("USER")
	                    		.build())
	                   .build())
	   			.withMemberNumber(1)
	   			.build();
	
 	    // Automatically cascades Persist to UserCredentials..then to Authority
	    memberDao.save(member);
 	    
	    List<Member> members = memberDao.findAll();
	    
	    // Validate Member save
	    Assert.assertNotNull(member.getId());
	    Assert.assertEquals(member.getFirstName(), members.get(0).getFirstName());
	   
	    // Validate Authority save [..validates UserCredentials save]
 	    Assert.assertNotNull(member.getUserCredentials().getAuthority().get(0).getId());
 
	}
	
    // Demonstrate Lazy load issue [no @Transactional - otherwise we will be in PersistentContext]
	// Expected result is LazyInitializationException
	@Test(expected=LazyInitializationException.class)
	@Sql({"classpath:sql/lazy.sql"})
	@Sql(scripts = "classpath:sql/cleanupLazy.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
 	public void testOneToOneUniLazy() {
 
 		// Need to complete transaction [close "persistenceContext"] in order to see Lazy exception
	    List<Member> members = memberDao.findAll();
 	    
	    // Throws exception
	    members.get(0).getUserCredentials().getUsername();

	}

	// Demo delete
	@Test
	@Transactional
 	@Sql({"classpath:sql/lazy.sql"})
	@Sql(scripts = "classpath:sql/cleanupLazy.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testOneToOneUniMemberDelete() {
 
	    List<Member> members = memberDao.findAll();
	    Member member = members.get(0);

	    memberDao.delete(member.getId());
	    
	    member = memberDao.findOne(member.getId());
	    Assert.assertNull(member);

	    
 	}
}
