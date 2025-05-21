package me.dionclei.ecommerce_payment_service.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Order {
	
	@Id
	private String id;
	
	private Double price;

	public Order() {}
	
	public Order(@JsonProperty("id") String id, @JsonProperty("amount") double price) {
		super();
		this.id = id;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
