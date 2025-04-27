package me.dionclei.ecommerce_payment_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

@Configuration
public class DataBaseConfig implements CommandLineRunner {
	
    private final ConnectionFactory connectionFactory;

    public DataBaseConfig(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
	
	@Override
	public void run(String... args) throws Exception {
		String query =  """
				CREATE TABLE Orders(
					id TEXT PRIMARY KEY,
					price REAL
				);
				""";
		Mono.from(connectionFactory.create())
        .flatMap(connection -> Mono.from(connection.createStatement(query).execute())
                                   .doFinally(signalType -> connection.close()))
        .block();
	}

}
