package edu.mum.service;

import java.util.List;

import edu.mum.domain.Category;
 
public interface CategoryService {

	public void save(Category category);
	public void update(Category category);
	public List<Category> findAll();
	public Category findByCategoryName(String name);

	public Category findOne(Long id);

}
