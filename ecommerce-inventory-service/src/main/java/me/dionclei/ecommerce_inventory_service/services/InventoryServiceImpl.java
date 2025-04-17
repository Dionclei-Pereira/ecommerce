package me.dionclei.ecommerce_inventory_service.services;

import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_inventory_service.entities.Inventory;
import me.dionclei.ecommerce_inventory_service.repositories.InventoryRepository;
import me.dionclei.ecommerce_inventory_service.services.interfaces.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;
	
	public InventoryServiceImpl(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}
	
	public boolean isInStock(String productCode, Integer quantity) {
		Inventory inv = inventoryRepository.findByProductCode(productCode);
		return inv != null && inv.getQuantity() > quantity;
	}

}
