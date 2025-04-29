package me.dionclei.ecommerce_inventory_service.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_inventory_service.events.OrderPaidEvent;
import me.dionclei.ecommerce_inventory_service.events.OutOfStockEvent;
import me.dionclei.ecommerce_inventory_service.exceptions.OutOfStockException;
import me.dionclei.ecommerce_inventory_service.publishers.InventoryOutOfStockPublisher;
import me.dionclei.ecommerce_inventory_service.services.interfaces.InventoryService;

@Service
public class OrderPaidListener {
	
	private InventoryService service;
	private InventoryOutOfStockPublisher inventoryOutOfStockPublisher;
	
	public OrderPaidListener(InventoryService service, InventoryOutOfStockPublisher inventoryOutOfStockPublisher) {
		super();
		this.service = service;
		this.inventoryOutOfStockPublisher = inventoryOutOfStockPublisher;
	}

	@RabbitListener(queues = "order.paid.queue")
	public void onOrderPaid(OrderPaidEvent event) {
		try {
			service.decrease(event.items());
		} catch (OutOfStockException e) {
			inventoryOutOfStockPublisher.publish(new OutOfStockEvent(event.id()));
		}
	}
}
