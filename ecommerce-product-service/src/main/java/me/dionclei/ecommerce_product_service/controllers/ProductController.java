package me.dionclei.ecommerce_product_service.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import me.dionclei.ecommerce_product_service.dto.ProductDTO;
import me.dionclei.ecommerce_product_service.repositories.ProductRepository;

@RestController
@RequestMapping("api/products")
public class ProductController {
	
	private final ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(p -> p.toDTO()).toList();
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO product) {
        return productRepository.save(product.toObj()).toDTO();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productRepository.findById(id).map(p -> p.toDTO())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
}
