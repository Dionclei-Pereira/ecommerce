package me.dionclei.ecommerce_order_service.listerners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_order_service.dto.OrderItemDTO;
import me.dionclei.ecommerce_order_service.enums.Status;
import me.dionclei.ecommerce_order_service.events.OrderPaidEvent;
import me.dionclei.ecommerce_order_service.events.PaymentCompletedEvent;
import me.dionclei.ecommerce_order_service.publishers.OrderPaidPublisher;
import me.dionclei.ecommerce_order_service.services.interfaces.OrderService;

@Service
public class PaymentCompletedListener {
	
	private OrderPaidPublisher orderPaidPublisher;
	private OrderService service;
	
	public PaymentCompletedListener(OrderPaidPublisher orderPaidPublisher, OrderService service) {
		this.orderPaidPublisher = orderPaidPublisher;
		this.service = service;
	}

	@RabbitListener(queues = "payment.completed.queue")
	public void onPaymentCompleted(@Payload PaymentCompletedEvent event) {
		Long id = Long.parseLong(event.id());
		var order = service.findById(id);
		order.setStatus(Status.PAID);
		service.save(order);
		orderPaidPublisher.publish(new OrderPaidEvent(id, order.getItems()
				.stream().map(o -> new OrderItemDTO(o.getProductId(), o.getQuantity())).toList()));
	}
}
