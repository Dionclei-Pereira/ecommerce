package me.dionclei.ecommerce_payment_service.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_payment_service.events.OrderCanceledEvent;

@Service
public class OrderCanceledListener {
	
	@SuppressWarnings("unused")
	private RabbitTemplate template;
	
	public OrderCanceledListener(RabbitTemplate template) {
		this.template = template;
	}
	
	@RabbitListener(queues = "order.canceled.queue")
	public void onOrderCanceled(OrderCanceledEvent event) {
		System.out.println("Refunding: " + event.price());
	}
}
