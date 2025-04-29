package me.dionclei.ecommerce_order_service.controllers;



import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dionclei.ecommerce_order_service.dto.OrderRequest;
import me.dionclei.ecommerce_order_service.entities.Order;
import me.dionclei.ecommerce_order_service.services.interfaces.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
    	this.orderService = orderService;
    }
    
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request, Principal principal) {
        try {
            orderService.placeOrder(request, principal.getName());
            return ResponseEntity.ok("Order created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Order>> findAll(Principal principal) {
        return ResponseEntity.ok().body(orderService.findAll(Long.parseLong(principal.getName())));
    }

}
