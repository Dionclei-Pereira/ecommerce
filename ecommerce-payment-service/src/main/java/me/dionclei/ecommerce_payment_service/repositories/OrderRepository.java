package me.dionclei.ecommerce_payment_service.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import me.dionclei.ecommerce_payment_service.entities.Order;

public interface OrderRepository extends R2dbcRepository<Order, String> {

}
