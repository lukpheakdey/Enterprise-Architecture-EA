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
   
	// Opens session   - clsed when TX ends
	 protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	 
	 /*
	  *  
	  */
			 
    @Override
    public void save( T entity ){
    	    this.getSession().save(entity);
     	 
      }

    public void delete( T entity ){
    	    this.getSession().delete(entity);
      }

	@Override
	public void delete(Long id) {
        T entity = findOne( id );
        delete( entity );  
    }

	@Override
	public T findOne( Long id ){
 
		   T entity = (T) this.getSession().get( daoType, id );

	     return entity;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(){
 			   List<T> entities = (List<T>) this.getSession().createQuery( "from " + daoType.getName() ).list();
 		     return entities;
 	}	
	
	@Override
	public T update( T entity ){
 			   entity = (T) this.getSession().merge( entity );
 		     return entity;
 	   }


 }