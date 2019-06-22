package edu.mum.dao.impl;

 

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;

import org.springframework.stereotype.Repository;

import edu.mum.dao.OrderDao;
import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;

 


@SuppressWarnings("unchecked")
@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

	public OrderDaoImpl() {
		super.setDaoType(Order.class );
		}

	public Order findByGraph(Long id) {

	    EntityGraph graph = entityManager.getEntityGraph("graph.Order.items");

	    Map hints = new HashMap();
	    hints.put("javax.persistence.fetchgraph", graph);

		Order order = this.findOne(id, hints);
		return order;

		// "Dynamic" version -- using JAVA API  - NO NAMED Query

/*		EntityGraph<Order> graph = entityManager.createEntityGraph(Order.class);
		graph.addAttributeNodes("payments");
		Subgraph<OrderItem> itemGraph = graph.addSubgraph("items");
		itemGraph.addAttributeNodes("product");

		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put("javax.persistence.loadgraph", graph);

		Order order = this.findOne(id, hints);
		return order;
*/
	}
     


 
 }