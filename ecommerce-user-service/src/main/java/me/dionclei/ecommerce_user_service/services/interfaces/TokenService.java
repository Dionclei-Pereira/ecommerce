package me.dionclei.ecommerce_user_service.services.interfaces;

import me.dionclei.ecommerce_user_service.entities.EcommerceUser;

public interface TokenService {
	
	String generateToken(EcommerceUser user);
	
}
