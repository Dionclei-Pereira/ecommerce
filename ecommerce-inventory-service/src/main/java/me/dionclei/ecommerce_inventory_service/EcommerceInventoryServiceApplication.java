package me.dionclei.ecommerce_inventory_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcommerceInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceInventoryServiceApplication.class, args);
	}

}
