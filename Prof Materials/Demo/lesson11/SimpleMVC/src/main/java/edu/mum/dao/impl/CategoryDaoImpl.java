package edu.mum.dao.impl;


import org.springframework.stereotype.Repository;

import edu.mum.dao.CategoryDao;
import edu.mum.domain.Category;

	@Repository
	public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao
	{
		
		public CategoryDaoImpl() {
			super.setDaoType(Category.class );
			}


  
	}

