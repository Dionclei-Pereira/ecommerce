package me.dionclei.ecommerce_payment_service.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import me.dionclei.ecommerce_payment_service.entities.Order;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}
