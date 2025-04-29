package me.dionclei.ecommerce_payment_service.services.interfaces;

import me.dionclei.ecommerce_payment_service.entities.Order;
import reactor.core.publisher.Mono;

public interface PaymentService {
	
	Mono<Order> findOrderById(String id);
	
	Mono<Void> pay(Order o);

}
