package me.dionclei.ecommerce_order_service.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dionclei.ecommerce_order_service.clients.InventoryClient;
import me.dionclei.ecommerce_order_service.clients.ProductClient;
import me.dionclei.ecommerce_order_service.dto.OrderItemDTO;
import me.dionclei.ecommerce_order_service.dto.OrderRequest;
import me.dionclei.ecommerce_order_service.dto.ProductDTO;
import me.dionclei.ecommerce_order_service.entities.Order;
import me.dionclei.ecommerce_order_service.entities.OrderItem;
import me.dionclei.ecommerce_order_service.enums.Status;
import me.dionclei.ecommerce_order_service.exceptions.InventoryException;
import me.dionclei.ecommerce_order_service.exceptions.ProductException;
import me.dionclei.ecommerce_order_service.repository.OrderRepository;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final ProductClient productClient;
	private final InventoryClient inventoryClient;
	
	public OrderService(OrderRepository orderRepository, ProductClient productClient, InventoryClient inventoryClient) {
		this.inventoryClient = inventoryClient;
		this.productClient = productClient;
		this.orderRepository = orderRepository;
	}
	
	@Transactional
	public void placeOrder(OrderRequest request) {
		List<OrderItem> orderItems = new ArrayList<>();
		for (OrderItemDTO itemDto : request.items()) {
			ProductDTO product = null;
			try {
				product = productClient.getProduct(itemDto.productId());
			} catch (Exception e) {
				throw new ProductException("Product with id " + itemDto.productId() + " was not found");
			}
			
			boolean isInStock = inventoryClient.isInStock(itemDto.productId().toString(), itemDto.quantity());
			
            if (!isInStock) {
                throw new InventoryException("Product with id " + itemDto.productId() + " is out of stock");
            }
            
            OrderItem item = new OrderItem();
            item.setProductId(product.id());
            item.setPrice(product.price());
            item.setQuantity(itemDto.quantity());
            orderItems.add(item);
		}
        Order order = new Order();
        order.setOrderDate(Instant.now());
        order.setStatus(Status.PLACED);
        order.setItems(orderItems);

        orderRepository.save(order);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

}
