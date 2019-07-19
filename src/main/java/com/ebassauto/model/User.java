package com.ebassauto.model;

import org.springframework.stereotype.Component;

@Component
public class User {

	private String username, password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}