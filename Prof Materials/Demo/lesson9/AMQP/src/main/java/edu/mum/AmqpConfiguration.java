package edu.mum;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.mum.amqp.DirectListener;
import edu.mum.amqp.OrderListener;

 /*
  * This is the Java Config version. It has corresponding "comments" which
  * show the XML config analog.
  */
@Configuration
public class AmqpConfiguration {

/*    <rabbit:connection-factory id="connectionFactory" host="localhost" username="joe" password="joe"/>*/
	    @Bean
	    public ConnectionFactory connectionFactory() {
	    	CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
	    	connectionFactory.setUsername("joe");
	    	connectionFactory.setPassword("joe");
	        return connectionFactory;
	    }

/*	    <rabbit:admin connection-factory="connectionFactory" />*/
	    @Bean
	    public AmqpAdmin amqpAdmin() {
	        return new RabbitAdmin(connectionFactory());
	    }

 	    @Bean
	    public Queue purchasesStoreQueue() {
	       return new Queue("purchasesStore");
	    }

	    @Bean
	    public Queue purchasesOnlineQueue() {
	       return new Queue("purchasesOnline");
	    }

	    @Bean
	    public Queue purchasesOnlineClassicQueue() {
	       return new Queue("purchasesOnlineClassic");
	    }
	    
		@Bean
		TopicExchange orderExchange() {
			return new TopicExchange("order");	
		}
		
		@Bean
		List<Binding> bindings() {

		    return Arrays.asList(BindingBuilder.bind(purchasesStoreQueue()).to(orderExchange()).with("purchases.store.#"), 
		    		BindingBuilder.bind(purchasesOnlineQueue()).to(orderExchange()).with("purchases.online.#"),
		    		BindingBuilder.bind(purchasesOnlineClassicQueue()).to(orderExchange()).with("purchases.online.classic.#")
		    		);
		}

		
/*		<!-- **************** TOPIC PRODUCER  -->
		<!-- routing-key is "default" -->
		    <rabbit:template id="topicTemplate" connection-factory="connectionFactory"
		                     reply-timeout="2000" routing-key="purchases.store"
		                     exchange="order" />

*/
		   @Bean
		    public RabbitTemplate topicTemplate() {
		        RabbitTemplate topicTemplate=new RabbitTemplate(connectionFactory());
		        topicTemplate.setRoutingKey("purchases.store");
		        topicTemplate.setExchange("order");
		        topicTemplate.setReplyTimeout(2000);
		        return topicTemplate;
		    }

/*
		   <!--  ****************  TOPIC CONSUMER for KEY purchases.store on Queue purchasesStore************************* -->
		   <rabbit:listener-container connection-factory="connectionFactory">
		  	<rabbit:listener ref="orderListener" method="listen" queue-names="purchasesStore" />
		  </rabbit:listener-container>

		  <bean id="orderListener" class="edu.mum.amqp.OrderListener" />
*/
		   
		   @Bean
		   public SimpleMessageListenerContainer orderListenerContainer() {
		       SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		       container.setConnectionFactory(connectionFactory());
		       container.setQueues(purchasesStoreQueue());
		       container.setMessageListener(new MessageListenerAdapter(orderListener(),"listen"));
		       return container;
		   }
		   
		   @Bean
		   OrderListener orderListener() {
			   return new OrderListener();
		   }
		   
		
/*		   <!-- *********** DIRECT EXCHANGE ***************** -->
			<rabbit:queue name="directQueue" durable="true"/>
			<rabbit:queue name="answerQueue" durable="true"/>

			 <rabbit:direct-exchange name="direct" durable="true">
			  <rabbit:bindings>
			   <rabbit:binding queue="directQueue" key="order.key"></rabbit:binding>
			   <rabbit:binding queue="answerQueue" key="order.answer"></rabbit:binding>
			  </rabbit:bindings>
			 </rabbit:direct-exchange>
*/
// Declare Queues, Exchange & Bind all at once...
 		   @Bean
		   public List<Declarable> directExchangeBindings() {
		       Queue directQueue = new Queue("directQueue", true);
		       Queue answerQueue = new Queue("answerQueue", true);
		       DirectExchange directExchange = new DirectExchange("direct");
		    
		       List<Declarable> bindingList = Arrays.<Declarable>asList(
		    		   directQueue,
		    		   answerQueue,
		    		   directExchange,
		    	 BindingBuilder.bind(directQueue).to(directExchange).with("order.key"),
		         BindingBuilder.bind(answerQueue).to(directExchange).with("order.answer"));
		       
		       return bindingList;
		   }


/*		   <!-- **************** DIRECT PRODUCER  -->
		   <!-- This producer will publish with this routing key [essentially to queue directQueue] associated with it...] -->
		       <rabbit:template id="directTemplate" connection-factory="connectionFactory"
		                        reply-timeout="2000" routing-key="order.key"
		                        exchange="direct" />
*/
		   @Bean
		    public RabbitTemplate directTemplate() {
		        RabbitTemplate directTemplate= new RabbitTemplate(connectionFactory());
		        directTemplate.setRoutingKey("order.key");
		        directTemplate.setExchange("direct");
		        directTemplate.setReplyTimeout(2000);
		        return directTemplate;
		    }
  
/*         <!-- **************** ANSWER PRODUCER  -->
		   <!-- This producer will publish with this routing key [essentially to queue answerQueue] associated with it...] -->
		       <rabbit:template id="answerTemplate" connection-factory="connectionFactory"
		                        reply-timeout="2000" routing-key="order.answer"
		                        exchange="direct" />
*/
		   @Bean
		    public RabbitTemplate answerTemplate() {
		        RabbitTemplate answerTemplate= new RabbitTemplate(connectionFactory());
		        answerTemplate.setRoutingKey("order.answer");
		        answerTemplate.setExchange("direct");
		        answerTemplate.setReplyTimeout(2000);
		        return answerTemplate;
		    }
  
		   

/*		   <!--  ****************  DIRECT CONSUMER ************************* -->
		    <rabbit:listener-container connection-factory="connectionFactory">
		   	<rabbit:listener ref="queueListener" method="listen" queue-names="directQueue" />
		   </rabbit:listener-container>

		   <bean id="queueListener" class="edu.mum.amqp.DirectListener" />
*/
		   
		   @Bean
		   public SimpleMessageListenerContainer directListenerContainer() {
		       SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		       container.setConnectionFactory(connectionFactory());
		       container.setQueueNames("directQueue");
		       container.setMessageListener(new MessageListenerAdapter(queueListener(),"listen"));
		       return container;
		   }
		   
		   @Bean
		   DirectListener queueListener() {
			   return new DirectListener();
		   }
		   
		   
}
