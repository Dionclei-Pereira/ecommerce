package me.dionclei.ecommerce_order_service.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_order_service.events.OrderPaidEvent;

@Service
public class OrderPaidPublisher {

	private RabbitTemplate template;

	public OrderPaidPublisher(RabbitTemplate template) {
		this.template = template;
	}
	
	public void publish(OrderPaidEvent event) {
		template.convertAndSend("ecommerce.exchange", "order.paid", event);
	}
}
