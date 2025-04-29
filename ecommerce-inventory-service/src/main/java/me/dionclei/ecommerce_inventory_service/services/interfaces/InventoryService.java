package me.dionclei.ecommerce_inventory_service.services.interfaces;

import java.util.List;

import me.dionclei.ecommerce_inventory_service.dto.InventoryPostRequest;
import me.dionclei.ecommerce_inventory_service.entities.Inventory;
import me.dionclei.ecommerce_inventory_service.events.OrderItem;

public interface InventoryService {

	boolean isInStock(String productCode, Integer quantity);
	
	Integer getStock(String productCode);
	
	Integer patchStock(String productCode, Integer quantity);
	
	Inventory addInventory(InventoryPostRequest request);
	
	void decrease(List<OrderItem> items);
}
