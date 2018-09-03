package pw.io.booker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pw.io.booker.exception.BookerServiceException;
import pw.io.booker.model.Authentication;
import pw.io.booker.model.Customer;
import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.util.TokenCreator;

public class AuthenticationService {
	
	private AuthenticationRepository authenticationRepository;
	private CustomerRepository customerRepository;
	private TokenCreator tokenCreator;
	
	public AuthenticationService(AuthenticationRepository authenticationRepository,
			CustomerRepository customerRepository, TokenCreator tokenCreator) {
		super();
		this.authenticationRepository = authenticationRepository;
		this.customerRepository = customerRepository;
		this.tokenCreator = tokenCreator;
	}
	
	
	// LOGIN
	public String login(Authentication authentication) throws BookerServiceException {
		String token = "";
		Optional<Customer> customer = customerRepository.findByUsernameAndPassword(
				authentication.getCustomer().getUsername(), 
				authentication.getCustomer().getPassword());
			if(!customer.isPresent()) {
				throw new BookerServiceException("Account not found!");
			}
			authenticationRepository.deleteAll(authenticationRepository.findByCustomer(customer.get()));
			token = tokenCreator.encode(customer.get());
			
			Authentication auth = new Authentication();
			auth.setCustomer(customer.get());
			auth.setLoginDate(LocalDate.now());
			auth.setToken(token);
			
			authenticationRepository.save(auth);
			
			return token;
	}
	
	// LOGOUT
	public void logout(String token) {
		Authentication authentication = authenticationRepository.findByToken(token).get();
		authenticationRepository.delete(authentication);
	}
	
}
