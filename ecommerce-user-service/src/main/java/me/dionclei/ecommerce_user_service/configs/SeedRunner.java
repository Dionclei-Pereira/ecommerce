package me.dionclei.ecommerce_user_service.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import me.dionclei.ecommerce_user_service.entities.EcommerceUser;
import me.dionclei.ecommerce_user_service.enums.UserRole;
import me.dionclei.ecommerce_user_service.repositories.UserRepository;

@Configuration
public class SeedRunner implements CommandLineRunner {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		EcommerceUser u1 = new EcommerceUser(null, "Dionclei de Souza", "dionclei2@gmail.com", "12345678", UserRole.ADMIN);
		EcommerceUser u2 = new EcommerceUser(null, "Pedro da Silva", "pedro@gmail.com", "123456788", UserRole.USER);
		repository.saveAll(Arrays.asList(u1, u2));
	}
}
