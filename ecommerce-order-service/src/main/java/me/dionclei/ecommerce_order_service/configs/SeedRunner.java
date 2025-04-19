package me.dionclei.ecommerce_order_service.configs;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import me.dionclei.ecommerce_order_service.entities.Order;
import me.dionclei.ecommerce_order_service.entities.OrderItem;
import me.dionclei.ecommerce_order_service.enums.Status;
import me.dionclei.ecommerce_order_service.repository.OrderRepository;

@Configuration
public class SeedRunner implements CommandLineRunner {
	
	@Autowired
	private OrderRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		OrderItem item = new OrderItem(null, 1L, 3, 100.0);
		List<OrderItem> list = new ArrayList<>();
		list.add(item);
		
		OrderItem item2 = new OrderItem(null, 1L, 3, 100.0);
		List<OrderItem> list2 = new ArrayList<>();
		list2.add(item2);
		
		Order o1 = new Order(null, 1L, Instant.now(), Status.PLACED, list);
		Order o2 = new Order(null, 2L, Instant.now(), Status.REJECTED, list2);
		repository.saveAll(Arrays.asList(o1, o2));
	}

}
