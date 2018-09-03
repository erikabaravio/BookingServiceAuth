package pw.io.booker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.service.AuthenticationService;
import pw.io.booker.util.TokenCreator;

@Configuration
public class AuthenticationConfig {

	
	@Bean
	public AuthenticationService authenticationService(AuthenticationRepository authenticationRepository,
			CustomerRepository customerRepository, TokenCreator tokenCreator) {
		return new AuthenticationService(authenticationRepository, customerRepository, tokenCreator);
	}
}
