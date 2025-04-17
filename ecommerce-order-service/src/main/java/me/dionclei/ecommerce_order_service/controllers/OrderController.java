package me.dionclei.ecommerce_order_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dionclei.ecommerce_order_service.dto.OrderRequest;
import me.dionclei.ecommerce_order_service.services.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
    	this.orderService = orderService;
    }
    
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request) {
        try {
            orderService.placeOrder(request);
            return ResponseEntity.ok("Order created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
