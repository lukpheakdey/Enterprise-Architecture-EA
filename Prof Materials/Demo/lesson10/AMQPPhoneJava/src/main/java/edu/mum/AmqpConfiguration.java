package edu.mum;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
 
@Configuration
@ComponentScan("edu.mum")
@IntegrationComponentScan("edu.mum.integration")   // Scans for Integration resources
@EnableIntegration  // @Transformer 
public class AmqpConfiguration {

/*    <rabbit:connection-factory id="connectionFactory" host="localhost" username="joe" password="joe"/>*/  
	    @Bean
	    public ConnectionFactory connectionFactory() {
	    	CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
	    	connectionFactory.setUsername("joe");
	    	connectionFactory.setPassword("joe");
	        return connectionFactory;
	    }
	    

	    @Bean
	    public AmqpAdmin amqpAdmin() {
	        return new RabbitAdmin(connectionFactory());
	    }

 	    @Bean
	    public Queue purchasesPhoneQueue() {
	       return new Queue("purchasesPhone");
	    }
   
		@Bean
		TopicExchange orderExchange() {
			return new TopicExchange("order");	
		}
		
		@Bean
		List<Binding> bindings() {

		  return Arrays.asList(BindingBuilder.bind(purchasesPhoneQueue())
				  .to(orderExchange()).with("purchases.phone.#"));
		}

		   @Bean
		    public RabbitTemplate topicTemplate() {
		        RabbitTemplate topicTemplate=new RabbitTemplate(connectionFactory());
		        topicTemplate.setRoutingKey("purchases.phone");
		        topicTemplate.setExchange("order");
		        topicTemplate.setReplyTimeout(2000);
		        return topicTemplate;
		    }
		   
		   @Bean
		   public DirectChannel toAmqpOrder() {
		    return new DirectChannel();
		   }


		   // Spring Integration Flow...
		   @Bean
		   public IntegrationFlow rabbitSend(){
		   
			   // Message gets put on channel through OrderGateway...which is invoked in OrderServiceImpl
			return   IntegrationFlows.from(toAmqpOrder())
				   .handle(Amqp.outboundAdapter(topicTemplate()))
				   .get();
		   }	   
}
