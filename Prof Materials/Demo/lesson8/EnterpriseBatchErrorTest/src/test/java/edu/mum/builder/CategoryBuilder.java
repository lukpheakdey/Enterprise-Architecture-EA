package edu.mum.builder;

import edu.mum.domain.Category;

public class CategoryBuilder {

	private Category category;
  
	public CategoryBuilder() {
		this.category = new Category();
	}
	
    public CategoryBuilder withName(String name) {
        this.category.setName(name);
        return this;
    }

    public CategoryBuilder withId(Integer id) {
        this.category.setId(id);
        return this;
    }

      public Category build() {
        return category;
    }

	
}
