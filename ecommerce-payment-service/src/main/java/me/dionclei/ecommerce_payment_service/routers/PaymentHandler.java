package me.dionclei.ecommerce_payment_service.routers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import me.dionclei.ecommerce_payment_service.entities.Order;
import me.dionclei.ecommerce_payment_service.repositories.OrderRepository;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

@Component
public class PaymentHandler {
	
	private OrderRepository repository;
	
	public PaymentHandler(OrderRepository repository) {
		this.repository = repository;
	}
	
	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id");
		return repository.findById(id)
			.flatMap(o -> ok().contentType(MediaType.APPLICATION_JSON)
					.body(o, Order.class))
			.switchIfEmpty(notFound().build());
	}
	
	public Mono<ServerResponse> payOrder(ServerRequest request) {
		String id = request.pathVariable("id");
		return repository.findById(id).flatMap(o -> {
			repository.delete(o);
			return ok().contentType(MediaType.APPLICATION_JSON).bodyValue("Order with id: " + id + " was paid"); 
		}).switchIfEmpty(notFound().build());
	}
}
