package me.dionclei.ecommerce_inventory_service.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_inventory_service.events.DecreasedEvent;
import me.dionclei.ecommerce_inventory_service.events.OrderPaidEvent;
import me.dionclei.ecommerce_inventory_service.events.OutOfStockEvent;
import me.dionclei.ecommerce_inventory_service.exceptions.OutOfStockException;
import me.dionclei.ecommerce_inventory_service.publishers.InventoryDecreasedPublisher;
import me.dionclei.ecommerce_inventory_service.publishers.InventoryOutOfStockPublisher;
import me.dionclei.ecommerce_inventory_service.services.interfaces.InventoryService;

@Service
public class OrderPaidListener {
	
	private InventoryService service;
	private InventoryOutOfStockPublisher inventoryOutOfStockPublisher;
	private InventoryDecreasedPublisher inventoryDecreasedPublisher;
	
	public OrderPaidListener(InventoryService service, InventoryOutOfStockPublisher inventoryOutOfStockPublisher, InventoryDecreasedPublisher inventoryDecreasedPublisher) {
		super();
		this.service = service;
		this.inventoryOutOfStockPublisher = inventoryOutOfStockPublisher;
		this.inventoryDecreasedPublisher = inventoryDecreasedPublisher;
	}

	@RabbitListener(queues = "order.paid.queue")
	public void onOrderPaid(OrderPaidEvent event) {
		try {
			service.decrease(event.items());
			inventoryDecreasedPublisher.publish(new DecreasedEvent(event.id()));
		} catch (OutOfStockException e) {
			inventoryOutOfStockPublisher.publish(new OutOfStockEvent(event.id()));
		}
	}
}
