package edu.mum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.dao.GenericDao;


/*@SuppressWarnings("unchecked")
@Repository*/
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	@Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> daoType;

	public void setDaoType(Class<T> type) {
			daoType = type;
	}
   
	 protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
    @Override
    public void save( T entity ){
    	 Transaction tx=null;
    	 try {
     	     tx = this.getSession().beginTransaction();
    	    this.getSession().save(entity);
    	     tx.commit();
    	 }
    	 catch (Exception e) {
    	     if (tx!=null) tx.rollback();
    	     throw e;
    	 }
    	 finally {
    		 getSession().close();
    	 }
    	 
      }

    public void delete( T entity ){
   	 Transaction tx=null;
   	 try {
   	     tx = this.getSession().beginTransaction();
   	    this.getSession().delete(entity);
   	     tx.commit();
   	 }
   	 catch (Exception e) {
   	     if (tx!=null) tx.rollback();
   	     throw e;
   	 }
   	 finally {
   		 getSession().close();
   	 }
     }

	@Override
	public void delete(Long id) {
        T entity = findOne( id );
        delete( entity );  
    }

	@Override
	public T findOne( Long id ){
	Transaction tx=null;
	 try {
	     tx = this.getSession().beginTransaction();
		   T entity = (T) this.getSession().get( daoType, id );
	     tx.commit();
	     
	     return entity;
	 }
	 catch (Exception e) {
	     if (tx!=null) tx.rollback();
	     throw e;
	 }
	 finally {
		 getSession().close();
	 }

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(){
	   	 Transaction tx=null;
		 try {
		     tx = this.getSession().beginTransaction();
			   List<T> entities = (List<T>) this.getSession().createQuery( "from " + daoType.getName() ).list();
		     tx.commit();
		     
		     return entities;
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
		 finally {
			 getSession().close();
		 }
	}	
	@Override
	public T update( T entity ){
		Transaction tx=null;
		 try {
		     tx = this.getSession().beginTransaction();
			   entity = (T) this.getSession().merge( entity );
		     tx.commit();
		     
		     return entity;
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
		 finally {
			 getSession().close();
		 }
 	   }


 }