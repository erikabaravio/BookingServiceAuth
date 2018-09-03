package pw.io.booker.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pw.io.booker.model.Authentication;
import pw.io.booker.model.Customer;
import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.util.TokenCreator;

@RestController
public class AuthenticationController {

	public AuthenticationRepository authenticationRepository;
	public CustomerRepository customerRepository;
	public TokenCreator tokenCreator;
	

	
	public AuthenticationController(AuthenticationRepository authenticationRepository,
			CustomerRepository customerRepository, TokenCreator tokenCreator) {
		super();
		this.authenticationRepository = authenticationRepository;
		this.customerRepository = customerRepository;
	}



	@PostMapping("/login")
	public String login(@RequestBody Authentication authentication) {
		String token = "";
		Optional<Customer> customer = customerRepository.findByUsernameAndPassword(authentication.getCustomer().getUsername(), authentication.getCustomer().getPassword());
			if(!customer.isPresent()) {
				throw new RuntimeException("Wrong username!");
			}
			authenticationRepository.deleteAll(authenticationRepository.findByCustomer(customer.get()));
			token = tokenCreator.encode(customer.get());
			
			Authentication postAuth = new Authentication();
			postAuth.setCustomer(customer.get());
			postAuth.setLoginDate(LocalDate.now());
			postAuth.setToken(token);
			
			authenticationRepository.save(postAuth);
			
			return token;
		}
	
	@GetMapping("/logout")
	public void logout(@RequestParam("token") String token) {
		Authentication authentication = authenticationRepository.findByToken(token).get();
		authenticationRepository.delete(authentication);
	}
	



}
	

