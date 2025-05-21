package me.dionclei.ecommerce_payment_service.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_payment_service.entities.Order;
import me.dionclei.ecommerce_payment_service.events.OrderPlacedEvent;
import reactor.core.publisher.Mono;

@Service
public class OrderPlacedListener {
	
	@Autowired
	private ReactiveMongoTemplate template;

	public Mono<Void> saveOrder(Order order) {
	    return template.insert(order).then();
	}
	
	@RabbitListener(queues = "order.placed.queue")
	public void onOrderPlaced(OrderPlacedEvent event) {
		Order order = new Order(event.id(), event.price());
		saveOrder(order).subscribe();
	}
}
