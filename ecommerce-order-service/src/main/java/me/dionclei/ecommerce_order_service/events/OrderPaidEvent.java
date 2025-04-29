package me.dionclei.ecommerce_order_service.events;

import me.dionclei.ecommerce_order_service.entities.OrderItem;

public record OrderPaidEvent(Long id, java.util.List<OrderItem> items) {

}
