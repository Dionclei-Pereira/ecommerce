package me.dionclei.ecommerce_inventory_service.services.interfaces;

import me.dionclei.ecommerce_inventory_service.dto.InventoryPostRequest;
import me.dionclei.ecommerce_inventory_service.entities.Inventory;

public interface InventoryService {

	boolean isInStock(String productCode, Integer quantity);
	
	Integer getStock(String productCode);
	
	Integer patchStock(String productCode, Integer quantity);
	
	Inventory addInventory(InventoryPostRequest request);
}
