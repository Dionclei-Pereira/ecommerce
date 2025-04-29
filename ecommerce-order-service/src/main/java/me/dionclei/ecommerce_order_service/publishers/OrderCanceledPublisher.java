package me.dionclei.ecommerce_order_service.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_order_service.events.OrderCanceledEvent;

@Service
public class OrderCanceledPublisher {
	
	private RabbitTemplate template;
	
	public OrderCanceledPublisher(RabbitTemplate template) {
		this.template = template;
	}
	
	public void publish(OrderCanceledEvent event) {
		template.convertAndSend("ecommerce.exchange", "order.canceled", event);
	}
}
