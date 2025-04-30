package me.dionclei.ecommerce_payment_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
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
	Queue orderCanceledQueue() {
		return new Queue("order.canceled.queue");
	}
	
	@Bean
	Queue orderPlacedQueue() {
		return new Queue("order.placed.queue");
	}
	
	@Bean
	Queue paymentCompletedQueue() {
		return new Queue("payment.completed.queue");
	}
	
	@Bean
	Binding orderCanceledBinding(Queue orderCanceledQueue, TopicExchange ecommerceExchange) {
		return BindingBuilder.bind(orderCanceledQueue)
				.to(ecommerceExchange)
				.with("order.canceled");
	}
	
	@Bean
	Binding orderPlacedBinding(Queue orderPlacedQueue, TopicExchange ecommerceExchange) {
		return BindingBuilder.bind(orderPlacedQueue)
				.to(ecommerceExchange)
				.with("order.placed");
	}
	
    @Bean
    Binding paymentCompletedBinding(Queue paymentCompletedQueue, TopicExchange ecommerceExchange) {
        return BindingBuilder.bind(paymentCompletedQueue)
                .to(ecommerceExchange)
                .with("payment.completed");
    }
}
