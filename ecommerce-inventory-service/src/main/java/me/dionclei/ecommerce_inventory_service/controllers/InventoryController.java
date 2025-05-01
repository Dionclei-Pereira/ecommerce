package me.dionclei.ecommerce_inventory_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.dionclei.ecommerce_inventory_service.dto.InventoryPatchRequest;
import me.dionclei.ecommerce_inventory_service.dto.InventoryPostRequest;
import me.dionclei.ecommerce_inventory_service.entities.Inventory;
import me.dionclei.ecommerce_inventory_service.exceptions.ResourceNotFoundException;
import me.dionclei.ecommerce_inventory_service.services.interfaces.InventoryService;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
	
    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam String productCode, @RequestParam(defaultValue = "1") Integer quantity) {
        if (productCode == null || productCode.isBlank()) return ResponseEntity.ok(false);
    	boolean available = service.isInStock(productCode, quantity);
        return ResponseEntity.ok(available);
    }
     
    @GetMapping("/{productCode}")
    public ResponseEntity<Integer> getStock(@PathVariable String productCode) {
        Integer quantity = service.getStock(productCode);
        return ResponseEntity.ok(quantity);
    }
    
    @PatchMapping("/{productCode}")
    public ResponseEntity<Integer> patchStock(@PathVariable String productCode, @RequestBody InventoryPatchRequest request) {
        try {
        	Integer quantity = service.patchStock(productCode, request.quantity());
            return ResponseEntity.ok(quantity);
        } catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
    }
    
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody InventoryPostRequest inventoryDTO) {
    	try {
    		Inventory inv = service.addInventory(inventoryDTO);
    		return ResponseEntity.ok().body(inv);
    	} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
    }
}
