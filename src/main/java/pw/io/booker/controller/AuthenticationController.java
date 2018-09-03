package pw.io.booker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pw.io.booker.model.Authentication;
import pw.io.booker.service.AuthenticationService;

@RestController
public class AuthenticationController {

	private AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@PostMapping("/login")
	public String login(@RequestBody Authentication authentication) {
		return authenticationService.login(authentication);
		}
	
	@GetMapping("/logout")
	public void logout(@RequestParam("token") String token) {
		authenticationService.logout(token);
	}
}
	

