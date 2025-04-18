package me.dionclei.ecommerce_user_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.dionclei.ecommerce_user_service.dto.LoginRequest;
import me.dionclei.ecommerce_user_service.dto.RegisterRequest;
import me.dionclei.ecommerce_user_service.entities.EcommerceUser;
import me.dionclei.ecommerce_user_service.enums.UserRole;
import me.dionclei.ecommerce_user_service.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthenticationManager manager;
	private UserRepository repository;
	
	public AuthController(AuthenticationManager manager, UserRepository repository) {
		this.repository = repository;
		this.manager = manager;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody LoginRequest data) {
		var userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = manager.authenticate(userPassword);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {

		EcommerceUser u = new EcommerceUser(null, request.name(), request.email(), request.password(), UserRole.USER);
		repository.save(u);
		return ResponseEntity.ok().build();
	}
}
