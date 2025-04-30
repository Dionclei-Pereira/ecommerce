package me.dionclei.ecommerce_inventory_service.services;

import java.util.List;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dionclei.ecommerce_inventory_service.dto.InventoryPostRequest;
import me.dionclei.ecommerce_inventory_service.entities.Inventory;
import me.dionclei.ecommerce_inventory_service.events.OrderItem;
import me.dionclei.ecommerce_inventory_service.exceptions.OutOfStockException;
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
		return inv != null && inv.getQuantity() >= quantity;
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

	@Transactional
	public void decrease(List<OrderItem> items) {
	    for (OrderItem item : items) {
	        Long id = item.productId();
	        if (!isInStock(id.toString(), item.quantity())) {
	            throw new OutOfStockException("Item with id: " + id + " is out of stock");
	        }
	    }

	    for (OrderItem item : items) {
	        var inv = inventoryRepository.findById(item.productId());
	        if (inv.isPresent()) {
	        	inv.get().setQuantity(inv.get().getQuantity() - item.quantity());
	        	inventoryRepository.save(inv.get());
	        }
	    }
	}
}
