package me.dionclei.ecommerce_product_service.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import me.dionclei.ecommerce_product_service.entities.Product;
import me.dionclei.ecommerce_product_service.repositories.ProductRepository;

@Configuration
public class ConfigRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Product p1 = new Product(null, "Computer", 1300.0, "A compurter");
		Product p2 = new Product(null, "Cellphone", 1500.0, "A cellphone");
		
		productRepository.saveAll(Arrays.asList(p1, p2));
		
	}

}
