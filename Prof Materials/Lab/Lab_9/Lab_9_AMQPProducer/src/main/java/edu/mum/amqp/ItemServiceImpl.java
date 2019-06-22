package edu.mum.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import edu.mum.domain.Item;


public class ItemServiceImpl implements ItemService {

	public void publish(RabbitTemplate rabbitTemplate) {
    	Item item = new Item("Kazoo", "a Hummer");
        rabbitTemplate.convertAndSend(item);
          
        item = new Item("Hammer", "Ball Pean");
        rabbitTemplate.convertAndSend(item);
    }
}
