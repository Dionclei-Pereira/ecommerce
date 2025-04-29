package me.dionclei.ecommerce_payment_service.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_payment_service.events.PaymentCompletedEvent;

@Service
public class PaymentCompletedPublisher {
	
	private RabbitTemplate template;

	public PaymentCompletedPublisher(RabbitTemplate template) {
		this.template = template;
	}
	
	public void publish(PaymentCompletedEvent event) {
		template.convertAndSend("ecommerce.exchange", "payment.completed", event);
	}
}
