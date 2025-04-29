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
	Queue PaymentCompletedQueue() {
		return new Queue("payment.completed.queue");
	}
	
    @Bean
    Binding PaymentCompletedBinding(Queue PaymentCompletedQueue, TopicExchange ecommerceExchange) {
        return BindingBuilder.bind(PaymentCompletedQueue)
                .to(ecommerceExchange)
                .with("payment.completed");
    }
}
