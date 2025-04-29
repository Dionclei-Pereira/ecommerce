package me.dionclei.ecommerce_payment_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

import me.dionclei.ecommerce_payment_service.entities.Order;
import reactor.core.publisher.Mono;

@Configuration
public class RunConfig implements CommandLineRunner {
	
	@Autowired
	private R2dbcEntityTemplate template;

	public Mono<Void> saveOrder(Order order) {
	    return template.insert(Order.class).using(order).then();
	}
	
	@Override
	public void run(String... args) throws Exception {
		Order o = new Order("1", 522.23);
		saveOrder(o).subscribe();
	}
}
