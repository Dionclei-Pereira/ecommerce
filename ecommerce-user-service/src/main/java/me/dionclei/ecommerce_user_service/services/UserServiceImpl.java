package me.dionclei.ecommerce_user_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dionclei.ecommerce_user_service.dto.UserDTO;
import me.dionclei.ecommerce_user_service.exceptions.ResourceNotFoundException;
import me.dionclei.ecommerce_user_service.repositories.UserRepository;
import me.dionclei.ecommerce_user_service.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		var user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get().toDTO();
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(u -> u.toDTO()).toList();
	}
}
