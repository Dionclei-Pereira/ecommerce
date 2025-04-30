package me.dionclei.ecommerce_inventory_service.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_inventory_service.events.DecreasedEvent;

@Service
public class InventoryDecreasedPublisher {
	
	private RabbitTemplate template;
	
	public InventoryDecreasedPublisher(RabbitTemplate template) {
		this.template = template;
	}
	
	public void publish(DecreasedEvent event) {
		template.convertAndSend("ecommerce.exchange", "inventory.decreased", event);
	}
}
