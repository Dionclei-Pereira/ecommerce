package me.dionclei.ecommerce_user_service.filters;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.dionclei.ecommerce_user_service.entities.EcommerceUser;
import me.dionclei.ecommerce_user_service.repositories.UserRepository;
import me.dionclei.ecommerce_user_service.services.interfaces.TokenService;

@Component
public class AuthFilter extends OncePerRequestFilter {
	
	private TokenService service;

	private UserRepository repository;
	
	public AuthFilter(TokenService service, UserRepository repository) {
		this.service = service;
		this.repository = repository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    var token = request.getHeader("Authorization");

	    if (token != null) {
	        var subject = service.validateToken(token);
	        Optional<EcommerceUser> optionalUser = repository.findById(Long.parseLong(subject));

	        if (optionalUser.isPresent()) {
	            EcommerceUser user = optionalUser.get();
	            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	    }
	    
	    filterChain.doFilter(request, response);
	}

}
