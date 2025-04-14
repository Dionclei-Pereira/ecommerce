package me.dionclei.ecommerce_inventory_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dionclei.ecommerce_inventory_service.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	Inventory findByProductCode(String productCode);
	
}
