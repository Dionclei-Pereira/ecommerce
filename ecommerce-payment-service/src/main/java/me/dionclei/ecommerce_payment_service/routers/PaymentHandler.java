package me.dionclei.ecommerce_payment_service.routers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import me.dionclei.ecommerce_payment_service.services.interfaces.PaymentService;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

@Component
public class PaymentHandler {
	
	private PaymentService service;
	
	public PaymentHandler(PaymentService service) {
		this.service = service;
	}
	
	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id");
		return service.findOrderById(id)
			.flatMap(o -> ok().contentType(MediaType.APPLICATION_JSON)
					.bodyValue(o))
			.switchIfEmpty(notFound().build());
	}
	
	public Mono<ServerResponse> payOrder(ServerRequest request) {
	    String id = request.pathVariable("id");
	    return service.findOrderById(id)
	        .flatMap(o -> service.pay(o)
	            .then(ok().contentType(MediaType.APPLICATION_JSON)
	            .bodyValue("Order with id: " + id + " was paid")))
	        .switchIfEmpty(notFound().build());
	}
}
