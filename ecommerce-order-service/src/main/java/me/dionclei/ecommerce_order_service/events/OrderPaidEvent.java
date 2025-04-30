package me.dionclei.ecommerce_order_service.events;

import me.dionclei.ecommerce_order_service.dto.OrderItemDTO;

public record OrderPaidEvent(Long id, java.util.List<OrderItemDTO> items) {

}
