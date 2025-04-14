package me.dionclei.ecommerce_product_service.services.interfaces;

import java.util.List;

import me.dionclei.ecommerce_product_service.dto.ProductDTO;
import me.dionclei.ecommerce_product_service.entities.Product;
import me.dionclei.ecommerce_product_service.exceptions.ResourceNotFoundException;

public interface ProductService {
	
	ProductDTO save(Product product);
	
	List<ProductDTO> findAll();
	
	ProductDTO findById(Long id) throws ResourceNotFoundException;
}
