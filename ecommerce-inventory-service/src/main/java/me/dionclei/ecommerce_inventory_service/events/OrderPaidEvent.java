package me.dionclei.ecommerce_inventory_service.events;
import java.util.ArrayList;

public record OrderPaidEvent(Long id, ArrayList<OrderItem> items) {

}