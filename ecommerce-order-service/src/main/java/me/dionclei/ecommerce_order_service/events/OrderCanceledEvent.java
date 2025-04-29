package me.dionclei.ecommerce_order_service.events;

public record OrderCanceledEvent(String id, Double price) {

}
