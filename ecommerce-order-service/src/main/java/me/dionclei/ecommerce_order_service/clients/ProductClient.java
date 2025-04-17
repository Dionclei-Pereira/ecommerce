package me.dionclei.ecommerce_order_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import me.dionclei.ecommerce_order_service.dto.ProductDTO;

@FeignClient(name = "ecommerce-product-service")
public interface ProductClient {
	
    @GetMapping("/api/products/{id}")
    ProductDTO getProduct(@PathVariable Long id);
    
}
