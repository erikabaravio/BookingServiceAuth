package pw.io.booker.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Authentication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authenticationId;
	private String token;
	private LocalDate loginDate;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Customer customer;
	
	
	public int getAuthenticationId() {
		return authenticationId;
	}
	public void setAuthenticationId(int authenticationId) {
		this.authenticationId = authenticationId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDate getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(LocalDate loginDate) {
		this.loginDate = loginDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
