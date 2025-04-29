package me.dionclei.ecommerce_payment_service.services;

import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_payment_service.entities.Order;
import me.dionclei.ecommerce_payment_service.events.PaymentCompletedEvent;
import me.dionclei.ecommerce_payment_service.publishers.PaymentCompletedPublisher;
import me.dionclei.ecommerce_payment_service.repositories.OrderRepository;
import me.dionclei.ecommerce_payment_service.services.interfaces.PaymentService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private OrderRepository orderRepository;
	private PaymentCompletedPublisher paymentCompletedPublisher;
	
	public PaymentServiceImpl(OrderRepository orderRepository, PaymentCompletedPublisher paymentCompletedPublisher) {
		this.orderRepository = orderRepository;
		this.paymentCompletedPublisher = paymentCompletedPublisher;
	}
	
	public Mono<Order> findOrderById(String id) {
		return orderRepository.findById(id);
	}
	
	public Mono<Void> pay(Order o) {
	    var event = new PaymentCompletedEvent(o.getId(), o.getPrice());
	    
	    return Mono.fromRunnable(() -> paymentCompletedPublisher.publish(event))
	               .subscribeOn(Schedulers.boundedElastic())
	               .then(orderRepository.delete(o));
	}
	
}
