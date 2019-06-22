package edu.mum.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Category;
import edu.mum.dao.CategoryDao;
import edu.mum.service.CategoryService;

@Service
@Transactional 
public class CategoryServiceImpl implements CategoryService{
	
 	@Autowired
	private CategoryDao categoryDao;

	public List<Category>findAll() {
		return categoryDao.findAll();
	}

 
 	public void save(Category category) {
		   categoryDao.save(category);
	}
	
	public Category findOne(long categoryID) {
		return categoryDao.findOne(categoryID);
	}

 
 


}
