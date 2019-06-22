package edu.mum.amqp;

import edu.mum.domain.Item;

public class ItemListener {

	public void listen(Item item) {
			
		String name = item.getName();
		System.out.println("---------- DIRECT consumer for Item: " + name);
	}
}
