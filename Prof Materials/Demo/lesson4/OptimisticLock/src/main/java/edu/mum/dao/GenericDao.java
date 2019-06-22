package edu.mum.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;

public interface GenericDao<T> {
 
    void save(T t);

    void delete(Long id);

    T findOne(Long id);

	public T findOne( Long id, Map<String,Object> hints );

    T update(T t);   
    
    List<T> findAll();

    
    
}
