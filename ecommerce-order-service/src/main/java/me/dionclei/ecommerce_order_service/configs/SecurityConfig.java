package me.dionclei.ecommerce_order_service.configs;

import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import me.dionclei.auth.TokenService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity security, SecurityFilter filter) throws Exception {
		return security.csrf(c -> c.disable())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/api/orders").authenticated()
						.requestMatchers(HttpMethod.GET, "/api/orders").authenticated()
						.anyRequest().permitAll())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(e -> e.authenticationEntryPoint((request, response, ex) -> {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}))
				.build();
	}
	
	@Bean
	TokenService tokenService() throws Exception {
		return new TokenService(Paths.get("/app/public_key.pem"));
	}
}
