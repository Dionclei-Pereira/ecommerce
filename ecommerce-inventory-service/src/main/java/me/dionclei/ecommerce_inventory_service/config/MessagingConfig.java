package me.dionclei.ecommerce_inventory_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
	
	@Bean
	Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	TopicExchange ecommerceExchange() {
		return new TopicExchange("ecommerce.exchange");
	}
	
	@Bean
	Queue inventoryOutOfStockQueue() {
		return new Queue("inventory.out-of-stock.queue");
	}
    
	@Bean
	Queue orderPaidQueue() {
		return new Queue("order.paid.queue");
	}
	
    @Bean
    Queue orderCanceledQueue() {
        return new Queue("order.canceled.queue");
    }
    
    @Bean
    Binding inventoryOutOfStockBinding(Queue inventoryOutOfStockQueue, TopicExchange ecommerceExchange) {
        return BindingBuilder.bind(inventoryOutOfStockQueue)
                .to(ecommerceExchange)
                .with("inventory.out-of-stock");
    }
    
    @Bean
    Binding orderPaidBinding(Queue orderPaidQueue, TopicExchange ecommerceExchange) {
        return BindingBuilder.bind(orderPaidQueue)
                .to(ecommerceExchange)
                .with("order.paid");
    }
    
    @Bean
    Binding orderCanceledBinding(Queue orderCanceledQueue, TopicExchange ecommerceExchange) {
    	return BindingBuilder.bind(orderCanceledQueue)
    			.to(ecommerceExchange)
    			.with("order.canceled");
    }
}
