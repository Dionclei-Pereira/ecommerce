package me.dionclei.ecommerce_product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dionclei.ecommerce_product_service.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
