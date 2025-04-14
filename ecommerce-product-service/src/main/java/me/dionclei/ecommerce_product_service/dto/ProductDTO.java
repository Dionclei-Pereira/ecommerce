package me.dionclei.ecommerce_product_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.dionclei.ecommerce_product_service.entities.Product;

public record ProductDTO(
		Long id,
		
		@NotBlank(message = "Name is required") 
		String name,
		
		@NotNull(message = "Price is required")
		@Min(value = 10, message = "Price must be above 10")
		Double price,
		
		@NotBlank(message = "Description is required")
		String description) {
	
	public Product toObj() {
		return new Product(null, name, price, description);
	}

}
