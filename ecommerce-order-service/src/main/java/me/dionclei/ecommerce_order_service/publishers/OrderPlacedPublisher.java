package me.dionclei.ecommerce_order_service.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_order_service.events.OrderPlacedEvent;

@Service
public class OrderPlacedPublisher {
	
	private RabbitTemplate template;
	
	public OrderPlacedPublisher(RabbitTemplate template) {
		this.template = template;
	}
	
	public void publish(OrderPlacedEvent event) {
		template.convertAndSend("ecommerce.exchange", "order.placed", event);
	}
}
