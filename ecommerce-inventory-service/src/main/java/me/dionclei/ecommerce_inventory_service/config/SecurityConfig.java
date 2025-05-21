package me.dionclei.ecommerce_inventory_service.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import me.dionclei.auth.TokenService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity config, SecurityFilter filter) throws Exception {
		return config.csrf(c -> c.disable())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "api/inventory").permitAll()
						.requestMatchers(HttpMethod.GET, "api/inventory/{productCode}").permitAll()
						.requestMatchers(HttpMethod.POST, "api/inventory").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PATCH, "api/inventory/{productCode}").hasRole("ADMIN")
						.anyRequest().authenticated())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	TokenService service() throws Exception {
		return new TokenService(Paths.get("/app/public_key.pem"));
	}
}
