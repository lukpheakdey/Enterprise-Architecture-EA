package edu.mum.dao;

import java.util.List;

import edu.mum.domain.Item;
import edu.mum.domain.User;

public interface ItemDao extends GenericDao<Item> {
      
	public List<Item> findBySellerOrBuyer(Integer price, User buyer, User seller);
	public List<Item> findByCategoryName(String categoryName);
	public List<Item> findItemCriteria(Integer initialPrice, User buyer, User seller);
}
