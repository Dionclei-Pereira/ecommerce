package me.dionclei.ecommerce_order_service.dto;

import java.util.List;

public record OrderRequest(List<OrderItemDTO> items) {
}
