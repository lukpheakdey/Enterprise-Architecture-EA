package edu.mum.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import edu.mum.domain.Item;

public class ItemServiceImpl implements ItemService {

	@Override
	public void publish(RabbitTemplate rabbitTemplate) {
		//create items
		Item item1 = new Item(2000.0, "MacBookPro", "The best laptop you can get.");
		Item item2 = new Item(35000.0, "Tesla Model 3", "The best electric or green car you can get.");
		Item item3 = new Item(350.0, "AppleWatch", "The best smartwatch you can get.");
		Item item4 = new Item(1000.0, "iPhone", "The phone that revolutionized the consumer industry.");
		
		//send the items to the broker with a routing key of items.best
		rabbitTemplate.convertAndSend("direct.item", item1);
		rabbitTemplate.convertAndSend("direct.item", item2);
		rabbitTemplate.convertAndSend("direct.item", item3);
		rabbitTemplate.convertAndSend("direct.item", item4);
	}
}
