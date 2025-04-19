package me.dionclei.ecommerce_order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.dionclei.ecommerce_order_service.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query(nativeQuery = true, value = """
			SELECT * FROM orders WHERE user_id = :id;
			""")
	List<Order> findAllById(Long id);
	
}
