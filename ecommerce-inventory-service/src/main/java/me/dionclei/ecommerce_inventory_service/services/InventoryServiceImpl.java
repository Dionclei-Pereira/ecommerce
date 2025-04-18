package me.dionclei.ecommerce_inventory_service.services;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dionclei.ecommerce_inventory_service.dto.InventoryPostRequest;
import me.dionclei.ecommerce_inventory_service.entities.Inventory;
import me.dionclei.ecommerce_inventory_service.exceptions.ResourceNotFoundException;
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
	
	public Integer getStock(String productCode) {
		Inventory inv = inventoryRepository.findByProductCode(productCode);
		return inv != null ? inv.getQuantity() : 0;
	}
	
	@Transactional
	public Integer patchStock(String productCode, Integer quantity) {
		Inventory inv = inventoryRepository.findByProductCode(productCode);
		if (inv == null) throw new ResourceNotFoundException("Inventory not found");
		inv.setQuantity(inv.getQuantity() + quantity);
		inventoryRepository.save(inv);
		return inv.getQuantity();
	}

	@Transactional
	public Inventory addInventory(InventoryPostRequest request) {
		if (request == null) throw new NullArgumentException("Inventory request is null");
		Inventory inv = new Inventory(null, request.productCode(), request.quantity());
		inventoryRepository.save(inv);
		return inv;
	}
	
}
