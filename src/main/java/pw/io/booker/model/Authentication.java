package pw.io.booker.model;

import java.time.LocalDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Authentication {

	private String token;
	private LocalDate loginDate;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

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
