package edu.mum.dao.impl;

 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import edu.mum.dao.ItemDao;
import edu.mum.domain.Item;
import edu.mum.domain.User;


@SuppressWarnings("unchecked")
@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

	public ItemDaoImpl() {
		super.setDaoType(Item.class );
		}

	public List<Item> findBySellerOrBuyer(Integer initialPrice, User buyer, User seller) {
		BigDecimal price = new BigDecimal(initialPrice);
		String sellerPrice = "";
		String buyerPrice = "";
		String or = "";
		
		if (seller != null)	sellerPrice=  "(i.initialPrice >  :price  and i.seller.id = :sellerId) ";
		if (buyer != null)	buyerPrice=  "( u.id = :buyerId and i member of u.boughtItems and  i.initialPrice = i.reservePrice  )";
		if (buyer != null && seller != null) or = "OR";

		Query query = entityManager.createQuery("select distinct i from Item i, User u where "
				+      sellerPrice + or + buyerPrice );

		if (seller != null) query.setParameter("sellerId", seller.getId()).setParameter("price", price);
		if (buyer != null)  query.setParameter("buyerId", buyer.getId());
				
			     
		return (List<Item>) query.getResultList();
	}
	
	public List<Item> findByCategoryName(String categoryName) {
/*		Query query = entityManager.createQuery("select i from Item i, Category c where c.name = :categoryName and i item of c.items");*/

		Query query = entityManager.createNamedQuery("Item.findByCategory").setParameter("categoryName", categoryName);

		return (List<Item>) query.getResultList();
	}
	
	
	public List<Item> findItemCriteria(Integer initialPrice, User buyer, User seller) {
	     CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    // Typed query - expected results are of the type Item
		CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
		// From part of the clause
		Root<Item> itemRoot = query.from(Item.class);
		// The Select
		query.select(itemRoot).distinct(true);
	
		 List<Predicate> predicateList = new ArrayList<Predicate>();
  
 		    // Seller & initialPrice
		    if ((seller != null) && (initialPrice != null)  ) {
 		    	Predicate sellerPredicate = criteriaBuilder.equal(itemRoot.get("seller"),seller);
		    	Predicate pricePredicate = criteriaBuilder.gt(itemRoot.get("initialPrice"), initialPrice );
		        Predicate sellerInitialPredicate = criteriaBuilder.and(pricePredicate, sellerPredicate);
 
		        predicateList.add(sellerInitialPredicate);
 		    }
		 
		    // buyer & initial = reserve price
	    if ((buyer != null)) {

	    		// Get buyer: user.id = :buyer.id
	    		Root<User> userRoot = query.from(User.class);
		    	Predicate buyerPredicate = criteriaBuilder.equal(userRoot.get("id"),buyer.getId());
		    	
		    	// get items:  item is member of user.boughtItems
		    	Expression<Set<Item>> items = userRoot.get("boughtItems");
		    	Predicate memberOf = criteriaBuilder.isMember(itemRoot, items);

		    	// Combine...
		    	Predicate andBuyer = criteriaBuilder.and(buyerPredicate,memberOf);

		    	// item.initPrice == item.reservePrice
		    	Predicate pricePredicate = criteriaBuilder.equal(itemRoot.get("initialPrice"),itemRoot.get("reservePrice"));

		    	Predicate buyerMatchPredicate = criteriaBuilder.and(andBuyer,pricePredicate);
		        predicateList.add(buyerMatchPredicate);

 		    }
		    
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    query.where(criteriaBuilder.or(predicates));
		 
		 List<Item> items =  (List<Item>) entityManager.createQuery( query ).getResultList();
		 return items;
	}

 }