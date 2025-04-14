package me.dionclei.ecommerce_inventory_service.config;

import org.springframework.boot.CommandLineRunner;

import me.dionclei.ecommerce_inventory_service.entities.Inventory;
import me.dionclei.ecommerce_inventory_service.repositories.InventoryRepository;

public class ConfigRunner implements CommandLineRunner {

	private InventoryRepository inventoryRepository;
	
	public void run(String... args) throws Exception {
		
		inventoryRepository.save(new Inventory(null, "1", 100));
		inventoryRepository.save(new Inventory(null, "2", 50));
		
	}
}
