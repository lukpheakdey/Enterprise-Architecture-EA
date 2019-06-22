package edu.mum.dao;

import edu.mum.domain.Category;

public interface CategoryDao extends GenericDao<Category> {
      
	public Category findByCategoryName(String name);
}
