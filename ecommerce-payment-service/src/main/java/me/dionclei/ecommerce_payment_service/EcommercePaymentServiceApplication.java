package me.dionclei.ecommerce_payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcommercePaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercePaymentServiceApplication.class, args);
	}

}
