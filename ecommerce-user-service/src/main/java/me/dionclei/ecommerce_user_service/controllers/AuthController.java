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
import me.dionclei.ecommerce_user_service.dto.LoginResponse;
import me.dionclei.ecommerce_user_service.dto.RegisterRequest;
import me.dionclei.ecommerce_user_service.entities.EcommerceUser;
import me.dionclei.ecommerce_user_service.enums.UserRole;
import me.dionclei.ecommerce_user_service.repositories.UserRepository;
import me.dionclei.ecommerce_user_service.services.interfaces.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthenticationManager manager;
	private UserRepository repository;
	private TokenService tokenService;
	
	public AuthController(AuthenticationManager manager, UserRepository repository, TokenService tokenService) {
		this.repository = repository;
		this.manager = manager;
		this.tokenService = tokenService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest data) {
		var userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = manager.authenticate(userPassword);
		var token = tokenService.generateToken((EcommerceUser) auth.getPrincipal());
		return ResponseEntity.ok().body(new LoginResponse(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
		if (repository.findByEmail(request.email()) != null) return ResponseEntity.badRequest().body("Email must be unique");
		EcommerceUser u = new EcommerceUser(null, request.name(), request.email(), request.password(), UserRole.USER);
		repository.save(u);
		return ResponseEntity.ok().build();
	}
}
