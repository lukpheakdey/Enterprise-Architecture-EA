package edu.mum;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.mum.amqp.AnswerListener;
import edu.mum.amqp.OrderListener;
import edu.mum.amqp.OrderListenerStore;

 
@Configuration
@ComponentScan("edu.mum")
public class AmqpConfiguration {

/*    <rabbit:connection-factory id="connectionFactory" host="localhost" username="joe" password="joe"/>*/  
	    @Bean
	    public ConnectionFactory connectionFactory() {
	    	CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
	    	connectionFactory.setUsername("joe");
	    	connectionFactory.setPassword("joe");
	        return connectionFactory;
	    }
	    

/*
 * Topic Consumer
	    <rabbit:listener-container connection-factory="connectionFactory">
	    <!-- Get ALL Online purchases OR just "Classic" ones -->
	   <!-- 	<rabbit:listener ref="orderListener" method="listen" queue-names="purchasesOnlineClassic" /> -->
	   	<rabbit:listener ref="orderListener" method="listen" queue-names="purchasesOnline" />
	   </rabbit:listener-container>

	   <bean id="orderListener" class="edu.mum.amqp.OrderListener" />
*/
		   @Bean
		   public SimpleMessageListenerContainer orderListenerContainer() {
		       SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		       container.setConnectionFactory(connectionFactory());
		       container.setQueueNames("purchasesOnline");
		       container.setMessageListener(new MessageListenerAdapter(orderListener(),"listen"));
		       return container;
		   }
		   
		   @Bean
		   OrderListener orderListener() {
			   return new OrderListener();
		   }

// Dynamic Queue....Auto-declaring a non-durable Queue
		    @Bean
		    public RabbitAdmin  amqpAdmin() {
		    	RabbitAdmin  rabbitAdmin =  new RabbitAdmin(connectionFactory());
	 
		        return rabbitAdmin;
		    }
		    
		    // non durable queue, not exclusive, autodelete
		    @Bean
		    Queue purchasesStoreTooQueue() {
		        return new Queue("purchasesStoreToo", false,false,true);
		    }
			@Bean
			TopicExchange orderExchange() {
				return new TopicExchange("order");	
			}

			@Bean
			Binding binding() {

			    return (BindingBuilder.bind(purchasesStoreTooQueue()).to(orderExchange()).with("purchases.store.#")	);
			}

		   
		   @Bean
		   public SimpleMessageListenerContainer orderListenerContainerStore() {
		       SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		       container.setConnectionFactory(connectionFactory());
		       container.setQueueNames("purchasesStoreToo");
		       container.setMessageListener(new MessageListenerAdapter(orderListenerStore(),"listen"));
		       return container;
		   }
		   
		   @Bean
		   OrderListenerStore orderListenerStore() {
			   return new OrderListenerStore();
		   }
		   
		
 
	    
	    
/*
	   <!--  ****************  ANSWER CONSUMER ************************* -->
	    <rabbit:listener-container connection-factory="connectionFactory">
	   	<rabbit:listener ref="queueListener" method="listen" queue-names="answerQueue" />
	   </rabbit:listener-container>

	   <bean id="queueListener" class="edu.mum.amqp.AnswerListener" />

*/		   

		   
		   @Bean
		   public SimpleMessageListenerContainer directListenerContainer() {
		       SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		       container.setConnectionFactory(connectionFactory());
		       container.setQueueNames("answerQueue");
		       container.setMessageListener(new MessageListenerAdapter(queueListener(),"listen"));
		       return container;
		   }
		   
		   @Bean
		   AnswerListener queueListener() {
			   return new AnswerListener();
		   }
		   
		   
}
