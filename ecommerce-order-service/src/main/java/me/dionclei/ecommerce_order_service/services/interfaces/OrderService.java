package me.dionclei.ecommerce_order_service.services.interfaces;

import java.util.List;

import me.dionclei.ecommerce_order_service.dto.OrderRequest;
import me.dionclei.ecommerce_order_service.entities.Order;

public interface OrderService {
	
	void placeOrder(OrderRequest request, String id);
	
	List<Order> findAll(Long id);
	
	Order findById(Long id);
	
	Order save(Order order);
}
