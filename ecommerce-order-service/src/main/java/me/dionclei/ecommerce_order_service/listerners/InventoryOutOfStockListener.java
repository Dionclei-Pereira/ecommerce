package me.dionclei.ecommerce_order_service.listerners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_order_service.enums.Status;
import me.dionclei.ecommerce_order_service.events.InventoryOutOfStockEvent;
import me.dionclei.ecommerce_order_service.events.OrderCanceledEvent;
import me.dionclei.ecommerce_order_service.exceptions.ResourceNotFoundException;
import me.dionclei.ecommerce_order_service.publishers.OrderCanceledPublisher;
import me.dionclei.ecommerce_order_service.services.interfaces.OrderService;

@Service
public class InventoryOutOfStockListener {
	
	private OrderService service;
	private OrderCanceledPublisher orderCanceledPublisher;
	
	public InventoryOutOfStockListener(OrderService service, OrderCanceledPublisher orderCanceledPublisher) {
		super();
		this.service = service;
		this.orderCanceledPublisher = orderCanceledPublisher;
	}

	@RabbitListener(queues = "inventory.out-of-stock.queue")
	public void onInventoryOutOfStock(@Payload InventoryOutOfStockEvent event) {
		try {
			var order = service.findById(event.id());
			if (order != null && order.getStatus() == Status.PAID) {
				order.setStatus(Status.CANCELED);
				service.save(order);
				orderCanceledPublisher
					.publish(new OrderCanceledEvent(order.getId().toString(), order.getTotal()));
			}
		} catch (ResourceNotFoundException e) { }
	}
}
