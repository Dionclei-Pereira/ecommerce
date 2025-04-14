package me.dionclei.ecommerce_inventory_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.dionclei.ecommerce_inventory_service.services.interfaces.InventoryService;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
	
    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam String productCode) {
        boolean available = service.isInStock(productCode);
        return ResponseEntity.ok(available);
    }
}
