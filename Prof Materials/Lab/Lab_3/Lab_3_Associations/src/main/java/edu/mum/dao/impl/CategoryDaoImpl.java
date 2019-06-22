package edu.mum.dao.impl;

 

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.CategoryDao;
import edu.mum.domain.Category;


@SuppressWarnings("unchecked")
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

	public CategoryDaoImpl() {
		super.setDaoType(Category.class );
		}

	public Category findByCategoryName(String name) {
	     
		Query query = entityManager.createQuery("select p from Category p  where p.name =:name");
		return (Category) query.setParameter("name", name).getSingleResult();
			     

	}


 }