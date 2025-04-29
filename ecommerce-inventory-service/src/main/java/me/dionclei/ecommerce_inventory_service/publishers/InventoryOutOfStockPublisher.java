package me.dionclei.ecommerce_inventory_service.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_inventory_service.events.OutOfStockEvent;

@Service
public class InventoryOutOfStockPublisher {
	
	private RabbitTemplate template;
	
	public InventoryOutOfStockPublisher(RabbitTemplate template) {
		this.template = template;
	}
	
	public void publish(OutOfStockEvent event) {
		template.convertAndSend("ecommerce.exchange", "inventory.out-of-stock", event);
	}
}
