package me.dionclei.ecommerce_inventory_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import me.dionclei.ecommerce_inventory_service.entities.Inventory;
import me.dionclei.ecommerce_inventory_service.repositories.InventoryRepository;

@Configuration
public class ConfigRunner implements CommandLineRunner {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	public void run(String... args) throws Exception {
		
		inventoryRepository.save(new Inventory(null, "1", 2));
		inventoryRepository.save(new Inventory(null, "2", 50));
		
	}
}
