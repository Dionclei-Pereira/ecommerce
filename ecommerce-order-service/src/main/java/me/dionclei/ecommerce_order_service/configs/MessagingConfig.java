package me.dionclei.ecommerce_order_service.configs;

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
	Queue orderPaidQueue() {
		return new Queue("order.paid.queue");
	}
	
    @Bean
    Queue orderCanceledQueue() {
        return new Queue("order.canceled.queue");
    }
    
	@Bean
	Queue inventoryOutOfStockQueue() {
		return new Queue("inventory.out-of-stock.queue");
	}
	
	@Bean
	Queue inventoryDecreasedQueue() {
		return new Queue("inventory.decreased.order.queue");
	}
    
	@Bean
	Queue paymentCompletedQueue() {
		return new Queue("payment.completed.queue");
	}
	
    @Bean
    Binding paymentCompletedBinding(Queue paymentCompletedQueue, TopicExchange ecommerceExchange) {
        return BindingBuilder.bind(paymentCompletedQueue)
                .to(ecommerceExchange)
                .with("payment.completed");
    }
	
	@Bean
	Binding InventoryDecreasedBinding(Queue inventoryDecreasedQueue, TopicExchange ecommerceExchange) {
		return BindingBuilder.bind(inventoryDecreasedQueue)
				.to(ecommerceExchange)
				.with("inventory.decreased");
	}
	
    @Bean
    Binding InventoryOutOfStockBinding(Queue inventoryOutOfStockQueue, TopicExchange ecommerceExchange) {
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