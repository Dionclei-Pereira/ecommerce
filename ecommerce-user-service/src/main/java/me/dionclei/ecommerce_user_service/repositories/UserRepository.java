package me.dionclei.ecommerce_user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import me.dionclei.ecommerce_user_service.entities.EcommerceUser;

public interface UserRepository extends JpaRepository<EcommerceUser, Long> {
	UserDetails findByEmail(String email);
}
