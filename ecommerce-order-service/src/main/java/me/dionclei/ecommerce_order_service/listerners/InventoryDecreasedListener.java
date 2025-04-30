package me.dionclei.ecommerce_order_service.listerners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_order_service.enums.Status;
import me.dionclei.ecommerce_order_service.events.InventoryDecreasedEvent;
import me.dionclei.ecommerce_order_service.services.interfaces.OrderService;

@Service
public class InventoryDecreasedListener {
	
	private OrderService service;
	
	private InventoryDecreasedListener(OrderService service) {
		this.service = service;
	}
	
	@RabbitListener(queues = "inventory.decreased.order.queue")
	public void onInventoryDecreased(@Payload InventoryDecreasedEvent event) {
		var order = service.findById(event.id());
		if (order != null) {
			order.setStatus(Status.SHIPPING);
			service.save(order);
		}
	}
}
