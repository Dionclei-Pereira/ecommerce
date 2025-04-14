package me.dionclei.ecommerce_product_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import me.dionclei.ecommerce_product_service.dto.ProductDTO;
import me.dionclei.ecommerce_product_service.entities.Product;
import me.dionclei.ecommerce_product_service.exceptions.ResourceNotFoundException;
import me.dionclei.ecommerce_product_service.repositories.ProductRepository;
import me.dionclei.ecommerce_product_service.services.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public ProductDTO save(Product product) {
		return productRepository.save(product).toDTO();
	}

	@Override
	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream().map(p -> p.toDTO()).toList();
	}

	@Override
	public ProductDTO findById(Long id) throws ResourceNotFoundException {
		var product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get().toDTO();
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}

}
