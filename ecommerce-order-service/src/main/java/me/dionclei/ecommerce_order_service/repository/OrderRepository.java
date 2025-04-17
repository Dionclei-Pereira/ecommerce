package me.dionclei.ecommerce_order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dionclei.ecommerce_order_service.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
