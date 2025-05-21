package me.dionclei.ecommerce_payment_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import me.dionclei.ecommerce_payment_service.entities.Order;

@Configuration
public class RunConfig implements CommandLineRunner {

    @Autowired
    private ReactiveMongoTemplate template;

    private void resetDatabase() {
        template.dropCollection(Order.class)
                .then(template.insert(new Order("1", 522.23)))
                .subscribe();
    }

	@Override
	public void run(String... args) throws Exception {
		resetDatabase();
	}
}
