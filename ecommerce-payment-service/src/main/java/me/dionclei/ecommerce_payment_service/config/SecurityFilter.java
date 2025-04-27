package me.dionclei.ecommerce_payment_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import me.dionclei.auth.TokenService;
import reactor.core.publisher.Mono;

@Component
public class SecurityFilter implements WebFilter {
	
	@Autowired
	private TokenService service;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String token = exchange.getRequest().getHeaders().getFirst("Authorization");
		if (token != null) {
			var user = service.validateToken(token);
			if (user != null) {
				var roles = user.roles().stream().map(SimpleGrantedAuthority::new).toList();
				var auth = new UsernamePasswordAuthenticationToken(user.id(), null, roles);
				return chain.filter(exchange)
						.contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
			
			}
		}
		return chain.filter(exchange);
	}

}
