package me.dionclei.ecommerce_user_service.services.interfaces;

import java.util.List;

import me.dionclei.ecommerce_user_service.dto.UserDTO;

public interface UserService {
	
	UserDTO findById(Long id);
	
	List<UserDTO> findAll();
	
}
