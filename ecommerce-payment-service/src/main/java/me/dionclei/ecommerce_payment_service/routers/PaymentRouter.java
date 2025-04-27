package me.dionclei.ecommerce_payment_service.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class PaymentRouter {
	
	@Bean
	RouterFunction<ServerResponse> routeOrders(PaymentHandler handler) {
		return RouterFunctions.route(GET("api/payment/orders/{id}"), handler::findById)
				.andRoute(POST("api/payment/orders/{id}"), handler::payOrder);
	}
}
