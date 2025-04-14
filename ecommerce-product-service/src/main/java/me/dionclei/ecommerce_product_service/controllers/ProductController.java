package me.dionclei.ecommerce_product_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.dionclei.ecommerce_product_service.dto.ProductDTO;
import me.dionclei.ecommerce_product_service.exceptions.ResourceNotFoundException;
import me.dionclei.ecommerce_product_service.services.interfaces.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {
	
	private final ProductService prodService;
	
	public ProductController(ProductService prodService) {
		this.prodService = prodService;
	}
	
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok().body(prodService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO product) {
    	try {
    		return ResponseEntity.ok().body(prodService.save(product.toObj()));
    	} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
			return ResponseEntity.badRequest().build();
		}
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    	try {
    		return ResponseEntity.ok(prodService.findById(id));
    	} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
    }
}
