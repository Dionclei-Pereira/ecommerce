package me.dionclei.ecommerce_order_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ecommerce-inventory-service")
public interface InventoryClient {
	
    @GetMapping("/api/inventory")
    Boolean isInStock(@RequestParam String productCode, @RequestParam Integer quantity);
	
}
