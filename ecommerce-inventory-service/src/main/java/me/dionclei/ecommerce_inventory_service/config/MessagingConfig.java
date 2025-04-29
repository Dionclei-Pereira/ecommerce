package me.dionclei.ecommerce_inventory_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
	
	@Bean
	TopicExchange ecommerceExchange() {
		return new TopicExchange("ecommerce.exchange");
	}
	
	@Bean
	Queue InventoryOutOfStockQueue() {
		return new Queue("inventory.out-of-stock.queue");
	}
	
    @Bean
    Binding InventoryOutOfStockBinding(Queue InventoryOutOfStockQueue, TopicExchange ecommerceExchange) {
        return BindingBuilder.bind(InventoryOutOfStockQueue)
                .to(ecommerceExchange)
                .with("inventory.out-of-stock");
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
