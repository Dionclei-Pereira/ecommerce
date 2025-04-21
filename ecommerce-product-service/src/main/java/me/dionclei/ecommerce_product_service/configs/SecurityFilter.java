package me.dionclei.ecommerce_product_service.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.dionclei.auth.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = request.getHeader("Authorization");
		if (token != null) {
			var user = service.validateToken(token);
			if (user != null) {
				var roles = user.roles().stream().map(SimpleGrantedAuthority::new).toList();
				var auth = new UsernamePasswordAuthenticationToken(user.id(), null, roles);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}

}
