package me.dionclei.ecommerce_payment_service.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import me.dionclei.auth.TokenService;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Bean
	SecurityWebFilterChain filterChain(ServerHttpSecurity config, SecurityFilter filter) {
		return config.csrf(c -> c.disable())
				.authorizeExchange(exchange -> exchange
						.anyExchange().authenticated())
				.addFilterBefore(filter, SecurityWebFiltersOrder.AUTHORIZATION)
				.build();
	}
	
	@Bean
	TokenService service() throws Exception {
		return new TokenService(Paths.get("/app/public_key.pem"));
	}
}