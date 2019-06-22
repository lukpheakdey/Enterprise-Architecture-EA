package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.GenericDao;
import edu.mum.dao.CategoryDao;
import edu.mum.domain.Category;

@Service
@Transactional 
public class CategoryServiceImpl implements edu.mum.service.CategoryService {
	
 	@Autowired
	private CategoryDao categoryDao;

  	
    public void save( Category category) {  		
  		categoryDao.save(category);
 	}
  	
  	
    public void update( Category category) {  		
  		categoryDao.update(category);
 	}
  	
  	
	public List<Category> findAll() {
		return (List<Category>)categoryDao.findAll();
	}

	public Category findByCategoryName(String name) {
		return categoryDao.findByCategoryName(name);
	}
 
	public Category findOne(Long id) {
		return categoryDao.findOne(id);
	}
	
 
}
